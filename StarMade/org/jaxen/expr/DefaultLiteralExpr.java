package org.jaxen.expr;

import org.jaxen.Context;

class DefaultLiteralExpr
  extends DefaultExpr
  implements LiteralExpr
{
  private static final long serialVersionUID = -953829179036273338L;
  private String literal;
  
  DefaultLiteralExpr(String literal)
  {
    this.literal = literal;
  }
  
  public String getLiteral()
  {
    return this.literal;
  }
  
  public String toString()
  {
    return "[(DefaultLiteralExpr): " + getLiteral() + "]";
  }
  
  public String getText()
  {
    if (this.literal.indexOf('"') == -1) {
      return "\"" + getLiteral() + "\"";
    }
    return "'" + getLiteral() + "'";
  }
  
  public Object evaluate(Context context)
  {
    return getLiteral();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultLiteralExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */