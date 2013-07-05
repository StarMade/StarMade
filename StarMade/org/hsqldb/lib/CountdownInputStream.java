package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;

public final class CountdownInputStream extends InputStream
{
  private long m_count;
  private InputStream m_input;

  public CountdownInputStream(InputStream paramInputStream)
  {
    this.m_input = paramInputStream;
  }

  public int read()
    throws IOException
  {
    if (this.m_count <= 0L)
      return -1;
    int i = this.m_input.read();
    if (i >= 0)
      this.m_count -= 1L;
    return i;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new NullPointerException();
    if (this.m_count <= 0L)
      return -1;
    int i = paramArrayOfByte.length;
    if (i > this.m_count)
      i = (int)this.m_count;
    int j = this.m_input.read(paramArrayOfByte, 0, i);
    if (j > 0)
      this.m_count -= j;
    return j;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new NullPointerException();
    if (this.m_count <= 0L)
      return -1;
    if (paramInt2 > this.m_count)
      paramInt2 = (int)this.m_count;
    int i = this.m_input.read(paramArrayOfByte, paramInt1, paramInt2);
    if (i > 0)
      this.m_count -= i;
    return i;
  }

  public void close()
    throws IOException
  {
    this.m_input.close();
  }

  public int available()
    throws IOException
  {
    return Math.min(this.m_input.available(), (int)Math.min(2147483647L, this.m_count));
  }

  public long skip(long paramLong)
    throws IOException
  {
    return paramLong <= 0L ? 0L : this.m_input.skip(Math.min(this.m_count, paramLong));
  }

  public long getCount()
  {
    return this.m_count;
  }

  public void setCount(long paramLong)
  {
    this.m_count = paramLong;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.CountdownInputStream
 * JD-Core Version:    0.6.2
 */