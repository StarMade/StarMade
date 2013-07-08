package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.booleans.BooleanArraySet;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanCollections;
import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Char2BooleanArrayMap
  extends AbstractChar2BooleanMap
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient char[] key;
  private transient boolean[] value;
  private int size;
  
  public Char2BooleanArrayMap(char[] key, boolean[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Char2BooleanArrayMap()
  {
    this.key = CharArrays.EMPTY_ARRAY;
    this.value = BooleanArrays.EMPTY_ARRAY;
  }
  
  public Char2BooleanArrayMap(int capacity)
  {
    this.key = new char[capacity];
    this.value = new boolean[capacity];
  }
  
  public Char2BooleanArrayMap(Char2BooleanMap local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Char2BooleanArrayMap(Map<? extends Character, ? extends Boolean> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Char2BooleanArrayMap(char[] key, boolean[] value, int size)
  {
    this.key = key;
    this.value = value;
    this.size = size;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
    if (size > key.length) {
      throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")");
    }
  }
  
  public Char2BooleanMap.FastEntrySet char2BooleanEntrySet()
  {
    return new EntrySet(null);
  }
  
  private int findKey(char local_k)
  {
    char[] key = this.key;
    int local_i = this.size;
    while (local_i-- != 0) {
      if (key[local_i] == local_k) {
        return local_i;
      }
    }
    return -1;
  }
  
  public boolean get(char local_k)
  {
    char[] key = this.key;
    int local_i = this.size;
    while (local_i-- != 0) {
      if (key[local_i] == local_k) {
        return this.value[local_i];
      }
    }
    return this.defRetValue;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public void clear()
  {
    this.size = 0;
  }
  
  public boolean containsKey(char local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public boolean containsValue(boolean local_v)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.value[local_i] == local_v) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  public boolean put(char local_k, boolean local_v)
  {
    int oldKey = findKey(local_k);
    if (oldKey != -1)
    {
      boolean oldValue = this.value[oldKey];
      this.value[oldKey] = local_v;
      return oldValue;
    }
    if (this.size == this.key.length)
    {
      char[] oldValue = new char[this.size == 0 ? 2 : this.size * 2];
      boolean[] newValue = new boolean[this.size == 0 ? 2 : this.size * 2];
      int local_i = this.size;
      while (local_i-- != 0)
      {
        oldValue[local_i] = this.key[local_i];
        newValue[local_i] = this.value[local_i];
      }
      this.key = oldValue;
      this.value = newValue;
    }
    this.key[this.size] = local_k;
    this.value[this.size] = local_v;
    this.size += 1;
    return this.defRetValue;
  }
  
  public boolean remove(char local_k)
  {
    int oldPos = findKey(local_k);
    if (oldPos == -1) {
      return this.defRetValue;
    }
    boolean oldValue = this.value[oldPos];
    int tail = this.size - oldPos - 1;
    for (int local_i = 0; local_i < tail; local_i++)
    {
      this.key[(oldPos + local_i)] = this.key[(oldPos + local_i + 1)];
      this.value[(oldPos + local_i)] = this.value[(oldPos + local_i + 1)];
    }
    this.size -= 1;
    return oldValue;
  }
  
  public CharSet keySet()
  {
    return new CharArraySet(this.key, this.size);
  }
  
  public BooleanCollection values()
  {
    return BooleanCollections.unmodifiable(new BooleanArraySet(this.value, this.size));
  }
  
  public Char2BooleanArrayMap clone()
  {
    Char2BooleanArrayMap local_c;
    try
    {
      local_c = (Char2BooleanArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((char[])this.key.clone());
    local_c.value = ((boolean[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeChar(this.key[local_i]);
      local_s.writeBoolean(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new char[this.size];
    this.value = new boolean[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readChar();
      this.value[local_i] = local_s.readBoolean();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Char2BooleanMap.Entry>
    implements Char2BooleanMap.FastEntrySet
  {
    private EntrySet() {}
    
    public ObjectIterator<Char2BooleanMap.Entry> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Char2BooleanArrayMap.this.size;
        }
        
        public Char2BooleanMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractChar2BooleanMap.BasicEntry(Char2BooleanArrayMap.this.key[this.next], Char2BooleanArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Char2BooleanMap.Entry> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractChar2BooleanMap.BasicEntry entry = new AbstractChar2BooleanMap.BasicEntry('\000', false);
        
        public boolean hasNext()
        {
          return this.next < Char2BooleanArrayMap.this.size;
        }
        
        public Char2BooleanMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Char2BooleanArrayMap.this.key[this.next];
          this.entry.value = Char2BooleanArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Char2BooleanArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Character, Boolean> local_e = (Map.Entry)local_o;
      char local_k = ((Character)local_e.getKey()).charValue();
      return (Char2BooleanArrayMap.this.containsKey(local_k)) && (Char2BooleanArrayMap.this.get(local_k) == ((Boolean)local_e.getValue()).booleanValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2BooleanArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */