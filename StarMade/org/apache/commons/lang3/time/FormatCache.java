/*   1:    */package org.apache.commons.lang3.time;
/*   2:    */
/*   3:    */import java.text.DateFormat;
/*   4:    */import java.text.Format;
/*   5:    */import java.text.SimpleDateFormat;
/*   6:    */import java.util.Arrays;
/*   7:    */import java.util.Locale;
/*   8:    */import java.util.TimeZone;
/*   9:    */import java.util.concurrent.ConcurrentHashMap;
/*  10:    */import java.util.concurrent.ConcurrentMap;
/*  11:    */
/*  33:    */abstract class FormatCache<F extends Format>
/*  34:    */{
/*  35:    */  static final int NONE = -1;
/*  36:    */  private final ConcurrentMap<MultipartKey, F> cInstanceCache;
/*  37:    */  private final ConcurrentMap<MultipartKey, String> cDateTimeInstanceCache;
/*  38:    */  
/*  39:    */  FormatCache()
/*  40:    */  {
/*  41: 41 */    this.cInstanceCache = new ConcurrentHashMap(7);
/*  42:    */    
/*  44: 44 */    this.cDateTimeInstanceCache = new ConcurrentHashMap(7);
/*  45:    */  }
/*  46:    */  
/*  52:    */  public F getInstance()
/*  53:    */  {
/*  54: 54 */    return getDateTimeInstance(Integer.valueOf(3), Integer.valueOf(3), TimeZone.getDefault(), Locale.getDefault());
/*  55:    */  }
/*  56:    */  
/*  68:    */  public F getInstance(String pattern, TimeZone timeZone, Locale locale)
/*  69:    */  {
/*  70: 70 */    if (pattern == null) {
/*  71: 71 */      throw new NullPointerException("pattern must not be null");
/*  72:    */    }
/*  73: 73 */    if (timeZone == null) {
/*  74: 74 */      timeZone = TimeZone.getDefault();
/*  75:    */    }
/*  76: 76 */    if (locale == null) {
/*  77: 77 */      locale = Locale.getDefault();
/*  78:    */    }
/*  79: 79 */    MultipartKey key = new MultipartKey(new Object[] { pattern, timeZone, locale });
/*  80: 80 */    F format = (Format)this.cInstanceCache.get(key);
/*  81: 81 */    if (format == null) {
/*  82: 82 */      format = createInstance(pattern, timeZone, locale);
/*  83: 83 */      F previousValue = (Format)this.cInstanceCache.putIfAbsent(key, format);
/*  84: 84 */      if (previousValue != null)
/*  85:    */      {
/*  87: 87 */        format = previousValue;
/*  88:    */      }
/*  89:    */    }
/*  90: 90 */    return format;
/*  91:    */  }
/*  92:    */  
/* 105:    */  protected abstract F createInstance(String paramString, TimeZone paramTimeZone, Locale paramLocale);
/* 106:    */  
/* 118:    */  public F getDateTimeInstance(Integer dateStyle, Integer timeStyle, TimeZone timeZone, Locale locale)
/* 119:    */  {
/* 120:120 */    if (locale == null) {
/* 121:121 */      locale = Locale.getDefault();
/* 122:    */    }
/* 123:123 */    MultipartKey key = new MultipartKey(new Object[] { dateStyle, timeStyle, locale });
/* 124:    */    
/* 125:125 */    String pattern = (String)this.cDateTimeInstanceCache.get(key);
/* 126:126 */    if (pattern == null) {
/* 127:    */      try { DateFormat formatter;
/* 128:    */        DateFormat formatter;
/* 129:129 */        if (dateStyle == null) {
/* 130:130 */          formatter = DateFormat.getTimeInstance(timeStyle.intValue(), locale);
/* 131:    */        } else { DateFormat formatter;
/* 132:132 */          if (timeStyle == null) {
/* 133:133 */            formatter = DateFormat.getDateInstance(dateStyle.intValue(), locale);
/* 134:    */          }
/* 135:    */          else
/* 136:136 */            formatter = DateFormat.getDateTimeInstance(dateStyle.intValue(), timeStyle.intValue(), locale);
/* 137:    */        }
/* 138:138 */        pattern = ((SimpleDateFormat)formatter).toPattern();
/* 139:139 */        String previous = (String)this.cDateTimeInstanceCache.putIfAbsent(key, pattern);
/* 140:140 */        if (previous != null)
/* 141:    */        {
/* 144:144 */          pattern = previous;
/* 145:    */        }
/* 146:    */      } catch (ClassCastException ex) {
/* 147:147 */        throw new IllegalArgumentException("No date time pattern for locale: " + locale);
/* 148:    */      }
/* 149:    */    }
/* 150:    */    
/* 151:151 */    return getInstance(pattern, timeZone, locale);
/* 152:    */  }
/* 153:    */  
/* 156:    */  private static class MultipartKey
/* 157:    */  {
/* 158:    */    private final Object[] keys;
/* 159:    */    
/* 161:    */    private int hashCode;
/* 162:    */    
/* 165:    */    public MultipartKey(Object... keys)
/* 166:    */    {
/* 167:167 */      this.keys = keys;
/* 168:    */    }
/* 169:    */    
/* 173:    */    public boolean equals(Object obj)
/* 174:    */    {
/* 175:175 */      if (this == obj) {
/* 176:176 */        return true;
/* 177:    */      }
/* 178:178 */      if (!(obj instanceof MultipartKey)) {
/* 179:179 */        return false;
/* 180:    */      }
/* 181:181 */      return Arrays.equals(this.keys, ((MultipartKey)obj).keys);
/* 182:    */    }
/* 183:    */    
/* 187:    */    public int hashCode()
/* 188:    */    {
/* 189:189 */      if (this.hashCode == 0) {
/* 190:190 */        int rc = 0;
/* 191:191 */        for (Object key : this.keys) {
/* 192:192 */          if (key != null) {
/* 193:193 */            rc = rc * 7 + key.hashCode();
/* 194:    */          }
/* 195:    */        }
/* 196:196 */        this.hashCode = rc;
/* 197:    */      }
/* 198:198 */      return this.hashCode;
/* 199:    */    }
/* 200:    */  }
/* 201:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.time.FormatCache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */