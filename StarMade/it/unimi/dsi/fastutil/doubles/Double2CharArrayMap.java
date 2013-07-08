package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.chars.CharArraySet;
import it.unimi.dsi.fastutil.chars.CharArrays;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharCollections;
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

public class Double2CharArrayMap
  extends AbstractDouble2CharMap
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient double[] key;
  private transient char[] value;
  private int size;
  
  public Double2CharArrayMap(double[] key, char[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Double2CharArrayMap()
  {
    this.key = DoubleArrays.EMPTY_ARRAY;
    this.value = CharArrays.EMPTY_ARRAY;
  }
  
  public Double2CharArrayMap(int capacity)
  {
    this.key = new double[capacity];
    this.value = new char[capacity];
  }
  
  public Double2CharArrayMap(Double2CharMap local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Double2CharArrayMap(Map<? extends Double, ? extends Character> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Double2CharArrayMap(double[] key, char[] value, int size)
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
  
  public Double2CharMap.FastEntrySet double2CharEntrySet()
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
  
  public char get(double local_k)
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
    this.size = 0;
  }
  
  public boolean containsKey(double local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public boolean containsValue(char local_v)
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
  
  public char put(double local_k, char local_v)
  {
    int oldKey = findKey(local_k);
    if (oldKey != -1)
    {
      char oldValue = this.value[oldKey];
      this.value[oldKey] = local_v;
      return oldValue;
    }
    if (this.size == this.key.length)
    {
      double[] oldValue = new double[this.size == 0 ? 2 : this.size * 2];
      char[] newValue = new char[this.size == 0 ? 2 : this.size * 2];
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
  
  public char remove(double local_k)
  {
    int oldPos = findKey(local_k);
    if (oldPos == -1) {
      return this.defRetValue;
    }
    char oldValue = this.value[oldPos];
    int tail = this.size - oldPos - 1;
    for (int local_i = 0; local_i < tail; local_i++)
    {
      this.key[(oldPos + local_i)] = this.key[(oldPos + local_i + 1)];
      this.value[(oldPos + local_i)] = this.value[(oldPos + local_i + 1)];
    }
    this.size -= 1;
    return oldValue;
  }
  
  public DoubleSet keySet()
  {
    return new DoubleArraySet(this.key, this.size);
  }
  
  public CharCollection values()
  {
    return CharCollections.unmodifiable(new CharArraySet(this.value, this.size));
  }
  
  public Double2CharArrayMap clone()
  {
    Double2CharArrayMap local_c;
    try
    {
      local_c = (Double2CharArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((double[])this.key.clone());
    local_c.value = ((char[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeDouble(this.key[local_i]);
      local_s.writeChar(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new double[this.size];
    this.value = new char[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readDouble();
      this.value[local_i] = local_s.readChar();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Double2CharMap.Entry>
    implements Double2CharMap.FastEntrySet
  {
    private EntrySet() {}
    
    public ObjectIterator<Double2CharMap.Entry> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Double2CharArrayMap.this.size;
        }
        
        public Double2CharMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractDouble2CharMap.BasicEntry(Double2CharArrayMap.this.key[this.next], Double2CharArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Double2CharMap.Entry> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractDouble2CharMap.BasicEntry entry = new AbstractDouble2CharMap.BasicEntry(0.0D, '\000');
        
        public boolean hasNext()
        {
          return this.next < Double2CharArrayMap.this.size;
        }
        
        public Double2CharMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Double2CharArrayMap.this.key[this.next];
          this.entry.value = Double2CharArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Double2CharArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, Character> local_e = (Map.Entry)local_o;
      double local_k = ((Double)local_e.getKey()).doubleValue();
      return (Double2CharArrayMap.this.containsKey(local_k)) && (Double2CharArrayMap.this.get(local_k) == ((Character)local_e.getValue()).charValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2CharArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */