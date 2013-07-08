package org.hsqldb.persist;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.SchemaManager;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.FileArchiver;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.StopWatch;
import org.hsqldb.rowio.RowInputBinary180;
import org.hsqldb.rowio.RowInputBinaryDecode;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputBinary180;
import org.hsqldb.rowio.RowOutputBinaryEncode;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.store.BitMap;

public class DataFileCache
{
  protected FileAccess field_2045;
  public static final int FLAG_ISSHADOWED = 1;
  public static final int FLAG_ISSAVED = 2;
  public static final int FLAG_ROWINFO = 3;
  public static final int FLAG_190 = 4;
  public static final int FLAG_HX = 5;
  static final int LONG_EMPTY_SIZE = 4;
  static final int LONG_FREE_POS_POS = 12;
  static final int LONG_EMPTY_INDEX_POS = 20;
  static final int FLAGS_POS = 28;
  static final int MIN_INITIAL_FREE_POS = 32;
  DataFileBlockManager freeBlocks;
  static final int initIOBufferSize = 4096;
  protected String dataFileName;
  protected String backupFileName;
  protected Database database;
  protected boolean logEvents = true;
  protected boolean fileModified;
  protected boolean cacheModified;
  protected int dataFileScale;
  protected boolean cacheReadonly;
  protected int cachedRowPadding;
  protected int initialFreePos;
  protected long fileStartFreePosition;
  protected boolean hasRowInfo = false;
  protected int storeCount;
  protected RowInputInterface rowIn;
  public RowOutputInterface rowOut;
  public long maxDataFileSize;
  boolean is180;
  protected RandomAccessInterface dataFile;
  protected volatile long fileFreePosition;
  protected int maxCacheRows;
  protected long maxCacheBytes;
  protected Cache cache;
  private RAShadowFile shadowFile;
  ReadWriteLock lock = new ReentrantReadWriteLock();
  Lock readLock = this.lock.readLock();
  Lock writeLock = this.lock.writeLock();
  
  public DataFileCache(Database paramDatabase, String paramString)
  {
    initParams(paramDatabase, paramString);
    this.cache = new Cache(this);
  }
  
  protected void initParams(Database paramDatabase, String paramString)
  {
    this.dataFileName = (paramString + ".data");
    this.backupFileName = (paramString + ".backup");
    this.database = paramDatabase;
    this.field_2045 = paramDatabase.logger.getFileAccess();
    this.dataFileScale = paramDatabase.logger.getDataFileScale();
    this.cachedRowPadding = 8;
    if (this.dataFileScale > 8) {
      this.cachedRowPadding = this.dataFileScale;
    }
    this.initialFreePos = 32;
    if (this.initialFreePos < this.dataFileScale) {
      this.initialFreePos = this.dataFileScale;
    }
    this.cacheReadonly = paramDatabase.logger.propFilesReadOnly;
    this.maxCacheRows = paramDatabase.logger.propCacheMaxRows;
    this.maxCacheBytes = paramDatabase.logger.propCacheMaxSize;
    this.maxDataFileSize = (2147483647L * this.dataFileScale * paramDatabase.logger.getDataFileFactor());
    this.dataFile = null;
    this.shadowFile = null;
  }
  
  public void open(boolean paramBoolean)
  {
    if (this.database.logger.isStoredFileAccess())
    {
      openStoredFileAccess(paramBoolean);
      return;
    }
    this.fileFreePosition = this.initialFreePos;
    logInfoEvent("dataFileCache open start");
    try
    {
      boolean bool1 = this.database.logger.propNioDataFile;
      int i;
      if (this.database.isFilesInJar()) {
        i = 2;
      } else if (bool1) {
        i = 1;
      } else {
        i = 0;
      }
      if ((paramBoolean) || (this.database.isFilesInJar()))
      {
        this.dataFile = ScaledRAFile.newScaledRAFile(this.database, this.dataFileName, paramBoolean, i);
        this.dataFile.seek(28L);
        int j = this.dataFile.readInt();
        this.is180 = (!BitMap.isSet(j, 4));
        if (BitMap.isSet(j, 5)) {
          throw Error.error(453);
        }
        this.dataFile.seek(12L);
        this.fileFreePosition = this.dataFile.readLong();
        initBuffers();
        return;
      }
      long l1 = 0L;
      boolean bool2 = this.field_2045.isStreamElement(this.dataFileName);
      boolean bool3 = this.database.logger.propIncrementBackup;
      boolean bool4 = false;
      if (bool2)
      {
        this.dataFile = new ScaledRAFileSimple(this.database, this.dataFileName, "r");
        long l2 = this.dataFile.length();
        int m = 0;
        if (l2 > this.initialFreePos)
        {
          this.dataFile.seek(28L);
          int n = this.dataFile.readInt();
          bool4 = BitMap.isSet(n, 2);
          bool3 = BitMap.isSet(n, 1);
          this.is180 = (!BitMap.isSet(n, 4));
          if (BitMap.isSet(n, 5)) {
            m = 1;
          }
        }
        this.dataFile.close();
        if (l2 > this.maxDataFileSize) {
          throw Error.error(453, "requires large database support");
        }
        if (m != 0) {
          throw Error.error(453);
        }
        if ((bool4) && (bool3))
        {
          boolean bool6 = this.field_2045.isStreamElement(this.backupFileName);
          if (bool6) {
            bool4 = false;
          }
        }
      }
      if (bool4)
      {
        if (bool3)
        {
          deleteBackup();
        }
        else
        {
          boolean bool5 = this.field_2045.isStreamElement(this.backupFileName);
          if (!bool5) {
            backupFile(false);
          }
        }
      }
      else if (bool3) {
        bool2 = restoreBackupIncremental();
      } else {
        bool2 = restoreBackup();
      }
      this.dataFile = ScaledRAFile.newScaledRAFile(this.database, this.dataFileName, paramBoolean, i);
      if (bool2)
      {
        this.dataFile.seek(28L);
        int k = this.dataFile.readInt();
        this.is180 = (!BitMap.isSet(k, 4));
        this.dataFile.seek(4L);
        l1 = this.dataFile.readLong();
        this.dataFile.seek(12L);
        this.fileFreePosition = this.dataFile.readLong();
        this.fileStartFreePosition = this.fileFreePosition;
        openShadowFile();
      }
      else
      {
        initNewFile();
      }
      initBuffers();
      this.fileModified = false;
      this.cacheModified = false;
      this.freeBlocks = new DataFileBlockManager(this.database.logger.propMaxFreeBlocks, this.dataFileScale, 0, l1);
      logInfoEvent("dataFileCache open end");
    }
    catch (Throwable localThrowable)
    {
      logSevereEvent("dataFileCache open failed", localThrowable);
      close(false);
      throw Error.error(localThrowable, 452, 52, new Object[] { localThrowable.toString(), this.dataFileName });
    }
  }
  
  void openStoredFileAccess(boolean paramBoolean)
  {
    this.fileFreePosition = this.initialFreePos;
    logInfoEvent("dataFileCache open start");
    try
    {
      int i = 3;
      if (paramBoolean)
      {
        this.dataFile = ScaledRAFile.newScaledRAFile(this.database, this.dataFileName, paramBoolean, i);
        this.dataFile.seek(28L);
        int j = this.dataFile.readInt();
        this.is180 = (!BitMap.isSet(j, 4));
        this.dataFile.seek(12L);
        this.fileFreePosition = this.dataFile.readLong();
        initBuffers();
        return;
      }
      long l = 0L;
      boolean bool1 = this.field_2045.isStreamElement(this.dataFileName);
      boolean bool2 = this.database.logger.propIncrementBackup;
      int k = this.database.getProperties().getDBModified() == 1 ? 1 : 0;
      if ((bool1) && (k != 0)) {
        if (bool2) {
          bool1 = restoreBackupIncremental();
        } else {
          bool1 = restoreBackup();
        }
      }
      this.dataFile = ScaledRAFile.newScaledRAFile(this.database, this.dataFileName, paramBoolean, i);
      if (bool1)
      {
        this.dataFile.seek(4L);
        l = this.dataFile.readLong();
        this.dataFile.seek(12L);
        this.fileFreePosition = this.dataFile.readLong();
        this.fileStartFreePosition = this.fileFreePosition;
        this.dataFile.seek(28L);
        int m = this.dataFile.readInt();
        this.is180 = (!BitMap.isSet(m, 4));
        openShadowFile();
      }
      else
      {
        initNewFile();
      }
      initBuffers();
      this.fileModified = false;
      this.cacheModified = false;
      this.freeBlocks = new DataFileBlockManager(this.database.logger.propMaxFreeBlocks, this.dataFileScale, 0, l);
      logInfoEvent("dataFileCache open end");
    }
    catch (Throwable localThrowable)
    {
      logSevereEvent("dataFileCache open failed", localThrowable);
      close(false);
      throw Error.error(localThrowable, 452, 52, new Object[] { localThrowable.toString(), this.dataFileName });
    }
  }
  
  void initNewFile()
    throws IOException
  {
    this.fileFreePosition = this.initialFreePos;
    this.fileStartFreePosition = this.initialFreePos;
    this.dataFile.seek(12L);
    this.dataFile.writeLong(this.fileFreePosition);
    int i = 0;
    if (this.database.logger.propIncrementBackup) {
      i = BitMap.set(i, 1);
    }
    i = BitMap.set(i, 2);
    i = BitMap.set(i, 4);
    this.dataFile.seek(28L);
    this.dataFile.writeInt(i);
    this.dataFile.synch();
    this.is180 = false;
  }
  
  void openShadowFile()
  {
    if ((this.database.logger.propIncrementBackup) && (this.fileFreePosition != this.initialFreePos)) {
      this.shadowFile = new RAShadowFile(this.database, this.dataFile, this.backupFileName, this.fileFreePosition, 16384);
    }
  }
  
  void setIncrementBackup(boolean paramBoolean)
  {
    this.writeLock.lock();
    try
    {
      this.dataFile.seek(28L);
      int i = this.dataFile.readInt();
      if (paramBoolean) {
        i = BitMap.set(i, 1);
      } else {
        i = BitMap.unset(i, 1);
      }
      this.dataFile.seek(28L);
      this.dataFile.writeInt(i);
      this.dataFile.synch();
      this.fileModified = true;
    }
    catch (Throwable localThrowable)
    {
      logSevereEvent("backupFile failed", localThrowable);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  private boolean restoreBackup()
  {
    deleteFile();
    try
    {
      FileAccess localFileAccess = this.database.logger.getFileAccess();
      if (localFileAccess.isStreamElement(this.backupFileName))
      {
        FileArchiver.unarchive(this.backupFileName, this.dataFileName, localFileAccess, 1);
        return true;
      }
      return false;
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(localThrowable, 452, 26, new Object[] { localThrowable.toString(), this.backupFileName });
    }
  }
  
  private boolean restoreBackupIncremental()
  {
    try
    {
      if (this.field_2045.isStreamElement(this.backupFileName))
      {
        RAShadowFile.restoreFile(this.database, this.backupFileName, this.dataFileName);
        deleteBackup();
        return true;
      }
      return false;
    }
    catch (IOException localIOException)
    {
      throw Error.error(452, localIOException);
    }
  }
  
  public void close(boolean paramBoolean)
  {
    this.writeLock.lock();
    try
    {
      if (this.dataFile == null) {
        return;
      }
      if (paramBoolean)
      {
        commitChanges();
      }
      else if (this.shadowFile != null)
      {
        this.shadowFile.close();
        this.shadowFile = null;
      }
      this.dataFile.close();
      logDetailEvent("dataFileCache file close");
      this.dataFile = null;
      if (!paramBoolean) {
        return;
      }
      int i = this.fileFreePosition == this.initialFreePos ? 1 : 0;
      if (i != 0)
      {
        deleteFile();
        deleteBackup();
      }
    }
    catch (HsqlException localHsqlException)
    {
      throw localHsqlException;
    }
    catch (Throwable localThrowable)
    {
      logSevereEvent("dataFileCache close failed", localThrowable);
      throw Error.error(localThrowable, 452, 53, new Object[] { localThrowable.toString(), this.dataFileName });
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  protected void clear()
  {
    this.writeLock.lock();
    try
    {
      this.cache.clear();
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public void adjustStoreCount(int paramInt)
  {
    this.writeLock.lock();
    try
    {
      this.storeCount += paramInt;
      if (this.storeCount == 0) {
        clear();
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public void commitChanges()
  {
    this.writeLock.lock();
    try
    {
      if (this.cacheReadonly) {
        return;
      }
      logInfoEvent("dataFileCache commit start");
      this.cache.saveAll();
      if ((this.fileModified) || (this.freeBlocks.isModified()))
      {
        this.dataFile.seek(4L);
        this.dataFile.writeLong(this.freeBlocks.getLostBlocksSize());
        this.dataFile.seek(12L);
        this.dataFile.writeLong(this.fileFreePosition);
        this.dataFile.seek(28L);
        int i = this.dataFile.readInt();
        i = BitMap.set(i, 2);
        this.dataFile.seek(28L);
        this.dataFile.writeInt(i);
      }
      this.dataFile.synch();
      this.cache.logSynchEvent();
      this.fileModified = false;
      this.cacheModified = false;
      this.fileStartFreePosition = this.fileFreePosition;
      if (this.shadowFile != null)
      {
        this.shadowFile.close();
        this.shadowFile = null;
      }
      logDetailEvent("dataFileCache commit end");
    }
    catch (Throwable localThrowable)
    {
      logSevereEvent("dataFileCache commit failed", localThrowable);
      throw Error.error(localThrowable, 452, 53, new Object[] { localThrowable.toString(), this.dataFileName });
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  protected void initBuffers()
  {
    if (this.rowOut == null) {
      if (this.is180) {
        this.rowOut = new RowOutputBinary180(4096, this.cachedRowPadding);
      } else {
        this.rowOut = new RowOutputBinaryEncode(this.database.logger.getCrypto(), 4096, this.cachedRowPadding);
      }
    }
    if (this.rowIn == null) {
      if (this.is180) {
        this.rowIn = new RowInputBinary180(new byte[4096]);
      } else {
        this.rowIn = new RowInputBinaryDecode(this.database.logger.getCrypto(), new byte[4096]);
      }
    }
  }
  
  DataFileDefrag defrag()
  {
    this.writeLock.lock();
    try
    {
      this.cache.saveAll();
      DataFileDefrag localDataFileDefrag1 = new DataFileDefrag(this.database, this, this.dataFileName);
      localDataFileDefrag1.process();
      close(true);
      this.cache.clear();
      if (!this.database.logger.propIncrementBackup) {
        backupFile(true);
      }
      this.database.schemaManager.setTempIndexRoots(localDataFileDefrag1.getIndexRoots());
      try
      {
        this.database.logger.log.writeScript(false);
      }
      finally
      {
        this.database.schemaManager.setTempIndexRoots((long[][])null);
      }
      this.database.getProperties().setProperty("hsqldb.script_format", this.database.logger.propScriptFormat);
      this.database.getProperties().setDBModified(2);
      this.database.logger.log.closeLog();
      this.database.logger.log.deleteLog();
      this.database.logger.log.renameNewScript();
      renameBackupFile();
      renameDataFile();
      this.database.getProperties().setDBModified(0);
      open(false);
      this.database.schemaManager.setIndexRoots(localDataFileDefrag1.getIndexRoots());
      if (this.database.logger.log.dbLogWriter != null) {
        this.database.logger.log.openLog();
      }
      this.database.getProperties().setDBModified(1);
      DataFileDefrag localDataFileDefrag2 = localDataFileDefrag1;
      return localDataFileDefrag2;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public void remove(long paramLong, PersistentStore paramPersistentStore)
  {
    this.writeLock.lock();
    try
    {
      CachedObject localCachedObject = release(paramLong);
      if (localCachedObject != null)
      {
        int i = localCachedObject.getStorageSize();
        this.freeBlocks.add(paramLong, i);
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public void removePersistence(CachedObject paramCachedObject) {}
  
  long setFilePos(CachedObject paramCachedObject)
  {
    int i = paramCachedObject.getStorageSize();
    long l1 = this.freeBlocks.get(i);
    if (l1 == -1L)
    {
      l1 = this.fileFreePosition / this.dataFileScale;
      long l2 = this.fileFreePosition + i;
      if (l2 > this.maxDataFileSize)
      {
        logSevereEvent("data file reached maximum size " + this.dataFileName, null);
        throw Error.error(468);
      }
      boolean bool = this.dataFile.ensureLength(l2);
      if (!bool)
      {
        logSevereEvent("data file cannot be enlarged - disk spacee " + this.dataFileName, null);
        throw Error.error(468);
      }
      this.fileFreePosition = l2;
    }
    paramCachedObject.setPos(l1);
    return l1;
  }
  
  public void add(CachedObject paramCachedObject)
  {
    this.writeLock.lock();
    try
    {
      this.cacheModified = true;
      long l = setFilePos(paramCachedObject);
      this.cache.put(l, paramCachedObject);
      if (paramCachedObject.getStorageSize() > 4096) {
        this.rowOut.reset(paramCachedObject.getStorageSize());
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public int getStorageSize(long paramLong)
  {
    this.readLock.lock();
    try
    {
      CachedObject localCachedObject = this.cache.get(paramLong);
      if (localCachedObject != null)
      {
        int i = localCachedObject.getStorageSize();
        return i;
      }
    }
    finally
    {
      this.readLock.unlock();
    }
    return readSize(paramLong);
  }
  
  public void replace(CachedObject paramCachedObject)
  {
    this.writeLock.lock();
    try
    {
      long l = paramCachedObject.getPos();
      this.cache.replace(l, paramCachedObject);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public CachedObject get(CachedObject paramCachedObject, PersistentStore paramPersistentStore, boolean paramBoolean)
  {
    this.readLock.lock();
    long l;
    try
    {
      CachedObject localCachedObject;
      if (paramCachedObject.isInMemory())
      {
        if (paramBoolean) {
          paramCachedObject.keepInMemory(true);
        }
        localCachedObject = paramCachedObject;
        return localCachedObject;
      }
      l = paramCachedObject.getPos();
      if (l < 0L)
      {
        localCachedObject = null;
        return localCachedObject;
      }
      paramCachedObject = this.cache.get(l);
      if (paramCachedObject != null)
      {
        if (paramBoolean) {
          paramCachedObject.keepInMemory(true);
        }
        localCachedObject = paramCachedObject;
        return localCachedObject;
      }
    }
    finally
    {
      this.readLock.unlock();
    }
    return getFromFile(l, paramPersistentStore, paramBoolean);
  }
  
  public CachedObject get(long paramLong, PersistentStore paramPersistentStore, boolean paramBoolean)
  {
    if (paramLong < 0L) {
      return null;
    }
    this.readLock.lock();
    try
    {
      CachedObject localCachedObject1 = this.cache.get(paramLong);
      if (localCachedObject1 != null)
      {
        if (paramBoolean) {
          localCachedObject1.keepInMemory(true);
        }
        CachedObject localCachedObject2 = localCachedObject1;
        return localCachedObject2;
      }
    }
    finally
    {
      this.readLock.unlock();
    }
    return getFromFile(paramLong, paramPersistentStore, paramBoolean);
  }
  
  private CachedObject getFromFile(long paramLong, PersistentStore paramPersistentStore, boolean paramBoolean)
  {
    CachedObject localCachedObject1 = null;
    this.writeLock.lock();
    try
    {
      localCachedObject1 = this.cache.get(paramLong);
      if (localCachedObject1 != null)
      {
        if (paramBoolean) {
          localCachedObject1.keepInMemory(true);
        }
        CachedObject localCachedObject2 = localCachedObject1;
        return localCachedObject2;
      }
      int i = 0;
      while (i < 2) {
        try
        {
          RowInputInterface localRowInputInterface = readObject(paramLong);
          if (localRowInputInterface == null)
          {
            CachedObject localCachedObject4 = null;
            return localCachedObject4;
          }
          localCachedObject1 = paramPersistentStore.get(localRowInputInterface);
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          this.cache.forceCleanUp();
          System.gc();
          logSevereEvent(this.dataFileName + " getFromFile out of mem " + paramLong, localOutOfMemoryError);
          if (i > 0) {
            throw localOutOfMemoryError;
          }
          i++;
        }
      }
      paramLong = localCachedObject1.getPos();
      this.cache.put(paramLong, localCachedObject1);
      if (paramBoolean) {
        localCachedObject1.keepInMemory(true);
      }
      paramPersistentStore.set(localCachedObject1);
      CachedObject localCachedObject3 = localCachedObject1;
      return localCachedObject3;
    }
    catch (HsqlException localHsqlException)
    {
      logSevereEvent(this.dataFileName + " getFromFile " + paramLong, localHsqlException);
      throw localHsqlException;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  RowInputInterface getRaw(int paramInt)
  {
    this.writeLock.lock();
    try
    {
      RowInputInterface localRowInputInterface = readObject(paramInt);
      return localRowInputInterface;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  protected int readSize(long paramLong)
  {
    this.writeLock.lock();
    try
    {
      this.dataFile.seek(paramLong * this.dataFileScale);
      int i = this.dataFile.readInt();
      return i;
    }
    catch (IOException localIOException)
    {
      logSevereEvent("readSize", localIOException, paramLong);
      throw Error.error(466, localIOException);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  protected RowInputInterface readObject(long paramLong)
  {
    try
    {
      this.dataFile.seek(paramLong * this.dataFileScale);
      int i = this.dataFile.readInt();
      this.rowIn.resetRow(paramLong, i);
      this.dataFile.read(this.rowIn.getBuffer(), 4, i - 4);
      return this.rowIn;
    }
    catch (IOException localIOException)
    {
      logSevereEvent("readObject", localIOException, paramLong);
      throw Error.error(466, localIOException);
    }
  }
  
  public CachedObject release(long paramLong)
  {
    this.writeLock.lock();
    try
    {
      CachedObject localCachedObject = this.cache.release(paramLong);
      return localCachedObject;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  protected void saveRows(CachedObject[] paramArrayOfCachedObject, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return;
    }
    try
    {
      copyShadow(paramArrayOfCachedObject, paramInt1, paramInt2);
      setFileModified();
      for (int i = paramInt1; i < paramInt1 + paramInt2; i++)
      {
        CachedObject localCachedObject = paramArrayOfCachedObject[i];
        saveRowNoLock(localCachedObject);
        paramArrayOfCachedObject[i] = null;
      }
    }
    catch (HsqlException localHsqlException)
    {
      throw localHsqlException;
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(466, localThrowable);
    }
    finally
    {
      initBuffers();
    }
  }
  
  public void saveRow(CachedObject paramCachedObject)
  {
    this.writeLock.lock();
    try
    {
      copyShadow(paramCachedObject);
      setFileModified();
      saveRowNoLock(paramCachedObject);
    }
    catch (Throwable localThrowable)
    {
      logSevereEvent("saveRow", localThrowable, paramCachedObject.getPos());
      throw Error.error(466, localThrowable);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  protected void saveRowNoLock(CachedObject paramCachedObject)
  {
    try
    {
      this.rowOut.reset();
      paramCachedObject.write(this.rowOut);
      this.dataFile.seek(paramCachedObject.getPos() * this.dataFileScale);
      this.dataFile.write(this.rowOut.getOutputStream().getBuffer(), 0, this.rowOut.getOutputStream().size());
    }
    catch (IOException localIOException)
    {
      logSevereEvent("saveRowNoLock", localIOException, paramCachedObject.getPos());
      throw Error.error(466, localIOException);
    }
  }
  
  protected void copyShadow(CachedObject[] paramArrayOfCachedObject, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.shadowFile != null)
    {
      long l1 = this.cache.saveAllTimer.elapsedTime();
      for (int i = paramInt1; i < paramInt1 + paramInt2; i++)
      {
        CachedObject localCachedObject = paramArrayOfCachedObject[i];
        long l2 = localCachedObject.getPos() * this.dataFileScale;
        this.shadowFile.copy(l2, localCachedObject.getStorageSize());
      }
      this.shadowFile.synch();
      l1 = this.cache.saveAllTimer.elapsedTime() - l1;
      logDetailEvent("shadow copy [time, size] " + l1 + " " + this.shadowFile.getSavedLength());
    }
  }
  
  protected void copyShadow(CachedObject paramCachedObject)
    throws IOException
  {
    if (this.shadowFile != null)
    {
      long l = paramCachedObject.getPos() * this.dataFileScale;
      this.shadowFile.copy(l, paramCachedObject.getStorageSize());
      this.shadowFile.synch();
    }
  }
  
  void backupFile(boolean paramBoolean)
  {
    this.writeLock.lock();
    try
    {
      if (this.database.logger.propIncrementBackup)
      {
        if (this.field_2045.isStreamElement(this.backupFileName)) {
          deleteBackup();
        }
        return;
      }
      if (this.field_2045.isStreamElement(this.dataFileName))
      {
        String str = paramBoolean ? this.dataFileName + ".new" : this.dataFileName;
        FileArchiver.archive(str, this.backupFileName + ".new", this.database.logger.getFileAccess(), 1);
      }
    }
    catch (IOException localIOException)
    {
      logSevereEvent("backupFile failed", localIOException);
      throw Error.error(466, localIOException);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  void renameBackupFile()
  {
    this.writeLock.lock();
    try
    {
      if (this.database.logger.propIncrementBackup)
      {
        deleteBackup();
        return;
      }
      if (this.field_2045.isStreamElement(this.backupFileName + ".new"))
      {
        deleteBackup();
        this.field_2045.renameElement(this.backupFileName + ".new", this.backupFileName);
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  void renameDataFile()
  {
    this.writeLock.lock();
    try
    {
      if (this.field_2045.isStreamElement(this.dataFileName + ".new"))
      {
        deleteFile();
        this.field_2045.renameElement(this.dataFileName + ".new", this.dataFileName);
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  void deleteFile()
  {
    this.writeLock.lock();
    try
    {
      this.field_2045.removeElement(this.dataFileName);
      if (this.database.logger.isStoredFileAccess()) {
        return;
      }
      if (this.field_2045.isStreamElement(this.dataFileName))
      {
        this.database.logger.log.deleteOldDataFiles();
        this.field_2045.removeElement(this.dataFileName);
        if (this.field_2045.isStreamElement(this.dataFileName))
        {
          String str = FileUtil.newDiscardFileName(this.dataFileName);
          this.field_2045.renameElement(this.dataFileName, str);
        }
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  void deleteBackup()
  {
    this.writeLock.lock();
    try
    {
      if (this.field_2045.isStreamElement(this.backupFileName)) {
        this.field_2045.removeElement(this.backupFileName);
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  public int capacity()
  {
    return this.maxCacheRows;
  }
  
  public long bytesCapacity()
  {
    return this.maxCacheBytes;
  }
  
  public long getTotalCachedBlockSize()
  {
    return this.cache.getTotalCachedBlockSize();
  }
  
  public int getFreeBlockCount()
  {
    return this.freeBlocks.size();
  }
  
  public int getTotalFreeBlockSize()
  {
    return 0;
  }
  
  public long getFileFreePos()
  {
    return this.fileFreePosition;
  }
  
  public int getCachedObjectCount()
  {
    return this.cache.size();
  }
  
  public int getAccessCount()
  {
    return this.cache.incrementAccessCount();
  }
  
  public String getFileName()
  {
    return this.dataFileName;
  }
  
  public boolean hasRowInfo()
  {
    return this.hasRowInfo;
  }
  
  public boolean isFileModified()
  {
    return this.fileModified;
  }
  
  public boolean isModified()
  {
    return this.cacheModified;
  }
  
  public boolean isFileOpen()
  {
    return this.dataFile != null;
  }
  
  protected void setFileModified()
  {
    this.writeLock.lock();
    try
    {
      if (!this.fileModified)
      {
        long l = this.cache.saveAllTimer.elapsedTime();
        this.cache.saveAllTimer.start();
        this.dataFile.seek(28L);
        int i = this.dataFile.readInt();
        i = BitMap.unset(i, 2);
        this.dataFile.seek(28L);
        this.dataFile.writeInt(i);
        this.dataFile.synch();
        this.cache.saveAllTimer.stop();
        logDetailEvent("flags set " + this.cache.saveAllTimer.elapsedTime());
        this.fileModified = true;
      }
    }
    catch (Throwable localThrowable) {}finally
    {
      this.writeLock.unlock();
    }
  }
  
  public int getFlags()
  {
    try
    {
      this.dataFile.seek(28L);
      int i = this.dataFile.readInt();
      return i;
    }
    catch (Throwable localThrowable) {}
    return 0;
  }
  
  public boolean isDataReadOnly()
  {
    return this.cacheReadonly;
  }
  
  public RAShadowFile getShadowFile()
  {
    return this.shadowFile;
  }
  
  private void logSevereEvent(String paramString, Throwable paramThrowable, long paramLong)
  {
    if (this.logEvents)
    {
      StringBuffer localStringBuffer = new StringBuffer(paramString);
      localStringBuffer.append(' ').append(paramLong);
      paramString = localStringBuffer.toString();
      this.database.logger.logSevereEvent(paramString, paramThrowable);
    }
  }
  
  private void logSevereEvent(String paramString, Throwable paramThrowable)
  {
    if (this.logEvents) {
      this.database.logger.logSevereEvent(paramString, paramThrowable);
    }
  }
  
  public void logInfoEvent(String paramString)
  {
    if (this.logEvents) {
      this.database.logger.logInfoEvent(paramString);
    }
  }
  
  public void logDetailEvent(String paramString)
  {
    if (this.logEvents) {
      this.database.logger.logDetailEvent(paramString);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.DataFileCache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */