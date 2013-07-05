/*    */ package org.jaxen.expr.iter;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.jaxen.ContextSupport;
/*    */ import org.jaxen.Navigator;
/*    */ import org.jaxen.UnsupportedAxisException;
/*    */ 
/*    */ public class IterableNamespaceAxis extends IterableAxis
/*    */ {
/*    */   private static final long serialVersionUID = -8022585664651357087L;
/*    */ 
/*    */   public IterableNamespaceAxis(int value)
/*    */   {
/* 65 */     super(value);
/*    */   }
/*    */ 
/*    */   public Iterator iterator(Object contextNode, ContextSupport support)
/*    */     throws UnsupportedAxisException
/*    */   {
/* 71 */     return support.getNavigator().getNamespaceAxisIterator(contextNode);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.iter.IterableNamespaceAxis
 * JD-Core Version:    0.6.2
 */