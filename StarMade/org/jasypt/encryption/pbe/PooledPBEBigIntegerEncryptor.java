/*   1:    */package org.jasypt.encryption.pbe;
/*   2:    */
/*   3:    */import java.math.BigInteger;
/*   4:    */import java.security.Provider;
/*   5:    */import org.jasypt.commons.CommonUtils;
/*   6:    */import org.jasypt.encryption.pbe.config.PBEConfig;
/*   7:    */import org.jasypt.exceptions.AlreadyInitializedException;
/*   8:    */import org.jasypt.salt.SaltGenerator;
/*   9:    */
/*  58:    */public final class PooledPBEBigIntegerEncryptor
/*  59:    */  implements PBEBigIntegerCleanablePasswordEncryptor
/*  60:    */{
/*  61:    */  private final StandardPBEBigIntegerEncryptor firstEncryptor;
/*  62: 62 */  private PBEConfig config = null;
/*  63: 63 */  private int poolSize = 0;
/*  64: 64 */  private boolean poolSizeSet = false;
/*  65:    */  
/*  66:    */  private StandardPBEBigIntegerEncryptor[] pool;
/*  67: 67 */  private int roundRobin = 0;
/*  68:    */  
/*  76: 76 */  private boolean initialized = false;
/*  77:    */  
/*  83:    */  public PooledPBEBigIntegerEncryptor()
/*  84:    */  {
/*  85: 85 */    this.firstEncryptor = new StandardPBEBigIntegerEncryptor();
/*  86:    */  }
/*  87:    */  
/* 115:    */  public synchronized void setConfig(PBEConfig config)
/* 116:    */  {
/* 117:117 */    this.firstEncryptor.setConfig(config);
/* 118:118 */    this.config = config;
/* 119:    */  }
/* 120:    */  
/* 135:    */  public void setAlgorithm(String algorithm)
/* 136:    */  {
/* 137:137 */    this.firstEncryptor.setAlgorithm(algorithm);
/* 138:    */  }
/* 139:    */  
/* 154:    */  public void setPassword(String password)
/* 155:    */  {
/* 156:156 */    this.firstEncryptor.setPassword(password);
/* 157:    */  }
/* 158:    */  
/* 187:    */  public synchronized void setPasswordCharArray(char[] password)
/* 188:    */  {
/* 189:189 */    this.firstEncryptor.setPasswordCharArray(password);
/* 190:    */  }
/* 191:    */  
/* 205:    */  public void setKeyObtentionIterations(int keyObtentionIterations)
/* 206:    */  {
/* 207:207 */    this.firstEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/* 208:    */  }
/* 209:    */  
/* 218:    */  public void setSaltGenerator(SaltGenerator saltGenerator)
/* 219:    */  {
/* 220:220 */    this.firstEncryptor.setSaltGenerator(saltGenerator);
/* 221:    */  }
/* 222:    */  
/* 248:    */  public void setProviderName(String providerName)
/* 249:    */  {
/* 250:250 */    this.firstEncryptor.setProviderName(providerName);
/* 251:    */  }
/* 252:    */  
/* 271:    */  public void setProvider(Provider provider)
/* 272:    */  {
/* 273:273 */    this.firstEncryptor.setProvider(provider);
/* 274:    */  }
/* 275:    */  
/* 287:    */  public synchronized void setPoolSize(int poolSize)
/* 288:    */  {
/* 289:289 */    CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
/* 290:290 */    if (isInitialized()) {
/* 291:291 */      throw new AlreadyInitializedException();
/* 292:    */    }
/* 293:293 */    this.poolSize = poolSize;
/* 294:294 */    this.poolSizeSet = true;
/* 295:    */  }
/* 296:    */  
/* 319:    */  public boolean isInitialized()
/* 320:    */  {
/* 321:321 */    return this.initialized;
/* 322:    */  }
/* 323:    */  
/* 357:    */  public synchronized void initialize()
/* 358:    */  {
/* 359:359 */    if (!this.initialized)
/* 360:    */    {
/* 361:361 */      if (this.config != null)
/* 362:    */      {
/* 363:363 */        Integer configPoolSize = this.config.getPoolSize();
/* 364:    */        
/* 365:365 */        this.poolSize = ((this.poolSizeSet) || (configPoolSize == null) ? this.poolSize : configPoolSize.intValue());
/* 366:    */      }
/* 367:    */      
/* 371:371 */      if (this.poolSize <= 0) {
/* 372:372 */        throw new IllegalArgumentException("Pool size must be set and > 0");
/* 373:    */      }
/* 374:    */      
/* 375:375 */      this.pool = this.firstEncryptor.cloneAndInitializeEncryptor(this.poolSize);
/* 376:    */      
/* 377:377 */      this.initialized = true;
/* 378:    */    }
/* 379:    */  }
/* 380:    */  
/* 431:    */  public BigInteger encrypt(BigInteger message)
/* 432:    */  {
/* 433:433 */    if (!isInitialized()) {
/* 434:434 */      initialize();
/* 435:    */    }
/* 436:    */    
/* 437:    */    int poolPosition;
/* 438:438 */    synchronized (this) {
/* 439:439 */      poolPosition = this.roundRobin;
/* 440:440 */      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/* 441:    */    }
/* 442:    */    
/* 443:443 */    return this.pool[poolPosition].encrypt(message);
/* 444:    */  }
/* 445:    */  
/* 473:    */  public BigInteger decrypt(BigInteger encryptedMessage)
/* 474:    */  {
/* 475:475 */    if (!isInitialized()) {
/* 476:476 */      initialize();
/* 477:    */    }
/* 478:    */    
/* 479:    */    int poolPosition;
/* 480:480 */    synchronized (this) {
/* 481:481 */      poolPosition = this.roundRobin;
/* 482:482 */      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/* 483:    */    }
/* 484:    */    
/* 485:485 */    return this.pool[poolPosition].decrypt(encryptedMessage);
/* 486:    */  }
/* 487:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.PooledPBEBigIntegerEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */