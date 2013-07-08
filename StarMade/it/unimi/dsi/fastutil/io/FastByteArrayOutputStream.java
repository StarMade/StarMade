package it.unimi.dsi.fastutil.io;

import it.unimi.dsi.fastutil.bytes.ByteArrays;
import java.io.IOException;

public class FastByteArrayOutputStream
  extends MeasurableOutputStream
  implements RepositionableStream
{
  public static final int DEFAULT_INITIAL_CAPACITY = 16;
  public byte[] array;
  public int length;
  private int position;
  
  public FastByteArrayOutputStream()
  {
    this(16);
  }
  
  public FastByteArrayOutputStream(int initialCapacity)
  {
    this.array = new byte[initialCapacity];
  }
  
  public FastByteArrayOutputStream(byte[] local_a)
  {
    this.array = local_a;
  }
  
  public void reset()
  {
    this.length = 0;
    this.position = 0;
  }
  
  public void trim()
  {
    this.array = ByteArrays.trim(this.array, this.length);
  }
  
  public void write(int local_b)
  {
    if (this.position == this.length)
    {
      this.length += 1;
      if (this.position == this.array.length) {
        this.array = ByteArrays.grow(this.array, this.length);
      }
    }
    this.array[(this.position++)] = ((byte)local_b);
  }
  
  public void write(byte[] local_b, int off, int len)
    throws IOException
  {
    ByteArrays.ensureOffsetLength(local_b, off, len);
    if (this.position + len > this.array.length) {
      this.array = ByteArrays.grow(this.array, this.position + len, this.position);
    }
    System.arraycopy(local_b, off, this.array, this.position, len);
    if (this.position + len > this.length) {
      this.length = (this.position += len);
    }
  }
  
  public void position(long newPosition)
  {
    if (this.position > 2147483647) {
      throw new IllegalArgumentException("Position too large: " + newPosition);
    }
    this.position = ((int)newPosition);
  }
  
  public long position()
  {
    return this.position;
  }
  
  public long length()
    throws IOException
  {
    return this.length;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastByteArrayOutputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */