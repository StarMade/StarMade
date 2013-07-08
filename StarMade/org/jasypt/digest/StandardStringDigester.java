package org.jasypt.digest;

import java.security.Provider;
import org.jasypt.commons.CommonUtils;
import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.jasypt.digest.config.DigesterConfig;
import org.jasypt.digest.config.StringDigesterConfig;
import org.jasypt.exceptions.AlreadyInitializedException;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.normalization.Normalizer;
import org.jasypt.salt.SaltGenerator;

public final class StandardStringDigester
  implements StringDigester
{
  public static final String MESSAGE_CHARSET = "UTF-8";
  public static final String DIGEST_CHARSET = "US-ASCII";
  public static final boolean DEFAULT_UNICODE_NORMALIZATION_IGNORED = false;
  public static final String DEFAULT_STRING_OUTPUT_TYPE = "base64";
  private final StandardByteDigester byteDigester;
  private StringDigesterConfig stringDigesterConfig = null;
  private boolean unicodeNormalizationIgnored = false;
  private String stringOutputType = "base64";
  private boolean stringOutputTypeBase64 = true;
  private String prefix = null;
  private String suffix = null;
  private boolean unicodeNormalizationIgnoredSet = false;
  private boolean stringOutputTypeSet = false;
  private boolean prefixSet = false;
  private boolean suffixSet = false;
  private final Base64 base64;
  
  public StandardStringDigester()
  {
    this.byteDigester = new StandardByteDigester();
    this.base64 = new Base64();
  }
  
  private StandardStringDigester(StandardByteDigester standardByteDigester)
  {
    this.byteDigester = standardByteDigester;
    this.base64 = new Base64();
  }
  
  public synchronized void setConfig(DigesterConfig config)
  {
    this.byteDigester.setConfig(config);
    if ((config != null) && ((config instanceof StringDigesterConfig))) {
      this.stringDigesterConfig = ((StringDigesterConfig)config);
    }
  }
  
  public void setAlgorithm(String algorithm)
  {
    this.byteDigester.setAlgorithm(algorithm);
  }
  
  public void setSaltSizeBytes(int saltSizeBytes)
  {
    this.byteDigester.setSaltSizeBytes(saltSizeBytes);
  }
  
  public void setIterations(int iterations)
  {
    this.byteDigester.setIterations(iterations);
  }
  
  public void setSaltGenerator(SaltGenerator saltGenerator)
  {
    this.byteDigester.setSaltGenerator(saltGenerator);
  }
  
  public void setProviderName(String providerName)
  {
    this.byteDigester.setProviderName(providerName);
  }
  
  public void setProvider(Provider provider)
  {
    this.byteDigester.setProvider(provider);
  }
  
  public synchronized void setInvertPositionOfSaltInMessageBeforeDigesting(boolean invertPositionOfSaltInMessageBeforeDigesting)
  {
    this.byteDigester.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
  }
  
  public synchronized void setInvertPositionOfPlainSaltInEncryptionResults(boolean invertPositionOfPlainSaltInEncryptionResults)
  {
    this.byteDigester.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
  }
  
  public synchronized void setUseLenientSaltSizeCheck(boolean useLenientSaltSizeCheck)
  {
    this.byteDigester.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
  }
  
  public synchronized void setUnicodeNormalizationIgnored(boolean unicodeNormalizationIgnored)
  {
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.unicodeNormalizationIgnored = unicodeNormalizationIgnored;
    this.unicodeNormalizationIgnoredSet = true;
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
  
  public synchronized void setPrefix(String prefix)
  {
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.prefix = prefix;
    this.prefixSet = true;
  }
  
  public synchronized void setSuffix(String suffix)
  {
    if (isInitialized()) {
      throw new AlreadyInitializedException();
    }
    this.suffix = suffix;
    this.suffixSet = true;
  }
  
  StandardStringDigester cloneDigester()
  {
    if (!isInitialized()) {
      initialize();
    }
    StandardStringDigester cloned = new StandardStringDigester(this.byteDigester.cloneDigester());
    cloned.setPrefix(this.prefix);
    cloned.setSuffix(this.suffix);
    if (CommonUtils.isNotEmpty(this.stringOutputType)) {
      cloned.setStringOutputType(this.stringOutputType);
    }
    cloned.setUnicodeNormalizationIgnored(this.unicodeNormalizationIgnored);
    return cloned;
  }
  
  public boolean isInitialized()
  {
    return this.byteDigester.isInitialized();
  }
  
  public synchronized void initialize()
  {
    if (!isInitialized())
    {
      if (this.stringDigesterConfig != null)
      {
        Boolean configUnicodeNormalizationIgnored = this.stringDigesterConfig.isUnicodeNormalizationIgnored();
        String configStringOutputType = this.stringDigesterConfig.getStringOutputType();
        String configPrefix = this.stringDigesterConfig.getPrefix();
        String configSuffix = this.stringDigesterConfig.getSuffix();
        this.unicodeNormalizationIgnored = ((this.unicodeNormalizationIgnoredSet) || (configUnicodeNormalizationIgnored == null) ? this.unicodeNormalizationIgnored : configUnicodeNormalizationIgnored.booleanValue());
        this.stringOutputType = ((this.stringOutputTypeSet) || (configStringOutputType == null) ? this.stringOutputType : configStringOutputType);
        this.prefix = ((this.prefixSet) || (configPrefix == null) ? this.prefix : configPrefix);
        this.suffix = ((this.suffixSet) || (configSuffix == null) ? this.suffix : configSuffix);
      }
      this.stringOutputTypeBase64 = "base64".equalsIgnoreCase(this.stringOutputType);
      this.byteDigester.initialize();
    }
  }
  
  public String digest(String message)
  {
    if (message == null) {
      return null;
    }
    if (!isInitialized()) {
      initialize();
    }
    try
    {
      String normalizedMessage = null;
      if (!this.unicodeNormalizationIgnored) {
        normalizedMessage = Normalizer.normalizeToNfc(message);
      } else {
        normalizedMessage = message;
      }
      byte[] messageBytes = normalizedMessage.getBytes("UTF-8");
      byte[] digest = this.byteDigester.digest(messageBytes);
      StringBuffer result = new StringBuffer();
      if (this.prefix != null) {
        result.append(this.prefix);
      }
      if (this.stringOutputTypeBase64)
      {
        digest = this.base64.encode(digest);
        result.append(new String(digest, "US-ASCII"));
      }
      else
      {
        result.append(CommonUtils.toHexadecimal(digest));
      }
      if (this.suffix != null) {
        result.append(this.suffix);
      }
      return result.toString();
    }
    catch (EncryptionInitializationException normalizedMessage)
    {
      throw normalizedMessage;
    }
    catch (EncryptionOperationNotPossibleException normalizedMessage)
    {
      throw normalizedMessage;
    }
    catch (Exception normalizedMessage)
    {
      throw new EncryptionOperationNotPossibleException();
    }
  }
  
  public boolean matches(String message, String digest)
  {
    String processedDigest = digest;
    if (processedDigest != null)
    {
      if (this.prefix != null)
      {
        if (!processedDigest.startsWith(this.prefix)) {
          throw new EncryptionOperationNotPossibleException("Digest does not start with required prefix \"" + this.prefix + "\"");
        }
        processedDigest = processedDigest.substring(this.prefix.length());
      }
      if (this.suffix != null)
      {
        if (!processedDigest.endsWith(this.suffix)) {
          throw new EncryptionOperationNotPossibleException("Digest does not end with required suffix \"" + this.suffix + "\"");
        }
        processedDigest = processedDigest.substring(0, processedDigest.length() - this.suffix.length());
      }
    }
    if (message == null) {
      return processedDigest == null;
    }
    if (processedDigest == null) {
      return false;
    }
    if (!isInitialized()) {
      initialize();
    }
    try
    {
      String normalizedMessage = null;
      if (!this.unicodeNormalizationIgnored) {
        normalizedMessage = Normalizer.normalizeToNfc(message);
      } else {
        normalizedMessage = message;
      }
      byte[] messageBytes = normalizedMessage.getBytes("UTF-8");
      byte[] digestBytes = null;
      if (this.stringOutputTypeBase64)
      {
        digestBytes = processedDigest.getBytes("US-ASCII");
        digestBytes = this.base64.decode(digestBytes);
      }
      else
      {
        digestBytes = CommonUtils.fromHexadecimal(processedDigest);
      }
      return this.byteDigester.matches(messageBytes, digestBytes);
    }
    catch (EncryptionInitializationException normalizedMessage)
    {
      throw normalizedMessage;
    }
    catch (EncryptionOperationNotPossibleException normalizedMessage)
    {
      throw normalizedMessage;
    }
    catch (Exception normalizedMessage)
    {
      throw new EncryptionOperationNotPossibleException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.digest.StandardStringDigester
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */