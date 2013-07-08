package it.unimi.dsi.fastutil.doubles;

import java.util.NoSuchElementException;

public class DoubleHeapPriorityQueue
  extends AbstractDoublePriorityQueue
{
  protected double[] heap = DoubleArrays.EMPTY_ARRAY;
  protected int size;
  protected DoubleComparator field_76;
  
  public DoubleHeapPriorityQueue(int capacity, DoubleComparator local_c)
  {
    if (capacity > 0) {
      this.heap = new double[capacity];
    }
    this.field_76 = local_c;
  }
  
  public DoubleHeapPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public DoubleHeapPriorityQueue(DoubleComparator local_c)
  {
    this(0, local_c);
  }
  
  public DoubleHeapPriorityQueue()
  {
    this(0, null);
  }
  
  public DoubleHeapPriorityQueue(double[] local_a, int size, DoubleComparator local_c)
  {
    this(local_c);
    this.heap = local_a;
    this.size = size;
    DoubleHeaps.makeHeap(local_a, size, local_c);
  }
  
  public DoubleHeapPriorityQueue(double[] local_a, DoubleComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public DoubleHeapPriorityQueue(double[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public DoubleHeapPriorityQueue(double[] local_a)
  {
    this(local_a, local_a.length);
  }
  
  public void enqueue(double local_x)
  {
    if (this.size == this.heap.length) {
      this.heap = DoubleArrays.grow(this.heap, this.size + 1);
    }
    this.heap[(this.size++)] = local_x;
    DoubleHeaps.upHeap(this.heap, this.size, this.size - 1, this.field_76);
  }
  
  public double dequeueDouble()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    double result = this.heap[0];
    this.heap[0] = this.heap[(--this.size)];
    if (this.size != 0) {
      DoubleHeaps.downHeap(this.heap, this.size, 0, this.field_76);
    }
    return result;
  }
  
  public double firstDouble()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.heap[0];
  }
  
  public void changed()
  {
    DoubleHeaps.downHeap(this.heap, this.size, 0, this.field_76);
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
    this.heap = DoubleArrays.trim(this.heap, this.size);
  }
  
  public DoubleComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleHeapPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */