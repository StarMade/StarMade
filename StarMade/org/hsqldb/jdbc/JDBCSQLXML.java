package org.hsqldb.jdbc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.xml.bind.util.JAXBResult;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.hsqldb.lib.ClosableByteArrayOutputStream;
import org.hsqldb.lib.StringConverter;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class JDBCSQLXML
  implements SQLXML
{
  private static String domFeatures = "XML 3.0 Traversal +Events 2.0";
  private static DOMImplementation domImplementation;
  private static DOMImplementationRegistry domImplementationRegistry;
  private static ThreadPoolExecutor executorService;
  private static Transformer identityTransformer;
  private static TransformerFactory transformerFactory;
  private static final Charset utf8Charset = localCharset;
  private static ArrayBlockingQueue<Runnable> workQueue;
  private SAX2DOMBuilder builder;
  private boolean closed;
  private volatile byte[] gzdata;
  private InputStream inputStream;
  private ClosableByteArrayOutputStream outputStream;
  private DOMResult domResult;
  private String publicId;
  private boolean readable;
  private String systemId;
  private boolean writable;
  
  protected JDBCSQLXML()
  {
    setReadable(false);
    setWritable(true);
  }
  
  protected JDBCSQLXML(byte[] paramArrayOfByte)
    throws SQLException
  {
    this(paramArrayOfByte, null);
  }
  
  protected JDBCSQLXML(char[] paramArrayOfChar)
    throws SQLException
  {
    this(paramArrayOfChar, 0, paramArrayOfChar.length, null);
  }
  
  protected JDBCSQLXML(Document paramDocument)
    throws SQLException
  {
    this(new DOMSource(paramDocument));
  }
  
  protected JDBCSQLXML(InputStream paramInputStream)
    throws SQLException
  {
    this(paramInputStream, null);
  }
  
  protected JDBCSQLXML(Reader paramReader)
    throws SQLException
  {
    this(paramReader, null);
  }
  
  public JDBCSQLXML(Source paramSource)
    throws SQLException
  {
    init(paramSource);
  }
  
  protected JDBCSQLXML(String paramString)
    throws SQLException
  {
    this(new StreamSource(new StringReader(paramString)));
  }
  
  protected JDBCSQLXML(byte[] paramArrayOfByte, String paramString)
    throws SQLException
  {
    this(new StreamSource(new ByteArrayInputStream(paramArrayOfByte), paramString));
  }
  
  protected JDBCSQLXML(char[] paramArrayOfChar, String paramString)
    throws SQLException
  {
    this(paramArrayOfChar, 0, paramArrayOfChar.length, paramString);
  }
  
  protected JDBCSQLXML(InputStream paramInputStream, String paramString)
    throws SQLException
  {
    this(new StreamSource(paramInputStream, paramString));
  }
  
  protected JDBCSQLXML(Reader paramReader, String paramString)
    throws SQLException
  {
    this(new StreamSource(paramReader, paramString));
  }
  
  protected JDBCSQLXML(String paramString1, String paramString2)
    throws SQLException
  {
    this(new StreamSource(new StringReader(paramString1), paramString2));
  }
  
  protected JDBCSQLXML(byte[] paramArrayOfByte, boolean paramBoolean, String paramString1, String paramString2)
    throws SQLException
  {
    setGZipData(paramBoolean ? (byte[])paramArrayOfByte.clone() : paramArrayOfByte);
    this.systemId = paramString1;
    this.publicId = paramString2;
  }
  
  protected JDBCSQLXML(char[] paramArrayOfChar, int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
    this(new StreamSource(new CharArrayReader(paramArrayOfChar, paramInt1, paramInt2), paramString));
  }
  
  public void free()
    throws SQLException
  {
    close();
  }
  
  public synchronized InputStream getBinaryStream()
    throws SQLException
  {
    checkClosed();
    checkReadable();
    InputStream localInputStream = getBinaryStreamImpl();
    setReadable(false);
    setWritable(false);
    return localInputStream;
  }
  
  public synchronized OutputStream setBinaryStream()
    throws SQLException
  {
    checkClosed();
    checkWritable();
    OutputStream localOutputStream = setBinaryStreamImpl();
    setWritable(false);
    setReadable(true);
    return localOutputStream;
  }
  
  public synchronized Reader getCharacterStream()
    throws SQLException
  {
    checkClosed();
    checkReadable();
    Reader localReader = getCharacterStreamImpl();
    setReadable(false);
    setWritable(false);
    return localReader;
  }
  
  public synchronized Writer setCharacterStream()
    throws SQLException
  {
    checkClosed();
    checkWritable();
    Writer localWriter = setCharacterStreamImpl();
    setReadable(true);
    setWritable(false);
    return localWriter;
  }
  
  public synchronized String getString()
    throws SQLException
  {
    checkClosed();
    checkReadable();
    String str = getStringImpl();
    setReadable(false);
    setWritable(false);
    return str;
  }
  
  public synchronized void setString(String paramString)
    throws SQLException
  {
    if (paramString == null) {
      throw Util.nullArgument("value");
    }
    checkWritable();
    setStringImpl(paramString);
    setReadable(true);
    setWritable(false);
  }
  
  public synchronized <T extends Source> T getSource(Class<T> paramClass)
    throws SQLException
  {
    checkClosed();
    checkReadable();
    Source localSource = getSourceImpl(paramClass);
    setReadable(false);
    setWritable(false);
    return localSource;
  }
  
  public synchronized <T extends Result> T setResult(Class<T> paramClass)
    throws SQLException
  {
    checkClosed();
    checkWritable();
    Result localResult = createResult(paramClass);
    setReadable(true);
    setWritable(false);
    return localResult;
  }
  
  protected static ExecutorService getExecutorService()
  {
    if (executorService == null)
    {
      int i = 1;
      int j = 10;
      long l = 1L;
      TimeUnit localTimeUnit = TimeUnit.SECONDS;
      workQueue = new ArrayBlockingQueue(10);
      executorService = new ThreadPoolExecutor(i, j, l, localTimeUnit, workQueue);
    }
    return executorService;
  }
  
  protected static TransformerFactory getTransformerFactory()
    throws SQLException
  {
    if (transformerFactory == null) {
      try
      {
        transformerFactory = TransformerFactory.newInstance();
      }
      catch (TransformerFactoryConfigurationError localTransformerFactoryConfigurationError)
      {
        throw Exceptions.transformFailed(localTransformerFactoryConfigurationError);
      }
    }
    return transformerFactory;
  }
  
  protected static Transformer getIdentityTransformer()
    throws SQLException
  {
    if (identityTransformer == null) {
      try
      {
        identityTransformer = getTransformerFactory().newTransformer();
      }
      catch (TransformerConfigurationException localTransformerConfigurationException)
      {
        throw Exceptions.transformFailed(localTransformerConfigurationException);
      }
    }
    return identityTransformer;
  }
  
  protected static DOMImplementationRegistry getDOMImplementationRegistry()
    throws SQLException
  {
    if (domImplementationRegistry == null) {
      try
      {
        domImplementationRegistry = DOMImplementationRegistry.newInstance();
      }
      catch (ClassCastException localClassCastException)
      {
        throw Exceptions.domInstantiation(localClassCastException);
      }
      catch (InstantiationException localInstantiationException)
      {
        throw Exceptions.domInstantiation(localInstantiationException);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw Exceptions.domInstantiation(localClassNotFoundException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw Exceptions.domInstantiation(localIllegalAccessException);
      }
    }
    return domImplementationRegistry;
  }
  
  protected static DOMImplementation getDOMImplementation()
    throws SQLException
  {
    if (domImplementation == null) {
      domImplementation = getDOMImplementationRegistry().getDOMImplementation(domFeatures);
    }
    if (domImplementation == null)
    {
      RuntimeException localRuntimeException = new RuntimeException("Not supported: " + domFeatures);
      throw Exceptions.domInstantiation(localRuntimeException);
    }
    return domImplementation;
  }
  
  protected static Document createDocument(String paramString1, String paramString2, DocumentType paramDocumentType)
    throws SQLException
  {
    try
    {
      return getDOMImplementation().createDocument(paramString1, paramString2, paramDocumentType);
    }
    catch (DOMException localDOMException)
    {
      throw Exceptions.domInstantiation(localDOMException);
    }
  }
  
  protected static Document createDocument()
    throws SQLException
  {
    return createDocument(null, null, null);
  }
  
  protected void init(Source paramSource)
    throws SQLException
  {
    if (paramSource == null) {
      throw Util.nullArgument("source");
    }
    Transformer localTransformer = getIdentityTransformer();
    StreamResult localStreamResult = new StreamResult();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    GZIPOutputStream localGZIPOutputStream;
    try
    {
      localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
    }
    catch (IOException localIOException1)
    {
      throw Exceptions.transformFailed(localIOException1);
    }
    localStreamResult.setOutputStream(localGZIPOutputStream);
    try
    {
      localTransformer.transform(paramSource, localStreamResult);
    }
    catch (TransformerException localTransformerException)
    {
      throw Exceptions.transformFailed(localTransformerException);
    }
    try
    {
      localGZIPOutputStream.close();
    }
    catch (IOException localIOException2)
    {
      throw Exceptions.transformFailed(localIOException2);
    }
    byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
    setGZipData(arrayOfByte);
    setReadable(true);
    setWritable(false);
  }
  
  protected void setGZipData(byte[] paramArrayOfByte)
    throws SQLException
  {
    if (paramArrayOfByte == null) {
      throw Util.nullArgument("data");
    }
    this.gzdata = paramArrayOfByte;
  }
  
  protected byte[] gZipData()
  {
    return this.gzdata;
  }
  
  protected byte[] getGZipData()
    throws SQLException
  {
    byte[] arrayOfByte = gZipData();
    if (arrayOfByte != null) {
      return arrayOfByte;
    }
    Object localObject1;
    if (this.domResult != null)
    {
      localObject1 = new DOMSource(this.domResult.getNode(), this.domResult.getSystemId());
      OutputStream localOutputStream = setBinaryStreamImpl();
      StreamResult localStreamResult = new StreamResult(localOutputStream);
      try
      {
        identityTransformer.transform((Source)localObject1, localStreamResult);
      }
      catch (TransformerException localTransformerException)
      {
        throw Exceptions.transformFailed(localTransformerException);
      }
      try
      {
        localOutputStream.close();
      }
      catch (IOException localIOException2)
      {
        throw Exceptions.transformFailed(localIOException2);
      }
    }
    if (this.outputStream == null) {
      throw Exceptions.notReadable("No Data.");
    }
    if (!this.outputStream.isClosed()) {
      throw Exceptions.notReadable("Stream used for writing must be closed but is still open.");
    }
    if (this.outputStream.isFreed()) {
      throw Exceptions.notReadable("Stream used for writing was freed and is no longer valid.");
    }
    try
    {
      setGZipData(this.outputStream.toByteArray());
      localObject1 = gZipData();
      return localObject1;
    }
    catch (IOException localIOException1)
    {
      throw Exceptions.notReadable();
    }
    finally
    {
      freeOutputStream();
    }
  }
  
  protected synchronized void close()
  {
    this.closed = true;
    setReadable(false);
    setWritable(false);
    freeOutputStream();
    freeInputStream();
    freeDomResult();
    this.gzdata = null;
  }
  
  protected void freeInputStream()
  {
    if (this.inputStream != null) {
      try
      {
        this.inputStream.close();
      }
      catch (IOException localIOException) {}finally
      {
        this.inputStream = null;
      }
    }
  }
  
  protected void freeOutputStream()
  {
    if (this.outputStream != null)
    {
      try
      {
        this.outputStream.free();
      }
      catch (IOException localIOException) {}
      this.outputStream = null;
    }
  }
  
  protected synchronized void checkClosed()
    throws SQLException
  {
    if (this.closed) {
      throw Exceptions.inFreedState();
    }
  }
  
  protected synchronized void checkReadable()
    throws SQLException
  {
    if (!isReadable()) {
      throw Exceptions.notReadable();
    }
  }
  
  protected synchronized void setReadable(boolean paramBoolean)
  {
    this.readable = paramBoolean;
  }
  
  protected synchronized void checkWritable()
    throws SQLException
  {
    if (!isWritable()) {
      throw Exceptions.notWritable();
    }
  }
  
  protected synchronized void setWritable(boolean paramBoolean)
  {
    this.writable = paramBoolean;
  }
  
  public synchronized boolean isReadable()
  {
    return this.readable;
  }
  
  public synchronized boolean isWritable()
  {
    return this.writable;
  }
  
  protected InputStream getBinaryStreamImpl()
    throws SQLException
  {
    try
    {
      byte[] arrayOfByte = getGZipData();
      ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
      return new GZIPInputStream(localByteArrayInputStream);
    }
    catch (IOException localIOException)
    {
      throw Exceptions.transformFailed(localIOException);
    }
  }
  
  protected Reader getCharacterStreamImpl()
    throws SQLException
  {
    return new InputStreamReader(getBinaryStreamImpl());
  }
  
  protected String getStringImpl()
    throws SQLException
  {
    try
    {
      return StringConverter.inputStreamToString(getBinaryStreamImpl(), "US-ASCII");
    }
    catch (IOException localIOException)
    {
      throw Exceptions.transformFailed(localIOException);
    }
  }
  
  protected OutputStream setBinaryStreamImpl()
    throws SQLException
  {
    this.outputStream = new ClosableByteArrayOutputStream();
    try
    {
      return new GZIPOutputStream(this.outputStream);
    }
    catch (IOException localIOException)
    {
      this.outputStream = null;
      throw Exceptions.resultInstantiation(localIOException);
    }
  }
  
  protected Writer setCharacterStreamImpl()
    throws SQLException
  {
    return new OutputStreamWriter(setBinaryStreamImpl());
  }
  
  protected void setStringImpl(String paramString)
    throws SQLException
  {
    init(new StreamSource(new StringReader(paramString)));
  }
  
  protected <T extends Source> T getSourceImpl(Class<T> paramClass)
    throws SQLException
  {
    if (!JAXBSource.class.isAssignableFrom(paramClass))
    {
      if (StreamSource.class.isAssignableFrom(paramClass)) {
        return createStreamSource(paramClass);
      }
      if ((paramClass == null) || (DOMSource.class.isAssignableFrom(paramClass))) {
        return createDOMSource(paramClass);
      }
      if (SAXSource.class.isAssignableFrom(paramClass)) {
        return createSAXSource(paramClass);
      }
      if (StAXSource.class.isAssignableFrom(paramClass)) {
        return createStAXSource(paramClass);
      }
    }
    throw Util.invalidArgument("sourceClass: " + paramClass);
  }
  
  protected <T extends Source> T createStreamSource(Class<T> paramClass)
    throws SQLException
  {
    StreamSource localStreamSource = null;
    try
    {
      localStreamSource = paramClass == null ? new StreamSource() : (StreamSource)paramClass.newInstance();
    }
    catch (SecurityException localSecurityException)
    {
      throw Exceptions.sourceInstantiation(localSecurityException);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw Exceptions.sourceInstantiation(localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw Exceptions.sourceInstantiation(localIllegalAccessException);
    }
    catch (ClassCastException localClassCastException)
    {
      throw Exceptions.sourceInstantiation(localClassCastException);
    }
    Reader localReader = getCharacterStreamImpl();
    localStreamSource.setReader(localReader);
    return localStreamSource;
  }
  
  protected <T extends Source> T createDOMSource(Class<T> paramClass)
    throws SQLException
  {
    DOMSource localDOMSource = null;
    try
    {
      localDOMSource = paramClass == null ? new DOMSource() : (DOMSource)paramClass.newInstance();
    }
    catch (SecurityException localSecurityException)
    {
      throw Exceptions.sourceInstantiation(localSecurityException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw Exceptions.sourceInstantiation(localIllegalAccessException);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw Exceptions.sourceInstantiation(localInstantiationException);
    }
    catch (ClassCastException localClassCastException)
    {
      throw Exceptions.sourceInstantiation(localClassCastException);
    }
    Transformer localTransformer = getIdentityTransformer();
    InputStream localInputStream = getBinaryStreamImpl();
    StreamSource localStreamSource = new StreamSource();
    DOMResult localDOMResult = new DOMResult();
    localStreamSource.setInputStream(localInputStream);
    try
    {
      localTransformer.transform(localStreamSource, localDOMResult);
    }
    catch (TransformerException localTransformerException)
    {
      throw Exceptions.transformFailed(localTransformerException);
    }
    localDOMSource.setNode(localDOMResult.getNode());
    localDOMSource.setSystemId(localDOMResult.getSystemId());
    return localDOMSource;
  }
  
  protected <T extends Source> T createSAXSource(Class<T> paramClass)
    throws SQLException
  {
    SAXSource localSAXSource = null;
    try
    {
      localSAXSource = paramClass == null ? new SAXSource() : (SAXSource)paramClass.newInstance();
    }
    catch (SecurityException localSecurityException)
    {
      throw Exceptions.sourceInstantiation(localSecurityException);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw Exceptions.sourceInstantiation(localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw Exceptions.sourceInstantiation(localIllegalAccessException);
    }
    catch (ClassCastException localClassCastException)
    {
      throw Exceptions.sourceInstantiation(localClassCastException);
    }
    Reader localReader = getCharacterStreamImpl();
    InputSource localInputSource = new InputSource(localReader);
    localSAXSource.setInputSource(localInputSource);
    return localSAXSource;
  }
  
  protected <T extends Source> T createStAXSource(Class<T> paramClass)
    throws SQLException
  {
    StAXSource localStAXSource = null;
    Constructor localConstructor = null;
    Reader localReader = null;
    XMLInputFactory localXMLInputFactory = null;
    XMLEventReader localXMLEventReader = null;
    try
    {
      localXMLInputFactory = XMLInputFactory.newInstance();
    }
    catch (FactoryConfigurationError localFactoryConfigurationError)
    {
      throw Exceptions.sourceInstantiation(localFactoryConfigurationError);
    }
    try
    {
      localConstructor = paramClass == null ? StAXSource.class.getConstructor(new Class[] { XMLEventReader.class }) : paramClass.getConstructor(new Class[] { XMLEventReader.class });
    }
    catch (SecurityException localSecurityException1)
    {
      throw Exceptions.sourceInstantiation(localSecurityException1);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw Exceptions.sourceInstantiation(localNoSuchMethodException);
    }
    localReader = getCharacterStreamImpl();
    try
    {
      localXMLEventReader = localXMLInputFactory.createXMLEventReader(localReader);
    }
    catch (XMLStreamException localXMLStreamException)
    {
      throw Exceptions.sourceInstantiation(localXMLStreamException);
    }
    try
    {
      localStAXSource = (StAXSource)localConstructor.newInstance(new Object[] { localXMLEventReader });
    }
    catch (SecurityException localSecurityException2)
    {
      throw Exceptions.sourceInstantiation(localSecurityException2);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw Exceptions.sourceInstantiation(localIllegalArgumentException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw Exceptions.sourceInstantiation(localIllegalAccessException);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw Exceptions.sourceInstantiation(localInstantiationException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw Exceptions.sourceInstantiation(localInvocationTargetException.getTargetException());
    }
    catch (ClassCastException localClassCastException)
    {
      throw Exceptions.sourceInstantiation(localClassCastException);
    }
    return localStAXSource;
  }
  
  protected <T extends Result> T createResult(Class<T> paramClass)
    throws SQLException
  {
    checkWritable();
    setWritable(false);
    setReadable(true);
    if (!JAXBResult.class.isAssignableFrom(paramClass))
    {
      if ((paramClass == null) || (StreamResult.class.isAssignableFrom(paramClass))) {
        return createStreamResult(paramClass);
      }
      if (DOMResult.class.isAssignableFrom(paramClass)) {
        return createDOMResult(paramClass);
      }
      if (SAXResult.class.isAssignableFrom(paramClass)) {
        return createSAXResult(paramClass);
      }
      if (StAXResult.class.isAssignableFrom(paramClass)) {
        return createStAXResult(paramClass);
      }
    }
    throw Util.invalidArgument("resultClass: " + paramClass);
  }
  
  protected <T extends Result> T createStreamResult(Class<T> paramClass)
    throws SQLException
  {
    StreamResult localStreamResult = null;
    try
    {
      localStreamResult = paramClass == null ? new StreamResult() : (StreamResult)paramClass.newInstance();
    }
    catch (SecurityException localSecurityException)
    {
      throw Exceptions.resultInstantiation(localSecurityException);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw Exceptions.resultInstantiation(localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw Exceptions.resultInstantiation(localIllegalAccessException);
    }
    catch (ClassCastException localClassCastException)
    {
      throw Exceptions.resultInstantiation(localClassCastException);
    }
    OutputStream localOutputStream = setBinaryStreamImpl();
    localStreamResult.setOutputStream(localOutputStream);
    return localStreamResult;
  }
  
  protected <T extends Result> T createDOMResult(Class<T> paramClass)
    throws SQLException
  {
    try
    {
      Result localResult = paramClass == null ? new DOMResult() : (Result)paramClass.newInstance();
      this.domResult = ((DOMResult)localResult);
      return localResult;
    }
    catch (SecurityException localSecurityException)
    {
      throw Exceptions.resultInstantiation(localSecurityException);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw Exceptions.resultInstantiation(localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw Exceptions.resultInstantiation(localIllegalAccessException);
    }
    catch (ClassCastException localClassCastException)
    {
      throw Exceptions.resultInstantiation(localClassCastException);
    }
  }
  
  protected <T extends Result> T createSAXResult(Class<T> paramClass)
    throws SQLException
  {
    SAXResult localSAXResult = null;
    try
    {
      localSAXResult = paramClass == null ? new SAXResult() : (SAXResult)paramClass.newInstance();
    }
    catch (SecurityException localSecurityException)
    {
      throw Exceptions.resultInstantiation(localSecurityException);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw Exceptions.resultInstantiation(localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw Exceptions.resultInstantiation(localIllegalAccessException);
    }
    catch (ClassCastException localClassCastException)
    {
      throw Exceptions.resultInstantiation(localClassCastException);
    }
    SAX2DOMBuilder localSAX2DOMBuilder = null;
    try
    {
      localSAX2DOMBuilder = new SAX2DOMBuilder();
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      throw Exceptions.resultInstantiation(localParserConfigurationException);
    }
    this.domResult = new DOMResult();
    localSAXResult.setHandler(localSAX2DOMBuilder);
    this.domResult.setNode(localSAX2DOMBuilder.getDocument());
    return localSAXResult;
  }
  
  protected <T extends Result> T createStAXResult(Class<T> paramClass)
    throws SQLException
  {
    StAXResult localStAXResult = null;
    try
    {
      this.domResult = new DOMResult(new SAX2DOMBuilder().getDocument());
      XMLOutputFactory localXMLOutputFactory = XMLOutputFactory.newInstance();
      XMLStreamWriter localXMLStreamWriter = localXMLOutputFactory.createXMLStreamWriter(this.domResult);
      if ((paramClass == null) || (paramClass == StAXResult.class))
      {
        localStAXResult = new StAXResult(localXMLStreamWriter);
      }
      else
      {
        Constructor localConstructor = paramClass.getConstructor(new Class[] { XMLStreamWriter.class });
        localStAXResult = (StAXResult)localConstructor.newInstance(new Object[] { localXMLStreamWriter });
      }
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      throw Exceptions.resultInstantiation(localParserConfigurationException);
    }
    catch (SecurityException localSecurityException)
    {
      throw Exceptions.resultInstantiation(localSecurityException);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw Exceptions.resultInstantiation(localIllegalArgumentException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw Exceptions.resultInstantiation(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw Exceptions.resultInstantiation(localInvocationTargetException.getTargetException());
    }
    catch (FactoryConfigurationError localFactoryConfigurationError)
    {
      throw Exceptions.resultInstantiation(localFactoryConfigurationError);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw Exceptions.resultInstantiation(localInstantiationException);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw Exceptions.resultInstantiation(localNoSuchMethodException);
    }
    catch (XMLStreamException localXMLStreamException)
    {
      throw Exceptions.resultInstantiation(localXMLStreamException);
    }
    return localStAXResult;
  }
  
  protected void freeDomResult()
  {
    this.domResult = null;
  }
  
  static
  {
    Charset localCharset = null;
    try
    {
      localCharset = Charset.forName("UTF8");
    }
    catch (Exception localException) {}
  }
  
  public static class SAX2XMLStreamWriter
    implements ContentHandler, Closeable
  {
    private List<QualifiedName> namespaces = new ArrayList();
    private boolean closed;
    private Locator locator;
    private XMLStreamWriter writer;
    
    public SAX2XMLStreamWriter(XMLStreamWriter paramXMLStreamWriter)
    {
      if (paramXMLStreamWriter == null) {
        throw new NullPointerException("writer");
      }
      this.writer = paramXMLStreamWriter;
    }
    
    public void startDocument()
      throws SAXException
    {
      checkClosed();
      try
      {
        this.writer.writeStartDocument();
      }
      catch (XMLStreamException localXMLStreamException)
      {
        throw new SAXException(localXMLStreamException);
      }
    }
    
    public void endDocument()
      throws SAXException
    {
      checkClosed();
      try
      {
        this.writer.writeEndDocument();
        this.writer.flush();
      }
      catch (XMLStreamException localXMLStreamException)
      {
        throw new SAXException(localXMLStreamException);
      }
    }
    
    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      checkClosed();
      try
      {
        this.writer.writeCharacters(paramArrayOfChar, paramInt1, paramInt2);
      }
      catch (XMLStreamException localXMLStreamException)
      {
        throw new SAXException(localXMLStreamException);
      }
    }
    
    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
      throws SAXException
    {
      checkClosed();
      try
      {
        int i = paramString3.indexOf(58);
        String str = i > 0 ? paramString3.substring(0, i) : "";
        this.writer.writeStartElement(str, paramString2, paramString1);
        int j = this.namespaces.size();
        for (int k = 0; k < j; k++)
        {
          QualifiedName localQualifiedName = (QualifiedName)this.namespaces.get(k);
          this.writer.writeNamespace(localQualifiedName.prefix, localQualifiedName.namespaceName);
        }
        this.namespaces.clear();
        j = paramAttributes.getLength();
        for (k = 0; k < j; k++) {
          this.writer.writeAttribute(paramAttributes.getURI(k), paramAttributes.getLocalName(k), paramAttributes.getValue(k));
        }
      }
      catch (XMLStreamException localXMLStreamException)
      {
        throw new SAXException(localXMLStreamException);
      }
    }
    
    public void endElement(String paramString1, String paramString2, String paramString3)
      throws SAXException
    {
      checkClosed();
      try
      {
        this.writer.writeEndElement();
      }
      catch (XMLStreamException localXMLStreamException)
      {
        throw new SAXException(localXMLStreamException);
      }
    }
    
    public void startPrefixMapping(String paramString1, String paramString2)
      throws SAXException
    {
      checkClosed();
      try
      {
        this.writer.setPrefix(paramString1, paramString2);
        this.namespaces.add(new QualifiedName(paramString1, paramString2));
      }
      catch (XMLStreamException localXMLStreamException)
      {
        throw new SAXException(localXMLStreamException);
      }
    }
    
    public void endPrefixMapping(String paramString)
      throws SAXException
    {
      checkClosed();
    }
    
    public void ignorableWhitespace(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      characters(paramArrayOfChar, paramInt1, paramInt2);
    }
    
    public void processingInstruction(String paramString1, String paramString2)
      throws SAXException
    {
      checkClosed();
      try
      {
        this.writer.writeProcessingInstruction(paramString1, paramString2);
      }
      catch (XMLStreamException localXMLStreamException)
      {
        throw new SAXException(localXMLStreamException);
      }
    }
    
    public void setDocumentLocator(Locator paramLocator)
    {
      this.locator = paramLocator;
    }
    
    public Locator getDocumentLocator()
    {
      return this.locator;
    }
    
    public void skippedEntity(String paramString)
      throws SAXException
    {
      checkClosed();
    }
    
    public void comment(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      checkClosed();
      try
      {
        this.writer.writeComment(new String(paramArrayOfChar, paramInt1, paramInt2));
      }
      catch (XMLStreamException localXMLStreamException)
      {
        throw new SAXException(localXMLStreamException);
      }
    }
    
    public XMLStreamWriter getWriter()
    {
      return this.writer;
    }
    
    protected List<QualifiedName> getNamespaces()
    {
      return this.namespaces;
    }
    
    public void close()
      throws IOException
    {
      if (!this.closed)
      {
        this.closed = true;
        try
        {
          this.writer.close();
        }
        catch (XMLStreamException localXMLStreamException)
        {
          throw new IOException(localXMLStreamException);
        }
        finally
        {
          this.writer = null;
          this.locator = null;
          this.namespaces = null;
        }
      }
    }
    
    public boolean isClosed()
    {
      return this.closed;
    }
    
    protected void checkClosed()
      throws SAXException
    {
      if (isClosed()) {
        throw new SAXException("content handler is closed.");
      }
    }
    
    protected class QualifiedName
    {
      public final String namespaceName;
      public final String prefix;
      
      public QualifiedName(String paramString1, String paramString2)
      {
        this.prefix = paramString1;
        this.namespaceName = paramString2;
      }
    }
  }
  
  protected static class SAX2DOMBuilder
    implements ContentHandler, Closeable
  {
    private boolean closed;
    private Element currentElement;
    private Node currentNode;
    private Document document;
    private Locator locator;
    
    public SAX2DOMBuilder()
      throws ParserConfigurationException
    {
      DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
      localDocumentBuilderFactory.setValidating(false);
      localDocumentBuilderFactory.setNamespaceAware(true);
      DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
      this.document = localDocumentBuilder.newDocument();
      this.currentNode = this.document;
    }
    
    public void setDocumentLocator(Locator paramLocator)
    {
      this.locator = paramLocator;
    }
    
    public Locator getDocumentLocator()
    {
      return this.locator;
    }
    
    public void startDocument()
      throws SAXException
    {
      checkClosed();
    }
    
    public void endDocument()
      throws SAXException
    {
      checkClosed();
      close();
    }
    
    public void startPrefixMapping(String paramString1, String paramString2)
      throws SAXException
    {
      checkClosed();
    }
    
    public void endPrefixMapping(String paramString)
      throws SAXException
    {
      checkClosed();
    }
    
    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
      throws SAXException
    {
      checkClosed();
      Element localElement;
      if ((paramString1 == null) || (paramString1.length() == 0)) {
        localElement = getDocument().createElement(paramString3);
      } else {
        localElement = getDocument().createElementNS(paramString1, paramString3);
      }
      if (paramAttributes != null) {
        for (int i = 0; i < paramAttributes.getLength(); i++)
        {
          String str1 = paramAttributes.getURI(i);
          String str2 = paramAttributes.getQName(i);
          String str3 = paramAttributes.getValue(i);
          if ((str1 == null) || (str1.length() == 0)) {
            localElement.setAttribute(str2, str3);
          } else {
            localElement.setAttributeNS(str1, str2, str3);
          }
        }
      }
      getCurrentNode().appendChild(localElement);
      setCurrentNode(localElement);
      if (getCurrentElement() == null) {
        setCurrentElement(localElement);
      }
    }
    
    public void endElement(String paramString1, String paramString2, String paramString3)
      throws SAXException
    {
      checkClosed();
      setCurrentNode(getCurrentNode().getParentNode());
    }
    
    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      checkClosed();
      Node localNode = getCurrentNode().getLastChild();
      String str = new String(paramArrayOfChar, paramInt1, paramInt2);
      if ((localNode != null) && (localNode.getNodeType() == 3))
      {
        ((Text)localNode).appendData(str);
      }
      else
      {
        Text localText = getDocument().createTextNode(str);
        getCurrentNode().appendChild(localText);
      }
    }
    
    public void ignorableWhitespace(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      characters(paramArrayOfChar, paramInt1, paramInt2);
    }
    
    public void processingInstruction(String paramString1, String paramString2)
      throws SAXException
    {
      checkClosed();
      ProcessingInstruction localProcessingInstruction = getDocument().createProcessingInstruction(paramString1, paramString2);
      getCurrentNode().appendChild(localProcessingInstruction);
    }
    
    public void skippedEntity(String paramString)
      throws SAXException
    {
      checkClosed();
      EntityReference localEntityReference = getDocument().createEntityReference(paramString);
      getCurrentNode().appendChild(localEntityReference);
    }
    
    public void close()
    {
      this.closed = true;
    }
    
    public void free()
    {
      close();
      this.document = null;
      this.currentElement = null;
      this.currentNode = null;
      this.locator = null;
    }
    
    public boolean isClosed()
    {
      return this.closed;
    }
    
    protected void checkClosed()
      throws SAXException
    {
      if (isClosed()) {
        throw new SAXException("content handler is closed.");
      }
    }
    
    public Document getDocument()
    {
      return this.document;
    }
    
    protected Element getCurrentElement()
    {
      return this.currentElement;
    }
    
    protected void setCurrentElement(Element paramElement)
    {
      this.currentElement = paramElement;
    }
    
    protected Node getCurrentNode()
    {
      return this.currentNode;
    }
    
    protected void setCurrentNode(Node paramNode)
    {
      this.currentNode = paramNode;
    }
  }
  
  protected static class Exceptions
  {
    static SQLException domInstantiation(Throwable paramThrowable)
    {
      Exception localException = (paramThrowable instanceof Exception) ? (Exception)paramThrowable : new Exception(paramThrowable);
      return Util.sqlException(458, "SQLXML DOM instantiation failed: " + paramThrowable, localException);
    }
    
    static SQLException sourceInstantiation(Throwable paramThrowable)
    {
      Exception localException = (paramThrowable instanceof Exception) ? (Exception)paramThrowable : new Exception(paramThrowable);
      return Util.sqlException(458, "SQLXML Source instantiation failed: " + paramThrowable, localException);
    }
    
    static SQLException resultInstantiation(Throwable paramThrowable)
    {
      Exception localException = (paramThrowable instanceof Exception) ? (Exception)paramThrowable : new Exception(paramThrowable);
      return Util.sqlException(458, "SQLXML Result instantiation failed: " + paramThrowable, localException);
    }
    
    static SQLException parseFailed(Throwable paramThrowable)
    {
      Exception localException = (paramThrowable instanceof Exception) ? (Exception)paramThrowable : new Exception(paramThrowable);
      return Util.sqlException(458, "parse failed: " + paramThrowable, localException);
    }
    
    static SQLException transformFailed(Throwable paramThrowable)
    {
      Exception localException = (paramThrowable instanceof Exception) ? (Exception)paramThrowable : new Exception(paramThrowable);
      return Util.sqlException(458, "transform failed: " + paramThrowable, localException);
    }
    
    static SQLException notReadable()
    {
      return Util.sqlException(467, "SQLXML in not readable state");
    }
    
    static SQLException notReadable(String paramString)
    {
      return Util.sqlException(467, "SQLXML in not readable state: " + paramString);
    }
    
    static SQLException notWritable()
    {
      return Util.sqlException(467, "SQLXML in not writable state");
    }
    
    static SQLException directUpdateByLocatorNotSupported()
    {
      return Util.sqlException(1500, "SQLXML direct update by locator");
    }
    
    static SQLException inFreedState()
    {
      return Util.sqlException(458, "SQLXML in freed state");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCSQLXML
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */