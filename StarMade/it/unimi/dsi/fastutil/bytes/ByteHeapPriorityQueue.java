package it.unimi.dsi.fastutil.bytes;

import java.util.NoSuchElementException;

public class ByteHeapPriorityQueue
  extends AbstractBytePriorityQueue
{
  protected byte[] heap = ByteArrays.EMPTY_ARRAY;
  protected int size;
  protected ByteComparator field_76;
  
  public ByteHeapPriorityQueue(int capacity, ByteComparator local_c)
  {
    if (capacity > 0) {
      this.heap = new byte[capacity];
    }
    this.field_76 = local_c;
  }
  
  public ByteHeapPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public ByteHeapPriorityQueue(ByteComparator local_c)
  {
    this(0, local_c);
  }
  
  public ByteHeapPriorityQueue()
  {
    this(0, null);
  }
  
  public ByteHeapPriorityQueue(byte[] local_a, int size, ByteComparator local_c)
  {
    this(local_c);
    this.heap = local_a;
    this.size = size;
    ByteHeaps.makeHeap(local_a, size, local_c);
  }
  
  public ByteHeapPriorityQueue(byte[] local_a, ByteComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public ByteHeapPriorityQueue(byte[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public ByteHeapPriorityQueue(byte[] local_a)
  {
    this(local_a, local_a.length);
  }
  
  public void enqueue(byte local_x)
  {
    if (this.size == this.heap.length) {
      this.heap = ByteArrays.grow(this.heap, this.size + 1);
    }
    this.heap[(this.size++)] = local_x;
    ByteHeaps.upHeap(this.heap, this.size, this.size - 1, this.field_76);
  }
  
  public byte dequeueByte()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    byte result = this.heap[0];
    this.heap[0] = this.heap[(--this.size)];
    if (this.size != 0) {
      ByteHeaps.downHeap(this.heap, this.size, 0, this.field_76);
    }
    return result;
  }
  
  public byte firstByte()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.heap[0];
  }
  
  public void changed()
  {
    ByteHeaps.downHeap(this.heap, this.size, 0, this.field_76);
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
    this.heap = ByteArrays.trim(this.heap, this.size);
  }
  
  public ByteComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteHeapPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */