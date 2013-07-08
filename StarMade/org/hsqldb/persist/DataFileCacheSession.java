package org.hsqldb.persist;

import java.util.concurrent.locks.Lock;
import org.hsqldb.Database;
import org.hsqldb.RowAVLDisk;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.Iterator;

public class DataFileCacheSession
  extends DataFileCache
{
  public DataFileCacheSession(Database paramDatabase, String paramString)
  {
    super(paramDatabase, paramString);
    this.logEvents = false;
  }
  
  protected void initParams(Database paramDatabase, String paramString)
  {
    this.dataFileName = (paramString + ".data.tmp");
    this.database = paramDatabase;
    this.fa = FileUtil.getFileUtil();
    this.dataFileScale = 64;
    this.cachedRowPadding = this.dataFileScale;
    this.initialFreePos = this.dataFileScale;
    this.maxCacheRows = 2048;
    this.maxCacheBytes = (this.maxCacheRows * 1024);
    this.maxDataFileSize = (2147483647L * this.dataFileScale);
    this.dataFile = null;
  }
  
  public void open(boolean paramBoolean)
  {
    try
    {
      this.dataFile = new ScaledRAFile(this.database, this.dataFileName, false, false, false);
      this.fileFreePosition = this.initialFreePos;
      initBuffers();
      this.freeBlocks = new DataFileBlockManager(0, this.dataFileScale, 0, 0L);
    }
    catch (Throwable localThrowable)
    {
      this.database.logger.logWarningEvent("Failed to open Session RA file", localThrowable);
      close(false);
      throw Error.error(localThrowable, 452, 52, new Object[] { localThrowable.toString(), this.dataFileName });
    }
  }
  
  public void close(boolean paramBoolean)
  {
    this.writeLock.lock();
    try
    {
      clear();
      if (this.dataFile != null)
      {
        this.dataFile.close();
        this.dataFile = null;
        this.fa.removeElement(this.dataFileName);
      }
    }
    catch (Throwable localThrowable)
    {
      this.database.logger.logWarningEvent("Failed to close Session RA file", localThrowable);
      throw Error.error(localThrowable, 452, 53, new Object[] { localThrowable.toString(), this.dataFileName });
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  protected void clear()
  {
    Iterator localIterator = this.cache.getIterator();
    while (localIterator.hasNext())
    {
      RowAVLDisk localRowAVLDisk = (RowAVLDisk)localIterator.next();
      localRowAVLDisk.setInMemory(false);
      localRowAVLDisk.destroy();
    }
    this.cache.clear();
    this.fileStartFreePosition = (this.fileFreePosition = this.initialFreePos);
    initBuffers();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.DataFileCacheSession
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */