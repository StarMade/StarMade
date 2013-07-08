/*   1:    */package org.apache.commons.lang3.text;
/*   2:    */
/*   3:    */import org.apache.commons.lang3.StringUtils;
/*   4:    */import org.apache.commons.lang3.SystemUtils;
/*   5:    */
/*  63:    */public class WordUtils
/*  64:    */{
/*  65:    */  public static String wrap(String str, int wrapLength)
/*  66:    */  {
/*  67: 67 */    return wrap(str, wrapLength, null, false);
/*  68:    */  }
/*  69:    */  
/*  87:    */  public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords)
/*  88:    */  {
/*  89: 89 */    if (str == null) {
/*  90: 90 */      return null;
/*  91:    */    }
/*  92: 92 */    if (newLineStr == null) {
/*  93: 93 */      newLineStr = SystemUtils.LINE_SEPARATOR;
/*  94:    */    }
/*  95: 95 */    if (wrapLength < 1) {
/*  96: 96 */      wrapLength = 1;
/*  97:    */    }
/*  98: 98 */    int inputLineLength = str.length();
/*  99: 99 */    int offset = 0;
/* 100:100 */    StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);
/* 101:    */    
/* 102:102 */    while (inputLineLength - offset > wrapLength) {
/* 103:103 */      if (str.charAt(offset) == ' ') {
/* 104:104 */        offset++;
/* 105:    */      }
/* 106:    */      else {
/* 107:107 */        int spaceToWrapAt = str.lastIndexOf(' ', wrapLength + offset);
/* 108:    */        
/* 109:109 */        if (spaceToWrapAt >= offset)
/* 110:    */        {
/* 111:111 */          wrappedLine.append(str.substring(offset, spaceToWrapAt));
/* 112:112 */          wrappedLine.append(newLineStr);
/* 113:113 */          offset = spaceToWrapAt + 1;
/* 116:    */        }
/* 117:117 */        else if (wrapLongWords)
/* 118:    */        {
/* 119:119 */          wrappedLine.append(str.substring(offset, wrapLength + offset));
/* 120:120 */          wrappedLine.append(newLineStr);
/* 121:121 */          offset += wrapLength;
/* 122:    */        }
/* 123:    */        else {
/* 124:124 */          spaceToWrapAt = str.indexOf(' ', wrapLength + offset);
/* 125:125 */          if (spaceToWrapAt >= 0) {
/* 126:126 */            wrappedLine.append(str.substring(offset, spaceToWrapAt));
/* 127:127 */            wrappedLine.append(newLineStr);
/* 128:128 */            offset = spaceToWrapAt + 1;
/* 129:    */          } else {
/* 130:130 */            wrappedLine.append(str.substring(offset));
/* 131:131 */            offset = inputLineLength;
/* 132:    */          }
/* 133:    */        }
/* 134:    */      }
/* 135:    */    }
/* 136:    */    
/* 138:138 */    wrappedLine.append(str.substring(offset));
/* 139:    */    
/* 140:140 */    return wrappedLine.toString();
/* 141:    */  }
/* 142:    */  
/* 166:    */  public static String capitalize(String str)
/* 167:    */  {
/* 168:168 */    return capitalize(str, null);
/* 169:    */  }
/* 170:    */  
/* 199:    */  public static String capitalize(String str, char... delimiters)
/* 200:    */  {
/* 201:201 */    int delimLen = delimiters == null ? -1 : delimiters.length;
/* 202:202 */    if ((StringUtils.isEmpty(str)) || (delimLen == 0)) {
/* 203:203 */      return str;
/* 204:    */    }
/* 205:205 */    char[] buffer = str.toCharArray();
/* 206:206 */    boolean capitalizeNext = true;
/* 207:207 */    for (int i = 0; i < buffer.length; i++) {
/* 208:208 */      char ch = buffer[i];
/* 209:209 */      if (isDelimiter(ch, delimiters)) {
/* 210:210 */        capitalizeNext = true;
/* 211:211 */      } else if (capitalizeNext) {
/* 212:212 */        buffer[i] = Character.toTitleCase(ch);
/* 213:213 */        capitalizeNext = false;
/* 214:    */      }
/* 215:    */    }
/* 216:216 */    return new String(buffer);
/* 217:    */  }
/* 218:    */  
/* 238:    */  public static String capitalizeFully(String str)
/* 239:    */  {
/* 240:240 */    return capitalizeFully(str, null);
/* 241:    */  }
/* 242:    */  
/* 268:    */  public static String capitalizeFully(String str, char... delimiters)
/* 269:    */  {
/* 270:270 */    int delimLen = delimiters == null ? -1 : delimiters.length;
/* 271:271 */    if ((StringUtils.isEmpty(str)) || (delimLen == 0)) {
/* 272:272 */      return str;
/* 273:    */    }
/* 274:274 */    str = str.toLowerCase();
/* 275:275 */    return capitalize(str, delimiters);
/* 276:    */  }
/* 277:    */  
/* 295:    */  public static String uncapitalize(String str)
/* 296:    */  {
/* 297:297 */    return uncapitalize(str, null);
/* 298:    */  }
/* 299:    */  
/* 324:    */  public static String uncapitalize(String str, char... delimiters)
/* 325:    */  {
/* 326:326 */    int delimLen = delimiters == null ? -1 : delimiters.length;
/* 327:327 */    if ((StringUtils.isEmpty(str)) || (delimLen == 0)) {
/* 328:328 */      return str;
/* 329:    */    }
/* 330:330 */    char[] buffer = str.toCharArray();
/* 331:331 */    boolean uncapitalizeNext = true;
/* 332:332 */    for (int i = 0; i < buffer.length; i++) {
/* 333:333 */      char ch = buffer[i];
/* 334:334 */      if (isDelimiter(ch, delimiters)) {
/* 335:335 */        uncapitalizeNext = true;
/* 336:336 */      } else if (uncapitalizeNext) {
/* 337:337 */        buffer[i] = Character.toLowerCase(ch);
/* 338:338 */        uncapitalizeNext = false;
/* 339:    */      }
/* 340:    */    }
/* 341:341 */    return new String(buffer);
/* 342:    */  }
/* 343:    */  
/* 366:    */  public static String swapCase(String str)
/* 367:    */  {
/* 368:368 */    if (StringUtils.isEmpty(str)) {
/* 369:369 */      return str;
/* 370:    */    }
/* 371:371 */    char[] buffer = str.toCharArray();
/* 372:    */    
/* 373:373 */    boolean whitespace = true;
/* 374:    */    
/* 375:375 */    for (int i = 0; i < buffer.length; i++) {
/* 376:376 */      char ch = buffer[i];
/* 377:377 */      if (Character.isUpperCase(ch)) {
/* 378:378 */        buffer[i] = Character.toLowerCase(ch);
/* 379:379 */        whitespace = false;
/* 380:380 */      } else if (Character.isTitleCase(ch)) {
/* 381:381 */        buffer[i] = Character.toLowerCase(ch);
/* 382:382 */        whitespace = false;
/* 383:383 */      } else if (Character.isLowerCase(ch)) {
/* 384:384 */        if (whitespace) {
/* 385:385 */          buffer[i] = Character.toTitleCase(ch);
/* 386:386 */          whitespace = false;
/* 387:    */        } else {
/* 388:388 */          buffer[i] = Character.toUpperCase(ch);
/* 389:    */        }
/* 390:    */      } else {
/* 391:391 */        whitespace = Character.isWhitespace(ch);
/* 392:    */      }
/* 393:    */    }
/* 394:394 */    return new String(buffer);
/* 395:    */  }
/* 396:    */  
/* 419:    */  public static String initials(String str)
/* 420:    */  {
/* 421:421 */    return initials(str, null);
/* 422:    */  }
/* 423:    */  
/* 450:    */  public static String initials(String str, char... delimiters)
/* 451:    */  {
/* 452:452 */    if (StringUtils.isEmpty(str)) {
/* 453:453 */      return str;
/* 454:    */    }
/* 455:455 */    if ((delimiters != null) && (delimiters.length == 0)) {
/* 456:456 */      return "";
/* 457:    */    }
/* 458:458 */    int strLen = str.length();
/* 459:459 */    char[] buf = new char[strLen / 2 + 1];
/* 460:460 */    int count = 0;
/* 461:461 */    boolean lastWasGap = true;
/* 462:462 */    for (int i = 0; i < strLen; i++) {
/* 463:463 */      char ch = str.charAt(i);
/* 464:    */      
/* 465:465 */      if (isDelimiter(ch, delimiters)) {
/* 466:466 */        lastWasGap = true;
/* 467:467 */      } else if (lastWasGap) {
/* 468:468 */        buf[(count++)] = ch;
/* 469:469 */        lastWasGap = false;
/* 470:    */      }
/* 471:    */    }
/* 472:    */    
/* 474:474 */    return new String(buf, 0, count);
/* 475:    */  }
/* 476:    */  
/* 484:    */  private static boolean isDelimiter(char ch, char[] delimiters)
/* 485:    */  {
/* 486:486 */    if (delimiters == null) {
/* 487:487 */      return Character.isWhitespace(ch);
/* 488:    */    }
/* 489:489 */    for (char delimiter : delimiters) {
/* 490:490 */      if (ch == delimiter) {
/* 491:491 */        return true;
/* 492:    */      }
/* 493:    */    }
/* 494:494 */    return false;
/* 495:    */  }
/* 496:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.WordUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */