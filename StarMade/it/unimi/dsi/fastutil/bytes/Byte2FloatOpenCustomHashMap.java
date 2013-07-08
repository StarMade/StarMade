package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.floats.FloatIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Byte2FloatOpenCustomHashMap
  extends AbstractByte2FloatMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient byte[] key;
  protected transient float[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Byte2FloatMap.FastEntrySet entries;
  protected volatile transient ByteSet keys;
  protected volatile transient FloatCollection values;
  protected ByteHash.Strategy strategy;
  
  public Byte2FloatOpenCustomHashMap(int expected, float local_f, ByteHash.Strategy strategy)
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
    this.key = new byte[this.field_49];
    this.value = new float[this.field_49];
    this.used = new boolean[this.field_49];
  }
  
  public Byte2FloatOpenCustomHashMap(int expected, ByteHash.Strategy strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public Byte2FloatOpenCustomHashMap(ByteHash.Strategy strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public Byte2FloatOpenCustomHashMap(Map<? extends Byte, ? extends Float> local_m, float local_f, ByteHash.Strategy strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Byte2FloatOpenCustomHashMap(Map<? extends Byte, ? extends Float> local_m, ByteHash.Strategy strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Byte2FloatOpenCustomHashMap(Byte2FloatMap local_m, float local_f, ByteHash.Strategy strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Byte2FloatOpenCustomHashMap(Byte2FloatMap local_m, ByteHash.Strategy strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Byte2FloatOpenCustomHashMap(byte[] local_k, float[] local_v, float local_f, ByteHash.Strategy strategy)
  {
    this(local_k.length, local_f, strategy);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Byte2FloatOpenCustomHashMap(byte[] local_k, float[] local_v, ByteHash.Strategy strategy)
  {
    this(local_k, local_v, 0.75F, strategy);
  }
  
  public ByteHash.Strategy strategy()
  {
    return this.strategy;
  }
  
  public float put(byte local_k, float local_v)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        float oldValue = this.value[pos];
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
  
  public Float put(Byte local_ok, Float local_ov)
  {
    float local_v = local_ov.floatValue();
    byte local_k = local_ok.byteValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        Float oldValue = Float.valueOf(this.value[pos]);
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
  
  public float add(byte local_k, float incr)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        float oldValue = this.value[pos];
        this.value[pos] += incr;
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = (this.defRetValue + incr);
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
  
  public float remove(byte local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        float local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Float remove(Object local_ok)
  {
    byte local_k = ((Byte)local_ok).byteValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        float local_v = this.value[pos];
        shiftKeys(pos);
        return Float.valueOf(local_v);
      }
    }
    return null;
  }
  
  public Float get(Byte local_ok)
  {
    byte local_k = local_ok.byteValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return Float.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public float get(byte local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(byte local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(float local_v)
  {
    float[] value = this.value;
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
  
  public Byte2FloatMap.FastEntrySet byte2FloatEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public ByteSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public FloatCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractFloatCollection()
      {
        public FloatIterator iterator()
        {
          return new Byte2FloatOpenCustomHashMap.ValueIterator(Byte2FloatOpenCustomHashMap.this);
        }
        
        public int size()
        {
          return Byte2FloatOpenCustomHashMap.this.size;
        }
        
        public boolean contains(float local_v)
        {
          return Byte2FloatOpenCustomHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Byte2FloatOpenCustomHashMap.this.clear();
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
    byte[] key = this.key;
    float[] value = this.value;
    int newMask = newN - 1;
    byte[] newKey = new byte[newN];
    float[] newValue = new float[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      byte local_k = key[local_i];
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
  
  public Byte2FloatOpenCustomHashMap clone()
  {
    Byte2FloatOpenCustomHashMap local_c;
    try
    {
      local_c = (Byte2FloatOpenCustomHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((byte[])this.key.clone());
    local_c.value = ((float[])this.value.clone());
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
      local_t ^= HashCommon.float2int(this.value[local_i]);
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    byte[] key = this.key;
    float[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeByte(key[local_e]);
      local_s.writeFloat(value[local_e]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_49 = HashCommon.arraySize(this.size, this.field_48);
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.mask = (this.field_49 - 1);
    byte[] key = this.key = new byte[this.field_49];
    float[] value = this.value = new float[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      byte local_k = local_s.readByte();
      float local_v = local_s.readFloat();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Byte2FloatOpenCustomHashMap.MapIterator
    implements FloatIterator
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public float nextFloat()
    {
      return Byte2FloatOpenCustomHashMap.this.value[nextEntry()];
    }
    
    public Float next()
    {
      return Float.valueOf(Byte2FloatOpenCustomHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractByteSet
  {
    private KeySet() {}
    
    public ByteIterator iterator()
    {
      return new Byte2FloatOpenCustomHashMap.KeyIterator(Byte2FloatOpenCustomHashMap.this);
    }
    
    public int size()
    {
      return Byte2FloatOpenCustomHashMap.this.size;
    }
    
    public boolean contains(byte local_k)
    {
      return Byte2FloatOpenCustomHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(byte local_k)
    {
      int oldSize = Byte2FloatOpenCustomHashMap.this.size;
      Byte2FloatOpenCustomHashMap.this.remove(local_k);
      return Byte2FloatOpenCustomHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Byte2FloatOpenCustomHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Byte2FloatOpenCustomHashMap.MapIterator
    implements ByteIterator
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public byte nextByte()
    {
      return Byte2FloatOpenCustomHashMap.this.key[nextEntry()];
    }
    
    public Byte next()
    {
      return Byte.valueOf(Byte2FloatOpenCustomHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Byte2FloatMap.Entry>
    implements Byte2FloatMap.FastEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Byte2FloatMap.Entry> iterator()
    {
      return new Byte2FloatOpenCustomHashMap.EntryIterator(Byte2FloatOpenCustomHashMap.this, null);
    }
    
    public ObjectIterator<Byte2FloatMap.Entry> fastIterator()
    {
      return new Byte2FloatOpenCustomHashMap.FastEntryIterator(Byte2FloatOpenCustomHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Byte, Float> local_e = (Map.Entry)local_o;
      byte local_k = ((Byte)local_e.getKey()).byteValue();
      for (int pos = HashCommon.murmurHash3(Byte2FloatOpenCustomHashMap.this.strategy.hashCode(local_k)) & Byte2FloatOpenCustomHashMap.this.mask; Byte2FloatOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Byte2FloatOpenCustomHashMap.this.mask) {
        if (Byte2FloatOpenCustomHashMap.this.strategy.equals(Byte2FloatOpenCustomHashMap.this.key[pos], local_k)) {
          return Byte2FloatOpenCustomHashMap.this.value[pos] == ((Float)local_e.getValue()).floatValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Byte, Float> local_e = (Map.Entry)local_o;
      byte local_k = ((Byte)local_e.getKey()).byteValue();
      for (int pos = HashCommon.murmurHash3(Byte2FloatOpenCustomHashMap.this.strategy.hashCode(local_k)) & Byte2FloatOpenCustomHashMap.this.mask; Byte2FloatOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Byte2FloatOpenCustomHashMap.this.mask) {
        if (Byte2FloatOpenCustomHashMap.this.strategy.equals(Byte2FloatOpenCustomHashMap.this.key[pos], local_k))
        {
          Byte2FloatOpenCustomHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Byte2FloatOpenCustomHashMap.this.size;
    }
    
    public void clear()
    {
      Byte2FloatOpenCustomHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Byte2FloatOpenCustomHashMap.MapIterator
    implements ObjectIterator<Byte2FloatMap.Entry>
  {
    final AbstractByte2FloatMap.BasicEntry entry = new AbstractByte2FloatMap.BasicEntry((byte)0, 0.0F);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractByte2FloatMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Byte2FloatOpenCustomHashMap.this.key[local_e];
      this.entry.value = Byte2FloatOpenCustomHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Byte2FloatOpenCustomHashMap.MapIterator
    implements ObjectIterator<Byte2FloatMap.Entry>
  {
    private Byte2FloatOpenCustomHashMap.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Byte2FloatMap.Entry next()
    {
      return this.entry = new Byte2FloatOpenCustomHashMap.MapEntry(Byte2FloatOpenCustomHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Byte2FloatOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Byte2FloatOpenCustomHashMap.this.field_49;
    int last = -1;
    int field_1859 = Byte2FloatOpenCustomHashMap.this.size;
    ByteArrayList wrapped;
    
    private MapIterator()
    {
      boolean[] used = Byte2FloatOpenCustomHashMap.this.used;
      while ((this.field_1859 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_1859 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_1859 -= 1;
      if (this.pos < 0)
      {
        byte local_k = this.wrapped.getByte(-(this.last = --this.pos) - 2);
        for (int pos = HashCommon.murmurHash3(Byte2FloatOpenCustomHashMap.this.strategy.hashCode(local_k)) & Byte2FloatOpenCustomHashMap.this.mask; Byte2FloatOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Byte2FloatOpenCustomHashMap.this.mask) {
          if (Byte2FloatOpenCustomHashMap.this.strategy.equals(Byte2FloatOpenCustomHashMap.this.key[pos], local_k)) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_1859 != 0)
      {
        boolean[] local_k = Byte2FloatOpenCustomHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Byte2FloatOpenCustomHashMap.this.mask; Byte2FloatOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Byte2FloatOpenCustomHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Byte2FloatOpenCustomHashMap.this.strategy.hashCode(Byte2FloatOpenCustomHashMap.this.key[pos])) & Byte2FloatOpenCustomHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Byte2FloatOpenCustomHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ByteArrayList();
          }
          this.wrapped.add(Byte2FloatOpenCustomHashMap.this.key[pos]);
        }
        Byte2FloatOpenCustomHashMap.this.key[last] = Byte2FloatOpenCustomHashMap.this.key[pos];
        Byte2FloatOpenCustomHashMap.this.value[last] = Byte2FloatOpenCustomHashMap.this.value[pos];
      }
      Byte2FloatOpenCustomHashMap.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Byte2FloatOpenCustomHashMap.this.remove(this.wrapped.getByte(-this.pos - 2));
        this.last = -1;
        return;
      }
      Byte2FloatOpenCustomHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_1859 > 0))
      {
        this.field_1859 += 1;
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
    implements Byte2FloatMap.Entry, Map.Entry<Byte, Float>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Byte getKey()
    {
      return Byte.valueOf(Byte2FloatOpenCustomHashMap.this.key[this.index]);
    }
    
    public byte getByteKey()
    {
      return Byte2FloatOpenCustomHashMap.this.key[this.index];
    }
    
    public Float getValue()
    {
      return Float.valueOf(Byte2FloatOpenCustomHashMap.this.value[this.index]);
    }
    
    public float getFloatValue()
    {
      return Byte2FloatOpenCustomHashMap.this.value[this.index];
    }
    
    public float setValue(float local_v)
    {
      float oldValue = Byte2FloatOpenCustomHashMap.this.value[this.index];
      Byte2FloatOpenCustomHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Float setValue(Float local_v)
    {
      return Float.valueOf(setValue(local_v.floatValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Byte, Float> local_e = (Map.Entry)local_o;
      return (Byte2FloatOpenCustomHashMap.this.strategy.equals(Byte2FloatOpenCustomHashMap.this.key[this.index], ((Byte)local_e.getKey()).byteValue())) && (Byte2FloatOpenCustomHashMap.this.value[this.index] == ((Float)local_e.getValue()).floatValue());
    }
    
    public int hashCode()
    {
      return Byte2FloatOpenCustomHashMap.this.strategy.hashCode(Byte2FloatOpenCustomHashMap.this.key[this.index]) ^ HashCommon.float2int(Byte2FloatOpenCustomHashMap.this.value[this.index]);
    }
    
    public String toString()
    {
      return Byte2FloatOpenCustomHashMap.this.key[this.index] + "=>" + Byte2FloatOpenCustomHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2FloatOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */