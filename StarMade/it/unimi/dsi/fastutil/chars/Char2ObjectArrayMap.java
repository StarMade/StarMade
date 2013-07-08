package it.unimi.dsi.fastutil.chars;

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

public class Char2ObjectArrayMap<V>
  extends AbstractChar2ObjectMap<V>
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient char[] key;
  private transient Object[] value;
  private int size;
  
  public Char2ObjectArrayMap(char[] key, Object[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Char2ObjectArrayMap()
  {
    this.key = CharArrays.EMPTY_ARRAY;
    this.value = ObjectArrays.EMPTY_ARRAY;
  }
  
  public Char2ObjectArrayMap(int capacity)
  {
    this.key = new char[capacity];
    this.value = new Object[capacity];
  }
  
  public Char2ObjectArrayMap(Char2ObjectMap<V> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Char2ObjectArrayMap(Map<? extends Character, ? extends V> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Char2ObjectArrayMap(char[] key, Object[] value, int size)
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
  
  public Char2ObjectMap.FastEntrySet<V> char2ObjectEntrySet()
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
  
  public V get(char local_k)
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
    int local_i = this.size;
    while (local_i-- != 0) {
      this.value[local_i] = null;
    }
    this.size = 0;
  }
  
  public boolean containsKey(char local_k)
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
  
  public V put(char local_k, V local_v)
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
      char[] oldValue = new char[this.size == 0 ? 2 : this.size * 2];
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
  
  public V remove(char local_k)
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
  
  public CharSet keySet()
  {
    return new CharArraySet(this.key, this.size);
  }
  
  public ObjectCollection<V> values()
  {
    return ObjectCollections.unmodifiable(new ObjectArraySet(this.value, this.size));
  }
  
  public Char2ObjectArrayMap<V> clone()
  {
    Char2ObjectArrayMap<V> local_c;
    try
    {
      local_c = (Char2ObjectArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((char[])this.key.clone());
    local_c.value = ((Object[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeChar(this.key[local_i]);
      local_s.writeObject(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new char[this.size];
    this.value = new Object[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readChar();
      this.value[local_i] = local_s.readObject();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Char2ObjectMap.Entry<V>>
    implements Char2ObjectMap.FastEntrySet<V>
  {
    private EntrySet() {}
    
    public ObjectIterator<Char2ObjectMap.Entry<V>> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Char2ObjectArrayMap.this.size;
        }
        
        public Char2ObjectMap.Entry<V> next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractChar2ObjectMap.BasicEntry(Char2ObjectArrayMap.this.key[this.next], Char2ObjectArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Char2ObjectMap.Entry<V>> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractChar2ObjectMap.BasicEntry<V> entry = new AbstractChar2ObjectMap.BasicEntry('\000', null);
        
        public boolean hasNext()
        {
          return this.next < Char2ObjectArrayMap.this.size;
        }
        
        public Char2ObjectMap.Entry<V> next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Char2ObjectArrayMap.this.key[this.next];
          this.entry.value = Char2ObjectArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Char2ObjectArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Character, V> local_e = (Map.Entry)local_o;
      char local_k = ((Character)local_e.getKey()).charValue();
      return (Char2ObjectArrayMap.this.containsKey(local_k)) && (Char2ObjectArrayMap.this.get(local_k) == null ? local_e.getValue() == null : Char2ObjectArrayMap.this.get(local_k).equals(local_e.getValue()));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */