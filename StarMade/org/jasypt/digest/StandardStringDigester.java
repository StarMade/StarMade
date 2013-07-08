/*    1:     */package org.jasypt.digest;
/*    2:     */
/*    3:     */import java.security.Provider;
/*    4:     */import org.jasypt.commons.CommonUtils;
/*    5:     */import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
/*    6:     */import org.jasypt.digest.config.DigesterConfig;
/*    7:     */import org.jasypt.digest.config.StringDigesterConfig;
/*    8:     */import org.jasypt.exceptions.AlreadyInitializedException;
/*    9:     */import org.jasypt.exceptions.EncryptionInitializationException;
/*   10:     */import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*   11:     */import org.jasypt.normalization.Normalizer;
/*   12:     */import org.jasypt.salt.SaltGenerator;
/*   13:     */
/*  234:     */public final class StandardStringDigester
/*  235:     */  implements StringDigester
/*  236:     */{
/*  237:     */  public static final String MESSAGE_CHARSET = "UTF-8";
/*  238:     */  public static final String DIGEST_CHARSET = "US-ASCII";
/*  239:     */  public static final boolean DEFAULT_UNICODE_NORMALIZATION_IGNORED = false;
/*  240:     */  public static final String DEFAULT_STRING_OUTPUT_TYPE = "base64";
/*  241:     */  private final StandardByteDigester byteDigester;
/*  242: 242 */  private StringDigesterConfig stringDigesterConfig = null;
/*  243:     */  
/*  246: 246 */  private boolean unicodeNormalizationIgnored = false;
/*  247:     */  
/*  251: 251 */  private String stringOutputType = "base64";
/*  252: 252 */  private boolean stringOutputTypeBase64 = true;
/*  253:     */  
/*  256: 256 */  private String prefix = null;
/*  257: 257 */  private String suffix = null;
/*  258:     */  
/*  265: 265 */  private boolean unicodeNormalizationIgnoredSet = false;
/*  266: 266 */  private boolean stringOutputTypeSet = false;
/*  267: 267 */  private boolean prefixSet = false;
/*  268: 268 */  private boolean suffixSet = false;
/*  269:     */  
/*  274:     */  private final Base64 base64;
/*  275:     */  
/*  281:     */  public StandardStringDigester()
/*  282:     */  {
/*  283: 283 */    this.byteDigester = new StandardByteDigester();
/*  284: 284 */    this.base64 = new Base64();
/*  285:     */  }
/*  286:     */  
/*  293:     */  private StandardStringDigester(StandardByteDigester standardByteDigester)
/*  294:     */  {
/*  295: 295 */    this.byteDigester = standardByteDigester;
/*  296: 296 */    this.base64 = new Base64();
/*  297:     */  }
/*  298:     */  
/*  328:     */  public synchronized void setConfig(DigesterConfig config)
/*  329:     */  {
/*  330: 330 */    this.byteDigester.setConfig(config);
/*  331: 331 */    if ((config != null) && ((config instanceof StringDigesterConfig))) {
/*  332: 332 */      this.stringDigesterConfig = ((StringDigesterConfig)config);
/*  333:     */    }
/*  334:     */  }
/*  335:     */  
/*  362:     */  public void setAlgorithm(String algorithm)
/*  363:     */  {
/*  364: 364 */    this.byteDigester.setAlgorithm(algorithm);
/*  365:     */  }
/*  366:     */  
/*  381:     */  public void setSaltSizeBytes(int saltSizeBytes)
/*  382:     */  {
/*  383: 383 */    this.byteDigester.setSaltSizeBytes(saltSizeBytes);
/*  384:     */  }
/*  385:     */  
/*  401:     */  public void setIterations(int iterations)
/*  402:     */  {
/*  403: 403 */    this.byteDigester.setIterations(iterations);
/*  404:     */  }
/*  405:     */  
/*  414:     */  public void setSaltGenerator(SaltGenerator saltGenerator)
/*  415:     */  {
/*  416: 416 */    this.byteDigester.setSaltGenerator(saltGenerator);
/*  417:     */  }
/*  418:     */  
/*  446:     */  public void setProviderName(String providerName)
/*  447:     */  {
/*  448: 448 */    this.byteDigester.setProviderName(providerName);
/*  449:     */  }
/*  450:     */  
/*  471:     */  public void setProvider(Provider provider)
/*  472:     */  {
/*  473: 473 */    this.byteDigester.setProvider(provider);
/*  474:     */  }
/*  475:     */  
/*  497:     */  public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting)
/*  498:     */  {
/*  499: 499 */    this.byteDigester.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
/*  500:     */  }
/*  501:     */  
/*  523:     */  public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults)
/*  524:     */  {
/*  525: 525 */    this.byteDigester.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
/*  526:     */  }
/*  527:     */  
/*  566:     */  public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck)
/*  567:     */  {
/*  568: 568 */    this.byteDigester.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
/*  569:     */  }
/*  570:     */  
/*  598:     */  public synchronized void setUnicodeNormalizationIgnored(boolean unicodeNormalizationIgnored)
/*  599:     */  {
/*  600: 600 */    if (isInitialized()) {
/*  601: 601 */      throw new AlreadyInitializedException();
/*  602:     */    }
/*  603: 603 */    this.unicodeNormalizationIgnored = unicodeNormalizationIgnored;
/*  604: 604 */    this.unicodeNormalizationIgnoredSet = true;
/*  605:     */  }
/*  606:     */  
/*  624:     */  public synchronized void setStringOutputType(String stringOutputType)
/*  625:     */  {
/*  626: 626 */    CommonUtils.validateNotEmpty(stringOutputType, "String output type cannot be set empty");
/*  627: 627 */    if (isInitialized()) {
/*  628: 628 */      throw new AlreadyInitializedException();
/*  629:     */    }
/*  630: 630 */    this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/*  631:     */    
/*  633: 633 */    this.stringOutputTypeSet = true;
/*  634:     */  }
/*  635:     */  
/*  650:     */  public synchronized void setPrefix(String prefix)
/*  651:     */  {
/*  652: 652 */    if (isInitialized()) {
/*  653: 653 */      throw new AlreadyInitializedException();
/*  654:     */    }
/*  655: 655 */    this.prefix = prefix;
/*  656: 656 */    this.prefixSet = true;
/*  657:     */  }
/*  658:     */  
/*  673:     */  public synchronized void setSuffix(String suffix)
/*  674:     */  {
/*  675: 675 */    if (isInitialized()) {
/*  676: 676 */      throw new AlreadyInitializedException();
/*  677:     */    }
/*  678: 678 */    this.suffix = suffix;
/*  679: 679 */    this.suffixSet = true;
/*  680:     */  }
/*  681:     */  
/*  693:     */  StandardStringDigester cloneDigester()
/*  694:     */  {
/*  695: 695 */    if (!isInitialized()) {
/*  696: 696 */      initialize();
/*  697:     */    }
/*  698:     */    
/*  699: 699 */    StandardStringDigester cloned = new StandardStringDigester(this.byteDigester.cloneDigester());
/*  700:     */    
/*  701: 701 */    cloned.setPrefix(this.prefix);
/*  702: 702 */    cloned.setSuffix(this.suffix);
/*  703: 703 */    if (CommonUtils.isNotEmpty(this.stringOutputType)) {
/*  704: 704 */      cloned.setStringOutputType(this.stringOutputType);
/*  705:     */    }
/*  706: 706 */    cloned.setUnicodeNormalizationIgnored(this.unicodeNormalizationIgnored);
/*  707:     */    
/*  708: 708 */    return cloned;
/*  709:     */  }
/*  710:     */  
/*  735:     */  public boolean isInitialized()
/*  736:     */  {
/*  737: 737 */    return this.byteDigester.isInitialized();
/*  738:     */  }
/*  739:     */  
/*  775:     */  public synchronized void initialize()
/*  776:     */  {
/*  777: 777 */    if (!isInitialized())
/*  778:     */    {
/*  784: 784 */      if (this.stringDigesterConfig != null)
/*  785:     */      {
/*  786: 786 */        Boolean configUnicodeNormalizationIgnored = this.stringDigesterConfig.isUnicodeNormalizationIgnored();
/*  787:     */        
/*  788: 788 */        String configStringOutputType = this.stringDigesterConfig.getStringOutputType();
/*  789:     */        
/*  790: 790 */        String configPrefix = this.stringDigesterConfig.getPrefix();
/*  791:     */        
/*  792: 792 */        String configSuffix = this.stringDigesterConfig.getSuffix();
/*  793:     */        
/*  795: 795 */        this.unicodeNormalizationIgnored = ((this.unicodeNormalizationIgnoredSet) || (configUnicodeNormalizationIgnored == null) ? this.unicodeNormalizationIgnored : configUnicodeNormalizationIgnored.booleanValue());
/*  796:     */        
/*  798: 798 */        this.stringOutputType = ((this.stringOutputTypeSet) || (configStringOutputType == null) ? this.stringOutputType : configStringOutputType);
/*  799:     */        
/*  801: 801 */        this.prefix = ((this.prefixSet) || (configPrefix == null) ? this.prefix : configPrefix);
/*  802:     */        
/*  804: 804 */        this.suffix = ((this.suffixSet) || (configSuffix == null) ? this.suffix : configSuffix);
/*  805:     */      }
/*  806:     */      
/*  810: 810 */      this.stringOutputTypeBase64 = "base64".equalsIgnoreCase(this.stringOutputType);
/*  811:     */      
/*  814: 814 */      this.byteDigester.initialize();
/*  815:     */    }
/*  816:     */  }
/*  817:     */  
/*  895:     */  public String digest(String message)
/*  896:     */  {
/*  897: 897 */    if (message == null) {
/*  898: 898 */      return null;
/*  899:     */    }
/*  900:     */    
/*  902: 902 */    if (!isInitialized()) {
/*  903: 903 */      initialize();
/*  904:     */    }
/*  905:     */    
/*  907:     */    try
/*  908:     */    {
/*  909: 909 */      String normalizedMessage = null;
/*  910: 910 */      if (!this.unicodeNormalizationIgnored) {
/*  911: 911 */        normalizedMessage = Normalizer.normalizeToNfc(message);
/*  912:     */      } else {
/*  913: 913 */        normalizedMessage = message;
/*  914:     */      }
/*  915:     */      
/*  919: 919 */      byte[] messageBytes = normalizedMessage.getBytes("UTF-8");
/*  920:     */      
/*  922: 922 */      byte[] digest = this.byteDigester.digest(messageBytes);
/*  923:     */      
/*  925: 925 */      StringBuffer result = new StringBuffer();
/*  926:     */      
/*  927: 927 */      if (this.prefix != null)
/*  928:     */      {
/*  929: 929 */        result.append(this.prefix);
/*  930:     */      }
/*  931:     */      
/*  934: 934 */      if (this.stringOutputTypeBase64) {
/*  935: 935 */        digest = this.base64.encode(digest);
/*  936: 936 */        result.append(new String(digest, "US-ASCII"));
/*  937:     */      } else {
/*  938: 938 */        result.append(CommonUtils.toHexadecimal(digest));
/*  939:     */      }
/*  940:     */      
/*  941: 941 */      if (this.suffix != null)
/*  942:     */      {
/*  943: 943 */        result.append(this.suffix);
/*  944:     */      }
/*  945:     */      
/*  946: 946 */      return result.toString();
/*  947:     */    }
/*  948:     */    catch (EncryptionInitializationException e) {
/*  949: 949 */      throw e;
/*  950:     */    } catch (EncryptionOperationNotPossibleException e) {
/*  951: 951 */      throw e;
/*  952:     */    }
/*  953:     */    catch (Exception e)
/*  954:     */    {
/*  955: 955 */      throw new EncryptionOperationNotPossibleException();
/*  956:     */    }
/*  957:     */  }
/*  958:     */  
/*  991:     */  public boolean matches(String message, String digest)
/*  992:     */  {
/*  993: 993 */    String processedDigest = digest;
/*  994:     */    
/*  995: 995 */    if (processedDigest != null) {
/*  996: 996 */      if (this.prefix != null) {
/*  997: 997 */        if (!processedDigest.startsWith(this.prefix)) {
/*  998: 998 */          throw new EncryptionOperationNotPossibleException("Digest does not start with required prefix \"" + this.prefix + "\"");
/*  999:     */        }
/* 1000:     */        
/* 1001:1001 */        processedDigest = processedDigest.substring(this.prefix.length());
/* 1002:     */      }
/* 1003:1003 */      if (this.suffix != null) {
/* 1004:1004 */        if (!processedDigest.endsWith(this.suffix)) {
/* 1005:1005 */          throw new EncryptionOperationNotPossibleException("Digest does not end with required suffix \"" + this.suffix + "\"");
/* 1006:     */        }
/* 1007:     */        
/* 1008:1008 */        processedDigest = processedDigest.substring(0, processedDigest.length() - this.suffix.length());
/* 1009:     */      }
/* 1010:     */    }
/* 1011:     */    
/* 1013:1013 */    if (message == null)
/* 1014:1014 */      return processedDigest == null;
/* 1015:1015 */    if (processedDigest == null) {
/* 1016:1016 */      return false;
/* 1017:     */    }
/* 1018:     */    
/* 1021:1021 */    if (!isInitialized()) {
/* 1022:1022 */      initialize();
/* 1023:     */    }
/* 1024:     */    
/* 1026:     */    try
/* 1027:     */    {
/* 1028:1028 */      String normalizedMessage = null;
/* 1029:1029 */      if (!this.unicodeNormalizationIgnored) {
/* 1030:1030 */        normalizedMessage = Normalizer.normalizeToNfc(message);
/* 1031:     */      } else {
/* 1032:1032 */        normalizedMessage = message;
/* 1033:     */      }
/* 1034:     */      
/* 1037:1037 */      byte[] messageBytes = normalizedMessage.getBytes("UTF-8");
/* 1038:     */      
/* 1042:1042 */      byte[] digestBytes = null;
/* 1043:1043 */      if (this.stringOutputTypeBase64)
/* 1044:     */      {
/* 1045:1045 */        digestBytes = processedDigest.getBytes("US-ASCII");
/* 1046:1046 */        digestBytes = this.base64.decode(digestBytes);
/* 1047:     */      } else {
/* 1048:1048 */        digestBytes = CommonUtils.fromHexadecimal(processedDigest);
/* 1049:     */      }
/* 1050:     */      
/* 1052:1052 */      return this.byteDigester.matches(messageBytes, digestBytes);
/* 1053:     */    }
/* 1054:     */    catch (EncryptionInitializationException e) {
/* 1055:1055 */      throw e;
/* 1056:     */    } catch (EncryptionOperationNotPossibleException e) {
/* 1057:1057 */      throw e;
/* 1058:     */    }
/* 1059:     */    catch (Exception e)
/* 1060:     */    {
/* 1061:1061 */      throw new EncryptionOperationNotPossibleException();
/* 1062:     */    }
/* 1063:     */  }
/* 1064:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.StandardStringDigester
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */