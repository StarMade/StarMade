package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractByte2ReferenceMap<V>
  extends AbstractByte2ReferenceFunction<V>
  implements Byte2ReferenceMap<V>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(byte local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Byte, ? extends V> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Byte, ? extends V>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Byte2ReferenceMap)) {
      while (local_n-- != 0)
      {
        Byte2ReferenceMap.Entry<? extends V> local_e = (Byte2ReferenceMap.Entry)local_i.next();
        put(local_e.getByteKey(), local_e.getValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Byte, ? extends V> local_e = (Map.Entry)local_i.next();
        put((Byte)local_e.getKey(), local_e.getValue());
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
        return AbstractByte2ReferenceMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractByte2ReferenceMap.this.size();
      }
      
      public void clear()
      {
        AbstractByte2ReferenceMap.this.clear();
      }
      
      public ByteIterator iterator()
      {
        new AbstractByteIterator()
        {
          final ObjectIterator<Map.Entry<Byte, V>> field_58 = AbstractByte2ReferenceMap.this.entrySet().iterator();
          
          public byte nextByte()
          {
            return ((Byte2ReferenceMap.Entry)this.field_58.next()).getByteKey();
          }
          
          public boolean hasNext()
          {
            return this.field_58.hasNext();
          }
        };
      }
    };
  }
  
  public ReferenceCollection<V> values()
  {
    new AbstractReferenceCollection()
    {
      public boolean contains(Object local_k)
      {
        return AbstractByte2ReferenceMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractByte2ReferenceMap.this.size();
      }
      
      public void clear()
      {
        AbstractByte2ReferenceMap.this.clear();
      }
      
      public ObjectIterator<V> iterator()
      {
        new AbstractObjectIterator()
        {
          final ObjectIterator<Map.Entry<Byte, V>> field_3 = AbstractByte2ReferenceMap.this.entrySet().iterator();
          
          public V next()
          {
            return ((Byte2ReferenceMap.Entry)this.field_3.next()).getValue();
          }
          
          public boolean hasNext()
          {
            return this.field_3.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Byte, V>> entrySet()
  {
    return byte2ReferenceEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Byte, V>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Byte, V>> local_i = entrySet().iterator();
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
      Byte2ReferenceMap.Entry<V> local_e = (Byte2ReferenceMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getByteKey()));
      local_s.append("=>");
      if (this == local_e.getValue()) {
        local_s.append("(this map)");
      } else {
        local_s.append(String.valueOf(local_e.getValue()));
      }
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry<V>
    implements Byte2ReferenceMap.Entry<V>
  {
    protected byte key;
    protected V value;
    
    public BasicEntry(Byte key, V value)
    {
      this.key = key.byteValue();
      this.value = value;
    }
    
    public BasicEntry(byte key, V value)
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
    
    public V getValue()
    {
      return this.value;
    }
    
    public V setValue(V value)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == ((Byte)local_e.getKey()).byteValue()) && (this.value == local_e.getValue());
    }
    
    public int hashCode()
    {
      return this.key ^ (this.value == null ? 0 : System.identityHashCode(this.value));
    }
    
    public String toString()
    {
      return this.key + "->" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2ReferenceMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */