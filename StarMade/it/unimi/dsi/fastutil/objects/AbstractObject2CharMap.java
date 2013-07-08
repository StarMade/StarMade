package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractObject2CharMap<K>
  extends AbstractObject2CharFunction<K>
  implements Object2CharMap<K>, Serializable
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
  
  public boolean containsKey(Object local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends K, ? extends Character> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends K, ? extends Character>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Object2CharMap)) {
      while (local_n-- != 0)
      {
        Object2CharMap.Entry<? extends K> local_e = (Object2CharMap.Entry)local_i.next();
        put(local_e.getKey(), local_e.getCharValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends K, ? extends Character> local_e = (Map.Entry)local_i.next();
        put(local_e.getKey(), (Character)local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public ObjectSet<K> keySet()
  {
    new AbstractObjectSet()
    {
      public boolean contains(Object local_k)
      {
        return AbstractObject2CharMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractObject2CharMap.this.size();
      }
      
      public void clear()
      {
        AbstractObject2CharMap.this.clear();
      }
      
      public ObjectIterator<K> iterator()
      {
        new AbstractObjectIterator()
        {
          final ObjectIterator<Map.Entry<K, Character>> field_3 = AbstractObject2CharMap.this.entrySet().iterator();
          
          public K next()
          {
            return ((Object2CharMap.Entry)this.field_3.next()).getKey();
          }
          
          public boolean hasNext()
          {
            return this.field_3.hasNext();
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
        return AbstractObject2CharMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractObject2CharMap.this.size();
      }
      
      public void clear()
      {
        AbstractObject2CharMap.this.clear();
      }
      
      public CharIterator iterator()
      {
        new AbstractCharIterator()
        {
          final ObjectIterator<Map.Entry<K, Character>> field_67 = AbstractObject2CharMap.this.entrySet().iterator();
          
          public char nextChar()
          {
            return ((Object2CharMap.Entry)this.field_67.next()).getCharValue();
          }
          
          public boolean hasNext()
          {
            return this.field_67.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<K, Character>> entrySet()
  {
    return object2CharEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<K, Character>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<K, Character>> local_i = entrySet().iterator();
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
      Object2CharMap.Entry<K> local_e = (Object2CharMap.Entry)local_i.next();
      if (this == local_e.getKey()) {
        local_s.append("(this map)");
      } else {
        local_s.append(String.valueOf(local_e.getKey()));
      }
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getCharValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry<K>
    implements Object2CharMap.Entry<K>
  {
    protected K key;
    protected char value;
    
    public BasicEntry(K key, Character value)
    {
      this.key = key;
      this.value = value.charValue();
    }
    
    public BasicEntry(K key, char value)
    {
      this.key = key;
      this.value = value;
    }
    
    public K getKey()
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
      return (this.key == null ? local_e.getKey() == null : this.key.equals(local_e.getKey())) && (this.value == ((Character)local_e.getValue()).charValue());
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ this.value;
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2CharMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */