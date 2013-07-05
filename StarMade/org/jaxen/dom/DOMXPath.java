/*    */ package org.jaxen.dom;
/*    */ 
/*    */ import org.jaxen.BaseXPath;
/*    */ import org.jaxen.JaxenException;
/*    */ 
/*    */ public class DOMXPath extends BaseXPath
/*    */ {
/*    */   private static final long serialVersionUID = 5551221776001439091L;
/*    */ 
/*    */   public DOMXPath(String xpathExpr)
/*    */     throws JaxenException
/*    */   {
/* 87 */     super(xpathExpr, DocumentNavigator.getInstance());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.dom.DOMXPath
 * JD-Core Version:    0.6.2
 */