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

public class TextCache
  extends DataFileCache
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
    if (this.dataFileName == null) {
      throw Error.error(301);
    }
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
      if (this.fileFreePosition > 2147483647L) {
        throw Error.error(468);
      }
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
    if (this.dataFile == null) {
      return;
    }
    this.writeLock.lock();
    try
    {
      this.cache.saveAll();
      int i = this.dataFile.length() <= TextFileSettings.NL.length() ? 1 : 0;
      this.dataFile.synch();
      this.dataFile.close();
      this.dataFile = null;
      if ((i != 0) && (!this.cacheReadonly)) {
        FileUtil.getFileUtil().delete(this.dataFileName);
      }
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
      if (localCachedObject != null) {
        return;
      }
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
  
  /* Error */
  public CachedObject get(CachedObject paramCachedObject, PersistentStore paramPersistentStore, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_0
    //   7: getfield 62	org/hsqldb/persist/TextCache:writeLock	Ljava/util/concurrent/locks/Lock;
    //   10: invokeinterface 63 1 0
    //   15: aload_0
    //   16: getfield 64	org/hsqldb/persist/TextCache:cache	Lorg/hsqldb/persist/Cache;
    //   19: aload_1
    //   20: invokeinterface 92 1 0
    //   25: invokevirtual 103	org/hsqldb/persist/Cache:get	(J)Lorg/hsqldb/persist/CachedObject;
    //   28: astore 4
    //   30: aload 4
    //   32: ifnull +18 -> 50
    //   35: aload_1
    //   36: astore 5
    //   38: aload_0
    //   39: getfield 62	org/hsqldb/persist/TextCache:writeLock	Ljava/util/concurrent/locks/Lock;
    //   42: invokeinterface 72 1 0
    //   47: aload 5
    //   49: areturn
    //   50: aload_0
    //   51: getfield 5	org/hsqldb/persist/TextCache:buffer	Lorg/hsqldb/lib/HsqlByteArrayOutputStream;
    //   54: aload_1
    //   55: invokeinterface 74 1 0
    //   60: invokevirtual 104	org/hsqldb/lib/HsqlByteArrayOutputStream:reset	(I)V
    //   63: aload_0
    //   64: getfield 50	org/hsqldb/persist/TextCache:dataFile	Lorg/hsqldb/persist/RandomAccessInterface;
    //   67: aload_1
    //   68: invokeinterface 92 1 0
    //   73: invokeinterface 93 3 0
    //   78: aload_0
    //   79: getfield 50	org/hsqldb/persist/TextCache:dataFile	Lorg/hsqldb/persist/RandomAccessInterface;
    //   82: aload_0
    //   83: getfield 5	org/hsqldb/persist/TextCache:buffer	Lorg/hsqldb/lib/HsqlByteArrayOutputStream;
    //   86: invokevirtual 94	org/hsqldb/lib/HsqlByteArrayOutputStream:getBuffer	()[B
    //   89: iconst_0
    //   90: aload_1
    //   91: invokeinterface 74 1 0
    //   96: invokeinterface 105 4 0
    //   101: aload_0
    //   102: getfield 5	org/hsqldb/persist/TextCache:buffer	Lorg/hsqldb/lib/HsqlByteArrayOutputStream;
    //   105: aload_1
    //   106: invokeinterface 74 1 0
    //   111: invokevirtual 106	org/hsqldb/lib/HsqlByteArrayOutputStream:setSize	(I)V
    //   114: aload_0
    //   115: getfield 5	org/hsqldb/persist/TextCache:buffer	Lorg/hsqldb/lib/HsqlByteArrayOutputStream;
    //   118: aload_0
    //   119: getfield 15	org/hsqldb/persist/TextCache:textFileSettings	Lorg/hsqldb/persist/TextFileSettings;
    //   122: getfield 39	org/hsqldb/persist/TextFileSettings:stringEncoding	Ljava/lang/String;
    //   125: invokevirtual 107	org/hsqldb/lib/HsqlByteArrayOutputStream:toString	(Ljava/lang/String;)Ljava/lang/String;
    //   128: astore 5
    //   130: aload_0
    //   131: getfield 37	org/hsqldb/persist/TextCache:rowIn	Lorg/hsqldb/rowio/RowInputInterface;
    //   134: checkcast 42	org/hsqldb/rowio/RowInputText
    //   137: aload 5
    //   139: aload_1
    //   140: invokeinterface 92 1 0
    //   145: aload_0
    //   146: getfield 5	org/hsqldb/persist/TextCache:buffer	Lorg/hsqldb/lib/HsqlByteArrayOutputStream;
    //   149: invokevirtual 95	org/hsqldb/lib/HsqlByteArrayOutputStream:size	()I
    //   152: invokevirtual 108	org/hsqldb/rowio/RowInputText:setSource	(Ljava/lang/String;JI)V
    //   155: aload_2
    //   156: aload_1
    //   157: aload_0
    //   158: getfield 37	org/hsqldb/persist/TextCache:rowIn	Lorg/hsqldb/rowio/RowInputInterface;
    //   161: invokeinterface 109 3 0
    //   166: pop
    //   167: aload_0
    //   168: getfield 64	org/hsqldb/persist/TextCache:cache	Lorg/hsqldb/persist/Cache;
    //   171: aload_1
    //   172: invokeinterface 92 1 0
    //   177: aload_1
    //   178: invokevirtual 100	org/hsqldb/persist/Cache:put	(JLorg/hsqldb/persist/CachedObject;)V
    //   181: aload_1
    //   182: astore 6
    //   184: aload_0
    //   185: getfield 62	org/hsqldb/persist/TextCache:writeLock	Ljava/util/concurrent/locks/Lock;
    //   188: invokeinterface 72 1 0
    //   193: aload 6
    //   195: areturn
    //   196: astore 5
    //   198: aload_0
    //   199: getfield 10	org/hsqldb/persist/TextCache:database	Lorg/hsqldb/Database;
    //   202: getfield 75	org/hsqldb/Database:logger	Lorg/hsqldb/persist/Logger;
    //   205: new 76	java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   212: aload_0
    //   213: getfield 17	org/hsqldb/persist/TextCache:dataFileName	Ljava/lang/String;
    //   216: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: ldc 110
    //   221: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: aload_1
    //   225: invokeinterface 92 1 0
    //   230: invokevirtual 111	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   233: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   236: aload 5
    //   238: invokevirtual 81	org/hsqldb/persist/Logger:logSevereEvent	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   241: aload_0
    //   242: getfield 64	org/hsqldb/persist/TextCache:cache	Lorg/hsqldb/persist/Cache;
    //   245: invokevirtual 112	org/hsqldb/persist/Cache:forceCleanUp	()V
    //   248: invokestatic 113	java/lang/System:gc	()V
    //   251: aload_1
    //   252: astore 6
    //   254: aload_0
    //   255: getfield 62	org/hsqldb/persist/TextCache:writeLock	Ljava/util/concurrent/locks/Lock;
    //   258: invokeinterface 72 1 0
    //   263: aload 6
    //   265: areturn
    //   266: astore 7
    //   268: aload_0
    //   269: getfield 62	org/hsqldb/persist/TextCache:writeLock	Ljava/util/concurrent/locks/Lock;
    //   272: invokeinterface 72 1 0
    //   277: aload 7
    //   279: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	280	0	this	TextCache
    //   0	280	1	paramCachedObject	CachedObject
    //   0	280	2	paramPersistentStore	PersistentStore
    //   0	280	3	paramBoolean	boolean
    //   28	3	4	localCachedObject1	CachedObject
    //   36	102	5	localObject1	Object
    //   196	41	5	localIOException	IOException
    //   182	82	6	localCachedObject2	CachedObject
    //   266	12	7	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   50	184	196	java/io/IOException
    //   15	38	266	finally
    //   50	184	266	finally
    //   196	254	266	finally
    //   266	268	266	finally
  }
  
  public CachedObject get(long paramLong, PersistentStore paramPersistentStore, boolean paramBoolean)
  {
    throw Error.runtimeError(201, "TextCache");
  }
  
  protected void saveRows(CachedObject[] paramArrayOfCachedObject, int paramInt1, int paramInt2) {}
  
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
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */