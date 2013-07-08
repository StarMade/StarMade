/*  1:   */package org.jasypt.properties;
/*  2:   */
/*  3:   */import org.jasypt.encryption.StringEncryptor;
/*  4:   */import org.jasypt.util.text.TextEncryptor;
/*  5:   */
/* 48:   */public final class PropertyValueEncryptionUtils
/* 49:   */{
/* 50:   */  private static final String ENCRYPTED_VALUE_PREFIX = "ENC(";
/* 51:   */  private static final String ENCRYPTED_VALUE_SUFFIX = ")";
/* 52:   */  
/* 53:   */  public static boolean isEncryptedValue(String value)
/* 54:   */  {
/* 55:55 */    if (value == null) {
/* 56:56 */      return false;
/* 57:   */    }
/* 58:58 */    String trimmedValue = value.trim();
/* 59:59 */    return (trimmedValue.startsWith("ENC(")) && (trimmedValue.endsWith(")"));
/* 60:   */  }
/* 61:   */  
/* 62:   */  private static String getInnerEncryptedValue(String value)
/* 63:   */  {
/* 64:64 */    return value.substring("ENC(".length(), value.length() - ")".length());
/* 65:   */  }
/* 66:   */  
/* 70:   */  public static String decrypt(String encodedValue, StringEncryptor encryptor)
/* 71:   */  {
/* 72:72 */    return encryptor.decrypt(getInnerEncryptedValue(encodedValue.trim()));
/* 73:   */  }
/* 74:   */  
/* 76:   */  public static String decrypt(String encodedValue, TextEncryptor encryptor)
/* 77:   */  {
/* 78:78 */    return encryptor.decrypt(getInnerEncryptedValue(encodedValue.trim()));
/* 79:   */  }
/* 80:   */  
/* 82:   */  public static String encrypt(String decodedValue, StringEncryptor encryptor)
/* 83:   */  {
/* 84:84 */    return "ENC(" + encryptor.encrypt(decodedValue) + ")";
/* 85:   */  }
/* 86:   */  
/* 91:   */  public static String encrypt(String decodedValue, TextEncryptor encryptor)
/* 92:   */  {
/* 93:93 */    return "ENC(" + encryptor.encrypt(decodedValue) + ")";
/* 94:   */  }
/* 95:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.properties.PropertyValueEncryptionUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */