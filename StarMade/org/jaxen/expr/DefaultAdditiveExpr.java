/*  1:   */package org.jaxen.expr;
/*  2:   */
/* 49:   */abstract class DefaultAdditiveExpr
/* 50:   */  extends DefaultArithExpr
/* 51:   */  implements AdditiveExpr
/* 52:   */{
/* 53:   */  DefaultAdditiveExpr(Expr lhs, Expr rhs)
/* 54:   */  {
/* 55:55 */    super(lhs, rhs);
/* 56:   */  }
/* 57:   */  
/* 59:   */  public String toString()
/* 60:   */  {
/* 61:61 */    return "[(" + getClass().getName() + "): " + getLHS() + ", " + getRHS() + "]";
/* 62:   */  }
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultAdditiveExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */