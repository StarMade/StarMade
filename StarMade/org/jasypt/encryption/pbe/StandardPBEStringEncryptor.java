/*   1:    */package org.jasypt.encryption.pbe;
/*   2:    */
/*   3:    */import java.security.Provider;
/*   4:    */import org.jasypt.commons.CommonUtils;
/*   5:    */import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
/*   6:    */import org.jasypt.encryption.pbe.config.PBEConfig;
/*   7:    */import org.jasypt.encryption.pbe.config.StringPBEConfig;
/*   8:    */import org.jasypt.exceptions.AlreadyInitializedException;
/*   9:    */import org.jasypt.exceptions.EncryptionInitializationException;
/*  10:    */import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*  11:    */import org.jasypt.salt.SaltGenerator;
/*  12:    */
/* 175:    */public final class StandardPBEStringEncryptor
/* 176:    */  implements PBEStringCleanablePasswordEncryptor
/* 177:    */{
/* 178:    */  private static final String MESSAGE_CHARSET = "UTF-8";
/* 179:    */  private static final String ENCRYPTED_MESSAGE_CHARSET = "US-ASCII";
/* 180:    */  public static final String DEFAULT_STRING_OUTPUT_TYPE = "base64";
/* 181:181 */  private StringPBEConfig stringPBEConfig = null;
/* 182:    */  
/* 185:185 */  private String stringOutputType = "base64";
/* 186:186 */  private boolean stringOutputTypeBase64 = true;
/* 187:    */  
/* 194:194 */  private boolean stringOutputTypeSet = false;
/* 195:    */  
/* 199:    */  private final StandardPBEByteEncryptor byteEncryptor;
/* 200:    */  
/* 204:    */  private final Base64 base64;
/* 205:    */  
/* 210:    */  public StandardPBEStringEncryptor()
/* 211:    */  {
/* 212:212 */    this.byteEncryptor = new StandardPBEByteEncryptor();
/* 213:213 */    this.base64 = new Base64();
/* 214:    */  }
/* 215:    */  
/* 222:    */  private StandardPBEStringEncryptor(StandardPBEByteEncryptor standardPBEByteEncryptor)
/* 223:    */  {
/* 224:224 */    this.byteEncryptor = standardPBEByteEncryptor;
/* 225:225 */    this.base64 = new Base64();
/* 226:    */  }
/* 227:    */  
/* 255:    */  public synchronized void setConfig(PBEConfig config)
/* 256:    */  {
/* 257:257 */    this.byteEncryptor.setConfig(config);
/* 258:258 */    if ((config != null) && ((config instanceof StringPBEConfig))) {
/* 259:259 */      this.stringPBEConfig = ((StringPBEConfig)config);
/* 260:    */    }
/* 261:    */  }
/* 262:    */  
/* 277:    */  public void setAlgorithm(String algorithm)
/* 278:    */  {
/* 279:279 */    this.byteEncryptor.setAlgorithm(algorithm);
/* 280:    */  }
/* 281:    */  
/* 296:    */  public void setPassword(String password)
/* 297:    */  {
/* 298:298 */    this.byteEncryptor.setPassword(password);
/* 299:    */  }
/* 300:    */  
/* 329:    */  public void setPasswordCharArray(char[] password)
/* 330:    */  {
/* 331:331 */    this.byteEncryptor.setPasswordCharArray(password);
/* 332:    */  }
/* 333:    */  
/* 347:    */  public void setKeyObtentionIterations(int keyObtentionIterations)
/* 348:    */  {
/* 349:349 */    this.byteEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/* 350:    */  }
/* 351:    */  
/* 360:    */  public void setSaltGenerator(SaltGenerator saltGenerator)
/* 361:    */  {
/* 362:362 */    this.byteEncryptor.setSaltGenerator(saltGenerator);
/* 363:    */  }
/* 364:    */  
/* 392:    */  public void setProviderName(String providerName)
/* 393:    */  {
/* 394:394 */    this.byteEncryptor.setProviderName(providerName);
/* 395:    */  }
/* 396:    */  
/* 417:    */  public void setProvider(Provider provider)
/* 418:    */  {
/* 419:419 */    this.byteEncryptor.setProvider(provider);
/* 420:    */  }
/* 421:    */  
/* 439:    */  public synchronized void setStringOutputType(String stringOutputType)
/* 440:    */  {
/* 441:441 */    CommonUtils.validateNotEmpty(stringOutputType, "String output type cannot be set empty");
/* 442:    */    
/* 443:443 */    if (isInitialized()) {
/* 444:444 */      throw new AlreadyInitializedException();
/* 445:    */    }
/* 446:446 */    this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/* 447:    */    
/* 450:450 */    this.stringOutputTypeSet = true;
/* 451:    */  }
/* 452:    */  
/* 467:    */  synchronized StandardPBEStringEncryptor[] cloneAndInitializeEncryptor(int size)
/* 468:    */  {
/* 469:469 */    StandardPBEByteEncryptor[] byteEncryptorClones = this.byteEncryptor.cloneAndInitializeEncryptor(size);
/* 470:    */    
/* 472:472 */    initializeSpecifics();
/* 473:    */    
/* 474:474 */    StandardPBEStringEncryptor[] clones = new StandardPBEStringEncryptor[size];
/* 475:    */    
/* 476:476 */    clones[0] = this;
/* 477:    */    
/* 478:478 */    for (int i = 1; i < size; i++) {
/* 479:479 */      clones[i] = new StandardPBEStringEncryptor(byteEncryptorClones[i]);
/* 480:480 */      if (CommonUtils.isNotEmpty(this.stringOutputType)) {
/* 481:481 */        clones[i].setStringOutputType(this.stringOutputType);
/* 482:    */      }
/* 483:    */    }
/* 484:    */    
/* 485:485 */    return clones;
/* 486:    */  }
/* 487:    */  
/* 511:    */  public boolean isInitialized()
/* 512:    */  {
/* 513:513 */    return this.byteEncryptor.isInitialized();
/* 514:    */  }
/* 515:    */  
/* 549:    */  public synchronized void initialize()
/* 550:    */  {
/* 551:551 */    if (!isInitialized()) {
/* 552:552 */      initializeSpecifics();
/* 553:553 */      this.byteEncryptor.initialize();
/* 554:    */    }
/* 555:    */  }
/* 556:    */  
/* 565:    */  private void initializeSpecifics()
/* 566:    */  {
/* 567:567 */    if (this.stringPBEConfig != null)
/* 568:    */    {
/* 569:569 */      String configStringOutputType = this.stringPBEConfig.getStringOutputType();
/* 570:    */      
/* 572:572 */      this.stringOutputType = ((this.stringOutputTypeSet) || (configStringOutputType == null) ? this.stringOutputType : configStringOutputType);
/* 573:    */    }
/* 574:    */    
/* 578:578 */    this.stringOutputTypeBase64 = "base64".equalsIgnoreCase(this.stringOutputType);
/* 579:    */  }
/* 580:    */  
/* 623:    */  public String encrypt(String message)
/* 624:    */  {
/* 625:625 */    if (message == null) {
/* 626:626 */      return null;
/* 627:    */    }
/* 628:    */    
/* 630:630 */    if (!isInitialized()) {
/* 631:631 */      initialize();
/* 632:    */    }
/* 633:    */    
/* 637:    */    try
/* 638:    */    {
/* 639:639 */      byte[] messageBytes = message.getBytes("UTF-8");
/* 640:    */      
/* 642:642 */      byte[] encryptedMessage = this.byteEncryptor.encrypt(messageBytes);
/* 643:    */      
/* 646:646 */      String result = null;
/* 647:647 */      if (this.stringOutputTypeBase64) {
/* 648:648 */        encryptedMessage = this.base64.encode(encryptedMessage);
/* 649:649 */        result = new String(encryptedMessage, "US-ASCII");
/* 650:    */      }
/* 651:651 */      return CommonUtils.toHexadecimal(encryptedMessage);
/* 653:    */    }
/* 654:    */    catch (EncryptionInitializationException e)
/* 655:    */    {
/* 657:657 */      throw e;
/* 658:    */    } catch (EncryptionOperationNotPossibleException e) {
/* 659:659 */      throw e;
/* 660:    */    }
/* 661:    */    catch (Exception e)
/* 662:    */    {
/* 663:663 */      throw new EncryptionOperationNotPossibleException();
/* 664:    */    }
/* 665:    */  }
/* 666:    */  
/* 697:    */  public String decrypt(String encryptedMessage)
/* 698:    */  {
/* 699:699 */    if (encryptedMessage == null) {
/* 700:700 */      return null;
/* 701:    */    }
/* 702:    */    
/* 704:704 */    if (!isInitialized()) {
/* 705:705 */      initialize();
/* 706:    */    }
/* 707:    */    
/* 708:    */    try
/* 709:    */    {
/* 710:710 */      byte[] encryptedMessageBytes = null;
/* 711:    */      
/* 714:714 */      if (this.stringOutputTypeBase64) {
/* 715:715 */        encryptedMessageBytes = encryptedMessage.getBytes("US-ASCII");
/* 716:    */        
/* 717:717 */        encryptedMessageBytes = this.base64.decode(encryptedMessageBytes);
/* 718:    */      }
/* 719:    */      else {
/* 720:720 */        encryptedMessageBytes = CommonUtils.fromHexadecimal(encryptedMessage);
/* 721:    */      }
/* 722:    */      
/* 725:725 */      byte[] message = this.byteEncryptor.decrypt(encryptedMessageBytes);
/* 726:    */      
/* 730:730 */      return new String(message, "UTF-8");
/* 731:    */    }
/* 732:    */    catch (EncryptionInitializationException e) {
/* 733:733 */      throw e;
/* 734:    */    } catch (EncryptionOperationNotPossibleException e) {
/* 735:735 */      throw e;
/* 736:    */    }
/* 737:    */    catch (Exception e)
/* 738:    */    {
/* 739:739 */      throw new EncryptionOperationNotPossibleException();
/* 740:    */    }
/* 741:    */  }
/* 742:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.StandardPBEStringEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */