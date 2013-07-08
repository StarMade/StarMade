/*   1:    */package org.jasypt.encryption.pbe;
/*   2:    */
/*   3:    */import java.math.BigInteger;
/*   4:    */import java.security.Provider;
/*   5:    */import org.jasypt.commons.CommonUtils;
/*   6:    */import org.jasypt.encryption.pbe.config.PBEConfig;
/*   7:    */import org.jasypt.exceptions.EncryptionInitializationException;
/*   8:    */import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*   9:    */import org.jasypt.salt.SaltGenerator;
/*  10:    */
/* 132:    */public final class StandardPBEBigIntegerEncryptor
/* 133:    */  implements PBEBigIntegerCleanablePasswordEncryptor
/* 134:    */{
/* 135:    */  private final StandardPBEByteEncryptor byteEncryptor;
/* 136:    */  
/* 137:    */  public StandardPBEBigIntegerEncryptor()
/* 138:    */  {
/* 139:139 */    this.byteEncryptor = new StandardPBEByteEncryptor();
/* 140:    */  }
/* 141:    */  
/* 148:    */  private StandardPBEBigIntegerEncryptor(StandardPBEByteEncryptor standardPBEByteEncryptor)
/* 149:    */  {
/* 150:150 */    this.byteEncryptor = standardPBEByteEncryptor;
/* 151:    */  }
/* 152:    */  
/* 178:    */  public void setConfig(PBEConfig config)
/* 179:    */  {
/* 180:180 */    this.byteEncryptor.setConfig(config);
/* 181:    */  }
/* 182:    */  
/* 197:    */  public void setAlgorithm(String algorithm)
/* 198:    */  {
/* 199:199 */    this.byteEncryptor.setAlgorithm(algorithm);
/* 200:    */  }
/* 201:    */  
/* 216:    */  public void setPassword(String password)
/* 217:    */  {
/* 218:218 */    this.byteEncryptor.setPassword(password);
/* 219:    */  }
/* 220:    */  
/* 249:    */  public void setPasswordCharArray(char[] password)
/* 250:    */  {
/* 251:251 */    this.byteEncryptor.setPasswordCharArray(password);
/* 252:    */  }
/* 253:    */  
/* 267:    */  public void setKeyObtentionIterations(int keyObtentionIterations)
/* 268:    */  {
/* 269:269 */    this.byteEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/* 270:    */  }
/* 271:    */  
/* 280:    */  public void setSaltGenerator(SaltGenerator saltGenerator)
/* 281:    */  {
/* 282:282 */    this.byteEncryptor.setSaltGenerator(saltGenerator);
/* 283:    */  }
/* 284:    */  
/* 312:    */  public void setProviderName(String providerName)
/* 313:    */  {
/* 314:314 */    this.byteEncryptor.setProviderName(providerName);
/* 315:    */  }
/* 316:    */  
/* 337:    */  public void setProvider(Provider provider)
/* 338:    */  {
/* 339:339 */    this.byteEncryptor.setProvider(provider);
/* 340:    */  }
/* 341:    */  
/* 356:    */  synchronized StandardPBEBigIntegerEncryptor[] cloneAndInitializeEncryptor(int size)
/* 357:    */  {
/* 358:358 */    StandardPBEByteEncryptor[] byteEncryptorClones = this.byteEncryptor.cloneAndInitializeEncryptor(size);
/* 359:    */    
/* 361:361 */    StandardPBEBigIntegerEncryptor[] clones = new StandardPBEBigIntegerEncryptor[size];
/* 362:    */    
/* 363:363 */    clones[0] = this;
/* 364:    */    
/* 365:365 */    for (int i = 1; i < size; i++) {
/* 366:366 */      clones[i] = new StandardPBEBigIntegerEncryptor(byteEncryptorClones[i]);
/* 367:    */    }
/* 368:    */    
/* 369:369 */    return clones;
/* 370:    */  }
/* 371:    */  
/* 395:    */  public boolean isInitialized()
/* 396:    */  {
/* 397:397 */    return this.byteEncryptor.isInitialized();
/* 398:    */  }
/* 399:    */  
/* 431:    */  public void initialize()
/* 432:    */  {
/* 433:433 */    this.byteEncryptor.initialize();
/* 434:    */  }
/* 435:    */  
/* 479:    */  public BigInteger encrypt(BigInteger message)
/* 480:    */  {
/* 481:481 */    if (message == null) {
/* 482:482 */      return null;
/* 483:    */    }
/* 484:    */    
/* 486:    */    try
/* 487:    */    {
/* 488:488 */      byte[] messageBytes = message.toByteArray();
/* 489:    */      
/* 491:491 */      byte[] encryptedMessage = this.byteEncryptor.encrypt(messageBytes);
/* 492:    */      
/* 498:498 */      byte[] encryptedMessageLengthBytes = NumberUtils.byteArrayFromInt(encryptedMessage.length);
/* 499:    */      
/* 502:502 */      byte[] encryptionResult = CommonUtils.appendArrays(encryptedMessage, encryptedMessageLengthBytes);
/* 503:    */      
/* 506:506 */      return new BigInteger(encryptionResult);
/* 507:    */    }
/* 508:    */    catch (EncryptionInitializationException e) {
/* 509:509 */      throw e;
/* 510:    */    } catch (EncryptionOperationNotPossibleException e) {
/* 511:511 */      throw e;
/* 512:    */    }
/* 513:    */    catch (Exception e)
/* 514:    */    {
/* 515:515 */      throw new EncryptionOperationNotPossibleException();
/* 516:    */    }
/* 517:    */  }
/* 518:    */  
/* 545:    */  public BigInteger decrypt(BigInteger encryptedMessage)
/* 546:    */  {
/* 547:547 */    if (encryptedMessage == null) {
/* 548:548 */      return null;
/* 549:    */    }
/* 550:    */    
/* 552:    */    try
/* 553:    */    {
/* 554:554 */      byte[] encryptedMessageBytes = encryptedMessage.toByteArray();
/* 555:    */      
/* 557:557 */      encryptedMessageBytes = NumberUtils.processBigIntegerEncryptedByteArray(encryptedMessageBytes, encryptedMessage.signum());
/* 558:    */      
/* 562:562 */      byte[] message = this.byteEncryptor.decrypt(encryptedMessageBytes);
/* 563:    */      
/* 565:565 */      return new BigInteger(message);
/* 566:    */    }
/* 567:    */    catch (EncryptionInitializationException e) {
/* 568:568 */      throw e;
/* 569:    */    } catch (EncryptionOperationNotPossibleException e) {
/* 570:570 */      throw e;
/* 571:    */    }
/* 572:    */    catch (Exception e)
/* 573:    */    {
/* 574:574 */      throw new EncryptionOperationNotPossibleException();
/* 575:    */    }
/* 576:    */  }
/* 577:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */