/*   1:    */package org.jasypt.digest.config;
/*   2:    */
/*   3:    */import java.security.Provider;
/*   4:    */import org.jasypt.exceptions.EncryptionInitializationException;
/*   5:    */import org.jasypt.salt.SaltGenerator;
/*   6:    */
/*  52:    */public class SimpleDigesterConfig
/*  53:    */  implements DigesterConfig
/*  54:    */{
/*  55: 55 */  private String algorithm = null;
/*  56: 56 */  private Integer iterations = null;
/*  57: 57 */  private Integer saltSizeBytes = null;
/*  58: 58 */  private SaltGenerator saltGenerator = null;
/*  59: 59 */  private String providerName = null;
/*  60: 60 */  private Provider provider = null;
/*  61: 61 */  private Boolean invertPositionOfSaltInMessageBeforeDigesting = null;
/*  62: 62 */  private Boolean invertPositionOfPlainSaltInEncryptionResults = null;
/*  63: 63 */  private Boolean useLenientSaltSizeCheck = null;
/*  64: 64 */  private Integer poolSize = null;
/*  65:    */  
/* 103:    */  public void setAlgorithm(String algorithm)
/* 104:    */  {
/* 105:105 */    this.algorithm = algorithm;
/* 106:    */  }
/* 107:    */  
/* 121:    */  public void setIterations(Integer iterations)
/* 122:    */  {
/* 123:123 */    this.iterations = iterations;
/* 124:    */  }
/* 125:    */  
/* 141:    */  public void setIterations(String iterations)
/* 142:    */  {
/* 143:143 */    if (iterations != null) {
/* 144:    */      try {
/* 145:145 */        this.iterations = new Integer(iterations);
/* 146:    */      } catch (NumberFormatException e) {
/* 147:147 */        throw new EncryptionInitializationException(e);
/* 148:    */      }
/* 149:    */    } else {
/* 150:150 */      this.iterations = null;
/* 151:    */    }
/* 152:    */  }
/* 153:    */  
/* 167:    */  public void setSaltSizeBytes(Integer saltSizeBytes)
/* 168:    */  {
/* 169:169 */    this.saltSizeBytes = saltSizeBytes;
/* 170:    */  }
/* 171:    */  
/* 187:    */  public void setSaltSizeBytes(String saltSizeBytes)
/* 188:    */  {
/* 189:189 */    if (saltSizeBytes != null) {
/* 190:    */      try {
/* 191:191 */        this.saltSizeBytes = new Integer(saltSizeBytes);
/* 192:    */      } catch (NumberFormatException e) {
/* 193:193 */        throw new EncryptionInitializationException(e);
/* 194:    */      }
/* 195:    */    } else {
/* 196:196 */      this.saltSizeBytes = null;
/* 197:    */    }
/* 198:    */  }
/* 199:    */  
/* 215:    */  public void setSaltGenerator(SaltGenerator saltGenerator)
/* 216:    */  {
/* 217:217 */    this.saltGenerator = saltGenerator;
/* 218:    */  }
/* 219:    */  
/* 235:    */  public void setSaltGeneratorClassName(String saltGeneratorClassName)
/* 236:    */  {
/* 237:237 */    if (saltGeneratorClassName != null) {
/* 238:    */      try {
/* 239:239 */        Class saltGeneratorClass = Thread.currentThread().getContextClassLoader().loadClass(saltGeneratorClassName);
/* 240:    */        
/* 241:241 */        this.saltGenerator = ((SaltGenerator)saltGeneratorClass.newInstance());
/* 242:    */      }
/* 243:    */      catch (Exception e) {
/* 244:244 */        throw new EncryptionInitializationException(e);
/* 245:    */      }
/* 246:    */    } else {
/* 247:247 */      this.saltGenerator = null;
/* 248:    */    }
/* 249:    */  }
/* 250:    */  
/* 272:    */  public void setProviderName(String providerName)
/* 273:    */  {
/* 274:274 */    this.providerName = providerName;
/* 275:    */  }
/* 276:    */  
/* 304:    */  public void setProvider(Provider provider)
/* 305:    */  {
/* 306:306 */    this.provider = provider;
/* 307:    */  }
/* 308:    */  
/* 335:    */  public void setProviderClassName(String providerClassName)
/* 336:    */  {
/* 337:337 */    if (providerClassName != null) {
/* 338:    */      try {
/* 339:339 */        Class providerClass = Thread.currentThread().getContextClassLoader().loadClass(providerClassName);
/* 340:    */        
/* 341:341 */        this.provider = ((Provider)providerClass.newInstance());
/* 342:    */      } catch (Exception e) {
/* 343:343 */        throw new EncryptionInitializationException(e);
/* 344:    */      }
/* 345:    */    } else {
/* 346:346 */      this.provider = null;
/* 347:    */    }
/* 348:    */  }
/* 349:    */  
/* 373:    */  public void setInvertPositionOfSaltInMessageBeforeDigesting(Boolean invertPositionOfSaltInMessageBeforeDigesting)
/* 374:    */  {
/* 375:375 */    this.invertPositionOfSaltInMessageBeforeDigesting = invertPositionOfSaltInMessageBeforeDigesting;
/* 376:    */  }
/* 377:    */  
/* 401:    */  public void setInvertPositionOfPlainSaltInEncryptionResults(Boolean invertPositionOfPlainSaltInEncryptionResults)
/* 402:    */  {
/* 403:403 */    this.invertPositionOfPlainSaltInEncryptionResults = invertPositionOfPlainSaltInEncryptionResults;
/* 404:    */  }
/* 405:    */  
/* 446:    */  public void setUseLenientSaltSizeCheck(Boolean useLenientSaltSizeCheck)
/* 447:    */  {
/* 448:448 */    this.useLenientSaltSizeCheck = useLenientSaltSizeCheck;
/* 449:    */  }
/* 450:    */  
/* 470:    */  public void setPoolSize(Integer poolSize)
/* 471:    */  {
/* 472:472 */    this.poolSize = poolSize;
/* 473:    */  }
/* 474:    */  
/* 494:    */  public void setPoolSize(String poolSize)
/* 495:    */  {
/* 496:496 */    if (poolSize != null) {
/* 497:    */      try {
/* 498:498 */        this.poolSize = new Integer(poolSize);
/* 499:    */      } catch (NumberFormatException e) {
/* 500:500 */        throw new EncryptionInitializationException(e);
/* 501:    */      }
/* 502:    */    } else {
/* 503:503 */      this.poolSize = null;
/* 504:    */    }
/* 505:    */  }
/* 506:    */  
/* 508:    */  public String getAlgorithm()
/* 509:    */  {
/* 510:510 */    return this.algorithm;
/* 511:    */  }
/* 512:    */  
/* 513:    */  public Integer getIterations()
/* 514:    */  {
/* 515:515 */    return this.iterations;
/* 516:    */  }
/* 517:    */  
/* 518:    */  public Integer getSaltSizeBytes()
/* 519:    */  {
/* 520:520 */    return this.saltSizeBytes;
/* 521:    */  }
/* 522:    */  
/* 523:    */  public SaltGenerator getSaltGenerator()
/* 524:    */  {
/* 525:525 */    return this.saltGenerator;
/* 526:    */  }
/* 527:    */  
/* 528:    */  public String getProviderName() {
/* 529:529 */    return this.providerName;
/* 530:    */  }
/* 531:    */  
/* 532:    */  public Provider getProvider() {
/* 533:533 */    return this.provider;
/* 534:    */  }
/* 535:    */  
/* 536:    */  public Boolean getInvertPositionOfSaltInMessageBeforeDigesting() {
/* 537:537 */    return this.invertPositionOfSaltInMessageBeforeDigesting;
/* 538:    */  }
/* 539:    */  
/* 540:    */  public Boolean getInvertPositionOfPlainSaltInEncryptionResults() {
/* 541:541 */    return this.invertPositionOfPlainSaltInEncryptionResults;
/* 542:    */  }
/* 543:    */  
/* 544:    */  public Boolean getUseLenientSaltSizeCheck() {
/* 545:545 */    return this.useLenientSaltSizeCheck;
/* 546:    */  }
/* 547:    */  
/* 548:    */  public Integer getPoolSize() {
/* 549:549 */    return this.poolSize;
/* 550:    */  }
/* 551:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.config.SimpleDigesterConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */