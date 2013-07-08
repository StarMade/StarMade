package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractObject2DoubleMap<K>
  extends AbstractObject2DoubleFunction<K>
  implements Object2DoubleMap<K>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_ov)
  {
    return containsValue(((Double)local_ov).doubleValue());
  }
  
  public boolean containsValue(double local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(Object local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends K, ? extends Double> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends K, ? extends Double>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Object2DoubleMap)) {
      while (local_n-- != 0)
      {
        Object2DoubleMap.Entry<? extends K> local_e = (Object2DoubleMap.Entry)local_i.next();
        put(local_e.getKey(), local_e.getDoubleValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends K, ? extends Double> local_e = (Map.Entry)local_i.next();
        put(local_e.getKey(), (Double)local_e.getValue());
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
        return AbstractObject2DoubleMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractObject2DoubleMap.this.size();
      }
      
      public void clear()
      {
        AbstractObject2DoubleMap.this.clear();
      }
      
      public ObjectIterator<K> iterator()
      {
        new AbstractObjectIterator()
        {
          final ObjectIterator<Map.Entry<K, Double>> field_3 = AbstractObject2DoubleMap.this.entrySet().iterator();
          
          public K next()
          {
            return ((Object2DoubleMap.Entry)this.field_3.next()).getKey();
          }
          
          public boolean hasNext()
          {
            return this.field_3.hasNext();
          }
        };
      }
    };
  }
  
  public DoubleCollection values()
  {
    new AbstractDoubleCollection()
    {
      public boolean contains(double local_k)
      {
        return AbstractObject2DoubleMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractObject2DoubleMap.this.size();
      }
      
      public void clear()
      {
        AbstractObject2DoubleMap.this.clear();
      }
      
      public DoubleIterator iterator()
      {
        new AbstractDoubleIterator()
        {
          final ObjectIterator<Map.Entry<K, Double>> field_68 = AbstractObject2DoubleMap.this.entrySet().iterator();
          
          public double nextDouble()
          {
            return ((Object2DoubleMap.Entry)this.field_68.next()).getDoubleValue();
          }
          
          public boolean hasNext()
          {
            return this.field_68.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<K, Double>> entrySet()
  {
    return object2DoubleEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<K, Double>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<K, Double>> local_i = entrySet().iterator();
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
      Object2DoubleMap.Entry<K> local_e = (Object2DoubleMap.Entry)local_i.next();
      if (this == local_e.getKey()) {
        local_s.append("(this map)");
      } else {
        local_s.append(String.valueOf(local_e.getKey()));
      }
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getDoubleValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry<K>
    implements Object2DoubleMap.Entry<K>
  {
    protected K key;
    protected double value;
    
    public BasicEntry(K key, Double value)
    {
      this.key = key;
      this.value = value.doubleValue();
    }
    
    public BasicEntry(K key, double value)
    {
      this.key = key;
      this.value = value;
    }
    
    public K getKey()
    {
      return this.key;
    }
    
    public Double getValue()
    {
      return Double.valueOf(this.value);
    }
    
    public double getDoubleValue()
    {
      return this.value;
    }
    
    public double setValue(double value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double setValue(Double value)
    {
      return Double.valueOf(setValue(value.doubleValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == null ? local_e.getKey() == null : this.key.equals(local_e.getKey())) && (this.value == ((Double)local_e.getValue()).doubleValue());
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ HashCommon.double2int(this.value);
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2DoubleMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */