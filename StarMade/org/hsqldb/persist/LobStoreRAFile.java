package org.hsqldb.persist;

import org.hsqldb.Database;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess;

public class LobStoreRAFile
  implements LobStore
{
  final int lobBlockSize;
  RandomAccessInterface file;
  Database database;
  
  public LobStoreRAFile(Database paramDatabase, int paramInt)
  {
    this.lobBlockSize = paramInt;
    this.database = paramDatabase;
    try
    {
      String str = paramDatabase.getPath() + ".lobs";
      boolean bool = paramDatabase.logger.getFileAccess().isStreamElement(str);
      if (bool) {
        openFile();
      }
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(466, localThrowable);
    }
  }
  
  private void openFile()
  {
    try
    {
      String str = this.database.getPath() + ".lobs";
      boolean bool = this.database.isFilesReadOnly();
      if (this.database.logger.isStoredFileAccess()) {
        this.file = ScaledRAFile.newScaledRAFile(this.database, str, bool, 3);
      } else {
        this.file = new ScaledRAFileSimple(this.database, str, bool ? "r" : "rws");
      }
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(466, localThrowable);
    }
  }
  
  public byte[] getBlockBytes(int paramInt1, int paramInt2)
  {
    if (this.file == null) {
      throw Error.error(452);
    }
    try
    {
      long l = paramInt1 * this.lobBlockSize;
      int i = paramInt2 * this.lobBlockSize;
      byte[] arrayOfByte = new byte[i];
      this.file.seek(l);
      this.file.read(arrayOfByte, 0, i);
      return arrayOfByte;
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(466, localThrowable);
    }
  }
  
  public void setBlockBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.file == null) {
      openFile();
    }
    try
    {
      long l = paramInt1 * this.lobBlockSize;
      int i = paramInt2 * this.lobBlockSize;
      this.file.seek(l);
      this.file.write(paramArrayOfByte, 0, i);
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(466, localThrowable);
    }
  }
  
  public void setBlockBytes(byte[] paramArrayOfByte, long paramLong, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return;
    }
    if (this.file == null) {
      openFile();
    }
    try
    {
      this.file.seek(paramLong);
      this.file.write(paramArrayOfByte, paramInt1, paramInt2);
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(466, localThrowable);
    }
  }
  
  public int getBlockSize()
  {
    return this.lobBlockSize;
  }
  
  public void close()
  {
    try
    {
      if (this.file != null)
      {
        this.file.synch();
        this.file.close();
      }
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(466, localThrowable);
    }
  }
  
  public void synch()
  {
    if (this.file != null) {
      this.file.synch();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.LobStoreRAFile
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */