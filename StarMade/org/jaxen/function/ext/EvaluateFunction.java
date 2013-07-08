package org.jaxen.function.ext;

import java.util.Collections;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;
import org.jaxen.XPath;
import org.jaxen.function.StringFunction;
import org.jaxen.saxpath.SAXPathException;

public class EvaluateFunction
  implements Function
{
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() == 1) {
      return evaluate(context, args.get(0));
    }
    throw new FunctionCallException("evaluate() requires one argument");
  }
  
  public static List evaluate(Context context, Object arg)
    throws FunctionCallException
  {
    List contextNodes = context.getNodeSet();
    if (contextNodes.size() == 0) {
      return Collections.EMPTY_LIST;
    }
    Navigator nav = context.getNavigator();
    String xpathString;
    String xpathString;
    if ((arg instanceof String)) {
      xpathString = (String)arg;
    } else {
      xpathString = StringFunction.evaluate(arg, nav);
    }
    try
    {
      XPath xpath = nav.parseXPath(xpathString);
      ContextSupport support = context.getContextSupport();
      xpath.setVariableContext(support.getVariableContext());
      xpath.setFunctionContext(support.getFunctionContext());
      xpath.setNamespaceContext(support.getNamespaceContext());
      return xpath.selectNodes(context.duplicate());
    }
    catch (SAXPathException xpath)
    {
      throw new FunctionCallException(xpath.toString());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.ext.EvaluateFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */