package org.hsqldb.index;

import java.io.IOException;
import org.hsqldb.Row;
import org.hsqldb.RowAVLDisk;
import org.hsqldb.error.Error;
import org.hsqldb.lib.LongLookup;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class NodeAVLDiskLarge extends NodeAVL
{
  final RowAVLDisk row;
  private long iLeft = -1L;
  private long iRight = -1L;
  private long iParent = -1L;
  private int iId;
  public static final int SIZE_IN_BYTE = 16;

  public NodeAVLDiskLarge(RowAVLDisk paramRowAVLDisk, RowInputInterface paramRowInputInterface, int paramInt)
    throws IOException
  {
    this.row = paramRowAVLDisk;
    this.iId = paramInt;
    int i = paramRowInputInterface.readInt();
    this.iBalance = ((byte)i);
    this.iLeft = (paramRowInputInterface.readInt() & 0xFFFFFFFF);
    this.iRight = (paramRowInputInterface.readInt() & 0xFFFFFFFF);
    this.iParent = (paramRowInputInterface.readInt() & 0xFFFFFFFF);
    if (i > 255)
    {
      this.iParent |= i << 8 & 0x0;
      this.iLeft |= i << 16 & 0x0;
      this.iRight |= i << 24 & 0x0;
    }
    if (this.iLeft == 0L)
      this.iLeft = -1L;
    if (this.iRight == 0L)
      this.iRight = -1L;
    if (this.iParent == 0L)
      this.iParent = -1L;
  }

  public NodeAVLDiskLarge(RowAVLDisk paramRowAVLDisk, int paramInt)
  {
    this.row = paramRowAVLDisk;
    this.iId = paramInt;
  }

  public void delete()
  {
    this.iLeft = -1L;
    this.iRight = -1L;
    this.iParent = -1L;
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

  private NodeAVLDiskLarge findNode(PersistentStore paramPersistentStore, long paramLong)
  {
    NodeAVLDiskLarge localNodeAVLDiskLarge = null;
    RowAVLDisk localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(paramLong, false);
    if (localRowAVLDisk != null)
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    return localNodeAVLDiskLarge;
  }

  boolean isLeft(NodeAVL paramNodeAVL)
  {
    if (paramNodeAVL == null)
      return this.iLeft == -1L;
    return this.iLeft == paramNodeAVL.getPos();
  }

  boolean isRight(NodeAVL paramNodeAVL)
  {
    if (paramNodeAVL == null)
      return this.iRight == -1L;
    return this.iRight == paramNodeAVL.getPos();
  }

  NodeAVL getLeft(PersistentStore paramPersistentStore)
  {
    NodeAVLDiskLarge localNodeAVLDiskLarge = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    }
    if (localNodeAVLDiskLarge.iLeft == -1L)
      return null;
    if ((localNodeAVLDiskLarge.nLeft == null) || (!localNodeAVLDiskLarge.nLeft.isInMemory()))
    {
      localNodeAVLDiskLarge.nLeft = findNode(paramPersistentStore, localNodeAVLDiskLarge.iLeft);
      localNodeAVLDiskLarge.nLeft.nParent = localNodeAVLDiskLarge;
    }
    return localNodeAVLDiskLarge.nLeft;
  }

  NodeAVL getRight(PersistentStore paramPersistentStore)
  {
    NodeAVLDiskLarge localNodeAVLDiskLarge = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    }
    if (localNodeAVLDiskLarge.iRight == -1L)
      return null;
    if ((localNodeAVLDiskLarge.nRight == null) || (!localNodeAVLDiskLarge.nRight.isInMemory()))
    {
      localNodeAVLDiskLarge.nRight = findNode(paramPersistentStore, localNodeAVLDiskLarge.iRight);
      localNodeAVLDiskLarge.nRight.nParent = localNodeAVLDiskLarge;
    }
    return localNodeAVLDiskLarge.nRight;
  }

  NodeAVL getParent(PersistentStore paramPersistentStore)
  {
    NodeAVLDiskLarge localNodeAVLDiskLarge = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    }
    if (localNodeAVLDiskLarge.iParent == -1L)
      return null;
    if ((localNodeAVLDiskLarge.nParent == null) || (!localNodeAVLDiskLarge.nParent.isInMemory()))
      localNodeAVLDiskLarge.nParent = findNode(paramPersistentStore, this.iParent);
    return localNodeAVLDiskLarge.nParent;
  }

  public int getBalance(PersistentStore paramPersistentStore)
  {
    NodeAVLDiskLarge localNodeAVLDiskLarge = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    }
    return localNodeAVLDiskLarge.iBalance;
  }

  boolean isRoot(PersistentStore paramPersistentStore)
  {
    NodeAVLDiskLarge localNodeAVLDiskLarge = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    }
    return localNodeAVLDiskLarge.iParent == -1L;
  }

  boolean isFromLeft(PersistentStore paramPersistentStore)
  {
    NodeAVLDiskLarge localNodeAVLDiskLarge = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, false);
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    }
    if (localNodeAVLDiskLarge.iParent == -1L)
      return true;
    if ((localNodeAVLDiskLarge.nParent == null) || (!localNodeAVLDiskLarge.nParent.isInMemory()))
      localNodeAVLDiskLarge.nParent = findNode(paramPersistentStore, this.iParent);
    return localRowAVLDisk.getPos() == ((NodeAVLDiskLarge)localNodeAVLDiskLarge.nParent).iLeft;
  }

  public NodeAVL child(PersistentStore paramPersistentStore, boolean paramBoolean)
  {
    return paramBoolean ? getLeft(paramPersistentStore) : getRight(paramPersistentStore);
  }

  NodeAVL setParent(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    NodeAVLDiskLarge localNodeAVLDiskLarge = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.keepInMemory(true))
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, true);
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    }
    if (!localRowAVLDisk.isInMemory())
    {
      localRowAVLDisk.keepInMemory(false);
      throw Error.runtimeError(201, "NodeAVLDisk");
    }
    localRowAVLDisk.setNodesChanged();
    localNodeAVLDiskLarge.iParent = (paramNodeAVL == null ? -1L : paramNodeAVL.getPos());
    if ((paramNodeAVL != null) && (!paramNodeAVL.isInMemory()))
      paramNodeAVL = findNode(paramPersistentStore, paramNodeAVL.getPos());
    localNodeAVLDiskLarge.nParent = ((NodeAVLDiskLarge)paramNodeAVL);
    localRowAVLDisk.keepInMemory(false);
    return localNodeAVLDiskLarge;
  }

  public NodeAVL setBalance(PersistentStore paramPersistentStore, int paramInt)
  {
    NodeAVLDiskLarge localNodeAVLDiskLarge = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.keepInMemory(true))
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, true);
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    }
    if (!localRowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk");
    localRowAVLDisk.setNodesChanged();
    localNodeAVLDiskLarge.iBalance = paramInt;
    localRowAVLDisk.keepInMemory(false);
    return localNodeAVLDiskLarge;
  }

  NodeAVL setLeft(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    NodeAVLDiskLarge localNodeAVLDiskLarge = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.keepInMemory(true))
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, true);
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    }
    if (!localRowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk");
    localRowAVLDisk.setNodesChanged();
    localNodeAVLDiskLarge.iLeft = (paramNodeAVL == null ? -1L : paramNodeAVL.getPos());
    if ((paramNodeAVL != null) && (!paramNodeAVL.isInMemory()))
      paramNodeAVL = findNode(paramPersistentStore, paramNodeAVL.getPos());
    localNodeAVLDiskLarge.nLeft = ((NodeAVLDiskLarge)paramNodeAVL);
    localRowAVLDisk.keepInMemory(false);
    return localNodeAVLDiskLarge;
  }

  NodeAVL setRight(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    NodeAVLDiskLarge localNodeAVLDiskLarge = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.keepInMemory(true))
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, true);
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    }
    if (!localRowAVLDisk.isInMemory())
      throw Error.runtimeError(201, "NodeAVLDisk");
    localRowAVLDisk.setNodesChanged();
    localNodeAVLDiskLarge.iRight = (paramNodeAVL == null ? -1L : paramNodeAVL.getPos());
    if ((paramNodeAVL != null) && (!paramNodeAVL.isInMemory()))
      paramNodeAVL = findNode(paramPersistentStore, paramNodeAVL.getPos());
    localNodeAVLDiskLarge.nRight = ((NodeAVLDiskLarge)paramNodeAVL);
    localRowAVLDisk.keepInMemory(false);
    return localNodeAVLDiskLarge;
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
    NodeAVLDiskLarge localNodeAVLDiskLarge = this;
    RowAVLDisk localRowAVLDisk = this.row;
    if (!localRowAVLDisk.keepInMemory(true))
    {
      localRowAVLDisk = (RowAVLDisk)paramPersistentStore.get(this.row, true);
      localNodeAVLDiskLarge = (NodeAVLDiskLarge)localRowAVLDisk.getNode(this.iId);
    }
    if (localNodeAVLDiskLarge.iParent == -1L)
    {
      if (paramNodeAVL != null)
        paramNodeAVL = paramNodeAVL.setParent(paramPersistentStore, null);
      paramPersistentStore.setAccessor(paramIndex, paramNodeAVL);
    }
    else
    {
      boolean bool = localNodeAVLDiskLarge.isFromLeft(paramPersistentStore);
      localNodeAVLDiskLarge.getParent(paramPersistentStore).set(paramPersistentStore, bool, paramNodeAVL);
    }
    localRowAVLDisk.keepInMemory(false);
  }

  boolean equals(NodeAVL paramNodeAVL)
  {
    if ((paramNodeAVL instanceof NodeAVLDiskLarge))
      return (this == paramNodeAVL) || (this.row.getPos() == ((NodeAVLDiskLarge)paramNodeAVL).getPos());
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
        if (this.row.getPos() == ((NodeAVLDiskLarge)this.nParent).iLeft)
          this.nParent.nLeft = null;
        else
          this.nParent.nRight = null;
      this.nLeft = (this.nRight = this.nParent = null);
    }
  }

  public void write(RowOutputInterface paramRowOutputInterface)
  {
    write(paramRowOutputInterface, null);
  }

  public void write(RowOutputInterface paramRowOutputInterface, LongLookup paramLongLookup)
  {
    long l1 = getTranslatePointer(this.iLeft, paramLongLookup);
    long l2 = getTranslatePointer(this.iRight, paramLongLookup);
    long l3 = getTranslatePointer(this.iParent, paramLongLookup);
    int i = 0;
    i |= (int)((l3 & 0x0) >> 8);
    i |= (int)((l1 & 0x0) >> 16);
    i |= (int)((l2 & 0x0) >> 24);
    if (i == 0)
      i = this.iBalance;
    else
      i |= this.iBalance & 0xFF;
    paramRowOutputInterface.writeInt(i);
    paramRowOutputInterface.writeInt((int)l1);
    paramRowOutputInterface.writeInt((int)l2);
    paramRowOutputInterface.writeInt((int)l3);
  }

  private static long getTranslatePointer(long paramLong, LongLookup paramLongLookup)
  {
    long l = 0L;
    if (paramLong != -1L)
      if (paramLongLookup == null)
        l = paramLong;
      else
        l = paramLongLookup.lookup(paramLong);
    return l;
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
 * Qualified Name:     org.hsqldb.index.NodeAVLDiskLarge
 * JD-Core Version:    0.6.2
 */