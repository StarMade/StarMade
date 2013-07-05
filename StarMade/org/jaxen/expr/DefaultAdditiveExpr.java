/*    */ package org.jaxen.expr;
/*    */ 
/*    */ abstract class DefaultAdditiveExpr extends DefaultArithExpr
/*    */   implements AdditiveExpr
/*    */ {
/*    */   DefaultAdditiveExpr(Expr lhs, Expr rhs)
/*    */   {
/* 55 */     super(lhs, rhs);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 61 */     return "[(" + getClass().getName() + "): " + getLHS() + ", " + getRHS() + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultAdditiveExpr
 * JD-Core Version:    0.6.2
 */