/*   1:    */package org.jasypt.digest.config;
/*   2:    */
/*   3:    */import java.security.Provider;
/*   4:    */import org.jasypt.commons.CommonUtils;
/*   5:    */import org.jasypt.salt.SaltGenerator;
/*   6:    */
/*  51:    */public class EnvironmentDigesterConfig
/*  52:    */  extends SimpleDigesterConfig
/*  53:    */{
/*  54: 54 */  private String algorithmEnvName = null;
/*  55: 55 */  private String iterationsEnvName = null;
/*  56: 56 */  private String saltSizeBytesEnvName = null;
/*  57: 57 */  private String saltGeneratorClassNameEnvName = null;
/*  58: 58 */  private String providerNameEnvName = null;
/*  59: 59 */  private String providerClassNameEnvName = null;
/*  60: 60 */  private String invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
/*  61: 61 */  private String invertPositionOfPlainSaltInEncryptionResultsEnvName = null;
/*  62: 62 */  private String useLenientSaltSizeCheckEnvName = null;
/*  63: 63 */  private String poolSizeEnvName = null;
/*  64:    */  
/*  65: 65 */  private String algorithmSysPropertyName = null;
/*  66: 66 */  private String iterationsSysPropertyName = null;
/*  67: 67 */  private String saltSizeBytesSysPropertyName = null;
/*  68: 68 */  private String saltGeneratorClassNameSysPropertyName = null;
/*  69: 69 */  private String providerNameSysPropertyName = null;
/*  70: 70 */  private String providerClassNameSysPropertyName = null;
/*  71: 71 */  private String invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
/*  72: 72 */  private String invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
/*  73: 73 */  private String useLenientSaltSizeCheckSysPropertyName = null;
/*  74: 74 */  private String poolSizeSysPropertyName = null;
/*  75:    */  
/*  93:    */  public String getAlgorithmEnvName()
/*  94:    */  {
/*  95: 95 */    return this.algorithmEnvName;
/*  96:    */  }
/*  97:    */  
/* 104:    */  public void setAlgorithmEnvName(String algorithmEnvName)
/* 105:    */  {
/* 106:106 */    this.algorithmEnvName = algorithmEnvName;
/* 107:107 */    if (algorithmEnvName == null) {
/* 108:108 */      super.setAlgorithm(null);
/* 109:    */    } else {
/* 110:110 */      this.algorithmSysPropertyName = null;
/* 111:111 */      super.setAlgorithm(System.getenv(algorithmEnvName));
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 121:    */  public String getAlgorithmSysPropertyName()
/* 122:    */  {
/* 123:123 */    return this.algorithmSysPropertyName;
/* 124:    */  }
/* 125:    */  
/* 132:    */  public void setAlgorithmSysPropertyName(String algorithmSysPropertyName)
/* 133:    */  {
/* 134:134 */    this.algorithmSysPropertyName = algorithmSysPropertyName;
/* 135:135 */    if (algorithmSysPropertyName == null) {
/* 136:136 */      super.setAlgorithm(null);
/* 137:    */    } else {
/* 138:138 */      this.algorithmEnvName = null;
/* 139:139 */      super.setAlgorithm(System.getProperty(algorithmSysPropertyName));
/* 140:    */    }
/* 141:    */  }
/* 142:    */  
/* 149:    */  public String getIterationsEnvName()
/* 150:    */  {
/* 151:151 */    return this.iterationsEnvName;
/* 152:    */  }
/* 153:    */  
/* 160:    */  public void setIterationsEnvName(String iterationsEnvName)
/* 161:    */  {
/* 162:162 */    this.iterationsEnvName = iterationsEnvName;
/* 163:163 */    if (iterationsEnvName == null) {
/* 164:164 */      super.setIterations((String)null);
/* 165:    */    } else {
/* 166:166 */      this.iterationsSysPropertyName = null;
/* 167:167 */      super.setIterations(System.getenv(iterationsEnvName));
/* 168:    */    }
/* 169:    */  }
/* 170:    */  
/* 177:    */  public String getIterationsSysPropertyName()
/* 178:    */  {
/* 179:179 */    return this.iterationsSysPropertyName;
/* 180:    */  }
/* 181:    */  
/* 188:    */  public void setIterationsSysPropertyName(String iterationsSysPropertyName)
/* 189:    */  {
/* 190:190 */    this.iterationsSysPropertyName = iterationsSysPropertyName;
/* 191:191 */    if (iterationsSysPropertyName == null) {
/* 192:192 */      super.setIterations((String)null);
/* 193:    */    } else {
/* 194:194 */      this.iterationsEnvName = null;
/* 195:195 */      super.setIterations(System.getProperty(iterationsSysPropertyName));
/* 196:    */    }
/* 197:    */  }
/* 198:    */  
/* 205:    */  public String getSaltSizeBytesEnvName()
/* 206:    */  {
/* 207:207 */    return this.saltSizeBytesEnvName;
/* 208:    */  }
/* 209:    */  
/* 216:    */  public void setSaltSizeBytesEnvName(String saltSizeBytesEnvName)
/* 217:    */  {
/* 218:218 */    this.saltSizeBytesEnvName = saltSizeBytesEnvName;
/* 219:219 */    if (saltSizeBytesEnvName == null) {
/* 220:220 */      super.setSaltSizeBytes((String)null);
/* 221:    */    } else {
/* 222:222 */      this.saltSizeBytesSysPropertyName = null;
/* 223:223 */      super.setSaltSizeBytes(System.getenv(saltSizeBytesEnvName));
/* 224:    */    }
/* 225:    */  }
/* 226:    */  
/* 233:    */  public String getSaltSizeBytesSysPropertyName()
/* 234:    */  {
/* 235:235 */    return this.saltSizeBytesSysPropertyName;
/* 236:    */  }
/* 237:    */  
/* 244:    */  public void setSaltSizeBytesSysPropertyName(String saltSizeBytesSysPropertyName)
/* 245:    */  {
/* 246:246 */    this.saltSizeBytesSysPropertyName = saltSizeBytesSysPropertyName;
/* 247:247 */    if (saltSizeBytesSysPropertyName == null) {
/* 248:248 */      super.setSaltSizeBytes((Integer)null);
/* 249:    */    } else {
/* 250:250 */      this.saltSizeBytesEnvName = null;
/* 251:251 */      super.setSaltSizeBytes(System.getProperty(saltSizeBytesSysPropertyName));
/* 252:    */    }
/* 253:    */  }
/* 254:    */  
/* 264:    */  public String getSaltGeneratorClassNameEnvName()
/* 265:    */  {
/* 266:266 */    return this.saltGeneratorClassNameEnvName;
/* 267:    */  }
/* 268:    */  
/* 283:    */  public void setSaltGeneratorClassNameEnvName(String saltGeneratorClassNameEnvName)
/* 284:    */  {
/* 285:285 */    this.saltGeneratorClassNameEnvName = saltGeneratorClassNameEnvName;
/* 286:286 */    if (saltGeneratorClassNameEnvName == null) {
/* 287:287 */      super.setSaltGenerator(null);
/* 288:    */    } else {
/* 289:289 */      this.saltGeneratorClassNameSysPropertyName = null;
/* 290:290 */      String saltGeneratorClassName = System.getenv(saltGeneratorClassNameEnvName);
/* 291:291 */      super.setSaltGeneratorClassName(saltGeneratorClassName);
/* 292:    */    }
/* 293:    */  }
/* 294:    */  
/* 303:    */  public String getSaltGeneratorClassNameSysPropertyName()
/* 304:    */  {
/* 305:305 */    return this.saltGeneratorClassNameSysPropertyName;
/* 306:    */  }
/* 307:    */  
/* 322:    */  public void setSaltGeneratorClassNameSysPropertyName(String saltGeneratorClassNameSysPropertyName)
/* 323:    */  {
/* 324:324 */    this.saltGeneratorClassNameSysPropertyName = saltGeneratorClassNameSysPropertyName;
/* 325:325 */    if (saltGeneratorClassNameSysPropertyName == null) {
/* 326:326 */      super.setSaltGenerator(null);
/* 327:    */    } else {
/* 328:328 */      this.saltGeneratorClassNameEnvName = null;
/* 329:329 */      String saltGeneratorClassName = System.getProperty(saltGeneratorClassNameSysPropertyName);
/* 330:    */      
/* 331:331 */      super.setSaltGeneratorClassName(saltGeneratorClassName);
/* 332:    */    }
/* 333:    */  }
/* 334:    */  
/* 343:    */  public String getProviderNameEnvName()
/* 344:    */  {
/* 345:345 */    return this.providerNameEnvName;
/* 346:    */  }
/* 347:    */  
/* 358:    */  public void setProviderNameEnvName(String providerNameEnvName)
/* 359:    */  {
/* 360:360 */    this.providerNameEnvName = providerNameEnvName;
/* 361:361 */    if (providerNameEnvName == null) {
/* 362:362 */      super.setProviderName(null);
/* 363:    */    } else {
/* 364:364 */      this.providerNameSysPropertyName = null;
/* 365:365 */      super.setProviderName(System.getenv(providerNameEnvName));
/* 366:    */    }
/* 367:    */  }
/* 368:    */  
/* 377:    */  public String getProviderNameSysPropertyName()
/* 378:    */  {
/* 379:379 */    return this.providerNameSysPropertyName;
/* 380:    */  }
/* 381:    */  
/* 390:    */  public void setProviderNameSysPropertyName(String providerNameSysPropertyName)
/* 391:    */  {
/* 392:392 */    this.providerNameSysPropertyName = providerNameSysPropertyName;
/* 393:393 */    if (providerNameSysPropertyName == null) {
/* 394:394 */      super.setProviderName(null);
/* 395:    */    } else {
/* 396:396 */      this.providerNameEnvName = null;
/* 397:397 */      super.setProviderName(System.getProperty(providerNameSysPropertyName));
/* 398:    */    }
/* 399:    */  }
/* 400:    */  
/* 409:    */  public String getProviderClassNameEnvName()
/* 410:    */  {
/* 411:411 */    return this.providerClassNameEnvName;
/* 412:    */  }
/* 413:    */  
/* 428:    */  public void setProviderClassNameEnvName(String providerClassNameEnvName)
/* 429:    */  {
/* 430:430 */    this.providerClassNameEnvName = providerClassNameEnvName;
/* 431:431 */    if (providerClassNameEnvName == null) {
/* 432:432 */      super.setProvider(null);
/* 433:    */    } else {
/* 434:434 */      this.providerClassNameSysPropertyName = null;
/* 435:435 */      String providerClassName = System.getenv(providerClassNameEnvName);
/* 436:436 */      super.setProviderClassName(providerClassName);
/* 437:    */    }
/* 438:    */  }
/* 439:    */  
/* 448:    */  public String getProviderClassNameSysPropertyName()
/* 449:    */  {
/* 450:450 */    return this.providerClassNameSysPropertyName;
/* 451:    */  }
/* 452:    */  
/* 467:    */  public void setProviderClassNameSysPropertyName(String providerClassNameSysPropertyName)
/* 468:    */  {
/* 469:469 */    this.providerClassNameSysPropertyName = providerClassNameSysPropertyName;
/* 470:470 */    if (providerClassNameSysPropertyName == null) {
/* 471:471 */      super.setProvider(null);
/* 472:    */    } else {
/* 473:473 */      this.providerClassNameEnvName = null;
/* 474:474 */      String providerClassName = System.getProperty(providerClassNameSysPropertyName);
/* 475:475 */      super.setProviderClassName(providerClassName);
/* 476:    */    }
/* 477:    */  }
/* 478:    */  
/* 491:    */  public String getInvertPositionOfSaltInMessageBeforeDigestingEnvName()
/* 492:    */  {
/* 493:493 */    return this.invertPositionOfSaltInMessageBeforeDigestingEnvName;
/* 494:    */  }
/* 495:    */  
/* 508:    */  public void setInvertPositionOfSaltInMessageBeforeDigestingEnvName(String invertPositionOfSaltInMessageBeforeDigestingEnvName)
/* 509:    */  {
/* 510:510 */    this.invertPositionOfSaltInMessageBeforeDigestingEnvName = invertPositionOfSaltInMessageBeforeDigestingEnvName;
/* 511:511 */    if (invertPositionOfSaltInMessageBeforeDigestingEnvName == null) {
/* 512:512 */      super.setInvertPositionOfSaltInMessageBeforeDigesting(null);
/* 513:    */    } else {
/* 514:514 */      this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
/* 515:515 */      super.setInvertPositionOfSaltInMessageBeforeDigesting(CommonUtils.getStandardBooleanValue(System.getenv(invertPositionOfSaltInMessageBeforeDigestingEnvName)));
/* 516:    */    }
/* 517:    */  }
/* 518:    */  
/* 535:    */  public String getInvertPositionOfSaltInMessageBeforeDigestingSysPropertyName()
/* 536:    */  {
/* 537:537 */    return this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName;
/* 538:    */  }
/* 539:    */  
/* 552:    */  public void setInvertPositionOfSaltInMessageBeforeDigestingSysPropertyName(String invertPositionOfSaltInMessageBeforeDigestingSysPropertyName)
/* 553:    */  {
/* 554:554 */    this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = invertPositionOfSaltInMessageBeforeDigestingSysPropertyName;
/* 555:555 */    if (invertPositionOfSaltInMessageBeforeDigestingSysPropertyName == null) {
/* 556:556 */      super.setInvertPositionOfSaltInMessageBeforeDigesting(null);
/* 557:    */    } else {
/* 558:558 */      this.invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
/* 559:559 */      super.setInvertPositionOfSaltInMessageBeforeDigesting(CommonUtils.getStandardBooleanValue(System.getProperty(invertPositionOfSaltInMessageBeforeDigestingSysPropertyName)));
/* 560:    */    }
/* 561:    */  }
/* 562:    */  
/* 579:    */  public String getInvertPositionOfPlainSaltInEncryptionResultsEnvName()
/* 580:    */  {
/* 581:581 */    return this.invertPositionOfPlainSaltInEncryptionResultsEnvName;
/* 582:    */  }
/* 583:    */  
/* 596:    */  public void setInvertPositionOfPlainSaltInEncryptionResultsEnvName(String invertPositionOfPlainSaltInEncryptionResultsEnvName)
/* 597:    */  {
/* 598:598 */    this.invertPositionOfPlainSaltInEncryptionResultsEnvName = invertPositionOfPlainSaltInEncryptionResultsEnvName;
/* 599:599 */    if (invertPositionOfPlainSaltInEncryptionResultsEnvName == null) {
/* 600:600 */      super.setInvertPositionOfPlainSaltInEncryptionResults(null);
/* 601:    */    } else {
/* 602:602 */      this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
/* 603:603 */      super.setInvertPositionOfPlainSaltInEncryptionResults(CommonUtils.getStandardBooleanValue(System.getenv(invertPositionOfPlainSaltInEncryptionResultsEnvName)));
/* 604:    */    }
/* 605:    */  }
/* 606:    */  
/* 619:    */  public String getInvertPositionOfPlainSaltInEncryptionResultsSysPropertyName()
/* 620:    */  {
/* 621:621 */    return this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName;
/* 622:    */  }
/* 623:    */  
/* 636:    */  public void setInvertPositionOfPlainSaltInEncryptionResultsSysPropertyName(String invertPositionOfPlainSaltInEncryptionResultsSysPropertyName)
/* 637:    */  {
/* 638:638 */    this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = invertPositionOfPlainSaltInEncryptionResultsSysPropertyName;
/* 639:639 */    if (invertPositionOfPlainSaltInEncryptionResultsSysPropertyName == null) {
/* 640:640 */      super.setInvertPositionOfPlainSaltInEncryptionResults(null);
/* 641:    */    } else {
/* 642:642 */      this.invertPositionOfPlainSaltInEncryptionResultsEnvName = null;
/* 643:643 */      super.setInvertPositionOfPlainSaltInEncryptionResults(CommonUtils.getStandardBooleanValue(System.getProperty(invertPositionOfPlainSaltInEncryptionResultsSysPropertyName)));
/* 644:    */    }
/* 645:    */  }
/* 646:    */  
/* 664:    */  public String getUseLenientSaltSizeCheckEnvName()
/* 665:    */  {
/* 666:666 */    return this.useLenientSaltSizeCheckEnvName;
/* 667:    */  }
/* 668:    */  
/* 681:    */  public void setUseLenientSaltSizeCheckEnvName(String useLenientSaltSizeCheckEnvName)
/* 682:    */  {
/* 683:683 */    this.useLenientSaltSizeCheckEnvName = useLenientSaltSizeCheckEnvName;
/* 684:684 */    if (useLenientSaltSizeCheckEnvName == null) {
/* 685:685 */      super.setUseLenientSaltSizeCheck(null);
/* 686:    */    } else {
/* 687:687 */      this.useLenientSaltSizeCheckSysPropertyName = null;
/* 688:688 */      super.setUseLenientSaltSizeCheck(CommonUtils.getStandardBooleanValue(System.getenv(useLenientSaltSizeCheckEnvName)));
/* 689:    */    }
/* 690:    */  }
/* 691:    */  
/* 704:    */  public String getUseLenientSaltSizeCheckSysPropertyName()
/* 705:    */  {
/* 706:706 */    return this.useLenientSaltSizeCheckSysPropertyName;
/* 707:    */  }
/* 708:    */  
/* 721:    */  public void setUseLenientSaltSizeCheckSysPropertyName(String useLenientSaltSizeCheckSysPropertyName)
/* 722:    */  {
/* 723:723 */    this.useLenientSaltSizeCheckSysPropertyName = useLenientSaltSizeCheckSysPropertyName;
/* 724:724 */    if (useLenientSaltSizeCheckSysPropertyName == null) {
/* 725:725 */      super.setUseLenientSaltSizeCheck(null);
/* 726:    */    } else {
/* 727:727 */      this.useLenientSaltSizeCheckEnvName = null;
/* 728:728 */      super.setUseLenientSaltSizeCheck(CommonUtils.getStandardBooleanValue(System.getProperty(useLenientSaltSizeCheckSysPropertyName)));
/* 729:    */    }
/* 730:    */  }
/* 731:    */  
/* 750:    */  public String getPoolSizeEnvName()
/* 751:    */  {
/* 752:752 */    return this.poolSizeEnvName;
/* 753:    */  }
/* 754:    */  
/* 767:    */  public void setPoolSizeEnvName(String poolSizeEnvName)
/* 768:    */  {
/* 769:769 */    this.poolSizeEnvName = poolSizeEnvName;
/* 770:770 */    if (poolSizeEnvName == null) {
/* 771:771 */      super.setPoolSize((String)null);
/* 772:    */    } else {
/* 773:773 */      this.poolSizeSysPropertyName = null;
/* 774:774 */      super.setPoolSize(System.getenv(poolSizeEnvName));
/* 775:    */    }
/* 776:    */  }
/* 777:    */  
/* 788:    */  public String getPoolSizeSysPropertyName()
/* 789:    */  {
/* 790:790 */    return this.poolSizeSysPropertyName;
/* 791:    */  }
/* 792:    */  
/* 805:    */  public void setPoolSizeSysPropertyName(String poolSizeSysPropertyName)
/* 806:    */  {
/* 807:807 */    this.poolSizeSysPropertyName = poolSizeSysPropertyName;
/* 808:808 */    if (poolSizeSysPropertyName == null) {
/* 809:809 */      super.setPoolSize((String)null);
/* 810:    */    } else {
/* 811:811 */      this.poolSizeEnvName = null;
/* 812:812 */      super.setPoolSize(System.getProperty(poolSizeSysPropertyName));
/* 813:    */    }
/* 814:    */  }
/* 815:    */  
/* 821:    */  public void setAlgorithm(String algorithm)
/* 822:    */  {
/* 823:823 */    this.algorithmEnvName = null;
/* 824:824 */    this.algorithmSysPropertyName = null;
/* 825:825 */    super.setAlgorithm(algorithm);
/* 826:    */  }
/* 827:    */  
/* 829:    */  public void setIterations(Integer iterations)
/* 830:    */  {
/* 831:831 */    this.iterationsEnvName = null;
/* 832:832 */    this.iterationsSysPropertyName = null;
/* 833:833 */    super.setIterations(iterations);
/* 834:    */  }
/* 835:    */  
/* 836:    */  public void setIterations(String iterations)
/* 837:    */  {
/* 838:838 */    this.iterationsEnvName = null;
/* 839:839 */    this.iterationsSysPropertyName = null;
/* 840:840 */    super.setIterations(iterations);
/* 841:    */  }
/* 842:    */  
/* 844:    */  public void setSaltSizeBytes(Integer saltSizeBytes)
/* 845:    */  {
/* 846:846 */    this.saltSizeBytesEnvName = null;
/* 847:847 */    this.saltSizeBytesSysPropertyName = null;
/* 848:848 */    super.setSaltSizeBytes(saltSizeBytes);
/* 849:    */  }
/* 850:    */  
/* 851:    */  public void setSaltSizeBytes(String saltSizeBytes)
/* 852:    */  {
/* 853:853 */    this.saltSizeBytesEnvName = null;
/* 854:854 */    this.saltSizeBytesSysPropertyName = null;
/* 855:855 */    super.setSaltSizeBytes(saltSizeBytes);
/* 856:    */  }
/* 857:    */  
/* 859:    */  public void setSaltGenerator(SaltGenerator saltGenerator)
/* 860:    */  {
/* 861:861 */    this.saltGeneratorClassNameEnvName = null;
/* 862:862 */    this.saltGeneratorClassNameSysPropertyName = null;
/* 863:863 */    super.setSaltGenerator(saltGenerator);
/* 864:    */  }
/* 865:    */  
/* 866:    */  public void setSaltGeneratorClassName(String saltGeneratorClassName)
/* 867:    */  {
/* 868:868 */    this.saltGeneratorClassNameEnvName = null;
/* 869:869 */    this.saltGeneratorClassNameSysPropertyName = null;
/* 870:870 */    super.setSaltGeneratorClassName(saltGeneratorClassName);
/* 871:    */  }
/* 872:    */  
/* 873:    */  public void setProviderName(String providerName)
/* 874:    */  {
/* 875:875 */    this.providerNameEnvName = null;
/* 876:876 */    this.providerNameSysPropertyName = null;
/* 877:877 */    super.setProviderName(providerName);
/* 878:    */  }
/* 879:    */  
/* 880:    */  public void setProvider(Provider provider)
/* 881:    */  {
/* 882:882 */    this.providerClassNameEnvName = null;
/* 883:883 */    this.providerClassNameSysPropertyName = null;
/* 884:884 */    super.setProvider(provider);
/* 885:    */  }
/* 886:    */  
/* 887:    */  public void setProviderClassName(String providerClassName)
/* 888:    */  {
/* 889:889 */    this.providerClassNameEnvName = null;
/* 890:890 */    this.providerClassNameSysPropertyName = null;
/* 891:891 */    super.setProviderClassName(providerClassName);
/* 892:    */  }
/* 893:    */  
/* 895:    */  public void setInvertPositionOfPlainSaltInEncryptionResults(Boolean invertPositionOfPlainSaltInEncryptionResults)
/* 896:    */  {
/* 897:897 */    this.invertPositionOfPlainSaltInEncryptionResultsEnvName = null;
/* 898:898 */    this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
/* 899:899 */    super.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
/* 900:    */  }
/* 901:    */  
/* 903:    */  public void setInvertPositionOfSaltInMessageBeforeDigesting(Boolean invertPositionOfSaltInMessageBeforeDigesting)
/* 904:    */  {
/* 905:905 */    this.invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
/* 906:906 */    this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
/* 907:907 */    super.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
/* 908:    */  }
/* 909:    */  
/* 911:    */  public void setUseLenientSaltSizeCheck(Boolean useLenientSaltSizeCheck)
/* 912:    */  {
/* 913:913 */    this.useLenientSaltSizeCheckEnvName = null;
/* 914:914 */    this.useLenientSaltSizeCheckSysPropertyName = null;
/* 915:915 */    super.setUseLenientSaltSizeCheck(useLenientSaltSizeCheck);
/* 916:    */  }
/* 917:    */  
/* 918:    */  public void setPoolSize(Integer poolSize)
/* 919:    */  {
/* 920:920 */    this.poolSizeEnvName = null;
/* 921:921 */    this.poolSizeSysPropertyName = null;
/* 922:922 */    super.setPoolSize(poolSize);
/* 923:    */  }
/* 924:    */  
/* 925:    */  public void setPoolSize(String poolSize)
/* 926:    */  {
/* 927:927 */    this.poolSizeEnvName = null;
/* 928:928 */    this.poolSizeSysPropertyName = null;
/* 929:929 */    super.setPoolSize(poolSize);
/* 930:    */  }
/* 931:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.config.EnvironmentDigesterConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */