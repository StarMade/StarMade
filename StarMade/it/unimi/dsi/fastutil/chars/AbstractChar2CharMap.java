package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractChar2CharMap
  extends AbstractChar2CharFunction
  implements Char2CharMap, Serializable
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
  
  public boolean containsKey(char local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Character, ? extends Character> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Character, ? extends Character>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Char2CharMap)) {
      while (local_n-- != 0)
      {
        Char2CharMap.Entry local_e = (Char2CharMap.Entry)local_i.next();
        put(local_e.getCharKey(), local_e.getCharValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Character, ? extends Character> local_e = (Map.Entry)local_i.next();
        put((Character)local_e.getKey(), (Character)local_e.getValue());
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
        return AbstractChar2CharMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractChar2CharMap.this.size();
      }
      
      public void clear()
      {
        AbstractChar2CharMap.this.clear();
      }
      
      public CharIterator iterator()
      {
        new AbstractCharIterator()
        {
          final ObjectIterator<Map.Entry<Character, Character>> field_67 = AbstractChar2CharMap.this.entrySet().iterator();
          
          public char nextChar()
          {
            return ((Char2CharMap.Entry)this.field_67.next()).getCharKey();
          }
          
          public boolean hasNext()
          {
            return this.field_67.hasNext();
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
        return AbstractChar2CharMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractChar2CharMap.this.size();
      }
      
      public void clear()
      {
        AbstractChar2CharMap.this.clear();
      }
      
      public CharIterator iterator()
      {
        new AbstractCharIterator()
        {
          final ObjectIterator<Map.Entry<Character, Character>> field_67 = AbstractChar2CharMap.this.entrySet().iterator();
          
          public char nextChar()
          {
            return ((Char2CharMap.Entry)this.field_67.next()).getCharValue();
          }
          
          public boolean hasNext()
          {
            return this.field_67.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Character, Character>> entrySet()
  {
    return char2CharEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Character, Character>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Character, Character>> local_i = entrySet().iterator();
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
      Char2CharMap.Entry local_e = (Char2CharMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getCharKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getCharValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Char2CharMap.Entry
  {
    protected char key;
    protected char value;
    
    public BasicEntry(Character key, Character value)
    {
      this.key = key.charValue();
      this.value = value.charValue();
    }
    
    public BasicEntry(char key, char value)
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
      return (this.key == ((Character)local_e.getKey()).charValue()) && (this.value == ((Character)local_e.getValue()).charValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2CharMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */