package org.jaxen.expr;

import org.jaxen.Context;

class DefaultNumberExpr
  extends DefaultExpr
  implements NumberExpr
{
  private static final long serialVersionUID = -6021898973386269611L;
  private Double number;
  
  DefaultNumberExpr(Double number)
  {
    this.number = number;
  }
  
  public Number getNumber()
  {
    return this.number;
  }
  
  public String toString()
  {
    return "[(DefaultNumberExpr): " + getNumber() + "]";
  }
  
  public String getText()
  {
    return getNumber().toString();
  }
  
  public Object evaluate(Context context)
  {
    return getNumber();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultNumberExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */