/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Collections;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import java.util.StringTokenizer;
/*   8:    */import org.jaxen.Context;
/*   9:    */import org.jaxen.Function;
/*  10:    */import org.jaxen.FunctionCallException;
/*  11:    */import org.jaxen.Navigator;
/*  12:    */
/* 101:    */public class IdFunction
/* 102:    */  implements Function
/* 103:    */{
/* 104:    */  public Object call(Context context, List args)
/* 105:    */    throws FunctionCallException
/* 106:    */  {
/* 107:107 */    if (args.size() == 1) {
/* 108:108 */      return evaluate(context.getNodeSet(), args.get(0), context.getNavigator());
/* 109:    */    }
/* 110:    */    
/* 112:112 */    throw new FunctionCallException("id() requires one argument");
/* 113:    */  }
/* 114:    */  
/* 129:    */  public static List evaluate(List contextNodes, Object arg, Navigator nav)
/* 130:    */  {
/* 131:131 */    if (contextNodes.size() == 0) { return Collections.EMPTY_LIST;
/* 132:    */    }
/* 133:133 */    List nodes = new ArrayList();
/* 134:    */    
/* 135:135 */    Object contextNode = contextNodes.get(0);
/* 136:    */    
/* 137:137 */    if ((arg instanceof List)) {
/* 138:138 */      Iterator iter = ((List)arg).iterator();
/* 139:139 */      while (iter.hasNext()) {
/* 140:140 */        String id = StringFunction.evaluate(iter.next(), nav);
/* 141:141 */        nodes.addAll(evaluate(contextNodes, id, nav));
/* 142:    */      }
/* 143:    */    }
/* 144:    */    else {
/* 145:145 */      String ids = StringFunction.evaluate(arg, nav);
/* 146:146 */      StringTokenizer tok = new StringTokenizer(ids, " \t\n\r");
/* 147:147 */      while (tok.hasMoreTokens()) {
/* 148:148 */        String id = tok.nextToken();
/* 149:149 */        Object node = nav.getElementById(contextNode, id);
/* 150:150 */        if (node != null) {
/* 151:151 */          nodes.add(node);
/* 152:    */        }
/* 153:    */      }
/* 154:    */    }
/* 155:155 */    return nodes;
/* 156:    */  }
/* 157:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.IdFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */