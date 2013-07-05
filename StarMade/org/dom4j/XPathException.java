/*    */ package org.dom4j;
/*    */ 
/*    */ public class XPathException extends RuntimeException
/*    */ {
/*    */   private String xpath;
/*    */ 
/*    */   public XPathException(String xpath)
/*    */   {
/* 24 */     super("Exception occurred evaluting XPath: " + xpath);
/* 25 */     this.xpath = xpath;
/*    */   }
/*    */ 
/*    */   public XPathException(String xpath, String reason) {
/* 29 */     super("Exception occurred evaluting XPath: " + xpath + " " + reason);
/* 30 */     this.xpath = xpath;
/*    */   }
/*    */ 
/*    */   public XPathException(String xpath, Exception e) {
/* 34 */     super("Exception occurred evaluting XPath: " + xpath + ". Exception: " + e.getMessage());
/*    */ 
/* 36 */     this.xpath = xpath;
/*    */   }
/*    */ 
/*    */   public String getXPath()
/*    */   {
/* 45 */     return this.xpath;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.XPathException
 * JD-Core Version:    0.6.2
 */