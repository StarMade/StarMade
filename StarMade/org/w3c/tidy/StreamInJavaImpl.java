package org.w3c.tidy;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class StreamInJavaImpl
  implements StreamIn
{
  private static final int CHARBUF_SIZE = 16;
  private int[] charbuf = new int[16];
  private int bufpos;
  private Reader reader;
  private boolean endOfStream;
  private boolean pushed;
  private int curcol;
  private int lastcol;
  private int curline;
  private int tabsize;
  private int tabs;

  protected StreamInJavaImpl(InputStream paramInputStream, String paramString, int paramInt)
    throws UnsupportedEncodingException
  {
    this.reader = new InputStreamReader(paramInputStream, paramString);
    this.pushed = false;
    this.tabsize = paramInt;
    this.curline = 1;
    this.curcol = 1;
    this.endOfStream = false;
  }

  protected StreamInJavaImpl(Reader paramReader, int paramInt)
  {
    this.reader = paramReader;
    this.pushed = false;
    this.tabsize = paramInt;
    this.curline = 1;
    this.curcol = 1;
    this.endOfStream = false;
  }

  public int readCharFromStream()
  {
    int i;
    try
    {
      i = this.reader.read();
      if (i < 0)
        this.endOfStream = true;
    }
    catch (IOException localIOException)
    {
      this.endOfStream = true;
      return -1;
    }
    return i;
  }

  public int readChar()
  {
    if (this.pushed)
    {
      i = this.charbuf[(--this.bufpos)];
      if (this.bufpos == 0)
        this.pushed = false;
      if (i == 10)
      {
        this.curcol = 1;
        this.curline += 1;
        return i;
      }
      this.curcol += 1;
      return i;
    }
    this.lastcol = this.curcol;
    if (this.tabs > 0)
    {
      this.curcol += 1;
      this.tabs -= 1;
      return 32;
    }
    int i = readCharFromStream();
    if (i < 0)
    {
      this.endOfStream = true;
      return -1;
    }
    if (i == 10)
    {
      this.curcol = 1;
      this.curline += 1;
      return i;
    }
    if (i == 13)
    {
      i = readCharFromStream();
      if (i != 10)
      {
        if (i != -1)
          ungetChar(i);
        i = 10;
      }
      this.curcol = 1;
      this.curline += 1;
      return i;
    }
    if (i == 9)
    {
      this.tabs = (this.tabsize - (this.curcol - 1) % this.tabsize - 1);
      this.curcol += 1;
      i = 32;
      return i;
    }
    this.curcol += 1;
    return i;
  }

  public void ungetChar(int paramInt)
  {
    this.pushed = true;
    if (this.bufpos >= 16)
    {
      System.arraycopy(this.charbuf, 0, this.charbuf, 1, 15);
      this.bufpos -= 1;
    }
    this.charbuf[(this.bufpos++)] = paramInt;
    if (paramInt == 10)
      this.curline -= 1;
    this.curcol = this.lastcol;
  }

  public boolean isEndOfStream()
  {
    return this.endOfStream;
  }

  public int getCurcol()
  {
    return this.curcol;
  }

  public int getCurline()
  {
    return this.curline;
  }

  public void setLexer(Lexer paramLexer)
  {
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.StreamInJavaImpl
 * JD-Core Version:    0.6.2
 */