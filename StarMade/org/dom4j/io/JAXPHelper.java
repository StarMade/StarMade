/*  1:   */package org.dom4j.io;
/*  2:   */
/*  3:   */import javax.xml.parsers.DocumentBuilder;
/*  4:   */import javax.xml.parsers.DocumentBuilderFactory;
/*  5:   */import javax.xml.parsers.SAXParser;
/*  6:   */import javax.xml.parsers.SAXParserFactory;
/*  7:   */import org.w3c.dom.Document;
/*  8:   */import org.xml.sax.XMLReader;
/*  9:   */
/* 41:   */class JAXPHelper
/* 42:   */{
/* 43:   */  public static XMLReader createXMLReader(boolean validating, boolean namespaceAware)
/* 44:   */    throws Exception
/* 45:   */  {
/* 46:46 */    SAXParserFactory factory = SAXParserFactory.newInstance();
/* 47:47 */    factory.setValidating(validating);
/* 48:48 */    factory.setNamespaceAware(namespaceAware);
/* 49:   */    
/* 50:50 */    SAXParser parser = factory.newSAXParser();
/* 51:   */    
/* 52:52 */    return parser.getXMLReader();
/* 53:   */  }
/* 54:   */  
/* 55:   */  public static Document createDocument(boolean validating, boolean namespaceAware) throws Exception
/* 56:   */  {
/* 57:57 */    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/* 58:58 */    factory.setValidating(validating);
/* 59:59 */    factory.setNamespaceAware(namespaceAware);
/* 60:   */    
/* 61:61 */    DocumentBuilder builder = factory.newDocumentBuilder();
/* 62:   */    
/* 63:63 */    return builder.newDocument();
/* 64:   */  }
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.JAXPHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */