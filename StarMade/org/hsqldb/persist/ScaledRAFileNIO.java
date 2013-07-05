package org.hsqldb.persist;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import org.hsqldb.Database;

final class ScaledRAFileNIO
  implements RandomAccessInterface
{
  private final Database database;
  private final boolean readOnly;
  private final long maxLength;
  private long fileLength;
  private RandomAccessFile file;
  private FileDescriptor fileDescriptor;
  private MappedByteBuffer buffer;
  private long bufferPosition;
  private int bufferLength;
  private long currentPosition;
  private FileChannel channel;
  private boolean buffersModified;
  private MappedByteBuffer[] buffers = new MappedByteBuffer[0];
  private static final String JVM_ERROR = "JVM threw unsupported Exception";
  static final int largeBufferScale = 24;
  static final int largeBufferSize = 16777216;
  static final long largeBufferMask = -16777216L;

  ScaledRAFileNIO(Database paramDatabase, String paramString, boolean paramBoolean, long paramLong1, long paramLong2)
    throws Throwable
  {
    this.database = paramDatabase;
    this.maxLength = paramLong2;
    File localFile = new File(paramString);
    if (paramBoolean)
    {
      paramLong1 = localFile.length();
    }
    else
    {
      if (localFile.length() > paramLong1)
        paramLong1 = localFile.length();
      paramLong1 = ScaledRAFile.getBinaryNormalisedCeiling(paramLong1, 24);
    }
    this.file = new RandomAccessFile(paramString, paramBoolean ? "r" : "rw");
    this.readOnly = paramBoolean;
    this.channel = this.file.getChannel();
    this.fileDescriptor = this.file.getFD();
    if (ensureLength(paramLong1))
    {
      this.buffer = this.buffers[0];
      this.bufferLength = this.buffer.limit();
      this.bufferPosition = 0L;
      this.currentPosition = 0L;
    }
    else
    {
      close();
      IOException localIOException = new IOException("NIO buffer allocation failed");
      throw localIOException;
    }
  }

  public long length()
    throws IOException
  {
    try
    {
      return this.file.length();
    }
    catch (IOException localIOException1)
    {
      this.database.logger.logWarningEvent("nio", localIOException1);
      throw localIOException1;
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logWarningEvent("JVM threw unsupported Exception", localThrowable1);
      IOException localIOException2 = new IOException(localThrowable1.toString());
      try
      {
        localIOException2.initCause(localThrowable1);
      }
      catch (Throwable localThrowable2)
      {
      }
      throw localIOException2;
    }
  }

  public void seek(long paramLong)
    throws IOException
  {
    try
    {
      positionBufferSeek(paramLong);
      this.buffer.position((int)(paramLong - this.bufferPosition));
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      this.database.logger.logWarningEvent("nio", localIllegalArgumentException);
      localIOException = new IOException(localIllegalArgumentException.toString());
      try
      {
        localIOException.initCause(localIllegalArgumentException);
      }
      catch (Throwable localThrowable2)
      {
      }
      throw localIOException;
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logWarningEvent("JVM threw unsupported Exception", localThrowable1);
      IOException localIOException = new IOException(localThrowable1.toString());
      try
      {
        localIOException.initCause(localThrowable1);
      }
      catch (Throwable localThrowable3)
      {
      }
      throw localIOException;
    }
  }

  public long getFilePointer()
    throws IOException
  {
    try
    {
      return this.currentPosition;
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logWarningEvent("JVM threw unsupported Exception", localThrowable1);
      IOException localIOException = new IOException(localThrowable1.toString());
      try
      {
        localIOException.initCause(localThrowable1);
      }
      catch (Throwable localThrowable2)
      {
      }
      throw localIOException;
    }
  }

  public int read()
    throws IOException
  {
    try
    {
      int i = this.buffer.get();
      positionBufferMove(1);
      return i;
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logWarningEvent("JVM threw unsupported Exception", localThrowable1);
      IOException localIOException = new IOException(localThrowable1.toString());
      try
      {
        localIOException.initCause(localThrowable1);
      }
      catch (Throwable localThrowable2)
      {
      }
      throw localIOException;
    }
  }

  public void read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    try
    {
      while (true)
      {
        long l = this.bufferPosition + this.bufferLength - this.currentPosition;
        if (l > paramInt2)
          l = paramInt2;
        this.buffer.get(paramArrayOfByte, paramInt1, (int)l);
        positionBufferMove((int)l);
        paramInt2 = (int)(paramInt2 - l);
        paramInt1 = (int)(paramInt1 + l);
        if (paramInt2 == 0)
          break;
      }
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logWarningEvent("JVM threw unsupported Exception", localThrowable1);
      IOException localIOException = new IOException(localThrowable1.toString());
      try
      {
        localIOException.initCause(localThrowable1);
      }
      catch (Throwable localThrowable2)
      {
      }
      throw localIOException;
    }
  }

  public int readInt()
    throws IOException
  {
    try
    {
      int i = this.buffer.getInt();
      positionBufferMove(4);
      return i;
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logWarningEvent("JVM threw unsupported Exception", localThrowable1);
      IOException localIOException = new IOException(localThrowable1.toString());
      try
      {
        localIOException.initCause(localThrowable1);
      }
      catch (Throwable localThrowable2)
      {
      }
      throw localIOException;
    }
  }

  public long readLong()
    throws IOException
  {
    try
    {
      long l = this.buffer.getLong();
      positionBufferMove(8);
      return l;
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logWarningEvent("JVM threw unsupported Exception", localThrowable1);
      IOException localIOException = new IOException(localThrowable1.toString());
      try
      {
        localIOException.initCause(localThrowable1);
      }
      catch (Throwable localThrowable2)
      {
      }
      throw localIOException;
    }
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    try
    {
      this.buffersModified = true;
      while (true)
      {
        long l = this.bufferPosition + this.bufferLength - this.currentPosition;
        if (l > paramInt2)
          l = paramInt2;
        this.buffer.put(paramArrayOfByte, paramInt1, (int)l);
        positionBufferMove((int)l);
        paramInt2 = (int)(paramInt2 - l);
        paramInt1 = (int)(paramInt1 + l);
        if (paramInt2 == 0)
          break;
      }
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logWarningEvent("JVM threw unsupported Exception", localThrowable1);
      IOException localIOException = new IOException(localThrowable1.toString());
      try
      {
        localIOException.initCause(localThrowable1);
      }
      catch (Throwable localThrowable2)
      {
      }
      throw localIOException;
    }
  }

  public void writeInt(int paramInt)
    throws IOException
  {
    try
    {
      this.buffersModified = true;
      this.buffer.putInt(paramInt);
      positionBufferMove(4);
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logWarningEvent("JVM threw unsupported Exception", localThrowable1);
      IOException localIOException = new IOException(localThrowable1.toString());
      try
      {
        localIOException.initCause(localThrowable1);
      }
      catch (Throwable localThrowable2)
      {
      }
      throw localIOException;
    }
  }

  public void writeLong(long paramLong)
    throws IOException
  {
    try
    {
      this.buffersModified = true;
      this.buffer.putLong(paramLong);
      positionBufferMove(8);
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logWarningEvent("JVM threw unsupported Exception", localThrowable1);
      IOException localIOException = new IOException(localThrowable1.toString());
      try
      {
        localIOException.initCause(localThrowable1);
      }
      catch (Throwable localThrowable2)
      {
      }
      throw localIOException;
    }
  }

  public void close()
    throws IOException
  {
    try
    {
      this.database.logger.logDetailEvent("NIO file close, size: " + this.fileLength);
      this.buffer = null;
      this.channel = null;
      for (int i = 0; i < this.buffers.length; i++)
      {
        unmap(this.buffers[i]);
        this.buffers[i] = null;
      }
      this.file.close();
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logWarningEvent("NIO buffer close error JVM threw unsupported Exception ", localThrowable1);
      IOException localIOException = new IOException(localThrowable1.toString());
      try
      {
        localIOException.initCause(localThrowable1);
      }
      catch (Throwable localThrowable2)
      {
      }
      throw localIOException;
    }
  }

  public boolean isReadOnly()
  {
    return this.readOnly;
  }

  public boolean ensureLength(long paramLong)
  {
    if (paramLong > this.maxLength)
      return false;
    while (paramLong > this.fileLength)
      if (!enlargeFile(paramLong))
        return false;
    return true;
  }

  private boolean enlargeFile(long paramLong)
  {
    try
    {
      long l = paramLong;
      if (!this.readOnly)
        l = 16777216L;
      FileChannel.MapMode localMapMode = this.readOnly ? FileChannel.MapMode.READ_ONLY : FileChannel.MapMode.READ_WRITE;
      if ((!this.readOnly) && (this.file.length() < this.fileLength + l))
      {
        this.file.seek(this.fileLength + l - 1L);
        this.file.writeByte(0);
      }
      MappedByteBuffer[] arrayOfMappedByteBuffer = new MappedByteBuffer[this.buffers.length + 1];
      MappedByteBuffer localMappedByteBuffer = this.channel.map(localMapMode, this.fileLength, l);
      System.arraycopy(this.buffers, 0, arrayOfMappedByteBuffer, 0, this.buffers.length);
      arrayOfMappedByteBuffer[this.buffers.length] = localMappedByteBuffer;
      this.buffers = arrayOfMappedByteBuffer;
      this.fileLength += l;
      this.database.logger.logDetailEvent("NIO buffer instance, file size " + this.fileLength);
    }
    catch (Throwable localThrowable)
    {
      this.database.logger.logDetailEvent("NOI buffer allocate failed, file size " + paramLong);
      return false;
    }
    return true;
  }

  public boolean setLength(long paramLong)
  {
    if (paramLong > this.fileLength)
      return enlargeFile(paramLong);
    try
    {
      seek(0L);
    }
    catch (Throwable localThrowable)
    {
    }
    return true;
  }

  public Database getDatabase()
  {
    return null;
  }

  public void synch()
  {
    int i = 0;
    for (int j = 0; j < this.buffers.length; j++)
      try
      {
        this.buffers[j].force();
      }
      catch (Throwable localThrowable2)
      {
        this.database.logger.logSevereEvent("NIO buffer force error JVM threw unsupported Exception ", localThrowable2);
        i = 1;
      }
    if (i != 0)
      for (j = 0; j < this.buffers.length; j++)
        try
        {
          this.buffers[j].force();
        }
        catch (Throwable localThrowable3)
        {
          this.database.logger.logSevereEvent("NIO buffer force error JVM threw unsupported Exception ", localThrowable3);
        }
    try
    {
      this.fileDescriptor.sync();
      this.buffersModified = false;
    }
    catch (Throwable localThrowable1)
    {
      this.database.logger.logSevereEvent("NIO RA file sync error JVM threw unsupported Exception ", localThrowable1);
    }
  }

  private void positionBufferSeek(long paramLong)
  {
    if ((paramLong < this.bufferPosition) || (paramLong >= this.bufferPosition + this.bufferLength))
      setCurrentBuffer(paramLong);
    this.buffer.position((int)(paramLong - this.bufferPosition));
    this.currentPosition = paramLong;
  }

  private void positionBufferMove(int paramInt)
  {
    long l = this.currentPosition + paramInt;
    if (l >= this.bufferPosition + this.bufferLength)
      setCurrentBuffer(l);
    this.buffer.position((int)(l - this.bufferPosition));
    this.currentPosition = l;
  }

  private void setCurrentBuffer(long paramLong)
  {
    int i = (int)(paramLong >> 24);
    this.buffer = this.buffers[i];
    this.bufferPosition = (paramLong &= -16777216L);
  }

  private void unmap(MappedByteBuffer paramMappedByteBuffer)
    throws IOException
  {
    if (paramMappedByteBuffer == null)
      return;
    try
    {
      Method localMethod1 = paramMappedByteBuffer.getClass().getMethod("cleaner", new Class[0]);
      localMethod1.setAccessible(true);
      Object localObject = localMethod1.invoke(paramMappedByteBuffer, new Object[0]);
      Method localMethod2 = localObject.getClass().getMethod("clean", new Class[0]);
      localMethod2.invoke(localObject, new Object[0]);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
    }
    catch (Throwable localThrowable)
    {
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.ScaledRAFileNIO
 * JD-Core Version:    0.6.2
 */