/*     */ package org.jasypt.util.text;
/*     */ 
/*     */ import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
/*     */ 
/*     */ public final class StrongTextEncryptor
/*     */   implements TextEncryptor
/*     */ {
/*     */   private final StandardPBEStringEncryptor encryptor;
/*     */ 
/*     */   public StrongTextEncryptor()
/*     */   {
/*  74 */     this.encryptor = new StandardPBEStringEncryptor();
/*  75 */     this.encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/*  85 */     this.encryptor.setPassword(password);
/*     */   }
/*     */ 
/*     */   public void setPasswordCharArray(char[] password)
/*     */   {
/*  96 */     this.encryptor.setPasswordCharArray(password);
/*     */   }
/*     */ 
/*     */   public String encrypt(String message)
/*     */   {
/* 107 */     return this.encryptor.encrypt(message);
/*     */   }
/*     */ 
/*     */   public String decrypt(String encryptedMessage)
/*     */   {
/* 118 */     return this.encryptor.decrypt(encryptedMessage);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.text.StrongTextEncryptor
 * JD-Core Version:    0.6.2
 */