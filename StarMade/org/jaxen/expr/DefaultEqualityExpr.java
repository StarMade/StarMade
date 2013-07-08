package org.jaxen.expr;

import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.function.BooleanFunction;
import org.jaxen.function.NumberFunction;
import org.jaxen.function.StringFunction;

abstract class DefaultEqualityExpr
  extends DefaultTruthExpr
  implements EqualityExpr
{
  DefaultEqualityExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String toString()
  {
    return "[(DefaultEqualityExpr): " + getLHS() + ", " + getRHS() + "]";
  }
  
  public Object evaluate(Context context)
    throws JaxenException
  {
    Object lhsValue = getLHS().evaluate(context);
    Object rhsValue = getRHS().evaluate(context);
    if ((lhsValue == null) || (rhsValue == null)) {
      return Boolean.FALSE;
    }
    Navigator nav = context.getNavigator();
    if (bothAreSets(lhsValue, rhsValue)) {
      return evaluateSetSet((List)lhsValue, (List)rhsValue, nav);
    }
    if ((isSet(lhsValue)) && (isBoolean(rhsValue)))
    {
      Boolean lhsBoolean = ((List)lhsValue).isEmpty() ? Boolean.FALSE : Boolean.TRUE;
      Boolean rhsBoolean = (Boolean)rhsValue;
      return Boolean.valueOf(evaluateObjectObject(lhsBoolean, rhsBoolean, nav));
    }
    if ((isBoolean(lhsValue)) && (isSet(rhsValue)))
    {
      Boolean lhsBoolean = (Boolean)lhsValue;
      Boolean rhsBoolean = ((List)rhsValue).isEmpty() ? Boolean.FALSE : Boolean.TRUE;
      return Boolean.valueOf(evaluateObjectObject(lhsBoolean, rhsBoolean, nav));
    }
    if (eitherIsSet(lhsValue, rhsValue))
    {
      if (isSet(lhsValue)) {
        return evaluateSetSet((List)lhsValue, convertToList(rhsValue), nav);
      }
      return evaluateSetSet(convertToList(lhsValue), (List)rhsValue, nav);
    }
    return Boolean.valueOf(evaluateObjectObject(lhsValue, rhsValue, nav));
  }
  
  private Boolean evaluateSetSet(List lhsSet, List rhsSet, Navigator nav)
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
    if (eitherIsBoolean(lhs, rhs)) {
      return evaluateObjectObject(BooleanFunction.evaluate(lhs, nav), BooleanFunction.evaluate(rhs, nav));
    }
    if (eitherIsNumber(lhs, rhs)) {
      return evaluateObjectObject(NumberFunction.evaluate(lhs, nav), NumberFunction.evaluate(rhs, nav));
    }
    return evaluateObjectObject(StringFunction.evaluate(lhs, nav), StringFunction.evaluate(rhs, nav));
  }
  
  protected abstract boolean evaluateObjectObject(Object paramObject1, Object paramObject2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultEqualityExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */