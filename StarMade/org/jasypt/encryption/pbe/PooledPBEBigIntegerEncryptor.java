/*     */ package org.jasypt.encryption.pbe;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import java.security.Provider;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.encryption.pbe.config.PBEConfig;
/*     */ import org.jasypt.exceptions.AlreadyInitializedException;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public final class PooledPBEBigIntegerEncryptor
/*     */   implements PBEBigIntegerCleanablePasswordEncryptor
/*     */ {
/*     */   private final StandardPBEBigIntegerEncryptor firstEncryptor;
/*  62 */   private PBEConfig config = null;
/*  63 */   private int poolSize = 0;
/*  64 */   private boolean poolSizeSet = false;
/*     */   private StandardPBEBigIntegerEncryptor[] pool;
/*  67 */   private int roundRobin = 0;
/*     */ 
/*  76 */   private boolean initialized = false;
/*     */ 
/*     */   public PooledPBEBigIntegerEncryptor()
/*     */   {
/*  85 */     this.firstEncryptor = new StandardPBEBigIntegerEncryptor();
/*     */   }
/*     */ 
/*     */   public synchronized void setConfig(PBEConfig config)
/*     */   {
/* 117 */     this.firstEncryptor.setConfig(config);
/* 118 */     this.config = config;
/*     */   }
/*     */ 
/*     */   public void setAlgorithm(String algorithm)
/*     */   {
/* 137 */     this.firstEncryptor.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 156 */     this.firstEncryptor.setPassword(password);
/*     */   }
/*     */ 
/*     */   public synchronized void setPasswordCharArray(char[] password)
/*     */   {
/* 189 */     this.firstEncryptor.setPasswordCharArray(password);
/*     */   }
/*     */ 
/*     */   public void setKeyObtentionIterations(int keyObtentionIterations)
/*     */   {
/* 207 */     this.firstEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/*     */   }
/*     */ 
/*     */   public void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 220 */     this.firstEncryptor.setSaltGenerator(saltGenerator);
/*     */   }
/*     */ 
/*     */   public void setProviderName(String providerName)
/*     */   {
/* 250 */     this.firstEncryptor.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public void setProvider(Provider provider)
/*     */   {
/* 273 */     this.firstEncryptor.setProvider(provider);
/*     */   }
/*     */ 
/*     */   public synchronized void setPoolSize(int poolSize)
/*     */   {
/* 289 */     CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
/* 290 */     if (isInitialized()) {
/* 291 */       throw new AlreadyInitializedException();
/*     */     }
/* 293 */     this.poolSize = poolSize;
/* 294 */     this.poolSizeSet = true;
/*     */   }
/*     */ 
/*     */   public boolean isInitialized()
/*     */   {
/* 321 */     return this.initialized;
/*     */   }
/*     */ 
/*     */   public synchronized void initialize()
/*     */   {
/* 359 */     if (!this.initialized)
/*     */     {
/* 361 */       if (this.config != null)
/*     */       {
/* 363 */         Integer configPoolSize = this.config.getPoolSize();
/*     */ 
/* 365 */         this.poolSize = ((this.poolSizeSet) || (configPoolSize == null) ? this.poolSize : configPoolSize.intValue());
/*     */       }
/*     */ 
/* 371 */       if (this.poolSize <= 0) {
/* 372 */         throw new IllegalArgumentException("Pool size must be set and > 0");
/*     */       }
/*     */ 
/* 375 */       this.pool = this.firstEncryptor.cloneAndInitializeEncryptor(this.poolSize);
/*     */ 
/* 377 */       this.initialized = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public BigInteger encrypt(BigInteger message)
/*     */   {
/* 433 */     if (!isInitialized())
/* 434 */       initialize();
/*     */     int poolPosition;
/* 438 */     synchronized (this) {
/* 439 */       poolPosition = this.roundRobin;
/* 440 */       this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/*     */     }
/*     */ 
/* 443 */     return this.pool[poolPosition].encrypt(message);
/*     */   }
/*     */ 
/*     */   public BigInteger decrypt(BigInteger encryptedMessage)
/*     */   {
/* 475 */     if (!isInitialized())
/* 476 */       initialize();
/*     */     int poolPosition;
/* 480 */     synchronized (this) {
/* 481 */       poolPosition = this.roundRobin;
/* 482 */       this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/*     */     }
/*     */ 
/* 485 */     return this.pool[poolPosition].decrypt(encryptedMessage);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.PooledPBEBigIntegerEncryptor
 * JD-Core Version:    0.6.2
 */