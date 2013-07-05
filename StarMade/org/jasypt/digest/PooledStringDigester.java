/*     */ package org.jasypt.digest;
/*     */ 
/*     */ import java.security.Provider;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.digest.config.DigesterConfig;
/*     */ import org.jasypt.exceptions.AlreadyInitializedException;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public class PooledStringDigester
/*     */   implements StringDigester
/*     */ {
/*     */   private final StandardStringDigester firstDigester;
/*  60 */   private DigesterConfig config = null;
/*  61 */   private int poolSize = 0;
/*  62 */   private boolean poolSizeSet = false;
/*     */   private StandardStringDigester[] pool;
/*  65 */   private int roundRobin = 0;
/*     */ 
/*  74 */   private boolean initialized = false;
/*     */ 
/*     */   public PooledStringDigester()
/*     */   {
/*  83 */     this.firstDigester = new StandardStringDigester();
/*     */   }
/*     */ 
/*     */   public synchronized void setConfig(DigesterConfig config)
/*     */   {
/* 117 */     this.firstDigester.setConfig(config);
/* 118 */     this.config = config;
/*     */   }
/*     */ 
/*     */   public synchronized void setAlgorithm(String algorithm)
/*     */   {
/* 149 */     this.firstDigester.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public synchronized void setSaltSizeBytes(int saltSizeBytes)
/*     */   {
/* 168 */     this.firstDigester.setSaltSizeBytes(saltSizeBytes);
/*     */   }
/*     */ 
/*     */   public synchronized void setIterations(int iterations)
/*     */   {
/* 188 */     this.firstDigester.setIterations(iterations);
/*     */   }
/*     */ 
/*     */   public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 201 */     this.firstDigester.setSaltGenerator(saltGenerator);
/*     */   }
/*     */ 
/*     */   public synchronized void setProviderName(String providerName)
/*     */   {
/* 231 */     this.firstDigester.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public synchronized void setProvider(Provider provider)
/*     */   {
/* 254 */     this.firstDigester.setProvider(provider);
/*     */   }
/*     */ 
/*     */   public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting)
/*     */   {
/* 278 */     this.firstDigester.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
/*     */   }
/*     */ 
/*     */   public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults)
/*     */   {
/* 304 */     this.firstDigester.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
/*     */   }
/*     */ 
/*     */   public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck)
/*     */   {
/* 346 */     this.firstDigester.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
/*     */   }
/*     */ 
/*     */   public synchronized void setUnicodeNormalizationIgnored(boolean unicodeNormalizationIgnored)
/*     */   {
/* 377 */     this.firstDigester.setUnicodeNormalizationIgnored(unicodeNormalizationIgnored);
/*     */   }
/*     */ 
/*     */   public synchronized void setStringOutputType(String stringOutputType)
/*     */   {
/* 398 */     this.firstDigester.setStringOutputType(stringOutputType);
/*     */   }
/*     */ 
/*     */   public synchronized void setPrefix(String prefix)
/*     */   {
/* 416 */     this.firstDigester.setPrefix(prefix);
/*     */   }
/*     */ 
/*     */   public synchronized void setSuffix(String suffix)
/*     */   {
/* 434 */     this.firstDigester.setSuffix(suffix);
/*     */   }
/*     */ 
/*     */   public synchronized void setPoolSize(int poolSize)
/*     */   {
/* 450 */     CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
/* 451 */     if (isInitialized()) {
/* 452 */       throw new AlreadyInitializedException();
/*     */     }
/* 454 */     this.poolSize = poolSize;
/* 455 */     this.poolSizeSet = true;
/*     */   }
/*     */ 
/*     */   public boolean isInitialized()
/*     */   {
/* 484 */     return this.initialized;
/*     */   }
/*     */ 
/*     */   public synchronized void initialize()
/*     */   {
/* 525 */     if (!this.initialized)
/*     */     {
/* 527 */       if (this.config != null)
/*     */       {
/* 529 */         Integer configPoolSize = this.config.getPoolSize();
/*     */ 
/* 531 */         this.poolSize = ((this.poolSizeSet) || (configPoolSize == null) ? this.poolSize : configPoolSize.intValue());
/*     */       }
/*     */ 
/* 537 */       if (this.poolSize <= 0) {
/* 538 */         throw new IllegalArgumentException("Pool size must be set and > 0");
/*     */       }
/*     */ 
/* 541 */       this.pool = new StandardStringDigester[this.poolSize];
/* 542 */       this.pool[0] = this.firstDigester;
/*     */ 
/* 545 */       for (int i = 1; i < this.poolSize; i++) {
/* 546 */         this.pool[i] = this.pool[(i - 1)].cloneDigester();
/*     */       }
/*     */ 
/* 549 */       this.initialized = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public String digest(String message)
/*     */   {
/* 635 */     if (!isInitialized())
/* 636 */       initialize();
/*     */     int poolPosition;
/* 640 */     synchronized (this) {
/* 641 */       poolPosition = this.roundRobin;
/* 642 */       this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/*     */     }
/* 644 */     return this.pool[poolPosition].digest(message);
/*     */   }
/*     */ 
/*     */   public boolean matches(String message, String digest)
/*     */   {
/* 683 */     if (!isInitialized())
/* 684 */       initialize();
/*     */     int poolPosition;
/* 688 */     synchronized (this) {
/* 689 */       poolPosition = this.roundRobin;
/* 690 */       this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/*     */     }
/*     */ 
/* 693 */     return this.pool[poolPosition].matches(message, digest);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.PooledStringDigester
 * JD-Core Version:    0.6.2
 */