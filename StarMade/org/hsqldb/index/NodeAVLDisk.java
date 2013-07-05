package org.hsqldb.index;

import java.io.IOException;
import org.hsqldb.Row;
import org.hsqldb.RowAVLDisk;
import org.hsqldb.error.Error;
import org.hsqldb.lib.LongLookup;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class NodeAVLDisk extends NodeAVL
{
  final RowAVLDisk row;
  private int iLeft = -1;
  private int iRight = -1;
  private int iParent = -1;
  private int iId;
  public static final int SIZE_IN_BYTE = 16;

  public NodeAVLDisk(RowAVLDisk paramRowAVLDisk, RowInputInterface paramRowInputInterface, int paramInt)
    throws IOException
  {
    this.row = paramRowAVLDisk;
    this.iId = paramInt;
    this.iBalance = paramRowInputInterface.readInt();
    this.iLeft = paramRowInputInterface.readInt();
    this.iRight = paramRowInputInterface.readInt();
    this.iParent = paramRowInputInterface.readInt();
    if (this.iLeft <= 0)
      this.iLeft = -1;
    if (this.iRight <= 0)
      this.iRight = -1;
    if (this.iParent <= 0)
      this.iParent = -1;
  }

  public NodeAVLDisk(RowAVLDisk paramRowAVLDisk, int paramInt)
  {
    this.row = paramRowAVLDisk;
    this.iId = paramInt;
  }

  public void delete()
  {
    this.iLeft = -1;
    this.iRight = -1;
    this.iParent = -1;
    this.nLeft = null;
    this.nRight = null;
    this.nParent = null;
    this.iBalance = 0;
    this.row.setNodesChanged();
  }

  public boolean isInMemory()
  {
    return this.row.isInMemory();
  }

  public boolean isMemory()
  {
    return false;
  }

  public long getPos()
  {
    return this.row.getPos();
  }

  public Row getRow(PersistentStore paramPersistentStore)
  {
    if (!this.row.isInMemory())
      return (RowAVLDisk)paramPersistentStore.get(this.row, false);
    this.row.updateAccessCount(paramPersistentStore.getAccessCount());
    return this.row;
  }

  public Object[] getData(PersistentStore paramPersistentStore)
  {
    return this.row.getData();
  }

  private NodeAVLDisk findNode(PersistentStore paramPersistentStore, int paramInt)
  {
    NodeAVLDisk localNodeAVLDisk = null;
    RowAVLDisk localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(paramInt, false);
    if (localRowAVLDisk != null)
      localNodeAVLDisk = (NodeAVLDisk)localRowAVLDisk.getNode(this.iId);
    return localNodeAVLDisk;
  }

  boolean isLeft(NodeAVL paramNodeAVL)
  {
    if (paramNodeAVL == null)
      return this.iLeft == -1;
    return this.iLeft == paramNodeAVL.getPos();
  }

  boolean isRight(NodeAVL paramNodeAVL)
  {
    if (paramNodeAVL == null)
      return this.iRight == -1;
    return this.iRight == paramNodeAVL.getPos();
  }

  NodeAVL getLeft(PersistentStore paramPersistentStore)
  {
    NodeAVLDisk localNodeAVLDisk = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDisk = (NodeAVLDisk)localRowAVLDisk.getNode(this.iId);
    }
    if (localNodeAVLDisk.iLeft == -1)
      return null;
    if ((localNodeAVLDisk.nLeft == null) || (!localNodeAVLDisk.nLeft.isInMemory()))
    {
      localNodeAVLDisk.nLeft = findNode(paramPersistentStore, localNodeAVLDisk.iLeft);
      localNodeAVLDisk.nLeft.nParent = localNodeAVLDisk;
    }
    return localNodeAVLDisk.nLeft;
  }

  NodeAVL getRight(PersistentStore paramPersistentStore)
  {
    NodeAVLDisk localNodeAVLDisk = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDisk = (NodeAVLDisk)localRowAVLDisk.getNode(this.iId);
    }
    if (localNodeAVLDisk.iRight == -1)
      return null;
    if ((localNodeAVLDisk.nRight == null) || (!localNodeAVLDisk.nRight.isInMemory()))
    {
      localNodeAVLDisk.nRight = findNode(paramPersistentStore, localNodeAVLDisk.iRight);
      localNodeAVLDisk.nRight.nParent = localNodeAVLDisk;
    }
    return localNodeAVLDisk.nRight;
  }

  NodeAVL getParent(PersistentStore paramPersistentStore)
  {
    NodeAVLDisk localNodeAVLDisk = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDisk = (NodeAVLDisk)localRowAVLDisk.getNode(this.iId);
    }
    if (localNodeAVLDisk.iParent == -1)
      return null;
    if ((localNodeAVLDisk.nParent == null) || (!localNodeAVLDisk.nParent.isInMemory()))
      localNodeAVLDisk.nParent = findNode(paramPersistentStore, this.iParent);
    return localNodeAVLDisk.nParent;
  }

  public int getBalance(PersistentStore paramPersistentStore)
  {
    NodeAVLDisk localNodeAVLDisk = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDisk = (NodeAVLDisk)localRowAVLDisk.getNode(this.iId);
    }
    return localNodeAVLDisk.iBalance;
  }

  boolean isRoot(PersistentStore paramPersistentStore)
  {
    NodeAVLDisk localNodeAVLDisk = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDisk = (NodeAVLDisk)localRowAVLDisk.getNode(this.iId);
    }
    return localNodeAVLDisk.iParent == -1;
  }

  boolean isFromLeft(PersistentStore paramPersistentStore)
  {
    NodeAVLDisk localNodeAVLDisk = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDisk = (NodeAVLDisk)localRowAVLDisk.getNode(this.iId);
    }
    if (localNodeAVLDisk.iParent == -1)
      return true;
    if ((localNodeAVLDisk.nParent == null) || (!localNodeAVLDisk.nParent.isInMemory()))
      localNodeAVLDisk.nParent = findNode(paramPersistentStore, this.iParent);
    return localRowAVLDisk.getPos() == ((NodeAVLDisk)localNodeAVLDisk.nParent).iLeft;
  }

  public NodeAVL child(PersistentStore paramPersistentStore, boolean paramBoolean)
  {
    return paramBoolean ? getLeft(paramPersistentStore) : getRight(paramPersistentStore);
  }

  NodeAVL setParent(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    NodeAVLDisk localNodeAVLDisk = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.keepInMemory(true))
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, true);
      localNodeAVLDisk = (NodeAVLDisk)localRowAVLDisk.getNode(this.iId);
    }
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk.keepInMemory(false);
      throw Error.runtimeError(201, "NodeAVLDisk");
    }
    localRowAVLDisk.setNodesChanged();
    localNodeAVLDisk.iParent = (paramNodeAVL == null ? -1 : (int)paramNodeAVL.getPos());
    localNodeAVLDisk.nParent = ((NodeAVLDisk)paramNodeAVL);
    localRowAVLDisk.keepInMemory(false);
    return localNodeAVLDisk;
  }

  public NodeAVL setBalance(PersistentStore paramPersistentStore, int paramInt)
  {
    NodeAVLDisk localNodeAVLDisk = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.keepInMemory(true))
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, true);
      localNodeAVLDisk = (NodeAVLDisk)localRowAVLDisk.getNode(this.iId);
    }
    if (!localRowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk");
    localRowAVLDisk.setNodesChanged();
    localNodeAVLDisk.iBalance = paramInt;
    localRowAVLDisk.keepInMemory(false);
    return localNodeAVLDisk;
  }

  NodeAVL setLeft(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    NodeAVLDisk localNodeAVLDisk = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.keepInMemory(true))
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, true);
      localNodeAVLDisk = (NodeAVLDisk)localRowAVLDisk.getNode(this.iId);
    }
    if (!localRowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk");
    localRowAVLDisk.setNodesChanged();
    localNodeAVLDisk.iLeft = (paramNodeAVL == null ? -1 : (int)paramNodeAVL.getPos());
    localNodeAVLDisk.nLeft = ((NodeAVLDisk)paramNodeAVL);
    localRowAVLDisk.keepInMemory(false);
    return localNodeAVLDisk;
  }

  NodeAVL setRight(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    NodeAVLDisk localNodeAVLDisk = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.keepInMemory(true))
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, true);
      localNodeAVLDisk = (NodeAVLDisk)localRowAVLDisk.getNode(this.iId);
    }
    if (!localRowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk");
    localRowAVLDisk.setNodesChanged();
    localNodeAVLDisk.iRight = (paramNodeAVL == null ? -1 : (int)paramNodeAVL.getPos());
    localNodeAVLDisk.nRight = ((NodeAVLDisk)paramNodeAVL);
    localRowAVLDisk.keepInMemory(false);
    return localNodeAVLDisk;
  }

  public NodeAVL set(PersistentStore paramPersistentStore, boolean paramBoolean, NodeAVL paramNodeAVL)
  {
    NodeAVL localNodeAVL;
    if (paramBoolean)
      localNodeAVL = setLeft(paramPersistentStore, paramNodeAVL);
    else
      localNodeAVL = setRight(paramPersistentStore, paramNodeAVL);
    if (paramNodeAVL != null)
      paramNodeAVL.setParent(paramPersistentStore, this);
    return localNodeAVL;
  }

  public void replace(PersistentStore paramPersistentStore, Index paramIndex, NodeAVL paramNodeAVL)
  {
    if (this.iParent == -1)
    {
      if (paramNodeAVL != null)
        paramNodeAVL = paramNodeAVL.setParent(paramPersistentStore, null);
      paramPersistentStore.setAccessor(paramIndex, paramNodeAVL);
    }
    else
    {
      boolean bool = isFromLeft(paramPersistentStore);
      getParent(paramPersistentStore).set(paramPersistentStore, bool, paramNodeAVL);
    }
  }

  boolean equals(NodeAVL paramNodeAVL)
  {
    if ((paramNodeAVL instanceof NodeAVLDisk))
      return (this == paramNodeAVL) || (getPos() == ((NodeAVLDisk)paramNodeAVL).getPos());
    return false;
  }

  public int getRealSize(RowOutputInterface paramRowOutputInterface)
  {
    return 16;
  }

  public void setInMemory(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      if (this.nLeft != null)
        this.nLeft.nParent = null;
      if (this.nRight != null)
        this.nRight.nParent = null;
      if (this.nParent != null)
        if (this.row.getPos() == ((NodeAVLDisk)this.nParent).iLeft)
          this.nParent.nLeft = null;
        else
          this.nParent.nRight = null;
      this.nLeft = (this.nRight = this.nParent = null);
    }
  }

  public void write(RowOutputInterface paramRowOutputInterface)
  {
    paramRowOutputInterface.writeInt(this.iBalance);
    paramRowOutputInterface.writeInt(this.iLeft == -1 ? 0 : this.iLeft);
    paramRowOutputInterface.writeInt(this.iRight == -1 ? 0 : this.iRight);
    paramRowOutputInterface.writeInt(this.iParent == -1 ? 0 : this.iParent);
  }

  public void write(RowOutputInterface paramRowOutputInterface, LongLookup paramLongLookup)
  {
    paramRowOutputInterface.writeInt(this.iBalance);
    paramRowOutputInterface.writeInt(getTranslatePointer(this.iLeft, paramLongLookup));
    paramRowOutputInterface.writeInt(getTranslatePointer(this.iRight, paramLongLookup));
    paramRowOutputInterface.writeInt(getTranslatePointer(this.iParent, paramLongLookup));
  }

  private static int getTranslatePointer(int paramInt, LongLookup paramLongLookup)
  {
    int i = 0;
    if (paramInt != -1)
      if (paramLongLookup == null)
        i = paramInt;
      else
        i = (int)paramLongLookup.lookup(paramInt);
    return i;
  }

  public void restore()
  {
  }

  public void destroy()
  {
  }

  public void updateAccessCount(int paramInt)
  {
  }

  public int getAccessCount()
  {
    return 0;
  }

  public void setStorageSize(int paramInt)
  {
  }

  public int getStorageSize()
  {
    return 0;
  }

  public void setPos(long paramLong)
  {
  }

  public boolean isNew()
  {
    return false;
  }

  public boolean hasChanged()
  {
    return false;
  }

  public boolean isKeepInMemory()
  {
    return false;
  }

  public boolean keepInMemory(boolean paramBoolean)
  {
    return false;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.index.NodeAVLDisk
 * JD-Core Version:    0.6.2
 */