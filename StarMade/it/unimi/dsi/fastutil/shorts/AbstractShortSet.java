package it.unimi.dsi.fastutil.shorts;

import java.util.Set;

public abstract class AbstractShortSet
  extends AbstractShortCollection
  implements Cloneable, ShortSet
{
  public abstract ShortIterator iterator();
  
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
    ShortIterator local_i = iterator();
    while (local_n-- != 0)
    {
      short local_k = local_i.nextShort();
      local_h += local_k;
    }
    return local_h;
  }
  
  public boolean remove(short local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean rem(short local_k)
  {
    return remove(local_k);
  }
  
  public boolean remove(Object local_o)
  {
    return remove(((Short)local_o).shortValue());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */