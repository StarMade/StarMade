package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectIterators;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractIntCollection
  extends AbstractCollection<Integer>
  implements IntCollection
{
  public int[] toArray(int[] local_a)
  {
    return toIntArray(local_a);
  }
  
  public int[] toIntArray()
  {
    return toIntArray(null);
  }
  
  public int[] toIntArray(int[] local_a)
  {
    if ((local_a == null) || (local_a.length < size())) {
      local_a = new int[size()];
    }
    IntIterators.unwrap(iterator(), local_a);
    return local_a;
  }
  
  public boolean addAll(IntCollection local_c)
  {
    boolean retVal = false;
    IntIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add(local_i.nextInt())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean containsAll(IntCollection local_c)
  {
    IntIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (!contains(local_i.nextInt())) {
        return false;
      }
    }
    return true;
  }
  
  public boolean retainAll(IntCollection local_c)
  {
    boolean retVal = false;
    int local_n = size();
    IntIterator local_i = iterator();
    while (local_n-- != 0) {
      if (!local_c.contains(local_i.nextInt()))
      {
        local_i.remove();
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean removeAll(IntCollection local_c)
  {
    boolean retVal = false;
    int local_n = local_c.size();
    IntIterator local_i = local_c.iterator();
    while (local_n-- != 0) {
      if (rem(local_i.nextInt())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public Object[] toArray()
  {
    Object[] local_a = new Object[size()];
    ObjectIterators.unwrap(iterator(), local_a);
    return local_a;
  }
  
  public <T> T[] toArray(T[] local_a)
  {
    if (local_a.length < size()) {
      local_a = (Object[])Array.newInstance(local_a.getClass().getComponentType(), size());
    }
    ObjectIterators.unwrap(iterator(), local_a);
    return local_a;
  }
  
  public boolean addAll(Collection<? extends Integer> local_c)
  {
    boolean retVal = false;
    Iterator<? extends Integer> local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add((Integer)local_i.next())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean add(int local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  public IntIterator intIterator()
  {
    return iterator();
  }
  
  public abstract IntIterator iterator();
  
  public boolean remove(Object local_ok)
  {
    return rem(((Integer)local_ok).intValue());
  }
  
  public boolean add(Integer local_o)
  {
    return add(local_o.intValue());
  }
  
  public boolean rem(Object local_o)
  {
    return rem(((Integer)local_o).intValue());
  }
  
  public boolean contains(Object local_o)
  {
    return contains(((Integer)local_o).intValue());
  }
  
  public boolean contains(int local_k)
  {
    IntIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextInt()) {
        return true;
      }
    }
    return false;
  }
  
  public boolean rem(int local_k)
  {
    IntIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextInt())
      {
        iterator.remove();
        return true;
      }
    }
    return false;
  }
  
  public boolean containsAll(Collection<?> local_c)
  {
    int local_n = local_c.size();
    Iterator<?> local_i = local_c.iterator();
    while (local_n-- != 0) {
      if (!contains(local_i.next())) {
        return false;
      }
    }
    return true;
  }
  
  public boolean retainAll(Collection<?> local_c)
  {
    boolean retVal = false;
    int local_n = size();
    Iterator<?> local_i = iterator();
    while (local_n-- != 0) {
      if (!local_c.contains(local_i.next()))
      {
        local_i.remove();
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean removeAll(Collection<?> local_c)
  {
    boolean retVal = false;
    int local_n = local_c.size();
    Iterator<?> local_i = local_c.iterator();
    while (local_n-- != 0) {
      if (remove(local_i.next())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    IntIterator local_i = iterator();
    int local_n = size();
    boolean first = true;
    local_s.append("{");
    while (local_n-- != 0)
    {
      if (first) {
        first = false;
      } else {
        local_s.append(", ");
      }
      int local_k = local_i.nextInt();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("}");
    return local_s.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */