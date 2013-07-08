package org.jasypt.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.Arrays;
import org.jasypt.commons.CommonUtils;
import org.jasypt.digest.config.DigesterConfig;
import org.jasypt.exceptions.AlreadyInitializedException;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.RandomSaltGenerator;
import org.jasypt.salt.SaltGenerator;

public final class StandardByteDigester
  implements ByteDigester
{
  public static final String DEFAULT_ALGORITHM = "MD5";
  public static final int DEFAULT_SALT_SIZE_BYTES = 8;
  public static final int DEFAULT_ITERATIONS = 1000;
  private String algorithm = "MD5";
  private int saltSizeBytes = 8;
  private int iterations = 1000;
  private SaltGenerator saltGenerator = null;
  private String providerName = null;
  private Provider provider = null;
  private boolean invertPositionOfSaltInMessageBeforeDigesting = false;
  private boolean invertPositionOfPlainSaltInEncryptionResults = false;
  private boolean useLenientSaltSizeCheck = false;
  private DigesterConfig config = null;
  private boolean algorithmSet = false;
  private boolean saltSizeBytesSet = false;
  private boolean iterationsSet = false;
  private boolean saltGeneratorSet = false;
  private boolean providerNameSet = false;
  private boolean providerSet = false;
  private boolean invertPositionOfSaltInMessageBeforeDigestingSet = false;
  private boolean invertPositionOfPlainSaltInEncryptionResultsSet = false;
  private boolean useLenientSaltSizeCheckSet = false;
  private boolean initialized = false;
  private boolean useSalt = true;
  private MessageDigest field_374 = null;
  private int digestLengthBytes = 0;
  
  public synchronized void setConfig(DigesterConfig config)
  {
    CommonUtils.validateNotNull(config, "Config cannot be set null");
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.config = config;
  }
  
  public synchronized void setAlgorithm(String algorithm)
  {
    CommonUtils.validateNotEmpty(algorithm, "Algorithm cannot be empty");
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.algorithm = algorithm;
    this.algorithmSet = true;
  }
  
  public synchronized void setSaltSizeBytes(int saltSizeBytes)
  {
    CommonUtils.validateIsTrue(saltSizeBytes >= 0, "Salt size in bytes must be non-negative");
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.saltSizeBytes = saltSizeBytes;
    this.useSalt = (saltSizeBytes > 0);
    this.saltSizeBytesSet = true;
  }
  
  public synchronized void setIterations(int iterations)
  {
    CommonUtils.validateIsTrue(iterations > 0, "Number of iterations must be greater than zero");
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.iterations = iterations;
    this.iterationsSet = true;
  }
  
  public synchronized void setSaltGenerator(SaltGenerator saltGenerator)
  {
    CommonUtils.validateNotNull(saltGenerator, "Salt generator cannot be set null");
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.saltGenerator = saltGenerator;
    this.saltGeneratorSet = true;
  }
  
  public synchronized void setProviderName(String providerName)
  {
    CommonUtils.validateNotNull(providerName, "Provider name cannot be set null");
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.providerName = providerName;
    this.providerNameSet = true;
  }
  
  public synchronized void setProvider(Provider provider)
  {
    CommonUtils.validateNotNull(provider, "Provider cannot be set null");
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.provider = provider;
    this.providerSet = true;
  }
  
  public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting)
  {
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.invertPositionOfSaltInMessageBeforeDigesting = invertPositionOfSaltInMessageBeforeDigesting;
    this.invertPositionOfSaltInMessageBeforeDigestingSet = true;
  }
  
  public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults)
  {
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.invertPositionOfPlainSaltInEncryptionResults = invertPositionOfPlainSaltInEncryptionResults;
    this.invertPositionOfPlainSaltInEncryptionResultsSet = true;
  }
  
  public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck)
  {
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.useLenientSaltSizeCheck = useLenientSaltSizeCheck;
    this.useLenientSaltSizeCheckSet = true;
  }
  
  StandardByteDigester cloneDigester()
  {
    if (!isInitialized()) {
      initialize();
    }
    StandardByteDigester cloned = new StandardByteDigester();
    if (CommonUtils.isNotEmpty(this.algorithm)) {
      cloned.setAlgorithm(this.algorithm);
    }
    cloned.setInvertPositionOfPlainSaltInEncryptionResults(this.invertPositionOfPlainSaltInEncryptionResults);
    cloned.setInvertPositionOfSaltInMessageBeforeDigesting(this.invertPositionOfSaltInMessageBeforeDigesting);
    cloned.setIterations(this.iterations);
    if (this.provider != null) {
      cloned.setProvider(this.provider);
    }
    if (this.providerName != null) {
      cloned.setProviderName(this.providerName);
    }
    if (this.saltGenerator != null) {
      cloned.setSaltGenerator(this.saltGenerator);
    }
    cloned.setSaltSizeBytes(this.saltSizeBytes);
    cloned.setUseLenientSaltSizeCheck(this.useLenientSaltSizeCheck);
    return cloned;
  }
  
  public boolean isInitialized()
  {
    return this.initialized;
  }
  
  public synchronized void initialize()
  {
    if (!this.initialized)
    {
      if (this.config != null)
      {
        String configAlgorithm = this.config.getAlgorithm();
        if (configAlgorithm != null) {
          CommonUtils.validateNotEmpty(configAlgorithm, "Algorithm cannot be empty");
        }
        Integer configSaltSizeBytes = this.config.getSaltSizeBytes();
        if (configSaltSizeBytes != null) {
          CommonUtils.validateIsTrue(configSaltSizeBytes.intValue() >= 0, "Salt size in bytes must be non-negative");
        }
        Integer configIterations = this.config.getIterations();
        if (configIterations != null) {
          CommonUtils.validateIsTrue(configIterations.intValue() > 0, "Number of iterations must be greater than zero");
        }
        SaltGenerator configSaltGenerator = this.config.getSaltGenerator();
        String configProviderName = this.config.getProviderName();
        if (configProviderName != null) {
          CommonUtils.validateNotEmpty(configProviderName, "Provider name cannot be empty");
        }
        Provider configProvider = this.config.getProvider();
        Boolean configInvertPositionOfSaltInMessageBeforeDigesting = this.config.getInvertPositionOfSaltInMessageBeforeDigesting();
        Boolean configInvertPositionOfPlainSaltInEncryptionResults = this.config.getInvertPositionOfPlainSaltInEncryptionResults();
        Boolean configUseLenientSaltSizeCheck = this.config.getUseLenientSaltSizeCheck();
        this.algorithm = ((this.algorithmSet) || (configAlgorithm == null) ? this.algorithm : configAlgorithm);
        this.saltSizeBytes = ((this.saltSizeBytesSet) || (configSaltSizeBytes == null) ? this.saltSizeBytes : configSaltSizeBytes.intValue());
        this.iterations = ((this.iterationsSet) || (configIterations == null) ? this.iterations : configIterations.intValue());
        this.saltGenerator = ((this.saltGeneratorSet) || (configSaltGenerator == null) ? this.saltGenerator : configSaltGenerator);
        this.providerName = ((this.providerNameSet) || (configProviderName == null) ? this.providerName : configProviderName);
        this.provider = ((this.providerSet) || (configProvider == null) ? this.provider : configProvider);
        this.invertPositionOfSaltInMessageBeforeDigesting = ((this.invertPositionOfSaltInMessageBeforeDigestingSet) || (configInvertPositionOfSaltInMessageBeforeDigesting == null) ? this.invertPositionOfSaltInMessageBeforeDigesting : configInvertPositionOfSaltInMessageBeforeDigesting.booleanValue());
        this.invertPositionOfPlainSaltInEncryptionResults = ((this.invertPositionOfPlainSaltInEncryptionResultsSet) || (configInvertPositionOfPlainSaltInEncryptionResults == null) ? this.invertPositionOfPlainSaltInEncryptionResults : configInvertPositionOfPlainSaltInEncryptionResults.booleanValue());
        this.useLenientSaltSizeCheck = ((this.useLenientSaltSizeCheckSet) || (configUseLenientSaltSizeCheck == null) ? this.useLenientSaltSizeCheck : configUseLenientSaltSizeCheck.booleanValue());
      }
      if (this.saltGenerator == null) {
        this.saltGenerator = new RandomSaltGenerator();
      }
      if ((this.useLenientSaltSizeCheck) && (!this.saltGenerator.includePlainSaltInEncryptionResults())) {
        throw new EncryptionInitializationException("The configured Salt Generator (" + this.saltGenerator.getClass().getName() + ") does not include plain salt " + "in encryption results, which is not compatible" + "with setting the salt size checking behaviour to \"lenient\".");
      }
      try
      {
        if (this.provider != null) {
          this.field_374 = MessageDigest.getInstance(this.algorithm, this.provider);
        } else if (this.providerName != null) {
          this.field_374 = MessageDigest.getInstance(this.algorithm, this.providerName);
        } else {
          this.field_374 = MessageDigest.getInstance(this.algorithm);
        }
      }
      catch (NoSuchAlgorithmException configAlgorithm)
      {
        throw new EncryptionInitializationException(configAlgorithm);
      }
      catch (NoSuchProviderException configAlgorithm)
      {
        throw new EncryptionInitializationException(configAlgorithm);
      }
      this.digestLengthBytes = this.field_374.getDigestLength();
      if (this.digestLengthBytes <= 0) {
        throw new EncryptionInitializationException("The configured algorithm (" + this.algorithm + ") or its provider do  " + "not allow knowing the digest length beforehand " + "(getDigestLength() operation), which is not compatible" + "with setting the salt size checking behaviour to \"lenient\".");
      }
      this.initialized = true;
    }
  }
  
  public byte[] digest(byte[] message)
  {
    if (message == null) {
      return null;
    }
    if (!isInitialized()) {
      initialize();
    }
    byte[] salt = null;
    if (this.useSalt) {
      salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
    }
    return digest(message, salt);
  }
  
  private byte[] digest(byte[] message, byte[] salt)
  {
    try
    {
      byte[] digest = null;
      synchronized (this.field_374)
      {
        this.field_374.reset();
        if (salt != null)
        {
          if (!this.invertPositionOfSaltInMessageBeforeDigesting)
          {
            this.field_374.update(salt);
            this.field_374.update(message);
          }
          else
          {
            this.field_374.update(message);
            this.field_374.update(salt);
          }
        }
        else {
          this.field_374.update(message);
        }
        digest = this.field_374.digest();
        for (int local_i = 0; local_i < this.iterations - 1; local_i++)
        {
          this.field_374.reset();
          digest = this.field_374.digest(digest);
        }
      }
      if ((this.saltGenerator.includePlainSaltInEncryptionResults()) && (salt != null))
      {
        if (!this.invertPositionOfPlainSaltInEncryptionResults) {
          return CommonUtils.appendArrays(salt, digest);
        }
        return CommonUtils.appendArrays(digest, salt);
      }
      return digest;
    }
    catch (Exception digest)
    {
      throw new EncryptionOperationNotPossibleException();
    }
  }
  
  public boolean matches(byte[] message, byte[] digest)
  {
    if (message == null) {
      return digest == null;
    }
    if (digest == null) {
      return false;
    }
    if (!isInitialized()) {
      initialize();
    }
    try
    {
      byte[] salt = null;
      if (this.useSalt) {
        if (this.saltGenerator.includePlainSaltInEncryptionResults())
        {
          int digestSaltSize = this.saltSizeBytes;
          if (this.digestLengthBytes > 0)
          {
            if (this.useLenientSaltSizeCheck)
            {
              if (digest.length < this.digestLengthBytes) {
                throw new EncryptionOperationNotPossibleException();
              }
              digestSaltSize = digest.length - this.digestLengthBytes;
            }
            else if (digest.length != this.digestLengthBytes + this.saltSizeBytes)
            {
              throw new EncryptionOperationNotPossibleException();
            }
          }
          else if (digest.length < this.saltSizeBytes) {
            throw new EncryptionOperationNotPossibleException();
          }
          if (!this.invertPositionOfPlainSaltInEncryptionResults)
          {
            salt = new byte[digestSaltSize];
            System.arraycopy(digest, 0, salt, 0, digestSaltSize);
          }
          else
          {
            salt = new byte[digestSaltSize];
            System.arraycopy(digest, digest.length - digestSaltSize, salt, 0, digestSaltSize);
          }
        }
        else
        {
          salt = this.saltGenerator.generateSalt(this.saltSizeBytes);
        }
      }
      byte[] digestSaltSize = digest(message, salt);
      return Arrays.equals(digestSaltSize, digest);
    }
    catch (Exception salt)
    {
      throw new EncryptionOperationNotPossibleException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.digest.StandardByteDigester
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */