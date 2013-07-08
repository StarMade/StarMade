package it.unimi.dsi.fastutil.shorts;

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

public class Short2ShortArrayMap
  extends AbstractShort2ShortMap
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient short[] key;
  private transient short[] value;
  private int size;
  
  public Short2ShortArrayMap(short[] key, short[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Short2ShortArrayMap()
  {
    this.key = ShortArrays.EMPTY_ARRAY;
    this.value = ShortArrays.EMPTY_ARRAY;
  }
  
  public Short2ShortArrayMap(int capacity)
  {
    this.key = new short[capacity];
    this.value = new short[capacity];
  }
  
  public Short2ShortArrayMap(Short2ShortMap local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Short2ShortArrayMap(Map<? extends Short, ? extends Short> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Short2ShortArrayMap(short[] key, short[] value, int size)
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
  
  public Short2ShortMap.FastEntrySet short2ShortEntrySet()
  {
    return new EntrySet(null);
  }
  
  private int findKey(short local_k)
  {
    short[] key = this.key;
    int local_i = this.size;
    while (local_i-- != 0) {
      if (key[local_i] == local_k) {
        return local_i;
      }
    }
    return -1;
  }
  
  public short get(short local_k)
  {
    short[] key = this.key;
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
  
  public boolean containsKey(short local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public boolean containsValue(short local_v)
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
  
  public short put(short local_k, short local_v)
  {
    int oldKey = findKey(local_k);
    if (oldKey != -1)
    {
      short oldValue = this.value[oldKey];
      this.value[oldKey] = local_v;
      return oldValue;
    }
    if (this.size == this.key.length)
    {
      short[] oldValue = new short[this.size == 0 ? 2 : this.size * 2];
      short[] newValue = new short[this.size == 0 ? 2 : this.size * 2];
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
  
  public short remove(short local_k)
  {
    int oldPos = findKey(local_k);
    if (oldPos == -1) {
      return this.defRetValue;
    }
    short oldValue = this.value[oldPos];
    int tail = this.size - oldPos - 1;
    for (int local_i = 0; local_i < tail; local_i++)
    {
      this.key[(oldPos + local_i)] = this.key[(oldPos + local_i + 1)];
      this.value[(oldPos + local_i)] = this.value[(oldPos + local_i + 1)];
    }
    this.size -= 1;
    return oldValue;
  }
  
  public ShortSet keySet()
  {
    return new ShortArraySet(this.key, this.size);
  }
  
  public ShortCollection values()
  {
    return ShortCollections.unmodifiable(new ShortArraySet(this.value, this.size));
  }
  
  public Short2ShortArrayMap clone()
  {
    Short2ShortArrayMap local_c;
    try
    {
      local_c = (Short2ShortArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((short[])this.key.clone());
    local_c.value = ((short[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeShort(this.key[local_i]);
      local_s.writeShort(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new short[this.size];
    this.value = new short[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readShort();
      this.value[local_i] = local_s.readShort();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Short2ShortMap.Entry>
    implements Short2ShortMap.FastEntrySet
  {
    private EntrySet() {}
    
    public ObjectIterator<Short2ShortMap.Entry> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Short2ShortArrayMap.this.size;
        }
        
        public Short2ShortMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractShort2ShortMap.BasicEntry(Short2ShortArrayMap.this.key[this.next], Short2ShortArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Short2ShortMap.Entry> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractShort2ShortMap.BasicEntry entry = new AbstractShort2ShortMap.BasicEntry((short)0, (short)0);
        
        public boolean hasNext()
        {
          return this.next < Short2ShortArrayMap.this.size;
        }
        
        public Short2ShortMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Short2ShortArrayMap.this.key[this.next];
          this.entry.value = Short2ShortArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Short2ShortArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Short, Short> local_e = (Map.Entry)local_o;
      short local_k = ((Short)local_e.getKey()).shortValue();
      return (Short2ShortArrayMap.this.containsKey(local_k)) && (Short2ShortArrayMap.this.get(local_k) == ((Short)local_e.getValue()).shortValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ShortArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */