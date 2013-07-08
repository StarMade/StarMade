package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.Logger;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.persist.PersistentStoreCollectionDatabase;
import org.hsqldb.persist.PersistentStoreCollectionSession;
import org.hsqldb.types.Type;

public class TableBase
{
  public static final int INFO_SCHEMA_TABLE = 1;
  public static final int SYSTEM_SUBQUERY = 2;
  public static final int TEMP_TABLE = 3;
  public static final int MEMORY_TABLE = 4;
  public static final int CACHED_TABLE = 5;
  public static final int TEMP_TEXT_TABLE = 6;
  public static final int TEXT_TABLE = 7;
  public static final int VIEW_TABLE = 8;
  public static final int RESULT_TABLE = 9;
  public static final int TRANSITION_TABLE = 10;
  public static final int FUNCTION_TABLE = 11;
  public static final int SYSTEM_TABLE = 12;
  public static final int CHANGE_SET_TABLE = 13;
  public static final int SCOPE_STATEMENT = 21;
  public static final int SCOPE_TRANSACTION = 22;
  public static final int SCOPE_SESSION = 23;
  public static final int SCOPE_FULL = 24;
  public PersistentStore store;
  public int persistenceScope;
  public long persistenceId;
  int[] primaryKeyCols;
  Type[] primaryKeyTypes;
  int[] primaryKeyColsSequence;
  Index[] indexList;
  public Database database;
  int[] bestRowIdentifierCols;
  boolean bestRowIdentifierStrict;
  int[] bestIndexForColumn;
  Index bestIndex;
  Index fullIndex;
  boolean[] colNotNull;
  Type[] colTypes;
  protected int columnCount;
  int tableType;
  protected boolean isReadOnly;
  protected boolean isTemp;
  protected boolean isCached;
  protected boolean isText;
  boolean isView;
  protected boolean isWithDataSource;
  public boolean isSessionBased;
  protected boolean isSchemaBased;
  protected boolean isLogged;
  private boolean isTransactional = true;
  boolean hasLobColumn;
  long dataTimestamp;
  
  TableBase() {}
  
  public TableBase(Session paramSession, Database paramDatabase, int paramInt1, int paramInt2, Type[] paramArrayOfType)
  {
    this.tableType = paramInt2;
    this.persistenceScope = paramInt1;
    this.isSessionBased = true;
    this.persistenceId = paramDatabase.persistentStoreCollection.getNextId();
    this.database = paramDatabase;
    this.colTypes = paramArrayOfType;
    this.columnCount = paramArrayOfType.length;
    this.primaryKeyCols = new int[0];
    this.primaryKeyTypes = new Type[0];
    this.indexList = new Index[0];
    createPrimaryIndex(this.primaryKeyCols, this.primaryKeyTypes, null);
  }
  
  public TableBase duplicate()
  {
    TableBase localTableBase = new TableBase();
    localTableBase.tableType = this.tableType;
    localTableBase.persistenceScope = this.persistenceScope;
    localTableBase.isSessionBased = this.isSessionBased;
    localTableBase.persistenceId = this.database.persistentStoreCollection.getNextId();
    localTableBase.database = this.database;
    localTableBase.colTypes = this.colTypes;
    localTableBase.columnCount = this.columnCount;
    localTableBase.primaryKeyCols = this.primaryKeyCols;
    localTableBase.primaryKeyTypes = this.primaryKeyTypes;
    localTableBase.indexList = this.indexList;
    return localTableBase;
  }
  
  public final int getTableType()
  {
    return this.tableType;
  }
  
  public long getPersistenceId()
  {
    return this.persistenceId;
  }
  
  int getId()
  {
    return 0;
  }
  
  public final boolean onCommitPreserve()
  {
    return this.persistenceScope == 23;
  }
  
  public final RowIterator rowIterator(Session paramSession)
  {
    PersistentStore localPersistentStore = getRowStore(paramSession);
    return getPrimaryIndex().firstRow(paramSession, localPersistentStore);
  }
  
  public final RowIterator rowIterator(PersistentStore paramPersistentStore)
  {
    return getPrimaryIndex().firstRow(paramPersistentStore);
  }
  
  public final int getIndexCount()
  {
    return this.indexList.length;
  }
  
  public final Index getPrimaryIndex()
  {
    return this.indexList.length > 0 ? this.indexList[0] : null;
  }
  
  public final Type[] getPrimaryKeyTypes()
  {
    return this.primaryKeyTypes;
  }
  
  public final boolean hasPrimaryKey()
  {
    return this.primaryKeyCols.length != 0;
  }
  
  public final int[] getPrimaryKey()
  {
    return this.primaryKeyCols;
  }
  
  public final Type[] getColumnTypes()
  {
    return this.colTypes;
  }
  
  public Index getFullIndex()
  {
    return this.fullIndex;
  }
  
  public final Index getIndex(int paramInt)
  {
    return this.indexList[paramInt];
  }
  
  public final Index[] getIndexList()
  {
    return this.indexList;
  }
  
  public final boolean[] getNewColumnCheckList()
  {
    return new boolean[getColumnCount()];
  }
  
  public int getColumnCount()
  {
    return this.columnCount;
  }
  
  public final int getDataColumnCount()
  {
    return this.colTypes.length;
  }
  
  public boolean isTransactional()
  {
    return this.isTransactional;
  }
  
  public void setTransactional(boolean paramBoolean)
  {
    this.isTransactional = paramBoolean;
  }
  
  public final void setBestRowIdentifiers()
  {
    Object localObject = null;
    int i = 0;
    boolean bool = false;
    int j = 0;
    if (this.colNotNull == null) {
      return;
    }
    this.bestIndex = null;
    this.bestIndexForColumn = new int[this.colTypes.length];
    ArrayUtil.fillArray(this.bestIndexForColumn, -1);
    for (int k = 0; k < this.indexList.length; k++)
    {
      Index localIndex1 = this.indexList[k];
      int[] arrayOfInt = localIndex1.getColumns();
      int m = localIndex1.getColumnCount();
      if (m != 0)
      {
        if (k == 0) {
          bool = true;
        }
        if (this.bestIndexForColumn[arrayOfInt[0]] == -1)
        {
          this.bestIndexForColumn[arrayOfInt[0]] = k;
        }
        else
        {
          Index localIndex2 = this.indexList[this.bestIndexForColumn[arrayOfInt[0]]];
          if (m > localIndex2.getColumns().length) {
            this.bestIndexForColumn[arrayOfInt[0]] = k;
          }
        }
        if (!localIndex1.isUnique())
        {
          if (this.bestIndex == null) {
            this.bestIndex = localIndex1;
          }
        }
        else
        {
          int n = 0;
          for (int i1 = 0; i1 < m; i1++) {
            if (this.colNotNull[arrayOfInt[i1]] != 0) {
              n++;
            }
          }
          if (this.bestIndex != null) {
            this.bestIndex = localIndex1;
          }
          if (n == m)
          {
            if ((localObject == null) || (i != j) || (m < i))
            {
              localObject = arrayOfInt;
              i = m;
              j = m;
              bool = true;
            }
          }
          else if ((!bool) && ((localObject == null) || (m < i) || (n > j)))
          {
            localObject = arrayOfInt;
            i = m;
            j = n;
          }
        }
      }
    }
    if ((localObject == null) || (i == localObject.length)) {
      this.bestRowIdentifierCols = localObject;
    } else {
      this.bestRowIdentifierCols = ArrayUtil.arraySlice(localObject, 0, i);
    }
    this.bestRowIdentifierStrict = bool;
    if (this.indexList[0].getColumnCount() > 0) {
      this.bestIndex = this.indexList[0];
    }
  }
  
  public final void createPrimaryIndex(int[] paramArrayOfInt, Type[] paramArrayOfType, HsqlNameManager.HsqlName paramHsqlName)
  {
    long l = this.database.persistentStoreCollection.getNextId();
    Index localIndex = this.database.logger.newIndex(paramHsqlName, l, this, paramArrayOfInt, null, null, paramArrayOfType, true, paramArrayOfInt.length > 0, paramArrayOfInt.length > 0, false);
    try
    {
      addIndex(localIndex);
    }
    catch (HsqlException localHsqlException) {}
  }
  
  public final Index createAndAddIndexStructure(HsqlNameManager.HsqlName paramHsqlName, int[] paramArrayOfInt, boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    Index localIndex = createIndexStructure(paramHsqlName, paramArrayOfInt, paramArrayOfBoolean1, paramArrayOfBoolean2, paramBoolean1, paramBoolean2, paramBoolean3);
    addIndex(localIndex);
    return localIndex;
  }
  
  final Index createIndexStructure(HsqlNameManager.HsqlName paramHsqlName, int[] paramArrayOfInt, boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (this.primaryKeyCols == null) {
      throw Error.runtimeError(201, "createIndex");
    }
    int i = paramArrayOfInt.length;
    int[] arrayOfInt = new int[i];
    Type[] arrayOfType = new Type[i];
    for (int j = 0; j < i; j++)
    {
      arrayOfInt[j] = paramArrayOfInt[j];
      arrayOfType[j] = this.colTypes[arrayOfInt[j]];
    }
    long l = this.database.persistentStoreCollection.getNextId();
    Index localIndex = this.database.logger.newIndex(paramHsqlName, l, this, arrayOfInt, paramArrayOfBoolean1, paramArrayOfBoolean2, arrayOfType, false, paramBoolean1, paramBoolean2, paramBoolean3);
    return localIndex;
  }
  
  public void dropIndex(int paramInt)
  {
    this.indexList = ((Index[])ArrayUtil.toAdjustedArray(this.indexList, null, paramInt, -1));
    for (int i = 0; i < this.indexList.length; i++) {
      this.indexList[i].setPosition(i);
    }
    setBestRowIdentifiers();
    if (this.store != null) {
      this.store.resetAccessorKeys(this.indexList);
    }
  }
  
  final void addIndex(Index paramIndex)
  {
    for (int i = 0; i < this.indexList.length; i++)
    {
      Index localIndex = this.indexList[i];
      int j = paramIndex.getIndexOrderValue() - localIndex.getIndexOrderValue();
      if (j < 0) {
        break;
      }
    }
    this.indexList = ((Index[])ArrayUtil.toAdjustedArray(this.indexList, paramIndex, i, 1));
    for (i = 0; i < this.indexList.length; i++) {
      this.indexList[i].setPosition(i);
    }
    if (this.store != null) {
      try
      {
        this.store.resetAccessorKeys(this.indexList);
      }
      catch (HsqlException localHsqlException)
      {
        this.indexList = ((Index[])ArrayUtil.toAdjustedArray(this.indexList, null, paramIndex.getPosition(), -1));
        for (i = 0; i < this.indexList.length; i++) {
          this.indexList[i].setPosition(i);
        }
        throw localHsqlException;
      }
    }
    setBestRowIdentifiers();
  }
  
  final void removeIndex(int paramInt)
  {
    setBestRowIdentifiers();
  }
  
  public final void setIndexes(Index[] paramArrayOfIndex)
  {
    this.indexList = paramArrayOfIndex;
  }
  
  public final Object[] getEmptyRowData()
  {
    return new Object[getDataColumnCount()];
  }
  
  public final Index createIndex(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, int[] paramArrayOfInt, boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    Index localIndex = createAndAddIndexStructure(paramHsqlName, paramArrayOfInt, paramArrayOfBoolean1, paramArrayOfBoolean2, paramBoolean1, paramBoolean2, paramBoolean3);
    return localIndex;
  }
  
  public void clearAllData(Session paramSession)
  {
    PersistentStore localPersistentStore = getRowStore(paramSession);
    localPersistentStore.removeAll();
  }
  
  public void clearAllData(PersistentStore paramPersistentStore)
  {
    paramPersistentStore.removeAll();
  }
  
  public final boolean isEmpty(Session paramSession)
  {
    if (getIndexCount() == 0) {
      return true;
    }
    PersistentStore localPersistentStore = getRowStore(paramSession);
    return getIndex(0).isEmpty(localPersistentStore);
  }
  
  public PersistentStore getRowStore(Session paramSession)
  {
    return this.store == null ? paramSession.sessionData.persistentStoreCollection.getStore(this) : this.store;
  }
  
  public void setDataTimestamp(long paramLong) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.TableBase
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */