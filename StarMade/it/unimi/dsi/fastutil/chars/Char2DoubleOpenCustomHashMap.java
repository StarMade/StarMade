package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Char2DoubleOpenCustomHashMap
  extends AbstractChar2DoubleMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient char[] key;
  protected transient double[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Char2DoubleMap.FastEntrySet entries;
  protected volatile transient CharSet keys;
  protected volatile transient DoubleCollection values;
  protected CharHash.Strategy strategy;
  
  public Char2DoubleOpenCustomHashMap(int expected, float local_f, CharHash.Strategy strategy)
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
    this.key = new char[this.field_49];
    this.value = new double[this.field_49];
    this.used = new boolean[this.field_49];
  }
  
  public Char2DoubleOpenCustomHashMap(int expected, CharHash.Strategy strategy)
  {
    this(expected, 0.75F, strategy);
  }
  
  public Char2DoubleOpenCustomHashMap(CharHash.Strategy strategy)
  {
    this(16, 0.75F, strategy);
  }
  
  public Char2DoubleOpenCustomHashMap(Map<? extends Character, ? extends Double> local_m, float local_f, CharHash.Strategy strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Char2DoubleOpenCustomHashMap(Map<? extends Character, ? extends Double> local_m, CharHash.Strategy strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Char2DoubleOpenCustomHashMap(Char2DoubleMap local_m, float local_f, CharHash.Strategy strategy)
  {
    this(local_m.size(), local_f, strategy);
    putAll(local_m);
  }
  
  public Char2DoubleOpenCustomHashMap(Char2DoubleMap local_m, CharHash.Strategy strategy)
  {
    this(local_m, 0.75F, strategy);
  }
  
  public Char2DoubleOpenCustomHashMap(char[] local_k, double[] local_v, float local_f, CharHash.Strategy strategy)
  {
    this(local_k.length, local_f, strategy);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Char2DoubleOpenCustomHashMap(char[] local_k, double[] local_v, CharHash.Strategy strategy)
  {
    this(local_k, local_v, 0.75F, strategy);
  }
  
  public CharHash.Strategy strategy()
  {
    return this.strategy;
  }
  
  public double put(char local_k, double local_v)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        double oldValue = this.value[pos];
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
  
  public Double put(Character local_ok, Double local_ov)
  {
    double local_v = local_ov.doubleValue();
    char local_k = local_ok.charValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        Double oldValue = Double.valueOf(this.value[pos]);
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
  
  public double add(char local_k, double incr)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        double oldValue = this.value[pos];
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
  
  public double remove(char local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        double local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Double remove(Object local_ok)
  {
    char local_k = ((Character)local_ok).charValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k))
      {
        this.size -= 1;
        double local_v = this.value[pos];
        shiftKeys(pos);
        return Double.valueOf(local_v);
      }
    }
    return null;
  }
  
  public Double get(Character local_ok)
  {
    char local_k = local_ok.charValue();
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return Double.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public double get(char local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(char local_k)
  {
    for (int pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.strategy.equals(this.key[pos], local_k)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsValue(double local_v)
  {
    double[] value = this.value;
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
  
  public Char2DoubleMap.FastEntrySet char2DoubleEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public CharSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public DoubleCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractDoubleCollection()
      {
        public DoubleIterator iterator()
        {
          return new Char2DoubleOpenCustomHashMap.ValueIterator(Char2DoubleOpenCustomHashMap.this);
        }
        
        public int size()
        {
          return Char2DoubleOpenCustomHashMap.this.size;
        }
        
        public boolean contains(double local_v)
        {
          return Char2DoubleOpenCustomHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Char2DoubleOpenCustomHashMap.this.clear();
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
    char[] key = this.key;
    double[] value = this.value;
    int newMask = newN - 1;
    char[] newKey = new char[newN];
    double[] newValue = new double[newN];
    boolean[] newUsed = new boolean[newN];
    int local_j = this.size;
    while (local_j-- != 0)
    {
      while (used[local_i] == 0) {
        local_i++;
      }
      char local_k = key[local_i];
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
  
  public Char2DoubleOpenCustomHashMap clone()
  {
    Char2DoubleOpenCustomHashMap local_c;
    try
    {
      local_c = (Char2DoubleOpenCustomHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((char[])this.key.clone());
    local_c.value = ((double[])this.value.clone());
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
      local_t ^= HashCommon.double2int(this.value[local_i]);
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    char[] key = this.key;
    double[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeChar(key[local_e]);
      local_s.writeDouble(value[local_e]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_49 = HashCommon.arraySize(this.size, this.field_48);
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.mask = (this.field_49 - 1);
    char[] key = this.key = new char[this.field_49];
    double[] value = this.value = new double[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      char local_k = local_s.readChar();
      double local_v = local_s.readDouble();
      for (pos = HashCommon.murmurHash3(this.strategy.hashCode(local_k)) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Char2DoubleOpenCustomHashMap.MapIterator
    implements DoubleIterator
  {
    public ValueIterator()
    {
      super(null);
    }
    
    public double nextDouble()
    {
      return Char2DoubleOpenCustomHashMap.this.value[nextEntry()];
    }
    
    public Double next()
    {
      return Double.valueOf(Char2DoubleOpenCustomHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractCharSet
  {
    private KeySet() {}
    
    public CharIterator iterator()
    {
      return new Char2DoubleOpenCustomHashMap.KeyIterator(Char2DoubleOpenCustomHashMap.this);
    }
    
    public int size()
    {
      return Char2DoubleOpenCustomHashMap.this.size;
    }
    
    public boolean contains(char local_k)
    {
      return Char2DoubleOpenCustomHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(char local_k)
    {
      int oldSize = Char2DoubleOpenCustomHashMap.this.size;
      Char2DoubleOpenCustomHashMap.this.remove(local_k);
      return Char2DoubleOpenCustomHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Char2DoubleOpenCustomHashMap.this.clear();
    }
  }
  
  private final class KeyIterator
    extends Char2DoubleOpenCustomHashMap.MapIterator
    implements CharIterator
  {
    public KeyIterator()
    {
      super(null);
    }
    
    public char nextChar()
    {
      return Char2DoubleOpenCustomHashMap.this.key[nextEntry()];
    }
    
    public Character next()
    {
      return Character.valueOf(Char2DoubleOpenCustomHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSet<Char2DoubleMap.Entry>
    implements Char2DoubleMap.FastEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectIterator<Char2DoubleMap.Entry> iterator()
    {
      return new Char2DoubleOpenCustomHashMap.EntryIterator(Char2DoubleOpenCustomHashMap.this, null);
    }
    
    public ObjectIterator<Char2DoubleMap.Entry> fastIterator()
    {
      return new Char2DoubleOpenCustomHashMap.FastEntryIterator(Char2DoubleOpenCustomHashMap.this, null);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Character, Double> local_e = (Map.Entry)local_o;
      char local_k = ((Character)local_e.getKey()).charValue();
      for (int pos = HashCommon.murmurHash3(Char2DoubleOpenCustomHashMap.this.strategy.hashCode(local_k)) & Char2DoubleOpenCustomHashMap.this.mask; Char2DoubleOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Char2DoubleOpenCustomHashMap.this.mask) {
        if (Char2DoubleOpenCustomHashMap.this.strategy.equals(Char2DoubleOpenCustomHashMap.this.key[pos], local_k)) {
          return Char2DoubleOpenCustomHashMap.this.value[pos] == ((Double)local_e.getValue()).doubleValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Character, Double> local_e = (Map.Entry)local_o;
      char local_k = ((Character)local_e.getKey()).charValue();
      for (int pos = HashCommon.murmurHash3(Char2DoubleOpenCustomHashMap.this.strategy.hashCode(local_k)) & Char2DoubleOpenCustomHashMap.this.mask; Char2DoubleOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Char2DoubleOpenCustomHashMap.this.mask) {
        if (Char2DoubleOpenCustomHashMap.this.strategy.equals(Char2DoubleOpenCustomHashMap.this.key[pos], local_k))
        {
          Char2DoubleOpenCustomHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Char2DoubleOpenCustomHashMap.this.size;
    }
    
    public void clear()
    {
      Char2DoubleOpenCustomHashMap.this.clear();
    }
  }
  
  private class FastEntryIterator
    extends Char2DoubleOpenCustomHashMap.MapIterator
    implements ObjectIterator<Char2DoubleMap.Entry>
  {
    final AbstractChar2DoubleMap.BasicEntry entry = new AbstractChar2DoubleMap.BasicEntry('\000', 0.0D);
    
    private FastEntryIterator()
    {
      super(null);
    }
    
    public AbstractChar2DoubleMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Char2DoubleOpenCustomHashMap.this.key[local_e];
      this.entry.value = Char2DoubleOpenCustomHashMap.this.value[local_e];
      return this.entry;
    }
  }
  
  private class EntryIterator
    extends Char2DoubleOpenCustomHashMap.MapIterator
    implements ObjectIterator<Char2DoubleMap.Entry>
  {
    private Char2DoubleOpenCustomHashMap.MapEntry entry;
    
    private EntryIterator()
    {
      super(null);
    }
    
    public Char2DoubleMap.Entry next()
    {
      return this.entry = new Char2DoubleOpenCustomHashMap.MapEntry(Char2DoubleOpenCustomHashMap.this, nextEntry());
    }
    
    public void remove()
    {
      super.remove();
      Char2DoubleOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
    }
  }
  
  private class MapIterator
  {
    int pos = Char2DoubleOpenCustomHashMap.this.field_49;
    int last = -1;
    int field_1671 = Char2DoubleOpenCustomHashMap.this.size;
    CharArrayList wrapped;
    
    private MapIterator()
    {
      boolean[] used = Char2DoubleOpenCustomHashMap.this.used;
      while ((this.field_1671 != 0) && (used[(--this.pos)] == 0)) {}
    }
    
    public boolean hasNext()
    {
      return this.field_1671 != 0;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.field_1671 -= 1;
      if (this.pos < 0)
      {
        char local_k = this.wrapped.getChar(-(this.last = --this.pos) - 2);
        for (int pos = HashCommon.murmurHash3(Char2DoubleOpenCustomHashMap.this.strategy.hashCode(local_k)) & Char2DoubleOpenCustomHashMap.this.mask; Char2DoubleOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Char2DoubleOpenCustomHashMap.this.mask) {
          if (Char2DoubleOpenCustomHashMap.this.strategy.equals(Char2DoubleOpenCustomHashMap.this.key[pos], local_k)) {
            return pos;
          }
        }
      }
      this.last = this.pos;
      if (this.field_1671 != 0)
      {
        boolean[] local_k = Char2DoubleOpenCustomHashMap.this.used;
        while ((this.pos-- != 0) && (local_k[this.pos] == 0)) {}
      }
      return this.last;
    }
    
    protected final int shiftKeys(int pos)
    {
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Char2DoubleOpenCustomHashMap.this.mask; Char2DoubleOpenCustomHashMap.this.used[pos] != 0; pos = pos + 1 & Char2DoubleOpenCustomHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Char2DoubleOpenCustomHashMap.this.strategy.hashCode(Char2DoubleOpenCustomHashMap.this.key[pos])) & Char2DoubleOpenCustomHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Char2DoubleOpenCustomHashMap.this.used[pos] == 0) {
          break;
        }
        if (pos < last)
        {
          if (this.wrapped == null) {
            this.wrapped = new CharArrayList();
          }
          this.wrapped.add(Char2DoubleOpenCustomHashMap.this.key[pos]);
        }
        Char2DoubleOpenCustomHashMap.this.key[last] = Char2DoubleOpenCustomHashMap.this.key[pos];
        Char2DoubleOpenCustomHashMap.this.value[last] = Char2DoubleOpenCustomHashMap.this.value[pos];
      }
      Char2DoubleOpenCustomHashMap.this.used[last] = false;
      return last;
    }
    
    public void remove()
    {
      if (this.last == -1) {
        throw new IllegalStateException();
      }
      if (this.pos < -1)
      {
        Char2DoubleOpenCustomHashMap.this.remove(this.wrapped.getChar(-this.pos - 2));
        this.last = -1;
        return;
      }
      Char2DoubleOpenCustomHashMap.this.size -= 1;
      if ((shiftKeys(this.last) == this.pos) && (this.field_1671 > 0))
      {
        this.field_1671 += 1;
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
    implements Char2DoubleMap.Entry, Map.Entry<Character, Double>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Character getKey()
    {
      return Character.valueOf(Char2DoubleOpenCustomHashMap.this.key[this.index]);
    }
    
    public char getCharKey()
    {
      return Char2DoubleOpenCustomHashMap.this.key[this.index];
    }
    
    public Double getValue()
    {
      return Double.valueOf(Char2DoubleOpenCustomHashMap.this.value[this.index]);
    }
    
    public double getDoubleValue()
    {
      return Char2DoubleOpenCustomHashMap.this.value[this.index];
    }
    
    public double setValue(double local_v)
    {
      double oldValue = Char2DoubleOpenCustomHashMap.this.value[this.index];
      Char2DoubleOpenCustomHashMap.this.value[this.index] = local_v;
      return oldValue;
    }
    
    public Double setValue(Double local_v)
    {
      return Double.valueOf(setValue(local_v.doubleValue()));
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Character, Double> local_e = (Map.Entry)local_o;
      return (Char2DoubleOpenCustomHashMap.this.strategy.equals(Char2DoubleOpenCustomHashMap.this.key[this.index], ((Character)local_e.getKey()).charValue())) && (Char2DoubleOpenCustomHashMap.this.value[this.index] == ((Double)local_e.getValue()).doubleValue());
    }
    
    public int hashCode()
    {
      return Char2DoubleOpenCustomHashMap.this.strategy.hashCode(Char2DoubleOpenCustomHashMap.this.key[this.index]) ^ HashCommon.double2int(Char2DoubleOpenCustomHashMap.this.value[this.index]);
    }
    
    public String toString()
    {
      return Char2DoubleOpenCustomHashMap.this.key[this.index] + "=>" + Char2DoubleOpenCustomHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2DoubleOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */