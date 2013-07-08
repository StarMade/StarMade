package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamWrapper
  implements InputStreamInterface
{
  InputStream field_429;
  long limitSize = -1L;
  long fetchedSize = 0L;
  
  public InputStreamWrapper(InputStream paramInputStream)
  {
    this.field_429 = paramInputStream;
  }
  
  public int read()
    throws IOException
  {
    if (this.fetchedSize == this.limitSize) {
      return -1;
    }
    int i = this.field_429.read();
    if (i < 0)
    {
      if (this.limitSize == -1L) {
        return -1;
      }
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
    if (this.fetchedSize == this.limitSize) {
      return -1;
    }
    if ((this.limitSize >= 0L) && (this.limitSize - this.fetchedSize < paramInt2)) {
      paramInt2 = (int)(this.limitSize - this.fetchedSize);
    }
    int i = this.field_429.read(paramArrayOfByte, paramInt1, paramInt2);
    if (i < 0)
    {
      if (this.limitSize == -1L) {
        return -1;
      }
      throw new IOException("stream not reached the end" + this.fetchedSize + " " + this.limitSize);
    }
    this.fetchedSize += i;
    return i;
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    return this.field_429.skip(paramLong);
  }
  
  public int available()
    throws IOException
  {
    return this.field_429.available();
  }
  
  public void close()
    throws IOException
  {
    this.field_429.close();
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


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.InputStreamWrapper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */