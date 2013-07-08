package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Short2ObjectOpenCustomHashMap<V>
  extends AbstractShort2ObjectMap<V>
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient short[] key;
  protected transient V[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Short2ObjectMap.FastEntrySet<V> entries;
  protected volatile transient ShortSet keys;
  protected volatile transient ObjectCollection<V> values;
  protected ShortHash.Strategy strategy;
  
  public Short2ObjectOpenCustomHashMap(int expected, float local_f, ShortHash.Strategy strategy)
  {
    this.strategy = strategy;
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
    this.key = new short[this.field_49];
    this.value = ((Object[])new Object[this.field_49]);
    this.used = new boolean[this.field_49];
  }
  
  public Short2ObjectOpenCustomHashMap(int expected, ShortHash.Strategy strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public Short2ObjectOpenCustomHashMap(ShortHash.Strategy strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public Short2ObjectOpenCustomHashMap(Map<? extends Short, ? extends V> local_m, float local_f, ShortHash.Strategy strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Short2ObjectOpenCustomHashMap(Map<? extends Short, ? extends V> local_m, ShortHash.Strategy strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Short2ObjectOpenCustomHashMap(Short2ObjectMap<V> local_m, float local_f, ShortHash.Strategy strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Short2ObjectOpenCustomHashMap(Short2ObjectMap<V> local_m, ShortHash.Strategy strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Short2ObjectOpenCustomHashMap(short[] local_k, V[] local_v, float local_f, ShortHash.Strategy strategy)
  {
    this(local_k.length, local_f, strategy);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Short2ObjectOpenCustomHashMap(short[] local_k, V[] local_v, ShortHash.Strategy strategy)
  {
    this(local_k, local_v, 0.75F, strategy);
  }
  
  public ShortHash.Strategy strategy()
  {
    return this.strategy;
  }
  
  public V put(short local_k, V local_v)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        V oldValue = this.value[pos];
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
  
  public V put(Short local_ok, V local_ov)
  {
    V local_v = local_ov;
    short local_k = local_ok.shortValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        V oldValue = this.value[pos];
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
  
  protected final int shiftKeys(int pos)
  {
    int last;
    for (;;)
    {
      for (pos = (last = pos) + 1 & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask)
      {
        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
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
    this.value[last] = null;
    return last;
  }
  
  public V remove(short local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        V local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public V remove(Object local_ok)
  {
    short local_k = ((Short)local_ok).shortValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        V local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public V get(Short local_ok)
  {
    short local_k = local_ok.shortValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public V get(short local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(short local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(Object local_v)
  {
    V[] value = this.value;
    boolean[] used = this.used;
    int local_i = this.field_49;
    while (local_i-- != 0) {
      if ((used[local_i] != 0) && (value[local_i] == null ? local_v == null : value[local_i].equals(local_v))) {
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
    ObjectArrays.fill(this.value, null);
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
  
  public Short2ObjectMap.FastEntrySet<V> short2ObjectEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public ShortSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public ObjectCollection<V> values()
  {
    if (this.values == null) {
      this.values = new AbstractObjectCollection()
      {
        public ObjectIterator<V> iterator()
        {
          return new Short2ObjectOpenCustomHashMap.ValueIterator(Short2ObjectOpenCustomHashMap.this);
        }
        
        public int size()
        {
          return Short2ObjectOpenCustomHashMap.this.size;
        }
        
        public boolean contains(Object local_v)
        {
          return Short2ObjectOpenCustomHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Short2ObjectOpenCustomHashMap.this.clear();
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
    short[] key = this.key;
    V[] value = this.value;
    int newMask = newN - 1;
    short[] newKey = new short[newN];
    V[] newValue = (Object[])new Object[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      short local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
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
  
  public Short2ObjectOpenCustomHashMap<V> clone()
  {
    Short2ObjectOpenCustomHashMap<V> local_c;
    try
    {
      local_c = (Short2ObjectOpenCustomHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((short[])this.key.clone());
    local_c.value = ((Object[])this.value.clone());
    local_c.used = ((boolean[])this.used.clone());
    local_c.strategy = this.strategy;
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
      local_t = this.strategy.hashCode(this.key[local_i]);
      if (this != this.value[local_i]) {
        local_t ^= (this.value[local_i] == null ? 0 : this.value[local_i].hashCode());
      }
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    short[] key = this.key;
    V[] value = this.value;
    Short2ObjectOpenCustomHashMap<V>.MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeShort(key[local_e]);
      local_s.writeObject(value[local_e]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_49 = HashCommon.arraySize(this.size, this.field_48);
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.mask = (this.field_49 - 1);
    short[] key = this.key = new short[this.field_49];
    V[] value = this.value = (Object[])new Object[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      short local_k = local_s.readShort();
      V local_v = local_s.readObject();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Short2ObjectOpenCustomHashMap<V>.MapIterator
    implements ObjectIterator<V>
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public V next()
    {
      return Short2ObjectOpenCustomHashMap.this.value[nextEntry()];
    }
  }
  
  private final class KeySet
    extends AbstractShortSet
  {
    private KeySet() {}
    
    public ShortIterator iterator()
    {
      return new Short2ObjectOpenCustomHashMap.KeyIterator(Short2ObjectOpenCustomHashMap.this);
    }
    
    public int size()
    {
      return Short2ObjectOpenCustomHashMap.this.size;
    }
    
    public boolean contains(short local_k)
    {
      return Short2ObjectOpenCustomHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(short local_k)
    {
      int oldSize = Short2ObjectOpenCustomHashMap.this.size;
      Short2ObjectOpenCustomHashMap.this.remove(local_k);
      return Short2ObjectOpenCustomHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Short2ObjectOpenCustomHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Short2ObjectOpenCustomHashMap.MapIterator
    implements ShortIterator
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public short nextShort()
    {
      return Short2ObjectOpenCustomHashMap.this.key[nextEntry()];
    }
    
    public Short next()
    {
      return Short.valueOf(Short2ObjectOpenCustomHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Short2ObjectMap.Entry<V>>
    implements Short2ObjectMap.FastEntrySet<V>
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Short2ObjectMap.Entry<V>> iterator()
    {
      return new Short2ObjectOpenCustomHashMap.EntryIterator(Short2ObjectOpenCustomHashMap.this, null);
    }
    
    public ObjectIterator<Short2ObjectMap.Entry<V>> fastIterator()
    {
      return new Short2ObjectOpenCustomHashMap.FastEntryIterator(Short2ObjectOpenCustomHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Short, V> local_e = (Map.Entry)local_o;
      short local_k = ((Short)local_e.getKey()).shortValue();
      for (int pos = HashCommon.murmurHash3(Short2ObjectOpenCustomHashMap.this.strategy.hashCode(local_k)) & Short2ObjectOpenCustomHashMap.this.mask; Short2ObjectOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Short2ObjectOpenCustomHashMap.this.mask) {
        if (Short2ObjectOpenCustomHashMap.this.strategy.equals(Short2ObjectOpenCustomHashMap.this.key[pos], local_k)) {
          return Short2ObjectOpenCustomHashMap.this.value[pos] == null ? false : local_e.getValue() == null ? true : Short2ObjectOpenCustomHashMap.this.value[pos].equals(local_e.getValue());
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Short, V> local_e = (Map.Entry)local_o;
      short local_k = ((Short)local_e.getKey()).shortValue();
      for (int pos = HashCommon.murmurHash3(Short2ObjectOpenCustomHashMap.this.strategy.hashCode(local_k)) & Short2ObjectOpenCustomHashMap.this.mask; Short2ObjectOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Short2ObjectOpenCustomHashMap.this.mask) {
        if (Short2ObjectOpenCustomHashMap.this.strategy.equals(Short2ObjectOpenCustomHashMap.this.key[pos], local_k))
        {
          Short2ObjectOpenCustomHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Short2ObjectOpenCustomHashMap.this.size;
    }
    
    public void clear()
    {
      Short2ObjectOpenCustomHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Short2ObjectOpenCustomHashMap<V>.MapIterator
    implements ObjectIterator<Short2ObjectMap.Entry<V>>
  {
    final AbstractShort2ObjectMap.BasicEntry<V> entry = new AbstractShort2ObjectMap.BasicEntry((short)0, null);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractShort2ObjectMap.BasicEntry<V> next()
    {
      int local_e = nextEntry();
      this.entry.key = Short2ObjectOpenCustomHashMap.this.key[local_e];
      this.entry.value = Short2ObjectOpenCustomHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Short2ObjectOpenCustomHashMap<V>.MapIterator
    implements ObjectIterator<Short2ObjectMap.Entry<V>>
  {
    private Short2ObjectOpenCustomHashMap<V>.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Short2ObjectMap.Entry<V> next()
    {
      return this.entry = new Short2ObjectOpenCustomHashMap.MapEntry(Short2ObjectOpenCustomHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Short2ObjectOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Short2ObjectOpenCustomHashMap.this.field_49;
    int last = -1;
    int field_1923 = Short2ObjectOpenCustomHashMap.this.size;
    ShortArrayList wrapped;
    
    private MapIterator()
    {
      boolean[] used = Short2ObjectOpenCustomHashMap.this.used;
      while ((this.field_1923 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_1923 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_1923 -= 1;
      if (this.pos < 0)
      {
        short local_k = this.wrapped.getShort(-(this.last = --this.pos) - 2);
        for (int pos = HashCommon.murmurHash3(Short2ObjectOpenCustomHashMap.this.strategy.hashCode(local_k)) & Short2ObjectOpenCustomHashMap.this.mask; Short2ObjectOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Short2ObjectOpenCustomHashMap.this.mask) {
          if (Short2ObjectOpenCustomHashMap.this.strategy.equals(Short2ObjectOpenCustomHashMap.this.key[pos], local_k)) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_1923 != 0)
      {
        boolean[] local_k = Short2ObjectOpenCustomHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Short2ObjectOpenCustomHashMap.this.mask; Short2ObjectOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Short2ObjectOpenCustomHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Short2ObjectOpenCustomHashMap.this.strategy.hashCode(Short2ObjectOpenCustomHashMap.this.key[pos])) & Short2ObjectOpenCustomHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Short2ObjectOpenCustomHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ShortArrayList();
          }
          this.wrapped.add(Short2ObjectOpenCustomHashMap.this.key[pos]);
        }
        Short2ObjectOpenCustomHashMap.this.key[last] = Short2ObjectOpenCustomHashMap.this.key[pos];
        Short2ObjectOpenCustomHashMap.this.value[last] = Short2ObjectOpenCustomHashMap.this.value[pos];
      }
      Short2ObjectOpenCustomHashMap.this.used[last] = false;
      Short2ObjectOpenCustomHashMap.this.value[last] = null;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Short2ObjectOpenCustomHashMap.this.remove(this.wrapped.getShort(-this.pos - 2));
        this.last = -1;
        return;
      }
      Short2ObjectOpenCustomHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_1923 > 0))
      {
        this.field_1923 += 1;
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
    implements Short2ObjectMap.Entry<V>, Map.Entry<Short, V>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Short getKey()
    {
      return Short.valueOf(Short2ObjectOpenCustomHashMap.this.key[this.index]);
    }
    
    public short getShortKey()
    {
      return Short2ObjectOpenCustomHashMap.this.key[this.index];
    }
    
    public V getValue()
    {
      return Short2ObjectOpenCustomHashMap.this.value[this.index];
    }
    
    public V setValue(V local_v)
    {
      V oldValue = Short2ObjectOpenCustomHashMap.this.value[this.index];
      Short2ObjectOpenCustomHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Short, V> local_e = (Map.Entry)local_o;
      return (Short2ObjectOpenCustomHashMap.this.strategy.equals(Short2ObjectOpenCustomHashMap.this.key[this.index], ((Short)local_e.getKey()).shortValue())) && (Short2ObjectOpenCustomHashMap.this.value[this.index] == null ? local_e.getValue() == null : Short2ObjectOpenCustomHashMap.this.value[this.index].equals(local_e.getValue()));
    }
    
    public int hashCode()
    {
      return Short2ObjectOpenCustomHashMap.this.strategy.hashCode(Short2ObjectOpenCustomHashMap.this.key[this.index]) ^ (Short2ObjectOpenCustomHashMap.this.value[this.index] == null ? 0 : Short2ObjectOpenCustomHashMap.this.value[this.index].hashCode());
    }
    
    public String toString()
    {
      return Short2ObjectOpenCustomHashMap.this.key[this.index] + "=>" + Short2ObjectOpenCustomHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ObjectOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */