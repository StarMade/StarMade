package org.hsqldb.lib;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class ClosableByteArrayOutputStream
  extends OutputStream
{
  protected byte[] buf;
  protected int count;
  protected boolean closed;
  protected boolean freed;
  
  public ClosableByteArrayOutputStream()
  {
    this(32);
  }
  
  public ClosableByteArrayOutputStream(int paramInt)
    throws IllegalArgumentException
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Negative initial size: " + paramInt);
    }
    this.buf = new byte[paramInt];
  }
  
  public synchronized void write(int paramInt)
    throws IOException
  {
    checkClosed();
    int i = this.count + 1;
    if (i > this.buf.length) {
      this.buf = copyOf(this.buf, Math.max(this.buf.length << 1, i));
    }
    this.buf[this.count] = ((byte)paramInt);
    this.count = i;
  }
  
  public synchronized void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    checkClosed();
    if ((paramInt1 < 0) || (paramInt1 > paramArrayOfByte.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length) || (paramInt1 + paramInt2 < 0)) {
      throw new IndexOutOfBoundsException();
    }
    if (paramInt2 == 0) {
      return;
    }
    int i = this.count + paramInt2;
    if (i > this.buf.length) {
      this.buf = copyOf(this.buf, Math.max(this.buf.length << 1, i));
    }
    System.arraycopy(paramArrayOfByte, paramInt1, this.buf, this.count, paramInt2);
    this.count = i;
  }
  
  public void flush()
    throws IOException
  {
    checkClosed();
  }
  
  public synchronized void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    checkFreed();
    paramOutputStream.write(this.buf, 0, this.count);
  }
  
  public synchronized int capacity()
    throws IOException
  {
    checkFreed();
    return this.buf.length;
  }
  
  public synchronized void reset()
    throws IOException
  {
    checkClosed();
    this.count = 0;
  }
  
  public synchronized void trimToSize()
    throws IOException
  {
    checkFreed();
    if (this.buf.length > this.count) {
      this.buf = copyOf(this.buf, this.count);
    }
  }
  
  public synchronized byte[] toByteArray()
    throws IOException
  {
    checkFreed();
    return copyOf(this.buf, this.count);
  }
  
  public synchronized int size()
    throws IOException
  {
    return this.count;
  }
  
  public synchronized void setSize(int paramInt)
  {
    if (paramInt < 0) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    if (paramInt > this.buf.length) {
      this.buf = copyOf(this.buf, Math.max(this.buf.length << 1, paramInt));
    }
    this.count = paramInt;
  }
  
  public synchronized ByteArrayInputStream toByteArrayInputStream()
    throws IOException
  {
    checkFreed();
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(this.buf, 0, this.count);
    free();
    return localByteArrayInputStream;
  }
  
  public synchronized String toString()
  {
    try
    {
      checkFreed();
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException.toString());
    }
    return new String(this.buf, 0, this.count);
  }
  
  public synchronized String toString(String paramString)
    throws IOException, UnsupportedEncodingException
  {
    checkFreed();
    return new String(this.buf, 0, this.count, paramString);
  }
  
  public synchronized void close()
    throws IOException
  {
    this.closed = true;
  }
  
  public synchronized boolean isClosed()
  {
    return this.closed;
  }
  
  public synchronized void free()
    throws IOException
  {
    this.closed = true;
    this.freed = true;
    this.buf = null;
    this.count = 0;
  }
  
  public synchronized boolean isFreed()
  {
    return this.freed;
  }
  
  protected synchronized void checkClosed()
    throws IOException
  {
    if (this.closed) {
      throw new IOException("stream is closed.");
    }
  }
  
  protected synchronized void checkFreed()
    throws IOException
  {
    if (this.freed) {
      throw new IOException("stream buffer is freed.");
    }
  }
  
  protected byte[] copyOf(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, Math.min(paramArrayOfByte.length, paramInt));
    return arrayOfByte;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.ClosableByteArrayOutputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */