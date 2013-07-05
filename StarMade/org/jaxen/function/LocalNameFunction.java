/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class LocalNameFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/*  96 */     if (args.size() == 0)
/*     */     {
/*  98 */       return evaluate(context.getNodeSet(), context.getNavigator());
/*     */     }
/*     */ 
/* 102 */     if (args.size() == 1)
/*     */     {
/* 104 */       return evaluate(args, context.getNavigator());
/*     */     }
/*     */ 
/* 108 */     throw new FunctionCallException("local-name() requires zero or one argument.");
/*     */   }
/*     */ 
/*     */   public static String evaluate(List list, Navigator nav)
/*     */     throws FunctionCallException
/*     */   {
/* 124 */     if (!list.isEmpty())
/*     */     {
/* 126 */       Object first = list.get(0);
/*     */ 
/* 128 */       if ((first instanceof List))
/*     */       {
/* 130 */         return evaluate((List)first, nav);
/*     */       }
/*     */ 
/* 133 */       if (nav.isElement(first))
/*     */       {
/* 135 */         return nav.getElementName(first);
/*     */       }
/* 137 */       if (nav.isAttribute(first))
/*     */       {
/* 139 */         return nav.getAttributeName(first);
/*     */       }
/* 141 */       if (nav.isProcessingInstruction(first))
/*     */       {
/* 143 */         return nav.getProcessingInstructionTarget(first);
/*     */       }
/* 145 */       if (nav.isNamespace(first))
/*     */       {
/* 147 */         return nav.getNamespacePrefix(first);
/*     */       }
/* 149 */       if (nav.isDocument(first))
/*     */       {
/* 151 */         return "";
/*     */       }
/* 153 */       if (nav.isComment(first))
/*     */       {
/* 155 */         return "";
/*     */       }
/* 157 */       if (nav.isText(first))
/*     */       {
/* 159 */         return "";
/*     */       }
/*     */ 
/* 162 */       throw new FunctionCallException("The argument to the local-name function must be a node-set");
/*     */     }
/*     */ 
/* 166 */     return "";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.LocalNameFunction
 * JD-Core Version:    0.6.2
 */