/*    */ package org.jaxen.dom4j;
/*    */ 
/*    */ import org.jaxen.BaseXPath;
/*    */ import org.jaxen.JaxenException;
/*    */ 
/*    */ public class Dom4jXPath extends BaseXPath
/*    */ {
/*    */   private static final long serialVersionUID = -75510941087659775L;
/*    */ 
/*    */   public Dom4jXPath(String xpathExpr)
/*    */     throws JaxenException
/*    */   {
/* 92 */     super(xpathExpr, DocumentNavigator.getInstance());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.dom4j.Dom4jXPath
 * JD-Core Version:    0.6.2
 */