package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.HashCommon;
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

public abstract class AbstractFloat2ObjectMap<V>
  extends AbstractFloat2ObjectFunction<V>
  implements Float2ObjectMap<V>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(float local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Float, ? extends V> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Float, ? extends V>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Float2ObjectMap)) {
      while (local_n-- != 0)
      {
        Float2ObjectMap.Entry<? extends V> local_e = (Float2ObjectMap.Entry)local_i.next();
        put(local_e.getFloatKey(), local_e.getValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Float, ? extends V> local_e = (Map.Entry)local_i.next();
        put((Float)local_e.getKey(), local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public FloatSet keySet()
  {
    new AbstractFloatSet()
    {
      public boolean contains(float local_k)
      {
        return AbstractFloat2ObjectMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractFloat2ObjectMap.this.size();
      }
      
      public void clear()
      {
        AbstractFloat2ObjectMap.this.clear();
      }
      
      public FloatIterator iterator()
      {
        new AbstractFloatIterator()
        {
          final ObjectIterator<Map.Entry<Float, V>> field_52 = AbstractFloat2ObjectMap.this.entrySet().iterator();
          
          public float nextFloat()
          {
            return ((Float2ObjectMap.Entry)this.field_52.next()).getFloatKey();
          }
          
          public boolean hasNext()
          {
            return this.field_52.hasNext();
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
        return AbstractFloat2ObjectMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractFloat2ObjectMap.this.size();
      }
      
      public void clear()
      {
        AbstractFloat2ObjectMap.this.clear();
      }
      
      public ObjectIterator<V> iterator()
      {
        new AbstractObjectIterator()
        {
          final ObjectIterator<Map.Entry<Float, V>> field_3 = AbstractFloat2ObjectMap.this.entrySet().iterator();
          
          public V next()
          {
            return ((Float2ObjectMap.Entry)this.field_3.next()).getValue();
          }
          
          public boolean hasNext()
          {
            return this.field_3.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Float, V>> entrySet()
  {
    return float2ObjectEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Float, V>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Float, V>> local_i = entrySet().iterator();
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
      Float2ObjectMap.Entry<V> local_e = (Float2ObjectMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getFloatKey()));
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
    implements Float2ObjectMap.Entry<V>
  {
    protected float key;
    protected V value;
    
    public BasicEntry(Float key, V value)
    {
      this.key = key.floatValue();
      this.value = value;
    }
    
    public BasicEntry(float key, V value)
    {
      this.key = key;
      this.value = value;
    }
    
    public Float getKey()
    {
      return Float.valueOf(this.key);
    }
    
    public float getFloatKey()
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
      return (this.key == ((Float)local_e.getKey()).floatValue()) && (this.value == null ? local_e.getValue() == null : this.value.equals(local_e.getValue()));
    }
    
    public int hashCode()
    {
      return HashCommon.float2int(this.key) ^ (this.value == null ? 0 : this.value.hashCode());
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2ObjectMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */