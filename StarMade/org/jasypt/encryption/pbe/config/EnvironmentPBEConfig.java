/*   1:    */package org.jasypt.encryption.pbe.config;
/*   2:    */
/*   3:    */import java.security.Provider;
/*   4:    */import org.jasypt.salt.SaltGenerator;
/*   5:    */
/*  50:    */public class EnvironmentPBEConfig
/*  51:    */  extends SimplePBEConfig
/*  52:    */{
/*  53: 53 */  private String algorithmEnvName = null;
/*  54: 54 */  private String keyObtentionIterationsEnvName = null;
/*  55: 55 */  private String passwordEnvName = null;
/*  56: 56 */  private String saltGeneratorClassNameEnvName = null;
/*  57: 57 */  private String providerNameEnvName = null;
/*  58: 58 */  private String providerClassNameEnvName = null;
/*  59: 59 */  private String poolSizeEnvName = null;
/*  60:    */  
/*  61: 61 */  private String algorithmSysPropertyName = null;
/*  62: 62 */  private String keyObtentionIterationsSysPropertyName = null;
/*  63: 63 */  private String passwordSysPropertyName = null;
/*  64: 64 */  private String saltGeneratorClassNameSysPropertyName = null;
/*  65: 65 */  private String providerNameSysPropertyName = null;
/*  66: 66 */  private String providerClassNameSysPropertyName = null;
/*  67: 67 */  private String poolSizeSysPropertyName = null;
/*  68:    */  
/*  86:    */  public String getAlgorithmEnvName()
/*  87:    */  {
/*  88: 88 */    return this.algorithmEnvName;
/*  89:    */  }
/*  90:    */  
/*  97:    */  public void setAlgorithmEnvName(String algorithmEnvName)
/*  98:    */  {
/*  99: 99 */    this.algorithmEnvName = algorithmEnvName;
/* 100:100 */    if (algorithmEnvName == null) {
/* 101:101 */      super.setAlgorithm(null);
/* 102:    */    } else {
/* 103:103 */      this.algorithmSysPropertyName = null;
/* 104:104 */      super.setAlgorithm(System.getenv(algorithmEnvName));
/* 105:    */    }
/* 106:    */  }
/* 107:    */  
/* 114:    */  public String getAlgorithmSysPropertyName()
/* 115:    */  {
/* 116:116 */    return this.algorithmSysPropertyName;
/* 117:    */  }
/* 118:    */  
/* 125:    */  public void setAlgorithmSysPropertyName(String algorithmSysPropertyName)
/* 126:    */  {
/* 127:127 */    this.algorithmSysPropertyName = algorithmSysPropertyName;
/* 128:128 */    if (algorithmSysPropertyName == null) {
/* 129:129 */      super.setAlgorithm(null);
/* 130:    */    } else {
/* 131:131 */      this.algorithmEnvName = null;
/* 132:132 */      super.setAlgorithm(System.getProperty(algorithmSysPropertyName));
/* 133:    */    }
/* 134:    */  }
/* 135:    */  
/* 142:    */  public String getKeyObtentionIterationsEnvName()
/* 143:    */  {
/* 144:144 */    return this.keyObtentionIterationsEnvName;
/* 145:    */  }
/* 146:    */  
/* 153:    */  public void setKeyObtentionIterationsEnvName(String keyObtentionIterationsEnvName)
/* 154:    */  {
/* 155:155 */    this.keyObtentionIterationsEnvName = keyObtentionIterationsEnvName;
/* 156:156 */    if (keyObtentionIterationsEnvName == null) {
/* 157:157 */      super.setKeyObtentionIterations((Integer)null);
/* 158:    */    } else {
/* 159:159 */      this.keyObtentionIterationsSysPropertyName = null;
/* 160:160 */      super.setKeyObtentionIterations(System.getenv(keyObtentionIterationsEnvName));
/* 161:    */    }
/* 162:    */  }
/* 163:    */  
/* 171:    */  public String getKeyObtentionIterationsSysPropertyName()
/* 172:    */  {
/* 173:173 */    return this.keyObtentionIterationsSysPropertyName;
/* 174:    */  }
/* 175:    */  
/* 182:    */  public void setKeyObtentionIterationsSysPropertyName(String keyObtentionIterationsSysPropertyName)
/* 183:    */  {
/* 184:184 */    this.keyObtentionIterationsSysPropertyName = keyObtentionIterationsSysPropertyName;
/* 185:185 */    if (keyObtentionIterationsSysPropertyName == null) {
/* 186:186 */      super.setKeyObtentionIterations((Integer)null);
/* 187:    */    } else {
/* 188:188 */      this.keyObtentionIterationsEnvName = null;
/* 189:189 */      super.setKeyObtentionIterations(System.getProperty(keyObtentionIterationsSysPropertyName));
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 200:    */  public String getPasswordEnvName()
/* 201:    */  {
/* 202:202 */    return this.passwordEnvName;
/* 203:    */  }
/* 204:    */  
/* 211:    */  public void setPasswordEnvName(String passwordEnvName)
/* 212:    */  {
/* 213:213 */    this.passwordEnvName = passwordEnvName;
/* 214:214 */    if (passwordEnvName == null) {
/* 215:215 */      super.setPassword(null);
/* 216:    */    } else {
/* 217:217 */      this.passwordSysPropertyName = null;
/* 218:218 */      super.setPassword(System.getenv(passwordEnvName));
/* 219:    */    }
/* 220:    */  }
/* 221:    */  
/* 228:    */  public String getPasswordSysPropertyName()
/* 229:    */  {
/* 230:230 */    return this.passwordSysPropertyName;
/* 231:    */  }
/* 232:    */  
/* 239:    */  public void setPasswordSysPropertyName(String passwordSysPropertyName)
/* 240:    */  {
/* 241:241 */    this.passwordSysPropertyName = passwordSysPropertyName;
/* 242:242 */    if (passwordSysPropertyName == null) {
/* 243:243 */      super.setPassword(null);
/* 244:    */    } else {
/* 245:245 */      this.passwordEnvName = null;
/* 246:246 */      super.setPassword(System.getProperty(passwordSysPropertyName));
/* 247:    */    }
/* 248:    */  }
/* 249:    */  
/* 258:    */  public String getSaltGeneratorClassNameEnvName()
/* 259:    */  {
/* 260:260 */    return this.saltGeneratorClassNameEnvName;
/* 261:    */  }
/* 262:    */  
/* 277:    */  public void setSaltGeneratorClassNameEnvName(String saltGeneratorClassNameEnvName)
/* 278:    */  {
/* 279:279 */    this.saltGeneratorClassNameEnvName = saltGeneratorClassNameEnvName;
/* 280:280 */    if (saltGeneratorClassNameEnvName == null) {
/* 281:281 */      super.setSaltGenerator(null);
/* 282:    */    } else {
/* 283:283 */      this.saltGeneratorClassNameSysPropertyName = null;
/* 284:284 */      String saltGeneratorClassName = System.getenv(saltGeneratorClassNameEnvName);
/* 285:285 */      super.setSaltGeneratorClassName(saltGeneratorClassName);
/* 286:    */    }
/* 287:    */  }
/* 288:    */  
/* 297:    */  public String getSaltGeneratorClassNameSysPropertyName()
/* 298:    */  {
/* 299:299 */    return this.saltGeneratorClassNameSysPropertyName;
/* 300:    */  }
/* 301:    */  
/* 316:    */  public void setSaltGeneratorClassNameSysPropertyName(String saltGeneratorClassNameSysPropertyName)
/* 317:    */  {
/* 318:318 */    this.saltGeneratorClassNameSysPropertyName = saltGeneratorClassNameSysPropertyName;
/* 319:319 */    if (saltGeneratorClassNameSysPropertyName == null) {
/* 320:320 */      super.setSaltGenerator(null);
/* 321:    */    } else {
/* 322:322 */      this.saltGeneratorClassNameEnvName = null;
/* 323:323 */      String saltGeneratorClassName = System.getProperty(saltGeneratorClassNameSysPropertyName);
/* 324:324 */      super.setSaltGeneratorClassName(saltGeneratorClassName);
/* 325:    */    }
/* 326:    */  }
/* 327:    */  
/* 336:    */  public String getProviderNameEnvName()
/* 337:    */  {
/* 338:338 */    return this.providerNameEnvName;
/* 339:    */  }
/* 340:    */  
/* 351:    */  public void setProviderNameEnvName(String providerNameEnvName)
/* 352:    */  {
/* 353:353 */    this.providerNameEnvName = providerNameEnvName;
/* 354:354 */    if (providerNameEnvName == null) {
/* 355:355 */      super.setProviderName(null);
/* 356:    */    } else {
/* 357:357 */      this.providerNameSysPropertyName = null;
/* 358:358 */      super.setProviderName(System.getenv(providerNameEnvName));
/* 359:    */    }
/* 360:    */  }
/* 361:    */  
/* 370:    */  public String getProviderNameSysPropertyName()
/* 371:    */  {
/* 372:372 */    return this.providerNameSysPropertyName;
/* 373:    */  }
/* 374:    */  
/* 383:    */  public void setProviderNameSysPropertyName(String providerNameSysPropertyName)
/* 384:    */  {
/* 385:385 */    this.providerNameSysPropertyName = providerNameSysPropertyName;
/* 386:386 */    if (providerNameSysPropertyName == null) {
/* 387:387 */      super.setProviderName(null);
/* 388:    */    } else {
/* 389:389 */      this.providerNameEnvName = null;
/* 390:390 */      super.setProviderName(System.getProperty(providerNameSysPropertyName));
/* 391:    */    }
/* 392:    */  }
/* 393:    */  
/* 403:    */  public String getProviderClassNameEnvName()
/* 404:    */  {
/* 405:405 */    return this.providerClassNameEnvName;
/* 406:    */  }
/* 407:    */  
/* 422:    */  public void setProviderClassNameEnvName(String providerClassNameEnvName)
/* 423:    */  {
/* 424:424 */    this.providerClassNameEnvName = providerClassNameEnvName;
/* 425:425 */    if (providerClassNameEnvName == null) {
/* 426:426 */      super.setProvider(null);
/* 427:    */    } else {
/* 428:428 */      this.providerClassNameSysPropertyName = null;
/* 429:429 */      String providerClassName = System.getenv(providerClassNameEnvName);
/* 430:430 */      super.setProviderClassName(providerClassName);
/* 431:    */    }
/* 432:    */  }
/* 433:    */  
/* 442:    */  public String getProviderClassNameSysPropertyName()
/* 443:    */  {
/* 444:444 */    return this.providerClassNameSysPropertyName;
/* 445:    */  }
/* 446:    */  
/* 461:    */  public void setProviderClassNameSysPropertyName(String providerClassNameSysPropertyName)
/* 462:    */  {
/* 463:463 */    this.providerClassNameSysPropertyName = providerClassNameSysPropertyName;
/* 464:464 */    if (providerClassNameSysPropertyName == null) {
/* 465:465 */      super.setProvider(null);
/* 466:    */    } else {
/* 467:467 */      this.providerClassNameEnvName = null;
/* 468:468 */      String providerClassName = System.getProperty(providerClassNameSysPropertyName);
/* 469:469 */      super.setProviderClassName(providerClassName);
/* 470:    */    }
/* 471:    */  }
/* 472:    */  
/* 491:    */  public String getPoolSizeEnvName()
/* 492:    */  {
/* 493:493 */    return this.poolSizeEnvName;
/* 494:    */  }
/* 495:    */  
/* 508:    */  public void setPoolSizeEnvName(String poolSizeEnvName)
/* 509:    */  {
/* 510:510 */    this.poolSizeEnvName = poolSizeEnvName;
/* 511:511 */    if (poolSizeEnvName == null) {
/* 512:512 */      super.setPoolSize((String)null);
/* 513:    */    } else {
/* 514:514 */      this.poolSizeSysPropertyName = null;
/* 515:515 */      super.setPoolSize(System.getenv(poolSizeEnvName));
/* 516:    */    }
/* 517:    */  }
/* 518:    */  
/* 529:    */  public String getPoolSizeSysPropertyName()
/* 530:    */  {
/* 531:531 */    return this.poolSizeSysPropertyName;
/* 532:    */  }
/* 533:    */  
/* 546:    */  public void setPoolSizeSysPropertyName(String poolSizeSysPropertyName)
/* 547:    */  {
/* 548:548 */    this.poolSizeSysPropertyName = poolSizeSysPropertyName;
/* 549:549 */    if (poolSizeSysPropertyName == null) {
/* 550:550 */      super.setPoolSize((String)null);
/* 551:    */    } else {
/* 552:552 */      this.poolSizeEnvName = null;
/* 553:553 */      super.setPoolSize(System.getProperty(poolSizeSysPropertyName));
/* 554:    */    }
/* 555:    */  }
/* 556:    */  
/* 565:    */  public void setAlgorithm(String algorithm)
/* 566:    */  {
/* 567:567 */    this.algorithmEnvName = null;
/* 568:568 */    this.algorithmSysPropertyName = null;
/* 569:569 */    super.setAlgorithm(algorithm);
/* 570:    */  }
/* 571:    */  
/* 573:    */  public void setKeyObtentionIterations(Integer keyObtentionIterations)
/* 574:    */  {
/* 575:575 */    this.keyObtentionIterationsEnvName = null;
/* 576:576 */    this.keyObtentionIterationsSysPropertyName = null;
/* 577:577 */    super.setKeyObtentionIterations(keyObtentionIterations);
/* 578:    */  }
/* 579:    */  
/* 580:    */  public void setKeyObtentionIterations(String keyObtentionIterations)
/* 581:    */  {
/* 582:582 */    this.keyObtentionIterationsEnvName = null;
/* 583:583 */    this.keyObtentionIterationsSysPropertyName = null;
/* 584:584 */    super.setKeyObtentionIterations(keyObtentionIterations);
/* 585:    */  }
/* 586:    */  
/* 587:    */  public void setPassword(String password)
/* 588:    */  {
/* 589:589 */    this.passwordEnvName = null;
/* 590:590 */    this.passwordSysPropertyName = null;
/* 591:591 */    super.setPassword(password);
/* 592:    */  }
/* 593:    */  
/* 594:    */  public void setPasswordCharArray(char[] password)
/* 595:    */  {
/* 596:596 */    this.passwordEnvName = null;
/* 597:597 */    this.passwordSysPropertyName = null;
/* 598:598 */    super.setPasswordCharArray(password);
/* 599:    */  }
/* 600:    */  
/* 601:    */  public void setSaltGenerator(SaltGenerator saltGenerator)
/* 602:    */  {
/* 603:603 */    this.saltGeneratorClassNameEnvName = null;
/* 604:604 */    this.saltGeneratorClassNameSysPropertyName = null;
/* 605:605 */    super.setSaltGenerator(saltGenerator);
/* 606:    */  }
/* 607:    */  
/* 608:    */  public void setSaltGeneratorClassName(String saltGeneratorClassName)
/* 609:    */  {
/* 610:610 */    this.saltGeneratorClassNameEnvName = null;
/* 611:611 */    this.saltGeneratorClassNameSysPropertyName = null;
/* 612:612 */    super.setSaltGeneratorClassName(saltGeneratorClassName);
/* 613:    */  }
/* 614:    */  
/* 615:    */  public void setProviderName(String providerName)
/* 616:    */  {
/* 617:617 */    this.providerNameEnvName = null;
/* 618:618 */    this.providerNameSysPropertyName = null;
/* 619:619 */    super.setProviderName(providerName);
/* 620:    */  }
/* 621:    */  
/* 623:    */  public void setProvider(Provider provider)
/* 624:    */  {
/* 625:625 */    this.providerClassNameEnvName = null;
/* 626:626 */    this.providerClassNameSysPropertyName = null;
/* 627:627 */    super.setProvider(provider);
/* 628:    */  }
/* 629:    */  
/* 630:    */  public void setProviderClassName(String providerClassName)
/* 631:    */  {
/* 632:632 */    this.providerClassNameEnvName = null;
/* 633:633 */    this.providerClassNameSysPropertyName = null;
/* 634:634 */    super.setProviderClassName(providerClassName);
/* 635:    */  }
/* 636:    */  
/* 637:    */  public void setPoolSize(Integer poolSize)
/* 638:    */  {
/* 639:639 */    this.poolSizeEnvName = null;
/* 640:640 */    this.poolSizeSysPropertyName = null;
/* 641:641 */    super.setPoolSize(poolSize);
/* 642:    */  }
/* 643:    */  
/* 644:    */  public void setPoolSize(String poolSize)
/* 645:    */  {
/* 646:646 */    this.poolSizeEnvName = null;
/* 647:647 */    this.poolSizeSysPropertyName = null;
/* 648:648 */    super.setPoolSize(poolSize);
/* 649:    */  }
/* 650:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.config.EnvironmentPBEConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */