/*     */ package org.jasypt.digest.config;
/*     */ 
/*     */ import java.security.Provider;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public class EnvironmentDigesterConfig extends SimpleDigesterConfig
/*     */ {
/*  54 */   private String algorithmEnvName = null;
/*  55 */   private String iterationsEnvName = null;
/*  56 */   private String saltSizeBytesEnvName = null;
/*  57 */   private String saltGeneratorClassNameEnvName = null;
/*  58 */   private String providerNameEnvName = null;
/*  59 */   private String providerClassNameEnvName = null;
/*  60 */   private String invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
/*  61 */   private String invertPositionOfPlainSaltInEncryptionResultsEnvName = null;
/*  62 */   private String useLenientSaltSizeCheckEnvName = null;
/*  63 */   private String poolSizeEnvName = null;
/*     */ 
/*  65 */   private String algorithmSysPropertyName = null;
/*  66 */   private String iterationsSysPropertyName = null;
/*  67 */   private String saltSizeBytesSysPropertyName = null;
/*  68 */   private String saltGeneratorClassNameSysPropertyName = null;
/*  69 */   private String providerNameSysPropertyName = null;
/*  70 */   private String providerClassNameSysPropertyName = null;
/*  71 */   private String invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
/*  72 */   private String invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
/*  73 */   private String useLenientSaltSizeCheckSysPropertyName = null;
/*  74 */   private String poolSizeSysPropertyName = null;
/*     */ 
/*     */   public String getAlgorithmEnvName()
/*     */   {
/*  95 */     return this.algorithmEnvName;
/*     */   }
/*     */ 
/*     */   public void setAlgorithmEnvName(String algorithmEnvName)
/*     */   {
/* 106 */     this.algorithmEnvName = algorithmEnvName;
/* 107 */     if (algorithmEnvName == null) {
/* 108 */       super.setAlgorithm(null);
/*     */     } else {
/* 110 */       this.algorithmSysPropertyName = null;
/* 111 */       super.setAlgorithm(System.getenv(algorithmEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getAlgorithmSysPropertyName()
/*     */   {
/* 123 */     return this.algorithmSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setAlgorithmSysPropertyName(String algorithmSysPropertyName)
/*     */   {
/* 134 */     this.algorithmSysPropertyName = algorithmSysPropertyName;
/* 135 */     if (algorithmSysPropertyName == null) {
/* 136 */       super.setAlgorithm(null);
/*     */     } else {
/* 138 */       this.algorithmEnvName = null;
/* 139 */       super.setAlgorithm(System.getProperty(algorithmSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getIterationsEnvName()
/*     */   {
/* 151 */     return this.iterationsEnvName;
/*     */   }
/*     */ 
/*     */   public void setIterationsEnvName(String iterationsEnvName)
/*     */   {
/* 162 */     this.iterationsEnvName = iterationsEnvName;
/* 163 */     if (iterationsEnvName == null) {
/* 164 */       super.setIterations((String)null);
/*     */     } else {
/* 166 */       this.iterationsSysPropertyName = null;
/* 167 */       super.setIterations(System.getenv(iterationsEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getIterationsSysPropertyName()
/*     */   {
/* 179 */     return this.iterationsSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setIterationsSysPropertyName(String iterationsSysPropertyName)
/*     */   {
/* 190 */     this.iterationsSysPropertyName = iterationsSysPropertyName;
/* 191 */     if (iterationsSysPropertyName == null) {
/* 192 */       super.setIterations((String)null);
/*     */     } else {
/* 194 */       this.iterationsEnvName = null;
/* 195 */       super.setIterations(System.getProperty(iterationsSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSaltSizeBytesEnvName()
/*     */   {
/* 207 */     return this.saltSizeBytesEnvName;
/*     */   }
/*     */ 
/*     */   public void setSaltSizeBytesEnvName(String saltSizeBytesEnvName)
/*     */   {
/* 218 */     this.saltSizeBytesEnvName = saltSizeBytesEnvName;
/* 219 */     if (saltSizeBytesEnvName == null) {
/* 220 */       super.setSaltSizeBytes((String)null);
/*     */     } else {
/* 222 */       this.saltSizeBytesSysPropertyName = null;
/* 223 */       super.setSaltSizeBytes(System.getenv(saltSizeBytesEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSaltSizeBytesSysPropertyName()
/*     */   {
/* 235 */     return this.saltSizeBytesSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setSaltSizeBytesSysPropertyName(String saltSizeBytesSysPropertyName)
/*     */   {
/* 246 */     this.saltSizeBytesSysPropertyName = saltSizeBytesSysPropertyName;
/* 247 */     if (saltSizeBytesSysPropertyName == null) {
/* 248 */       super.setSaltSizeBytes((Integer)null);
/*     */     } else {
/* 250 */       this.saltSizeBytesEnvName = null;
/* 251 */       super.setSaltSizeBytes(System.getProperty(saltSizeBytesSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSaltGeneratorClassNameEnvName()
/*     */   {
/* 266 */     return this.saltGeneratorClassNameEnvName;
/*     */   }
/*     */ 
/*     */   public void setSaltGeneratorClassNameEnvName(String saltGeneratorClassNameEnvName)
/*     */   {
/* 285 */     this.saltGeneratorClassNameEnvName = saltGeneratorClassNameEnvName;
/* 286 */     if (saltGeneratorClassNameEnvName == null) {
/* 287 */       super.setSaltGenerator(null);
/*     */     } else {
/* 289 */       this.saltGeneratorClassNameSysPropertyName = null;
/* 290 */       String saltGeneratorClassName = System.getenv(saltGeneratorClassNameEnvName);
/* 291 */       super.setSaltGeneratorClassName(saltGeneratorClassName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSaltGeneratorClassNameSysPropertyName()
/*     */   {
/* 305 */     return this.saltGeneratorClassNameSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setSaltGeneratorClassNameSysPropertyName(String saltGeneratorClassNameSysPropertyName)
/*     */   {
/* 324 */     this.saltGeneratorClassNameSysPropertyName = saltGeneratorClassNameSysPropertyName;
/* 325 */     if (saltGeneratorClassNameSysPropertyName == null) {
/* 326 */       super.setSaltGenerator(null);
/*     */     } else {
/* 328 */       this.saltGeneratorClassNameEnvName = null;
/* 329 */       String saltGeneratorClassName = System.getProperty(saltGeneratorClassNameSysPropertyName);
/*     */ 
/* 331 */       super.setSaltGeneratorClassName(saltGeneratorClassName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProviderNameEnvName()
/*     */   {
/* 345 */     return this.providerNameEnvName;
/*     */   }
/*     */ 
/*     */   public void setProviderNameEnvName(String providerNameEnvName)
/*     */   {
/* 360 */     this.providerNameEnvName = providerNameEnvName;
/* 361 */     if (providerNameEnvName == null) {
/* 362 */       super.setProviderName(null);
/*     */     } else {
/* 364 */       this.providerNameSysPropertyName = null;
/* 365 */       super.setProviderName(System.getenv(providerNameEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProviderNameSysPropertyName()
/*     */   {
/* 379 */     return this.providerNameSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setProviderNameSysPropertyName(String providerNameSysPropertyName)
/*     */   {
/* 392 */     this.providerNameSysPropertyName = providerNameSysPropertyName;
/* 393 */     if (providerNameSysPropertyName == null) {
/* 394 */       super.setProviderName(null);
/*     */     } else {
/* 396 */       this.providerNameEnvName = null;
/* 397 */       super.setProviderName(System.getProperty(providerNameSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProviderClassNameEnvName()
/*     */   {
/* 411 */     return this.providerClassNameEnvName;
/*     */   }
/*     */ 
/*     */   public void setProviderClassNameEnvName(String providerClassNameEnvName)
/*     */   {
/* 430 */     this.providerClassNameEnvName = providerClassNameEnvName;
/* 431 */     if (providerClassNameEnvName == null) {
/* 432 */       super.setProvider(null);
/*     */     } else {
/* 434 */       this.providerClassNameSysPropertyName = null;
/* 435 */       String providerClassName = System.getenv(providerClassNameEnvName);
/* 436 */       super.setProviderClassName(providerClassName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProviderClassNameSysPropertyName()
/*     */   {
/* 450 */     return this.providerClassNameSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setProviderClassNameSysPropertyName(String providerClassNameSysPropertyName)
/*     */   {
/* 469 */     this.providerClassNameSysPropertyName = providerClassNameSysPropertyName;
/* 470 */     if (providerClassNameSysPropertyName == null) {
/* 471 */       super.setProvider(null);
/*     */     } else {
/* 473 */       this.providerClassNameEnvName = null;
/* 474 */       String providerClassName = System.getProperty(providerClassNameSysPropertyName);
/* 475 */       super.setProviderClassName(providerClassName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getInvertPositionOfSaltInMessageBeforeDigestingEnvName()
/*     */   {
/* 493 */     return this.invertPositionOfSaltInMessageBeforeDigestingEnvName;
/*     */   }
/*     */ 
/*     */   public void setInvertPositionOfSaltInMessageBeforeDigestingEnvName(String invertPositionOfSaltInMessageBeforeDigestingEnvName)
/*     */   {
/* 510 */     this.invertPositionOfSaltInMessageBeforeDigestingEnvName = invertPositionOfSaltInMessageBeforeDigestingEnvName;
/* 511 */     if (invertPositionOfSaltInMessageBeforeDigestingEnvName == null) {
/* 512 */       super.setInvertPositionOfSaltInMessageBeforeDigesting(null);
/*     */     } else {
/* 514 */       this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
/* 515 */       super.setInvertPositionOfSaltInMessageBeforeDigesting(CommonUtils.getStandardBooleanValue(System.getenv(invertPositionOfSaltInMessageBeforeDigestingEnvName)));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getInvertPositionOfSaltInMessageBeforeDigestingSysPropertyName()
/*     */   {
/* 537 */     return this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setInvertPositionOfSaltInMessageBeforeDigestingSysPropertyName(String invertPositionOfSaltInMessageBeforeDigestingSysPropertyName)
/*     */   {
/* 554 */     this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = invertPositionOfSaltInMessageBeforeDigestingSysPropertyName;
/* 555 */     if (invertPositionOfSaltInMessageBeforeDigestingSysPropertyName == null) {
/* 556 */       super.setInvertPositionOfSaltInMessageBeforeDigesting(null);
/*     */     } else {
/* 558 */       this.invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
/* 559 */       super.setInvertPositionOfSaltInMessageBeforeDigesting(CommonUtils.getStandardBooleanValue(System.getProperty(invertPositionOfSaltInMessageBeforeDigestingSysPropertyName)));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getInvertPositionOfPlainSaltInEncryptionResultsEnvName()
/*     */   {
/* 581 */     return this.invertPositionOfPlainSaltInEncryptionResultsEnvName;
/*     */   }
/*     */ 
/*     */   public void setInvertPositionOfPlainSaltInEncryptionResultsEnvName(String invertPositionOfPlainSaltInEncryptionResultsEnvName)
/*     */   {
/* 598 */     this.invertPositionOfPlainSaltInEncryptionResultsEnvName = invertPositionOfPlainSaltInEncryptionResultsEnvName;
/* 599 */     if (invertPositionOfPlainSaltInEncryptionResultsEnvName == null) {
/* 600 */       super.setInvertPositionOfPlainSaltInEncryptionResults(null);
/*     */     } else {
/* 602 */       this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
/* 603 */       super.setInvertPositionOfPlainSaltInEncryptionResults(CommonUtils.getStandardBooleanValue(System.getenv(invertPositionOfPlainSaltInEncryptionResultsEnvName)));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getInvertPositionOfPlainSaltInEncryptionResultsSysPropertyName()
/*     */   {
/* 621 */     return this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setInvertPositionOfPlainSaltInEncryptionResultsSysPropertyName(String invertPositionOfPlainSaltInEncryptionResultsSysPropertyName)
/*     */   {
/* 638 */     this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = invertPositionOfPlainSaltInEncryptionResultsSysPropertyName;
/* 639 */     if (invertPositionOfPlainSaltInEncryptionResultsSysPropertyName == null) {
/* 640 */       super.setInvertPositionOfPlainSaltInEncryptionResults(null);
/*     */     } else {
/* 642 */       this.invertPositionOfPlainSaltInEncryptionResultsEnvName = null;
/* 643 */       super.setInvertPositionOfPlainSaltInEncryptionResults(CommonUtils.getStandardBooleanValue(System.getProperty(invertPositionOfPlainSaltInEncryptionResultsSysPropertyName)));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getUseLenientSaltSizeCheckEnvName()
/*     */   {
/* 666 */     return this.useLenientSaltSizeCheckEnvName;
/*     */   }
/*     */ 
/*     */   public void setUseLenientSaltSizeCheckEnvName(String useLenientSaltSizeCheckEnvName)
/*     */   {
/* 683 */     this.useLenientSaltSizeCheckEnvName = useLenientSaltSizeCheckEnvName;
/* 684 */     if (useLenientSaltSizeCheckEnvName == null) {
/* 685 */       super.setUseLenientSaltSizeCheck(null);
/*     */     } else {
/* 687 */       this.useLenientSaltSizeCheckSysPropertyName = null;
/* 688 */       super.setUseLenientSaltSizeCheck(CommonUtils.getStandardBooleanValue(System.getenv(useLenientSaltSizeCheckEnvName)));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getUseLenientSaltSizeCheckSysPropertyName()
/*     */   {
/* 706 */     return this.useLenientSaltSizeCheckSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setUseLenientSaltSizeCheckSysPropertyName(String useLenientSaltSizeCheckSysPropertyName)
/*     */   {
/* 723 */     this.useLenientSaltSizeCheckSysPropertyName = useLenientSaltSizeCheckSysPropertyName;
/* 724 */     if (useLenientSaltSizeCheckSysPropertyName == null) {
/* 725 */       super.setUseLenientSaltSizeCheck(null);
/*     */     } else {
/* 727 */       this.useLenientSaltSizeCheckEnvName = null;
/* 728 */       super.setUseLenientSaltSizeCheck(CommonUtils.getStandardBooleanValue(System.getProperty(useLenientSaltSizeCheckSysPropertyName)));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getPoolSizeEnvName()
/*     */   {
/* 752 */     return this.poolSizeEnvName;
/*     */   }
/*     */ 
/*     */   public void setPoolSizeEnvName(String poolSizeEnvName)
/*     */   {
/* 769 */     this.poolSizeEnvName = poolSizeEnvName;
/* 770 */     if (poolSizeEnvName == null) {
/* 771 */       super.setPoolSize((String)null);
/*     */     } else {
/* 773 */       this.poolSizeSysPropertyName = null;
/* 774 */       super.setPoolSize(System.getenv(poolSizeEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getPoolSizeSysPropertyName()
/*     */   {
/* 790 */     return this.poolSizeSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setPoolSizeSysPropertyName(String poolSizeSysPropertyName)
/*     */   {
/* 807 */     this.poolSizeSysPropertyName = poolSizeSysPropertyName;
/* 808 */     if (poolSizeSysPropertyName == null) {
/* 809 */       super.setPoolSize((String)null);
/*     */     } else {
/* 811 */       this.poolSizeEnvName = null;
/* 812 */       super.setPoolSize(System.getProperty(poolSizeSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setAlgorithm(String algorithm)
/*     */   {
/* 823 */     this.algorithmEnvName = null;
/* 824 */     this.algorithmSysPropertyName = null;
/* 825 */     super.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public void setIterations(Integer iterations)
/*     */   {
/* 831 */     this.iterationsEnvName = null;
/* 832 */     this.iterationsSysPropertyName = null;
/* 833 */     super.setIterations(iterations);
/*     */   }
/*     */ 
/*     */   public void setIterations(String iterations)
/*     */   {
/* 838 */     this.iterationsEnvName = null;
/* 839 */     this.iterationsSysPropertyName = null;
/* 840 */     super.setIterations(iterations);
/*     */   }
/*     */ 
/*     */   public void setSaltSizeBytes(Integer saltSizeBytes)
/*     */   {
/* 846 */     this.saltSizeBytesEnvName = null;
/* 847 */     this.saltSizeBytesSysPropertyName = null;
/* 848 */     super.setSaltSizeBytes(saltSizeBytes);
/*     */   }
/*     */ 
/*     */   public void setSaltSizeBytes(String saltSizeBytes)
/*     */   {
/* 853 */     this.saltSizeBytesEnvName = null;
/* 854 */     this.saltSizeBytesSysPropertyName = null;
/* 855 */     super.setSaltSizeBytes(saltSizeBytes);
/*     */   }
/*     */ 
/*     */   public void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 861 */     this.saltGeneratorClassNameEnvName = null;
/* 862 */     this.saltGeneratorClassNameSysPropertyName = null;
/* 863 */     super.setSaltGenerator(saltGenerator);
/*     */   }
/*     */ 
/*     */   public void setSaltGeneratorClassName(String saltGeneratorClassName)
/*     */   {
/* 868 */     this.saltGeneratorClassNameEnvName = null;
/* 869 */     this.saltGeneratorClassNameSysPropertyName = null;
/* 870 */     super.setSaltGeneratorClassName(saltGeneratorClassName);
/*     */   }
/*     */ 
/*     */   public void setProviderName(String providerName)
/*     */   {
/* 875 */     this.providerNameEnvName = null;
/* 876 */     this.providerNameSysPropertyName = null;
/* 877 */     super.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public void setProvider(Provider provider)
/*     */   {
/* 882 */     this.providerClassNameEnvName = null;
/* 883 */     this.providerClassNameSysPropertyName = null;
/* 884 */     super.setProvider(provider);
/*     */   }
/*     */ 
/*     */   public void setProviderClassName(String providerClassName)
/*     */   {
/* 889 */     this.providerClassNameEnvName = null;
/* 890 */     this.providerClassNameSysPropertyName = null;
/* 891 */     super.setProviderClassName(providerClassName);
/*     */   }
/*     */ 
/*     */   public void setInvertPositionOfPlainSaltInEncryptionResults(Boolean invertPositionOfPlainSaltInEncryptionResults)
/*     */   {
/* 897 */     this.invertPositionOfPlainSaltInEncryptionResultsEnvName = null;
/* 898 */     this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
/* 899 */     super.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
/*     */   }
/*     */ 
/*     */   public void setInvertPositionOfSaltInMessageBeforeDigesting(Boolean invertPositionOfSaltInMessageBeforeDigesting)
/*     */   {
/* 905 */     this.invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
/* 906 */     this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
/* 907 */     super.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
/*     */   }
/*     */ 
/*     */   public void setUseLenientSaltSizeCheck(Boolean useLenientSaltSizeCheck)
/*     */   {
/* 913 */     this.useLenientSaltSizeCheckEnvName = null;
/* 914 */     this.useLenientSaltSizeCheckSysPropertyName = null;
/* 915 */     super.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
/*     */   }
/*     */ 
/*     */   public void setPoolSize(Integer poolSize)
/*     */   {
/* 920 */     this.poolSizeEnvName = null;
/* 921 */     this.poolSizeSysPropertyName = null;
/* 922 */     super.setPoolSize(poolSize);
/*     */   }
/*     */ 
/*     */   public void setPoolSize(String poolSize)
/*     */   {
/* 927 */     this.poolSizeEnvName = null;
/* 928 */     this.poolSizeSysPropertyName = null;
/* 929 */     super.setPoolSize(poolSize);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.config.EnvironmentDigesterConfig
 * JD-Core Version:    0.6.2
 */