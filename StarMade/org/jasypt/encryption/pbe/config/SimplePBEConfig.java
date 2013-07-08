/*   1:    */package org.jasypt.encryption.pbe.config;
/*   2:    */
/*   3:    */import java.security.Provider;
/*   4:    */import org.jasypt.exceptions.EncryptionInitializationException;
/*   5:    */import org.jasypt.exceptions.PasswordAlreadyCleanedException;
/*   6:    */import org.jasypt.salt.SaltGenerator;
/*   7:    */
/*  66:    */public class SimplePBEConfig
/*  67:    */  implements PBEConfig, PBECleanablePasswordConfig
/*  68:    */{
/*  69: 69 */  private String algorithm = null;
/*  70: 70 */  private char[] password = null;
/*  71: 71 */  private Integer keyObtentionIterations = null;
/*  72: 72 */  private SaltGenerator saltGenerator = null;
/*  73: 73 */  private String providerName = null;
/*  74: 74 */  private Provider provider = null;
/*  75: 75 */  private Integer poolSize = null;
/*  76:    */  
/*  77: 77 */  private boolean passwordCleaned = false;
/*  78:    */  
/* 104:    */  public void setAlgorithm(String algorithm)
/* 105:    */  {
/* 106:106 */    this.algorithm = algorithm;
/* 107:    */  }
/* 108:    */  
/* 118:    */  public void setPassword(String password)
/* 119:    */  {
/* 120:120 */    if (this.password != null)
/* 121:    */    {
/* 122:122 */      cleanPassword();
/* 123:    */    }
/* 124:124 */    if (password == null) {
/* 125:125 */      this.password = null;
/* 126:    */    } else {
/* 127:127 */      this.password = password.toCharArray();
/* 128:    */    }
/* 129:    */  }
/* 130:    */  
/* 154:    */  public void setPasswordCharArray(char[] password)
/* 155:    */  {
/* 156:156 */    if (this.password != null)
/* 157:    */    {
/* 158:158 */      cleanPassword();
/* 159:    */    }
/* 160:160 */    if (password == null) {
/* 161:161 */      this.password = null;
/* 162:    */    } else {
/* 163:163 */      this.password = new char[password.length];
/* 164:164 */      System.arraycopy(password, 0, this.password, 0, password.length);
/* 165:    */    }
/* 166:    */  }
/* 167:    */  
/* 177:    */  public void setKeyObtentionIterations(Integer keyObtentionIterations)
/* 178:    */  {
/* 179:179 */    this.keyObtentionIterations = keyObtentionIterations;
/* 180:    */  }
/* 181:    */  
/* 193:    */  public void setKeyObtentionIterations(String keyObtentionIterations)
/* 194:    */  {
/* 195:195 */    if (keyObtentionIterations != null) {
/* 196:    */      try {
/* 197:197 */        this.keyObtentionIterations = new Integer(keyObtentionIterations);
/* 198:    */      } catch (NumberFormatException e) {
/* 199:199 */        throw new EncryptionInitializationException(e);
/* 200:    */      }
/* 201:    */    } else {
/* 202:202 */      this.keyObtentionIterations = null;
/* 203:    */    }
/* 204:    */  }
/* 205:    */  
/* 219:    */  public void setSaltGenerator(SaltGenerator saltGenerator)
/* 220:    */  {
/* 221:221 */    this.saltGenerator = saltGenerator;
/* 222:    */  }
/* 223:    */  
/* 239:    */  public void setSaltGeneratorClassName(String saltGeneratorClassName)
/* 240:    */  {
/* 241:241 */    if (saltGeneratorClassName != null) {
/* 242:    */      try {
/* 243:243 */        Class saltGeneratorClass = Thread.currentThread().getContextClassLoader().loadClass(saltGeneratorClassName);
/* 244:    */        
/* 245:245 */        this.saltGenerator = ((SaltGenerator)saltGeneratorClass.newInstance());
/* 246:    */      }
/* 247:    */      catch (Exception e) {
/* 248:248 */        throw new EncryptionInitializationException(e);
/* 249:    */      }
/* 250:    */    } else {
/* 251:251 */      this.saltGenerator = null;
/* 252:    */    }
/* 253:    */  }
/* 254:    */  
/* 276:    */  public void setProviderName(String providerName)
/* 277:    */  {
/* 278:278 */    this.providerName = providerName;
/* 279:    */  }
/* 280:    */  
/* 308:    */  public void setProvider(Provider provider)
/* 309:    */  {
/* 310:310 */    this.provider = provider;
/* 311:    */  }
/* 312:    */  
/* 339:    */  public void setProviderClassName(String providerClassName)
/* 340:    */  {
/* 341:341 */    if (providerClassName != null) {
/* 342:    */      try {
/* 343:343 */        Class providerClass = Thread.currentThread().getContextClassLoader().loadClass(providerClassName);
/* 344:    */        
/* 345:345 */        this.provider = ((Provider)providerClass.newInstance());
/* 346:    */      } catch (Exception e) {
/* 347:347 */        throw new EncryptionInitializationException(e);
/* 348:    */      }
/* 349:    */    } else {
/* 350:350 */      this.provider = null;
/* 351:    */    }
/* 352:    */  }
/* 353:    */  
/* 376:    */  public void setPoolSize(Integer poolSize)
/* 377:    */  {
/* 378:378 */    this.poolSize = poolSize;
/* 379:    */  }
/* 380:    */  
/* 400:    */  public void setPoolSize(String poolSize)
/* 401:    */  {
/* 402:402 */    if (poolSize != null) {
/* 403:    */      try {
/* 404:404 */        this.poolSize = new Integer(poolSize);
/* 405:    */      } catch (NumberFormatException e) {
/* 406:406 */        throw new EncryptionInitializationException(e);
/* 407:    */      }
/* 408:    */    } else {
/* 409:409 */      this.poolSize = null;
/* 410:    */    }
/* 411:    */  }
/* 412:    */  
/* 416:    */  public String getAlgorithm()
/* 417:    */  {
/* 418:418 */    return this.algorithm;
/* 419:    */  }
/* 420:    */  
/* 421:    */  public String getPassword()
/* 422:    */  {
/* 423:423 */    if (this.passwordCleaned) {
/* 424:424 */      throw new PasswordAlreadyCleanedException();
/* 425:    */    }
/* 426:426 */    return new String(this.password);
/* 427:    */  }
/* 428:    */  
/* 429:    */  public char[] getPasswordCharArray()
/* 430:    */  {
/* 431:431 */    if (this.passwordCleaned) {
/* 432:432 */      throw new PasswordAlreadyCleanedException();
/* 433:    */    }
/* 434:434 */    char[] result = new char[this.password.length];
/* 435:435 */    System.arraycopy(this.password, 0, result, 0, this.password.length);
/* 436:436 */    return result;
/* 437:    */  }
/* 438:    */  
/* 439:    */  public Integer getKeyObtentionIterations()
/* 440:    */  {
/* 441:441 */    return this.keyObtentionIterations;
/* 442:    */  }
/* 443:    */  
/* 444:    */  public SaltGenerator getSaltGenerator()
/* 445:    */  {
/* 446:446 */    return this.saltGenerator;
/* 447:    */  }
/* 448:    */  
/* 449:    */  public String getProviderName() {
/* 450:450 */    return this.providerName;
/* 451:    */  }
/* 452:    */  
/* 453:    */  public Provider getProvider() {
/* 454:454 */    return this.provider;
/* 455:    */  }
/* 456:    */  
/* 457:    */  public Integer getPoolSize() {
/* 458:458 */    return this.poolSize;
/* 459:    */  }
/* 460:    */  
/* 462:    */  public void cleanPassword()
/* 463:    */  {
/* 464:464 */    if (this.password != null) {
/* 465:465 */      int pwdLength = this.password.length;
/* 466:466 */      for (int i = 0; i < pwdLength; i++) {
/* 467:467 */        this.password[i] = '\000';
/* 468:    */      }
/* 469:469 */      this.password = new char[0];
/* 470:    */    }
/* 471:471 */    this.passwordCleaned = true;
/* 472:    */  }
/* 473:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.SimplePBEConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */