package org.hsqldb;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.StringUtil;

public class HsqlDateTime
{
  private static Locale defaultLocale = Locale.UK;
  private static long currentDateMillis = getNormalisedDate(System.currentTimeMillis());
  public static final Calendar tempCalDefault = new GregorianCalendar();
  public static final Calendar tempCalGMT = new GregorianCalendar(TimeZone.getTimeZone("GMT"), defaultLocale);
  private static final Date tempDate = new Date(0L);
  private static final String sdfdPattern = "yyyy-MM-dd";
  static SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
  private static final String sdftPattern = "HH:mm:ss";
  static SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
  private static final String sdftsPattern = "yyyy-MM-dd HH:mm:ss";
  static SimpleDateFormat sdfts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static final String sdftsSysPattern = "yyyy-MM-dd HH:mm:ss.SSS";
  static SimpleDateFormat sdftsSys = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  private static Date sysDate = new Date();
  private static final char[][] dateTokens = { { 'R', 'R', 'R', 'R' }, { 'I', 'Y', 'Y', 'Y' }, { 'Y', 'Y', 'Y', 'Y' }, { 'I', 'Y' }, { 'Y', 'Y' }, { 'B', 'C' }, { 'B', '.', 'C', '.' }, { 'A', 'D' }, { 'A', '.', 'D', '.' }, { 'M', 'O', 'N' }, { 'M', 'O', 'N', 'T', 'H' }, { 'M', 'M' }, { 'D', 'A', 'Y' }, { 'D', 'Y' }, { 'W', 'W' }, { 'I', 'W' }, { 'D', 'D' }, { 'D', 'D', 'D' }, { 'W' }, { 'H', 'H', '2', '4' }, { 'H', 'H', '1', '2' }, { 'H', 'H' }, { 'M', 'I' }, { 'S', 'S' }, { 'A', 'M' }, { 'P', 'M' }, { 'A', '.', 'M', '.' }, { 'P', '.', 'M', '.' }, { 'F', 'F' } };
  private static final String[] javaDateTokens = { "yyyy", "'*IYYY'", "yyyy", "'*IY'", "yy", "G", "G", "G", "G", "MMM", "MMMMM", "MM", "EEEE", "EE", "'*WW'", "w", "dd", "D", "'*W'", "HH", "KK", "KK", "mm", "ss", "aaa", "aaa", "aaa", "aaa", "S" };
  private static final int[] sqlIntervalCodes = { -1, -1, 101, -1, 101, -1, -1, -1, -1, 102, 102, 102, -1, -1, 262, -1, 103, 103, -1, 104, -1, 104, 105, 106, -1, -1, -1, -1, -1 };
  private static final char e = 'ð¿¿';
  
  /* Error */
  public static long getDateSeconds(String paramString)
  {
    // Byte code:
    //   0: getstatic 3	org/hsqldb/HsqlDateTime:sdfd	Ljava/text/SimpleDateFormat;
    //   3: dup
    //   4: astore_1
    //   5: monitorenter
    //   6: getstatic 3	org/hsqldb/HsqlDateTime:sdfd	Ljava/text/SimpleDateFormat;
    //   9: aload_0
    //   10: invokevirtual 4	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   13: astore_2
    //   14: aload_2
    //   15: invokevirtual 5	java/util/Date:getTime	()J
    //   18: ldc2_w 6
    //   21: ldiv
    //   22: aload_1
    //   23: monitorexit
    //   24: lreturn
    //   25: astore_3
    //   26: aload_1
    //   27: monitorexit
    //   28: aload_3
    //   29: athrow
    //   30: astore_1
    //   31: sipush 3407
    //   34: invokestatic 9	org/hsqldb/error/Error:error	(I)Lorg/hsqldb/HsqlException;
    //   37: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	paramString	String
    //   30	1	1	localException	Exception
    //   13	2	2	localDate	Date
    //   25	4	3	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   6	24	25	finally
    //   25	28	25	finally
    //   0	24	30	java/lang/Exception
    //   25	30	30	java/lang/Exception
  }
  
  public static String getDateString(long paramLong)
  {
    synchronized (sdfd)
    {
      sysDate.setTime(paramLong * 1000L);
      return sdfd.format(sysDate);
    }
  }
  
  /* Error */
  public static long getTimestampSeconds(String paramString)
  {
    // Byte code:
    //   0: getstatic 13	org/hsqldb/HsqlDateTime:sdfts	Ljava/text/SimpleDateFormat;
    //   3: dup
    //   4: astore_1
    //   5: monitorenter
    //   6: getstatic 13	org/hsqldb/HsqlDateTime:sdfts	Ljava/text/SimpleDateFormat;
    //   9: aload_0
    //   10: invokevirtual 4	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   13: astore_2
    //   14: aload_2
    //   15: invokevirtual 5	java/util/Date:getTime	()J
    //   18: ldc2_w 6
    //   21: ldiv
    //   22: aload_1
    //   23: monitorexit
    //   24: lreturn
    //   25: astore_3
    //   26: aload_1
    //   27: monitorexit
    //   28: aload_3
    //   29: athrow
    //   30: astore_1
    //   31: sipush 3407
    //   34: invokestatic 9	org/hsqldb/error/Error:error	(I)Lorg/hsqldb/HsqlException;
    //   37: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	paramString	String
    //   30	1	1	localException	Exception
    //   13	2	2	localDate	Date
    //   25	4	3	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   6	24	25	finally
    //   25	28	25	finally
    //   0	24	30	java/lang/Exception
    //   25	30	30	java/lang/Exception
  }
  
  public static void getTimestampString(StringBuffer paramStringBuffer, long paramLong, int paramInt1, int paramInt2)
  {
    synchronized (sdfts)
    {
      tempDate.setTime(paramLong * 1000L);
      paramStringBuffer.append(sdfts.format(tempDate));
      if (paramInt2 > 0)
      {
        paramStringBuffer.append('.');
        paramStringBuffer.append(StringUtil.toZeroPaddedString(paramInt1, 9, paramInt2));
      }
    }
  }
  
  public static String getTimestampString(long paramLong)
  {
    synchronized (sdfts)
    {
      sysDate.setTime(paramLong);
      return sdfts.format(sysDate);
    }
  }
  
  public static synchronized long getCurrentDateMillis(long paramLong)
  {
    if (paramLong - currentDateMillis >= 86400000L) {
      currentDateMillis = getNormalisedDate(paramLong);
    }
    return currentDateMillis;
  }
  
  public static String getSystemTimeString()
  {
    synchronized (sdftsSys)
    {
      sysDate.setTime(System.currentTimeMillis());
      return sdftsSys.format(sysDate);
    }
  }
  
  private static void resetToDate(Calendar paramCalendar)
  {
    paramCalendar.set(11, 0);
    paramCalendar.set(12, 0);
    paramCalendar.set(13, 0);
    paramCalendar.set(14, 0);
  }
  
  private static void resetToTime(Calendar paramCalendar)
  {
    paramCalendar.set(1, 1970);
    paramCalendar.set(2, 0);
    paramCalendar.set(5, 1);
    paramCalendar.set(14, 0);
  }
  
  public static long convertMillisToCalendar(Calendar paramCalendar, long paramLong)
  {
    paramCalendar.clear();
    synchronized (tempCalGMT)
    {
      synchronized (paramCalendar)
      {
        tempCalGMT.setTimeInMillis(paramLong);
        paramCalendar.set(tempCalGMT.get(1), tempCalGMT.get(2), tempCalGMT.get(5), tempCalGMT.get(11), tempCalGMT.get(12), tempCalGMT.get(13));
        return paramCalendar.getTimeInMillis();
      }
    }
  }
  
  public static long convertMillisFromCalendar(Calendar paramCalendar, long paramLong)
  {
    synchronized (tempCalGMT)
    {
      synchronized (paramCalendar)
      {
        tempCalGMT.clear();
        paramCalendar.setTimeInMillis(paramLong);
        tempCalGMT.set(paramCalendar.get(1), paramCalendar.get(2), paramCalendar.get(5), paramCalendar.get(11), paramCalendar.get(12), paramCalendar.get(13));
        return tempCalGMT.getTimeInMillis();
      }
    }
  }
  
  public static void setTimeInMillis(Calendar paramCalendar, long paramLong)
  {
    paramCalendar.setTimeInMillis(paramLong);
  }
  
  public static long getTimeInMillis(Calendar paramCalendar)
  {
    return paramCalendar.getTimeInMillis();
  }
  
  public static long convertToNormalisedTime(long paramLong)
  {
    return convertToNormalisedTime(paramLong, tempCalGMT);
  }
  
  public static long convertToNormalisedTime(long paramLong, Calendar paramCalendar)
  {
    synchronized (paramCalendar)
    {
      setTimeInMillis(paramCalendar, paramLong);
      resetToDate(paramCalendar);
      long l = getTimeInMillis(paramCalendar);
      return paramLong - l;
    }
  }
  
  public static long convertToNormalisedDate(long paramLong, Calendar paramCalendar)
  {
    synchronized (paramCalendar)
    {
      setTimeInMillis(paramCalendar, paramLong);
      resetToDate(paramCalendar);
      return getTimeInMillis(paramCalendar);
    }
  }
  
  public static long getNormalisedTime(long paramLong)
  {
    Calendar localCalendar = tempCalGMT;
    synchronized (localCalendar)
    {
      setTimeInMillis(localCalendar, paramLong);
      resetToTime(localCalendar);
      return getTimeInMillis(localCalendar);
    }
  }
  
  public static long getNormalisedTime(Calendar paramCalendar, long paramLong)
  {
    synchronized (paramCalendar)
    {
      setTimeInMillis(paramCalendar, paramLong);
      resetToTime(paramCalendar);
      return getTimeInMillis(paramCalendar);
    }
  }
  
  public static long getNormalisedDate(long paramLong)
  {
    synchronized (tempCalGMT)
    {
      setTimeInMillis(tempCalGMT, paramLong);
      resetToDate(tempCalGMT);
      return getTimeInMillis(tempCalGMT);
    }
  }
  
  public static long getNormalisedDate(Calendar paramCalendar, long paramLong)
  {
    synchronized (paramCalendar)
    {
      setTimeInMillis(paramCalendar, paramLong);
      resetToDate(paramCalendar);
      return getTimeInMillis(paramCalendar);
    }
  }
  
  public static int getZoneSeconds(Calendar paramCalendar)
  {
    return (paramCalendar.get(15) + paramCalendar.get(16)) / 1000;
  }
  
  public static int getZoneMillis(Calendar paramCalendar, long paramLong)
  {
    return paramCalendar.getTimeZone().getOffset(paramLong);
  }
  
  public static int getDateTimePart(long paramLong, int paramInt)
  {
    synchronized (tempCalGMT)
    {
      tempCalGMT.setTimeInMillis(paramLong);
      return tempCalGMT.get(paramInt);
    }
  }
  
  public static long getTruncatedPart(long paramLong, int paramInt)
  {
    synchronized (tempCalGMT)
    {
      tempCalGMT.setTimeInMillis(paramLong);
      switch (paramInt)
      {
      case 262: 
        int i = tempCalGMT.get(1);
        int j = tempCalGMT.get(3);
        tempCalGMT.clear();
        tempCalGMT.set(1, i);
        tempCalGMT.set(3, j);
        break;
      default: 
        zeroFromPart(tempCalGMT, paramInt);
      }
      return tempCalGMT.getTimeInMillis();
    }
  }
  
  public static long getRoundedPart(long paramLong, int paramInt)
  {
    synchronized (tempCalGMT)
    {
      tempCalGMT.setTimeInMillis(paramLong);
      switch (paramInt)
      {
      case 101: 
        if (tempCalGMT.get(2) > 6) {
          tempCalGMT.add(1, 1);
        }
        break;
      case 102: 
        if (tempCalGMT.get(5) > 15) {
          tempCalGMT.add(2, 1);
        }
        break;
      case 103: 
        if (tempCalGMT.get(11) > 11) {
          tempCalGMT.add(5, 1);
        }
        break;
      case 104: 
        if (tempCalGMT.get(12) > 29) {
          tempCalGMT.add(11, 1);
        }
        break;
      case 105: 
        if (tempCalGMT.get(13) > 29) {
          tempCalGMT.add(12, 1);
        }
        break;
      case 106: 
        if (tempCalGMT.get(14) > 499) {
          tempCalGMT.add(13, 1);
        }
        break;
      case 262: 
        int i = tempCalGMT.get(1);
        int j = tempCalGMT.get(3);
        int k = tempCalGMT.get(7);
        if (k > 3) {
          j++;
        }
        tempCalGMT.clear();
        tempCalGMT.set(1, i);
        tempCalGMT.set(3, j);
        return tempCalGMT.getTimeInMillis();
      }
      zeroFromPart(tempCalGMT, paramInt);
      return tempCalGMT.getTimeInMillis();
    }
  }
  
  static void zeroFromPart(Calendar paramCalendar, int paramInt)
  {
    switch (paramInt)
    {
    case 101: 
      paramCalendar.set(2, 0);
    case 102: 
      paramCalendar.set(5, 1);
    case 103: 
      paramCalendar.set(11, 0);
    case 104: 
      paramCalendar.set(12, 0);
    case 105: 
      paramCalendar.set(13, 0);
    case 106: 
      paramCalendar.set(14, 0);
    }
  }
  
  public static Date toDate(String paramString1, String paramString2, SimpleDateFormat paramSimpleDateFormat)
  {
    String str = toJavaDatePattern(paramString2);
    int i = str.indexOf("*IY");
    if (i >= 0) {
      throw Error.error(3472);
    }
    i = str.indexOf("*WW");
    if (i >= 0) {
      throw Error.error(3472);
    }
    i = str.indexOf("*W");
    if (i >= 0) {
      throw Error.error(3472);
    }
    Date localDate;
    try
    {
      paramSimpleDateFormat.applyPattern(str);
      localDate = paramSimpleDateFormat.parse(paramString1);
    }
    catch (Exception localException)
    {
      throw Error.error(3407, localException.toString());
    }
    return localDate;
  }
  
  public static String toFormattedDate(Date paramDate, String paramString, SimpleDateFormat paramSimpleDateFormat)
  {
    String str1 = toJavaDatePattern(paramString);
    try
    {
      paramSimpleDateFormat.applyPattern(str1);
    }
    catch (Exception localException)
    {
      throw Error.error(3472);
    }
    String str2 = paramSimpleDateFormat.format(paramDate);
    int i = str2.indexOf("*IY");
    Calendar localCalendar;
    int j;
    int k;
    int m;
    if (i >= 0)
    {
      localCalendar = paramSimpleDateFormat.getCalendar();
      j = 3;
      k = str2.indexOf("*IYYY");
      if (k >= 0)
      {
        j = 5;
        i = k;
      }
      m = localCalendar.get(1);
      int n = localCalendar.get(3);
      if ((n == 1) && (localCalendar.get(6) > 360)) {
        m++;
      }
      String str3 = String.valueOf(m);
      if (j == 3) {
        str3 = str3.substring(str3.length() - 2);
      }
      StringBuilder localStringBuilder2 = new StringBuilder(str2);
      localStringBuilder2.replace(i, i + j, str3);
      str2 = localStringBuilder2.toString();
    }
    i = str2.indexOf("*WW");
    StringBuilder localStringBuilder1;
    if (i >= 0)
    {
      localCalendar = paramSimpleDateFormat.getCalendar();
      j = 3;
      k = localCalendar.get(6);
      m = (k - 1) / 7 + 1;
      localStringBuilder1 = new StringBuilder(str2);
      localStringBuilder1.replace(i, i + j, String.valueOf(m));
      str2 = localStringBuilder1.toString();
    }
    i = str2.indexOf("*W");
    if (i >= 0)
    {
      localCalendar = paramSimpleDateFormat.getCalendar();
      j = 2;
      k = localCalendar.get(5);
      m = (k - 1) / 7 + 1;
      localStringBuilder1 = new StringBuilder(str2);
      localStringBuilder1.replace(i, i + j, String.valueOf(m));
      str2 = localStringBuilder1.toString();
    }
    return str2;
  }
  
  public static String toJavaDatePattern(String paramString)
  {
    int i = paramString.length();
    StringBuffer localStringBuffer = new StringBuffer(i);
    Tokenizer localTokenizer = new Tokenizer();
    for (int k = 0; k <= i; k++)
    {
      int j = k == i ? 65535 : paramString.charAt(k);
      if (localTokenizer.isInQuotes())
      {
        if (localTokenizer.isQuoteChar(j)) {
          j = 39;
        } else if (j == 39) {
          localStringBuffer.append(j);
        }
        localStringBuffer.append(j);
      }
      else if (!localTokenizer.next(j, k))
      {
        if (localTokenizer.consumed)
        {
          int m = localTokenizer.getLastMatch();
          localStringBuffer.append(javaDateTokens[m]);
          k = localTokenizer.matchOffset;
        }
        else if (localTokenizer.isQuoteChar(j))
        {
          j = 39;
          localStringBuffer.append(j);
        }
        else if (localTokenizer.isLiteral(j))
        {
          localStringBuffer.append(j);
        }
        else if (j != 65535)
        {
          throw Error.error(3407, paramString.substring(k));
        }
        localTokenizer.reset();
      }
    }
    if (localTokenizer.isInQuotes()) {
      throw Error.error(3407);
    }
    String str = localStringBuffer.toString();
    return str;
  }
  
  public static int toStandardIntervalPart(String paramString)
  {
    int i = paramString.length();
    Tokenizer localTokenizer = new Tokenizer();
    for (int j = 0; j <= i; j++)
    {
      char c = j == i ? 65535 : paramString.charAt(j);
      if (!localTokenizer.next(c, j))
      {
        int k = localTokenizer.getLastMatch();
        if (k >= 0) {
          return sqlIntervalCodes[k];
        }
        return -1;
      }
    }
    return -1;
  }
  
  static
  {
    tempCalGMT.setLenient(false);
    sdfd.setCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"), defaultLocale));
    sdfd.setLenient(false);
    sdft.setCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"), defaultLocale));
    sdft.setLenient(false);
    sdfts.setCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"), defaultLocale));
    sdfts.setLenient(false);
  }
  
  static class Tokenizer
  {
    private int lastMatched;
    private int matchOffset;
    private int offset;
    private long state;
    private boolean consumed;
    private boolean isInQuotes;
    private boolean matched;
    private final char quoteChar = '"';
    private final char[] literalChars = defaultLiterals;
    private static char[] defaultLiterals = { ' ', ',', '-', '.', '/', ':', ';' };
    char[][] tokens = HsqlDateTime.dateTokens;
    
    public Tokenizer()
    {
      reset();
    }
    
    public void reset()
    {
      this.lastMatched = -1;
      this.offset = -1;
      this.state = 0L;
      this.consumed = false;
      this.matched = false;
    }
    
    public int length()
    {
      return this.offset;
    }
    
    public int getLastMatch()
    {
      return this.lastMatched;
    }
    
    public boolean isConsumed()
    {
      return this.consumed;
    }
    
    public boolean wasMatched()
    {
      return this.matched;
    }
    
    public boolean isInQuotes()
    {
      return this.isInQuotes;
    }
    
    public boolean isQuoteChar(char paramChar)
    {
      if (this.quoteChar == paramChar)
      {
        this.isInQuotes = (!this.isInQuotes);
        return true;
      }
      return false;
    }
    
    public boolean isLiteral(char paramChar)
    {
      return ArrayUtil.isInSortedArray(paramChar, this.literalChars);
    }
    
    private boolean isZeroBit(int paramInt)
    {
      return (this.state & 1L << paramInt) == 0L;
    }
    
    private void setBit(int paramInt)
    {
      this.state |= 1L << paramInt;
    }
    
    public boolean next(char paramChar, int paramInt)
    {
      int i = ++this.offset;
      int j = this.offset + 1;
      int k = 0;
      this.matched = false;
      int m = this.tokens.length;
      for (;;)
      {
        m--;
        if (m < 0) {
          break;
        }
        if (isZeroBit(m)) {
          if (this.tokens[m][i] == paramChar)
          {
            if (this.tokens[m].length == j)
            {
              setBit(m);
              this.lastMatched = m;
              this.consumed = true;
              this.matched = true;
              this.matchOffset = paramInt;
            }
            else
            {
              k++;
            }
          }
          else {
            setBit(m);
          }
        }
      }
      return k > 0;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.HsqlDateTime
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */