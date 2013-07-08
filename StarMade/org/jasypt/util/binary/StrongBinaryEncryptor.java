package org.jasypt.util.binary;

import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;

public final class StrongBinaryEncryptor
  implements BinaryEncryptor
{
  private final StandardPBEByteEncryptor encryptor = new StandardPBEByteEncryptor();
  
  public StrongBinaryEncryptor()
  {
    this.encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
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
 * Qualified Name:     org.jasypt.util.binary.StrongBinaryEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */