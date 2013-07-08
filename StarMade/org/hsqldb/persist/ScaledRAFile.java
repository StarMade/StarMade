package org.hsqldb.persist;

import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.hsqldb.Database;
import org.hsqldb.lib.HsqlByteArrayInputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.Storage;

final class ScaledRAFile
  implements RandomAccessInterface
{
  static final int DATA_FILE_RAF = 0;
  static final int DATA_FILE_NIO = 1;
  static final int DATA_FILE_JAR = 2;
  static final int DATA_FILE_STORED = 3;
  static final int DATA_FILE_SINGLE = 4;
  static final int DATA_FILE_TEXT = 5;
  static final int bufferScale = 12;
  static final int bufferSize = 4096;
  static final long bufferMask = -4096L;
  final Database database;
  final RandomAccessFile file;
  final FileDescriptor fileDescriptor;
  private final boolean readOnly;
  final String fileName;
  final byte[] buffer;
  final HsqlByteArrayInputStream ba;
  final byte[] valueBuffer;
  final HsqlByteArrayOutputStream vbao;
  final HsqlByteArrayInputStream vbai;
  long bufferOffset;
  long fileLength;
  final boolean extendLength;
  long seekPosition;
  int cacheHit;
  
  static RandomAccessInterface newScaledRAFile(Database paramDatabase, String paramString, boolean paramBoolean, int paramInt)
    throws FileNotFoundException, IOException
  {
    if (paramInt == 3) {
      try
      {
        String str1 = paramDatabase.getURLProperties().getProperty("storage_class_name");
        String str2 = paramDatabase.getURLProperties().getProperty("storage_key");
        Class localClass;
        try
        {
          ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
          localClass = localClassLoader.loadClass(str1);
        }
        catch (ClassNotFoundException localClassNotFoundException2)
        {
          localClass = Class.forName(str1);
        }
        Constructor localConstructor = localClass.getConstructor(new Class[] { String.class, Boolean.class, Object.class });
        Object localObject2 = localConstructor.newInstance(new Object[] { paramString, new Boolean(paramBoolean), str2 });
        if ((localObject2 instanceof RandomAccessInterface)) {
          return (RandomAccessInterface)localObject2;
        }
        if ((localObject2 instanceof Storage)) {
          return new ScaledRAStorageWrapper((Storage)localObject2);
        }
        throw new IOException();
      }
      catch (ClassNotFoundException localClassNotFoundException1)
      {
        throw new IOException();
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        throw new IOException();
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new IOException();
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new IOException();
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        throw new IOException();
      }
    }
    if (paramInt == 2) {
      return new ScaledRAFileInJar(paramString);
    }
    if (paramInt == 5)
    {
      localObject1 = new ScaledRAFile(paramDatabase, paramString, paramBoolean, false, true);
      return localObject1;
    }
    if (paramInt == 0) {
      return new ScaledRAFile(paramDatabase, paramString, paramBoolean, true, false);
    }
    Object localObject1 = new File(paramString);
    long l = ((File)localObject1).length();
    if (l > paramDatabase.logger.propNioMaxSize) {
      return new ScaledRAFile(paramDatabase, paramString, paramBoolean, true, false);
    }
    try
    {
      Class.forName("java.nio.MappedByteBuffer");
      return new ScaledRAFileHybrid(paramDatabase, paramString, paramBoolean);
    }
    catch (Exception localException) {}
    return new ScaledRAFile(paramDatabase, paramString, paramBoolean, true, false);
  }
  
  ScaledRAFile(Database paramDatabase, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    throws FileNotFoundException, IOException
  {
    this.database = paramDatabase;
    this.fileName = paramString;
    this.readOnly = paramBoolean1;
    this.extendLength = paramBoolean2;
    String str = paramBoolean3 ? "rws" : paramBoolean1 ? "r" : "rw";
    this.file = new RandomAccessFile(paramString, str);
    this.buffer = new byte[4096];
    this.ba = new HsqlByteArrayInputStream(this.buffer);
    this.valueBuffer = new byte[8];
    this.vbao = new HsqlByteArrayOutputStream(this.valueBuffer);
    this.vbai = new HsqlByteArrayInputStream(this.valueBuffer);
    this.fileDescriptor = this.file.getFD();
    this.fileLength = length();
    readIntoBuffer();
  }
  
  public long length()
    throws IOException
  {
    return this.file.length();
  }
  
  public void seek(long paramLong)
    throws IOException
  {
    if ((this.readOnly) && (this.fileLength < paramLong)) {
      throw new IOException("read beyond end of file");
    }
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
    long l1 = this.seekPosition & 0xFFFFF000;
    long l2 = this.fileLength - l1;
    if (l2 > this.buffer.length) {
      l2 = this.buffer.length;
    }
    if (l2 < 0L) {
      throw new IOException("read beyond end of file");
    }
    try
    {
      this.file.seek(l1);
      this.file.readFully(this.buffer, 0, (int)l2);
      this.bufferOffset = l1;
    }
    catch (IOException localIOException)
    {
      resetPointer();
      this.database.logger.logWarningEvent(" " + l1 + " " + l2, localIOException);
      throw localIOException;
    }
  }
  
  public int read()
    throws IOException
  {
    try
    {
      if (this.seekPosition >= this.fileLength) {
        return -1;
      }
      if ((this.seekPosition < this.bufferOffset) || (this.seekPosition >= this.bufferOffset + this.buffer.length)) {
        readIntoBuffer();
      } else {
        this.cacheHit += 1;
      }
      int i = this.buffer[((int)(this.seekPosition - this.bufferOffset))] & 0xFF;
      this.seekPosition += 1L;
      return i;
    }
    catch (IOException localIOException)
    {
      resetPointer();
      this.database.logger.logWarningEvent("read failed", localIOException);
      throw localIOException;
    }
  }
  
  public long readLong()
    throws IOException
  {
    this.vbai.reset();
    read(this.valueBuffer, 0, 8);
    return this.vbai.readLong();
  }
  
  public int readInt()
    throws IOException
  {
    this.vbai.reset();
    read(this.valueBuffer, 0, 4);
    return this.vbai.readInt();
  }
  
  public void read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    try
    {
      if (this.seekPosition + paramInt2 > this.fileLength) {
        throw new EOFException();
      }
      if ((paramInt2 > this.buffer.length) && ((this.seekPosition < this.bufferOffset) || (this.seekPosition >= this.bufferOffset + this.buffer.length)))
      {
        this.file.seek(this.seekPosition);
        this.file.readFully(paramArrayOfByte, paramInt1, paramInt2);
        this.seekPosition += paramInt2;
        return;
      }
      if ((this.seekPosition < this.bufferOffset) || (this.seekPosition >= this.bufferOffset + this.buffer.length)) {
        readIntoBuffer();
      } else {
        this.cacheHit += 1;
      }
      this.ba.reset();
      if (this.seekPosition - this.bufferOffset != this.ba.skip(this.seekPosition - this.bufferOffset)) {
        throw new EOFException();
      }
      int i = this.ba.read(paramArrayOfByte, paramInt1, paramInt2);
      this.seekPosition += i;
      if (i < paramInt2)
      {
        this.file.seek(this.seekPosition);
        this.file.readFully(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
        this.seekPosition += paramInt2 - i;
      }
    }
    catch (IOException localIOException)
    {
      resetPointer();
      this.database.logger.logWarningEvent("failed to read a byte array", localIOException);
      throw localIOException;
    }
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    try
    {
      this.file.seek(this.seekPosition);
      if ((this.seekPosition < this.bufferOffset + this.buffer.length) && (this.seekPosition + paramInt2 > this.bufferOffset)) {
        writeToBuffer(paramArrayOfByte, paramInt1, paramInt2);
      }
      this.file.write(paramArrayOfByte, paramInt1, paramInt2);
      this.seekPosition += paramInt2;
      if ((!this.extendLength) && (this.fileLength < this.seekPosition)) {
        this.fileLength = this.seekPosition;
      }
    }
    catch (IOException localIOException)
    {
      resetPointer();
      this.database.logger.logWarningEvent("failed to write a byte array", localIOException);
      throw localIOException;
    }
  }
  
  public void writeInt(int paramInt)
    throws IOException
  {
    this.vbao.reset();
    this.vbao.writeInt(paramInt);
    write(this.valueBuffer, 0, 4);
  }
  
  public void writeLong(long paramLong)
    throws IOException
  {
    this.vbao.reset();
    this.vbao.writeLong(paramLong);
    write(this.valueBuffer, 0, 8);
  }
  
  public void close()
    throws IOException
  {
    this.file.close();
  }
  
  public boolean isReadOnly()
  {
    return this.readOnly;
  }
  
  public boolean ensureLength(long paramLong)
  {
    if (paramLong <= this.fileLength) {
      return true;
    }
    try
    {
      extendLength(paramLong);
    }
    catch (IOException localIOException)
    {
      return false;
    }
    return true;
  }
  
  public boolean setLength(long paramLong)
  {
    try
    {
      this.file.setLength(paramLong);
      this.file.seek(0L);
      this.fileLength = this.file.length();
      this.seekPosition = 0L;
      readIntoBuffer();
      return true;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  public void synch()
  {
    try
    {
      this.fileDescriptor.sync();
    }
    catch (IOException localIOException)
    {
      this.database.logger.logSevereEvent("RA file sync error ", localIOException);
    }
  }
  
  private void writeToBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = paramInt2;
    int j = paramInt1;
    int k = (int)(this.seekPosition - this.bufferOffset);
    if (k < 0)
    {
      j -= k;
      i += k;
      k = 0;
    }
    int m = (int)(this.bufferOffset + this.buffer.length - this.seekPosition);
    if (m < i) {
      i = m;
    }
    System.arraycopy(paramArrayOfByte, j, this.buffer, k, i);
  }
  
  private long getExtendLength(long paramLong)
  {
    if (!this.extendLength) {
      return paramLong;
    }
    int i;
    if (paramLong < 262144L) {
      i = 2;
    } else if (paramLong < 1048576L) {
      i = 6;
    } else if (paramLong < 33554432L) {
      i = 8;
    } else {
      i = 12;
    }
    paramLong = getBinaryNormalisedCeiling(paramLong, 12 + i);
    return paramLong;
  }
  
  private void extendLength(long paramLong)
    throws IOException
  {
    long l = getExtendLength(paramLong);
    if (l > this.fileLength) {
      try
      {
        this.file.seek(l - 1L);
        this.file.write(0);
        this.fileLength = l;
      }
      catch (IOException localIOException)
      {
        this.database.logger.logWarningEvent("data file enlarge failed ", localIOException);
        throw localIOException;
      }
    }
  }
  
  private void resetPointer()
  {
    try
    {
      this.seekPosition = 0L;
      this.fileLength = length();
      readIntoBuffer();
    }
    catch (Throwable localThrowable) {}
  }
  
  static long getBinaryNormalisedCeiling(long paramLong, int paramInt)
  {
    long l1 = -1L << paramInt;
    long l2 = paramLong & l1;
    if (l2 != paramLong) {
      l2 += (1 << paramInt);
    }
    return l2;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.ScaledRAFile
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */