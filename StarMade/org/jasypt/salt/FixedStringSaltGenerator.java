package org.jasypt.salt;

import java.io.UnsupportedEncodingException;
import org.jasypt.commons.CommonUtils;
import org.jasypt.exceptions.EncryptionInitializationException;

public class FixedStringSaltGenerator
  implements SaltGenerator
{
  private static final String DEFAULT_CHARSET = "UTF-8";
  private String salt = null;
  private String charset = "UTF-8";
  private byte[] saltBytes = null;
  
  public synchronized void setSalt(String salt)
  {
    CommonUtils.validateNotNull(salt, "Salt cannot be set null");
    this.salt = salt;
  }
  
  public synchronized void setCharset(String charset)
  {
    CommonUtils.validateNotNull(charset, "Charset cannot be set null");
    this.charset = charset;
  }
  
  public byte[] generateSalt(int lengthBytes)
  {
    if (this.salt == null) {
      throw new EncryptionInitializationException("Salt has not been set");
    }
    if (this.saltBytes == null) {
      try
      {
        this.saltBytes = this.salt.getBytes(this.charset);
      }
      catch (UnsupportedEncodingException local_e)
      {
        throw new EncryptionInitializationException("Invalid charset specified: " + this.charset);
      }
    }
    if (this.saltBytes.length < lengthBytes) {
      throw new EncryptionInitializationException("Requested salt larger than set");
    }
    byte[] local_e = new byte[lengthBytes];
    System.arraycopy(this.saltBytes, 0, local_e, 0, lengthBytes);
    return local_e;
  }
  
  public boolean includePlainSaltInEncryptionResults()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.salt.FixedStringSaltGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */