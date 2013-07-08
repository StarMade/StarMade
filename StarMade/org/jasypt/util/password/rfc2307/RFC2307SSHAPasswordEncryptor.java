/*   1:    */package org.jasypt.util.password.rfc2307;
/*   2:    */
/*   3:    */import org.jasypt.digest.StandardStringDigester;
/*   4:    */import org.jasypt.util.password.PasswordEncryptor;
/*   5:    */
/*  58:    */public final class RFC2307SSHAPasswordEncryptor
/*  59:    */  implements PasswordEncryptor
/*  60:    */{
/*  61:    */  private final StandardStringDigester digester;
/*  62:    */  
/*  63:    */  public RFC2307SSHAPasswordEncryptor()
/*  64:    */  {
/*  65: 65 */    this.digester = new StandardStringDigester();
/*  66: 66 */    this.digester.setAlgorithm("SHA-1");
/*  67: 67 */    this.digester.setIterations(1);
/*  68: 68 */    this.digester.setSaltSizeBytes(8);
/*  69: 69 */    this.digester.setPrefix("{SSHA}");
/*  70: 70 */    this.digester.setInvertPositionOfSaltInMessageBeforeDigesting(true);
/*  71: 71 */    this.digester.setInvertPositionOfPlainSaltInEncryptionResults(true);
/*  72: 72 */    this.digester.setUseLenientSaltSizeCheck(true);
/*  73:    */  }
/*  74:    */  
/*  85:    */  public void setSaltSizeBytes(int saltSizeBytes)
/*  86:    */  {
/*  87: 87 */    this.digester.setSaltSizeBytes(saltSizeBytes);
/*  88:    */  }
/*  89:    */  
/* 102:    */  public void setStringOutputType(String stringOutputType)
/* 103:    */  {
/* 104:104 */    this.digester.setStringOutputType(stringOutputType);
/* 105:    */  }
/* 106:    */  
/* 114:    */  public String encryptPassword(String password)
/* 115:    */  {
/* 116:116 */    return this.digester.digest(password);
/* 117:    */  }
/* 118:    */  
/* 135:    */  public boolean checkPassword(String plainPassword, String encryptedPassword)
/* 136:    */  {
/* 137:137 */    return this.digester.matches(plainPassword, encryptedPassword);
/* 138:    */  }
/* 139:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.password.rfc2307.RFC2307SSHAPasswordEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */