/*   1:    */package org.jasypt.properties;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.ObjectOutputStream;
/*   5:    */import java.util.Properties;
/*   6:    */import org.jasypt.commons.CommonUtils;
/*   7:    */import org.jasypt.encryption.StringEncryptor;
/*   8:    */import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*   9:    */import org.jasypt.util.text.TextEncryptor;
/*  10:    */
/*  78:    */public final class EncryptableProperties
/*  79:    */  extends Properties
/*  80:    */{
/*  81:    */  private static final long serialVersionUID = 6479795856725500639L;
/*  82: 82 */  private final Integer ident = new Integer(CommonUtils.nextRandomInt());
/*  83:    */  
/*  87: 87 */  private boolean beenSerialized = false;
/*  88:    */  
/*  98:    */  public EncryptableProperties(StringEncryptor stringEncryptor)
/*  99:    */  {
/* 100:100 */    this(null, stringEncryptor);
/* 101:    */  }
/* 102:    */  
/* 112:    */  public EncryptableProperties(TextEncryptor textEncryptor)
/* 113:    */  {
/* 114:114 */    this(null, textEncryptor);
/* 115:    */  }
/* 116:    */  
/* 128:    */  public EncryptableProperties(Properties defaults, StringEncryptor stringEncryptor)
/* 129:    */  {
/* 130:130 */    super(defaults);
/* 131:131 */    CommonUtils.validateNotNull(stringEncryptor, "Encryptor cannot be null");
/* 132:132 */    EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
/* 133:    */    
/* 134:134 */    registry.setStringEncryptor(this, stringEncryptor);
/* 135:    */  }
/* 136:    */  
/* 148:    */  public EncryptableProperties(Properties defaults, TextEncryptor textEncryptor)
/* 149:    */  {
/* 150:150 */    super(defaults);
/* 151:151 */    CommonUtils.validateNotNull(textEncryptor, "Encryptor cannot be null");
/* 152:152 */    EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
/* 153:    */    
/* 154:154 */    registry.setTextEncryptor(this, textEncryptor);
/* 155:    */  }
/* 156:    */  
/* 166:    */  public String getProperty(String key)
/* 167:    */  {
/* 168:168 */    return decode(super.getProperty(key));
/* 169:    */  }
/* 170:    */  
/* 185:    */  public String getProperty(String key, String defaultValue)
/* 186:    */  {
/* 187:187 */    return decode(super.getProperty(key, defaultValue));
/* 188:    */  }
/* 189:    */  
/* 200:    */  public synchronized Object get(Object key)
/* 201:    */  {
/* 202:202 */    Object value = super.get(key);
/* 203:203 */    String valueStr = (value instanceof String) ? (String)value : null;
/* 204:    */    
/* 205:205 */    return decode(valueStr);
/* 206:    */  }
/* 207:    */  
/* 211:    */  Integer getIdent()
/* 212:    */  {
/* 213:213 */    return this.ident;
/* 214:    */  }
/* 215:    */  
/* 220:    */  private synchronized String decode(String encodedValue)
/* 221:    */  {
/* 222:222 */    if (!PropertyValueEncryptionUtils.isEncryptedValue(encodedValue)) {
/* 223:223 */      return encodedValue;
/* 224:    */    }
/* 225:225 */    EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
/* 226:    */    
/* 227:227 */    StringEncryptor stringEncryptor = registry.getStringEncryptor(this);
/* 228:228 */    if (stringEncryptor != null) {
/* 229:229 */      return PropertyValueEncryptionUtils.decrypt(encodedValue, stringEncryptor);
/* 230:    */    }
/* 231:    */    
/* 232:232 */    TextEncryptor textEncryptor = registry.getTextEncryptor(this);
/* 233:233 */    if (textEncryptor != null) {
/* 234:234 */      return PropertyValueEncryptionUtils.decrypt(encodedValue, textEncryptor);
/* 235:    */    }
/* 236:    */    
/* 243:243 */    throw new EncryptionOperationNotPossibleException("Neither a string encryptor nor a text encryptor exist for this instance of EncryptableProperties. This is usually caused by the instance having been serialized and then de-serialized in a different classloader or virtual machine, which is an unsupported behaviour (as encryptors cannot be serialized themselves)");
/* 244:    */  }
/* 245:    */  
/* 253:    */  private void writeObject(ObjectOutputStream outputStream)
/* 254:    */    throws IOException
/* 255:    */  {
/* 256:256 */    this.beenSerialized = true;
/* 257:257 */    outputStream.defaultWriteObject();
/* 258:    */  }
/* 259:    */  
/* 260:    */  protected void finalize()
/* 261:    */    throws Throwable
/* 262:    */  {
/* 263:263 */    if (!this.beenSerialized) {
/* 264:264 */      EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
/* 265:    */      
/* 266:266 */      registry.removeEntries(this);
/* 267:    */    }
/* 268:    */  }
/* 269:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.properties.EncryptableProperties
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */