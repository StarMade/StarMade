package it.unimi.dsi.fastutil.floats;

import java.util.NoSuchElementException;

public class FloatArrayPriorityQueue
  extends AbstractFloatPriorityQueue
{
  protected float[] array = FloatArrays.EMPTY_ARRAY;
  protected int size;
  protected FloatComparator field_76;
  protected int firstIndex;
  protected boolean firstIndexValid;
  
  public FloatArrayPriorityQueue(int capacity, FloatComparator local_c)
  {
    if (capacity > 0) {
      this.array = new float[capacity];
    }
    this.field_76 = local_c;
  }
  
  public FloatArrayPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public FloatArrayPriorityQueue(FloatComparator local_c)
  {
    this(0, local_c);
  }
  
  public FloatArrayPriorityQueue()
  {
    this(0, null);
  }
  
  public FloatArrayPriorityQueue(float[] local_a, int size, FloatComparator local_c)
  {
    this(local_c);
    this.array = local_a;
    this.size = size;
  }
  
  public FloatArrayPriorityQueue(float[] local_a, FloatComparator local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public FloatArrayPriorityQueue(float[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public FloatArrayPriorityQueue(float[] local_a)
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
    float first = this.array[firstIndex];
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
  
  public void enqueue(float local_x)
  {
    if (this.size == this.array.length) {
      this.array = FloatArrays.grow(this.array, this.size + 1);
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
  
  public float dequeueFloat()
  {
    ensureNonEmpty();
    int first = findFirst();
    float result = this.array[first];
    System.arraycopy(this.array, first + 1, this.array, first, --this.size - first);
    this.firstIndexValid = false;
    return result;
  }
  
  public float firstFloat()
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
    this.array = FloatArrays.trim(this.array, this.size);
  }
  
  public FloatComparator comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatArrayPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */