package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.Validate;

public class FastDateFormat
  extends Format
{
  private static final long serialVersionUID = 1L;
  public static final int FULL = 0;
  public static final int LONG = 1;
  public static final int MEDIUM = 2;
  public static final int SHORT = 3;
  private static final FormatCache<FastDateFormat> cache = new FormatCache()
  {
    protected FastDateFormat createInstance(String pattern, TimeZone timeZone, Locale locale)
    {
      return new FastDateFormat(pattern, timeZone, locale);
    }
  };
  private static ConcurrentMap<TimeZoneDisplayKey, String> cTimeZoneDisplayCache = new ConcurrentHashMap(7);
  private final String mPattern;
  private final TimeZone mTimeZone;
  private final Locale mLocale;
  private transient Rule[] mRules;
  private transient int mMaxLengthEstimate;
  
  public static FastDateFormat getInstance()
  {
    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(3), Integer.valueOf(3), null, null);
  }
  
  public static FastDateFormat getInstance(String pattern)
  {
    return (FastDateFormat)cache.getInstance(pattern, null, null);
  }
  
  public static FastDateFormat getInstance(String pattern, TimeZone timeZone)
  {
    return (FastDateFormat)cache.getInstance(pattern, timeZone, null);
  }
  
  public static FastDateFormat getInstance(String pattern, Locale locale)
  {
    return (FastDateFormat)cache.getInstance(pattern, null, locale);
  }
  
  public static FastDateFormat getInstance(String pattern, TimeZone timeZone, Locale locale)
  {
    return (FastDateFormat)cache.getInstance(pattern, timeZone, locale);
  }
  
  public static FastDateFormat getDateInstance(int style)
  {
    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, null, null);
  }
  
  public static FastDateFormat getDateInstance(int style, Locale locale)
  {
    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, null, locale);
  }
  
  public static FastDateFormat getDateInstance(int style, TimeZone timeZone)
  {
    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, timeZone, null);
  }
  
  public static FastDateFormat getDateInstance(int style, TimeZone timeZone, Locale locale)
  {
    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(style), null, timeZone, locale);
  }
  
  public static FastDateFormat getTimeInstance(int style)
  {
    return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), null, null);
  }
  
  public static FastDateFormat getTimeInstance(int style, Locale locale)
  {
    return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), null, locale);
  }
  
  public static FastDateFormat getTimeInstance(int style, TimeZone timeZone)
  {
    return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), timeZone, null);
  }
  
  public static FastDateFormat getTimeInstance(int style, TimeZone timeZone, Locale locale)
  {
    return (FastDateFormat)cache.getDateTimeInstance(null, Integer.valueOf(style), timeZone, locale);
  }
  
  public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle)
  {
    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), null, null);
  }
  
  public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, Locale locale)
  {
    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), null, locale);
  }
  
  public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone)
  {
    return getDateTimeInstance(dateStyle, timeStyle, timeZone, null);
  }
  
  public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone, Locale locale)
  {
    return (FastDateFormat)cache.getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), timeZone, locale);
  }
  
  static String getTimeZoneDisplay(TimeZone local_tz, boolean daylight, int style, Locale locale)
  {
    TimeZoneDisplayKey key = new TimeZoneDisplayKey(local_tz, daylight, style, locale);
    String value = (String)cTimeZoneDisplayCache.get(key);
    if (value == null)
    {
      value = local_tz.getDisplayName(daylight, style, locale);
      String prior = (String)cTimeZoneDisplayCache.putIfAbsent(key, value);
      if (prior != null) {
        value = prior;
      }
    }
    return value;
  }
  
  protected FastDateFormat(String pattern, TimeZone timeZone, Locale locale)
  {
    this.mPattern = pattern;
    this.mTimeZone = timeZone;
    this.mLocale = locale;
    init();
  }
  
  private void init()
  {
    List<Rule> rulesList = parsePattern();
    this.mRules = ((Rule[])rulesList.toArray(new Rule[rulesList.size()]));
    int len = 0;
    int local_i = this.mRules.length;
    for (;;)
    {
      local_i--;
      if (local_i < 0) {
        break;
      }
      len += this.mRules[local_i].estimateLength();
    }
    this.mMaxLengthEstimate = len;
  }
  
  protected List<Rule> parsePattern()
  {
    DateFormatSymbols symbols = new DateFormatSymbols(this.mLocale);
    List<Rule> rules = new ArrayList();
    String[] ERAs = symbols.getEras();
    String[] months = symbols.getMonths();
    String[] shortMonths = symbols.getShortMonths();
    String[] weekdays = symbols.getWeekdays();
    String[] shortWeekdays = symbols.getShortWeekdays();
    String[] AmPmStrings = symbols.getAmPmStrings();
    int length = this.mPattern.length();
    int[] indexRef = new int[1];
    for (int local_i = 0; local_i < length; local_i++)
    {
      indexRef[0] = local_i;
      String token = parseToken(this.mPattern, indexRef);
      local_i = indexRef[0];
      int tokenLen = token.length();
      if (tokenLen == 0) {
        break;
      }
      char local_c = token.charAt(0);
      Rule rule;
      Rule rule;
      Rule rule;
      Rule rule;
      Rule rule;
      Rule rule;
      switch (local_c)
      {
      case 'G': 
        rule = new TextField(0, ERAs);
        break;
      case 'y': 
        if (tokenLen == 2) {
          rule = TwoDigitYearField.INSTANCE;
        } else {
          rule = selectNumberRule(1, tokenLen < 4 ? 4 : tokenLen);
        }
        break;
      case 'M': 
        if (tokenLen >= 4)
        {
          rule = new TextField(2, months);
        }
        else
        {
          Rule rule;
          if (tokenLen == 3)
          {
            rule = new TextField(2, shortMonths);
          }
          else
          {
            Rule rule;
            if (tokenLen == 2) {
              rule = TwoDigitMonthField.INSTANCE;
            } else {
              rule = UnpaddedMonthField.INSTANCE;
            }
          }
        }
        break;
      case 'd': 
        rule = selectNumberRule(5, tokenLen);
        break;
      case 'h': 
        rule = new TwelveHourField(selectNumberRule(10, tokenLen));
        break;
      case 'H': 
        rule = selectNumberRule(11, tokenLen);
        break;
      case 'm': 
        rule = selectNumberRule(12, tokenLen);
        break;
      case 's': 
        rule = selectNumberRule(13, tokenLen);
        break;
      case 'S': 
        rule = selectNumberRule(14, tokenLen);
        break;
      case 'E': 
        rule = new TextField(7, tokenLen < 4 ? shortWeekdays : weekdays);
        break;
      case 'D': 
        rule = selectNumberRule(6, tokenLen);
        break;
      case 'F': 
        rule = selectNumberRule(8, tokenLen);
        break;
      case 'w': 
        rule = selectNumberRule(3, tokenLen);
        break;
      case 'W': 
        rule = selectNumberRule(4, tokenLen);
        break;
      case 'a': 
        rule = new TextField(9, AmPmStrings);
        break;
      case 'k': 
        rule = new TwentyFourHourField(selectNumberRule(11, tokenLen));
        break;
      case 'K': 
        rule = selectNumberRule(10, tokenLen);
        break;
      case 'z': 
        if (tokenLen >= 4) {
          rule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 1);
        } else {
          rule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 0);
        }
        break;
      case 'Z': 
        if (tokenLen == 1) {
          rule = TimeZoneNumberRule.INSTANCE_NO_COLON;
        } else {
          rule = TimeZoneNumberRule.INSTANCE_COLON;
        }
        break;
      case '\'': 
        String sub = token.substring(1);
        if (sub.length() == 1) {
          rule = new CharacterLiteral(sub.charAt(0));
        } else {
          rule = new StringLiteral(sub);
        }
        break;
      case '(': 
      case ')': 
      case '*': 
      case '+': 
      case ',': 
      case '-': 
      case '.': 
      case '/': 
      case '0': 
      case '1': 
      case '2': 
      case '3': 
      case '4': 
      case '5': 
      case '6': 
      case '7': 
      case '8': 
      case '9': 
      case ':': 
      case ';': 
      case '<': 
      case '=': 
      case '>': 
      case '?': 
      case '@': 
      case 'A': 
      case 'B': 
      case 'C': 
      case 'I': 
      case 'J': 
      case 'L': 
      case 'N': 
      case 'O': 
      case 'P': 
      case 'Q': 
      case 'R': 
      case 'T': 
      case 'U': 
      case 'V': 
      case 'X': 
      case 'Y': 
      case '[': 
      case '\\': 
      case ']': 
      case '^': 
      case '_': 
      case '`': 
      case 'b': 
      case 'c': 
      case 'e': 
      case 'f': 
      case 'g': 
      case 'i': 
      case 'j': 
      case 'l': 
      case 'n': 
      case 'o': 
      case 'p': 
      case 'q': 
      case 'r': 
      case 't': 
      case 'u': 
      case 'v': 
      case 'x': 
      default: 
        throw new IllegalArgumentException("Illegal pattern component: " + token);
      }
      rules.add(rule);
    }
    return rules;
  }
  
  protected String parseToken(String pattern, int[] indexRef)
  {
    StringBuilder buf = new StringBuilder();
    int local_i = indexRef[0];
    int length = pattern.length();
    char local_c = pattern.charAt(local_i);
    if (((local_c >= 'A') && (local_c <= 'Z')) || ((local_c >= 'a') && (local_c <= 'z'))) {
      buf.append(local_c);
    }
    while (local_i + 1 < length)
    {
      char peek = pattern.charAt(local_i + 1);
      if (peek == local_c)
      {
        buf.append(local_c);
        local_i++;
        continue;
        buf.append('\'');
        boolean peek = false;
        while (local_i < length)
        {
          local_c = pattern.charAt(local_i);
          if (local_c == '\'')
          {
            if ((local_i + 1 < length) && (pattern.charAt(local_i + 1) == '\''))
            {
              local_i++;
              buf.append(local_c);
            }
            else
            {
              peek = !peek;
            }
          }
          else
          {
            if ((!peek) && (((local_c >= 'A') && (local_c <= 'Z')) || ((local_c >= 'a') && (local_c <= 'z'))))
            {
              local_i--;
              break;
            }
            buf.append(local_c);
          }
          local_i++;
        }
      }
    }
    indexRef[0] = local_i;
    return buf.toString();
  }
  
  protected NumberRule selectNumberRule(int field, int padding)
  {
    switch (padding)
    {
    case 1: 
      return new UnpaddedNumberField(field);
    case 2: 
      return new TwoDigitNumberField(field);
    }
    return new PaddedNumberField(field, padding);
  }
  
  public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos)
  {
    if ((obj instanceof Date)) {
      return format((Date)obj, toAppendTo);
    }
    if ((obj instanceof Calendar)) {
      return format((Calendar)obj, toAppendTo);
    }
    if ((obj instanceof Long)) {
      return format(((Long)obj).longValue(), toAppendTo);
    }
    throw new IllegalArgumentException("Unknown class: " + (obj == null ? "<null>" : obj.getClass().getName()));
  }
  
  public String format(long millis)
  {
    return format(new Date(millis));
  }
  
  public String format(Date date)
  {
    Calendar local_c = new GregorianCalendar(this.mTimeZone, this.mLocale);
    local_c.setTime(date);
    return applyRules(local_c, new StringBuffer(this.mMaxLengthEstimate)).toString();
  }
  
  public String format(Calendar calendar)
  {
    return format(calendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
  }
  
  public StringBuffer format(long millis, StringBuffer buf)
  {
    return format(new Date(millis), buf);
  }
  
  public StringBuffer format(Date date, StringBuffer buf)
  {
    Calendar local_c = new GregorianCalendar(this.mTimeZone, this.mLocale);
    local_c.setTime(date);
    return applyRules(local_c, buf);
  }
  
  public StringBuffer format(Calendar calendar, StringBuffer buf)
  {
    return applyRules(calendar, buf);
  }
  
  protected StringBuffer applyRules(Calendar calendar, StringBuffer buf)
  {
    for (Rule rule : this.mRules) {
      rule.appendTo(buf, calendar);
    }
    return buf;
  }
  
  public Object parseObject(String source, ParsePosition pos)
  {
    pos.setIndex(0);
    pos.setErrorIndex(0);
    return null;
  }
  
  public String getPattern()
  {
    return this.mPattern;
  }
  
  public TimeZone getTimeZone()
  {
    return this.mTimeZone;
  }
  
  public Locale getLocale()
  {
    return this.mLocale;
  }
  
  public int getMaxLengthEstimate()
  {
    return this.mMaxLengthEstimate;
  }
  
  public boolean equals(Object obj)
  {
    if (!(obj instanceof FastDateFormat)) {
      return false;
    }
    FastDateFormat other = (FastDateFormat)obj;
    return (this.mPattern.equals(other.mPattern)) && (this.mTimeZone.equals(other.mTimeZone)) && (this.mLocale.equals(other.mLocale));
  }
  
  public int hashCode()
  {
    return this.mPattern.hashCode() + 13 * (this.mTimeZone.hashCode() + 13 * this.mLocale.hashCode());
  }
  
  public String toString()
  {
    return "FastDateFormat[" + this.mPattern + "]";
  }
  
  private void readObject(ObjectInputStream local_in)
    throws IOException, ClassNotFoundException
  {
    local_in.defaultReadObject();
    init();
  }
  
  private static class TimeZoneDisplayKey
  {
    private final TimeZone mTimeZone;
    private final int mStyle;
    private final Locale mLocale;
    
    TimeZoneDisplayKey(TimeZone timeZone, boolean daylight, int style, Locale locale)
    {
      this.mTimeZone = timeZone;
      if (daylight) {
        style |= -2147483648;
      }
      this.mStyle = style;
      this.mLocale = locale;
    }
    
    public int hashCode()
    {
      return (this.mStyle * 31 + this.mLocale.hashCode()) * 31 + this.mTimeZone.hashCode();
    }
    
    public boolean equals(Object obj)
    {
      if (this == obj) {
        return true;
      }
      if ((obj instanceof TimeZoneDisplayKey))
      {
        TimeZoneDisplayKey other = (TimeZoneDisplayKey)obj;
        return (this.mTimeZone.equals(other.mTimeZone)) && (this.mStyle == other.mStyle) && (this.mLocale.equals(other.mLocale));
      }
      return false;
    }
  }
  
  private static class TimeZoneNumberRule
    implements FastDateFormat.Rule
  {
    static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);
    static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
    final boolean mColon;
    
    TimeZoneNumberRule(boolean colon)
    {
      this.mColon = colon;
    }
    
    public int estimateLength()
    {
      return 5;
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      int offset = calendar.get(15) + calendar.get(16);
      if (offset < 0)
      {
        buffer.append('-');
        offset = -offset;
      }
      else
      {
        buffer.append('+');
      }
      int hours = offset / 3600000;
      buffer.append((char)(hours / 10 + 48));
      buffer.append((char)(hours % 10 + 48));
      if (this.mColon) {
        buffer.append(':');
      }
      int minutes = offset / 60000 - 60 * hours;
      buffer.append((char)(minutes / 10 + 48));
      buffer.append((char)(minutes % 10 + 48));
    }
  }
  
  private static class TimeZoneNameRule
    implements FastDateFormat.Rule
  {
    private final TimeZone mTimeZone;
    private final String mStandard;
    private final String mDaylight;
    
    TimeZoneNameRule(TimeZone timeZone, Locale locale, int style)
    {
      this.mTimeZone = timeZone;
      this.mStandard = FastDateFormat.getTimeZoneDisplay(timeZone, false, style, locale);
      this.mDaylight = FastDateFormat.getTimeZoneDisplay(timeZone, true, style, locale);
    }
    
    public int estimateLength()
    {
      return Math.max(this.mStandard.length(), this.mDaylight.length());
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      if ((this.mTimeZone.useDaylightTime()) && (calendar.get(16) != 0)) {
        buffer.append(this.mDaylight);
      } else {
        buffer.append(this.mStandard);
      }
    }
  }
  
  private static class TwentyFourHourField
    implements FastDateFormat.NumberRule
  {
    private final FastDateFormat.NumberRule mRule;
    
    TwentyFourHourField(FastDateFormat.NumberRule rule)
    {
      this.mRule = rule;
    }
    
    public int estimateLength()
    {
      return this.mRule.estimateLength();
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      int value = calendar.get(11);
      if (value == 0) {
        value = calendar.getMaximum(11) + 1;
      }
      this.mRule.appendTo(buffer, value);
    }
    
    public void appendTo(StringBuffer buffer, int value)
    {
      this.mRule.appendTo(buffer, value);
    }
  }
  
  private static class TwelveHourField
    implements FastDateFormat.NumberRule
  {
    private final FastDateFormat.NumberRule mRule;
    
    TwelveHourField(FastDateFormat.NumberRule rule)
    {
      this.mRule = rule;
    }
    
    public int estimateLength()
    {
      return this.mRule.estimateLength();
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      int value = calendar.get(10);
      if (value == 0) {
        value = calendar.getLeastMaximum(10) + 1;
      }
      this.mRule.appendTo(buffer, value);
    }
    
    public void appendTo(StringBuffer buffer, int value)
    {
      this.mRule.appendTo(buffer, value);
    }
  }
  
  private static class TwoDigitMonthField
    implements FastDateFormat.NumberRule
  {
    static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();
    
    public int estimateLength()
    {
      return 2;
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      appendTo(buffer, calendar.get(2) + 1);
    }
    
    public final void appendTo(StringBuffer buffer, int value)
    {
      buffer.append((char)(value / 10 + 48));
      buffer.append((char)(value % 10 + 48));
    }
  }
  
  private static class TwoDigitYearField
    implements FastDateFormat.NumberRule
  {
    static final TwoDigitYearField INSTANCE = new TwoDigitYearField();
    
    public int estimateLength()
    {
      return 2;
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      appendTo(buffer, calendar.get(1) % 100);
    }
    
    public final void appendTo(StringBuffer buffer, int value)
    {
      buffer.append((char)(value / 10 + 48));
      buffer.append((char)(value % 10 + 48));
    }
  }
  
  private static class TwoDigitNumberField
    implements FastDateFormat.NumberRule
  {
    private final int mField;
    
    TwoDigitNumberField(int field)
    {
      this.mField = field;
    }
    
    public int estimateLength()
    {
      return 2;
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      appendTo(buffer, calendar.get(this.mField));
    }
    
    public final void appendTo(StringBuffer buffer, int value)
    {
      if (value < 100)
      {
        buffer.append((char)(value / 10 + 48));
        buffer.append((char)(value % 10 + 48));
      }
      else
      {
        buffer.append(Integer.toString(value));
      }
    }
  }
  
  private static class PaddedNumberField
    implements FastDateFormat.NumberRule
  {
    private final int mField;
    private final int mSize;
    
    PaddedNumberField(int field, int size)
    {
      if (size < 3) {
        throw new IllegalArgumentException();
      }
      this.mField = field;
      this.mSize = size;
    }
    
    public int estimateLength()
    {
      return 4;
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      appendTo(buffer, calendar.get(this.mField));
    }
    
    public final void appendTo(StringBuffer buffer, int value)
    {
      if (value < 100)
      {
        int local_i = this.mSize;
        for (;;)
        {
          local_i--;
          if (local_i < 2) {
            break;
          }
          buffer.append('0');
        }
        buffer.append((char)(value / 10 + 48));
        buffer.append((char)(value % 10 + 48));
      }
      else
      {
        int local_i;
        int local_i;
        if (value < 1000)
        {
          local_i = 3;
        }
        else
        {
          Validate.isTrue(value > -1, "Negative values should not be possible", value);
          local_i = Integer.toString(value).length();
        }
        int local_i1 = this.mSize;
        for (;;)
        {
          local_i1--;
          if (local_i1 < local_i) {
            break;
          }
          buffer.append('0');
        }
        buffer.append(Integer.toString(value));
      }
    }
  }
  
  private static class UnpaddedMonthField
    implements FastDateFormat.NumberRule
  {
    static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();
    
    public int estimateLength()
    {
      return 2;
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      appendTo(buffer, calendar.get(2) + 1);
    }
    
    public final void appendTo(StringBuffer buffer, int value)
    {
      if (value < 10)
      {
        buffer.append((char)(value + 48));
      }
      else
      {
        buffer.append((char)(value / 10 + 48));
        buffer.append((char)(value % 10 + 48));
      }
    }
  }
  
  private static class UnpaddedNumberField
    implements FastDateFormat.NumberRule
  {
    private final int mField;
    
    UnpaddedNumberField(int field)
    {
      this.mField = field;
    }
    
    public int estimateLength()
    {
      return 4;
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      appendTo(buffer, calendar.get(this.mField));
    }
    
    public final void appendTo(StringBuffer buffer, int value)
    {
      if (value < 10)
      {
        buffer.append((char)(value + 48));
      }
      else if (value < 100)
      {
        buffer.append((char)(value / 10 + 48));
        buffer.append((char)(value % 10 + 48));
      }
      else
      {
        buffer.append(Integer.toString(value));
      }
    }
  }
  
  private static class TextField
    implements FastDateFormat.Rule
  {
    private final int mField;
    private final String[] mValues;
    
    TextField(int field, String[] values)
    {
      this.mField = field;
      this.mValues = values;
    }
    
    public int estimateLength()
    {
      int max = 0;
      int local_i = this.mValues.length;
      for (;;)
      {
        local_i--;
        if (local_i < 0) {
          break;
        }
        int len = this.mValues[local_i].length();
        if (len > max) {
          max = len;
        }
      }
      return max;
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      buffer.append(this.mValues[calendar.get(this.mField)]);
    }
  }
  
  private static class StringLiteral
    implements FastDateFormat.Rule
  {
    private final String mValue;
    
    StringLiteral(String value)
    {
      this.mValue = value;
    }
    
    public int estimateLength()
    {
      return this.mValue.length();
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      buffer.append(this.mValue);
    }
  }
  
  private static class CharacterLiteral
    implements FastDateFormat.Rule
  {
    private final char mValue;
    
    CharacterLiteral(char value)
    {
      this.mValue = value;
    }
    
    public int estimateLength()
    {
      return 1;
    }
    
    public void appendTo(StringBuffer buffer, Calendar calendar)
    {
      buffer.append(this.mValue);
    }
  }
  
  private static abstract interface NumberRule
    extends FastDateFormat.Rule
  {
    public abstract void appendTo(StringBuffer paramStringBuffer, int paramInt);
  }
  
  private static abstract interface Rule
  {
    public abstract int estimateLength();
    
    public abstract void appendTo(StringBuffer paramStringBuffer, Calendar paramCalendar);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.time.FastDateFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */