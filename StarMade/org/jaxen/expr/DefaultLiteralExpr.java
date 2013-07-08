/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */
/* 55:   */class DefaultLiteralExpr
/* 56:   */  extends DefaultExpr
/* 57:   */  implements LiteralExpr
/* 58:   */{
/* 59:   */  private static final long serialVersionUID = -953829179036273338L;
/* 60:   */  private String literal;
/* 61:   */  
/* 62:   */  DefaultLiteralExpr(String literal)
/* 63:   */  {
/* 64:64 */    this.literal = literal;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public String getLiteral()
/* 68:   */  {
/* 69:69 */    return this.literal;
/* 70:   */  }
/* 71:   */  
/* 72:   */  public String toString()
/* 73:   */  {
/* 74:74 */    return "[(DefaultLiteralExpr): " + getLiteral() + "]";
/* 75:   */  }
/* 76:   */  
/* 78:   */  public String getText()
/* 79:   */  {
/* 80:80 */    if (this.literal.indexOf('"') == -1) {
/* 81:81 */      return "\"" + getLiteral() + "\"";
/* 82:   */    }
/* 83:   */    
/* 84:84 */    return "'" + getLiteral() + "'";
/* 85:   */  }
/* 86:   */  
/* 89:   */  public Object evaluate(Context context)
/* 90:   */  {
/* 91:91 */    return getLiteral();
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultLiteralExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */