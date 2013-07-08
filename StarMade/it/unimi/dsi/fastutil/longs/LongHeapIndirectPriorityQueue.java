package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.ints.IntArrays;
import java.util.NoSuchElementException;

public class LongHeapIndirectPriorityQueue
  extends LongHeapSemiIndirectPriorityQueue
{
  protected int[] inv;
  
  public LongHeapIndirectPriorityQueue(long[] refArray, int capacity, LongComparator local_c)
  {
    super(refArray, capacity, local_c);
    if (capacity > 0) {
      this.heap = new int[capacity];
    }
    this.refArray = refArray;
    this.field_69 = local_c;
    this.inv = new int[refArray.length];
    IntArrays.fill(this.inv, -1);
  }
  
  public LongHeapIndirectPriorityQueue(long[] refArray, int capacity)
  {
    this(refArray, capacity, null);
  }
  
  public LongHeapIndirectPriorityQueue(long[] refArray, LongComparator local_c)
  {
    this(refArray, refArray.length, local_c);
  }
  
  public LongHeapIndirectPriorityQueue(long[] refArray)
  {
    this(refArray, refArray.length, null);
  }
  
  public LongHeapIndirectPriorityQueue(long[] refArray, int[] local_a, int size, LongComparator local_c)
  {
    this(refArray, 0, local_c);
    this.heap = local_a;
    this.size = size;
    int local_i = size;
    while (local_i-- != 0)
    {
      if (this.inv[local_a[local_i]] != -1) {
        throw new IllegalArgumentException("Index " + local_a[local_i] + " appears twice in the heap");
      }
      this.inv[local_a[local_i]] = local_i;
    }
    LongIndirectHeaps.makeHeap(refArray, local_a, this.inv, size, local_c);
  }
  
  public LongHeapIndirectPriorityQueue(long[] refArray, int[] local_a, LongComparator local_c)
  {
    this(refArray, local_a, local_a.length, local_c);
  }
  
  public LongHeapIndirectPriorityQueue(long[] refArray, int[] local_a, int size)
  {
    this(refArray, local_a, size, null);
  }
  
  public LongHeapIndirectPriorityQueue(long[] refArray, int[] local_a)
  {
    this(refArray, local_a, local_a.length);
  }
  
  public void enqueue(int local_x)
  {
    if (this.inv[local_x] >= 0) {
      throw new IllegalArgumentException("Index " + local_x + " belongs to the queue");
    }
    if (this.size == this.heap.length) {
      this.heap = IntArrays.grow(this.heap, this.size + 1);
    }
    int tmp83_82 = local_x;
    this.heap[this.size] = tmp83_82;
    this.inv[tmp83_82] = (this.size++);
    LongIndirectHeaps.upHeap(this.refArray, this.heap, this.inv, this.size, this.size - 1, this.field_69);
  }
  
  public boolean contains(int index)
  {
    return this.inv[index] >= 0;
  }
  
  public int dequeue()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    int result = this.heap[0];
    if (--this.size != 0)
    {
      int tmp54_53 = this.heap[this.size];
      this.heap[0] = tmp54_53;
      this.inv[tmp54_53] = 0;
    }
    this.inv[result] = -1;
    if (this.size != 0) {
      LongIndirectHeaps.downHeap(this.refArray, this.heap, this.inv, this.size, 0, this.field_69);
    }
    return result;
  }
  
  public void changed()
  {
    LongIndirectHeaps.downHeap(this.refArray, this.heap, this.inv, this.size, 0, this.field_69);
  }
  
  public void changed(int index)
  {
    int pos = this.inv[index];
    if (pos < 0) {
      throw new IllegalArgumentException("Index " + index + " does not belong to the queue");
    }
    int newPos = LongIndirectHeaps.upHeap(this.refArray, this.heap, this.inv, this.size, pos, this.field_69);
    LongIndirectHeaps.downHeap(this.refArray, this.heap, this.inv, this.size, newPos, this.field_69);
  }
  
  public void allChanged()
  {
    LongIndirectHeaps.makeHeap(this.refArray, this.heap, this.inv, this.size, this.field_69);
  }
  
  public boolean remove(int index)
  {
    int result = this.inv[index];
    if (result < 0) {
      return false;
    }
    this.inv[index] = -1;
    if (result < --this.size)
    {
      int tmp53_52 = this.heap[this.size];
      this.heap[result] = tmp53_52;
      this.inv[tmp53_52] = result;
      int newPos = LongIndirectHeaps.upHeap(this.refArray, this.heap, this.inv, this.size, result, this.field_69);
      LongIndirectHeaps.downHeap(this.refArray, this.heap, this.inv, this.size, newPos, this.field_69);
    }
    return true;
  }
  
  public void clear()
  {
    this.size = 0;
    IntArrays.fill(this.inv, -1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongHeapIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */