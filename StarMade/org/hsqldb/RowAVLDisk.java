package org.hsqldb;

import java.io.IOException;
import org.hsqldb.error.Error;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.index.NodeAVLDisk;
import org.hsqldb.lib.LongLookup;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class RowAVLDisk
  extends RowAVL
{
  public static final int NO_POS = -1;
  int storageSize;
  int keepCount;
  volatile boolean isInMemory;
  int accessCount;
  boolean isNew;
  boolean hasDataChanged;
  private boolean hasNodesChanged;
  
  public RowAVLDisk(TableBase paramTableBase, Object[] paramArrayOfObject, PersistentStore paramPersistentStore)
  {
    super(paramTableBase, paramArrayOfObject);
    setNewNodes(paramPersistentStore);
    this.hasDataChanged = (this.hasNodesChanged = this.isNew = 1);
  }
  
  public RowAVLDisk(TableBase paramTableBase, RowInputInterface paramRowInputInterface)
    throws IOException
  {
    super(paramTableBase, (Object[])null);
    this.position = paramRowInputInterface.getPos();
    this.storageSize = paramRowInputInterface.getSize();
    int i = paramTableBase.getIndexCount();
    this.nPrimaryNode = new NodeAVLDisk(this, paramRowInputInterface, 0);
    NodeAVL localNodeAVL = this.nPrimaryNode;
    for (int j = 1; j < i; j++)
    {
      localNodeAVL.nNext = new NodeAVLDisk(this, paramRowInputInterface, j);
      localNodeAVL = localNodeAVL.nNext;
    }
    this.rowData = paramRowInputInterface.readData(this.table.getColumnTypes());
  }
  
  RowAVLDisk(TableBase paramTableBase)
  {
    super(paramTableBase, (Object[])null);
  }
  
  public NodeAVL insertNode(int paramInt)
  {
    return null;
  }
  
  private void readRowInfo(RowInputInterface paramRowInputInterface) {}
  
  public synchronized void setNodesChanged()
  {
    this.hasNodesChanged = true;
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
  
  public boolean isMemory()
  {
    return false;
  }
  
  public void setPos(long paramLong)
  {
    this.position = paramLong;
  }
  
  public synchronized void setChanged(boolean paramBoolean)
  {
    this.hasDataChanged = paramBoolean;
  }
  
  public boolean isNew()
  {
    return this.isNew;
  }
  
  public synchronized boolean hasChanged()
  {
    return (this.hasNodesChanged) || (this.hasDataChanged);
  }
  
  public TableBase getTable()
  {
    return this.table;
  }
  
  public void setStorageSize(int paramInt)
  {
    this.storageSize = paramInt;
  }
  
  public synchronized boolean isKeepInMemory()
  {
    return this.keepCount > 0;
  }
  
  public void delete(PersistentStore paramPersistentStore)
  {
    RowAVLDisk localRowAVLDisk = this;
    if (!localRowAVLDisk.keepInMemory(true)) {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(localRowAVLDisk, true);
    }
    super.delete(paramPersistentStore);
    localRowAVLDisk.keepInMemory(false);
  }
  
  public void destroy()
  {
    NodeAVL localNodeAVL1 = this.nPrimaryNode;
    while (localNodeAVL1 != null)
    {
      NodeAVL localNodeAVL2 = localNodeAVL1;
      localNodeAVL1 = localNodeAVL1.nNext;
      localNodeAVL2.nNext = null;
    }
    this.nPrimaryNode = null;
  }
  
  public synchronized boolean keepInMemory(boolean paramBoolean)
  {
    if (!this.isInMemory) {
      return false;
    }
    if (paramBoolean)
    {
      this.keepCount += 1;
    }
    else
    {
      this.keepCount -= 1;
      if (this.keepCount < 0) {
        throw Error.runtimeError(201, "RowAVLDisk - keep count");
      }
    }
    return true;
  }
  
  public synchronized boolean isInMemory()
  {
    return this.isInMemory;
  }
  
  public synchronized void setInMemory(boolean paramBoolean)
  {
    this.isInMemory = paramBoolean;
    if (paramBoolean) {
      return;
    }
    for (NodeAVL localNodeAVL = this.nPrimaryNode; localNodeAVL != null; localNodeAVL = localNodeAVL.nNext) {
      localNodeAVL.setInMemory(paramBoolean);
    }
  }
  
  public void setNewNodes(PersistentStore paramPersistentStore)
  {
    int i = paramPersistentStore.getAccessorKeys().length;
    this.nPrimaryNode = new NodeAVLDisk(this, 0);
    NodeAVL localNodeAVL = this.nPrimaryNode;
    for (int j = 1; j < i; j++)
    {
      localNodeAVL.nNext = new NodeAVLDisk(this, j);
      localNodeAVL = localNodeAVL.nNext;
    }
  }
  
  public int getRealSize(RowOutputInterface paramRowOutputInterface)
  {
    return paramRowOutputInterface.getSize(this);
  }
  
  public void write(RowOutputInterface paramRowOutputInterface)
  {
    writeNodes(paramRowOutputInterface);
    if (this.hasDataChanged)
    {
      paramRowOutputInterface.writeData(this, this.table.colTypes);
      paramRowOutputInterface.writeEnd();
      this.hasDataChanged = false;
      this.isNew = false;
    }
  }
  
  public void write(RowOutputInterface paramRowOutputInterface, LongLookup paramLongLookup)
  {
    paramRowOutputInterface.writeSize(this.storageSize);
    for (NodeAVL localNodeAVL = this.nPrimaryNode; localNodeAVL != null; localNodeAVL = localNodeAVL.nNext) {
      localNodeAVL.write(paramRowOutputInterface, paramLongLookup);
    }
    paramRowOutputInterface.writeData(this, this.table.colTypes);
    paramRowOutputInterface.writeEnd();
  }
  
  void writeNodes(RowOutputInterface paramRowOutputInterface)
  {
    paramRowOutputInterface.writeSize(this.storageSize);
    for (NodeAVL localNodeAVL = this.nPrimaryNode; localNodeAVL != null; localNodeAVL = localNodeAVL.nNext) {
      localNodeAVL.write(paramRowOutputInterface);
    }
    this.hasNodesChanged = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.RowAVLDisk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */