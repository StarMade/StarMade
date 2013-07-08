package it.unimi.dsi.fastutil.doubles;

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

public class Double2IntOpenHashMap
  extends AbstractDouble2IntMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient double[] key;
  protected transient int[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Double2IntMap.FastEntrySet entries;
  protected volatile transient DoubleSet keys;
  protected volatile transient IntCollection values;
  
  public Double2IntOpenHashMap(int expected, float local_f)
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
    this.key = new double[this.field_49];
    this.value = new int[this.field_49];
    this.used = new boolean[this.field_49];
  }
  
  public Double2IntOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Double2IntOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Double2IntOpenHashMap(Map<? extends Double, ? extends Integer> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Double2IntOpenHashMap(Map<? extends Double, ? extends Integer> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Double2IntOpenHashMap(Double2IntMap local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Double2IntOpenHashMap(Double2IntMap local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Double2IntOpenHashMap(double[] local_k, int[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Double2IntOpenHashMap(double[] local_k, int[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public int put(double local_k, int local_v)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
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
  
  public Integer put(Double local_ok, Integer local_ov)
  {
    int local_v = local_ov.intValue();
    double local_k = local_ok.doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
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
  
  public int add(double local_k, int incr)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
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
        int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(this.key[pos])) & this.mask;
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
  
  public int remove(double local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
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
    double local_k = ((Double)local_ok).doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
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
  
  public Integer get(Double local_ok)
  {
    double local_k = local_ok.doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return Integer.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public int get(double local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(double local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
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
  
  public Double2IntMap.FastEntrySet double2IntEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public DoubleSet keySet()
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
          return new Double2IntOpenHashMap.ValueIterator(Double2IntOpenHashMap.this);
        }
        
        public int size()
        {
          return Double2IntOpenHashMap.this.size;
        }
        
        public boolean contains(int local_v)
        {
          return Double2IntOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Double2IntOpenHashMap.this.clear();
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
    double[] key = this.key;
    int[] value = this.value;
    int newMask = newN - 1;
    double[] newKey = new double[newN];
    int[] newValue = new int[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      double local_k = key[local_i];
      for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
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
  
  public Double2IntOpenHashMap clone()
  {
    Double2IntOpenHashMap local_c;
    try
    {
      local_c = (Double2IntOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((double[])this.key.clone());
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
      local_t = HashCommon.double2int(this.key[local_i]);
      local_t ^= this.value[local_i];
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    double[] key = this.key;
    int[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeDouble(key[local_e]);
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
    double[] key = this.key = new double[this.field_49];
    int[] value = this.value = new int[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      double local_k = local_s.readDouble();
      int local_v = local_s.readInt();
      for (pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Double2IntOpenHashMap.MapIterator
    implements IntIterator
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public int nextInt()
    {
      return Double2IntOpenHashMap.this.value[nextEntry()];
    }
    
    public Integer next()
    {
      return Integer.valueOf(Double2IntOpenHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractDoubleSet
  {
    private KeySet() {}
    
    public DoubleIterator iterator()
    {
      return new Double2IntOpenHashMap.KeyIterator(Double2IntOpenHashMap.this);
    }
    
    public int size()
    {
      return Double2IntOpenHashMap.this.size;
    }
    
    public boolean contains(double local_k)
    {
      return Double2IntOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(double local_k)
    {
      int oldSize = Double2IntOpenHashMap.this.size;
      Double2IntOpenHashMap.this.remove(local_k);
      return Double2IntOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Double2IntOpenHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Double2IntOpenHashMap.MapIterator
    implements DoubleIterator
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public double nextDouble()
    {
      return Double2IntOpenHashMap.this.key[nextEntry()];
    }
    
    public Double next()
    {
      return Double.valueOf(Double2IntOpenHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Double2IntMap.Entry>
    implements Double2IntMap.FastEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Double2IntMap.Entry> iterator()
    {
      return new Double2IntOpenHashMap.EntryIterator(Double2IntOpenHashMap.this, null);
    }
    
    public ObjectIterator<Double2IntMap.Entry> fastIterator()
    {
      return new Double2IntOpenHashMap.FastEntryIterator(Double2IntOpenHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, Integer> local_e = (Map.Entry)local_o;
      double local_k = ((Double)local_e.getKey()).doubleValue();
      for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & Double2IntOpenHashMap.this.mask; Double2IntOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2IntOpenHashMap.this.mask) {
        if (Double2IntOpenHashMap.this.key[pos] == local_k) {
          return Double2IntOpenHashMap.this.value[pos] == ((Integer)local_e.getValue()).intValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, Integer> local_e = (Map.Entry)local_o;
      double local_k = ((Double)local_e.getKey()).doubleValue();
      for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & Double2IntOpenHashMap.this.mask; Double2IntOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2IntOpenHashMap.this.mask) {
        if (Double2IntOpenHashMap.this.key[pos] == local_k)
        {
          Double2IntOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Double2IntOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Double2IntOpenHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Double2IntOpenHashMap.MapIterator
    implements ObjectIterator<Double2IntMap.Entry>
  {
    final AbstractDouble2IntMap.BasicEntry entry = new AbstractDouble2IntMap.BasicEntry(0.0D, 0);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractDouble2IntMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Double2IntOpenHashMap.this.key[local_e];
      this.entry.value = Double2IntOpenHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Double2IntOpenHashMap.MapIterator
    implements ObjectIterator<Double2IntMap.Entry>
  {
    private Double2IntOpenHashMap.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Double2IntMap.Entry next()
    {
      return this.entry = new Double2IntOpenHashMap.MapEntry(Double2IntOpenHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Double2IntOpenHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Double2IntOpenHashMap.this.field_49;
    int last = -1;
    int field_2230 = Double2IntOpenHashMap.this.size;
    DoubleArrayList wrapped;
    
    private MapIterator()
    {
      boolean[] used = Double2IntOpenHashMap.this.used;
      while ((this.field_2230 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_2230 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_2230 -= 1;
      if (this.pos < 0)
      {
        double local_k = this.wrapped.getDouble(-(this.last = --this.pos) - 2);
        for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & Double2IntOpenHashMap.this.mask; Double2IntOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2IntOpenHashMap.this.mask) {
          if (Double2IntOpenHashMap.this.key[pos] == local_k) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_2230 != 0)
      {
        boolean[] local_k = Double2IntOpenHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Double2IntOpenHashMap.this.mask; Double2IntOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2IntOpenHashMap.this.mask)
        {
          int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(Double2IntOpenHashMap.this.key[pos])) & Double2IntOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Double2IntOpenHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new DoubleArrayList();
          }
          this.wrapped.add(Double2IntOpenHashMap.this.key[pos]);
        }
        Double2IntOpenHashMap.this.key[last] = Double2IntOpenHashMap.this.key[pos];
        Double2IntOpenHashMap.this.value[last] = Double2IntOpenHashMap.this.value[pos];
      }
      Double2IntOpenHashMap.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Double2IntOpenHashMap.this.remove(this.wrapped.getDouble(-this.pos - 2));
        this.last = -1;
        return;
      }
      Double2IntOpenHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_2230 > 0))
      {
        this.field_2230 += 1;
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
    implements Double2IntMap.Entry, Map.Entry<Double, Integer>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Double getKey()
    {
      return Double.valueOf(Double2IntOpenHashMap.this.key[this.index]);
    }
    
    public double getDoubleKey()
    {
      return Double2IntOpenHashMap.this.key[this.index];
    }
    
    public Integer getValue()
    {
      return Integer.valueOf(Double2IntOpenHashMap.this.value[this.index]);
    }
    
    public int getIntValue()
    {
      return Double2IntOpenHashMap.this.value[this.index];
    }
    
    public int setValue(int local_v)
    {
      int oldValue = Double2IntOpenHashMap.this.value[this.index];
      Double2IntOpenHashMap.this.value[this.index] = local_v;
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
      Map.Entry<Double, Integer> local_e = (Map.Entry)local_o;
      return (Double2IntOpenHashMap.this.key[this.index] == ((Double)local_e.getKey()).doubleValue()) && (Double2IntOpenHashMap.this.value[this.index] == ((Integer)local_e.getValue()).intValue());
    }
    
    public int hashCode()
    {
      return HashCommon.double2int(Double2IntOpenHashMap.this.key[this.index]) ^ Double2IntOpenHashMap.this.value[this.index];
    }
    
    public String toString()
    {
      return Double2IntOpenHashMap.this.key[this.index] + "=>" + Double2IntOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2IntOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */