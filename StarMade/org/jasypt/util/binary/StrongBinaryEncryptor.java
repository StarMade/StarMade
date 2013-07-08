/*   1:    */package org.jasypt.util.binary;
/*   2:    */
/*   3:    */import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
/*   4:    */
/*  62:    */public final class StrongBinaryEncryptor
/*  63:    */  implements BinaryEncryptor
/*  64:    */{
/*  65:    */  private final StandardPBEByteEncryptor encryptor;
/*  66:    */  
/*  67:    */  public StrongBinaryEncryptor()
/*  68:    */  {
/*  69: 69 */    this.encryptor = new StandardPBEByteEncryptor();
/*  70: 70 */    this.encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
/*  71:    */  }
/*  72:    */  
/*  78:    */  public void setPassword(String password)
/*  79:    */  {
/*  80: 80 */    this.encryptor.setPassword(password);
/*  81:    */  }
/*  82:    */  
/*  89:    */  public void setPasswordCharArray(char[] password)
/*  90:    */  {
/*  91: 91 */    this.encryptor.setPasswordCharArray(password);
/*  92:    */  }
/*  93:    */  
/* 100:    */  public byte[] encrypt(byte[] binary)
/* 101:    */  {
/* 102:102 */    return this.encryptor.encrypt(binary);
/* 103:    */  }
/* 104:    */  
/* 111:    */  public byte[] decrypt(byte[] encryptedBinary)
/* 112:    */  {
/* 113:113 */    return this.encryptor.decrypt(encryptedBinary);
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.binary.StrongBinaryEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */