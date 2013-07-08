package org.dom4j.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.ElementModifier;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXModifier;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;

public class JAXBModifier
  extends JAXBSupport
{
  private SAXModifier modifier;
  private XMLWriter xmlWriter;
  private boolean pruneElements;
  private OutputFormat outputFormat;
  private HashMap modifiers = new HashMap();
  
  public JAXBModifier(String contextPath)
  {
    super(contextPath);
    this.outputFormat = new OutputFormat();
  }
  
  public JAXBModifier(String contextPath, ClassLoader classloader)
  {
    super(contextPath, classloader);
    this.outputFormat = new OutputFormat();
  }
  
  public JAXBModifier(String contextPath, OutputFormat outputFormat)
  {
    super(contextPath);
    this.outputFormat = outputFormat;
  }
  
  public JAXBModifier(String contextPath, ClassLoader classloader, OutputFormat outputFormat)
  {
    super(contextPath, classloader);
    this.outputFormat = outputFormat;
  }
  
  public Document modify(File source)
    throws DocumentException, IOException
  {
    return installModifier().modify(source);
  }
  
  public Document modify(File source, Charset charset)
    throws DocumentException, IOException
  {
    try
    {
      Reader reader = new InputStreamReader(new FileInputStream(source), charset);
      return installModifier().modify(reader);
    }
    catch (JAXBRuntimeException reader)
    {
      Throwable cause = reader.getCause();
      throw new DocumentException(cause.getMessage(), cause);
    }
    catch (FileNotFoundException reader)
    {
      throw new DocumentException(reader.getMessage(), reader);
    }
  }
  
  public Document modify(InputSource source)
    throws DocumentException, IOException
  {
    try
    {
      return installModifier().modify(source);
    }
    catch (JAXBRuntimeException local_ex)
    {
      Throwable cause = local_ex.getCause();
      throw new DocumentException(cause.getMessage(), cause);
    }
  }
  
  public Document modify(InputStream source)
    throws DocumentException, IOException
  {
    try
    {
      return installModifier().modify(source);
    }
    catch (JAXBRuntimeException local_ex)
    {
      Throwable cause = local_ex.getCause();
      throw new DocumentException(cause.getMessage(), cause);
    }
  }
  
  public Document modify(InputStream source, String systemId)
    throws DocumentException, IOException
  {
    try
    {
      return installModifier().modify(source);
    }
    catch (JAXBRuntimeException local_ex)
    {
      Throwable cause = local_ex.getCause();
      throw new DocumentException(cause.getMessage(), cause);
    }
  }
  
  public Document modify(Reader local_r)
    throws DocumentException, IOException
  {
    try
    {
      return installModifier().modify(local_r);
    }
    catch (JAXBRuntimeException local_ex)
    {
      Throwable cause = local_ex.getCause();
      throw new DocumentException(cause.getMessage(), cause);
    }
  }
  
  public Document modify(Reader source, String systemId)
    throws DocumentException, IOException
  {
    try
    {
      return installModifier().modify(source);
    }
    catch (JAXBRuntimeException local_ex)
    {
      Throwable cause = local_ex.getCause();
      throw new DocumentException(cause.getMessage(), cause);
    }
  }
  
  public Document modify(String url)
    throws DocumentException, IOException
  {
    try
    {
      return installModifier().modify(url);
    }
    catch (JAXBRuntimeException local_ex)
    {
      Throwable cause = local_ex.getCause();
      throw new DocumentException(cause.getMessage(), cause);
    }
  }
  
  public Document modify(URL source)
    throws DocumentException, IOException
  {
    try
    {
      return installModifier().modify(source);
    }
    catch (JAXBRuntimeException local_ex)
    {
      Throwable cause = local_ex.getCause();
      throw new DocumentException(cause.getMessage(), cause);
    }
  }
  
  public void setOutput(File file)
    throws IOException
  {
    createXMLWriter().setOutputStream(new FileOutputStream(file));
  }
  
  public void setOutput(OutputStream outputStream)
    throws IOException
  {
    createXMLWriter().setOutputStream(outputStream);
  }
  
  public void setOutput(Writer writer)
    throws IOException
  {
    createXMLWriter().setWriter(writer);
  }
  
  public void addObjectModifier(String path, JAXBObjectModifier mod)
  {
    this.modifiers.put(path, mod);
  }
  
  public void removeObjectModifier(String path)
  {
    this.modifiers.remove(path);
    getModifier().removeModifier(path);
  }
  
  public void resetObjectModifiers()
  {
    this.modifiers.clear();
    getModifier().resetModifiers();
  }
  
  public boolean isPruneElements()
  {
    return this.pruneElements;
  }
  
  public void setPruneElements(boolean pruneElements)
  {
    this.pruneElements = pruneElements;
  }
  
  private SAXModifier installModifier()
    throws IOException
  {
    this.modifier = new SAXModifier(isPruneElements());
    this.modifier.resetModifiers();
    Iterator modifierIt = this.modifiers.entrySet().iterator();
    while (modifierIt.hasNext())
    {
      Map.Entry entry = (Map.Entry)modifierIt.next();
      ElementModifier mod = new JAXBElementModifier(this, (JAXBObjectModifier)entry.getValue());
      getModifier().addModifier((String)entry.getKey(), mod);
    }
    this.modifier.setXMLWriter(getXMLWriter());
    return this.modifier;
  }
  
  private SAXModifier getModifier()
  {
    if (this.modifier == null) {
      this.modifier = new SAXModifier(isPruneElements());
    }
    return this.modifier;
  }
  
  private XMLWriter getXMLWriter()
  {
    return this.xmlWriter;
  }
  
  private XMLWriter createXMLWriter()
    throws IOException
  {
    if (this.xmlWriter == null) {
      this.xmlWriter = new XMLWriter(this.outputFormat);
    }
    return this.xmlWriter;
  }
  
  private class JAXBElementModifier
    implements ElementModifier
  {
    private JAXBModifier jaxbModifier;
    private JAXBObjectModifier objectModifier;
    
    public JAXBElementModifier(JAXBModifier jaxbModifier, JAXBObjectModifier objectModifier)
    {
      this.jaxbModifier = jaxbModifier;
      this.objectModifier = objectModifier;
    }
    
    public org.dom4j.Element modifyElement(org.dom4j.Element element)
      throws Exception
    {
      javax.xml.bind.Element originalObject = this.jaxbModifier.unmarshal(element);
      javax.xml.bind.Element modifiedObject = this.objectModifier.modifyObject(originalObject);
      return this.jaxbModifier.marshal(modifiedObject);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.jaxb.JAXBModifier
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */