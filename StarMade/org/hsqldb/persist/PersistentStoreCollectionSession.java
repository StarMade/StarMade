package org.hsqldb.persist;

import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.dbinfo.DatabaseInformation;
import org.hsqldb.error.Error;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HsqlDeque;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.store.ValuePool;

public class PersistentStoreCollectionSession
  implements PersistentStoreCollection
{
  private final Session session;
  private final LongKeyHashMap rowStoreMapSession = new LongKeyHashMap();
  private LongKeyHashMap rowStoreMapTransaction = new LongKeyHashMap();
  private LongKeyHashMap rowStoreMapStatement = new LongKeyHashMap();
  private HsqlDeque rowStoreListStatement;
  DataFileCacheSession resultCache;
  
  public PersistentStoreCollectionSession(Session paramSession)
  {
    this.session = paramSession;
  }
  
  public void setStore(Object paramObject, PersistentStore paramPersistentStore)
  {
    TableBase localTableBase = (TableBase)paramObject;
    switch (localTableBase.persistenceScope)
    {
    case 21: 
      if (paramPersistentStore == null) {
        this.rowStoreMapStatement.remove(localTableBase.getPersistenceId());
      } else {
        this.rowStoreMapStatement.put(localTableBase.getPersistenceId(), paramPersistentStore);
      }
      break;
    case 22: 
    case 24: 
      if (paramPersistentStore == null) {
        this.rowStoreMapTransaction.remove(localTableBase.getPersistenceId());
      } else {
        this.rowStoreMapTransaction.put(localTableBase.getPersistenceId(), paramPersistentStore);
      }
      break;
    case 23: 
      if (paramPersistentStore == null) {
        this.rowStoreMapSession.remove(localTableBase.getPersistenceId());
      } else {
        this.rowStoreMapSession.put(localTableBase.getPersistenceId(), paramPersistentStore);
      }
      break;
    default: 
      throw Error.runtimeError(201, "PersistentStoreCollectionSession");
    }
  }
  
  public PersistentStore getViewStore(long paramLong)
  {
    return (PersistentStore)this.rowStoreMapStatement.get(paramLong);
  }
  
  public PersistentStore getStore(Object paramObject)
  {
    try
    {
      TableBase localTableBase = (TableBase)paramObject;
      PersistentStore localPersistentStore;
      switch (localTableBase.persistenceScope)
      {
      case 21: 
        localPersistentStore = (PersistentStore)this.rowStoreMapStatement.get(localTableBase.getPersistenceId());
        if (localPersistentStore == null) {
          localPersistentStore = this.session.database.logger.newStore(this.session, this, localTableBase);
        }
        return localPersistentStore;
      case 22: 
      case 24: 
        localPersistentStore = (PersistentStore)this.rowStoreMapTransaction.get(localTableBase.getPersistenceId());
        if (localPersistentStore == null) {
          localPersistentStore = this.session.database.logger.newStore(this.session, this, localTableBase);
        }
        if (localTableBase.getTableType() == 1) {
          this.session.database.dbInfo.setStore(this.session, (Table)localTableBase, localPersistentStore);
        }
        return localPersistentStore;
      case 23: 
        localPersistentStore = (PersistentStore)this.rowStoreMapSession.get(localTableBase.getPersistenceId());
        if (localPersistentStore == null) {
          localPersistentStore = this.session.database.logger.newStore(this.session, this, localTableBase);
        }
        return localPersistentStore;
      }
    }
    catch (HsqlException localHsqlException) {}
    throw Error.runtimeError(201, "PersistentStoreCollectionSession");
  }
  
  public void clearAllTables()
  {
    clearSessionTables();
    clearTransactionTables();
    clearStatementTables();
    closeResultCache();
  }
  
  public void clearResultTables(long paramLong)
  {
    if (this.rowStoreMapSession.isEmpty()) {
      return;
    }
    Iterator localIterator = this.rowStoreMapSession.values().iterator();
    while (localIterator.hasNext())
    {
      PersistentStore localPersistentStore = (PersistentStore)localIterator.next();
      if (localPersistentStore.getTimestamp() == paramLong)
      {
        localPersistentStore.release();
        localIterator.remove();
      }
    }
  }
  
  public void clearSessionTables()
  {
    if (this.rowStoreMapSession.isEmpty()) {
      return;
    }
    Iterator localIterator = this.rowStoreMapSession.values().iterator();
    while (localIterator.hasNext())
    {
      PersistentStore localPersistentStore = (PersistentStore)localIterator.next();
      localPersistentStore.release();
    }
    this.rowStoreMapSession.clear();
  }
  
  public void clearTransactionTables()
  {
    if (this.rowStoreMapTransaction.isEmpty()) {
      return;
    }
    Iterator localIterator = this.rowStoreMapTransaction.values().iterator();
    while (localIterator.hasNext())
    {
      PersistentStore localPersistentStore = (PersistentStore)localIterator.next();
      localPersistentStore.release();
    }
    this.rowStoreMapTransaction.clear();
  }
  
  public void clearStatementTables()
  {
    if (this.rowStoreMapStatement.isEmpty()) {
      return;
    }
    Iterator localIterator = this.rowStoreMapStatement.values().iterator();
    while (localIterator.hasNext())
    {
      PersistentStore localPersistentStore = (PersistentStore)localIterator.next();
      localPersistentStore.release();
    }
    this.rowStoreMapStatement.clear();
  }
  
  public void registerIndex(Table paramTable)
  {
    PersistentStore localPersistentStore = findStore(paramTable);
    if (localPersistentStore == null) {
      return;
    }
    localPersistentStore.resetAccessorKeys(paramTable.getIndexList());
  }
  
  public PersistentStore findStore(Table paramTable)
  {
    PersistentStore localPersistentStore = null;
    switch (paramTable.persistenceScope)
    {
    case 21: 
      localPersistentStore = (PersistentStore)this.rowStoreMapStatement.get(paramTable.getPersistenceId());
      break;
    case 22: 
    case 24: 
      localPersistentStore = (PersistentStore)this.rowStoreMapTransaction.get(paramTable.getPersistenceId());
      break;
    case 23: 
      localPersistentStore = (PersistentStore)this.rowStoreMapSession.get(paramTable.getPersistenceId());
    }
    return localPersistentStore;
  }
  
  public void moveData(Table paramTable1, Table paramTable2, int paramInt1, int paramInt2)
  {
    PersistentStore localPersistentStore1 = findStore(paramTable1);
    if (localPersistentStore1 == null) {
      return;
    }
    PersistentStore localPersistentStore2 = getStore(paramTable2);
    try
    {
      localPersistentStore2.moveData(this.session, localPersistentStore1, paramInt1, paramInt2);
    }
    catch (HsqlException localHsqlException)
    {
      localPersistentStore2.release();
      setStore(paramTable2, null);
      throw localHsqlException;
    }
    setStore(paramTable1, null);
  }
  
  public void push()
  {
    if (this.rowStoreListStatement == null) {
      this.rowStoreListStatement = new HsqlDeque();
    }
    if (this.rowStoreMapStatement.isEmpty())
    {
      this.rowStoreListStatement.add(ValuePool.emptyObjectArray);
      return;
    }
    Object[] arrayOfObject = this.rowStoreMapStatement.toArray();
    this.rowStoreListStatement.add(arrayOfObject);
    this.rowStoreMapStatement.clear();
  }
  
  public void pop()
  {
    Object[] arrayOfObject = (Object[])this.rowStoreListStatement.removeLast();
    clearStatementTables();
    for (int i = 0; i < arrayOfObject.length; i++)
    {
      PersistentStore localPersistentStore = (PersistentStore)arrayOfObject[i];
      this.rowStoreMapStatement.put(localPersistentStore.getTable().getPersistenceId(), localPersistentStore);
    }
  }
  
  public DataFileCacheSession getResultCache()
  {
    if (this.resultCache == null)
    {
      String str = this.session.database.logger.getTempDirectoryPath();
      if (str == null) {
        return null;
      }
      try
      {
        this.resultCache = new DataFileCacheSession(this.session.database, str + "/session_" + Long.toString(this.session.getId()));
        this.resultCache.open(false);
      }
      catch (Throwable localThrowable)
      {
        return null;
      }
    }
    return this.resultCache;
  }
  
  public void closeResultCache()
  {
    if (this.resultCache != null)
    {
      try
      {
        this.resultCache.close(false);
        this.resultCache.deleteFile();
      }
      catch (HsqlException localHsqlException) {}
      this.resultCache = null;
    }
  }
  
  public void release()
  {
    clearAllTables();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.PersistentStoreCollectionSession
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */