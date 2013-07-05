/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class IdFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 107 */     if (args.size() == 1) {
/* 108 */       return evaluate(context.getNodeSet(), args.get(0), context.getNavigator());
/*     */     }
/*     */ 
/* 112 */     throw new FunctionCallException("id() requires one argument");
/*     */   }
/*     */ 
/*     */   public static List evaluate(List contextNodes, Object arg, Navigator nav)
/*     */   {
/* 131 */     if (contextNodes.size() == 0) return Collections.EMPTY_LIST;
/*     */ 
/* 133 */     List nodes = new ArrayList();
/*     */ 
/* 135 */     Object contextNode = contextNodes.get(0);
/*     */ 
/* 137 */     if ((arg instanceof List)) {
/* 138 */       Iterator iter = ((List)arg).iterator();
/* 139 */       while (iter.hasNext()) {
/* 140 */         String id = StringFunction.evaluate(iter.next(), nav);
/* 141 */         nodes.addAll(evaluate(contextNodes, id, nav));
/*     */       }
/*     */     }
/*     */     else {
/* 145 */       String ids = StringFunction.evaluate(arg, nav);
/* 146 */       StringTokenizer tok = new StringTokenizer(ids, " \t\n\r");
/* 147 */       while (tok.hasMoreTokens()) {
/* 148 */         String id = tok.nextToken();
/* 149 */         Object node = nav.getElementById(contextNode, id);
/* 150 */         if (node != null) {
/* 151 */           nodes.add(node);
/*     */         }
/*     */       }
/*     */     }
/* 155 */     return nodes;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.IdFunction
 * JD-Core Version:    0.6.2
 */