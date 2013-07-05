/*      */ package org.apache.commons.lang3.time;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.text.DateFormatSymbols;
/*      */ import java.text.FieldPosition;
/*      */ import java.text.Format;
/*      */ import java.text.ParsePosition;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.TimeZone;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import java.util.concurrent.ConcurrentMap;
/*      */ import org.apache.commons.lang3.Validate;
/*      */ 
/*      */ public class FastDateFormat extends Format
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   public static final int FULL = 0;
/*      */   public static final int LONG = 1;
/*      */   public static final int MEDIUM = 2;
/*      */   public static final int SHORT = 3;
/*  107 */   private static final FormatCache<FastDateFormat> cache = new FormatCache()
/*      */   {
/*      */     protected FastDateFormat createInstance(String pattern, TimeZone timeZone, Locale locale) {
/*  110 */       return new FastDateFormat(pattern, timeZone, locale);
/*      */     }
/*  107 */   };
/*      */ 
/*  114 */   private static ConcurrentMap<TimeZoneDisplayKey, String> cTimeZoneDisplayCache = new ConcurrentHashMap(7);
/*      */   private final String mPattern;
/*      */   private final TimeZone mTimeZone;
/*      */   private final Locale mLocale;
/*      */   private transient Rule[] mRules;
/*      */   private transient int mMaxLengthEstimate;
/*      */ 
/*      */   public static FastDateFormat getInstance()
/*      */   {
/*  146 */     return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(3), Integer.valueOf(3), null, null);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getInstance(String pattern)
/*      */   {
/*  159 */     return (FastDateFormat)cache.getInstance(pattern, null, null);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getInstance(String pattern, TimeZone timeZone)
/*      */   {
/*  174 */     return (FastDateFormat)cache.getInstance(pattern, timeZone, null);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getInstance(String pattern, Locale locale)
/*      */   {
/*  188 */     return (FastDateFormat)cache.getInstance(pattern, null, locale);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getInstance(String pattern, TimeZone timeZone, Locale locale)
/*      */   {
/*  205 */     return (FastDateFormat)cache.getInstance(pattern, timeZone, locale);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getDateInstance(int style)
/*      */   {
/*  220 */     return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, null, null);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getDateInstance(int style, Locale locale)
/*      */   {
/*  235 */     return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, null, locale);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getDateInstance(int style, TimeZone timeZone)
/*      */   {
/*  251 */     return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, timeZone, null);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getDateInstance(int style, TimeZone timeZone, Locale locale)
/*      */   {
/*  267 */     return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, timeZone, locale);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getTimeInstance(int style)
/*      */   {
/*  282 */     return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), null, null);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getTimeInstance(int style, Locale locale)
/*      */   {
/*  297 */     return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), null, locale);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getTimeInstance(int style, TimeZone timeZone)
/*      */   {
/*  313 */     return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), timeZone, null);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getTimeInstance(int style, TimeZone timeZone, Locale locale)
/*      */   {
/*  329 */     return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), timeZone, locale);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle)
/*      */   {
/*  345 */     return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), null, null);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, Locale locale)
/*      */   {
/*  361 */     return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), null, locale);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone)
/*      */   {
/*  378 */     return getDateTimeInstance(dateStyle, timeStyle, timeZone, null);
/*      */   }
/*      */ 
/*      */   public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone, Locale locale)
/*      */   {
/*  395 */     return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), timeZone, locale);
/*      */   }
/*      */ 
/*      */   static String getTimeZoneDisplay(TimeZone tz, boolean daylight, int style, Locale locale)
/*      */   {
/*  409 */     TimeZoneDisplayKey key = new TimeZoneDisplayKey(tz, daylight, style, locale);
/*  410 */     String value = (String)cTimeZoneDisplayCache.get(key);
/*  411 */     if (value == null)
/*      */     {
/*  413 */       value = tz.getDisplayName(daylight, style, locale);
/*  414 */       String prior = (String)cTimeZoneDisplayCache.putIfAbsent(key, value);
/*  415 */       if (prior != null) {
/*  416 */         value = prior;
/*      */       }
/*      */     }
/*  419 */     return value;
/*      */   }
/*      */ 
/*      */   protected FastDateFormat(String pattern, TimeZone timeZone, Locale locale)
/*      */   {
/*  433 */     this.mPattern = pattern;
/*  434 */     this.mTimeZone = timeZone;
/*  435 */     this.mLocale = locale;
/*      */ 
/*  437 */     init();
/*      */   }
/*      */ 
/*      */   private void init()
/*      */   {
/*  444 */     List rulesList = parsePattern();
/*  445 */     this.mRules = ((Rule[])rulesList.toArray(new Rule[rulesList.size()]));
/*      */ 
/*  447 */     int len = 0;
/*  448 */     int i = this.mRules.length;
/*      */     while (true) { i--; if (i < 0) break;
/*  449 */       len += this.mRules[i].estimateLength();
/*      */     }
/*      */ 
/*  452 */     this.mMaxLengthEstimate = len;
/*      */   }
/*      */ 
/*      */   protected List<Rule> parsePattern()
/*      */   {
/*  464 */     DateFormatSymbols symbols = new DateFormatSymbols(this.mLocale);
/*  465 */     List rules = new ArrayList();
/*      */ 
/*  467 */     String[] ERAs = symbols.getEras();
/*  468 */     String[] months = symbols.getMonths();
/*  469 */     String[] shortMonths = symbols.getShortMonths();
/*  470 */     String[] weekdays = symbols.getWeekdays();
/*  471 */     String[] shortWeekdays = symbols.getShortWeekdays();
/*  472 */     String[] AmPmStrings = symbols.getAmPmStrings();
/*      */ 
/*  474 */     int length = this.mPattern.length();
/*  475 */     int[] indexRef = new int[1];
/*      */ 
/*  477 */     for (int i = 0; i < length; i++) {
/*  478 */       indexRef[0] = i;
/*  479 */       String token = parseToken(this.mPattern, indexRef);
/*  480 */       i = indexRef[0];
/*      */ 
/*  482 */       int tokenLen = token.length();
/*  483 */       if (tokenLen == 0)
/*      */       {
/*      */         break;
/*      */       }
/*      */ 
/*  488 */       char c = token.charAt(0);
/*      */       Rule rule;
/*      */       Rule rule;
/*      */       Rule rule;
/*      */       Rule rule;
/*      */       Rule rule;
/*      */       Rule rule;
/*  490 */       switch (c) {
/*      */       case 'G':
/*  492 */         rule = new TextField(0, ERAs);
/*  493 */         break;
/*      */       case 'y':
/*  495 */         if (tokenLen == 2)
/*  496 */           rule = TwoDigitYearField.INSTANCE;
/*      */         else {
/*  498 */           rule = selectNumberRule(1, tokenLen < 4 ? 4 : tokenLen);
/*      */         }
/*  500 */         break;
/*      */       case 'M':
/*  502 */         if (tokenLen >= 4) {
/*  503 */           rule = new TextField(2, months);
/*      */         }
/*      */         else
/*      */         {
/*      */           Rule rule;
/*  504 */           if (tokenLen == 3) {
/*  505 */             rule = new TextField(2, shortMonths);
/*      */           }
/*      */           else
/*      */           {
/*      */             Rule rule;
/*  506 */             if (tokenLen == 2)
/*  507 */               rule = TwoDigitMonthField.INSTANCE;
/*      */             else
/*  509 */               rule = UnpaddedMonthField.INSTANCE; 
/*      */           }
/*      */         }
/*  511 */         break;
/*      */       case 'd':
/*  513 */         rule = selectNumberRule(5, tokenLen);
/*  514 */         break;
/*      */       case 'h':
/*  516 */         rule = new TwelveHourField(selectNumberRule(10, tokenLen));
/*  517 */         break;
/*      */       case 'H':
/*  519 */         rule = selectNumberRule(11, tokenLen);
/*  520 */         break;
/*      */       case 'm':
/*  522 */         rule = selectNumberRule(12, tokenLen);
/*  523 */         break;
/*      */       case 's':
/*  525 */         rule = selectNumberRule(13, tokenLen);
/*  526 */         break;
/*      */       case 'S':
/*  528 */         rule = selectNumberRule(14, tokenLen);
/*  529 */         break;
/*      */       case 'E':
/*  531 */         rule = new TextField(7, tokenLen < 4 ? shortWeekdays : weekdays);
/*  532 */         break;
/*      */       case 'D':
/*  534 */         rule = selectNumberRule(6, tokenLen);
/*  535 */         break;
/*      */       case 'F':
/*  537 */         rule = selectNumberRule(8, tokenLen);
/*  538 */         break;
/*      */       case 'w':
/*  540 */         rule = selectNumberRule(3, tokenLen);
/*  541 */         break;
/*      */       case 'W':
/*  543 */         rule = selectNumberRule(4, tokenLen);
/*  544 */         break;
/*      */       case 'a':
/*  546 */         rule = new TextField(9, AmPmStrings);
/*  547 */         break;
/*      */       case 'k':
/*  549 */         rule = new TwentyFourHourField(selectNumberRule(11, tokenLen));
/*  550 */         break;
/*      */       case 'K':
/*  552 */         rule = selectNumberRule(10, tokenLen);
/*  553 */         break;
/*      */       case 'z':
/*  555 */         if (tokenLen >= 4)
/*  556 */           rule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 1);
/*      */         else {
/*  558 */           rule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 0);
/*      */         }
/*  560 */         break;
/*      */       case 'Z':
/*  562 */         if (tokenLen == 1)
/*  563 */           rule = TimeZoneNumberRule.INSTANCE_NO_COLON;
/*      */         else {
/*  565 */           rule = TimeZoneNumberRule.INSTANCE_COLON;
/*      */         }
/*  567 */         break;
/*      */       case '\'':
/*  569 */         String sub = token.substring(1);
/*  570 */         if (sub.length() == 1)
/*  571 */           rule = new CharacterLiteral(sub.charAt(0));
/*      */         else {
/*  573 */           rule = new StringLiteral(sub);
/*      */         }
/*  575 */         break;
/*      */       case '(':
/*      */       case ')':
/*      */       case '*':
/*      */       case '+':
/*      */       case ',':
/*      */       case '-':
/*      */       case '.':
/*      */       case '/':
/*      */       case '0':
/*      */       case '1':
/*      */       case '2':
/*      */       case '3':
/*      */       case '4':
/*      */       case '5':
/*      */       case '6':
/*      */       case '7':
/*      */       case '8':
/*      */       case '9':
/*      */       case ':':
/*      */       case ';':
/*      */       case '<':
/*      */       case '=':
/*      */       case '>':
/*      */       case '?':
/*      */       case '@':
/*      */       case 'A':
/*      */       case 'B':
/*      */       case 'C':
/*      */       case 'I':
/*      */       case 'J':
/*      */       case 'L':
/*      */       case 'N':
/*      */       case 'O':
/*      */       case 'P':
/*      */       case 'Q':
/*      */       case 'R':
/*      */       case 'T':
/*      */       case 'U':
/*      */       case 'V':
/*      */       case 'X':
/*      */       case 'Y':
/*      */       case '[':
/*      */       case '\\':
/*      */       case ']':
/*      */       case '^':
/*      */       case '_':
/*      */       case '`':
/*      */       case 'b':
/*      */       case 'c':
/*      */       case 'e':
/*      */       case 'f':
/*      */       case 'g':
/*      */       case 'i':
/*      */       case 'j':
/*      */       case 'l':
/*      */       case 'n':
/*      */       case 'o':
/*      */       case 'p':
/*      */       case 'q':
/*      */       case 'r':
/*      */       case 't':
/*      */       case 'u':
/*      */       case 'v':
/*      */       case 'x':
/*      */       default:
/*  577 */         throw new IllegalArgumentException("Illegal pattern component: " + token);
/*      */       }
/*      */ 
/*  580 */       rules.add(rule);
/*      */     }
/*      */ 
/*  583 */     return rules;
/*      */   }
/*      */ 
/*      */   protected String parseToken(String pattern, int[] indexRef)
/*      */   {
/*  594 */     StringBuilder buf = new StringBuilder();
/*      */ 
/*  596 */     int i = indexRef[0];
/*  597 */     int length = pattern.length();
/*      */ 
/*  599 */     char c = pattern.charAt(i);
/*  600 */     if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')))
/*      */     {
/*  603 */       buf.append(c);
/*      */     }
/*  605 */     while (i + 1 < length) {
/*  606 */       char peek = pattern.charAt(i + 1);
/*  607 */       if (peek == c) {
/*  608 */         buf.append(c);
/*  609 */         i++;
/*      */ 
/*  613 */         continue;
/*      */ 
/*  616 */         buf.append('\'');
/*      */ 
/*  618 */         boolean inLiteral = false;
/*      */ 
/*  620 */         for (; i < length; i++) {
/*  621 */           c = pattern.charAt(i);
/*      */ 
/*  623 */           if (c == '\'') {
/*  624 */             if ((i + 1 < length) && (pattern.charAt(i + 1) == '\''))
/*      */             {
/*  626 */               i++;
/*  627 */               buf.append(c);
/*      */             } else {
/*  629 */               inLiteral = !inLiteral;
/*      */             }
/*      */           } else { if ((!inLiteral) && (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))))
/*      */             {
/*  633 */               i--;
/*  634 */               break;
/*      */             }
/*  636 */             buf.append(c);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  641 */     indexRef[0] = i;
/*  642 */     return buf.toString();
/*      */   }
/*      */ 
/*      */   protected NumberRule selectNumberRule(int field, int padding)
/*      */   {
/*  653 */     switch (padding) {
/*      */     case 1:
/*  655 */       return new UnpaddedNumberField(field);
/*      */     case 2:
/*  657 */       return new TwoDigitNumberField(field);
/*      */     }
/*  659 */     return new PaddedNumberField(field, padding);
/*      */   }
/*      */ 
/*      */   public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos)
/*      */   {
/*  676 */     if ((obj instanceof Date))
/*  677 */       return format((Date)obj, toAppendTo);
/*  678 */     if ((obj instanceof Calendar))
/*  679 */       return format((Calendar)obj, toAppendTo);
/*  680 */     if ((obj instanceof Long)) {
/*  681 */       return format(((Long)obj).longValue(), toAppendTo);
/*      */     }
/*  683 */     throw new IllegalArgumentException("Unknown class: " + (obj == null ? "<null>" : obj.getClass().getName()));
/*      */   }
/*      */ 
/*      */   public String format(long millis)
/*      */   {
/*  696 */     return format(new Date(millis));
/*      */   }
/*      */ 
/*      */   public String format(Date date)
/*      */   {
/*  706 */     Calendar c = new GregorianCalendar(this.mTimeZone, this.mLocale);
/*  707 */     c.setTime(date);
/*  708 */     return applyRules(c, new StringBuffer(this.mMaxLengthEstimate)).toString();
/*      */   }
/*      */ 
/*      */   public String format(Calendar calendar)
/*      */   {
/*  718 */     return format(calendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
/*      */   }
/*      */ 
/*      */   public StringBuffer format(long millis, StringBuffer buf)
/*      */   {
/*  731 */     return format(new Date(millis), buf);
/*      */   }
/*      */ 
/*      */   public StringBuffer format(Date date, StringBuffer buf)
/*      */   {
/*  743 */     Calendar c = new GregorianCalendar(this.mTimeZone, this.mLocale);
/*  744 */     c.setTime(date);
/*  745 */     return applyRules(c, buf);
/*      */   }
/*      */ 
/*      */   public StringBuffer format(Calendar calendar, StringBuffer buf)
/*      */   {
/*  757 */     return applyRules(calendar, buf);
/*      */   }
/*      */ 
/*      */   protected StringBuffer applyRules(Calendar calendar, StringBuffer buf)
/*      */   {
/*  769 */     for (Rule rule : this.mRules) {
/*  770 */       rule.appendTo(buf, calendar);
/*      */     }
/*  772 */     return buf;
/*      */   }
/*      */ 
/*      */   public Object parseObject(String source, ParsePosition pos)
/*      */   {
/*  786 */     pos.setIndex(0);
/*  787 */     pos.setErrorIndex(0);
/*  788 */     return null;
/*      */   }
/*      */ 
/*      */   public String getPattern()
/*      */   {
/*  799 */     return this.mPattern;
/*      */   }
/*      */ 
/*      */   public TimeZone getTimeZone()
/*      */   {
/*  810 */     return this.mTimeZone;
/*      */   }
/*      */ 
/*      */   public Locale getLocale()
/*      */   {
/*  819 */     return this.mLocale;
/*      */   }
/*      */ 
/*      */   public int getMaxLengthEstimate()
/*      */   {
/*  832 */     return this.mMaxLengthEstimate;
/*      */   }
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/*  845 */     if (!(obj instanceof FastDateFormat)) {
/*  846 */       return false;
/*      */     }
/*  848 */     FastDateFormat other = (FastDateFormat)obj;
/*  849 */     return (this.mPattern.equals(other.mPattern)) && (this.mTimeZone.equals(other.mTimeZone)) && (this.mLocale.equals(other.mLocale));
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  861 */     return this.mPattern.hashCode() + 13 * (this.mTimeZone.hashCode() + 13 * this.mLocale.hashCode());
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/*  871 */     return "FastDateFormat[" + this.mPattern + "]";
/*      */   }
/*      */ 
/*      */   private void readObject(ObjectInputStream in)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/*  885 */     in.defaultReadObject();
/*  886 */     init();
/*      */   }
/*      */ 
/*      */   private static class TimeZoneDisplayKey
/*      */   {
/*      */     private final TimeZone mTimeZone;
/*      */     private final int mStyle;
/*      */     private final Locale mLocale;
/*      */ 
/*      */     TimeZoneDisplayKey(TimeZone timeZone, boolean daylight, int style, Locale locale)
/*      */     {
/* 1485 */       this.mTimeZone = timeZone;
/* 1486 */       if (daylight) {
/* 1487 */         style |= -2147483648;
/*      */       }
/* 1489 */       this.mStyle = style;
/* 1490 */       this.mLocale = locale;
/*      */     }
/*      */ 
/*      */     public int hashCode()
/*      */     {
/* 1498 */       return (this.mStyle * 31 + this.mLocale.hashCode()) * 31 + this.mTimeZone.hashCode();
/*      */     }
/*      */ 
/*      */     public boolean equals(Object obj)
/*      */     {
/* 1506 */       if (this == obj) {
/* 1507 */         return true;
/*      */       }
/* 1509 */       if ((obj instanceof TimeZoneDisplayKey)) {
/* 1510 */         TimeZoneDisplayKey other = (TimeZoneDisplayKey)obj;
/* 1511 */         return (this.mTimeZone.equals(other.mTimeZone)) && (this.mStyle == other.mStyle) && (this.mLocale.equals(other.mLocale));
/*      */       }
/*      */ 
/* 1516 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class TimeZoneNumberRule
/*      */     implements FastDateFormat.Rule
/*      */   {
/* 1418 */     static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);
/* 1419 */     static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
/*      */     final boolean mColon;
/*      */ 
/*      */     TimeZoneNumberRule(boolean colon)
/*      */     {
/* 1429 */       this.mColon = colon;
/*      */     }
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/* 1436 */       return 5;
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/* 1443 */       int offset = calendar.get(15) + calendar.get(16);
/*      */ 
/* 1445 */       if (offset < 0) {
/* 1446 */         buffer.append('-');
/* 1447 */         offset = -offset;
/*      */       } else {
/* 1449 */         buffer.append('+');
/*      */       }
/*      */ 
/* 1452 */       int hours = offset / 3600000;
/* 1453 */       buffer.append((char)(hours / 10 + 48));
/* 1454 */       buffer.append((char)(hours % 10 + 48));
/*      */ 
/* 1456 */       if (this.mColon) {
/* 1457 */         buffer.append(':');
/*      */       }
/*      */ 
/* 1460 */       int minutes = offset / 60000 - 60 * hours;
/* 1461 */       buffer.append((char)(minutes / 10 + 48));
/* 1462 */       buffer.append((char)(minutes % 10 + 48));
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class TimeZoneNameRule
/*      */     implements FastDateFormat.Rule
/*      */   {
/*      */     private final TimeZone mTimeZone;
/*      */     private final String mStandard;
/*      */     private final String mDaylight;
/*      */ 
/*      */     TimeZoneNameRule(TimeZone timeZone, Locale locale, int style)
/*      */     {
/* 1388 */       this.mTimeZone = timeZone;
/*      */ 
/* 1390 */       this.mStandard = FastDateFormat.getTimeZoneDisplay(timeZone, false, style, locale);
/* 1391 */       this.mDaylight = FastDateFormat.getTimeZoneDisplay(timeZone, true, style, locale);
/*      */     }
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/* 1398 */       return Math.max(this.mStandard.length(), this.mDaylight.length());
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/* 1405 */       if ((this.mTimeZone.useDaylightTime()) && (calendar.get(16) != 0))
/* 1406 */         buffer.append(this.mDaylight);
/*      */       else
/* 1408 */         buffer.append(this.mStandard);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class TwentyFourHourField
/*      */     implements FastDateFormat.NumberRule
/*      */   {
/*      */     private final FastDateFormat.NumberRule mRule;
/*      */ 
/*      */     TwentyFourHourField(FastDateFormat.NumberRule rule)
/*      */     {
/* 1343 */       this.mRule = rule;
/*      */     }
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/* 1350 */       return this.mRule.estimateLength();
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/* 1357 */       int value = calendar.get(11);
/* 1358 */       if (value == 0) {
/* 1359 */         value = calendar.getMaximum(11) + 1;
/*      */       }
/* 1361 */       this.mRule.appendTo(buffer, value);
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, int value)
/*      */     {
/* 1368 */       this.mRule.appendTo(buffer, value);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class TwelveHourField
/*      */     implements FastDateFormat.NumberRule
/*      */   {
/*      */     private final FastDateFormat.NumberRule mRule;
/*      */ 
/*      */     TwelveHourField(FastDateFormat.NumberRule rule)
/*      */     {
/* 1301 */       this.mRule = rule;
/*      */     }
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/* 1308 */       return this.mRule.estimateLength();
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/* 1315 */       int value = calendar.get(10);
/* 1316 */       if (value == 0) {
/* 1317 */         value = calendar.getLeastMaximum(10) + 1;
/*      */       }
/* 1319 */       this.mRule.appendTo(buffer, value);
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, int value)
/*      */     {
/* 1326 */       this.mRule.appendTo(buffer, value);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class TwoDigitMonthField
/*      */     implements FastDateFormat.NumberRule
/*      */   {
/* 1256 */     static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/* 1269 */       return 2;
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/* 1276 */       appendTo(buffer, calendar.get(2) + 1);
/*      */     }
/*      */ 
/*      */     public final void appendTo(StringBuffer buffer, int value)
/*      */     {
/* 1283 */       buffer.append((char)(value / 10 + 48));
/* 1284 */       buffer.append((char)(value % 10 + 48));
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class TwoDigitYearField
/*      */     implements FastDateFormat.NumberRule
/*      */   {
/* 1220 */     static final TwoDigitYearField INSTANCE = new TwoDigitYearField();
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/* 1233 */       return 2;
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/* 1240 */       appendTo(buffer, calendar.get(1) % 100);
/*      */     }
/*      */ 
/*      */     public final void appendTo(StringBuffer buffer, int value)
/*      */     {
/* 1247 */       buffer.append((char)(value / 10 + 48));
/* 1248 */       buffer.append((char)(value % 10 + 48));
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class TwoDigitNumberField
/*      */     implements FastDateFormat.NumberRule
/*      */   {
/*      */     private final int mField;
/*      */ 
/*      */     TwoDigitNumberField(int field)
/*      */     {
/* 1186 */       this.mField = field;
/*      */     }
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/* 1193 */       return 2;
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/* 1200 */       appendTo(buffer, calendar.get(this.mField));
/*      */     }
/*      */ 
/*      */     public final void appendTo(StringBuffer buffer, int value)
/*      */     {
/* 1207 */       if (value < 100) {
/* 1208 */         buffer.append((char)(value / 10 + 48));
/* 1209 */         buffer.append((char)(value % 10 + 48));
/*      */       } else {
/* 1211 */         buffer.append(Integer.toString(value));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class PaddedNumberField
/*      */     implements FastDateFormat.NumberRule
/*      */   {
/*      */     private final int mField;
/*      */     private final int mSize;
/*      */ 
/*      */     PaddedNumberField(int field, int size)
/*      */     {
/* 1126 */       if (size < 3)
/*      */       {
/* 1128 */         throw new IllegalArgumentException();
/*      */       }
/* 1130 */       this.mField = field;
/* 1131 */       this.mSize = size;
/*      */     }
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/* 1138 */       return 4;
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/* 1145 */       appendTo(buffer, calendar.get(this.mField));
/*      */     }
/*      */ 
/*      */     public final void appendTo(StringBuffer buffer, int value)
/*      */     {
/* 1152 */       if (value < 100) {
/* 1153 */         int i = this.mSize;
/*      */         while (true) { i--; if (i < 2) break;
/* 1154 */           buffer.append('0');
/*      */         }
/* 1156 */         buffer.append((char)(value / 10 + 48));
/* 1157 */         buffer.append((char)(value % 10 + 48));
/*      */       }
/*      */       else
/*      */       {
/*      */         int digits;
/*      */         int digits;
/* 1160 */         if (value < 1000) {
/* 1161 */           digits = 3;
/*      */         } else {
/* 1163 */           Validate.isTrue(value > -1, "Negative values should not be possible", value);
/* 1164 */           digits = Integer.toString(value).length();
/*      */         }
/* 1166 */         int i = this.mSize;
/*      */         while (true) { i--; if (i < digits) break;
/* 1167 */           buffer.append('0');
/*      */         }
/* 1169 */         buffer.append(Integer.toString(value));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class UnpaddedMonthField
/*      */     implements FastDateFormat.NumberRule
/*      */   {
/* 1075 */     static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/* 1089 */       return 2;
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/* 1096 */       appendTo(buffer, calendar.get(2) + 1);
/*      */     }
/*      */ 
/*      */     public final void appendTo(StringBuffer buffer, int value)
/*      */     {
/* 1103 */       if (value < 10) {
/* 1104 */         buffer.append((char)(value + 48));
/*      */       } else {
/* 1106 */         buffer.append((char)(value / 10 + 48));
/* 1107 */         buffer.append((char)(value % 10 + 48));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class UnpaddedNumberField
/*      */     implements FastDateFormat.NumberRule
/*      */   {
/*      */     private final int mField;
/*      */ 
/*      */     UnpaddedNumberField(int field)
/*      */     {
/* 1039 */       this.mField = field;
/*      */     }
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/* 1046 */       return 4;
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/* 1053 */       appendTo(buffer, calendar.get(this.mField));
/*      */     }
/*      */ 
/*      */     public final void appendTo(StringBuffer buffer, int value)
/*      */     {
/* 1060 */       if (value < 10) {
/* 1061 */         buffer.append((char)(value + 48));
/* 1062 */       } else if (value < 100) {
/* 1063 */         buffer.append((char)(value / 10 + 48));
/* 1064 */         buffer.append((char)(value % 10 + 48));
/*      */       } else {
/* 1066 */         buffer.append(Integer.toString(value));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class TextField
/*      */     implements FastDateFormat.Rule
/*      */   {
/*      */     private final int mField;
/*      */     private final String[] mValues;
/*      */ 
/*      */     TextField(int field, String[] values)
/*      */     {
/* 1001 */       this.mField = field;
/* 1002 */       this.mValues = values;
/*      */     }
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/* 1009 */       int max = 0;
/* 1010 */       int i = this.mValues.length;
/*      */       while (true) { i--; if (i < 0) break;
/* 1011 */         int len = this.mValues[i].length();
/* 1012 */         if (len > max) {
/* 1013 */           max = len;
/*      */         }
/*      */       }
/* 1016 */       return max;
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/* 1023 */       buffer.append(this.mValues[calendar.get(this.mField)]);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class StringLiteral
/*      */     implements FastDateFormat.Rule
/*      */   {
/*      */     private final String mValue;
/*      */ 
/*      */     StringLiteral(String value)
/*      */     {
/*  968 */       this.mValue = value;
/*      */     }
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/*  975 */       return this.mValue.length();
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/*  982 */       buffer.append(this.mValue);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class CharacterLiteral
/*      */     implements FastDateFormat.Rule
/*      */   {
/*      */     private final char mValue;
/*      */ 
/*      */     CharacterLiteral(char value)
/*      */     {
/*  937 */       this.mValue = value;
/*      */     }
/*      */ 
/*      */     public int estimateLength()
/*      */     {
/*  944 */       return 1;
/*      */     }
/*      */ 
/*      */     public void appendTo(StringBuffer buffer, Calendar calendar)
/*      */     {
/*  951 */       buffer.append(this.mValue);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static abstract interface NumberRule extends FastDateFormat.Rule
/*      */   {
/*      */     public abstract void appendTo(StringBuffer paramStringBuffer, int paramInt);
/*      */   }
/*      */ 
/*      */   private static abstract interface Rule
/*      */   {
/*      */     public abstract int estimateLength();
/*      */ 
/*      */     public abstract void appendTo(StringBuffer paramStringBuffer, Calendar paramCalendar);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.time.FastDateFormat
 * JD-Core Version:    0.6.2
 */