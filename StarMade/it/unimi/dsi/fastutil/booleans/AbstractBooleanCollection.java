package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.objects.ObjectIterators;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractBooleanCollection
  extends AbstractCollection<Boolean>
  implements BooleanCollection
{
  public boolean[] toArray(boolean[] local_a)
  {
    return toBooleanArray(local_a);
  }
  
  public boolean[] toBooleanArray()
  {
    return toBooleanArray(null);
  }
  
  public boolean[] toBooleanArray(boolean[] local_a)
  {
    if ((local_a == null) || (local_a.length < size())) {
      local_a = new boolean[size()];
    }
    BooleanIterators.unwrap(iterator(), local_a);
    return local_a;
  }
  
  public boolean addAll(BooleanCollection local_c)
  {
    boolean retVal = false;
    BooleanIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add(local_i.nextBoolean())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean containsAll(BooleanCollection local_c)
  {
    BooleanIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (!contains(local_i.nextBoolean())) {
        return false;
      }
    }
    return true;
  }
  
  public boolean retainAll(BooleanCollection local_c)
  {
    boolean retVal = false;
    int local_n = size();
    BooleanIterator local_i = iterator();
    while (local_n-- != 0) {
      if (!local_c.contains(local_i.nextBoolean()))
      {
        local_i.remove();
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean removeAll(BooleanCollection local_c)
  {
    boolean retVal = false;
    int local_n = local_c.size();
    BooleanIterator local_i = local_c.iterator();
    while (local_n-- != 0) {
      if (rem(local_i.nextBoolean())) {
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
  
  public boolean addAll(Collection<? extends Boolean> local_c)
  {
    boolean retVal = false;
    Iterator<? extends Boolean> local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add((Boolean)local_i.next())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean add(boolean local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  public BooleanIterator booleanIterator()
  {
    return iterator();
  }
  
  public abstract BooleanIterator iterator();
  
  public boolean remove(Object local_ok)
  {
    return rem(((Boolean)local_ok).booleanValue());
  }
  
  public boolean add(Boolean local_o)
  {
    return add(local_o.booleanValue());
  }
  
  public boolean rem(Object local_o)
  {
    return rem(((Boolean)local_o).booleanValue());
  }
  
  public boolean contains(Object local_o)
  {
    return contains(((Boolean)local_o).booleanValue());
  }
  
  public boolean contains(boolean local_k)
  {
    BooleanIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextBoolean()) {
        return true;
      }
    }
    return false;
  }
  
  public boolean rem(boolean local_k)
  {
    BooleanIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextBoolean())
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
    BooleanIterator local_i = iterator();
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
      boolean local_k = local_i.nextBoolean();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("}");
    return local_s.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */