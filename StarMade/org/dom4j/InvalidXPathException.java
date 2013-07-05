/*    */ package org.dom4j;
/*    */ 
/*    */ public class InvalidXPathException extends IllegalArgumentException
/*    */ {
/*    */   private static final long serialVersionUID = 3257009869058881592L;
/*    */ 
/*    */   public InvalidXPathException(String xpath)
/*    */   {
/* 23 */     super("Invalid XPath expression: " + xpath);
/*    */   }
/*    */ 
/*    */   public InvalidXPathException(String xpath, String reason) {
/* 27 */     super("Invalid XPath expression: " + xpath + " " + reason);
/*    */   }
/*    */ 
/*    */   public InvalidXPathException(String xpath, Throwable t) {
/* 31 */     super("Invalid XPath expression: '" + xpath + "'. Caused by: " + t.getMessage());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.InvalidXPathException
 * JD-Core Version:    0.6.2
 */