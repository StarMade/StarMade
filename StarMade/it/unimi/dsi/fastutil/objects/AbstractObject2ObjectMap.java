package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractObject2ObjectMap<K, V>
  extends AbstractObject2ObjectFunction<K, V>
  implements Object2ObjectMap<K, V>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(Object local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends K, ? extends V> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends K, ? extends V>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Object2ObjectMap)) {
      while (local_n-- != 0)
      {
        Object2ObjectMap.Entry<? extends K, ? extends V> local_e = (Object2ObjectMap.Entry)local_i.next();
        put(local_e.getKey(), local_e.getValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends K, ? extends V> local_e = (Map.Entry)local_i.next();
        put(local_e.getKey(), local_e.getValue());
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
        return AbstractObject2ObjectMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractObject2ObjectMap.this.size();
      }
      
      public void clear()
      {
        AbstractObject2ObjectMap.this.clear();
      }
      
      public ObjectIterator<K> iterator()
      {
        new AbstractObjectIterator()
        {
          final ObjectIterator<Map.Entry<K, V>> field_3 = AbstractObject2ObjectMap.this.entrySet().iterator();
          
          public K next()
          {
            return ((Object2ObjectMap.Entry)this.field_3.next()).getKey();
          }
          
          public boolean hasNext()
          {
            return this.field_3.hasNext();
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
        return AbstractObject2ObjectMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractObject2ObjectMap.this.size();
      }
      
      public void clear()
      {
        AbstractObject2ObjectMap.this.clear();
      }
      
      public ObjectIterator<V> iterator()
      {
        new AbstractObjectIterator()
        {
          final ObjectIterator<Map.Entry<K, V>> field_3 = AbstractObject2ObjectMap.this.entrySet().iterator();
          
          public V next()
          {
            return ((Object2ObjectMap.Entry)this.field_3.next()).getValue();
          }
          
          public boolean hasNext()
          {
            return this.field_3.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<K, V>> entrySet()
  {
    return object2ObjectEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<K, V>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<K, V>> local_i = entrySet().iterator();
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
      Object2ObjectMap.Entry<K, V> local_e = (Object2ObjectMap.Entry)local_i.next();
      if (this == local_e.getKey()) {
        local_s.append("(this map)");
      } else {
        local_s.append(String.valueOf(local_e.getKey()));
      }
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
  
  public static class BasicEntry<K, V>
    implements Object2ObjectMap.Entry<K, V>
  {
    protected K key;
    protected V value;
    
    public BasicEntry(K key, V value)
    {
      this.key = key;
      this.value = value;
    }
    
    public K getKey()
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
      return (this.key == null ? local_e.getKey() == null : this.key.equals(local_e.getKey())) && (this.value == null ? local_e.getValue() == null : this.value.equals(local_e.getValue()));
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2ObjectMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */