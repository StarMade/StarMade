/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */import org.jaxen.JaxenException;
/*  5:   */import org.jaxen.function.NumberFunction;
/*  6:   */
/* 58:   */class DefaultDivExpr
/* 59:   */  extends DefaultMultiplicativeExpr
/* 60:   */{
/* 61:   */  private static final long serialVersionUID = 6318739386201615441L;
/* 62:   */  
/* 63:   */  DefaultDivExpr(Expr lhs, Expr rhs)
/* 64:   */  {
/* 65:65 */    super(lhs, rhs);
/* 66:   */  }
/* 67:   */  
/* 69:   */  public String getOperator()
/* 70:   */  {
/* 71:71 */    return "div";
/* 72:   */  }
/* 73:   */  
/* 74:   */  public Object evaluate(Context context) throws JaxenException
/* 75:   */  {
/* 76:76 */    Number lhsValue = NumberFunction.evaluate(getLHS().evaluate(context), context.getNavigator());
/* 77:   */    
/* 78:78 */    Number rhsValue = NumberFunction.evaluate(getRHS().evaluate(context), context.getNavigator());
/* 79:   */    
/* 81:81 */    double result = lhsValue.doubleValue() / rhsValue.doubleValue();
/* 82:   */    
/* 83:83 */    return new Double(result);
/* 84:   */  }
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultDivExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */