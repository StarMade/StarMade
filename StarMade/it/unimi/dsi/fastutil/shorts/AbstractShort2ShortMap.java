package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractShort2ShortMap
  extends AbstractShort2ShortFunction
  implements Short2ShortMap, Serializable
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
  
  public boolean containsKey(short local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Short, ? extends Short> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Short, ? extends Short>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Short2ShortMap)) {
      while (local_n-- != 0)
      {
        Short2ShortMap.Entry local_e = (Short2ShortMap.Entry)local_i.next();
        put(local_e.getShortKey(), local_e.getShortValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Short, ? extends Short> local_e = (Map.Entry)local_i.next();
        put((Short)local_e.getKey(), (Short)local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public ShortSet keySet()
  {
    new AbstractShortSet()
    {
      public boolean contains(short local_k)
      {
        return AbstractShort2ShortMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractShort2ShortMap.this.size();
      }
      
      public void clear()
      {
        AbstractShort2ShortMap.this.clear();
      }
      
      public ShortIterator iterator()
      {
        new AbstractShortIterator()
        {
          final ObjectIterator<Map.Entry<Short, Short>> field_53 = AbstractShort2ShortMap.this.entrySet().iterator();
          
          public short nextShort()
          {
            return ((Short2ShortMap.Entry)this.field_53.next()).getShortKey();
          }
          
          public boolean hasNext()
          {
            return this.field_53.hasNext();
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
        return AbstractShort2ShortMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractShort2ShortMap.this.size();
      }
      
      public void clear()
      {
        AbstractShort2ShortMap.this.clear();
      }
      
      public ShortIterator iterator()
      {
        new AbstractShortIterator()
        {
          final ObjectIterator<Map.Entry<Short, Short>> field_53 = AbstractShort2ShortMap.this.entrySet().iterator();
          
          public short nextShort()
          {
            return ((Short2ShortMap.Entry)this.field_53.next()).getShortValue();
          }
          
          public boolean hasNext()
          {
            return this.field_53.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Short, Short>> entrySet()
  {
    return short2ShortEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Short, Short>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Short, Short>> local_i = entrySet().iterator();
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
      Short2ShortMap.Entry local_e = (Short2ShortMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getShortKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getShortValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Short2ShortMap.Entry
  {
    protected short key;
    protected short value;
    
    public BasicEntry(Short key, Short value)
    {
      this.key = key.shortValue();
      this.value = value.shortValue();
    }
    
    public BasicEntry(short key, short value)
    {
      this.key = key;
      this.value = value;
    }
    
    public Short getKey()
    {
      return Short.valueOf(this.key);
    }
    
    public short getShortKey()
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
      return (this.key == ((Short)local_e.getKey()).shortValue()) && (this.value == ((Short)local_e.getValue()).shortValue());
    }
    
    public int hashCode()
    {
      return this.key ^ this.value;
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ShortMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */