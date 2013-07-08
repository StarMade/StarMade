package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectIterators;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractLongCollection
  extends AbstractCollection<Long>
  implements LongCollection
{
  public long[] toArray(long[] local_a)
  {
    return toLongArray(local_a);
  }
  
  public long[] toLongArray()
  {
    return toLongArray(null);
  }
  
  public long[] toLongArray(long[] local_a)
  {
    if ((local_a == null) || (local_a.length < size())) {
      local_a = new long[size()];
    }
    LongIterators.unwrap(iterator(), local_a);
    return local_a;
  }
  
  public boolean addAll(LongCollection local_c)
  {
    boolean retVal = false;
    LongIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add(local_i.nextLong())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean containsAll(LongCollection local_c)
  {
    LongIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (!contains(local_i.nextLong())) {
        return false;
      }
    }
    return true;
  }
  
  public boolean retainAll(LongCollection local_c)
  {
    boolean retVal = false;
    int local_n = size();
    LongIterator local_i = iterator();
    while (local_n-- != 0) {
      if (!local_c.contains(local_i.nextLong()))
      {
        local_i.remove();
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean removeAll(LongCollection local_c)
  {
    boolean retVal = false;
    int local_n = local_c.size();
    LongIterator local_i = local_c.iterator();
    while (local_n-- != 0) {
      if (rem(local_i.nextLong())) {
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
  
  public boolean addAll(Collection<? extends Long> local_c)
  {
    boolean retVal = false;
    Iterator<? extends Long> local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add((Long)local_i.next())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean add(long local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  public LongIterator longIterator()
  {
    return iterator();
  }
  
  public abstract LongIterator iterator();
  
  public boolean remove(Object local_ok)
  {
    return rem(((Long)local_ok).longValue());
  }
  
  public boolean add(Long local_o)
  {
    return add(local_o.longValue());
  }
  
  public boolean rem(Object local_o)
  {
    return rem(((Long)local_o).longValue());
  }
  
  public boolean contains(Object local_o)
  {
    return contains(((Long)local_o).longValue());
  }
  
  public boolean contains(long local_k)
  {
    LongIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextLong()) {
        return true;
      }
    }
    return false;
  }
  
  public boolean rem(long local_k)
  {
    LongIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextLong())
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
    LongIterator local_i = iterator();
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
      long local_k = local_i.nextLong();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("}");
    return local_s.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */