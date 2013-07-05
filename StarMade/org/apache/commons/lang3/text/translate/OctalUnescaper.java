/*    */ package org.apache.commons.lang3.text.translate;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ 
/*    */ public class OctalUnescaper extends CharSequenceTranslator
/*    */ {
/* 36 */   private static int OCTAL_MAX = 377;
/*    */ 
/*    */   public int translate(CharSequence input, int index, Writer out)
/*    */     throws IOException
/*    */   {
/* 43 */     if ((input.charAt(index) == '\\') && (index < input.length() - 1) && (Character.isDigit(input.charAt(index + 1)))) {
/* 44 */       int start = index + 1;
/*    */ 
/* 46 */       int end = index + 2;
/* 47 */       while ((end < input.length()) && (Character.isDigit(input.charAt(end)))) {
/* 48 */         end++;
/* 49 */         if (Integer.parseInt(input.subSequence(start, end).toString(), 10) > OCTAL_MAX) {
/* 50 */           end--;
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/* 55 */       out.write(Integer.parseInt(input.subSequence(start, end).toString(), 8));
/* 56 */       return 1 + end - start;
/*    */     }
/* 58 */     return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.OctalUnescaper
 * JD-Core Version:    0.6.2
 */