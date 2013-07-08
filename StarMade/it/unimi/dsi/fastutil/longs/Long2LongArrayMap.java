package it.unimi.dsi.fastutil.longs;

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

public class Long2LongArrayMap
  extends AbstractLong2LongMap
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient long[] key;
  private transient long[] value;
  private int size;
  
  public Long2LongArrayMap(long[] key, long[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Long2LongArrayMap()
  {
    this.key = LongArrays.EMPTY_ARRAY;
    this.value = LongArrays.EMPTY_ARRAY;
  }
  
  public Long2LongArrayMap(int capacity)
  {
    this.key = new long[capacity];
    this.value = new long[capacity];
  }
  
  public Long2LongArrayMap(Long2LongMap local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Long2LongArrayMap(Map<? extends Long, ? extends Long> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Long2LongArrayMap(long[] key, long[] value, int size)
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
  
  public Long2LongMap.FastEntrySet long2LongEntrySet()
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
  
  public long get(long local_k)
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
  
  public boolean containsValue(long local_v)
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
  
  public long put(long local_k, long local_v)
  {
    int oldKey = findKey(local_k);
    if (oldKey != -1)
    {
      long oldValue = this.value[oldKey];
      this.value[oldKey] = local_v;
      return oldValue;
    }
    if (this.size == this.key.length)
    {
      long[] oldValue = new long[this.size == 0 ? 2 : this.size * 2];
      long[] newValue = new long[this.size == 0 ? 2 : this.size * 2];
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
  
  public long remove(long local_k)
  {
    int oldPos = findKey(local_k);
    if (oldPos == -1) {
      return this.defRetValue;
    }
    long oldValue = this.value[oldPos];
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
  
  public LongCollection values()
  {
    return LongCollections.unmodifiable(new LongArraySet(this.value, this.size));
  }
  
  public Long2LongArrayMap clone()
  {
    Long2LongArrayMap local_c;
    try
    {
      local_c = (Long2LongArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((long[])this.key.clone());
    local_c.value = ((long[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeLong(this.key[local_i]);
      local_s.writeLong(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new long[this.size];
    this.value = new long[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readLong();
      this.value[local_i] = local_s.readLong();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Long2LongMap.Entry>
    implements Long2LongMap.FastEntrySet
  {
    private EntrySet() {}
    
    public ObjectIterator<Long2LongMap.Entry> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Long2LongArrayMap.this.size;
        }
        
        public Long2LongMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractLong2LongMap.BasicEntry(Long2LongArrayMap.this.key[this.next], Long2LongArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Long2LongMap.Entry> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractLong2LongMap.BasicEntry entry = new AbstractLong2LongMap.BasicEntry(0L, 0L);
        
        public boolean hasNext()
        {
          return this.next < Long2LongArrayMap.this.size;
        }
        
        public Long2LongMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Long2LongArrayMap.this.key[this.next];
          this.entry.value = Long2LongArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Long2LongArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Long, Long> local_e = (Map.Entry)local_o;
      long local_k = ((Long)local_e.getKey()).longValue();
      return (Long2LongArrayMap.this.containsKey(local_k)) && (Long2LongArrayMap.this.get(local_k) == ((Long)local_e.getValue()).longValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2LongArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */