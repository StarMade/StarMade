package it.unimi.dsi.fastutil.io;

import it.unimi.dsi.fastutil.bytes.ByteArrays;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.util.EnumSet;

public class FastBufferedInputStream
  extends MeasurableInputStream
  implements RepositionableStream
{
  public static final int DEFAULT_BUFFER_SIZE = 8192;
  public static final EnumSet<LineTerminator> ALL_TERMINATORS = EnumSet.allOf(LineTerminator.class);
  protected InputStream field_322;
  protected byte[] buffer;
  protected int pos;
  protected long readBytes;
  protected int avail;
  private FileChannel fileChannel;
  private RepositionableStream repositionableStream;
  private MeasurableStream measurableStream;
  
  public FastBufferedInputStream(InputStream local_is, int bufSize)
  {
    if (bufSize <= 0) {
      throw new IllegalArgumentException("Illegal buffer size: " + bufSize);
    }
    this.field_322 = local_is;
    this.buffer = new byte[bufSize];
    if ((local_is instanceof RepositionableStream)) {
      this.repositionableStream = ((RepositionableStream)local_is);
    }
    if ((local_is instanceof MeasurableStream)) {
      this.measurableStream = ((MeasurableStream)local_is);
    }
    if (this.repositionableStream == null) {
      try
      {
        this.fileChannel = ((FileChannel)local_is.getClass().getMethod("getChannel", new Class[0]).invoke(local_is, new Object[0]));
      }
      catch (IllegalAccessException local_e) {}catch (IllegalArgumentException local_e) {}catch (NoSuchMethodException local_e) {}catch (InvocationTargetException local_e) {}catch (ClassCastException local_e) {}
    }
  }
  
  public FastBufferedInputStream(InputStream local_is)
  {
    this(local_is, 8192);
  }
  
  protected boolean noMoreCharacters()
    throws IOException
  {
    if (this.avail == 0)
    {
      this.avail = this.field_322.read(this.buffer);
      if (this.avail <= 0)
      {
        this.avail = 0;
        return true;
      }
      this.pos = 0;
    }
    return false;
  }
  
  public int read()
    throws IOException
  {
    if (noMoreCharacters()) {
      return -1;
    }
    this.avail -= 1;
    this.readBytes += 1L;
    return this.buffer[(this.pos++)] & 0xFF;
  }
  
  public int read(byte[] local_b, int offset, int length)
    throws IOException
  {
    if (length <= this.avail)
    {
      System.arraycopy(this.buffer, this.pos, local_b, offset, length);
      this.pos += length;
      this.avail -= length;
      this.readBytes += length;
      return length;
    }
    int head = this.avail;
    System.arraycopy(this.buffer, this.pos, local_b, offset, head);
    this.pos = (this.avail = 0);
    this.readBytes += head;
    if (length > this.buffer.length)
    {
      int result = this.field_322.read(local_b, offset + head, length - head);
      if (result > 0) {
        this.readBytes += result;
      }
      return result < 0 ? head : head == 0 ? -1 : result + head;
    }
    if (noMoreCharacters()) {
      return head == 0 ? -1 : head;
    }
    int result = Math.min(length - head, this.avail);
    this.readBytes += result;
    System.arraycopy(this.buffer, 0, local_b, offset + head, result);
    this.pos = result;
    this.avail -= result;
    return result + head;
  }
  
  public int readLine(byte[] array)
    throws IOException
  {
    return readLine(array, 0, array.length, ALL_TERMINATORS);
  }
  
  public int readLine(byte[] array, EnumSet<LineTerminator> terminators)
    throws IOException
  {
    return readLine(array, 0, array.length, terminators);
  }
  
  public int readLine(byte[] array, int off, int len)
    throws IOException
  {
    return readLine(array, off, len, ALL_TERMINATORS);
  }
  
  public int readLine(byte[] array, int off, int len, EnumSet<LineTerminator> terminators)
    throws IOException
  {
    ByteArrays.ensureOffsetLength(array, off, len);
    if (len == 0) {
      return 0;
    }
    if (noMoreCharacters()) {
      return -1;
    }
    int local_k = 0;
    int remaining = len;
    int read = 0;
    do
    {
      for (;;)
      {
        for (int local_i = 0; (local_i < this.avail) && (local_i < remaining) && ((local_k = this.buffer[(this.pos + local_i)]) != 10) && (local_k != 13); local_i++) {}
        System.arraycopy(this.buffer, this.pos, array, off + read, local_i);
        this.pos += local_i;
        this.avail -= local_i;
        read += local_i;
        remaining -= local_i;
        if (remaining == 0)
        {
          this.readBytes += read;
          return read;
        }
        if (this.avail <= 0) {
          break;
        }
        if (local_k == 10)
        {
          this.pos += 1;
          this.avail -= 1;
          if (terminators.contains(LineTerminator.field_1756))
          {
            this.readBytes += read + 1;
            return read;
          }
          array[(off + read++)] = 10;
          remaining--;
        }
        else if (local_k == 13)
        {
          this.pos += 1;
          this.avail -= 1;
          if (terminators.contains(LineTerminator.CR_LF)) {
            if (this.avail > 0)
            {
              if (this.buffer[this.pos] == 10)
              {
                this.pos += 1;
                this.avail -= 1;
                this.readBytes += read + 2;
                return read;
              }
            }
            else
            {
              if (noMoreCharacters())
              {
                if (!terminators.contains(LineTerminator.field_1755))
                {
                  array[(off + read++)] = 13;
                  remaining--;
                  this.readBytes += read;
                }
                else
                {
                  this.readBytes += read + 1;
                }
                return read;
              }
              if (this.buffer[0] == 10)
              {
                this.pos += 1;
                this.avail -= 1;
                this.readBytes += read + 2;
                return read;
              }
            }
          }
          if (terminators.contains(LineTerminator.field_1755))
          {
            this.readBytes += read + 1;
            return read;
          }
          array[(off + read++)] = 13;
          remaining--;
        }
      }
    } while (!noMoreCharacters());
    this.readBytes += read;
    return read;
  }
  
  public void position(long newPosition)
    throws IOException
  {
    long position = this.readBytes;
    if ((newPosition <= position + this.avail) && (newPosition >= position - this.pos))
    {
      this.pos = ((int)(this.pos + (newPosition - position)));
      this.avail = ((int)(this.avail - (newPosition - position)));
      this.readBytes = newPosition;
      return;
    }
    if (this.repositionableStream != null) {
      this.repositionableStream.position(newPosition);
    } else if (this.fileChannel != null) {
      this.fileChannel.position(newPosition);
    } else {
      throw new UnsupportedOperationException("position() can only be called if the underlying byte stream implements the RepositionableStream interface or if the getChannel() method of the underlying byte stream exists and returns a FileChannel");
    }
    this.readBytes = newPosition;
    this.avail = (this.pos = 0);
  }
  
  public long position()
    throws IOException
  {
    return this.readBytes;
  }
  
  public long length()
    throws IOException
  {
    if (this.measurableStream != null) {
      return this.measurableStream.length();
    }
    if (this.fileChannel != null) {
      return this.fileChannel.size();
    }
    throw new UnsupportedOperationException();
  }
  
  private long skipByReading(long local_n)
    throws IOException
  {
    int len;
    for (long toSkip = local_n; toSkip > 0L; toSkip -= len)
    {
      len = this.field_322.read(this.buffer, 0, (int)Math.min(this.buffer.length, toSkip));
      if (len <= 0) {
        break;
      }
    }
    return local_n - toSkip;
  }
  
  public long skip(long local_n)
    throws IOException
  {
    if (local_n <= this.avail)
    {
      int local_m = (int)local_n;
      this.pos += local_m;
      this.avail -= local_m;
      this.readBytes += local_n;
      return local_n;
    }
    long local_m = local_n - this.avail;
    long result = 0L;
    this.avail = 0;
    while (local_m != 0L)
    {
      if ((result = this.field_322 == System.in ? skipByReading(local_m) : this.field_322.skip(local_m)) >= local_m) {
        break;
      }
      if (result == 0L)
      {
        if (this.field_322.read() == -1) {
          break;
        }
        local_m -= 1L;
      }
      else
      {
        local_m -= result;
      }
    }
    long local_t = local_n - (local_m - result);
    this.readBytes += local_t;
    return local_t;
  }
  
  public int available()
    throws IOException
  {
    return (int)Math.min(this.field_322.available() + this.avail, 2147483647L);
  }
  
  public void close()
    throws IOException
  {
    if (this.field_322 == null) {
      return;
    }
    if (this.field_322 != System.in) {
      this.field_322.close();
    }
    this.field_322 = null;
    this.buffer = null;
  }
  
  public void flush()
  {
    if (this.field_322 == null) {
      return;
    }
    this.readBytes += this.avail;
    this.avail = (this.pos = 0);
  }
  
  @Deprecated
  public void reset()
  {
    flush();
  }
  
  public static enum LineTerminator
  {
    field_1755,  field_1756,  CR_LF;
    
    private LineTerminator() {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastBufferedInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */