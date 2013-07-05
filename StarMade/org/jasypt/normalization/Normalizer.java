/*     */ package org.jasypt.normalization;
/*     */ 
/*     */ import java.text.Normalizer.Form;
/*     */ import org.jasypt.exceptions.EncryptionInitializationException;
/*     */ 
/*     */ public final class Normalizer
/*     */ {
/*     */   private static final String ICU_NORMALIZER_CLASS_NAME = "com.ibm.icu.text.Normalizer";
/*     */   private static final String JDK_NORMALIZER_CLASS_NAME = "java.text.Normalizer";
/*  46 */   private static Boolean useIcuNormalizer = null;
/*     */ 
/*     */   public static String normalizeToNfc(String message)
/*     */   {
/*  64 */     return new String(normalizeToNfc(message.toCharArray()));
/*     */   }
/*     */ 
/*     */   public static char[] normalizeToNfc(char[] message)
/*     */   {
/*  84 */     if (useIcuNormalizer == null)
/*     */     {
/*     */       try
/*     */       {
/*  88 */         Thread.currentThread().getContextClassLoader().loadClass("com.ibm.icu.text.Normalizer");
/*  89 */         useIcuNormalizer = Boolean.TRUE;
/*     */       } catch (ClassNotFoundException e) {
/*     */         try {
/*  92 */           Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer");
/*     */         } catch (ClassNotFoundException e2) {
/*  94 */           throw new EncryptionInitializationException("Cannot find a valid UNICODE normalizer: neither java.text.Normalizer nor com.ibm.icu.text.Normalizer have been found at the classpath. If you are using a version of the JDK older than JavaSE 6, you should include the icu4j library in your classpath.");
/*     */         }
/*     */ 
/* 100 */         useIcuNormalizer = Boolean.FALSE;
/*     */       }
/*     */     }
/*     */ 
/* 104 */     if (useIcuNormalizer.booleanValue()) {
/* 105 */       return normalizeWithIcu4j(message);
/*     */     }
/*     */ 
/* 108 */     return normalizeWithJavaNormalizer(message);
/*     */   }
/*     */ 
/*     */   private static char[] normalizeWithJavaNormalizer(char[] message)
/*     */   {
/* 118 */     String messageStr = new String(message);
/* 119 */     String result = java.text.Normalizer.normalize(messageStr, Normalizer.Form.NFC);
/*     */ 
/* 121 */     return result.toCharArray();
/*     */   }
/*     */ 
/*     */   private static char[] normalizeWithIcu4j(char[] message)
/*     */   {
/* 129 */     char[] normalizationResult = new char[message.length * 2];
/* 130 */     int normalizationResultSize = 0;
/*     */     while (true)
/*     */     {
/* 137 */       normalizationResultSize = com.ibm.icu.text.Normalizer.normalize(message, normalizationResult, com.ibm.icu.text.Normalizer.NFC, 0);
/*     */ 
/* 139 */       if (normalizationResultSize <= normalizationResult.length)
/*     */       {
/* 142 */         char[] result = new char[normalizationResultSize];
/* 143 */         System.arraycopy(normalizationResult, 0, result, 0, normalizationResultSize);
/* 144 */         for (int i = 0; i < normalizationResult.length; i++) {
/* 145 */           normalizationResult[i] = '\000';
/*     */         }
/* 147 */         return result;
/*     */       }
/*     */ 
/* 150 */       for (int i = 0; i < normalizationResult.length; i++) {
/* 151 */         normalizationResult[i] = '\000';
/*     */       }
/* 153 */       normalizationResult = new char[normalizationResultSize];
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.normalization.Normalizer
 * JD-Core Version:    0.6.2
 */