package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectIterators;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractByteCollection
  extends AbstractCollection<Byte>
  implements ByteCollection
{
  public byte[] toArray(byte[] local_a)
  {
    return toByteArray(local_a);
  }
  
  public byte[] toByteArray()
  {
    return toByteArray(null);
  }
  
  public byte[] toByteArray(byte[] local_a)
  {
    if ((local_a == null) || (local_a.length < size())) {
      local_a = new byte[size()];
    }
    ByteIterators.unwrap(iterator(), local_a);
    return local_a;
  }
  
  public boolean addAll(ByteCollection local_c)
  {
    boolean retVal = false;
    ByteIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add(local_i.nextByte())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean containsAll(ByteCollection local_c)
  {
    ByteIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (!contains(local_i.nextByte())) {
        return false;
      }
    }
    return true;
  }
  
  public boolean retainAll(ByteCollection local_c)
  {
    boolean retVal = false;
    int local_n = size();
    ByteIterator local_i = iterator();
    while (local_n-- != 0) {
      if (!local_c.contains(local_i.nextByte()))
      {
        local_i.remove();
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean removeAll(ByteCollection local_c)
  {
    boolean retVal = false;
    int local_n = local_c.size();
    ByteIterator local_i = local_c.iterator();
    while (local_n-- != 0) {
      if (rem(local_i.nextByte())) {
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
  
  public boolean addAll(Collection<? extends Byte> local_c)
  {
    boolean retVal = false;
    Iterator<? extends Byte> local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add((Byte)local_i.next())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean add(byte local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  public ByteIterator byteIterator()
  {
    return iterator();
  }
  
  public abstract ByteIterator iterator();
  
  public boolean remove(Object local_ok)
  {
    return rem(((Byte)local_ok).byteValue());
  }
  
  public boolean add(Byte local_o)
  {
    return add(local_o.byteValue());
  }
  
  public boolean rem(Object local_o)
  {
    return rem(((Byte)local_o).byteValue());
  }
  
  public boolean contains(Object local_o)
  {
    return contains(((Byte)local_o).byteValue());
  }
  
  public boolean contains(byte local_k)
  {
    ByteIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextByte()) {
        return true;
      }
    }
    return false;
  }
  
  public boolean rem(byte local_k)
  {
    ByteIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextByte())
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
    ByteIterator local_i = iterator();
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
      byte local_k = local_i.nextByte();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("}");
    return local_s.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */