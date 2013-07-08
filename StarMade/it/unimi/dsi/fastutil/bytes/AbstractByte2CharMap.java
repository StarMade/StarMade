package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractByte2CharMap
  extends AbstractByte2CharFunction
  implements Byte2CharMap, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  
  public boolean containsValue(Object local_ov)
  {
    return containsValue(((Character)local_ov).charValue());
  }
  
  public boolean containsValue(char local_v)
  {
    return values().contains(local_v);
  }
  
  public boolean containsKey(byte local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Byte, ? extends Character> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Byte, ? extends Character>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Byte2CharMap)) {
      while (local_n-- != 0)
      {
        Byte2CharMap.Entry local_e = (Byte2CharMap.Entry)local_i.next();
        put(local_e.getByteKey(), local_e.getCharValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Byte, ? extends Character> local_e = (Map.Entry)local_i.next();
        put((Byte)local_e.getKey(), (Character)local_e.getValue());
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
        return AbstractByte2CharMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractByte2CharMap.this.size();
      }
      
      public void clear()
      {
        AbstractByte2CharMap.this.clear();
      }
      
      public ByteIterator iterator()
      {
        new AbstractByteIterator()
        {
          final ObjectIterator<Map.Entry<Byte, Character>> field_58 = AbstractByte2CharMap.this.entrySet().iterator();
          
          public byte nextByte()
          {
            return ((Byte2CharMap.Entry)this.field_58.next()).getByteKey();
          }
          
          public boolean hasNext()
          {
            return this.field_58.hasNext();
          }
        };
      }
    };
  }
  
  public CharCollection values()
  {
    new AbstractCharCollection()
    {
      public boolean contains(char local_k)
      {
        return AbstractByte2CharMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractByte2CharMap.this.size();
      }
      
      public void clear()
      {
        AbstractByte2CharMap.this.clear();
      }
      
      public CharIterator iterator()
      {
        new AbstractCharIterator()
        {
          final ObjectIterator<Map.Entry<Byte, Character>> field_67 = AbstractByte2CharMap.this.entrySet().iterator();
          
          public char nextChar()
          {
            return ((Byte2CharMap.Entry)this.field_67.next()).getCharValue();
          }
          
          public boolean hasNext()
          {
            return this.field_67.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Byte, Character>> entrySet()
  {
    return byte2CharEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Byte, Character>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Byte, Character>> local_i = entrySet().iterator();
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
      Byte2CharMap.Entry local_e = (Byte2CharMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getByteKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getCharValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Byte2CharMap.Entry
  {
    protected byte key;
    protected char value;
    
    public BasicEntry(Byte key, Character value)
    {
      this.key = key.byteValue();
      this.value = value.charValue();
    }
    
    public BasicEntry(byte key, char value)
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
    
    public Character getValue()
    {
      return Character.valueOf(this.value);
    }
    
    public char getCharValue()
    {
      return this.value;
    }
    
    public char setValue(char value)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character setValue(Character value)
    {
      return Character.valueOf(setValue(value.charValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<?, ?> local_e = (Map.Entry)local_o;
      return (this.key == ((Byte)local_e.getKey()).byteValue()) && (this.value == ((Character)local_e.getValue()).charValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2CharMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */