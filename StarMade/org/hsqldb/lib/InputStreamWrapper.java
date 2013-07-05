package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamWrapper
  implements InputStreamInterface
{
  InputStream is;
  long limitSize = -1L;
  long fetchedSize = 0L;

  public InputStreamWrapper(InputStream paramInputStream)
  {
    this.is = paramInputStream;
  }

  public int read()
    throws IOException
  {
    if (this.fetchedSize == this.limitSize)
      return -1;
    int i = this.is.read();
    if (i < 0)
    {
      if (this.limitSize == -1L)
        return -1;
      throw new IOException("stream not reached the end" + this.fetchedSize + " " + this.limitSize);
    }
    this.fetchedSize += 1L;
    return i;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.fetchedSize == this.limitSize)
      return -1;
    if ((this.limitSize >= 0L) && (this.limitSize - this.fetchedSize < paramInt2))
      paramInt2 = (int)(this.limitSize - this.fetchedSize);
    int i = this.is.read(paramArrayOfByte, paramInt1, paramInt2);
    if (i < 0)
    {
      if (this.limitSize == -1L)
        return -1;
      throw new IOException("stream not reached the end" + this.fetchedSize + " " + this.limitSize);
    }
    this.fetchedSize += i;
    return i;
  }

  public long skip(long paramLong)
    throws IOException
  {
    return this.is.skip(paramLong);
  }

  public int available()
    throws IOException
  {
    return this.is.available();
  }

  public void close()
    throws IOException
  {
    this.is.close();
  }

  public void setSizeLimit(long paramLong)
  {
    this.limitSize = paramLong;
  }

  public long getSizeLimit()
  {
    return this.limitSize;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.InputStreamWrapper
 * JD-Core Version:    0.6.2
 */