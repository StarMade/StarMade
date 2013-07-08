package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractLong2LongMap
  extends AbstractLong2LongFunction
  implements Long2LongMap, Serializable
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
  
  public boolean containsKey(long local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Long, ? extends Long> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Long, ? extends Long>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Long2LongMap)) {
      while (local_n-- != 0)
      {
        Long2LongMap.Entry local_e = (Long2LongMap.Entry)local_i.next();
        put(local_e.getLongKey(), local_e.getLongValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Long, ? extends Long> local_e = (Map.Entry)local_i.next();
        put((Long)local_e.getKey(), (Long)local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public LongSet keySet()
  {
    new AbstractLongSet()
    {
      public boolean contains(long local_k)
      {
        return AbstractLong2LongMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractLong2LongMap.this.size();
      }
      
      public void clear()
      {
        AbstractLong2LongMap.this.clear();
      }
      
      public LongIterator iterator()
      {
        new AbstractLongIterator()
        {
          final ObjectIterator<Map.Entry<Long, Long>> field_1 = AbstractLong2LongMap.this.entrySet().iterator();
          
          public long nextLong()
          {
            return ((Long2LongMap.Entry)this.field_1.next()).getLongKey();
          }
          
          public boolean hasNext()
          {
            return this.field_1.hasNext();
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
        return AbstractLong2LongMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractLong2LongMap.this.size();
      }
      
      public void clear()
      {
        AbstractLong2LongMap.this.clear();
      }
      
      public LongIterator iterator()
      {
        new AbstractLongIterator()
        {
          final ObjectIterator<Map.Entry<Long, Long>> field_1 = AbstractLong2LongMap.this.entrySet().iterator();
          
          public long nextLong()
          {
            return ((Long2LongMap.Entry)this.field_1.next()).getLongValue();
          }
          
          public boolean hasNext()
          {
            return this.field_1.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Long, Long>> entrySet()
  {
    return long2LongEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Long, Long>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Long, Long>> local_i = entrySet().iterator();
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
      Long2LongMap.Entry local_e = (Long2LongMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getLongKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getLongValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Long2LongMap.Entry
  {
    protected long key;
    protected long value;
    
    public BasicEntry(Long key, Long value)
    {
      this.key = key.longValue();
      this.value = value.longValue();
    }
    
    public BasicEntry(long key, long value)
    {
      this.key = key;
      this.value = value;
    }
    
    public Long getKey()
    {
      return Long.valueOf(this.key);
    }
    
    public long getLongKey()
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
      return (this.key == ((Long)local_e.getKey()).longValue()) && (this.value == ((Long)local_e.getValue()).longValue());
    }
    
    public int hashCode()
    {
      return HashCommon.long2int(this.key) ^ HashCommon.long2int(this.value);
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2LongMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */