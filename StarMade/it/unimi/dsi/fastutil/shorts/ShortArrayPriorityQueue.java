package it.unimi.dsi.fastutil.shorts;

import java.util.NoSuchElementException;

public class ShortArrayPriorityQueue
  extends AbstractShortPriorityQueue
{
  protected short[] array = ShortArrays.EMPTY_ARRAY;
  protected int size;
  protected ShortComparator field_76;
  protected int firstIndex;
  protected boolean firstIndexValid;
  
  public ShortArrayPriorityQueue(int capacity, ShortComparator local_c)
  {
    if (capacity > 0) {
      this.array = new short[capacity];
    }
    this.field_76 = local_c;
  }
  
  public ShortArrayPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public ShortArrayPriorityQueue(ShortComparator local_c)
  {
    this(0, local_c);
  }
  
  public ShortArrayPriorityQueue()
  {
    this(0, null);
  }
  
  public ShortArrayPriorityQueue(short[] local_a, int size, ShortComparator local_c)
  {
    this(local_c);
    this.array = local_a;
    this.size = size;
  }
  
  public ShortArrayPriorityQueue(short[] local_a, ShortComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public ShortArrayPriorityQueue(short[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public ShortArrayPriorityQueue(short[] local_a)
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
    short first = this.array[firstIndex];
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
  
  public void enqueue(short local_x)
  {
    if (this.size == this.array.length) {
      this.array = ShortArrays.grow(this.array, this.size + 1);
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
  
  public short dequeueShort()
  {
    ensureNonEmpty();
    int first = findFirst();
    short result = this.array[first];
    System.arraycopy(this.array, first + 1, this.array, first, --this.size - first);
    this.firstIndexValid = false;
    return result;
  }
  
  public short firstShort()
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
    this.array = ShortArrays.trim(this.array, this.size);
  }
  
  public ShortComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortArrayPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */