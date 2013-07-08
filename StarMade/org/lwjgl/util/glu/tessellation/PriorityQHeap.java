package org.lwjgl.util.glu.tessellation;

class PriorityQHeap
  extends PriorityQ
{
  PriorityQ.PQnode[] nodes = new PriorityQ.PQnode[33];
  PriorityQ.PQhandleElem[] handles;
  int size = 0;
  int max = 32;
  int freeList;
  boolean initialized;
  PriorityQ.Leq leq;
  
  PriorityQHeap(PriorityQ.Leq leq)
  {
    for (int local_i = 0; local_i < this.nodes.length; local_i++) {
      this.nodes[local_i] = new PriorityQ.PQnode();
    }
    this.handles = new PriorityQ.PQhandleElem[33];
    for (int local_i = 0; local_i < this.handles.length; local_i++) {
      this.handles[local_i] = new PriorityQ.PQhandleElem();
    }
    this.initialized = false;
    this.freeList = 0;
    this.leq = leq;
    this.nodes[1].handle = 1;
    this.handles[1].key = null;
  }
  
  void pqDeletePriorityQ()
  {
    this.handles = null;
    this.nodes = null;
  }
  
  void FloatDown(int curr)
  {
    PriorityQ.PQnode[] local_n = this.nodes;
    PriorityQ.PQhandleElem[] local_h = this.handles;
    int hCurr = local_n[curr].handle;
    for (;;)
    {
      int child = curr << 1;
      if ((child < this.size) && (LEQ(this.leq, local_h[local_n[(child + 1)].handle].key, local_h[local_n[child].handle].key))) {
        child++;
      }
      assert (child <= this.max);
      int hChild = local_n[child].handle;
      if ((child > this.size) || (LEQ(this.leq, local_h[hCurr].key, local_h[hChild].key)))
      {
        local_n[curr].handle = hCurr;
        local_h[hCurr].node = curr;
        break;
      }
      local_n[curr].handle = hChild;
      local_h[hChild].node = curr;
      curr = child;
    }
  }
  
  void FloatUp(int curr)
  {
    PriorityQ.PQnode[] local_n = this.nodes;
    PriorityQ.PQhandleElem[] local_h = this.handles;
    int hCurr = local_n[curr].handle;
    for (;;)
    {
      int parent = curr >> 1;
      int hParent = local_n[parent].handle;
      if ((parent == 0) || (LEQ(this.leq, local_h[hParent].key, local_h[hCurr].key)))
      {
        local_n[curr].handle = hCurr;
        local_h[hCurr].node = curr;
        break;
      }
      local_n[curr].handle = hParent;
      local_h[hParent].node = curr;
      curr = parent;
    }
  }
  
  boolean pqInit()
  {
    for (int local_i = this.size; local_i >= 1; local_i--) {
      FloatDown(local_i);
    }
    this.initialized = true;
    return true;
  }
  
  int pqInsert(Object keyNew)
  {
    int curr = ++this.size;
    if (curr * 2 > this.max)
    {
      PriorityQ.PQnode[] saveNodes = this.nodes;
      PriorityQ.PQhandleElem[] saveHandles = this.handles;
      this.max <<= 1;
      PriorityQ.PQnode[] pqNodes = new PriorityQ.PQnode[this.max + 1];
      System.arraycopy(this.nodes, 0, pqNodes, 0, this.nodes.length);
      for (int local_i = this.nodes.length; local_i < pqNodes.length; local_i++) {
        pqNodes[local_i] = new PriorityQ.PQnode();
      }
      this.nodes = pqNodes;
      if (this.nodes == null)
      {
        this.nodes = saveNodes;
        return 2147483647;
      }
      PriorityQ.PQhandleElem[] local_i = new PriorityQ.PQhandleElem[this.max + 1];
      System.arraycopy(this.handles, 0, local_i, 0, this.handles.length);
      for (int local_i1 = this.handles.length; local_i1 < local_i.length; local_i1++) {
        local_i[local_i1] = new PriorityQ.PQhandleElem();
      }
      this.handles = local_i;
      if (this.handles == null)
      {
        this.handles = saveHandles;
        return 2147483647;
      }
    }
    int free;
    int free;
    if (this.freeList == 0)
    {
      free = curr;
    }
    else
    {
      free = this.freeList;
      this.freeList = this.handles[free].node;
    }
    this.nodes[curr].handle = free;
    this.handles[free].node = curr;
    this.handles[free].key = keyNew;
    if (this.initialized) {
      FloatUp(curr);
    }
    assert (free != 2147483647);
    return free;
  }
  
  Object pqExtractMin()
  {
    PriorityQ.PQnode[] local_n = this.nodes;
    PriorityQ.PQhandleElem[] local_h = this.handles;
    int hMin = local_n[1].handle;
    Object min = local_h[hMin].key;
    if (this.size > 0)
    {
      local_n[1].handle = local_n[this.size].handle;
      local_h[local_n[1].handle].node = 1;
      local_h[hMin].key = null;
      local_h[hMin].node = this.freeList;
      this.freeList = hMin;
      if (--this.size > 0) {
        FloatDown(1);
      }
    }
    return min;
  }
  
  void pqDelete(int hCurr)
  {
    PriorityQ.PQnode[] local_n = this.nodes;
    PriorityQ.PQhandleElem[] local_h = this.handles;
    assert ((hCurr >= 1) && (hCurr <= this.max) && (local_h[hCurr].key != null));
    int curr = local_h[hCurr].node;
    local_n[curr].handle = local_n[this.size].handle;
    local_h[local_n[curr].handle].node = curr;
    if (curr <= --this.size) {
      if ((curr <= 1) || (LEQ(this.leq, local_h[local_n[(curr >> 1)].handle].key, local_h[local_n[curr].handle].key))) {
        FloatDown(curr);
      } else {
        FloatUp(curr);
      }
    }
    local_h[hCurr].key = null;
    local_h[hCurr].node = this.freeList;
    this.freeList = hCurr;
  }
  
  Object pqMinimum()
  {
    return this.handles[this.nodes[1].handle].key;
  }
  
  boolean pqIsEmpty()
  {
    return this.size == 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.tessellation.PriorityQHeap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */