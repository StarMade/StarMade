/*     */ package org.jasypt.encryption.pbe;
/*     */ 
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.Provider;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.PBEKeySpec;
/*     */ import javax.crypto.spec.PBEParameterSpec;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.encryption.pbe.config.PBECleanablePasswordConfig;
/*     */ import org.jasypt.encryption.pbe.config.PBEConfig;
/*     */ import org.jasypt.exceptions.AlreadyInitializedException;
/*     */ import org.jasypt.exceptions.EncryptionInitializationException;
/*     */ import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*     */ import org.jasypt.normalization.Normalizer;
/*     */ import org.jasypt.salt.RandomSaltGenerator;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public final class StandardPBEByteEncryptor
/*     */   implements PBEByteCleanablePasswordEncryptor
/*     */ {
/*     */   public static final String DEFAULT_ALGORITHM = "PBEWithMD5AndDES";
/*     */   public static final int DEFAULT_KEY_OBTENTION_ITERATIONS = 1000;
/*     */   public static final int DEFAULT_SALT_SIZE_BYTES = 8;
/* 148 */   private String algorithm = "PBEWithMD5AndDES";
/* 149 */   private String providerName = null;
/* 150 */   private Provider provider = null;
/*     */ 
/* 154 */   private char[] password = null;
/*     */ 
/* 158 */   private int keyObtentionIterations = 1000;
/*     */ 
/* 163 */   private SaltGenerator saltGenerator = null;
/*     */ 
/* 170 */   private int saltSizeBytes = 8;
/*     */ 
/* 174 */   private PBEConfig config = null;
/*     */ 
/* 181 */   private boolean algorithmSet = false;
/* 182 */   private boolean passwordSet = false;
/* 183 */   private boolean iterationsSet = false;
/* 184 */   private boolean saltGeneratorSet = false;
/* 185 */   private boolean providerNameSet = false;
/* 186 */   private boolean providerSet = false;
/*     */ 
/* 195 */   private boolean initialized = false;
/*     */ 
/* 199 */   private SecretKey key = null;
/*     */ 
/* 202 */   private Cipher encryptCipher = null;
/* 203 */   private Cipher decryptCipher = null;
/*     */ 
/*     */   public synchronized void setConfig(PBEConfig config)
/*     */   {
/* 241 */     CommonUtils.validateNotNull(config, "Config cannot be set null");
/* 242 */     if (isInitialized()) {
/* 243 */       throw new AlreadyInitializedException();
/*     */     }
/* 245 */     this.config = config;
/*     */   }
/*     */ 
/*     */   public synchronized void setAlgorithm(String algorithm)
/*     */   {
/* 264 */     CommonUtils.validateNotEmpty(algorithm, "Algorithm cannot be set empty");
/* 265 */     if (isInitialized()) {
/* 266 */       throw new AlreadyInitializedException();
/*     */     }
/* 268 */     this.algorithm = algorithm;
/* 269 */     this.algorithmSet = true;
/*     */   }
/*     */ 
/*     */   public synchronized void setPassword(String password)
/*     */   {
/* 288 */     CommonUtils.validateNotEmpty(password, "Password cannot be set empty");
/* 289 */     if (isInitialized()) {
/* 290 */       throw new AlreadyInitializedException();
/*     */     }
/* 292 */     if (this.password != null)
/*     */     {
/* 294 */       cleanPassword(this.password);
/*     */     }
/* 296 */     this.password = password.toCharArray();
/* 297 */     this.passwordSet = true;
/*     */   }
/*     */ 
/*     */   public synchronized void setPasswordCharArray(char[] password)
/*     */   {
/* 330 */     CommonUtils.validateNotNull(password, "Password cannot be set null");
/* 331 */     CommonUtils.validateIsTrue(password.length > 0, "Password cannot be set empty");
/* 332 */     if (isInitialized()) {
/* 333 */       throw new AlreadyInitializedException();
/*     */     }
/* 335 */     if (this.password != null)
/*     */     {
/* 337 */       cleanPassword(this.password);
/*     */     }
/* 339 */     this.password = new char[password.length];
/* 340 */     System.arraycopy(password, 0, this.password, 0, password.length);
/* 341 */     this.passwordSet = true;
/*     */   }
/*     */ 
/*     */   public synchronized void setKeyObtentionIterations(int keyObtentionIterations)
/*     */   {
/* 360 */     CommonUtils.validateIsTrue(keyObtentionIterations > 0, "Number of iterations for key obtention must be greater than zero");
/*     */ 
/* 363 */     if (isInitialized()) {
/* 364 */       throw new AlreadyInitializedException();
/*     */     }
/* 366 */     this.keyObtentionIterations = keyObtentionIterations;
/* 367 */     this.iterationsSet = true;
/*     */   }
/*     */ 
/*     */   public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 380 */     CommonUtils.validateNotNull(saltGenerator, "Salt generator cannot be set null");
/* 381 */     if (isInitialized()) {
/* 382 */       throw new AlreadyInitializedException();
/*     */     }
/* 384 */     this.saltGenerator = saltGenerator;
/* 385 */     this.saltGeneratorSet = true;
/*     */   }
/*     */ 
/*     */   public synchronized void setProviderName(String providerName)
/*     */   {
/* 417 */     CommonUtils.validateNotNull(providerName, "Provider name cannot be set null");
/* 418 */     if (isInitialized()) {
/* 419 */       throw new AlreadyInitializedException();
/*     */     }
/* 421 */     this.providerName = providerName;
/* 422 */     this.providerNameSet = true;
/*     */   }
/*     */ 
/*     */   public synchronized void setProvider(Provider provider)
/*     */   {
/* 447 */     CommonUtils.validateNotNull(provider, "Provider cannot be set null");
/* 448 */     if (isInitialized()) {
/* 449 */       throw new AlreadyInitializedException();
/*     */     }
/* 451 */     this.provider = provider;
/* 452 */     this.providerSet = true;
/*     */   }
/*     */ 
/*     */   synchronized StandardPBEByteEncryptor[] cloneAndInitializeEncryptor(int size)
/*     */   {
/* 471 */     if (isInitialized()) {
/* 472 */       throw new EncryptionInitializationException("Cannot clone encryptor if it has been already initialized");
/*     */     }
/*     */ 
/* 478 */     resolveConfigurationPassword();
/*     */ 
/* 480 */     char[] copiedPassword = new char[this.password.length];
/* 481 */     System.arraycopy(this.password, 0, copiedPassword, 0, this.password.length);
/*     */ 
/* 485 */     initialize();
/*     */ 
/* 487 */     StandardPBEByteEncryptor[] clones = new StandardPBEByteEncryptor[size];
/*     */ 
/* 489 */     clones[0] = this;
/*     */ 
/* 491 */     for (int i = 1; i < size; i++)
/*     */     {
/* 493 */       StandardPBEByteEncryptor clone = new StandardPBEByteEncryptor();
/* 494 */       clone.setPasswordCharArray(copiedPassword);
/* 495 */       if (CommonUtils.isNotEmpty(this.algorithm)) {
/* 496 */         clone.setAlgorithm(this.algorithm);
/*     */       }
/* 498 */       clone.setKeyObtentionIterations(this.keyObtentionIterations);
/* 499 */       if (this.provider != null) {
/* 500 */         clone.setProvider(this.provider);
/*     */       }
/* 502 */       if (this.providerName != null) {
/* 503 */         clone.setProviderName(this.providerName);
/*     */       }
/* 505 */       if (this.saltGenerator != null) {
/* 506 */         clone.setSaltGenerator(this.saltGenerator);
/*     */       }
/*     */ 
/* 509 */       clones[i] = clone;
/*     */     }
/*     */ 
/* 513 */     cleanPassword(copiedPassword);
/*     */ 
/* 515 */     return clones;
/*     */   }
/*     */ 
/*     */   public boolean isInitialized()
/*     */   {
/* 543 */     return this.initialized;
/*     */   }
/*     */ 
/*     */   public synchronized void initialize()
/*     */   {
/* 581 */     if (!this.initialized)
/*     */     {
/* 588 */       if (this.config != null)
/*     */       {
/* 590 */         resolveConfigurationPassword();
/*     */ 
/* 592 */         String configAlgorithm = this.config.getAlgorithm();
/* 593 */         if (configAlgorithm != null) {
/* 594 */           CommonUtils.validateNotEmpty(configAlgorithm, "Algorithm cannot be set empty");
/*     */         }
/*     */ 
/* 598 */         Integer configKeyObtentionIterations = this.config.getKeyObtentionIterations();
/*     */ 
/* 600 */         if (configKeyObtentionIterations != null) {
/* 601 */           CommonUtils.validateIsTrue(configKeyObtentionIterations.intValue() > 0, "Number of iterations for key obtention must be greater than zero");
/*     */         }
/*     */ 
/* 606 */         SaltGenerator configSaltGenerator = this.config.getSaltGenerator();
/*     */ 
/* 608 */         String configProviderName = this.config.getProviderName();
/* 609 */         if (configProviderName != null) {
/* 610 */           CommonUtils.validateNotEmpty(configProviderName, "Provider name cannot be empty");
/*     */         }
/*     */ 
/* 614 */         Provider configProvider = this.config.getProvider();
/*     */ 
/* 616 */         this.algorithm = ((this.algorithmSet) || (configAlgorithm == null) ? this.algorithm : configAlgorithm);
/*     */ 
/* 619 */         this.keyObtentionIterations = ((this.iterationsSet) || (configKeyObtentionIterations == null) ? this.keyObtentionIterations : configKeyObtentionIterations.intValue());
/*     */ 
/* 624 */         this.saltGenerator = ((this.saltGeneratorSet) || (configSaltGenerator == null) ? this.saltGenerator : configSaltGenerator);
/*     */ 
/* 627 */         this.providerName = ((this.providerNameSet) || (configProviderName == null) ? this.providerName : configProviderName);
/*     */ 
/* 630 */         this.provider = ((this.providerSet) || (configProvider == null) ? this.provider : configProvider);
/*     */       }
/*     */ 
/* 640 */       if (this.saltGenerator == null) {
/* 641 */         this.saltGenerator = new RandomSaltGenerator();
/*     */       }
/*     */ 
/*     */       try
/*     */       {
/* 647 */         if (this.password == null) {
/* 648 */           throw new EncryptionInitializationException("Password not set for Password Based Encryptor");
/*     */         }
/*     */ 
/* 653 */         char[] normalizedPassword = Normalizer.normalizeToNfc(this.password);
/*     */ 
/* 658 */         PBEKeySpec pbeKeySpec = new PBEKeySpec(normalizedPassword);
/*     */ 
/* 661 */         cleanPassword(this.password);
/* 662 */         cleanPassword(normalizedPassword);
/*     */ 
/* 665 */         if (this.provider != null)
/*     */         {
/* 667 */           SecretKeyFactory factory = SecretKeyFactory.getInstance(this.algorithm, this.provider);
/*     */ 
/* 672 */           this.key = factory.generateSecret(pbeKeySpec);
/*     */ 
/* 674 */           this.encryptCipher = Cipher.getInstance(this.algorithm, this.provider);
/*     */ 
/* 676 */           this.decryptCipher = Cipher.getInstance(this.algorithm, this.provider);
/*     */         }
/* 679 */         else if (this.providerName != null)
/*     */         {
/* 681 */           SecretKeyFactory factory = SecretKeyFactory.getInstance(this.algorithm, this.providerName);
/*     */ 
/* 686 */           this.key = factory.generateSecret(pbeKeySpec);
/*     */ 
/* 688 */           this.encryptCipher = Cipher.getInstance(this.algorithm, this.providerName);
/*     */ 
/* 690 */           this.decryptCipher = Cipher.getInstance(this.algorithm, this.providerName);
/*     */         }
/*     */         else
/*     */         {
/* 695 */           SecretKeyFactory factory = SecretKeyFactory.getInstance(this.algorithm);
/*     */ 
/* 698 */           this.key = factory.generateSecret(pbeKeySpec);
/*     */ 
/* 700 */           this.encryptCipher = Cipher.getInstance(this.algorithm);
/* 701 */           this.decryptCipher = Cipher.getInstance(this.algorithm);
/*     */         }
/*     */       }
/*     */       catch (EncryptionInitializationException e)
/*     */       {
/* 706 */         throw e;
/*     */       } catch (Throwable t) {
/* 708 */         throw new EncryptionInitializationException(t);
/*     */       }
/*     */ 
/* 714 */       int algorithmBlockSize = this.encryptCipher.getBlockSize();
/* 715 */       if (algorithmBlockSize > 0) {
/* 716 */         this.saltSizeBytes = algorithmBlockSize;
/*     */       }
/*     */ 
/* 720 */       this.initialized = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   private synchronized void resolveConfigurationPassword()
/*     */   {
/* 730 */     if (!this.initialized)
/*     */     {
/* 732 */       if ((this.config != null) && (!this.passwordSet))
/*     */       {
/* 738 */         char[] configPassword = null;
/* 739 */         if ((this.config instanceof PBECleanablePasswordConfig)) {
/* 740 */           configPassword = ((PBECleanablePasswordConfig)this.config).getPasswordCharArray();
/*     */         } else {
/* 742 */           String configPwd = this.config.getPassword();
/* 743 */           if (configPwd != null) {
/* 744 */             configPassword = configPwd.toCharArray();
/*     */           }
/*     */         }
/*     */ 
/* 748 */         if (configPassword != null) {
/* 749 */           CommonUtils.validateIsTrue(configPassword.length > 0, "Password cannot be set empty");
/*     */         }
/*     */ 
/* 753 */         if (configPassword != null) {
/* 754 */           this.password = new char[configPassword.length];
/* 755 */           System.arraycopy(configPassword, 0, this.password, 0, configPassword.length);
/* 756 */           this.passwordSet = true;
/* 757 */           cleanPassword(configPassword);
/*     */         }
/*     */ 
/* 761 */         if ((this.config instanceof PBECleanablePasswordConfig))
/* 762 */           ((PBECleanablePasswordConfig)this.config).cleanPassword();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void cleanPassword(char[] password)
/*     */   {
/* 775 */     if (password != null)
/* 776 */       synchronized (password) {
/* 777 */         int pwdLength = password.length;
/* 778 */         for (int i = 0; i < pwdLength; i++)
/* 779 */           password[i] = '\000';
/*     */       }
/*     */   }
/*     */ 
/*     */   public byte[] encrypt(byte[] message)
/*     */     throws EncryptionOperationNotPossibleException
/*     */   {
/* 824 */     if (message == null) {
/* 825 */       return null;
/*     */     }
/*     */ 
/* 829 */     if (!isInitialized()) {
/* 830 */       initialize();
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 836 */       byte[] salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
/*     */ 
/* 842 */       PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, this.keyObtentionIterations);
/*     */ 
/* 845 */       byte[] encryptedMessage = null;
/* 846 */       synchronized (this.encryptCipher) {
/* 847 */         this.encryptCipher.init(1, this.key, parameterSpec);
/*     */ 
/* 849 */         encryptedMessage = this.encryptCipher.doFinal(message);
/*     */       }
/*     */ 
/* 855 */       if (this.saltGenerator.includePlainSaltInEncryptionResults())
/*     */       {
/* 858 */         return CommonUtils.appendArrays(salt, encryptedMessage);
/*     */       }
/*     */ 
/* 863 */       return encryptedMessage;
/*     */     }
/*     */     catch (InvalidKeyException e)
/*     */     {
/* 868 */       handleInvalidKeyException(e);
/* 869 */       throw new EncryptionOperationNotPossibleException();
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 873 */     throw new EncryptionOperationNotPossibleException();
/*     */   }
/*     */ 
/*     */   public byte[] decrypt(byte[] encryptedMessage)
/*     */     throws EncryptionOperationNotPossibleException
/*     */   {
/* 906 */     if (encryptedMessage == null) {
/* 907 */       return null;
/*     */     }
/*     */ 
/* 911 */     if (!isInitialized()) {
/* 912 */       initialize();
/*     */     }
/*     */ 
/* 915 */     if (this.saltGenerator.includePlainSaltInEncryptionResults())
/*     */     {
/* 917 */       if (encryptedMessage.length <= this.saltSizeBytes) {
/* 918 */         throw new EncryptionOperationNotPossibleException();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 928 */       byte[] salt = null;
/* 929 */       byte[] encryptedMessageKernel = null;
/* 930 */       if (this.saltGenerator.includePlainSaltInEncryptionResults())
/*     */       {
/* 932 */         int saltStart = 0;
/* 933 */         int saltSize = this.saltSizeBytes < encryptedMessage.length ? this.saltSizeBytes : encryptedMessage.length;
/*     */ 
/* 935 */         int encMesKernelStart = this.saltSizeBytes < encryptedMessage.length ? this.saltSizeBytes : encryptedMessage.length;
/*     */ 
/* 937 */         int encMesKernelSize = this.saltSizeBytes < encryptedMessage.length ? encryptedMessage.length - this.saltSizeBytes : 0;
/*     */ 
/* 940 */         salt = new byte[saltSize];
/* 941 */         encryptedMessageKernel = new byte[encMesKernelSize];
/*     */ 
/* 943 */         System.arraycopy(encryptedMessage, 0, salt, 0, saltSize);
/* 944 */         System.arraycopy(encryptedMessage, encMesKernelStart, encryptedMessageKernel, 0, encMesKernelSize);
/*     */       }
/*     */       else
/*     */       {
/* 948 */         salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
/* 949 */         encryptedMessageKernel = encryptedMessage;
/*     */       }
/*     */ 
/* 957 */       PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, this.keyObtentionIterations);
/*     */ 
/* 960 */       byte[] decryptedMessage = null;
/*     */ 
/* 962 */       synchronized (this.decryptCipher) {
/* 963 */         this.decryptCipher.init(2, this.key, parameterSpec);
/*     */ 
/* 965 */         decryptedMessage = this.decryptCipher.doFinal(encryptedMessageKernel);
/*     */       }
/*     */ 
/* 970 */       return decryptedMessage;
/*     */     }
/*     */     catch (InvalidKeyException e)
/*     */     {
/* 976 */       handleInvalidKeyException(e);
/* 977 */       throw new EncryptionOperationNotPossibleException();
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 981 */     throw new EncryptionOperationNotPossibleException();
/*     */   }
/*     */ 
/*     */   private void handleInvalidKeyException(InvalidKeyException e)
/*     */   {
/* 996 */     if ((e.getMessage() != null) && (e.getMessage().toUpperCase().indexOf("KEY SIZE") != -1))
/*     */     {
/* 999 */       throw new EncryptionOperationNotPossibleException("Encryption raised an exception. A possible cause is you are using strong encryption algorithms and you have not installed the Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files in this Java Virtual Machine");
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.StandardPBEByteEncryptor
 * JD-Core Version:    0.6.2
 */