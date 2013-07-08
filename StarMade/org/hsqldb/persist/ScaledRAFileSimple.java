package org.hsqldb.persist;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.hsqldb.Database;

final class ScaledRAFileSimple
  implements RandomAccessInterface
{
  final RandomAccessFile file;
  final boolean readOnly;
  final Database database;
  
  ScaledRAFileSimple(Database paramDatabase, String paramString1, String paramString2)
    throws FileNotFoundException, IOException
  {
    this.file = new RandomAccessFile(paramString1, paramString2);
    this.database = paramDatabase;
    this.readOnly = paramString2.equals("r");
  }
  
  public long length()
    throws IOException
  {
    return this.file.length();
  }
  
  public void seek(long paramLong)
    throws IOException
  {
    this.file.seek(paramLong);
  }
  
  public long getFilePointer()
    throws IOException
  {
    return this.file.getFilePointer();
  }
  
  public int read()
    throws IOException
  {
    return this.file.read();
  }
  
  public long readLong()
    throws IOException
  {
    return this.file.readLong();
  }
  
  public int readInt()
    throws IOException
  {
    return this.file.readInt();
  }
  
  public void read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.file.readFully(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.file.write(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public void writeInt(int paramInt)
    throws IOException
  {
    this.file.writeInt(paramInt);
  }
  
  public void writeLong(long paramLong)
    throws IOException
  {
    this.file.writeLong(paramLong);
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
    return true;
  }
  
  public boolean setLength(long paramLong)
  {
    try
    {
      this.file.setLength(paramLong);
      return true;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  public Database getDatabase()
  {
    return null;
  }
  
  public void synch()
  {
    try
    {
      this.file.getFD().sync();
    }
    catch (IOException localIOException)
    {
      this.database.logger.logSevereEvent("RA file sync error ", localIOException);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.ScaledRAFileSimple
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */