package org.hsqldb;

import java.io.IOException;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class RowAVLDiskData
  extends RowAVL
{
  PersistentStore store;
  int accessCount;
  boolean hasDataChanged;
  int storageSize;
  
  public RowAVLDiskData(PersistentStore paramPersistentStore, TableBase paramTableBase, Object[] paramArrayOfObject)
  {
    super(paramTableBase, paramArrayOfObject);
    setNewNodes(paramPersistentStore);
    this.store = paramPersistentStore;
    this.hasDataChanged = true;
  }
  
  public RowAVLDiskData(PersistentStore paramPersistentStore, TableBase paramTableBase, RowInputInterface paramRowInputInterface)
    throws IOException
  {
    super(paramTableBase, (Object[])null);
    setNewNodes(paramPersistentStore);
    this.position = paramRowInputInterface.getPos();
    this.storageSize = paramRowInputInterface.getSize();
    this.rowData = paramRowInputInterface.readData(this.table.getColumnTypes());
    this.hasDataChanged = false;
    this.store = paramPersistentStore;
  }
  
  public void getRowData(TableBase paramTableBase, RowInputInterface paramRowInputInterface)
    throws IOException
  {
    this.rowData = paramRowInputInterface.readData(paramTableBase.getColumnTypes());
  }
  
  public void setData(Object[] paramArrayOfObject)
  {
    this.rowData = paramArrayOfObject;
  }
  
  public Object[] getData()
  {
    Object[] arrayOfObject = this.rowData;
    if (arrayOfObject == null)
    {
      this.store.writeLock();
      try
      {
        this.store.get(this, false);
        arrayOfObject = this.rowData;
        if (arrayOfObject == null)
        {
          this.store.get(this, false);
          arrayOfObject = this.rowData;
        }
      }
      finally
      {
        this.store.writeUnlock();
      }
    }
    else
    {
      this.accessCount += 1;
    }
    return arrayOfObject;
  }
  
  public void setNewNodes(PersistentStore paramPersistentStore)
  {
    int i = paramPersistentStore.getAccessorKeys().length;
    this.nPrimaryNode = new NodeAVL(this);
    NodeAVL localNodeAVL = this.nPrimaryNode;
    for (int j = 1; j < i; j++)
    {
      localNodeAVL.nNext = new NodeAVL(this);
      localNodeAVL = localNodeAVL.nNext;
    }
  }
  
  public NodeAVL insertNode(int paramInt)
  {
    NodeAVL localNodeAVL1 = getNode(paramInt - 1);
    NodeAVL localNodeAVL2 = new NodeAVL(this);
    localNodeAVL2.nNext = localNodeAVL1.nNext;
    localNodeAVL1.nNext = localNodeAVL2;
    return localNodeAVL2;
  }
  
  void setPrimaryNode(NodeAVL paramNodeAVL)
  {
    this.nPrimaryNode = paramNodeAVL;
  }
  
  public int getRealSize(RowOutputInterface paramRowOutputInterface)
  {
    return paramRowOutputInterface.getSize(this);
  }
  
  public void write(RowOutputInterface paramRowOutputInterface)
  {
    paramRowOutputInterface.writeSize(this.storageSize);
    paramRowOutputInterface.writeData(this, this.table.colTypes);
    paramRowOutputInterface.writeEnd();
    this.hasDataChanged = false;
  }
  
  public synchronized void setChanged(boolean paramBoolean)
  {
    this.hasDataChanged = paramBoolean;
  }
  
  public boolean isNew()
  {
    return false;
  }
  
  public boolean hasChanged()
  {
    return this.hasDataChanged;
  }
  
  public void updateAccessCount(int paramInt)
  {
    this.accessCount = paramInt;
  }
  
  public int getAccessCount()
  {
    return this.accessCount;
  }
  
  public int getStorageSize()
  {
    return this.storageSize;
  }
  
  public void setStorageSize(int paramInt)
  {
    this.storageSize = paramInt;
  }
  
  public void setPos(long paramLong)
  {
    this.position = paramLong;
  }
  
  public boolean isMemory()
  {
    return true;
  }
  
  public boolean isInMemory()
  {
    return this.rowData != null;
  }
  
  public boolean isKeepInMemory()
  {
    return false;
  }
  
  public boolean keepInMemory(boolean paramBoolean)
  {
    return true;
  }
  
  public void setInMemory(boolean paramBoolean)
  {
    if (!paramBoolean) {
      this.rowData = null;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.RowAVLDiskData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */