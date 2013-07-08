package it.unimi.dsi.fastutil.longs;

import java.util.NoSuchElementException;

public class LongHeapPriorityQueue
  extends AbstractLongPriorityQueue
{
  protected long[] heap = LongArrays.EMPTY_ARRAY;
  protected int size;
  protected LongComparator field_76;
  
  public LongHeapPriorityQueue(int capacity, LongComparator local_c)
  {
    if (capacity > 0) {
      this.heap = new long[capacity];
    }
    this.field_76 = local_c;
  }
  
  public LongHeapPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public LongHeapPriorityQueue(LongComparator local_c)
  {
    this(0, local_c);
  }
  
  public LongHeapPriorityQueue()
  {
    this(0, null);
  }
  
  public LongHeapPriorityQueue(long[] local_a, int size, LongComparator local_c)
  {
    this(local_c);
    this.heap = local_a;
    this.size = size;
    LongHeaps.makeHeap(local_a, size, local_c);
  }
  
  public LongHeapPriorityQueue(long[] local_a, LongComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public LongHeapPriorityQueue(long[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public LongHeapPriorityQueue(long[] local_a)
  {
    this(local_a, local_a.length);
  }
  
  public void enqueue(long local_x)
  {
    if (this.size == this.heap.length) {
      this.heap = LongArrays.grow(this.heap, this.size + 1);
    }
    this.heap[(this.size++)] = local_x;
    LongHeaps.upHeap(this.heap, this.size, this.size - 1, this.field_76);
  }
  
  public long dequeueLong()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    long result = this.heap[0];
    this.heap[0] = this.heap[(--this.size)];
    if (this.size != 0) {
      LongHeaps.downHeap(this.heap, this.size, 0, this.field_76);
    }
    return result;
  }
  
  public long firstLong()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.heap[0];
  }
  
  public void changed()
  {
    LongHeaps.downHeap(this.heap, this.size, 0, this.field_76);
  }
  
  public int size()
  {
    return this.size;
  }
  
  public void clear()
  {
    this.size = 0;
  }
  
  public void trim()
  {
    this.heap = LongArrays.trim(this.heap, this.size);
  }
  
  public LongComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongHeapPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */