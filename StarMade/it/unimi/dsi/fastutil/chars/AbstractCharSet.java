package it.unimi.dsi.fastutil.chars;

import java.util.Set;

public abstract class AbstractCharSet
  extends AbstractCharCollection
  implements Cloneable, CharSet
{
  public abstract CharIterator iterator();
  
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
    CharIterator local_i = iterator();
    while (local_n-- != 0)
    {
      char local_k = local_i.nextChar();
      local_h += local_k;
    }
    return local_h;
  }
  
  public boolean remove(char local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean rem(char local_k)
  {
    return remove(local_k);
  }
  
  public boolean remove(Object local_o)
  {
    return remove(((Character)local_o).charValue());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */