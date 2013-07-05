/*     */ package org.jasypt.util.numeric;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor;
/*     */ 
/*     */ public final class StrongDecimalNumberEncryptor
/*     */   implements DecimalNumberEncryptor
/*     */ {
/*     */   private final StandardPBEBigDecimalEncryptor encryptor;
/*     */ 
/*     */   public StrongDecimalNumberEncryptor()
/*     */   {
/*  71 */     this.encryptor = new StandardPBEBigDecimalEncryptor();
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
/*     */   public BigDecimal encrypt(BigDecimal number)
/*     */   {
/* 104 */     return this.encryptor.encrypt(number);
/*     */   }
/*     */ 
/*     */   public BigDecimal decrypt(BigDecimal encryptedNumber)
/*     */   {
/* 115 */     return this.encryptor.decrypt(encryptedNumber);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.numeric.StrongDecimalNumberEncryptor
 * JD-Core Version:    0.6.2
 */