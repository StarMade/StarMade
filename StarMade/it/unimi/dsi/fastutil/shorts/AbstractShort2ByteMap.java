package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractShort2ByteMap
  extends AbstractShort2ByteFunction
  implements Short2ByteMap, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_ov)
  {
    return containsValue(((Byte)local_ov).byteValue());
  }
  
  public boolean containsValue(byte local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(short local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Short, ? extends Byte> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Short, ? extends Byte>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Short2ByteMap)) {
      while (local_n-- != 0)
      {
        Short2ByteMap.Entry local_e = (Short2ByteMap.Entry)local_i.next();
        put(local_e.getShortKey(), local_e.getByteValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Short, ? extends Byte> local_e = (Map.Entry)local_i.next();
        put((Short)local_e.getKey(), (Byte)local_e.getValue());
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
        return AbstractShort2ByteMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractShort2ByteMap.this.size();
      }
      
      public void clear()
      {
        AbstractShort2ByteMap.this.clear();
      }
      
      public ShortIterator iterator()
      {
        new AbstractShortIterator()
        {
          final ObjectIterator<Map.Entry<Short, Byte>> field_53 = AbstractShort2ByteMap.this.entrySet().iterator();
          
          public short nextShort()
          {
            return ((Short2ByteMap.Entry)this.field_53.next()).getShortKey();
          }
          
          public boolean hasNext()
          {
            return this.field_53.hasNext();
          }
        };
      }
    };
  }
  
  public ByteCollection values()
  {
    new AbstractByteCollection()
    {
      public boolean contains(byte local_k)
      {
        return AbstractShort2ByteMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractShort2ByteMap.this.size();
      }
      
      public void clear()
      {
        AbstractShort2ByteMap.this.clear();
      }
      
      public ByteIterator iterator()
      {
        new AbstractByteIterator()
        {
          final ObjectIterator<Map.Entry<Short, Byte>> field_58 = AbstractShort2ByteMap.this.entrySet().iterator();
          
          public byte nextByte()
          {
            return ((Short2ByteMap.Entry)this.field_58.next()).getByteValue();
          }
          
          public boolean hasNext()
          {
            return this.field_58.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Short, Byte>> entrySet()
  {
    return short2ByteEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Short, Byte>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Short, Byte>> local_i = entrySet().iterator();
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
      Short2ByteMap.Entry local_e = (Short2ByteMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getShortKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getByteValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Short2ByteMap.Entry
  {
    protected short key;
    protected byte value;
    
    public BasicEntry(Short key, Byte value)
    {
      this.key = key.shortValue();
      this.value = value.byteValue();
    }
    
    public BasicEntry(short key, byte value)
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
    
    public Byte getValue()
    {
      return Byte.valueOf(this.value);
    }
    
    public byte getByteValue()
    {
      return this.value;
    }
    
    public byte setValue(byte value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte setValue(Byte value)
    {
      return Byte.valueOf(setValue(value.byteValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == ((Short)local_e.getKey()).shortValue()) && (this.value == ((Byte)local_e.getValue()).byteValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ByteMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */