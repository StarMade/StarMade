package it.unimi.dsi.fastutil.longs;

import java.util.NoSuchElementException;

public class LongArrayFIFOQueue
  extends AbstractLongPriorityQueue
{
  public static final int INITIAL_CAPACITY = 16;
  protected long[] array = LongArrays.EMPTY_ARRAY;
  protected int length;
  protected int start;
  protected int end;
  
  public LongArrayFIFOQueue(int capacity)
  {
    if (capacity > 0) {
      this.array = new long[capacity];
    }
    this.length = this.array.length;
  }
  
  public LongArrayFIFOQueue()
  {
    this(16);
  }
  
  public LongComparator comparator()
  {
    return null;
  }
  
  public long dequeueLong()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    long local_t = this.array[this.start];
    if (++this.start == this.length) {
      this.start = 0;
    }
    return local_t;
  }
  
  public long dequeueLastLong()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    if (this.end == 0) {
      this.end = this.length;
    }
    long local_t = this.array[(--this.end)];
    return local_t;
  }
  
  private final void expand()
  {
    long[] newArray = LongArrays.grow(this.array, this.length + 1, 0);
    System.arraycopy(this.array, this.start, newArray, 0, this.length - this.start);
    System.arraycopy(this.array, 0, newArray, this.length - this.start, this.end);
    this.start = 0;
    this.end = this.length;
    this.length = (this.array = newArray).length;
  }
  
  public void enqueue(long local_x)
  {
    this.array[(this.end++)] = local_x;
    if (this.end == this.length) {
      this.end = 0;
    }
    if (this.end == this.start) {
      expand();
    }
  }
  
  public void enqueueFirst(long local_x)
  {
    if (this.start == 0) {
      this.start = this.length;
    }
    this.array[(--this.start)] = local_x;
    if (this.end == this.start) {
      expand();
    }
  }
  
  public long firstLong()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    return this.array[this.start];
  }
  
  public long lastLong()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    return this.array[(this.end - 1)];
  }
  
  public void clear()
  {
    this.start = (this.end = 0);
  }
  
  public void trim()
  {
    int size = size();
    long[] newArray = new long[size + 1];
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
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */