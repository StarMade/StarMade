/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class TranslateFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 127 */     if (args.size() == 3) {
/* 128 */       return evaluate(args.get(0), args.get(1), args.get(2), context.getNavigator());
/*     */     }
/*     */ 
/* 134 */     throw new FunctionCallException("translate() requires three arguments.");
/*     */   }
/*     */ 
/*     */   public static String evaluate(Object strArg, Object fromArg, Object toArg, Navigator nav)
/*     */     throws FunctionCallException
/*     */   {
/* 162 */     String inStr = StringFunction.evaluate(strArg, nav);
/* 163 */     String fromStr = StringFunction.evaluate(fromArg, nav);
/* 164 */     String toStr = StringFunction.evaluate(toArg, nav);
/*     */ 
/* 167 */     Map characterMap = new HashMap();
/* 168 */     String[] fromCharacters = toUnicodeCharacters(fromStr);
/* 169 */     String[] toCharacters = toUnicodeCharacters(toStr);
/* 170 */     int fromLen = fromCharacters.length;
/* 171 */     int toLen = toCharacters.length;
/* 172 */     for (int i = 0; i < fromLen; i++) {
/* 173 */       String cFrom = fromCharacters[i];
/* 174 */       if (!characterMap.containsKey(cFrom))
/*     */       {
/* 179 */         if (i < toLen)
/*     */         {
/* 181 */           characterMap.put(cFrom, toCharacters[i]);
/*     */         }
/*     */         else
/*     */         {
/* 185 */           characterMap.put(cFrom, null);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 190 */     StringBuffer outStr = new StringBuffer(inStr.length());
/* 191 */     String[] inCharacters = toUnicodeCharacters(inStr);
/* 192 */     int inLen = inCharacters.length;
/* 193 */     for (int i = 0; i < inLen; i++) {
/* 194 */       String cIn = inCharacters[i];
/* 195 */       if (characterMap.containsKey(cIn)) {
/* 196 */         String cTo = (String)characterMap.get(cIn);
/* 197 */         if (cTo != null)
/* 198 */           outStr.append(cTo);
/*     */       }
/*     */       else
/*     */       {
/* 202 */         outStr.append(cIn);
/*     */       }
/*     */     }
/*     */ 
/* 206 */     return outStr.toString();
/*     */   }
/*     */ 
/*     */   private static String[] toUnicodeCharacters(String s) throws FunctionCallException
/*     */   {
/* 211 */     String[] result = new String[s.length()];
/* 212 */     int stringLength = 0;
/* 213 */     for (int i = 0; i < s.length(); i++) {
/* 214 */       char c1 = s.charAt(i);
/* 215 */       if (isHighSurrogate(c1)) {
/*     */         try {
/* 217 */           char c2 = s.charAt(i + 1);
/* 218 */           if (isLowSurrogate(c2)) {
/* 219 */             result[stringLength] = (c1 + "" + c2).intern();
/* 220 */             i++;
/*     */           }
/*     */           else {
/* 223 */             throw new FunctionCallException("Mismatched surrogate pair in translate function");
/*     */           }
/*     */         }
/*     */         catch (StringIndexOutOfBoundsException ex) {
/* 227 */           throw new FunctionCallException("High surrogate without low surrogate at end of string passed to translate function");
/*     */         }
/*     */       }
/*     */       else {
/* 231 */         result[stringLength] = String.valueOf(c1).intern();
/*     */       }
/* 233 */       stringLength++;
/*     */     }
/*     */ 
/* 236 */     if (stringLength == result.length) return result;
/*     */ 
/* 239 */     String[] trimmed = new String[stringLength];
/* 240 */     System.arraycopy(result, 0, trimmed, 0, stringLength);
/* 241 */     return trimmed;
/*     */   }
/*     */ 
/*     */   private static boolean isHighSurrogate(char c)
/*     */   {
/* 246 */     return (c >= 55296) && (c <= 56319);
/*     */   }
/*     */ 
/*     */   private static boolean isLowSurrogate(char c) {
/* 250 */     return (c >= 56320) && (c <= 57343);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.TranslateFunction
 * JD-Core Version:    0.6.2
 */