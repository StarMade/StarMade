package org.jasypt.intf.service;

import org.jasypt.commons.CommonUtils;
import org.jasypt.digest.StandardStringDigester;
import org.jasypt.digest.config.EnvironmentStringDigesterConfig;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

public final class JasyptStatelessService
{
  public String digest(String input, String algorithm, String algorithmEnvName, String algorithmSysPropertyName, String iterations, String iterationsEnvName, String iterationsSysPropertyName, String saltSizeBytes, String saltSizeBytesEnvName, String saltSizeBytesSysPropertyName, String saltGeneratorClassName, String saltGeneratorClassNameEnvName, String saltGeneratorClassNameSysPropertyName, String providerName, String providerNameEnvName, String providerNameSysPropertyName, String providerClassName, String providerClassNameEnvName, String providerClassNameSysPropertyName, String invertPositionOfSaltInMessageBeforeDigesting, String invertPositionOfSaltInMessageBeforeDigestingEnvName, String invertPositionOfSaltInMessageBeforeDigestingSysPropertyName, String invertPositionOfPlainSaltInEncryptionResults, String invertPositionOfPlainSaltInEncryptionResultsEnvName, String invertPositionOfPlainSaltInEncryptionResultsSysPropertyName, String useLenientSaltSizeCheck, String useLenientSaltSizeCheckEnvName, String useLenientSaltSizeCheckSysPropertyName, String unicodeNormalizationIgnored, String unicodeNormalizationIgnoredEnvName, String unicodeNormalizationIgnoredSysPropertyName, String stringOutputType, String stringOutputTypeEnvName, String stringOutputTypeSysPropertyName, String prefix, String prefixEnvName, String prefixSysPropertyName, String suffix, String suffixEnvName, String suffixSysPropertyName)
  {
    EnvironmentStringDigesterConfig config = new EnvironmentStringDigesterConfig();
    if (algorithmEnvName != null) {
      config.setAlgorithmEnvName(algorithmEnvName);
    }
    if (algorithmSysPropertyName != null) {
      config.setAlgorithmSysPropertyName(algorithmSysPropertyName);
    }
    if (algorithm != null) {
      config.setAlgorithm(algorithm);
    }
    if (iterationsEnvName != null) {
      config.setIterationsEnvName(iterationsEnvName);
    }
    if (iterationsSysPropertyName != null) {
      config.setIterationsSysPropertyName(iterationsSysPropertyName);
    }
    if (iterations != null) {
      config.setIterations(iterations);
    }
    if (saltSizeBytesEnvName != null) {
      config.setSaltSizeBytesEnvName(saltSizeBytesEnvName);
    }
    if (saltSizeBytesSysPropertyName != null) {
      config.setSaltSizeBytesSysPropertyName(saltSizeBytesSysPropertyName);
    }
    if (saltSizeBytes != null) {
      config.setSaltSizeBytes(saltSizeBytes);
    }
    if (saltGeneratorClassNameEnvName != null) {
      config.setSaltGeneratorClassNameEnvName(saltGeneratorClassNameEnvName);
    }
    if (saltGeneratorClassNameSysPropertyName != null) {
      config.setSaltGeneratorClassNameSysPropertyName(saltGeneratorClassNameSysPropertyName);
    }
    if (saltGeneratorClassName != null) {
      config.setSaltGeneratorClassName(saltGeneratorClassName);
    }
    if (providerNameEnvName != null) {
      config.setProviderNameEnvName(providerNameEnvName);
    }
    if (providerNameSysPropertyName != null) {
      config.setProviderNameSysPropertyName(providerNameSysPropertyName);
    }
    if (providerName != null) {
      config.setProviderName(providerName);
    }
    if (providerClassNameEnvName != null) {
      config.setProviderClassNameEnvName(providerClassNameEnvName);
    }
    if (providerClassNameSysPropertyName != null) {
      config.setProviderClassNameSysPropertyName(providerClassNameSysPropertyName);
    }
    if (providerClassName != null) {
      config.setProviderClassName(providerClassName);
    }
    if (invertPositionOfSaltInMessageBeforeDigestingEnvName != null) {
      config.setInvertPositionOfSaltInMessageBeforeDigestingEnvName(invertPositionOfSaltInMessageBeforeDigestingEnvName);
    }
    if (invertPositionOfSaltInMessageBeforeDigestingSysPropertyName != null) {
      config.setInvertPositionOfSaltInMessageBeforeDigestingSysPropertyName(invertPositionOfSaltInMessageBeforeDigestingSysPropertyName);
    }
    if (invertPositionOfSaltInMessageBeforeDigesting != null) {
      config.setInvertPositionOfSaltInMessageBeforeDigesting(CommonUtils.getStandardBooleanValue(invertPositionOfSaltInMessageBeforeDigesting));
    }
    if (invertPositionOfPlainSaltInEncryptionResultsEnvName != null) {
      config.setInvertPositionOfPlainSaltInEncryptionResultsEnvName(invertPositionOfPlainSaltInEncryptionResultsEnvName);
    }
    if (invertPositionOfPlainSaltInEncryptionResultsSysPropertyName != null) {
      config.setInvertPositionOfPlainSaltInEncryptionResultsSysPropertyName(invertPositionOfPlainSaltInEncryptionResultsSysPropertyName);
    }
    if (invertPositionOfPlainSaltInEncryptionResults != null) {
      config.setInvertPositionOfPlainSaltInEncryptionResults(CommonUtils.getStandardBooleanValue(invertPositionOfPlainSaltInEncryptionResults));
    }
    if (useLenientSaltSizeCheckEnvName != null) {
      config.setUseLenientSaltSizeCheckEnvName(useLenientSaltSizeCheckEnvName);
    }
    if (useLenientSaltSizeCheckSysPropertyName != null) {
      config.setUseLenientSaltSizeCheckSysPropertyName(useLenientSaltSizeCheckSysPropertyName);
    }
    if (useLenientSaltSizeCheck != null) {
      config.setUseLenientSaltSizeCheck(CommonUtils.getStandardBooleanValue(useLenientSaltSizeCheck));
    }
    if (unicodeNormalizationIgnoredEnvName != null) {
      config.setUnicodeNormalizationIgnoredEnvName(unicodeNormalizationIgnoredEnvName);
    }
    if (unicodeNormalizationIgnoredSysPropertyName != null) {
      config.setUnicodeNormalizationIgnoredSysPropertyName(unicodeNormalizationIgnoredSysPropertyName);
    }
    if (unicodeNormalizationIgnored != null) {
      config.setUnicodeNormalizationIgnored(unicodeNormalizationIgnored);
    }
    if (stringOutputTypeEnvName != null) {
      config.setStringOutputTypeEnvName(stringOutputTypeEnvName);
    }
    if (stringOutputTypeSysPropertyName != null) {
      config.setStringOutputTypeSysPropertyName(stringOutputTypeSysPropertyName);
    }
    if (stringOutputType != null) {
      config.setStringOutputType(stringOutputType);
    }
    if (prefixEnvName != null) {
      config.setPrefixEnvName(prefixEnvName);
    }
    if (prefixSysPropertyName != null) {
      config.setPrefixSysPropertyName(prefixSysPropertyName);
    }
    if (prefix != null) {
      config.setPrefix(prefix);
    }
    if (suffixEnvName != null) {
      config.setSuffixEnvName(suffixEnvName);
    }
    if (suffixSysPropertyName != null) {
      config.setSuffixSysPropertyName(suffixSysPropertyName);
    }
    if (suffix != null) {
      config.setSuffix(suffix);
    }
    StandardStringDigester digester = new StandardStringDigester();
    digester.setConfig(config);
    return digester.digest(input);
  }
  
  public String encrypt(String input, String password, String passwordEnvName, String passwordSysPropertyName, String algorithm, String algorithmEnvName, String algorithmSysPropertyName, String keyObtentionIterations, String keyObtentionIterationsEnvName, String keyObtentionIterationsSysPropertyName, String saltGeneratorClassName, String saltGeneratorClassNameEnvName, String saltGeneratorClassNameSysPropertyName, String providerName, String providerNameEnvName, String providerNameSysPropertyName, String providerClassName, String providerClassNameEnvName, String providerClassNameSysPropertyName, String stringOutputType, String stringOutputTypeEnvName, String stringOutputTypeSysPropertyName)
  {
    EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
    if (algorithmEnvName != null) {
      config.setAlgorithmEnvName(algorithmEnvName);
    }
    if (algorithmSysPropertyName != null) {
      config.setAlgorithmSysPropertyName(algorithmSysPropertyName);
    }
    if (algorithm != null) {
      config.setAlgorithm(algorithm);
    }
    if (keyObtentionIterationsEnvName != null) {
      config.setKeyObtentionIterationsEnvName(keyObtentionIterationsEnvName);
    }
    if (keyObtentionIterationsSysPropertyName != null) {
      config.setKeyObtentionIterationsSysPropertyName(keyObtentionIterationsSysPropertyName);
    }
    if (keyObtentionIterations != null) {
      config.setKeyObtentionIterations(keyObtentionIterations);
    }
    if (passwordEnvName != null) {
      config.setPasswordEnvName(passwordEnvName);
    }
    if (passwordSysPropertyName != null) {
      config.setPasswordSysPropertyName(passwordSysPropertyName);
    }
    if (password != null) {
      config.setPassword(password);
    }
    if (saltGeneratorClassNameEnvName != null) {
      config.setSaltGeneratorClassNameEnvName(saltGeneratorClassNameEnvName);
    }
    if (saltGeneratorClassNameSysPropertyName != null) {
      config.setSaltGeneratorClassNameSysPropertyName(saltGeneratorClassNameSysPropertyName);
    }
    if (saltGeneratorClassName != null) {
      config.setSaltGeneratorClassName(saltGeneratorClassName);
    }
    if (providerNameEnvName != null) {
      config.setProviderNameEnvName(providerNameEnvName);
    }
    if (providerNameSysPropertyName != null) {
      config.setProviderNameSysPropertyName(providerNameSysPropertyName);
    }
    if (providerName != null) {
      config.setProviderName(providerName);
    }
    if (providerClassNameEnvName != null) {
      config.setProviderClassNameEnvName(providerClassNameEnvName);
    }
    if (providerClassNameSysPropertyName != null) {
      config.setProviderClassNameSysPropertyName(providerClassNameSysPropertyName);
    }
    if (providerClassName != null) {
      config.setProviderClassName(providerClassName);
    }
    if (stringOutputTypeEnvName != null) {
      config.setStringOutputTypeEnvName(stringOutputTypeEnvName);
    }
    if (stringOutputTypeSysPropertyName != null) {
      config.setStringOutputTypeSysPropertyName(stringOutputTypeSysPropertyName);
    }
    if (stringOutputType != null) {
      config.setStringOutputType(stringOutputType);
    }
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setConfig(config);
    return encryptor.encrypt(input);
  }
  
  public String decrypt(String input, String password, String passwordEnvName, String passwordSysPropertyName, String algorithm, String algorithmEnvName, String algorithmSysPropertyName, String keyObtentionIterations, String keyObtentionIterationsEnvName, String keyObtentionIterationsSysPropertyName, String saltGeneratorClassName, String saltGeneratorClassNameEnvName, String saltGeneratorClassNameSysPropertyName, String providerName, String providerNameEnvName, String providerNameSysPropertyName, String providerClassName, String providerClassNameEnvName, String providerClassNameSysPropertyName, String stringOutputType, String stringOutputTypeEnvName, String stringOutputTypeSysPropertyName)
  {
    EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
    if (algorithmEnvName != null) {
      config.setAlgorithmEnvName(algorithmEnvName);
    }
    if (algorithmSysPropertyName != null) {
      config.setAlgorithmSysPropertyName(algorithmSysPropertyName);
    }
    if (algorithm != null) {
      config.setAlgorithm(algorithm);
    }
    if (keyObtentionIterationsEnvName != null) {
      config.setKeyObtentionIterationsEnvName(keyObtentionIterationsEnvName);
    }
    if (keyObtentionIterationsSysPropertyName != null) {
      config.setKeyObtentionIterationsSysPropertyName(keyObtentionIterationsSysPropertyName);
    }
    if (keyObtentionIterations != null) {
      config.setKeyObtentionIterations(keyObtentionIterations);
    }
    if (passwordEnvName != null) {
      config.setPasswordEnvName(passwordEnvName);
    }
    if (passwordSysPropertyName != null) {
      config.setPasswordSysPropertyName(passwordSysPropertyName);
    }
    if (password != null) {
      config.setPassword(password);
    }
    if (saltGeneratorClassNameEnvName != null) {
      config.setSaltGeneratorClassNameEnvName(saltGeneratorClassNameEnvName);
    }
    if (saltGeneratorClassNameSysPropertyName != null) {
      config.setSaltGeneratorClassNameSysPropertyName(saltGeneratorClassNameSysPropertyName);
    }
    if (saltGeneratorClassName != null) {
      config.setSaltGeneratorClassName(saltGeneratorClassName);
    }
    if (providerNameEnvName != null) {
      config.setProviderNameEnvName(providerNameEnvName);
    }
    if (providerNameSysPropertyName != null) {
      config.setProviderNameSysPropertyName(providerNameSysPropertyName);
    }
    if (providerName != null) {
      config.setProviderName(providerName);
    }
    if (providerClassNameEnvName != null) {
      config.setProviderClassNameEnvName(providerClassNameEnvName);
    }
    if (providerClassNameSysPropertyName != null) {
      config.setProviderClassNameSysPropertyName(providerClassNameSysPropertyName);
    }
    if (providerClassName != null) {
      config.setProviderClassName(providerClassName);
    }
    if (stringOutputTypeEnvName != null) {
      config.setStringOutputTypeEnvName(stringOutputTypeEnvName);
    }
    if (stringOutputTypeSysPropertyName != null) {
      config.setStringOutputTypeSysPropertyName(stringOutputTypeSysPropertyName);
    }
    if (stringOutputType != null) {
      config.setStringOutputType(stringOutputType);
    }
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setConfig(config);
    return encryptor.decrypt(input);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.intf.service.JasyptStatelessService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */