package org.hsqldb;

import org.hsqldb.index.NodeAVL;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.persist.PersistentStore;

public class RowAVL
  extends Row
{
  public NodeAVL nPrimaryNode;
  
  protected RowAVL(TableBase paramTableBase, Object[] paramArrayOfObject)
  {
    super(paramTableBase, paramArrayOfObject);
  }
  
  public RowAVL(TableBase paramTableBase, Object[] paramArrayOfObject, int paramInt, PersistentStore paramPersistentStore)
  {
    super(paramTableBase, paramArrayOfObject);
    this.position = paramInt;
    setNewNodes(paramPersistentStore);
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
  
  public NodeAVL getNode(int paramInt)
  {
    for (NodeAVL localNodeAVL = this.nPrimaryNode; paramInt-- > 0; localNodeAVL = localNodeAVL.nNext) {}
    return localNodeAVL;
  }
  
  NodeAVL getNextNode(NodeAVL paramNodeAVL)
  {
    if (paramNodeAVL == null) {
      paramNodeAVL = this.nPrimaryNode;
    } else {
      paramNodeAVL = paramNodeAVL.nNext;
    }
    return paramNodeAVL;
  }
  
  public NodeAVL insertNode(int paramInt)
  {
    NodeAVL localNodeAVL1 = getNode(paramInt - 1);
    NodeAVL localNodeAVL2 = new NodeAVL(this);
    localNodeAVL2.nNext = localNodeAVL1.nNext;
    localNodeAVL1.nNext = localNodeAVL2;
    return localNodeAVL2;
  }
  
  public void clearNonPrimaryNodes()
  {
    for (NodeAVL localNodeAVL = this.nPrimaryNode.nNext; localNodeAVL != null; localNodeAVL = localNodeAVL.nNext) {
      localNodeAVL.delete();
    }
  }
  
  public void delete(PersistentStore paramPersistentStore)
  {
    for (NodeAVL localNodeAVL = this.nPrimaryNode; localNodeAVL != null; localNodeAVL = localNodeAVL.nNext) {
      localNodeAVL.delete();
    }
  }
  
  public void restore() {}
  
  public void destroy()
  {
    JavaSystem.memoryRecords += 1;
    clearNonPrimaryNodes();
    NodeAVL localNodeAVL1 = this.nPrimaryNode;
    while (localNodeAVL1 != null)
    {
      NodeAVL localNodeAVL2 = localNodeAVL1;
      localNodeAVL1 = localNodeAVL1.nNext;
      localNodeAVL2.nNext = null;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.RowAVL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */