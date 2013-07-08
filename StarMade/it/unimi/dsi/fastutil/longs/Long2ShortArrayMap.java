package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.shorts.ShortArraySet;
import it.unimi.dsi.fastutil.shorts.ShortArrays;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortCollections;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Long2ShortArrayMap
  extends AbstractLong2ShortMap
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient long[] key;
  private transient short[] value;
  private int size;
  
  public Long2ShortArrayMap(long[] key, short[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Long2ShortArrayMap()
  {
    this.key = LongArrays.EMPTY_ARRAY;
    this.value = ShortArrays.EMPTY_ARRAY;
  }
  
  public Long2ShortArrayMap(int capacity)
  {
    this.key = new long[capacity];
    this.value = new short[capacity];
  }
  
  public Long2ShortArrayMap(Long2ShortMap local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Long2ShortArrayMap(Map<? extends Long, ? extends Short> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Long2ShortArrayMap(long[] key, short[] value, int size)
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
  
  public Long2ShortMap.FastEntrySet long2ShortEntrySet()
  {
    return new EntrySet(null);
  }
  
  private int findKey(long local_k)
  {
    long[] key = this.key;
    int local_i = this.size;
    while (local_i-- != 0) {
      if (key[local_i] == local_k) {
        return local_i;
      }
    }
    return -1;
  }
  
  public short get(long local_k)
  {
    long[] key = this.key;
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
  
  public boolean containsKey(long local_k)
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
  
  public short put(long local_k, short local_v)
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
      long[] oldValue = new long[this.size == 0 ? 2 : this.size * 2];
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
  
  public short remove(long local_k)
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
  
  public LongSet keySet()
  {
    return new LongArraySet(this.key, this.size);
  }
  
  public ShortCollection values()
  {
    return ShortCollections.unmodifiable(new ShortArraySet(this.value, this.size));
  }
  
  public Long2ShortArrayMap clone()
  {
    Long2ShortArrayMap local_c;
    try
    {
      local_c = (Long2ShortArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((long[])this.key.clone());
    local_c.value = ((short[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeLong(this.key[local_i]);
      local_s.writeShort(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new long[this.size];
    this.value = new short[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readLong();
      this.value[local_i] = local_s.readShort();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Long2ShortMap.Entry>
    implements Long2ShortMap.FastEntrySet
  {
    private EntrySet() {}
    
    public ObjectIterator<Long2ShortMap.Entry> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Long2ShortArrayMap.this.size;
        }
        
        public Long2ShortMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractLong2ShortMap.BasicEntry(Long2ShortArrayMap.this.key[this.next], Long2ShortArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Long2ShortMap.Entry> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractLong2ShortMap.BasicEntry entry = new AbstractLong2ShortMap.BasicEntry(0L, (short)0);
        
        public boolean hasNext()
        {
          return this.next < Long2ShortArrayMap.this.size;
        }
        
        public Long2ShortMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Long2ShortArrayMap.this.key[this.next];
          this.entry.value = Long2ShortArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Long2ShortArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Long, Short> local_e = (Map.Entry)local_o;
      long local_k = ((Long)local_e.getKey()).longValue();
      return (Long2ShortArrayMap.this.containsKey(local_k)) && (Long2ShortArrayMap.this.get(local_k) == ((Short)local_e.getValue()).shortValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ShortArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */