package it.unimi.dsi.fastutil.longs;

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

public abstract class AbstractLong2FloatMap
  extends AbstractLong2FloatFunction
  implements Long2FloatMap, Serializable
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
  
  public boolean containsKey(long local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Long, ? extends Float> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Long, ? extends Float>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Long2FloatMap)) {
      while (local_n-- != 0)
      {
        Long2FloatMap.Entry local_e = (Long2FloatMap.Entry)local_i.next();
        put(local_e.getLongKey(), local_e.getFloatValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Long, ? extends Float> local_e = (Map.Entry)local_i.next();
        put((Long)local_e.getKey(), (Float)local_e.getValue());
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
        return AbstractLong2FloatMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractLong2FloatMap.this.size();
      }
      
      public void clear()
      {
        AbstractLong2FloatMap.this.clear();
      }
      
      public LongIterator iterator()
      {
        new AbstractLongIterator()
        {
          final ObjectIterator<Map.Entry<Long, Float>> field_1 = AbstractLong2FloatMap.this.entrySet().iterator();
          
          public long nextLong()
          {
            return ((Long2FloatMap.Entry)this.field_1.next()).getLongKey();
          }
          
          public boolean hasNext()
          {
            return this.field_1.hasNext();
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
        return AbstractLong2FloatMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractLong2FloatMap.this.size();
      }
      
      public void clear()
      {
        AbstractLong2FloatMap.this.clear();
      }
      
      public FloatIterator iterator()
      {
        new AbstractFloatIterator()
        {
          final ObjectIterator<Map.Entry<Long, Float>> field_52 = AbstractLong2FloatMap.this.entrySet().iterator();
          
          public float nextFloat()
          {
            return ((Long2FloatMap.Entry)this.field_52.next()).getFloatValue();
          }
          
          public boolean hasNext()
          {
            return this.field_52.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Long, Float>> entrySet()
  {
    return long2FloatEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Long, Float>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Long, Float>> local_i = entrySet().iterator();
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
      Long2FloatMap.Entry local_e = (Long2FloatMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getLongKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getFloatValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Long2FloatMap.Entry
  {
    protected long key;
    protected float value;
    
    public BasicEntry(Long key, Float value)
    {
      this.key = key.longValue();
      this.value = value.floatValue();
    }
    
    public BasicEntry(long key, float value)
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
      return (this.key == ((Long)local_e.getKey()).longValue()) && (this.value == ((Float)local_e.getValue()).floatValue());
    }
    
    public int hashCode()
    {
      return HashCommon.long2int(this.key) ^ HashCommon.float2int(this.value);
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2FloatMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */