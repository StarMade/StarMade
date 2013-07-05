/*    */ package org.jaxen.jdom;
/*    */ 
/*    */ import org.jaxen.BaseXPath;
/*    */ import org.jaxen.JaxenException;
/*    */ 
/*    */ public class JDOMXPath extends BaseXPath
/*    */ {
/*    */   private static final long serialVersionUID = 6426091824802286928L;
/*    */ 
/*    */   public JDOMXPath(String xpathExpr)
/*    */     throws JaxenException
/*    */   {
/* 91 */     super(xpathExpr, DocumentNavigator.getInstance());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.jdom.JDOMXPath
 * JD-Core Version:    0.6.2
 */