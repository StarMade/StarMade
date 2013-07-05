/*    */ package org.apache.ws.commons.serialize;
/*    */ 
/*    */ import java.nio.charset.Charset;
/*    */ import java.nio.charset.CharsetEncoder;
/*    */ import org.xml.sax.SAXException;
/*    */ 
/*    */ public class CharSetXMLWriter extends XMLWriterImpl
/*    */ {
/*    */   private CharsetEncoder charsetEncoder;
/*    */ 
/*    */   public void startDocument()
/*    */     throws SAXException
/*    */   {
/* 32 */     String enc = getEncoding();
/* 33 */     if (enc == null) {
/* 34 */       enc = "UTF-8";
/*    */     }
/* 36 */     Charset charSet = Charset.forName(enc);
/* 37 */     if (charSet.canEncode()) {
/* 38 */       this.charsetEncoder = charSet.newEncoder();
/*    */     }
/* 40 */     super.startDocument();
/*    */   }
/*    */ 
/*    */   public boolean canEncode(char c) {
/* 44 */     return this.charsetEncoder == null ? super.canEncode(c) : this.charsetEncoder.canEncode(c);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.serialize.CharSetXMLWriter
 * JD-Core Version:    0.6.2
 */