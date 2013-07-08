/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.function.NumberFunction;
/*  4:   */
/* 56:   */class DefaultEqualsExpr
/* 57:   */  extends DefaultEqualityExpr
/* 58:   */{
/* 59:   */  private static final long serialVersionUID = -8327599812627931648L;
/* 60:   */  
/* 61:   */  DefaultEqualsExpr(Expr lhs, Expr rhs)
/* 62:   */  {
/* 63:63 */    super(lhs, rhs);
/* 64:   */  }
/* 65:   */  
/* 66:   */  public String getOperator()
/* 67:   */  {
/* 68:68 */    return "=";
/* 69:   */  }
/* 70:   */  
/* 71:   */  public String toString()
/* 72:   */  {
/* 73:73 */    return "[(DefaultEqualsExpr): " + getLHS() + ", " + getRHS() + "]";
/* 74:   */  }
/* 75:   */  
/* 76:   */  protected boolean evaluateObjectObject(Object lhs, Object rhs)
/* 77:   */  {
/* 78:78 */    if (eitherIsNumber(lhs, rhs))
/* 79:   */    {
/* 80:80 */      if ((NumberFunction.isNaN((Double)lhs)) || (NumberFunction.isNaN((Double)rhs)))
/* 81:   */      {
/* 82:82 */        return false;
/* 83:   */      }
/* 84:   */    }
/* 85:   */    
/* 86:86 */    return lhs.equals(rhs);
/* 87:   */  }
/* 88:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultEqualsExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */