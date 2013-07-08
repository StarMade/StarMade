package org.hsqldb.lib;

import java.util.Comparator;

public class HsqlArrayHeap
  implements HsqlHeap
{
  protected Comparator oc;
  protected int count;
  protected Object[] heap;
  
  public HsqlArrayHeap(int paramInt, Comparator paramComparator)
    throws IllegalArgumentException
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("" + paramInt);
    }
    if (paramComparator == null) {
      throw new IllegalArgumentException("null comparator");
    }
    this.heap = new Object[paramInt];
    this.oc = paramComparator;
  }
  
  public synchronized void clear()
  {
    for (int i = 0; i < this.count; i++) {
      this.heap[i] = null;
    }
    this.count = 0;
  }
  
  public synchronized void add(Object paramObject)
    throws IllegalArgumentException, RuntimeException
  {
    if (paramObject == null) {
      throw new IllegalArgumentException("null element");
    }
    if (isFull()) {
      throw new RuntimeException("full");
    }
    if (this.count >= this.heap.length) {
      increaseCapacity();
    }
    int i = this.count;
    this.count += 1;
    while (i > 0)
    {
      int j = i - 1 >> 1;
      try
      {
        if (this.oc.compare(paramObject, this.heap[j]) >= 0) {
          break;
        }
      }
      catch (Exception localException)
      {
        throw new IllegalArgumentException(localException.toString());
      }
      this.heap[i] = this.heap[j];
      i = j;
    }
    this.heap[i] = paramObject;
  }
  
  public synchronized boolean isEmpty()
  {
    return this.count == 0;
  }
  
  public synchronized boolean isFull()
  {
    return this.count == 2147483647;
  }
  
  public synchronized Object peek()
  {
    return this.heap[0];
  }
  
  public synchronized Object remove()
  {
    if (this.count == 0) {
      return null;
    }
    int i = 0;
    Object localObject2 = this.heap[i];
    this.count -= 1;
    if (this.count == 0)
    {
      this.heap[0] = null;
      return localObject2;
    }
    Object localObject1 = this.heap[this.count];
    this.heap[this.count] = null;
    for (;;)
    {
      int j = (i << 1) + 1;
      if (j >= this.count) {
        break;
      }
      int k = (i << 1) + 2;
      int m = (k >= this.count) || (this.oc.compare(this.heap[j], this.heap[k]) < 0) ? j : k;
      if (this.oc.compare(localObject1, this.heap[m]) <= 0) {
        break;
      }
      this.heap[i] = this.heap[m];
      i = m;
    }
    this.heap[i] = localObject1;
    return localObject2;
  }
  
  public synchronized int size()
  {
    return this.count;
  }
  
  public synchronized String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(super.toString());
    localStringBuffer.append(" : size=");
    localStringBuffer.append(this.count);
    localStringBuffer.append(' ');
    localStringBuffer.append('[');
    for (int i = 0; i < this.count; i++)
    {
      localStringBuffer.append(this.heap[i]);
      if (i + 1 < this.count)
      {
        localStringBuffer.append(',');
        localStringBuffer.append(' ');
      }
    }
    localStringBuffer.append(']');
    return localStringBuffer.toString();
  }
  
  private void increaseCapacity()
  {
    Object[] arrayOfObject = this.heap;
    this.heap = new Object[3 * this.heap.length / 2 + 1];
    System.arraycopy(arrayOfObject, 0, this.heap, 0, this.count);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.HsqlArrayHeap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */