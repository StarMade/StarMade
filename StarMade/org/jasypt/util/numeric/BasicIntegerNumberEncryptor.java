/*     */ package org.jasypt.util.numeric;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;
/*     */ 
/*     */ public final class BasicIntegerNumberEncryptor
/*     */   implements IntegerNumberEncryptor
/*     */ {
/*     */   private final StandardPBEBigIntegerEncryptor encryptor;
/*     */ 
/*     */   public BasicIntegerNumberEncryptor()
/*     */   {
/*  70 */     this.encryptor = new StandardPBEBigIntegerEncryptor();
/*  71 */     this.encryptor.setAlgorithm("PBEWithMD5AndDES");
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/*  81 */     this.encryptor.setPassword(password);
/*     */   }
/*     */ 
/*     */   public void setPasswordCharArray(char[] password)
/*     */   {
/*  92 */     this.encryptor.setPasswordCharArray(password);
/*     */   }
/*     */ 
/*     */   public BigInteger encrypt(BigInteger number)
/*     */   {
/* 103 */     return this.encryptor.encrypt(number);
/*     */   }
/*     */ 
/*     */   public BigInteger decrypt(BigInteger encryptedNumber)
/*     */   {
/* 114 */     return this.encryptor.decrypt(encryptedNumber);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.numeric.BasicIntegerNumberEncryptor
 * JD-Core Version:    0.6.2
 */