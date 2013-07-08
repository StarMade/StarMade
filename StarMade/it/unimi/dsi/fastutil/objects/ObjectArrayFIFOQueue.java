package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.AbstractPriorityQueue;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ObjectArrayFIFOQueue<K>
  extends AbstractPriorityQueue<K>
{
  public static final int INITIAL_CAPACITY = 16;
  protected K[] array = (Object[])ObjectArrays.EMPTY_ARRAY;
  protected int length;
  protected int start;
  protected int end;
  
  public ObjectArrayFIFOQueue(int capacity)
  {
    if (capacity > 0) {
      this.array = ((Object[])new Object[capacity]);
    }
    this.length = this.array.length;
  }
  
  public ObjectArrayFIFOQueue()
  {
    this(16);
  }
  
  public Comparator<? super K> comparator()
  {
    return null;
  }
  
  public K dequeue()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    K local_t = this.array[this.start];
    this.array[this.start] = null;
    if (++this.start == this.length) {
      this.start = 0;
    }
    return local_t;
  }
  
  public K dequeueLast()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    if (this.end == 0) {
      this.end = this.length;
    }
    K local_t = this.array[(--this.end)];
    this.array[this.end] = null;
    return local_t;
  }
  
  private final void expand()
  {
    K[] newArray = ObjectArrays.grow(this.array, this.length + 1, 0);
    System.arraycopy(this.array, this.start, newArray, 0, this.length - this.start);
    System.arraycopy(this.array, 0, newArray, this.length - this.start, this.end);
    this.start = 0;
    this.end = this.length;
    this.length = (this.array = newArray).length;
  }
  
  public void enqueue(K local_x)
  {
    this.array[(this.end++)] = local_x;
    if (this.end == this.length) {
      this.end = 0;
    }
    if (this.end == this.start) {
      expand();
    }
  }
  
  public void enqueueFirst(K local_x)
  {
    if (this.start == 0) {
      this.start = this.length;
    }
    this.array[(--this.start)] = local_x;
    if (this.end == this.start) {
      expand();
    }
  }
  
  public K first()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    return this.array[this.start];
  }
  
  public K last()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    return this.array[(this.end - 1)];
  }
  
  public void clear()
  {
    if (this.start <= this.end)
    {
      ObjectArrays.fill(this.array, this.start, this.end, null);
    }
    else
    {
      ObjectArrays.fill(this.array, this.start, this.length, null);
      ObjectArrays.fill(this.array, 0, this.end, null);
    }
    this.start = (this.end = 0);
  }
  
  public void trim()
  {
    int size = size();
    K[] newArray = (Object[])new Object[size + 1];
    if (this.start <= this.end)
    {
      System.arraycopy(this.array, this.start, newArray, 0, this.end - this.start);
    }
    else
    {
      System.arraycopy(this.array, this.start, newArray, 0, this.length - this.start);
      System.arraycopy(this.array, 0, newArray, this.length - this.start, this.end);
    }
    this.start = 0;
    this.length = ((this.end = size) + 1);
    this.array = newArray;
  }
  
  public int size()
  {
    int apparentLength = this.end - this.start;
    return apparentLength >= 0 ? apparentLength : this.length + apparentLength;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */