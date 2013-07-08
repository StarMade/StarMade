package it.unimi.dsi.fastutil.objects;

import java.util.Set;

public abstract class AbstractReferenceSet<K>
  extends AbstractReferenceCollection<K>
  implements Cloneable, ReferenceSet<K>
{
  public abstract ObjectIterator<K> iterator();
  
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
    ObjectIterator<K> local_i = iterator();
    while (local_n-- != 0)
    {
      K local_k = local_i.next();
      local_h += (local_k == null ? 0 : System.identityHashCode(local_k));
    }
    return local_h;
  }
  
  public boolean remove(Object local_k)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReferenceSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */