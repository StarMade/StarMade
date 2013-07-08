package it.unimi.dsi.fastutil.ints;

import java.util.Set;

public abstract class AbstractIntSet
  extends AbstractIntCollection
  implements Cloneable, IntSet
{
  public abstract IntIterator iterator();
  
  public boolean equals(Object local_o)
  {
    if (local_o == this) {
      return true;
    }
    if (!(local_o instanceof Set)) {
      return false;
    }
    Set<?> local_s = (Set)local_o;
    if (local_s.size() != size()) {
      return false;
    }
    return containsAll(local_s);
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    IntIterator local_i = iterator();
    while (local_n-- != 0)
    {
      int local_k = local_i.nextInt();
      local_h += local_k;
    }
    return local_h;
  }
  
  public boolean remove(int local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean rem(int local_k)
  {
    return remove(local_k);
  }
  
  public boolean remove(Object local_o)
  {
    return remove(((Integer)local_o).intValue());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */