/*   1:    */package org.jasypt.util.password;
/*   2:    */
/*   3:    */import java.security.Provider;
/*   4:    */import org.jasypt.digest.StandardStringDigester;
/*   5:    */import org.jasypt.digest.config.DigesterConfig;
/*   6:    */
/*  69:    */public final class ConfigurablePasswordEncryptor
/*  70:    */  implements PasswordEncryptor
/*  71:    */{
/*  72:    */  private final StandardStringDigester digester;
/*  73:    */  
/*  74:    */  public ConfigurablePasswordEncryptor()
/*  75:    */  {
/*  76: 76 */    this.digester = new StandardStringDigester();
/*  77:    */  }
/*  78:    */  
/*  87:    */  public void setConfig(DigesterConfig config)
/*  88:    */  {
/*  89: 89 */    this.digester.setConfig(config);
/*  90:    */  }
/*  91:    */  
/* 119:    */  public void setAlgorithm(String algorithm)
/* 120:    */  {
/* 121:121 */    this.digester.setAlgorithm(algorithm);
/* 122:    */  }
/* 123:    */  
/* 155:    */  public void setProviderName(String providerName)
/* 156:    */  {
/* 157:157 */    this.digester.setProviderName(providerName);
/* 158:    */  }
/* 159:    */  
/* 184:    */  public void setProvider(Provider provider)
/* 185:    */  {
/* 186:186 */    this.digester.setProvider(provider);
/* 187:    */  }
/* 188:    */  
/* 199:    */  public void setPlainDigest(boolean plainDigest)
/* 200:    */  {
/* 201:201 */    if (plainDigest) {
/* 202:202 */      this.digester.setIterations(1);
/* 203:203 */      this.digester.setSaltSizeBytes(0);
/* 204:    */    } else {
/* 205:205 */      this.digester.setIterations(1000);
/* 206:206 */      this.digester.setSaltSizeBytes(8);
/* 207:    */    }
/* 208:    */  }
/* 209:    */  
/* 223:    */  public void setStringOutputType(String stringOutputType)
/* 224:    */  {
/* 225:225 */    this.digester.setStringOutputType(stringOutputType);
/* 226:    */  }
/* 227:    */  
/* 235:    */  public String encryptPassword(String password)
/* 236:    */  {
/* 237:237 */    return this.digester.digest(password);
/* 238:    */  }
/* 239:    */  
/* 250:    */  public boolean checkPassword(String plainPassword, String encryptedPassword)
/* 251:    */  {
/* 252:252 */    return this.digester.matches(plainPassword, encryptedPassword);
/* 253:    */  }
/* 254:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.password.ConfigurablePasswordEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */