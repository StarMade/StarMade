/*    */ package org.dom4j.io;
/*    */ 
/*    */ import org.dom4j.DocumentFactory;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.XMLReader;
/*    */ 
/*    */ class SAXModifyReader extends SAXReader
/*    */ {
/*    */   private XMLWriter xmlWriter;
/*    */   private boolean pruneElements;
/*    */ 
/*    */   public SAXModifyReader()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SAXModifyReader(boolean validating)
/*    */   {
/* 36 */     super(validating);
/*    */   }
/*    */ 
/*    */   public SAXModifyReader(DocumentFactory factory) {
/* 40 */     super(factory);
/*    */   }
/*    */ 
/*    */   public SAXModifyReader(DocumentFactory factory, boolean validating) {
/* 44 */     super(factory, validating);
/*    */   }
/*    */ 
/*    */   public SAXModifyReader(XMLReader xmlReader) {
/* 48 */     super(xmlReader);
/*    */   }
/*    */ 
/*    */   public SAXModifyReader(XMLReader xmlReader, boolean validating) {
/* 52 */     super(xmlReader, validating);
/*    */   }
/*    */ 
/*    */   public SAXModifyReader(String xmlReaderClassName) throws SAXException {
/* 56 */     super(xmlReaderClassName);
/*    */   }
/*    */ 
/*    */   public SAXModifyReader(String xmlReaderClassName, boolean validating) throws SAXException
/*    */   {
/* 61 */     super(xmlReaderClassName, validating);
/*    */   }
/*    */ 
/*    */   public void setXMLWriter(XMLWriter writer) {
/* 65 */     this.xmlWriter = writer;
/*    */   }
/*    */ 
/*    */   public boolean isPruneElements() {
/* 69 */     return this.pruneElements;
/*    */   }
/*    */ 
/*    */   public void setPruneElements(boolean pruneElements) {
/* 73 */     this.pruneElements = pruneElements;
/*    */   }
/*    */ 
/*    */   protected SAXContentHandler createContentHandler(XMLReader reader) {
/* 77 */     SAXModifyContentHandler handler = new SAXModifyContentHandler(getDocumentFactory(), getDispatchHandler());
/*    */ 
/* 80 */     handler.setXMLWriter(this.xmlWriter);
/*    */ 
/* 82 */     return handler;
/*    */   }
/*    */ 
/*    */   protected XMLWriter getXMLWriter() {
/* 86 */     return this.xmlWriter;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXModifyReader
 * JD-Core Version:    0.6.2
 */