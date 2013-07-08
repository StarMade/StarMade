package it.unimi.dsi.fastutil.longs;

import java.util.NoSuchElementException;

public class LongArrayPriorityQueue
  extends AbstractLongPriorityQueue
{
  protected long[] array = LongArrays.EMPTY_ARRAY;
  protected int size;
  protected LongComparator field_76;
  protected int firstIndex;
  protected boolean firstIndexValid;
  
  public LongArrayPriorityQueue(int capacity, LongComparator local_c)
  {
    if (capacity > 0) {
      this.array = new long[capacity];
    }
    this.field_76 = local_c;
  }
  
  public LongArrayPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public LongArrayPriorityQueue(LongComparator local_c)
  {
    this(0, local_c);
  }
  
  public LongArrayPriorityQueue()
  {
    this(0, null);
  }
  
  public LongArrayPriorityQueue(long[] local_a, int size, LongComparator local_c)
  {
    this(local_c);
    this.array = local_a;
    this.size = size;
  }
  
  public LongArrayPriorityQueue(long[] local_a, LongComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public LongArrayPriorityQueue(long[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public LongArrayPriorityQueue(long[] local_a)
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
    long first = this.array[firstIndex];
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
  
  public void enqueue(long local_x)
  {
    if (this.size == this.array.length) {
      this.array = LongArrays.grow(this.array, this.size + 1);
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
  
  public long dequeueLong()
  {
    ensureNonEmpty();
    int first = findFirst();
    long result = this.array[first];
    System.arraycopy(this.array, first + 1, this.array, first, --this.size - first);
    this.firstIndexValid = false;
    return result;
  }
  
  public long firstLong()
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
    this.array = LongArrays.trim(this.array, this.size);
  }
  
  public LongComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongArrayPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */