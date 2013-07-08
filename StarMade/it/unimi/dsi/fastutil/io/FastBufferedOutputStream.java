package it.unimi.dsi.fastutil.io;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;

public class FastBufferedOutputStream
  extends MeasurableOutputStream
  implements RepositionableStream
{
  private static final boolean ASSERTS = false;
  public static final int DEFAULT_BUFFER_SIZE = 8192;
  protected byte[] buffer;
  protected int pos;
  protected int avail;
  protected OutputStream field_406;
  private FileChannel fileChannel;
  private RepositionableStream repositionableStream;
  private MeasurableStream measurableStream;
  
  public FastBufferedOutputStream(OutputStream local_os, int bufferSize)
  {
    this.field_406 = local_os;
    this.buffer = new byte[bufferSize];
    this.avail = bufferSize;
    if ((local_os instanceof RepositionableStream)) {
      this.repositionableStream = ((RepositionableStream)local_os);
    }
    if ((local_os instanceof MeasurableStream)) {
      this.measurableStream = ((MeasurableStream)local_os);
    }
    if (this.repositionableStream == null) {
      try
      {
        this.fileChannel = ((FileChannel)local_os.getClass().getMethod("getChannel", new Class[0]).invoke(local_os, new Object[0]));
      }
      catch (IllegalAccessException local_e) {}catch (IllegalArgumentException local_e) {}catch (NoSuchMethodException local_e) {}catch (InvocationTargetException local_e) {}catch (ClassCastException local_e) {}
    }
  }
  
  public FastBufferedOutputStream(OutputStream local_os)
  {
    this(local_os, 8192);
  }
  
  private void dumpBuffer(boolean ifFull)
    throws IOException
  {
    if ((!ifFull) || (this.avail == 0))
    {
      this.field_406.write(this.buffer, 0, this.pos);
      this.pos = 0;
      this.avail = this.buffer.length;
    }
  }
  
  public void write(int local_b)
    throws IOException
  {
    this.avail -= 1;
    this.buffer[(this.pos++)] = ((byte)local_b);
    dumpBuffer(true);
  }
  
  public void write(byte[] local_b, int offset, int length)
    throws IOException
  {
    if (length >= this.buffer.length)
    {
      dumpBuffer(false);
      this.field_406.write(local_b, offset, length);
      return;
    }
    if (length <= this.avail)
    {
      System.arraycopy(local_b, offset, this.buffer, this.pos, length);
      this.pos += length;
      this.avail -= length;
      dumpBuffer(true);
      return;
    }
    dumpBuffer(false);
    System.arraycopy(local_b, offset, this.buffer, 0, length);
    this.pos = length;
    this.avail -= length;
  }
  
  public void flush()
    throws IOException
  {
    dumpBuffer(false);
    this.field_406.flush();
  }
  
  public void close()
    throws IOException
  {
    if (this.field_406 == null) {
      return;
    }
    flush();
    if (this.field_406 != System.out) {
      this.field_406.close();
    }
    this.field_406 = null;
    this.buffer = null;
  }
  
  public long position()
    throws IOException
  {
    if (this.repositionableStream != null) {
      return this.repositionableStream.position() + this.pos;
    }
    if (this.measurableStream != null) {
      return this.measurableStream.position() + this.pos;
    }
    if (this.fileChannel != null) {
      return this.fileChannel.position() + this.pos;
    }
    throw new UnsupportedOperationException("position() can only be called if the underlying byte stream implements the MeasurableStream or RepositionableStream interface or if the getChannel() method of the underlying byte stream exists and returns a FileChannel");
  }
  
  public void position(long newPosition)
    throws IOException
  {
    flush();
    if (this.repositionableStream != null) {
      this.repositionableStream.position(newPosition);
    } else if (this.fileChannel != null) {
      this.fileChannel.position(newPosition);
    } else {
      throw new UnsupportedOperationException("position() can only be called if the underlying byte stream implements the RepositionableStream interface or if the getChannel() method of the underlying byte stream exists and returns a FileChannel");
    }
  }
  
  public long length()
    throws IOException
  {
    flush();
    if (this.measurableStream != null) {
      return this.measurableStream.length();
    }
    if (this.fileChannel != null) {
      return this.fileChannel.size();
    }
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastBufferedOutputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */