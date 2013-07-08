package org.jasypt.util.binary;

import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;

public final class BasicBinaryEncryptor
  implements BinaryEncryptor
{
  private final StandardPBEByteEncryptor encryptor = new StandardPBEByteEncryptor();
  
  public BasicBinaryEncryptor()
  {
    this.encryptor.setAlgorithm("PBEWithMD5AndDES");
  }
  
  public void setPassword(String password)
  {
    this.encryptor.setPassword(password);
  }
  
  public void setPasswordCharArray(char[] password)
  {
    this.encryptor.setPasswordCharArray(password);
  }
  
  public byte[] encrypt(byte[] binary)
  {
    return this.encryptor.encrypt(binary);
  }
  
  public byte[] decrypt(byte[] encryptedBinary)
  {
    return this.encryptor.decrypt(encryptedBinary);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.util.binary.BasicBinaryEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */