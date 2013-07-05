/*      */ package org.jasypt.digest;
/*      */ 
/*      */ import java.security.MessageDigest;
/*      */ import java.security.NoSuchAlgorithmException;
/*      */ import java.security.NoSuchProviderException;
/*      */ import java.security.Provider;
/*      */ import java.util.Arrays;
/*      */ import org.jasypt.commons.CommonUtils;
/*      */ import org.jasypt.digest.config.DigesterConfig;
/*      */ import org.jasypt.exceptions.AlreadyInitializedException;
/*      */ import org.jasypt.exceptions.EncryptionInitializationException;
/*      */ import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*      */ import org.jasypt.salt.RandomSaltGenerator;
/*      */ import org.jasypt.salt.SaltGenerator;
/*      */ 
/*      */ public final class StandardByteDigester
/*      */   implements ByteDigester
/*      */ {
/*      */   public static final String DEFAULT_ALGORITHM = "MD5";
/*      */   public static final int DEFAULT_SALT_SIZE_BYTES = 8;
/*      */   public static final int DEFAULT_ITERATIONS = 1000;
/*  185 */   private String algorithm = "MD5";
/*      */ 
/*  187 */   private int saltSizeBytes = 8;
/*      */ 
/*  189 */   private int iterations = 1000;
/*      */ 
/*  193 */   private SaltGenerator saltGenerator = null;
/*      */ 
/*  196 */   private String providerName = null;
/*      */ 
/*  199 */   private Provider provider = null;
/*      */ 
/*  202 */   private boolean invertPositionOfSaltInMessageBeforeDigesting = false;
/*      */ 
/*  205 */   private boolean invertPositionOfPlainSaltInEncryptionResults = false;
/*      */ 
/*  208 */   private boolean useLenientSaltSizeCheck = false;
/*      */ 
/*  218 */   private DigesterConfig config = null;
/*      */ 
/*  225 */   private boolean algorithmSet = false;
/*  226 */   private boolean saltSizeBytesSet = false;
/*  227 */   private boolean iterationsSet = false;
/*  228 */   private boolean saltGeneratorSet = false;
/*  229 */   private boolean providerNameSet = false;
/*  230 */   private boolean providerSet = false;
/*  231 */   private boolean invertPositionOfSaltInMessageBeforeDigestingSet = false;
/*  232 */   private boolean invertPositionOfPlainSaltInEncryptionResultsSet = false;
/*  233 */   private boolean useLenientSaltSizeCheckSet = false;
/*      */ 
/*  241 */   private boolean initialized = false;
/*      */ 
/*  247 */   private boolean useSalt = true;
/*      */ 
/*  255 */   private MessageDigest md = null;
/*      */ 
/*  262 */   private int digestLengthBytes = 0;
/*      */ 
/*      */   public synchronized void setConfig(DigesterConfig config)
/*      */   {
/*  302 */     CommonUtils.validateNotNull(config, "Config cannot be set null");
/*  303 */     if (isInitialized()) {
/*  304 */       throw new AlreadyInitializedException();
/*      */     }
/*  306 */     this.config = config;
/*      */   }
/*      */ 
/*      */   public synchronized void setAlgorithm(String algorithm)
/*      */   {
/*  337 */     CommonUtils.validateNotEmpty(algorithm, "Algorithm cannot be empty");
/*  338 */     if (isInitialized()) {
/*  339 */       throw new AlreadyInitializedException();
/*      */     }
/*  341 */     this.algorithm = algorithm;
/*  342 */     this.algorithmSet = true;
/*      */   }
/*      */ 
/*      */   public synchronized void setSaltSizeBytes(int saltSizeBytes)
/*      */   {
/*  361 */     CommonUtils.validateIsTrue(saltSizeBytes >= 0, "Salt size in bytes must be non-negative");
/*  362 */     if (isInitialized()) {
/*  363 */       throw new AlreadyInitializedException();
/*      */     }
/*  365 */     this.saltSizeBytes = saltSizeBytes;
/*  366 */     this.useSalt = (saltSizeBytes > 0);
/*  367 */     this.saltSizeBytesSet = true;
/*      */   }
/*      */ 
/*      */   public synchronized void setIterations(int iterations)
/*      */   {
/*  387 */     CommonUtils.validateIsTrue(iterations > 0, "Number of iterations must be greater than zero");
/*  388 */     if (isInitialized()) {
/*  389 */       throw new AlreadyInitializedException();
/*      */     }
/*  391 */     this.iterations = iterations;
/*  392 */     this.iterationsSet = true;
/*      */   }
/*      */ 
/*      */   public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
/*      */   {
/*  407 */     CommonUtils.validateNotNull(saltGenerator, "Salt generator cannot be set null");
/*  408 */     if (isInitialized()) {
/*  409 */       throw new AlreadyInitializedException();
/*      */     }
/*  411 */     this.saltGenerator = saltGenerator;
/*  412 */     this.saltGeneratorSet = true;
/*      */   }
/*      */ 
/*      */   public synchronized void setProviderName(String providerName)
/*      */   {
/*  444 */     CommonUtils.validateNotNull(providerName, "Provider name cannot be set null");
/*  445 */     if (isInitialized()) {
/*  446 */       throw new AlreadyInitializedException();
/*      */     }
/*  448 */     this.providerName = providerName;
/*  449 */     this.providerNameSet = true;
/*      */   }
/*      */ 
/*      */   public synchronized void setProvider(Provider provider)
/*      */   {
/*  474 */     CommonUtils.validateNotNull(provider, "Provider cannot be set null");
/*  475 */     if (isInitialized()) {
/*  476 */       throw new AlreadyInitializedException();
/*      */     }
/*  478 */     this.provider = provider;
/*  479 */     this.providerSet = true;
/*      */   }
/*      */ 
/*      */   public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting)
/*      */   {
/*  506 */     if (isInitialized()) {
/*  507 */       throw new AlreadyInitializedException();
/*      */     }
/*  509 */     this.invertPositionOfSaltInMessageBeforeDigesting = invertPositionOfSaltInMessageBeforeDigesting;
/*  510 */     this.invertPositionOfSaltInMessageBeforeDigestingSet = true;
/*      */   }
/*      */ 
/*      */   public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults)
/*      */   {
/*  538 */     if (isInitialized()) {
/*  539 */       throw new AlreadyInitializedException();
/*      */     }
/*  541 */     this.invertPositionOfPlainSaltInEncryptionResults = invertPositionOfPlainSaltInEncryptionResults;
/*  542 */     this.invertPositionOfPlainSaltInEncryptionResultsSet = true;
/*      */   }
/*      */ 
/*      */   public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck)
/*      */   {
/*  588 */     if (isInitialized()) {
/*  589 */       throw new AlreadyInitializedException();
/*      */     }
/*  591 */     this.useLenientSaltSizeCheck = useLenientSaltSizeCheck;
/*  592 */     this.useLenientSaltSizeCheckSet = true;
/*      */   }
/*      */ 
/*      */   StandardByteDigester cloneDigester()
/*      */   {
/*  607 */     if (!isInitialized()) {
/*  608 */       initialize();
/*      */     }
/*      */ 
/*  611 */     StandardByteDigester cloned = new StandardByteDigester();
/*  612 */     if (CommonUtils.isNotEmpty(this.algorithm)) {
/*  613 */       cloned.setAlgorithm(this.algorithm);
/*      */     }
/*  615 */     cloned.setInvertPositionOfPlainSaltInEncryptionResults(this.invertPositionOfPlainSaltInEncryptionResults);
/*  616 */     cloned.setInvertPositionOfSaltInMessageBeforeDigesting(this.invertPositionOfSaltInMessageBeforeDigesting);
/*  617 */     cloned.setIterations(this.iterations);
/*  618 */     if (this.provider != null) {
/*  619 */       cloned.setProvider(this.provider);
/*      */     }
/*  621 */     if (this.providerName != null) {
/*  622 */       cloned.setProviderName(this.providerName);
/*      */     }
/*  624 */     if (this.saltGenerator != null) {
/*  625 */       cloned.setSaltGenerator(this.saltGenerator);
/*      */     }
/*  627 */     cloned.setSaltSizeBytes(this.saltSizeBytes);
/*  628 */     cloned.setUseLenientSaltSizeCheck(this.useLenientSaltSizeCheck);
/*      */ 
/*  630 */     return cloned;
/*      */   }
/*      */ 
/*      */   public boolean isInitialized()
/*      */   {
/*  659 */     return this.initialized;
/*      */   }
/*      */ 
/*      */   public synchronized void initialize()
/*      */   {
/*  698 */     if (!this.initialized)
/*      */     {
/*  705 */       if (this.config != null)
/*      */       {
/*  707 */         String configAlgorithm = this.config.getAlgorithm();
/*  708 */         if (configAlgorithm != null) {
/*  709 */           CommonUtils.validateNotEmpty(configAlgorithm, "Algorithm cannot be empty");
/*      */         }
/*      */ 
/*  712 */         Integer configSaltSizeBytes = this.config.getSaltSizeBytes();
/*  713 */         if (configSaltSizeBytes != null) {
/*  714 */           CommonUtils.validateIsTrue(configSaltSizeBytes.intValue() >= 0, "Salt size in bytes must be non-negative");
/*      */         }
/*      */ 
/*  718 */         Integer configIterations = this.config.getIterations();
/*  719 */         if (configIterations != null) {
/*  720 */           CommonUtils.validateIsTrue(configIterations.intValue() > 0, "Number of iterations must be greater than zero");
/*      */         }
/*      */ 
/*  724 */         SaltGenerator configSaltGenerator = this.config.getSaltGenerator();
/*      */ 
/*  726 */         String configProviderName = this.config.getProviderName();
/*  727 */         if (configProviderName != null) {
/*  728 */           CommonUtils.validateNotEmpty(configProviderName, "Provider name cannot be empty");
/*      */         }
/*      */ 
/*  731 */         Provider configProvider = this.config.getProvider();
/*      */ 
/*  733 */         Boolean configInvertPositionOfSaltInMessageBeforeDigesting = this.config.getInvertPositionOfSaltInMessageBeforeDigesting();
/*      */ 
/*  736 */         Boolean configInvertPositionOfPlainSaltInEncryptionResults = this.config.getInvertPositionOfPlainSaltInEncryptionResults();
/*      */ 
/*  739 */         Boolean configUseLenientSaltSizeCheck = this.config.getUseLenientSaltSizeCheck();
/*      */ 
/*  743 */         this.algorithm = ((this.algorithmSet) || (configAlgorithm == null) ? this.algorithm : configAlgorithm);
/*      */ 
/*  746 */         this.saltSizeBytes = ((this.saltSizeBytesSet) || (configSaltSizeBytes == null) ? this.saltSizeBytes : configSaltSizeBytes.intValue());
/*      */ 
/*  749 */         this.iterations = ((this.iterationsSet) || (configIterations == null) ? this.iterations : configIterations.intValue());
/*      */ 
/*  752 */         this.saltGenerator = ((this.saltGeneratorSet) || (configSaltGenerator == null) ? this.saltGenerator : configSaltGenerator);
/*      */ 
/*  755 */         this.providerName = ((this.providerNameSet) || (configProviderName == null) ? this.providerName : configProviderName);
/*      */ 
/*  758 */         this.provider = ((this.providerSet) || (configProvider == null) ? this.provider : configProvider);
/*      */ 
/*  761 */         this.invertPositionOfSaltInMessageBeforeDigesting = ((this.invertPositionOfSaltInMessageBeforeDigestingSet) || (configInvertPositionOfSaltInMessageBeforeDigesting == null) ? this.invertPositionOfSaltInMessageBeforeDigesting : configInvertPositionOfSaltInMessageBeforeDigesting.booleanValue());
/*      */ 
/*  764 */         this.invertPositionOfPlainSaltInEncryptionResults = ((this.invertPositionOfPlainSaltInEncryptionResultsSet) || (configInvertPositionOfPlainSaltInEncryptionResults == null) ? this.invertPositionOfPlainSaltInEncryptionResults : configInvertPositionOfPlainSaltInEncryptionResults.booleanValue());
/*      */ 
/*  767 */         this.useLenientSaltSizeCheck = ((this.useLenientSaltSizeCheckSet) || (configUseLenientSaltSizeCheck == null) ? this.useLenientSaltSizeCheck : configUseLenientSaltSizeCheck.booleanValue());
/*      */       }
/*      */ 
/*  777 */       if (this.saltGenerator == null) {
/*  778 */         this.saltGenerator = new RandomSaltGenerator();
/*      */       }
/*      */ 
/*  786 */       if ((this.useLenientSaltSizeCheck) && 
/*  787 */         (!this.saltGenerator.includePlainSaltInEncryptionResults())) {
/*  788 */         throw new EncryptionInitializationException("The configured Salt Generator (" + this.saltGenerator.getClass().getName() + ") does not include plain salt " + "in encryption results, which is not compatible" + "with setting the salt size checking behaviour to \"lenient\".");
/*      */       }
/*      */ 
/*      */       try
/*      */       {
/*  803 */         if (this.provider != null) {
/*  804 */           this.md = MessageDigest.getInstance(this.algorithm, this.provider);
/*      */         }
/*  808 */         else if (this.providerName != null) {
/*  809 */           this.md = MessageDigest.getInstance(this.algorithm, this.providerName);
/*      */         }
/*      */         else
/*      */         {
/*  814 */           this.md = MessageDigest.getInstance(this.algorithm);
/*      */         }
/*      */       } catch (NoSuchAlgorithmException e) {
/*  817 */         throw new EncryptionInitializationException(e);
/*      */       } catch (NoSuchProviderException e) {
/*  819 */         throw new EncryptionInitializationException(e);
/*      */       }
/*      */ 
/*  827 */       this.digestLengthBytes = this.md.getDigestLength();
/*  828 */       if (this.digestLengthBytes <= 0) {
/*  829 */         throw new EncryptionInitializationException("The configured algorithm (" + this.algorithm + ") or its provider do  " + "not allow knowing the digest length beforehand " + "(getDigestLength() operation), which is not compatible" + "with setting the salt size checking behaviour to \"lenient\".");
/*      */       }
/*      */ 
/*  837 */       this.initialized = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public byte[] digest(byte[] message)
/*      */   {
/*  917 */     if (message == null) {
/*  918 */       return null;
/*      */     }
/*      */ 
/*  922 */     if (!isInitialized()) {
/*  923 */       initialize();
/*      */     }
/*      */ 
/*  927 */     byte[] salt = null;
/*  928 */     if (this.useSalt) {
/*  929 */       salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
/*      */     }
/*      */ 
/*  933 */     return digest(message, salt);
/*      */   }
/*      */ 
/*      */   private byte[] digest(byte[] message, byte[] salt)
/*      */   {
/*      */     try
/*      */     {
/*  947 */       byte[] digest = null;
/*      */ 
/*  949 */       synchronized (this.md)
/*      */       {
/*  951 */         this.md.reset();
/*      */ 
/*  953 */         if (salt != null)
/*      */         {
/*  955 */           if (!this.invertPositionOfSaltInMessageBeforeDigesting)
/*      */           {
/*  958 */             this.md.update(salt);
/*  959 */             this.md.update(message);
/*      */           }
/*      */           else
/*      */           {
/*  964 */             this.md.update(message);
/*  965 */             this.md.update(salt);
/*      */           }
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  972 */           this.md.update(message);
/*      */         }
/*      */ 
/*  976 */         digest = this.md.digest();
/*  977 */         for (int i = 0; i < this.iterations - 1; i++) {
/*  978 */           this.md.reset();
/*  979 */           digest = this.md.digest(digest);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  987 */       if ((this.saltGenerator.includePlainSaltInEncryptionResults()) && (salt != null))
/*      */       {
/*  989 */         if (!this.invertPositionOfPlainSaltInEncryptionResults)
/*      */         {
/*  992 */           return CommonUtils.appendArrays(salt, digest);
/*      */         }
/*      */ 
/*  997 */         return CommonUtils.appendArrays(digest, salt);
/*      */       }
/*      */ 
/* 1001 */       return digest;
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*      */     }
/* 1006 */     throw new EncryptionOperationNotPossibleException();
/*      */   }
/*      */ 
/*      */   public boolean matches(byte[] message, byte[] digest)
/*      */   {
/* 1043 */     if (message == null)
/* 1044 */       return digest == null;
/* 1045 */     if (digest == null) {
/* 1046 */       return false;
/*      */     }
/*      */ 
/* 1050 */     if (!isInitialized()) {
/* 1051 */       initialize();
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/* 1057 */       byte[] salt = null;
/* 1058 */       if (this.useSalt)
/*      */       {
/* 1063 */         if (this.saltGenerator.includePlainSaltInEncryptionResults())
/*      */         {
/* 1066 */           int digestSaltSize = this.saltSizeBytes;
/* 1067 */           if (this.digestLengthBytes > 0) {
/* 1068 */             if (this.useLenientSaltSizeCheck) {
/* 1069 */               if (digest.length < this.digestLengthBytes) {
/* 1070 */                 throw new EncryptionOperationNotPossibleException();
/*      */               }
/* 1072 */               digestSaltSize = digest.length - this.digestLengthBytes;
/*      */             }
/* 1074 */             else if (digest.length != this.digestLengthBytes + this.saltSizeBytes) {
/* 1075 */               throw new EncryptionOperationNotPossibleException();
/*      */             }
/*      */ 
/*      */           }
/* 1080 */           else if (digest.length < this.saltSizeBytes) {
/* 1081 */             throw new EncryptionOperationNotPossibleException();
/*      */           }
/*      */ 
/* 1085 */           if (!this.invertPositionOfPlainSaltInEncryptionResults) {
/* 1086 */             salt = new byte[digestSaltSize];
/* 1087 */             System.arraycopy(digest, 0, salt, 0, digestSaltSize);
/*      */           } else {
/* 1089 */             salt = new byte[digestSaltSize];
/* 1090 */             System.arraycopy(digest, digest.length - digestSaltSize, salt, 0, digestSaltSize);
/*      */           }
/*      */         }
/*      */         else {
/* 1094 */           salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1099 */       byte[] encryptedMessage = digest(message, salt);
/*      */ 
/* 1102 */       return Arrays.equals(encryptedMessage, digest);
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*      */     }
/* 1107 */     throw new EncryptionOperationNotPossibleException();
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.StandardByteDigester
 * JD-Core Version:    0.6.2
 */