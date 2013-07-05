/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.IllegalCharsetNameException;
/*     */ 
/*     */ public class CharEncoding
/*     */ {
/*     */   public static final String ISO_8859_1 = "ISO-8859-1";
/*     */   public static final String US_ASCII = "US-ASCII";
/*     */   public static final String UTF_16 = "UTF-16";
/*     */   public static final String UTF_16BE = "UTF-16BE";
/*     */   public static final String UTF_16LE = "UTF-16LE";
/*     */   public static final String UTF_8 = "UTF-8";
/*     */ 
/*     */   public static boolean isSupported(String name)
/*     */   {
/*  95 */     if (name == null)
/*  96 */       return false;
/*     */     try
/*     */     {
/*  99 */       return Charset.isSupported(name); } catch (IllegalCharsetNameException ex) {
/*     */     }
/* 101 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharEncoding
 * JD-Core Version:    0.6.2
 */