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
    this.field_2045 = FileUtil.getFileUtil();
    this.textFileSettings = new TextFileSettings(paramDatabase, paramString);
    this.dataFileName = this.textFileSettings.getFileName();
    if (this.dataFileName == null) {
      throw Error.error(301);
    }
    this.dataFileName = ((FileUtil)this.field_2045).canonicalOrAbsolutePath(this.dataFileName);
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
      this.rowIn = new RowInputTextQuoted(this.textFileSettings.field_1928, this.textFileSettings.field_1929, this.textFileSettings.lvs, this.textFileSettings.isAllQuoted);
      this.rowOut = new RowOutputTextQuoted(this.textFileSettings.field_1928, this.textFileSettings.field_1929, this.textFileSettings.lvs, this.textFileSettings.isAllQuoted, this.textFileSettings.stringEncoding);
    }
    else
    {
      this.rowIn = new RowInputText(this.textFileSettings.field_1928, this.textFileSettings.field_1929, this.textFileSettings.lvs, false);
      this.rowOut = new RowOutputText(this.textFileSettings.field_1928, this.textFileSettings.field_1929, this.textFileSettings.lvs, false, this.textFileSettings.stringEncoding);
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
      int i = this.dataFile.length() <= TextFileSettings.field_1927.length() ? 1 : 0;
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
    //   7: getfield 205	org/hsqldb/persist/TextCache:writeLock	Ljava/util/concurrent/locks/Lock;
    //   10: invokeinterface 210 1 0
    //   15: aload_0
    //   16: getfield 214	org/hsqldb/persist/TextCache:cache	Lorg/hsqldb/persist/Cache;
    //   19: aload_1
    //   20: invokeinterface 319 1 0
    //   25: invokevirtual 354	org/hsqldb/persist/Cache:get	(J)Lorg/hsqldb/persist/CachedObject;
    //   28: astore 4
    //   30: aload 4
    //   32: ifnull +18 -> 50
    //   35: aload_1
    //   36: astore 5
    //   38: aload_0
    //   39: getfield 205	org/hsqldb/persist/TextCache:writeLock	Ljava/util/concurrent/locks/Lock;
    //   42: invokeinterface 241 1 0
    //   47: aload 5
    //   49: areturn
    //   50: aload_0
    //   51: getfield 32	org/hsqldb/persist/TextCache:buffer	Lorg/hsqldb/lib/HsqlByteArrayOutputStream;
    //   54: aload_1
    //   55: invokeinterface 251 1 0
    //   60: invokevirtual 356	org/hsqldb/lib/HsqlByteArrayOutputStream:reset	(I)V
    //   63: aload_0
    //   64: getfield 169	org/hsqldb/persist/TextCache:dataFile	Lorg/hsqldb/persist/RandomAccessInterface;
    //   67: aload_1
    //   68: invokeinterface 319 1 0
    //   73: invokeinterface 322 3 0
    //   78: aload_0
    //   79: getfield 169	org/hsqldb/persist/TextCache:dataFile	Lorg/hsqldb/persist/RandomAccessInterface;
    //   82: aload_0
    //   83: getfield 32	org/hsqldb/persist/TextCache:buffer	Lorg/hsqldb/lib/HsqlByteArrayOutputStream;
    //   86: invokevirtual 326	org/hsqldb/lib/HsqlByteArrayOutputStream:getBuffer	()[B
    //   89: iconst_0
    //   90: aload_1
    //   91: invokeinterface 251 1 0
    //   96: invokeinterface 359 4 0
    //   101: aload_0
    //   102: getfield 32	org/hsqldb/persist/TextCache:buffer	Lorg/hsqldb/lib/HsqlByteArrayOutputStream;
    //   105: aload_1
    //   106: invokeinterface 251 1 0
    //   111: invokevirtual 362	org/hsqldb/lib/HsqlByteArrayOutputStream:setSize	(I)V
    //   114: aload_0
    //   115: getfield 32	org/hsqldb/persist/TextCache:buffer	Lorg/hsqldb/lib/HsqlByteArrayOutputStream;
    //   118: aload_0
    //   119: getfield 58	org/hsqldb/persist/TextCache:textFileSettings	Lorg/hsqldb/persist/TextFileSettings;
    //   122: getfield 132	org/hsqldb/persist/TextFileSettings:stringEncoding	Ljava/lang/String;
    //   125: invokevirtual 364	org/hsqldb/lib/HsqlByteArrayOutputStream:toString	(Ljava/lang/String;)Ljava/lang/String;
    //   128: astore 5
    //   130: aload_0
    //   131: getfield 127	org/hsqldb/persist/TextCache:rowIn	Lorg/hsqldb/rowio/RowInputInterface;
    //   134: checkcast 141	org/hsqldb/rowio/RowInputText
    //   137: aload 5
    //   139: aload_1
    //   140: invokeinterface 319 1 0
    //   145: aload_0
    //   146: getfield 32	org/hsqldb/persist/TextCache:buffer	Lorg/hsqldb/lib/HsqlByteArrayOutputStream;
    //   149: invokevirtual 329	org/hsqldb/lib/HsqlByteArrayOutputStream:size	()I
    //   152: invokevirtual 368	org/hsqldb/rowio/RowInputText:setSource	(Ljava/lang/String;JI)V
    //   155: aload_2
    //   156: aload_1
    //   157: aload_0
    //   158: getfield 127	org/hsqldb/persist/TextCache:rowIn	Lorg/hsqldb/rowio/RowInputInterface;
    //   161: invokeinterface 371 3 0
    //   166: pop
    //   167: aload_0
    //   168: getfield 214	org/hsqldb/persist/TextCache:cache	Lorg/hsqldb/persist/Cache;
    //   171: aload_1
    //   172: invokeinterface 319 1 0
    //   177: aload_1
    //   178: invokevirtual 344	org/hsqldb/persist/Cache:put	(JLorg/hsqldb/persist/CachedObject;)V
    //   181: aload_1
    //   182: astore 6
    //   184: aload_0
    //   185: getfield 205	org/hsqldb/persist/TextCache:writeLock	Ljava/util/concurrent/locks/Lock;
    //   188: invokeinterface 241 1 0
    //   193: aload 6
    //   195: areturn
    //   196: astore 5
    //   198: aload_0
    //   199: getfield 43	org/hsqldb/persist/TextCache:database	Lorg/hsqldb/Database;
    //   202: getfield 255	org/hsqldb/Database:logger	Lorg/hsqldb/persist/Logger;
    //   205: new 257	java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial 258	java/lang/StringBuilder:<init>	()V
    //   212: aload_0
    //   213: getfield 65	org/hsqldb/persist/TextCache:dataFileName	Ljava/lang/String;
    //   216: invokevirtual 264	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: ldc_w 373
    //   222: invokevirtual 264	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: aload_1
    //   226: invokeinterface 319 1 0
    //   231: invokevirtual 376	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   234: invokevirtual 265	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   237: aload 5
    //   239: invokevirtual 271	org/hsqldb/persist/Logger:logSevereEvent	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   242: aload_0
    //   243: getfield 214	org/hsqldb/persist/TextCache:cache	Lorg/hsqldb/persist/Cache;
    //   246: invokevirtual 379	org/hsqldb/persist/Cache:forceCleanUp	()V
    //   249: invokestatic 384	java/lang/System:gc	()V
    //   252: aload_1
    //   253: astore 6
    //   255: aload_0
    //   256: getfield 205	org/hsqldb/persist/TextCache:writeLock	Ljava/util/concurrent/locks/Lock;
    //   259: invokeinterface 241 1 0
    //   264: aload 6
    //   266: areturn
    //   267: astore 7
    //   269: aload_0
    //   270: getfield 205	org/hsqldb/persist/TextCache:writeLock	Ljava/util/concurrent/locks/Lock;
    //   273: invokeinterface 241 1 0
    //   278: aload 7
    //   280: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	281	0	this	TextCache
    //   0	281	1	paramCachedObject	CachedObject
    //   0	281	2	paramPersistentStore	PersistentStore
    //   0	281	3	paramBoolean	boolean
    //   28	3	4	localCachedObject1	CachedObject
    //   36	102	5	localObject1	Object
    //   196	42	5	localIOException	IOException
    //   182	83	6	localCachedObject2	CachedObject
    //   267	12	7	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   50	184	196	java/io/IOException
    //   15	38	267	finally
    //   50	184	267	finally
    //   196	255	267	finally
    //   267	269	267	finally
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
      String str = paramString + TextFileSettings.field_1927;
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


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.TextCache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */