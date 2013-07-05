/*     */ package org.jasypt.util.text;
/*     */ 
/*     */ import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
/*     */ 
/*     */ public final class BasicTextEncryptor
/*     */   implements TextEncryptor
/*     */ {
/*     */   private final StandardPBEStringEncryptor encryptor;
/*     */ 
/*     */   public BasicTextEncryptor()
/*     */   {
/*  68 */     this.encryptor = new StandardPBEStringEncryptor();
/*  69 */     this.encryptor.setAlgorithm("PBEWithMD5AndDES");
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/*  79 */     this.encryptor.setPassword(password);
/*     */   }
/*     */ 
/*     */   public void setPasswordCharArray(char[] password)
/*     */   {
/*  90 */     this.encryptor.setPasswordCharArray(password);
/*     */   }
/*     */ 
/*     */   public String encrypt(String message)
/*     */   {
/* 101 */     return this.encryptor.encrypt(message);
/*     */   }
/*     */ 
/*     */   public String decrypt(String encryptedMessage)
/*     */   {
/* 112 */     return this.encryptor.decrypt(encryptedMessage);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.text.BasicTextEncryptor
 * JD-Core Version:    0.6.2
 */