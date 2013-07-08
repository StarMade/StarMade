package it.unimi.dsi.fastutil.shorts;

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

public abstract class AbstractShort2CharMap
  extends AbstractShort2CharFunction
  implements Short2CharMap, Serializable
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
  
  public boolean containsKey(short local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Short, ? extends Character> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Short, ? extends Character>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Short2CharMap)) {
      while (local_n-- != 0)
      {
        Short2CharMap.Entry local_e = (Short2CharMap.Entry)local_i.next();
        put(local_e.getShortKey(), local_e.getCharValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Short, ? extends Character> local_e = (Map.Entry)local_i.next();
        put((Short)local_e.getKey(), (Character)local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public ShortSet keySet()
  {
    new AbstractShortSet()
    {
      public boolean contains(short local_k)
      {
        return AbstractShort2CharMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractShort2CharMap.this.size();
      }
      
      public void clear()
      {
        AbstractShort2CharMap.this.clear();
      }
      
      public ShortIterator iterator()
      {
        new AbstractShortIterator()
        {
          final ObjectIterator<Map.Entry<Short, Character>> field_53 = AbstractShort2CharMap.this.entrySet().iterator();
          
          public short nextShort()
          {
            return ((Short2CharMap.Entry)this.field_53.next()).getShortKey();
          }
          
          public boolean hasNext()
          {
            return this.field_53.hasNext();
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
        return AbstractShort2CharMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractShort2CharMap.this.size();
      }
      
      public void clear()
      {
        AbstractShort2CharMap.this.clear();
      }
      
      public CharIterator iterator()
      {
        new AbstractCharIterator()
        {
          final ObjectIterator<Map.Entry<Short, Character>> field_67 = AbstractShort2CharMap.this.entrySet().iterator();
          
          public char nextChar()
          {
            return ((Short2CharMap.Entry)this.field_67.next()).getCharValue();
          }
          
          public boolean hasNext()
          {
            return this.field_67.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Short, Character>> entrySet()
  {
    return short2CharEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Short, Character>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Short, Character>> local_i = entrySet().iterator();
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
      Short2CharMap.Entry local_e = (Short2CharMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getShortKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getCharValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Short2CharMap.Entry
  {
    protected short key;
    protected char value;
    
    public BasicEntry(Short key, Character value)
    {
      this.key = key.shortValue();
      this.value = value.charValue();
    }
    
    public BasicEntry(short key, char value)
    {
      this.key = key;
      this.value = value;
    }
    
    public Short getKey()
    {
      return Short.valueOf(this.key);
    }
    
    public short getShortKey()
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
      return (this.key == ((Short)local_e.getKey()).shortValue()) && (this.value == ((Character)local_e.getValue()).charValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2CharMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */