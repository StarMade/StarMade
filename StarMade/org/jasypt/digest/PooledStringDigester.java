/*   1:    */package org.jasypt.digest;
/*   2:    */
/*   3:    */import java.security.Provider;
/*   4:    */import org.jasypt.commons.CommonUtils;
/*   5:    */import org.jasypt.digest.config.DigesterConfig;
/*   6:    */import org.jasypt.exceptions.AlreadyInitializedException;
/*   7:    */import org.jasypt.salt.SaltGenerator;
/*   8:    */
/*  56:    */public class PooledStringDigester
/*  57:    */  implements StringDigester
/*  58:    */{
/*  59:    */  private final StandardStringDigester firstDigester;
/*  60: 60 */  private DigesterConfig config = null;
/*  61: 61 */  private int poolSize = 0;
/*  62: 62 */  private boolean poolSizeSet = false;
/*  63:    */  
/*  64:    */  private StandardStringDigester[] pool;
/*  65: 65 */  private int roundRobin = 0;
/*  66:    */  
/*  74: 74 */  private boolean initialized = false;
/*  75:    */  
/*  81:    */  public PooledStringDigester()
/*  82:    */  {
/*  83: 83 */    this.firstDigester = new StandardStringDigester();
/*  84:    */  }
/*  85:    */  
/* 115:    */  public synchronized void setConfig(DigesterConfig config)
/* 116:    */  {
/* 117:117 */    this.firstDigester.setConfig(config);
/* 118:118 */    this.config = config;
/* 119:    */  }
/* 120:    */  
/* 147:    */  public synchronized void setAlgorithm(String algorithm)
/* 148:    */  {
/* 149:149 */    this.firstDigester.setAlgorithm(algorithm);
/* 150:    */  }
/* 151:    */  
/* 166:    */  public synchronized void setSaltSizeBytes(int saltSizeBytes)
/* 167:    */  {
/* 168:168 */    this.firstDigester.setSaltSizeBytes(saltSizeBytes);
/* 169:    */  }
/* 170:    */  
/* 186:    */  public synchronized void setIterations(int iterations)
/* 187:    */  {
/* 188:188 */    this.firstDigester.setIterations(iterations);
/* 189:    */  }
/* 190:    */  
/* 199:    */  public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
/* 200:    */  {
/* 201:201 */    this.firstDigester.setSaltGenerator(saltGenerator);
/* 202:    */  }
/* 203:    */  
/* 229:    */  public synchronized void setProviderName(String providerName)
/* 230:    */  {
/* 231:231 */    this.firstDigester.setProviderName(providerName);
/* 232:    */  }
/* 233:    */  
/* 252:    */  public synchronized void setProvider(Provider provider)
/* 253:    */  {
/* 254:254 */    this.firstDigester.setProvider(provider);
/* 255:    */  }
/* 256:    */  
/* 276:    */  public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting)
/* 277:    */  {
/* 278:278 */    this.firstDigester.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
/* 279:    */  }
/* 280:    */  
/* 302:    */  public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults)
/* 303:    */  {
/* 304:304 */    this.firstDigester.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
/* 305:    */  }
/* 306:    */  
/* 344:    */  public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck)
/* 345:    */  {
/* 346:346 */    this.firstDigester.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
/* 347:    */  }
/* 348:    */  
/* 375:    */  public synchronized void setUnicodeNormalizationIgnored(boolean unicodeNormalizationIgnored)
/* 376:    */  {
/* 377:377 */    this.firstDigester.setUnicodeNormalizationIgnored(unicodeNormalizationIgnored);
/* 378:    */  }
/* 379:    */  
/* 396:    */  public synchronized void setStringOutputType(String stringOutputType)
/* 397:    */  {
/* 398:398 */    this.firstDigester.setStringOutputType(stringOutputType);
/* 399:    */  }
/* 400:    */  
/* 414:    */  public synchronized void setPrefix(String prefix)
/* 415:    */  {
/* 416:416 */    this.firstDigester.setPrefix(prefix);
/* 417:    */  }
/* 418:    */  
/* 432:    */  public synchronized void setSuffix(String suffix)
/* 433:    */  {
/* 434:434 */    this.firstDigester.setSuffix(suffix);
/* 435:    */  }
/* 436:    */  
/* 448:    */  public synchronized void setPoolSize(int poolSize)
/* 449:    */  {
/* 450:450 */    CommonUtils.validateIsTrue(poolSize > 0, "Pool size be > 0");
/* 451:451 */    if (isInitialized()) {
/* 452:452 */      throw new AlreadyInitializedException();
/* 453:    */    }
/* 454:454 */    this.poolSize = poolSize;
/* 455:455 */    this.poolSizeSet = true;
/* 456:    */  }
/* 457:    */  
/* 482:    */  public boolean isInitialized()
/* 483:    */  {
/* 484:484 */    return this.initialized;
/* 485:    */  }
/* 486:    */  
/* 523:    */  public synchronized void initialize()
/* 524:    */  {
/* 525:525 */    if (!this.initialized)
/* 526:    */    {
/* 527:527 */      if (this.config != null)
/* 528:    */      {
/* 529:529 */        Integer configPoolSize = this.config.getPoolSize();
/* 530:    */        
/* 531:531 */        this.poolSize = ((this.poolSizeSet) || (configPoolSize == null) ? this.poolSize : configPoolSize.intValue());
/* 532:    */      }
/* 533:    */      
/* 537:537 */      if (this.poolSize <= 0) {
/* 538:538 */        throw new IllegalArgumentException("Pool size must be set and > 0");
/* 539:    */      }
/* 540:    */      
/* 541:541 */      this.pool = new StandardStringDigester[this.poolSize];
/* 542:542 */      this.pool[0] = this.firstDigester;
/* 543:    */      
/* 545:545 */      for (int i = 1; i < this.poolSize; i++) {
/* 546:546 */        this.pool[i] = this.pool[(i - 1)].cloneDigester();
/* 547:    */      }
/* 548:    */      
/* 549:549 */      this.initialized = true;
/* 550:    */    }
/* 551:    */  }
/* 552:    */  
/* 633:    */  public String digest(String message)
/* 634:    */  {
/* 635:635 */    if (!isInitialized()) {
/* 636:636 */      initialize();
/* 637:    */    }
/* 638:    */    
/* 639:    */    int poolPosition;
/* 640:640 */    synchronized (this) {
/* 641:641 */      poolPosition = this.roundRobin;
/* 642:642 */      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/* 643:    */    }
/* 644:644 */    return this.pool[poolPosition].digest(message);
/* 645:    */  }
/* 646:    */  
/* 681:    */  public boolean matches(String message, String digest)
/* 682:    */  {
/* 683:683 */    if (!isInitialized()) {
/* 684:684 */      initialize();
/* 685:    */    }
/* 686:    */    
/* 687:    */    int poolPosition;
/* 688:688 */    synchronized (this) {
/* 689:689 */      poolPosition = this.roundRobin;
/* 690:690 */      this.roundRobin = ((this.roundRobin + 1) % this.poolSize);
/* 691:    */    }
/* 692:    */    
/* 693:693 */    return this.pool[poolPosition].matches(message, digest);
/* 694:    */  }
/* 695:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.PooledStringDigester
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */