package org.jasypt.encryption.pbe;

import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.StringPBEConfig;
import org.jasypt.exceptions.AlreadyInitializedException;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.SaltGenerator;

public final class StandardPBEStringEncryptor
  implements PBEStringCleanablePasswordEncryptor
{
  private static final String MESSAGE_CHARSET = "UTF-8";
  private static final String ENCRYPTED_MESSAGE_CHARSET = "US-ASCII";
  public static final String DEFAULT_STRING_OUTPUT_TYPE = "base64";
  private StringPBEConfig stringPBEConfig = null;
  private String stringOutputType = "base64";
  private boolean stringOutputTypeBase64 = true;
  private boolean stringOutputTypeSet = false;
  private final StandardPBEByteEncryptor byteEncryptor;
  private final Base64 base64;
  
  public StandardPBEStringEncryptor()
  {
    this.byteEncryptor = new StandardPBEByteEncryptor();
    this.base64 = new Base64();
  }
  
  private StandardPBEStringEncryptor(StandardPBEByteEncryptor standardPBEByteEncryptor)
  {
    this.byteEncryptor = standardPBEByteEncryptor;
    this.base64 = new Base64();
  }
  
  public synchronized void setConfig(PBEConfig config)
  {
    this.byteEncryptor.setConfig(config);
    if ((config != null) && ((config instanceof StringPBEConfig))) {
      this.stringPBEConfig = ((StringPBEConfig)config);
    }
  }
  
  public void setAlgorithm(String algorithm)
  {
    this.byteEncryptor.setAlgorithm(algorithm);
  }
  
  public void setPassword(String password)
  {
    this.byteEncryptor.setPassword(password);
  }
  
  public void setPasswordCharArray(char[] password)
  {
    this.byteEncryptor.setPasswordCharArray(password);
  }
  
  public void setKeyObtentionIterations(int keyObtentionIterations)
  {
    this.byteEncryptor.setKeyObtentionIterations(keyObtentionIterations);
  }
  
  public void setSaltGenerator(SaltGenerator saltGenerator)
  {
    this.byteEncryptor.setSaltGenerator(saltGenerator);
  }
  
  public void setProviderName(String providerName)
  {
    this.byteEncryptor.setProviderName(providerName);
  }
  
  public void setProvider(Provider provider)
  {
    this.byteEncryptor.setProvider(provider);
  }
  
  public synchronized void setStringOutputType(String stringOutputType)
  {
    CommonUtils.validateNotEmpty(stringOutputType, "String output type cannot be set empty");
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
    this.stringOutputTypeSet = true;
  }
  
  synchronized StandardPBEStringEncryptor[] cloneAndInitializeEncryptor(int size)
  {
    StandardPBEByteEncryptor[] byteEncryptorClones = this.byteEncryptor.cloneAndInitializeEncryptor(size);
    initializeSpecifics();
    StandardPBEStringEncryptor[] clones = new StandardPBEStringEncryptor[size];
    clones[0] = this;
    for (int local_i = 1; local_i < size; local_i++)
    {
      clones[local_i] = new StandardPBEStringEncryptor(byteEncryptorClones[local_i]);
      if (CommonUtils.isNotEmpty(this.stringOutputType)) {
        clones[local_i].setStringOutputType(this.stringOutputType);
      }
    }
    return clones;
  }
  
  public boolean isInitialized()
  {
    return this.byteEncryptor.isInitialized();
  }
  
  public synchronized void initialize()
  {
    if (!isInitialized())
    {
      initializeSpecifics();
      this.byteEncryptor.initialize();
    }
  }
  
  private void initializeSpecifics()
  {
    if (this.stringPBEConfig != null)
    {
      String configStringOutputType = this.stringPBEConfig.getStringOutputType();
      this.stringOutputType = ((this.stringOutputTypeSet) || (configStringOutputType == null) ? this.stringOutputType : configStringOutputType);
    }
    this.stringOutputTypeBase64 = "base64".equalsIgnoreCase(this.stringOutputType);
  }
  
  public String encrypt(String message)
  {
    if (message == null) {
      return null;
    }
    if (!isInitialized()) {
      initialize();
    }
    try
    {
      byte[] messageBytes = message.getBytes("UTF-8");
      byte[] encryptedMessage = this.byteEncryptor.encrypt(messageBytes);
      String result = null;
      if (this.stringOutputTypeBase64)
      {
        encryptedMessage = this.base64.encode(encryptedMessage);
        result = new String(encryptedMessage, "US-ASCII");
      }
      else
      {
        result = CommonUtils.toHexadecimal(encryptedMessage);
      }
      return result;
    }
    catch (EncryptionInitializationException messageBytes)
    {
      throw messageBytes;
    }
    catch (EncryptionOperationNotPossibleException messageBytes)
    {
      throw messageBytes;
    }
    catch (Exception messageBytes)
    {
      throw new EncryptionOperationNotPossibleException();
    }
  }
  
  public String decrypt(String encryptedMessage)
  {
    if (encryptedMessage == null) {
      return null;
    }
    if (!isInitialized()) {
      initialize();
    }
    try
    {
      byte[] encryptedMessageBytes = null;
      if (this.stringOutputTypeBase64)
      {
        encryptedMessageBytes = encryptedMessage.getBytes("US-ASCII");
        encryptedMessageBytes = this.base64.decode(encryptedMessageBytes);
      }
      else
      {
        encryptedMessageBytes = CommonUtils.fromHexadecimal(encryptedMessage);
      }
      byte[] message = this.byteEncryptor.decrypt(encryptedMessageBytes);
      return new String(message, "UTF-8");
    }
    catch (EncryptionInitializationException encryptedMessageBytes)
    {
      throw encryptedMessageBytes;
    }
    catch (EncryptionOperationNotPossibleException encryptedMessageBytes)
    {
      throw encryptedMessageBytes;
    }
    catch (Exception encryptedMessageBytes)
    {
      throw new EncryptionOperationNotPossibleException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.encryption.pbe.StandardPBEStringEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */