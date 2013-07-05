/*    */ package org.jaxen.expr.iter;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.jaxen.ContextSupport;
/*    */ import org.jaxen.Navigator;
/*    */ import org.jaxen.UnsupportedAxisException;
/*    */ 
/*    */ public class IterableSelfAxis extends IterableAxis
/*    */ {
/*    */   private static final long serialVersionUID = 8292222516706760134L;
/*    */ 
/*    */   public IterableSelfAxis(int value)
/*    */   {
/* 65 */     super(value);
/*    */   }
/*    */ 
/*    */   public Iterator iterator(Object contextNode, ContextSupport support)
/*    */     throws UnsupportedAxisException
/*    */   {
/* 71 */     return support.getNavigator().getSelfAxisIterator(contextNode);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.iter.IterableSelfAxis
 * JD-Core Version:    0.6.2
 */