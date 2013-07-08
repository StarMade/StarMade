/*   1:    */package org.apache.commons.lang3.text;
/*   2:    */
/*   3:    */import java.text.Format;
/*   4:    */import java.text.MessageFormat;
/*   5:    */import java.text.ParsePosition;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.Collection;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.Locale;
/*  10:    */import java.util.Map;
/*  11:    */import org.apache.commons.lang3.ObjectUtils;
/*  12:    */import org.apache.commons.lang3.Validate;
/*  13:    */
/*  75:    */public class ExtendedMessageFormat
/*  76:    */  extends MessageFormat
/*  77:    */{
/*  78:    */  private static final long serialVersionUID = -2362048321261811743L;
/*  79:    */  private static final int HASH_SEED = 31;
/*  80:    */  private static final String DUMMY_PATTERN = "";
/*  81:    */  private static final String ESCAPED_QUOTE = "''";
/*  82:    */  private static final char START_FMT = ',';
/*  83:    */  private static final char END_FE = '}';
/*  84:    */  private static final char START_FE = '{';
/*  85:    */  private static final char QUOTE = '\'';
/*  86:    */  private String toPattern;
/*  87:    */  private final Map<String, ? extends FormatFactory> registry;
/*  88:    */  
/*  89:    */  public ExtendedMessageFormat(String pattern)
/*  90:    */  {
/*  91: 91 */    this(pattern, Locale.getDefault());
/*  92:    */  }
/*  93:    */  
/* 100:    */  public ExtendedMessageFormat(String pattern, Locale locale)
/* 101:    */  {
/* 102:102 */    this(pattern, locale, null);
/* 103:    */  }
/* 104:    */  
/* 111:    */  public ExtendedMessageFormat(String pattern, Map<String, ? extends FormatFactory> registry)
/* 112:    */  {
/* 113:113 */    this(pattern, Locale.getDefault(), registry);
/* 114:    */  }
/* 115:    */  
/* 123:    */  public ExtendedMessageFormat(String pattern, Locale locale, Map<String, ? extends FormatFactory> registry)
/* 124:    */  {
/* 125:125 */    super("");
/* 126:126 */    setLocale(locale);
/* 127:127 */    this.registry = registry;
/* 128:128 */    applyPattern(pattern);
/* 129:    */  }
/* 130:    */  
/* 134:    */  public String toPattern()
/* 135:    */  {
/* 136:136 */    return this.toPattern;
/* 137:    */  }
/* 138:    */  
/* 144:    */  public final void applyPattern(String pattern)
/* 145:    */  {
/* 146:146 */    if (this.registry == null) {
/* 147:147 */      super.applyPattern(pattern);
/* 148:148 */      this.toPattern = super.toPattern();
/* 149:149 */      return;
/* 150:    */    }
/* 151:151 */    ArrayList<Format> foundFormats = new ArrayList();
/* 152:152 */    ArrayList<String> foundDescriptions = new ArrayList();
/* 153:153 */    StringBuilder stripCustom = new StringBuilder(pattern.length());
/* 154:    */    
/* 155:155 */    ParsePosition pos = new ParsePosition(0);
/* 156:156 */    char[] c = pattern.toCharArray();
/* 157:157 */    int fmtCount = 0;
/* 158:158 */    while (pos.getIndex() < pattern.length()) {
/* 159:159 */      switch (c[pos.getIndex()]) {
/* 160:    */      case '\'': 
/* 161:161 */        appendQuotedString(pattern, pos, stripCustom, true);
/* 162:162 */        break;
/* 163:    */      case '{': 
/* 164:164 */        fmtCount++;
/* 165:165 */        seekNonWs(pattern, pos);
/* 166:166 */        int start = pos.getIndex();
/* 167:167 */        int index = readArgumentIndex(pattern, next(pos));
/* 168:168 */        stripCustom.append('{').append(index);
/* 169:169 */        seekNonWs(pattern, pos);
/* 170:170 */        Format format = null;
/* 171:171 */        String formatDescription = null;
/* 172:172 */        if (c[pos.getIndex()] == ',') {
/* 173:173 */          formatDescription = parseFormatDescription(pattern, next(pos));
/* 174:    */          
/* 175:175 */          format = getFormat(formatDescription);
/* 176:176 */          if (format == null) {
/* 177:177 */            stripCustom.append(',').append(formatDescription);
/* 178:    */          }
/* 179:    */        }
/* 180:180 */        foundFormats.add(format);
/* 181:181 */        foundDescriptions.add(format == null ? null : formatDescription);
/* 182:182 */        Validate.isTrue(foundFormats.size() == fmtCount);
/* 183:183 */        Validate.isTrue(foundDescriptions.size() == fmtCount);
/* 184:184 */        if (c[pos.getIndex()] != '}') {
/* 185:185 */          throw new IllegalArgumentException("Unreadable format element at position " + start);
/* 186:    */        }
/* 187:    */        break;
/* 188:    */      }
/* 189:    */      
/* 190:190 */      stripCustom.append(c[pos.getIndex()]);
/* 191:191 */      next(pos);
/* 192:    */    }
/* 193:    */    
/* 194:194 */    super.applyPattern(stripCustom.toString());
/* 195:195 */    this.toPattern = insertFormats(super.toPattern(), foundDescriptions);
/* 196:196 */    if (containsElements(foundFormats)) {
/* 197:197 */      Format[] origFormats = getFormats();
/* 198:    */      
/* 200:200 */      int i = 0;
/* 201:201 */      for (Iterator<Format> it = foundFormats.iterator(); it.hasNext(); i++) {
/* 202:202 */        Format f = (Format)it.next();
/* 203:203 */        if (f != null) {
/* 204:204 */          origFormats[i] = f;
/* 205:    */        }
/* 206:    */      }
/* 207:207 */      super.setFormats(origFormats);
/* 208:    */    }
/* 209:    */  }
/* 210:    */  
/* 218:    */  public void setFormat(int formatElementIndex, Format newFormat)
/* 219:    */  {
/* 220:220 */    throw new UnsupportedOperationException();
/* 221:    */  }
/* 222:    */  
/* 230:    */  public void setFormatByArgumentIndex(int argumentIndex, Format newFormat)
/* 231:    */  {
/* 232:232 */    throw new UnsupportedOperationException();
/* 233:    */  }
/* 234:    */  
/* 241:    */  public void setFormats(Format[] newFormats)
/* 242:    */  {
/* 243:243 */    throw new UnsupportedOperationException();
/* 244:    */  }
/* 245:    */  
/* 252:    */  public void setFormatsByArgumentIndex(Format[] newFormats)
/* 253:    */  {
/* 254:254 */    throw new UnsupportedOperationException();
/* 255:    */  }
/* 256:    */  
/* 263:    */  public boolean equals(Object obj)
/* 264:    */  {
/* 265:265 */    if (obj == this) {
/* 266:266 */      return true;
/* 267:    */    }
/* 268:268 */    if (obj == null) {
/* 269:269 */      return false;
/* 270:    */    }
/* 271:271 */    if (!super.equals(obj)) {
/* 272:272 */      return false;
/* 273:    */    }
/* 274:274 */    if (ObjectUtils.notEqual(getClass(), obj.getClass())) {
/* 275:275 */      return false;
/* 276:    */    }
/* 277:277 */    ExtendedMessageFormat rhs = (ExtendedMessageFormat)obj;
/* 278:278 */    if (ObjectUtils.notEqual(this.toPattern, rhs.toPattern)) {
/* 279:279 */      return false;
/* 280:    */    }
/* 281:281 */    if (ObjectUtils.notEqual(this.registry, rhs.registry)) {
/* 282:282 */      return false;
/* 283:    */    }
/* 284:284 */    return true;
/* 285:    */  }
/* 286:    */  
/* 292:    */  public int hashCode()
/* 293:    */  {
/* 294:294 */    int result = super.hashCode();
/* 295:295 */    result = 31 * result + ObjectUtils.hashCode(this.registry);
/* 296:296 */    result = 31 * result + ObjectUtils.hashCode(this.toPattern);
/* 297:297 */    return result;
/* 298:    */  }
/* 299:    */  
/* 305:    */  private Format getFormat(String desc)
/* 306:    */  {
/* 307:307 */    if (this.registry != null) {
/* 308:308 */      String name = desc;
/* 309:309 */      String args = null;
/* 310:310 */      int i = desc.indexOf(',');
/* 311:311 */      if (i > 0) {
/* 312:312 */        name = desc.substring(0, i).trim();
/* 313:313 */        args = desc.substring(i + 1).trim();
/* 314:    */      }
/* 315:315 */      FormatFactory factory = (FormatFactory)this.registry.get(name);
/* 316:316 */      if (factory != null) {
/* 317:317 */        return factory.getFormat(name, args, getLocale());
/* 318:    */      }
/* 319:    */    }
/* 320:320 */    return null;
/* 321:    */  }
/* 322:    */  
/* 329:    */  private int readArgumentIndex(String pattern, ParsePosition pos)
/* 330:    */  {
/* 331:331 */    int start = pos.getIndex();
/* 332:332 */    seekNonWs(pattern, pos);
/* 333:333 */    StringBuffer result = new StringBuffer();
/* 334:334 */    boolean error = false;
/* 335:335 */    for (; (!error) && (pos.getIndex() < pattern.length()); next(pos)) {
/* 336:336 */      char c = pattern.charAt(pos.getIndex());
/* 337:337 */      if (Character.isWhitespace(c)) {
/* 338:338 */        seekNonWs(pattern, pos);
/* 339:339 */        c = pattern.charAt(pos.getIndex());
/* 340:340 */        if ((c != ',') && (c != '}')) {
/* 341:341 */          error = true;
/* 342:342 */          continue;
/* 343:    */        }
/* 344:    */      }
/* 345:345 */      if (((c == ',') || (c == '}')) && (result.length() > 0)) {
/* 346:    */        try {
/* 347:347 */          return Integer.parseInt(result.toString());
/* 348:    */        }
/* 349:    */        catch (NumberFormatException e) {}
/* 350:    */      }
/* 351:    */      
/* 353:353 */      error = !Character.isDigit(c);
/* 354:354 */      result.append(c);
/* 355:    */    }
/* 356:356 */    if (error) {
/* 357:357 */      throw new IllegalArgumentException("Invalid format argument index at position " + start + ": " + pattern.substring(start, pos.getIndex()));
/* 358:    */    }
/* 359:    */    
/* 361:361 */    throw new IllegalArgumentException("Unterminated format element at position " + start);
/* 362:    */  }
/* 363:    */  
/* 371:    */  private String parseFormatDescription(String pattern, ParsePosition pos)
/* 372:    */  {
/* 373:373 */    int start = pos.getIndex();
/* 374:374 */    seekNonWs(pattern, pos);
/* 375:375 */    int text = pos.getIndex();
/* 376:376 */    int depth = 1;
/* 377:377 */    for (; pos.getIndex() < pattern.length(); next(pos)) {
/* 378:378 */      switch (pattern.charAt(pos.getIndex())) {
/* 379:    */      case '{': 
/* 380:380 */        depth++;
/* 381:381 */        break;
/* 382:    */      case '}': 
/* 383:383 */        depth--;
/* 384:384 */        if (depth == 0) {
/* 385:385 */          return pattern.substring(text, pos.getIndex());
/* 386:    */        }
/* 387:    */        break;
/* 388:    */      case '\'': 
/* 389:389 */        getQuotedString(pattern, pos, false);
/* 390:    */      }
/* 391:    */      
/* 392:    */    }
/* 393:393 */    throw new IllegalArgumentException("Unterminated format element at position " + start);
/* 394:    */  }
/* 395:    */  
/* 403:    */  private String insertFormats(String pattern, ArrayList<String> customPatterns)
/* 404:    */  {
/* 405:405 */    if (!containsElements(customPatterns)) {
/* 406:406 */      return pattern;
/* 407:    */    }
/* 408:408 */    StringBuilder sb = new StringBuilder(pattern.length() * 2);
/* 409:409 */    ParsePosition pos = new ParsePosition(0);
/* 410:410 */    int fe = -1;
/* 411:411 */    int depth = 0;
/* 412:412 */    while (pos.getIndex() < pattern.length()) {
/* 413:413 */      char c = pattern.charAt(pos.getIndex());
/* 414:414 */      switch (c) {
/* 415:    */      case '\'': 
/* 416:416 */        appendQuotedString(pattern, pos, sb, false);
/* 417:417 */        break;
/* 418:    */      case '{': 
/* 419:419 */        depth++;
/* 420:420 */        if (depth == 1) {
/* 421:421 */          fe++;
/* 422:422 */          sb.append('{').append(readArgumentIndex(pattern, next(pos)));
/* 423:    */          
/* 424:424 */          String customPattern = (String)customPatterns.get(fe);
/* 425:425 */          if (customPattern != null)
/* 426:426 */            sb.append(',').append(customPattern);
/* 427:    */        }
/* 428:428 */        break;
/* 429:    */      
/* 430:    */      case '}': 
/* 431:431 */        depth--;
/* 432:    */      
/* 433:    */      default: 
/* 434:434 */        sb.append(c);
/* 435:435 */        next(pos);
/* 436:    */      }
/* 437:    */    }
/* 438:438 */    return sb.toString();
/* 439:    */  }
/* 440:    */  
/* 446:    */  private void seekNonWs(String pattern, ParsePosition pos)
/* 447:    */  {
/* 448:448 */    int len = 0;
/* 449:449 */    char[] buffer = pattern.toCharArray();
/* 450:    */    do {
/* 451:451 */      len = StrMatcher.splitMatcher().isMatch(buffer, pos.getIndex());
/* 452:452 */      pos.setIndex(pos.getIndex() + len);
/* 453:453 */    } while ((len > 0) && (pos.getIndex() < pattern.length()));
/* 454:    */  }
/* 455:    */  
/* 461:    */  private ParsePosition next(ParsePosition pos)
/* 462:    */  {
/* 463:463 */    pos.setIndex(pos.getIndex() + 1);
/* 464:464 */    return pos;
/* 465:    */  }
/* 466:    */  
/* 477:    */  private StringBuilder appendQuotedString(String pattern, ParsePosition pos, StringBuilder appendTo, boolean escapingOn)
/* 478:    */  {
/* 479:479 */    int start = pos.getIndex();
/* 480:480 */    char[] c = pattern.toCharArray();
/* 481:481 */    if ((escapingOn) && (c[start] == '\'')) {
/* 482:482 */      next(pos);
/* 483:483 */      return appendTo == null ? null : appendTo.append('\'');
/* 484:    */    }
/* 485:485 */    int lastHold = start;
/* 486:486 */    for (int i = pos.getIndex(); i < pattern.length(); i++) {
/* 487:487 */      if ((escapingOn) && (pattern.substring(i).startsWith("''"))) {
/* 488:488 */        appendTo.append(c, lastHold, pos.getIndex() - lastHold).append('\'');
/* 489:    */        
/* 490:490 */        pos.setIndex(i + "''".length());
/* 491:491 */        lastHold = pos.getIndex();
/* 492:    */      }
/* 493:    */      else {
/* 494:494 */        switch (c[pos.getIndex()]) {
/* 495:    */        case '\'': 
/* 496:496 */          next(pos);
/* 497:497 */          return appendTo == null ? null : appendTo.append(c, lastHold, pos.getIndex() - lastHold);
/* 498:    */        }
/* 499:    */        
/* 500:500 */        next(pos);
/* 501:    */      }
/* 502:    */    }
/* 503:503 */    throw new IllegalArgumentException("Unterminated quoted string at position " + start);
/* 504:    */  }
/* 505:    */  
/* 514:    */  private void getQuotedString(String pattern, ParsePosition pos, boolean escapingOn)
/* 515:    */  {
/* 516:516 */    appendQuotedString(pattern, pos, null, escapingOn);
/* 517:    */  }
/* 518:    */  
/* 523:    */  private boolean containsElements(Collection<?> coll)
/* 524:    */  {
/* 525:525 */    if ((coll == null) || (coll.isEmpty())) {
/* 526:526 */      return false;
/* 527:    */    }
/* 528:528 */    for (Iterator i$ = coll.iterator(); i$.hasNext();) { Object name = i$.next();
/* 529:529 */      if (name != null) {
/* 530:530 */        return true;
/* 531:    */      }
/* 532:    */    }
/* 533:533 */    return false;
/* 534:    */  }
/* 535:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.ExtendedMessageFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */