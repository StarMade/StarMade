/*     */ package org.jasypt.encryption.pbe;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.security.Provider;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.encryption.pbe.config.PBEConfig;
/*     */ import org.jasypt.exceptions.EncryptionInitializationException;
/*     */ import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public final class StandardPBEBigDecimalEncryptor
/*     */   implements PBEBigDecimalCleanablePasswordEncryptor
/*     */ {
/*     */   private final StandardPBEByteEncryptor byteEncryptor;
/*     */ 
/*     */   public StandardPBEBigDecimalEncryptor()
/*     */   {
/* 140 */     this.byteEncryptor = new StandardPBEByteEncryptor();
/*     */   }
/*     */ 
/*     */   private StandardPBEBigDecimalEncryptor(StandardPBEByteEncryptor standardPBEByteEncryptor)
/*     */   {
/* 151 */     this.byteEncryptor = standardPBEByteEncryptor;
/*     */   }
/*     */ 
/*     */   public void setConfig(PBEConfig config)
/*     */   {
/* 181 */     this.byteEncryptor.setConfig(config);
/*     */   }
/*     */ 
/*     */   public void setAlgorithm(String algorithm)
/*     */   {
/* 200 */     this.byteEncryptor.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 219 */     this.byteEncryptor.setPassword(password);
/*     */   }
/*     */ 
/*     */   public void setPasswordCharArray(char[] password)
/*     */   {
/* 252 */     this.byteEncryptor.setPasswordCharArray(password);
/*     */   }
/*     */ 
/*     */   public void setKeyObtentionIterations(int keyObtentionIterations)
/*     */   {
/* 270 */     this.byteEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/*     */   }
/*     */ 
/*     */   public void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 283 */     this.byteEncryptor.setSaltGenerator(saltGenerator);
/*     */   }
/*     */ 
/*     */   public void setProviderName(String providerName)
/*     */   {
/* 315 */     this.byteEncryptor.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public void setProvider(Provider provider)
/*     */   {
/* 340 */     this.byteEncryptor.setProvider(provider);
/*     */   }
/*     */ 
/*     */   synchronized StandardPBEBigDecimalEncryptor[] cloneAndInitializeEncryptor(int size)
/*     */   {
/* 359 */     StandardPBEByteEncryptor[] byteEncryptorClones = this.byteEncryptor.cloneAndInitializeEncryptor(size);
/*     */ 
/* 362 */     StandardPBEBigDecimalEncryptor[] clones = new StandardPBEBigDecimalEncryptor[size];
/*     */ 
/* 364 */     clones[0] = this;
/*     */ 
/* 366 */     for (int i = 1; i < size; i++) {
/* 367 */       clones[i] = new StandardPBEBigDecimalEncryptor(byteEncryptorClones[i]);
/*     */     }
/*     */ 
/* 370 */     return clones;
/*     */   }
/*     */ 
/*     */   public boolean isInitialized()
/*     */   {
/* 398 */     return this.byteEncryptor.isInitialized();
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 434 */     this.byteEncryptor.initialize();
/*     */   }
/*     */ 
/*     */   public BigDecimal encrypt(BigDecimal message)
/*     */   {
/* 487 */     if (message == null) {
/* 488 */       return null;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 494 */       int scale = message.scale();
/*     */ 
/* 497 */       BigInteger unscaledMessage = message.unscaledValue();
/* 498 */       byte[] messageBytes = unscaledMessage.toByteArray();
/*     */ 
/* 501 */       byte[] encryptedMessage = this.byteEncryptor.encrypt(messageBytes);
/*     */ 
/* 508 */       byte[] encryptedMessageLengthBytes = NumberUtils.byteArrayFromInt(encryptedMessage.length);
/*     */ 
/* 512 */       byte[] encryptionResult = CommonUtils.appendArrays(encryptedMessage, encryptedMessageLengthBytes);
/*     */ 
/* 516 */       return new BigDecimal(new BigInteger(encryptionResult), scale);
/*     */     }
/*     */     catch (EncryptionInitializationException e) {
/* 519 */       throw e;
/*     */     } catch (EncryptionOperationNotPossibleException e) {
/* 521 */       throw e;
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 525 */     throw new EncryptionOperationNotPossibleException();
/*     */   }
/*     */ 
/*     */   public BigDecimal decrypt(BigDecimal encryptedMessage)
/*     */   {
/* 557 */     if (encryptedMessage == null) {
/* 558 */       return null;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 564 */       int scale = encryptedMessage.scale();
/*     */ 
/* 567 */       BigInteger unscaledEncryptedMessage = encryptedMessage.unscaledValue();
/*     */ 
/* 569 */       byte[] encryptedMessageBytes = unscaledEncryptedMessage.toByteArray();
/*     */ 
/* 573 */       encryptedMessageBytes = NumberUtils.processBigIntegerEncryptedByteArray(encryptedMessageBytes, encryptedMessage.signum());
/*     */ 
/* 578 */       byte[] message = this.byteEncryptor.decrypt(encryptedMessageBytes);
/*     */ 
/* 581 */       return new BigDecimal(new BigInteger(message), scale);
/*     */     }
/*     */     catch (EncryptionInitializationException e) {
/* 584 */       throw e;
/*     */     } catch (EncryptionOperationNotPossibleException e) {
/* 586 */       throw e;
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 590 */     throw new EncryptionOperationNotPossibleException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor
 * JD-Core Version:    0.6.2
 */