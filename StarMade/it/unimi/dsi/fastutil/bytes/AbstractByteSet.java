package it.unimi.dsi.fastutil.bytes;

import java.util.Set;

public abstract class AbstractByteSet
  extends AbstractByteCollection
  implements Cloneable, ByteSet
{
  public abstract ByteIterator iterator();
  
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
    ByteIterator local_i = iterator();
    while (local_n-- != 0)
    {
      byte local_k = local_i.nextByte();
      local_h += local_k;
    }
    return local_h;
  }
  
  public boolean remove(byte local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean rem(byte local_k)
  {
    return remove(local_k);
  }
  
  public boolean remove(Object local_o)
  {
    return remove(((Byte)local_o).byteValue());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */