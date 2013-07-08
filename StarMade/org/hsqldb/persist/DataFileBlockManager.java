package org.hsqldb.persist;

import org.hsqldb.lib.DoubleIntIndex;

public class DataFileBlockManager
{
  private DoubleIntIndex lookup;
  private final int capacity;
  private int midSize;
  private final int scale;
  private final int reuseMin;
  private long releaseCount;
  private long requestCount;
  private long requestSize;
  long lostFreeBlockSize;
  boolean isModified;
  
  public DataFileBlockManager(int paramInt1, int paramInt2, int paramInt3, long paramLong)
  {
    this.lookup = new DoubleIntIndex(paramInt1, true);
    this.lookup.setValuesSearchTarget();
    this.capacity = paramInt1;
    this.scale = paramInt2;
    this.reuseMin = paramInt3;
    this.lostFreeBlockSize = paramLong;
    this.midSize = 128;
  }
  
  void add(long paramLong, int paramInt)
  {
    this.isModified = true;
    if ((this.capacity == 0) || (paramInt < this.reuseMin))
    {
      this.lostFreeBlockSize += paramInt;
      return;
    }
    this.releaseCount += 1L;
    if (this.lookup.size() == this.capacity) {
      resetList();
    }
    if (paramLong < 2147483647L) {
      this.lookup.add((int)paramLong, paramInt);
    }
  }
  
  int get(int paramInt)
  {
    if ((this.capacity == 0) || (paramInt < this.reuseMin)) {
      return -1;
    }
    int i = this.lookup.findFirstGreaterEqualKeyIndex(paramInt);
    if (i == -1) {
      return -1;
    }
    this.requestCount += 1L;
    this.requestSize += paramInt;
    int j = this.lookup.getValue(i);
    int k = j - paramInt;
    int m = this.lookup.getKey(i);
    this.lookup.remove(i);
    if (k >= this.midSize)
    {
      int n = m + paramInt / this.scale;
      this.lookup.add(n, k);
    }
    else
    {
      this.lostFreeBlockSize += k;
    }
    return m;
  }
  
  int size()
  {
    return this.lookup.size();
  }
  
  long getLostBlocksSize()
  {
    return this.lostFreeBlockSize;
  }
  
  boolean isModified()
  {
    return this.isModified;
  }
  
  void clear()
  {
    removeBlocks(this.lookup.size());
  }
  
  private void resetList()
  {
    if (this.requestCount != 0L) {
      this.midSize = ((int)(this.requestSize / this.requestCount));
    }
    int i = this.lookup.findFirstGreaterEqualSlotIndex(this.midSize);
    if (i < this.lookup.size() / 4) {
      i = this.lookup.size() / 4;
    }
    removeBlocks(i);
  }
  
  private void removeBlocks(int paramInt)
  {
    for (int i = 0; i < paramInt; i++) {
      this.lostFreeBlockSize += this.lookup.getValue(i);
    }
    this.lookup.removeRange(0, paramInt);
  }
  
  private void checkIntegrity()
    throws NullPointerException
  {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.DataFileBlockManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */