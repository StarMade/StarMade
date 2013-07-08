/*  1:   */package org.jaxen.expr;
/*  2:   */
/* 28:   */class DefaultGreaterThanExpr
/* 29:   */  extends DefaultRelationalExpr
/* 30:   */{
/* 31:   */  private static final long serialVersionUID = 6379252220540222867L;
/* 32:   */  
/* 57:   */  DefaultGreaterThanExpr(Expr lhs, Expr rhs)
/* 58:   */  {
/* 59:59 */    super(lhs, rhs);
/* 60:   */  }
/* 61:   */  
/* 62:   */  public String getOperator()
/* 63:   */  {
/* 64:64 */    return ">";
/* 65:   */  }
/* 66:   */  
/* 67:   */  protected boolean evaluateDoubleDouble(Double lhs, Double rhs)
/* 68:   */  {
/* 69:69 */    return lhs.compareTo(rhs) > 0;
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultGreaterThanExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */