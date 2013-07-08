package org.lwjgl.util.glu.tessellation;

class PriorityQSort
  extends PriorityQ
{
  PriorityQHeap heap;
  Object[] keys;
  int[] order;
  int size;
  int max;
  boolean initialized;
  PriorityQ.Leq leq;
  
  PriorityQSort(PriorityQ.Leq leq)
  {
    this.heap = new PriorityQHeap(leq);
    this.keys = new Object[32];
    this.size = 0;
    this.max = 32;
    this.initialized = false;
    this.leq = leq;
  }
  
  void pqDeletePriorityQ()
  {
    if (this.heap != null) {
      this.heap.pqDeletePriorityQ();
    }
    this.order = null;
    this.keys = null;
  }
  
  private static boolean LT(PriorityQ.Leq leq, Object local_x, Object local_y)
  {
    return !PriorityQHeap.LEQ(leq, local_y, local_x);
  }
  
  private static boolean GT(PriorityQ.Leq leq, Object local_x, Object local_y)
  {
    return !PriorityQHeap.LEQ(leq, local_x, local_y);
  }
  
  private static void Swap(int[] array, int local_a, int local_b)
  {
    int tmp = array[local_a];
    array[local_a] = array[local_b];
    array[local_b] = tmp;
  }
  
  boolean pqInit()
  {
    Stack[] stack = new Stack[50];
    for (int local_k = 0; local_k < stack.length; local_k++) {
      stack[local_k] = new Stack(null);
    }
    int local_k = 0;
    int seed = 2016473283;
    this.order = new int[this.size + 1];
    int local_p = 0;
    int local_r = this.size - 1;
    int piv = 0;
    for (int local_i = local_p; local_i <= local_r; local_i++)
    {
      this.order[local_i] = piv;
      piv++;
    }
    stack[local_k].field_1713 = local_p;
    stack[local_k].field_1714 = local_r;
    local_k++;
    for (;;)
    {
      local_k--;
      if (local_k < 0) {
        break;
      }
      local_p = stack[local_k].field_1713;
      local_r = stack[local_k].field_1714;
      while (local_r > local_p + 10)
      {
        seed = Math.abs(seed * 1539415821 + 1);
        local_i = local_p + seed % (local_r - local_p + 1);
        piv = this.order[local_i];
        this.order[local_i] = this.order[local_p];
        this.order[local_p] = piv;
        local_i = local_p - 1;
        int local_j = local_r + 1;
        do
        {
          do
          {
            local_i++;
          } while (GT(this.leq, this.keys[this.order[local_i]], this.keys[piv]));
          do
          {
            local_j--;
          } while (LT(this.leq, this.keys[this.order[local_j]], this.keys[piv]));
          Swap(this.order, local_i, local_j);
        } while (local_i < local_j);
        Swap(this.order, local_i, local_j);
        if (local_i - local_p < local_r - local_j)
        {
          stack[local_k].field_1713 = (local_j + 1);
          stack[local_k].field_1714 = local_r;
          local_k++;
          local_r = local_i - 1;
        }
        else
        {
          stack[local_k].field_1713 = local_p;
          stack[local_k].field_1714 = (local_i - 1);
          local_k++;
          local_p = local_j + 1;
        }
      }
      for (local_i = local_p + 1; local_i <= local_r; local_i++)
      {
        piv = this.order[local_i];
        for (int local_j = local_i; (local_j > local_p) && (LT(this.leq, this.keys[this.order[(local_j - 1)]], this.keys[piv])); local_j--) {
          this.order[local_j] = this.order[(local_j - 1)];
        }
        this.order[local_j] = piv;
      }
    }
    this.max = this.size;
    this.initialized = true;
    this.heap.pqInit();
    return true;
  }
  
  int pqInsert(Object keyNew)
  {
    if (this.initialized) {
      return this.heap.pqInsert(keyNew);
    }
    int curr = this.size;
    if (++this.size >= this.max)
    {
      Object[] saveKey = this.keys;
      this.max <<= 1;
      Object[] pqKeys = new Object[this.max];
      System.arraycopy(this.keys, 0, pqKeys, 0, this.keys.length);
      this.keys = pqKeys;
      if (this.keys == null)
      {
        this.keys = saveKey;
        return 2147483647;
      }
    }
    assert (curr != 2147483647);
    this.keys[curr] = keyNew;
    return -(curr + 1);
  }
  
  Object pqExtractMin()
  {
    if (this.size == 0) {
      return this.heap.pqExtractMin();
    }
    Object sortMin = this.keys[this.order[(this.size - 1)]];
    if (!this.heap.pqIsEmpty())
    {
      Object heapMin = this.heap.pqMinimum();
      if (LEQ(this.leq, heapMin, sortMin)) {
        return this.heap.pqExtractMin();
      }
    }
    do
    {
      this.size -= 1;
    } while ((this.size > 0) && (this.keys[this.order[(this.size - 1)]] == null));
    return sortMin;
  }
  
  Object pqMinimum()
  {
    if (this.size == 0) {
      return this.heap.pqMinimum();
    }
    Object sortMin = this.keys[this.order[(this.size - 1)]];
    if (!this.heap.pqIsEmpty())
    {
      Object heapMin = this.heap.pqMinimum();
      if (PriorityQHeap.LEQ(this.leq, heapMin, sortMin)) {
        return heapMin;
      }
    }
    return sortMin;
  }
  
  boolean pqIsEmpty()
  {
    return (this.size == 0) && (this.heap.pqIsEmpty());
  }
  
  void pqDelete(int curr)
  {
    if (curr >= 0)
    {
      this.heap.pqDelete(curr);
      return;
    }
    curr = -(curr + 1);
    assert ((curr < this.max) && (this.keys[curr] != null));
    this.keys[curr] = null;
    while ((this.size > 0) && (this.keys[this.order[(this.size - 1)]] == null)) {
      this.size -= 1;
    }
  }
  
  private static class Stack
  {
    int field_1713;
    int field_1714;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.tessellation.PriorityQSort
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */