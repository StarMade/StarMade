package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Double2ReferenceOpenHashMap<V>
  extends AbstractDouble2ReferenceMap<V>
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient double[] key;
  protected transient V[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Double2ReferenceMap.FastEntrySet<V> entries;
  protected volatile transient DoubleSet keys;
  protected volatile transient ReferenceCollection<V> values;
  
  public Double2ReferenceOpenHashMap(int expected, float local_f)
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
    this.value = ((Object[])new Object[this.field_49]);
    this.used = new boolean[this.field_49];
  }
  
  public Double2ReferenceOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Double2ReferenceOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Double2ReferenceOpenHashMap(Map<? extends Double, ? extends V> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Double2ReferenceOpenHashMap(Map<? extends Double, ? extends V> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Double2ReferenceOpenHashMap(Double2ReferenceMap<V> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Double2ReferenceOpenHashMap(Double2ReferenceMap<V> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Double2ReferenceOpenHashMap(double[] local_k, V[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Double2ReferenceOpenHashMap(double[] local_k, V[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public V put(double local_k, V local_v)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
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
  
  public V put(Double local_ok, V local_ov)
  {
    V local_v = local_ov;
    double local_k = local_ok.doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
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
    this.value[last] = null;
    return last;
  }
  
  public V remove(double local_k)
  {
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
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
    double local_k = ((Double)local_ok).doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        V local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public V get(Double local_ok)
  {
    double local_k = local_ok.doubleValue();
    for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public V get(double local_k)
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
  
  public boolean containsValue(Object local_v)
  {
    V[] value = this.value;
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
  
  public Double2ReferenceMap.FastEntrySet<V> double2ReferenceEntrySet()
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
  
  public ReferenceCollection<V> values()
  {
    if (this.values == null) {
      this.values = new AbstractReferenceCollection()
      {
        public ObjectIterator<V> iterator()
        {
          return new Double2ReferenceOpenHashMap.ValueIterator(Double2ReferenceOpenHashMap.this);
        }
        
        public int size()
        {
          return Double2ReferenceOpenHashMap.this.size;
        }
        
        public boolean contains(Object local_v)
        {
          return Double2ReferenceOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Double2ReferenceOpenHashMap.this.clear();
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
    V[] value = this.value;
    int newMask = newN - 1;
    double[] newKey = new double[newN];
    V[] newValue = (Object[])new Object[newN];
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
  
  public Double2ReferenceOpenHashMap<V> clone()
  {
    Double2ReferenceOpenHashMap<V> local_c;
    try
    {
      local_c = (Double2ReferenceOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((double[])this.key.clone());
    local_c.value = ((Object[])this.value.clone());
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
      if (this != this.value[local_i]) {
        local_t ^= (this.value[local_i] == null ? 0 : System.identityHashCode(this.value[local_i]));
      }
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    double[] key = this.key;
    V[] value = this.value;
    Double2ReferenceOpenHashMap<V>.MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeDouble(key[local_e]);
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
    double[] key = this.key = new double[this.field_49];
    V[] value = this.value = (Object[])new Object[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      double local_k = local_s.readDouble();
      V local_v = local_s.readObject();
      for (pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Double2ReferenceOpenHashMap<V>.MapIterator
    implements ObjectIterator<V>
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public V next()
    {
      return Double2ReferenceOpenHashMap.this.value[nextEntry()];
    }
  }
  
  private final class KeySet
    extends AbstractDoubleSet
  {
    private KeySet() {}
    
    public DoubleIterator iterator()
    {
      return new Double2ReferenceOpenHashMap.KeyIterator(Double2ReferenceOpenHashMap.this);
    }
    
    public int size()
    {
      return Double2ReferenceOpenHashMap.this.size;
    }
    
    public boolean contains(double local_k)
    {
      return Double2ReferenceOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(double local_k)
    {
      int oldSize = Double2ReferenceOpenHashMap.this.size;
      Double2ReferenceOpenHashMap.this.remove(local_k);
      return Double2ReferenceOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Double2ReferenceOpenHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Double2ReferenceOpenHashMap.MapIterator
    implements DoubleIterator
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public double nextDouble()
    {
      return Double2ReferenceOpenHashMap.this.key[nextEntry()];
    }
    
    public Double next()
    {
      return Double.valueOf(Double2ReferenceOpenHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Double2ReferenceMap.Entry<V>>
    implements Double2ReferenceMap.FastEntrySet<V>
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Double2ReferenceMap.Entry<V>> iterator()
    {
      return new Double2ReferenceOpenHashMap.EntryIterator(Double2ReferenceOpenHashMap.this, null);
    }
    
    public ObjectIterator<Double2ReferenceMap.Entry<V>> fastIterator()
    {
      return new Double2ReferenceOpenHashMap.FastEntryIterator(Double2ReferenceOpenHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, V> local_e = (Map.Entry)local_o;
      double local_k = ((Double)local_e.getKey()).doubleValue();
      for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & Double2ReferenceOpenHashMap.this.mask; Double2ReferenceOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2ReferenceOpenHashMap.this.mask) {
        if (Double2ReferenceOpenHashMap.this.key[pos] == local_k) {
          return Double2ReferenceOpenHashMap.this.value[pos] == local_e.getValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, V> local_e = (Map.Entry)local_o;
      double local_k = ((Double)local_e.getKey()).doubleValue();
      for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & Double2ReferenceOpenHashMap.this.mask; Double2ReferenceOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2ReferenceOpenHashMap.this.mask) {
        if (Double2ReferenceOpenHashMap.this.key[pos] == local_k)
        {
          Double2ReferenceOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Double2ReferenceOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Double2ReferenceOpenHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Double2ReferenceOpenHashMap<V>.MapIterator
    implements ObjectIterator<Double2ReferenceMap.Entry<V>>
  {
    final AbstractDouble2ReferenceMap.BasicEntry<V> entry = new AbstractDouble2ReferenceMap.BasicEntry(0.0D, null);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractDouble2ReferenceMap.BasicEntry<V> next()
    {
      int local_e = nextEntry();
      this.entry.key = Double2ReferenceOpenHashMap.this.key[local_e];
      this.entry.value = Double2ReferenceOpenHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Double2ReferenceOpenHashMap<V>.MapIterator
    implements ObjectIterator<Double2ReferenceMap.Entry<V>>
  {
    private Double2ReferenceOpenHashMap<V>.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Double2ReferenceMap.Entry<V> next()
    {
      return this.entry = new Double2ReferenceOpenHashMap.MapEntry(Double2ReferenceOpenHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Double2ReferenceOpenHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Double2ReferenceOpenHashMap.this.field_49;
    int last = -1;
    int field_1754 = Double2ReferenceOpenHashMap.this.size;
    DoubleArrayList wrapped;
    
    private MapIterator()
    {
      boolean[] used = Double2ReferenceOpenHashMap.this.used;
      while ((this.field_1754 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_1754 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_1754 -= 1;
      if (this.pos < 0)
      {
        double local_k = this.wrapped.getDouble(-(this.last = --this.pos) - 2);
        for (int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(local_k)) & Double2ReferenceOpenHashMap.this.mask; Double2ReferenceOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2ReferenceOpenHashMap.this.mask) {
          if (Double2ReferenceOpenHashMap.this.key[pos] == local_k) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_1754 != 0)
      {
        boolean[] local_k = Double2ReferenceOpenHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Double2ReferenceOpenHashMap.this.mask; Double2ReferenceOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Double2ReferenceOpenHashMap.this.mask)
        {
          int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(Double2ReferenceOpenHashMap.this.key[pos])) & Double2ReferenceOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Double2ReferenceOpenHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new DoubleArrayList();
          }
          this.wrapped.add(Double2ReferenceOpenHashMap.this.key[pos]);
        }
        Double2ReferenceOpenHashMap.this.key[last] = Double2ReferenceOpenHashMap.this.key[pos];
        Double2ReferenceOpenHashMap.this.value[last] = Double2ReferenceOpenHashMap.this.value[pos];
      }
      Double2ReferenceOpenHashMap.this.used[last] = false;
      Double2ReferenceOpenHashMap.this.value[last] = null;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Double2ReferenceOpenHashMap.this.remove(this.wrapped.getDouble(-this.pos - 2));
        this.last = -1;
        return;
      }
      Double2ReferenceOpenHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_1754 > 0))
      {
        this.field_1754 += 1;
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
    implements Double2ReferenceMap.Entry<V>, Map.Entry<Double, V>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Double getKey()
    {
      return Double.valueOf(Double2ReferenceOpenHashMap.this.key[this.index]);
    }
    
    public double getDoubleKey()
    {
      return Double2ReferenceOpenHashMap.this.key[this.index];
    }
    
    public V getValue()
    {
      return Double2ReferenceOpenHashMap.this.value[this.index];
    }
    
    public V setValue(V local_v)
    {
      V oldValue = Double2ReferenceOpenHashMap.this.value[this.index];
      Double2ReferenceOpenHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Double, V> local_e = (Map.Entry)local_o;
      return (Double2ReferenceOpenHashMap.this.key[this.index] == ((Double)local_e.getKey()).doubleValue()) && (Double2ReferenceOpenHashMap.this.value[this.index] == local_e.getValue());
    }
    
    public int hashCode()
    {
      return HashCommon.double2int(Double2ReferenceOpenHashMap.this.key[this.index]) ^ (Double2ReferenceOpenHashMap.this.value[this.index] == null ? 0 : System.identityHashCode(Double2ReferenceOpenHashMap.this.value[this.index]));
    }
    
    public String toString()
    {
      return Double2ReferenceOpenHashMap.this.key[this.index] + "=>" + Double2ReferenceOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ReferenceOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */