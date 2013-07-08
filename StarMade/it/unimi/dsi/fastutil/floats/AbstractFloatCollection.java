package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectIterators;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractFloatCollection
  extends AbstractCollection<Float>
  implements FloatCollection
{
  public float[] toArray(float[] local_a)
  {
    return toFloatArray(local_a);
  }
  
  public float[] toFloatArray()
  {
    return toFloatArray(null);
  }
  
  public float[] toFloatArray(float[] local_a)
  {
    if ((local_a == null) || (local_a.length < size())) {
      local_a = new float[size()];
    }
    FloatIterators.unwrap(iterator(), local_a);
    return local_a;
  }
  
  public boolean addAll(FloatCollection local_c)
  {
    boolean retVal = false;
    FloatIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add(local_i.nextFloat())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean containsAll(FloatCollection local_c)
  {
    FloatIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (!contains(local_i.nextFloat())) {
        return false;
      }
    }
    return true;
  }
  
  public boolean retainAll(FloatCollection local_c)
  {
    boolean retVal = false;
    int local_n = size();
    FloatIterator local_i = iterator();
    while (local_n-- != 0) {
      if (!local_c.contains(local_i.nextFloat()))
      {
        local_i.remove();
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean removeAll(FloatCollection local_c)
  {
    boolean retVal = false;
    int local_n = local_c.size();
    FloatIterator local_i = local_c.iterator();
    while (local_n-- != 0) {
      if (rem(local_i.nextFloat())) {
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
  
  public boolean addAll(Collection<? extends Float> local_c)
  {
    boolean retVal = false;
    Iterator<? extends Float> local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add((Float)local_i.next())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean add(float local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  public FloatIterator floatIterator()
  {
    return iterator();
  }
  
  public abstract FloatIterator iterator();
  
  public boolean remove(Object local_ok)
  {
    return rem(((Float)local_ok).floatValue());
  }
  
  public boolean add(Float local_o)
  {
    return add(local_o.floatValue());
  }
  
  public boolean rem(Object local_o)
  {
    return rem(((Float)local_o).floatValue());
  }
  
  public boolean contains(Object local_o)
  {
    return contains(((Float)local_o).floatValue());
  }
  
  public boolean contains(float local_k)
  {
    FloatIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextFloat()) {
        return true;
      }
    }
    return false;
  }
  
  public boolean rem(float local_k)
  {
    FloatIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextFloat())
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
    FloatIterator local_i = iterator();
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
      float local_k = local_i.nextFloat();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("}");
    return local_s.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */