/*     */ package org.jasypt.encryption.pbe.config;
/*     */ 
/*     */ import java.security.Provider;
/*     */ import org.jasypt.exceptions.EncryptionInitializationException;
/*     */ import org.jasypt.exceptions.PasswordAlreadyCleanedException;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public class SimplePBEConfig
/*     */   implements PBEConfig, PBECleanablePasswordConfig
/*     */ {
/*  69 */   private String algorithm = null;
/*  70 */   private char[] password = null;
/*  71 */   private Integer keyObtentionIterations = null;
/*  72 */   private SaltGenerator saltGenerator = null;
/*  73 */   private String providerName = null;
/*  74 */   private Provider provider = null;
/*  75 */   private Integer poolSize = null;
/*     */ 
/*  77 */   private boolean passwordCleaned = false;
/*     */ 
/*     */   public void setAlgorithm(String algorithm)
/*     */   {
/* 106 */     this.algorithm = algorithm;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 120 */     if (this.password != null)
/*     */     {
/* 122 */       cleanPassword();
/*     */     }
/* 124 */     if (password == null)
/* 125 */       this.password = null;
/*     */     else
/* 127 */       this.password = password.toCharArray();
/*     */   }
/*     */ 
/*     */   public void setPasswordCharArray(char[] password)
/*     */   {
/* 156 */     if (this.password != null)
/*     */     {
/* 158 */       cleanPassword();
/*     */     }
/* 160 */     if (password == null) {
/* 161 */       this.password = null;
/*     */     } else {
/* 163 */       this.password = new char[password.length];
/* 164 */       System.arraycopy(password, 0, this.password, 0, password.length);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setKeyObtentionIterations(Integer keyObtentionIterations)
/*     */   {
/* 179 */     this.keyObtentionIterations = keyObtentionIterations;
/*     */   }
/*     */ 
/*     */   public void setKeyObtentionIterations(String keyObtentionIterations)
/*     */   {
/* 195 */     if (keyObtentionIterations != null)
/*     */       try {
/* 197 */         this.keyObtentionIterations = new Integer(keyObtentionIterations);
/*     */       } catch (NumberFormatException e) {
/* 199 */         throw new EncryptionInitializationException(e);
/*     */       }
/*     */     else
/* 202 */       this.keyObtentionIterations = null;
/*     */   }
/*     */ 
/*     */   public void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 221 */     this.saltGenerator = saltGenerator;
/*     */   }
/*     */ 
/*     */   public void setSaltGeneratorClassName(String saltGeneratorClassName)
/*     */   {
/* 241 */     if (saltGeneratorClassName != null)
/*     */       try {
/* 243 */         Class saltGeneratorClass = Thread.currentThread().getContextClassLoader().loadClass(saltGeneratorClassName);
/*     */ 
/* 245 */         this.saltGenerator = ((SaltGenerator)saltGeneratorClass.newInstance());
/*     */       }
/*     */       catch (Exception e) {
/* 248 */         throw new EncryptionInitializationException(e);
/*     */       }
/*     */     else
/* 251 */       this.saltGenerator = null;
/*     */   }
/*     */ 
/*     */   public void setProviderName(String providerName)
/*     */   {
/* 278 */     this.providerName = providerName;
/*     */   }
/*     */ 
/*     */   public void setProvider(Provider provider)
/*     */   {
/* 310 */     this.provider = provider;
/*     */   }
/*     */ 
/*     */   public void setProviderClassName(String providerClassName)
/*     */   {
/* 341 */     if (providerClassName != null)
/*     */       try {
/* 343 */         Class providerClass = Thread.currentThread().getContextClassLoader().loadClass(providerClassName);
/*     */ 
/* 345 */         this.provider = ((Provider)providerClass.newInstance());
/*     */       } catch (Exception e) {
/* 347 */         throw new EncryptionInitializationException(e);
/*     */       }
/*     */     else
/* 350 */       this.provider = null;
/*     */   }
/*     */ 
/*     */   public void setPoolSize(Integer poolSize)
/*     */   {
/* 378 */     this.poolSize = poolSize;
/*     */   }
/*     */ 
/*     */   public void setPoolSize(String poolSize)
/*     */   {
/* 402 */     if (poolSize != null)
/*     */       try {
/* 404 */         this.poolSize = new Integer(poolSize);
/*     */       } catch (NumberFormatException e) {
/* 406 */         throw new EncryptionInitializationException(e);
/*     */       }
/*     */     else
/* 409 */       this.poolSize = null;
/*     */   }
/*     */ 
/*     */   public String getAlgorithm()
/*     */   {
/* 418 */     return this.algorithm;
/*     */   }
/*     */ 
/*     */   public String getPassword()
/*     */   {
/* 423 */     if (this.passwordCleaned) {
/* 424 */       throw new PasswordAlreadyCleanedException();
/*     */     }
/* 426 */     return new String(this.password);
/*     */   }
/*     */ 
/*     */   public char[] getPasswordCharArray()
/*     */   {
/* 431 */     if (this.passwordCleaned) {
/* 432 */       throw new PasswordAlreadyCleanedException();
/*     */     }
/* 434 */     char[] result = new char[this.password.length];
/* 435 */     System.arraycopy(this.password, 0, result, 0, this.password.length);
/* 436 */     return result;
/*     */   }
/*     */ 
/*     */   public Integer getKeyObtentionIterations()
/*     */   {
/* 441 */     return this.keyObtentionIterations;
/*     */   }
/*     */ 
/*     */   public SaltGenerator getSaltGenerator()
/*     */   {
/* 446 */     return this.saltGenerator;
/*     */   }
/*     */ 
/*     */   public String getProviderName() {
/* 450 */     return this.providerName;
/*     */   }
/*     */ 
/*     */   public Provider getProvider() {
/* 454 */     return this.provider;
/*     */   }
/*     */ 
/*     */   public Integer getPoolSize() {
/* 458 */     return this.poolSize;
/*     */   }
/*     */ 
/*     */   public void cleanPassword()
/*     */   {
/* 464 */     if (this.password != null) {
/* 465 */       int pwdLength = this.password.length;
/* 466 */       for (int i = 0; i < pwdLength; i++) {
/* 467 */         this.password[i] = '\000';
/*     */       }
/* 469 */       this.password = new char[0];
/*     */     }
/* 471 */     this.passwordCleaned = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.SimplePBEConfig
 * JD-Core Version:    0.6.2
 */