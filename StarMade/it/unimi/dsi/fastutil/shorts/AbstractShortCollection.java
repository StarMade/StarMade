package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectIterators;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractShortCollection
  extends AbstractCollection<Short>
  implements ShortCollection
{
  public short[] toArray(short[] local_a)
  {
    return toShortArray(local_a);
  }
  
  public short[] toShortArray()
  {
    return toShortArray(null);
  }
  
  public short[] toShortArray(short[] local_a)
  {
    if ((local_a == null) || (local_a.length < size())) {
      local_a = new short[size()];
    }
    ShortIterators.unwrap(iterator(), local_a);
    return local_a;
  }
  
  public boolean addAll(ShortCollection local_c)
  {
    boolean retVal = false;
    ShortIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add(local_i.nextShort())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean containsAll(ShortCollection local_c)
  {
    ShortIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (!contains(local_i.nextShort())) {
        return false;
      }
    }
    return true;
  }
  
  public boolean retainAll(ShortCollection local_c)
  {
    boolean retVal = false;
    int local_n = size();
    ShortIterator local_i = iterator();
    while (local_n-- != 0) {
      if (!local_c.contains(local_i.nextShort()))
      {
        local_i.remove();
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean removeAll(ShortCollection local_c)
  {
    boolean retVal = false;
    int local_n = local_c.size();
    ShortIterator local_i = local_c.iterator();
    while (local_n-- != 0) {
      if (rem(local_i.nextShort())) {
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
  
  public boolean addAll(Collection<? extends Short> local_c)
  {
    boolean retVal = false;
    Iterator<? extends Short> local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add((Short)local_i.next())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean add(short local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  public ShortIterator shortIterator()
  {
    return iterator();
  }
  
  public abstract ShortIterator iterator();
  
  public boolean remove(Object local_ok)
  {
    return rem(((Short)local_ok).shortValue());
  }
  
  public boolean add(Short local_o)
  {
    return add(local_o.shortValue());
  }
  
  public boolean rem(Object local_o)
  {
    return rem(((Short)local_o).shortValue());
  }
  
  public boolean contains(Object local_o)
  {
    return contains(((Short)local_o).shortValue());
  }
  
  public boolean contains(short local_k)
  {
    ShortIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextShort()) {
        return true;
      }
    }
    return false;
  }
  
  public boolean rem(short local_k)
  {
    ShortIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextShort())
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
    ShortIterator local_i = iterator();
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
      short local_k = local_i.nextShort();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("}");
    return local_s.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */