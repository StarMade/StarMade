/*    1:     */package org.jasypt.digest;
/*    2:     */
/*    3:     */import java.security.MessageDigest;
/*    4:     */import java.security.NoSuchAlgorithmException;
/*    5:     */import java.security.NoSuchProviderException;
/*    6:     */import java.security.Provider;
/*    7:     */import java.util.Arrays;
/*    8:     */import org.jasypt.commons.CommonUtils;
/*    9:     */import org.jasypt.digest.config.DigesterConfig;
/*   10:     */import org.jasypt.exceptions.AlreadyInitializedException;
/*   11:     */import org.jasypt.exceptions.EncryptionInitializationException;
/*   12:     */import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*   13:     */import org.jasypt.salt.RandomSaltGenerator;
/*   14:     */import org.jasypt.salt.SaltGenerator;
/*   15:     */
/*  179:     */public final class StandardByteDigester
/*  180:     */  implements ByteDigester
/*  181:     */{
/*  182:     */  public static final String DEFAULT_ALGORITHM = "MD5";
/*  183:     */  public static final int DEFAULT_SALT_SIZE_BYTES = 8;
/*  184:     */  public static final int DEFAULT_ITERATIONS = 1000;
/*  185: 185 */  private String algorithm = "MD5";
/*  186:     */  
/*  187: 187 */  private int saltSizeBytes = 8;
/*  188:     */  
/*  189: 189 */  private int iterations = 1000;
/*  190:     */  
/*  193: 193 */  private SaltGenerator saltGenerator = null;
/*  194:     */  
/*  196: 196 */  private String providerName = null;
/*  197:     */  
/*  199: 199 */  private Provider provider = null;
/*  200:     */  
/*  202: 202 */  private boolean invertPositionOfSaltInMessageBeforeDigesting = false;
/*  203:     */  
/*  205: 205 */  private boolean invertPositionOfPlainSaltInEncryptionResults = false;
/*  206:     */  
/*  208: 208 */  private boolean useLenientSaltSizeCheck = false;
/*  209:     */  
/*  218: 218 */  private DigesterConfig config = null;
/*  219:     */  
/*  225: 225 */  private boolean algorithmSet = false;
/*  226: 226 */  private boolean saltSizeBytesSet = false;
/*  227: 227 */  private boolean iterationsSet = false;
/*  228: 228 */  private boolean saltGeneratorSet = false;
/*  229: 229 */  private boolean providerNameSet = false;
/*  230: 230 */  private boolean providerSet = false;
/*  231: 231 */  private boolean invertPositionOfSaltInMessageBeforeDigestingSet = false;
/*  232: 232 */  private boolean invertPositionOfPlainSaltInEncryptionResultsSet = false;
/*  233: 233 */  private boolean useLenientSaltSizeCheckSet = false;
/*  234:     */  
/*  241: 241 */  private boolean initialized = false;
/*  242:     */  
/*  247: 247 */  private boolean useSalt = true;
/*  248:     */  
/*  255: 255 */  private MessageDigest md = null;
/*  256:     */  
/*  262: 262 */  private int digestLengthBytes = 0;
/*  263:     */  
/*  300:     */  public synchronized void setConfig(DigesterConfig config)
/*  301:     */  {
/*  302: 302 */    CommonUtils.validateNotNull(config, "Config cannot be set null");
/*  303: 303 */    if (isInitialized()) {
/*  304: 304 */      throw new AlreadyInitializedException();
/*  305:     */    }
/*  306: 306 */    this.config = config;
/*  307:     */  }
/*  308:     */  
/*  335:     */  public synchronized void setAlgorithm(String algorithm)
/*  336:     */  {
/*  337: 337 */    CommonUtils.validateNotEmpty(algorithm, "Algorithm cannot be empty");
/*  338: 338 */    if (isInitialized()) {
/*  339: 339 */      throw new AlreadyInitializedException();
/*  340:     */    }
/*  341: 341 */    this.algorithm = algorithm;
/*  342: 342 */    this.algorithmSet = true;
/*  343:     */  }
/*  344:     */  
/*  359:     */  public synchronized void setSaltSizeBytes(int saltSizeBytes)
/*  360:     */  {
/*  361: 361 */    CommonUtils.validateIsTrue(saltSizeBytes >= 0, "Salt size in bytes must be non-negative");
/*  362: 362 */    if (isInitialized()) {
/*  363: 363 */      throw new AlreadyInitializedException();
/*  364:     */    }
/*  365: 365 */    this.saltSizeBytes = saltSizeBytes;
/*  366: 366 */    this.useSalt = (saltSizeBytes > 0);
/*  367: 367 */    this.saltSizeBytesSet = true;
/*  368:     */  }
/*  369:     */  
/*  385:     */  public synchronized void setIterations(int iterations)
/*  386:     */  {
/*  387: 387 */    CommonUtils.validateIsTrue(iterations > 0, "Number of iterations must be greater than zero");
/*  388: 388 */    if (isInitialized()) {
/*  389: 389 */      throw new AlreadyInitializedException();
/*  390:     */    }
/*  391: 391 */    this.iterations = iterations;
/*  392: 392 */    this.iterationsSet = true;
/*  393:     */  }
/*  394:     */  
/*  405:     */  public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
/*  406:     */  {
/*  407: 407 */    CommonUtils.validateNotNull(saltGenerator, "Salt generator cannot be set null");
/*  408: 408 */    if (isInitialized()) {
/*  409: 409 */      throw new AlreadyInitializedException();
/*  410:     */    }
/*  411: 411 */    this.saltGenerator = saltGenerator;
/*  412: 412 */    this.saltGeneratorSet = true;
/*  413:     */  }
/*  414:     */  
/*  442:     */  public synchronized void setProviderName(String providerName)
/*  443:     */  {
/*  444: 444 */    CommonUtils.validateNotNull(providerName, "Provider name cannot be set null");
/*  445: 445 */    if (isInitialized()) {
/*  446: 446 */      throw new AlreadyInitializedException();
/*  447:     */    }
/*  448: 448 */    this.providerName = providerName;
/*  449: 449 */    this.providerNameSet = true;
/*  450:     */  }
/*  451:     */  
/*  472:     */  public synchronized void setProvider(Provider provider)
/*  473:     */  {
/*  474: 474 */    CommonUtils.validateNotNull(provider, "Provider cannot be set null");
/*  475: 475 */    if (isInitialized()) {
/*  476: 476 */      throw new AlreadyInitializedException();
/*  477:     */    }
/*  478: 478 */    this.provider = provider;
/*  479: 479 */    this.providerSet = true;
/*  480:     */  }
/*  481:     */  
/*  504:     */  public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting)
/*  505:     */  {
/*  506: 506 */    if (isInitialized()) {
/*  507: 507 */      throw new AlreadyInitializedException();
/*  508:     */    }
/*  509: 509 */    this.invertPositionOfSaltInMessageBeforeDigesting = invertPositionOfSaltInMessageBeforeDigesting;
/*  510: 510 */    this.invertPositionOfSaltInMessageBeforeDigestingSet = true;
/*  511:     */  }
/*  512:     */  
/*  536:     */  public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults)
/*  537:     */  {
/*  538: 538 */    if (isInitialized()) {
/*  539: 539 */      throw new AlreadyInitializedException();
/*  540:     */    }
/*  541: 541 */    this.invertPositionOfPlainSaltInEncryptionResults = invertPositionOfPlainSaltInEncryptionResults;
/*  542: 542 */    this.invertPositionOfPlainSaltInEncryptionResultsSet = true;
/*  543:     */  }
/*  544:     */  
/*  586:     */  public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck)
/*  587:     */  {
/*  588: 588 */    if (isInitialized()) {
/*  589: 589 */      throw new AlreadyInitializedException();
/*  590:     */    }
/*  591: 591 */    this.useLenientSaltSizeCheck = useLenientSaltSizeCheck;
/*  592: 592 */    this.useLenientSaltSizeCheckSet = true;
/*  593:     */  }
/*  594:     */  
/*  605:     */  StandardByteDigester cloneDigester()
/*  606:     */  {
/*  607: 607 */    if (!isInitialized()) {
/*  608: 608 */      initialize();
/*  609:     */    }
/*  610:     */    
/*  611: 611 */    StandardByteDigester cloned = new StandardByteDigester();
/*  612: 612 */    if (CommonUtils.isNotEmpty(this.algorithm)) {
/*  613: 613 */      cloned.setAlgorithm(this.algorithm);
/*  614:     */    }
/*  615: 615 */    cloned.setInvertPositionOfPlainSaltInEncryptionResults(this.invertPositionOfPlainSaltInEncryptionResults);
/*  616: 616 */    cloned.setInvertPositionOfSaltInMessageBeforeDigesting(this.invertPositionOfSaltInMessageBeforeDigesting);
/*  617: 617 */    cloned.setIterations(this.iterations);
/*  618: 618 */    if (this.provider != null) {
/*  619: 619 */      cloned.setProvider(this.provider);
/*  620:     */    }
/*  621: 621 */    if (this.providerName != null) {
/*  622: 622 */      cloned.setProviderName(this.providerName);
/*  623:     */    }
/*  624: 624 */    if (this.saltGenerator != null) {
/*  625: 625 */      cloned.setSaltGenerator(this.saltGenerator);
/*  626:     */    }
/*  627: 627 */    cloned.setSaltSizeBytes(this.saltSizeBytes);
/*  628: 628 */    cloned.setUseLenientSaltSizeCheck(this.useLenientSaltSizeCheck);
/*  629:     */    
/*  630: 630 */    return cloned;
/*  631:     */  }
/*  632:     */  
/*  657:     */  public boolean isInitialized()
/*  658:     */  {
/*  659: 659 */    return this.initialized;
/*  660:     */  }
/*  661:     */  
/*  696:     */  public synchronized void initialize()
/*  697:     */  {
/*  698: 698 */    if (!this.initialized)
/*  699:     */    {
/*  705: 705 */      if (this.config != null)
/*  706:     */      {
/*  707: 707 */        String configAlgorithm = this.config.getAlgorithm();
/*  708: 708 */        if (configAlgorithm != null) {
/*  709: 709 */          CommonUtils.validateNotEmpty(configAlgorithm, "Algorithm cannot be empty");
/*  710:     */        }
/*  711:     */        
/*  712: 712 */        Integer configSaltSizeBytes = this.config.getSaltSizeBytes();
/*  713: 713 */        if (configSaltSizeBytes != null) {
/*  714: 714 */          CommonUtils.validateIsTrue(configSaltSizeBytes.intValue() >= 0, "Salt size in bytes must be non-negative");
/*  715:     */        }
/*  716:     */        
/*  718: 718 */        Integer configIterations = this.config.getIterations();
/*  719: 719 */        if (configIterations != null) {
/*  720: 720 */          CommonUtils.validateIsTrue(configIterations.intValue() > 0, "Number of iterations must be greater than zero");
/*  721:     */        }
/*  722:     */        
/*  724: 724 */        SaltGenerator configSaltGenerator = this.config.getSaltGenerator();
/*  725:     */        
/*  726: 726 */        String configProviderName = this.config.getProviderName();
/*  727: 727 */        if (configProviderName != null) {
/*  728: 728 */          CommonUtils.validateNotEmpty(configProviderName, "Provider name cannot be empty");
/*  729:     */        }
/*  730:     */        
/*  731: 731 */        Provider configProvider = this.config.getProvider();
/*  732:     */        
/*  733: 733 */        Boolean configInvertPositionOfSaltInMessageBeforeDigesting = this.config.getInvertPositionOfSaltInMessageBeforeDigesting();
/*  734:     */        
/*  736: 736 */        Boolean configInvertPositionOfPlainSaltInEncryptionResults = this.config.getInvertPositionOfPlainSaltInEncryptionResults();
/*  737:     */        
/*  739: 739 */        Boolean configUseLenientSaltSizeCheck = this.config.getUseLenientSaltSizeCheck();
/*  740:     */        
/*  743: 743 */        this.algorithm = ((this.algorithmSet) || (configAlgorithm == null) ? this.algorithm : configAlgorithm);
/*  744:     */        
/*  746: 746 */        this.saltSizeBytes = ((this.saltSizeBytesSet) || (configSaltSizeBytes == null) ? this.saltSizeBytes : configSaltSizeBytes.intValue());
/*  747:     */        
/*  749: 749 */        this.iterations = ((this.iterationsSet) || (configIterations == null) ? this.iterations : configIterations.intValue());
/*  750:     */        
/*  752: 752 */        this.saltGenerator = ((this.saltGeneratorSet) || (configSaltGenerator == null) ? this.saltGenerator : configSaltGenerator);
/*  753:     */        
/*  755: 755 */        this.providerName = ((this.providerNameSet) || (configProviderName == null) ? this.providerName : configProviderName);
/*  756:     */        
/*  758: 758 */        this.provider = ((this.providerSet) || (configProvider == null) ? this.provider : configProvider);
/*  759:     */        
/*  761: 761 */        this.invertPositionOfSaltInMessageBeforeDigesting = ((this.invertPositionOfSaltInMessageBeforeDigestingSet) || (configInvertPositionOfSaltInMessageBeforeDigesting == null) ? this.invertPositionOfSaltInMessageBeforeDigesting : configInvertPositionOfSaltInMessageBeforeDigesting.booleanValue());
/*  762:     */        
/*  764: 764 */        this.invertPositionOfPlainSaltInEncryptionResults = ((this.invertPositionOfPlainSaltInEncryptionResultsSet) || (configInvertPositionOfPlainSaltInEncryptionResults == null) ? this.invertPositionOfPlainSaltInEncryptionResults : configInvertPositionOfPlainSaltInEncryptionResults.booleanValue());
/*  765:     */        
/*  767: 767 */        this.useLenientSaltSizeCheck = ((this.useLenientSaltSizeCheckSet) || (configUseLenientSaltSizeCheck == null) ? this.useLenientSaltSizeCheck : configUseLenientSaltSizeCheck.booleanValue());
/*  768:     */      }
/*  769:     */      
/*  777: 777 */      if (this.saltGenerator == null) {
/*  778: 778 */        this.saltGenerator = new RandomSaltGenerator();
/*  779:     */      }
/*  780:     */      
/*  786: 786 */      if ((this.useLenientSaltSizeCheck) && 
/*  787: 787 */        (!this.saltGenerator.includePlainSaltInEncryptionResults())) {
/*  788: 788 */        throw new EncryptionInitializationException("The configured Salt Generator (" + this.saltGenerator.getClass().getName() + ") does not include plain salt " + "in encryption results, which is not compatible" + "with setting the salt size checking behaviour to \"lenient\".");
/*  789:     */      }
/*  790:     */      
/*  801:     */      try
/*  802:     */      {
/*  803: 803 */        if (this.provider != null) {
/*  804: 804 */          this.md = MessageDigest.getInstance(this.algorithm, this.provider);
/*  807:     */        }
/*  808: 808 */        else if (this.providerName != null) {
/*  809: 809 */          this.md = MessageDigest.getInstance(this.algorithm, this.providerName);
/*  811:     */        }
/*  812:     */        else
/*  813:     */        {
/*  814: 814 */          this.md = MessageDigest.getInstance(this.algorithm);
/*  815:     */        }
/*  816:     */      } catch (NoSuchAlgorithmException e) {
/*  817: 817 */        throw new EncryptionInitializationException(e);
/*  818:     */      } catch (NoSuchProviderException e) {
/*  819: 819 */        throw new EncryptionInitializationException(e);
/*  820:     */      }
/*  821:     */      
/*  827: 827 */      this.digestLengthBytes = this.md.getDigestLength();
/*  828: 828 */      if (this.digestLengthBytes <= 0) {
/*  829: 829 */        throw new EncryptionInitializationException("The configured algorithm (" + this.algorithm + ") or its provider do  " + "not allow knowing the digest length beforehand " + "(getDigestLength() operation), which is not compatible" + "with setting the salt size checking behaviour to \"lenient\".");
/*  830:     */      }
/*  831:     */      
/*  837: 837 */      this.initialized = true;
/*  838:     */    }
/*  839:     */  }
/*  840:     */  
/*  915:     */  public byte[] digest(byte[] message)
/*  916:     */  {
/*  917: 917 */    if (message == null) {
/*  918: 918 */      return null;
/*  919:     */    }
/*  920:     */    
/*  922: 922 */    if (!isInitialized()) {
/*  923: 923 */      initialize();
/*  924:     */    }
/*  925:     */    
/*  927: 927 */    byte[] salt = null;
/*  928: 928 */    if (this.useSalt) {
/*  929: 929 */      salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
/*  930:     */    }
/*  931:     */    
/*  933: 933 */    return digest(message, salt);
/*  934:     */  }
/*  935:     */  
/*  943:     */  private byte[] digest(byte[] message, byte[] salt)
/*  944:     */  {
/*  945:     */    try
/*  946:     */    {
/*  947: 947 */      byte[] digest = null;
/*  948:     */      
/*  949: 949 */      synchronized (this.md)
/*  950:     */      {
/*  951: 951 */        this.md.reset();
/*  952:     */        
/*  953: 953 */        if (salt != null)
/*  954:     */        {
/*  955: 955 */          if (!this.invertPositionOfSaltInMessageBeforeDigesting)
/*  956:     */          {
/*  958: 958 */            this.md.update(salt);
/*  959: 959 */            this.md.update(message);
/*  961:     */          }
/*  962:     */          else
/*  963:     */          {
/*  964: 964 */            this.md.update(message);
/*  965: 965 */            this.md.update(salt);
/*  966:     */          }
/*  967:     */          
/*  969:     */        }
/*  970:     */        else
/*  971:     */        {
/*  972: 972 */          this.md.update(message);
/*  973:     */        }
/*  974:     */        
/*  976: 976 */        digest = this.md.digest();
/*  977: 977 */        for (int i = 0; i < this.iterations - 1; i++) {
/*  978: 978 */          this.md.reset();
/*  979: 979 */          digest = this.md.digest(digest);
/*  980:     */        }
/*  981:     */      }
/*  982:     */      
/*  987: 987 */      if ((this.saltGenerator.includePlainSaltInEncryptionResults()) && (salt != null))
/*  988:     */      {
/*  989: 989 */        if (!this.invertPositionOfPlainSaltInEncryptionResults)
/*  990:     */        {
/*  992: 992 */          return CommonUtils.appendArrays(salt, digest);
/*  993:     */        }
/*  994:     */        
/*  997: 997 */        return CommonUtils.appendArrays(digest, salt);
/*  998:     */      }
/*  999:     */      
/* 1001:1001 */      return digest;
/* 1003:     */    }
/* 1004:     */    catch (Exception e)
/* 1005:     */    {
/* 1006:1006 */      throw new EncryptionOperationNotPossibleException();
/* 1007:     */    }
/* 1008:     */  }
/* 1009:     */  
/* 1041:     */  public boolean matches(byte[] message, byte[] digest)
/* 1042:     */  {
/* 1043:1043 */    if (message == null)
/* 1044:1044 */      return digest == null;
/* 1045:1045 */    if (digest == null) {
/* 1046:1046 */      return false;
/* 1047:     */    }
/* 1048:     */    
/* 1050:1050 */    if (!isInitialized()) {
/* 1051:1051 */      initialize();
/* 1052:     */    }
/* 1053:     */    
/* 1055:     */    try
/* 1056:     */    {
/* 1057:1057 */      byte[] salt = null;
/* 1058:1058 */      if (this.useSalt)
/* 1059:     */      {
/* 1063:1063 */        if (this.saltGenerator.includePlainSaltInEncryptionResults())
/* 1064:     */        {
/* 1066:1066 */          int digestSaltSize = this.saltSizeBytes;
/* 1067:1067 */          if (this.digestLengthBytes > 0) {
/* 1068:1068 */            if (this.useLenientSaltSizeCheck) {
/* 1069:1069 */              if (digest.length < this.digestLengthBytes) {
/* 1070:1070 */                throw new EncryptionOperationNotPossibleException();
/* 1071:     */              }
/* 1072:1072 */              digestSaltSize = digest.length - this.digestLengthBytes;
/* 1073:     */            }
/* 1074:1074 */            else if (digest.length != this.digestLengthBytes + this.saltSizeBytes) {
/* 1075:1075 */              throw new EncryptionOperationNotPossibleException();
/* 1076:     */            }
/* 1077:     */            
/* 1079:     */          }
/* 1080:1080 */          else if (digest.length < this.saltSizeBytes) {
/* 1081:1081 */            throw new EncryptionOperationNotPossibleException();
/* 1082:     */          }
/* 1083:     */          
/* 1085:1085 */          if (!this.invertPositionOfPlainSaltInEncryptionResults) {
/* 1086:1086 */            salt = new byte[digestSaltSize];
/* 1087:1087 */            System.arraycopy(digest, 0, salt, 0, digestSaltSize);
/* 1088:     */          } else {
/* 1089:1089 */            salt = new byte[digestSaltSize];
/* 1090:1090 */            System.arraycopy(digest, digest.length - digestSaltSize, salt, 0, digestSaltSize);
/* 1091:     */          }
/* 1092:     */        }
/* 1093:     */        else {
/* 1094:1094 */          salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
/* 1095:     */        }
/* 1096:     */      }
/* 1097:     */      
/* 1099:1099 */      byte[] encryptedMessage = digest(message, salt);
/* 1100:     */      
/* 1102:1102 */      return Arrays.equals(encryptedMessage, digest);
/* 1104:     */    }
/* 1105:     */    catch (Exception e)
/* 1106:     */    {
/* 1107:1107 */      throw new EncryptionOperationNotPossibleException();
/* 1108:     */    }
/* 1109:     */  }
/* 1110:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.StandardByteDigester
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */