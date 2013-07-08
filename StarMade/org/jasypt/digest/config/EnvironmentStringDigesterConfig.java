/*   1:    */package org.jasypt.digest.config;
/*   2:    */
/*   3:    */import org.jasypt.commons.CommonUtils;
/*   4:    */
/*  50:    */public class EnvironmentStringDigesterConfig
/*  51:    */  extends EnvironmentDigesterConfig
/*  52:    */  implements StringDigesterConfig
/*  53:    */{
/*  54: 54 */  private Boolean unicodeNormalizationIgnored = null;
/*  55: 55 */  private String stringOutputType = null;
/*  56: 56 */  private String prefix = null;
/*  57: 57 */  private String suffix = null;
/*  58:    */  
/*  59: 59 */  private String unicodeNormalizationIgnoredEnvName = null;
/*  60: 60 */  private String stringOutputTypeEnvName = null;
/*  61: 61 */  private String prefixEnvName = null;
/*  62: 62 */  private String suffixEnvName = null;
/*  63:    */  
/*  64: 64 */  private String unicodeNormalizationIgnoredSysPropertyName = null;
/*  65: 65 */  private String stringOutputTypeSysPropertyName = null;
/*  66: 66 */  private String prefixSysPropertyName = null;
/*  67: 67 */  private String suffixSysPropertyName = null;
/*  68:    */  
/*  87:    */  public String getUnicodeNormalizationIgnoredEnvName()
/*  88:    */  {
/*  89: 89 */    return this.unicodeNormalizationIgnoredEnvName;
/*  90:    */  }
/*  91:    */  
/*  99:    */  public void setUnicodeNormalizationIgnoredEnvName(String unicodeNormalizationIgnoredEnvName)
/* 100:    */  {
/* 101:101 */    this.unicodeNormalizationIgnoredEnvName = unicodeNormalizationIgnoredEnvName;
/* 102:    */    
/* 103:103 */    if (unicodeNormalizationIgnoredEnvName == null) {
/* 104:104 */      this.unicodeNormalizationIgnored = null;
/* 105:    */    } else {
/* 106:106 */      this.unicodeNormalizationIgnoredSysPropertyName = null;
/* 107:107 */      String unicodeNormalizationIgnoredValue = System.getenv(unicodeNormalizationIgnoredEnvName);
/* 108:    */      
/* 109:109 */      if (unicodeNormalizationIgnoredValue != null) {
/* 110:110 */        this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnoredValue);
/* 111:    */      }
/* 112:    */      else
/* 113:    */      {
/* 114:114 */        this.unicodeNormalizationIgnored = null;
/* 115:    */      }
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 125:    */  public String getUnicodeNormalizationIgnoredSysPropertyName()
/* 126:    */  {
/* 127:127 */    return this.unicodeNormalizationIgnoredSysPropertyName;
/* 128:    */  }
/* 129:    */  
/* 136:    */  public void setUnicodeNormalizationIgnoredSysPropertyName(String unicodeNormalizationIgnoredSysPropertyName)
/* 137:    */  {
/* 138:138 */    this.unicodeNormalizationIgnoredSysPropertyName = unicodeNormalizationIgnoredSysPropertyName;
/* 139:    */    
/* 140:140 */    if (unicodeNormalizationIgnoredSysPropertyName == null) {
/* 141:141 */      this.unicodeNormalizationIgnored = null;
/* 142:    */    } else {
/* 143:143 */      this.unicodeNormalizationIgnoredEnvName = null;
/* 144:144 */      String unicodeNormalizationIgnoredValue = System.getProperty(unicodeNormalizationIgnoredSysPropertyName);
/* 145:    */      
/* 146:146 */      if (unicodeNormalizationIgnoredValue != null) {
/* 147:147 */        this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnoredValue);
/* 148:    */      }
/* 149:    */      else
/* 150:    */      {
/* 151:151 */        this.unicodeNormalizationIgnored = null;
/* 152:    */      }
/* 153:    */    }
/* 154:    */  }
/* 155:    */  
/* 162:    */  public String getStringOutputTypeEnvName()
/* 163:    */  {
/* 164:164 */    return this.stringOutputTypeEnvName;
/* 165:    */  }
/* 166:    */  
/* 173:    */  public void setStringOutputTypeEnvName(String stringOutputTypeEnvName)
/* 174:    */  {
/* 175:175 */    this.stringOutputTypeEnvName = stringOutputTypeEnvName;
/* 176:176 */    if (stringOutputTypeEnvName == null) {
/* 177:177 */      this.stringOutputType = null;
/* 178:    */    } else {
/* 179:179 */      this.stringOutputTypeSysPropertyName = null;
/* 180:180 */      this.stringOutputType = CommonUtils.getStandardStringOutputType(System.getenv(stringOutputTypeEnvName));
/* 181:    */    }
/* 182:    */  }
/* 183:    */  
/* 192:    */  public String getStringOutputTypeSysPropertyName()
/* 193:    */  {
/* 194:194 */    return this.stringOutputTypeSysPropertyName;
/* 195:    */  }
/* 196:    */  
/* 203:    */  public void setStringOutputTypeSysPropertyName(String stringOutputTypeSysPropertyName)
/* 204:    */  {
/* 205:205 */    this.stringOutputTypeSysPropertyName = stringOutputTypeSysPropertyName;
/* 206:206 */    if (stringOutputTypeSysPropertyName == null) {
/* 207:207 */      this.stringOutputType = null;
/* 208:    */    } else {
/* 209:209 */      this.stringOutputTypeEnvName = null;
/* 210:210 */      this.stringOutputType = CommonUtils.getStandardStringOutputType(System.getProperty(stringOutputTypeSysPropertyName));
/* 211:    */    }
/* 212:    */  }
/* 213:    */  
/* 244:    */  public void setUnicodeNormalizationIgnored(Boolean unicodeNormalizationIgnored)
/* 245:    */  {
/* 246:246 */    this.unicodeNormalizationIgnoredEnvName = null;
/* 247:247 */    this.unicodeNormalizationIgnoredSysPropertyName = null;
/* 248:248 */    this.unicodeNormalizationIgnored = unicodeNormalizationIgnored;
/* 249:    */  }
/* 250:    */  
/* 281:    */  public void setUnicodeNormalizationIgnored(String unicodeNormalizationIgnored)
/* 282:    */  {
/* 283:283 */    this.unicodeNormalizationIgnoredEnvName = null;
/* 284:284 */    this.unicodeNormalizationIgnoredSysPropertyName = null;
/* 285:285 */    if (unicodeNormalizationIgnored != null) {
/* 286:286 */      this.unicodeNormalizationIgnored = CommonUtils.getStandardBooleanValue(unicodeNormalizationIgnored);
/* 287:    */    }
/* 288:    */    else {
/* 289:289 */      this.unicodeNormalizationIgnored = null;
/* 290:    */    }
/* 291:    */  }
/* 292:    */  
/* 308:    */  public void setStringOutputType(String stringOutputType)
/* 309:    */  {
/* 310:310 */    this.stringOutputTypeEnvName = null;
/* 311:311 */    this.stringOutputTypeSysPropertyName = null;
/* 312:312 */    this.stringOutputType = CommonUtils.getStandardStringOutputType(stringOutputType);
/* 313:    */  }
/* 314:    */  
/* 336:    */  public void setPrefix(String prefix)
/* 337:    */  {
/* 338:338 */    this.prefixEnvName = null;
/* 339:339 */    this.prefixSysPropertyName = null;
/* 340:340 */    this.prefix = prefix;
/* 341:    */  }
/* 342:    */  
/* 361:    */  public void setSuffix(String suffix)
/* 362:    */  {
/* 363:363 */    this.suffixEnvName = null;
/* 364:364 */    this.suffixSysPropertyName = null;
/* 365:365 */    this.suffix = suffix;
/* 366:    */  }
/* 367:    */  
/* 369:    */  public Boolean isUnicodeNormalizationIgnored()
/* 370:    */  {
/* 371:371 */    return this.unicodeNormalizationIgnored;
/* 372:    */  }
/* 373:    */  
/* 374:    */  public String getStringOutputType()
/* 375:    */  {
/* 376:376 */    return this.stringOutputType;
/* 377:    */  }
/* 378:    */  
/* 380:    */  public String getPrefix()
/* 381:    */  {
/* 382:382 */    return this.prefix;
/* 383:    */  }
/* 384:    */  
/* 385:    */  public String getSuffix()
/* 386:    */  {
/* 387:387 */    return this.suffix;
/* 388:    */  }
/* 389:    */  
/* 403:    */  public String getPrefixEnvName()
/* 404:    */  {
/* 405:405 */    return this.prefixEnvName;
/* 406:    */  }
/* 407:    */  
/* 418:    */  public void setPrefixEnvName(String prefixEnvName)
/* 419:    */  {
/* 420:420 */    this.prefixEnvName = prefixEnvName;
/* 421:421 */    if (prefixEnvName == null) {
/* 422:422 */      this.prefix = null;
/* 423:    */    } else {
/* 424:424 */      this.prefixSysPropertyName = null;
/* 425:425 */      this.prefix = System.getenv(prefixEnvName);
/* 426:    */    }
/* 427:    */  }
/* 428:    */  
/* 438:    */  public String getPrefixSysPropertyName()
/* 439:    */  {
/* 440:440 */    return this.prefixSysPropertyName;
/* 441:    */  }
/* 442:    */  
/* 452:    */  public void setPrefixSysPropertyName(String prefixSysPropertyName)
/* 453:    */  {
/* 454:454 */    this.prefixSysPropertyName = prefixSysPropertyName;
/* 455:455 */    if (prefixSysPropertyName == null) {
/* 456:456 */      this.prefix = null;
/* 457:    */    } else {
/* 458:458 */      this.prefixEnvName = null;
/* 459:459 */      this.prefix = System.getProperty(prefixSysPropertyName);
/* 460:    */    }
/* 461:    */  }
/* 462:    */  
/* 472:    */  public String getSuffixEnvName()
/* 473:    */  {
/* 474:474 */    return this.suffixEnvName;
/* 475:    */  }
/* 476:    */  
/* 487:    */  public void setSuffixEnvName(String suffixEnvName)
/* 488:    */  {
/* 489:489 */    this.suffixEnvName = suffixEnvName;
/* 490:490 */    if (suffixEnvName == null) {
/* 491:491 */      this.suffix = null;
/* 492:    */    } else {
/* 493:493 */      this.suffixSysPropertyName = null;
/* 494:494 */      this.suffix = System.getenv(suffixEnvName);
/* 495:    */    }
/* 496:    */  }
/* 497:    */  
/* 507:    */  public String getSuffixSysPropertyName()
/* 508:    */  {
/* 509:509 */    return this.suffixSysPropertyName;
/* 510:    */  }
/* 511:    */  
/* 521:    */  public void setSuffixSysPropertyName(String suffixSysPropertyName)
/* 522:    */  {
/* 523:523 */    this.suffixSysPropertyName = suffixSysPropertyName;
/* 524:524 */    if (suffixSysPropertyName == null) {
/* 525:525 */      this.suffix = null;
/* 526:    */    } else {
/* 527:527 */      this.suffixEnvName = null;
/* 528:528 */      this.suffix = System.getProperty(suffixSysPropertyName);
/* 529:    */    }
/* 530:    */  }
/* 531:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.digest.config.EnvironmentStringDigesterConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */