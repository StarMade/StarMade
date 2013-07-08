/*  1:   */package org.jaxen.expr;
/*  2:   */
/* 51:   */abstract class DefaultLogicalExpr
/* 52:   */  extends DefaultTruthExpr
/* 53:   */  implements LogicalExpr
/* 54:   */{
/* 55:   */  DefaultLogicalExpr(Expr lhs, Expr rhs)
/* 56:   */  {
/* 57:57 */    super(lhs, rhs);
/* 58:   */  }
/* 59:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultLogicalExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */