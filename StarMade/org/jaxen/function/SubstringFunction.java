/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class SubstringFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 168 */     int argc = args.size();
/* 169 */     if ((argc < 2) || (argc > 3)) {
/* 170 */       throw new FunctionCallException("substring() requires two or three arguments.");
/*     */     }
/*     */ 
/* 173 */     Navigator nav = context.getNavigator();
/*     */ 
/* 175 */     String str = StringFunction.evaluate(args.get(0), nav);
/*     */ 
/* 177 */     if (str == null) {
/* 178 */       return "";
/*     */     }
/*     */ 
/* 181 */     int stringLength = StringLengthFunction.evaluate(args.get(0), nav).intValue();
/*     */ 
/* 183 */     if (stringLength == 0) {
/* 184 */       return "";
/*     */     }
/*     */ 
/* 187 */     Double d1 = NumberFunction.evaluate(args.get(1), nav);
/*     */ 
/* 189 */     if (d1.isNaN()) {
/* 190 */       return "";
/*     */     }
/*     */ 
/* 193 */     int start = RoundFunction.evaluate(d1, nav).intValue() - 1;
/*     */ 
/* 195 */     int substringLength = stringLength;
/* 196 */     if (argc == 3) {
/* 197 */       Double d2 = NumberFunction.evaluate(args.get(2), nav);
/*     */ 
/* 199 */       if (!d2.isNaN()) {
/* 200 */         substringLength = RoundFunction.evaluate(d2, nav).intValue();
/*     */       }
/*     */       else {
/* 203 */         substringLength = 0;
/*     */       }
/*     */     }
/*     */ 
/* 207 */     if (substringLength < 0) return "";
/*     */ 
/* 209 */     int end = start + substringLength;
/* 210 */     if (argc == 2) end = stringLength;
/*     */ 
/* 213 */     if (start < 0) {
/* 214 */       start = 0;
/*     */     }
/* 216 */     else if (start > stringLength) {
/* 217 */       return "";
/*     */     }
/*     */ 
/* 220 */     if (end > stringLength) {
/* 221 */       end = stringLength;
/*     */     }
/* 223 */     else if (end < start) return "";
/*     */ 
/* 225 */     if (stringLength == str.length())
/*     */     {
/* 227 */       return str.substring(start, end);
/*     */     }
/*     */ 
/* 230 */     return unicodeSubstring(str, start, end);
/*     */   }
/*     */ 
/*     */   private static String unicodeSubstring(String s, int start, int end)
/*     */   {
/* 237 */     StringBuffer result = new StringBuffer(s.length());
/* 238 */     int jChar = 0; for (int uChar = 0; uChar < end; uChar++) {
/* 239 */       char c = s.charAt(jChar);
/* 240 */       if (uChar >= start) result.append(c);
/* 241 */       if (c >= 55296)
/*     */       {
/* 244 */         jChar++;
/* 245 */         if (uChar >= start) result.append(s.charAt(jChar));
/*     */       }
/* 238 */       jChar++;
/*     */     }
/*     */ 
/* 248 */     return result.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.SubstringFunction
 * JD-Core Version:    0.6.2
 */