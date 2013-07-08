package org.hsqldb.lib;

import java.util.NoSuchElementException;

public class DoubleIntIndex
  implements IntLookup, LongLookup
{
  private int count = 0;
  private int capacity;
  private boolean sorted = true;
  private boolean sortOnValues = true;
  private boolean hasChanged;
  private final boolean fixedSize;
  private int[] keys;
  private int[] values;
  private int targetSearchValue;
  
  public DoubleIntIndex(int paramInt, boolean paramBoolean)
  {
    this.capacity = paramInt;
    this.keys = new int[paramInt];
    this.values = new int[paramInt];
    this.fixedSize = paramBoolean;
    this.hasChanged = true;
  }
  
  public synchronized int getKey(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.count)) {
      throw new IndexOutOfBoundsException();
    }
    return this.keys[paramInt];
  }
  
  public synchronized int getValue(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.count)) {
      throw new IndexOutOfBoundsException();
    }
    return this.values[paramInt];
  }
  
  public synchronized void setKey(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) || (paramInt1 >= this.count)) {
      throw new IndexOutOfBoundsException();
    }
    if (!this.sortOnValues) {
      this.sorted = false;
    }
    this.keys[paramInt1] = paramInt2;
  }
  
  public synchronized void setValue(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) || (paramInt1 >= this.count)) {
      throw new IndexOutOfBoundsException();
    }
    if (this.sortOnValues) {
      this.sorted = false;
    }
    this.values[paramInt1] = paramInt2;
  }
  
  public synchronized int size()
  {
    return this.count;
  }
  
  public synchronized int capacity()
  {
    return this.capacity;
  }
  
  public synchronized boolean addUnsorted(int paramInt1, int paramInt2)
  {
    if (this.count == this.capacity)
    {
      if (this.fixedSize) {
        return false;
      }
      doubleCapacity();
    }
    if ((this.sorted) && (this.count != 0)) {
      if (this.sortOnValues)
      {
        if (paramInt2 < this.values[(this.count - 1)]) {
          this.sorted = false;
        }
      }
      else if (paramInt1 < this.keys[(this.count - 1)]) {
        this.sorted = false;
      }
    }
    this.hasChanged = true;
    this.keys[this.count] = paramInt1;
    this.values[this.count] = paramInt2;
    this.count += 1;
    return true;
  }
  
  public synchronized boolean addSorted(int paramInt1, int paramInt2)
  {
    if (this.count == this.capacity)
    {
      if (this.fixedSize) {
        return false;
      }
      doubleCapacity();
    }
    if (this.count != 0) {
      if (this.sortOnValues)
      {
        if (paramInt2 < this.values[(this.count - 1)]) {
          return false;
        }
      }
      else if (paramInt1 < this.keys[(this.count - 1)]) {
        return false;
      }
    }
    this.hasChanged = true;
    this.keys[this.count] = paramInt1;
    this.values[this.count] = paramInt2;
    this.count += 1;
    return true;
  }
  
  public synchronized boolean addUnique(int paramInt1, int paramInt2)
  {
    if (this.count == this.capacity)
    {
      if (this.fixedSize) {
        return false;
      }
      doubleCapacity();
    }
    if (!this.sorted) {
      fastQuickSort();
    }
    this.targetSearchValue = (this.sortOnValues ? paramInt2 : paramInt1);
    int i = binaryEmptySlotSearch();
    if (i == -1) {
      return false;
    }
    this.hasChanged = true;
    if (this.count != i) {
      moveRows(i, i + 1, this.count - i);
    }
    this.keys[i] = paramInt1;
    this.values[i] = paramInt2;
    this.count += 1;
    return true;
  }
  
  public int add(long paramLong1, long paramLong2)
  {
    if ((paramLong1 > 2147483647L) || (paramLong1 < -2147483648L)) {
      throw new IllegalArgumentException();
    }
    if ((paramLong2 > 2147483647L) || (paramLong2 < -2147483648L)) {
      throw new IllegalArgumentException();
    }
    return add((int)paramLong1, (int)paramLong2);
  }
  
  public synchronized int add(int paramInt1, int paramInt2)
  {
    if (this.count == this.capacity)
    {
      if (this.fixedSize) {
        return -1;
      }
      doubleCapacity();
    }
    if (!this.sorted) {
      fastQuickSort();
    }
    this.targetSearchValue = (this.sortOnValues ? paramInt2 : paramInt1);
    int i = binarySlotSearch();
    if (i == -1) {
      return i;
    }
    this.hasChanged = true;
    if (this.count != i) {
      moveRows(i, i + 1, this.count - i);
    }
    this.keys[i] = paramInt1;
    this.values[i] = paramInt2;
    this.count += 1;
    return i;
  }
  
  public long lookup(long paramLong)
    throws NoSuchElementException
  {
    if ((paramLong > 2147483647L) || (paramLong < -2147483648L)) {
      throw new NoSuchElementException();
    }
    return lookup((int)paramLong);
  }
  
  public int lookup(int paramInt)
    throws NoSuchElementException
  {
    if (this.sortOnValues)
    {
      this.sorted = false;
      this.sortOnValues = false;
    }
    int i = findFirstEqualKeyIndex(paramInt);
    if (i == -1) {
      throw new NoSuchElementException();
    }
    return getValue(i);
  }
  
  public long lookup(long paramLong1, long paramLong2)
  {
    if ((paramLong1 > 2147483647L) || (paramLong1 < -2147483648L)) {
      return paramLong2;
    }
    if (this.sortOnValues)
    {
      this.sorted = false;
      this.sortOnValues = false;
    }
    int i = findFirstEqualKeyIndex((int)paramLong1);
    if (i == -1) {
      return paramLong2;
    }
    return getValue(i);
  }
  
  public int lookup(int paramInt1, int paramInt2)
  {
    if (this.sortOnValues)
    {
      this.sorted = false;
      this.sortOnValues = false;
    }
    int i = findFirstEqualKeyIndex(paramInt1);
    if (i == -1) {
      return paramInt2;
    }
    return getValue(i);
  }
  
  public int lookupFirstGreaterEqual(int paramInt)
    throws NoSuchElementException
  {
    if (this.sortOnValues)
    {
      this.sorted = false;
      this.sortOnValues = false;
    }
    int i = findFirstGreaterEqualKeyIndex(paramInt);
    if (i == -1) {
      throw new NoSuchElementException();
    }
    return getValue(i);
  }
  
  public synchronized void setValuesSearchTarget()
  {
    if (!this.sortOnValues) {
      this.sorted = false;
    }
    this.sortOnValues = true;
  }
  
  public synchronized void setKeysSearchTarget()
  {
    if (this.sortOnValues) {
      this.sorted = false;
    }
    this.sortOnValues = false;
  }
  
  public synchronized int findFirstGreaterEqualKeyIndex(int paramInt)
  {
    int i = findFirstGreaterEqualSlotIndex(paramInt);
    return i == this.count ? -1 : i;
  }
  
  public synchronized int findFirstEqualKeyIndex(int paramInt)
  {
    if (!this.sorted) {
      fastQuickSort();
    }
    this.targetSearchValue = paramInt;
    return binaryFirstSearch();
  }
  
  public synchronized int findFirstGreaterEqualSlotIndex(int paramInt)
  {
    if (!this.sorted) {
      fastQuickSort();
    }
    this.targetSearchValue = paramInt;
    return binarySlotSearch();
  }
  
  private int binaryFirstSearch()
  {
    int i = 0;
    int j = this.count;
    int k = 0;
    int m = 0;
    int n = this.count;
    while (i < j)
    {
      k = (i + j) / 2;
      m = compare(k);
      if (m < 0)
      {
        j = k;
      }
      else if (m > 0)
      {
        i = k + 1;
      }
      else
      {
        j = k;
        n = k;
      }
    }
    return n == this.count ? -1 : n;
  }
  
  private int binaryGreaterSearch()
  {
    int i = 0;
    int j = this.count;
    int k = 0;
    int m = 0;
    while (i < j)
    {
      k = (i + j) / 2;
      m = compare(k);
      if (m < 0) {
        j = k;
      } else {
        i = k + 1;
      }
    }
    return i == this.count ? -1 : i;
  }
  
  private int binarySlotSearch()
  {
    int i = 0;
    int j = this.count;
    int k = 0;
    int m = 0;
    while (i < j)
    {
      k = (i + j) / 2;
      m = compare(k);
      if (m <= 0) {
        j = k;
      } else {
        i = k + 1;
      }
    }
    return i;
  }
  
  private int binaryEmptySlotSearch()
  {
    int i = 0;
    int j = this.count;
    int k = 0;
    int m = 0;
    while (i < j)
    {
      k = (i + j) / 2;
      m = compare(k);
      if (m < 0) {
        j = k;
      } else if (m > 0) {
        i = k + 1;
      } else {
        return -1;
      }
    }
    return i;
  }
  
  public synchronized void sort()
  {
    fastQuickSort();
  }
  
  private synchronized void fastQuickSort()
  {
    DoubleIntIndex localDoubleIntIndex = new DoubleIntIndex(32, false);
    int i = 16;
    localDoubleIntIndex.push(0, this.count - 1);
    while (localDoubleIntIndex.size() > 0)
    {
      int j = localDoubleIntIndex.peekKey();
      int k = localDoubleIntIndex.peekValue();
      localDoubleIntIndex.pop();
      if (k - j >= i)
      {
        int m = partition(j, k, j + (k - j) / 2);
        localDoubleIntIndex.push(j, m - 1);
        localDoubleIntIndex.push(m + 1, k);
      }
      else
      {
        insertionSort(j, k);
      }
    }
    this.sorted = true;
  }
  
  private int partition(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt1;
    swap(paramInt3, paramInt2);
    for (int j = paramInt1; j <= paramInt2 - 1; j++) {
      if (lessThan(j, paramInt2))
      {
        swap(j, i);
        i++;
      }
    }
    swap(i, paramInt2);
    return i;
  }
  
  private synchronized void fastQuickSortRecursive()
  {
    quickSort(0, this.count - 1);
    insertionSort(0, this.count - 1);
    this.sorted = true;
  }
  
  private void quickSort(int paramInt1, int paramInt2)
  {
    int i = 16;
    if (paramInt2 - paramInt1 > i)
    {
      int j = (paramInt2 + paramInt1) / 2;
      if (lessThan(j, paramInt1)) {
        swap(paramInt1, j);
      }
      if (lessThan(paramInt2, paramInt1)) {
        swap(paramInt1, paramInt2);
      }
      if (lessThan(paramInt2, j)) {
        swap(j, paramInt2);
      }
      int k = paramInt2 - 1;
      swap(j, k);
      j = paramInt1;
      int m = k;
      for (;;)
      {
        if (!lessThan(++j, m))
        {
          while (lessThan(m, --k)) {}
          if (k < j) {
            break;
          }
          swap(j, k);
        }
      }
      swap(j, paramInt2 - 1);
      quickSort(paramInt1, k);
      quickSort(j + 1, paramInt2);
    }
  }
  
  private void insertionSort(int paramInt1, int paramInt2)
  {
    for (int i = paramInt1 + 1; i <= paramInt2; i++)
    {
      for (int j = i; (j > paramInt1) && (lessThan(i, j - 1)); j--) {}
      if (i != j) {
        moveAndInsertRow(i, j);
      }
    }
  }
  
  protected void moveAndInsertRow(int paramInt1, int paramInt2)
  {
    int i = this.keys[paramInt1];
    int j = this.values[paramInt1];
    moveRows(paramInt2, paramInt2 + 1, paramInt1 - paramInt2);
    this.keys[paramInt2] = i;
    this.values[paramInt2] = j;
  }
  
  protected void swap(int paramInt1, int paramInt2)
  {
    int i = this.keys[paramInt1];
    int j = this.values[paramInt1];
    this.keys[paramInt1] = this.keys[paramInt2];
    this.values[paramInt1] = this.values[paramInt2];
    this.keys[paramInt2] = i;
    this.values[paramInt2] = j;
  }
  
  protected int compare(int paramInt)
  {
    if (this.sortOnValues)
    {
      if (this.targetSearchValue > this.values[paramInt]) {
        return 1;
      }
      if (this.targetSearchValue < this.values[paramInt]) {
        return -1;
      }
    }
    else
    {
      if (this.targetSearchValue > this.keys[paramInt]) {
        return 1;
      }
      if (this.targetSearchValue < this.keys[paramInt]) {
        return -1;
      }
    }
    return 0;
  }
  
  protected boolean lessThan(int paramInt1, int paramInt2)
  {
    if (this.sortOnValues)
    {
      if (this.values[paramInt1] < this.values[paramInt2]) {
        return true;
      }
    }
    else if (this.keys[paramInt1] < this.keys[paramInt2]) {
      return true;
    }
    return false;
  }
  
  protected void moveRows(int paramInt1, int paramInt2, int paramInt3)
  {
    System.arraycopy(this.keys, paramInt1, this.keys, paramInt2, paramInt3);
    System.arraycopy(this.values, paramInt1, this.values, paramInt2, paramInt3);
  }
  
  protected void doubleCapacity()
  {
    this.keys = ((int[])ArrayUtil.resizeArray(this.keys, this.capacity * 2));
    this.values = ((int[])ArrayUtil.resizeArray(this.values, this.capacity * 2));
    this.capacity *= 2;
  }
  
  public void removeRange(int paramInt1, int paramInt2)
  {
    moveRows(paramInt2, paramInt1, this.count - paramInt2);
    this.count -= paramInt2 - paramInt1;
  }
  
  public void removeAll()
  {
    this.hasChanged = true;
    ArrayUtil.clearArray(73, this.keys, 0, this.count);
    ArrayUtil.clearArray(73, this.values, 0, this.count);
    this.count = 0;
  }
  
  public final synchronized void remove(int paramInt)
  {
    this.hasChanged = true;
    moveRows(paramInt + 1, paramInt, this.count - paramInt - 1);
    this.count -= 1;
    this.keys[this.count] = 0;
    this.values[this.count] = 0;
  }
  
  private int peekKey()
  {
    return getKey(this.count - 1);
  }
  
  private int peekValue()
  {
    return getValue(this.count - 1);
  }
  
  private boolean pop()
  {
    if (this.count > 0)
    {
      this.count -= 1;
      return true;
    }
    return false;
  }
  
  private boolean push(int paramInt1, int paramInt2)
  {
    return addUnsorted(paramInt1, paramInt2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.DoubleIntIndex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */