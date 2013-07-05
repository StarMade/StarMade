/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class NamespaceUriFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 110 */     if (args.size() == 0)
/*     */     {
/* 112 */       return evaluate(context.getNodeSet(), context.getNavigator());
/*     */     }
/*     */ 
/* 116 */     if (args.size() == 1)
/*     */     {
/* 118 */       return evaluate(args, context.getNavigator());
/*     */     }
/*     */ 
/* 122 */     throw new FunctionCallException("namespace-uri() requires zero or one argument.");
/*     */   }
/*     */ 
/*     */   public static String evaluate(List list, Navigator nav)
/*     */     throws FunctionCallException
/*     */   {
/* 138 */     if (!list.isEmpty())
/*     */     {
/* 140 */       Object first = list.get(0);
/*     */ 
/* 142 */       if ((first instanceof List))
/*     */       {
/* 144 */         return evaluate((List)first, nav);
/*     */       }
/*     */ 
/* 147 */       if (nav.isElement(first))
/*     */       {
/* 149 */         return nav.getElementNamespaceUri(first);
/*     */       }
/* 151 */       if (nav.isAttribute(first))
/*     */       {
/* 153 */         String uri = nav.getAttributeNamespaceUri(first);
/* 154 */         if (uri == null) return "";
/* 155 */         return uri;
/*     */       }
/* 157 */       if (nav.isProcessingInstruction(first))
/*     */       {
/* 159 */         return "";
/*     */       }
/* 161 */       if (nav.isNamespace(first))
/*     */       {
/* 163 */         return "";
/*     */       }
/* 165 */       if (nav.isDocument(first))
/*     */       {
/* 167 */         return "";
/*     */       }
/* 169 */       if (nav.isComment(first))
/*     */       {
/* 171 */         return "";
/*     */       }
/* 173 */       if (nav.isText(first))
/*     */       {
/* 175 */         return "";
/*     */       }
/*     */ 
/* 178 */       throw new FunctionCallException("The argument to the namespace-uri function must be a node-set");
/*     */     }
/*     */ 
/* 184 */     return "";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.NamespaceUriFunction
 * JD-Core Version:    0.6.2
 */