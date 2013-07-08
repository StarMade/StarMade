/*  1:   */package org.jaxen.expr;
/*  2:   */
/* 52:   */abstract class DefaultMultiplicativeExpr
/* 53:   */  extends DefaultArithExpr
/* 54:   */  implements MultiplicativeExpr
/* 55:   */{
/* 56:   */  DefaultMultiplicativeExpr(Expr lhs, Expr rhs)
/* 57:   */  {
/* 58:58 */    super(lhs, rhs);
/* 59:   */  }
/* 60:   */  
/* 62:   */  public String toString()
/* 63:   */  {
/* 64:64 */    return "[(DefaultMultiplicativeExpr): " + getLHS() + ", " + getRHS() + "]";
/* 65:   */  }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultMultiplicativeExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */