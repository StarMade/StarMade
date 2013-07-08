package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.HashCommon;
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

public abstract class AbstractLong2ByteMap
  extends AbstractLong2ByteFunction
  implements Long2ByteMap, Serializable
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
  
  public boolean containsKey(long local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Long, ? extends Byte> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Long, ? extends Byte>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Long2ByteMap)) {
      while (local_n-- != 0)
      {
        Long2ByteMap.Entry local_e = (Long2ByteMap.Entry)local_i.next();
        put(local_e.getLongKey(), local_e.getByteValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Long, ? extends Byte> local_e = (Map.Entry)local_i.next();
        put((Long)local_e.getKey(), (Byte)local_e.getValue());
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
        return AbstractLong2ByteMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractLong2ByteMap.this.size();
      }
      
      public void clear()
      {
        AbstractLong2ByteMap.this.clear();
      }
      
      public LongIterator iterator()
      {
        new AbstractLongIterator()
        {
          final ObjectIterator<Map.Entry<Long, Byte>> field_1 = AbstractLong2ByteMap.this.entrySet().iterator();
          
          public long nextLong()
          {
            return ((Long2ByteMap.Entry)this.field_1.next()).getLongKey();
          }
          
          public boolean hasNext()
          {
            return this.field_1.hasNext();
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
        return AbstractLong2ByteMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractLong2ByteMap.this.size();
      }
      
      public void clear()
      {
        AbstractLong2ByteMap.this.clear();
      }
      
      public ByteIterator iterator()
      {
        new AbstractByteIterator()
        {
          final ObjectIterator<Map.Entry<Long, Byte>> field_58 = AbstractLong2ByteMap.this.entrySet().iterator();
          
          public byte nextByte()
          {
            return ((Long2ByteMap.Entry)this.field_58.next()).getByteValue();
          }
          
          public boolean hasNext()
          {
            return this.field_58.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Long, Byte>> entrySet()
  {
    return long2ByteEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Long, Byte>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Long, Byte>> local_i = entrySet().iterator();
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
      Long2ByteMap.Entry local_e = (Long2ByteMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getLongKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getByteValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Long2ByteMap.Entry
  {
    protected long key;
    protected byte value;
    
    public BasicEntry(Long key, Byte value)
    {
      this.key = key.longValue();
      this.value = value.byteValue();
    }
    
    public BasicEntry(long key, byte value)
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
      return (this.key == ((Long)local_e.getKey()).longValue()) && (this.value == ((Byte)local_e.getValue()).byteValue());
    }
    
    public int hashCode()
    {
      return HashCommon.long2int(this.key) ^ this.value;
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2ByteMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */