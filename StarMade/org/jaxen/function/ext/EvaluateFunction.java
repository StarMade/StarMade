/*   1:    */package org.jaxen.function.ext;
/*   2:    */
/*   3:    */import java.util.Collections;
/*   4:    */import java.util.List;
/*   5:    */import org.jaxen.Context;
/*   6:    */import org.jaxen.ContextSupport;
/*   7:    */import org.jaxen.Function;
/*   8:    */import org.jaxen.FunctionCallException;
/*   9:    */import org.jaxen.Navigator;
/*  10:    */import org.jaxen.XPath;
/*  11:    */import org.jaxen.function.StringFunction;
/*  12:    */import org.jaxen.saxpath.SAXPathException;
/*  13:    */
/*  65:    */public class EvaluateFunction
/*  66:    */  implements Function
/*  67:    */{
/*  68:    */  public Object call(Context context, List args)
/*  69:    */    throws FunctionCallException
/*  70:    */  {
/*  71: 71 */    if (args.size() == 1) {
/*  72: 72 */      return evaluate(context, args.get(0));
/*  73:    */    }
/*  74:    */    
/*  75: 75 */    throw new FunctionCallException("evaluate() requires one argument");
/*  76:    */  }
/*  77:    */  
/*  78:    */  public static List evaluate(Context context, Object arg)
/*  79:    */    throws FunctionCallException
/*  80:    */  {
/*  81: 81 */    List contextNodes = context.getNodeSet();
/*  82:    */    
/*  83: 83 */    if (contextNodes.size() == 0) {
/*  84: 84 */      return Collections.EMPTY_LIST;
/*  85:    */    }
/*  86: 86 */    Navigator nav = context.getNavigator();
/*  87:    */    String xpathString;
/*  88:    */    String xpathString;
/*  89: 89 */    if ((arg instanceof String)) {
/*  90: 90 */      xpathString = (String)arg;
/*  91:    */    } else {
/*  92: 92 */      xpathString = StringFunction.evaluate(arg, nav);
/*  93:    */    }
/*  94:    */    try {
/*  95: 95 */      XPath xpath = nav.parseXPath(xpathString);
/*  96: 96 */      ContextSupport support = context.getContextSupport();
/*  97: 97 */      xpath.setVariableContext(support.getVariableContext());
/*  98: 98 */      xpath.setFunctionContext(support.getFunctionContext());
/*  99: 99 */      xpath.setNamespaceContext(support.getNamespaceContext());
/* 100:100 */      return xpath.selectNodes(context.duplicate());
/* 101:    */    }
/* 102:    */    catch (SAXPathException e) {
/* 103:103 */      throw new FunctionCallException(e.toString());
/* 104:    */    }
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ext.EvaluateFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */