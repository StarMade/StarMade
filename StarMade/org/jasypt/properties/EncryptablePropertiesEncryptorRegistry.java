/*  1:   */package org.jasypt.properties;
/*  2:   */
/*  3:   */import java.util.Collections;
/*  4:   */import java.util.HashMap;
/*  5:   */import java.util.Map;
/*  6:   */import org.jasypt.encryption.StringEncryptor;
/*  7:   */import org.jasypt.util.text.TextEncryptor;
/*  8:   */
/* 42:   */final class EncryptablePropertiesEncryptorRegistry
/* 43:   */{
/* 44:44 */  private static final EncryptablePropertiesEncryptorRegistry instance = new EncryptablePropertiesEncryptorRegistry();
/* 45:   */  
/* 47:47 */  private final Map stringEncryptors = Collections.synchronizedMap(new HashMap());
/* 48:48 */  private final Map textEncryptors = Collections.synchronizedMap(new HashMap());
/* 49:   */  
/* 50:   */  static EncryptablePropertiesEncryptorRegistry getInstance()
/* 51:   */  {
/* 52:52 */    return instance;
/* 53:   */  }
/* 54:   */  
/* 60:   */  void removeEntries(EncryptableProperties prop)
/* 61:   */  {
/* 62:62 */    this.stringEncryptors.remove(prop.getIdent());
/* 63:63 */    this.textEncryptors.remove(prop.getIdent());
/* 64:   */  }
/* 65:   */  
/* 66:   */  StringEncryptor getStringEncryptor(EncryptableProperties prop)
/* 67:   */  {
/* 68:68 */    return (StringEncryptor)this.stringEncryptors.get(prop.getIdent());
/* 69:   */  }
/* 70:   */  
/* 71:   */  void setStringEncryptor(EncryptableProperties prop, StringEncryptor encryptor)
/* 72:   */  {
/* 73:73 */    this.stringEncryptors.put(prop.getIdent(), encryptor);
/* 74:   */  }
/* 75:   */  
/* 76:   */  TextEncryptor getTextEncryptor(EncryptableProperties prop)
/* 77:   */  {
/* 78:78 */    return (TextEncryptor)this.textEncryptors.get(prop.getIdent());
/* 79:   */  }
/* 80:   */  
/* 81:   */  void setTextEncryptor(EncryptableProperties prop, TextEncryptor encryptor)
/* 82:   */  {
/* 83:83 */    this.textEncryptors.put(prop.getIdent(), encryptor);
/* 84:   */  }
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.properties.EncryptablePropertiesEncryptorRegistry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */