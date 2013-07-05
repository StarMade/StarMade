/*     */ package org.jasypt.intf.service;
/*     */ 
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.digest.StandardStringDigester;
/*     */ import org.jasypt.digest.config.EnvironmentStringDigesterConfig;
/*     */ import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
/*     */ import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
/*     */ 
/*     */ public final class JasyptStatelessService
/*     */ {
/*     */   public String digest(String input, String algorithm, String algorithmEnvName, String algorithmSysPropertyName, String iterations, String iterationsEnvName, String iterationsSysPropertyName, String saltSizeBytes, String saltSizeBytesEnvName, String saltSizeBytesSysPropertyName, String saltGeneratorClassName, String saltGeneratorClassNameEnvName, String saltGeneratorClassNameSysPropertyName, String providerName, String providerNameEnvName, String providerNameSysPropertyName, String providerClassName, String providerClassNameEnvName, String providerClassNameSysPropertyName, String invertPositionOfSaltInMessageBeforeDigesting, String invertPositionOfSaltInMessageBeforeDigestingEnvName, String invertPositionOfSaltInMessageBeforeDigestingSysPropertyName, String invertPositionOfPlainSaltInEncryptionResults, String invertPositionOfPlainSaltInEncryptionResultsEnvName, String invertPositionOfPlainSaltInEncryptionResultsSysPropertyName, String useLenientSaltSizeCheck, String useLenientSaltSizeCheckEnvName, String useLenientSaltSizeCheckSysPropertyName, String unicodeNormalizationIgnored, String unicodeNormalizationIgnoredEnvName, String unicodeNormalizationIgnoredSysPropertyName, String stringOutputType, String stringOutputTypeEnvName, String stringOutputTypeSysPropertyName, String prefix, String prefixEnvName, String prefixSysPropertyName, String suffix, String suffixEnvName, String suffixSysPropertyName)
/*     */   {
/* 155 */     EnvironmentStringDigesterConfig config = new EnvironmentStringDigesterConfig();
/*     */ 
/* 158 */     if (algorithmEnvName != null) {
/* 159 */       config.setAlgorithmEnvName(algorithmEnvName);
/*     */     }
/* 161 */     if (algorithmSysPropertyName != null) {
/* 162 */       config.setAlgorithmSysPropertyName(algorithmSysPropertyName);
/*     */     }
/* 164 */     if (algorithm != null) {
/* 165 */       config.setAlgorithm(algorithm);
/*     */     }
/*     */ 
/* 168 */     if (iterationsEnvName != null) {
/* 169 */       config.setIterationsEnvName(iterationsEnvName);
/*     */     }
/* 171 */     if (iterationsSysPropertyName != null) {
/* 172 */       config.setIterationsSysPropertyName(iterationsSysPropertyName);
/*     */     }
/* 174 */     if (iterations != null) {
/* 175 */       config.setIterations(iterations);
/*     */     }
/*     */ 
/* 178 */     if (saltSizeBytesEnvName != null) {
/* 179 */       config.setSaltSizeBytesEnvName(saltSizeBytesEnvName);
/*     */     }
/* 181 */     if (saltSizeBytesSysPropertyName != null) {
/* 182 */       config.setSaltSizeBytesSysPropertyName(saltSizeBytesSysPropertyName);
/*     */     }
/* 184 */     if (saltSizeBytes != null) {
/* 185 */       config.setSaltSizeBytes(saltSizeBytes);
/*     */     }
/*     */ 
/* 188 */     if (saltGeneratorClassNameEnvName != null) {
/* 189 */       config.setSaltGeneratorClassNameEnvName(saltGeneratorClassNameEnvName);
/*     */     }
/*     */ 
/* 192 */     if (saltGeneratorClassNameSysPropertyName != null) {
/* 193 */       config.setSaltGeneratorClassNameSysPropertyName(saltGeneratorClassNameSysPropertyName);
/*     */     }
/*     */ 
/* 196 */     if (saltGeneratorClassName != null) {
/* 197 */       config.setSaltGeneratorClassName(saltGeneratorClassName);
/*     */     }
/*     */ 
/* 200 */     if (providerNameEnvName != null) {
/* 201 */       config.setProviderNameEnvName(providerNameEnvName);
/*     */     }
/* 203 */     if (providerNameSysPropertyName != null) {
/* 204 */       config.setProviderNameSysPropertyName(providerNameSysPropertyName);
/*     */     }
/* 206 */     if (providerName != null) {
/* 207 */       config.setProviderName(providerName);
/*     */     }
/*     */ 
/* 210 */     if (providerClassNameEnvName != null) {
/* 211 */       config.setProviderClassNameEnvName(providerClassNameEnvName);
/*     */     }
/* 213 */     if (providerClassNameSysPropertyName != null) {
/* 214 */       config.setProviderClassNameSysPropertyName(providerClassNameSysPropertyName);
/*     */     }
/*     */ 
/* 217 */     if (providerClassName != null) {
/* 218 */       config.setProviderClassName(providerClassName);
/*     */     }
/*     */ 
/* 221 */     if (invertPositionOfSaltInMessageBeforeDigestingEnvName != null) {
/* 222 */       config.setInvertPositionOfSaltInMessageBeforeDigestingEnvName(invertPositionOfSaltInMessageBeforeDigestingEnvName);
/*     */     }
/* 224 */     if (invertPositionOfSaltInMessageBeforeDigestingSysPropertyName != null) {
/* 225 */       config.setInvertPositionOfSaltInMessageBeforeDigestingSysPropertyName(invertPositionOfSaltInMessageBeforeDigestingSysPropertyName);
/*     */     }
/*     */ 
/* 228 */     if (invertPositionOfSaltInMessageBeforeDigesting != null) {
/* 229 */       config.setInvertPositionOfSaltInMessageBeforeDigesting(CommonUtils.getStandardBooleanValue(invertPositionOfSaltInMessageBeforeDigesting));
/*     */     }
/*     */ 
/* 233 */     if (invertPositionOfPlainSaltInEncryptionResultsEnvName != null) {
/* 234 */       config.setInvertPositionOfPlainSaltInEncryptionResultsEnvName(invertPositionOfPlainSaltInEncryptionResultsEnvName);
/*     */     }
/* 236 */     if (invertPositionOfPlainSaltInEncryptionResultsSysPropertyName != null) {
/* 237 */       config.setInvertPositionOfPlainSaltInEncryptionResultsSysPropertyName(invertPositionOfPlainSaltInEncryptionResultsSysPropertyName);
/*     */     }
/*     */ 
/* 240 */     if (invertPositionOfPlainSaltInEncryptionResults != null) {
/* 241 */       config.setInvertPositionOfPlainSaltInEncryptionResults(CommonUtils.getStandardBooleanValue(invertPositionOfPlainSaltInEncryptionResults));
/*     */     }
/*     */ 
/* 245 */     if (useLenientSaltSizeCheckEnvName != null) {
/* 246 */       config.setUseLenientSaltSizeCheckEnvName(useLenientSaltSizeCheckEnvName);
/*     */     }
/* 248 */     if (useLenientSaltSizeCheckSysPropertyName != null) {
/* 249 */       config.setUseLenientSaltSizeCheckSysPropertyName(useLenientSaltSizeCheckSysPropertyName);
/*     */     }
/*     */ 
/* 252 */     if (useLenientSaltSizeCheck != null) {
/* 253 */       config.setUseLenientSaltSizeCheck(CommonUtils.getStandardBooleanValue(useLenientSaltSizeCheck));
/*     */     }
/*     */ 
/* 257 */     if (unicodeNormalizationIgnoredEnvName != null) {
/* 258 */       config.setUnicodeNormalizationIgnoredEnvName(unicodeNormalizationIgnoredEnvName);
/*     */     }
/*     */ 
/* 261 */     if (unicodeNormalizationIgnoredSysPropertyName != null) {
/* 262 */       config.setUnicodeNormalizationIgnoredSysPropertyName(unicodeNormalizationIgnoredSysPropertyName);
/*     */     }
/*     */ 
/* 265 */     if (unicodeNormalizationIgnored != null) {
/* 266 */       config.setUnicodeNormalizationIgnored(unicodeNormalizationIgnored);
/*     */     }
/*     */ 
/* 269 */     if (stringOutputTypeEnvName != null) {
/* 270 */       config.setStringOutputTypeEnvName(stringOutputTypeEnvName);
/*     */     }
/* 272 */     if (stringOutputTypeSysPropertyName != null) {
/* 273 */       config.setStringOutputTypeSysPropertyName(stringOutputTypeSysPropertyName);
/*     */     }
/*     */ 
/* 276 */     if (stringOutputType != null) {
/* 277 */       config.setStringOutputType(stringOutputType);
/*     */     }
/*     */ 
/* 280 */     if (prefixEnvName != null) {
/* 281 */       config.setPrefixEnvName(prefixEnvName);
/*     */     }
/* 283 */     if (prefixSysPropertyName != null) {
/* 284 */       config.setPrefixSysPropertyName(prefixSysPropertyName);
/*     */     }
/*     */ 
/* 287 */     if (prefix != null) {
/* 288 */       config.setPrefix(prefix);
/*     */     }
/*     */ 
/* 291 */     if (suffixEnvName != null) {
/* 292 */       config.setSuffixEnvName(suffixEnvName);
/*     */     }
/* 294 */     if (suffixSysPropertyName != null) {
/* 295 */       config.setSuffixSysPropertyName(suffixSysPropertyName);
/*     */     }
/*     */ 
/* 298 */     if (suffix != null) {
/* 299 */       config.setSuffix(suffix);
/*     */     }
/*     */ 
/* 303 */     StandardStringDigester digester = new StandardStringDigester();
/* 304 */     digester.setConfig(config);
/*     */ 
/* 306 */     return digester.digest(input);
/*     */   }
/*     */ 
/*     */   public String encrypt(String input, String password, String passwordEnvName, String passwordSysPropertyName, String algorithm, String algorithmEnvName, String algorithmSysPropertyName, String keyObtentionIterations, String keyObtentionIterationsEnvName, String keyObtentionIterationsSysPropertyName, String saltGeneratorClassName, String saltGeneratorClassNameEnvName, String saltGeneratorClassNameSysPropertyName, String providerName, String providerNameEnvName, String providerNameSysPropertyName, String providerClassName, String providerClassNameEnvName, String providerClassNameSysPropertyName, String stringOutputType, String stringOutputTypeEnvName, String stringOutputTypeSysPropertyName)
/*     */   {
/* 368 */     EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
/*     */ 
/* 371 */     if (algorithmEnvName != null) {
/* 372 */       config.setAlgorithmEnvName(algorithmEnvName);
/*     */     }
/* 374 */     if (algorithmSysPropertyName != null) {
/* 375 */       config.setAlgorithmSysPropertyName(algorithmSysPropertyName);
/*     */     }
/* 377 */     if (algorithm != null) {
/* 378 */       config.setAlgorithm(algorithm);
/*     */     }
/*     */ 
/* 381 */     if (keyObtentionIterationsEnvName != null) {
/* 382 */       config.setKeyObtentionIterationsEnvName(keyObtentionIterationsEnvName);
/*     */     }
/*     */ 
/* 385 */     if (keyObtentionIterationsSysPropertyName != null) {
/* 386 */       config.setKeyObtentionIterationsSysPropertyName(keyObtentionIterationsSysPropertyName);
/*     */     }
/*     */ 
/* 389 */     if (keyObtentionIterations != null) {
/* 390 */       config.setKeyObtentionIterations(keyObtentionIterations);
/*     */     }
/*     */ 
/* 393 */     if (passwordEnvName != null) {
/* 394 */       config.setPasswordEnvName(passwordEnvName);
/*     */     }
/* 396 */     if (passwordSysPropertyName != null) {
/* 397 */       config.setPasswordSysPropertyName(passwordSysPropertyName);
/*     */     }
/* 399 */     if (password != null) {
/* 400 */       config.setPassword(password);
/*     */     }
/*     */ 
/* 403 */     if (saltGeneratorClassNameEnvName != null) {
/* 404 */       config.setSaltGeneratorClassNameEnvName(saltGeneratorClassNameEnvName);
/*     */     }
/*     */ 
/* 407 */     if (saltGeneratorClassNameSysPropertyName != null) {
/* 408 */       config.setSaltGeneratorClassNameSysPropertyName(saltGeneratorClassNameSysPropertyName);
/*     */     }
/*     */ 
/* 411 */     if (saltGeneratorClassName != null) {
/* 412 */       config.setSaltGeneratorClassName(saltGeneratorClassName);
/*     */     }
/*     */ 
/* 415 */     if (providerNameEnvName != null) {
/* 416 */       config.setProviderNameEnvName(providerNameEnvName);
/*     */     }
/* 418 */     if (providerNameSysPropertyName != null) {
/* 419 */       config.setProviderNameSysPropertyName(providerNameSysPropertyName);
/*     */     }
/* 421 */     if (providerName != null) {
/* 422 */       config.setProviderName(providerName);
/*     */     }
/*     */ 
/* 425 */     if (providerClassNameEnvName != null) {
/* 426 */       config.setProviderClassNameEnvName(providerClassNameEnvName);
/*     */     }
/* 428 */     if (providerClassNameSysPropertyName != null) {
/* 429 */       config.setProviderClassNameSysPropertyName(providerClassNameSysPropertyName);
/*     */     }
/*     */ 
/* 432 */     if (providerClassName != null) {
/* 433 */       config.setProviderClassName(providerClassName);
/*     */     }
/*     */ 
/* 436 */     if (stringOutputTypeEnvName != null) {
/* 437 */       config.setStringOutputTypeEnvName(stringOutputTypeEnvName);
/*     */     }
/* 439 */     if (stringOutputTypeSysPropertyName != null) {
/* 440 */       config.setStringOutputTypeSysPropertyName(stringOutputTypeSysPropertyName);
/*     */     }
/*     */ 
/* 443 */     if (stringOutputType != null) {
/* 444 */       config.setStringOutputType(stringOutputType);
/*     */     }
/*     */ 
/* 448 */     StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
/* 449 */     encryptor.setConfig(config);
/*     */ 
/* 451 */     return encryptor.encrypt(input);
/*     */   }
/*     */ 
/*     */   public String decrypt(String input, String password, String passwordEnvName, String passwordSysPropertyName, String algorithm, String algorithmEnvName, String algorithmSysPropertyName, String keyObtentionIterations, String keyObtentionIterationsEnvName, String keyObtentionIterationsSysPropertyName, String saltGeneratorClassName, String saltGeneratorClassNameEnvName, String saltGeneratorClassNameSysPropertyName, String providerName, String providerNameEnvName, String providerNameSysPropertyName, String providerClassName, String providerClassNameEnvName, String providerClassNameSysPropertyName, String stringOutputType, String stringOutputTypeEnvName, String stringOutputTypeSysPropertyName)
/*     */   {
/* 513 */     EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
/*     */ 
/* 516 */     if (algorithmEnvName != null) {
/* 517 */       config.setAlgorithmEnvName(algorithmEnvName);
/*     */     }
/* 519 */     if (algorithmSysPropertyName != null) {
/* 520 */       config.setAlgorithmSysPropertyName(algorithmSysPropertyName);
/*     */     }
/* 522 */     if (algorithm != null) {
/* 523 */       config.setAlgorithm(algorithm);
/*     */     }
/*     */ 
/* 526 */     if (keyObtentionIterationsEnvName != null) {
/* 527 */       config.setKeyObtentionIterationsEnvName(keyObtentionIterationsEnvName);
/*     */     }
/*     */ 
/* 530 */     if (keyObtentionIterationsSysPropertyName != null) {
/* 531 */       config.setKeyObtentionIterationsSysPropertyName(keyObtentionIterationsSysPropertyName);
/*     */     }
/*     */ 
/* 534 */     if (keyObtentionIterations != null) {
/* 535 */       config.setKeyObtentionIterations(keyObtentionIterations);
/*     */     }
/*     */ 
/* 538 */     if (passwordEnvName != null) {
/* 539 */       config.setPasswordEnvName(passwordEnvName);
/*     */     }
/* 541 */     if (passwordSysPropertyName != null) {
/* 542 */       config.setPasswordSysPropertyName(passwordSysPropertyName);
/*     */     }
/* 544 */     if (password != null) {
/* 545 */       config.setPassword(password);
/*     */     }
/*     */ 
/* 548 */     if (saltGeneratorClassNameEnvName != null) {
/* 549 */       config.setSaltGeneratorClassNameEnvName(saltGeneratorClassNameEnvName);
/*     */     }
/*     */ 
/* 552 */     if (saltGeneratorClassNameSysPropertyName != null) {
/* 553 */       config.setSaltGeneratorClassNameSysPropertyName(saltGeneratorClassNameSysPropertyName);
/*     */     }
/*     */ 
/* 556 */     if (saltGeneratorClassName != null) {
/* 557 */       config.setSaltGeneratorClassName(saltGeneratorClassName);
/*     */     }
/*     */ 
/* 560 */     if (providerNameEnvName != null) {
/* 561 */       config.setProviderNameEnvName(providerNameEnvName);
/*     */     }
/* 563 */     if (providerNameSysPropertyName != null) {
/* 564 */       config.setProviderNameSysPropertyName(providerNameSysPropertyName);
/*     */     }
/* 566 */     if (providerName != null) {
/* 567 */       config.setProviderName(providerName);
/*     */     }
/*     */ 
/* 570 */     if (providerClassNameEnvName != null) {
/* 571 */       config.setProviderClassNameEnvName(providerClassNameEnvName);
/*     */     }
/* 573 */     if (providerClassNameSysPropertyName != null) {
/* 574 */       config.setProviderClassNameSysPropertyName(providerClassNameSysPropertyName);
/*     */     }
/*     */ 
/* 577 */     if (providerClassName != null) {
/* 578 */       config.setProviderClassName(providerClassName);
/*     */     }
/*     */ 
/* 581 */     if (stringOutputTypeEnvName != null) {
/* 582 */       config.setStringOutputTypeEnvName(stringOutputTypeEnvName);
/*     */     }
/* 584 */     if (stringOutputTypeSysPropertyName != null) {
/* 585 */       config.setStringOutputTypeSysPropertyName(stringOutputTypeSysPropertyName);
/*     */     }
/*     */ 
/* 588 */     if (stringOutputType != null) {
/* 589 */       config.setStringOutputType(stringOutputType);
/*     */     }
/*     */ 
/* 593 */     StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
/* 594 */     encryptor.setConfig(config);
/*     */ 
/* 596 */     return encryptor.decrypt(input);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.service.JasyptStatelessService
 * JD-Core Version:    0.6.2
 */