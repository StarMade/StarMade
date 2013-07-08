package it.unimi.dsi.fastutil.bytes;

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

public abstract class AbstractByte2BooleanMap
  extends AbstractByte2BooleanFunction
  implements Byte2BooleanMap, Serializable
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
  
  public boolean containsKey(byte local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Byte, ? extends Boolean> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Byte, ? extends Boolean>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Byte2BooleanMap)) {
      while (local_n-- != 0)
      {
        Byte2BooleanMap.Entry local_e = (Byte2BooleanMap.Entry)local_i.next();
        put(local_e.getByteKey(), local_e.getBooleanValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Byte, ? extends Boolean> local_e = (Map.Entry)local_i.next();
        put((Byte)local_e.getKey(), (Boolean)local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public ByteSet keySet()
  {
    new AbstractByteSet()
    {
      public boolean contains(byte local_k)
      {
        return AbstractByte2BooleanMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractByte2BooleanMap.this.size();
      }
      
      public void clear()
      {
        AbstractByte2BooleanMap.this.clear();
      }
      
      public ByteIterator iterator()
      {
        new AbstractByteIterator()
        {
          final ObjectIterator<Map.Entry<Byte, Boolean>> field_58 = AbstractByte2BooleanMap.this.entrySet().iterator();
          
          public byte nextByte()
          {
            return ((Byte2BooleanMap.Entry)this.field_58.next()).getByteKey();
          }
          
          public boolean hasNext()
          {
            return this.field_58.hasNext();
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
        return AbstractByte2BooleanMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractByte2BooleanMap.this.size();
      }
      
      public void clear()
      {
        AbstractByte2BooleanMap.this.clear();
      }
      
      public BooleanIterator iterator()
      {
        new AbstractBooleanIterator()
        {
          final ObjectIterator<Map.Entry<Byte, Boolean>> field_60 = AbstractByte2BooleanMap.this.entrySet().iterator();
          
          public boolean nextBoolean()
          {
            return ((Byte2BooleanMap.Entry)this.field_60.next()).getBooleanValue();
          }
          
          public boolean hasNext()
          {
            return this.field_60.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Byte, Boolean>> entrySet()
  {
    return byte2BooleanEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Byte, Boolean>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Byte, Boolean>> local_i = entrySet().iterator();
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
      Byte2BooleanMap.Entry local_e = (Byte2BooleanMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getByteKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getBooleanValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Byte2BooleanMap.Entry
  {
    protected byte key;
    protected boolean value;
    
    public BasicEntry(Byte key, Boolean value)
    {
      this.key = key.byteValue();
      this.value = value.booleanValue();
    }
    
    public BasicEntry(byte key, boolean value)
    {
      this.key = key;
      this.value = value;
    }
    
    public Byte getKey()
    {
      return Byte.valueOf(this.key);
    }
    
    public byte getByteKey()
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
      return (this.key == ((Byte)local_e.getKey()).byteValue()) && (this.value == ((Boolean)local_e.getValue()).booleanValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2BooleanMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */