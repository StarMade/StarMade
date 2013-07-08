package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;

public class StringInputStream
  extends InputStream
{
  protected int strOffset = 0;
  protected int charOffset = 0;
  protected int available;
  protected String str;
  
  public StringInputStream(String paramString)
  {
    this.str = paramString;
    this.available = (paramString.length() * 2);
  }
  
  public int read()
    throws IOException
  {
    if (this.available == 0) {
      return -1;
    }
    this.available -= 1;
    int i = this.str.charAt(this.strOffset);
    if (this.charOffset == 0)
    {
      this.charOffset = 1;
      return (i & 0xFF00) >> 8;
    }
    this.charOffset = 0;
    this.strOffset += 1;
    return i & 0xFF;
  }
  
  public int available()
    throws IOException
  {
    return this.available;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.StringInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */