/*   1:    */package org.jasypt.encryption.pbe;
/*   2:    */
/*   3:    */import java.security.Provider;
/*   4:    */import org.jasypt.commons.CommonUtils;
/*   5:    */import org.jasypt.encryption.pbe.config.PBEConfig;
/*   6:    */import org.jasypt.exceptions.AlreadyInitializedException;
/*   7:    */import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*   8:    */import org.jasypt.salt.SaltGenerator;
/*   9:    */
/*  55:    */public final class PooledPBEByteEncryptor
/*  56:    */  implements PBEByteCleanablePasswordEncryptor
/*  57:    */{
/*  58:    */  private final StandardPBEByteEncryptor firstEncryptor;
/*  59: 59 */  private PBEConfig config = null;
/*  60: 60 */  private int poolSize = 0;
/*  61: 61 */  private boolean poolSizeSet = false;
/*  62:    */  
/*  63:    */  private StandardPBEByteEncryptor[] pool;
/*  64: 64 */  private int roundRobin = 0;
/*  65:    */  
/*  73: 73 */  private boolean initialized = false;
/*  74:    */  
/*  81:    */  public PooledPBEByteEncryptor()
/*  82:    */  {
/*  83: 83 */    this.firstEncryptor = new StandardPBEByteEncryptor();
/*  84:    */  }
/*  85:    */  
/* 111:    */  public synchronized void setConfig(PBEConfig config)
/* 112:    */  {
/* 113:113 */    this.firstEncryptor.setConfig(config);
/* 114:114 */    this.config = config;
/* 115:    */  }
/* 116:    */  
/* 131:    */  public synchronized void setAlgorithm(String algorithm)
/* 132:    */  {
/* 133:133 */    this.firstEncryptor.setAlgorithm(algorithm);
/* 134:    */  }
/* 135:    */  
/* 150:    */  public synchronized void setPassword(String password)
/* 151:    */  {
/* 152:152 */    this.firstEncryptor.setPassword(password);
/* 153:    */  }
/* 154:    */  
/* 183:    */  public synchronized void setPasswordCharArray(char[] password)
/* 184:    */  {
/* 185:185 */    this.firstEncryptor.setPasswordCharArray(password);
/* 186:    */  }
/* 187:    */  
/* 202:    */  public synchronized void setKeyObtentionIterations(int keyObtentionIterations)
/* 203:    */  {
/* 204:204 */    this.firstEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/* 205:    */  }
/* 206:    */  
/* 215:    */  public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
/* 216:    */  {
/* 217:217 */    this.firstEncryptor.setSaltGenerator(saltGenerator);
/* 218:    */  }
/* 219:    */  
/* 245:    */  public synchronized void setProviderName(String providerName)
/* 246:    */  {
/* 247:247 */    this.firstEncryptor.setProviderName(providerName);
/* 248:    */  }
/* 249:    */  
/* 268:    */  public synchronized void setProvider(Provider provider)
/* 269:    */  {
/* 270:270 */    this.firstEncryptor.setProvider(provider);
/* 271:    */  }
/* 272:    */  
/* 284:    */  public synchronized void setPoolSize(int poolSize)
/* 285:    */  {
/* 286:286 */    CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
/* 287:287 */    if (isInitialized()) {
/* 288:288 */      throw new AlreadyInitializedException();
/* 289:    */    }
/* 290:290 */    this.poolSize = poolSize;
/* 291:291 */    this.poolSizeSet = true;
/* 292:    */  }
/* 293:    */  
/* 316:    */  public boolean isInitialized()
/* 317:    */  {
/* 318:318 */    return this.initialized;
/* 319:    */  }
/* 320:    */  
/* 354:    */  public synchronized void initialize()
/* 355:    */  {
/* 356:356 */    if (!this.initialized)
/* 357:    */    {
/* 358:358 */      if (this.config != null)
/* 359:    */      {
/* 360:360 */        Integer configPoolSize = this.config.getPoolSize();
/* 361:    */        
/* 362:362 */        this.poolSize = ((this.poolSizeSet) || (configPoolSize == null) ? this.poolSize : configPoolSize.intValue());
/* 363:    */      }
/* 364:    */      
/* 368:368 */      if (this.poolSize <= 0) {
/* 369:369 */        throw new IllegalArgumentException("Pool size must be set and > 0");
/* 370:    */      }
/* 371:    */      
/* 372:372 */      this.pool = this.firstEncryptor.cloneAndInitializeEncryptor(this.poolSize);
/* 373:    */      
/* 374:374 */      this.initialized = true;
/* 375:    */    }
/* 376:    */  }
/* 377:    */  
/* 416:    */  public byte[] encrypt(byte[] message)
/* 417:    */    throws EncryptionOperationNotPossibleException
/* 418:    */  {
/* 419:419 */    if (!isInitialized()) {
/* 420:420 */      initialize();
/* 421:    */    }
/* 422:    */    
/* 423:    */    int poolPosition;
/* 424:424 */    synchronized (this) {
/* 425:425 */      poolPosition = this.roundRobin;
/* 426:426 */      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/* 427:    */    }
/* 428:    */    
/* 429:429 */    return this.pool[poolPosition].encrypt(message);
/* 430:    */  }
/* 431:    */  
/* 459:    */  public byte[] decrypt(byte[] encryptedMessage)
/* 460:    */    throws EncryptionOperationNotPossibleException
/* 461:    */  {
/* 462:462 */    if (!isInitialized()) {
/* 463:463 */      initialize();
/* 464:    */    }
/* 465:    */    
/* 466:    */    int poolPosition;
/* 467:467 */    synchronized (this) {
/* 468:468 */      poolPosition = this.roundRobin;
/* 469:469 */      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/* 470:    */    }
/* 471:    */    
/* 472:472 */    return this.pool[poolPosition].decrypt(encryptedMessage);
/* 473:    */  }
/* 474:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.PooledPBEByteEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */