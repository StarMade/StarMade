/*      */ package org.jasypt.digest;
/*      */ 
/*      */ import java.security.Provider;
/*      */ import org.jasypt.commons.CommonUtils;
/*      */ import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
/*      */ import org.jasypt.digest.config.DigesterConfig;
/*      */ import org.jasypt.digest.config.StringDigesterConfig;
/*      */ import org.jasypt.exceptions.AlreadyInitializedException;
/*      */ import org.jasypt.exceptions.EncryptionInitializationException;
/*      */ import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*      */ import org.jasypt.normalization.Normalizer;
/*      */ import org.jasypt.salt.SaltGenerator;
/*      */ 
/*      */ public final class StandardStringDigester
/*      */   implements StringDigester
/*      */ {
/*      */   public static final String MESSAGE_CHARSET = "UTF-8";
/*      */   public static final String DIGEST_CHARSET = "US-ASCII";
/*      */   public static final boolean DEFAULT_UNICODE_NORMALIZATION_IGNORED = false;
/*      */   public static final String DEFAULT_STRING_OUTPUT_TYPE = "base64";
/*      */   private final StandardByteDigester byteDigester;
/*  242 */   private StringDigesterConfig stringDigesterConfig = null;
/*      */ 
/*  246 */   private boolean unicodeNormalizationIgnored = false;
/*      */ 
/*  251 */   private String stringOutputType = "base64";
/*  252 */   private boolean stringOutputTypeBase64 = true;
/*      */ 
/*  256 */   private String prefix = null;
/*  257 */   private String suffix = null;
/*      */ 
/*  265 */   private boolean unicodeNormalizationIgnoredSet = false;
/*  266 */   private boolean stringOutputTypeSet = false;
/*  267 */   private boolean prefixSet = false;
/*  268 */   private boolean suffixSet = false;
/*      */   private final Base64 base64;
/*      */ 
/*      */   public StandardStringDigester()
/*      */   {
/*  283 */     this.byteDigester = new StandardByteDigester();
/*  284 */     this.base64 = new Base64();
/*      */   }
/*      */ 
/*      */   private StandardStringDigester(StandardByteDigester standardByteDigester)
/*      */   {
/*  295 */     this.byteDigester = standardByteDigester;
/*  296 */     this.base64 = new Base64();
/*      */   }
/*      */ 
/*      */   public synchronized void setConfig(DigesterConfig config)
/*      */   {
/*  330 */     this.byteDigester.setConfig(config);
/*  331 */     if ((config != null) && ((config instanceof StringDigesterConfig)))
/*  332 */       this.stringDigesterConfig = ((StringDigesterConfig)config);
/*      */   }
/*      */ 
/*      */   public void setAlgorithm(String algorithm)
/*      */   {
/*  364 */     this.byteDigester.setAlgorithm(algorithm);
/*      */   }
/*      */ 
/*      */   public void setSaltSizeBytes(int saltSizeBytes)
/*      */   {
/*  383 */     this.byteDigester.setSaltSizeBytes(saltSizeBytes);
/*      */   }
/*      */ 
/*      */   public void setIterations(int iterations)
/*      */   {
/*  403 */     this.byteDigester.setIterations(iterations);
/*      */   }
/*      */ 
/*      */   public void setSaltGenerator(SaltGenerator saltGenerator)
/*      */   {
/*  416 */     this.byteDigester.setSaltGenerator(saltGenerator);
/*      */   }
/*      */ 
/*      */   public void setProviderName(String providerName)
/*      */   {
/*  448 */     this.byteDigester.setProviderName(providerName);
/*      */   }
/*      */ 
/*      */   public void setProvider(Provider provider)
/*      */   {
/*  473 */     this.byteDigester.setProvider(provider);
/*      */   }
/*      */ 
/*      */   public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting)
/*      */   {
/*  499 */     this.byteDigester.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
/*      */   }
/*      */ 
/*      */   public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults)
/*      */   {
/*  525 */     this.byteDigester.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
/*      */   }
/*      */ 
/*      */   public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck)
/*      */   {
/*  568 */     this.byteDigester.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
/*      */   }
/*      */ 
/*      */   public synchronized void setUnicodeNormalizationIgnored(boolean unicodeNormalizationIgnored)
/*      */   {
/*  600 */     if (isInitialized()) {
/*  601 */       throw new AlreadyInitializedException();
/*      */     }
/*  603 */     this.unicodeNormalizationIgnored = unicodeNormalizationIgnored;
/*  604 */     this.unicodeNormalizationIgnoredSet = true;
/*      */   }
/*      */ 
/*      */   public synchronized void setStringOutputType(String stringOutputType)
/*      */   {
/*  626 */     CommonUtils.validateNotEmpty(stringOutputType, "String output type cannot be set empty");
/*  627 */     if (isInitialized()) {
/*  628 */       throw new AlreadyInitializedException();
/*      */     }
/*  630 */     this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/*      */ 
/*  633 */     this.stringOutputTypeSet = true;
/*      */   }
/*      */ 
/*      */   public synchronized void setPrefix(String prefix)
/*      */   {
/*  652 */     if (isInitialized()) {
/*  653 */       throw new AlreadyInitializedException();
/*      */     }
/*  655 */     this.prefix = prefix;
/*  656 */     this.prefixSet = true;
/*      */   }
/*      */ 
/*      */   public synchronized void setSuffix(String suffix)
/*      */   {
/*  675 */     if (isInitialized()) {
/*  676 */       throw new AlreadyInitializedException();
/*      */     }
/*  678 */     this.suffix = suffix;
/*  679 */     this.suffixSet = true;
/*      */   }
/*      */ 
/*      */   StandardStringDigester cloneDigester()
/*      */   {
/*  695 */     if (!isInitialized()) {
/*  696 */       initialize();
/*      */     }
/*      */ 
/*  699 */     StandardStringDigester cloned = new StandardStringDigester(this.byteDigester.cloneDigester());
/*      */ 
/*  701 */     cloned.setPrefix(this.prefix);
/*  702 */     cloned.setSuffix(this.suffix);
/*  703 */     if (CommonUtils.isNotEmpty(this.stringOutputType)) {
/*  704 */       cloned.setStringOutputType(this.stringOutputType);
/*      */     }
/*  706 */     cloned.setUnicodeNormalizationIgnored(this.unicodeNormalizationIgnored);
/*      */ 
/*  708 */     return cloned;
/*      */   }
/*      */ 
/*      */   public boolean isInitialized()
/*      */   {
/*  737 */     return this.byteDigester.isInitialized();
/*      */   }
/*      */ 
/*      */   public synchronized void initialize()
/*      */   {
/*  777 */     if (!isInitialized())
/*      */     {
/*  784 */       if (this.stringDigesterConfig != null)
/*      */       {
/*  786 */         Boolean configUnicodeNormalizationIgnored = this.stringDigesterConfig.isUnicodeNormalizationIgnored();
/*      */ 
/*  788 */         String configStringOutputType = this.stringDigesterConfig.getStringOutputType();
/*      */ 
/*  790 */         String configPrefix = this.stringDigesterConfig.getPrefix();
/*      */ 
/*  792 */         String configSuffix = this.stringDigesterConfig.getSuffix();
/*      */ 
/*  795 */         this.unicodeNormalizationIgnored = ((this.unicodeNormalizationIgnoredSet) || (configUnicodeNormalizationIgnored == null) ? this.unicodeNormalizationIgnored : configUnicodeNormalizationIgnored.booleanValue());
/*      */ 
/*  798 */         this.stringOutputType = ((this.stringOutputTypeSet) || (configStringOutputType == null) ? this.stringOutputType : configStringOutputType);
/*      */ 
/*  801 */         this.prefix = ((this.prefixSet) || (configPrefix == null) ? this.prefix : configPrefix);
/*      */ 
/*  804 */         this.suffix = ((this.suffixSet) || (configSuffix == null) ? this.suffix : configSuffix);
/*      */       }
/*      */ 
/*  810 */       this.stringOutputTypeBase64 = "base64".equalsIgnoreCase(this.stringOutputType);
/*      */ 
/*  814 */       this.byteDigester.initialize();
/*      */     }
/*      */   }
/*      */ 
/*      */   public String digest(String message)
/*      */   {
/*  897 */     if (message == null) {
/*  898 */       return null;
/*      */     }
/*      */ 
/*  902 */     if (!isInitialized()) {
/*  903 */       initialize();
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/*  909 */       String normalizedMessage = null;
/*  910 */       if (!this.unicodeNormalizationIgnored)
/*  911 */         normalizedMessage = Normalizer.normalizeToNfc(message);
/*      */       else {
/*  913 */         normalizedMessage = message;
/*      */       }
/*      */ 
/*  919 */       byte[] messageBytes = normalizedMessage.getBytes("UTF-8");
/*      */ 
/*  922 */       byte[] digest = this.byteDigester.digest(messageBytes);
/*      */ 
/*  925 */       StringBuffer result = new StringBuffer();
/*      */ 
/*  927 */       if (this.prefix != null)
/*      */       {
/*  929 */         result.append(this.prefix);
/*      */       }
/*      */ 
/*  934 */       if (this.stringOutputTypeBase64) {
/*  935 */         digest = this.base64.encode(digest);
/*  936 */         result.append(new String(digest, "US-ASCII"));
/*      */       } else {
/*  938 */         result.append(CommonUtils.toHexadecimal(digest));
/*      */       }
/*      */ 
/*  941 */       if (this.suffix != null)
/*      */       {
/*  943 */         result.append(this.suffix);
/*      */       }
/*      */ 
/*  946 */       return result.toString();
/*      */     }
/*      */     catch (EncryptionInitializationException e) {
/*  949 */       throw e;
/*      */     } catch (EncryptionOperationNotPossibleException e) {
/*  951 */       throw e;
/*      */     }
/*      */     catch (Exception e) {
/*      */     }
/*  955 */     throw new EncryptionOperationNotPossibleException();
/*      */   }
/*      */ 
/*      */   public boolean matches(String message, String digest)
/*      */   {
/*  993 */     String processedDigest = digest;
/*      */ 
/*  995 */     if (processedDigest != null) {
/*  996 */       if (this.prefix != null) {
/*  997 */         if (!processedDigest.startsWith(this.prefix)) {
/*  998 */           throw new EncryptionOperationNotPossibleException("Digest does not start with required prefix \"" + this.prefix + "\"");
/*      */         }
/*      */ 
/* 1001 */         processedDigest = processedDigest.substring(this.prefix.length());
/*      */       }
/* 1003 */       if (this.suffix != null) {
/* 1004 */         if (!processedDigest.endsWith(this.suffix)) {
/* 1005 */           throw new EncryptionOperationNotPossibleException("Digest does not end with required suffix \"" + this.suffix + "\"");
/*      */         }
/*      */ 
/* 1008 */         processedDigest = processedDigest.substring(0, processedDigest.length() - this.suffix.length());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1013 */     if (message == null)
/* 1014 */       return processedDigest == null;
/* 1015 */     if (processedDigest == null) {
/* 1016 */       return false;
/*      */     }
/*      */ 
/* 1021 */     if (!isInitialized()) {
/* 1022 */       initialize();
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/* 1028 */       String normalizedMessage = null;
/* 1029 */       if (!this.unicodeNormalizationIgnored)
/* 1030 */         normalizedMessage = Normalizer.normalizeToNfc(message);
/*      */       else {
/* 1032 */         normalizedMessage = message;
/*      */       }
/*      */ 
/* 1037 */       byte[] messageBytes = normalizedMessage.getBytes("UTF-8");
/*      */ 
/* 1042 */       byte[] digestBytes = null;
/* 1043 */       if (this.stringOutputTypeBase64)
/*      */       {
/* 1045 */         digestBytes = processedDigest.getBytes("US-ASCII");
/* 1046 */         digestBytes = this.base64.decode(digestBytes);
/*      */       } else {
/* 1048 */         digestBytes = CommonUtils.fromHexadecimal(processedDigest);
/*      */       }
/*      */ 
/* 1052 */       return this.byteDigester.matches(messageBytes, digestBytes);
/*      */     }
/*      */     catch (EncryptionInitializationException e) {
/* 1055 */       throw e;
/*      */     } catch (EncryptionOperationNotPossibleException e) {
/* 1057 */       throw e;
/*      */     }
/*      */     catch (Exception e) {
/*      */     }
/* 1061 */     throw new EncryptionOperationNotPossibleException();
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.StandardStringDigester
 * JD-Core Version:    0.6.2
 */