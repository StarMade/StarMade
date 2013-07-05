/*    */ package org.jaxen.expr.iter;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.jaxen.ContextSupport;
/*    */ import org.jaxen.Navigator;
/*    */ import org.jaxen.UnsupportedAxisException;
/*    */ 
/*    */ public class IterableAncestorAxis extends IterableAxis
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public IterableAncestorAxis(int value)
/*    */   {
/* 66 */     super(value);
/*    */   }
/*    */ 
/*    */   public Iterator iterator(Object contextNode, ContextSupport support)
/*    */     throws UnsupportedAxisException
/*    */   {
/* 72 */     return support.getNavigator().getAncestorAxisIterator(contextNode);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.iter.IterableAncestorAxis
 * JD-Core Version:    0.6.2
 */