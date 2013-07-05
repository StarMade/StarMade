/*    */ package org.dom4j.io;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.io.Writer;
/*    */ import javax.xml.transform.sax.SAXResult;
/*    */ import org.xml.sax.ContentHandler;
/*    */ import org.xml.sax.ext.LexicalHandler;
/*    */ 
/*    */ public class XMLResult extends SAXResult
/*    */ {
/*    */   private XMLWriter xmlWriter;
/*    */ 
/*    */   public XMLResult()
/*    */   {
/* 33 */     this(new XMLWriter());
/*    */   }
/*    */ 
/*    */   public XMLResult(Writer writer) {
/* 37 */     this(new XMLWriter(writer));
/*    */   }
/*    */ 
/*    */   public XMLResult(Writer writer, OutputFormat format) {
/* 41 */     this(new XMLWriter(writer, format));
/*    */   }
/*    */ 
/*    */   public XMLResult(OutputStream out) throws UnsupportedEncodingException {
/* 45 */     this(new XMLWriter(out));
/*    */   }
/*    */ 
/*    */   public XMLResult(OutputStream out, OutputFormat format) throws UnsupportedEncodingException
/*    */   {
/* 50 */     this(new XMLWriter(out, format));
/*    */   }
/*    */ 
/*    */   public XMLResult(XMLWriter xmlWriter) {
/* 54 */     super(xmlWriter);
/* 55 */     this.xmlWriter = xmlWriter;
/* 56 */     setLexicalHandler(xmlWriter);
/*    */   }
/*    */ 
/*    */   public XMLWriter getXMLWriter() {
/* 60 */     return this.xmlWriter;
/*    */   }
/*    */ 
/*    */   public void setXMLWriter(XMLWriter writer) {
/* 64 */     this.xmlWriter = writer;
/* 65 */     setHandler(this.xmlWriter);
/* 66 */     setLexicalHandler(this.xmlWriter);
/*    */   }
/*    */ 
/*    */   public ContentHandler getHandler() {
/* 70 */     return this.xmlWriter;
/*    */   }
/*    */ 
/*    */   public LexicalHandler getLexicalHandler() {
/* 74 */     return this.xmlWriter;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.XMLResult
 * JD-Core Version:    0.6.2
 */