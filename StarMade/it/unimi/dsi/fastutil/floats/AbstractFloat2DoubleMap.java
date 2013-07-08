package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractFloat2DoubleMap
  extends AbstractFloat2DoubleFunction
  implements Float2DoubleMap, Serializable
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
  
  public boolean containsKey(float local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Float, ? extends Double> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Float, ? extends Double>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Float2DoubleMap)) {
      while (local_n-- != 0)
      {
        Float2DoubleMap.Entry local_e = (Float2DoubleMap.Entry)local_i.next();
        put(local_e.getFloatKey(), local_e.getDoubleValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Float, ? extends Double> local_e = (Map.Entry)local_i.next();
        put((Float)local_e.getKey(), (Double)local_e.getValue());
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
        return AbstractFloat2DoubleMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractFloat2DoubleMap.this.size();
      }
      
      public void clear()
      {
        AbstractFloat2DoubleMap.this.clear();
      }
      
      public FloatIterator iterator()
      {
        new AbstractFloatIterator()
        {
          final ObjectIterator<Map.Entry<Float, Double>> field_52 = AbstractFloat2DoubleMap.this.entrySet().iterator();
          
          public float nextFloat()
          {
            return ((Float2DoubleMap.Entry)this.field_52.next()).getFloatKey();
          }
          
          public boolean hasNext()
          {
            return this.field_52.hasNext();
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
        return AbstractFloat2DoubleMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractFloat2DoubleMap.this.size();
      }
      
      public void clear()
      {
        AbstractFloat2DoubleMap.this.clear();
      }
      
      public DoubleIterator iterator()
      {
        new AbstractDoubleIterator()
        {
          final ObjectIterator<Map.Entry<Float, Double>> field_68 = AbstractFloat2DoubleMap.this.entrySet().iterator();
          
          public double nextDouble()
          {
            return ((Float2DoubleMap.Entry)this.field_68.next()).getDoubleValue();
          }
          
          public boolean hasNext()
          {
            return this.field_68.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Float, Double>> entrySet()
  {
    return float2DoubleEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Float, Double>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Float, Double>> local_i = entrySet().iterator();
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
      Float2DoubleMap.Entry local_e = (Float2DoubleMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getFloatKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getDoubleValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Float2DoubleMap.Entry
  {
    protected float key;
    protected double value;
    
    public BasicEntry(Float key, Double value)
    {
      this.key = key.floatValue();
      this.value = value.doubleValue();
    }
    
    public BasicEntry(float key, double value)
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
      return (this.key == ((Float)local_e.getKey()).floatValue()) && (this.value == ((Double)local_e.getValue()).doubleValue());
    }
    
    public int hashCode()
    {
      return HashCommon.float2int(this.key) ^ HashCommon.double2int(this.value);
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2DoubleMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */