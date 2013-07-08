package org.apache.commons.lang3.time;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

abstract class FormatCache<F extends Format>
{
  static final int NONE = -1;
  private final ConcurrentMap<MultipartKey, F> cInstanceCache = new ConcurrentHashMap(7);
  private final ConcurrentMap<MultipartKey, String> cDateTimeInstanceCache = new ConcurrentHashMap(7);
  
  public F getInstance()
  {
    return getDateTimeInstance(Integer.valueOf(3), Integer.valueOf(3), TimeZone.getDefault(), Locale.getDefault());
  }
  
  public F getInstance(String pattern, TimeZone timeZone, Locale locale)
  {
    if (pattern == null) {
      throw new NullPointerException("pattern must not be null");
    }
    if (timeZone == null) {
      timeZone = TimeZone.getDefault();
    }
    if (locale == null) {
      locale = Locale.getDefault();
    }
    MultipartKey key = new MultipartKey(new Object[] { pattern, timeZone, locale });
    F format = (Format)this.cInstanceCache.get(key);
    if (format == null)
    {
      format = createInstance(pattern, timeZone, locale);
      F previousValue = (Format)this.cInstanceCache.putIfAbsent(key, format);
      if (previousValue != null) {
        format = previousValue;
      }
    }
    return format;
  }
  
  protected abstract F createInstance(String paramString, TimeZone paramTimeZone, Locale paramLocale);
  
  public F getDateTimeInstance(Integer dateStyle, Integer timeStyle, TimeZone timeZone, Locale locale)
  {
    if (locale == null) {
      locale = Locale.getDefault();
    }
    MultipartKey key = new MultipartKey(new Object[] { dateStyle, timeStyle, locale });
    String pattern = (String)this.cDateTimeInstanceCache.get(key);
    if (pattern == null) {
      try
      {
        DateFormat formatter;
        DateFormat formatter;
        if (dateStyle == null)
        {
          formatter = DateFormat.getTimeInstance(timeStyle.intValue(), locale);
        }
        else
        {
          DateFormat formatter;
          if (timeStyle == null) {
            formatter = DateFormat.getDateInstance(dateStyle.intValue(), locale);
          } else {
            formatter = DateFormat.getDateTimeInstance(dateStyle.intValue(), timeStyle.intValue(), locale);
          }
        }
        pattern = ((SimpleDateFormat)formatter).toPattern();
        String previous = (String)this.cDateTimeInstanceCache.putIfAbsent(key, pattern);
        if (previous != null) {
          pattern = previous;
        }
      }
      catch (ClassCastException formatter)
      {
        throw new IllegalArgumentException("No date time pattern for locale: " + locale);
      }
    }
    return getInstance(pattern, timeZone, locale);
  }
  
  private static class MultipartKey
  {
    private final Object[] keys;
    private int hashCode;
    
    public MultipartKey(Object... keys)
    {
      this.keys = keys;
    }
    
    public boolean equals(Object obj)
    {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof MultipartKey)) {
        return false;
      }
      return Arrays.equals(this.keys, ((MultipartKey)obj).keys);
    }
    
    public int hashCode()
    {
      if (this.hashCode == 0)
      {
        int local_rc = 0;
        for (Object key : this.keys) {
          if (key != null) {
            local_rc = local_rc * 7 + key.hashCode();
          }
        }
        this.hashCode = local_rc;
      }
      return this.hashCode;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.time.FormatCache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */