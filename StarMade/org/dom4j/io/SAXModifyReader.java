/*  1:   */package org.dom4j.io;
/*  2:   */
/*  3:   */import org.dom4j.DocumentFactory;
/*  4:   */import org.xml.sax.SAXException;
/*  5:   */import org.xml.sax.XMLReader;
/*  6:   */
/* 26:   */class SAXModifyReader
/* 27:   */  extends SAXReader
/* 28:   */{
/* 29:   */  private XMLWriter xmlWriter;
/* 30:   */  private boolean pruneElements;
/* 31:   */  
/* 32:   */  public SAXModifyReader() {}
/* 33:   */  
/* 34:   */  public SAXModifyReader(boolean validating)
/* 35:   */  {
/* 36:36 */    super(validating);
/* 37:   */  }
/* 38:   */  
/* 39:   */  public SAXModifyReader(DocumentFactory factory) {
/* 40:40 */    super(factory);
/* 41:   */  }
/* 42:   */  
/* 43:   */  public SAXModifyReader(DocumentFactory factory, boolean validating) {
/* 44:44 */    super(factory, validating);
/* 45:   */  }
/* 46:   */  
/* 47:   */  public SAXModifyReader(XMLReader xmlReader) {
/* 48:48 */    super(xmlReader);
/* 49:   */  }
/* 50:   */  
/* 51:   */  public SAXModifyReader(XMLReader xmlReader, boolean validating) {
/* 52:52 */    super(xmlReader, validating);
/* 53:   */  }
/* 54:   */  
/* 55:   */  public SAXModifyReader(String xmlReaderClassName) throws SAXException {
/* 56:56 */    super(xmlReaderClassName);
/* 57:   */  }
/* 58:   */  
/* 59:   */  public SAXModifyReader(String xmlReaderClassName, boolean validating) throws SAXException
/* 60:   */  {
/* 61:61 */    super(xmlReaderClassName, validating);
/* 62:   */  }
/* 63:   */  
/* 64:   */  public void setXMLWriter(XMLWriter writer) {
/* 65:65 */    this.xmlWriter = writer;
/* 66:   */  }
/* 67:   */  
/* 68:   */  public boolean isPruneElements() {
/* 69:69 */    return this.pruneElements;
/* 70:   */  }
/* 71:   */  
/* 72:   */  public void setPruneElements(boolean pruneElements) {
/* 73:73 */    this.pruneElements = pruneElements;
/* 74:   */  }
/* 75:   */  
/* 76:   */  protected SAXContentHandler createContentHandler(XMLReader reader) {
/* 77:77 */    SAXModifyContentHandler handler = new SAXModifyContentHandler(getDocumentFactory(), getDispatchHandler());
/* 78:   */    
/* 80:80 */    handler.setXMLWriter(this.xmlWriter);
/* 81:   */    
/* 82:82 */    return handler;
/* 83:   */  }
/* 84:   */  
/* 85:   */  protected XMLWriter getXMLWriter() {
/* 86:86 */    return this.xmlWriter;
/* 87:   */  }
/* 88:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXModifyReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */