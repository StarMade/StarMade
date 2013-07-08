package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntArrays;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntCollections;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Reference2IntArrayMap<K>
  extends AbstractReference2IntMap<K>
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient Object[] key;
  private transient int[] value;
  private int size;
  
  public Reference2IntArrayMap(Object[] key, int[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Reference2IntArrayMap()
  {
    this.key = ObjectArrays.EMPTY_ARRAY;
    this.value = IntArrays.EMPTY_ARRAY;
  }
  
  public Reference2IntArrayMap(int capacity)
  {
    this.key = new Object[capacity];
    this.value = new int[capacity];
  }
  
  public Reference2IntArrayMap(Reference2IntMap<K> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Reference2IntArrayMap(Map<? extends K, ? extends Integer> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Reference2IntArrayMap(Object[] key, int[] value, int size)
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
  
  public Reference2IntMap.FastEntrySet<K> reference2IntEntrySet()
  {
    return new EntrySet(null);
  }
  
  private int findKey(Object local_k)
  {
    Object[] key = this.key;
    int local_i = this.size;
    while (local_i-- != 0) {
      if (key[local_i] == local_k) {
        return local_i;
      }
    }
    return -1;
  }
  
  public int getInt(Object local_k)
  {
    Object[] key = this.key;
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
      this.key[local_i] = null;
    }
    this.size = 0;
  }
  
  public boolean containsKey(Object local_k)
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
  
  public int put(K local_k, int local_v)
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
      Object[] oldValue = new Object[this.size == 0 ? 2 : this.size * 2];
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
  
  public int removeInt(Object local_k)
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
    this.key[this.size] = null;
    return oldValue;
  }
  
  public ReferenceSet<K> keySet()
  {
    return new ReferenceArraySet(this.key, this.size);
  }
  
  public IntCollection values()
  {
    return IntCollections.unmodifiable(new IntArraySet(this.value, this.size));
  }
  
  public Reference2IntArrayMap<K> clone()
  {
    Reference2IntArrayMap<K> local_c;
    try
    {
      local_c = (Reference2IntArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((Object[])this.key.clone());
    local_c.value = ((int[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeObject(this.key[local_i]);
      local_s.writeInt(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new Object[this.size];
    this.value = new int[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readObject();
      this.value[local_i] = local_s.readInt();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Reference2IntMap.Entry<K>>
    implements Reference2IntMap.FastEntrySet<K>
  {
    private EntrySet() {}
    
    public ObjectIterator<Reference2IntMap.Entry<K>> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Reference2IntArrayMap.this.size;
        }
        
        public Reference2IntMap.Entry<K> next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractReference2IntMap.BasicEntry(Reference2IntArrayMap.this.key[this.next], Reference2IntArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Reference2IntMap.Entry<K>> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractReference2IntMap.BasicEntry<K> entry = new AbstractReference2IntMap.BasicEntry(null, 0);
        
        public boolean hasNext()
        {
          return this.next < Reference2IntArrayMap.this.size;
        }
        
        public Reference2IntMap.Entry<K> next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Reference2IntArrayMap.this.key[this.next];
          this.entry.value = Reference2IntArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Reference2IntArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Integer> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      return (Reference2IntArrayMap.this.containsKey(local_k)) && (Reference2IntArrayMap.this.get(local_k).intValue() == ((Integer)local_e.getValue()).intValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2IntArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */