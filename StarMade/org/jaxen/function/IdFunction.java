package org.jaxen.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class IdFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() == 1) {
      return evaluate(context.getNodeSet(), args.get(0), context.getNavigator());
    }
    throw new FunctionCallException("id() requires one argument");
  }
  
  public static List evaluate(List contextNodes, Object arg, Navigator nav)
  {
    if (contextNodes.size() == 0) {
      return Collections.EMPTY_LIST;
    }
    List nodes = new ArrayList();
    Object contextNode = contextNodes.get(0);
    if ((arg instanceof List))
    {
      Iterator iter = ((List)arg).iterator();
      while (iter.hasNext())
      {
        String local_id = StringFunction.evaluate(iter.next(), nav);
        nodes.addAll(evaluate(contextNodes, local_id, nav));
      }
    }
    else
    {
      String iter = StringFunction.evaluate(arg, nav);
      StringTokenizer local_id = new StringTokenizer(iter, " \t\n\r");
      while (local_id.hasMoreTokens())
      {
        String local_id1 = local_id.nextToken();
        Object node = nav.getElementById(contextNode, local_id1);
        if (node != null) {
          nodes.add(node);
        }
      }
    }
    return nodes;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.IdFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */