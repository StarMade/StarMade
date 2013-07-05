/*    */ package org.jasypt.properties;
/*    */ 
/*    */ import org.jasypt.encryption.StringEncryptor;
/*    */ import org.jasypt.util.text.TextEncryptor;
/*    */ 
/*    */ public final class PropertyValueEncryptionUtils
/*    */ {
/*    */   private static final String ENCRYPTED_VALUE_PREFIX = "ENC(";
/*    */   private static final String ENCRYPTED_VALUE_SUFFIX = ")";
/*    */ 
/*    */   public static boolean isEncryptedValue(String value)
/*    */   {
/* 55 */     if (value == null) {
/* 56 */       return false;
/*    */     }
/* 58 */     String trimmedValue = value.trim();
/* 59 */     return (trimmedValue.startsWith("ENC(")) && (trimmedValue.endsWith(")"));
/*    */   }
/*    */ 
/*    */   private static String getInnerEncryptedValue(String value)
/*    */   {
/* 64 */     return value.substring("ENC(".length(), value.length() - ")".length());
/*    */   }
/*    */ 
/*    */   public static String decrypt(String encodedValue, StringEncryptor encryptor)
/*    */   {
/* 72 */     return encryptor.decrypt(getInnerEncryptedValue(encodedValue.trim()));
/*    */   }
/*    */ 
/*    */   public static String decrypt(String encodedValue, TextEncryptor encryptor)
/*    */   {
/* 78 */     return encryptor.decrypt(getInnerEncryptedValue(encodedValue.trim()));
/*    */   }
/*    */ 
/*    */   public static String encrypt(String decodedValue, StringEncryptor encryptor)
/*    */   {
/* 84 */     return "ENC(" + encryptor.encrypt(decodedValue) + ")";
/*    */   }
/*    */ 
/*    */   public static String encrypt(String decodedValue, TextEncryptor encryptor)
/*    */   {
/* 93 */     return "ENC(" + encryptor.encrypt(decodedValue) + ")";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.properties.PropertyValueEncryptionUtils
 * JD-Core Version:    0.6.2
 */