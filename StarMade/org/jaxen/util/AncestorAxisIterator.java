/*    */ package org.jaxen.util;
/*    */ 
/*    */ import org.jaxen.Navigator;
/*    */ 
/*    */ public class AncestorAxisIterator extends AncestorOrSelfAxisIterator
/*    */ {
/*    */   public AncestorAxisIterator(Object contextNode, Navigator navigator)
/*    */   {
/* 76 */     super(contextNode, navigator);
/*    */ 
/* 78 */     next();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.AncestorAxisIterator
 * JD-Core Version:    0.6.2
 */