/*  1:   */package org.jasypt.salt;
/*  2:   */
/*  3:   */import java.util.Arrays;
/*  4:   */
/* 53:   */public class ZeroSaltGenerator
/* 54:   */  implements SaltGenerator
/* 55:   */{
/* 56:   */  public byte[] generateSalt(int lengthBytes)
/* 57:   */  {
/* 58:58 */    byte[] result = new byte[lengthBytes];
/* 59:59 */    Arrays.fill(result, (byte)0);
/* 60:60 */    return result;
/* 61:   */  }
/* 62:   */  
/* 70:   */  public boolean includePlainSaltInEncryptionResults()
/* 71:   */  {
/* 72:72 */    return false;
/* 73:   */  }
/* 74:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.salt.ZeroSaltGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */