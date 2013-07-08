package org.jaxen.expr;

import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.function.NumberFunction;

abstract class DefaultRelationalExpr
  extends DefaultTruthExpr
  implements RelationalExpr
{
  DefaultRelationalExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String toString()
  {
    return "[(DefaultRelationalExpr): " + getLHS() + ", " + getRHS() + "]";
  }
  
  public Object evaluate(Context context)
    throws JaxenException
  {
    Object lhsValue = getLHS().evaluate(context);
    Object rhsValue = getRHS().evaluate(context);
    Navigator nav = context.getNavigator();
    if (bothAreSets(lhsValue, rhsValue)) {
      return evaluateSetSet((List)lhsValue, (List)rhsValue, nav);
    }
    if (eitherIsSet(lhsValue, rhsValue))
    {
      if (isSet(lhsValue)) {
        return evaluateSetSet((List)lhsValue, convertToList(rhsValue), nav);
      }
      return evaluateSetSet(convertToList(lhsValue), (List)rhsValue, nav);
    }
    return evaluateObjectObject(lhsValue, rhsValue, nav) ? Boolean.TRUE : Boolean.FALSE;
  }
  
  private Object evaluateSetSet(List lhsSet, List rhsSet, Navigator nav)
  {
    if ((setIsEmpty(lhsSet)) || (setIsEmpty(rhsSet))) {
      return Boolean.FALSE;
    }
    Iterator lhsIterator = lhsSet.iterator();
    while (lhsIterator.hasNext())
    {
      Object lhs = lhsIterator.next();
      Iterator rhsIterator = rhsSet.iterator();
      while (rhsIterator.hasNext())
      {
        Object rhs = rhsIterator.next();
        if (evaluateObjectObject(lhs, rhs, nav)) {
          return Boolean.TRUE;
        }
      }
    }
    return Boolean.FALSE;
  }
  
  private boolean evaluateObjectObject(Object lhs, Object rhs, Navigator nav)
  {
    if ((lhs == null) || (rhs == null)) {
      return false;
    }
    Double lhsNum = NumberFunction.evaluate(lhs, nav);
    Double rhsNum = NumberFunction.evaluate(rhs, nav);
    if ((NumberFunction.isNaN(lhsNum)) || (NumberFunction.isNaN(rhsNum))) {
      return false;
    }
    return evaluateDoubleDouble(lhsNum, rhsNum);
  }
  
  protected abstract boolean evaluateDoubleDouble(Double paramDouble1, Double paramDouble2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultRelationalExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */