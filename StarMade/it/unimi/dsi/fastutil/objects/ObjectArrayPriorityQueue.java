package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.AbstractPriorityQueue;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ObjectArrayPriorityQueue<K>
  extends AbstractPriorityQueue<K>
{
  protected K[] array = (Object[])ObjectArrays.EMPTY_ARRAY;
  protected int size;
  protected Comparator<? super K> field_76;
  protected int firstIndex;
  protected boolean firstIndexValid;
  
  public ObjectArrayPriorityQueue(int capacity, Comparator<? super K> local_c)
  {
    if (capacity > 0) {
      this.array = ((Object[])new Object[capacity]);
    }
    this.field_76 = local_c;
  }
  
  public ObjectArrayPriorityQueue(int capacity)
  {
    this(capacity, null);
  }
  
  public ObjectArrayPriorityQueue(Comparator<? super K> local_c)
  {
    this(0, local_c);
  }
  
  public ObjectArrayPriorityQueue()
  {
    this(0, null);
  }
  
  public ObjectArrayPriorityQueue(K[] local_a, int size, Comparator<? super K> local_c)
  {
    this(local_c);
    this.array = local_a;
    this.size = size;
  }
  
  public ObjectArrayPriorityQueue(K[] local_a, Comparator<? super K> local_c)
  {
    this(local_a, local_a.length, local_c);
  }
  
  public ObjectArrayPriorityQueue(K[] local_a, int size)
  {
    this(local_a, size, null);
  }
  
  public ObjectArrayPriorityQueue(K[] local_a)
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
    K first = this.array[firstIndex];
    if (this.field_76 == null) {
      while (local_i-- != 0) {
        if (((Comparable)this.array[local_i]).compareTo(first) < 0) {
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
  
  public void enqueue(K local_x)
  {
    if (this.size == this.array.length) {
      this.array = ObjectArrays.grow(this.array, this.size + 1);
    }
    if (this.firstIndexValid)
    {
      if (this.field_76 == null)
      {
        if (((Comparable)local_x).compareTo(this.array[this.firstIndex]) < 0) {
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
  
  public K dequeue()
  {
    ensureNonEmpty();
    int first = findFirst();
    K result = this.array[first];
    System.arraycopy(this.array, first + 1, this.array, first, --this.size - first);
    this.array[this.size] = null;
    this.firstIndexValid = false;
    return result;
  }
  
  public K first()
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
    ObjectArrays.fill(this.array, 0, this.size, null);
    this.size = 0;
    this.firstIndexValid = false;
  }
  
  public void trim()
  {
    this.array = ObjectArrays.trim(this.array, this.size);
  }
  
  public Comparator<? super K> comparator()
  {
    return this.field_76;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrayPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */