package it.unimi.dsi.fastutil.chars;

import java.util.NoSuchElementException;

public class CharHeapPriorityQueue
  extends AbstractCharPriorityQueue
{
  protected char[] heap = CharArrays.EMPTY_ARRAY;
  protected int size;
  protected CharComparator field_76;
  
  public CharHeapPriorityQueue(int capacity, CharComparator local_c)
  {
    if (capacity > 0) {
      this.heap = new char[capacity];
    }
    this.field_76 = local_c;
  }
  
  public CharHeapPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public CharHeapPriorityQueue(CharComparator local_c)
  {
    this(0, local_c);
  }
  
  public CharHeapPriorityQueue()
  {
    this(0, null);
  }
  
  public CharHeapPriorityQueue(char[] local_a, int size, CharComparator local_c)
  {
    this(local_c);
    this.heap = local_a;
    this.size = size;
    CharHeaps.makeHeap(local_a, size, local_c);
  }
  
  public CharHeapPriorityQueue(char[] local_a, CharComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public CharHeapPriorityQueue(char[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public CharHeapPriorityQueue(char[] local_a)
  {
    this(local_a, local_a.length);
  }
  
  public void enqueue(char local_x)
  {
    if (this.size == this.heap.length) {
      this.heap = CharArrays.grow(this.heap, this.size + 1);
    }
    this.heap[(this.size++)] = local_x;
    CharHeaps.upHeap(this.heap, this.size, this.size - 1, this.field_76);
  }
  
  public char dequeueChar()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    char result = this.heap[0];
    this.heap[0] = this.heap[(--this.size)];
    if (this.size != 0) {
      CharHeaps.downHeap(this.heap, this.size, 0, this.field_76);
    }
    return result;
  }
  
  public char firstChar()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.heap[0];
  }
  
  public void changed()
  {
    CharHeaps.downHeap(this.heap, this.size, 0, this.field_76);
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
    this.heap = CharArrays.trim(this.heap, this.size);
  }
  
  public CharComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharHeapPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */