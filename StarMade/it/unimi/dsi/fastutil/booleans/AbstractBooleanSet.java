package it.unimi.dsi.fastutil.booleans;

import java.util.Set;

public abstract class AbstractBooleanSet
  extends AbstractBooleanCollection
  implements Cloneable, BooleanSet
{
  public abstract BooleanIterator iterator();
  
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
    BooleanIterator local_i = iterator();
    while (local_n-- != 0)
    {
      boolean local_k = local_i.nextBoolean();
      local_h += (local_k ? 1231 : 1237);
    }
    return local_h;
  }
  
  public boolean remove(boolean local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean rem(boolean local_k)
  {
    return remove(local_k);
  }
  
  public boolean remove(Object local_o)
  {
    return remove(((Boolean)local_o).booleanValue());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */