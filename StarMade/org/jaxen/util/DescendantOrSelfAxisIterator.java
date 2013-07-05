/*    */ package org.jaxen.util;
/*    */ 
/*    */ import org.jaxen.Navigator;
/*    */ 
/*    */ public class DescendantOrSelfAxisIterator extends DescendantAxisIterator
/*    */ {
/*    */   public DescendantOrSelfAxisIterator(Object contextNode, Navigator navigator)
/*    */   {
/* 72 */     super(navigator, new SingleObjectIterator(contextNode));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.DescendantOrSelfAxisIterator
 * JD-Core Version:    0.6.2
 */