package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Float2IntOpenHashMap
  extends AbstractFloat2IntMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient float[] key;
  protected transient int[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Float2IntMap.FastEntrySet entries;
  protected volatile transient FloatSet keys;
  protected volatile transient IntCollection values;
  
  public Float2IntOpenHashMap(int expected, float local_f)
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
    this.key = new float[this.field_49];
    this.value = new int[this.field_49];
    this.used = new boolean[this.field_49];
  }
  
  public Float2IntOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Float2IntOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Float2IntOpenHashMap(Map<? extends Float, ? extends Integer> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Float2IntOpenHashMap(Map<? extends Float, ? extends Integer> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Float2IntOpenHashMap(Float2IntMap local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Float2IntOpenHashMap(Float2IntMap local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Float2IntOpenHashMap(float[] local_k, int[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Float2IntOpenHashMap(float[] local_k, int[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public int put(float local_k, int local_v)
  {
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        int oldValue = this.value[pos];
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
  
  public Integer put(Float local_ok, Integer local_ov)
  {
    int local_v = local_ov.intValue();
    float local_k = local_ok.floatValue();
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        Integer oldValue = Integer.valueOf(this.value[pos]);
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
  
  public int add(float local_k, int incr)
  {
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        int oldValue = this.value[pos];
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
        int slot = HashCommon.murmurHash3(HashCommon.float2int(this.key[pos])) & this.mask;
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
  
  public int remove(float local_k)
  {
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        int local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Integer remove(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        int local_v = this.value[pos];
        shiftKeys(pos);
        return Integer.valueOf(local_v);
      }
    }
    return null;
  }
  
  public Integer get(Float local_ok)
  {
    float local_k = local_ok.floatValue();
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return Integer.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public int get(float local_k)
  {
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(float local_k)
  {
    for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(int local_v)
  {
    int[] value = this.value;
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
  
  public Float2IntMap.FastEntrySet float2IntEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public FloatSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public IntCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractIntCollection()
      {
        public IntIterator iterator()
        {
          return new Float2IntOpenHashMap.ValueIterator(Float2IntOpenHashMap.this);
        }
        
        public int size()
        {
          return Float2IntOpenHashMap.this.size;
        }
        
        public boolean contains(int local_v)
        {
          return Float2IntOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Float2IntOpenHashMap.this.clear();
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
    float[] key = this.key;
    int[] value = this.value;
    int newMask = newN - 1;
    float[] newKey = new float[newN];
    int[] newValue = new int[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      float local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
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
  
  public Float2IntOpenHashMap clone()
  {
    Float2IntOpenHashMap local_c;
    try
    {
      local_c = (Float2IntOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((float[])this.key.clone());
    local_c.value = ((int[])this.value.clone());
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
      local_t = HashCommon.float2int(this.key[local_i]);
      local_t ^= this.value[local_i];
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    float[] key = this.key;
    int[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeFloat(key[local_e]);
      local_s.writeInt(value[local_e]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_49 = HashCommon.arraySize(this.size, this.field_48);
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.mask = (this.field_49 - 1);
    float[] key = this.key = new float[this.field_49];
    int[] value = this.value = new int[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      float local_k = local_s.readFloat();
      int local_v = local_s.readInt();
      for (pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Float2IntOpenHashMap.MapIterator
    implements IntIterator
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public int nextInt()
    {
      return Float2IntOpenHashMap.this.value[nextEntry()];
    }
    
    public Integer next()
    {
      return Integer.valueOf(Float2IntOpenHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractFloatSet
  {
    private KeySet() {}
    
    public FloatIterator iterator()
    {
      return new Float2IntOpenHashMap.KeyIterator(Float2IntOpenHashMap.this);
    }
    
    public int size()
    {
      return Float2IntOpenHashMap.this.size;
    }
    
    public boolean contains(float local_k)
    {
      return Float2IntOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(float local_k)
    {
      int oldSize = Float2IntOpenHashMap.this.size;
      Float2IntOpenHashMap.this.remove(local_k);
      return Float2IntOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Float2IntOpenHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Float2IntOpenHashMap.MapIterator
    implements FloatIterator
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public float nextFloat()
    {
      return Float2IntOpenHashMap.this.key[nextEntry()];
    }
    
    public Float next()
    {
      return Float.valueOf(Float2IntOpenHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Float2IntMap.Entry>
    implements Float2IntMap.FastEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Float2IntMap.Entry> iterator()
    {
      return new Float2IntOpenHashMap.EntryIterator(Float2IntOpenHashMap.this, null);
    }
    
    public ObjectIterator<Float2IntMap.Entry> fastIterator()
    {
      return new Float2IntOpenHashMap.FastEntryIterator(Float2IntOpenHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Float, Integer> local_e = (Map.Entry)local_o;
      float local_k = ((Float)local_e.getKey()).floatValue();
      for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & Float2IntOpenHashMap.this.mask; Float2IntOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Float2IntOpenHashMap.this.mask) {
        if (Float2IntOpenHashMap.this.key[pos] == local_k) {
          return Float2IntOpenHashMap.this.value[pos] == ((Integer)local_e.getValue()).intValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Float, Integer> local_e = (Map.Entry)local_o;
      float local_k = ((Float)local_e.getKey()).floatValue();
      for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & Float2IntOpenHashMap.this.mask; Float2IntOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Float2IntOpenHashMap.this.mask) {
        if (Float2IntOpenHashMap.this.key[pos] == local_k)
        {
          Float2IntOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Float2IntOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Float2IntOpenHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Float2IntOpenHashMap.MapIterator
    implements ObjectIterator<Float2IntMap.Entry>
  {
    final AbstractFloat2IntMap.BasicEntry entry = new AbstractFloat2IntMap.BasicEntry(0.0F, 0);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractFloat2IntMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Float2IntOpenHashMap.this.key[local_e];
      this.entry.value = Float2IntOpenHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Float2IntOpenHashMap.MapIterator
    implements ObjectIterator<Float2IntMap.Entry>
  {
    private Float2IntOpenHashMap.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Float2IntMap.Entry next()
    {
      return this.entry = new Float2IntOpenHashMap.MapEntry(Float2IntOpenHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Float2IntOpenHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Float2IntOpenHashMap.this.field_49;
    int last = -1;
    int field_1652 = Float2IntOpenHashMap.this.size;
    FloatArrayList wrapped;
    
    private MapIterator()
    {
      boolean[] used = Float2IntOpenHashMap.this.used;
      while ((this.field_1652 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_1652 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_1652 -= 1;
      if (this.pos < 0)
      {
        float local_k = this.wrapped.getFloat(-(this.last = --this.pos) - 2);
        for (int pos = HashCommon.murmurHash3(HashCommon.float2int(local_k)) & Float2IntOpenHashMap.this.mask; Float2IntOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Float2IntOpenHashMap.this.mask) {
          if (Float2IntOpenHashMap.this.key[pos] == local_k) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_1652 != 0)
      {
        boolean[] local_k = Float2IntOpenHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Float2IntOpenHashMap.this.mask; Float2IntOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Float2IntOpenHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(HashCommon.float2int(Float2IntOpenHashMap.this.key[pos])) & Float2IntOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Float2IntOpenHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new FloatArrayList();
          }
          this.wrapped.add(Float2IntOpenHashMap.this.key[pos]);
        }
        Float2IntOpenHashMap.this.key[last] = Float2IntOpenHashMap.this.key[pos];
        Float2IntOpenHashMap.this.value[last] = Float2IntOpenHashMap.this.value[pos];
      }
      Float2IntOpenHashMap.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Float2IntOpenHashMap.this.remove(this.wrapped.getFloat(-this.pos - 2));
        this.last = -1;
        return;
      }
      Float2IntOpenHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_1652 > 0))
      {
        this.field_1652 += 1;
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
    implements Float2IntMap.Entry, Map.Entry<Float, Integer>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Float getKey()
    {
      return Float.valueOf(Float2IntOpenHashMap.this.key[this.index]);
    }
    
    public float getFloatKey()
    {
      return Float2IntOpenHashMap.this.key[this.index];
    }
    
    public Integer getValue()
    {
      return Integer.valueOf(Float2IntOpenHashMap.this.value[this.index]);
    }
    
    public int getIntValue()
    {
      return Float2IntOpenHashMap.this.value[this.index];
    }
    
    public int setValue(int local_v)
    {
      int oldValue = Float2IntOpenHashMap.this.value[this.index];
      Float2IntOpenHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Integer setValue(Integer local_v)
    {
      return Integer.valueOf(setValue(local_v.intValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Float, Integer> local_e = (Map.Entry)local_o;
      return (Float2IntOpenHashMap.this.key[this.index] == ((Float)local_e.getKey()).floatValue()) && (Float2IntOpenHashMap.this.value[this.index] == ((Integer)local_e.getValue()).intValue());
    }
    
    public int hashCode()
    {
      return HashCommon.float2int(Float2IntOpenHashMap.this.key[this.index]) ^ Float2IntOpenHashMap.this.value[this.index];
    }
    
    public String toString()
    {
      return Float2IntOpenHashMap.this.key[this.index] + "=>" + Float2IntOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2IntOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */