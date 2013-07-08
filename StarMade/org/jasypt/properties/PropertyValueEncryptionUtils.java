package org.jasypt.properties;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.TextEncryptor;

public final class PropertyValueEncryptionUtils
{
  private static final String ENCRYPTED_VALUE_PREFIX = "ENC(";
  private static final String ENCRYPTED_VALUE_SUFFIX = ")";
  
  public static boolean isEncryptedValue(String value)
  {
    if (value == null) {
      return false;
    }
    String trimmedValue = value.trim();
    return (trimmedValue.startsWith("ENC(")) && (trimmedValue.endsWith(")"));
  }
  
  private static String getInnerEncryptedValue(String value)
  {
    return value.substring("ENC(".length(), value.length() - ")".length());
  }
  
  public static String decrypt(String encodedValue, StringEncryptor encryptor)
  {
    return encryptor.decrypt(getInnerEncryptedValue(encodedValue.trim()));
  }
  
  public static String decrypt(String encodedValue, TextEncryptor encryptor)
  {
    return encryptor.decrypt(getInnerEncryptedValue(encodedValue.trim()));
  }
  
  public static String encrypt(String decodedValue, StringEncryptor encryptor)
  {
    return "ENC(" + encryptor.encrypt(decodedValue) + ")";
  }
  
  public static String encrypt(String decodedValue, TextEncryptor encryptor)
  {
    return "ENC(" + encryptor.encrypt(decodedValue) + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.properties.PropertyValueEncryptionUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */