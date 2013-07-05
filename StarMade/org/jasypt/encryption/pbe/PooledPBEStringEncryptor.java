/*     */ package org.jasypt.encryption.pbe;
/*     */ 
/*     */ import java.security.Provider;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.encryption.pbe.config.PBEConfig;
/*     */ import org.jasypt.exceptions.AlreadyInitializedException;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public final class PooledPBEStringEncryptor
/*     */   implements PBEStringCleanablePasswordEncryptor
/*     */ {
/*     */   private final StandardPBEStringEncryptor firstEncryptor;
/*  61 */   private PBEConfig config = null;
/*  62 */   private int poolSize = 0;
/*  63 */   private boolean poolSizeSet = false;
/*     */   private StandardPBEStringEncryptor[] pool;
/*  66 */   private int roundRobin = 0;
/*     */ 
/*  75 */   private boolean initialized = false;
/*     */ 
/*     */   public PooledPBEStringEncryptor()
/*     */   {
/*  84 */     this.firstEncryptor = new StandardPBEStringEncryptor();
/*     */   }
/*     */ 
/*     */   public synchronized void setConfig(PBEConfig config)
/*     */   {
/* 116 */     this.firstEncryptor.setConfig(config);
/* 117 */     this.config = config;
/*     */   }
/*     */ 
/*     */   public void setAlgorithm(String algorithm)
/*     */   {
/* 136 */     this.firstEncryptor.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 155 */     this.firstEncryptor.setPassword(password);
/*     */   }
/*     */ 
/*     */   public synchronized void setPasswordCharArray(char[] password)
/*     */   {
/* 188 */     this.firstEncryptor.setPasswordCharArray(password);
/*     */   }
/*     */ 
/*     */   public void setKeyObtentionIterations(int keyObtentionIterations)
/*     */   {
/* 206 */     this.firstEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/*     */   }
/*     */ 
/*     */   public void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 219 */     this.firstEncryptor.setSaltGenerator(saltGenerator);
/*     */   }
/*     */ 
/*     */   public void setProviderName(String providerName)
/*     */   {
/* 249 */     this.firstEncryptor.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public void setProvider(Provider provider)
/*     */   {
/* 272 */     this.firstEncryptor.setProvider(provider);
/*     */   }
/*     */ 
/*     */   public synchronized void setStringOutputType(String stringOutputType)
/*     */   {
/* 292 */     this.firstEncryptor.setStringOutputType(stringOutputType);
/*     */   }
/*     */ 
/*     */   public synchronized void setPoolSize(int poolSize)
/*     */   {
/* 308 */     CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
/* 309 */     if (isInitialized()) {
/* 310 */       throw new AlreadyInitializedException();
/*     */     }
/* 312 */     this.poolSize = poolSize;
/* 313 */     this.poolSizeSet = true;
/*     */   }
/*     */ 
/*     */   public boolean isInitialized()
/*     */   {
/* 340 */     return this.initialized;
/*     */   }
/*     */ 
/*     */   public synchronized void initialize()
/*     */   {
/* 378 */     if (!this.initialized)
/*     */     {
/* 380 */       if (this.config != null)
/*     */       {
/* 382 */         Integer configPoolSize = this.config.getPoolSize();
/*     */ 
/* 384 */         this.poolSize = ((this.poolSizeSet) || (configPoolSize == null) ? this.poolSize : configPoolSize.intValue());
/*     */       }
/*     */ 
/* 390 */       if (this.poolSize <= 0) {
/* 391 */         throw new IllegalArgumentException("Pool size must be set and > 0");
/*     */       }
/*     */ 
/* 394 */       this.pool = this.firstEncryptor.cloneAndInitializeEncryptor(this.poolSize);
/*     */ 
/* 396 */       this.initialized = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public String encrypt(String message)
/*     */   {
/* 444 */     if (!isInitialized())
/* 445 */       initialize();
/*     */     int poolPosition;
/* 449 */     synchronized (this) {
/* 450 */       poolPosition = this.roundRobin;
/* 451 */       this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/*     */     }
/*     */ 
/* 454 */     return this.pool[poolPosition].encrypt(message);
/*     */   }
/*     */ 
/*     */   public String decrypt(String encryptedMessage)
/*     */   {
/* 490 */     if (!isInitialized())
/* 491 */       initialize();
/*     */     int poolPosition;
/* 495 */     synchronized (this) {
/* 496 */       poolPosition = this.roundRobin;
/* 497 */       this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/*     */     }
/*     */ 
/* 500 */     return this.pool[poolPosition].decrypt(encryptedMessage);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.PooledPBEStringEncryptor
 * JD-Core Version:    0.6.2
 */