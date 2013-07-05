/*     */ package org.jasypt.digest;
/*     */ 
/*     */ import java.security.Provider;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.digest.config.DigesterConfig;
/*     */ import org.jasypt.exceptions.AlreadyInitializedException;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public class PooledByteDigester
/*     */   implements ByteDigester
/*     */ {
/*     */   private final StandardByteDigester firstDigester;
/*  58 */   private DigesterConfig config = null;
/*  59 */   private int poolSize = 0;
/*  60 */   private boolean poolSizeSet = false;
/*     */   private StandardByteDigester[] pool;
/*  63 */   private int roundRobin = 0;
/*     */ 
/*  72 */   private boolean initialized = false;
/*     */ 
/*     */   public PooledByteDigester()
/*     */   {
/*  81 */     this.firstDigester = new StandardByteDigester();
/*     */   }
/*     */ 
/*     */   public synchronized void setConfig(DigesterConfig config)
/*     */   {
/* 112 */     this.firstDigester.setConfig(config);
/* 113 */     this.config = config;
/*     */   }
/*     */ 
/*     */   public synchronized void setAlgorithm(String algorithm)
/*     */   {
/* 144 */     this.firstDigester.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public synchronized void setSaltSizeBytes(int saltSizeBytes)
/*     */   {
/* 163 */     this.firstDigester.setSaltSizeBytes(saltSizeBytes);
/*     */   }
/*     */ 
/*     */   public synchronized void setIterations(int iterations)
/*     */   {
/* 183 */     this.firstDigester.setIterations(iterations);
/*     */   }
/*     */ 
/*     */   public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 196 */     this.firstDigester.setSaltGenerator(saltGenerator);
/*     */   }
/*     */ 
/*     */   public synchronized void setProviderName(String providerName)
/*     */   {
/* 226 */     this.firstDigester.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public synchronized void setProvider(Provider provider)
/*     */   {
/* 249 */     this.firstDigester.setProvider(provider);
/*     */   }
/*     */ 
/*     */   public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting)
/*     */   {
/* 273 */     this.firstDigester.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
/*     */   }
/*     */ 
/*     */   public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults)
/*     */   {
/* 299 */     this.firstDigester.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
/*     */   }
/*     */ 
/*     */   public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck)
/*     */   {
/* 341 */     this.firstDigester.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
/*     */   }
/*     */ 
/*     */   public synchronized void setPoolSize(int poolSize)
/*     */   {
/* 357 */     CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
/* 358 */     if (isInitialized()) {
/* 359 */       throw new AlreadyInitializedException();
/*     */     }
/* 361 */     this.poolSize = poolSize;
/* 362 */     this.poolSizeSet = true;
/*     */   }
/*     */ 
/*     */   public boolean isInitialized()
/*     */   {
/* 390 */     return this.initialized;
/*     */   }
/*     */ 
/*     */   public synchronized void initialize()
/*     */   {
/* 431 */     if (!this.initialized)
/*     */     {
/* 433 */       if (this.config != null)
/*     */       {
/* 435 */         Integer configPoolSize = this.config.getPoolSize();
/*     */ 
/* 437 */         this.poolSize = ((this.poolSizeSet) || (configPoolSize == null) ? this.poolSize : configPoolSize.intValue());
/*     */       }
/*     */ 
/* 443 */       if (this.poolSize <= 0) {
/* 444 */         throw new IllegalArgumentException("Pool size must be set and > 0");
/*     */       }
/*     */ 
/* 447 */       this.pool = new StandardByteDigester[this.poolSize];
/* 448 */       this.pool[0] = this.firstDigester;
/*     */ 
/* 450 */       for (int i = 1; i < this.poolSize; i++) {
/* 451 */         this.pool[i] = this.pool[(i - 1)].cloneDigester();
/*     */       }
/*     */ 
/* 454 */       this.initialized = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] digest(byte[] message)
/*     */   {
/* 537 */     if (!isInitialized())
/* 538 */       initialize();
/*     */     int poolPosition;
/* 542 */     synchronized (this) {
/* 543 */       poolPosition = this.roundRobin;
/* 544 */       this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/*     */     }
/*     */ 
/* 547 */     return this.pool[poolPosition].digest(message);
/*     */   }
/*     */ 
/*     */   public boolean matches(byte[] message, byte[] digest)
/*     */   {
/* 586 */     if (!isInitialized())
/* 587 */       initialize();
/*     */     int poolPosition;
/* 591 */     synchronized (this) {
/* 592 */       poolPosition = this.roundRobin;
/* 593 */       this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/*     */     }
/*     */ 
/* 596 */     return this.pool[poolPosition].matches(message, digest);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.PooledByteDigester
 * JD-Core Version:    0.6.2
 */