/*     */ package org.jasypt.encryption.pbe;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import java.security.Provider;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.encryption.pbe.config.PBEConfig;
/*     */ import org.jasypt.exceptions.EncryptionInitializationException;
/*     */ import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public final class StandardPBEBigIntegerEncryptor
/*     */   implements PBEBigIntegerCleanablePasswordEncryptor
/*     */ {
/*     */   private final StandardPBEByteEncryptor byteEncryptor;
/*     */ 
/*     */   public StandardPBEBigIntegerEncryptor()
/*     */   {
/* 139 */     this.byteEncryptor = new StandardPBEByteEncryptor();
/*     */   }
/*     */ 
/*     */   private StandardPBEBigIntegerEncryptor(StandardPBEByteEncryptor standardPBEByteEncryptor)
/*     */   {
/* 150 */     this.byteEncryptor = standardPBEByteEncryptor;
/*     */   }
/*     */ 
/*     */   public void setConfig(PBEConfig config)
/*     */   {
/* 180 */     this.byteEncryptor.setConfig(config);
/*     */   }
/*     */ 
/*     */   public void setAlgorithm(String algorithm)
/*     */   {
/* 199 */     this.byteEncryptor.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 218 */     this.byteEncryptor.setPassword(password);
/*     */   }
/*     */ 
/*     */   public void setPasswordCharArray(char[] password)
/*     */   {
/* 251 */     this.byteEncryptor.setPasswordCharArray(password);
/*     */   }
/*     */ 
/*     */   public void setKeyObtentionIterations(int keyObtentionIterations)
/*     */   {
/* 269 */     this.byteEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/*     */   }
/*     */ 
/*     */   public void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 282 */     this.byteEncryptor.setSaltGenerator(saltGenerator);
/*     */   }
/*     */ 
/*     */   public void setProviderName(String providerName)
/*     */   {
/* 314 */     this.byteEncryptor.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public void setProvider(Provider provider)
/*     */   {
/* 339 */     this.byteEncryptor.setProvider(provider);
/*     */   }
/*     */ 
/*     */   synchronized StandardPBEBigIntegerEncryptor[] cloneAndInitializeEncryptor(int size)
/*     */   {
/* 358 */     StandardPBEByteEncryptor[] byteEncryptorClones = this.byteEncryptor.cloneAndInitializeEncryptor(size);
/*     */ 
/* 361 */     StandardPBEBigIntegerEncryptor[] clones = new StandardPBEBigIntegerEncryptor[size];
/*     */ 
/* 363 */     clones[0] = this;
/*     */ 
/* 365 */     for (int i = 1; i < size; i++) {
/* 366 */       clones[i] = new StandardPBEBigIntegerEncryptor(byteEncryptorClones[i]);
/*     */     }
/*     */ 
/* 369 */     return clones;
/*     */   }
/*     */ 
/*     */   public boolean isInitialized()
/*     */   {
/* 397 */     return this.byteEncryptor.isInitialized();
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 433 */     this.byteEncryptor.initialize();
/*     */   }
/*     */ 
/*     */   public BigInteger encrypt(BigInteger message)
/*     */   {
/* 481 */     if (message == null) {
/* 482 */       return null;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 488 */       byte[] messageBytes = message.toByteArray();
/*     */ 
/* 491 */       byte[] encryptedMessage = this.byteEncryptor.encrypt(messageBytes);
/*     */ 
/* 498 */       byte[] encryptedMessageLengthBytes = NumberUtils.byteArrayFromInt(encryptedMessage.length);
/*     */ 
/* 502 */       byte[] encryptionResult = CommonUtils.appendArrays(encryptedMessage, encryptedMessageLengthBytes);
/*     */ 
/* 506 */       return new BigInteger(encryptionResult);
/*     */     }
/*     */     catch (EncryptionInitializationException e) {
/* 509 */       throw e;
/*     */     } catch (EncryptionOperationNotPossibleException e) {
/* 511 */       throw e;
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 515 */     throw new EncryptionOperationNotPossibleException();
/*     */   }
/*     */ 
/*     */   public BigInteger decrypt(BigInteger encryptedMessage)
/*     */   {
/* 547 */     if (encryptedMessage == null) {
/* 548 */       return null;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 554 */       byte[] encryptedMessageBytes = encryptedMessage.toByteArray();
/*     */ 
/* 557 */       encryptedMessageBytes = NumberUtils.processBigIntegerEncryptedByteArray(encryptedMessageBytes, encryptedMessage.signum());
/*     */ 
/* 562 */       byte[] message = this.byteEncryptor.decrypt(encryptedMessageBytes);
/*     */ 
/* 565 */       return new BigInteger(message);
/*     */     }
/*     */     catch (EncryptionInitializationException e) {
/* 568 */       throw e;
/*     */     } catch (EncryptionOperationNotPossibleException e) {
/* 570 */       throw e;
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 574 */     throw new EncryptionOperationNotPossibleException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor
 * JD-Core Version:    0.6.2
 */