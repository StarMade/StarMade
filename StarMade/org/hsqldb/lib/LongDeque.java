package org.hsqldb.lib;

import java.util.NoSuchElementException;

public class LongDeque
{
  private long[] list = new long[10];
  private int firstindex = 0;
  private int endindex = 0;
  protected int elementCount;
  private static final int DEFAULT_INITIAL_CAPACITY = 10;
  
  public int size()
  {
    return this.elementCount;
  }
  
  public boolean isEmpty()
  {
    return this.elementCount == 0;
  }
  
  public long getFirst()
    throws NoSuchElementException
  {
    if (this.elementCount == 0) {
      throw new NoSuchElementException();
    }
    return this.list[this.firstindex];
  }
  
  public long getLast()
    throws NoSuchElementException
  {
    if (this.elementCount == 0) {
      throw new NoSuchElementException();
    }
    return this.list[(this.endindex - 1)];
  }
  
  public long get(int paramInt)
    throws IndexOutOfBoundsException
  {
    int i = getInternalIndex(paramInt);
    return this.list[i];
  }
  
  public long removeFirst()
    throws NoSuchElementException
  {
    if (this.elementCount == 0) {
      throw new NoSuchElementException();
    }
    long l = this.list[this.firstindex];
    this.list[this.firstindex] = 0L;
    this.firstindex += 1;
    this.elementCount -= 1;
    if (this.elementCount == 0) {
      this.firstindex = (this.endindex = 0);
    } else if (this.firstindex == this.list.length) {
      this.firstindex = 0;
    }
    return l;
  }
  
  public long removeLast()
    throws NoSuchElementException
  {
    if (this.elementCount == 0) {
      throw new NoSuchElementException();
    }
    this.endindex -= 1;
    long l = this.list[this.endindex];
    this.list[this.endindex] = 0L;
    this.elementCount -= 1;
    if (this.elementCount == 0) {
      this.firstindex = (this.endindex = 0);
    } else if (this.endindex == 0) {
      this.endindex = this.list.length;
    }
    return l;
  }
  
  public boolean add(long paramLong)
  {
    resetCapacity();
    if (this.endindex == this.list.length) {
      this.endindex = 0;
    }
    this.list[this.endindex] = paramLong;
    this.elementCount += 1;
    this.endindex += 1;
    return true;
  }
  
  public boolean addLast(long paramLong)
  {
    return add(paramLong);
  }
  
  public boolean addFirst(long paramLong)
  {
    resetCapacity();
    this.firstindex -= 1;
    if (this.firstindex < 0)
    {
      this.firstindex = (this.list.length - 1);
      if (this.endindex == 0) {
        this.endindex = this.list.length;
      }
    }
    this.list[this.firstindex] = paramLong;
    this.elementCount += 1;
    return true;
  }
  
  public int addAll(LongDeque paramLongDeque)
  {
    int i = 0;
    for (int j = 0; j < paramLongDeque.size(); j++)
    {
      add(paramLongDeque.get(j));
      i++;
    }
    return i;
  }
  
  public void clear()
  {
    if (this.elementCount == 0) {
      return;
    }
    this.firstindex = (this.endindex = this.elementCount = 0);
    for (int i = 0; i < this.list.length; i++) {
      this.list[i] = 0L;
    }
  }
  
  public void zeroSize()
  {
    this.firstindex = (this.endindex = this.elementCount = 0);
  }
  
  public int indexOf(long paramLong)
  {
    for (int i = 0; i < this.elementCount; i++)
    {
      int j = this.firstindex + i;
      if (j >= this.list.length) {
        j -= this.list.length;
      }
      if (this.list[j] == paramLong) {
        return i;
      }
    }
    return -1;
  }
  
  public long remove(int paramInt)
  {
    int i = getInternalIndex(paramInt);
    long l = this.list[i];
    if (i == this.firstindex)
    {
      this.list[this.firstindex] = 0L;
      this.firstindex += 1;
      if (this.firstindex == this.list.length) {
        this.firstindex = 0;
      }
    }
    else if (i > this.firstindex)
    {
      System.arraycopy(this.list, this.firstindex, this.list, this.firstindex + 1, i - this.firstindex);
      this.list[this.firstindex] = 0L;
      this.firstindex += 1;
      if (this.firstindex == this.list.length) {
        this.firstindex = 0;
      }
    }
    else
    {
      System.arraycopy(this.list, i + 1, this.list, i, this.endindex - i - 1);
      this.endindex -= 1;
      this.list[this.endindex] = 0L;
      if (this.endindex == 0) {
        this.endindex = this.list.length;
      }
    }
    this.elementCount -= 1;
    if (this.elementCount == 0) {
      this.firstindex = (this.endindex = 0);
    }
    return l;
  }
  
  public boolean contains(long paramLong)
  {
    for (int i = 0; i < this.elementCount; i++)
    {
      int j = this.firstindex + i;
      if (j >= this.list.length) {
        j -= this.list.length;
      }
      if (this.list[j] == paramLong) {
        return true;
      }
    }
    return false;
  }
  
  public void toArray(int[] paramArrayOfInt)
  {
    for (int i = 0; i < this.elementCount; i++) {
      paramArrayOfInt[i] = ((int)get(i));
    }
  }
  
  public void toArray(long[] paramArrayOfLong)
  {
    for (int i = 0; i < this.elementCount; i++) {
      paramArrayOfLong[i] = get(i);
    }
  }
  
  private int getInternalIndex(int paramInt)
    throws IndexOutOfBoundsException
  {
    if ((paramInt < 0) || (paramInt >= this.elementCount)) {
      throw new IndexOutOfBoundsException();
    }
    int i = this.firstindex + paramInt;
    if (i >= this.list.length) {
      i -= this.list.length;
    }
    return i;
  }
  
  private void resetCapacity()
  {
    if (this.elementCount < this.list.length) {
      return;
    }
    long[] arrayOfLong = new long[this.list.length * 2];
    System.arraycopy(this.list, this.firstindex, arrayOfLong, this.firstindex, this.list.length - this.firstindex);
    if (this.endindex <= this.firstindex)
    {
      System.arraycopy(this.list, 0, arrayOfLong, this.list.length, this.endindex);
      this.endindex = (this.list.length + this.endindex);
    }
    this.list = arrayOfLong;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.LongDeque
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */