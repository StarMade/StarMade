package org.hsqldb.persist;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.hsqldb.Database;
import org.hsqldb.lib.HsqlByteArrayInputStream;

final class ScaledRAFileInJar
  implements RandomAccessInterface
{
  DataInputStream file;
  final String fileName;
  long fileLength;
  boolean bufferDirty = true;
  byte[] buffer = new byte[4096];
  HsqlByteArrayInputStream ba = new HsqlByteArrayInputStream(this.buffer);
  long bufferOffset;
  long seekPosition;
  long realPosition;
  
  ScaledRAFileInJar(String paramString)
    throws FileNotFoundException, IOException
  {
    this.fileName = paramString;
    this.fileLength = getLength();
    resetStream();
  }
  
  public long length()
    throws IOException
  {
    return this.fileLength;
  }
  
  public void seek(long paramLong)
    throws IOException
  {
    this.seekPosition = paramLong;
  }
  
  public long getFilePointer()
    throws IOException
  {
    return this.seekPosition;
  }
  
  private void readIntoBuffer()
    throws IOException
  {
    long l1 = this.seekPosition;
    this.bufferDirty = false;
    long l2 = l1 % this.buffer.length;
    long l3 = this.fileLength - (l1 - l2);
    if (l3 <= 0L) {
      throw new IOException("read beyond end of file");
    }
    if (l3 > this.buffer.length) {
      l3 = this.buffer.length;
    }
    fileSeek(l1 - l2);
    this.file.readFully(this.buffer, 0, (int)l3);
    this.bufferOffset = (l1 - l2);
    this.realPosition = (this.bufferOffset + l3);
  }
  
  public int read()
    throws IOException
  {
    if (this.seekPosition >= this.fileLength) {
      return -1;
    }
    if ((this.bufferDirty) || (this.seekPosition < this.bufferOffset) || (this.seekPosition >= this.bufferOffset + this.buffer.length)) {
      readIntoBuffer();
    }
    this.ba.reset();
    this.ba.skip(this.seekPosition - this.bufferOffset);
    int i = this.ba.read();
    this.seekPosition += 1L;
    return i;
  }
  
  public long readLong()
    throws IOException
  {
    long l1 = readInt();
    long l2 = readInt();
    return (l1 << 32) + (l2 & 0xFFFFFFFF);
  }
  
  public int readInt()
    throws IOException
  {
    if ((this.bufferDirty) || (this.seekPosition < this.bufferOffset) || (this.seekPosition >= this.bufferOffset + this.buffer.length)) {
      readIntoBuffer();
    }
    this.ba.reset();
    this.ba.skip(this.seekPosition - this.bufferOffset);
    int i = this.ba.readInt();
    this.seekPosition += 4L;
    return i;
  }
  
  public void read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if ((this.bufferDirty) || (this.seekPosition < this.bufferOffset) || (this.seekPosition >= this.bufferOffset + this.buffer.length)) {
      readIntoBuffer();
    }
    this.ba.reset();
    this.ba.skip(this.seekPosition - this.bufferOffset);
    int i = this.ba.read(paramArrayOfByte, paramInt1, paramInt2);
    this.seekPosition += i;
    if (i < paramInt2)
    {
      if (this.seekPosition != this.realPosition) {
        fileSeek(this.seekPosition);
      }
      this.file.readFully(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
      this.seekPosition += paramInt2 - i;
      this.realPosition = this.seekPosition;
    }
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {}
  
  public void writeInt(int paramInt)
    throws IOException
  {}
  
  public void writeLong(long paramLong)
    throws IOException
  {}
  
  public void close()
    throws IOException
  {
    this.file.close();
  }
  
  public boolean isReadOnly()
  {
    return true;
  }
  
  private long getLength()
    throws IOException
  {
    int i = 0;
    resetStream();
    while (this.file.read() >= 0) {
      i++;
    }
    return i;
  }
  
  private void resetStream()
    throws IOException
  {
    if (this.file != null) {
      this.file.close();
    }
    InputStream localInputStream = null;
    try
    {
      localInputStream = getClass().getResourceAsStream(this.fileName);
      if (localInputStream == null)
      {
        ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
        if (localClassLoader != null) {
          localInputStream = localClassLoader.getResourceAsStream(this.fileName);
        }
      }
    }
    catch (Throwable localThrowable) {}finally
    {
      if (localInputStream == null) {
        throw new FileNotFoundException(this.fileName);
      }
    }
    this.file = new DataInputStream(localInputStream);
  }
  
  private void fileSeek(long paramLong)
    throws IOException
  {
    long l = this.realPosition;
    if (paramLong < l) {
      resetStream();
    }
    for (l = 0L; paramLong > l; l += this.file.skip(paramLong - l)) {}
  }
  
  public boolean ensureLength(long paramLong)
  {
    return true;
  }
  
  public boolean setLength(long paramLong)
  {
    return false;
  }
  
  public Database getDatabase()
  {
    return null;
  }
  
  public void synch() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.ScaledRAFileInJar
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */