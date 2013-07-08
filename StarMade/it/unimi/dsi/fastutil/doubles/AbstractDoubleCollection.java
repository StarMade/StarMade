package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectIterators;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractDoubleCollection
  extends AbstractCollection<Double>
  implements DoubleCollection
{
  public double[] toArray(double[] local_a)
  {
    return toDoubleArray(local_a);
  }
  
  public double[] toDoubleArray()
  {
    return toDoubleArray(null);
  }
  
  public double[] toDoubleArray(double[] local_a)
  {
    if ((local_a == null) || (local_a.length < size())) {
      local_a = new double[size()];
    }
    DoubleIterators.unwrap(iterator(), local_a);
    return local_a;
  }
  
  public boolean addAll(DoubleCollection local_c)
  {
    boolean retVal = false;
    DoubleIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add(local_i.nextDouble())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean containsAll(DoubleCollection local_c)
  {
    DoubleIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (!contains(local_i.nextDouble())) {
        return false;
      }
    }
    return true;
  }
  
  public boolean retainAll(DoubleCollection local_c)
  {
    boolean retVal = false;
    int local_n = size();
    DoubleIterator local_i = iterator();
    while (local_n-- != 0) {
      if (!local_c.contains(local_i.nextDouble()))
      {
        local_i.remove();
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean removeAll(DoubleCollection local_c)
  {
    boolean retVal = false;
    int local_n = local_c.size();
    DoubleIterator local_i = local_c.iterator();
    while (local_n-- != 0) {
      if (rem(local_i.nextDouble())) {
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
  
  public boolean addAll(Collection<? extends Double> local_c)
  {
    boolean retVal = false;
    Iterator<? extends Double> local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add((Double)local_i.next())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean add(double local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  public DoubleIterator doubleIterator()
  {
    return iterator();
  }
  
  public abstract DoubleIterator iterator();
  
  public boolean remove(Object local_ok)
  {
    return rem(((Double)local_ok).doubleValue());
  }
  
  public boolean add(Double local_o)
  {
    return add(local_o.doubleValue());
  }
  
  public boolean rem(Object local_o)
  {
    return rem(((Double)local_o).doubleValue());
  }
  
  public boolean contains(Object local_o)
  {
    return contains(((Double)local_o).doubleValue());
  }
  
  public boolean contains(double local_k)
  {
    DoubleIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextDouble()) {
        return true;
      }
    }
    return false;
  }
  
  public boolean rem(double local_k)
  {
    DoubleIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextDouble())
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
    DoubleIterator local_i = iterator();
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
      double local_k = local_i.nextDouble();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("}");
    return local_s.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */