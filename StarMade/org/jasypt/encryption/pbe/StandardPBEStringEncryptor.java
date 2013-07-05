/*     */ package org.jasypt.encryption.pbe;
/*     */ 
/*     */ import java.security.Provider;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
/*     */ import org.jasypt.encryption.pbe.config.PBEConfig;
/*     */ import org.jasypt.encryption.pbe.config.StringPBEConfig;
/*     */ import org.jasypt.exceptions.AlreadyInitializedException;
/*     */ import org.jasypt.exceptions.EncryptionInitializationException;
/*     */ import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*     */ import org.jasypt.salt.SaltGenerator;
/*     */ 
/*     */ public final class StandardPBEStringEncryptor
/*     */   implements PBEStringCleanablePasswordEncryptor
/*     */ {
/*     */   private static final String MESSAGE_CHARSET = "UTF-8";
/*     */   private static final String ENCRYPTED_MESSAGE_CHARSET = "US-ASCII";
/*     */   public static final String DEFAULT_STRING_OUTPUT_TYPE = "base64";
/* 181 */   private StringPBEConfig stringPBEConfig = null;
/*     */ 
/* 185 */   private String stringOutputType = "base64";
/* 186 */   private boolean stringOutputTypeBase64 = true;
/*     */ 
/* 194 */   private boolean stringOutputTypeSet = false;
/*     */   private final StandardPBEByteEncryptor byteEncryptor;
/*     */   private final Base64 base64;
/*     */ 
/*     */   public StandardPBEStringEncryptor()
/*     */   {
/* 212 */     this.byteEncryptor = new StandardPBEByteEncryptor();
/* 213 */     this.base64 = new Base64();
/*     */   }
/*     */ 
/*     */   private StandardPBEStringEncryptor(StandardPBEByteEncryptor standardPBEByteEncryptor)
/*     */   {
/* 224 */     this.byteEncryptor = standardPBEByteEncryptor;
/* 225 */     this.base64 = new Base64();
/*     */   }
/*     */ 
/*     */   public synchronized void setConfig(PBEConfig config)
/*     */   {
/* 257 */     this.byteEncryptor.setConfig(config);
/* 258 */     if ((config != null) && ((config instanceof StringPBEConfig)))
/* 259 */       this.stringPBEConfig = ((StringPBEConfig)config);
/*     */   }
/*     */ 
/*     */   public void setAlgorithm(String algorithm)
/*     */   {
/* 279 */     this.byteEncryptor.setAlgorithm(algorithm);
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 298 */     this.byteEncryptor.setPassword(password);
/*     */   }
/*     */ 
/*     */   public void setPasswordCharArray(char[] password)
/*     */   {
/* 331 */     this.byteEncryptor.setPasswordCharArray(password);
/*     */   }
/*     */ 
/*     */   public void setKeyObtentionIterations(int keyObtentionIterations)
/*     */   {
/* 349 */     this.byteEncryptor.setKeyObtentionIterations(keyObtentionIterations);
/*     */   }
/*     */ 
/*     */   public void setSaltGenerator(SaltGenerator saltGenerator)
/*     */   {
/* 362 */     this.byteEncryptor.setSaltGenerator(saltGenerator);
/*     */   }
/*     */ 
/*     */   public void setProviderName(String providerName)
/*     */   {
/* 394 */     this.byteEncryptor.setProviderName(providerName);
/*     */   }
/*     */ 
/*     */   public void setProvider(Provider provider)
/*     */   {
/* 419 */     this.byteEncryptor.setProvider(provider);
/*     */   }
/*     */ 
/*     */   public synchronized void setStringOutputType(String stringOutputType)
/*     */   {
/* 441 */     CommonUtils.validateNotEmpty(stringOutputType, "String output type cannot be set empty");
/*     */ 
/* 443 */     if (isInitialized()) {
/* 444 */       throw new AlreadyInitializedException();
/*     */     }
/* 446 */     this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/*     */ 
/* 450 */     this.stringOutputTypeSet = true;
/*     */   }
/*     */ 
/*     */   synchronized StandardPBEStringEncryptor[] cloneAndInitializeEncryptor(int size)
/*     */   {
/* 469 */     StandardPBEByteEncryptor[] byteEncryptorClones = this.byteEncryptor.cloneAndInitializeEncryptor(size);
/*     */ 
/* 472 */     initializeSpecifics();
/*     */ 
/* 474 */     StandardPBEStringEncryptor[] clones = new StandardPBEStringEncryptor[size];
/*     */ 
/* 476 */     clones[0] = this;
/*     */ 
/* 478 */     for (int i = 1; i < size; i++) {
/* 479 */       clones[i] = new StandardPBEStringEncryptor(byteEncryptorClones[i]);
/* 480 */       if (CommonUtils.isNotEmpty(this.stringOutputType)) {
/* 481 */         clones[i].setStringOutputType(this.stringOutputType);
/*     */       }
/*     */     }
/*     */ 
/* 485 */     return clones;
/*     */   }
/*     */ 
/*     */   public boolean isInitialized()
/*     */   {
/* 513 */     return this.byteEncryptor.isInitialized();
/*     */   }
/*     */ 
/*     */   public synchronized void initialize()
/*     */   {
/* 551 */     if (!isInitialized()) {
/* 552 */       initializeSpecifics();
/* 553 */       this.byteEncryptor.initialize();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initializeSpecifics()
/*     */   {
/* 567 */     if (this.stringPBEConfig != null)
/*     */     {
/* 569 */       String configStringOutputType = this.stringPBEConfig.getStringOutputType();
/*     */ 
/* 572 */       this.stringOutputType = ((this.stringOutputTypeSet) || (configStringOutputType == null) ? this.stringOutputType : configStringOutputType);
/*     */     }
/*     */ 
/* 578 */     this.stringOutputTypeBase64 = "base64".equalsIgnoreCase(this.stringOutputType);
/*     */   }
/*     */ 
/*     */   public String encrypt(String message)
/*     */   {
/* 625 */     if (message == null) {
/* 626 */       return null;
/*     */     }
/*     */ 
/* 630 */     if (!isInitialized()) {
/* 631 */       initialize();
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 639 */       byte[] messageBytes = message.getBytes("UTF-8");
/*     */ 
/* 642 */       byte[] encryptedMessage = this.byteEncryptor.encrypt(messageBytes);
/*     */ 
/* 646 */       String result = null;
/* 647 */       if (this.stringOutputTypeBase64) {
/* 648 */         encryptedMessage = this.base64.encode(encryptedMessage);
/* 649 */         result = new String(encryptedMessage, "US-ASCII");
/*     */       }
/* 651 */       return CommonUtils.toHexadecimal(encryptedMessage);
/*     */     }
/*     */     catch (EncryptionInitializationException e)
/*     */     {
/* 657 */       throw e;
/*     */     } catch (EncryptionOperationNotPossibleException e) {
/* 659 */       throw e;
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 663 */     throw new EncryptionOperationNotPossibleException();
/*     */   }
/*     */ 
/*     */   public String decrypt(String encryptedMessage)
/*     */   {
/* 699 */     if (encryptedMessage == null) {
/* 700 */       return null;
/*     */     }
/*     */ 
/* 704 */     if (!isInitialized()) {
/* 705 */       initialize();
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 710 */       byte[] encryptedMessageBytes = null;
/*     */ 
/* 714 */       if (this.stringOutputTypeBase64) {
/* 715 */         encryptedMessageBytes = encryptedMessage.getBytes("US-ASCII");
/*     */ 
/* 717 */         encryptedMessageBytes = this.base64.decode(encryptedMessageBytes);
/*     */       }
/*     */       else {
/* 720 */         encryptedMessageBytes = CommonUtils.fromHexadecimal(encryptedMessage);
/*     */       }
/*     */ 
/* 725 */       byte[] message = this.byteEncryptor.decrypt(encryptedMessageBytes);
/*     */ 
/* 730 */       return new String(message, "UTF-8");
/*     */     }
/*     */     catch (EncryptionInitializationException e) {
/* 733 */       throw e;
/*     */     } catch (EncryptionOperationNotPossibleException e) {
/* 735 */       throw e;
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 739 */     throw new EncryptionOperationNotPossibleException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.StandardPBEStringEncryptor
 * JD-Core Version:    0.6.2
 */