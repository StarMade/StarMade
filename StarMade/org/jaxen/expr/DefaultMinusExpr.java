/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */import org.jaxen.JaxenException;
/*  5:   */import org.jaxen.function.NumberFunction;
/*  6:   */
/* 59:   */class DefaultMinusExpr
/* 60:   */  extends DefaultAdditiveExpr
/* 61:   */{
/* 62:   */  private static final long serialVersionUID = 6468563688098527800L;
/* 63:   */  
/* 64:   */  DefaultMinusExpr(Expr lhs, Expr rhs)
/* 65:   */  {
/* 66:66 */    super(lhs, rhs);
/* 67:   */  }
/* 68:   */  
/* 70:   */  public String getOperator()
/* 71:   */  {
/* 72:72 */    return "-";
/* 73:   */  }
/* 74:   */  
/* 75:   */  public Object evaluate(Context context) throws JaxenException
/* 76:   */  {
/* 77:77 */    Number lhsValue = NumberFunction.evaluate(getLHS().evaluate(context), context.getNavigator());
/* 78:   */    
/* 79:79 */    Number rhsValue = NumberFunction.evaluate(getRHS().evaluate(context), context.getNavigator());
/* 80:   */    
/* 82:82 */    double result = lhsValue.doubleValue() - rhsValue.doubleValue();
/* 83:83 */    return new Double(result);
/* 84:   */  }
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultMinusExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */