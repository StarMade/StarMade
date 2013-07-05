/*     */ package org.jasypt.util.digest;
/*     */ 
/*     */ import java.security.Provider;
/*     */ import org.jasypt.digest.StandardByteDigester;
/*     */ 
/*     */ public final class Digester
/*     */ {
/*     */   public static final String DEFAULT_ALGORITHM = "MD5";
/*     */   private static final int ITERATIONS = 1;
/*     */   private static final int SALT_SIZE_BYTES = 0;
/*     */   private final StandardByteDigester digester;
/*     */ 
/*     */   public Digester()
/*     */   {
/*  85 */     this.digester = new StandardByteDigester();
/*  86 */     this.digester.setIterations(1);
/*  87 */     this.digester.setSaltSizeBytes(0);
/*     */   }
/*     */ 
/*     */   public Digester(String algorithm)
/*     */   {
/*  98 */     this.digester = new StandardByteDigester();
/*  99 */     this.digester.setIterations(1);
/* 100 */     this.digester.setSaltSizeBytes(0);
/* 101 */     this.digester.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public Digester(String algorithm, String providerName)
/*     */   {
/* 115 */     this.digester = new StandardByteDigester();
/* 116 */     this.digester.setIterations(1);
/* 117 */     this.digester.setSaltSizeBytes(0);
/* 118 */     this.digester.setAlgorithm(algorithm);
/* 119 */     this.digester.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public Digester(String algorithm, Provider provider)
/*     */   {
/* 133 */     this.digester = new StandardByteDigester();
/* 134 */     this.digester.setIterations(1);
/* 135 */     this.digester.setSaltSizeBytes(0);
/* 136 */     this.digester.setAlgorithm(algorithm);
/* 137 */     this.digester.setProvider(provider);
/*     */   }
/*     */ 
/*     */   public void setAlgorithm(String algorithm)
/*     */   {
/* 171 */     this.digester.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public void setProviderName(String providerName)
/*     */   {
/* 206 */     this.digester.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public void setProvider(Provider provider)
/*     */   {
/* 234 */     this.digester.setProvider(provider);
/*     */   }
/*     */ 
/*     */   public byte[] digest(byte[] binary)
/*     */   {
/* 246 */     return this.digester.digest(binary);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.digest.Digester
 * JD-Core Version:    0.6.2
 */