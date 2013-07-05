/*    */ package org.jasypt.util.password;
/*    */ 
/*    */ import org.jasypt.digest.StandardStringDigester;
/*    */ 
/*    */ public final class BasicPasswordEncryptor
/*    */   implements PasswordEncryptor
/*    */ {
/*    */   private final StandardStringDigester digester;
/*    */ 
/*    */   public BasicPasswordEncryptor()
/*    */   {
/* 67 */     this.digester = new StandardStringDigester();
/* 68 */     this.digester.initialize();
/*    */   }
/*    */ 
/*    */   public String encryptPassword(String password)
/*    */   {
/* 80 */     return this.digester.digest(password);
/*    */   }
/*    */ 
/*    */   public boolean checkPassword(String plainPassword, String encryptedPassword)
/*    */   {
/* 95 */     return this.digester.matches(plainPassword, encryptedPassword);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.password.BasicPasswordEncryptor
 * JD-Core Version:    0.6.2
 */