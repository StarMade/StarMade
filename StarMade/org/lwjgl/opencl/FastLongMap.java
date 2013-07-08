package org.lwjgl.opencl;

import java.util.Iterator;

final class FastLongMap<V>
  implements Iterable<Entry<V>>
{
  private Entry[] table;
  private int size;
  private int mask;
  private int capacity;
  private int threshold;
  
  FastLongMap()
  {
    this(16, 0.75F);
  }
  
  FastLongMap(int initialCapacity)
  {
    this(initialCapacity, 0.75F);
  }
  
  FastLongMap(int initialCapacity, float loadFactor)
  {
    if (initialCapacity > 1073741824) {
      throw new IllegalArgumentException("initialCapacity is too large.");
    }
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("initialCapacity must be greater than zero.");
    }
    if (loadFactor <= 0.0F) {
      throw new IllegalArgumentException("initialCapacity must be greater than zero.");
    }
    for (this.capacity = 1; this.capacity < initialCapacity; this.capacity <<= 1) {}
    this.threshold = ((int)(this.capacity * loadFactor));
    this.table = new Entry[this.capacity];
    this.mask = (this.capacity - 1);
  }
  
  private int index(long key)
  {
    return index(key, this.mask);
  }
  
  private static int index(long key, int mask)
  {
    int hash = (int)(key ^ key >>> 32);
    return hash & mask;
  }
  
  public V put(long key, V value)
  {
    Entry<V>[] table = this.table;
    int index = index(key);
    for (Entry<V> local_e = table[index]; local_e != null; local_e = local_e.next) {
      if (local_e.key == key)
      {
        V oldValue = local_e.value;
        local_e.value = value;
        return oldValue;
      }
    }
    table[index] = new Entry(key, value, table[index]);
    if (this.size++ >= this.threshold) {
      rehash(table);
    }
    return null;
  }
  
  private void rehash(Entry<V>[] table)
  {
    int newCapacity = 2 * this.capacity;
    int newMask = newCapacity - 1;
    Entry<V>[] newTable = new Entry[newCapacity];
    for (int local_i = 0; local_i < table.length; local_i++)
    {
      Entry<V> local_e = table[local_i];
      if (local_e != null) {
        do
        {
          Entry<V> next = local_e.next;
          int index = index(local_e.key, newMask);
          local_e.next = newTable[index];
          newTable[index] = local_e;
          local_e = next;
        } while (local_e != null);
      }
    }
    this.table = newTable;
    this.capacity = newCapacity;
    this.mask = newMask;
    this.threshold *= 2;
  }
  
  public V get(long key)
  {
    int index = index(key);
    for (Entry<V> local_e = this.table[index]; local_e != null; local_e = local_e.next) {
      if (local_e.key == key) {
        return local_e.value;
      }
    }
    return null;
  }
  
  public boolean containsValue(Object value)
  {
    Entry<V>[] table = this.table;
    for (int local_i = table.length - 1; local_i >= 0; local_i--) {
      for (Entry<V> local_e = table[local_i]; local_e != null; local_e = local_e.next) {
        if (local_e.value.equals(value)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean containsKey(long key)
  {
    int index = index(key);
    for (Entry<V> local_e = this.table[index]; local_e != null; local_e = local_e.next) {
      if (local_e.key == key) {
        return true;
      }
    }
    return false;
  }
  
  public V remove(long key)
  {
    int index = index(key);
    Entry<V> prev = this.table[index];
    Entry<V> next;
    for (Entry<V> local_e = prev; local_e != null; local_e = next)
    {
      next = local_e.next;
      if (local_e.key == key)
      {
        this.size -= 1;
        if (prev == local_e) {
          this.table[index] = next;
        } else {
          prev.next = next;
        }
        return local_e.value;
      }
      prev = local_e;
    }
    return null;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  public void clear()
  {
    Entry<V>[] table = this.table;
    for (int index = table.length - 1; index >= 0; index--) {
      table[index] = null;
    }
    this.size = 0;
  }
  
  public FastLongMap<V>.EntryIterator iterator()
  {
    return new EntryIterator();
  }
  
  static final class Entry<T>
  {
    final long key;
    T value;
    Entry<T> next;
    
    Entry(long key, T value, Entry<T> next)
    {
      this.key = key;
      this.value = value;
      this.next = next;
    }
    
    public long getKey()
    {
      return this.key;
    }
    
    public T getValue()
    {
      return this.value;
    }
  }
  
  public class EntryIterator
    implements Iterator<FastLongMap.Entry<V>>
  {
    private int nextIndex;
    private FastLongMap.Entry<V> current;
    
    EntryIterator()
    {
      reset();
    }
    
    public void reset()
    {
      this.current = null;
      FastLongMap.Entry<V>[] table = FastLongMap.this.table;
      for (int local_i = table.length - 1; (local_i >= 0) && (table[local_i] == null); local_i--) {}
      this.nextIndex = local_i;
    }
    
    public boolean hasNext()
    {
      if (this.nextIndex >= 0) {
        return true;
      }
      FastLongMap.Entry local_e = this.current;
      return (local_e != null) && (local_e.next != null);
    }
    
    public FastLongMap.Entry<V> next()
    {
      FastLongMap.Entry<V> local_e = this.current;
      if (local_e != null)
      {
        local_e = local_e.next;
        if (local_e != null)
        {
          this.current = local_e;
          return local_e;
        }
      }
      FastLongMap.Entry<V>[] table = FastLongMap.this.table;
      int local_i = this.nextIndex;
      local_e = this.current = table[local_i];
      for (;;)
      {
        local_i--;
        if (local_i >= 0) {
          if (table[local_i] != null) {
            break;
          }
        }
      }
      this.nextIndex = local_i;
      return local_e;
    }
    
    public void remove()
    {
      FastLongMap.this.remove(this.current.key);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.FastLongMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */