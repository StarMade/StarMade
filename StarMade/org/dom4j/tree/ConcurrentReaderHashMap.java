package org.dom4j.tree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

class ConcurrentReaderHashMap
  extends AbstractMap
  implements Map, Cloneable, Serializable
{
  protected final BarrierLock barrierLock = new BarrierLock();
  protected transient Object lastWrite;
  public static int DEFAULT_INITIAL_CAPACITY = 32;
  private static final int MINIMUM_CAPACITY = 4;
  private static final int MAXIMUM_CAPACITY = 1073741824;
  public static final float DEFAULT_LOAD_FACTOR = 0.75F;
  protected transient Entry[] table;
  protected transient int count;
  protected int threshold;
  protected float loadFactor;
  protected transient Set keySet = null;
  protected transient Set entrySet = null;
  protected transient Collection values = null;
  
  protected final void recordModification(Object local_x)
  {
    synchronized (this.barrierLock)
    {
      this.lastWrite = local_x;
    }
  }
  
  protected final Entry[] getTableForReading()
  {
    synchronized (this.barrierLock)
    {
      return this.table;
    }
  }
  
  private int p2capacity(int initialCapacity)
  {
    int cap = initialCapacity;
    int result;
    int result;
    if ((cap > 1073741824) || (cap < 0))
    {
      result = 1073741824;
    }
    else
    {
      result = 4;
      while (result < cap) {
        result <<= 1;
      }
    }
    return result;
  }
  
  private static int hash(Object local_x)
  {
    int local_h = local_x.hashCode();
    return (local_h << 7) - local_h + (local_h >>> 9) + (local_h >>> 17);
  }
  
  protected boolean eq(Object local_x, Object local_y)
  {
    return (local_x == local_y) || (local_x.equals(local_y));
  }
  
  public ConcurrentReaderHashMap(int initialCapacity, float loadFactor)
  {
    if (loadFactor <= 0.0F) {
      throw new IllegalArgumentException("Illegal Load factor: " + loadFactor);
    }
    this.loadFactor = loadFactor;
    int cap = p2capacity(initialCapacity);
    this.table = new Entry[cap];
    this.threshold = ((int)(cap * loadFactor));
  }
  
  public ConcurrentReaderHashMap(int initialCapacity)
  {
    this(initialCapacity, 0.75F);
  }
  
  public ConcurrentReaderHashMap()
  {
    this(DEFAULT_INITIAL_CAPACITY, 0.75F);
  }
  
  public ConcurrentReaderHashMap(Map local_t)
  {
    this(Math.max((int)(local_t.size() / 0.75F) + 1, 16), 0.75F);
    putAll(local_t);
  }
  
  public synchronized int size()
  {
    return this.count;
  }
  
  public synchronized boolean isEmpty()
  {
    return this.count == 0;
  }
  
  public Object get(Object key)
  {
    int hash = hash(key);
    Entry[] tab = this.table;
    int index = hash & tab.length - 1;
    Entry first = tab[index];
    Entry local_e = first;
    for (;;)
    {
      if (local_e == null)
      {
        Entry[] reread = getTableForReading();
        if ((tab == reread) && (first == tab[index])) {
          return null;
        }
        tab = reread;
        local_e = first = tab[(index = hash & tab.length - 1)];
      }
      else if ((local_e.hash == hash) && (eq(key, local_e.key)))
      {
        Object reread = local_e.value;
        if (reread != null) {
          return reread;
        }
        synchronized (this)
        {
          tab = this.table;
        }
        local_e = first = tab[(index = hash & tab.length - 1)];
      }
      else
      {
        local_e = local_e.next;
      }
    }
  }
  
  public boolean containsKey(Object key)
  {
    return get(key) != null;
  }
  
  public Object put(Object key, Object value)
  {
    if (value == null) {
      throw new NullPointerException();
    }
    int hash = hash(key);
    Entry[] tab = this.table;
    int index = hash & tab.length - 1;
    Entry first = tab[index];
    for (Entry local_e = first; (local_e != null) && ((local_e.hash != hash) || (!eq(key, local_e.key))); local_e = local_e.next) {}
    synchronized (this)
    {
      if (tab == this.table) {
        if (local_e == null)
        {
          if (first == tab[index])
          {
            Entry newEntry = new Entry(hash, key, value, first);
            tab[index] = newEntry;
            if (++this.count >= this.threshold) {
              rehash();
            } else {
              recordModification(newEntry);
            }
            return null;
          }
        }
        else
        {
          Object newEntry = local_e.value;
          if ((first == tab[index]) && (newEntry != null))
          {
            local_e.value = value;
            return newEntry;
          }
        }
      }
      return sput(key, value, hash);
    }
  }
  
  protected Object sput(Object key, Object value, int hash)
  {
    Entry[] tab = this.table;
    int index = hash & tab.length - 1;
    Entry first = tab[index];
    for (Entry local_e = first;; local_e = local_e.next)
    {
      if (local_e == null)
      {
        Entry newEntry = new Entry(hash, key, value, first);
        tab[index] = newEntry;
        if (++this.count >= this.threshold) {
          rehash();
        } else {
          recordModification(newEntry);
        }
        return null;
      }
      if ((local_e.hash == hash) && (eq(key, local_e.key)))
      {
        Object newEntry = local_e.value;
        local_e.value = value;
        return newEntry;
      }
    }
  }
  
  protected void rehash()
  {
    Entry[] oldTable = this.table;
    int oldCapacity = oldTable.length;
    if (oldCapacity >= 1073741824)
    {
      this.threshold = 2147483647;
      return;
    }
    int newCapacity = oldCapacity << 1;
    int mask = newCapacity - 1;
    this.threshold = ((int)(newCapacity * this.loadFactor));
    Entry[] newTable = new Entry[newCapacity];
    for (int local_i = 0; local_i < oldCapacity; local_i++)
    {
      Entry local_e = oldTable[local_i];
      if (local_e != null)
      {
        int idx = local_e.hash & mask;
        Entry next = local_e.next;
        if (next == null)
        {
          newTable[idx] = local_e;
        }
        else
        {
          Entry lastRun = local_e;
          int lastIdx = idx;
          for (Entry last = next; last != null; last = last.next)
          {
            int local_k = last.hash & mask;
            if (local_k != lastIdx)
            {
              lastIdx = local_k;
              lastRun = last;
            }
          }
          newTable[lastIdx] = lastRun;
          for (Entry last = local_e; last != lastRun; last = last.next)
          {
            int local_k = last.hash & mask;
            newTable[local_k] = new Entry(last.hash, last.key, last.value, newTable[local_k]);
          }
        }
      }
    }
    this.table = newTable;
    recordModification(newTable);
  }
  
  public Object remove(Object key)
  {
    int hash = hash(key);
    Entry[] tab = this.table;
    int index = hash & tab.length - 1;
    Entry first = tab[index];
    Entry local_e = first;
    for (local_e = first; (local_e != null) && ((local_e.hash != hash) || (!eq(key, local_e.key))); local_e = local_e.next) {}
    synchronized (this)
    {
      if (tab == this.table) {
        if (local_e == null)
        {
          if (first == tab[index]) {
            return null;
          }
        }
        else
        {
          Object oldValue = local_e.value;
          if ((first == tab[index]) && (oldValue != null))
          {
            local_e.value = null;
            this.count -= 1;
            Entry head = local_e.next;
            for (Entry local_p = first; local_p != local_e; local_p = local_p.next) {
              head = new Entry(local_p.hash, local_p.key, local_p.value, head);
            }
            tab[index] = head;
            recordModification(head);
            return oldValue;
          }
        }
      }
      return sremove(key, hash);
    }
  }
  
  protected Object sremove(Object key, int hash)
  {
    Entry[] tab = this.table;
    int index = hash & tab.length - 1;
    Entry first = tab[index];
    for (Entry local_e = first; local_e != null; local_e = local_e.next) {
      if ((local_e.hash == hash) && (eq(key, local_e.key)))
      {
        Object oldValue = local_e.value;
        local_e.value = null;
        this.count -= 1;
        Entry head = local_e.next;
        for (Entry local_p = first; local_p != local_e; local_p = local_p.next) {
          head = new Entry(local_p.hash, local_p.key, local_p.value, head);
        }
        tab[index] = head;
        recordModification(head);
        return oldValue;
      }
    }
    return null;
  }
  
  public boolean containsValue(Object value)
  {
    if (value == null) {
      throw new NullPointerException();
    }
    Entry[] tab = getTableForReading();
    for (int local_i = 0; local_i < tab.length; local_i++) {
      for (Entry local_e = tab[local_i]; local_e != null; local_e = local_e.next) {
        if (value.equals(local_e.value)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean contains(Object value)
  {
    return containsValue(value);
  }
  
  public synchronized void putAll(Map local_t)
  {
    int local_n = local_t.size();
    if (local_n == 0) {
      return;
    }
    while (local_n >= this.threshold) {
      rehash();
    }
    Iterator local_it = local_t.entrySet().iterator();
    while (local_it.hasNext())
    {
      Map.Entry entry = (Map.Entry)local_it.next();
      Object key = entry.getKey();
      Object value = entry.getValue();
      put(key, value);
    }
  }
  
  public synchronized void clear()
  {
    Entry[] tab = this.table;
    for (int local_i = 0; local_i < tab.length; local_i++)
    {
      for (Entry local_e = tab[local_i]; local_e != null; local_e = local_e.next) {
        local_e.value = null;
      }
      tab[local_i] = null;
    }
    this.count = 0;
    recordModification(tab);
  }
  
  public synchronized Object clone()
  {
    try
    {
      ConcurrentReaderHashMap local_t = (ConcurrentReaderHashMap)super.clone();
      local_t.keySet = null;
      local_t.entrySet = null;
      local_t.values = null;
      Entry[] tab = this.table;
      local_t.table = new Entry[tab.length];
      Entry[] ttab = local_t.table;
      for (int local_i = 0; local_i < tab.length; local_i++)
      {
        Entry first = null;
        for (Entry local_e = tab[local_i]; local_e != null; local_e = local_e.next) {
          first = new Entry(local_e.hash, local_e.key, local_e.value, first);
        }
        ttab[local_i] = first;
      }
      return local_t;
    }
    catch (CloneNotSupportedException local_t)
    {
      throw new InternalError();
    }
  }
  
  public Set keySet()
  {
    Set local_ks = this.keySet;
    return this.keySet = new KeySet(null);
  }
  
  public Collection values()
  {
    Collection local_vs = this.values;
    return this.values = new Values(null);
  }
  
  public Set entrySet()
  {
    Set local_es = this.entrySet;
    return this.entrySet = new EntrySet(null);
  }
  
  protected synchronized boolean findAndRemoveEntry(Map.Entry entry)
  {
    Object key = entry.getKey();
    Object local_v = get(key);
    if ((local_v != null) && (local_v.equals(entry.getValue())))
    {
      remove(key);
      return true;
    }
    return false;
  }
  
  public Enumeration keys()
  {
    return new KeyIterator();
  }
  
  public Enumeration elements()
  {
    return new ValueIterator();
  }
  
  private synchronized void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    local_s.writeInt(this.table.length);
    local_s.writeInt(this.count);
    for (int index = this.table.length - 1; index >= 0; index--) {
      for (Entry entry = this.table[index]; entry != null; entry = entry.next)
      {
        local_s.writeObject(entry.key);
        local_s.writeObject(entry.value);
      }
    }
  }
  
  private synchronized void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    int numBuckets = local_s.readInt();
    this.table = new Entry[numBuckets];
    int size = local_s.readInt();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object key = local_s.readObject();
      Object value = local_s.readObject();
      put(key, value);
    }
  }
  
  public synchronized int capacity()
  {
    return this.table.length;
  }
  
  public float loadFactor()
  {
    return this.loadFactor;
  }
  
  protected class ValueIterator
    extends ConcurrentReaderHashMap.HashIterator
  {
    protected ValueIterator()
    {
      super();
    }
    
    protected Object returnValueOfNext()
    {
      return this.currentValue;
    }
  }
  
  protected class KeyIterator
    extends ConcurrentReaderHashMap.HashIterator
  {
    protected KeyIterator()
    {
      super();
    }
    
    protected Object returnValueOfNext()
    {
      return this.currentKey;
    }
  }
  
  protected class HashIterator
    implements Iterator, Enumeration
  {
    protected final ConcurrentReaderHashMap.Entry[] tab = ConcurrentReaderHashMap.this.getTableForReading();
    protected int index = this.tab.length - 1;
    protected ConcurrentReaderHashMap.Entry entry = null;
    protected Object currentKey;
    protected Object currentValue;
    protected ConcurrentReaderHashMap.Entry lastReturned = null;
    
    protected HashIterator() {}
    
    public boolean hasMoreElements()
    {
      return hasNext();
    }
    
    public Object nextElement()
    {
      return next();
    }
    
    public boolean hasNext()
    {
      do
      {
        if (this.entry != null)
        {
          Object local_v = this.entry.value;
          if (local_v != null)
          {
            this.currentKey = this.entry.key;
            this.currentValue = local_v;
            return true;
          }
        }
        for (this.entry = this.entry.next; (this.entry == null) && (this.index >= 0); this.entry = this.tab[(this.index--)]) {}
      } while (this.entry != null);
      this.currentKey = (this.currentValue = null);
      return false;
    }
    
    protected Object returnValueOfNext()
    {
      return this.entry;
    }
    
    public Object next()
    {
      if ((this.currentKey == null) && (!hasNext())) {
        throw new NoSuchElementException();
      }
      Object result = returnValueOfNext();
      this.lastReturned = this.entry;
      this.currentKey = (this.currentValue = null);
      this.entry = this.entry.next;
      return result;
    }
    
    public void remove()
    {
      if (this.lastReturned == null) {
        throw new IllegalStateException();
      }
      ConcurrentReaderHashMap.this.remove(this.lastReturned.key);
      this.lastReturned = null;
    }
  }
  
  protected static class Entry
    implements Map.Entry
  {
    protected final int hash;
    protected final Object key;
    protected final Entry next;
    protected volatile Object value;
    
    Entry(int hash, Object key, Object value, Entry next)
    {
      this.hash = hash;
      this.key = key;
      this.next = next;
      this.value = value;
    }
    
    public Object getKey()
    {
      return this.key;
    }
    
    public Object getValue()
    {
      return this.value;
    }
    
    public Object setValue(Object value)
    {
      if (value == null) {
        throw new NullPointerException();
      }
      Object oldValue = this.value;
      this.value = value;
      return oldValue;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry local_e = (Map.Entry)local_o;
      return (this.key.equals(local_e.getKey())) && (this.value.equals(local_e.getValue()));
    }
    
    public int hashCode()
    {
      return this.key.hashCode() ^ this.value.hashCode();
    }
    
    public String toString()
    {
      return this.key + "=" + this.value;
    }
  }
  
  private class EntrySet
    extends AbstractSet
  {
    private EntrySet() {}
    
    public Iterator iterator()
    {
      return new ConcurrentReaderHashMap.HashIterator(ConcurrentReaderHashMap.this);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry entry = (Map.Entry)local_o;
      Object local_v = ConcurrentReaderHashMap.this.get(entry.getKey());
      return (local_v != null) && (local_v.equals(entry.getValue()));
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      return ConcurrentReaderHashMap.this.findAndRemoveEntry((Map.Entry)local_o);
    }
    
    public int size()
    {
      return ConcurrentReaderHashMap.this.size();
    }
    
    public void clear()
    {
      ConcurrentReaderHashMap.this.clear();
    }
    
    EntrySet(ConcurrentReaderHashMap.1 local_x1)
    {
      this();
    }
  }
  
  private class Values
    extends AbstractCollection
  {
    private Values() {}
    
    public Iterator iterator()
    {
      return new ConcurrentReaderHashMap.ValueIterator(ConcurrentReaderHashMap.this);
    }
    
    public int size()
    {
      return ConcurrentReaderHashMap.this.size();
    }
    
    public boolean contains(Object local_o)
    {
      return ConcurrentReaderHashMap.this.containsValue(local_o);
    }
    
    public void clear()
    {
      ConcurrentReaderHashMap.this.clear();
    }
    
    Values(ConcurrentReaderHashMap.1 local_x1)
    {
      this();
    }
  }
  
  private class KeySet
    extends AbstractSet
  {
    private KeySet() {}
    
    public Iterator iterator()
    {
      return new ConcurrentReaderHashMap.KeyIterator(ConcurrentReaderHashMap.this);
    }
    
    public int size()
    {
      return ConcurrentReaderHashMap.this.size();
    }
    
    public boolean contains(Object local_o)
    {
      return ConcurrentReaderHashMap.this.containsKey(local_o);
    }
    
    public boolean remove(Object local_o)
    {
      return ConcurrentReaderHashMap.this.remove(local_o) != null;
    }
    
    public void clear()
    {
      ConcurrentReaderHashMap.this.clear();
    }
    
    KeySet(ConcurrentReaderHashMap.1 local_x1)
    {
      this();
    }
  }
  
  protected static class BarrierLock
    implements Serializable
  {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.ConcurrentReaderHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */