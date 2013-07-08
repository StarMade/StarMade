package org.hsqldb;

import java.io.IOException;
import org.hsqldb.index.NodeAVL;
import org.hsqldb.index.NodeAVLDiskLarge;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rowio.RowInputInterface;

public class RowAVLDiskLarge
  extends RowAVLDisk
{
  public RowAVLDiskLarge(TableBase paramTableBase, Object[] paramArrayOfObject, PersistentStore paramPersistentStore)
  {
    super(paramTableBase, paramArrayOfObject, paramPersistentStore);
  }
  
  public RowAVLDiskLarge(TableBase paramTableBase, RowInputInterface paramRowInputInterface)
    throws IOException
  {
    super(paramTableBase);
    this.position = paramRowInputInterface.getPos();
    this.storageSize = paramRowInputInterface.getSize();
    int i = paramTableBase.getIndexCount();
    this.nPrimaryNode = new NodeAVLDiskLarge(this, paramRowInputInterface, 0);
    NodeAVL localNodeAVL = this.nPrimaryNode;
    for (int j = 1; j < i; j++)
    {
      localNodeAVL.nNext = new NodeAVLDiskLarge(this, paramRowInputInterface, j);
      localNodeAVL = localNodeAVL.nNext;
    }
    this.rowData = paramRowInputInterface.readData(this.table.getColumnTypes());
  }
  
  public void setNewNodes(PersistentStore paramPersistentStore)
  {
    int i = paramPersistentStore.getAccessorKeys().length;
    this.nPrimaryNode = new NodeAVLDiskLarge(this, 0);
    NodeAVL localNodeAVL = this.nPrimaryNode;
    for (int j = 1; j < i; j++)
    {
      localNodeAVL.nNext = new NodeAVLDiskLarge(this, j);
      localNodeAVL = localNodeAVL.nNext;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.RowAVLDiskLarge
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */