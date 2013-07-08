/*    1:     */package org.apache.commons.lang3.time;
/*    2:     */
/*    3:     */import java.io.IOException;
/*    4:     */import java.io.ObjectInputStream;
/*    5:     */import java.text.DateFormatSymbols;
/*    6:     */import java.text.FieldPosition;
/*    7:     */import java.text.Format;
/*    8:     */import java.text.ParsePosition;
/*    9:     */import java.util.ArrayList;
/*   10:     */import java.util.Calendar;
/*   11:     */import java.util.Date;
/*   12:     */import java.util.GregorianCalendar;
/*   13:     */import java.util.List;
/*   14:     */import java.util.Locale;
/*   15:     */import java.util.TimeZone;
/*   16:     */import java.util.concurrent.ConcurrentHashMap;
/*   17:     */import java.util.concurrent.ConcurrentMap;
/*   18:     */import org.apache.commons.lang3.Validate;
/*   19:     */
/*   99:     */public class FastDateFormat
/*  100:     */  extends Format
/*  101:     */{
/*  102:     */  private static final long serialVersionUID = 1L;
/*  103:     */  public static final int FULL = 0;
/*  104:     */  public static final int LONG = 1;
/*  105:     */  public static final int MEDIUM = 2;
/*  106:     */  public static final int SHORT = 3;
/*  107: 107 */  private static final FormatCache<FastDateFormat> cache = new FormatCache()
/*  108:     */  {
/*  109:     */    protected FastDateFormat createInstance(String pattern, TimeZone timeZone, Locale locale) {
/*  110: 110 */      return new FastDateFormat(pattern, timeZone, locale);
/*  111:     */    }
/*  112:     */  };
/*  113:     */  
/*  114: 114 */  private static ConcurrentMap<TimeZoneDisplayKey, String> cTimeZoneDisplayCache = new ConcurrentHashMap(7);
/*  115:     */  
/*  119:     */  private final String mPattern;
/*  120:     */  
/*  124:     */  private final TimeZone mTimeZone;
/*  125:     */  
/*  129:     */  private final Locale mLocale;
/*  130:     */  
/*  134:     */  private transient Rule[] mRules;
/*  135:     */  
/*  139:     */  private transient int mMaxLengthEstimate;
/*  140:     */  
/*  144:     */  public static FastDateFormat getInstance()
/*  145:     */  {
/*  146: 146 */    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(3), Integer.valueOf(3), null, null);
/*  147:     */  }
/*  148:     */  
/*  157:     */  public static FastDateFormat getInstance(String pattern)
/*  158:     */  {
/*  159: 159 */    return (FastDateFormat)cache.getInstance(pattern, null, null);
/*  160:     */  }
/*  161:     */  
/*  172:     */  public static FastDateFormat getInstance(String pattern, TimeZone timeZone)
/*  173:     */  {
/*  174: 174 */    return (FastDateFormat)cache.getInstance(pattern, timeZone, null);
/*  175:     */  }
/*  176:     */  
/*  186:     */  public static FastDateFormat getInstance(String pattern, Locale locale)
/*  187:     */  {
/*  188: 188 */    return (FastDateFormat)cache.getInstance(pattern, null, locale);
/*  189:     */  }
/*  190:     */  
/*  203:     */  public static FastDateFormat getInstance(String pattern, TimeZone timeZone, Locale locale)
/*  204:     */  {
/*  205: 205 */    return (FastDateFormat)cache.getInstance(pattern, timeZone, locale);
/*  206:     */  }
/*  207:     */  
/*  218:     */  public static FastDateFormat getDateInstance(int style)
/*  219:     */  {
/*  220: 220 */    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, null, null);
/*  221:     */  }
/*  222:     */  
/*  233:     */  public static FastDateFormat getDateInstance(int style, Locale locale)
/*  234:     */  {
/*  235: 235 */    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, null, locale);
/*  236:     */  }
/*  237:     */  
/*  249:     */  public static FastDateFormat getDateInstance(int style, TimeZone timeZone)
/*  250:     */  {
/*  251: 251 */    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, timeZone, null);
/*  252:     */  }
/*  253:     */  
/*  265:     */  public static FastDateFormat getDateInstance(int style, TimeZone timeZone, Locale locale)
/*  266:     */  {
/*  267: 267 */    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, timeZone, locale);
/*  268:     */  }
/*  269:     */  
/*  280:     */  public static FastDateFormat getTimeInstance(int style)
/*  281:     */  {
/*  282: 282 */    return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), null, null);
/*  283:     */  }
/*  284:     */  
/*  295:     */  public static FastDateFormat getTimeInstance(int style, Locale locale)
/*  296:     */  {
/*  297: 297 */    return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), null, locale);
/*  298:     */  }
/*  299:     */  
/*  311:     */  public static FastDateFormat getTimeInstance(int style, TimeZone timeZone)
/*  312:     */  {
/*  313: 313 */    return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), timeZone, null);
/*  314:     */  }
/*  315:     */  
/*  327:     */  public static FastDateFormat getTimeInstance(int style, TimeZone timeZone, Locale locale)
/*  328:     */  {
/*  329: 329 */    return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), timeZone, locale);
/*  330:     */  }
/*  331:     */  
/*  343:     */  public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle)
/*  344:     */  {
/*  345: 345 */    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), null, null);
/*  346:     */  }
/*  347:     */  
/*  359:     */  public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, Locale locale)
/*  360:     */  {
/*  361: 361 */    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), null, locale);
/*  362:     */  }
/*  363:     */  
/*  376:     */  public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone)
/*  377:     */  {
/*  378: 378 */    return getDateTimeInstance(dateStyle, timeStyle, timeZone, null);
/*  379:     */  }
/*  380:     */  
/*  393:     */  public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone, Locale locale)
/*  394:     */  {
/*  395: 395 */    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), timeZone, locale);
/*  396:     */  }
/*  397:     */  
/*  407:     */  static String getTimeZoneDisplay(TimeZone tz, boolean daylight, int style, Locale locale)
/*  408:     */  {
/*  409: 409 */    TimeZoneDisplayKey key = new TimeZoneDisplayKey(tz, daylight, style, locale);
/*  410: 410 */    String value = (String)cTimeZoneDisplayCache.get(key);
/*  411: 411 */    if (value == null)
/*  412:     */    {
/*  413: 413 */      value = tz.getDisplayName(daylight, style, locale);
/*  414: 414 */      String prior = (String)cTimeZoneDisplayCache.putIfAbsent(key, value);
/*  415: 415 */      if (prior != null) {
/*  416: 416 */        value = prior;
/*  417:     */      }
/*  418:     */    }
/*  419: 419 */    return value;
/*  420:     */  }
/*  421:     */  
/*  431:     */  protected FastDateFormat(String pattern, TimeZone timeZone, Locale locale)
/*  432:     */  {
/*  433: 433 */    this.mPattern = pattern;
/*  434: 434 */    this.mTimeZone = timeZone;
/*  435: 435 */    this.mLocale = locale;
/*  436:     */    
/*  437: 437 */    init();
/*  438:     */  }
/*  439:     */  
/*  442:     */  private void init()
/*  443:     */  {
/*  444: 444 */    List<Rule> rulesList = parsePattern();
/*  445: 445 */    this.mRules = ((Rule[])rulesList.toArray(new Rule[rulesList.size()]));
/*  446:     */    
/*  447: 447 */    int len = 0;
/*  448: 448 */    int i = this.mRules.length; for (;;) { i--; if (i < 0) break;
/*  449: 449 */      len += this.mRules[i].estimateLength();
/*  450:     */    }
/*  451:     */    
/*  452: 452 */    this.mMaxLengthEstimate = len;
/*  453:     */  }
/*  454:     */  
/*  462:     */  protected List<Rule> parsePattern()
/*  463:     */  {
/*  464: 464 */    DateFormatSymbols symbols = new DateFormatSymbols(this.mLocale);
/*  465: 465 */    List<Rule> rules = new ArrayList();
/*  466:     */    
/*  467: 467 */    String[] ERAs = symbols.getEras();
/*  468: 468 */    String[] months = symbols.getMonths();
/*  469: 469 */    String[] shortMonths = symbols.getShortMonths();
/*  470: 470 */    String[] weekdays = symbols.getWeekdays();
/*  471: 471 */    String[] shortWeekdays = symbols.getShortWeekdays();
/*  472: 472 */    String[] AmPmStrings = symbols.getAmPmStrings();
/*  473:     */    
/*  474: 474 */    int length = this.mPattern.length();
/*  475: 475 */    int[] indexRef = new int[1];
/*  476:     */    
/*  477: 477 */    for (int i = 0; i < length; i++) {
/*  478: 478 */      indexRef[0] = i;
/*  479: 479 */      String token = parseToken(this.mPattern, indexRef);
/*  480: 480 */      i = indexRef[0];
/*  481:     */      
/*  482: 482 */      int tokenLen = token.length();
/*  483: 483 */      if (tokenLen == 0) {
/*  484:     */        break;
/*  485:     */      }
/*  486:     */      
/*  488: 488 */      char c = token.charAt(0);
/*  489:     */      Rule rule;
/*  490: 490 */      Rule rule; Rule rule; Rule rule; Rule rule; Rule rule; switch (c) {
/*  491:     */      case 'G': 
/*  492: 492 */        rule = new TextField(0, ERAs);
/*  493: 493 */        break;
/*  494:     */      case 'y': 
/*  495: 495 */        if (tokenLen == 2) {
/*  496: 496 */          rule = TwoDigitYearField.INSTANCE;
/*  497:     */        } else {
/*  498: 498 */          rule = selectNumberRule(1, tokenLen < 4 ? 4 : tokenLen);
/*  499:     */        }
/*  500: 500 */        break;
/*  501:     */      case 'M': 
/*  502: 502 */        if (tokenLen >= 4) {
/*  503: 503 */          rule = new TextField(2, months); } else { Rule rule;
/*  504: 504 */          if (tokenLen == 3) {
/*  505: 505 */            rule = new TextField(2, shortMonths); } else { Rule rule;
/*  506: 506 */            if (tokenLen == 2) {
/*  507: 507 */              rule = TwoDigitMonthField.INSTANCE;
/*  508:     */            } else
/*  509: 509 */              rule = UnpaddedMonthField.INSTANCE;
/*  510:     */          } }
/*  511: 511 */        break;
/*  512:     */      case 'd': 
/*  513: 513 */        rule = selectNumberRule(5, tokenLen);
/*  514: 514 */        break;
/*  515:     */      case 'h': 
/*  516: 516 */        rule = new TwelveHourField(selectNumberRule(10, tokenLen));
/*  517: 517 */        break;
/*  518:     */      case 'H': 
/*  519: 519 */        rule = selectNumberRule(11, tokenLen);
/*  520: 520 */        break;
/*  521:     */      case 'm': 
/*  522: 522 */        rule = selectNumberRule(12, tokenLen);
/*  523: 523 */        break;
/*  524:     */      case 's': 
/*  525: 525 */        rule = selectNumberRule(13, tokenLen);
/*  526: 526 */        break;
/*  527:     */      case 'S': 
/*  528: 528 */        rule = selectNumberRule(14, tokenLen);
/*  529: 529 */        break;
/*  530:     */      case 'E': 
/*  531: 531 */        rule = new TextField(7, tokenLen < 4 ? shortWeekdays : weekdays);
/*  532: 532 */        break;
/*  533:     */      case 'D': 
/*  534: 534 */        rule = selectNumberRule(6, tokenLen);
/*  535: 535 */        break;
/*  536:     */      case 'F': 
/*  537: 537 */        rule = selectNumberRule(8, tokenLen);
/*  538: 538 */        break;
/*  539:     */      case 'w': 
/*  540: 540 */        rule = selectNumberRule(3, tokenLen);
/*  541: 541 */        break;
/*  542:     */      case 'W': 
/*  543: 543 */        rule = selectNumberRule(4, tokenLen);
/*  544: 544 */        break;
/*  545:     */      case 'a': 
/*  546: 546 */        rule = new TextField(9, AmPmStrings);
/*  547: 547 */        break;
/*  548:     */      case 'k': 
/*  549: 549 */        rule = new TwentyFourHourField(selectNumberRule(11, tokenLen));
/*  550: 550 */        break;
/*  551:     */      case 'K': 
/*  552: 552 */        rule = selectNumberRule(10, tokenLen);
/*  553: 553 */        break;
/*  554:     */      case 'z': 
/*  555: 555 */        if (tokenLen >= 4) {
/*  556: 556 */          rule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 1);
/*  557:     */        } else {
/*  558: 558 */          rule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 0);
/*  559:     */        }
/*  560: 560 */        break;
/*  561:     */      case 'Z': 
/*  562: 562 */        if (tokenLen == 1) {
/*  563: 563 */          rule = TimeZoneNumberRule.INSTANCE_NO_COLON;
/*  564:     */        } else {
/*  565: 565 */          rule = TimeZoneNumberRule.INSTANCE_COLON;
/*  566:     */        }
/*  567: 567 */        break;
/*  568:     */      case '\'': 
/*  569: 569 */        String sub = token.substring(1);
/*  570: 570 */        if (sub.length() == 1) {
/*  571: 571 */          rule = new CharacterLiteral(sub.charAt(0));
/*  572:     */        } else {
/*  573: 573 */          rule = new StringLiteral(sub);
/*  574:     */        }
/*  575: 575 */        break;
/*  576:     */      case '(': case ')': case '*': case '+': case ',': case '-': case '.': case '/': case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': case ':': case ';': case '<': case '=': case '>': case '?': case '@': case 'A': case 'B': case 'C': case 'I': case 'J': case 'L': case 'N': case 'O': case 'P': case 'Q': case 'R': case 'T': case 'U': case 'V': case 'X': case 'Y': case '[': case '\\': case ']': case '^': case '_': case '`': case 'b': case 'c': case 'e': case 'f': case 'g': case 'i': case 'j': case 'l': case 'n': case 'o': case 'p': case 'q': case 'r': case 't': case 'u': case 'v': case 'x': default: 
/*  577: 577 */        throw new IllegalArgumentException("Illegal pattern component: " + token);
/*  578:     */      }
/*  579:     */      
/*  580: 580 */      rules.add(rule);
/*  581:     */    }
/*  582:     */    
/*  583: 583 */    return rules;
/*  584:     */  }
/*  585:     */  
/*  592:     */  protected String parseToken(String pattern, int[] indexRef)
/*  593:     */  {
/*  594: 594 */    StringBuilder buf = new StringBuilder();
/*  595:     */    
/*  596: 596 */    int i = indexRef[0];
/*  597: 597 */    int length = pattern.length();
/*  598:     */    
/*  599: 599 */    char c = pattern.charAt(i);
/*  600: 600 */    if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')))
/*  601:     */    {
/*  603: 603 */      buf.append(c);
/*  604:     */    }
/*  605: 605 */    while (i + 1 < length) {
/*  606: 606 */      char peek = pattern.charAt(i + 1);
/*  607: 607 */      if (peek == c) {
/*  608: 608 */        buf.append(c);
/*  609: 609 */        i++;
/*  610:     */        
/*  613: 613 */        continue;
/*  614:     */        
/*  616: 616 */        buf.append('\'');
/*  617:     */        
/*  618: 618 */        boolean inLiteral = false;
/*  619: 620 */        for (; 
/*  620: 620 */            i < length; i++) {
/*  621: 621 */          c = pattern.charAt(i);
/*  622:     */          
/*  623: 623 */          if (c == '\'') {
/*  624: 624 */            if ((i + 1 < length) && (pattern.charAt(i + 1) == '\''))
/*  625:     */            {
/*  626: 626 */              i++;
/*  627: 627 */              buf.append(c);
/*  628:     */            } else {
/*  629: 629 */              inLiteral = !inLiteral;
/*  630:     */            }
/*  631: 631 */          } else { if ((!inLiteral) && (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))))
/*  632:     */            {
/*  633: 633 */              i--;
/*  634: 634 */              break;
/*  635:     */            }
/*  636: 636 */            buf.append(c);
/*  637:     */          }
/*  638:     */        }
/*  639:     */      }
/*  640:     */    }
/*  641: 641 */    indexRef[0] = i;
/*  642: 642 */    return buf.toString();
/*  643:     */  }
/*  644:     */  
/*  651:     */  protected NumberRule selectNumberRule(int field, int padding)
/*  652:     */  {
/*  653: 653 */    switch (padding) {
/*  654:     */    case 1: 
/*  655: 655 */      return new UnpaddedNumberField(field);
/*  656:     */    case 2: 
/*  657: 657 */      return new TwoDigitNumberField(field);
/*  658:     */    }
/*  659: 659 */    return new PaddedNumberField(field, padding);
/*  660:     */  }
/*  661:     */  
/*  674:     */  public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos)
/*  675:     */  {
/*  676: 676 */    if ((obj instanceof Date))
/*  677: 677 */      return format((Date)obj, toAppendTo);
/*  678: 678 */    if ((obj instanceof Calendar))
/*  679: 679 */      return format((Calendar)obj, toAppendTo);
/*  680: 680 */    if ((obj instanceof Long)) {
/*  681: 681 */      return format(((Long)obj).longValue(), toAppendTo);
/*  682:     */    }
/*  683: 683 */    throw new IllegalArgumentException("Unknown class: " + (obj == null ? "<null>" : obj.getClass().getName()));
/*  684:     */  }
/*  685:     */  
/*  694:     */  public String format(long millis)
/*  695:     */  {
/*  696: 696 */    return format(new Date(millis));
/*  697:     */  }
/*  698:     */  
/*  704:     */  public String format(Date date)
/*  705:     */  {
/*  706: 706 */    Calendar c = new GregorianCalendar(this.mTimeZone, this.mLocale);
/*  707: 707 */    c.setTime(date);
/*  708: 708 */    return applyRules(c, new StringBuffer(this.mMaxLengthEstimate)).toString();
/*  709:     */  }
/*  710:     */  
/*  716:     */  public String format(Calendar calendar)
/*  717:     */  {
/*  718: 718 */    return format(calendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
/*  719:     */  }
/*  720:     */  
/*  729:     */  public StringBuffer format(long millis, StringBuffer buf)
/*  730:     */  {
/*  731: 731 */    return format(new Date(millis), buf);
/*  732:     */  }
/*  733:     */  
/*  741:     */  public StringBuffer format(Date date, StringBuffer buf)
/*  742:     */  {
/*  743: 743 */    Calendar c = new GregorianCalendar(this.mTimeZone, this.mLocale);
/*  744: 744 */    c.setTime(date);
/*  745: 745 */    return applyRules(c, buf);
/*  746:     */  }
/*  747:     */  
/*  755:     */  public StringBuffer format(Calendar calendar, StringBuffer buf)
/*  756:     */  {
/*  757: 757 */    return applyRules(calendar, buf);
/*  758:     */  }
/*  759:     */  
/*  767:     */  protected StringBuffer applyRules(Calendar calendar, StringBuffer buf)
/*  768:     */  {
/*  769: 769 */    for (Rule rule : this.mRules) {
/*  770: 770 */      rule.appendTo(buf, calendar);
/*  771:     */    }
/*  772: 772 */    return buf;
/*  773:     */  }
/*  774:     */  
/*  784:     */  public Object parseObject(String source, ParsePosition pos)
/*  785:     */  {
/*  786: 786 */    pos.setIndex(0);
/*  787: 787 */    pos.setErrorIndex(0);
/*  788: 788 */    return null;
/*  789:     */  }
/*  790:     */  
/*  797:     */  public String getPattern()
/*  798:     */  {
/*  799: 799 */    return this.mPattern;
/*  800:     */  }
/*  801:     */  
/*  808:     */  public TimeZone getTimeZone()
/*  809:     */  {
/*  810: 810 */    return this.mTimeZone;
/*  811:     */  }
/*  812:     */  
/*  817:     */  public Locale getLocale()
/*  818:     */  {
/*  819: 819 */    return this.mLocale;
/*  820:     */  }
/*  821:     */  
/*  830:     */  public int getMaxLengthEstimate()
/*  831:     */  {
/*  832: 832 */    return this.mMaxLengthEstimate;
/*  833:     */  }
/*  834:     */  
/*  843:     */  public boolean equals(Object obj)
/*  844:     */  {
/*  845: 845 */    if (!(obj instanceof FastDateFormat)) {
/*  846: 846 */      return false;
/*  847:     */    }
/*  848: 848 */    FastDateFormat other = (FastDateFormat)obj;
/*  849: 849 */    return (this.mPattern.equals(other.mPattern)) && (this.mTimeZone.equals(other.mTimeZone)) && (this.mLocale.equals(other.mLocale));
/*  850:     */  }
/*  851:     */  
/*  859:     */  public int hashCode()
/*  860:     */  {
/*  861: 861 */    return this.mPattern.hashCode() + 13 * (this.mTimeZone.hashCode() + 13 * this.mLocale.hashCode());
/*  862:     */  }
/*  863:     */  
/*  869:     */  public String toString()
/*  870:     */  {
/*  871: 871 */    return "FastDateFormat[" + this.mPattern + "]";
/*  872:     */  }
/*  873:     */  
/*  882:     */  private void readObject(ObjectInputStream in)
/*  883:     */    throws IOException, ClassNotFoundException
/*  884:     */  {
/*  885: 885 */    in.defaultReadObject();
/*  886: 886 */    init();
/*  887:     */  }
/*  888:     */  
/*  895:     */  private static abstract interface Rule
/*  896:     */  {
/*  897:     */    public abstract int estimateLength();
/*  898:     */    
/*  905:     */    public abstract void appendTo(StringBuffer paramStringBuffer, Calendar paramCalendar);
/*  906:     */  }
/*  907:     */  
/*  914:     */  private static abstract interface NumberRule
/*  915:     */    extends FastDateFormat.Rule
/*  916:     */  {
/*  917:     */    public abstract void appendTo(StringBuffer paramStringBuffer, int paramInt);
/*  918:     */  }
/*  919:     */  
/*  925:     */  private static class CharacterLiteral
/*  926:     */    implements FastDateFormat.Rule
/*  927:     */  {
/*  928:     */    private final char mValue;
/*  929:     */    
/*  935:     */    CharacterLiteral(char value)
/*  936:     */    {
/*  937: 937 */      this.mValue = value;
/*  938:     */    }
/*  939:     */    
/*  942:     */    public int estimateLength()
/*  943:     */    {
/*  944: 944 */      return 1;
/*  945:     */    }
/*  946:     */    
/*  949:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/*  950:     */    {
/*  951: 951 */      buffer.append(this.mValue);
/*  952:     */    }
/*  953:     */  }
/*  954:     */  
/*  958:     */  private static class StringLiteral
/*  959:     */    implements FastDateFormat.Rule
/*  960:     */  {
/*  961:     */    private final String mValue;
/*  962:     */    
/*  966:     */    StringLiteral(String value)
/*  967:     */    {
/*  968: 968 */      this.mValue = value;
/*  969:     */    }
/*  970:     */    
/*  973:     */    public int estimateLength()
/*  974:     */    {
/*  975: 975 */      return this.mValue.length();
/*  976:     */    }
/*  977:     */    
/*  980:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/*  981:     */    {
/*  982: 982 */      buffer.append(this.mValue);
/*  983:     */    }
/*  984:     */  }
/*  985:     */  
/*  989:     */  private static class TextField
/*  990:     */    implements FastDateFormat.Rule
/*  991:     */  {
/*  992:     */    private final int mField;
/*  993:     */    
/*  995:     */    private final String[] mValues;
/*  996:     */    
/*  999:     */    TextField(int field, String[] values)
/* 1000:     */    {
/* 1001:1001 */      this.mField = field;
/* 1002:1002 */      this.mValues = values;
/* 1003:     */    }
/* 1004:     */    
/* 1007:     */    public int estimateLength()
/* 1008:     */    {
/* 1009:1009 */      int max = 0;
/* 1010:1010 */      int i = this.mValues.length; for (;;) { i--; if (i < 0) break;
/* 1011:1011 */        int len = this.mValues[i].length();
/* 1012:1012 */        if (len > max) {
/* 1013:1013 */          max = len;
/* 1014:     */        }
/* 1015:     */      }
/* 1016:1016 */      return max;
/* 1017:     */    }
/* 1018:     */    
/* 1021:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/* 1022:     */    {
/* 1023:1023 */      buffer.append(this.mValues[calendar.get(this.mField)]);
/* 1024:     */    }
/* 1025:     */  }
/* 1026:     */  
/* 1030:     */  private static class UnpaddedNumberField
/* 1031:     */    implements FastDateFormat.NumberRule
/* 1032:     */  {
/* 1033:     */    private final int mField;
/* 1034:     */    
/* 1037:     */    UnpaddedNumberField(int field)
/* 1038:     */    {
/* 1039:1039 */      this.mField = field;
/* 1040:     */    }
/* 1041:     */    
/* 1044:     */    public int estimateLength()
/* 1045:     */    {
/* 1046:1046 */      return 4;
/* 1047:     */    }
/* 1048:     */    
/* 1051:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/* 1052:     */    {
/* 1053:1053 */      appendTo(buffer, calendar.get(this.mField));
/* 1054:     */    }
/* 1055:     */    
/* 1058:     */    public final void appendTo(StringBuffer buffer, int value)
/* 1059:     */    {
/* 1060:1060 */      if (value < 10) {
/* 1061:1061 */        buffer.append((char)(value + 48));
/* 1062:1062 */      } else if (value < 100) {
/* 1063:1063 */        buffer.append((char)(value / 10 + 48));
/* 1064:1064 */        buffer.append((char)(value % 10 + 48));
/* 1065:     */      } else {
/* 1066:1066 */        buffer.append(Integer.toString(value));
/* 1067:     */      }
/* 1068:     */    }
/* 1069:     */  }
/* 1070:     */  
/* 1072:     */  private static class UnpaddedMonthField
/* 1073:     */    implements FastDateFormat.NumberRule
/* 1074:     */  {
/* 1075:1075 */    static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();
/* 1076:     */    
/* 1087:     */    public int estimateLength()
/* 1088:     */    {
/* 1089:1089 */      return 2;
/* 1090:     */    }
/* 1091:     */    
/* 1094:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/* 1095:     */    {
/* 1096:1096 */      appendTo(buffer, calendar.get(2) + 1);
/* 1097:     */    }
/* 1098:     */    
/* 1101:     */    public final void appendTo(StringBuffer buffer, int value)
/* 1102:     */    {
/* 1103:1103 */      if (value < 10) {
/* 1104:1104 */        buffer.append((char)(value + 48));
/* 1105:     */      } else {
/* 1106:1106 */        buffer.append((char)(value / 10 + 48));
/* 1107:1107 */        buffer.append((char)(value % 10 + 48));
/* 1108:     */      }
/* 1109:     */    }
/* 1110:     */  }
/* 1111:     */  
/* 1114:     */  private static class PaddedNumberField
/* 1115:     */    implements FastDateFormat.NumberRule
/* 1116:     */  {
/* 1117:     */    private final int mField;
/* 1118:     */    
/* 1120:     */    private final int mSize;
/* 1121:     */    
/* 1124:     */    PaddedNumberField(int field, int size)
/* 1125:     */    {
/* 1126:1126 */      if (size < 3)
/* 1127:     */      {
/* 1128:1128 */        throw new IllegalArgumentException();
/* 1129:     */      }
/* 1130:1130 */      this.mField = field;
/* 1131:1131 */      this.mSize = size;
/* 1132:     */    }
/* 1133:     */    
/* 1136:     */    public int estimateLength()
/* 1137:     */    {
/* 1138:1138 */      return 4;
/* 1139:     */    }
/* 1140:     */    
/* 1143:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/* 1144:     */    {
/* 1145:1145 */      appendTo(buffer, calendar.get(this.mField));
/* 1146:     */    }
/* 1147:     */    
/* 1150:     */    public final void appendTo(StringBuffer buffer, int value)
/* 1151:     */    {
/* 1152:1152 */      if (value < 100) {
/* 1153:1153 */        int i = this.mSize; for (;;) { i--; if (i < 2) break;
/* 1154:1154 */          buffer.append('0');
/* 1155:     */        }
/* 1156:1156 */        buffer.append((char)(value / 10 + 48));
/* 1157:1157 */        buffer.append((char)(value % 10 + 48));
/* 1158:     */      } else { int digits;
/* 1159:     */        int digits;
/* 1160:1160 */        if (value < 1000) {
/* 1161:1161 */          digits = 3;
/* 1162:     */        } else {
/* 1163:1163 */          Validate.isTrue(value > -1, "Negative values should not be possible", value);
/* 1164:1164 */          digits = Integer.toString(value).length();
/* 1165:     */        }
/* 1166:1166 */        int i = this.mSize; for (;;) { i--; if (i < digits) break;
/* 1167:1167 */          buffer.append('0');
/* 1168:     */        }
/* 1169:1169 */        buffer.append(Integer.toString(value));
/* 1170:     */      }
/* 1171:     */    }
/* 1172:     */  }
/* 1173:     */  
/* 1177:     */  private static class TwoDigitNumberField
/* 1178:     */    implements FastDateFormat.NumberRule
/* 1179:     */  {
/* 1180:     */    private final int mField;
/* 1181:     */    
/* 1184:     */    TwoDigitNumberField(int field)
/* 1185:     */    {
/* 1186:1186 */      this.mField = field;
/* 1187:     */    }
/* 1188:     */    
/* 1191:     */    public int estimateLength()
/* 1192:     */    {
/* 1193:1193 */      return 2;
/* 1194:     */    }
/* 1195:     */    
/* 1198:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/* 1199:     */    {
/* 1200:1200 */      appendTo(buffer, calendar.get(this.mField));
/* 1201:     */    }
/* 1202:     */    
/* 1205:     */    public final void appendTo(StringBuffer buffer, int value)
/* 1206:     */    {
/* 1207:1207 */      if (value < 100) {
/* 1208:1208 */        buffer.append((char)(value / 10 + 48));
/* 1209:1209 */        buffer.append((char)(value % 10 + 48));
/* 1210:     */      } else {
/* 1211:1211 */        buffer.append(Integer.toString(value));
/* 1212:     */      }
/* 1213:     */    }
/* 1214:     */  }
/* 1215:     */  
/* 1217:     */  private static class TwoDigitYearField
/* 1218:     */    implements FastDateFormat.NumberRule
/* 1219:     */  {
/* 1220:1220 */    static final TwoDigitYearField INSTANCE = new TwoDigitYearField();
/* 1221:     */    
/* 1231:     */    public int estimateLength()
/* 1232:     */    {
/* 1233:1233 */      return 2;
/* 1234:     */    }
/* 1235:     */    
/* 1238:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/* 1239:     */    {
/* 1240:1240 */      appendTo(buffer, calendar.get(1) % 100);
/* 1241:     */    }
/* 1242:     */    
/* 1245:     */    public final void appendTo(StringBuffer buffer, int value)
/* 1246:     */    {
/* 1247:1247 */      buffer.append((char)(value / 10 + 48));
/* 1248:1248 */      buffer.append((char)(value % 10 + 48));
/* 1249:     */    }
/* 1250:     */  }
/* 1251:     */  
/* 1253:     */  private static class TwoDigitMonthField
/* 1254:     */    implements FastDateFormat.NumberRule
/* 1255:     */  {
/* 1256:1256 */    static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();
/* 1257:     */    
/* 1267:     */    public int estimateLength()
/* 1268:     */    {
/* 1269:1269 */      return 2;
/* 1270:     */    }
/* 1271:     */    
/* 1274:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/* 1275:     */    {
/* 1276:1276 */      appendTo(buffer, calendar.get(2) + 1);
/* 1277:     */    }
/* 1278:     */    
/* 1281:     */    public final void appendTo(StringBuffer buffer, int value)
/* 1282:     */    {
/* 1283:1283 */      buffer.append((char)(value / 10 + 48));
/* 1284:1284 */      buffer.append((char)(value % 10 + 48));
/* 1285:     */    }
/* 1286:     */  }
/* 1287:     */  
/* 1291:     */  private static class TwelveHourField
/* 1292:     */    implements FastDateFormat.NumberRule
/* 1293:     */  {
/* 1294:     */    private final FastDateFormat.NumberRule mRule;
/* 1295:     */    
/* 1299:     */    TwelveHourField(FastDateFormat.NumberRule rule)
/* 1300:     */    {
/* 1301:1301 */      this.mRule = rule;
/* 1302:     */    }
/* 1303:     */    
/* 1306:     */    public int estimateLength()
/* 1307:     */    {
/* 1308:1308 */      return this.mRule.estimateLength();
/* 1309:     */    }
/* 1310:     */    
/* 1313:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/* 1314:     */    {
/* 1315:1315 */      int value = calendar.get(10);
/* 1316:1316 */      if (value == 0) {
/* 1317:1317 */        value = calendar.getLeastMaximum(10) + 1;
/* 1318:     */      }
/* 1319:1319 */      this.mRule.appendTo(buffer, value);
/* 1320:     */    }
/* 1321:     */    
/* 1324:     */    public void appendTo(StringBuffer buffer, int value)
/* 1325:     */    {
/* 1326:1326 */      this.mRule.appendTo(buffer, value);
/* 1327:     */    }
/* 1328:     */  }
/* 1329:     */  
/* 1333:     */  private static class TwentyFourHourField
/* 1334:     */    implements FastDateFormat.NumberRule
/* 1335:     */  {
/* 1336:     */    private final FastDateFormat.NumberRule mRule;
/* 1337:     */    
/* 1341:     */    TwentyFourHourField(FastDateFormat.NumberRule rule)
/* 1342:     */    {
/* 1343:1343 */      this.mRule = rule;
/* 1344:     */    }
/* 1345:     */    
/* 1348:     */    public int estimateLength()
/* 1349:     */    {
/* 1350:1350 */      return this.mRule.estimateLength();
/* 1351:     */    }
/* 1352:     */    
/* 1355:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/* 1356:     */    {
/* 1357:1357 */      int value = calendar.get(11);
/* 1358:1358 */      if (value == 0) {
/* 1359:1359 */        value = calendar.getMaximum(11) + 1;
/* 1360:     */      }
/* 1361:1361 */      this.mRule.appendTo(buffer, value);
/* 1362:     */    }
/* 1363:     */    
/* 1366:     */    public void appendTo(StringBuffer buffer, int value)
/* 1367:     */    {
/* 1368:1368 */      this.mRule.appendTo(buffer, value);
/* 1369:     */    }
/* 1370:     */  }
/* 1371:     */  
/* 1374:     */  private static class TimeZoneNameRule
/* 1375:     */    implements FastDateFormat.Rule
/* 1376:     */  {
/* 1377:     */    private final TimeZone mTimeZone;
/* 1378:     */    
/* 1380:     */    private final String mStandard;
/* 1381:     */    
/* 1383:     */    private final String mDaylight;
/* 1384:     */    
/* 1386:     */    TimeZoneNameRule(TimeZone timeZone, Locale locale, int style)
/* 1387:     */    {
/* 1388:1388 */      this.mTimeZone = timeZone;
/* 1389:     */      
/* 1390:1390 */      this.mStandard = FastDateFormat.getTimeZoneDisplay(timeZone, false, style, locale);
/* 1391:1391 */      this.mDaylight = FastDateFormat.getTimeZoneDisplay(timeZone, true, style, locale);
/* 1392:     */    }
/* 1393:     */    
/* 1396:     */    public int estimateLength()
/* 1397:     */    {
/* 1398:1398 */      return Math.max(this.mStandard.length(), this.mDaylight.length());
/* 1399:     */    }
/* 1400:     */    
/* 1403:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/* 1404:     */    {
/* 1405:1405 */      if ((this.mTimeZone.useDaylightTime()) && (calendar.get(16) != 0)) {
/* 1406:1406 */        buffer.append(this.mDaylight);
/* 1407:     */      } else {
/* 1408:1408 */        buffer.append(this.mStandard);
/* 1409:     */      }
/* 1410:     */    }
/* 1411:     */  }
/* 1412:     */  
/* 1415:     */  private static class TimeZoneNumberRule
/* 1416:     */    implements FastDateFormat.Rule
/* 1417:     */  {
/* 1418:1418 */    static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);
/* 1419:1419 */    static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
/* 1420:     */    
/* 1423:     */    final boolean mColon;
/* 1424:     */    
/* 1427:     */    TimeZoneNumberRule(boolean colon)
/* 1428:     */    {
/* 1429:1429 */      this.mColon = colon;
/* 1430:     */    }
/* 1431:     */    
/* 1434:     */    public int estimateLength()
/* 1435:     */    {
/* 1436:1436 */      return 5;
/* 1437:     */    }
/* 1438:     */    
/* 1441:     */    public void appendTo(StringBuffer buffer, Calendar calendar)
/* 1442:     */    {
/* 1443:1443 */      int offset = calendar.get(15) + calendar.get(16);
/* 1444:     */      
/* 1445:1445 */      if (offset < 0) {
/* 1446:1446 */        buffer.append('-');
/* 1447:1447 */        offset = -offset;
/* 1448:     */      } else {
/* 1449:1449 */        buffer.append('+');
/* 1450:     */      }
/* 1451:     */      
/* 1452:1452 */      int hours = offset / 3600000;
/* 1453:1453 */      buffer.append((char)(hours / 10 + 48));
/* 1454:1454 */      buffer.append((char)(hours % 10 + 48));
/* 1455:     */      
/* 1456:1456 */      if (this.mColon) {
/* 1457:1457 */        buffer.append(':');
/* 1458:     */      }
/* 1459:     */      
/* 1460:1460 */      int minutes = offset / 60000 - 60 * hours;
/* 1461:1461 */      buffer.append((char)(minutes / 10 + 48));
/* 1462:1462 */      buffer.append((char)(minutes % 10 + 48));
/* 1463:     */    }
/* 1464:     */  }
/* 1465:     */  
/* 1469:     */  private static class TimeZoneDisplayKey
/* 1470:     */  {
/* 1471:     */    private final TimeZone mTimeZone;
/* 1472:     */    
/* 1475:     */    private final int mStyle;
/* 1476:     */    
/* 1479:     */    private final Locale mLocale;
/* 1480:     */    
/* 1483:     */    TimeZoneDisplayKey(TimeZone timeZone, boolean daylight, int style, Locale locale)
/* 1484:     */    {
/* 1485:1485 */      this.mTimeZone = timeZone;
/* 1486:1486 */      if (daylight) {
/* 1487:1487 */        style |= -2147483648;
/* 1488:     */      }
/* 1489:1489 */      this.mStyle = style;
/* 1490:1490 */      this.mLocale = locale;
/* 1491:     */    }
/* 1492:     */    
/* 1496:     */    public int hashCode()
/* 1497:     */    {
/* 1498:1498 */      return (this.mStyle * 31 + this.mLocale.hashCode()) * 31 + this.mTimeZone.hashCode();
/* 1499:     */    }
/* 1500:     */    
/* 1504:     */    public boolean equals(Object obj)
/* 1505:     */    {
/* 1506:1506 */      if (this == obj) {
/* 1507:1507 */        return true;
/* 1508:     */      }
/* 1509:1509 */      if ((obj instanceof TimeZoneDisplayKey)) {
/* 1510:1510 */        TimeZoneDisplayKey other = (TimeZoneDisplayKey)obj;
/* 1511:1511 */        return (this.mTimeZone.equals(other.mTimeZone)) && (this.mStyle == other.mStyle) && (this.mLocale.equals(other.mLocale));
/* 1512:     */      }
/* 1513:     */      
/* 1516:1516 */      return false;
/* 1517:     */    }
/* 1518:     */  }
/* 1519:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.time.FastDateFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */