/*    */ package org.apache.commons.lang3.text.translate;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ 
/*    */ public abstract class CodePointTranslator extends CharSequenceTranslator
/*    */ {
/*    */   public final int translate(CharSequence input, int index, Writer out)
/*    */     throws IOException
/*    */   {
/* 37 */     int codepoint = Character.codePointAt(input, index);
/* 38 */     boolean consumed = translate(codepoint, out);
/* 39 */     if (consumed) {
/* 40 */       return 1;
/*    */     }
/* 42 */     return 0;
/*    */   }
/*    */ 
/*    */   public abstract boolean translate(int paramInt, Writer paramWriter)
/*    */     throws IOException;
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.CodePointTranslator
 * JD-Core Version:    0.6.2
 */