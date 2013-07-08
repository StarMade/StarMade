package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.AbstractIndirectPriorityQueue;
import it.unimi.dsi.fastutil.ints.IntArrays;
import java.util.NoSuchElementException;

public class FloatArrayIndirectPriorityQueue
  extends AbstractIndirectPriorityQueue<Float>
  implements FloatIndirectPriorityQueue
{
  protected float[] refArray;
  protected int[] array = IntArrays.EMPTY_ARRAY;
  protected int size;
  protected FloatComparator field_69;
  protected int firstIndex;
  protected boolean firstIndexValid;
  
  public FloatArrayIndirectPriorityQueue(float[] refArray, int capacity, FloatComparator local_c)
  {
    if (capacity > 0) {
      this.array = new int[capacity];
    }
    this.refArray = refArray;
    this.field_69 = local_c;
  }
  
  public FloatArrayIndirectPriorityQueue(float[] refArray, int capacity)
  {
    this(refArray, capacity, null);
  }
  
  public FloatArrayIndirectPriorityQueue(float[] refArray, FloatComparator local_c)
  {
    this(refArray, refArray.length, local_c);
  }
  
  public FloatArrayIndirectPriorityQueue(float[] refArray)
  {
    this(refArray, refArray.length, null);
  }
  
  public FloatArrayIndirectPriorityQueue(float[] refArray, int[] local_a, int size, FloatComparator local_c)
  {
    this(refArray, 0, local_c);
    this.array = local_a;
    this.size = size;
  }
  
  public FloatArrayIndirectPriorityQueue(float[] refArray, int[] local_a, FloatComparator local_c)
  {
    this(refArray, local_a, local_a.length, local_c);
  }
  
  public FloatArrayIndirectPriorityQueue(float[] refArray, int[] local_a, int size)
  {
    this(refArray, local_a, size, null);
  }
  
  public FloatArrayIndirectPriorityQueue(float[] refArray, int[] local_a)
  {
    this(refArray, local_a, local_a.length);
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
    float first = this.refArray[this.array[firstIndex]];
    if (this.field_69 == null) {
      while (local_i-- != 0) {
        if (this.refArray[this.array[local_i]] < first) {
          first = this.refArray[this.array[(firstIndex = local_i)]];
        }
      }
    }
    while (local_i-- != 0) {
      if (this.field_69.compare(this.refArray[this.array[local_i]], first) < 0) {
        first = this.refArray[this.array[(firstIndex = local_i)]];
      }
    }
    return this.firstIndex = firstIndex;
  }
  
  private int findLast()
  {
    int local_i = this.size;
    local_i--;
    int lastIndex = local_i;
    float last = this.refArray[this.array[lastIndex]];
    if (this.field_69 == null) {
      while (local_i-- != 0) {
        if (last < this.refArray[this.array[local_i]]) {
          last = this.refArray[this.array[(lastIndex = local_i)]];
        }
      }
    }
    while (local_i-- != 0) {
      if (this.field_69.compare(last, this.refArray[this.array[local_i]]) < 0) {
        last = this.refArray[this.array[(lastIndex = local_i)]];
      }
    }
    return lastIndex;
  }
  
  protected final void ensureNonEmpty()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
  }
  
  protected void ensureElement(int index)
  {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
    }
    if (index >= this.refArray.length) {
      throw new IndexOutOfBoundsException("Index (" + index + ") is larger than or equal to reference array size (" + this.refArray.length + ")");
    }
  }
  
  public void enqueue(int local_x)
  {
    ensureElement(local_x);
    if (this.size == this.array.length) {
      this.array = IntArrays.grow(this.array, this.size + 1);
    }
    if (this.firstIndexValid)
    {
      if (this.field_69 == null)
      {
        if (this.refArray[local_x] < this.refArray[this.array[this.firstIndex]]) {
          this.firstIndex = this.size;
        }
      }
      else if (this.field_69.compare(this.refArray[local_x], this.refArray[this.array[this.firstIndex]]) < 0) {
        this.firstIndex = this.size;
      }
    }
    else {
      this.firstIndexValid = false;
    }
    this.array[(this.size++)] = local_x;
  }
  
  public int dequeue()
  {
    ensureNonEmpty();
    int firstIndex = findFirst();
    int result = this.array[firstIndex];
    if (--this.size != 0) {
      System.arraycopy(this.array, firstIndex + 1, this.array, firstIndex, this.size - firstIndex);
    }
    this.firstIndexValid = false;
    return result;
  }
  
  public int first()
  {
    ensureNonEmpty();
    return this.array[findFirst()];
  }
  
  public int last()
  {
    ensureNonEmpty();
    return this.array[findLast()];
  }
  
  public void changed()
  {
    ensureNonEmpty();
    this.firstIndexValid = false;
  }
  
  public void changed(int index)
  {
    ensureElement(index);
    if (index == this.firstIndex) {
      this.firstIndexValid = false;
    }
  }
  
  public void allChanged()
  {
    this.firstIndexValid = false;
  }
  
  public boolean remove(int index)
  {
    ensureElement(index);
    int[] local_a = this.array;
    int local_i = this.size;
    while (local_i-- != 0) {
      if (local_a[local_i] == index) {
        break;
      }
    }
    if (local_i < 0) {
      return false;
    }
    this.firstIndexValid = false;
    if (--this.size != 0) {
      System.arraycopy(local_a, local_i + 1, local_a, local_i, this.size - local_i);
    }
    return true;
  }
  
  public int front(int[] local_a)
  {
    float top = this.refArray[this.array[findFirst()]];
    int local_i = this.size;
    int local_c = 0;
    while (local_i-- != 0) {
      if (top == this.refArray[this.array[local_i]]) {
        local_a[(local_c++)] = this.array[local_i];
      }
    }
    return local_c;
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
    this.array = IntArrays.trim(this.array, this.size);
  }
  
  public FloatComparator comparator()
  {
    return this.field_69;
  }
  
  public String toString()
  {
    StringBuffer local_s = new StringBuffer();
    local_s.append("[");
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      if (local_i != 0) {
        local_s.append(", ");
      }
      local_s.append(this.refArray[this.array[local_i]]);
    }
    local_s.append("]");
    return local_s.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatArrayIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */