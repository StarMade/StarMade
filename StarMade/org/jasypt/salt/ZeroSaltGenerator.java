package org.jasypt.salt;

import java.util.Arrays;

public class ZeroSaltGenerator
  implements SaltGenerator
{
  public byte[] generateSalt(int lengthBytes)
  {
    byte[] result = new byte[lengthBytes];
    Arrays.fill(result, (byte)0);
    return result;
  }
  
  public boolean includePlainSaltInEncryptionResults()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.salt.ZeroSaltGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */