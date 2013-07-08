package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ReferenceArraySet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import it.unimi.dsi.fastutil.objects.ReferenceCollections;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Byte2ReferenceArrayMap<V>
  extends AbstractByte2ReferenceMap<V>
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient byte[] key;
  private transient Object[] value;
  private int size;
  
  public Byte2ReferenceArrayMap(byte[] key, Object[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Byte2ReferenceArrayMap()
  {
    this.key = ByteArrays.EMPTY_ARRAY;
    this.value = ObjectArrays.EMPTY_ARRAY;
  }
  
  public Byte2ReferenceArrayMap(int capacity)
  {
    this.key = new byte[capacity];
    this.value = new Object[capacity];
  }
  
  public Byte2ReferenceArrayMap(Byte2ReferenceMap<V> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Byte2ReferenceArrayMap(Map<? extends Byte, ? extends V> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Byte2ReferenceArrayMap(byte[] key, Object[] value, int size)
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
  
  public Byte2ReferenceMap.FastEntrySet<V> byte2ReferenceEntrySet()
  {
    return new EntrySet(null);
  }
  
  private int findKey(byte local_k)
  {
    byte[] key = this.key;
    int local_i = this.size;
    while (local_i-- != 0) {
      if (key[local_i] == local_k) {
        return local_i;
      }
    }
    return -1;
  }
  
  public V get(byte local_k)
  {
    byte[] key = this.key;
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
    int local_i = this.size;
    while (local_i-- != 0) {
      this.value[local_i] = null;
    }
    this.size = 0;
  }
  
  public boolean containsKey(byte local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public boolean containsValue(Object local_v)
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
  
  public V put(byte local_k, V local_v)
  {
    int oldKey = findKey(local_k);
    if (oldKey != -1)
    {
      V oldValue = this.value[oldKey];
      this.value[oldKey] = local_v;
      return oldValue;
    }
    if (this.size == this.key.length)
    {
      byte[] oldValue = new byte[this.size == 0 ? 2 : this.size * 2];
      Object[] newValue = new Object[this.size == 0 ? 2 : this.size * 2];
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
  
  public V remove(byte local_k)
  {
    int oldPos = findKey(local_k);
    if (oldPos == -1) {
      return this.defRetValue;
    }
    V oldValue = this.value[oldPos];
    int tail = this.size - oldPos - 1;
    for (int local_i = 0; local_i < tail; local_i++)
    {
      this.key[(oldPos + local_i)] = this.key[(oldPos + local_i + 1)];
      this.value[(oldPos + local_i)] = this.value[(oldPos + local_i + 1)];
    }
    this.size -= 1;
    this.value[this.size] = null;
    return oldValue;
  }
  
  public ByteSet keySet()
  {
    return new ByteArraySet(this.key, this.size);
  }
  
  public ReferenceCollection<V> values()
  {
    return ReferenceCollections.unmodifiable(new ReferenceArraySet(this.value, this.size));
  }
  
  public Byte2ReferenceArrayMap<V> clone()
  {
    Byte2ReferenceArrayMap<V> local_c;
    try
    {
      local_c = (Byte2ReferenceArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((byte[])this.key.clone());
    local_c.value = ((Object[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeByte(this.key[local_i]);
      local_s.writeObject(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new byte[this.size];
    this.value = new Object[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readByte();
      this.value[local_i] = local_s.readObject();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Byte2ReferenceMap.Entry<V>>
    implements Byte2ReferenceMap.FastEntrySet<V>
  {
    private EntrySet() {}
    
    public ObjectIterator<Byte2ReferenceMap.Entry<V>> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Byte2ReferenceArrayMap.this.size;
        }
        
        public Byte2ReferenceMap.Entry<V> next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractByte2ReferenceMap.BasicEntry(Byte2ReferenceArrayMap.this.key[this.next], Byte2ReferenceArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Byte2ReferenceMap.Entry<V>> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractByte2ReferenceMap.BasicEntry<V> entry = new AbstractByte2ReferenceMap.BasicEntry((byte)0, null);
        
        public boolean hasNext()
        {
          return this.next < Byte2ReferenceArrayMap.this.size;
        }
        
        public Byte2ReferenceMap.Entry<V> next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Byte2ReferenceArrayMap.this.key[this.next];
          this.entry.value = Byte2ReferenceArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Byte2ReferenceArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Byte, V> local_e = (Map.Entry)local_o;
      byte local_k = ((Byte)local_e.getKey()).byteValue();
      return (Byte2ReferenceArrayMap.this.containsKey(local_k)) && (Byte2ReferenceArrayMap.this.get(local_k) == local_e.getValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ReferenceArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */