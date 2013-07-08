/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.List;
/*   5:    */import org.jaxen.Context;
/*   6:    */import org.jaxen.Function;
/*   7:    */import org.jaxen.FunctionCallException;
/*   8:    */import org.jaxen.Navigator;
/*   9:    */
/*  87:    */public class SumFunction
/*  88:    */  implements Function
/*  89:    */{
/*  90:    */  public Object call(Context context, List args)
/*  91:    */    throws FunctionCallException
/*  92:    */  {
/*  93: 93 */    if (args.size() == 1)
/*  94:    */    {
/*  95: 95 */      return evaluate(args.get(0), context.getNavigator());
/*  96:    */    }
/*  97:    */    
/*  99: 99 */    throw new FunctionCallException("sum() requires one argument.");
/* 100:    */  }
/* 101:    */  
/* 114:    */  public static Double evaluate(Object obj, Navigator nav)
/* 115:    */    throws FunctionCallException
/* 116:    */  {
/* 117:117 */    double sum = 0.0D;
/* 118:    */    
/* 119:119 */    if ((obj instanceof List))
/* 120:    */    {
/* 121:121 */      Iterator nodeIter = ((List)obj).iterator();
/* 122:122 */      while (nodeIter.hasNext())
/* 123:    */      {
/* 124:124 */        double term = NumberFunction.evaluate(nodeIter.next(), nav).doubleValue();
/* 125:    */        
/* 126:126 */        sum += term;
/* 127:    */      }
/* 128:    */    }
/* 129:    */    else
/* 130:    */    {
/* 131:131 */      throw new FunctionCallException("The argument to the sum function must be a node-set");
/* 132:    */    }
/* 133:    */    
/* 134:134 */    return new Double(sum);
/* 135:    */  }
/* 136:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.SumFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */