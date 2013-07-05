/*     */ package org.jaxen.function;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Function;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class NormalizeSpaceFunction
/*     */   implements Function
/*     */ {
/*     */   public Object call(Context context, List args)
/*     */     throws FunctionCallException
/*     */   {
/* 107 */     if (args.size() == 0) {
/* 108 */       return evaluate(context.getNodeSet(), context.getNavigator());
/*     */     }
/*     */ 
/* 111 */     if (args.size() == 1)
/*     */     {
/* 113 */       return evaluate(args.get(0), context.getNavigator());
/*     */     }
/*     */ 
/* 117 */     throw new FunctionCallException("normalize-space() cannot have more than one argument");
/*     */   }
/*     */ 
/*     */   public static String evaluate(Object strArg, Navigator nav)
/*     */   {
/* 136 */     String str = StringFunction.evaluate(strArg, nav);
/*     */ 
/* 139 */     char[] buffer = str.toCharArray();
/* 140 */     int write = 0;
/* 141 */     int lastWrite = 0;
/* 142 */     boolean wroteOne = false;
/* 143 */     int read = 0;
/*     */     while (true) { if (read >= buffer.length)
/*     */         break label101;
/* 146 */       if (isXMLSpace(buffer[read]))
/*     */       {
/* 148 */         if (wroteOne)
/*     */         {
/* 150 */           buffer[(write++)] = ' ';
/*     */         }
/*     */ 
/* 154 */         read++;
/*     */ 
/* 156 */         if (read >= buffer.length) continue; if (isXMLSpace(buffer[read])) break; continue;
/*     */       }
/*     */ 
/* 160 */       buffer[(write++)] = buffer[(read++)];
/* 161 */       wroteOne = true;
/* 162 */       lastWrite = write;
/*     */     }
/*     */ 
/* 166 */     label101: return new String(buffer, 0, lastWrite);
/*     */   }
/*     */ 
/*     */   private static boolean isXMLSpace(char c)
/*     */   {
/* 171 */     return (c == ' ') || (c == '\n') || (c == '\r') || (c == '\t');
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.NormalizeSpaceFunction
 * JD-Core Version:    0.6.2
 */