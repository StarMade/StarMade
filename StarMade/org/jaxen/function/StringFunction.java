/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.JaxenRuntimeException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ 
/*     */ public class StringFunction
/*     */   implements Function
/*     */ {
/* 186 */   private static DecimalFormat format = (DecimalFormat)NumberFormat.getInstance(Locale.ENGLISH);
/*     */ 
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 217 */     int size = args.size();
/*     */ 
/* 219 */     if (size == 0)
/*     */     {
/* 221 */       return evaluate(context.getNodeSet(), context.getNavigator());
/*     */     }
/*     */ 
/* 224 */     if (size == 1)
/*     */     {
/* 226 */       return evaluate(args.get(0), context.getNavigator());
/*     */     }
/*     */ 
/* 230 */     throw new FunctionCallException("string() takes at most argument.");
/*     */   }
/*     */ 
/*     */   public static String evaluate(Object obj, Navigator nav)
/*     */   {
/*     */     try
/*     */     {
/* 253 */       if ((nav != null) && (nav.isText(obj)))
/*     */       {
/* 255 */         return nav.getTextStringValue(obj);
/*     */       }
/*     */ 
/* 258 */       if ((obj instanceof List))
/*     */       {
/* 260 */         List list = (List)obj;
/* 261 */         if (list.isEmpty())
/*     */         {
/* 263 */           return "";
/*     */         }
/*     */ 
/* 266 */         obj = list.get(0);
/*     */       }
/*     */ 
/* 269 */       if (nav != null)
/*     */       {
/* 272 */         if (nav.isElement(obj))
/*     */         {
/* 274 */           return nav.getElementStringValue(obj);
/*     */         }
/* 276 */         if (nav.isAttribute(obj))
/*     */         {
/* 278 */           return nav.getAttributeStringValue(obj);
/*     */         }
/*     */ 
/* 281 */         if (nav.isDocument(obj))
/*     */         {
/* 283 */           Iterator childAxisIterator = nav.getChildAxisIterator(obj);
/* 284 */           while (childAxisIterator.hasNext())
/*     */           {
/* 286 */             Object descendant = childAxisIterator.next();
/* 287 */             if (nav.isElement(descendant))
/*     */             {
/* 289 */               return nav.getElementStringValue(descendant);
/*     */             }
/*     */           }
/*     */         } else {
/* 293 */           if (nav.isProcessingInstruction(obj))
/*     */           {
/* 295 */             return nav.getProcessingInstructionData(obj);
/*     */           }
/* 297 */           if (nav.isComment(obj))
/*     */           {
/* 299 */             return nav.getCommentStringValue(obj);
/*     */           }
/* 301 */           if (nav.isText(obj))
/*     */           {
/* 303 */             return nav.getTextStringValue(obj);
/*     */           }
/* 305 */           if (nav.isNamespace(obj))
/*     */           {
/* 307 */             return nav.getNamespaceStringValue(obj);
/*     */           }
/*     */         }
/*     */       }
/* 311 */       if ((obj instanceof String))
/*     */       {
/* 313 */         return (String)obj;
/*     */       }
/* 315 */       if ((obj instanceof Boolean))
/*     */       {
/* 317 */         return stringValue(((Boolean)obj).booleanValue());
/*     */       }
/* 319 */       if ((obj instanceof Number))
/*     */       {
/* 321 */         return stringValue(((Number)obj).doubleValue());
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (UnsupportedAxisException e)
/*     */     {
/* 327 */       throw new JaxenRuntimeException(e);
/*     */     }
/*     */ 
/* 330 */     return "";
/*     */   }
/*     */ 
/*     */   private static String stringValue(double value)
/*     */   {
/* 339 */     if (value == 0.0D) return "0";
/*     */ 
/* 342 */     String result = null;
/* 343 */     synchronized (format) {
/* 344 */       result = format.format(value);
/*     */     }
/* 346 */     return result;
/*     */   }
/*     */ 
/*     */   private static String stringValue(boolean value)
/*     */   {
/* 352 */     return value ? "true" : "false";
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 189 */     DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
/* 190 */     symbols.setNaN("NaN");
/* 191 */     symbols.setInfinity("Infinity");
/* 192 */     format.setGroupingUsed(false);
/* 193 */     format.setMaximumFractionDigits(32);
/* 194 */     format.setDecimalFormatSymbols(symbols);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.StringFunction
 * JD-Core Version:    0.6.2
 */