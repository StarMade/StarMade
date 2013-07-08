package it.unimi.dsi.fastutil.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class FastMultiByteArrayInputStream
  extends MeasurableInputStream
  implements RepositionableStream
{
  public static final int SLICE_BITS = 30;
  public static final int SLICE_SIZE = 1073741824;
  public static final int SLICE_MASK = 1073741823;
  public byte[][] array;
  public long length;
  private long position;
  private long mark;
  
  public FastMultiByteArrayInputStream(MeasurableInputStream local_is)
    throws IOException
  {
    this(local_is, local_is.length());
  }
  
  public FastMultiByteArrayInputStream(InputStream local_is, long size)
    throws IOException
  {
    this.length = size;
    this.array = new byte[(int)((size + 1073741824L - 1L) / 1073741824L)][];
    for (int local_i = 0; local_i < this.array.length; local_i++)
    {
      this.array[local_i] = new byte[size >= 1073741824L ? 1073741824 : (int)size];
      if (local_is.read(this.array[local_i]) != this.array[local_i].length) {
        throw new EOFException();
      }
      size -= this.array[local_i].length;
    }
  }
  
  public FastMultiByteArrayInputStream(FastMultiByteArrayInputStream local_is)
  {
    this.array = local_is.array;
    this.length = local_is.length;
  }
  
  public FastMultiByteArrayInputStream(byte[] array)
  {
    this.array = new byte[1][];
    this.array[0] = array;
    this.length = array.length;
  }
  
  public boolean markSupported()
  {
    return true;
  }
  
  public void reset()
  {
    this.position = this.mark;
  }
  
  public void close() {}
  
  public void mark(int dummy)
  {
    this.mark = this.position;
  }
  
  public int available()
  {
    if (this.length - this.position > 2147483647L) {
      return 2147483647;
    }
    return (int)(this.length - this.position);
  }
  
  public long skip(long local_n)
  {
    if (local_n <= this.length - this.position)
    {
      this.position += local_n;
      return local_n;
    }
    local_n = this.length - this.position;
    this.position = this.length;
    return local_n;
  }
  
  public int read()
  {
    if (this.length == this.position) {
      return -1;
    }
    return this.array[((int)(this.position >>> 30))][((int)(this.position++ & 0x3FFFFFFF))] & 0xFF;
  }
  
  public int read(byte[] local_b, int offset, int length)
  {
    if (this.length == this.position) {
      return length == 0 ? 0 : -1;
    }
    int local_n = (int)Math.min(length, this.length - this.position);
    int local_m = local_n;
    do
    {
      int res = Math.min(local_n, this.array[((int)(this.position >>> 30))].length - (int)(this.position & 0x3FFFFFFF));
      System.arraycopy(this.array[((int)(this.position >>> 30))], (int)(this.position & 0x3FFFFFFF), local_b, offset, res);
      local_n -= res;
      offset += res;
      this.position += res;
    } while (local_n > 0);
    return local_m;
  }
  
  public long position()
  {
    return this.position;
  }
  
  public void position(long newPosition)
  {
    this.position = Math.min(newPosition, this.length);
  }
  
  public long length()
    throws IOException
  {
    return this.length;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastMultiByteArrayInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */