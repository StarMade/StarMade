/*    */ package org.jasypt.salt;
/*    */ 
/*    */ import org.jasypt.commons.CommonUtils;
/*    */ import org.jasypt.exceptions.EncryptionInitializationException;
/*    */ 
/*    */ public class FixedByteArraySaltGenerator
/*    */   implements SaltGenerator
/*    */ {
/* 45 */   private byte[] salt = null;
/*    */ 
/*    */   public synchronized void setSalt(byte[] salt)
/*    */   {
/* 62 */     CommonUtils.validateNotNull(salt, "Salt cannot be set null");
/* 63 */     this.salt = ((byte[])salt.clone());
/*    */   }
/*    */ 
/*    */   public byte[] generateSalt(int lengthBytes)
/*    */   {
/* 74 */     if (this.salt == null) {
/* 75 */       throw new EncryptionInitializationException("Salt has not been set");
/*    */     }
/*    */ 
/* 78 */     if (this.salt.length < lengthBytes) {
/* 79 */       throw new EncryptionInitializationException("Requested salt larger than set");
/*    */     }
/*    */ 
/* 82 */     byte[] generatedSalt = new byte[lengthBytes];
/* 83 */     System.arraycopy(this.salt, 0, generatedSalt, 0, lengthBytes);
/* 84 */     return generatedSalt;
/*    */   }
/*    */ 
/*    */   public boolean includePlainSaltInEncryptionResults()
/*    */   {
/* 96 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.salt.FixedByteArraySaltGenerator
 * JD-Core Version:    0.6.2
 */