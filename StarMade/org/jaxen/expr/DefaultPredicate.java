/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */import org.jaxen.JaxenException;
/*  5:   */
/* 57:   */class DefaultPredicate
/* 58:   */  implements Predicate
/* 59:   */{
/* 60:   */  private static final long serialVersionUID = -4140068594075364971L;
/* 61:   */  private Expr expr;
/* 62:   */  
/* 63:   */  DefaultPredicate(Expr expr)
/* 64:   */  {
/* 65:65 */    setExpr(expr);
/* 66:   */  }
/* 67:   */  
/* 68:   */  public Expr getExpr()
/* 69:   */  {
/* 70:70 */    return this.expr;
/* 71:   */  }
/* 72:   */  
/* 73:   */  public void setExpr(Expr expr)
/* 74:   */  {
/* 75:75 */    this.expr = expr;
/* 76:   */  }
/* 77:   */  
/* 78:   */  public String getText()
/* 79:   */  {
/* 80:80 */    return "[" + getExpr().getText() + "]";
/* 81:   */  }
/* 82:   */  
/* 83:   */  public String toString()
/* 84:   */  {
/* 85:85 */    return "[(DefaultPredicate): " + getExpr() + "]";
/* 86:   */  }
/* 87:   */  
/* 88:   */  public void simplify()
/* 89:   */  {
/* 90:90 */    setExpr(getExpr().simplify());
/* 91:   */  }
/* 92:   */  
/* 93:   */  public Object evaluate(Context context) throws JaxenException
/* 94:   */  {
/* 95:95 */    return getExpr().evaluate(context);
/* 96:   */  }
/* 97:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultPredicate
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */