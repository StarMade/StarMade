package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractFloat2ShortMap
  extends AbstractFloat2ShortFunction
  implements Float2ShortMap, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_ov)
  {
    return containsValue(((Short)local_ov).shortValue());
  }
  
  public boolean containsValue(short local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(float local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Float, ? extends Short> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Float, ? extends Short>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Float2ShortMap)) {
      while (local_n-- != 0)
      {
        Float2ShortMap.Entry local_e = (Float2ShortMap.Entry)local_i.next();
        put(local_e.getFloatKey(), local_e.getShortValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Float, ? extends Short> local_e = (Map.Entry)local_i.next();
        put((Float)local_e.getKey(), (Short)local_e.getValue());
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
        return AbstractFloat2ShortMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractFloat2ShortMap.this.size();
      }
      
      public void clear()
      {
        AbstractFloat2ShortMap.this.clear();
      }
      
      public FloatIterator iterator()
      {
        new AbstractFloatIterator()
        {
          final ObjectIterator<Map.Entry<Float, Short>> field_52 = AbstractFloat2ShortMap.this.entrySet().iterator();
          
          public float nextFloat()
          {
            return ((Float2ShortMap.Entry)this.field_52.next()).getFloatKey();
          }
          
          public boolean hasNext()
          {
            return this.field_52.hasNext();
          }
        };
      }
    };
  }
  
  public ShortCollection values()
  {
    new AbstractShortCollection()
    {
      public boolean contains(short local_k)
      {
        return AbstractFloat2ShortMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractFloat2ShortMap.this.size();
      }
      
      public void clear()
      {
        AbstractFloat2ShortMap.this.clear();
      }
      
      public ShortIterator iterator()
      {
        new AbstractShortIterator()
        {
          final ObjectIterator<Map.Entry<Float, Short>> field_53 = AbstractFloat2ShortMap.this.entrySet().iterator();
          
          public short nextShort()
          {
            return ((Float2ShortMap.Entry)this.field_53.next()).getShortValue();
          }
          
          public boolean hasNext()
          {
            return this.field_53.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Float, Short>> entrySet()
  {
    return float2ShortEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Float, Short>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Float, Short>> local_i = entrySet().iterator();
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
      Float2ShortMap.Entry local_e = (Float2ShortMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getFloatKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getShortValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Float2ShortMap.Entry
  {
    protected float key;
    protected short value;
    
    public BasicEntry(Float key, Short value)
    {
      this.key = key.floatValue();
      this.value = value.shortValue();
    }
    
    public BasicEntry(float key, short value)
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
    
    public Short getValue()
    {
      return Short.valueOf(this.value);
    }
    
    public short getShortValue()
    {
      return this.value;
    }
    
    public short setValue(short value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short setValue(Short value)
    {
      return Short.valueOf(setValue(value.shortValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == ((Float)local_e.getKey()).floatValue()) && (this.value == ((Short)local_e.getValue()).shortValue());
    }
    
    public int hashCode()
    {
      return HashCommon.float2int(this.key) ^ this.value;
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2ShortMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */