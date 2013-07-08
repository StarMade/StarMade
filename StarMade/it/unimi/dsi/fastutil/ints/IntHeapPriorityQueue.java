package it.unimi.dsi.fastutil.ints;

import java.util.NoSuchElementException;

public class IntHeapPriorityQueue
  extends AbstractIntPriorityQueue
{
  protected int[] heap = IntArrays.EMPTY_ARRAY;
  protected int size;
  protected IntComparator field_76;
  
  public IntHeapPriorityQueue(int capacity, IntComparator local_c)
  {
    if (capacity > 0) {
      this.heap = new int[capacity];
    }
    this.field_76 = local_c;
  }
  
  public IntHeapPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public IntHeapPriorityQueue(IntComparator local_c)
  {
    this(0, local_c);
  }
  
  public IntHeapPriorityQueue()
  {
    this(0, null);
  }
  
  public IntHeapPriorityQueue(int[] local_a, int size, IntComparator local_c)
  {
    this(local_c);
    this.heap = local_a;
    this.size = size;
    IntHeaps.makeHeap(local_a, size, local_c);
  }
  
  public IntHeapPriorityQueue(int[] local_a, IntComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public IntHeapPriorityQueue(int[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public IntHeapPriorityQueue(int[] local_a)
  {
    this(local_a, local_a.length);
  }
  
  public void enqueue(int local_x)
  {
    if (this.size == this.heap.length) {
      this.heap = IntArrays.grow(this.heap, this.size + 1);
    }
    this.heap[(this.size++)] = local_x;
    IntHeaps.upHeap(this.heap, this.size, this.size - 1, this.field_76);
  }
  
  public int dequeueInt()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    int result = this.heap[0];
    this.heap[0] = this.heap[(--this.size)];
    if (this.size != 0) {
      IntHeaps.downHeap(this.heap, this.size, 0, this.field_76);
    }
    return result;
  }
  
  public int firstInt()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.heap[0];
  }
  
  public void changed()
  {
    IntHeaps.downHeap(this.heap, this.size, 0, this.field_76);
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
    this.heap = IntArrays.trim(this.heap, this.size);
  }
  
  public IntComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntHeapPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */