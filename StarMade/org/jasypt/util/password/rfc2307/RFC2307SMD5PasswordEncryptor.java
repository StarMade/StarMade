/*     */ package org.jasypt.util.password.rfc2307;
/*     */ 
/*     */ import org.jasypt.digest.StandardStringDigester;
/*     */ import org.jasypt.util.password.PasswordEncryptor;
/*     */ 
/*     */ public final class RFC2307SMD5PasswordEncryptor
/*     */   implements PasswordEncryptor
/*     */ {
/*     */   private final StandardStringDigester digester;
/*     */ 
/*     */   public RFC2307SMD5PasswordEncryptor()
/*     */   {
/*  65 */     this.digester = new StandardStringDigester();
/*  66 */     this.digester.setAlgorithm("MD5");
/*  67 */     this.digester.setIterations(1);
/*  68 */     this.digester.setSaltSizeBytes(8);
/*  69 */     this.digester.setPrefix("{SMD5}");
/*  70 */     this.digester.setInvertPositionOfSaltInMessageBeforeDigesting(true);
/*  71 */     this.digester.setInvertPositionOfPlainSaltInEncryptionResults(true);
/*  72 */     this.digester.setUseLenientSaltSizeCheck(true);
/*     */   }
/*     */ 
/*     */   public void setSaltSizeBytes(int saltSizeBytes)
/*     */   {
/*  87 */     this.digester.setSaltSizeBytes(saltSizeBytes);
/*     */   }
/*     */ 
/*     */   public void setStringOutputType(String stringOutputType)
/*     */   {
/* 104 */     this.digester.setStringOutputType(stringOutputType);
/*     */   }
/*     */ 
/*     */   public String encryptPassword(String password)
/*     */   {
/* 116 */     return this.digester.digest(password);
/*     */   }
/*     */ 
/*     */   public boolean checkPassword(String plainPassword, String encryptedPassword)
/*     */   {
/* 137 */     return this.digester.matches(plainPassword, encryptedPassword);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.password.rfc2307.RFC2307SMD5PasswordEncryptor
 * JD-Core Version:    0.6.2
 */