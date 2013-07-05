/*    */ package org.jasypt.salt;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class ZeroSaltGenerator
/*    */   implements SaltGenerator
/*    */ {
/*    */   public byte[] generateSalt(int lengthBytes)
/*    */   {
/* 58 */     byte[] result = new byte[lengthBytes];
/* 59 */     Arrays.fill(result, (byte)0);
/* 60 */     return result;
/*    */   }
/*    */ 
/*    */   public boolean includePlainSaltInEncryptionResults()
/*    */   {
/* 72 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.salt.ZeroSaltGenerator
 * JD-Core Version:    0.6.2
 */