package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractDouble2IntMap
  extends AbstractDouble2IntFunction
  implements Double2IntMap, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_ov)
  {
    return containsValue(((Integer)local_ov).intValue());
  }
  
  public boolean containsValue(int local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(double local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Double, ? extends Integer> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Double, ? extends Integer>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Double2IntMap)) {
      while (local_n-- != 0)
      {
        Double2IntMap.Entry local_e = (Double2IntMap.Entry)local_i.next();
        put(local_e.getDoubleKey(), local_e.getIntValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Double, ? extends Integer> local_e = (Map.Entry)local_i.next();
        put((Double)local_e.getKey(), (Integer)local_e.getValue());
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
        return AbstractDouble2IntMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractDouble2IntMap.this.size();
      }
      
      public void clear()
      {
        AbstractDouble2IntMap.this.clear();
      }
      
      public DoubleIterator iterator()
      {
        new AbstractDoubleIterator()
        {
          final ObjectIterator<Map.Entry<Double, Integer>> field_68 = AbstractDouble2IntMap.this.entrySet().iterator();
          
          public double nextDouble()
          {
            return ((Double2IntMap.Entry)this.field_68.next()).getDoubleKey();
          }
          
          public boolean hasNext()
          {
            return this.field_68.hasNext();
          }
        };
      }
    };
  }
  
  public IntCollection values()
  {
    new AbstractIntCollection()
    {
      public boolean contains(int local_k)
      {
        return AbstractDouble2IntMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractDouble2IntMap.this.size();
      }
      
      public void clear()
      {
        AbstractDouble2IntMap.this.clear();
      }
      
      public IntIterator iterator()
      {
        new AbstractIntIterator()
        {
          final ObjectIterator<Map.Entry<Double, Integer>> field_70 = AbstractDouble2IntMap.this.entrySet().iterator();
          
          public int nextInt()
          {
            return ((Double2IntMap.Entry)this.field_70.next()).getIntValue();
          }
          
          public boolean hasNext()
          {
            return this.field_70.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Double, Integer>> entrySet()
  {
    return double2IntEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Double, Integer>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Double, Integer>> local_i = entrySet().iterator();
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
      Double2IntMap.Entry local_e = (Double2IntMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getDoubleKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getIntValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Double2IntMap.Entry
  {
    protected double key;
    protected int value;
    
    public BasicEntry(Double key, Integer value)
    {
      this.key = key.doubleValue();
      this.value = value.intValue();
    }
    
    public BasicEntry(double key, int value)
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
    
    public Integer getValue()
    {
      return Integer.valueOf(this.value);
    }
    
    public int getIntValue()
    {
      return this.value;
    }
    
    public int setValue(int value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Integer setValue(Integer value)
    {
      return Integer.valueOf(setValue(value.intValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == ((Double)local_e.getKey()).doubleValue()) && (this.value == ((Integer)local_e.getValue()).intValue());
    }
    
    public int hashCode()
    {
      return HashCommon.double2int(this.key) ^ this.value;
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2IntMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */