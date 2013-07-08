package it.unimi.dsi.fastutil.chars;

import java.util.NoSuchElementException;

public class CharArrayFIFOQueue
  extends AbstractCharPriorityQueue
{
  public static final int INITIAL_CAPACITY = 16;
  protected char[] array = CharArrays.EMPTY_ARRAY;
  protected int length;
  protected int start;
  protected int end;
  
  public CharArrayFIFOQueue(int capacity)
  {
    if (capacity > 0) {
      this.array = new char[capacity];
    }
    this.length = this.array.length;
  }
  
  public CharArrayFIFOQueue()
  {
    this(16);
  }
  
  public CharComparator comparator()
  {
    return null;
  }
  
  public char dequeueChar()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    char local_t = this.array[this.start];
    if (++this.start == this.length) {
      this.start = 0;
    }
    return local_t;
  }
  
  public char dequeueLastChar()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    if (this.end == 0) {
      this.end = this.length;
    }
    char local_t = this.array[(--this.end)];
    return local_t;
  }
  
  private final void expand()
  {
    char[] newArray = CharArrays.grow(this.array, this.length + 1, 0);
    System.arraycopy(this.array, this.start, newArray, 0, this.length - this.start);
    System.arraycopy(this.array, 0, newArray, this.length - this.start, this.end);
    this.start = 0;
    this.end = this.length;
    this.length = (this.array = newArray).length;
  }
  
  public void enqueue(char local_x)
  {
    this.array[(this.end++)] = local_x;
    if (this.end == this.length) {
      this.end = 0;
    }
    if (this.end == this.start) {
      expand();
    }
  }
  
  public void enqueueFirst(char local_x)
  {
    if (this.start == 0) {
      this.start = this.length;
    }
    this.array[(--this.start)] = local_x;
    if (this.end == this.start) {
      expand();
    }
  }
  
  public char firstChar()
  {
    if (this.start == this.end) {
      throw new NoSuchElementException();
    }
    return this.array[this.start];
  }
  
  public char lastChar()
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
    char[] newArray = new char[size + 1];
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
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharArrayFIFOQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */