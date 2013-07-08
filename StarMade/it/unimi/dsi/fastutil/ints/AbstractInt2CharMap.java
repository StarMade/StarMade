package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractInt2CharMap
  extends AbstractInt2CharFunction
  implements Int2CharMap, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_ov)
  {
    return containsValue(((Character)local_ov).charValue());
  }
  
  public boolean containsValue(char local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(int local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Integer, ? extends Character> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Integer, ? extends Character>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Int2CharMap)) {
      while (local_n-- != 0)
      {
        Int2CharMap.Entry local_e = (Int2CharMap.Entry)local_i.next();
        put(local_e.getIntKey(), local_e.getCharValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Integer, ? extends Character> local_e = (Map.Entry)local_i.next();
        put((Integer)local_e.getKey(), (Character)local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public IntSet keySet()
  {
    new AbstractIntSet()
    {
      public boolean contains(int local_k)
      {
        return AbstractInt2CharMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractInt2CharMap.this.size();
      }
      
      public void clear()
      {
        AbstractInt2CharMap.this.clear();
      }
      
      public IntIterator iterator()
      {
        new AbstractIntIterator()
        {
          final ObjectIterator<Map.Entry<Integer, Character>> field_70 = AbstractInt2CharMap.this.entrySet().iterator();
          
          public int nextInt()
          {
            return ((Int2CharMap.Entry)this.field_70.next()).getIntKey();
          }
          
          public boolean hasNext()
          {
            return this.field_70.hasNext();
          }
        };
      }
    };
  }
  
  public CharCollection values()
  {
    new AbstractCharCollection()
    {
      public boolean contains(char local_k)
      {
        return AbstractInt2CharMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractInt2CharMap.this.size();
      }
      
      public void clear()
      {
        AbstractInt2CharMap.this.clear();
      }
      
      public CharIterator iterator()
      {
        new AbstractCharIterator()
        {
          final ObjectIterator<Map.Entry<Integer, Character>> field_67 = AbstractInt2CharMap.this.entrySet().iterator();
          
          public char nextChar()
          {
            return ((Int2CharMap.Entry)this.field_67.next()).getCharValue();
          }
          
          public boolean hasNext()
          {
            return this.field_67.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Integer, Character>> entrySet()
  {
    return int2CharEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Integer, Character>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Integer, Character>> local_i = entrySet().iterator();
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
      Int2CharMap.Entry local_e = (Int2CharMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getIntKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getCharValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Int2CharMap.Entry
  {
    protected int key;
    protected char value;
    
    public BasicEntry(Integer key, Character value)
    {
      this.key = key.intValue();
      this.value = value.charValue();
    }
    
    public BasicEntry(int key, char value)
    {
      this.key = key;
      this.value = value;
    }
    
    public Integer getKey()
    {
      return Integer.valueOf(this.key);
    }
    
    public int getIntKey()
    {
      return this.key;
    }
    
    public Character getValue()
    {
      return Character.valueOf(this.value);
    }
    
    public char getCharValue()
    {
      return this.value;
    }
    
    public char setValue(char value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character setValue(Character value)
    {
      return Character.valueOf(setValue(value.charValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == ((Integer)local_e.getKey()).intValue()) && (this.value == ((Character)local_e.getValue()).charValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2CharMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */