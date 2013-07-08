package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractChar2ObjectMap<V>
  extends AbstractChar2ObjectFunction<V>
  implements Char2ObjectMap<V>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(char local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Character, ? extends V> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Character, ? extends V>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Char2ObjectMap)) {
      while (local_n-- != 0)
      {
        Char2ObjectMap.Entry<? extends V> local_e = (Char2ObjectMap.Entry)local_i.next();
        put(local_e.getCharKey(), local_e.getValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Character, ? extends V> local_e = (Map.Entry)local_i.next();
        put((Character)local_e.getKey(), local_e.getValue());
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
        return AbstractChar2ObjectMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractChar2ObjectMap.this.size();
      }
      
      public void clear()
      {
        AbstractChar2ObjectMap.this.clear();
      }
      
      public CharIterator iterator()
      {
        new AbstractCharIterator()
        {
          final ObjectIterator<Map.Entry<Character, V>> field_67 = AbstractChar2ObjectMap.this.entrySet().iterator();
          
          public char nextChar()
          {
            return ((Char2ObjectMap.Entry)this.field_67.next()).getCharKey();
          }
          
          public boolean hasNext()
          {
            return this.field_67.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectCollection<V> values()
  {
    new AbstractObjectCollection()
    {
      public boolean contains(Object local_k)
      {
        return AbstractChar2ObjectMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractChar2ObjectMap.this.size();
      }
      
      public void clear()
      {
        AbstractChar2ObjectMap.this.clear();
      }
      
      public ObjectIterator<V> iterator()
      {
        new AbstractObjectIterator()
        {
          final ObjectIterator<Map.Entry<Character, V>> field_3 = AbstractChar2ObjectMap.this.entrySet().iterator();
          
          public V next()
          {
            return ((Char2ObjectMap.Entry)this.field_3.next()).getValue();
          }
          
          public boolean hasNext()
          {
            return this.field_3.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Character, V>> entrySet()
  {
    return char2ObjectEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Character, V>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Character, V>> local_i = entrySet().iterator();
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
      Char2ObjectMap.Entry<V> local_e = (Char2ObjectMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getCharKey()));
      local_s.append("=>");
      if (this == local_e.getValue()) {
        local_s.append("(this map)");
      } else {
        local_s.append(String.valueOf(local_e.getValue()));
      }
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry<V>
    implements Char2ObjectMap.Entry<V>
  {
    protected char key;
    protected V value;
    
    public BasicEntry(Character key, V value)
    {
      this.key = key.charValue();
      this.value = value;
    }
    
    public BasicEntry(char key, V value)
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
    
    public V getValue()
    {
      return this.value;
    }
    
    public V setValue(V value)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == ((Character)local_e.getKey()).charValue()) && (this.value == null ? local_e.getValue() == null : this.value.equals(local_e.getValue()));
    }
    
    public int hashCode()
    {
      return this.key ^ (this.value == null ? 0 : this.value.hashCode());
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ObjectMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */