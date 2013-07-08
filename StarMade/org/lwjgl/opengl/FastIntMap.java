package org.lwjgl.opengl;

import java.util.Iterator;

final class FastIntMap<V>
  implements Iterable<Entry<V>>
{
  private Entry[] table;
  private int size;
  private int mask;
  private int capacity;
  private int threshold;
  
  FastIntMap()
  {
    this(16, 0.75F);
  }
  
  FastIntMap(int initialCapacity)
  {
    this(initialCapacity, 0.75F);
  }
  
  FastIntMap(int initialCapacity, float loadFactor)
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
  
  private int index(int key)
  {
    return index(key, this.mask);
  }
  
  private static int index(int key, int mask)
  {
    return key & mask;
  }
  
  public V put(int key, V value)
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
  
  public V get(int key)
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
  
  public boolean containsKey(int key)
  {
    int index = index(key);
    for (Entry<V> local_e = this.table[index]; local_e != null; local_e = local_e.next) {
      if (local_e.key == key) {
        return true;
      }
    }
    return false;
  }
  
  public V remove(int key)
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
  
  public FastIntMap<V>.EntryIterator iterator()
  {
    return new EntryIterator();
  }
  
  static final class Entry<T>
  {
    final int key;
    T value;
    Entry<T> next;
    
    Entry(int key, T value, Entry<T> next)
    {
      this.key = key;
      this.value = value;
      this.next = next;
    }
    
    public int getKey()
    {
      return this.key;
    }
    
    public T getValue()
    {
      return this.value;
    }
  }
  
  public class EntryIterator
    implements Iterator<FastIntMap.Entry<V>>
  {
    private int nextIndex;
    private FastIntMap.Entry<V> current;
    
    EntryIterator()
    {
      reset();
    }
    
    public void reset()
    {
      this.current = null;
      FastIntMap.Entry<V>[] table = FastIntMap.this.table;
      for (int local_i = table.length - 1; (local_i >= 0) && (table[local_i] == null); local_i--) {}
      this.nextIndex = local_i;
    }
    
    public boolean hasNext()
    {
      if (this.nextIndex >= 0) {
        return true;
      }
      FastIntMap.Entry local_e = this.current;
      return (local_e != null) && (local_e.next != null);
    }
    
    public FastIntMap.Entry<V> next()
    {
      FastIntMap.Entry<V> local_e = this.current;
      if (local_e != null)
      {
        local_e = local_e.next;
        if (local_e != null)
        {
          this.current = local_e;
          return local_e;
        }
      }
      FastIntMap.Entry<V>[] table = FastIntMap.this.table;
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
      FastIntMap.this.remove(this.current.key);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.FastIntMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */