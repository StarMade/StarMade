package org.hsqldb.persist;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.Lock;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.rowio.RowInputText;
import org.hsqldb.rowio.RowInputTextQuoted;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.rowio.RowOutputText;
import org.hsqldb.rowio.RowOutputTextQuoted;
import org.hsqldb.scriptio.ScriptWriterText;

public class TextCache extends DataFileCache
{
  TextFileSettings textFileSettings;
  protected String header;
  protected Table table;
  private LongKeyHashMap uncommittedCache;
  HsqlByteArrayOutputStream buffer = new HsqlByteArrayOutputStream(128);

  TextCache(Table paramTable, String paramString)
  {
    super(paramTable.database, paramString);
    this.table = paramTable;
    this.uncommittedCache = new LongKeyHashMap();
  }

  protected void initParams(Database paramDatabase, String paramString)
  {
    this.database = paramDatabase;
    this.fa = FileUtil.getFileUtil();
    this.textFileSettings = new TextFileSettings(paramDatabase, paramString);
    this.dataFileName = this.textFileSettings.getFileName();
    if (this.dataFileName == null)
      throw Error.error(301);
    this.dataFileName = ((FileUtil)this.fa).canonicalOrAbsolutePath(this.dataFileName);
    this.maxCacheRows = this.textFileSettings.getMaxCacheRows();
    this.maxCacheBytes = this.textFileSettings.getMaxCacheBytes();
    this.maxDataFileSize = 2147483647L;
    this.cachedRowPadding = 1;
    this.dataFileScale = 1;
  }

  protected void initBuffers()
  {
    if ((this.textFileSettings.isQuoted) || (this.textFileSettings.isAllQuoted))
    {
      this.rowIn = new RowInputTextQuoted(this.textFileSettings.fs, this.textFileSettings.vs, this.textFileSettings.lvs, this.textFileSettings.isAllQuoted);
      this.rowOut = new RowOutputTextQuoted(this.textFileSettings.fs, this.textFileSettings.vs, this.textFileSettings.lvs, this.textFileSettings.isAllQuoted, this.textFileSettings.stringEncoding);
    }
    else
    {
      this.rowIn = new RowInputText(this.textFileSettings.fs, this.textFileSettings.vs, this.textFileSettings.lvs, false);
      this.rowOut = new RowOutputText(this.textFileSettings.fs, this.textFileSettings.vs, this.textFileSettings.lvs, false, this.textFileSettings.stringEncoding);
    }
  }

  public void open(boolean paramBoolean)
  {
    this.fileFreePosition = 0L;
    try
    {
      int i = this.database.getType() == "res:" ? 2 : 5;
      this.dataFile = ScaledRAFile.newScaledRAFile(this.database, this.dataFileName, paramBoolean, i);
      this.fileFreePosition = this.dataFile.length();
      if (this.fileFreePosition > 2147483647L)
        throw Error.error(468);
      initBuffers();
      this.freeBlocks = new DataFileBlockManager(0, this.dataFileScale, 0, 0L);
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(localThrowable, 452, 42, new Object[] { localThrowable.toString(), this.dataFileName });
    }
    this.cacheReadonly = paramBoolean;
  }

  void reopen()
  {
    open(this.cacheReadonly);
  }

  public void close(boolean paramBoolean)
  {
    if (this.dataFile == null)
      return;
    this.writeLock.lock();
    try
    {
      this.cache.saveAll();
      int i = this.dataFile.length() <= TextFileSettings.NL.length() ? 1 : 0;
      this.dataFile.synch();
      this.dataFile.close();
      this.dataFile = null;
      if ((i != 0) && (!this.cacheReadonly))
        FileUtil.getFileUtil().delete(this.dataFileName);
      this.uncommittedCache.clear();
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(localThrowable, 452, 43, new Object[] { localThrowable.toString(), this.dataFileName });
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  void purge()
  {
    this.writeLock.lock();
    try
    {
      this.uncommittedCache.clear();
      if (this.cacheReadonly)
      {
        close(false);
      }
      else
      {
        if (this.dataFile != null)
        {
          this.dataFile.close();
          this.dataFile = null;
        }
        FileUtil.getFileUtil().delete(this.dataFileName);
      }
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(localThrowable, 452, 44, new Object[] { localThrowable.toString(), this.dataFileName });
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  long setFilePos(CachedObject paramCachedObject)
  {
    int i = paramCachedObject.getStorageSize();
    long l1 = this.fileFreePosition + i;
    if (l1 > this.maxDataFileSize)
    {
      this.database.logger.logSevereEvent("data file reached maximum size " + this.dataFileName, null);
      throw Error.error(468);
    }
    long l2 = this.fileFreePosition;
    paramCachedObject.setPos(l2);
    clearRowImage(paramCachedObject);
    this.fileFreePosition = l1;
    return l2;
  }

  public void remove(long paramLong, PersistentStore paramPersistentStore)
  {
    this.writeLock.lock();
    try
    {
      CachedObject localCachedObject = (CachedObject)this.uncommittedCache.remove(paramLong);
      if (localCachedObject != null)
        return;
      localCachedObject = this.cache.release(paramLong);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void removePersistence(CachedObject paramCachedObject)
  {
    this.writeLock.lock();
    try
    {
      clearRowImage(paramCachedObject);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  private void clearRowImage(CachedObject paramCachedObject)
  {
    try
    {
      int i = paramCachedObject.getStorageSize() - ScriptWriterText.BYTES_LINE_SEP.length;
      this.rowOut.reset();
      HsqlByteArrayOutputStream localHsqlByteArrayOutputStream = this.rowOut.getOutputStream();
      localHsqlByteArrayOutputStream.fill(32, i);
      localHsqlByteArrayOutputStream.write(ScriptWriterText.BYTES_LINE_SEP);
      this.dataFile.seek(paramCachedObject.getPos());
      this.dataFile.write(localHsqlByteArrayOutputStream.getBuffer(), 0, localHsqlByteArrayOutputStream.size());
    }
    catch (IOException localIOException)
    {
      throw Error.runtimeError(201, localIOException.getMessage());
    }
  }

  public void addInit(CachedObject paramCachedObject)
  {
    this.writeLock.lock();
    try
    {
      this.cache.put(paramCachedObject.getPos(), paramCachedObject);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void add(CachedObject paramCachedObject)
  {
    this.writeLock.lock();
    try
    {
      setFilePos(paramCachedObject);
      this.uncommittedCache.put(paramCachedObject.getPos(), paramCachedObject);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public CachedObject get(CachedObject paramCachedObject, PersistentStore paramPersistentStore, boolean paramBoolean)
  {
    if (paramCachedObject == null)
      return null;
    this.writeLock.lock();
    try
    {
      CachedObject localCachedObject1 = this.cache.get(paramCachedObject.getPos());
      Object localObject1;
      if (localCachedObject1 != null)
      {
        localObject1 = paramCachedObject;
        return localObject1;
      }
      try
      {
        this.buffer.reset(paramCachedObject.getStorageSize());
        this.dataFile.seek(paramCachedObject.getPos());
        this.dataFile.read(this.buffer.getBuffer(), 0, paramCachedObject.getStorageSize());
        this.buffer.setSize(paramCachedObject.getStorageSize());
        localObject1 = this.buffer.toString(this.textFileSettings.stringEncoding);
        ((RowInputText)this.rowIn).setSource((String)localObject1, paramCachedObject.getPos(), this.buffer.size());
        paramPersistentStore.get(paramCachedObject, this.rowIn);
        this.cache.put(paramCachedObject.getPos(), paramCachedObject);
        localCachedObject2 = paramCachedObject;
        return localCachedObject2;
      }
      catch (IOException localIOException)
      {
        this.database.logger.logSevereEvent(this.dataFileName + " getFromFile problem " + paramCachedObject.getPos(), localIOException);
        this.cache.forceCleanUp();
        System.gc();
        CachedObject localCachedObject2 = paramCachedObject;
        return localCachedObject2;
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public CachedObject get(long paramLong, PersistentStore paramPersistentStore, boolean paramBoolean)
  {
    throw Error.runtimeError(201, "TextCache");
  }

  protected void saveRows(CachedObject[] paramArrayOfCachedObject, int paramInt1, int paramInt2)
  {
  }

  public void saveRow(CachedObject paramCachedObject)
  {
    this.writeLock.lock();
    try
    {
      setFileModified();
      saveRowNoLock(paramCachedObject);
      this.uncommittedCache.remove(paramCachedObject.getPos());
      this.cache.put(paramCachedObject.getPos(), paramCachedObject);
    }
    catch (Throwable localThrowable)
    {
      this.database.logger.logSevereEvent("saveRow failed", localThrowable);
      throw Error.error(466, localThrowable);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public String getHeader()
  {
    return this.header;
  }

  public void setHeaderInitialise(String paramString)
  {
    this.header = paramString;
  }

  public void setHeader(String paramString)
  {
    if ((this.textFileSettings.ignoreFirst) && (this.fileFreePosition == 0L))
    {
      try
      {
        writeHeader(paramString);
        this.header = paramString;
      }
      catch (HsqlException localHsqlException)
      {
        throw new HsqlException(localHsqlException, Error.getMessage(467), 467);
      }
      return;
    }
    throw Error.error(486);
  }

  private void writeHeader(String paramString)
  {
    try
    {
      byte[] arrayOfByte = null;
      String str = paramString + TextFileSettings.NL;
      try
      {
        arrayOfByte = str.getBytes(this.textFileSettings.stringEncoding);
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        arrayOfByte = str.getBytes();
      }
      this.dataFile.seek(0L);
      this.dataFile.write(arrayOfByte, 0, arrayOfByte.length);
      this.fileFreePosition = arrayOfByte.length;
    }
    catch (IOException localIOException)
    {
      throw Error.error(484, localIOException);
    }
  }

  public int getLineNumber()
  {
    return ((RowInputText)this.rowIn).getLineNumber();
  }

  public TextFileSettings getTextFileSettings()
  {
    return this.textFileSettings;
  }

  public boolean isIgnoreFirstLine()
  {
    return this.textFileSettings.ignoreFirst;
  }

  protected void setFileModified()
  {
    this.fileModified = true;
  }

  public TextFileReader getTextFileReader()
  {
    return new TextFileReader(this.dataFile, this.textFileSettings, this.rowIn, this.cacheReadonly);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.TextCache
 * JD-Core Version:    0.6.2
 */