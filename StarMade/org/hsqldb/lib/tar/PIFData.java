package org.hsqldb.lib.tar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PIFData extends HashMap<String, String>
{
  static final long serialVersionUID = 3086795680582315773L;
  private static Pattern pifRecordPattern = Pattern.compile("\\d+ +([^=]+)=(.*)");
  private Long sizeObject = null;

  public Long getSize()
  {
    return this.sizeObject;
  }

  public PIFData(InputStream paramInputStream)
    throws TarMalformatException, IOException
  {
    BufferedReader localBufferedReader = null;
    try
    {
      localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream, "UTF-8"));
      int i = 0;
      while ((str1 = localBufferedReader.readLine()) != null)
      {
        i++;
        Matcher localMatcher = pifRecordPattern.matcher(str1);
        if (!localMatcher.matches())
          throw new TarMalformatException(RB.pif_malformat.getString(i, str1));
        String str2 = localMatcher.group(1);
        String str3 = localMatcher.group(2);
        if ((str3 == null) || (str3.length() < 1))
          remove(str2);
        else
          put(str2, str3);
      }
    }
    finally
    {
      try
      {
        paramInputStream.close();
      }
      finally
      {
        localBufferedReader = null;
      }
    }
    String str1 = (String)get("size");
    if (str1 != null)
      try
      {
        this.sizeObject = Long.valueOf(str1);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new TarMalformatException(RB.pif_malformat_size.getString(new String[] { str1 }));
      }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.tar.PIFData
 * JD-Core Version:    0.6.2
 */