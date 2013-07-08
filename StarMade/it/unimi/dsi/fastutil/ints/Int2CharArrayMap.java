package it.unimi.dsi.fastutil.ints;

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

public class Int2CharArrayMap
  extends AbstractInt2CharMap
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient int[] key;
  private transient char[] value;
  private int size;
  
  public Int2CharArrayMap(int[] key, char[] value)
  {
    this.key = key;
    this.value = value;
    this.size = key.length;
    if (key.length != value.length) {
      throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
    }
  }
  
  public Int2CharArrayMap()
  {
    this.key = IntArrays.EMPTY_ARRAY;
    this.value = CharArrays.EMPTY_ARRAY;
  }
  
  public Int2CharArrayMap(int capacity)
  {
    this.key = new int[capacity];
    this.value = new char[capacity];
  }
  
  public Int2CharArrayMap(Int2CharMap local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Int2CharArrayMap(Map<? extends Integer, ? extends Character> local_m)
  {
    this(local_m.size());
    putAll(local_m);
  }
  
  public Int2CharArrayMap(int[] key, char[] value, int size)
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
  
  public Int2CharMap.FastEntrySet int2CharEntrySet()
  {
    return new EntrySet(null);
  }
  
  private int findKey(int local_k)
  {
    int[] key = this.key;
    int local_i = this.size;
    while (local_i-- != 0) {
      if (key[local_i] == local_k) {
        return local_i;
      }
    }
    return -1;
  }
  
  public char get(int local_k)
  {
    int[] key = this.key;
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
  
  public boolean containsKey(int local_k)
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
  
  public char put(int local_k, char local_v)
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
      int[] oldValue = new int[this.size == 0 ? 2 : this.size * 2];
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
  
  public char remove(int local_k)
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
  
  public IntSet keySet()
  {
    return new IntArraySet(this.key, this.size);
  }
  
  public CharCollection values()
  {
    return CharCollections.unmodifiable(new CharArraySet(this.value, this.size));
  }
  
  public Int2CharArrayMap clone()
  {
    Int2CharArrayMap local_c;
    try
    {
      local_c = (Int2CharArrayMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.key = ((int[])this.key.clone());
    local_c.value = ((char[])this.value.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      local_s.writeInt(this.key[local_i]);
      local_s.writeChar(this.value[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.key = new int[this.size];
    this.value = new char[this.size];
    for (int local_i = 0; local_i < this.size; local_i++)
    {
      this.key[local_i] = local_s.readInt();
      this.value[local_i] = local_s.readChar();
    }
  }
  
  private final class EntrySet
    extends AbstractObjectSet<Int2CharMap.Entry>
    implements Int2CharMap.FastEntrySet
  {
    private EntrySet() {}
    
    public ObjectIterator<Int2CharMap.Entry> iterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        
        public boolean hasNext()
        {
          return this.next < Int2CharArrayMap.this.size;
        }
        
        public Int2CharMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          return new AbstractInt2CharMap.BasicEntry(Int2CharArrayMap.this.key[this.next], Int2CharArrayMap.this.value[(this.next++)]);
        }
      };
    }
    
    public ObjectIterator<Int2CharMap.Entry> fastIterator()
    {
      new AbstractObjectIterator()
      {
        int next = 0;
        final AbstractInt2CharMap.BasicEntry entry = new AbstractInt2CharMap.BasicEntry(0, '\000');
        
        public boolean hasNext()
        {
          return this.next < Int2CharArrayMap.this.size;
        }
        
        public Int2CharMap.Entry next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          this.entry.key = Int2CharArrayMap.this.key[this.next];
          this.entry.value = Int2CharArrayMap.this.value[(this.next++)];
          return this.entry;
        }
      };
    }
    
    public int size()
    {
      return Int2CharArrayMap.this.size;
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Integer, Character> local_e = (Map.Entry)local_o;
      int local_k = ((Integer)local_e.getKey()).intValue();
      return (Int2CharArrayMap.this.containsKey(local_k)) && (Int2CharArrayMap.this.get(local_k) == ((Character)local_e.getValue()).charValue());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2CharArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */