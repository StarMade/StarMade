/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */
/* 56:   */class DefaultNumberExpr
/* 57:   */  extends DefaultExpr
/* 58:   */  implements NumberExpr
/* 59:   */{
/* 60:   */  private static final long serialVersionUID = -6021898973386269611L;
/* 61:   */  private Double number;
/* 62:   */  
/* 63:   */  DefaultNumberExpr(Double number)
/* 64:   */  {
/* 65:65 */    this.number = number;
/* 66:   */  }
/* 67:   */  
/* 68:   */  public Number getNumber()
/* 69:   */  {
/* 70:70 */    return this.number;
/* 71:   */  }
/* 72:   */  
/* 73:   */  public String toString()
/* 74:   */  {
/* 75:75 */    return "[(DefaultNumberExpr): " + getNumber() + "]";
/* 76:   */  }
/* 77:   */  
/* 78:   */  public String getText()
/* 79:   */  {
/* 80:80 */    return getNumber().toString();
/* 81:   */  }
/* 82:   */  
/* 83:   */  public Object evaluate(Context context)
/* 84:   */  {
/* 85:85 */    return getNumber();
/* 86:   */  }
/* 87:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultNumberExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */