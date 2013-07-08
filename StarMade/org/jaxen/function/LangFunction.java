package org.jaxen.function;

import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class LangFunction
  implements Function
{
  private static final String LANG_LOCALNAME = "lang";
  private static final String XMLNS_URI = "http://www.w3.org/XML/1998/namespace";
  
  public Object call(Context context, List args)
    throws FunctionCallException
  {
    if (args.size() != 1) {
      throw new FunctionCallException("lang() requires exactly one argument.");
    }
    Object arg = args.get(0);
    try
    {
      return evaluate(context.getNodeSet(), arg, context.getNavigator());
    }
    catch (UnsupportedAxisException local_e)
    {
      throw new FunctionCallException("Can't evaluate lang()", local_e);
    }
  }
  
  private static Boolean evaluate(List contextNodes, Object lang, Navigator nav)
    throws UnsupportedAxisException
  {
    return evaluate(contextNodes.get(0), StringFunction.evaluate(lang, nav), nav) ? Boolean.TRUE : Boolean.FALSE;
  }
  
  private static boolean evaluate(Object node, String lang, Navigator nav)
    throws UnsupportedAxisException
  {
    Object element = node;
    if (!nav.isElement(element)) {}
    for (element = nav.getParentNode(node); (element != null) && (nav.isElement(element)); element = nav.getParentNode(element))
    {
      Iterator attrs = nav.getAttributeAxisIterator(element);
      while (attrs.hasNext())
      {
        Object attr = attrs.next();
        if (("lang".equals(nav.getAttributeName(attr))) && ("http://www.w3.org/XML/1998/namespace".equals(nav.getAttributeNamespaceUri(attr)))) {
          return isSublang(nav.getAttributeStringValue(attr), lang);
        }
      }
    }
    return false;
  }
  
  private static boolean isSublang(String sublang, String lang)
  {
    if (sublang.equalsIgnoreCase(lang)) {
      return true;
    }
    int local_ll = lang.length();
    return (sublang.length() > local_ll) && (sublang.charAt(local_ll) == '-') && (sublang.substring(0, local_ll).equalsIgnoreCase(lang));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.function.LangFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */