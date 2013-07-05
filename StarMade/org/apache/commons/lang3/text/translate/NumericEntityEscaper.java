/*     */ package org.apache.commons.lang3.text.translate;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ 
/*     */ public class NumericEntityEscaper extends CodePointTranslator
/*     */ {
/*     */   private final int below;
/*     */   private final int above;
/*     */   private final boolean between;
/*     */ 
/*     */   private NumericEntityEscaper(int below, int above, boolean between)
/*     */   {
/*  45 */     this.below = below;
/*  46 */     this.above = above;
/*  47 */     this.between = between;
/*     */   }
/*     */ 
/*     */   public NumericEntityEscaper()
/*     */   {
/*  54 */     this(0, 2147483647, true);
/*     */   }
/*     */ 
/*     */   public static NumericEntityEscaper below(int codepoint)
/*     */   {
/*  64 */     return outsideOf(codepoint, 2147483647);
/*     */   }
/*     */ 
/*     */   public static NumericEntityEscaper above(int codepoint)
/*     */   {
/*  74 */     return outsideOf(0, codepoint);
/*     */   }
/*     */ 
/*     */   public static NumericEntityEscaper between(int codepointLow, int codepointHigh)
/*     */   {
/*  85 */     return new NumericEntityEscaper(codepointLow, codepointHigh, true);
/*     */   }
/*     */ 
/*     */   public static NumericEntityEscaper outsideOf(int codepointLow, int codepointHigh)
/*     */   {
/*  96 */     return new NumericEntityEscaper(codepointLow, codepointHigh, false);
/*     */   }
/*     */ 
/*     */   public boolean translate(int codepoint, Writer out)
/*     */     throws IOException
/*     */   {
/* 104 */     if (this.between) {
/* 105 */       if ((codepoint < this.below) || (codepoint > this.above)) {
/* 106 */         return false;
/*     */       }
/*     */     }
/* 109 */     else if ((codepoint >= this.below) && (codepoint <= this.above)) {
/* 110 */       return false;
/*     */     }
/*     */ 
/* 114 */     out.write("&#");
/* 115 */     out.write(Integer.toString(codepoint, 10));
/* 116 */     out.write(59);
/* 117 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.NumericEntityEscaper
 * JD-Core Version:    0.6.2
 */