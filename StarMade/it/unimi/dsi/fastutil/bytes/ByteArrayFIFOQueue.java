package it.unimi.dsi.fastutil.bytes;

import java.util.NoSuchElementException;

public class ByteArrayFIFOQueue
  extends AbstractBytePriorityQueue
{
  public static final int INITIAL_CAPACITY = 16;
  protected byte[] array = ByteArrays.EMPTY_ARRAY;
  protected int length;
  protected int start;
  protected int end;
  
  public ByteArrayFIFOQueue(int capacity)
  {
    if (capacity > 0) {
      this.array = new byte[capacity];
    }
    this.length = this.array.length;
  }
  
  public ByteArrayFIFOQueue()
  {
    this(16);
  }
  
  public ByteComparator comparator()
  {
    return null;
  }
  
  public byte dequeueByte()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    byte local_t = this.array[this.start];
    if (++this.start == this.length) {
      this.start = 0;
    }
    return local_t;
  }
  
  public byte dequeueLastByte()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    if (this.end == 0) {
      this.end = this.length;
    }
    byte local_t = this.array[(--this.end)];
    return local_t;
  }
  
  private final void expand()
  {
    byte[] newArray = ByteArrays.grow(this.array, this.length + 1, 0);
    System.arraycopy(this.array, this.start, newArray, 0, this.length - this.start);
    System.arraycopy(this.array, 0, newArray, this.length - this.start, this.end);
    this.start = 0;
    this.end = this.length;
    this.length = (this.array = newArray).length;
  }
  
  public void enqueue(byte local_x)
  {
    this.array[(this.end++)] = local_x;
    if (this.end == this.length) {
      this.end = 0;
    }
    if (this.end == this.start) {
      expand();
    }
  }
  
  public void enqueueFirst(byte local_x)
  {
    if (this.start == 0) {
      this.start = this.length;
    }
    this.array[(--this.start)] = local_x;
    if (this.end == this.start) {
      expand();
    }
  }
  
  public byte firstByte()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    return this.array[this.start];
  }
  
  public byte lastByte()
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
    byte[] newArray = new byte[size + 1];
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteArrayFIFOQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */