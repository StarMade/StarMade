package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.AbstractPriorityQueue;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ObjectHeapPriorityQueue<K>
  extends AbstractPriorityQueue<K>
{
  protected K[] heap = (Object[])ObjectArrays.EMPTY_ARRAY;
  protected int size;
  protected Comparator<? super K> field_76;
  
  public ObjectHeapPriorityQueue(int capacity, Comparator<? super K> local_c)
  {
    if (capacity > 0) {
      this.heap = ((Object[])new Object[capacity]);
    }
    this.field_76 = local_c;
  }
  
  public ObjectHeapPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public ObjectHeapPriorityQueue(Comparator<? super K> local_c)
  {
    this(0, local_c);
  }
  
  public ObjectHeapPriorityQueue()
  {
    this(0, null);
  }
  
  public ObjectHeapPriorityQueue(K[] local_a, int size, Comparator<? super K> local_c)
  {
    this(local_c);
    this.heap = local_a;
    this.size = size;
    ObjectHeaps.makeHeap(local_a, size, local_c);
  }
  
  public ObjectHeapPriorityQueue(K[] local_a, Comparator<? super K> local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public ObjectHeapPriorityQueue(K[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public ObjectHeapPriorityQueue(K[] local_a)
  {
    this(local_a, local_a.length);
  }
  
  public void enqueue(K local_x)
  {
    if (this.size == this.heap.length) {
      this.heap = ObjectArrays.grow(this.heap, this.size + 1);
    }
    this.heap[(this.size++)] = local_x;
    ObjectHeaps.upHeap(this.heap, this.size, this.size - 1, this.field_76);
  }
  
  public K dequeue()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    K result = this.heap[0];
    this.heap[0] = this.heap[(--this.size)];
    this.heap[this.size] = null;
    if (this.size != 0) {
      ObjectHeaps.downHeap(this.heap, this.size, 0, this.field_76);
    }
    return result;
  }
  
  public K first()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.heap[0];
  }
  
  public void changed()
  {
    ObjectHeaps.downHeap(this.heap, this.size, 0, this.field_76);
  }
  
  public int size()
  {
    return this.size;
  }
  
  public void clear()
  {
    ObjectArrays.fill(this.heap, 0, this.size, null);
    this.size = 0;
  }
  
  public void trim()
  {
    this.heap = ObjectArrays.trim(this.heap, this.size);
  }
  
  public Comparator<? super K> comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */