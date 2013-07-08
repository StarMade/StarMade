package org.hsqldb.persist;

import org.hsqldb.TableBase;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongKeyHashMap;

public class PersistentStoreCollectionDatabase
  implements PersistentStoreCollection
{
  private long persistentStoreIdSequence;
  private final LongKeyHashMap rowStoreMap = new LongKeyHashMap();
  
  public void setStore(Object paramObject, PersistentStore paramPersistentStore)
  {
    long l = ((TableBase)paramObject).getPersistenceId();
    if (paramPersistentStore == null) {
      this.rowStoreMap.remove(l);
    } else {
      this.rowStoreMap.put(l, paramPersistentStore);
    }
  }
  
  public PersistentStore getStore(Object paramObject)
  {
    long l = ((TableBase)paramObject).getPersistenceId();
    PersistentStore localPersistentStore = (PersistentStore)this.rowStoreMap.get(l);
    return localPersistentStore;
  }
  
  public void releaseStore(TableBase paramTableBase)
  {
    PersistentStore localPersistentStore = (PersistentStore)this.rowStoreMap.get(paramTableBase.getPersistenceId());
    if (localPersistentStore != null)
    {
      localPersistentStore.release();
      this.rowStoreMap.remove(paramTableBase.getPersistenceId());
    }
  }
  
  public long getNextId()
  {
    return this.persistentStoreIdSequence++;
  }
  
  public void release()
  {
    if (this.rowStoreMap.isEmpty()) {
      return;
    }
    Iterator localIterator = this.rowStoreMap.values().iterator();
    while (localIterator.hasNext())
    {
      PersistentStore localPersistentStore = (PersistentStore)localIterator.next();
      localPersistentStore.release();
    }
    this.rowStoreMap.clear();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.PersistentStoreCollectionDatabase
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */