package org.hsqldb.lib;

import java.io.LineNumberReader;
import org.hsqldb.store.ValuePool;

public class LineGroupReader
{
  private static final String[] defaultContinuations = { " ", "*" };
  private static final String[] defaultIgnoredStarts = { "--" };
  static final String LS = System.getProperty("line.separator", "\n");
  LineNumberReader reader;
  String nextStartLine = null;
  int startLineNumber = 0;
  int nextStartLineNumber = 0;
  final String[] sectionContinuations;
  final String[] sectionStarts;
  final String[] ignoredStarts;
  
  public LineGroupReader(LineNumberReader paramLineNumberReader)
  {
    this.sectionContinuations = defaultContinuations;
    this.sectionStarts = ValuePool.emptyStringArray;
    this.ignoredStarts = defaultIgnoredStarts;
    this.reader = paramLineNumberReader;
    try
    {
      getSection();
    }
    catch (Exception localException) {}
  }
  
  public LineGroupReader(LineNumberReader paramLineNumberReader, String[] paramArrayOfString)
  {
    this.sectionStarts = paramArrayOfString;
    this.sectionContinuations = ValuePool.emptyStringArray;
    this.ignoredStarts = ValuePool.emptyStringArray;
    this.reader = paramLineNumberReader;
    try
    {
      getSection();
    }
    catch (Exception localException) {}
  }
  
  public HsqlArrayList getSection()
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    if (this.nextStartLine != null)
    {
      localHsqlArrayList.add(this.nextStartLine);
      this.startLineNumber = this.nextStartLineNumber;
    }
    for (;;)
    {
      int i = 0;
      String str = null;
      try
      {
        str = this.reader.readLine();
      }
      catch (Exception localException) {}
      if (str == null)
      {
        this.nextStartLine = null;
        return localHsqlArrayList;
      }
      str = str.substring(0, StringUtil.rightTrimSize(str));
      if ((str.length() != 0) && (!isIgnoredLine(str)))
      {
        if (isNewSectionLine(str)) {
          i = 1;
        }
        if (i != 0)
        {
          this.nextStartLine = str;
          this.nextStartLineNumber = this.reader.getLineNumber();
          return localHsqlArrayList;
        }
        localHsqlArrayList.add(str);
      }
    }
  }
  
  public HashMappedList getAsMap()
  {
    HashMappedList localHashMappedList = new HashMappedList();
    for (;;)
    {
      HsqlArrayList localHsqlArrayList = getSection();
      if (localHsqlArrayList.size() < 1) {
        break;
      }
      String str1 = (String)localHsqlArrayList.get(0);
      String str2 = convertToString(localHsqlArrayList, 1);
      localHashMappedList.put(str1, str2);
    }
    return localHashMappedList;
  }
  
  private boolean isNewSectionLine(String paramString)
  {
    if (this.sectionStarts.length == 0)
    {
      for (i = 0; i < this.sectionContinuations.length; i++) {
        if (paramString.startsWith(this.sectionContinuations[i])) {
          return false;
        }
      }
      return true;
    }
    for (int i = 0; i < this.sectionStarts.length; i++) {
      if (paramString.startsWith(this.sectionStarts[i])) {
        return true;
      }
    }
    return false;
  }
  
  private boolean isIgnoredLine(String paramString)
  {
    for (int i = 0; i < this.ignoredStarts.length; i++) {
      if (paramString.startsWith(this.ignoredStarts[i])) {
        return true;
      }
    }
    return false;
  }
  
  public int getStartLineNumber()
  {
    return this.startLineNumber;
  }
  
  public void close()
  {
    try
    {
      this.reader.close();
    }
    catch (Exception localException) {}
  }
  
  public static String convertToString(HsqlArrayList paramHsqlArrayList, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = paramInt; i < paramHsqlArrayList.size(); i++) {
      localStringBuffer.append(paramHsqlArrayList.get(i)).append(LS);
    }
    return localStringBuffer.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.LineGroupReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */