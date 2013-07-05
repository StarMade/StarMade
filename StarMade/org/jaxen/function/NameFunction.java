/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class NameFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 101 */     if (args.size() == 0)
/*     */     {
/* 103 */       return evaluate(context.getNodeSet(), context.getNavigator());
/*     */     }
/*     */ 
/* 107 */     if (args.size() == 1)
/*     */     {
/* 109 */       return evaluate(args, context.getNavigator());
/*     */     }
/*     */ 
/* 113 */     throw new FunctionCallException("name() requires zero or one argument.");
/*     */   }
/*     */ 
/*     */   public static String evaluate(List list, Navigator nav)
/*     */     throws FunctionCallException
/*     */   {
/* 129 */     if (!list.isEmpty())
/*     */     {
/* 131 */       Object first = list.get(0);
/*     */ 
/* 133 */       if ((first instanceof List))
/*     */       {
/* 135 */         return evaluate((List)first, nav);
/*     */       }
/*     */ 
/* 138 */       if (nav.isElement(first))
/*     */       {
/* 140 */         return nav.getElementQName(first);
/*     */       }
/* 142 */       if (nav.isAttribute(first))
/*     */       {
/* 144 */         return nav.getAttributeQName(first);
/*     */       }
/* 146 */       if (nav.isProcessingInstruction(first))
/*     */       {
/* 148 */         return nav.getProcessingInstructionTarget(first);
/*     */       }
/* 150 */       if (nav.isNamespace(first))
/*     */       {
/* 152 */         return nav.getNamespacePrefix(first);
/*     */       }
/* 154 */       if (nav.isDocument(first))
/*     */       {
/* 156 */         return "";
/*     */       }
/* 158 */       if (nav.isComment(first))
/*     */       {
/* 160 */         return "";
/*     */       }
/* 162 */       if (nav.isText(first))
/*     */       {
/* 164 */         return "";
/*     */       }
/*     */ 
/* 167 */       throw new FunctionCallException("The argument to the name function must be a node-set");
/*     */     }
/*     */ 
/* 171 */     return "";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.NameFunction
 * JD-Core Version:    0.6.2
 */