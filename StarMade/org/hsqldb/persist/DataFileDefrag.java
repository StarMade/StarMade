package org.hsqldb.persist;

import java.io.IOException;
import java.io.PrintStream;
import org.hsqldb.Database;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.Row;
import org.hsqldb.SchemaManager;
import org.hsqldb.Session;
import org.hsqldb.SessionManager;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.DoubleIntIndex;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.StopWatch;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.store.BitMap;

final class DataFileDefrag
{
  RandomAccessInterface randomAccessOut;
  long fileOffset;
  StopWatch stopw = new StopWatch();
  String dataFileName;
  long[][] rootsList;
  Database database;
  DataFileCache dataCache;
  int scale;
  DoubleIntIndex pointerLookup;
  
  DataFileDefrag(Database paramDatabase, DataFileCache paramDataFileCache, String paramString)
  {
    this.database = paramDatabase;
    this.dataCache = paramDataFileCache;
    this.scale = paramDataFileCache.dataFileScale;
    this.dataFileName = paramString;
  }
  
  void process()
  {
    Object localObject1 = null;
    this.database.logger.logDetailEvent("Defrag process begins");
    HsqlArrayList localHsqlArrayList = this.database.schemaManager.getAllTables(true);
    this.rootsList = new long[localHsqlArrayList.size()][];
    long l1 = 0L;
    int i = 0;
    int j = localHsqlArrayList.size();
    Table localTable;
    Object localObject2;
    while (i < j)
    {
      localTable = (Table)localHsqlArrayList.get(i);
      if (localTable.getTableType() == 5)
      {
        localObject2 = this.database.persistentStoreCollection.getStore(localTable);
        long l2 = ((PersistentStore)localObject2).elementCount();
        if (l2 > l1) {
          l1 = l2;
        }
      }
      i++;
    }
    if (l1 > 1073741823L) {
      throw Error.error(3426);
    }
    try
    {
      this.pointerLookup = new DoubleIntIndex((int)l1, false);
      if (this.database.logger.isStoredFileAccess()) {
        this.randomAccessOut = ScaledRAFile.newScaledRAFile(this.database, this.dataFileName + ".new", false, 3);
      } else {
        this.randomAccessOut = new ScaledRAFileSimple(this.database, this.dataFileName + ".new", "rw");
      }
      this.randomAccessOut.write(new byte[this.dataCache.initialFreePos], 0, this.dataCache.initialFreePos);
      this.fileOffset = this.dataCache.initialFreePos;
      i = 0;
      j = localHsqlArrayList.size();
      while (i < j)
      {
        localTable = (Table)localHsqlArrayList.get(i);
        if (localTable.getTableType() == 5)
        {
          localObject2 = writeTableToDataFile(localTable);
          this.rootsList[i] = localObject2;
          this.randomAccessOut.synch();
        }
        else
        {
          this.rootsList[i] = null;
        }
        this.database.logger.logDetailEvent("table complete " + localTable.getName().name);
        i++;
      }
      this.randomAccessOut.seek(12L);
      this.randomAccessOut.writeLong(this.fileOffset);
      i = 0;
      if (this.database.logger.propIncrementBackup) {
        i = BitMap.set(i, 1);
      }
      i = BitMap.set(i, 4);
      i = BitMap.set(i, 2);
      this.randomAccessOut.seek(28L);
      this.randomAccessOut.writeInt(i);
      this.randomAccessOut.synch();
      this.randomAccessOut.close();
      this.randomAccessOut = null;
      j = 0;
      int k = this.rootsList.length;
      while (j < k)
      {
        localObject2 = this.rootsList[j];
        if (localObject2 != null) {
          this.database.logger.logDetailEvent("roots: " + StringUtil.getList((long[])localObject2, ",", ""));
        }
        j++;
      }
    }
    catch (IOException localIOException)
    {
      localObject1 = localIOException;
      throw Error.error(452, localIOException);
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      localObject1 = localOutOfMemoryError;
      throw Error.error(460, localOutOfMemoryError);
    }
    catch (Throwable localThrowable2)
    {
      localObject1 = localThrowable2;
      throw Error.error(458, localThrowable2);
    }
    finally
    {
      try
      {
        if (this.randomAccessOut != null) {
          this.randomAccessOut.close();
        }
      }
      catch (Throwable localThrowable3) {}
      if ((localObject1 instanceof OutOfMemoryError)) {
        this.database.logger.logInfoEvent("defrag failed - out of memory - required: " + l1 * 8L);
      }
      if (localObject1 == null)
      {
        this.database.logger.logDetailEvent("Defrag transfer complete: " + this.stopw.elapsedTime());
      }
      else
      {
        this.database.logger.logSevereEvent("defrag failed ", (Throwable)localObject1);
        this.database.logger.getFileAccess().removeElement(this.dataFileName + ".new");
      }
    }
  }
  
  void updateTableIndexRoots()
  {
    HsqlArrayList localHsqlArrayList = this.database.schemaManager.getAllTables(true);
    int i = 0;
    int j = localHsqlArrayList.size();
    while (i < j)
    {
      Table localTable = (Table)localHsqlArrayList.get(i);
      if (localTable.getTableType() == 5)
      {
        long[] arrayOfLong = this.rootsList[i];
        localTable.setIndexRoots(arrayOfLong);
      }
      i++;
    }
  }
  
  long[] writeTableToDataFile(Table paramTable)
    throws IOException
  {
    Session localSession = this.database.getSessionManager().getSysSession();
    PersistentStore localPersistentStore = paramTable.getRowStore(localSession);
    RowOutputInterface localRowOutputInterface = this.dataCache.rowOut.duplicate();
    long[] arrayOfLong = paramTable.getIndexRootsArray();
    long l1 = this.fileOffset;
    long l2 = 0L;
    this.pointerLookup.removeAll();
    this.pointerLookup.setKeysSearchTarget();
    this.database.logger.logDetailEvent("lookup begins " + paramTable.getName().name + " " + this.stopw.elapsedTime());
    RowIterator localRowIterator = paramTable.rowIteratorClustered(localPersistentStore);
    Row localRow;
    while (localRowIterator.hasNext())
    {
      localRow = localRowIterator.getNextRow();
      this.pointerLookup.addUnsorted((int)localRow.getPos(), (int)(l1 / this.scale));
      if ((l2 != 0L) && (l2 % 100000L == 0L)) {
        this.database.logger.logDetailEvent("pointer pair for row " + l2 + " " + localRow.getPos() + " " + l1);
      }
      l1 += localRow.getStorageSize();
      l2 += 1L;
    }
    this.database.logger.logDetailEvent("table read " + paramTable.getName().name + " " + this.stopw.elapsedTime());
    l2 = 0L;
    localRowIterator = paramTable.rowIteratorClustered(localPersistentStore);
    while (localRowIterator.hasNext())
    {
      localRow = localRowIterator.getNextRow();
      localRowOutputInterface.reset();
      localRow.write(localRowOutputInterface, this.pointerLookup);
      this.randomAccessOut.write(localRowOutputInterface.getOutputStream().getBuffer(), 0, localRowOutputInterface.size());
      this.fileOffset += localRow.getStorageSize();
      if ((l2 != 0L) && (l2 % 100000L == 0L)) {
        this.database.logger.logDetailEvent("rows count " + l2 + " " + this.stopw.elapsedTime());
      }
      l2 += 1L;
    }
    for (int i = 0; i < paramTable.getIndexCount(); i++) {
      if (arrayOfLong[i] != -1L)
      {
        int j = this.pointerLookup.findFirstEqualKeyIndex((int)arrayOfLong[i]);
        if (j == -1) {
          throw Error.error(466);
        }
        arrayOfLong[i] = this.pointerLookup.getValue(j);
      }
    }
    this.database.logger.logDetailEvent("table written " + paramTable.getName().name);
    return arrayOfLong;
  }
  
  public long[][] getIndexRoots()
  {
    return this.rootsList;
  }
  
  static boolean checkAllTables(Database paramDatabase)
  {
    Session localSession = paramDatabase.getSessionManager().getSysSession();
    HsqlArrayList localHsqlArrayList = paramDatabase.schemaManager.getAllTables(true);
    int i = 0;
    int j = localHsqlArrayList.size();
    while (i < j)
    {
      Table localTable = (Table)localHsqlArrayList.get(i);
      int k = 0;
      if (localTable.getTableType() == 5)
      {
        RowIterator localRowIterator = localTable.rowIterator(localSession);
        while (localRowIterator.hasNext())
        {
          Row localRow = localRowIterator.getNextRow();
          k++;
        }
        System.out.println("table " + localTable.getName().name + " " + k);
      }
      i++;
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.DataFileDefrag
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */