package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectIterators;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractCharCollection
  extends AbstractCollection<Character>
  implements CharCollection
{
  public char[] toArray(char[] local_a)
  {
    return toCharArray(local_a);
  }
  
  public char[] toCharArray()
  {
    return toCharArray(null);
  }
  
  public char[] toCharArray(char[] local_a)
  {
    if ((local_a == null) || (local_a.length < size())) {
      local_a = new char[size()];
    }
    CharIterators.unwrap(iterator(), local_a);
    return local_a;
  }
  
  public boolean addAll(CharCollection local_c)
  {
    boolean retVal = false;
    CharIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add(local_i.nextChar())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean containsAll(CharCollection local_c)
  {
    CharIterator local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (!contains(local_i.nextChar())) {
        return false;
      }
    }
    return true;
  }
  
  public boolean retainAll(CharCollection local_c)
  {
    boolean retVal = false;
    int local_n = size();
    CharIterator local_i = iterator();
    while (local_n-- != 0) {
      if (!local_c.contains(local_i.nextChar()))
      {
        local_i.remove();
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean removeAll(CharCollection local_c)
  {
    boolean retVal = false;
    int local_n = local_c.size();
    CharIterator local_i = local_c.iterator();
    while (local_n-- != 0) {
      if (rem(local_i.nextChar())) {
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
  
  public boolean addAll(Collection<? extends Character> local_c)
  {
    boolean retVal = false;
    Iterator<? extends Character> local_i = local_c.iterator();
    int local_n = local_c.size();
    while (local_n-- != 0) {
      if (add((Character)local_i.next())) {
        retVal = true;
      }
    }
    return retVal;
  }
  
  public boolean add(char local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  @Deprecated
  public CharIterator charIterator()
  {
    return iterator();
  }
  
  public abstract CharIterator iterator();
  
  public boolean remove(Object local_ok)
  {
    return rem(((Character)local_ok).charValue());
  }
  
  public boolean add(Character local_o)
  {
    return add(local_o.charValue());
  }
  
  public boolean rem(Object local_o)
  {
    return rem(((Character)local_o).charValue());
  }
  
  public boolean contains(Object local_o)
  {
    return contains(((Character)local_o).charValue());
  }
  
  public boolean contains(char local_k)
  {
    CharIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextChar()) {
        return true;
      }
    }
    return false;
  }
  
  public boolean rem(char local_k)
  {
    CharIterator iterator = iterator();
    while (iterator.hasNext()) {
      if (local_k == iterator.nextChar())
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
    CharIterator local_i = iterator();
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
      char local_k = local_i.nextChar();
      local_s.append(String.valueOf(local_k));
    }
    local_s.append("}");
    return local_s.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */