package it.unimi.dsi.fastutil.ints;

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

public abstract class AbstractInt2DoubleMap
  extends AbstractInt2DoubleFunction
  implements Int2DoubleMap, Serializable
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
  
  public boolean containsKey(int local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Integer, ? extends Double> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Integer, ? extends Double>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Int2DoubleMap)) {
      while (local_n-- != 0)
      {
        Int2DoubleMap.Entry local_e = (Int2DoubleMap.Entry)local_i.next();
        put(local_e.getIntKey(), local_e.getDoubleValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Integer, ? extends Double> local_e = (Map.Entry)local_i.next();
        put((Integer)local_e.getKey(), (Double)local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public IntSet keySet()
  {
    new AbstractIntSet()
    {
      public boolean contains(int local_k)
      {
        return AbstractInt2DoubleMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractInt2DoubleMap.this.size();
      }
      
      public void clear()
      {
        AbstractInt2DoubleMap.this.clear();
      }
      
      public IntIterator iterator()
      {
        new AbstractIntIterator()
        {
          final ObjectIterator<Map.Entry<Integer, Double>> field_70 = AbstractInt2DoubleMap.this.entrySet().iterator();
          
          public int nextInt()
          {
            return ((Int2DoubleMap.Entry)this.field_70.next()).getIntKey();
          }
          
          public boolean hasNext()
          {
            return this.field_70.hasNext();
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
        return AbstractInt2DoubleMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractInt2DoubleMap.this.size();
      }
      
      public void clear()
      {
        AbstractInt2DoubleMap.this.clear();
      }
      
      public DoubleIterator iterator()
      {
        new AbstractDoubleIterator()
        {
          final ObjectIterator<Map.Entry<Integer, Double>> field_68 = AbstractInt2DoubleMap.this.entrySet().iterator();
          
          public double nextDouble()
          {
            return ((Int2DoubleMap.Entry)this.field_68.next()).getDoubleValue();
          }
          
          public boolean hasNext()
          {
            return this.field_68.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Integer, Double>> entrySet()
  {
    return int2DoubleEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Integer, Double>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Integer, Double>> local_i = entrySet().iterator();
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
      Int2DoubleMap.Entry local_e = (Int2DoubleMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getIntKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getDoubleValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Int2DoubleMap.Entry
  {
    protected int key;
    protected double value;
    
    public BasicEntry(Integer key, Double value)
    {
      this.key = key.intValue();
      this.value = value.doubleValue();
    }
    
    public BasicEntry(int key, double value)
    {
      this.key = key;
      this.value = value;
    }
    
    public Integer getKey()
    {
      return Integer.valueOf(this.key);
    }
    
    public int getIntKey()
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
      return (this.key == ((Integer)local_e.getKey()).intValue()) && (this.value == ((Double)local_e.getValue()).doubleValue());
    }
    
    public int hashCode()
    {
      return this.key ^ HashCommon.double2int(this.value);
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2DoubleMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */