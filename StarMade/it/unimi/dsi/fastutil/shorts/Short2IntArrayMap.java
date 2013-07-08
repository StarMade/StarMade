package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntArrays;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntCollections;
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

public class Short2IntArrayMap
  extends AbstractShort2IntMap
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient short[] key;
  private transient int[] value;
  private int size;
  
  public Short2IntArrayMap(short[] key, int[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Short2IntArrayMap()
  {
    this.key = ShortArrays.EMPTY_ARRAY;
    this.value = IntArrays.EMPTY_ARRAY;
  }
  
  public Short2IntArrayMap(int capacity)
  {
    this.key = new short[capacity];
    this.value = new int[capacity];
  }
  
  public Short2IntArrayMap(Short2IntMap local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Short2IntArrayMap(Map<? extends Short, ? extends Integer> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Short2IntArrayMap(short[] key, int[] value, int size)
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
  
  public Short2IntMap.FastEntrySet short2IntEntrySet()
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
  
  public int get(short local_k)
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
  
  public boolean containsValue(int local_v)
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
  
  public int put(short local_k, int local_v)
  {
    int oldKey = findKey(local_k);
    if (oldKey != -1)
    {
      int oldValue = this.value[oldKey];
      this.value[oldKey] = local_v;
      return oldValue;
    }
    if (this.size == this.key.length)
    {
      short[] oldValue = new short[this.size == 0 ? 2 : this.size * 2];
      int[] newValue = new int[this.size == 0 ? 2 : this.size * 2];
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
  
  public int remove(short local_k)
  {
    int oldPos = findKey(local_k);
    if (oldPos == -1) {
      return this.defRetValue;
    }
    int oldValue = this.value[oldPos];
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
  
  public IntCollection values()
  {
    return IntCollections.unmodifiable(new IntArraySet(this.value, this.size));
  }
  
  public Short2IntArrayMap clone()
  {
    Short2IntArrayMap local_c;
    try
    {
      local_c = (Short2IntArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((short[])this.key.clone());
    local_c.value = ((int[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeShort(this.key[local_i]);
      local_s.writeInt(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new short[this.size];
    this.value = new int[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readShort();
      this.value[local_i] = local_s.readInt();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Short2IntMap.Entry>
    implements Short2IntMap.FastEntrySet
  {
    private EntrySet() {}
    
    public ObjectIterator<Short2IntMap.Entry> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Short2IntArrayMap.this.size;
        }
        
        public Short2IntMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractShort2IntMap.BasicEntry(Short2IntArrayMap.this.key[this.next], Short2IntArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Short2IntMap.Entry> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractShort2IntMap.BasicEntry entry = new AbstractShort2IntMap.BasicEntry((short)0, 0);
        
        public boolean hasNext()
        {
          return this.next < Short2IntArrayMap.this.size;
        }
        
        public Short2IntMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Short2IntArrayMap.this.key[this.next];
          this.entry.value = Short2IntArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Short2IntArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Short, Integer> local_e = (Map.Entry)local_o;
      short local_k = ((Short)local_e.getKey()).shortValue();
      return (Short2IntArrayMap.this.containsKey(local_k)) && (Short2IntArrayMap.this.get(local_k) == ((Integer)local_e.getValue()).intValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2IntArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */