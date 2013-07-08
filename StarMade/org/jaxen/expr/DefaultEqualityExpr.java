/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.List;
/*   5:    */import org.jaxen.Context;
/*   6:    */import org.jaxen.JaxenException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */import org.jaxen.function.BooleanFunction;
/*   9:    */import org.jaxen.function.NumberFunction;
/*  10:    */import org.jaxen.function.StringFunction;
/*  11:    */
/*  57:    */abstract class DefaultEqualityExpr
/*  58:    */  extends DefaultTruthExpr
/*  59:    */  implements EqualityExpr
/*  60:    */{
/*  61:    */  DefaultEqualityExpr(Expr lhs, Expr rhs)
/*  62:    */  {
/*  63: 63 */    super(lhs, rhs);
/*  64:    */  }
/*  65:    */  
/*  66:    */  public String toString()
/*  67:    */  {
/*  68: 68 */    return "[(DefaultEqualityExpr): " + getLHS() + ", " + getRHS() + "]";
/*  69:    */  }
/*  70:    */  
/*  71:    */  public Object evaluate(Context context) throws JaxenException
/*  72:    */  {
/*  73: 73 */    Object lhsValue = getLHS().evaluate(context);
/*  74: 74 */    Object rhsValue = getRHS().evaluate(context);
/*  75:    */    
/*  76: 76 */    if ((lhsValue == null) || (rhsValue == null)) {
/*  77: 77 */      return Boolean.FALSE;
/*  78:    */    }
/*  79:    */    
/*  80: 80 */    Navigator nav = context.getNavigator();
/*  81:    */    
/*  82: 82 */    if (bothAreSets(lhsValue, rhsValue)) {
/*  83: 83 */      return evaluateSetSet((List)lhsValue, (List)rhsValue, nav);
/*  84:    */    }
/*  85: 85 */    if ((isSet(lhsValue)) && (isBoolean(rhsValue))) {
/*  86: 86 */      Boolean lhsBoolean = ((List)lhsValue).isEmpty() ? Boolean.FALSE : Boolean.TRUE;
/*  87: 87 */      Boolean rhsBoolean = (Boolean)rhsValue;
/*  88: 88 */      return Boolean.valueOf(evaluateObjectObject(lhsBoolean, rhsBoolean, nav));
/*  89:    */    }
/*  90: 90 */    if ((isBoolean(lhsValue)) && (isSet(rhsValue))) {
/*  91: 91 */      Boolean lhsBoolean = (Boolean)lhsValue;
/*  92: 92 */      Boolean rhsBoolean = ((List)rhsValue).isEmpty() ? Boolean.FALSE : Boolean.TRUE;
/*  93: 93 */      return Boolean.valueOf(evaluateObjectObject(lhsBoolean, rhsBoolean, nav));
/*  94:    */    }
/*  95: 95 */    if (eitherIsSet(lhsValue, rhsValue)) {
/*  96: 96 */      if (isSet(lhsValue)) {
/*  97: 97 */        return evaluateSetSet((List)lhsValue, convertToList(rhsValue), nav);
/*  98:    */      }
/*  99:    */      
/* 100:100 */      return evaluateSetSet(convertToList(lhsValue), (List)rhsValue, nav);
/* 101:    */    }
/* 102:    */    
/* 104:104 */    return Boolean.valueOf(evaluateObjectObject(lhsValue, rhsValue, nav));
/* 105:    */  }
/* 106:    */  
/* 112:    */  private Boolean evaluateSetSet(List lhsSet, List rhsSet, Navigator nav)
/* 113:    */  {
/* 114:114 */    if ((setIsEmpty(lhsSet)) || (setIsEmpty(rhsSet))) {
/* 115:115 */      return Boolean.FALSE;
/* 116:    */    }
/* 117:    */    
/* 118:118 */    for (Iterator lhsIterator = lhsSet.iterator(); lhsIterator.hasNext();)
/* 119:    */    {
/* 120:120 */      lhs = lhsIterator.next();
/* 121:    */      
/* 122:122 */      for (rhsIterator = rhsSet.iterator(); rhsIterator.hasNext();)
/* 123:    */      {
/* 124:124 */        Object rhs = rhsIterator.next();
/* 125:    */        
/* 126:126 */        if (evaluateObjectObject(lhs, rhs, nav))
/* 127:    */        {
/* 128:128 */          return Boolean.TRUE; }
/* 129:    */      }
/* 130:    */    }
/* 131:    */    Object lhs;
/* 132:    */    Iterator rhsIterator;
/* 133:133 */    return Boolean.FALSE;
/* 134:    */  }
/* 135:    */  
/* 136:    */  private boolean evaluateObjectObject(Object lhs, Object rhs, Navigator nav)
/* 137:    */  {
/* 138:138 */    if (eitherIsBoolean(lhs, rhs))
/* 139:    */    {
/* 140:140 */      return evaluateObjectObject(BooleanFunction.evaluate(lhs, nav), BooleanFunction.evaluate(rhs, nav));
/* 141:    */    }
/* 142:    */    
/* 143:143 */    if (eitherIsNumber(lhs, rhs))
/* 144:    */    {
/* 145:145 */      return evaluateObjectObject(NumberFunction.evaluate(lhs, nav), NumberFunction.evaluate(rhs, nav));
/* 146:    */    }
/* 147:    */    
/* 152:152 */    return evaluateObjectObject(StringFunction.evaluate(lhs, nav), StringFunction.evaluate(rhs, nav));
/* 153:    */  }
/* 154:    */  
/* 155:    */  protected abstract boolean evaluateObjectObject(Object paramObject1, Object paramObject2);
/* 156:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultEqualityExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */