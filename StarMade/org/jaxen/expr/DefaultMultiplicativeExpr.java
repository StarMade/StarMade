/*    */ package org.jaxen.expr;
/*    */ 
/*    */ abstract class DefaultMultiplicativeExpr extends DefaultArithExpr
/*    */   implements MultiplicativeExpr
/*    */ {
/*    */   DefaultMultiplicativeExpr(Expr lhs, Expr rhs)
/*    */   {
/* 58 */     super(lhs, rhs);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 64 */     return "[(DefaultMultiplicativeExpr): " + getLHS() + ", " + getRHS() + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultMultiplicativeExpr
 * JD-Core Version:    0.6.2
 */