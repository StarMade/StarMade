package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteCollection;
import it.unimi.dsi.fastutil.bytes.ByteIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Long2ByteOpenHashMap
  extends AbstractLong2ByteMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient long[] key;
  protected transient byte[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Long2ByteMap.FastEntrySet entries;
  protected volatile transient LongSet keys;
  protected volatile transient ByteCollection values;
  
  public Long2ByteOpenHashMap(int expected, float local_f)
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
    this.key = new long[this.field_49];
    this.value = new byte[this.field_49];
    this.used = new boolean[this.field_49];
  }
  
  public Long2ByteOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Long2ByteOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Long2ByteOpenHashMap(Map<? extends Long, ? extends Byte> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Long2ByteOpenHashMap(Map<? extends Long, ? extends Byte> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Long2ByteOpenHashMap(Long2ByteMap local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Long2ByteOpenHashMap(Long2ByteMap local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Long2ByteOpenHashMap(long[] local_k, byte[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Long2ByteOpenHashMap(long[] local_k, byte[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public byte put(long local_k, byte local_v)
  {
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        byte oldValue = this.value[pos];
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
  
  public Byte put(Long local_ok, Byte local_ov)
  {
    byte local_v = local_ov.byteValue();
    long local_k = local_ok.longValue();
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        Byte oldValue = Byte.valueOf(this.value[pos]);
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
  
  public byte add(long local_k, byte incr)
  {
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        byte oldValue = this.value[pos];
        int tmp49_47 = pos;
        byte[] tmp49_44 = this.value;
        tmp49_44[tmp49_47] = ((byte)(tmp49_44[tmp49_47] + incr));
        return tmp49_47;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = ((byte)(this.defRetValue + incr));
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
        int slot = (int)HashCommon.murmurHash3(this.key[pos]) & this.mask;
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
  
  public byte remove(long local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        byte local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Byte remove(Object local_ok)
  {
    long local_k = ((Long)local_ok).longValue();
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        byte local_v = this.value[pos];
        shiftKeys(pos);
        return Byte.valueOf(local_v);
      }
    }
    return null;
  }
  
  public Byte get(Long local_ok)
  {
    long local_k = local_ok.longValue();
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return Byte.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public byte get(long local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(long local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(byte local_v)
  {
    byte[] value = this.value;
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
  
  public Long2ByteMap.FastEntrySet long2ByteEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public LongSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public ByteCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractByteCollection()
      {
        public ByteIterator iterator()
        {
          return new Long2ByteOpenHashMap.ValueIterator(Long2ByteOpenHashMap.this);
        }
        
        public int size()
        {
          return Long2ByteOpenHashMap.this.size;
        }
        
        public boolean contains(byte local_v)
        {
          return Long2ByteOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Long2ByteOpenHashMap.this.clear();
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
    long[] key = this.key;
    byte[] value = this.value;
    int newMask = newN - 1;
    long[] newKey = new long[newN];
    byte[] newValue = new byte[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      long local_k = key[local_i];
      for (int pos = (int)HashCommon.murmurHash3(local_k) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
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
  
  public Long2ByteOpenHashMap clone()
  {
    Long2ByteOpenHashMap local_c;
    try
    {
      local_c = (Long2ByteOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((long[])this.key.clone());
    local_c.value = ((byte[])this.value.clone());
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
      local_t = HashCommon.long2int(this.key[local_i]);
      local_t ^= this.value[local_i];
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    long[] key = this.key;
    byte[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeLong(key[local_e]);
      local_s.writeByte(value[local_e]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_49 = HashCommon.arraySize(this.size, this.field_48);
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.mask = (this.field_49 - 1);
    long[] key = this.key = new long[this.field_49];
    byte[] value = this.value = new byte[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      long local_k = local_s.readLong();
      byte local_v = local_s.readByte();
      for (pos = (int)HashCommon.murmurHash3(local_k) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Long2ByteOpenHashMap.MapIterator
    implements ByteIterator
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public byte nextByte()
    {
      return Long2ByteOpenHashMap.this.value[nextEntry()];
    }
    
    public Byte next()
    {
      return Byte.valueOf(Long2ByteOpenHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractLongSet
  {
    private KeySet() {}
    
    public LongIterator iterator()
    {
      return new Long2ByteOpenHashMap.KeyIterator(Long2ByteOpenHashMap.this);
    }
    
    public int size()
    {
      return Long2ByteOpenHashMap.this.size;
    }
    
    public boolean contains(long local_k)
    {
      return Long2ByteOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(long local_k)
    {
      int oldSize = Long2ByteOpenHashMap.this.size;
      Long2ByteOpenHashMap.this.remove(local_k);
      return Long2ByteOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Long2ByteOpenHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Long2ByteOpenHashMap.MapIterator
    implements LongIterator
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public long nextLong()
    {
      return Long2ByteOpenHashMap.this.key[nextEntry()];
    }
    
    public Long next()
    {
      return Long.valueOf(Long2ByteOpenHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Long2ByteMap.Entry>
    implements Long2ByteMap.FastEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Long2ByteMap.Entry> iterator()
    {
      return new Long2ByteOpenHashMap.EntryIterator(Long2ByteOpenHashMap.this, null);
    }
    
    public ObjectIterator<Long2ByteMap.Entry> fastIterator()
    {
      return new Long2ByteOpenHashMap.FastEntryIterator(Long2ByteOpenHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Long, Byte> local_e = (Map.Entry)local_o;
      long local_k = ((Long)local_e.getKey()).longValue();
      for (int pos = (int)HashCommon.murmurHash3(local_k) & Long2ByteOpenHashMap.this.mask; Long2ByteOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Long2ByteOpenHashMap.this.mask) {
        if (Long2ByteOpenHashMap.this.key[pos] == local_k) {
          return Long2ByteOpenHashMap.this.value[pos] == ((Byte)local_e.getValue()).byteValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Long, Byte> local_e = (Map.Entry)local_o;
      long local_k = ((Long)local_e.getKey()).longValue();
      for (int pos = (int)HashCommon.murmurHash3(local_k) & Long2ByteOpenHashMap.this.mask; Long2ByteOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Long2ByteOpenHashMap.this.mask) {
        if (Long2ByteOpenHashMap.this.key[pos] == local_k)
        {
          Long2ByteOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Long2ByteOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Long2ByteOpenHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Long2ByteOpenHashMap.MapIterator
    implements ObjectIterator<Long2ByteMap.Entry>
  {
    final AbstractLong2ByteMap.BasicEntry entry = new AbstractLong2ByteMap.BasicEntry(0L, (byte)0);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractLong2ByteMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Long2ByteOpenHashMap.this.key[local_e];
      this.entry.value = Long2ByteOpenHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Long2ByteOpenHashMap.MapIterator
    implements ObjectIterator<Long2ByteMap.Entry>
  {
    private Long2ByteOpenHashMap.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Long2ByteMap.Entry next()
    {
      return this.entry = new Long2ByteOpenHashMap.MapEntry(Long2ByteOpenHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Long2ByteOpenHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Long2ByteOpenHashMap.this.field_49;
    int last = -1;
    int field_1843 = Long2ByteOpenHashMap.this.size;
    LongArrayList wrapped;
    
    private MapIterator()
    {
      boolean[] used = Long2ByteOpenHashMap.this.used;
      while ((this.field_1843 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_1843 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_1843 -= 1;
      if (this.pos < 0)
      {
        long local_k = this.wrapped.getLong(-(this.last = --this.pos) - 2);
        for (int pos = (int)HashCommon.murmurHash3(local_k) & Long2ByteOpenHashMap.this.mask; Long2ByteOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Long2ByteOpenHashMap.this.mask) {
          if (Long2ByteOpenHashMap.this.key[pos] == local_k) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_1843 != 0)
      {
        boolean[] local_k = Long2ByteOpenHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Long2ByteOpenHashMap.this.mask; Long2ByteOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Long2ByteOpenHashMap.this.mask)
        {
          int slot = (int)HashCommon.murmurHash3(Long2ByteOpenHashMap.this.key[pos]) & Long2ByteOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Long2ByteOpenHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new LongArrayList();
          }
          this.wrapped.add(Long2ByteOpenHashMap.this.key[pos]);
        }
        Long2ByteOpenHashMap.this.key[last] = Long2ByteOpenHashMap.this.key[pos];
        Long2ByteOpenHashMap.this.value[last] = Long2ByteOpenHashMap.this.value[pos];
      }
      Long2ByteOpenHashMap.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Long2ByteOpenHashMap.this.remove(this.wrapped.getLong(-this.pos - 2));
        this.last = -1;
        return;
      }
      Long2ByteOpenHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_1843 > 0))
      {
        this.field_1843 += 1;
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
    implements Long2ByteMap.Entry, Map.Entry<Long, Byte>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Long getKey()
    {
      return Long.valueOf(Long2ByteOpenHashMap.this.key[this.index]);
    }
    
    public long getLongKey()
    {
      return Long2ByteOpenHashMap.this.key[this.index];
    }
    
    public Byte getValue()
    {
      return Byte.valueOf(Long2ByteOpenHashMap.this.value[this.index]);
    }
    
    public byte getByteValue()
    {
      return Long2ByteOpenHashMap.this.value[this.index];
    }
    
    public byte setValue(byte local_v)
    {
      byte oldValue = Long2ByteOpenHashMap.this.value[this.index];
      Long2ByteOpenHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Byte setValue(Byte local_v)
    {
      return Byte.valueOf(setValue(local_v.byteValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Long, Byte> local_e = (Map.Entry)local_o;
      return (Long2ByteOpenHashMap.this.key[this.index] == ((Long)local_e.getKey()).longValue()) && (Long2ByteOpenHashMap.this.value[this.index] == ((Byte)local_e.getValue()).byteValue());
    }
    
    public int hashCode()
    {
      return HashCommon.long2int(Long2ByteOpenHashMap.this.key[this.index]) ^ Long2ByteOpenHashMap.this.value[this.index];
    }
    
    public String toString()
    {
      return Long2ByteOpenHashMap.this.key[this.index] + "=>" + Long2ByteOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */