/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.List;
/*   5:    */import org.jaxen.Context;
/*   6:    */import org.jaxen.JaxenException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */import org.jaxen.function.NumberFunction;
/*   9:    */
/*  57:    */abstract class DefaultRelationalExpr
/*  58:    */  extends DefaultTruthExpr
/*  59:    */  implements RelationalExpr
/*  60:    */{
/*  61:    */  DefaultRelationalExpr(Expr lhs, Expr rhs)
/*  62:    */  {
/*  63: 63 */    super(lhs, rhs);
/*  64:    */  }
/*  65:    */  
/*  66:    */  public String toString()
/*  67:    */  {
/*  68: 68 */    return "[(DefaultRelationalExpr): " + getLHS() + ", " + getRHS() + "]";
/*  69:    */  }
/*  70:    */  
/*  71:    */  public Object evaluate(Context context) throws JaxenException
/*  72:    */  {
/*  73: 73 */    Object lhsValue = getLHS().evaluate(context);
/*  74: 74 */    Object rhsValue = getRHS().evaluate(context);
/*  75: 75 */    Navigator nav = context.getNavigator();
/*  76:    */    
/*  77: 77 */    if (bothAreSets(lhsValue, rhsValue))
/*  78:    */    {
/*  79: 79 */      return evaluateSetSet((List)lhsValue, (List)rhsValue, nav);
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    if (eitherIsSet(lhsValue, rhsValue))
/*  83:    */    {
/*  84: 84 */      if (isSet(lhsValue))
/*  85:    */      {
/*  86: 86 */        return evaluateSetSet((List)lhsValue, convertToList(rhsValue), nav);
/*  87:    */      }
/*  88:    */      
/*  90: 90 */      return evaluateSetSet(convertToList(lhsValue), (List)rhsValue, nav);
/*  91:    */    }
/*  92:    */    
/*  94: 94 */    return evaluateObjectObject(lhsValue, rhsValue, nav) ? Boolean.TRUE : Boolean.FALSE;
/*  95:    */  }
/*  96:    */  
/*  97:    */  private Object evaluateSetSet(List lhsSet, List rhsSet, Navigator nav)
/*  98:    */  {
/*  99: 99 */    if ((setIsEmpty(lhsSet)) || (setIsEmpty(rhsSet)))
/* 100:    */    {
/* 101:101 */      return Boolean.FALSE;
/* 102:    */    }
/* 103:    */    
/* 104:104 */    for (Iterator lhsIterator = lhsSet.iterator(); lhsIterator.hasNext();)
/* 105:    */    {
/* 106:106 */      lhs = lhsIterator.next();
/* 107:    */      
/* 108:108 */      for (rhsIterator = rhsSet.iterator(); rhsIterator.hasNext();)
/* 109:    */      {
/* 110:110 */        Object rhs = rhsIterator.next();
/* 111:    */        
/* 112:112 */        if (evaluateObjectObject(lhs, rhs, nav))
/* 113:    */        {
/* 114:114 */          return Boolean.TRUE; }
/* 115:    */      }
/* 116:    */    }
/* 117:    */    Object lhs;
/* 118:    */    Iterator rhsIterator;
/* 119:119 */    return Boolean.FALSE;
/* 120:    */  }
/* 121:    */  
/* 122:    */  private boolean evaluateObjectObject(Object lhs, Object rhs, Navigator nav)
/* 123:    */  {
/* 124:124 */    if ((lhs == null) || (rhs == null))
/* 125:    */    {
/* 126:126 */      return false;
/* 127:    */    }
/* 128:    */    
/* 129:129 */    Double lhsNum = NumberFunction.evaluate(lhs, nav);
/* 130:130 */    Double rhsNum = NumberFunction.evaluate(rhs, nav);
/* 131:    */    
/* 132:132 */    if ((NumberFunction.isNaN(lhsNum)) || (NumberFunction.isNaN(rhsNum)))
/* 133:    */    {
/* 134:134 */      return false;
/* 135:    */    }
/* 136:    */    
/* 137:137 */    return evaluateDoubleDouble(lhsNum, rhsNum);
/* 138:    */  }
/* 139:    */  
/* 140:    */  protected abstract boolean evaluateDoubleDouble(Double paramDouble1, Double paramDouble2);
/* 141:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultRelationalExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */