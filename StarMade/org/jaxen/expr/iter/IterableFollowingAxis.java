/*    */ package org.jaxen.expr.iter;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.jaxen.ContextSupport;
/*    */ import org.jaxen.Navigator;
/*    */ import org.jaxen.UnsupportedAxisException;
/*    */ 
/*    */ public class IterableFollowingAxis extends IterableAxis
/*    */ {
/*    */   private static final long serialVersionUID = -7100245752300813209L;
/*    */ 
/*    */   public IterableFollowingAxis(int value)
/*    */   {
/* 65 */     super(value);
/*    */   }
/*    */ 
/*    */   public Iterator iterator(Object contextNode, ContextSupport support)
/*    */     throws UnsupportedAxisException
/*    */   {
/* 71 */     return support.getNavigator().getFollowingAxisIterator(contextNode);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.iter.IterableFollowingAxis
 * JD-Core Version:    0.6.2
 */