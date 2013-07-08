package it.unimi.dsi.fastutil.chars;

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

public abstract class AbstractChar2ByteMap
  extends AbstractChar2ByteFunction
  implements Char2ByteMap, Serializable
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
  
  public boolean containsKey(char local_k)
  {
    return keySet().contains(local_k);
  }
  
  public void putAll(Map<? extends Character, ? extends Byte> local_m)
  {
    int local_n = local_m.size();
    Iterator<? extends Map.Entry<? extends Character, ? extends Byte>> local_i = local_m.entrySet().iterator();
    if ((local_m instanceof Char2ByteMap)) {
      while (local_n-- != 0)
      {
        Char2ByteMap.Entry local_e = (Char2ByteMap.Entry)local_i.next();
        put(local_e.getCharKey(), local_e.getByteValue());
      }
    } else {
      while (local_n-- != 0)
      {
        Map.Entry<? extends Character, ? extends Byte> local_e = (Map.Entry)local_i.next();
        put((Character)local_e.getKey(), (Byte)local_e.getValue());
      }
    }
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public CharSet keySet()
  {
    new AbstractCharSet()
    {
      public boolean contains(char local_k)
      {
        return AbstractChar2ByteMap.this.containsKey(local_k);
      }
      
      public int size()
      {
        return AbstractChar2ByteMap.this.size();
      }
      
      public void clear()
      {
        AbstractChar2ByteMap.this.clear();
      }
      
      public CharIterator iterator()
      {
        new AbstractCharIterator()
        {
          final ObjectIterator<Map.Entry<Character, Byte>> field_67 = AbstractChar2ByteMap.this.entrySet().iterator();
          
          public char nextChar()
          {
            return ((Char2ByteMap.Entry)this.field_67.next()).getCharKey();
          }
          
          public boolean hasNext()
          {
            return this.field_67.hasNext();
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
        return AbstractChar2ByteMap.this.containsValue(local_k);
      }
      
      public int size()
      {
        return AbstractChar2ByteMap.this.size();
      }
      
      public void clear()
      {
        AbstractChar2ByteMap.this.clear();
      }
      
      public ByteIterator iterator()
      {
        new AbstractByteIterator()
        {
          final ObjectIterator<Map.Entry<Character, Byte>> field_58 = AbstractChar2ByteMap.this.entrySet().iterator();
          
          public byte nextByte()
          {
            return ((Char2ByteMap.Entry)this.field_58.next()).getByteValue();
          }
          
          public boolean hasNext()
          {
            return this.field_58.hasNext();
          }
        };
      }
    };
  }
  
  public ObjectSet<Map.Entry<Character, Byte>> entrySet()
  {
    return char2ByteEntrySet();
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_n = size();
    ObjectIterator<? extends Map.Entry<Character, Byte>> local_i = entrySet().iterator();
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
    ObjectIterator<? extends Map.Entry<Character, Byte>> local_i = entrySet().iterator();
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
      Char2ByteMap.Entry local_e = (Char2ByteMap.Entry)local_i.next();
      local_s.append(String.valueOf(local_e.getCharKey()));
      local_s.append("=>");
      local_s.append(String.valueOf(local_e.getByteValue()));
    }
    local_s.append("}");
    return local_s.toString();
  }
  
  public static class BasicEntry
    implements Char2ByteMap.Entry
  {
    protected char key;
    protected byte value;
    
    public BasicEntry(Character key, Byte value)
    {
      this.key = key.charValue();
      this.value = value.byteValue();
    }
    
    public BasicEntry(char key, byte value)
    {
      this.key = key;
      this.value = value;
    }
    
    public Character getKey()
    {
      return Character.valueOf(this.key);
    }
    
    public char getCharKey()
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
      return (this.key == ((Character)local_e.getKey()).charValue()) && (this.value == ((Byte)local_e.getValue()).byteValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ByteMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */