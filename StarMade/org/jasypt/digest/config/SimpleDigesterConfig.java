/*     */ package org.jasypt.digest.config;
/*     */ 
/*     */ import java.security.Provider;
/*     */ import org.jasypt.exceptions.EncryptionInitializationException;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public class SimpleDigesterConfig
/*     */   implements DigesterConfig
/*     */ {
/*  55 */   private String algorithm = null;
/*  56 */   private Integer iterations = null;
/*  57 */   private Integer saltSizeBytes = null;
/*  58 */   private SaltGenerator saltGenerator = null;
/*  59 */   private String providerName = null;
/*  60 */   private Provider provider = null;
/*  61 */   private Boolean invertPositionOfSaltInMessageBeforeDigesting = null;
/*  62 */   private Boolean invertPositionOfPlainSaltInEncryptionResults = null;
/*  63 */   private Boolean useLenientSaltSizeCheck = null;
/*  64 */   private Integer poolSize = null;
/*     */ 
/*     */   public void setAlgorithm(String algorithm)
/*     */   {
/* 105 */     this.algorithm = algorithm;
/*     */   }
/*     */ 
/*     */   public void setIterations(Integer iterations)
/*     */   {
/* 123 */     this.iterations = iterations;
/*     */   }
/*     */ 
/*     */   public void setIterations(String iterations)
/*     */   {
/* 143 */     if (iterations != null)
/*     */       try {
/* 145 */         this.iterations = new Integer(iterations);
/*     */       } catch (NumberFormatException e) {
/* 147 */         throw new EncryptionInitializationException(e);
/*     */       }
/*     */     else
/* 150 */       this.iterations = null;
/*     */   }
/*     */ 
/*     */   public void setSaltSizeBytes(Integer saltSizeBytes)
/*     */   {
/* 169 */     this.saltSizeBytes = saltSizeBytes;
/*     */   }
/*     */ 
/*     */   public void setSaltSizeBytes(String saltSizeBytes)
/*     */   {
/* 189 */     if (saltSizeBytes != null)
/*     */       try {
/* 191 */         this.saltSizeBytes = new Integer(saltSizeBytes);
/*     */       } catch (NumberFormatException e) {
/* 193 */         throw new EncryptionInitializationException(e);
/*     */       }
/*     */     else
/* 196 */       this.saltSizeBytes = null;
/*     */   }
/*     */ 
/*     */   public void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 217 */     this.saltGenerator = saltGenerator;
/*     */   }
/*     */ 
/*     */   public void setSaltGeneratorClassName(String saltGeneratorClassName)
/*     */   {
/* 237 */     if (saltGeneratorClassName != null)
/*     */       try {
/* 239 */         Class saltGeneratorClass = Thread.currentThread().getContextClassLoader().loadClass(saltGeneratorClassName);
/*     */ 
/* 241 */         this.saltGenerator = ((SaltGenerator)saltGeneratorClass.newInstance());
/*     */       }
/*     */       catch (Exception e) {
/* 244 */         throw new EncryptionInitializationException(e);
/*     */       }
/*     */     else
/* 247 */       this.saltGenerator = null;
/*     */   }
/*     */ 
/*     */   public void setProviderName(String providerName)
/*     */   {
/* 274 */     this.providerName = providerName;
/*     */   }
/*     */ 
/*     */   public void setProvider(Provider provider)
/*     */   {
/* 306 */     this.provider = provider;
/*     */   }
/*     */ 
/*     */   public void setProviderClassName(String providerClassName)
/*     */   {
/* 337 */     if (providerClassName != null)
/*     */       try {
/* 339 */         Class providerClass = Thread.currentThread().getContextClassLoader().loadClass(providerClassName);
/*     */ 
/* 341 */         this.provider = ((Provider)providerClass.newInstance());
/*     */       } catch (Exception e) {
/* 343 */         throw new EncryptionInitializationException(e);
/*     */       }
/*     */     else
/* 346 */       this.provider = null;
/*     */   }
/*     */ 
/*     */   public void setInvertPositionOfSaltInMessageBeforeDigesting(Boolean invertPositionOfSaltInMessageBeforeDigesting)
/*     */   {
/* 375 */     this.invertPositionOfSaltInMessageBeforeDigesting = invertPositionOfSaltInMessageBeforeDigesting;
/*     */   }
/*     */ 
/*     */   public void setInvertPositionOfPlainSaltInEncryptionResults(Boolean invertPositionOfPlainSaltInEncryptionResults)
/*     */   {
/* 403 */     this.invertPositionOfPlainSaltInEncryptionResults = invertPositionOfPlainSaltInEncryptionResults;
/*     */   }
/*     */ 
/*     */   public void setUseLenientSaltSizeCheck(Boolean useLenientSaltSizeCheck)
/*     */   {
/* 448 */     this.useLenientSaltSizeCheck = useLenientSaltSizeCheck;
/*     */   }
/*     */ 
/*     */   public void setPoolSize(Integer poolSize)
/*     */   {
/* 472 */     this.poolSize = poolSize;
/*     */   }
/*     */ 
/*     */   public void setPoolSize(String poolSize)
/*     */   {
/* 496 */     if (poolSize != null)
/*     */       try {
/* 498 */         this.poolSize = new Integer(poolSize);
/*     */       } catch (NumberFormatException e) {
/* 500 */         throw new EncryptionInitializationException(e);
/*     */       }
/*     */     else
/* 503 */       this.poolSize = null;
/*     */   }
/*     */ 
/*     */   public String getAlgorithm()
/*     */   {
/* 510 */     return this.algorithm;
/*     */   }
/*     */ 
/*     */   public Integer getIterations()
/*     */   {
/* 515 */     return this.iterations;
/*     */   }
/*     */ 
/*     */   public Integer getSaltSizeBytes()
/*     */   {
/* 520 */     return this.saltSizeBytes;
/*     */   }
/*     */ 
/*     */   public SaltGenerator getSaltGenerator()
/*     */   {
/* 525 */     return this.saltGenerator;
/*     */   }
/*     */ 
/*     */   public String getProviderName() {
/* 529 */     return this.providerName;
/*     */   }
/*     */ 
/*     */   public Provider getProvider() {
/* 533 */     return this.provider;
/*     */   }
/*     */ 
/*     */   public Boolean getInvertPositionOfSaltInMessageBeforeDigesting() {
/* 537 */     return this.invertPositionOfSaltInMessageBeforeDigesting;
/*     */   }
/*     */ 
/*     */   public Boolean getInvertPositionOfPlainSaltInEncryptionResults() {
/* 541 */     return this.invertPositionOfPlainSaltInEncryptionResults;
/*     */   }
/*     */ 
/*     */   public Boolean getUseLenientSaltSizeCheck() {
/* 545 */     return this.useLenientSaltSizeCheck;
/*     */   }
/*     */ 
/*     */   public Integer getPoolSize() {
/* 549 */     return this.poolSize;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.config.SimpleDigesterConfig
 * JD-Core Version:    0.6.2
 */