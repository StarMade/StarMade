/*     */ package org.jasypt.properties;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Properties;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.encryption.StringEncryptor;
/*     */ import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*     */ import org.jasypt.util.text.TextEncryptor;
/*     */ 
/*     */ public final class EncryptableProperties extends Properties
/*     */ {
/*     */   private static final long serialVersionUID = 6479795856725500639L;
/*  82 */   private final Integer ident = new Integer(CommonUtils.nextRandomInt());
/*     */ 
/*  87 */   private boolean beenSerialized = false;
/*     */ 
/*     */   public EncryptableProperties(StringEncryptor stringEncryptor)
/*     */   {
/* 100 */     this(null, stringEncryptor);
/*     */   }
/*     */ 
/*     */   public EncryptableProperties(TextEncryptor textEncryptor)
/*     */   {
/* 114 */     this(null, textEncryptor);
/*     */   }
/*     */ 
/*     */   public EncryptableProperties(Properties defaults, StringEncryptor stringEncryptor)
/*     */   {
/* 130 */     super(defaults);
/* 131 */     CommonUtils.validateNotNull(stringEncryptor, "Encryptor cannot be null");
/* 132 */     EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
/*     */ 
/* 134 */     registry.setStringEncryptor(this, stringEncryptor);
/*     */   }
/*     */ 
/*     */   public EncryptableProperties(Properties defaults, TextEncryptor textEncryptor)
/*     */   {
/* 150 */     super(defaults);
/* 151 */     CommonUtils.validateNotNull(textEncryptor, "Encryptor cannot be null");
/* 152 */     EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
/*     */ 
/* 154 */     registry.setTextEncryptor(this, textEncryptor);
/*     */   }
/*     */ 
/*     */   public String getProperty(String key)
/*     */   {
/* 168 */     return decode(super.getProperty(key));
/*     */   }
/*     */ 
/*     */   public String getProperty(String key, String defaultValue)
/*     */   {
/* 187 */     return decode(super.getProperty(key, defaultValue));
/*     */   }
/*     */ 
/*     */   public synchronized Object get(Object key)
/*     */   {
/* 202 */     Object value = super.get(key);
/* 203 */     String valueStr = (value instanceof String) ? (String)value : null;
/*     */ 
/* 205 */     return decode(valueStr);
/*     */   }
/*     */ 
/*     */   Integer getIdent()
/*     */   {
/* 213 */     return this.ident;
/*     */   }
/*     */ 
/*     */   private synchronized String decode(String encodedValue)
/*     */   {
/* 222 */     if (!PropertyValueEncryptionUtils.isEncryptedValue(encodedValue)) {
/* 223 */       return encodedValue;
/*     */     }
/* 225 */     EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
/*     */ 
/* 227 */     StringEncryptor stringEncryptor = registry.getStringEncryptor(this);
/* 228 */     if (stringEncryptor != null) {
/* 229 */       return PropertyValueEncryptionUtils.decrypt(encodedValue, stringEncryptor);
/*     */     }
/*     */ 
/* 232 */     TextEncryptor textEncryptor = registry.getTextEncryptor(this);
/* 233 */     if (textEncryptor != null) {
/* 234 */       return PropertyValueEncryptionUtils.decrypt(encodedValue, textEncryptor);
/*     */     }
/*     */ 
/* 243 */     throw new EncryptionOperationNotPossibleException("Neither a string encryptor nor a text encryptor exist for this instance of EncryptableProperties. This is usually caused by the instance having been serialized and then de-serialized in a different classloader or virtual machine, which is an unsupported behaviour (as encryptors cannot be serialized themselves)");
/*     */   }
/*     */ 
/*     */   private void writeObject(ObjectOutputStream outputStream)
/*     */     throws IOException
/*     */   {
/* 256 */     this.beenSerialized = true;
/* 257 */     outputStream.defaultWriteObject();
/*     */   }
/*     */ 
/*     */   protected void finalize()
/*     */     throws Throwable
/*     */   {
/* 263 */     if (!this.beenSerialized) {
/* 264 */       EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
/*     */ 
/* 266 */       registry.removeEntries(this);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.properties.EncryptableProperties
 * JD-Core Version:    0.6.2
 */