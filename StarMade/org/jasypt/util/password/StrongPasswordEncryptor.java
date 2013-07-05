/*    */ package org.jasypt.util.password;
/*    */ 
/*    */ import org.jasypt.digest.StandardStringDigester;
/*    */ 
/*    */ public final class StrongPasswordEncryptor
/*    */   implements PasswordEncryptor
/*    */ {
/*    */   private final StandardStringDigester digester;
/*    */ 
/*    */   public StrongPasswordEncryptor()
/*    */   {
/* 68 */     this.digester = new StandardStringDigester();
/* 69 */     this.digester.setAlgorithm("SHA-256");
/* 70 */     this.digester.setIterations(100000);
/* 71 */     this.digester.setSaltSizeBytes(16);
/* 72 */     this.digester.initialize();
/*    */   }
/*    */ 
/*    */   public String encryptPassword(String password)
/*    */   {
/* 84 */     return this.digester.digest(password);
/*    */   }
/*    */ 
/*    */   public boolean checkPassword(String plainPassword, String encryptedPassword)
/*    */   {
/* 99 */     return this.digester.matches(plainPassword, encryptedPassword);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.password.StrongPasswordEncryptor
 * JD-Core Version:    0.6.2
 */