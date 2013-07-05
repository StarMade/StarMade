/*    */ package org.jaxen.expr;
/*    */ 
/*    */ abstract class DefaultLogicalExpr extends DefaultTruthExpr
/*    */   implements LogicalExpr
/*    */ {
/*    */   DefaultLogicalExpr(Expr lhs, Expr rhs)
/*    */   {
/* 57 */     super(lhs, rhs);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultLogicalExpr
 * JD-Core Version:    0.6.2
 */