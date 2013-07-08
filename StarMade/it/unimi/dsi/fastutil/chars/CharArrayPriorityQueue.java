package it.unimi.dsi.fastutil.chars;

import java.util.NoSuchElementException;

public class CharArrayPriorityQueue
  extends AbstractCharPriorityQueue
{
  protected char[] array = CharArrays.EMPTY_ARRAY;
  protected int size;
  protected CharComparator field_76;
  protected int firstIndex;
  protected boolean firstIndexValid;
  
  public CharArrayPriorityQueue(int capacity, CharComparator local_c)
  {
    if (capacity > 0) {
      this.array = new char[capacity];
    }
    this.field_76 = local_c;
  }
  
  public CharArrayPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public CharArrayPriorityQueue(CharComparator local_c)
  {
    this(0, local_c);
  }
  
  public CharArrayPriorityQueue()
  {
    this(0, null);
  }
  
  public CharArrayPriorityQueue(char[] local_a, int size, CharComparator local_c)
  {
    this(local_c);
    this.array = local_a;
    this.size = size;
  }
  
  public CharArrayPriorityQueue(char[] local_a, CharComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public CharArrayPriorityQueue(char[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public CharArrayPriorityQueue(char[] local_a)
  {
    this(local_a, local_a.length);
  }
  
  private int findFirst()
  {
    if (this.firstIndexValid) {
      return this.firstIndex;
    }
    this.firstIndexValid = true;
    int local_i = this.size;
    local_i--;
    int firstIndex = local_i;
    char first = this.array[firstIndex];
    if (this.field_76 == null) {
      while (local_i-- != 0) {
        if (this.array[local_i] < first) {
          first = this.array[(firstIndex = local_i)];
        }
      }
    }
    while (local_i-- != 0) {
      if (this.field_76.compare(this.array[local_i], first) < 0) {
        first = this.array[(firstIndex = local_i)];
      }
    }
    return this.firstIndex = firstIndex;
  }
  
  private void ensureNonEmpty()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
  }
  
  public void enqueue(char local_x)
  {
    if (this.size == this.array.length) {
      this.array = CharArrays.grow(this.array, this.size + 1);
    }
    if (this.firstIndexValid)
    {
      if (this.field_76 == null)
      {
        if (local_x < this.array[this.firstIndex]) {
          this.firstIndex = this.size;
        }
      }
      else if (this.field_76.compare(local_x, this.array[this.firstIndex]) < 0) {
        this.firstIndex = this.size;
      }
    }
    else {
      this.firstIndexValid = false;
    }
    this.array[(this.size++)] = local_x;
  }
  
  public char dequeueChar()
  {
    ensureNonEmpty();
    int first = findFirst();
    char result = this.array[first];
    System.arraycopy(this.array, first + 1, this.array, first, --this.size - first);
    this.firstIndexValid = false;
    return result;
  }
  
  public char firstChar()
  {
    ensureNonEmpty();
    return this.array[findFirst()];
  }
  
  public void changed()
  {
    ensureNonEmpty();
    this.firstIndexValid = false;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public void clear()
  {
    this.size = 0;
    this.firstIndexValid = false;
  }
  
  public void trim()
  {
    this.array = CharArrays.trim(this.array, this.size);
  }
  
  public CharComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharArrayPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */