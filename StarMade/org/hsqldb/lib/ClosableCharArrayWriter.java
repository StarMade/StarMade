package org.hsqldb.lib;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Writer;

public class ClosableCharArrayWriter
  extends Writer
{
  protected char[] buf;
  protected int count;
  protected boolean closed;
  protected boolean freed;
  
  public ClosableCharArrayWriter()
  {
    this(32);
  }
  
  public ClosableCharArrayWriter(int paramInt)
    throws IllegalArgumentException
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Negative initial size: " + paramInt);
    }
    this.buf = new char[paramInt];
  }
  
  public synchronized void write(int paramInt)
    throws IOException
  {
    checkClosed();
    int i = this.count + 1;
    if (i > this.buf.length) {
      this.buf = copyOf(this.buf, Math.max(this.buf.length << 1, i));
    }
    this.buf[this.count] = ((char)paramInt);
    this.count = i;
  }
  
  public synchronized void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    checkClosed();
    if ((paramInt1 < 0) || (paramInt1 > paramArrayOfChar.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfChar.length) || (paramInt1 + paramInt2 < 0)) {
      throw new IndexOutOfBoundsException();
    }
    if (paramInt2 == 0) {
      return;
    }
    int i = this.count + paramInt2;
    if (i > this.buf.length) {
      this.buf = copyOf(this.buf, Math.max(this.buf.length << 1, i));
    }
    System.arraycopy(paramArrayOfChar, paramInt1, this.buf, this.count, paramInt2);
    this.count = i;
  }
  
  public synchronized void write(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    checkClosed();
    int i = paramString.length();
    if ((paramInt1 < 0) || (paramInt1 > i) || (paramInt2 < 0) || (paramInt1 + paramInt2 > i) || (paramInt1 + paramInt2 < 0)) {
      throw new IndexOutOfBoundsException();
    }
    if (paramInt2 == 0) {
      return;
    }
    int j = this.count + paramInt2;
    if (j > this.buf.length) {
      this.buf = copyOf(this.buf, Math.max(this.buf.length << 1, j));
    }
    paramString.getChars(paramInt1, paramInt1 + paramInt2, this.buf, this.count);
    this.count = j;
  }
  
  public void flush()
    throws IOException
  {
    checkClosed();
  }
  
  public synchronized void writeTo(Writer paramWriter)
    throws IOException
  {
    checkFreed();
    if (this.count > 0) {
      paramWriter.write(this.buf, 0, this.count);
    }
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
  
  public synchronized char[] toCharArray()
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
  
  public synchronized CharArrayReader toCharArrayReader()
    throws IOException
  {
    checkFreed();
    CharArrayReader localCharArrayReader = new CharArrayReader(this.buf, 0, this.count);
    free();
    return localCharArrayReader;
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
      throw new IOException("writer is closed.");
    }
  }
  
  protected synchronized void checkFreed()
    throws IOException
  {
    if (this.freed) {
      throw new IOException("write buffer is freed.");
    }
  }
  
  protected char[] copyOf(char[] paramArrayOfChar, int paramInt)
  {
    char[] arrayOfChar = new char[paramInt];
    System.arraycopy(paramArrayOfChar, 0, arrayOfChar, 0, Math.min(paramArrayOfChar.length, paramInt));
    return arrayOfChar;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.ClosableCharArrayWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */