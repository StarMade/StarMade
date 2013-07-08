/*   1:    */package org.jasypt.digest;
/*   2:    */
/*   3:    */import java.security.Provider;
/*   4:    */import org.jasypt.commons.CommonUtils;
/*   5:    */import org.jasypt.digest.config.DigesterConfig;
/*   6:    */import org.jasypt.exceptions.AlreadyInitializedException;
/*   7:    */import org.jasypt.salt.SaltGenerator;
/*   8:    */
/*  54:    */public class PooledByteDigester
/*  55:    */  implements ByteDigester
/*  56:    */{
/*  57:    */  private final StandardByteDigester firstDigester;
/*  58: 58 */  private DigesterConfig config = null;
/*  59: 59 */  private int poolSize = 0;
/*  60: 60 */  private boolean poolSizeSet = false;
/*  61:    */  
/*  62:    */  private StandardByteDigester[] pool;
/*  63: 63 */  private int roundRobin = 0;
/*  64:    */  
/*  72: 72 */  private boolean initialized = false;
/*  73:    */  
/*  79:    */  public PooledByteDigester()
/*  80:    */  {
/*  81: 81 */    this.firstDigester = new StandardByteDigester();
/*  82:    */  }
/*  83:    */  
/* 110:    */  public synchronized void setConfig(DigesterConfig config)
/* 111:    */  {
/* 112:112 */    this.firstDigester.setConfig(config);
/* 113:113 */    this.config = config;
/* 114:    */  }
/* 115:    */  
/* 142:    */  public synchronized void setAlgorithm(String algorithm)
/* 143:    */  {
/* 144:144 */    this.firstDigester.setAlgorithm(algorithm);
/* 145:    */  }
/* 146:    */  
/* 161:    */  public synchronized void setSaltSizeBytes(int saltSizeBytes)
/* 162:    */  {
/* 163:163 */    this.firstDigester.setSaltSizeBytes(saltSizeBytes);
/* 164:    */  }
/* 165:    */  
/* 181:    */  public synchronized void setIterations(int iterations)
/* 182:    */  {
/* 183:183 */    this.firstDigester.setIterations(iterations);
/* 184:    */  }
/* 185:    */  
/* 194:    */  public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
/* 195:    */  {
/* 196:196 */    this.firstDigester.setSaltGenerator(saltGenerator);
/* 197:    */  }
/* 198:    */  
/* 224:    */  public synchronized void setProviderName(String providerName)
/* 225:    */  {
/* 226:226 */    this.firstDigester.setProviderName(providerName);
/* 227:    */  }
/* 228:    */  
/* 247:    */  public synchronized void setProvider(Provider provider)
/* 248:    */  {
/* 249:249 */    this.firstDigester.setProvider(provider);
/* 250:    */  }
/* 251:    */  
/* 271:    */  public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting)
/* 272:    */  {
/* 273:273 */    this.firstDigester.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
/* 274:    */  }
/* 275:    */  
/* 297:    */  public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults)
/* 298:    */  {
/* 299:299 */    this.firstDigester.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
/* 300:    */  }
/* 301:    */  
/* 339:    */  public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck)
/* 340:    */  {
/* 341:341 */    this.firstDigester.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
/* 342:    */  }
/* 343:    */  
/* 355:    */  public synchronized void setPoolSize(int poolSize)
/* 356:    */  {
/* 357:357 */    CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
/* 358:358 */    if (isInitialized()) {
/* 359:359 */      throw new AlreadyInitializedException();
/* 360:    */    }
/* 361:361 */    this.poolSize = poolSize;
/* 362:362 */    this.poolSizeSet = true;
/* 363:    */  }
/* 364:    */  
/* 388:    */  public boolean isInitialized()
/* 389:    */  {
/* 390:390 */    return this.initialized;
/* 391:    */  }
/* 392:    */  
/* 429:    */  public synchronized void initialize()
/* 430:    */  {
/* 431:431 */    if (!this.initialized)
/* 432:    */    {
/* 433:433 */      if (this.config != null)
/* 434:    */      {
/* 435:435 */        Integer configPoolSize = this.config.getPoolSize();
/* 436:    */        
/* 437:437 */        this.poolSize = ((this.poolSizeSet) || (configPoolSize == null) ? this.poolSize : configPoolSize.intValue());
/* 438:    */      }
/* 439:    */      
/* 443:443 */      if (this.poolSize <= 0) {
/* 444:444 */        throw new IllegalArgumentException("Pool size must be set and > 0");
/* 445:    */      }
/* 446:    */      
/* 447:447 */      this.pool = new StandardByteDigester[this.poolSize];
/* 448:448 */      this.pool[0] = this.firstDigester;
/* 449:    */      
/* 450:450 */      for (int i = 1; i < this.poolSize; i++) {
/* 451:451 */        this.pool[i] = this.pool[(i - 1)].cloneDigester();
/* 452:    */      }
/* 453:    */      
/* 454:454 */      this.initialized = true;
/* 455:    */    }
/* 456:    */  }
/* 457:    */  
/* 535:    */  public byte[] digest(byte[] message)
/* 536:    */  {
/* 537:537 */    if (!isInitialized()) {
/* 538:538 */      initialize();
/* 539:    */    }
/* 540:    */    
/* 541:    */    int poolPosition;
/* 542:542 */    synchronized (this) {
/* 543:543 */      poolPosition = this.roundRobin;
/* 544:544 */      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/* 545:    */    }
/* 546:    */    
/* 547:547 */    return this.pool[poolPosition].digest(message);
/* 548:    */  }
/* 549:    */  
/* 584:    */  public boolean matches(byte[] message, byte[] digest)
/* 585:    */  {
/* 586:586 */    if (!isInitialized()) {
/* 587:587 */      initialize();
/* 588:    */    }
/* 589:    */    
/* 590:    */    int poolPosition;
/* 591:591 */    synchronized (this) {
/* 592:592 */      poolPosition = this.roundRobin;
/* 593:593 */      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/* 594:    */    }
/* 595:    */    
/* 596:596 */    return this.pool[poolPosition].matches(message, digest);
/* 597:    */  }
/* 598:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.PooledByteDigester
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */