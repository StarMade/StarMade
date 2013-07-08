package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.HashCommon;
import java.util.Set;

public abstract class AbstractFloatSet
  extends AbstractFloatCollection
  implements Cloneable, FloatSet
{
  public abstract FloatIterator iterator();
  
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
    FloatIterator local_i = iterator();
    while (local_n-- != 0)
    {
      float local_k = local_i.nextFloat();
      local_h += HashCommon.float2int(local_k);
    }
    return local_h;
  }
  
  public boolean remove(float local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean rem(float local_k)
  {
    return remove(local_k);
  }
  
  public boolean remove(Object local_o)
  {
    return remove(((Float)local_o).floatValue());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */