package it.unimi.dsi.fastutil.floats;

import java.util.NoSuchElementException;

public class FloatHeapPriorityQueue
  extends AbstractFloatPriorityQueue
{
  protected float[] heap = FloatArrays.EMPTY_ARRAY;
  protected int size;
  protected FloatComparator field_76;
  
  public FloatHeapPriorityQueue(int capacity, FloatComparator local_c)
  {
    if (capacity > 0) {
      this.heap = new float[capacity];
    }
    this.field_76 = local_c;
  }
  
  public FloatHeapPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public FloatHeapPriorityQueue(FloatComparator local_c)
  {
    this(0, local_c);
  }
  
  public FloatHeapPriorityQueue()
  {
    this(0, null);
  }
  
  public FloatHeapPriorityQueue(float[] local_a, int size, FloatComparator local_c)
  {
    this(local_c);
    this.heap = local_a;
    this.size = size;
    FloatHeaps.makeHeap(local_a, size, local_c);
  }
  
  public FloatHeapPriorityQueue(float[] local_a, FloatComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public FloatHeapPriorityQueue(float[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public FloatHeapPriorityQueue(float[] local_a)
  {
    this(local_a, local_a.length);
  }
  
  public void enqueue(float local_x)
  {
    if (this.size == this.heap.length) {
      this.heap = FloatArrays.grow(this.heap, this.size + 1);
    }
    this.heap[(this.size++)] = local_x;
    FloatHeaps.upHeap(this.heap, this.size, this.size - 1, this.field_76);
  }
  
  public float dequeueFloat()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    float result = this.heap[0];
    this.heap[0] = this.heap[(--this.size)];
    if (this.size != 0) {
      FloatHeaps.downHeap(this.heap, this.size, 0, this.field_76);
    }
    return result;
  }
  
  public float firstFloat()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.heap[0];
  }
  
  public void changed()
  {
    FloatHeaps.downHeap(this.heap, this.size, 0, this.field_76);
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
    this.heap = FloatArrays.trim(this.heap, this.size);
  }
  
  public FloatComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatHeapPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */