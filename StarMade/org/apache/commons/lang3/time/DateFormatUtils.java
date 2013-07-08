/*   1:    */package org.apache.commons.lang3.time;
/*   2:    */
/*   3:    */import java.util.Calendar;
/*   4:    */import java.util.Date;
/*   5:    */import java.util.Locale;
/*   6:    */import java.util.TimeZone;
/*   7:    */
/*  37:    */public class DateFormatUtils
/*  38:    */{
/*  39: 39 */  private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");
/*  40:    */  
/*  44: 44 */  public static final FastDateFormat ISO_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");
/*  45:    */  
/*  51: 51 */  public static final FastDateFormat ISO_DATETIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssZZ");
/*  52:    */  
/*  58: 58 */  public static final FastDateFormat ISO_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
/*  59:    */  
/*  67: 67 */  public static final FastDateFormat ISO_DATE_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-ddZZ");
/*  68:    */  
/*  74: 74 */  public static final FastDateFormat ISO_TIME_FORMAT = FastDateFormat.getInstance("'T'HH:mm:ss");
/*  75:    */  
/*  81: 81 */  public static final FastDateFormat ISO_TIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("'T'HH:mm:ssZZ");
/*  82:    */  
/*  90: 90 */  public static final FastDateFormat ISO_TIME_NO_T_FORMAT = FastDateFormat.getInstance("HH:mm:ss");
/*  91:    */  
/*  99: 99 */  public static final FastDateFormat ISO_TIME_NO_T_TIME_ZONE_FORMAT = FastDateFormat.getInstance("HH:mm:ssZZ");
/* 100:    */  
/* 106:106 */  public static final FastDateFormat SMTP_DATETIME_FORMAT = FastDateFormat.getInstance("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
/* 107:    */  
/* 126:    */  public static String formatUTC(long millis, String pattern)
/* 127:    */  {
/* 128:128 */    return format(new Date(millis), pattern, UTC_TIME_ZONE, null);
/* 129:    */  }
/* 130:    */  
/* 137:    */  public static String formatUTC(Date date, String pattern)
/* 138:    */  {
/* 139:139 */    return format(date, pattern, UTC_TIME_ZONE, null);
/* 140:    */  }
/* 141:    */  
/* 149:    */  public static String formatUTC(long millis, String pattern, Locale locale)
/* 150:    */  {
/* 151:151 */    return format(new Date(millis), pattern, UTC_TIME_ZONE, locale);
/* 152:    */  }
/* 153:    */  
/* 161:    */  public static String formatUTC(Date date, String pattern, Locale locale)
/* 162:    */  {
/* 163:163 */    return format(date, pattern, UTC_TIME_ZONE, locale);
/* 164:    */  }
/* 165:    */  
/* 172:    */  public static String format(long millis, String pattern)
/* 173:    */  {
/* 174:174 */    return format(new Date(millis), pattern, null, null);
/* 175:    */  }
/* 176:    */  
/* 183:    */  public static String format(Date date, String pattern)
/* 184:    */  {
/* 185:185 */    return format(date, pattern, null, null);
/* 186:    */  }
/* 187:    */  
/* 196:    */  public static String format(Calendar calendar, String pattern)
/* 197:    */  {
/* 198:198 */    return format(calendar, pattern, null, null);
/* 199:    */  }
/* 200:    */  
/* 208:    */  public static String format(long millis, String pattern, TimeZone timeZone)
/* 209:    */  {
/* 210:210 */    return format(new Date(millis), pattern, timeZone, null);
/* 211:    */  }
/* 212:    */  
/* 220:    */  public static String format(Date date, String pattern, TimeZone timeZone)
/* 221:    */  {
/* 222:222 */    return format(date, pattern, timeZone, null);
/* 223:    */  }
/* 224:    */  
/* 234:    */  public static String format(Calendar calendar, String pattern, TimeZone timeZone)
/* 235:    */  {
/* 236:236 */    return format(calendar, pattern, timeZone, null);
/* 237:    */  }
/* 238:    */  
/* 246:    */  public static String format(long millis, String pattern, Locale locale)
/* 247:    */  {
/* 248:248 */    return format(new Date(millis), pattern, null, locale);
/* 249:    */  }
/* 250:    */  
/* 258:    */  public static String format(Date date, String pattern, Locale locale)
/* 259:    */  {
/* 260:260 */    return format(date, pattern, null, locale);
/* 261:    */  }
/* 262:    */  
/* 272:    */  public static String format(Calendar calendar, String pattern, Locale locale)
/* 273:    */  {
/* 274:274 */    return format(calendar, pattern, null, locale);
/* 275:    */  }
/* 276:    */  
/* 285:    */  public static String format(long millis, String pattern, TimeZone timeZone, Locale locale)
/* 286:    */  {
/* 287:287 */    return format(new Date(millis), pattern, timeZone, locale);
/* 288:    */  }
/* 289:    */  
/* 298:    */  public static String format(Date date, String pattern, TimeZone timeZone, Locale locale)
/* 299:    */  {
/* 300:300 */    FastDateFormat df = FastDateFormat.getInstance(pattern, timeZone, locale);
/* 301:301 */    return df.format(date);
/* 302:    */  }
/* 303:    */  
/* 314:    */  public static String format(Calendar calendar, String pattern, TimeZone timeZone, Locale locale)
/* 315:    */  {
/* 316:316 */    FastDateFormat df = FastDateFormat.getInstance(pattern, timeZone, locale);
/* 317:317 */    return df.format(calendar);
/* 318:    */  }
/* 319:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.time.DateFormatUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */