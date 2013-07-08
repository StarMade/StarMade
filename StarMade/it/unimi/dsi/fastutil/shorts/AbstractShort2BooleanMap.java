package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractShort2BooleanMap
  extends AbstractShort2BooleanFunction
  implements Short2BooleanMap, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_ov)
  {
    return containsValue(((Boolean)local_ov).booleanValue());
  }
  
  public boolean containsValue(boolean local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(short local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Short, ? extends Boolean> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Short, ? extends Boolean>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Short2BooleanMap)) {
      while (local_n-- != 0)
      {
        Short2BooleanMap.Entry local_e = (Short2BooleanMap.Entry)local_i.next();
        put(local_e.getShortKey(), local_e.getBooleanValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Short, ? extends Boolean> local_e = (Map.Entry)local_i.next();
        put((Short)local_e.getKey(), (Boolean)local_e.getValue());
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
        return AbstractShort2BooleanMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractShort2BooleanMap.this.size();
      }
      
      public void clear()
      {
        AbstractShort2BooleanMap.this.clear();
      }
      
      public ShortIterator iterator()
      {
        new AbstractShortIterator()
        {
          final ObjectIterator<Map.Entry<Short, Boolean>> field_53 = AbstractShort2BooleanMap.this.entrySet().iterator();
          
          public short nextShort()
          {
            return ((Short2BooleanMap.Entry)this.field_53.next()).getShortKey();
          }
          
          public boolean hasNext()
          {
            return this.field_53.hasNext();
          }
        };
      }
    };
  }
  
  public BooleanCollection values()
  {
    new AbstractBooleanCollection()
    {
      public boolean contains(boolean local_k)
      {
        return AbstractShort2BooleanMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractShort2BooleanMap.this.size();
      }
      
      public void clear()
      {
        AbstractShort2BooleanMap.this.clear();
      }
      
      public BooleanIterator iterator()
      {
        new AbstractBooleanIterator()
        {
          final ObjectIterator<Map.Entry<Short, Boolean>> field_60 = AbstractShort2BooleanMap.this.entrySet().iterator();
          
          public boolean nextBoolean()
          {
            return ((Short2BooleanMap.Entry)this.field_60.next()).getBooleanValue();
          }
          
          public boolean hasNext()
          {
            return this.field_60.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Short, Boolean>> entrySet()
  {
    return short2BooleanEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Short, Boolean>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Short, Boolean>> local_i = entrySet().iterator();
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
      Short2BooleanMap.Entry local_e = (Short2BooleanMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getShortKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getBooleanValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Short2BooleanMap.Entry
  {
    protected short key;
    protected boolean value;
    
    public BasicEntry(Short key, Boolean value)
    {
      this.key = key.shortValue();
      this.value = value.booleanValue();
    }
    
    public BasicEntry(short key, boolean value)
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
    
    public Boolean getValue()
    {
      return Boolean.valueOf(this.value);
    }
    
    public boolean getBooleanValue()
    {
      return this.value;
    }
    
    public boolean setValue(boolean value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean setValue(Boolean value)
    {
      return Boolean.valueOf(setValue(value.booleanValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == ((Short)local_e.getKey()).shortValue()) && (this.value == ((Boolean)local_e.getValue()).booleanValue());
    }
    
    public int hashCode()
    {
      return this.key ^ (this.value ? 1231 : 1237);
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2BooleanMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */