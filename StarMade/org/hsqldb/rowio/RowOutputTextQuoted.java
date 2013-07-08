package org.hsqldb.rowio;

import org.hsqldb.lib.StringConverter;

public class RowOutputTextQuoted
  extends RowOutputText
{
  public RowOutputTextQuoted(String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4)
  {
    super(paramString1, paramString2, paramString3, paramBoolean, paramString4);
  }
  
  protected String checkConvertString(String paramString1, String paramString2)
  {
    if ((this.allQuoted) || (paramString1.length() == 0) || (paramString1.indexOf('"') != -1) || ((paramString2.length() > 0) && (paramString1.indexOf(paramString2) != -1)) || (hasUnprintable(paramString1))) {
      paramString1 = StringConverter.toQuotedString(paramString1, '"', true);
    }
    return paramString1;
  }
  
  private static boolean hasUnprintable(String paramString)
  {
    int i = 0;
    int j = paramString.length();
    while (i < j)
    {
      if (Character.isISOControl(paramString.charAt(i))) {
        return true;
      }
      i++;
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.rowio.RowOutputTextQuoted
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */