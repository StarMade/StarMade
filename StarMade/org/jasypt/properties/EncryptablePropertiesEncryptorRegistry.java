/*    */ package org.jasypt.properties;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.jasypt.encryption.StringEncryptor;
/*    */ import org.jasypt.util.text.TextEncryptor;
/*    */ 
/*    */ final class EncryptablePropertiesEncryptorRegistry
/*    */ {
/* 44 */   private static final EncryptablePropertiesEncryptorRegistry instance = new EncryptablePropertiesEncryptorRegistry();
/*    */ 
/* 47 */   private final Map stringEncryptors = Collections.synchronizedMap(new HashMap());
/* 48 */   private final Map textEncryptors = Collections.synchronizedMap(new HashMap());
/*    */ 
/*    */   static EncryptablePropertiesEncryptorRegistry getInstance()
/*    */   {
/* 52 */     return instance;
/*    */   }
/*    */ 
/*    */   void removeEntries(EncryptableProperties prop)
/*    */   {
/* 62 */     this.stringEncryptors.remove(prop.getIdent());
/* 63 */     this.textEncryptors.remove(prop.getIdent());
/*    */   }
/*    */ 
/*    */   StringEncryptor getStringEncryptor(EncryptableProperties prop)
/*    */   {
/* 68 */     return (StringEncryptor)this.stringEncryptors.get(prop.getIdent());
/*    */   }
/*    */ 
/*    */   void setStringEncryptor(EncryptableProperties prop, StringEncryptor encryptor)
/*    */   {
/* 73 */     this.stringEncryptors.put(prop.getIdent(), encryptor);
/*    */   }
/*    */ 
/*    */   TextEncryptor getTextEncryptor(EncryptableProperties prop)
/*    */   {
/* 78 */     return (TextEncryptor)this.textEncryptors.get(prop.getIdent());
/*    */   }
/*    */ 
/*    */   void setTextEncryptor(EncryptableProperties prop, TextEncryptor encryptor)
/*    */   {
/* 83 */     this.textEncryptors.put(prop.getIdent(), encryptor);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.properties.EncryptablePropertiesEncryptorRegistry
 * JD-Core Version:    0.6.2
 */