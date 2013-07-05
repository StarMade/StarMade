/*     */ package org.jasypt.encryption.pbe;
/*     */ 
/*     */ import java.security.Provider;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.encryption.pbe.config.PBEConfig;
/*     */ import org.jasypt.exceptions.AlreadyInitializedException;
/*     */ import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public final class PooledPBEByteEncryptor
/*     */   implements PBEByteCleanablePasswordEncryptor
/*     */ {
/*     */   private final StandardPBEByteEncryptor firstEncryptor;
/*  59 */   private PBEConfig config = null;
/*  60 */   private int poolSize = 0;
/*  61 */   private boolean poolSizeSet = false;
/*     */   private StandardPBEByteEncryptor[] pool;
/*  64 */   private int roundRobin = 0;
/*     */ 
/*  73 */   private boolean initialized = false;
/*     */ 
/*     */   public PooledPBEByteEncryptor()
/*     */   {
/*  83 */     this.firstEncryptor = new StandardPBEByteEncryptor();
/*     */   }
/*     */ 
/*     */   public synchronized void setConfig(PBEConfig config)
/*     */   {
/* 113 */     this.firstEncryptor.setConfig(config);
/* 114 */     this.config = config;
/*     */   }
/*     */ 
/*     */   public synchronized void setAlgorithm(String algorithm)
/*     */   {
/* 133 */     this.firstEncryptor.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public synchronized void setPassword(String password)
/*     */   {
/* 152 */     this.firstEncryptor.setPassword(password);
/*     */   }
/*     */ 
/*     */   public synchronized void setPasswordCharArray(char[] password)
/*     */   {
/* 185 */     this.firstEncryptor.setPasswordCharArray(password);
/*     */   }
/*     */ 
/*     */   public synchronized void setKeyObtentionIterations(int keyObtentionIterations)
/*     */   {
/* 204 */     this.firstEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/*     */   }
/*     */ 
/*     */   public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 217 */     this.firstEncryptor.setSaltGenerator(saltGenerator);
/*     */   }
/*     */ 
/*     */   public synchronized void setProviderName(String providerName)
/*     */   {
/* 247 */     this.firstEncryptor.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public synchronized void setProvider(Provider provider)
/*     */   {
/* 270 */     this.firstEncryptor.setProvider(provider);
/*     */   }
/*     */ 
/*     */   public synchronized void setPoolSize(int poolSize)
/*     */   {
/* 286 */     CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
/* 287 */     if (isInitialized()) {
/* 288 */       throw new AlreadyInitializedException();
/*     */     }
/* 290 */     this.poolSize = poolSize;
/* 291 */     this.poolSizeSet = true;
/*     */   }
/*     */ 
/*     */   public boolean isInitialized()
/*     */   {
/* 318 */     return this.initialized;
/*     */   }
/*     */ 
/*     */   public synchronized void initialize()
/*     */   {
/* 356 */     if (!this.initialized)
/*     */     {
/* 358 */       if (this.config != null)
/*     */       {
/* 360 */         Integer configPoolSize = this.config.getPoolSize();
/*     */ 
/* 362 */         this.poolSize = ((this.poolSizeSet) || (configPoolSize == null) ? this.poolSize : configPoolSize.intValue());
/*     */       }
/*     */ 
/* 368 */       if (this.poolSize <= 0) {
/* 369 */         throw new IllegalArgumentException("Pool size must be set and > 0");
/*     */       }
/*     */ 
/* 372 */       this.pool = this.firstEncryptor.cloneAndInitializeEncryptor(this.poolSize);
/*     */ 
/* 374 */       this.initialized = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] encrypt(byte[] message)
/*     */     throws EncryptionOperationNotPossibleException
/*     */   {
/* 419 */     if (!isInitialized())
/* 420 */       initialize();
/*     */     int poolPosition;
/* 424 */     synchronized (this) {
/* 425 */       poolPosition = this.roundRobin;
/* 426 */       this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/*     */     }
/*     */ 
/* 429 */     return this.pool[poolPosition].encrypt(message);
/*     */   }
/*     */ 
/*     */   public byte[] decrypt(byte[] encryptedMessage)
/*     */     throws EncryptionOperationNotPossibleException
/*     */   {
/* 462 */     if (!isInitialized())
/* 463 */       initialize();
/*     */     int poolPosition;
/* 467 */     synchronized (this) {
/* 468 */       poolPosition = this.roundRobin;
/* 469 */       this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/*     */     }
/*     */ 
/* 472 */     return this.pool[poolPosition].decrypt(encryptedMessage);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.PooledPBEByteEncryptor
 * JD-Core Version:    0.6.2
 */