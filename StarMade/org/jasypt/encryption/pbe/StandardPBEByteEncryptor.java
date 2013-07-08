/*   1:    */package org.jasypt.encryption.pbe;
/*   2:    */
/*   3:    */import java.security.InvalidKeyException;
/*   4:    */import java.security.Provider;
/*   5:    */import javax.crypto.Cipher;
/*   6:    */import javax.crypto.SecretKey;
/*   7:    */import javax.crypto.SecretKeyFactory;
/*   8:    */import javax.crypto.spec.PBEKeySpec;
/*   9:    */import javax.crypto.spec.PBEParameterSpec;
/*  10:    */import org.jasypt.commons.CommonUtils;
/*  11:    */import org.jasypt.encryption.pbe.config.PBECleanablePasswordConfig;
/*  12:    */import org.jasypt.encryption.pbe.config.PBEConfig;
/*  13:    */import org.jasypt.exceptions.AlreadyInitializedException;
/*  14:    */import org.jasypt.exceptions.EncryptionInitializationException;
/*  15:    */import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*  16:    */import org.jasypt.normalization.Normalizer;
/*  17:    */import org.jasypt.salt.RandomSaltGenerator;
/*  18:    */import org.jasypt.salt.SaltGenerator;
/*  19:    */
/* 142:    */public final class StandardPBEByteEncryptor
/* 143:    */  implements PBEByteCleanablePasswordEncryptor
/* 144:    */{
/* 145:    */  public static final String DEFAULT_ALGORITHM = "PBEWithMD5AndDES";
/* 146:    */  public static final int DEFAULT_KEY_OBTENTION_ITERATIONS = 1000;
/* 147:    */  public static final int DEFAULT_SALT_SIZE_BYTES = 8;
/* 148:148 */  private String algorithm = "PBEWithMD5AndDES";
/* 149:149 */  private String providerName = null;
/* 150:150 */  private Provider provider = null;
/* 151:    */  
/* 154:154 */  private char[] password = null;
/* 155:    */  
/* 158:158 */  private int keyObtentionIterations = 1000;
/* 159:    */  
/* 163:163 */  private SaltGenerator saltGenerator = null;
/* 164:    */  
/* 170:170 */  private int saltSizeBytes = 8;
/* 171:    */  
/* 174:174 */  private PBEConfig config = null;
/* 175:    */  
/* 181:181 */  private boolean algorithmSet = false;
/* 182:182 */  private boolean passwordSet = false;
/* 183:183 */  private boolean iterationsSet = false;
/* 184:184 */  private boolean saltGeneratorSet = false;
/* 185:185 */  private boolean providerNameSet = false;
/* 186:186 */  private boolean providerSet = false;
/* 187:    */  
/* 195:195 */  private boolean initialized = false;
/* 196:    */  
/* 199:199 */  private SecretKey key = null;
/* 200:    */  
/* 202:202 */  private Cipher encryptCipher = null;
/* 203:203 */  private Cipher decryptCipher = null;
/* 204:    */  
/* 239:    */  public synchronized void setConfig(PBEConfig config)
/* 240:    */  {
/* 241:241 */    CommonUtils.validateNotNull(config, "Config cannot be set null");
/* 242:242 */    if (isInitialized()) {
/* 243:243 */      throw new AlreadyInitializedException();
/* 244:    */    }
/* 245:245 */    this.config = config;
/* 246:    */  }
/* 247:    */  
/* 262:    */  public synchronized void setAlgorithm(String algorithm)
/* 263:    */  {
/* 264:264 */    CommonUtils.validateNotEmpty(algorithm, "Algorithm cannot be set empty");
/* 265:265 */    if (isInitialized()) {
/* 266:266 */      throw new AlreadyInitializedException();
/* 267:    */    }
/* 268:268 */    this.algorithm = algorithm;
/* 269:269 */    this.algorithmSet = true;
/* 270:    */  }
/* 271:    */  
/* 286:    */  public synchronized void setPassword(String password)
/* 287:    */  {
/* 288:288 */    CommonUtils.validateNotEmpty(password, "Password cannot be set empty");
/* 289:289 */    if (isInitialized()) {
/* 290:290 */      throw new AlreadyInitializedException();
/* 291:    */    }
/* 292:292 */    if (this.password != null)
/* 293:    */    {
/* 294:294 */      cleanPassword(this.password);
/* 295:    */    }
/* 296:296 */    this.password = password.toCharArray();
/* 297:297 */    this.passwordSet = true;
/* 298:    */  }
/* 299:    */  
/* 328:    */  public synchronized void setPasswordCharArray(char[] password)
/* 329:    */  {
/* 330:330 */    CommonUtils.validateNotNull(password, "Password cannot be set null");
/* 331:331 */    CommonUtils.validateIsTrue(password.length > 0, "Password cannot be set empty");
/* 332:332 */    if (isInitialized()) {
/* 333:333 */      throw new AlreadyInitializedException();
/* 334:    */    }
/* 335:335 */    if (this.password != null)
/* 336:    */    {
/* 337:337 */      cleanPassword(this.password);
/* 338:    */    }
/* 339:339 */    this.password = new char[password.length];
/* 340:340 */    System.arraycopy(password, 0, this.password, 0, password.length);
/* 341:341 */    this.passwordSet = true;
/* 342:    */  }
/* 343:    */  
/* 358:    */  public synchronized void setKeyObtentionIterations(int keyObtentionIterations)
/* 359:    */  {
/* 360:360 */    CommonUtils.validateIsTrue(keyObtentionIterations > 0, "Number of iterations for key obtention must be greater than zero");
/* 361:    */    
/* 363:363 */    if (isInitialized()) {
/* 364:364 */      throw new AlreadyInitializedException();
/* 365:    */    }
/* 366:366 */    this.keyObtentionIterations = keyObtentionIterations;
/* 367:367 */    this.iterationsSet = true;
/* 368:    */  }
/* 369:    */  
/* 378:    */  public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
/* 379:    */  {
/* 380:380 */    CommonUtils.validateNotNull(saltGenerator, "Salt generator cannot be set null");
/* 381:381 */    if (isInitialized()) {
/* 382:382 */      throw new AlreadyInitializedException();
/* 383:    */    }
/* 384:384 */    this.saltGenerator = saltGenerator;
/* 385:385 */    this.saltGeneratorSet = true;
/* 386:    */  }
/* 387:    */  
/* 415:    */  public synchronized void setProviderName(String providerName)
/* 416:    */  {
/* 417:417 */    CommonUtils.validateNotNull(providerName, "Provider name cannot be set null");
/* 418:418 */    if (isInitialized()) {
/* 419:419 */      throw new AlreadyInitializedException();
/* 420:    */    }
/* 421:421 */    this.providerName = providerName;
/* 422:422 */    this.providerNameSet = true;
/* 423:    */  }
/* 424:    */  
/* 445:    */  public synchronized void setProvider(Provider provider)
/* 446:    */  {
/* 447:447 */    CommonUtils.validateNotNull(provider, "Provider cannot be set null");
/* 448:448 */    if (isInitialized()) {
/* 449:449 */      throw new AlreadyInitializedException();
/* 450:    */    }
/* 451:451 */    this.provider = provider;
/* 452:452 */    this.providerSet = true;
/* 453:    */  }
/* 454:    */  
/* 469:    */  synchronized StandardPBEByteEncryptor[] cloneAndInitializeEncryptor(int size)
/* 470:    */  {
/* 471:471 */    if (isInitialized()) {
/* 472:472 */      throw new EncryptionInitializationException("Cannot clone encryptor if it has been already initialized");
/* 473:    */    }
/* 474:    */    
/* 478:478 */    resolveConfigurationPassword();
/* 479:    */    
/* 480:480 */    char[] copiedPassword = new char[this.password.length];
/* 481:481 */    System.arraycopy(this.password, 0, copiedPassword, 0, this.password.length);
/* 482:    */    
/* 485:485 */    initialize();
/* 486:    */    
/* 487:487 */    StandardPBEByteEncryptor[] clones = new StandardPBEByteEncryptor[size];
/* 488:    */    
/* 489:489 */    clones[0] = this;
/* 490:    */    
/* 491:491 */    for (int i = 1; i < size; i++)
/* 492:    */    {
/* 493:493 */      StandardPBEByteEncryptor clone = new StandardPBEByteEncryptor();
/* 494:494 */      clone.setPasswordCharArray(copiedPassword);
/* 495:495 */      if (CommonUtils.isNotEmpty(this.algorithm)) {
/* 496:496 */        clone.setAlgorithm(this.algorithm);
/* 497:    */      }
/* 498:498 */      clone.setKeyObtentionIterations(this.keyObtentionIterations);
/* 499:499 */      if (this.provider != null) {
/* 500:500 */        clone.setProvider(this.provider);
/* 501:    */      }
/* 502:502 */      if (this.providerName != null) {
/* 503:503 */        clone.setProviderName(this.providerName);
/* 504:    */      }
/* 505:505 */      if (this.saltGenerator != null) {
/* 506:506 */        clone.setSaltGenerator(this.saltGenerator);
/* 507:    */      }
/* 508:    */      
/* 509:509 */      clones[i] = clone;
/* 510:    */    }
/* 511:    */    
/* 513:513 */    cleanPassword(copiedPassword);
/* 514:    */    
/* 515:515 */    return clones;
/* 516:    */  }
/* 517:    */  
/* 541:    */  public boolean isInitialized()
/* 542:    */  {
/* 543:543 */    return this.initialized;
/* 544:    */  }
/* 545:    */  
/* 579:    */  public synchronized void initialize()
/* 580:    */  {
/* 581:581 */    if (!this.initialized)
/* 582:    */    {
/* 588:588 */      if (this.config != null)
/* 589:    */      {
/* 590:590 */        resolveConfigurationPassword();
/* 591:    */        
/* 592:592 */        String configAlgorithm = this.config.getAlgorithm();
/* 593:593 */        if (configAlgorithm != null) {
/* 594:594 */          CommonUtils.validateNotEmpty(configAlgorithm, "Algorithm cannot be set empty");
/* 595:    */        }
/* 596:    */        
/* 598:598 */        Integer configKeyObtentionIterations = this.config.getKeyObtentionIterations();
/* 599:    */        
/* 600:600 */        if (configKeyObtentionIterations != null) {
/* 601:601 */          CommonUtils.validateIsTrue(configKeyObtentionIterations.intValue() > 0, "Number of iterations for key obtention must be greater than zero");
/* 602:    */        }
/* 603:    */        
/* 606:606 */        SaltGenerator configSaltGenerator = this.config.getSaltGenerator();
/* 607:    */        
/* 608:608 */        String configProviderName = this.config.getProviderName();
/* 609:609 */        if (configProviderName != null) {
/* 610:610 */          CommonUtils.validateNotEmpty(configProviderName, "Provider name cannot be empty");
/* 611:    */        }
/* 612:    */        
/* 614:614 */        Provider configProvider = this.config.getProvider();
/* 615:    */        
/* 616:616 */        this.algorithm = ((this.algorithmSet) || (configAlgorithm == null) ? this.algorithm : configAlgorithm);
/* 617:    */        
/* 619:619 */        this.keyObtentionIterations = ((this.iterationsSet) || (configKeyObtentionIterations == null) ? this.keyObtentionIterations : configKeyObtentionIterations.intValue());
/* 620:    */        
/* 624:624 */        this.saltGenerator = ((this.saltGeneratorSet) || (configSaltGenerator == null) ? this.saltGenerator : configSaltGenerator);
/* 625:    */        
/* 627:627 */        this.providerName = ((this.providerNameSet) || (configProviderName == null) ? this.providerName : configProviderName);
/* 628:    */        
/* 630:630 */        this.provider = ((this.providerSet) || (configProvider == null) ? this.provider : configProvider);
/* 631:    */      }
/* 632:    */      
/* 640:640 */      if (this.saltGenerator == null) {
/* 641:641 */        this.saltGenerator = new RandomSaltGenerator();
/* 642:    */      }
/* 643:    */      
/* 645:    */      try
/* 646:    */      {
/* 647:647 */        if (this.password == null) {
/* 648:648 */          throw new EncryptionInitializationException("Password not set for Password Based Encryptor");
/* 649:    */        }
/* 650:    */        
/* 653:653 */        char[] normalizedPassword = Normalizer.normalizeToNfc(this.password);
/* 654:    */        
/* 658:658 */        PBEKeySpec pbeKeySpec = new PBEKeySpec(normalizedPassword);
/* 659:    */        
/* 661:661 */        cleanPassword(this.password);
/* 662:662 */        cleanPassword(normalizedPassword);
/* 663:    */        
/* 665:665 */        if (this.provider != null)
/* 666:    */        {
/* 667:667 */          SecretKeyFactory factory = SecretKeyFactory.getInstance(this.algorithm, this.provider);
/* 668:    */          
/* 672:672 */          this.key = factory.generateSecret(pbeKeySpec);
/* 673:    */          
/* 674:674 */          this.encryptCipher = Cipher.getInstance(this.algorithm, this.provider);
/* 675:    */          
/* 676:676 */          this.decryptCipher = Cipher.getInstance(this.algorithm, this.provider);
/* 678:    */        }
/* 679:679 */        else if (this.providerName != null)
/* 680:    */        {
/* 681:681 */          SecretKeyFactory factory = SecretKeyFactory.getInstance(this.algorithm, this.providerName);
/* 682:    */          
/* 686:686 */          this.key = factory.generateSecret(pbeKeySpec);
/* 687:    */          
/* 688:688 */          this.encryptCipher = Cipher.getInstance(this.algorithm, this.providerName);
/* 689:    */          
/* 690:690 */          this.decryptCipher = Cipher.getInstance(this.algorithm, this.providerName);
/* 692:    */        }
/* 693:    */        else
/* 694:    */        {
/* 695:695 */          SecretKeyFactory factory = SecretKeyFactory.getInstance(this.algorithm);
/* 696:    */          
/* 698:698 */          this.key = factory.generateSecret(pbeKeySpec);
/* 699:    */          
/* 700:700 */          this.encryptCipher = Cipher.getInstance(this.algorithm);
/* 701:701 */          this.decryptCipher = Cipher.getInstance(this.algorithm);
/* 702:    */        }
/* 703:    */      }
/* 704:    */      catch (EncryptionInitializationException e)
/* 705:    */      {
/* 706:706 */        throw e;
/* 707:    */      } catch (Throwable t) {
/* 708:708 */        throw new EncryptionInitializationException(t);
/* 709:    */      }
/* 710:    */      
/* 714:714 */      int algorithmBlockSize = this.encryptCipher.getBlockSize();
/* 715:715 */      if (algorithmBlockSize > 0) {
/* 716:716 */        this.saltSizeBytes = algorithmBlockSize;
/* 717:    */      }
/* 718:    */      
/* 720:720 */      this.initialized = true;
/* 721:    */    }
/* 722:    */  }
/* 723:    */  
/* 728:    */  private synchronized void resolveConfigurationPassword()
/* 729:    */  {
/* 730:730 */    if (!this.initialized)
/* 731:    */    {
/* 732:732 */      if ((this.config != null) && (!this.passwordSet))
/* 733:    */      {
/* 738:738 */        char[] configPassword = null;
/* 739:739 */        if ((this.config instanceof PBECleanablePasswordConfig)) {
/* 740:740 */          configPassword = ((PBECleanablePasswordConfig)this.config).getPasswordCharArray();
/* 741:    */        } else {
/* 742:742 */          String configPwd = this.config.getPassword();
/* 743:743 */          if (configPwd != null) {
/* 744:744 */            configPassword = configPwd.toCharArray();
/* 745:    */          }
/* 746:    */        }
/* 747:    */        
/* 748:748 */        if (configPassword != null) {
/* 749:749 */          CommonUtils.validateIsTrue(configPassword.length > 0, "Password cannot be set empty");
/* 750:    */        }
/* 751:    */        
/* 753:753 */        if (configPassword != null) {
/* 754:754 */          this.password = new char[configPassword.length];
/* 755:755 */          System.arraycopy(configPassword, 0, this.password, 0, configPassword.length);
/* 756:756 */          this.passwordSet = true;
/* 757:757 */          cleanPassword(configPassword);
/* 758:    */        }
/* 759:    */        
/* 761:761 */        if ((this.config instanceof PBECleanablePasswordConfig)) {
/* 762:762 */          ((PBECleanablePasswordConfig)this.config).cleanPassword();
/* 763:    */        }
/* 764:    */      }
/* 765:    */    }
/* 766:    */  }
/* 767:    */  
/* 773:    */  private static void cleanPassword(char[] password)
/* 774:    */  {
/* 775:775 */    if (password != null) {
/* 776:776 */      synchronized (password) {
/* 777:777 */        int pwdLength = password.length;
/* 778:778 */        for (int i = 0; i < pwdLength; i++) {
/* 779:779 */          password[i] = '\000';
/* 780:    */        }
/* 781:    */      }
/* 782:    */    }
/* 783:    */  }
/* 784:    */  
/* 821:    */  public byte[] encrypt(byte[] message)
/* 822:    */    throws EncryptionOperationNotPossibleException
/* 823:    */  {
/* 824:824 */    if (message == null) {
/* 825:825 */      return null;
/* 826:    */    }
/* 827:    */    
/* 829:829 */    if (!isInitialized()) {
/* 830:830 */      initialize();
/* 831:    */    }
/* 832:    */    
/* 834:    */    try
/* 835:    */    {
/* 836:836 */      byte[] salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
/* 837:    */      
/* 842:842 */      PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, this.keyObtentionIterations);
/* 843:    */      
/* 845:845 */      byte[] encryptedMessage = null;
/* 846:846 */      synchronized (this.encryptCipher) {
/* 847:847 */        this.encryptCipher.init(1, this.key, parameterSpec);
/* 848:    */        
/* 849:849 */        encryptedMessage = this.encryptCipher.doFinal(message);
/* 850:    */      }
/* 851:    */      
/* 855:855 */      if (this.saltGenerator.includePlainSaltInEncryptionResults())
/* 856:    */      {
/* 858:858 */        return CommonUtils.appendArrays(salt, encryptedMessage);
/* 859:    */      }
/* 860:    */      
/* 863:863 */      return encryptedMessage;
/* 865:    */    }
/* 866:    */    catch (InvalidKeyException e)
/* 867:    */    {
/* 868:868 */      handleInvalidKeyException(e);
/* 869:869 */      throw new EncryptionOperationNotPossibleException();
/* 870:    */    }
/* 871:    */    catch (Exception e)
/* 872:    */    {
/* 873:873 */      throw new EncryptionOperationNotPossibleException();
/* 874:    */    }
/* 875:    */  }
/* 876:    */  
/* 903:    */  public byte[] decrypt(byte[] encryptedMessage)
/* 904:    */    throws EncryptionOperationNotPossibleException
/* 905:    */  {
/* 906:906 */    if (encryptedMessage == null) {
/* 907:907 */      return null;
/* 908:    */    }
/* 909:    */    
/* 911:911 */    if (!isInitialized()) {
/* 912:912 */      initialize();
/* 913:    */    }
/* 914:    */    
/* 915:915 */    if (this.saltGenerator.includePlainSaltInEncryptionResults())
/* 916:    */    {
/* 917:917 */      if (encryptedMessage.length <= this.saltSizeBytes) {
/* 918:918 */        throw new EncryptionOperationNotPossibleException();
/* 919:    */      }
/* 920:    */    }
/* 921:    */    
/* 926:    */    try
/* 927:    */    {
/* 928:928 */      byte[] salt = null;
/* 929:929 */      byte[] encryptedMessageKernel = null;
/* 930:930 */      if (this.saltGenerator.includePlainSaltInEncryptionResults())
/* 931:    */      {
/* 932:932 */        int saltStart = 0;
/* 933:933 */        int saltSize = this.saltSizeBytes < encryptedMessage.length ? this.saltSizeBytes : encryptedMessage.length;
/* 934:    */        
/* 935:935 */        int encMesKernelStart = this.saltSizeBytes < encryptedMessage.length ? this.saltSizeBytes : encryptedMessage.length;
/* 936:    */        
/* 937:937 */        int encMesKernelSize = this.saltSizeBytes < encryptedMessage.length ? encryptedMessage.length - this.saltSizeBytes : 0;
/* 938:    */        
/* 940:940 */        salt = new byte[saltSize];
/* 941:941 */        encryptedMessageKernel = new byte[encMesKernelSize];
/* 942:    */        
/* 943:943 */        System.arraycopy(encryptedMessage, 0, salt, 0, saltSize);
/* 944:944 */        System.arraycopy(encryptedMessage, encMesKernelStart, encryptedMessageKernel, 0, encMesKernelSize);
/* 945:    */      }
/* 946:    */      else
/* 947:    */      {
/* 948:948 */        salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
/* 949:949 */        encryptedMessageKernel = encryptedMessage;
/* 950:    */      }
/* 951:    */      
/* 957:957 */      PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, this.keyObtentionIterations);
/* 958:    */      
/* 960:960 */      byte[] decryptedMessage = null;
/* 961:    */      
/* 962:962 */      synchronized (this.decryptCipher) {
/* 963:963 */        this.decryptCipher.init(2, this.key, parameterSpec);
/* 964:    */        
/* 965:965 */        decryptedMessage = this.decryptCipher.doFinal(encryptedMessageKernel);
/* 966:    */      }
/* 967:    */      
/* 970:970 */      return decryptedMessage;
/* 972:    */    }
/* 973:    */    catch (InvalidKeyException e)
/* 974:    */    {
/* 976:976 */      handleInvalidKeyException(e);
/* 977:977 */      throw new EncryptionOperationNotPossibleException();
/* 978:    */    }
/* 979:    */    catch (Exception e)
/* 980:    */    {
/* 981:981 */      throw new EncryptionOperationNotPossibleException();
/* 982:    */    }
/* 983:    */  }
/* 984:    */  
/* 994:    */  private void handleInvalidKeyException(InvalidKeyException e)
/* 995:    */  {
/* 996:996 */    if ((e.getMessage() != null) && (e.getMessage().toUpperCase().indexOf("KEY SIZE") != -1))
/* 997:    */    {
/* 999:999 */      throw new EncryptionOperationNotPossibleException("Encryption raised an exception. A possible cause is you are using strong encryption algorithms and you have not installed the Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files in this Java Virtual Machine");
/* 1000:    */    }
/* 1001:    */  }
/* 1002:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.StandardPBEByteEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */