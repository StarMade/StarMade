/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */import org.jaxen.JaxenException;
/*  5:   */import org.jaxen.function.NumberFunction;
/*  6:   */
/* 57:   */class DefaultUnaryExpr
/* 58:   */  extends DefaultExpr
/* 59:   */  implements UnaryExpr
/* 60:   */{
/* 61:   */  private static final long serialVersionUID = 2303714238683092334L;
/* 62:   */  private Expr expr;
/* 63:   */  
/* 64:   */  DefaultUnaryExpr(Expr expr)
/* 65:   */  {
/* 66:66 */    this.expr = expr;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public Expr getExpr()
/* 70:   */  {
/* 71:71 */    return this.expr;
/* 72:   */  }
/* 73:   */  
/* 74:   */  public String toString()
/* 75:   */  {
/* 76:76 */    return "[(DefaultUnaryExpr): " + getExpr() + "]";
/* 77:   */  }
/* 78:   */  
/* 79:   */  public String getText()
/* 80:   */  {
/* 81:81 */    return "-(" + getExpr().getText() + ")";
/* 82:   */  }
/* 83:   */  
/* 84:   */  public Expr simplify()
/* 85:   */  {
/* 86:86 */    this.expr = this.expr.simplify();
/* 87:   */    
/* 88:88 */    return this;
/* 89:   */  }
/* 90:   */  
/* 91:   */  public Object evaluate(Context context) throws JaxenException
/* 92:   */  {
/* 93:93 */    Number number = NumberFunction.evaluate(getExpr().evaluate(context), context.getNavigator());
/* 94:   */    
/* 96:96 */    return new Double(number.doubleValue() * -1.0D);
/* 97:   */  }
/* 98:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultUnaryExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */