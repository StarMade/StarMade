/*      */ package org.dom4j.io;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.Serializable;
/*      */ import java.net.URL;
/*      */ import org.dom4j.Document;
/*      */ import org.dom4j.DocumentException;
/*      */ import org.dom4j.DocumentFactory;
/*      */ import org.dom4j.ElementHandler;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.ErrorHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import org.xml.sax.XMLFilter;
/*      */ import org.xml.sax.XMLReader;
/*      */ import org.xml.sax.helpers.DefaultHandler;
/*      */ import org.xml.sax.helpers.XMLReaderFactory;
/*      */ 
/*      */ public class SAXReader
/*      */ {
/*      */   private static final String SAX_STRING_INTERNING = "http://xml.org/sax/features/string-interning";
/*      */   private static final String SAX_NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
/*      */   private static final String SAX_NAMESPACES = "http://xml.org/sax/features/namespaces";
/*      */   private static final String SAX_DECL_HANDLER = "http://xml.org/sax/properties/declaration-handler";
/*      */   private static final String SAX_LEXICAL_HANDLER = "http://xml.org/sax/properties/lexical-handler";
/*      */   private static final String SAX_LEXICALHANDLER = "http://xml.org/sax/handlers/LexicalHandler";
/*      */   private DocumentFactory factory;
/*      */   private XMLReader xmlReader;
/*      */   private boolean validating;
/*      */   private DispatchHandler dispatchHandler;
/*      */   private ErrorHandler errorHandler;
/*      */   private EntityResolver entityResolver;
/*  112 */   private boolean stringInternEnabled = true;
/*      */ 
/*  115 */   private boolean includeInternalDTDDeclarations = false;
/*      */ 
/*  118 */   private boolean includeExternalDTDDeclarations = false;
/*      */ 
/*  121 */   private boolean mergeAdjacentText = false;
/*      */ 
/*  124 */   private boolean stripWhitespaceText = false;
/*      */ 
/*  127 */   private boolean ignoreComments = false;
/*      */ 
/*  130 */   private String encoding = null;
/*      */   private XMLFilter xmlFilter;
/*      */ 
/*      */   public SAXReader()
/*      */   {
/*      */   }
/*      */ 
/*      */   public SAXReader(boolean validating)
/*      */   {
/*  142 */     this.validating = validating;
/*      */   }
/*      */ 
/*      */   public SAXReader(DocumentFactory factory) {
/*  146 */     this.factory = factory;
/*      */   }
/*      */ 
/*      */   public SAXReader(DocumentFactory factory, boolean validating) {
/*  150 */     this.factory = factory;
/*  151 */     this.validating = validating;
/*      */   }
/*      */ 
/*      */   public SAXReader(XMLReader xmlReader) {
/*  155 */     this.xmlReader = xmlReader;
/*      */   }
/*      */ 
/*      */   public SAXReader(XMLReader xmlReader, boolean validating) {
/*  159 */     this.xmlReader = xmlReader;
/*  160 */     this.validating = validating;
/*      */   }
/*      */ 
/*      */   public SAXReader(String xmlReaderClassName) throws SAXException {
/*  164 */     if (xmlReaderClassName != null)
/*  165 */       this.xmlReader = XMLReaderFactory.createXMLReader(xmlReaderClassName);
/*      */   }
/*      */ 
/*      */   public SAXReader(String xmlReaderClassName, boolean validating)
/*      */     throws SAXException
/*      */   {
/*  172 */     if (xmlReaderClassName != null) {
/*  173 */       this.xmlReader = XMLReaderFactory.createXMLReader(xmlReaderClassName);
/*      */     }
/*      */ 
/*  177 */     this.validating = validating;
/*      */   }
/*      */ 
/*      */   public void setProperty(String name, Object value)
/*      */     throws SAXException
/*      */   {
/*  198 */     getXMLReader().setProperty(name, value);
/*      */   }
/*      */ 
/*      */   public void setFeature(String name, boolean value)
/*      */     throws SAXException
/*      */   {
/*  218 */     getXMLReader().setFeature(name, value);
/*      */   }
/*      */ 
/*      */   public Document read(File file)
/*      */     throws DocumentException
/*      */   {
/*      */     try
/*      */     {
/*  243 */       InputSource source = new InputSource(new FileInputStream(file));
/*  244 */       if (this.encoding != null) {
/*  245 */         source.setEncoding(this.encoding);
/*      */       }
/*  247 */       String path = file.getAbsolutePath();
/*      */ 
/*  249 */       if (path != null)
/*      */       {
/*  251 */         StringBuffer sb = new StringBuffer("file://");
/*      */ 
/*  254 */         if (!path.startsWith(File.separator)) {
/*  255 */           sb.append("/");
/*      */         }
/*      */ 
/*  258 */         path = path.replace('\\', '/');
/*  259 */         sb.append(path);
/*      */ 
/*  261 */         source.setSystemId(sb.toString());
/*      */       }
/*      */ 
/*  264 */       return read(source);
/*      */     } catch (FileNotFoundException e) {
/*  266 */       throw new DocumentException(e.getMessage(), e);
/*      */     }
/*      */   }
/*      */ 
/*      */   public Document read(URL url)
/*      */     throws DocumentException
/*      */   {
/*  284 */     String systemID = url.toExternalForm();
/*      */ 
/*  286 */     InputSource source = new InputSource(systemID);
/*  287 */     if (this.encoding != null) {
/*  288 */       source.setEncoding(this.encoding);
/*      */     }
/*      */ 
/*  291 */     return read(source);
/*      */   }
/*      */ 
/*      */   public Document read(String systemId)
/*      */     throws DocumentException
/*      */   {
/*  316 */     InputSource source = new InputSource(systemId);
/*  317 */     if (this.encoding != null) {
/*  318 */       source.setEncoding(this.encoding);
/*      */     }
/*      */ 
/*  321 */     return read(source);
/*      */   }
/*      */ 
/*      */   public Document read(InputStream in)
/*      */     throws DocumentException
/*      */   {
/*  338 */     InputSource source = new InputSource(in);
/*  339 */     if (this.encoding != null) {
/*  340 */       source.setEncoding(this.encoding);
/*      */     }
/*      */ 
/*  343 */     return read(source);
/*      */   }
/*      */ 
/*      */   public Document read(Reader reader)
/*      */     throws DocumentException
/*      */   {
/*  360 */     InputSource source = new InputSource(reader);
/*  361 */     if (this.encoding != null) {
/*  362 */       source.setEncoding(this.encoding);
/*      */     }
/*      */ 
/*  365 */     return read(source);
/*      */   }
/*      */ 
/*      */   public Document read(InputStream in, String systemId)
/*      */     throws DocumentException
/*      */   {
/*  385 */     InputSource source = new InputSource(in);
/*  386 */     source.setSystemId(systemId);
/*  387 */     if (this.encoding != null) {
/*  388 */       source.setEncoding(this.encoding);
/*      */     }
/*      */ 
/*  391 */     return read(source);
/*      */   }
/*      */ 
/*      */   public Document read(Reader reader, String systemId)
/*      */     throws DocumentException
/*      */   {
/*  411 */     InputSource source = new InputSource(reader);
/*  412 */     source.setSystemId(systemId);
/*  413 */     if (this.encoding != null) {
/*  414 */       source.setEncoding(this.encoding);
/*      */     }
/*      */ 
/*  417 */     return read(source);
/*      */   }
/*      */ 
/*      */   public Document read(InputSource in)
/*      */     throws DocumentException
/*      */   {
/*      */     try
/*      */     {
/*  435 */       XMLReader reader = getXMLReader();
/*      */ 
/*  437 */       reader = installXMLFilter(reader);
/*      */ 
/*  439 */       EntityResolver thatEntityResolver = this.entityResolver;
/*      */ 
/*  441 */       if (thatEntityResolver == null) {
/*  442 */         thatEntityResolver = createDefaultEntityResolver(in.getSystemId());
/*      */ 
/*  444 */         this.entityResolver = thatEntityResolver;
/*      */       }
/*      */ 
/*  447 */       reader.setEntityResolver(thatEntityResolver);
/*      */ 
/*  449 */       SAXContentHandler contentHandler = createContentHandler(reader);
/*  450 */       contentHandler.setEntityResolver(thatEntityResolver);
/*  451 */       contentHandler.setInputSource(in);
/*      */ 
/*  453 */       boolean internal = isIncludeInternalDTDDeclarations();
/*  454 */       boolean external = isIncludeExternalDTDDeclarations();
/*      */ 
/*  456 */       contentHandler.setIncludeInternalDTDDeclarations(internal);
/*  457 */       contentHandler.setIncludeExternalDTDDeclarations(external);
/*  458 */       contentHandler.setMergeAdjacentText(isMergeAdjacentText());
/*  459 */       contentHandler.setStripWhitespaceText(isStripWhitespaceText());
/*  460 */       contentHandler.setIgnoreComments(isIgnoreComments());
/*  461 */       reader.setContentHandler(contentHandler);
/*      */ 
/*  463 */       configureReader(reader, contentHandler);
/*      */ 
/*  465 */       reader.parse(in);
/*      */ 
/*  467 */       return contentHandler.getDocument();
/*      */     } catch (Exception e) {
/*  469 */       if ((e instanceof SAXParseException))
/*      */       {
/*  471 */         SAXParseException parseException = (SAXParseException)e;
/*  472 */         String systemId = parseException.getSystemId();
/*      */ 
/*  474 */         if (systemId == null) {
/*  475 */           systemId = "";
/*      */         }
/*      */ 
/*  478 */         String message = "Error on line " + parseException.getLineNumber() + " of document " + systemId + " : " + parseException.getMessage();
/*      */ 
/*  482 */         throw new DocumentException(message, e);
/*      */       }
/*  484 */       throw new DocumentException(e.getMessage(), e);
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean isValidating()
/*      */   {
/*  499 */     return this.validating;
/*      */   }
/*      */ 
/*      */   public void setValidation(boolean validation)
/*      */   {
/*  509 */     this.validating = validation;
/*      */   }
/*      */ 
/*      */   public boolean isIncludeInternalDTDDeclarations()
/*      */   {
/*  519 */     return this.includeInternalDTDDeclarations;
/*      */   }
/*      */ 
/*      */   public void setIncludeInternalDTDDeclarations(boolean include)
/*      */   {
/*  531 */     this.includeInternalDTDDeclarations = include;
/*      */   }
/*      */ 
/*      */   public boolean isIncludeExternalDTDDeclarations()
/*      */   {
/*  541 */     return this.includeExternalDTDDeclarations;
/*      */   }
/*      */ 
/*      */   public void setIncludeExternalDTDDeclarations(boolean include)
/*      */   {
/*  553 */     this.includeExternalDTDDeclarations = include;
/*      */   }
/*      */ 
/*      */   public boolean isStringInternEnabled()
/*      */   {
/*  563 */     return this.stringInternEnabled;
/*      */   }
/*      */ 
/*      */   public void setStringInternEnabled(boolean stringInternEnabled)
/*      */   {
/*  574 */     this.stringInternEnabled = stringInternEnabled;
/*      */   }
/*      */ 
/*      */   public boolean isMergeAdjacentText()
/*      */   {
/*  583 */     return this.mergeAdjacentText;
/*      */   }
/*      */ 
/*      */   public void setMergeAdjacentText(boolean mergeAdjacentText)
/*      */   {
/*  594 */     this.mergeAdjacentText = mergeAdjacentText;
/*      */   }
/*      */ 
/*      */   public boolean isStripWhitespaceText()
/*      */   {
/*  604 */     return this.stripWhitespaceText;
/*      */   }
/*      */ 
/*      */   public void setStripWhitespaceText(boolean stripWhitespaceText)
/*      */   {
/*  615 */     this.stripWhitespaceText = stripWhitespaceText;
/*      */   }
/*      */ 
/*      */   public boolean isIgnoreComments()
/*      */   {
/*  624 */     return this.ignoreComments;
/*      */   }
/*      */ 
/*      */   public void setIgnoreComments(boolean ignoreComments)
/*      */   {
/*  634 */     this.ignoreComments = ignoreComments;
/*      */   }
/*      */ 
/*      */   public DocumentFactory getDocumentFactory()
/*      */   {
/*  644 */     if (this.factory == null) {
/*  645 */       this.factory = DocumentFactory.getInstance();
/*      */     }
/*      */ 
/*  648 */     return this.factory;
/*      */   }
/*      */ 
/*      */   public void setDocumentFactory(DocumentFactory documentFactory)
/*      */   {
/*  663 */     this.factory = documentFactory;
/*      */   }
/*      */ 
/*      */   public ErrorHandler getErrorHandler()
/*      */   {
/*  672 */     return this.errorHandler;
/*      */   }
/*      */ 
/*      */   public void setErrorHandler(ErrorHandler errorHandler)
/*      */   {
/*  683 */     this.errorHandler = errorHandler;
/*      */   }
/*      */ 
/*      */   public EntityResolver getEntityResolver()
/*      */   {
/*  692 */     return this.entityResolver;
/*      */   }
/*      */ 
/*      */   public void setEntityResolver(EntityResolver entityResolver)
/*      */   {
/*  702 */     this.entityResolver = entityResolver;
/*      */   }
/*      */ 
/*      */   public XMLReader getXMLReader()
/*      */     throws SAXException
/*      */   {
/*  714 */     if (this.xmlReader == null) {
/*  715 */       this.xmlReader = createXMLReader();
/*      */     }
/*      */ 
/*  718 */     return this.xmlReader;
/*      */   }
/*      */ 
/*      */   public void setXMLReader(XMLReader reader)
/*      */   {
/*  728 */     this.xmlReader = reader;
/*      */   }
/*      */ 
/*      */   public String getEncoding()
/*      */   {
/*  739 */     return this.encoding;
/*      */   }
/*      */ 
/*      */   public void setEncoding(String encoding)
/*      */   {
/*  749 */     this.encoding = encoding;
/*      */   }
/*      */ 
/*      */   public void setXMLReaderClassName(String xmlReaderClassName)
/*      */     throws SAXException
/*      */   {
/*  765 */     setXMLReader(XMLReaderFactory.createXMLReader(xmlReaderClassName));
/*      */   }
/*      */ 
/*      */   public void addHandler(String path, ElementHandler handler)
/*      */   {
/*  779 */     getDispatchHandler().addHandler(path, handler);
/*      */   }
/*      */ 
/*      */   public void removeHandler(String path)
/*      */   {
/*  790 */     getDispatchHandler().removeHandler(path);
/*      */   }
/*      */ 
/*      */   public void setDefaultHandler(ElementHandler handler)
/*      */   {
/*  803 */     getDispatchHandler().setDefaultHandler(handler);
/*      */   }
/*      */ 
/*      */   public void resetHandlers()
/*      */   {
/*  812 */     getDispatchHandler().resetHandlers();
/*      */   }
/*      */ 
/*      */   public XMLFilter getXMLFilter()
/*      */   {
/*  821 */     return this.xmlFilter;
/*      */   }
/*      */ 
/*      */   public void setXMLFilter(XMLFilter filter)
/*      */   {
/*  831 */     this.xmlFilter = filter;
/*      */   }
/*      */ 
/*      */   protected XMLReader installXMLFilter(XMLReader reader)
/*      */   {
/*  848 */     XMLFilter filter = getXMLFilter();
/*      */ 
/*  850 */     if (filter != null)
/*      */     {
/*  852 */       XMLFilter root = filter;
/*      */       while (true)
/*      */       {
/*  855 */         XMLReader parent = root.getParent();
/*      */ 
/*  857 */         if (!(parent instanceof XMLFilter)) break;
/*  858 */         root = (XMLFilter)parent;
/*      */       }
/*      */ 
/*  864 */       root.setParent(reader);
/*      */ 
/*  866 */       return filter;
/*      */     }
/*      */ 
/*  869 */     return reader;
/*      */   }
/*      */ 
/*      */   protected DispatchHandler getDispatchHandler() {
/*  873 */     if (this.dispatchHandler == null) {
/*  874 */       this.dispatchHandler = new DispatchHandler();
/*      */     }
/*      */ 
/*  877 */     return this.dispatchHandler;
/*      */   }
/*      */ 
/*      */   protected void setDispatchHandler(DispatchHandler dispatchHandler) {
/*  881 */     this.dispatchHandler = dispatchHandler;
/*      */   }
/*      */ 
/*      */   protected XMLReader createXMLReader()
/*      */     throws SAXException
/*      */   {
/*  894 */     return SAXHelper.createXMLReader(isValidating());
/*      */   }
/*      */ 
/*      */   protected void configureReader(XMLReader reader, DefaultHandler handler)
/*      */     throws DocumentException
/*      */   {
/*  911 */     SAXHelper.setParserProperty(reader, "http://xml.org/sax/handlers/LexicalHandler", handler);
/*      */ 
/*  914 */     SAXHelper.setParserProperty(reader, "http://xml.org/sax/properties/lexical-handler", handler);
/*      */ 
/*  917 */     if ((this.includeInternalDTDDeclarations) || (this.includeExternalDTDDeclarations)) {
/*  918 */       SAXHelper.setParserProperty(reader, "http://xml.org/sax/properties/declaration-handler", handler);
/*      */     }
/*      */ 
/*  922 */     SAXHelper.setParserFeature(reader, "http://xml.org/sax/features/namespaces", true);
/*      */ 
/*  924 */     SAXHelper.setParserFeature(reader, "http://xml.org/sax/features/namespace-prefixes", false);
/*      */ 
/*  927 */     SAXHelper.setParserFeature(reader, "http://xml.org/sax/features/string-interning", isStringInternEnabled());
/*      */ 
/*  939 */     SAXHelper.setParserFeature(reader, "http://xml.org/sax/features/use-locator2", true);
/*      */     try
/*      */     {
/*  944 */       reader.setFeature("http://xml.org/sax/features/validation", isValidating());
/*      */ 
/*  947 */       if (this.errorHandler != null)
/*  948 */         reader.setErrorHandler(this.errorHandler);
/*      */       else
/*  950 */         reader.setErrorHandler(handler);
/*      */     }
/*      */     catch (Exception e) {
/*  953 */       if (isValidating())
/*  954 */         throw new DocumentException("Validation not supported for XMLReader: " + reader, e);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected SAXContentHandler createContentHandler(XMLReader reader)
/*      */   {
/*  969 */     return new SAXContentHandler(getDocumentFactory(), this.dispatchHandler);
/*      */   }
/*      */ 
/*      */   protected EntityResolver createDefaultEntityResolver(String systemId) {
/*  973 */     String prefix = null;
/*      */ 
/*  975 */     if ((systemId != null) && (systemId.length() > 0)) {
/*  976 */       int idx = systemId.lastIndexOf('/');
/*      */ 
/*  978 */       if (idx > 0) {
/*  979 */         prefix = systemId.substring(0, idx + 1);
/*      */       }
/*      */     }
/*      */ 
/*  983 */     return new SAXEntityResolver(prefix);
/*      */   }
/*      */ 
/*      */   protected static class SAXEntityResolver implements EntityResolver, Serializable
/*      */   {
/*      */     protected String uriPrefix;
/*      */ 
/*      */     public SAXEntityResolver(String uriPrefix) {
/*  991 */       this.uriPrefix = uriPrefix;
/*      */     }
/*      */ 
/*      */     public InputSource resolveEntity(String publicId, String systemId)
/*      */     {
/*  996 */       if ((systemId != null) && (systemId.length() > 0) && 
/*  997 */         (this.uriPrefix != null) && (systemId.indexOf(':') <= 0)) {
/*  998 */         systemId = this.uriPrefix + systemId;
/*      */       }
/*      */ 
/* 1002 */       return new InputSource(systemId);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXReader
 * JD-Core Version:    0.6.2
 */