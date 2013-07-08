/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */import org.jaxen.JaxenException;
/*  5:   */import org.jaxen.Navigator;
/*  6:   */import org.jaxen.function.BooleanFunction;
/*  7:   */
/* 58:   */class DefaultAndExpr
/* 59:   */  extends DefaultLogicalExpr
/* 60:   */{
/* 61:   */  private static final long serialVersionUID = -5237984010263103742L;
/* 62:   */  
/* 63:   */  DefaultAndExpr(Expr lhs, Expr rhs)
/* 64:   */  {
/* 65:65 */    super(lhs, rhs);
/* 66:   */  }
/* 67:   */  
/* 69:   */  public String getOperator()
/* 70:   */  {
/* 71:71 */    return "and";
/* 72:   */  }
/* 73:   */  
/* 74:   */  public String toString()
/* 75:   */  {
/* 76:76 */    return "[(DefaultAndExpr): " + getLHS() + ", " + getRHS() + "]";
/* 77:   */  }
/* 78:   */  
/* 79:   */  public Object evaluate(Context context) throws JaxenException
/* 80:   */  {
/* 81:81 */    Navigator nav = context.getNavigator();
/* 82:82 */    Boolean lhsValue = BooleanFunction.evaluate(getLHS().evaluate(context), nav);
/* 83:   */    
/* 84:84 */    if (!lhsValue.booleanValue())
/* 85:   */    {
/* 86:86 */      return Boolean.FALSE;
/* 87:   */    }
/* 88:   */    
/* 91:91 */    Boolean rhsValue = BooleanFunction.evaluate(getRHS().evaluate(context), nav);
/* 92:   */    
/* 93:93 */    if (!rhsValue.booleanValue())
/* 94:   */    {
/* 95:95 */      return Boolean.FALSE;
/* 96:   */    }
/* 97:   */    
/* 98:98 */    return Boolean.TRUE;
/* 99:   */  }
/* 100:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultAndExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */