/*   1:    */package org.jasypt.encryption.pbe;
/*   2:    */
/*   3:    */import java.security.Provider;
/*   4:    */import org.jasypt.commons.CommonUtils;
/*   5:    */import org.jasypt.encryption.pbe.config.PBEConfig;
/*   6:    */import org.jasypt.exceptions.AlreadyInitializedException;
/*   7:    */import org.jasypt.salt.SaltGenerator;
/*   8:    */
/*  57:    */public final class PooledPBEStringEncryptor
/*  58:    */  implements PBEStringCleanablePasswordEncryptor
/*  59:    */{
/*  60:    */  private final StandardPBEStringEncryptor firstEncryptor;
/*  61: 61 */  private PBEConfig config = null;
/*  62: 62 */  private int poolSize = 0;
/*  63: 63 */  private boolean poolSizeSet = false;
/*  64:    */  
/*  65:    */  private StandardPBEStringEncryptor[] pool;
/*  66: 66 */  private int roundRobin = 0;
/*  67:    */  
/*  75: 75 */  private boolean initialized = false;
/*  76:    */  
/*  82:    */  public PooledPBEStringEncryptor()
/*  83:    */  {
/*  84: 84 */    this.firstEncryptor = new StandardPBEStringEncryptor();
/*  85:    */  }
/*  86:    */  
/* 114:    */  public synchronized void setConfig(PBEConfig config)
/* 115:    */  {
/* 116:116 */    this.firstEncryptor.setConfig(config);
/* 117:117 */    this.config = config;
/* 118:    */  }
/* 119:    */  
/* 134:    */  public void setAlgorithm(String algorithm)
/* 135:    */  {
/* 136:136 */    this.firstEncryptor.setAlgorithm(algorithm);
/* 137:    */  }
/* 138:    */  
/* 153:    */  public void setPassword(String password)
/* 154:    */  {
/* 155:155 */    this.firstEncryptor.setPassword(password);
/* 156:    */  }
/* 157:    */  
/* 186:    */  public synchronized void setPasswordCharArray(char[] password)
/* 187:    */  {
/* 188:188 */    this.firstEncryptor.setPasswordCharArray(password);
/* 189:    */  }
/* 190:    */  
/* 204:    */  public void setKeyObtentionIterations(int keyObtentionIterations)
/* 205:    */  {
/* 206:206 */    this.firstEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/* 207:    */  }
/* 208:    */  
/* 217:    */  public void setSaltGenerator(SaltGenerator saltGenerator)
/* 218:    */  {
/* 219:219 */    this.firstEncryptor.setSaltGenerator(saltGenerator);
/* 220:    */  }
/* 221:    */  
/* 247:    */  public void setProviderName(String providerName)
/* 248:    */  {
/* 249:249 */    this.firstEncryptor.setProviderName(providerName);
/* 250:    */  }
/* 251:    */  
/* 270:    */  public void setProvider(Provider provider)
/* 271:    */  {
/* 272:272 */    this.firstEncryptor.setProvider(provider);
/* 273:    */  }
/* 274:    */  
/* 290:    */  public synchronized void setStringOutputType(String stringOutputType)
/* 291:    */  {
/* 292:292 */    this.firstEncryptor.setStringOutputType(stringOutputType);
/* 293:    */  }
/* 294:    */  
/* 306:    */  public synchronized void setPoolSize(int poolSize)
/* 307:    */  {
/* 308:308 */    CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
/* 309:309 */    if (isInitialized()) {
/* 310:310 */      throw new AlreadyInitializedException();
/* 311:    */    }
/* 312:312 */    this.poolSize = poolSize;
/* 313:313 */    this.poolSizeSet = true;
/* 314:    */  }
/* 315:    */  
/* 338:    */  public boolean isInitialized()
/* 339:    */  {
/* 340:340 */    return this.initialized;
/* 341:    */  }
/* 342:    */  
/* 376:    */  public synchronized void initialize()
/* 377:    */  {
/* 378:378 */    if (!this.initialized)
/* 379:    */    {
/* 380:380 */      if (this.config != null)
/* 381:    */      {
/* 382:382 */        Integer configPoolSize = this.config.getPoolSize();
/* 383:    */        
/* 384:384 */        this.poolSize = ((this.poolSizeSet) || (configPoolSize == null) ? this.poolSize : configPoolSize.intValue());
/* 385:    */      }
/* 386:    */      
/* 390:390 */      if (this.poolSize <= 0) {
/* 391:391 */        throw new IllegalArgumentException("Pool size must be set and > 0");
/* 392:    */      }
/* 393:    */      
/* 394:394 */      this.pool = this.firstEncryptor.cloneAndInitializeEncryptor(this.poolSize);
/* 395:    */      
/* 396:396 */      this.initialized = true;
/* 397:    */    }
/* 398:    */  }
/* 399:    */  
/* 442:    */  public String encrypt(String message)
/* 443:    */  {
/* 444:444 */    if (!isInitialized()) {
/* 445:445 */      initialize();
/* 446:    */    }
/* 447:    */    
/* 448:    */    int poolPosition;
/* 449:449 */    synchronized (this) {
/* 450:450 */      poolPosition = this.roundRobin;
/* 451:451 */      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/* 452:    */    }
/* 453:    */    
/* 454:454 */    return this.pool[poolPosition].encrypt(message);
/* 455:    */  }
/* 456:    */  
/* 488:    */  public String decrypt(String encryptedMessage)
/* 489:    */  {
/* 490:490 */    if (!isInitialized()) {
/* 491:491 */      initialize();
/* 492:    */    }
/* 493:    */    
/* 494:    */    int poolPosition;
/* 495:495 */    synchronized (this) {
/* 496:496 */      poolPosition = this.roundRobin;
/* 497:497 */      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/* 498:    */    }
/* 499:    */    
/* 500:500 */    return this.pool[poolPosition].decrypt(encryptedMessage);
/* 501:    */  }
/* 502:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.PooledPBEStringEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */