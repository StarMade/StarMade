package org.jasypt.properties;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;
import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.TextEncryptor;

public final class EncryptableProperties
  extends Properties
{
  private static final long serialVersionUID = 6479795856725500639L;
  private final Integer ident = new Integer(CommonUtils.nextRandomInt());
  private boolean beenSerialized = false;
  
  public EncryptableProperties(StringEncryptor stringEncryptor)
  {
    this(null, stringEncryptor);
  }
  
  public EncryptableProperties(TextEncryptor textEncryptor)
  {
    this(null, textEncryptor);
  }
  
  public EncryptableProperties(Properties defaults, StringEncryptor stringEncryptor)
  {
    super(defaults);
    CommonUtils.validateNotNull(stringEncryptor, "Encryptor cannot be null");
    EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
    registry.setStringEncryptor(this, stringEncryptor);
  }
  
  public EncryptableProperties(Properties defaults, TextEncryptor textEncryptor)
  {
    super(defaults);
    CommonUtils.validateNotNull(textEncryptor, "Encryptor cannot be null");
    EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
    registry.setTextEncryptor(this, textEncryptor);
  }
  
  public String getProperty(String key)
  {
    return decode(super.getProperty(key));
  }
  
  public String getProperty(String key, String defaultValue)
  {
    return decode(super.getProperty(key, defaultValue));
  }
  
  public synchronized Object get(Object key)
  {
    Object value = super.get(key);
    String valueStr = (value instanceof String) ? (String)value : null;
    return decode(valueStr);
  }
  
  Integer getIdent()
  {
    return this.ident;
  }
  
  private synchronized String decode(String encodedValue)
  {
    if (!PropertyValueEncryptionUtils.isEncryptedValue(encodedValue)) {
      return encodedValue;
    }
    EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
    StringEncryptor stringEncryptor = registry.getStringEncryptor(this);
    if (stringEncryptor != null) {
      return PropertyValueEncryptionUtils.decrypt(encodedValue, stringEncryptor);
    }
    TextEncryptor textEncryptor = registry.getTextEncryptor(this);
    if (textEncryptor != null) {
      return PropertyValueEncryptionUtils.decrypt(encodedValue, textEncryptor);
    }
    throw new EncryptionOperationNotPossibleException("Neither a string encryptor nor a text encryptor exist for this instance of EncryptableProperties. This is usually caused by the instance having been serialized and then de-serialized in a different classloader or virtual machine, which is an unsupported behaviour (as encryptors cannot be serialized themselves)");
  }
  
  private void writeObject(ObjectOutputStream outputStream)
    throws IOException
  {
    this.beenSerialized = true;
    outputStream.defaultWriteObject();
  }
  
  protected void finalize()
    throws Throwable
  {
    if (!this.beenSerialized)
    {
      EncryptablePropertiesEncryptorRegistry registry = EncryptablePropertiesEncryptorRegistry.getInstance();
      registry.removeEntries(this);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.properties.EncryptableProperties
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */