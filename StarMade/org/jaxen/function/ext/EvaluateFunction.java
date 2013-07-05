/*    */ package org.jaxen.function.ext;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.jaxen.Context;
/*    */ import org.jaxen.ContextSupport;
/*    */ import org.jaxen.Function;
/*    */ import org.jaxen.FunctionCallException;
/*    */ import org.jaxen.Navigator;
/*    */ import org.jaxen.XPath;
/*    */ import org.jaxen.function.StringFunction;
/*    */ import org.jaxen.saxpath.SAXPathException;
/*    */ 
/*    */ public class EvaluateFunction
/*    */   implements Function
/*    */ {
/*    */   public Object call(Context context, List args)
/*    */     throws FunctionCallException
/*    */   {
/* 71 */     if (args.size() == 1) {
/* 72 */       return evaluate(context, args.get(0));
/*    */     }
/*    */ 
/* 75 */     throw new FunctionCallException("evaluate() requires one argument");
/*    */   }
/*    */ 
/*    */   public static List evaluate(Context context, Object arg)
/*    */     throws FunctionCallException
/*    */   {
/* 81 */     List contextNodes = context.getNodeSet();
/*    */ 
/* 83 */     if (contextNodes.size() == 0) {
/* 84 */       return Collections.EMPTY_LIST;
/*    */     }
/* 86 */     Navigator nav = context.getNavigator();
/*    */     String xpathString;
/*    */     String xpathString;
/* 89 */     if ((arg instanceof String))
/* 90 */       xpathString = (String)arg;
/*    */     else
/* 92 */       xpathString = StringFunction.evaluate(arg, nav);
/*    */     try
/*    */     {
/* 95 */       XPath xpath = nav.parseXPath(xpathString);
/* 96 */       ContextSupport support = context.getContextSupport();
/* 97 */       xpath.setVariableContext(support.getVariableContext());
/* 98 */       xpath.setFunctionContext(support.getFunctionContext());
/* 99 */       xpath.setNamespaceContext(support.getNamespaceContext());
/* 100 */       return xpath.selectNodes(context.duplicate());
/*    */     }
/*    */     catch (SAXPathException e) {
/* 103 */       throw new FunctionCallException(e.toString());
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ext.EvaluateFunction
 * JD-Core Version:    0.6.2
 */