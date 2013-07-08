package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectCollections;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Double2ObjectArrayMap<V>
  extends AbstractDouble2ObjectMap<V>
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient double[] key;
  private transient Object[] value;
  private int size;
  
  public Double2ObjectArrayMap(double[] key, Object[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Double2ObjectArrayMap()
  {
    this.key = DoubleArrays.EMPTY_ARRAY;
    this.value = ObjectArrays.EMPTY_ARRAY;
  }
  
  public Double2ObjectArrayMap(int capacity)
  {
    this.key = new double[capacity];
    this.value = new Object[capacity];
  }
  
  public Double2ObjectArrayMap(Double2ObjectMap<V> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Double2ObjectArrayMap(Map<? extends Double, ? extends V> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Double2ObjectArrayMap(double[] key, Object[] value, int size)
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
  
  public Double2ObjectMap.FastEntrySet<V> double2ObjectEntrySet()
  {
    return new EntrySet(null);
  }
  
  private int findKey(double local_k)
  {
    double[] key = this.key;
    int local_i = this.size;
    while (local_i-- != 0) {
      if (key[local_i] == local_k) {
        return local_i;
      }
    }
    return -1;
  }
  
  public V get(double local_k)
  {
    double[] key = this.key;
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
  
  public boolean containsKey(double local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public boolean containsValue(Object local_v)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.value[local_i] == null ? local_v == null : this.value[local_i].equals(local_v)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  public V put(double local_k, V local_v)
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
      double[] oldValue = new double[this.size == 0 ? 2 : this.size * 2];
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
  
  public V remove(double local_k)
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
  
  public DoubleSet keySet()
  {
    return new DoubleArraySet(this.key, this.size);
  }
  
  public ObjectCollection<V> values()
  {
    return ObjectCollections.unmodifiable(new ObjectArraySet(this.value, this.size));
  }
  
  public Double2ObjectArrayMap<V> clone()
  {
    Double2ObjectArrayMap<V> local_c;
    try
    {
      local_c = (Double2ObjectArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((double[])this.key.clone());
    local_c.value = ((Object[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeDouble(this.key[local_i]);
      local_s.writeObject(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new double[this.size];
    this.value = new Object[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readDouble();
      this.value[local_i] = local_s.readObject();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Double2ObjectMap.Entry<V>>
    implements Double2ObjectMap.FastEntrySet<V>
  {
    private EntrySet() {}
    
    public ObjectIterator<Double2ObjectMap.Entry<V>> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Double2ObjectArrayMap.this.size;
        }
        
        public Double2ObjectMap.Entry<V> next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractDouble2ObjectMap.BasicEntry(Double2ObjectArrayMap.this.key[this.next], Double2ObjectArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Double2ObjectMap.Entry<V>> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractDouble2ObjectMap.BasicEntry<V> entry = new AbstractDouble2ObjectMap.BasicEntry(0.0D, null);
        
        public boolean hasNext()
        {
          return this.next < Double2ObjectArrayMap.this.size;
        }
        
        public Double2ObjectMap.Entry<V> next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Double2ObjectArrayMap.this.key[this.next];
          this.entry.value = Double2ObjectArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Double2ObjectArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, V> local_e = (Map.Entry)local_o;
      double local_k = ((Double)local_e.getKey()).doubleValue();
      return (Double2ObjectArrayMap.this.containsKey(local_k)) && (Double2ObjectArrayMap.this.get(local_k) == null ? local_e.getValue() == null : Double2ObjectArrayMap.this.get(local_k).equals(local_e.getValue()));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ObjectArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */