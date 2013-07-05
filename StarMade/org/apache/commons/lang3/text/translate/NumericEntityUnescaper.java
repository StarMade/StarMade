/*     */ package org.apache.commons.lang3.text.translate;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumSet;
/*     */ 
/*     */ public class NumericEntityUnescaper extends CharSequenceTranslator
/*     */ {
/*     */   private final EnumSet<OPTION> options;
/*     */ 
/*     */   public NumericEntityUnescaper(OPTION[] options)
/*     */   {
/*  57 */     if (options.length > 0)
/*  58 */       this.options = EnumSet.copyOf(Arrays.asList(options));
/*     */     else
/*  60 */       this.options = EnumSet.copyOf(Arrays.asList(new OPTION[] { OPTION.semiColonRequired }));
/*     */   }
/*     */ 
/*     */   public boolean isSet(OPTION option)
/*     */   {
/*  71 */     return this.options == null ? false : this.options.contains(option);
/*     */   }
/*     */ 
/*     */   public int translate(CharSequence input, int index, Writer out)
/*     */     throws IOException
/*     */   {
/*  79 */     int seqEnd = input.length();
/*     */ 
/*  81 */     if ((input.charAt(index) == '&') && (index < seqEnd - 2) && (input.charAt(index + 1) == '#')) {
/*  82 */       int start = index + 2;
/*  83 */       boolean isHex = false;
/*     */ 
/*  85 */       char firstChar = input.charAt(start);
/*  86 */       if ((firstChar == 'x') || (firstChar == 'X')) {
/*  87 */         start++;
/*  88 */         isHex = true;
/*     */ 
/*  91 */         if (start == seqEnd) {
/*  92 */           return 0;
/*     */         }
/*     */       }
/*     */ 
/*  96 */       int end = start;
/*     */ 
/*  98 */       while ((end < seqEnd) && (((input.charAt(end) >= '0') && (input.charAt(end) <= '9')) || ((input.charAt(end) >= 'a') && (input.charAt(end) <= 'f')) || ((input.charAt(end) >= 'A') && (input.charAt(end) <= 'F'))))
/*     */       {
/* 102 */         end++;
/*     */       }
/*     */ 
/* 105 */       boolean semiNext = (end != seqEnd) && (input.charAt(end) == ';');
/*     */ 
/* 107 */       if (!semiNext) {
/* 108 */         if (isSet(OPTION.semiColonRequired)) {
/* 109 */           return 0;
/*     */         }
/* 111 */         if (isSet(OPTION.errorIfNoSemiColon))
/* 112 */           throw new IllegalArgumentException("Semi-colon required at end of numeric entity");
/*     */       }
/*     */       int entityValue;
/*     */       try
/*     */       {
/*     */         int entityValue;
/* 118 */         if (isHex)
/* 119 */           entityValue = Integer.parseInt(input.subSequence(start, end).toString(), 16);
/*     */         else
/* 121 */           entityValue = Integer.parseInt(input.subSequence(start, end).toString(), 10);
/*     */       }
/*     */       catch (NumberFormatException nfe) {
/* 124 */         return 0;
/*     */       }
/*     */ 
/* 127 */       if (entityValue > 65535) {
/* 128 */         char[] chrs = Character.toChars(entityValue);
/* 129 */         out.write(chrs[0]);
/* 130 */         out.write(chrs[1]);
/*     */       } else {
/* 132 */         out.write(entityValue);
/*     */       }
/*     */ 
/* 135 */       return 2 + end - start + (isHex ? 1 : 0) + (semiNext ? 1 : 0);
/*     */     }
/* 137 */     return 0;
/*     */   }
/*     */ 
/*     */   public static enum OPTION
/*     */   {
/*  35 */     semiColonRequired, semiColonOptional, errorIfNoSemiColon;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.NumericEntityUnescaper
 * JD-Core Version:    0.6.2
 */