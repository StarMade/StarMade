/*  1:   */package org.jasypt.salt;
/*  2:   */
/*  3:   */import org.jasypt.commons.CommonUtils;
/*  4:   */import org.jasypt.exceptions.EncryptionInitializationException;
/*  5:   */
/* 42:   */public class FixedByteArraySaltGenerator
/* 43:   */  implements SaltGenerator
/* 44:   */{
/* 45:45 */  private byte[] salt = null;
/* 46:   */  
/* 60:   */  public synchronized void setSalt(byte[] salt)
/* 61:   */  {
/* 62:62 */    CommonUtils.validateNotNull(salt, "Salt cannot be set null");
/* 63:63 */    this.salt = ((byte[])salt.clone());
/* 64:   */  }
/* 65:   */  
/* 72:   */  public byte[] generateSalt(int lengthBytes)
/* 73:   */  {
/* 74:74 */    if (this.salt == null) {
/* 75:75 */      throw new EncryptionInitializationException("Salt has not been set");
/* 76:   */    }
/* 77:   */    
/* 78:78 */    if (this.salt.length < lengthBytes) {
/* 79:79 */      throw new EncryptionInitializationException("Requested salt larger than set");
/* 80:   */    }
/* 81:   */    
/* 82:82 */    byte[] generatedSalt = new byte[lengthBytes];
/* 83:83 */    System.arraycopy(this.salt, 0, generatedSalt, 0, lengthBytes);
/* 84:84 */    return generatedSalt;
/* 85:   */  }
/* 86:   */  
/* 94:   */  public boolean includePlainSaltInEncryptionResults()
/* 95:   */  {
/* 96:96 */    return false;
/* 97:   */  }
/* 98:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.salt.FixedByteArraySaltGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */