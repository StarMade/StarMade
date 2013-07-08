package org.jaxen.expr;

import java.util.List;

abstract class DefaultTruthExpr
  extends DefaultBinaryExpr
{
  DefaultTruthExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String toString()
  {
    return "[(DefaultTruthExpr): " + getLHS() + ", " + getRHS() + "]";
  }
  
  protected boolean bothAreSets(Object lhs, Object rhs)
  {
    return ((lhs instanceof List)) && ((rhs instanceof List));
  }
  
  protected boolean eitherIsSet(Object lhs, Object rhs)
  {
    return ((lhs instanceof List)) || ((rhs instanceof List));
  }
  
  protected boolean isSet(Object obj)
  {
    return obj instanceof List;
  }
  
  protected boolean isBoolean(Object obj)
  {
    return obj instanceof Boolean;
  }
  
  protected boolean setIsEmpty(List set)
  {
    return (set == null) || (set.size() == 0);
  }
  
  protected boolean eitherIsBoolean(Object lhs, Object rhs)
  {
    return ((lhs instanceof Boolean)) || ((rhs instanceof Boolean));
  }
  
  protected boolean bothAreBoolean(Object lhs, Object rhs)
  {
    return ((lhs instanceof Boolean)) && ((rhs instanceof Boolean));
  }
  
  protected boolean eitherIsNumber(Object lhs, Object rhs)
  {
    return ((lhs instanceof Number)) || ((rhs instanceof Number));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultTruthExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */