package it.unimi.dsi.fastutil.objects;

import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractReferenceCollection<K>
  extends AbstractCollection<K>
  implements ReferenceCollection<K>
{
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
  
  public boolean addAll(Collection<? extends K> local_c)
  {
    boolean retVal = false;
    Iterator<? extends K> local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add(local_i.next())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean add(K local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  public ObjectIterator<K> objectIterator()
  {
    return iterator();
  }
  
  public abstract ObjectIterator<K> iterator();
  
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
    ObjectIterator<K> local_i = iterator();
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
      Object local_k = local_i.next();
      if (this == local_k) {
        local_s.append("(this collection)");
      } else {
        local_s.append(String.valueOf(local_k));
      }
    }
    local_s.append("}");
    return local_s.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReferenceCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */