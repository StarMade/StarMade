/*   1:    */package org.jasypt.salt;
/*   2:    */
/*   3:    */import java.io.UnsupportedEncodingException;
/*   4:    */import org.jasypt.commons.CommonUtils;
/*   5:    */import org.jasypt.exceptions.EncryptionInitializationException;
/*   6:    */
/*  46:    */public class FixedStringSaltGenerator
/*  47:    */  implements SaltGenerator
/*  48:    */{
/*  49:    */  private static final String DEFAULT_CHARSET = "UTF-8";
/*  50: 50 */  private String salt = null;
/*  51: 51 */  private String charset = "UTF-8";
/*  52:    */  
/*  53: 53 */  private byte[] saltBytes = null;
/*  54:    */  
/*  68:    */  public synchronized void setSalt(String salt)
/*  69:    */  {
/*  70: 70 */    CommonUtils.validateNotNull(salt, "Salt cannot be set null");
/*  71: 71 */    this.salt = salt;
/*  72:    */  }
/*  73:    */  
/*  79:    */  public synchronized void setCharset(String charset)
/*  80:    */  {
/*  81: 81 */    CommonUtils.validateNotNull(charset, "Charset cannot be set null");
/*  82: 82 */    this.charset = charset;
/*  83:    */  }
/*  84:    */  
/*  91:    */  public byte[] generateSalt(int lengthBytes)
/*  92:    */  {
/*  93: 93 */    if (this.salt == null) {
/*  94: 94 */      throw new EncryptionInitializationException("Salt has not been set");
/*  95:    */    }
/*  96:    */    
/*  97: 97 */    if (this.saltBytes == null) {
/*  98:    */      try {
/*  99: 99 */        this.saltBytes = this.salt.getBytes(this.charset);
/* 100:    */      } catch (UnsupportedEncodingException e) {
/* 101:101 */        throw new EncryptionInitializationException("Invalid charset specified: " + this.charset);
/* 102:    */      }
/* 103:    */    }
/* 104:    */    
/* 105:105 */    if (this.saltBytes.length < lengthBytes) {
/* 106:106 */      throw new EncryptionInitializationException("Requested salt larger than set");
/* 107:    */    }
/* 108:    */    
/* 109:109 */    byte[] generatedSalt = new byte[lengthBytes];
/* 110:110 */    System.arraycopy(this.saltBytes, 0, generatedSalt, 0, lengthBytes);
/* 111:111 */    return generatedSalt;
/* 112:    */  }
/* 113:    */  
/* 121:    */  public boolean includePlainSaltInEncryptionResults()
/* 122:    */  {
/* 123:123 */    return false;
/* 124:    */  }
/* 125:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.salt.FixedStringSaltGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */