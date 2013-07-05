/*     */ package org.jasypt.util.numeric;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor;
/*     */ 
/*     */ public final class BasicDecimalNumberEncryptor
/*     */   implements DecimalNumberEncryptor
/*     */ {
/*     */   private final StandardPBEBigDecimalEncryptor encryptor;
/*     */ 
/*     */   public BasicDecimalNumberEncryptor()
/*     */   {
/*  70 */     this.encryptor = new StandardPBEBigDecimalEncryptor();
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
/*     */   public BigDecimal encrypt(BigDecimal number)
/*     */   {
/* 103 */     return this.encryptor.encrypt(number);
/*     */   }
/*     */ 
/*     */   public BigDecimal decrypt(BigDecimal encryptedNumber)
/*     */   {
/* 114 */     return this.encryptor.decrypt(encryptedNumber);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.numeric.BasicDecimalNumberEncryptor
 * JD-Core Version:    0.6.2
 */