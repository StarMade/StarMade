package org.hsqldb.rowio;

import java.io.IOException;
import org.hsqldb.error.Error;

public class RowInputTextQuoted
  extends RowInputText
{
  private static final int NORMAL_FIELD = 0;
  private static final int NEED_END_QUOTE = 1;
  private static final int FOUND_QUOTE = 2;
  private char[] qtext;
  
  public RowInputTextQuoted(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    super(paramString1, paramString2, paramString3, paramBoolean);
  }
  
  public void setSource(String paramString, long paramLong, int paramInt)
  {
    super.setSource(paramString, paramLong, paramInt);
    this.qtext = paramString.toCharArray();
  }
  
  protected String getField(String paramString, int paramInt, boolean paramBoolean)
    throws IOException
  {
    String str = null;
    if ((this.next >= this.qtext.length) || (this.qtext[this.next] != '"')) {
      return super.getField(paramString, paramInt, paramBoolean);
    }
    try
    {
      this.field += 1;
      StringBuffer localStringBuffer = new StringBuffer();
      int i = 0;
      int j = 0;
      int k = -1;
      if (!paramBoolean) {
        k = this.text.indexOf(paramString, this.next);
      }
      while (this.next < this.qtext.length)
      {
        switch (j)
        {
        case 0: 
        default: 
          if (this.next == k)
          {
            this.next += paramInt;
            i = 1;
          }
          else if (this.qtext[this.next] == '"')
          {
            j = 1;
          }
          else
          {
            localStringBuffer.append(this.qtext[this.next]);
          }
          break;
        case 1: 
          if (this.qtext[this.next] == '"') {
            j = 2;
          } else {
            localStringBuffer.append(this.qtext[this.next]);
          }
          break;
        case 2: 
          if (this.qtext[this.next] == '"')
          {
            localStringBuffer.append(this.qtext[this.next]);
            j = 1;
          }
          else
          {
            this.next += paramInt - 1;
            j = 0;
            if (!paramBoolean)
            {
              this.next += 1;
              i = 1;
            }
          }
          break;
        }
        if (i != 0) {
          break;
        }
        this.next += 1;
      }
      str = localStringBuffer.toString();
    }
    catch (Exception localException)
    {
      Object[] arrayOfObject = { new Integer(this.field), localException.toString() };
      throw new IOException(Error.getMessage(41, 0, arrayOfObject));
    }
    return str;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.rowio.RowInputTextQuoted
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */