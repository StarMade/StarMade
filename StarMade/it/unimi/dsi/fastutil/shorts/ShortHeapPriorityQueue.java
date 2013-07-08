package it.unimi.dsi.fastutil.shorts;

import java.util.NoSuchElementException;

public class ShortHeapPriorityQueue
  extends AbstractShortPriorityQueue
{
  protected short[] heap = ShortArrays.EMPTY_ARRAY;
  protected int size;
  protected ShortComparator field_76;
  
  public ShortHeapPriorityQueue(int capacity, ShortComparator local_c)
  {
    if (capacity > 0) {
      this.heap = new short[capacity];
    }
    this.field_76 = local_c;
  }
  
  public ShortHeapPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public ShortHeapPriorityQueue(ShortComparator local_c)
  {
    this(0, local_c);
  }
  
  public ShortHeapPriorityQueue()
  {
    this(0, null);
  }
  
  public ShortHeapPriorityQueue(short[] local_a, int size, ShortComparator local_c)
  {
    this(local_c);
    this.heap = local_a;
    this.size = size;
    ShortHeaps.makeHeap(local_a, size, local_c);
  }
  
  public ShortHeapPriorityQueue(short[] local_a, ShortComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public ShortHeapPriorityQueue(short[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public ShortHeapPriorityQueue(short[] local_a)
  {
    this(local_a, local_a.length);
  }
  
  public void enqueue(short local_x)
  {
    if (this.size == this.heap.length) {
      this.heap = ShortArrays.grow(this.heap, this.size + 1);
    }
    this.heap[(this.size++)] = local_x;
    ShortHeaps.upHeap(this.heap, this.size, this.size - 1, this.field_76);
  }
  
  public short dequeueShort()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    short result = this.heap[0];
    this.heap[0] = this.heap[(--this.size)];
    if (this.size != 0) {
      ShortHeaps.downHeap(this.heap, this.size, 0, this.field_76);
    }
    return result;
  }
  
  public short firstShort()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.heap[0];
  }
  
  public void changed()
  {
    ShortHeaps.downHeap(this.heap, this.size, 0, this.field_76);
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
    this.heap = ShortArrays.trim(this.heap, this.size);
  }
  
  public ShortComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortHeapPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */