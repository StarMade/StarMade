/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */import org.jaxen.JaxenException;
/*  5:   */import org.jaxen.function.NumberFunction;
/*  6:   */
/* 57:   */class DefaultModExpr
/* 58:   */  extends DefaultMultiplicativeExpr
/* 59:   */{
/* 60:   */  private static final long serialVersionUID = -5554964716492040687L;
/* 61:   */  
/* 62:   */  DefaultModExpr(Expr lhs, Expr rhs)
/* 63:   */  {
/* 64:64 */    super(lhs, rhs);
/* 65:   */  }
/* 66:   */  
/* 68:   */  public String getOperator()
/* 69:   */  {
/* 70:70 */    return "mod";
/* 71:   */  }
/* 72:   */  
/* 73:   */  public Object evaluate(Context context) throws JaxenException
/* 74:   */  {
/* 75:75 */    Number lhsValue = NumberFunction.evaluate(getLHS().evaluate(context), context.getNavigator());
/* 76:   */    
/* 77:77 */    Number rhsValue = NumberFunction.evaluate(getRHS().evaluate(context), context.getNavigator());
/* 78:   */    
/* 80:80 */    double result = lhsValue.doubleValue() % rhsValue.doubleValue();
/* 81:81 */    return new Double(result);
/* 82:   */  }
/* 83:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultModExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */