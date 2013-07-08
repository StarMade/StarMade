package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractDouble2FloatMap
  extends AbstractDouble2FloatFunction
  implements Double2FloatMap, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_ov)
  {
    return containsValue(((Float)local_ov).floatValue());
  }
  
  public boolean containsValue(float local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(double local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Double, ? extends Float> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Double, ? extends Float>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Double2FloatMap)) {
      while (local_n-- != 0)
      {
        Double2FloatMap.Entry local_e = (Double2FloatMap.Entry)local_i.next();
        put(local_e.getDoubleKey(), local_e.getFloatValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Double, ? extends Float> local_e = (Map.Entry)local_i.next();
        put((Double)local_e.getKey(), (Float)local_e.getValue());
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
        return AbstractDouble2FloatMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractDouble2FloatMap.this.size();
      }
      
      public void clear()
      {
        AbstractDouble2FloatMap.this.clear();
      }
      
      public DoubleIterator iterator()
      {
        new AbstractDoubleIterator()
        {
          final ObjectIterator<Map.Entry<Double, Float>> field_68 = AbstractDouble2FloatMap.this.entrySet().iterator();
          
          public double nextDouble()
          {
            return ((Double2FloatMap.Entry)this.field_68.next()).getDoubleKey();
          }
          
          public boolean hasNext()
          {
            return this.field_68.hasNext();
          }
        };
      }
    };
  }
  
  public FloatCollection values()
  {
    new AbstractFloatCollection()
    {
      public boolean contains(float local_k)
      {
        return AbstractDouble2FloatMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractDouble2FloatMap.this.size();
      }
      
      public void clear()
      {
        AbstractDouble2FloatMap.this.clear();
      }
      
      public FloatIterator iterator()
      {
        new AbstractFloatIterator()
        {
          final ObjectIterator<Map.Entry<Double, Float>> field_52 = AbstractDouble2FloatMap.this.entrySet().iterator();
          
          public float nextFloat()
          {
            return ((Double2FloatMap.Entry)this.field_52.next()).getFloatValue();
          }
          
          public boolean hasNext()
          {
            return this.field_52.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Double, Float>> entrySet()
  {
    return double2FloatEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Double, Float>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Double, Float>> local_i = entrySet().iterator();
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
      Double2FloatMap.Entry local_e = (Double2FloatMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getDoubleKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getFloatValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Double2FloatMap.Entry
  {
    protected double key;
    protected float value;
    
    public BasicEntry(Double key, Float value)
    {
      this.key = key.doubleValue();
      this.value = value.floatValue();
    }
    
    public BasicEntry(double key, float value)
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
    
    public Float getValue()
    {
      return Float.valueOf(this.value);
    }
    
    public float getFloatValue()
    {
      return this.value;
    }
    
    public float setValue(float value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float setValue(Float value)
    {
      return Float.valueOf(setValue(value.floatValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == ((Double)local_e.getKey()).doubleValue()) && (this.value == ((Float)local_e.getValue()).floatValue());
    }
    
    public int hashCode()
    {
      return HashCommon.double2int(this.key) ^ HashCommon.float2int(this.value);
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2FloatMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */