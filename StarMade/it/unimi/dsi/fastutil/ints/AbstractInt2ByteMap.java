package it.unimi.dsi.fastutil.ints;

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

public abstract class AbstractInt2ByteMap
  extends AbstractInt2ByteFunction
  implements Int2ByteMap, Serializable
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
  
  public boolean containsKey(int local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Integer, ? extends Byte> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Integer, ? extends Byte>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Int2ByteMap)) {
      while (local_n-- != 0)
      {
        Int2ByteMap.Entry local_e = (Int2ByteMap.Entry)local_i.next();
        put(local_e.getIntKey(), local_e.getByteValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Integer, ? extends Byte> local_e = (Map.Entry)local_i.next();
        put((Integer)local_e.getKey(), (Byte)local_e.getValue());
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
        return AbstractInt2ByteMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractInt2ByteMap.this.size();
      }
      
      public void clear()
      {
        AbstractInt2ByteMap.this.clear();
      }
      
      public IntIterator iterator()
      {
        new AbstractIntIterator()
        {
          final ObjectIterator<Map.Entry<Integer, Byte>> field_70 = AbstractInt2ByteMap.this.entrySet().iterator();
          
          public int nextInt()
          {
            return ((Int2ByteMap.Entry)this.field_70.next()).getIntKey();
          }
          
          public boolean hasNext()
          {
            return this.field_70.hasNext();
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
        return AbstractInt2ByteMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractInt2ByteMap.this.size();
      }
      
      public void clear()
      {
        AbstractInt2ByteMap.this.clear();
      }
      
      public ByteIterator iterator()
      {
        new AbstractByteIterator()
        {
          final ObjectIterator<Map.Entry<Integer, Byte>> field_58 = AbstractInt2ByteMap.this.entrySet().iterator();
          
          public byte nextByte()
          {
            return ((Int2ByteMap.Entry)this.field_58.next()).getByteValue();
          }
          
          public boolean hasNext()
          {
            return this.field_58.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Integer, Byte>> entrySet()
  {
    return int2ByteEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Integer, Byte>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Integer, Byte>> local_i = entrySet().iterator();
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
      Int2ByteMap.Entry local_e = (Int2ByteMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getIntKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getByteValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Int2ByteMap.Entry
  {
    protected int key;
    protected byte value;
    
    public BasicEntry(Integer key, Byte value)
    {
      this.key = key.intValue();
      this.value = value.byteValue();
    }
    
    public BasicEntry(int key, byte value)
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
      return (this.key == ((Integer)local_e.getKey()).intValue()) && (this.value == ((Byte)local_e.getValue()).byteValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ByteMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */