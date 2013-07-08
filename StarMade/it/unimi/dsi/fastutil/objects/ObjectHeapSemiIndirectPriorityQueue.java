package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.AbstractIndirectPriorityQueue;
import it.unimi.dsi.fastutil.IndirectPriorityQueue;
import it.unimi.dsi.fastutil.ints.IntArrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ObjectHeapSemiIndirectPriorityQueue<K>
  extends AbstractIndirectPriorityQueue<K>
  implements IndirectPriorityQueue<K>
{
  protected K[] refArray;
  protected int[] heap = IntArrays.EMPTY_ARRAY;
  protected int size;
  protected Comparator<? super K> field_69;
  
  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int capacity, Comparator<? super K> local_c)
  {
    if (capacity > 0) {
      this.heap = new int[capacity];
    }
    this.refArray = refArray;
    this.field_69 = local_c;
  }
  
  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int capacity)
  {
    this(refArray, capacity, null);
  }
  
  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, Comparator<? super K> local_c)
  {
    this(refArray, refArray.length, local_c);
  }
  
  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray)
  {
    this(refArray, refArray.length, null);
  }
  
  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] local_a, int size, Comparator<? super K> local_c)
  {
    this(refArray, 0, local_c);
    this.heap = local_a;
    this.size = size;
    ObjectSemiIndirectHeaps.makeHeap(refArray, local_a, size, local_c);
  }
  
  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] local_a, Comparator<? super K> local_c)
  {
    this(refArray, local_a, local_a.length, local_c);
  }
  
  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] local_a, int size)
  {
    this(refArray, local_a, size, null);
  }
  
  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] local_a)
  {
    this(refArray, local_a, local_a.length);
  }
  
  protected void ensureElement(int index)
  {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
    }
    if (index >= this.refArray.length) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is larger than or equal to reference array size (" + this.refArray.length + ")");
    }
  }
  
  public void enqueue(int local_x)
  {
    ensureElement(local_x);
    if (this.size == this.heap.length) {
      this.heap = IntArrays.grow(this.heap, this.size + 1);
    }
    this.heap[(this.size++)] = local_x;
    ObjectSemiIndirectHeaps.upHeap(this.refArray, this.heap, this.size, this.size - 1, this.field_69);
  }
  
  public int dequeue()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    int result = this.heap[0];
    this.heap[0] = this.heap[(--this.size)];
    if (this.size != 0) {
      ObjectSemiIndirectHeaps.downHeap(this.refArray, this.heap, this.size, 0, this.field_69);
    }
    return result;
  }
  
  public int first()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.heap[0];
  }
  
  public void changed()
  {
    ObjectSemiIndirectHeaps.downHeap(this.refArray, this.heap, this.size, 0, this.field_69);
  }
  
  public void allChanged()
  {
    ObjectSemiIndirectHeaps.makeHeap(this.refArray, this.heap, this.size, this.field_69);
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
  
  public Comparator<? super K> comparator()
  {
    return this.field_69;
  }
  
  public int front(int[] local_a)
  {
    return ObjectSemiIndirectHeaps.front(this.refArray, this.heap, this.size, local_a);
  }
  
  public String toString()
  {
    StringBuffer local_s = new StringBuffer();
    local_s.append("[");
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      if (local_i != 0) {
        local_s.append(", ");
      }
      local_s.append(this.refArray[this.heap[local_i]]);
    }
    local_s.append("]");
    return local_s.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectHeapSemiIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */