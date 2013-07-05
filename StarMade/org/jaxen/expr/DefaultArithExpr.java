/*    */ package org.jaxen.expr;
/*    */ 
/*    */ abstract class DefaultArithExpr extends DefaultBinaryExpr
/*    */ {
/*    */   DefaultArithExpr(Expr lhs, Expr rhs)
/*    */   {
/* 57 */     super(lhs, rhs);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 63 */     return "[(DefaultArithExpr): " + getLHS() + ", " + getRHS() + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultArithExpr
 * JD-Core Version:    0.6.2
 */