/*     */ package org.jasypt.util.numeric;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;
/*     */ 
/*     */ public final class StrongIntegerNumberEncryptor
/*     */   implements IntegerNumberEncryptor
/*     */ {
/*     */   private final StandardPBEBigIntegerEncryptor encryptor;
/*     */ 
/*     */   public StrongIntegerNumberEncryptor()
/*     */   {
/*  71 */     this.encryptor = new StandardPBEBigIntegerEncryptor();
/*  72 */     this.encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/*  82 */     this.encryptor.setPassword(password);
/*     */   }
/*     */ 
/*     */   public void setPasswordCharArray(char[] password)
/*     */   {
/*  93 */     this.encryptor.setPasswordCharArray(password);
/*     */   }
/*     */ 
/*     */   public BigInteger encrypt(BigInteger number)
/*     */   {
/* 104 */     return this.encryptor.encrypt(number);
/*     */   }
/*     */ 
/*     */   public BigInteger decrypt(BigInteger encryptedNumber)
/*     */   {
/* 115 */     return this.encryptor.decrypt(encryptedNumber);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.numeric.StrongIntegerNumberEncryptor
 * JD-Core Version:    0.6.2
 */