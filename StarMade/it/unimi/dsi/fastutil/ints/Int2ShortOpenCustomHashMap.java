package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Int2ShortOpenCustomHashMap
  extends AbstractInt2ShortMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient int[] key;
  protected transient short[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Int2ShortMap.FastEntrySet entries;
  protected volatile transient IntSet keys;
  protected volatile transient ShortCollection values;
  protected IntHash.Strategy strategy;
  
  public Int2ShortOpenCustomHashMap(int expected, float local_f, IntHash.Strategy strategy)
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
    this.key = new int[this.field_49];
    this.value = new short[this.field_49];
    this.used = new boolean[this.field_49];
  }
  
  public Int2ShortOpenCustomHashMap(int expected, IntHash.Strategy strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public Int2ShortOpenCustomHashMap(IntHash.Strategy strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public Int2ShortOpenCustomHashMap(Map<? extends Integer, ? extends Short> local_m, float local_f, IntHash.Strategy strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Int2ShortOpenCustomHashMap(Map<? extends Integer, ? extends Short> local_m, IntHash.Strategy strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Int2ShortOpenCustomHashMap(Int2ShortMap local_m, float local_f, IntHash.Strategy strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Int2ShortOpenCustomHashMap(Int2ShortMap local_m, IntHash.Strategy strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Int2ShortOpenCustomHashMap(int[] local_k, short[] local_v, float local_f, IntHash.Strategy strategy)
  {
    this(local_k.length, local_f, strategy);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Int2ShortOpenCustomHashMap(int[] local_k, short[] local_v, IntHash.Strategy strategy)
  {
    this(local_k, local_v, 0.75F, strategy);
  }
  
  public IntHash.Strategy strategy()
  {
    return this.strategy;
  }
  
  public short put(int local_k, short local_v)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        short oldValue = this.value[pos];
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
  
  public Short put(Integer local_ok, Short local_ov)
  {
    short local_v = local_ov.shortValue();
    int local_k = local_ok.intValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        Short oldValue = Short.valueOf(this.value[pos]);
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
  
  public short add(int local_k, short incr)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        short oldValue = this.value[pos];
        int tmp60_59 = pos;
        short[] tmp60_56 = this.value;
        tmp60_56[tmp60_59] = ((short)(tmp60_56[tmp60_59] + incr));
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = ((short)(this.defRetValue + incr));
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
    return last;
  }
  
  public short remove(int local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        short local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Short remove(Object local_ok)
  {
    int local_k = ((Integer)local_ok).intValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        short local_v = this.value[pos];
        shiftKeys(pos);
        return Short.valueOf(local_v);
      }
    }
    return null;
  }
  
  public Short get(Integer local_ok)
  {
    int local_k = local_ok.intValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return Short.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public short get(int local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(int local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(short local_v)
  {
    short[] value = this.value;
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
  
  public Int2ShortMap.FastEntrySet int2ShortEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public IntSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public ShortCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractShortCollection()
      {
        public ShortIterator iterator()
        {
          return new Int2ShortOpenCustomHashMap.ValueIterator(Int2ShortOpenCustomHashMap.this);
        }
        
        public int size()
        {
          return Int2ShortOpenCustomHashMap.this.size;
        }
        
        public boolean contains(short local_v)
        {
          return Int2ShortOpenCustomHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Int2ShortOpenCustomHashMap.this.clear();
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
    int[] key = this.key;
    short[] value = this.value;
    int newMask = newN - 1;
    int[] newKey = new int[newN];
    short[] newValue = new short[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      int local_k = key[local_i];
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
  
  public Int2ShortOpenCustomHashMap clone()
  {
    Int2ShortOpenCustomHashMap local_c;
    try
    {
      local_c = (Int2ShortOpenCustomHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((int[])this.key.clone());
    local_c.value = ((short[])this.value.clone());
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
      local_t ^= this.value[local_i];
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    int[] key = this.key;
    short[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeInt(key[local_e]);
      local_s.writeShort(value[local_e]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_49 = HashCommon.arraySize(this.size, this.field_48);
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.mask = (this.field_49 - 1);
    int[] key = this.key = new int[this.field_49];
    short[] value = this.value = new short[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      int local_k = local_s.readInt();
      short local_v = local_s.readShort();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Int2ShortOpenCustomHashMap.MapIterator
    implements ShortIterator
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public short nextShort()
    {
      return Int2ShortOpenCustomHashMap.this.value[nextEntry()];
    }
    
    public Short next()
    {
      return Short.valueOf(Int2ShortOpenCustomHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractIntSet
  {
    private KeySet() {}
    
    public IntIterator iterator()
    {
      return new Int2ShortOpenCustomHashMap.KeyIterator(Int2ShortOpenCustomHashMap.this);
    }
    
    public int size()
    {
      return Int2ShortOpenCustomHashMap.this.size;
    }
    
    public boolean contains(int local_k)
    {
      return Int2ShortOpenCustomHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(int local_k)
    {
      int oldSize = Int2ShortOpenCustomHashMap.this.size;
      Int2ShortOpenCustomHashMap.this.remove(local_k);
      return Int2ShortOpenCustomHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Int2ShortOpenCustomHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Int2ShortOpenCustomHashMap.MapIterator
    implements IntIterator
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public int nextInt()
    {
      return Int2ShortOpenCustomHashMap.this.key[nextEntry()];
    }
    
    public Integer next()
    {
      return Integer.valueOf(Int2ShortOpenCustomHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Int2ShortMap.Entry>
    implements Int2ShortMap.FastEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Int2ShortMap.Entry> iterator()
    {
      return new Int2ShortOpenCustomHashMap.EntryIterator(Int2ShortOpenCustomHashMap.this, null);
    }
    
    public ObjectIterator<Int2ShortMap.Entry> fastIterator()
    {
      return new Int2ShortOpenCustomHashMap.FastEntryIterator(Int2ShortOpenCustomHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Integer, Short> local_e = (Map.Entry)local_o;
      int local_k = ((Integer)local_e.getKey()).intValue();
      for (int pos = HashCommon.murmurHash3(Int2ShortOpenCustomHashMap.this.strategy.hashCode(local_k)) & Int2ShortOpenCustomHashMap.this.mask; Int2ShortOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Int2ShortOpenCustomHashMap.this.mask) {
        if (Int2ShortOpenCustomHashMap.this.strategy.equals(Int2ShortOpenCustomHashMap.this.key[pos], local_k)) {
          return Int2ShortOpenCustomHashMap.this.value[pos] == ((Short)local_e.getValue()).shortValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Integer, Short> local_e = (Map.Entry)local_o;
      int local_k = ((Integer)local_e.getKey()).intValue();
      for (int pos = HashCommon.murmurHash3(Int2ShortOpenCustomHashMap.this.strategy.hashCode(local_k)) & Int2ShortOpenCustomHashMap.this.mask; Int2ShortOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Int2ShortOpenCustomHashMap.this.mask) {
        if (Int2ShortOpenCustomHashMap.this.strategy.equals(Int2ShortOpenCustomHashMap.this.key[pos], local_k))
        {
          Int2ShortOpenCustomHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Int2ShortOpenCustomHashMap.this.size;
    }
    
    public void clear()
    {
      Int2ShortOpenCustomHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Int2ShortOpenCustomHashMap.MapIterator
    implements ObjectIterator<Int2ShortMap.Entry>
  {
    final AbstractInt2ShortMap.BasicEntry entry = new AbstractInt2ShortMap.BasicEntry(0, (short)0);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractInt2ShortMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Int2ShortOpenCustomHashMap.this.key[local_e];
      this.entry.value = Int2ShortOpenCustomHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Int2ShortOpenCustomHashMap.MapIterator
    implements ObjectIterator<Int2ShortMap.Entry>
  {
    private Int2ShortOpenCustomHashMap.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Int2ShortMap.Entry next()
    {
      return this.entry = new Int2ShortOpenCustomHashMap.MapEntry(Int2ShortOpenCustomHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Int2ShortOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Int2ShortOpenCustomHashMap.this.field_49;
    int last = -1;
    int field_2209 = Int2ShortOpenCustomHashMap.this.size;
    IntArrayList wrapped;
    
    private MapIterator()
    {
      boolean[] used = Int2ShortOpenCustomHashMap.this.used;
      while ((this.field_2209 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_2209 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_2209 -= 1;
      if (this.pos < 0)
      {
        int local_k = this.wrapped.getInt(-(this.last = --this.pos) - 2);
        for (int pos = HashCommon.murmurHash3(Int2ShortOpenCustomHashMap.this.strategy.hashCode(local_k)) & Int2ShortOpenCustomHashMap.this.mask; Int2ShortOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Int2ShortOpenCustomHashMap.this.mask) {
          if (Int2ShortOpenCustomHashMap.this.strategy.equals(Int2ShortOpenCustomHashMap.this.key[pos], local_k)) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_2209 != 0)
      {
        boolean[] local_k = Int2ShortOpenCustomHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Int2ShortOpenCustomHashMap.this.mask; Int2ShortOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Int2ShortOpenCustomHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Int2ShortOpenCustomHashMap.this.strategy.hashCode(Int2ShortOpenCustomHashMap.this.key[pos])) & Int2ShortOpenCustomHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Int2ShortOpenCustomHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new IntArrayList();
          }
          this.wrapped.add(Int2ShortOpenCustomHashMap.this.key[pos]);
        }
        Int2ShortOpenCustomHashMap.this.key[last] = Int2ShortOpenCustomHashMap.this.key[pos];
        Int2ShortOpenCustomHashMap.this.value[last] = Int2ShortOpenCustomHashMap.this.value[pos];
      }
      Int2ShortOpenCustomHashMap.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Int2ShortOpenCustomHashMap.this.remove(this.wrapped.getInt(-this.pos - 2));
        this.last = -1;
        return;
      }
      Int2ShortOpenCustomHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_2209 > 0))
      {
        this.field_2209 += 1;
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
    implements Int2ShortMap.Entry, Map.Entry<Integer, Short>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Integer getKey()
    {
      return Integer.valueOf(Int2ShortOpenCustomHashMap.this.key[this.index]);
    }
    
    public int getIntKey()
    {
      return Int2ShortOpenCustomHashMap.this.key[this.index];
    }
    
    public Short getValue()
    {
      return Short.valueOf(Int2ShortOpenCustomHashMap.this.value[this.index]);
    }
    
    public short getShortValue()
    {
      return Int2ShortOpenCustomHashMap.this.value[this.index];
    }
    
    public short setValue(short local_v)
    {
      short oldValue = Int2ShortOpenCustomHashMap.this.value[this.index];
      Int2ShortOpenCustomHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Short setValue(Short local_v)
    {
      return Short.valueOf(setValue(local_v.shortValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Integer, Short> local_e = (Map.Entry)local_o;
      return (Int2ShortOpenCustomHashMap.this.strategy.equals(Int2ShortOpenCustomHashMap.this.key[this.index], ((Integer)local_e.getKey()).intValue())) && (Int2ShortOpenCustomHashMap.this.value[this.index] == ((Short)local_e.getValue()).shortValue());
    }
    
    public int hashCode()
    {
      return Int2ShortOpenCustomHashMap.this.strategy.hashCode(Int2ShortOpenCustomHashMap.this.key[this.index]) ^ Int2ShortOpenCustomHashMap.this.value[this.index];
    }
    
    public String toString()
    {
      return Int2ShortOpenCustomHashMap.this.key[this.index] + "=>" + Int2ShortOpenCustomHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ShortOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */