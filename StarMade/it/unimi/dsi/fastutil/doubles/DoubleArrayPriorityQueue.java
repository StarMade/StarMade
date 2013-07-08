package it.unimi.dsi.fastutil.doubles;

import java.util.NoSuchElementException;

public class DoubleArrayPriorityQueue
  extends AbstractDoublePriorityQueue
{
  protected double[] array = DoubleArrays.EMPTY_ARRAY;
  protected int size;
  protected DoubleComparator field_76;
  protected int firstIndex;
  protected boolean firstIndexValid;
  
  public DoubleArrayPriorityQueue(int capacity, DoubleComparator local_c)
  {
    if (capacity > 0) {
      this.array = new double[capacity];
    }
    this.field_76 = local_c;
  }
  
  public DoubleArrayPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public DoubleArrayPriorityQueue(DoubleComparator local_c)
  {
    this(0, local_c);
  }
  
  public DoubleArrayPriorityQueue()
  {
    this(0, null);
  }
  
  public DoubleArrayPriorityQueue(double[] local_a, int size, DoubleComparator local_c)
  {
    this(local_c);
    this.array = local_a;
    this.size = size;
  }
  
  public DoubleArrayPriorityQueue(double[] local_a, DoubleComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public DoubleArrayPriorityQueue(double[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public DoubleArrayPriorityQueue(double[] local_a)
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
    double first = this.array[firstIndex];
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
  
  public void enqueue(double local_x)
  {
    if (this.size == this.array.length) {
      this.array = DoubleArrays.grow(this.array, this.size + 1);
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
  
  public double dequeueDouble()
  {
    ensureNonEmpty();
    int first = findFirst();
    double result = this.array[first];
    System.arraycopy(this.array, first + 1, this.array, first, --this.size - first);
    this.firstIndexValid = false;
    return result;
  }
  
  public double firstDouble()
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
    this.array = DoubleArrays.trim(this.array, this.size);
  }
  
  public DoubleComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleArrayPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */