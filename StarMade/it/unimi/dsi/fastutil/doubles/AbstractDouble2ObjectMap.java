package it.unimi.dsi.fastutil.doubles;

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

public abstract class AbstractDouble2ObjectMap<V>
  extends AbstractDouble2ObjectFunction<V>
  implements Double2ObjectMap<V>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(double local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Double, ? extends V> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Double, ? extends V>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Double2ObjectMap)) {
      while (local_n-- != 0)
      {
        Double2ObjectMap.Entry<? extends V> local_e = (Double2ObjectMap.Entry)local_i.next();
        put(local_e.getDoubleKey(), local_e.getValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Double, ? extends V> local_e = (Map.Entry)local_i.next();
        put((Double)local_e.getKey(), local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public DoubleSet keySet()
  {
    new AbstractDoubleSet()
    {
      public boolean contains(double local_k)
      {
        return AbstractDouble2ObjectMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractDouble2ObjectMap.this.size();
      }
      
      public void clear()
      {
        AbstractDouble2ObjectMap.this.clear();
      }
      
      public DoubleIterator iterator()
      {
        new AbstractDoubleIterator()
        {
          final ObjectIterator<Map.Entry<Double, V>> field_68 = AbstractDouble2ObjectMap.this.entrySet().iterator();
          
          public double nextDouble()
          {
            return ((Double2ObjectMap.Entry)this.field_68.next()).getDoubleKey();
          }
          
          public boolean hasNext()
          {
            return this.field_68.hasNext();
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
        return AbstractDouble2ObjectMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractDouble2ObjectMap.this.size();
      }
      
      public void clear()
      {
        AbstractDouble2ObjectMap.this.clear();
      }
      
      public ObjectIterator<V> iterator()
      {
        new AbstractObjectIterator()
        {
          final ObjectIterator<Map.Entry<Double, V>> field_3 = AbstractDouble2ObjectMap.this.entrySet().iterator();
          
          public V next()
          {
            return ((Double2ObjectMap.Entry)this.field_3.next()).getValue();
          }
          
          public boolean hasNext()
          {
            return this.field_3.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Double, V>> entrySet()
  {
    return double2ObjectEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Double, V>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Double, V>> local_i = entrySet().iterator();
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
      Double2ObjectMap.Entry<V> local_e = (Double2ObjectMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getDoubleKey()));
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
    implements Double2ObjectMap.Entry<V>
  {
    protected double key;
    protected V value;
    
    public BasicEntry(Double key, V value)
    {
      this.key = key.doubleValue();
      this.value = value;
    }
    
    public BasicEntry(double key, V value)
    {
      this.key = key;
      this.value = value;
    }
    
    public Double getKey()
    {
      return Double.valueOf(this.key);
    }
    
    public double getDoubleKey()
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
      return (this.key == ((Double)local_e.getKey()).doubleValue()) && (this.value == null ? local_e.getValue() == null : this.value.equals(local_e.getValue()));
    }
    
    public int hashCode()
    {
      return HashCommon.double2int(this.key) ^ (this.value == null ? 0 : this.value.hashCode());
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2ObjectMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */