/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class StringLengthFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 102 */     if (args.size() == 0)
/*     */     {
/* 104 */       return evaluate(context.getNodeSet(), context.getNavigator());
/*     */     }
/*     */ 
/* 107 */     if (args.size() == 1)
/*     */     {
/* 109 */       return evaluate(args.get(0), context.getNavigator());
/*     */     }
/*     */ 
/* 113 */     throw new FunctionCallException("string-length() requires one argument.");
/*     */   }
/*     */ 
/*     */   public static Double evaluate(Object obj, Navigator nav)
/*     */     throws FunctionCallException
/*     */   {
/* 131 */     String str = StringFunction.evaluate(obj, nav);
/*     */ 
/* 133 */     char[] data = str.toCharArray();
/* 134 */     int length = 0;
/* 135 */     for (int i = 0; i < data.length; i++) {
/* 136 */       char c = data[i];
/* 137 */       length++;
/*     */ 
/* 140 */       if ((c >= 55296) && (c <= 57343)) {
/*     */         try {
/* 142 */           char low = data[(i + 1)];
/* 143 */           if ((low < 56320) || (low > 57343)) {
/* 144 */             throw new FunctionCallException("Bad surrogate pair in string " + str);
/*     */           }
/* 146 */           i++;
/*     */         }
/*     */         catch (ArrayIndexOutOfBoundsException ex) {
/* 149 */           throw new FunctionCallException("Bad surrogate pair in string " + str);
/*     */         }
/*     */       }
/*     */     }
/* 153 */     return new Double(length);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.StringLengthFunction
 * JD-Core Version:    0.6.2
 */