package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractChar2IntMap
  extends AbstractChar2IntFunction
  implements Char2IntMap, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_ov)
  {
    return containsValue(((Integer)local_ov).intValue());
  }
  
  public boolean containsValue(int local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(char local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Character, ? extends Integer> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Character, ? extends Integer>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Char2IntMap)) {
      while (local_n-- != 0)
      {
        Char2IntMap.Entry local_e = (Char2IntMap.Entry)local_i.next();
        put(local_e.getCharKey(), local_e.getIntValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Character, ? extends Integer> local_e = (Map.Entry)local_i.next();
        put((Character)local_e.getKey(), (Integer)local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public CharSet keySet()
  {
    new AbstractCharSet()
    {
      public boolean contains(char local_k)
      {
        return AbstractChar2IntMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractChar2IntMap.this.size();
      }
      
      public void clear()
      {
        AbstractChar2IntMap.this.clear();
      }
      
      public CharIterator iterator()
      {
        new AbstractCharIterator()
        {
          final ObjectIterator<Map.Entry<Character, Integer>> field_67 = AbstractChar2IntMap.this.entrySet().iterator();
          
          public char nextChar()
          {
            return ((Char2IntMap.Entry)this.field_67.next()).getCharKey();
          }
          
          public boolean hasNext()
          {
            return this.field_67.hasNext();
          }
        };
      }
    };
  }
  
  public IntCollection values()
  {
    new AbstractIntCollection()
    {
      public boolean contains(int local_k)
      {
        return AbstractChar2IntMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractChar2IntMap.this.size();
      }
      
      public void clear()
      {
        AbstractChar2IntMap.this.clear();
      }
      
      public IntIterator iterator()
      {
        new AbstractIntIterator()
        {
          final ObjectIterator<Map.Entry<Character, Integer>> field_70 = AbstractChar2IntMap.this.entrySet().iterator();
          
          public int nextInt()
          {
            return ((Char2IntMap.Entry)this.field_70.next()).getIntValue();
          }
          
          public boolean hasNext()
          {
            return this.field_70.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Character, Integer>> entrySet()
  {
    return char2IntEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Character, Integer>> local_i = entrySet().iterator();
    while (local_n-- != 0) {
      local_h += ((Map.Entry)local_i.next()).hashCode();
    }
    return local_h;
  }
  
  public boolean equals(Object local_o)
  {
    if (local_o == this) {
      return true;
    }
    if (!(local_o instanceof Map)) {
      return false;
    }
    Map<?, ?> local_m = (Map)local_o;
    if (local_m.size() != size()) {
      return false;
    }
    return entrySet().containsAll(local_m.entrySet());
  }
  
  public String toString()
  {
    StringBuilder local_s = new StringBuilder();
    ObjectIterator<? extends Map.Entry<Character, Integer>> local_i = entrySet().iterator();
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
      Char2IntMap.Entry local_e = (Char2IntMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getCharKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getIntValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Char2IntMap.Entry
  {
    protected char key;
    protected int value;
    
    public BasicEntry(Character key, Integer value)
    {
      this.key = key.charValue();
      this.value = value.intValue();
    }
    
    public BasicEntry(char key, int value)
    {
      this.key = key;
      this.value = value;
    }
    
    public Character getKey()
    {
      return Character.valueOf(this.key);
    }
    
    public char getCharKey()
    {
      return this.key;
    }
    
    public Integer getValue()
    {
      return Integer.valueOf(this.value);
    }
    
    public int getIntValue()
    {
      return this.value;
    }
    
    public int setValue(int value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer setValue(Integer value)
    {
      return Integer.valueOf(setValue(value.intValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == ((Character)local_e.getKey()).charValue()) && (this.value == ((Integer)local_e.getValue()).intValue());
    }
    
    public int hashCode()
    {
      return this.key ^ this.value;
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2IntMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */