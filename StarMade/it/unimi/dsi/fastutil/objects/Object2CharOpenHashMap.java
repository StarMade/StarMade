package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Object2CharOpenHashMap<K>
  extends AbstractObject2CharMap<K>
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient K[] key;
  protected transient char[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Object2CharMap.FastEntrySet<K> entries;
  protected volatile transient ObjectSet<K> keys;
  protected volatile transient CharCollection values;
  
  public Object2CharOpenHashMap(int expected, float local_f)
  {
    if ((local_f <= 0.0F) || (local_f > 1.0F)) {
      throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
    }
    if (expected < 0) {
      throw new IllegalArgumentException("The expected number of elements must be nonnegative");
    }
    this.field_48 = local_f;
    this.field_49 = HashCommon.arraySize(expected, local_f);
    this.mask = (this.field_49 - 1);
    this.maxFill = HashCommon.maxFill(this.field_49, local_f);
    this.key = ((Object[])new Object[this.field_49]);
    this.value = new char[this.field_49];
    this.used = new boolean[this.field_49];
  }
  
  public Object2CharOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Object2CharOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Object2CharOpenHashMap(Map<? extends K, ? extends Character> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Object2CharOpenHashMap(Map<? extends K, ? extends Character> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Object2CharOpenHashMap(Object2CharMap<K> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Object2CharOpenHashMap(Object2CharMap<K> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Object2CharOpenHashMap(K[] local_k, char[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Object2CharOpenHashMap(K[] local_k, char[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public char put(K local_k, char local_v)
  {
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k))
      {
        char oldValue = this.value[pos];
        this.value[pos] = local_v;
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = local_v;
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_48));
    }
    return this.defRetValue;
  }
  
  public Character put(K local_ok, Character local_ov)
  {
    char local_v = local_ov.charValue();
    K local_k = local_ok;
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k))
      {
        Character oldValue = Character.valueOf(this.value[pos]);
        this.value[pos] = local_v;
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = local_v;
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_48));
    }
    return null;
  }
  
  protected final int shiftKeys(int pos)
  {
    int last;
    for (;;)
    {
      for (pos = (last = pos) + 1 & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask)
      {
        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(this.key[pos].hashCode())) & this.mask;
        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
          break;
        }
      }
      if (this.used[pos] == 0) {
        break;
      }
      this.key[last] = this.key[pos];
      this.value[last] = this.value[pos];
    }
    this.used[last] = false;
    this.key[last] = null;
    return last;
  }
  
  public char removeChar(Object local_k)
  {
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k))
      {
        this.size -= 1;
        char local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Character remove(Object local_ok)
  {
    K local_k = local_ok;
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k))
      {
        this.size -= 1;
        char local_v = this.value[pos];
        shiftKeys(pos);
        return Character.valueOf(local_v);
      }
    }
    return null;
  }
  
  public char getChar(Object local_k)
  {
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k)) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(Object local_k)
  {
    for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == null ? local_k == null : this.key[pos].equals(local_k)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(char local_v)
  {
    char[] value = this.value;
    boolean[] used = this.used;
    int local_i = this.field_49;
    while (local_i-- != 0) {
      if ((used[local_i] != 0) && (value[local_i] == local_v)) {
        return true;
      }
    }
    return false;
  }
  
  public void clear()
  {
    if (this.size == 0) {
      return;
    }
    this.size = 0;
    BooleanArrays.fill(this.used, false);
    ObjectArrays.fill(this.key, null);
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  @Deprecated
  public void growthFactor(int growthFactor) {}
  
  @Deprecated
  public int growthFactor()
  {
    return 16;
  }
  
  public Object2CharMap.FastEntrySet<K> object2CharEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public ObjectSet<K> keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public CharCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractCharCollection()
      {
        public CharIterator iterator()
        {
          return new Object2CharOpenHashMap.ValueIterator(Object2CharOpenHashMap.this);
        }
        
        public int size()
        {
          return Object2CharOpenHashMap.this.size;
        }
        
        public boolean contains(char local_v)
        {
          return Object2CharOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Object2CharOpenHashMap.this.clear();
        }
      };
    }
    return this.values;
  }
  
  @Deprecated
  public boolean rehash()
  {
    return true;
  }
  
  public boolean trim()
  {
    int local_l = HashCommon.arraySize(this.size, this.field_48);
    if (local_l >= this.field_49) {
      return true;
    }
    try
    {
      rehash(local_l);
    }
    catch (OutOfMemoryError cantDoIt)
    {
      return false;
    }
    return true;
  }
  
  public boolean trim(int local_n)
  {
    int local_l = HashCommon.nextPowerOfTwo((int)Math.ceil(local_n / this.field_48));
    if (this.field_49 <= local_l) {
      return true;
    }
    try
    {
      rehash(local_l);
    }
    catch (OutOfMemoryError cantDoIt)
    {
      return false;
    }
    return true;
  }
  
  protected void rehash(int newN)
  {
    int local_i = 0;
    boolean[] used = this.used;
    K[] key = this.key;
    char[] value = this.value;
    int newMask = newN - 1;
    K[] newKey = (Object[])new Object[newN];
    char[] newValue = new char[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      K local_k = key[local_i];
      for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
      newUsed[pos] = true;
      newKey[pos] = local_k;
      newValue[pos] = value[local_i];
      local_i++;
    }
    this.field_49 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.key = newKey;
    this.value = newValue;
    this.used = newUsed;
  }
  
  public Object2CharOpenHashMap<K> clone()
  {
    Object2CharOpenHashMap<K> local_c;
    try
    {
      local_c = (Object2CharOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((Object[])this.key.clone());
    local_c.value = ((char[])this.value.clone());
    local_c.used = ((boolean[])this.used.clone());
    return local_c;
  }
  
  public int hashCode()
  {
    int local_h = 0;
    int local_j = this.size;
    int local_i = 0;
    int local_t = 0;
    while (local_j-- != 0)
    {
      while (this.used[local_i] == 0) {
        local_i++;
      }
      if (this != this.key[local_i]) {
        local_t = this.key[local_i] == null ? 0 : this.key[local_i].hashCode();
      }
      local_t ^= this.value[local_i];
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    K[] key = this.key;
    char[] value = this.value;
    Object2CharOpenHashMap<K>.MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeObject(key[local_e]);
      local_s.writeChar(value[local_e]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_49 = HashCommon.arraySize(this.size, this.field_48);
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.mask = (this.field_49 - 1);
    K[] key = this.key = (Object[])new Object[this.field_49];
    char[] value = this.value = new char[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      K local_k = local_s.readObject();
      char local_v = local_s.readChar();
      for (pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Object2CharOpenHashMap.MapIterator
    implements CharIterator
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public char nextChar()
    {
      return Object2CharOpenHashMap.this.value[nextEntry()];
    }
    
    public Character next()
    {
      return Character.valueOf(Object2CharOpenHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractObjectSet<K>
  {
    private KeySet() {}
    
    public ObjectIterator<K> iterator()
    {
      return new Object2CharOpenHashMap.KeyIterator(Object2CharOpenHashMap.this);
    }
    
    public int size()
    {
      return Object2CharOpenHashMap.this.size;
    }
    
    public boolean contains(Object local_k)
    {
      return Object2CharOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(Object local_k)
    {
      int oldSize = Object2CharOpenHashMap.this.size;
      Object2CharOpenHashMap.this.remove(local_k);
      return Object2CharOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Object2CharOpenHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Object2CharOpenHashMap<K>.MapIterator
    implements ObjectIterator<K>
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public K next()
    {
      return Object2CharOpenHashMap.this.key[nextEntry()];
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Object2CharMap.Entry<K>>
    implements Object2CharMap.FastEntrySet<K>
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Object2CharMap.Entry<K>> iterator()
    {
      return new Object2CharOpenHashMap.EntryIterator(Object2CharOpenHashMap.this, null);
    }
    
    public ObjectIterator<Object2CharMap.Entry<K>> fastIterator()
    {
      return new Object2CharOpenHashMap.FastEntryIterator(Object2CharOpenHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Character> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & Object2CharOpenHashMap.this.mask; Object2CharOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Object2CharOpenHashMap.this.mask) {
        if (Object2CharOpenHashMap.this.key[pos] == null ? local_k == null : Object2CharOpenHashMap.this.key[pos].equals(local_k)) {
          return Object2CharOpenHashMap.this.value[pos] == ((Character)local_e.getValue()).charValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Character> local_e = (Map.Entry)local_o;
      K local_k = local_e.getKey();
      for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & Object2CharOpenHashMap.this.mask; Object2CharOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Object2CharOpenHashMap.this.mask) {
        if (Object2CharOpenHashMap.this.key[pos] == null ? local_k == null : Object2CharOpenHashMap.this.key[pos].equals(local_k))
        {
          Object2CharOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Object2CharOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Object2CharOpenHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Object2CharOpenHashMap<K>.MapIterator
    implements ObjectIterator<Object2CharMap.Entry<K>>
  {
    final AbstractObject2CharMap.BasicEntry<K> entry = new AbstractObject2CharMap.BasicEntry(null, '\000');
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractObject2CharMap.BasicEntry<K> next()
    {
      int local_e = nextEntry();
      this.entry.key = Object2CharOpenHashMap.this.key[local_e];
      this.entry.value = Object2CharOpenHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Object2CharOpenHashMap<K>.MapIterator
    implements ObjectIterator<Object2CharMap.Entry<K>>
  {
    private Object2CharOpenHashMap<K>.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Object2CharMap.Entry<K> next()
    {
      return this.entry = new Object2CharOpenHashMap.MapEntry(Object2CharOpenHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Object2CharOpenHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Object2CharOpenHashMap.this.field_49;
    int last = -1;
    int field_1925 = Object2CharOpenHashMap.this.size;
    ObjectArrayList<K> wrapped;
    
    private MapIterator()
    {
      boolean[] used = Object2CharOpenHashMap.this.used;
      while ((this.field_1925 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_1925 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_1925 -= 1;
      if (this.pos < 0)
      {
        Object local_k = this.wrapped.get(-(this.last = --this.pos) - 2);
        for (int pos = (local_k == null ? 142593372 : HashCommon.murmurHash3(local_k.hashCode())) & Object2CharOpenHashMap.this.mask; Object2CharOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Object2CharOpenHashMap.this.mask) {
          if (Object2CharOpenHashMap.this.key[pos] == null ? local_k == null : Object2CharOpenHashMap.this.key[pos].equals(local_k)) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_1925 != 0)
      {
        boolean[] local_k = Object2CharOpenHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Object2CharOpenHashMap.this.mask; Object2CharOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Object2CharOpenHashMap.this.mask)
        {
          int slot = (Object2CharOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(Object2CharOpenHashMap.this.key[pos].hashCode())) & Object2CharOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Object2CharOpenHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ObjectArrayList();
          }
          this.wrapped.add(Object2CharOpenHashMap.this.key[pos]);
        }
        Object2CharOpenHashMap.this.key[last] = Object2CharOpenHashMap.this.key[pos];
        Object2CharOpenHashMap.this.value[last] = Object2CharOpenHashMap.this.value[pos];
      }
      Object2CharOpenHashMap.this.used[last] = false;
      Object2CharOpenHashMap.this.key[last] = null;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Object2CharOpenHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
        this.last = -1;
        return;
      }
      Object2CharOpenHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_1925 > 0))
      {
        this.field_1925 += 1;
        nextEntry();
      }
      this.last = -1;
    }
    
    public int skip(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasNext())) {
        nextEntry();
      }
      return local_n - local_i - 1;
    }
  }
  
  private final class MapEntry
    implements Object2CharMap.Entry<K>, Map.Entry<K, Character>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public K getKey()
    {
      return Object2CharOpenHashMap.this.key[this.index];
    }
    
    public Character getValue()
    {
      return Character.valueOf(Object2CharOpenHashMap.this.value[this.index]);
    }
    
    public char getCharValue()
    {
      return Object2CharOpenHashMap.this.value[this.index];
    }
    
    public char setValue(char local_v)
    {
      char oldValue = Object2CharOpenHashMap.this.value[this.index];
      Object2CharOpenHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Character setValue(Character local_v)
    {
      return Character.valueOf(setValue(local_v.charValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Character> local_e = (Map.Entry)local_o;
      return (Object2CharOpenHashMap.this.key[this.index] == null ? local_e.getKey() == null : Object2CharOpenHashMap.this.key[this.index].equals(local_e.getKey())) && (Object2CharOpenHashMap.this.value[this.index] == ((Character)local_e.getValue()).charValue());
    }
    
    public int hashCode()
    {
      return (Object2CharOpenHashMap.this.key[this.index] == null ? 0 : Object2CharOpenHashMap.this.key[this.index].hashCode()) ^ Object2CharOpenHashMap.this.value[this.index];
    }
    
    public String toString()
    {
      return Object2CharOpenHashMap.this.key[this.index] + "=>" + Object2CharOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2CharOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */