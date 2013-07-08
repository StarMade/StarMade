package org.hsqldb.persist;

import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.index.Index;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.navigator.RowIterator;

public class RowStoreAVLHybridExtended
  extends RowStoreAVLHybrid
{
  public RowStoreAVLHybridExtended(Session paramSession, PersistentStoreCollection paramPersistentStoreCollection, TableBase paramTableBase, boolean paramBoolean)
  {
    super(paramSession, paramPersistentStoreCollection, paramTableBase, paramBoolean);
  }
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean)
  {
    if (this.indexList != this.table.getIndexList()) {
      resetAccessorKeys(this.table.getIndexList());
    }
    return super.getNewCachedObject(paramSession, paramObject, paramBoolean);
  }
  
  public void indexRow(Session paramSession, Row paramRow)
  {
    NodeAVL localNodeAVL = ((RowAVL)paramRow).getNode(0);
    int i = 0;
    while (localNodeAVL != null)
    {
      i++;
      localNodeAVL = localNodeAVL.nNext;
    }
    if (i != this.indexList.length)
    {
      resetAccessorKeys(this.table.getIndexList());
      ((RowAVL)paramRow).setNewNodes(this);
    }
    super.indexRow(paramSession, paramRow);
  }
  
  public void delete(Session paramSession, Row paramRow)
  {
    paramRow = ((Table)this.table).getDeleteRowFromLog(paramSession, paramRow.getData());
    super.delete(paramSession, paramRow);
  }
  
  public CachedObject getAccessor(Index paramIndex)
  {
    int i = paramIndex.getPosition();
    if ((i >= this.accessorList.length) || (this.indexList[i] != paramIndex))
    {
      resetAccessorKeys(this.table.getIndexList());
      return getAccessor(paramIndex);
    }
    return this.accessorList[i];
  }
  
  public synchronized void resetAccessorKeys(Index[] paramArrayOfIndex)
  {
    if ((this.indexList.length == 0) || (this.accessorList[0] == null))
    {
      this.indexList = paramArrayOfIndex;
      this.accessorList = new CachedObject[this.indexList.length];
      return;
    }
    if (this.isCached)
    {
      resetAccessorKeysForCached();
      return;
    }
    super.resetAccessorKeys(paramArrayOfIndex);
  }
  
  private void resetAccessorKeysForCached()
  {
    RowStoreAVLHybridExtended localRowStoreAVLHybridExtended = new RowStoreAVLHybridExtended(this.session, this.manager, this.table, true);
    localRowStoreAVLHybridExtended.changeToDiskTable(this.session);
    RowIterator localRowIterator = this.table.rowIterator(this);
    while (localRowIterator.hasNext())
    {
      Row localRow1 = localRowIterator.getNextRow();
      Row localRow2 = (Row)localRowStoreAVLHybridExtended.getNewCachedObject(this.session, localRow1.getData(), false);
      localRowStoreAVLHybridExtended.indexRow(this.session, localRow2);
    }
    this.indexList = localRowStoreAVLHybridExtended.indexList;
    this.accessorList = localRowStoreAVLHybridExtended.accessorList;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.RowStoreAVLHybridExtended
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */