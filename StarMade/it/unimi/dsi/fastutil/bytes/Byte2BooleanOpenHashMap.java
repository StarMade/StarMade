package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Byte2BooleanOpenHashMap
  extends AbstractByte2BooleanMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient byte[] key;
  protected transient boolean[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Byte2BooleanMap.FastEntrySet entries;
  protected volatile transient ByteSet keys;
  protected volatile transient BooleanCollection values;
  
  public Byte2BooleanOpenHashMap(int expected, float local_f)
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
    this.key = new byte[this.field_49];
    this.value = new boolean[this.field_49];
    this.used = new boolean[this.field_49];
  }
  
  public Byte2BooleanOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Byte2BooleanOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Byte2BooleanOpenHashMap(Map<? extends Byte, ? extends Boolean> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Byte2BooleanOpenHashMap(Map<? extends Byte, ? extends Boolean> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Byte2BooleanOpenHashMap(Byte2BooleanMap local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Byte2BooleanOpenHashMap(Byte2BooleanMap local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Byte2BooleanOpenHashMap(byte[] local_k, boolean[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Byte2BooleanOpenHashMap(byte[] local_k, boolean[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public boolean put(byte local_k, boolean local_v)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        boolean oldValue = this.value[pos];
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
  
  public Boolean put(Byte local_ok, Boolean local_ov)
  {
    boolean local_v = local_ov.booleanValue();
    byte local_k = local_ok.byteValue();
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        Boolean oldValue = Boolean.valueOf(this.value[pos]);
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
        int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
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
  
  public boolean remove(byte local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        boolean local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Boolean remove(Object local_ok)
  {
    byte local_k = ((Byte)local_ok).byteValue();
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        boolean local_v = this.value[pos];
        shiftKeys(pos);
        return Boolean.valueOf(local_v);
      }
    }
    return null;
  }
  
  public Boolean get(Byte local_ok)
  {
    byte local_k = local_ok.byteValue();
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return Boolean.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public boolean get(byte local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(byte local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(boolean local_v)
  {
    boolean[] value = this.value;
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
  
  public Byte2BooleanMap.FastEntrySet byte2BooleanEntrySet()
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
  
  public BooleanCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractBooleanCollection()
      {
        public BooleanIterator iterator()
        {
          return new Byte2BooleanOpenHashMap.ValueIterator(Byte2BooleanOpenHashMap.this);
        }
        
        public int size()
        {
          return Byte2BooleanOpenHashMap.this.size;
        }
        
        public boolean contains(boolean local_v)
        {
          return Byte2BooleanOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Byte2BooleanOpenHashMap.this.clear();
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
    boolean[] value = this.value;
    int newMask = newN - 1;
    byte[] newKey = new byte[newN];
    boolean[] newValue = new boolean[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      byte local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(local_k) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
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
  
  public Byte2BooleanOpenHashMap clone()
  {
    Byte2BooleanOpenHashMap local_c;
    try
    {
      local_c = (Byte2BooleanOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((byte[])this.key.clone());
    local_c.value = ((boolean[])this.value.clone());
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
      local_t = this.key[local_i];
      local_t ^= (this.value[local_i] != 0 ? 1231 : 1237);
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    byte[] key = this.key;
    boolean[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeByte(key[local_e]);
      local_s.writeBoolean(value[local_e]);
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
    boolean[] value = this.value = new boolean[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      byte local_k = local_s.readByte();
      boolean local_v = local_s.readBoolean();
      for (pos = HashCommon.murmurHash3(local_k) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Byte2BooleanOpenHashMap.MapIterator
    implements BooleanIterator
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public boolean nextBoolean()
    {
      return Byte2BooleanOpenHashMap.this.value[nextEntry()];
    }
    
    public Boolean next()
    {
      return Boolean.valueOf(Byte2BooleanOpenHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractByteSet
  {
    private KeySet() {}
    
    public ByteIterator iterator()
    {
      return new Byte2BooleanOpenHashMap.KeyIterator(Byte2BooleanOpenHashMap.this);
    }
    
    public int size()
    {
      return Byte2BooleanOpenHashMap.this.size;
    }
    
    public boolean contains(byte local_k)
    {
      return Byte2BooleanOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(byte local_k)
    {
      int oldSize = Byte2BooleanOpenHashMap.this.size;
      Byte2BooleanOpenHashMap.this.remove(local_k);
      return Byte2BooleanOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Byte2BooleanOpenHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Byte2BooleanOpenHashMap.MapIterator
    implements ByteIterator
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public byte nextByte()
    {
      return Byte2BooleanOpenHashMap.this.key[nextEntry()];
    }
    
    public Byte next()
    {
      return Byte.valueOf(Byte2BooleanOpenHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Byte2BooleanMap.Entry>
    implements Byte2BooleanMap.FastEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Byte2BooleanMap.Entry> iterator()
    {
      return new Byte2BooleanOpenHashMap.EntryIterator(Byte2BooleanOpenHashMap.this, null);
    }
    
    public ObjectIterator<Byte2BooleanMap.Entry> fastIterator()
    {
      return new Byte2BooleanOpenHashMap.FastEntryIterator(Byte2BooleanOpenHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Byte, Boolean> local_e = (Map.Entry)local_o;
      byte local_k = ((Byte)local_e.getKey()).byteValue();
      for (int pos = HashCommon.murmurHash3(local_k) & Byte2BooleanOpenHashMap.this.mask; Byte2BooleanOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Byte2BooleanOpenHashMap.this.mask) {
        if (Byte2BooleanOpenHashMap.this.key[pos] == local_k) {
          return Byte2BooleanOpenHashMap.this.value[pos] == ((Boolean)local_e.getValue()).booleanValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Byte, Boolean> local_e = (Map.Entry)local_o;
      byte local_k = ((Byte)local_e.getKey()).byteValue();
      for (int pos = HashCommon.murmurHash3(local_k) & Byte2BooleanOpenHashMap.this.mask; Byte2BooleanOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Byte2BooleanOpenHashMap.this.mask) {
        if (Byte2BooleanOpenHashMap.this.key[pos] == local_k)
        {
          Byte2BooleanOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Byte2BooleanOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Byte2BooleanOpenHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Byte2BooleanOpenHashMap.MapIterator
    implements ObjectIterator<Byte2BooleanMap.Entry>
  {
    final AbstractByte2BooleanMap.BasicEntry entry = new AbstractByte2BooleanMap.BasicEntry((byte)0, false);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractByte2BooleanMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Byte2BooleanOpenHashMap.this.key[local_e];
      this.entry.value = Byte2BooleanOpenHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Byte2BooleanOpenHashMap.MapIterator
    implements ObjectIterator<Byte2BooleanMap.Entry>
  {
    private Byte2BooleanOpenHashMap.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Byte2BooleanMap.Entry next()
    {
      return this.entry = new Byte2BooleanOpenHashMap.MapEntry(Byte2BooleanOpenHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Byte2BooleanOpenHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Byte2BooleanOpenHashMap.this.field_49;
    int last = -1;
    int field_1826 = Byte2BooleanOpenHashMap.this.size;
    ByteArrayList wrapped;
    
    private MapIterator()
    {
      boolean[] used = Byte2BooleanOpenHashMap.this.used;
      while ((this.field_1826 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_1826 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_1826 -= 1;
      if (this.pos < 0)
      {
        byte local_k = this.wrapped.getByte(-(this.last = --this.pos) - 2);
        for (int pos = HashCommon.murmurHash3(local_k) & Byte2BooleanOpenHashMap.this.mask; Byte2BooleanOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Byte2BooleanOpenHashMap.this.mask) {
          if (Byte2BooleanOpenHashMap.this.key[pos] == local_k) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_1826 != 0)
      {
        boolean[] local_k = Byte2BooleanOpenHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Byte2BooleanOpenHashMap.this.mask; Byte2BooleanOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Byte2BooleanOpenHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Byte2BooleanOpenHashMap.this.key[pos]) & Byte2BooleanOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Byte2BooleanOpenHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new ByteArrayList();
          }
          this.wrapped.add(Byte2BooleanOpenHashMap.this.key[pos]);
        }
        Byte2BooleanOpenHashMap.this.key[last] = Byte2BooleanOpenHashMap.this.key[pos];
        Byte2BooleanOpenHashMap.this.value[last] = Byte2BooleanOpenHashMap.this.value[pos];
      }
      Byte2BooleanOpenHashMap.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Byte2BooleanOpenHashMap.this.remove(this.wrapped.getByte(-this.pos - 2));
        this.last = -1;
        return;
      }
      Byte2BooleanOpenHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_1826 > 0))
      {
        this.field_1826 += 1;
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
    implements Byte2BooleanMap.Entry, Map.Entry<Byte, Boolean>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Byte getKey()
    {
      return Byte.valueOf(Byte2BooleanOpenHashMap.this.key[this.index]);
    }
    
    public byte getByteKey()
    {
      return Byte2BooleanOpenHashMap.this.key[this.index];
    }
    
    public Boolean getValue()
    {
      return Boolean.valueOf(Byte2BooleanOpenHashMap.this.value[this.index]);
    }
    
    public boolean getBooleanValue()
    {
      return Byte2BooleanOpenHashMap.this.value[this.index];
    }
    
    public boolean setValue(boolean local_v)
    {
      boolean oldValue = Byte2BooleanOpenHashMap.this.value[this.index];
      Byte2BooleanOpenHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Boolean setValue(Boolean local_v)
    {
      return Boolean.valueOf(setValue(local_v.booleanValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Byte, Boolean> local_e = (Map.Entry)local_o;
      return (Byte2BooleanOpenHashMap.this.key[this.index] == ((Byte)local_e.getKey()).byteValue()) && (Byte2BooleanOpenHashMap.this.value[this.index] == ((Boolean)local_e.getValue()).booleanValue());
    }
    
    public int hashCode()
    {
      return Byte2BooleanOpenHashMap.this.key[this.index] ^ (Byte2BooleanOpenHashMap.this.value[this.index] != 0 ? 1231 : 1237);
    }
    
    public String toString()
    {
      return Byte2BooleanOpenHashMap.this.key[this.index] + "=>" + Byte2BooleanOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2BooleanOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */