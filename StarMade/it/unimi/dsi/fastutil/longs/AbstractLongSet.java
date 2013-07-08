package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.HashCommon;
import java.util.Set;

public abstract class AbstractLongSet
  extends AbstractLongCollection
  implements Cloneable, LongSet
{
  public abstract LongIterator iterator();
  
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
    LongIterator local_i = iterator();
    while (local_n-- != 0)
    {
      long local_k = local_i.nextLong();
      local_h += HashCommon.long2int(local_k);
    }
    return local_h;
  }
  
  public boolean remove(long local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean rem(long local_k)
  {
    return remove(local_k);
  }
  
  public boolean remove(Object local_o)
  {
    return remove(((Long)local_o).longValue());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */