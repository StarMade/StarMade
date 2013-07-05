/*    */ package org.jaxen.expr;
/*    */ 
/*    */ class DefaultGreaterThanExpr extends DefaultRelationalExpr
/*    */ {
/*    */   private static final long serialVersionUID = 6379252220540222867L;
/*    */ 
/*    */   DefaultGreaterThanExpr(Expr lhs, Expr rhs)
/*    */   {
/* 59 */     super(lhs, rhs);
/*    */   }
/*    */ 
/*    */   public String getOperator()
/*    */   {
/* 64 */     return ">";
/*    */   }
/*    */ 
/*    */   protected boolean evaluateDoubleDouble(Double lhs, Double rhs)
/*    */   {
/* 69 */     return lhs.compareTo(rhs) > 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultGreaterThanExpr
 * JD-Core Version:    0.6.2
 */