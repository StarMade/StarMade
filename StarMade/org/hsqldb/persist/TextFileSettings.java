package org.hsqldb.persist;

import org.hsqldb.Database;
import org.hsqldb.error.Error;

public class TextFileSettings
{
  public static final String NL = System.getProperty("line.separator");
  public String fs;
  public String vs;
  public String lvs;
  public String stringEncoding;
  public boolean isQuoted;
  public boolean isAllQuoted;
  public boolean ignoreFirst;
  Database database;
  String dataFileName;
  int maxCacheRows;
  int maxCacheBytes;
  static final byte[] BYTES_LINE_SEP = NL.getBytes();
  static final char DOUBLE_QUOTE_CHAR = '"';
  static final char BACKSLASH_CHAR = '\\';
  static final char LF_CHAR = '\n';
  static final char CR_CHAR = '\r';

  TextFileSettings(Database paramDatabase, String paramString)
  {
    this.database = paramDatabase;
    HsqlProperties localHsqlProperties = HsqlProperties.delimitedArgPairsToProps(paramString, "=", ";", "textdb");
    HsqlDatabaseProperties localHsqlDatabaseProperties = paramDatabase.getProperties();
    switch (localHsqlProperties.errorCodes.length)
    {
    case 0:
      this.dataFileName = null;
    case 1:
      this.dataFileName = localHsqlProperties.errorKeys[0].trim();
      break;
    default:
      throw Error.error(302);
    }
    this.fs = localHsqlDatabaseProperties.getStringProperty("textdb.fs");
    this.fs = localHsqlProperties.getProperty("textdb.fs", this.fs);
    this.vs = localHsqlDatabaseProperties.getStringProperty("textdb.vs");
    this.vs = localHsqlProperties.getProperty("textdb.vs", this.vs);
    this.lvs = localHsqlDatabaseProperties.getStringProperty("textdb.lvs");
    this.lvs = localHsqlProperties.getProperty("textdb.lvs", this.lvs);
    if (this.vs == null)
      this.vs = this.fs;
    if (this.lvs == null)
      this.lvs = this.fs;
    this.fs = translateSep(this.fs);
    this.vs = translateSep(this.vs);
    this.lvs = translateSep(this.lvs);
    if ((this.fs.length() == 0) || (this.vs.length() == 0) || (this.lvs.length() == 0))
      throw Error.error(303);
    this.ignoreFirst = localHsqlDatabaseProperties.isPropertyTrue("textdb.ignore_first");
    this.ignoreFirst = localHsqlProperties.isPropertyTrue("textdb.ignore_first", this.ignoreFirst);
    this.isQuoted = localHsqlDatabaseProperties.isPropertyTrue("textdb.quoted");
    this.isQuoted = localHsqlProperties.isPropertyTrue("textdb.quoted", this.isQuoted);
    this.isAllQuoted = localHsqlDatabaseProperties.isPropertyTrue("textdb.all_quoted");
    this.isAllQuoted = localHsqlProperties.isPropertyTrue("textdb.all_quoted", this.isAllQuoted);
    this.stringEncoding = localHsqlDatabaseProperties.getStringProperty("textdb.encoding");
    this.stringEncoding = localHsqlProperties.getProperty("textdb.encoding", this.stringEncoding);
    int i = localHsqlDatabaseProperties.getIntegerProperty("textdb.cache_scale");
    i = localHsqlProperties.getIntegerProperty("textdb.cache_scale", i);
    int j = localHsqlDatabaseProperties.getIntegerProperty("textdb.cache_size_scale");
    j = localHsqlProperties.getIntegerProperty("textdb.cache_size_scale", j);
    this.maxCacheRows = ((1 << i) * 3);
    this.maxCacheRows = localHsqlDatabaseProperties.getIntegerProperty("textdb.cache_rows", this.maxCacheRows);
    this.maxCacheRows = localHsqlProperties.getIntegerProperty("textdb.cache_rows", this.maxCacheRows);
    this.maxCacheBytes = ((1 << j) * this.maxCacheRows / 1024);
    if (this.maxCacheBytes < 4)
      this.maxCacheBytes = 4;
    this.maxCacheBytes = localHsqlDatabaseProperties.getIntegerProperty("textdb.cache_size", this.maxCacheBytes);
    this.maxCacheBytes = localHsqlProperties.getIntegerProperty("textdb.cache_size", this.maxCacheBytes);
    this.maxCacheBytes *= 1024;
  }

  String getFileName()
  {
    return this.dataFileName;
  }

  int getMaxCacheRows()
  {
    return this.maxCacheRows;
  }

  int getMaxCacheBytes()
  {
    return this.maxCacheBytes;
  }

  private static String translateSep(String paramString)
  {
    return translateSep(paramString, false);
  }

  private static String translateSep(String paramString, boolean paramBoolean)
  {
    if (paramString == null)
      return null;
    int i = paramString.indexOf('\\');
    if (i != -1)
    {
      int j = 0;
      char[] arrayOfChar = paramString.toCharArray();
      int k = 0;
      int m = paramString.length();
      StringBuffer localStringBuffer = new StringBuffer(m);
      do
      {
        localStringBuffer.append(arrayOfChar, j, i - j);
        i++;
        j = i;
        if (i >= m)
        {
          localStringBuffer.append('\\');
          break;
        }
        if (!paramBoolean)
          k = arrayOfChar[i];
        if (k == 110)
        {
          localStringBuffer.append('\n');
          j++;
        }
        else if (k == 114)
        {
          localStringBuffer.append('\r');
          j++;
        }
        else if (k == 116)
        {
          localStringBuffer.append('\t');
          j++;
        }
        else if (k == 92)
        {
          localStringBuffer.append('\\');
          j++;
        }
        else if (k == 117)
        {
          j++;
          localStringBuffer.append((char)Integer.parseInt(paramString.substring(j, j + 4), 16));
          j += 4;
        }
        else if (paramString.startsWith("semi", i))
        {
          localStringBuffer.append(';');
          j += 4;
        }
        else if (paramString.startsWith("space", i))
        {
          localStringBuffer.append(' ');
          j += 5;
        }
        else if (paramString.startsWith("quote", i))
        {
          localStringBuffer.append('"');
          j += 5;
        }
        else if (paramString.startsWith("apos", i))
        {
          localStringBuffer.append('\'');
          j += 4;
        }
        else
        {
          localStringBuffer.append('\\');
          localStringBuffer.append(arrayOfChar[i]);
          j++;
        }
      }
      while ((i = paramString.indexOf('\\', j)) != -1);
      localStringBuffer.append(arrayOfChar, j, m - j);
      paramString = localStringBuffer.toString();
    }
    return paramString;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.TextFileSettings
 * JD-Core Version:    0.6.2
 */