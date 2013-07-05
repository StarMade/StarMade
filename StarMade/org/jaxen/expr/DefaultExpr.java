/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.jaxen.util.SingleObjectIterator;
/*    */ import org.jaxen.util.SingletonList;
/*    */ 
/*    */ /** @deprecated */
/*    */ public abstract class DefaultExpr
/*    */   implements Expr
/*    */ {
/*    */   public Expr simplify()
/*    */   {
/* 66 */     return this;
/*    */   }
/*    */ 
/*    */   public static Iterator convertToIterator(Object obj)
/*    */   {
/* 71 */     if ((obj instanceof Iterator))
/*    */     {
/* 73 */       return (Iterator)obj;
/*    */     }
/*    */ 
/* 76 */     if ((obj instanceof List))
/*    */     {
/* 78 */       return ((List)obj).iterator();
/*    */     }
/*    */ 
/* 81 */     return new SingleObjectIterator(obj);
/*    */   }
/*    */ 
/*    */   public static List convertToList(Object obj)
/*    */   {
/* 86 */     if ((obj instanceof List))
/*    */     {
/* 88 */       return (List)obj;
/*    */     }
/*    */ 
/* 91 */     return new SingletonList(obj);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultExpr
 * JD-Core Version:    0.6.2
 */