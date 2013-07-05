package org.hsqldb.lib;

import java.io.DataOutput;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;

public class HsqlByteArrayOutputStream extends OutputStream
  implements DataOutput
{
  protected byte[] buffer;
  protected int count;

  public HsqlByteArrayOutputStream()
  {
    this(128);
  }

  public HsqlByteArrayOutputStream(int paramInt)
  {
    if (paramInt < 128)
      paramInt = 128;
    this.buffer = new byte[paramInt];
  }

  public HsqlByteArrayOutputStream(byte[] paramArrayOfByte)
  {
    this.buffer = paramArrayOfByte;
  }

  public HsqlByteArrayOutputStream(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    this.buffer = new byte[paramInt];
    int i = write(paramInputStream, paramInt);
    if (i != paramInt)
      throw new EOFException();
  }

  public HsqlByteArrayOutputStream(InputStream paramInputStream)
    throws IOException
  {
    this.buffer = new byte[''];
    while (true)
    {
      int i = paramInputStream.read(this.buffer, this.count, this.buffer.length - this.count);
      if (i == -1)
        break;
      this.count += i;
      if (this.count == this.buffer.length)
        ensureRoom(128);
    }
  }

  public void writeShort(int paramInt)
  {
    ensureRoom(2);
    this.buffer[(this.count++)] = ((byte)(paramInt >>> 8));
    this.buffer[(this.count++)] = ((byte)paramInt);
  }

  public void writeInt(int paramInt)
  {
    if (this.count + 4 > this.buffer.length)
      ensureRoom(4);
    this.buffer[(this.count++)] = ((byte)(paramInt >>> 24));
    this.buffer[(this.count++)] = ((byte)(paramInt >>> 16));
    this.buffer[(this.count++)] = ((byte)(paramInt >>> 8));
    this.buffer[(this.count++)] = ((byte)paramInt);
  }

  public void writeLong(long paramLong)
  {
    writeInt((int)(paramLong >>> 32));
    writeInt((int)paramLong);
  }

  public final void writeBytes(String paramString)
  {
    int i = paramString.length();
    ensureRoom(i);
    for (int j = 0; j < i; j++)
      this.buffer[(this.count++)] = ((byte)paramString.charAt(j));
  }

  public final void writeFloat(float paramFloat)
  {
    writeInt(Float.floatToIntBits(paramFloat));
  }

  public final void writeDouble(double paramDouble)
  {
    writeLong(Double.doubleToLongBits(paramDouble));
  }

  public void writeBoolean(boolean paramBoolean)
  {
    ensureRoom(1);
    this.buffer[(this.count++)] = ((byte)(paramBoolean ? 1 : 0));
  }

  public void writeByte(int paramInt)
  {
    ensureRoom(1);
    this.buffer[(this.count++)] = ((byte)paramInt);
  }

  public void writeChar(int paramInt)
  {
    ensureRoom(2);
    this.buffer[(this.count++)] = ((byte)(paramInt >>> 8));
    this.buffer[(this.count++)] = ((byte)paramInt);
  }

  public void writeChars(String paramString)
  {
    int i = paramString.length();
    ensureRoom(i * 2);
    for (int j = 0; j < i; j++)
    {
      int k = paramString.charAt(j);
      this.buffer[(this.count++)] = ((byte)(k >>> 8));
      this.buffer[(this.count++)] = ((byte)k);
    }
  }

  public void writeUTF(String paramString)
    throws IOException
  {
    int i = paramString.length();
    if (i > 65535)
      throw new UTFDataFormatException();
    ensureRoom(i * 3 + 2);
    int j = this.count;
    this.count += 2;
    StringConverter.stringToUTFBytes(paramString, this);
    int k = this.count - j - 2;
    if (k > 65535)
    {
      this.count = j;
      throw new UTFDataFormatException();
    }
    this.buffer[(j++)] = ((byte)(k >>> 8));
    this.buffer[j] = ((byte)k);
  }

  public void flush()
    throws IOException
  {
  }

  public void write(int paramInt)
  {
    ensureRoom(1);
    this.buffer[(this.count++)] = ((byte)paramInt);
  }

  public void write(byte[] paramArrayOfByte)
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    ensureRoom(paramInt2);
    System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.count, paramInt2);
    this.count += paramInt2;
  }

  public String toString()
  {
    return new String(this.buffer, 0, this.count);
  }

  public void close()
    throws IOException
  {
  }

  public void writeNoCheck(int paramInt)
  {
    this.buffer[(this.count++)] = ((byte)paramInt);
  }

  public void writeChars(char[] paramArrayOfChar)
  {
    int i = paramArrayOfChar.length;
    ensureRoom(i * 2);
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfChar[j];
      this.buffer[(this.count++)] = ((byte)(k >>> 8));
      this.buffer[(this.count++)] = ((byte)k);
    }
  }

  public int write(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    int i = paramInt;
    ensureRoom(paramInt);
    while (i > 0)
    {
      int j = paramInputStream.read(this.buffer, this.count, i);
      if (j == -1)
        break;
      i -= j;
      this.count += j;
    }
    return paramInt - i;
  }

  public int write(Reader paramReader, int paramInt)
    throws IOException
  {
    int i = paramInt;
    ensureRoom(paramInt * 2);
    while (i > 0)
    {
      int j = paramReader.read();
      if (j == -1)
        break;
      writeChar(j);
      i--;
    }
    return paramInt - i;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(this.buffer, 0, this.count);
  }

  public void reset()
  {
    this.count = 0;
  }

  public byte[] toByteArray()
  {
    byte[] arrayOfByte = new byte[this.count];
    System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.count);
    return arrayOfByte;
  }

  public int size()
  {
    return this.count;
  }

  public void setPosition(int paramInt)
  {
    if (paramInt > this.buffer.length)
      throw new ArrayIndexOutOfBoundsException();
    this.count = paramInt;
  }

  public String toString(String paramString)
    throws UnsupportedEncodingException
  {
    return new String(this.buffer, 0, this.count, paramString);
  }

  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    ensureRoom(paramInt2 * 2);
    for (int i = paramInt1; i < paramInt2; i++)
    {
      int j = paramArrayOfChar[i];
      this.buffer[(this.count++)] = ((byte)(j >>> 8));
      this.buffer[(this.count++)] = ((byte)j);
    }
  }

  public void fill(int paramInt1, int paramInt2)
  {
    ensureRoom(paramInt2);
    for (int i = 0; i < paramInt2; i++)
      this.buffer[(this.count++)] = ((byte)paramInt1);
  }

  public byte[] getBuffer()
  {
    return this.buffer;
  }

  public void ensureRoom(int paramInt)
  {
    int i = this.count + paramInt;
    int j = this.buffer.length;
    if (i > j)
    {
      while (i > j)
        j *= 2;
      byte[] arrayOfByte = new byte[j];
      System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.count);
      this.buffer = arrayOfByte;
    }
  }

  public void reset(int paramInt)
  {
    this.count = 0;
    if (paramInt > this.buffer.length)
      this.buffer = new byte[paramInt];
  }

  public void reset(byte[] paramArrayOfByte)
  {
    this.count = 0;
    this.buffer = paramArrayOfByte;
  }

  public void setSize(int paramInt)
  {
    if (paramInt > this.buffer.length)
      reset(paramInt);
    this.count = paramInt;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.HsqlByteArrayOutputStream
 * JD-Core Version:    0.6.2
 */