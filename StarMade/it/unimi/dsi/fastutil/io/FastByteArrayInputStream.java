package it.unimi.dsi.fastutil.io;

public class FastByteArrayInputStream
  extends MeasurableInputStream
  implements RepositionableStream
{
  public byte[] array;
  public int offset;
  public int length;
  private int position;
  private int mark;
  
  public FastByteArrayInputStream(byte[] array, int offset, int length)
  {
    this.array = array;
    this.offset = offset;
    this.length = length;
  }
  
  public FastByteArrayInputStream(byte[] array)
  {
    this(array, 0, array.length);
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
    return this.length - this.position;
  }
  
  public long skip(long local_n)
  {
    if (local_n <= this.length - this.position)
    {
      this.position += (int)local_n;
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
    return this.array[(this.offset + this.position++)] & 0xFF;
  }
  
  public int read(byte[] local_b, int offset, int length)
  {
    if (this.length == this.position) {
      return length == 0 ? 0 : -1;
    }
    int local_n = Math.min(length, this.length - this.position);
    System.arraycopy(this.array, this.offset + this.position, local_b, offset, local_n);
    this.position += local_n;
    return local_n;
  }
  
  public long position()
  {
    return this.position;
  }
  
  public void position(long newPosition)
  {
    this.position = ((int)Math.min(newPosition, this.length));
  }
  
  public long length()
  {
    return this.length;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastByteArrayInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */