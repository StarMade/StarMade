/*   1:    */package org.jasypt.encryption.pbe;
/*   2:    */
/*   3:    */import java.math.BigDecimal;
/*   4:    */import java.math.BigInteger;
/*   5:    */import java.security.Provider;
/*   6:    */import org.jasypt.commons.CommonUtils;
/*   7:    */import org.jasypt.encryption.pbe.config.PBEConfig;
/*   8:    */import org.jasypt.exceptions.EncryptionInitializationException;
/*   9:    */import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*  10:    */import org.jasypt.salt.SaltGenerator;
/*  11:    */
/* 133:    */public final class StandardPBEBigDecimalEncryptor
/* 134:    */  implements PBEBigDecimalCleanablePasswordEncryptor
/* 135:    */{
/* 136:    */  private final StandardPBEByteEncryptor byteEncryptor;
/* 137:    */  
/* 138:    */  public StandardPBEBigDecimalEncryptor()
/* 139:    */  {
/* 140:140 */    this.byteEncryptor = new StandardPBEByteEncryptor();
/* 141:    */  }
/* 142:    */  
/* 149:    */  private StandardPBEBigDecimalEncryptor(StandardPBEByteEncryptor standardPBEByteEncryptor)
/* 150:    */  {
/* 151:151 */    this.byteEncryptor = standardPBEByteEncryptor;
/* 152:    */  }
/* 153:    */  
/* 179:    */  public void setConfig(PBEConfig config)
/* 180:    */  {
/* 181:181 */    this.byteEncryptor.setConfig(config);
/* 182:    */  }
/* 183:    */  
/* 198:    */  public void setAlgorithm(String algorithm)
/* 199:    */  {
/* 200:200 */    this.byteEncryptor.setAlgorithm(algorithm);
/* 201:    */  }
/* 202:    */  
/* 217:    */  public void setPassword(String password)
/* 218:    */  {
/* 219:219 */    this.byteEncryptor.setPassword(password);
/* 220:    */  }
/* 221:    */  
/* 250:    */  public void setPasswordCharArray(char[] password)
/* 251:    */  {
/* 252:252 */    this.byteEncryptor.setPasswordCharArray(password);
/* 253:    */  }
/* 254:    */  
/* 268:    */  public void setKeyObtentionIterations(int keyObtentionIterations)
/* 269:    */  {
/* 270:270 */    this.byteEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/* 271:    */  }
/* 272:    */  
/* 281:    */  public void setSaltGenerator(SaltGenerator saltGenerator)
/* 282:    */  {
/* 283:283 */    this.byteEncryptor.setSaltGenerator(saltGenerator);
/* 284:    */  }
/* 285:    */  
/* 313:    */  public void setProviderName(String providerName)
/* 314:    */  {
/* 315:315 */    this.byteEncryptor.setProviderName(providerName);
/* 316:    */  }
/* 317:    */  
/* 338:    */  public void setProvider(Provider provider)
/* 339:    */  {
/* 340:340 */    this.byteEncryptor.setProvider(provider);
/* 341:    */  }
/* 342:    */  
/* 357:    */  synchronized StandardPBEBigDecimalEncryptor[] cloneAndInitializeEncryptor(int size)
/* 358:    */  {
/* 359:359 */    StandardPBEByteEncryptor[] byteEncryptorClones = this.byteEncryptor.cloneAndInitializeEncryptor(size);
/* 360:    */    
/* 362:362 */    StandardPBEBigDecimalEncryptor[] clones = new StandardPBEBigDecimalEncryptor[size];
/* 363:    */    
/* 364:364 */    clones[0] = this;
/* 365:    */    
/* 366:366 */    for (int i = 1; i < size; i++) {
/* 367:367 */      clones[i] = new StandardPBEBigDecimalEncryptor(byteEncryptorClones[i]);
/* 368:    */    }
/* 369:    */    
/* 370:370 */    return clones;
/* 371:    */  }
/* 372:    */  
/* 396:    */  public boolean isInitialized()
/* 397:    */  {
/* 398:398 */    return this.byteEncryptor.isInitialized();
/* 399:    */  }
/* 400:    */  
/* 432:    */  public void initialize()
/* 433:    */  {
/* 434:434 */    this.byteEncryptor.initialize();
/* 435:    */  }
/* 436:    */  
/* 485:    */  public BigDecimal encrypt(BigDecimal message)
/* 486:    */  {
/* 487:487 */    if (message == null) {
/* 488:488 */      return null;
/* 489:    */    }
/* 490:    */    
/* 492:    */    try
/* 493:    */    {
/* 494:494 */      int scale = message.scale();
/* 495:    */      
/* 497:497 */      BigInteger unscaledMessage = message.unscaledValue();
/* 498:498 */      byte[] messageBytes = unscaledMessage.toByteArray();
/* 499:    */      
/* 501:501 */      byte[] encryptedMessage = this.byteEncryptor.encrypt(messageBytes);
/* 502:    */      
/* 508:508 */      byte[] encryptedMessageLengthBytes = NumberUtils.byteArrayFromInt(encryptedMessage.length);
/* 509:    */      
/* 512:512 */      byte[] encryptionResult = CommonUtils.appendArrays(encryptedMessage, encryptedMessageLengthBytes);
/* 513:    */      
/* 516:516 */      return new BigDecimal(new BigInteger(encryptionResult), scale);
/* 517:    */    }
/* 518:    */    catch (EncryptionInitializationException e) {
/* 519:519 */      throw e;
/* 520:    */    } catch (EncryptionOperationNotPossibleException e) {
/* 521:521 */      throw e;
/* 522:    */    }
/* 523:    */    catch (Exception e)
/* 524:    */    {
/* 525:525 */      throw new EncryptionOperationNotPossibleException();
/* 526:    */    }
/* 527:    */  }
/* 528:    */  
/* 555:    */  public BigDecimal decrypt(BigDecimal encryptedMessage)
/* 556:    */  {
/* 557:557 */    if (encryptedMessage == null) {
/* 558:558 */      return null;
/* 559:    */    }
/* 560:    */    
/* 562:    */    try
/* 563:    */    {
/* 564:564 */      int scale = encryptedMessage.scale();
/* 565:    */      
/* 567:567 */      BigInteger unscaledEncryptedMessage = encryptedMessage.unscaledValue();
/* 568:    */      
/* 569:569 */      byte[] encryptedMessageBytes = unscaledEncryptedMessage.toByteArray();
/* 570:    */      
/* 573:573 */      encryptedMessageBytes = NumberUtils.processBigIntegerEncryptedByteArray(encryptedMessageBytes, encryptedMessage.signum());
/* 574:    */      
/* 578:578 */      byte[] message = this.byteEncryptor.decrypt(encryptedMessageBytes);
/* 579:    */      
/* 581:581 */      return new BigDecimal(new BigInteger(message), scale);
/* 582:    */    }
/* 583:    */    catch (EncryptionInitializationException e) {
/* 584:584 */      throw e;
/* 585:    */    } catch (EncryptionOperationNotPossibleException e) {
/* 586:586 */      throw e;
/* 587:    */    }
/* 588:    */    catch (Exception e)
/* 589:    */    {
/* 590:590 */      throw new EncryptionOperationNotPossibleException();
/* 591:    */    }
/* 592:    */  }
/* 593:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */