package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.HashCommon;
import java.util.Set;

public abstract class AbstractDoubleSet
  extends AbstractDoubleCollection
  implements Cloneable, DoubleSet
{
  public abstract DoubleIterator iterator();
  
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
    DoubleIterator local_i = iterator();
    while (local_n-- != 0)
    {
      double local_k = local_i.nextDouble();
      local_h += HashCommon.double2int(local_k);
    }
    return local_h;
  }
  
  public boolean remove(double local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean rem(double local_k)
  {
    return remove(local_k);
  }
  
  public boolean remove(Object local_o)
  {
    return remove(((Double)local_o).doubleValue());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */