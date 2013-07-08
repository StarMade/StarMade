package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.doubles.DoubleArraySet;
import it.unimi.dsi.fastutil.doubles.DoubleArrays;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleCollections;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Reference2DoubleArrayMap<K>
  extends AbstractReference2DoubleMap<K>
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient Object[] key;
  private transient double[] value;
  private int size;
  
  public Reference2DoubleArrayMap(Object[] key, double[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Reference2DoubleArrayMap()
  {
    this.key = ObjectArrays.EMPTY_ARRAY;
    this.value = DoubleArrays.EMPTY_ARRAY;
  }
  
  public Reference2DoubleArrayMap(int capacity)
  {
    this.key = new Object[capacity];
    this.value = new double[capacity];
  }
  
  public Reference2DoubleArrayMap(Reference2DoubleMap<K> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Reference2DoubleArrayMap(Map<? extends K, ? extends Double> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Reference2DoubleArrayMap(Object[] key, double[] value, int size)
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
  
  public Reference2DoubleMap.FastEntrySet<K> reference2DoubleEntrySet()
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
  
  public double getDouble(Object local_k)
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
  
  public boolean containsValue(double local_v)
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
  
  public double put(K local_k, double local_v)
  {
    int oldKey = findKey(local_k);
    if (oldKey != -1)
    {
      double oldValue = this.value[oldKey];
      this.value[oldKey] = local_v;
      return oldValue;
    }
    if (this.size == this.key.length)
    {
      Object[] oldValue = new Object[this.size == 0 ? 2 : this.size * 2];
      double[] newValue = new double[this.size == 0 ? 2 : this.size * 2];
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
  
  public double removeDouble(Object local_k)
  {
    int oldPos = findKey(local_k);
    if (oldPos == -1) {
      return this.defRetValue;
    }
    double oldValue = this.value[oldPos];
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
  
  public DoubleCollection values()
  {
    return DoubleCollections.unmodifiable(new DoubleArraySet(this.value, this.size));
  }
  
  public Reference2DoubleArrayMap<K> clone()
  {
    Reference2DoubleArrayMap<K> local_c;
    try
    {
      local_c = (Reference2DoubleArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((Object[])this.key.clone());
    local_c.value = ((double[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeObject(this.key[local_i]);
      local_s.writeDouble(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new Object[this.size];
    this.value = new double[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readObject();
      this.value[local_i] = local_s.readDouble();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Reference2DoubleMap.Entry<K>>
    implements Reference2DoubleMap.FastEntrySet<K>
  {
    private EntrySet() {}
    
    public ObjectIterator<Reference2DoubleMap.Entry<K>> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Reference2DoubleArrayMap.this.size;
        }
        
        public Reference2DoubleMap.Entry<K> next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractReference2DoubleMap.BasicEntry(Reference2DoubleArrayMap.this.key[this.next], Reference2DoubleArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Reference2DoubleMap.Entry<K>> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractReference2DoubleMap.BasicEntry<K> entry = new AbstractReference2DoubleMap.BasicEntry(null, 0.0D);
        
        public boolean hasNext()
        {
          return this.next < Reference2DoubleArrayMap.this.size;
        }
        
        public Reference2DoubleMap.Entry<K> next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Reference2DoubleArrayMap.this.key[this.next];
          this.entry.value = Reference2DoubleArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Reference2DoubleArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Double> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      return (Reference2DoubleArrayMap.this.containsKey(local_k)) && (Reference2DoubleArrayMap.this.get(local_k).doubleValue() == ((Double)local_e.getValue()).doubleValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2DoubleArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */