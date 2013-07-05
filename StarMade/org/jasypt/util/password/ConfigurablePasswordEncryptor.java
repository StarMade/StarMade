/*     */ package org.jasypt.util.password;
/*     */ 
/*     */ import java.security.Provider;
/*     */ import org.jasypt.digest.StandardStringDigester;
/*     */ import org.jasypt.digest.config.DigesterConfig;
/*     */ 
/*     */ public final class ConfigurablePasswordEncryptor
/*     */   implements PasswordEncryptor
/*     */ {
/*     */   private final StandardStringDigester digester;
/*     */ 
/*     */   public ConfigurablePasswordEncryptor()
/*     */   {
/*  76 */     this.digester = new StandardStringDigester();
/*     */   }
/*     */ 
/*     */   public void setConfig(DigesterConfig config)
/*     */   {
/*  89 */     this.digester.setConfig(config);
/*     */   }
/*     */ 
/*     */   public void setAlgorithm(String algorithm)
/*     */   {
/* 121 */     this.digester.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public void setProviderName(String providerName)
/*     */   {
/* 157 */     this.digester.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public void setProvider(Provider provider)
/*     */   {
/* 186 */     this.digester.setProvider(provider);
/*     */   }
/*     */ 
/*     */   public void setPlainDigest(boolean plainDigest)
/*     */   {
/* 201 */     if (plainDigest) {
/* 202 */       this.digester.setIterations(1);
/* 203 */       this.digester.setSaltSizeBytes(0);
/*     */     } else {
/* 205 */       this.digester.setIterations(1000);
/* 206 */       this.digester.setSaltSizeBytes(8);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setStringOutputType(String stringOutputType)
/*     */   {
/* 225 */     this.digester.setStringOutputType(stringOutputType);
/*     */   }
/*     */ 
/*     */   public String encryptPassword(String password)
/*     */   {
/* 237 */     return this.digester.digest(password);
/*     */   }
/*     */ 
/*     */   public boolean checkPassword(String plainPassword, String encryptedPassword)
/*     */   {
/* 252 */     return this.digester.matches(plainPassword, encryptedPassword);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.password.ConfigurablePasswordEncryptor
 * JD-Core Version:    0.6.2
 */