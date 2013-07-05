/*     */ package org.jasypt.encryption.pbe.config;
/*     */ 
/*     */ import java.security.Provider;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public class EnvironmentPBEConfig extends SimplePBEConfig
/*     */ {
/*  53 */   private String algorithmEnvName = null;
/*  54 */   private String keyObtentionIterationsEnvName = null;
/*  55 */   private String passwordEnvName = null;
/*  56 */   private String saltGeneratorClassNameEnvName = null;
/*  57 */   private String providerNameEnvName = null;
/*  58 */   private String providerClassNameEnvName = null;
/*  59 */   private String poolSizeEnvName = null;
/*     */ 
/*  61 */   private String algorithmSysPropertyName = null;
/*  62 */   private String keyObtentionIterationsSysPropertyName = null;
/*  63 */   private String passwordSysPropertyName = null;
/*  64 */   private String saltGeneratorClassNameSysPropertyName = null;
/*  65 */   private String providerNameSysPropertyName = null;
/*  66 */   private String providerClassNameSysPropertyName = null;
/*  67 */   private String poolSizeSysPropertyName = null;
/*     */ 
/*     */   public String getAlgorithmEnvName()
/*     */   {
/*  88 */     return this.algorithmEnvName;
/*     */   }
/*     */ 
/*     */   public void setAlgorithmEnvName(String algorithmEnvName)
/*     */   {
/*  99 */     this.algorithmEnvName = algorithmEnvName;
/* 100 */     if (algorithmEnvName == null) {
/* 101 */       super.setAlgorithm(null);
/*     */     } else {
/* 103 */       this.algorithmSysPropertyName = null;
/* 104 */       super.setAlgorithm(System.getenv(algorithmEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getAlgorithmSysPropertyName()
/*     */   {
/* 116 */     return this.algorithmSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setAlgorithmSysPropertyName(String algorithmSysPropertyName)
/*     */   {
/* 127 */     this.algorithmSysPropertyName = algorithmSysPropertyName;
/* 128 */     if (algorithmSysPropertyName == null) {
/* 129 */       super.setAlgorithm(null);
/*     */     } else {
/* 131 */       this.algorithmEnvName = null;
/* 132 */       super.setAlgorithm(System.getProperty(algorithmSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getKeyObtentionIterationsEnvName()
/*     */   {
/* 144 */     return this.keyObtentionIterationsEnvName;
/*     */   }
/*     */ 
/*     */   public void setKeyObtentionIterationsEnvName(String keyObtentionIterationsEnvName)
/*     */   {
/* 155 */     this.keyObtentionIterationsEnvName = keyObtentionIterationsEnvName;
/* 156 */     if (keyObtentionIterationsEnvName == null) {
/* 157 */       super.setKeyObtentionIterations((Integer)null);
/*     */     } else {
/* 159 */       this.keyObtentionIterationsSysPropertyName = null;
/* 160 */       super.setKeyObtentionIterations(System.getenv(keyObtentionIterationsEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getKeyObtentionIterationsSysPropertyName()
/*     */   {
/* 173 */     return this.keyObtentionIterationsSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setKeyObtentionIterationsSysPropertyName(String keyObtentionIterationsSysPropertyName)
/*     */   {
/* 184 */     this.keyObtentionIterationsSysPropertyName = keyObtentionIterationsSysPropertyName;
/* 185 */     if (keyObtentionIterationsSysPropertyName == null) {
/* 186 */       super.setKeyObtentionIterations((Integer)null);
/*     */     } else {
/* 188 */       this.keyObtentionIterationsEnvName = null;
/* 189 */       super.setKeyObtentionIterations(System.getProperty(keyObtentionIterationsSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getPasswordEnvName()
/*     */   {
/* 202 */     return this.passwordEnvName;
/*     */   }
/*     */ 
/*     */   public void setPasswordEnvName(String passwordEnvName)
/*     */   {
/* 213 */     this.passwordEnvName = passwordEnvName;
/* 214 */     if (passwordEnvName == null) {
/* 215 */       super.setPassword(null);
/*     */     } else {
/* 217 */       this.passwordSysPropertyName = null;
/* 218 */       super.setPassword(System.getenv(passwordEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getPasswordSysPropertyName()
/*     */   {
/* 230 */     return this.passwordSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setPasswordSysPropertyName(String passwordSysPropertyName)
/*     */   {
/* 241 */     this.passwordSysPropertyName = passwordSysPropertyName;
/* 242 */     if (passwordSysPropertyName == null) {
/* 243 */       super.setPassword(null);
/*     */     } else {
/* 245 */       this.passwordEnvName = null;
/* 246 */       super.setPassword(System.getProperty(passwordSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSaltGeneratorClassNameEnvName()
/*     */   {
/* 260 */     return this.saltGeneratorClassNameEnvName;
/*     */   }
/*     */ 
/*     */   public void setSaltGeneratorClassNameEnvName(String saltGeneratorClassNameEnvName)
/*     */   {
/* 279 */     this.saltGeneratorClassNameEnvName = saltGeneratorClassNameEnvName;
/* 280 */     if (saltGeneratorClassNameEnvName == null) {
/* 281 */       super.setSaltGenerator(null);
/*     */     } else {
/* 283 */       this.saltGeneratorClassNameSysPropertyName = null;
/* 284 */       String saltGeneratorClassName = System.getenv(saltGeneratorClassNameEnvName);
/* 285 */       super.setSaltGeneratorClassName(saltGeneratorClassName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSaltGeneratorClassNameSysPropertyName()
/*     */   {
/* 299 */     return this.saltGeneratorClassNameSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setSaltGeneratorClassNameSysPropertyName(String saltGeneratorClassNameSysPropertyName)
/*     */   {
/* 318 */     this.saltGeneratorClassNameSysPropertyName = saltGeneratorClassNameSysPropertyName;
/* 319 */     if (saltGeneratorClassNameSysPropertyName == null) {
/* 320 */       super.setSaltGenerator(null);
/*     */     } else {
/* 322 */       this.saltGeneratorClassNameEnvName = null;
/* 323 */       String saltGeneratorClassName = System.getProperty(saltGeneratorClassNameSysPropertyName);
/* 324 */       super.setSaltGeneratorClassName(saltGeneratorClassName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProviderNameEnvName()
/*     */   {
/* 338 */     return this.providerNameEnvName;
/*     */   }
/*     */ 
/*     */   public void setProviderNameEnvName(String providerNameEnvName)
/*     */   {
/* 353 */     this.providerNameEnvName = providerNameEnvName;
/* 354 */     if (providerNameEnvName == null) {
/* 355 */       super.setProviderName(null);
/*     */     } else {
/* 357 */       this.providerNameSysPropertyName = null;
/* 358 */       super.setProviderName(System.getenv(providerNameEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProviderNameSysPropertyName()
/*     */   {
/* 372 */     return this.providerNameSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setProviderNameSysPropertyName(String providerNameSysPropertyName)
/*     */   {
/* 385 */     this.providerNameSysPropertyName = providerNameSysPropertyName;
/* 386 */     if (providerNameSysPropertyName == null) {
/* 387 */       super.setProviderName(null);
/*     */     } else {
/* 389 */       this.providerNameEnvName = null;
/* 390 */       super.setProviderName(System.getProperty(providerNameSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProviderClassNameEnvName()
/*     */   {
/* 405 */     return this.providerClassNameEnvName;
/*     */   }
/*     */ 
/*     */   public void setProviderClassNameEnvName(String providerClassNameEnvName)
/*     */   {
/* 424 */     this.providerClassNameEnvName = providerClassNameEnvName;
/* 425 */     if (providerClassNameEnvName == null) {
/* 426 */       super.setProvider(null);
/*     */     } else {
/* 428 */       this.providerClassNameSysPropertyName = null;
/* 429 */       String providerClassName = System.getenv(providerClassNameEnvName);
/* 430 */       super.setProviderClassName(providerClassName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProviderClassNameSysPropertyName()
/*     */   {
/* 444 */     return this.providerClassNameSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setProviderClassNameSysPropertyName(String providerClassNameSysPropertyName)
/*     */   {
/* 463 */     this.providerClassNameSysPropertyName = providerClassNameSysPropertyName;
/* 464 */     if (providerClassNameSysPropertyName == null) {
/* 465 */       super.setProvider(null);
/*     */     } else {
/* 467 */       this.providerClassNameEnvName = null;
/* 468 */       String providerClassName = System.getProperty(providerClassNameSysPropertyName);
/* 469 */       super.setProviderClassName(providerClassName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getPoolSizeEnvName()
/*     */   {
/* 493 */     return this.poolSizeEnvName;
/*     */   }
/*     */ 
/*     */   public void setPoolSizeEnvName(String poolSizeEnvName)
/*     */   {
/* 510 */     this.poolSizeEnvName = poolSizeEnvName;
/* 511 */     if (poolSizeEnvName == null) {
/* 512 */       super.setPoolSize((String)null);
/*     */     } else {
/* 514 */       this.poolSizeSysPropertyName = null;
/* 515 */       super.setPoolSize(System.getenv(poolSizeEnvName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getPoolSizeSysPropertyName()
/*     */   {
/* 531 */     return this.poolSizeSysPropertyName;
/*     */   }
/*     */ 
/*     */   public void setPoolSizeSysPropertyName(String poolSizeSysPropertyName)
/*     */   {
/* 548 */     this.poolSizeSysPropertyName = poolSizeSysPropertyName;
/* 549 */     if (poolSizeSysPropertyName == null) {
/* 550 */       super.setPoolSize((String)null);
/*     */     } else {
/* 552 */       this.poolSizeEnvName = null;
/* 553 */       super.setPoolSize(System.getProperty(poolSizeSysPropertyName));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setAlgorithm(String algorithm)
/*     */   {
/* 567 */     this.algorithmEnvName = null;
/* 568 */     this.algorithmSysPropertyName = null;
/* 569 */     super.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public void setKeyObtentionIterations(Integer keyObtentionIterations)
/*     */   {
/* 575 */     this.keyObtentionIterationsEnvName = null;
/* 576 */     this.keyObtentionIterationsSysPropertyName = null;
/* 577 */     super.setKeyObtentionIterations(keyObtentionIterations);
/*     */   }
/*     */ 
/*     */   public void setKeyObtentionIterations(String keyObtentionIterations)
/*     */   {
/* 582 */     this.keyObtentionIterationsEnvName = null;
/* 583 */     this.keyObtentionIterationsSysPropertyName = null;
/* 584 */     super.setKeyObtentionIterations(keyObtentionIterations);
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 589 */     this.passwordEnvName = null;
/* 590 */     this.passwordSysPropertyName = null;
/* 591 */     super.setPassword(password);
/*     */   }
/*     */ 
/*     */   public void setPasswordCharArray(char[] password)
/*     */   {
/* 596 */     this.passwordEnvName = null;
/* 597 */     this.passwordSysPropertyName = null;
/* 598 */     super.setPasswordCharArray(password);
/*     */   }
/*     */ 
/*     */   public void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 603 */     this.saltGeneratorClassNameEnvName = null;
/* 604 */     this.saltGeneratorClassNameSysPropertyName = null;
/* 605 */     super.setSaltGenerator(saltGenerator);
/*     */   }
/*     */ 
/*     */   public void setSaltGeneratorClassName(String saltGeneratorClassName)
/*     */   {
/* 610 */     this.saltGeneratorClassNameEnvName = null;
/* 611 */     this.saltGeneratorClassNameSysPropertyName = null;
/* 612 */     super.setSaltGeneratorClassName(saltGeneratorClassName);
/*     */   }
/*     */ 
/*     */   public void setProviderName(String providerName)
/*     */   {
/* 617 */     this.providerNameEnvName = null;
/* 618 */     this.providerNameSysPropertyName = null;
/* 619 */     super.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public void setProvider(Provider provider)
/*     */   {
/* 625 */     this.providerClassNameEnvName = null;
/* 626 */     this.providerClassNameSysPropertyName = null;
/* 627 */     super.setProvider(provider);
/*     */   }
/*     */ 
/*     */   public void setProviderClassName(String providerClassName)
/*     */   {
/* 632 */     this.providerClassNameEnvName = null;
/* 633 */     this.providerClassNameSysPropertyName = null;
/* 634 */     super.setProviderClassName(providerClassName);
/*     */   }
/*     */ 
/*     */   public void setPoolSize(Integer poolSize)
/*     */   {
/* 639 */     this.poolSizeEnvName = null;
/* 640 */     this.poolSizeSysPropertyName = null;
/* 641 */     super.setPoolSize(poolSize);
/*     */   }
/*     */ 
/*     */   public void setPoolSize(String poolSize)
/*     */   {
/* 646 */     this.poolSizeEnvName = null;
/* 647 */     this.poolSizeSysPropertyName = null;
/* 648 */     super.setPoolSize(poolSize);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.EnvironmentPBEConfig
 * JD-Core Version:    0.6.2
 */