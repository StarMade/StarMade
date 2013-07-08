/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import org.dom4j.Document;
/*   5:    */import org.xml.sax.ContentHandler;
/*   6:    */import org.xml.sax.ErrorHandler;
/*   7:    */import org.xml.sax.SAXException;
/*   8:    */import org.xml.sax.XMLReader;
/*   9:    */import org.xml.sax.helpers.DefaultHandler;
/*  10:    */
/*  34:    */public class SAXValidator
/*  35:    */{
/*  36:    */  private XMLReader xmlReader;
/*  37:    */  private ErrorHandler errorHandler;
/*  38:    */  
/*  39:    */  public SAXValidator() {}
/*  40:    */  
/*  41:    */  public SAXValidator(XMLReader xmlReader)
/*  42:    */  {
/*  43: 43 */    this.xmlReader = xmlReader;
/*  44:    */  }
/*  45:    */  
/*  56:    */  public void validate(Document document)
/*  57:    */    throws SAXException
/*  58:    */  {
/*  59: 59 */    if (document != null) {
/*  60: 60 */      XMLReader reader = getXMLReader();
/*  61:    */      
/*  62: 62 */      if (this.errorHandler != null) {
/*  63: 63 */        reader.setErrorHandler(this.errorHandler);
/*  64:    */      }
/*  65:    */      try
/*  66:    */      {
/*  67: 67 */        reader.parse(new DocumentInputSource(document));
/*  68:    */      } catch (IOException e) {
/*  69: 69 */        throw new RuntimeException("Caught and exception that should never happen: " + e);
/*  70:    */      }
/*  71:    */    }
/*  72:    */  }
/*  73:    */  
/*  84:    */  public XMLReader getXMLReader()
/*  85:    */    throws SAXException
/*  86:    */  {
/*  87: 87 */    if (this.xmlReader == null) {
/*  88: 88 */      this.xmlReader = createXMLReader();
/*  89: 89 */      configureReader();
/*  90:    */    }
/*  91:    */    
/*  92: 92 */    return this.xmlReader;
/*  93:    */  }
/*  94:    */  
/* 102:    */  public void setXMLReader(XMLReader reader)
/* 103:    */    throws SAXException
/* 104:    */  {
/* 105:105 */    this.xmlReader = reader;
/* 106:106 */    configureReader();
/* 107:    */  }
/* 108:    */  
/* 113:    */  public ErrorHandler getErrorHandler()
/* 114:    */  {
/* 115:115 */    return this.errorHandler;
/* 116:    */  }
/* 117:    */  
/* 124:    */  public void setErrorHandler(ErrorHandler errorHandler)
/* 125:    */  {
/* 126:126 */    this.errorHandler = errorHandler;
/* 127:    */  }
/* 128:    */  
/* 139:    */  protected XMLReader createXMLReader()
/* 140:    */    throws SAXException
/* 141:    */  {
/* 142:142 */    return SAXHelper.createXMLReader(true);
/* 143:    */  }
/* 144:    */  
/* 149:    */  protected void configureReader()
/* 150:    */    throws SAXException
/* 151:    */  {
/* 152:152 */    ContentHandler handler = this.xmlReader.getContentHandler();
/* 153:    */    
/* 154:154 */    if (handler == null) {
/* 155:155 */      this.xmlReader.setContentHandler(new DefaultHandler());
/* 156:    */    }
/* 157:    */    
/* 159:159 */    this.xmlReader.setFeature("http://xml.org/sax/features/validation", true);
/* 160:    */    
/* 162:162 */    this.xmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
/* 163:163 */    this.xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
/* 164:    */  }
/* 165:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXValidator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */