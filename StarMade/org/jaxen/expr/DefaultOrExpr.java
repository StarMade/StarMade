/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import org.jaxen.Context;
/*   4:    */import org.jaxen.JaxenException;
/*   5:    */import org.jaxen.Navigator;
/*   6:    */import org.jaxen.function.BooleanFunction;
/*   7:    */
/*  60:    */class DefaultOrExpr
/*  61:    */  extends DefaultLogicalExpr
/*  62:    */{
/*  63:    */  private static final long serialVersionUID = 4894552680753026730L;
/*  64:    */  
/*  65:    */  DefaultOrExpr(Expr lhs, Expr rhs)
/*  66:    */  {
/*  67: 67 */    super(lhs, rhs);
/*  68:    */  }
/*  69:    */  
/*  71:    */  public String getOperator()
/*  72:    */  {
/*  73: 73 */    return "or";
/*  74:    */  }
/*  75:    */  
/*  76:    */  public String toString()
/*  77:    */  {
/*  78: 78 */    return "[(DefaultOrExpr): " + getLHS() + ", " + getRHS() + "]";
/*  79:    */  }
/*  80:    */  
/*  81:    */  public Object evaluate(Context context) throws JaxenException
/*  82:    */  {
/*  83: 83 */    Navigator nav = context.getNavigator();
/*  84: 84 */    Boolean lhsValue = BooleanFunction.evaluate(getLHS().evaluate(context), nav);
/*  85:    */    
/*  86: 86 */    if (lhsValue.booleanValue())
/*  87:    */    {
/*  88: 88 */      return Boolean.TRUE;
/*  89:    */    }
/*  90:    */    
/*  93: 93 */    Boolean rhsValue = BooleanFunction.evaluate(getRHS().evaluate(context), nav);
/*  94:    */    
/*  95: 95 */    if (rhsValue.booleanValue())
/*  96:    */    {
/*  97: 97 */      return Boolean.TRUE;
/*  98:    */    }
/*  99:    */    
/* 100:100 */    return Boolean.FALSE;
/* 101:    */  }
/* 102:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultOrExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */