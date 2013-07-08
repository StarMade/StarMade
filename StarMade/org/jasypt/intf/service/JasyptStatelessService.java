/*   1:    */package org.jasypt.intf.service;
/*   2:    */
/*   3:    */import org.jasypt.commons.CommonUtils;
/*   4:    */import org.jasypt.digest.StandardStringDigester;
/*   5:    */import org.jasypt.digest.config.EnvironmentStringDigesterConfig;
/*   6:    */import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
/*   7:    */import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
/*   8:    */
/* 151:    */public final class JasyptStatelessService
/* 152:    */{
/* 153:    */  public String digest(String input, String algorithm, String algorithmEnvName, String algorithmSysPropertyName, String iterations, String iterationsEnvName, String iterationsSysPropertyName, String saltSizeBytes, String saltSizeBytesEnvName, String saltSizeBytesSysPropertyName, String saltGeneratorClassName, String saltGeneratorClassNameEnvName, String saltGeneratorClassNameSysPropertyName, String providerName, String providerNameEnvName, String providerNameSysPropertyName, String providerClassName, String providerClassNameEnvName, String providerClassNameSysPropertyName, String invertPositionOfSaltInMessageBeforeDigesting, String invertPositionOfSaltInMessageBeforeDigestingEnvName, String invertPositionOfSaltInMessageBeforeDigestingSysPropertyName, String invertPositionOfPlainSaltInEncryptionResults, String invertPositionOfPlainSaltInEncryptionResultsEnvName, String invertPositionOfPlainSaltInEncryptionResultsSysPropertyName, String useLenientSaltSizeCheck, String useLenientSaltSizeCheckEnvName, String useLenientSaltSizeCheckSysPropertyName, String unicodeNormalizationIgnored, String unicodeNormalizationIgnoredEnvName, String unicodeNormalizationIgnoredSysPropertyName, String stringOutputType, String stringOutputTypeEnvName, String stringOutputTypeSysPropertyName, String prefix, String prefixEnvName, String prefixSysPropertyName, String suffix, String suffixEnvName, String suffixSysPropertyName)
/* 154:    */  {
/* 155:155 */    EnvironmentStringDigesterConfig config = new EnvironmentStringDigesterConfig();
/* 156:    */    
/* 158:158 */    if (algorithmEnvName != null) {
/* 159:159 */      config.setAlgorithmEnvName(algorithmEnvName);
/* 160:    */    }
/* 161:161 */    if (algorithmSysPropertyName != null) {
/* 162:162 */      config.setAlgorithmSysPropertyName(algorithmSysPropertyName);
/* 163:    */    }
/* 164:164 */    if (algorithm != null) {
/* 165:165 */      config.setAlgorithm(algorithm);
/* 166:    */    }
/* 167:    */    
/* 168:168 */    if (iterationsEnvName != null) {
/* 169:169 */      config.setIterationsEnvName(iterationsEnvName);
/* 170:    */    }
/* 171:171 */    if (iterationsSysPropertyName != null) {
/* 172:172 */      config.setIterationsSysPropertyName(iterationsSysPropertyName);
/* 173:    */    }
/* 174:174 */    if (iterations != null) {
/* 175:175 */      config.setIterations(iterations);
/* 176:    */    }
/* 177:    */    
/* 178:178 */    if (saltSizeBytesEnvName != null) {
/* 179:179 */      config.setSaltSizeBytesEnvName(saltSizeBytesEnvName);
/* 180:    */    }
/* 181:181 */    if (saltSizeBytesSysPropertyName != null) {
/* 182:182 */      config.setSaltSizeBytesSysPropertyName(saltSizeBytesSysPropertyName);
/* 183:    */    }
/* 184:184 */    if (saltSizeBytes != null) {
/* 185:185 */      config.setSaltSizeBytes(saltSizeBytes);
/* 186:    */    }
/* 187:    */    
/* 188:188 */    if (saltGeneratorClassNameEnvName != null) {
/* 189:189 */      config.setSaltGeneratorClassNameEnvName(saltGeneratorClassNameEnvName);
/* 190:    */    }
/* 191:    */    
/* 192:192 */    if (saltGeneratorClassNameSysPropertyName != null) {
/* 193:193 */      config.setSaltGeneratorClassNameSysPropertyName(saltGeneratorClassNameSysPropertyName);
/* 194:    */    }
/* 195:    */    
/* 196:196 */    if (saltGeneratorClassName != null) {
/* 197:197 */      config.setSaltGeneratorClassName(saltGeneratorClassName);
/* 198:    */    }
/* 199:    */    
/* 200:200 */    if (providerNameEnvName != null) {
/* 201:201 */      config.setProviderNameEnvName(providerNameEnvName);
/* 202:    */    }
/* 203:203 */    if (providerNameSysPropertyName != null) {
/* 204:204 */      config.setProviderNameSysPropertyName(providerNameSysPropertyName);
/* 205:    */    }
/* 206:206 */    if (providerName != null) {
/* 207:207 */      config.setProviderName(providerName);
/* 208:    */    }
/* 209:    */    
/* 210:210 */    if (providerClassNameEnvName != null) {
/* 211:211 */      config.setProviderClassNameEnvName(providerClassNameEnvName);
/* 212:    */    }
/* 213:213 */    if (providerClassNameSysPropertyName != null) {
/* 214:214 */      config.setProviderClassNameSysPropertyName(providerClassNameSysPropertyName);
/* 215:    */    }
/* 216:    */    
/* 217:217 */    if (providerClassName != null) {
/* 218:218 */      config.setProviderClassName(providerClassName);
/* 219:    */    }
/* 220:    */    
/* 221:221 */    if (invertPositionOfSaltInMessageBeforeDigestingEnvName != null) {
/* 222:222 */      config.setInvertPositionOfSaltInMessageBeforeDigestingEnvName(invertPositionOfSaltInMessageBeforeDigestingEnvName);
/* 223:    */    }
/* 224:224 */    if (invertPositionOfSaltInMessageBeforeDigestingSysPropertyName != null) {
/* 225:225 */      config.setInvertPositionOfSaltInMessageBeforeDigestingSysPropertyName(invertPositionOfSaltInMessageBeforeDigestingSysPropertyName);
/* 226:    */    }
/* 227:    */    
/* 228:228 */    if (invertPositionOfSaltInMessageBeforeDigesting != null) {
/* 229:229 */      config.setInvertPositionOfSaltInMessageBeforeDigesting(CommonUtils.getStandardBooleanValue(invertPositionOfSaltInMessageBeforeDigesting));
/* 230:    */    }
/* 231:    */    
/* 233:233 */    if (invertPositionOfPlainSaltInEncryptionResultsEnvName != null) {
/* 234:234 */      config.setInvertPositionOfPlainSaltInEncryptionResultsEnvName(invertPositionOfPlainSaltInEncryptionResultsEnvName);
/* 235:    */    }
/* 236:236 */    if (invertPositionOfPlainSaltInEncryptionResultsSysPropertyName != null) {
/* 237:237 */      config.setInvertPositionOfPlainSaltInEncryptionResultsSysPropertyName(invertPositionOfPlainSaltInEncryptionResultsSysPropertyName);
/* 238:    */    }
/* 239:    */    
/* 240:240 */    if (invertPositionOfPlainSaltInEncryptionResults != null) {
/* 241:241 */      config.setInvertPositionOfPlainSaltInEncryptionResults(CommonUtils.getStandardBooleanValue(invertPositionOfPlainSaltInEncryptionResults));
/* 242:    */    }
/* 243:    */    
/* 245:245 */    if (useLenientSaltSizeCheckEnvName != null) {
/* 246:246 */      config.setUseLenientSaltSizeCheckEnvName(useLenientSaltSizeCheckEnvName);
/* 247:    */    }
/* 248:248 */    if (useLenientSaltSizeCheckSysPropertyName != null) {
/* 249:249 */      config.setUseLenientSaltSizeCheckSysPropertyName(useLenientSaltSizeCheckSysPropertyName);
/* 250:    */    }
/* 251:    */    
/* 252:252 */    if (useLenientSaltSizeCheck != null) {
/* 253:253 */      config.setUseLenientSaltSizeCheck(CommonUtils.getStandardBooleanValue(useLenientSaltSizeCheck));
/* 254:    */    }
/* 255:    */    
/* 257:257 */    if (unicodeNormalizationIgnoredEnvName != null) {
/* 258:258 */      config.setUnicodeNormalizationIgnoredEnvName(unicodeNormalizationIgnoredEnvName);
/* 259:    */    }
/* 260:    */    
/* 261:261 */    if (unicodeNormalizationIgnoredSysPropertyName != null) {
/* 262:262 */      config.setUnicodeNormalizationIgnoredSysPropertyName(unicodeNormalizationIgnoredSysPropertyName);
/* 263:    */    }
/* 264:    */    
/* 265:265 */    if (unicodeNormalizationIgnored != null) {
/* 266:266 */      config.setUnicodeNormalizationIgnored(unicodeNormalizationIgnored);
/* 267:    */    }
/* 268:    */    
/* 269:269 */    if (stringOutputTypeEnvName != null) {
/* 270:270 */      config.setStringOutputTypeEnvName(stringOutputTypeEnvName);
/* 271:    */    }
/* 272:272 */    if (stringOutputTypeSysPropertyName != null) {
/* 273:273 */      config.setStringOutputTypeSysPropertyName(stringOutputTypeSysPropertyName);
/* 274:    */    }
/* 275:    */    
/* 276:276 */    if (stringOutputType != null) {
/* 277:277 */      config.setStringOutputType(stringOutputType);
/* 278:    */    }
/* 279:    */    
/* 280:280 */    if (prefixEnvName != null) {
/* 281:281 */      config.setPrefixEnvName(prefixEnvName);
/* 282:    */    }
/* 283:283 */    if (prefixSysPropertyName != null) {
/* 284:284 */      config.setPrefixSysPropertyName(prefixSysPropertyName);
/* 285:    */    }
/* 286:    */    
/* 287:287 */    if (prefix != null) {
/* 288:288 */      config.setPrefix(prefix);
/* 289:    */    }
/* 290:    */    
/* 291:291 */    if (suffixEnvName != null) {
/* 292:292 */      config.setSuffixEnvName(suffixEnvName);
/* 293:    */    }
/* 294:294 */    if (suffixSysPropertyName != null) {
/* 295:295 */      config.setSuffixSysPropertyName(suffixSysPropertyName);
/* 296:    */    }
/* 297:    */    
/* 298:298 */    if (suffix != null) {
/* 299:299 */      config.setSuffix(suffix);
/* 300:    */    }
/* 301:    */    
/* 303:303 */    StandardStringDigester digester = new StandardStringDigester();
/* 304:304 */    digester.setConfig(config);
/* 305:    */    
/* 306:306 */    return digester.digest(input);
/* 307:    */  }
/* 308:    */  
/* 366:    */  public String encrypt(String input, String password, String passwordEnvName, String passwordSysPropertyName, String algorithm, String algorithmEnvName, String algorithmSysPropertyName, String keyObtentionIterations, String keyObtentionIterationsEnvName, String keyObtentionIterationsSysPropertyName, String saltGeneratorClassName, String saltGeneratorClassNameEnvName, String saltGeneratorClassNameSysPropertyName, String providerName, String providerNameEnvName, String providerNameSysPropertyName, String providerClassName, String providerClassNameEnvName, String providerClassNameSysPropertyName, String stringOutputType, String stringOutputTypeEnvName, String stringOutputTypeSysPropertyName)
/* 367:    */  {
/* 368:368 */    EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
/* 369:    */    
/* 371:371 */    if (algorithmEnvName != null) {
/* 372:372 */      config.setAlgorithmEnvName(algorithmEnvName);
/* 373:    */    }
/* 374:374 */    if (algorithmSysPropertyName != null) {
/* 375:375 */      config.setAlgorithmSysPropertyName(algorithmSysPropertyName);
/* 376:    */    }
/* 377:377 */    if (algorithm != null) {
/* 378:378 */      config.setAlgorithm(algorithm);
/* 379:    */    }
/* 380:    */    
/* 381:381 */    if (keyObtentionIterationsEnvName != null) {
/* 382:382 */      config.setKeyObtentionIterationsEnvName(keyObtentionIterationsEnvName);
/* 383:    */    }
/* 384:    */    
/* 385:385 */    if (keyObtentionIterationsSysPropertyName != null) {
/* 386:386 */      config.setKeyObtentionIterationsSysPropertyName(keyObtentionIterationsSysPropertyName);
/* 387:    */    }
/* 388:    */    
/* 389:389 */    if (keyObtentionIterations != null) {
/* 390:390 */      config.setKeyObtentionIterations(keyObtentionIterations);
/* 391:    */    }
/* 392:    */    
/* 393:393 */    if (passwordEnvName != null) {
/* 394:394 */      config.setPasswordEnvName(passwordEnvName);
/* 395:    */    }
/* 396:396 */    if (passwordSysPropertyName != null) {
/* 397:397 */      config.setPasswordSysPropertyName(passwordSysPropertyName);
/* 398:    */    }
/* 399:399 */    if (password != null) {
/* 400:400 */      config.setPassword(password);
/* 401:    */    }
/* 402:    */    
/* 403:403 */    if (saltGeneratorClassNameEnvName != null) {
/* 404:404 */      config.setSaltGeneratorClassNameEnvName(saltGeneratorClassNameEnvName);
/* 405:    */    }
/* 406:    */    
/* 407:407 */    if (saltGeneratorClassNameSysPropertyName != null) {
/* 408:408 */      config.setSaltGeneratorClassNameSysPropertyName(saltGeneratorClassNameSysPropertyName);
/* 409:    */    }
/* 410:    */    
/* 411:411 */    if (saltGeneratorClassName != null) {
/* 412:412 */      config.setSaltGeneratorClassName(saltGeneratorClassName);
/* 413:    */    }
/* 414:    */    
/* 415:415 */    if (providerNameEnvName != null) {
/* 416:416 */      config.setProviderNameEnvName(providerNameEnvName);
/* 417:    */    }
/* 418:418 */    if (providerNameSysPropertyName != null) {
/* 419:419 */      config.setProviderNameSysPropertyName(providerNameSysPropertyName);
/* 420:    */    }
/* 421:421 */    if (providerName != null) {
/* 422:422 */      config.setProviderName(providerName);
/* 423:    */    }
/* 424:    */    
/* 425:425 */    if (providerClassNameEnvName != null) {
/* 426:426 */      config.setProviderClassNameEnvName(providerClassNameEnvName);
/* 427:    */    }
/* 428:428 */    if (providerClassNameSysPropertyName != null) {
/* 429:429 */      config.setProviderClassNameSysPropertyName(providerClassNameSysPropertyName);
/* 430:    */    }
/* 431:    */    
/* 432:432 */    if (providerClassName != null) {
/* 433:433 */      config.setProviderClassName(providerClassName);
/* 434:    */    }
/* 435:    */    
/* 436:436 */    if (stringOutputTypeEnvName != null) {
/* 437:437 */      config.setStringOutputTypeEnvName(stringOutputTypeEnvName);
/* 438:    */    }
/* 439:439 */    if (stringOutputTypeSysPropertyName != null) {
/* 440:440 */      config.setStringOutputTypeSysPropertyName(stringOutputTypeSysPropertyName);
/* 441:    */    }
/* 442:    */    
/* 443:443 */    if (stringOutputType != null) {
/* 444:444 */      config.setStringOutputType(stringOutputType);
/* 445:    */    }
/* 446:    */    
/* 448:448 */    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
/* 449:449 */    encryptor.setConfig(config);
/* 450:    */    
/* 451:451 */    return encryptor.encrypt(input);
/* 452:    */  }
/* 453:    */  
/* 511:    */  public String decrypt(String input, String password, String passwordEnvName, String passwordSysPropertyName, String algorithm, String algorithmEnvName, String algorithmSysPropertyName, String keyObtentionIterations, String keyObtentionIterationsEnvName, String keyObtentionIterationsSysPropertyName, String saltGeneratorClassName, String saltGeneratorClassNameEnvName, String saltGeneratorClassNameSysPropertyName, String providerName, String providerNameEnvName, String providerNameSysPropertyName, String providerClassName, String providerClassNameEnvName, String providerClassNameSysPropertyName, String stringOutputType, String stringOutputTypeEnvName, String stringOutputTypeSysPropertyName)
/* 512:    */  {
/* 513:513 */    EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
/* 514:    */    
/* 516:516 */    if (algorithmEnvName != null) {
/* 517:517 */      config.setAlgorithmEnvName(algorithmEnvName);
/* 518:    */    }
/* 519:519 */    if (algorithmSysPropertyName != null) {
/* 520:520 */      config.setAlgorithmSysPropertyName(algorithmSysPropertyName);
/* 521:    */    }
/* 522:522 */    if (algorithm != null) {
/* 523:523 */      config.setAlgorithm(algorithm);
/* 524:    */    }
/* 525:    */    
/* 526:526 */    if (keyObtentionIterationsEnvName != null) {
/* 527:527 */      config.setKeyObtentionIterationsEnvName(keyObtentionIterationsEnvName);
/* 528:    */    }
/* 529:    */    
/* 530:530 */    if (keyObtentionIterationsSysPropertyName != null) {
/* 531:531 */      config.setKeyObtentionIterationsSysPropertyName(keyObtentionIterationsSysPropertyName);
/* 532:    */    }
/* 533:    */    
/* 534:534 */    if (keyObtentionIterations != null) {
/* 535:535 */      config.setKeyObtentionIterations(keyObtentionIterations);
/* 536:    */    }
/* 537:    */    
/* 538:538 */    if (passwordEnvName != null) {
/* 539:539 */      config.setPasswordEnvName(passwordEnvName);
/* 540:    */    }
/* 541:541 */    if (passwordSysPropertyName != null) {
/* 542:542 */      config.setPasswordSysPropertyName(passwordSysPropertyName);
/* 543:    */    }
/* 544:544 */    if (password != null) {
/* 545:545 */      config.setPassword(password);
/* 546:    */    }
/* 547:    */    
/* 548:548 */    if (saltGeneratorClassNameEnvName != null) {
/* 549:549 */      config.setSaltGeneratorClassNameEnvName(saltGeneratorClassNameEnvName);
/* 550:    */    }
/* 551:    */    
/* 552:552 */    if (saltGeneratorClassNameSysPropertyName != null) {
/* 553:553 */      config.setSaltGeneratorClassNameSysPropertyName(saltGeneratorClassNameSysPropertyName);
/* 554:    */    }
/* 555:    */    
/* 556:556 */    if (saltGeneratorClassName != null) {
/* 557:557 */      config.setSaltGeneratorClassName(saltGeneratorClassName);
/* 558:    */    }
/* 559:    */    
/* 560:560 */    if (providerNameEnvName != null) {
/* 561:561 */      config.setProviderNameEnvName(providerNameEnvName);
/* 562:    */    }
/* 563:563 */    if (providerNameSysPropertyName != null) {
/* 564:564 */      config.setProviderNameSysPropertyName(providerNameSysPropertyName);
/* 565:    */    }
/* 566:566 */    if (providerName != null) {
/* 567:567 */      config.setProviderName(providerName);
/* 568:    */    }
/* 569:    */    
/* 570:570 */    if (providerClassNameEnvName != null) {
/* 571:571 */      config.setProviderClassNameEnvName(providerClassNameEnvName);
/* 572:    */    }
/* 573:573 */    if (providerClassNameSysPropertyName != null) {
/* 574:574 */      config.setProviderClassNameSysPropertyName(providerClassNameSysPropertyName);
/* 575:    */    }
/* 576:    */    
/* 577:577 */    if (providerClassName != null) {
/* 578:578 */      config.setProviderClassName(providerClassName);
/* 579:    */    }
/* 580:    */    
/* 581:581 */    if (stringOutputTypeEnvName != null) {
/* 582:582 */      config.setStringOutputTypeEnvName(stringOutputTypeEnvName);
/* 583:    */    }
/* 584:584 */    if (stringOutputTypeSysPropertyName != null) {
/* 585:585 */      config.setStringOutputTypeSysPropertyName(stringOutputTypeSysPropertyName);
/* 586:    */    }
/* 587:    */    
/* 588:588 */    if (stringOutputType != null) {
/* 589:589 */      config.setStringOutputType(stringOutputType);
/* 590:    */    }
/* 591:    */    
/* 593:593 */    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
/* 594:594 */    encryptor.setConfig(config);
/* 595:    */    
/* 596:596 */    return encryptor.decrypt(input);
/* 597:    */  }
/* 598:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.service.JasyptStatelessService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */