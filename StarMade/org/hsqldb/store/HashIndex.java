package org.hsqldb.store;

public class HashIndex
{
  int[] hashTable;
  int[] linkTable;
  int newNodePointer;
  int elementCount;
  int reclaimedNodePointer = -1;
  boolean fixedSize;
  boolean modified;

  public HashIndex(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramInt2 < paramInt1)
      paramInt2 = paramInt1;
    reset(paramInt1, paramInt2);
    this.fixedSize = paramBoolean;
  }

  public void reset(int paramInt1, int paramInt2)
  {
    int[] arrayOfInt1 = new int[paramInt1];
    int[] arrayOfInt2 = new int[paramInt2];
    this.hashTable = arrayOfInt1;
    this.linkTable = arrayOfInt2;
    resetTables();
  }

  public void resetTables()
  {
    int i = this.hashTable.length;
    int[] arrayOfInt = this.hashTable;
    while (true)
    {
      i--;
      if (i < 0)
        break;
      arrayOfInt[i] = -1;
    }
    this.newNodePointer = 0;
    this.elementCount = 0;
    this.reclaimedNodePointer = -1;
    this.modified = false;
  }

  public void clear()
  {
    int i = this.linkTable.length;
    int[] arrayOfInt = this.linkTable;
    while (true)
    {
      i--;
      if (i < 0)
        break;
      arrayOfInt[i] = 0;
    }
    resetTables();
  }

  public int getHashIndex(int paramInt)
  {
    return (paramInt & 0x7FFFFFFF) % this.hashTable.length;
  }

  public int getLookup(int paramInt)
  {
    if (this.elementCount == 0)
      return -1;
    int i = (paramInt & 0x7FFFFFFF) % this.hashTable.length;
    return this.hashTable[i];
  }

  public int getNextLookup(int paramInt)
  {
    return this.linkTable[paramInt];
  }

  public int linkNode(int paramInt1, int paramInt2)
  {
    int i = this.reclaimedNodePointer;
    if (i == -1)
      i = this.newNodePointer++;
    else
      this.reclaimedNodePointer = this.linkTable[i];
    if (paramInt2 == -1)
      this.hashTable[paramInt1] = i;
    else
      this.linkTable[paramInt2] = i;
    this.linkTable[i] = -1;
    this.elementCount += 1;
    this.modified = true;
    return i;
  }

  public void unlinkNode(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 == -1)
      this.hashTable[paramInt1] = this.linkTable[paramInt3];
    else
      this.linkTable[paramInt2] = this.linkTable[paramInt3];
    this.linkTable[paramInt3] = this.reclaimedNodePointer;
    this.reclaimedNodePointer = paramInt3;
    this.elementCount -= 1;
  }

  public boolean removeEmptyNode(int paramInt)
  {
    int i = 0;
    int j = -1;
    for (int k = this.reclaimedNodePointer; k >= 0; k = this.linkTable[k])
    {
      if (k == paramInt)
      {
        if (j == -1)
          this.reclaimedNodePointer = this.linkTable[paramInt];
        else
          this.linkTable[j] = this.linkTable[paramInt];
        i = 1;
        break;
      }
      j = k;
    }
    if (i == 0)
      return false;
    for (k = 0; k < this.newNodePointer; k++)
      if (this.linkTable[k] > paramInt)
        this.linkTable[k] -= 1;
    System.arraycopy(this.linkTable, paramInt + 1, this.linkTable, paramInt, this.newNodePointer - paramInt - 1);
    this.linkTable[(this.newNodePointer - 1)] = 0;
    this.newNodePointer -= 1;
    for (k = 0; k < this.hashTable.length; k++)
      if (this.hashTable[k] > paramInt)
        this.hashTable[k] -= 1;
    return true;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.store.HashIndex
 * JD-Core Version:    0.6.2
 */