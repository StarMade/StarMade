package it.unimi.dsi.fastutil.chars;

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

public class Char2IntArrayMap
  extends AbstractChar2IntMap
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient char[] key;
  private transient int[] value;
  private int size;
  
  public Char2IntArrayMap(char[] key, int[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Char2IntArrayMap()
  {
    this.key = CharArrays.EMPTY_ARRAY;
    this.value = IntArrays.EMPTY_ARRAY;
  }
  
  public Char2IntArrayMap(int capacity)
  {
    this.key = new char[capacity];
    this.value = new int[capacity];
  }
  
  public Char2IntArrayMap(Char2IntMap local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Char2IntArrayMap(Map<? extends Character, ? extends Integer> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Char2IntArrayMap(char[] key, int[] value, int size)
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
  
  public Char2IntMap.FastEntrySet char2IntEntrySet()
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
  
  public int get(char local_k)
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
    this.size = 0;
  }
  
  public boolean containsKey(char local_k)
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
  
  public int put(char local_k, int local_v)
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
      char[] oldValue = new char[this.size == 0 ? 2 : this.size * 2];
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
  
  public int remove(char local_k)
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
  
  public CharSet keySet()
  {
    return new CharArraySet(this.key, this.size);
  }
  
  public IntCollection values()
  {
    return IntCollections.unmodifiable(new IntArraySet(this.value, this.size));
  }
  
  public Char2IntArrayMap clone()
  {
    Char2IntArrayMap local_c;
    try
    {
      local_c = (Char2IntArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((char[])this.key.clone());
    local_c.value = ((int[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeChar(this.key[local_i]);
      local_s.writeInt(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new char[this.size];
    this.value = new int[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readChar();
      this.value[local_i] = local_s.readInt();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Char2IntMap.Entry>
    implements Char2IntMap.FastEntrySet
  {
    private EntrySet() {}
    
    public ObjectIterator<Char2IntMap.Entry> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Char2IntArrayMap.this.size;
        }
        
        public Char2IntMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractChar2IntMap.BasicEntry(Char2IntArrayMap.this.key[this.next], Char2IntArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Char2IntMap.Entry> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractChar2IntMap.BasicEntry entry = new AbstractChar2IntMap.BasicEntry('\000', 0);
        
        public boolean hasNext()
        {
          return this.next < Char2IntArrayMap.this.size;
        }
        
        public Char2IntMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Char2IntArrayMap.this.key[this.next];
          this.entry.value = Char2IntArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Char2IntArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Character, Integer> local_e = (Map.Entry)local_o;
      char local_k = ((Character)local_e.getKey()).charValue();
      return (Char2IntArrayMap.this.containsKey(local_k)) && (Char2IntArrayMap.this.get(local_k) == ((Integer)local_e.getValue()).intValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2IntArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */