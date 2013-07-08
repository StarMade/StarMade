/*  1:   */package org.jaxen.expr;
/*  2:   */
/* 52:   */abstract class DefaultArithExpr
/* 53:   */  extends DefaultBinaryExpr
/* 54:   */{
/* 55:   */  DefaultArithExpr(Expr lhs, Expr rhs)
/* 56:   */  {
/* 57:57 */    super(lhs, rhs);
/* 58:   */  }
/* 59:   */  
/* 61:   */  public String toString()
/* 62:   */  {
/* 63:63 */    return "[(DefaultArithExpr): " + getLHS() + ", " + getRHS() + "]";
/* 64:   */  }
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultArithExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */