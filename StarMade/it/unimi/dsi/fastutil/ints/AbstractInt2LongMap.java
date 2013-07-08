package it.unimi.dsi.fastutil.ints;

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

public abstract class AbstractInt2LongMap
  extends AbstractInt2LongFunction
  implements Int2LongMap, Serializable
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
  
  public boolean containsKey(int local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Integer, ? extends Long> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Integer, ? extends Long>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Int2LongMap)) {
      while (local_n-- != 0)
      {
        Int2LongMap.Entry local_e = (Int2LongMap.Entry)local_i.next();
        put(local_e.getIntKey(), local_e.getLongValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Integer, ? extends Long> local_e = (Map.Entry)local_i.next();
        put((Integer)local_e.getKey(), (Long)local_e.getValue());
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
        return AbstractInt2LongMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractInt2LongMap.this.size();
      }
      
      public void clear()
      {
        AbstractInt2LongMap.this.clear();
      }
      
      public IntIterator iterator()
      {
        new AbstractIntIterator()
        {
          final ObjectIterator<Map.Entry<Integer, Long>> field_70 = AbstractInt2LongMap.this.entrySet().iterator();
          
          public int nextInt()
          {
            return ((Int2LongMap.Entry)this.field_70.next()).getIntKey();
          }
          
          public boolean hasNext()
          {
            return this.field_70.hasNext();
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
        return AbstractInt2LongMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractInt2LongMap.this.size();
      }
      
      public void clear()
      {
        AbstractInt2LongMap.this.clear();
      }
      
      public LongIterator iterator()
      {
        new AbstractLongIterator()
        {
          final ObjectIterator<Map.Entry<Integer, Long>> field_1 = AbstractInt2LongMap.this.entrySet().iterator();
          
          public long nextLong()
          {
            return ((Int2LongMap.Entry)this.field_1.next()).getLongValue();
          }
          
          public boolean hasNext()
          {
            return this.field_1.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Integer, Long>> entrySet()
  {
    return int2LongEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Integer, Long>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Integer, Long>> local_i = entrySet().iterator();
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
      Int2LongMap.Entry local_e = (Int2LongMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getIntKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getLongValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Int2LongMap.Entry
  {
    protected int key;
    protected long value;
    
    public BasicEntry(Integer key, Long value)
    {
      this.key = key.intValue();
      this.value = value.longValue();
    }
    
    public BasicEntry(int key, long value)
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
      return (this.key == ((Integer)local_e.getKey()).intValue()) && (this.value == ((Long)local_e.getValue()).longValue());
    }
    
    public int hashCode()
    {
      return this.key ^ HashCommon.long2int(this.value);
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2LongMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */