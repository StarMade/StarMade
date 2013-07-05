/*     */ package org.jasypt.util.password.rfc2307;
/*     */ 
/*     */ import org.jasypt.digest.StandardStringDigester;
/*     */ import org.jasypt.util.password.PasswordEncryptor;
/*     */ 
/*     */ public final class RFC2307SHAPasswordEncryptor
/*     */   implements PasswordEncryptor
/*     */ {
/*     */   private final StandardStringDigester digester;
/*     */ 
/*     */   public RFC2307SHAPasswordEncryptor()
/*     */   {
/*  62 */     this.digester = new StandardStringDigester();
/*  63 */     this.digester.setAlgorithm("SHA-1");
/*  64 */     this.digester.setIterations(1);
/*  65 */     this.digester.setSaltSizeBytes(0);
/*  66 */     this.digester.setPrefix("{SHA}");
/*     */   }
/*     */ 
/*     */   public void setStringOutputType(String stringOutputType)
/*     */   {
/*  83 */     this.digester.setStringOutputType(stringOutputType);
/*     */   }
/*     */ 
/*     */   public String encryptPassword(String password)
/*     */   {
/*  95 */     return this.digester.digest(password);
/*     */   }
/*     */ 
/*     */   public boolean checkPassword(String plainPassword, String encryptedPassword)
/*     */   {
/* 110 */     return this.digester.matches(plainPassword, encryptedPassword);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.password.rfc2307.RFC2307SHAPasswordEncryptor
 * JD-Core Version:    0.6.2
 */