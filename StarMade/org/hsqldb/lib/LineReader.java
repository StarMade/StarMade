package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class LineReader
{
  boolean finished = false;
  boolean wasCR = false;
  boolean wasEOL = false;
  HsqlByteArrayOutputStream baOS = new HsqlByteArrayOutputStream(1024);
  final InputStream stream;
  final Charset charset;
  final String charsetName;
  
  public LineReader(InputStream paramInputStream, String paramString)
  {
    this.stream = paramInputStream;
    this.charsetName = paramString;
    this.charset = Charset.forName(paramString);
  }
  
  public String readLine()
    throws IOException
  {
    if (this.finished) {
      return null;
    }
    for (;;)
    {
      int i = this.stream.read();
      if (i == -1)
      {
        this.finished = true;
        if (this.baOS.size() != 0) {
          break;
        }
        return null;
      }
      switch (i)
      {
      case 13: 
        this.wasCR = true;
        break;
      case 10: 
        if (!this.wasCR) {
          break label107;
        }
        this.wasCR = false;
        break;
      default: 
        this.baOS.write(i);
        this.wasCR = false;
      }
    }
    label107:
    String str = new String(this.baOS.getBuffer(), 0, this.baOS.size(), this.charsetName);
    this.baOS.reset();
    return str;
  }
  
  public void close()
    throws IOException
  {
    this.stream.close();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.LineReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */