/*    */ package org.apache.commons.lang3.text.translate;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ 
/*    */ public class UnicodeUnescaper extends CharSequenceTranslator
/*    */ {
/*    */   public int translate(CharSequence input, int index, Writer out)
/*    */     throws IOException
/*    */   {
/* 37 */     if ((input.charAt(index) == '\\') && (index + 1 < input.length()) && (input.charAt(index + 1) == 'u'))
/*    */     {
/* 39 */       int i = 2;
/* 40 */       while ((index + i < input.length()) && (input.charAt(index + i) == 'u')) {
/* 41 */         i++;
/*    */       }
/*    */ 
/* 44 */       if ((index + i < input.length()) && (input.charAt(index + i) == '+')) {
/* 45 */         i++;
/*    */       }
/*    */ 
/* 48 */       if (index + i + 4 <= input.length())
/*    */       {
/* 50 */         CharSequence unicode = input.subSequence(index + i, index + i + 4);
/*    */         try
/*    */         {
/* 53 */           int value = Integer.parseInt(unicode.toString(), 16);
/* 54 */           out.write((char)value);
/*    */         } catch (NumberFormatException nfe) {
/* 56 */           throw new IllegalArgumentException("Unable to parse unicode value: " + unicode, nfe);
/*    */         }
/* 58 */         return i + 4;
/*    */       }
/* 60 */       throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + input.subSequence(index, input.length()) + "' due to end of CharSequence");
/*    */     }
/*    */ 
/* 64 */     return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.UnicodeUnescaper
 * JD-Core Version:    0.6.2
 */