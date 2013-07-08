package org.jasypt.encryption.pbe;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.SaltGenerator;

public final class StandardPBEBigDecimalEncryptor
  implements PBEBigDecimalCleanablePasswordEncryptor
{
  private final StandardPBEByteEncryptor byteEncryptor;
  
  public StandardPBEBigDecimalEncryptor()
  {
    this.byteEncryptor = new StandardPBEByteEncryptor();
  }
  
  private StandardPBEBigDecimalEncryptor(StandardPBEByteEncryptor standardPBEByteEncryptor)
  {
    this.byteEncryptor = standardPBEByteEncryptor;
  }
  
  public void setConfig(PBEConfig config)
  {
    this.byteEncryptor.setConfig(config);
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
  
  synchronized StandardPBEBigDecimalEncryptor[] cloneAndInitializeEncryptor(int size)
  {
    StandardPBEByteEncryptor[] byteEncryptorClones = this.byteEncryptor.cloneAndInitializeEncryptor(size);
    StandardPBEBigDecimalEncryptor[] clones = new StandardPBEBigDecimalEncryptor[size];
    clones[0] = this;
    for (int local_i = 1; local_i < size; local_i++) {
      clones[local_i] = new StandardPBEBigDecimalEncryptor(byteEncryptorClones[local_i]);
    }
    return clones;
  }
  
  public boolean isInitialized()
  {
    return this.byteEncryptor.isInitialized();
  }
  
  public void initialize()
  {
    this.byteEncryptor.initialize();
  }
  
  public BigDecimal encrypt(BigDecimal message)
  {
    if (message == null) {
      return null;
    }
    try
    {
      int scale = message.scale();
      BigInteger unscaledMessage = message.unscaledValue();
      byte[] messageBytes = unscaledMessage.toByteArray();
      byte[] encryptedMessage = this.byteEncryptor.encrypt(messageBytes);
      byte[] encryptedMessageLengthBytes = NumberUtils.byteArrayFromInt(encryptedMessage.length);
      byte[] encryptionResult = CommonUtils.appendArrays(encryptedMessage, encryptedMessageLengthBytes);
      return new BigDecimal(new BigInteger(encryptionResult), scale);
    }
    catch (EncryptionInitializationException scale)
    {
      throw scale;
    }
    catch (EncryptionOperationNotPossibleException scale)
    {
      throw scale;
    }
    catch (Exception scale)
    {
      throw new EncryptionOperationNotPossibleException();
    }
  }
  
  public BigDecimal decrypt(BigDecimal encryptedMessage)
  {
    if (encryptedMessage == null) {
      return null;
    }
    try
    {
      int scale = encryptedMessage.scale();
      BigInteger unscaledEncryptedMessage = encryptedMessage.unscaledValue();
      byte[] encryptedMessageBytes = unscaledEncryptedMessage.toByteArray();
      encryptedMessageBytes = NumberUtils.processBigIntegerEncryptedByteArray(encryptedMessageBytes, encryptedMessage.signum());
      byte[] message = this.byteEncryptor.decrypt(encryptedMessageBytes);
      return new BigDecimal(new BigInteger(message), scale);
    }
    catch (EncryptionInitializationException scale)
    {
      throw scale;
    }
    catch (EncryptionOperationNotPossibleException scale)
    {
      throw scale;
    }
    catch (Exception scale)
    {
      throw new EncryptionOperationNotPossibleException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.encryption.pbe.StandardPBEBigDecimalEncryptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */