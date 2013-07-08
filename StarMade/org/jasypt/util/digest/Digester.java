/*   1:    */package org.jasypt.util.digest;
/*   2:    */
/*   3:    */import java.security.Provider;
/*   4:    */import org.jasypt.digest.StandardByteDigester;
/*   5:    */
/*  76:    */public final class Digester
/*  77:    */{
/*  78:    */  public static final String DEFAULT_ALGORITHM = "MD5";
/*  79:    */  private static final int ITERATIONS = 1;
/*  80:    */  private static final int SALT_SIZE_BYTES = 0;
/*  81:    */  private final StandardByteDigester digester;
/*  82:    */  
/*  83:    */  public Digester()
/*  84:    */  {
/*  85: 85 */    this.digester = new StandardByteDigester();
/*  86: 86 */    this.digester.setIterations(1);
/*  87: 87 */    this.digester.setSaltSizeBytes(0);
/*  88:    */  }
/*  89:    */  
/*  96:    */  public Digester(String algorithm)
/*  97:    */  {
/*  98: 98 */    this.digester = new StandardByteDigester();
/*  99: 99 */    this.digester.setIterations(1);
/* 100:100 */    this.digester.setSaltSizeBytes(0);
/* 101:101 */    this.digester.setAlgorithm(algorithm);
/* 102:    */  }
/* 103:    */  
/* 113:    */  public Digester(String algorithm, String providerName)
/* 114:    */  {
/* 115:115 */    this.digester = new StandardByteDigester();
/* 116:116 */    this.digester.setIterations(1);
/* 117:117 */    this.digester.setSaltSizeBytes(0);
/* 118:118 */    this.digester.setAlgorithm(algorithm);
/* 119:119 */    this.digester.setProviderName(providerName);
/* 120:    */  }
/* 121:    */  
/* 131:    */  public Digester(String algorithm, Provider provider)
/* 132:    */  {
/* 133:133 */    this.digester = new StandardByteDigester();
/* 134:134 */    this.digester.setIterations(1);
/* 135:135 */    this.digester.setSaltSizeBytes(0);
/* 136:136 */    this.digester.setAlgorithm(algorithm);
/* 137:137 */    this.digester.setProvider(provider);
/* 138:    */  }
/* 139:    */  
/* 169:    */  public void setAlgorithm(String algorithm)
/* 170:    */  {
/* 171:171 */    this.digester.setAlgorithm(algorithm);
/* 172:    */  }
/* 173:    */  
/* 204:    */  public void setProviderName(String providerName)
/* 205:    */  {
/* 206:206 */    this.digester.setProviderName(providerName);
/* 207:    */  }
/* 208:    */  
/* 232:    */  public void setProvider(Provider provider)
/* 233:    */  {
/* 234:234 */    this.digester.setProvider(provider);
/* 235:    */  }
/* 236:    */  
/* 244:    */  public byte[] digest(byte[] binary)
/* 245:    */  {
/* 246:246 */    return this.digester.digest(binary);
/* 247:    */  }
/* 248:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.util.digest.Digester
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */