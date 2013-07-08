package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractDouble2LongMap
  extends AbstractDouble2LongFunction
  implements Double2LongMap, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_ov)
  {
    return containsValue(((Long)local_ov).longValue());
  }
  
  public boolean containsValue(long local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(double local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Double, ? extends Long> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Double, ? extends Long>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Double2LongMap)) {
      while (local_n-- != 0)
      {
        Double2LongMap.Entry local_e = (Double2LongMap.Entry)local_i.next();
        put(local_e.getDoubleKey(), local_e.getLongValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Double, ? extends Long> local_e = (Map.Entry)local_i.next();
        put((Double)local_e.getKey(), (Long)local_e.getValue());
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
        return AbstractDouble2LongMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractDouble2LongMap.this.size();
      }
      
      public void clear()
      {
        AbstractDouble2LongMap.this.clear();
      }
      
      public DoubleIterator iterator()
      {
        new AbstractDoubleIterator()
        {
          final ObjectIterator<Map.Entry<Double, Long>> field_68 = AbstractDouble2LongMap.this.entrySet().iterator();
          
          public double nextDouble()
          {
            return ((Double2LongMap.Entry)this.field_68.next()).getDoubleKey();
          }
          
          public boolean hasNext()
          {
            return this.field_68.hasNext();
          }
        };
      }
    };
  }
  
  public LongCollection values()
  {
    new AbstractLongCollection()
    {
      public boolean contains(long local_k)
      {
        return AbstractDouble2LongMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractDouble2LongMap.this.size();
      }
      
      public void clear()
      {
        AbstractDouble2LongMap.this.clear();
      }
      
      public LongIterator iterator()
      {
        new AbstractLongIterator()
        {
          final ObjectIterator<Map.Entry<Double, Long>> field_1 = AbstractDouble2LongMap.this.entrySet().iterator();
          
          public long nextLong()
          {
            return ((Double2LongMap.Entry)this.field_1.next()).getLongValue();
          }
          
          public boolean hasNext()
          {
            return this.field_1.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Double, Long>> entrySet()
  {
    return double2LongEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Double, Long>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Double, Long>> local_i = entrySet().iterator();
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
      Double2LongMap.Entry local_e = (Double2LongMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getDoubleKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getLongValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Double2LongMap.Entry
  {
    protected double key;
    protected long value;
    
    public BasicEntry(Double key, Long value)
    {
      this.key = key.doubleValue();
      this.value = value.longValue();
    }
    
    public BasicEntry(double key, long value)
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
    
    public Long getValue()
    {
      return Long.valueOf(this.value);
    }
    
    public long getLongValue()
    {
      return this.value;
    }
    
    public long setValue(long value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Long setValue(Long value)
    {
      return Long.valueOf(setValue(value.longValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == ((Double)local_e.getKey()).doubleValue()) && (this.value == ((Long)local_e.getValue()).longValue());
    }
    
    public int hashCode()
    {
      return HashCommon.double2int(this.key) ^ HashCommon.long2int(this.value);
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2LongMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */