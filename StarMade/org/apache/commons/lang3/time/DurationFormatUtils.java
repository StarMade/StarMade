/*   1:    */package org.apache.commons.lang3.time;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Calendar;
/*   5:    */import java.util.Date;
/*   6:    */import java.util.GregorianCalendar;
/*   7:    */import java.util.TimeZone;
/*   8:    */import org.apache.commons.lang3.StringUtils;
/*   9:    */
/*  70:    */public class DurationFormatUtils
/*  71:    */{
/*  72:    */  public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'";
/*  73:    */  
/*  74:    */  public static String formatDurationHMS(long durationMillis)
/*  75:    */  {
/*  76: 76 */    return formatDuration(durationMillis, "H:mm:ss.SSS");
/*  77:    */  }
/*  78:    */  
/*  89:    */  public static String formatDurationISO(long durationMillis)
/*  90:    */  {
/*  91: 91 */    return formatDuration(durationMillis, "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'", false);
/*  92:    */  }
/*  93:    */  
/* 104:    */  public static String formatDuration(long durationMillis, String format)
/* 105:    */  {
/* 106:106 */    return formatDuration(durationMillis, format, true);
/* 107:    */  }
/* 108:    */  
/* 122:    */  public static String formatDuration(long durationMillis, String format, boolean padWithZeros)
/* 123:    */  {
/* 124:124 */    Token[] tokens = lexx(format);
/* 125:    */    
/* 126:126 */    int days = 0;
/* 127:127 */    int hours = 0;
/* 128:128 */    int minutes = 0;
/* 129:129 */    int seconds = 0;
/* 130:130 */    int milliseconds = 0;
/* 131:    */    
/* 132:132 */    if (Token.containsTokenWithValue(tokens, d)) {
/* 133:133 */      days = (int)(durationMillis / 86400000L);
/* 134:134 */      durationMillis -= days * 86400000L;
/* 135:    */    }
/* 136:136 */    if (Token.containsTokenWithValue(tokens, H)) {
/* 137:137 */      hours = (int)(durationMillis / 3600000L);
/* 138:138 */      durationMillis -= hours * 3600000L;
/* 139:    */    }
/* 140:140 */    if (Token.containsTokenWithValue(tokens, m)) {
/* 141:141 */      minutes = (int)(durationMillis / 60000L);
/* 142:142 */      durationMillis -= minutes * 60000L;
/* 143:    */    }
/* 144:144 */    if (Token.containsTokenWithValue(tokens, s)) {
/* 145:145 */      seconds = (int)(durationMillis / 1000L);
/* 146:146 */      durationMillis -= seconds * 1000L;
/* 147:    */    }
/* 148:148 */    if (Token.containsTokenWithValue(tokens, S)) {
/* 149:149 */      milliseconds = (int)durationMillis;
/* 150:    */    }
/* 151:    */    
/* 152:152 */    return format(tokens, 0, 0, days, hours, minutes, seconds, milliseconds, padWithZeros);
/* 153:    */  }
/* 154:    */  
/* 172:    */  public static String formatDurationWords(long durationMillis, boolean suppressLeadingZeroElements, boolean suppressTrailingZeroElements)
/* 173:    */  {
/* 174:174 */    String duration = formatDuration(durationMillis, "d' days 'H' hours 'm' minutes 's' seconds'");
/* 175:175 */    if (suppressLeadingZeroElements)
/* 176:    */    {
/* 177:177 */      duration = " " + duration;
/* 178:178 */      String tmp = StringUtils.replaceOnce(duration, " 0 days", "");
/* 179:179 */      if (tmp.length() != duration.length()) {
/* 180:180 */        duration = tmp;
/* 181:181 */        tmp = StringUtils.replaceOnce(duration, " 0 hours", "");
/* 182:182 */        if (tmp.length() != duration.length()) {
/* 183:183 */          duration = tmp;
/* 184:184 */          tmp = StringUtils.replaceOnce(duration, " 0 minutes", "");
/* 185:185 */          duration = tmp;
/* 186:186 */          if (tmp.length() != duration.length()) {
/* 187:187 */            duration = StringUtils.replaceOnce(tmp, " 0 seconds", "");
/* 188:    */          }
/* 189:    */        }
/* 190:    */      }
/* 191:191 */      if (duration.length() != 0)
/* 192:    */      {
/* 193:193 */        duration = duration.substring(1);
/* 194:    */      }
/* 195:    */    }
/* 196:196 */    if (suppressTrailingZeroElements) {
/* 197:197 */      String tmp = StringUtils.replaceOnce(duration, " 0 seconds", "");
/* 198:198 */      if (tmp.length() != duration.length()) {
/* 199:199 */        duration = tmp;
/* 200:200 */        tmp = StringUtils.replaceOnce(duration, " 0 minutes", "");
/* 201:201 */        if (tmp.length() != duration.length()) {
/* 202:202 */          duration = tmp;
/* 203:203 */          tmp = StringUtils.replaceOnce(duration, " 0 hours", "");
/* 204:204 */          if (tmp.length() != duration.length()) {
/* 205:205 */            duration = StringUtils.replaceOnce(tmp, " 0 days", "");
/* 206:    */          }
/* 207:    */        }
/* 208:    */      }
/* 209:    */    }
/* 210:    */    
/* 211:211 */    duration = " " + duration;
/* 212:212 */    duration = StringUtils.replaceOnce(duration, " 1 seconds", " 1 second");
/* 213:213 */    duration = StringUtils.replaceOnce(duration, " 1 minutes", " 1 minute");
/* 214:214 */    duration = StringUtils.replaceOnce(duration, " 1 hours", " 1 hour");
/* 215:215 */    duration = StringUtils.replaceOnce(duration, " 1 days", " 1 day");
/* 216:216 */    return duration.trim();
/* 217:    */  }
/* 218:    */  
/* 228:    */  public static String formatPeriodISO(long startMillis, long endMillis)
/* 229:    */  {
/* 230:230 */    return formatPeriod(startMillis, endMillis, "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'", false, TimeZone.getDefault());
/* 231:    */  }
/* 232:    */  
/* 241:    */  public static String formatPeriod(long startMillis, long endMillis, String format)
/* 242:    */  {
/* 243:243 */    return formatPeriod(startMillis, endMillis, format, true, TimeZone.getDefault());
/* 244:    */  }
/* 245:    */  
/* 276:    */  public static String formatPeriod(long startMillis, long endMillis, String format, boolean padWithZeros, TimeZone timezone)
/* 277:    */  {
/* 278:278 */    Token[] tokens = lexx(format);
/* 279:    */    
/* 282:282 */    Calendar start = Calendar.getInstance(timezone);
/* 283:283 */    start.setTime(new Date(startMillis));
/* 284:284 */    Calendar end = Calendar.getInstance(timezone);
/* 285:285 */    end.setTime(new Date(endMillis));
/* 286:    */    
/* 288:288 */    int milliseconds = end.get(14) - start.get(14);
/* 289:289 */    int seconds = end.get(13) - start.get(13);
/* 290:290 */    int minutes = end.get(12) - start.get(12);
/* 291:291 */    int hours = end.get(11) - start.get(11);
/* 292:292 */    int days = end.get(5) - start.get(5);
/* 293:293 */    int months = end.get(2) - start.get(2);
/* 294:294 */    int years = end.get(1) - start.get(1);
/* 295:    */    
/* 297:297 */    while (milliseconds < 0) {
/* 298:298 */      milliseconds += 1000;
/* 299:299 */      seconds--;
/* 300:    */    }
/* 301:301 */    while (seconds < 0) {
/* 302:302 */      seconds += 60;
/* 303:303 */      minutes--;
/* 304:    */    }
/* 305:305 */    while (minutes < 0) {
/* 306:306 */      minutes += 60;
/* 307:307 */      hours--;
/* 308:    */    }
/* 309:309 */    while (hours < 0) {
/* 310:310 */      hours += 24;
/* 311:311 */      days--;
/* 312:    */    }
/* 313:    */    
/* 314:314 */    if (Token.containsTokenWithValue(tokens, M)) {
/* 315:315 */      while (days < 0) {
/* 316:316 */        days += start.getActualMaximum(5);
/* 317:317 */        months--;
/* 318:318 */        start.add(2, 1);
/* 319:    */      }
/* 320:    */      
/* 321:321 */      while (months < 0) {
/* 322:322 */        months += 12;
/* 323:323 */        years--;
/* 324:    */      }
/* 325:    */      
/* 326:326 */      if ((!Token.containsTokenWithValue(tokens, y)) && (years != 0)) {
/* 327:327 */        while (years != 0) {
/* 328:328 */          months += 12 * years;
/* 329:329 */          years = 0;
/* 330:    */        }
/* 331:    */      }
/* 332:    */    }
/* 333:    */    else
/* 334:    */    {
/* 335:335 */      if (!Token.containsTokenWithValue(tokens, y)) {
/* 336:336 */        int target = end.get(1);
/* 337:337 */        if (months < 0)
/* 338:    */        {
/* 339:339 */          target--;
/* 340:    */        }
/* 341:    */        
/* 342:342 */        while (start.get(1) != target) {
/* 343:343 */          days += start.getActualMaximum(6) - start.get(6);
/* 344:    */          
/* 346:346 */          if (((start instanceof GregorianCalendar)) && (start.get(2) == 1) && (start.get(5) == 29))
/* 347:    */          {
/* 349:349 */            days++;
/* 350:    */          }
/* 351:    */          
/* 352:352 */          start.add(1, 1);
/* 353:    */          
/* 354:354 */          days += start.get(6);
/* 355:    */        }
/* 356:    */        
/* 357:357 */        years = 0;
/* 358:    */      }
/* 359:    */      
/* 360:360 */      while (start.get(2) != end.get(2)) {
/* 361:361 */        days += start.getActualMaximum(5);
/* 362:362 */        start.add(2, 1);
/* 363:    */      }
/* 364:    */      
/* 365:365 */      months = 0;
/* 366:    */      
/* 367:367 */      while (days < 0) {
/* 368:368 */        days += start.getActualMaximum(5);
/* 369:369 */        months--;
/* 370:370 */        start.add(2, 1);
/* 371:    */      }
/* 372:    */    }
/* 373:    */    
/* 379:379 */    if (!Token.containsTokenWithValue(tokens, d)) {
/* 380:380 */      hours += 24 * days;
/* 381:381 */      days = 0;
/* 382:    */    }
/* 383:383 */    if (!Token.containsTokenWithValue(tokens, H)) {
/* 384:384 */      minutes += 60 * hours;
/* 385:385 */      hours = 0;
/* 386:    */    }
/* 387:387 */    if (!Token.containsTokenWithValue(tokens, m)) {
/* 388:388 */      seconds += 60 * minutes;
/* 389:389 */      minutes = 0;
/* 390:    */    }
/* 391:391 */    if (!Token.containsTokenWithValue(tokens, s)) {
/* 392:392 */      milliseconds += 1000 * seconds;
/* 393:393 */      seconds = 0;
/* 394:    */    }
/* 395:    */    
/* 396:396 */    return format(tokens, years, months, days, hours, minutes, seconds, milliseconds, padWithZeros);
/* 397:    */  }
/* 398:    */  
/* 414:    */  static String format(Token[] tokens, int years, int months, int days, int hours, int minutes, int seconds, int milliseconds, boolean padWithZeros)
/* 415:    */  {
/* 416:416 */    StringBuffer buffer = new StringBuffer();
/* 417:417 */    boolean lastOutputSeconds = false;
/* 418:418 */    int sz = tokens.length;
/* 419:419 */    for (int i = 0; i < sz; i++) {
/* 420:420 */      Token token = tokens[i];
/* 421:421 */      Object value = token.getValue();
/* 422:422 */      int count = token.getCount();
/* 423:423 */      if ((value instanceof StringBuffer)) {
/* 424:424 */        buffer.append(value.toString());
/* 425:    */      }
/* 426:426 */      else if (value == y) {
/* 427:427 */        buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(years), count, '0') : Integer.toString(years));
/* 428:    */        
/* 429:429 */        lastOutputSeconds = false;
/* 430:430 */      } else if (value == M) {
/* 431:431 */        buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(months), count, '0') : Integer.toString(months));
/* 432:    */        
/* 433:433 */        lastOutputSeconds = false;
/* 434:434 */      } else if (value == d) {
/* 435:435 */        buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(days), count, '0') : Integer.toString(days));
/* 436:    */        
/* 437:437 */        lastOutputSeconds = false;
/* 438:438 */      } else if (value == H) {
/* 439:439 */        buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(hours), count, '0') : Integer.toString(hours));
/* 440:    */        
/* 441:441 */        lastOutputSeconds = false;
/* 442:442 */      } else if (value == m) {
/* 443:443 */        buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(minutes), count, '0') : Integer.toString(minutes));
/* 444:    */        
/* 445:445 */        lastOutputSeconds = false;
/* 446:446 */      } else if (value == s) {
/* 447:447 */        buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(seconds), count, '0') : Integer.toString(seconds));
/* 448:    */        
/* 449:449 */        lastOutputSeconds = true;
/* 450:450 */      } else if (value == S) {
/* 451:451 */        if (lastOutputSeconds) {
/* 452:452 */          milliseconds += 1000;
/* 453:453 */          String str = padWithZeros ? StringUtils.leftPad(Integer.toString(milliseconds), count, '0') : Integer.toString(milliseconds);
/* 454:    */          
/* 456:456 */          buffer.append(str.substring(1));
/* 457:    */        } else {
/* 458:458 */          buffer.append(padWithZeros ? StringUtils.leftPad(Integer.toString(milliseconds), count, '0') : Integer.toString(milliseconds));
/* 459:    */        }
/* 460:    */        
/* 462:462 */        lastOutputSeconds = false;
/* 463:    */      }
/* 464:    */    }
/* 465:    */    
/* 466:466 */    return buffer.toString();
/* 467:    */  }
/* 468:    */  
/* 469:469 */  static final Object y = "y";
/* 470:470 */  static final Object M = "M";
/* 471:471 */  static final Object d = "d";
/* 472:472 */  static final Object H = "H";
/* 473:473 */  static final Object m = "m";
/* 474:474 */  static final Object s = "s";
/* 475:475 */  static final Object S = "S";
/* 476:    */  
/* 482:    */  static Token[] lexx(String format)
/* 483:    */  {
/* 484:484 */    char[] array = format.toCharArray();
/* 485:485 */    ArrayList<Token> list = new ArrayList(array.length);
/* 486:    */    
/* 487:487 */    boolean inLiteral = false;
/* 488:488 */    StringBuffer buffer = null;
/* 489:489 */    Token previous = null;
/* 490:490 */    int sz = array.length;
/* 491:491 */    for (int i = 0; i < sz; i++) {
/* 492:492 */      char ch = array[i];
/* 493:493 */      if ((inLiteral) && (ch != '\'')) {
/* 494:494 */        buffer.append(ch);
/* 495:    */      }
/* 496:    */      else {
/* 497:497 */        Object value = null;
/* 498:498 */        switch (ch)
/* 499:    */        {
/* 500:    */        case '\'': 
/* 501:501 */          if (inLiteral) {
/* 502:502 */            buffer = null;
/* 503:503 */            inLiteral = false;
/* 504:    */          } else {
/* 505:505 */            buffer = new StringBuffer();
/* 506:506 */            list.add(new Token(buffer));
/* 507:507 */            inLiteral = true;
/* 508:    */          }
/* 509:509 */          break;
/* 510:510 */        case 'y':  value = y;break;
/* 511:511 */        case 'M':  value = M;break;
/* 512:512 */        case 'd':  value = d;break;
/* 513:513 */        case 'H':  value = H;break;
/* 514:514 */        case 'm':  value = m;break;
/* 515:515 */        case 's':  value = s;break;
/* 516:516 */        case 'S':  value = S;break;
/* 517:    */        default: 
/* 518:518 */          if (buffer == null) {
/* 519:519 */            buffer = new StringBuffer();
/* 520:520 */            list.add(new Token(buffer));
/* 521:    */          }
/* 522:522 */          buffer.append(ch);
/* 523:    */        }
/* 524:    */        
/* 525:525 */        if (value != null) {
/* 526:526 */          if ((previous != null) && (previous.getValue() == value)) {
/* 527:527 */            previous.increment();
/* 528:    */          } else {
/* 529:529 */            Token token = new Token(value);
/* 530:530 */            list.add(token);
/* 531:531 */            previous = token;
/* 532:    */          }
/* 533:533 */          buffer = null;
/* 534:    */        }
/* 535:    */      } }
/* 536:536 */    return (Token[])list.toArray(new Token[list.size()]);
/* 537:    */  }
/* 538:    */  
/* 542:    */  static class Token
/* 543:    */  {
/* 544:    */    private final Object value;
/* 545:    */    
/* 547:    */    private int count;
/* 548:    */    
/* 551:    */    static boolean containsTokenWithValue(Token[] tokens, Object value)
/* 552:    */    {
/* 553:553 */      int sz = tokens.length;
/* 554:554 */      for (int i = 0; i < sz; i++) {
/* 555:555 */        if (tokens[i].getValue() == value) {
/* 556:556 */          return true;
/* 557:    */        }
/* 558:    */      }
/* 559:559 */      return false;
/* 560:    */    }
/* 561:    */    
/* 569:    */    Token(Object value)
/* 570:    */    {
/* 571:571 */      this.value = value;
/* 572:572 */      this.count = 1;
/* 573:    */    }
/* 574:    */    
/* 581:    */    Token(Object value, int count)
/* 582:    */    {
/* 583:583 */      this.value = value;
/* 584:584 */      this.count = count;
/* 585:    */    }
/* 586:    */    
/* 589:    */    void increment()
/* 590:    */    {
/* 591:591 */      this.count += 1;
/* 592:    */    }
/* 593:    */    
/* 598:    */    int getCount()
/* 599:    */    {
/* 600:600 */      return this.count;
/* 601:    */    }
/* 602:    */    
/* 607:    */    Object getValue()
/* 608:    */    {
/* 609:609 */      return this.value;
/* 610:    */    }
/* 611:    */    
/* 618:    */    public boolean equals(Object obj2)
/* 619:    */    {
/* 620:620 */      if ((obj2 instanceof Token)) {
/* 621:621 */        Token tok2 = (Token)obj2;
/* 622:622 */        if (this.value.getClass() != tok2.value.getClass()) {
/* 623:623 */          return false;
/* 624:    */        }
/* 625:625 */        if (this.count != tok2.count) {
/* 626:626 */          return false;
/* 627:    */        }
/* 628:628 */        if ((this.value instanceof StringBuffer))
/* 629:629 */          return this.value.toString().equals(tok2.value.toString());
/* 630:630 */        if ((this.value instanceof Number)) {
/* 631:631 */          return this.value.equals(tok2.value);
/* 632:    */        }
/* 633:633 */        return this.value == tok2.value;
/* 634:    */      }
/* 635:    */      
/* 636:636 */      return false;
/* 637:    */    }
/* 638:    */    
/* 646:    */    public int hashCode()
/* 647:    */    {
/* 648:648 */      return this.value.hashCode();
/* 649:    */    }
/* 650:    */    
/* 656:    */    public String toString()
/* 657:    */    {
/* 658:658 */      return StringUtils.repeat(this.value.toString(), this.count);
/* 659:    */    }
/* 660:    */  }
/* 661:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.time.DurationFormatUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */