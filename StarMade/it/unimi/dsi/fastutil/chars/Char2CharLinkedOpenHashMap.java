package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Char2CharLinkedOpenHashMap
  extends AbstractChar2CharSortedMap
  implements Serializable, Cloneable, Hash
{
  public static final long serialVersionUID = 0L;
  private static final boolean ASSERTS = false;
  protected transient char[] key;
  protected transient char[] value;
  protected transient boolean[] used;
  protected final float field_48;
  protected transient int field_49;
  protected transient int maxFill;
  protected transient int mask;
  protected int size;
  protected volatile transient Char2CharSortedMap.FastSortedEntrySet entries;
  protected volatile transient CharSortedSet keys;
  protected volatile transient CharCollection values;
  protected transient int first = -1;
  protected transient int last = -1;
  protected transient long[] link;
  
  public Char2CharLinkedOpenHashMap(int expected, float local_f)
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
    this.key = new char[this.field_49];
    this.value = new char[this.field_49];
    this.used = new boolean[this.field_49];
    this.link = new long[this.field_49];
  }
  
  public Char2CharLinkedOpenHashMap(int expected)
  {
    this(expected, 0.75F);
  }
  
  public Char2CharLinkedOpenHashMap()
  {
    this(16, 0.75F);
  }
  
  public Char2CharLinkedOpenHashMap(Map<? extends Character, ? extends Character> local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Char2CharLinkedOpenHashMap(Map<? extends Character, ? extends Character> local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Char2CharLinkedOpenHashMap(Char2CharMap local_m, float local_f)
  {
    this(local_m.size(), local_f);
    putAll(local_m);
  }
  
  public Char2CharLinkedOpenHashMap(Char2CharMap local_m)
  {
    this(local_m, 0.75F);
  }
  
  public Char2CharLinkedOpenHashMap(char[] local_k, char[] local_v, float local_f)
  {
    this(local_k.length, local_f);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Char2CharLinkedOpenHashMap(char[] local_k, char[] local_v)
  {
    this(local_k, local_v, 0.75F);
  }
  
  public char put(char local_k, char local_v)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        char oldValue = this.value[pos];
        this.value[pos] = local_v;
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = local_v;
    if (this.size == 0)
    {
      this.first = (this.last = pos);
      this.link[pos] = -1L;
    }
    else
    {
      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
      this.last = pos;
    }
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size + 1, this.field_48));
    }
    return this.defRetValue;
  }
  
  public Character put(Character local_ok, Character local_ov)
  {
    char local_v = local_ov.charValue();
    char local_k = local_ok.charValue();
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        Character oldValue = Character.valueOf(this.value[pos]);
        this.value[pos] = local_v;
        return oldValue;
      }
    }
    this.used[pos] = true;
    this.key[pos] = local_k;
    this.value[pos] = local_v;
    if (this.size == 0)
    {
      this.first = (this.last = pos);
      this.link[pos] = -1L;
    }
    else
    {
      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
      this.last = pos;
    }
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
      fixPointers(pos, last);
    }
    this.used[last] = false;
    return last;
  }
  
  public char remove(char local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        char local_v = this.value[pos];
        shiftKeys(pos);
        return local_v;
      }
    }
    return this.defRetValue;
  }
  
  public Character remove(Object local_ok)
  {
    char local_k = ((Character)local_ok).charValue();
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k)
      {
        this.size -= 1;
        fixPointers(pos);
        char local_v = this.value[pos];
        shiftKeys(pos);
        return Character.valueOf(local_v);
      }
    }
    return null;
  }
  
  public char removeFirstChar()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    this.size -= 1;
    int pos = this.first;
    this.first = ((int)this.link[pos]);
    if (0 <= this.first) {
      this.link[this.first] |= -4294967296L;
    }
    char local_v = this.value[pos];
    shiftKeys(pos);
    return local_v;
  }
  
  public char removeLastChar()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    this.size -= 1;
    int pos = this.last;
    this.last = ((int)(this.link[pos] >>> 32));
    if (0 <= this.last) {
      this.link[this.last] |= 4294967295L;
    }
    char local_v = this.value[pos];
    shiftKeys(pos);
    return local_v;
  }
  
  private void moveIndexToFirst(int local_i)
  {
    if ((this.size == 1) || (this.first == local_i)) {
      return;
    }
    if (this.last == local_i)
    {
      this.last = ((int)(this.link[local_i] >>> 32));
      this.link[this.last] |= 4294967295L;
    }
    else
    {
      long linki = this.link[local_i];
      int prev = (int)(linki >>> 32);
      int next = (int)linki;
      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
    }
    this.link[this.first] ^= (this.link[this.first] ^ (local_i & 0xFFFFFFFF) << 32) & 0x0;
    this.link[local_i] = (0x0 | this.first & 0xFFFFFFFF);
    this.first = local_i;
  }
  
  private void moveIndexToLast(int local_i)
  {
    if ((this.size == 1) || (this.last == local_i)) {
      return;
    }
    if (this.first == local_i)
    {
      this.first = ((int)this.link[local_i]);
      this.link[this.first] |= -4294967296L;
    }
    else
    {
      long linki = this.link[local_i];
      int prev = (int)(linki >>> 32);
      int next = (int)linki;
      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
    }
    this.link[this.last] ^= (this.link[this.last] ^ local_i & 0xFFFFFFFF) & 0xFFFFFFFF;
    this.link[local_i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
    this.last = local_i;
  }
  
  public char getAndMoveToFirst(char local_k)
  {
    char[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        moveIndexToFirst(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public char getAndMoveToLast(char local_k)
  {
    char[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        moveIndexToLast(pos);
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public char putAndMoveToFirst(char local_k, char local_v)
  {
    char[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        char oldValue = this.value[pos];
        this.value[pos] = local_v;
        moveIndexToFirst(pos);
        return oldValue;
      }
    }
    used[pos] = true;
    key[pos] = local_k;
    this.value[pos] = local_v;
    if (this.size == 0)
    {
      this.first = (this.last = pos);
      this.link[pos] = -1L;
    }
    else
    {
      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
      this.first = pos;
    }
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size, this.field_48));
    }
    return this.defRetValue;
  }
  
  public char putAndMoveToLast(char local_k, char local_v)
  {
    char[] key = this.key;
    boolean[] used = this.used;
    int mask = this.mask;
    for (int pos = HashCommon.murmurHash3(local_k) & mask; used[pos] != 0; pos = pos + 1 & mask) {
      if (local_k == key[pos])
      {
        char oldValue = this.value[pos];
        this.value[pos] = local_v;
        moveIndexToLast(pos);
        return oldValue;
      }
    }
    used[pos] = true;
    key[pos] = local_k;
    this.value[pos] = local_v;
    if (this.size == 0)
    {
      this.first = (this.last = pos);
      this.link[pos] = -1L;
    }
    else
    {
      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
      this.last = pos;
    }
    if (++this.size >= this.maxFill) {
      rehash(HashCommon.arraySize(this.size, this.field_48));
    }
    return this.defRetValue;
  }
  
  public Character get(Character local_ok)
  {
    char local_k = local_ok.charValue();
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return Character.valueOf(this.value[pos]);
      }
    }
    return null;
  }
  
  public char get(char local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
        return this.value[pos];
      }
    }
    return this.defRetValue;
  }
  
  public boolean containsKey(char local_k)
  {
    for (int pos = HashCommon.murmurHash3(local_k) & this.mask; this.used[pos] != 0; pos = pos + 1 & this.mask) {
      if (this.key[pos] == local_k) {
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
    this.first = (this.last = -1);
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
  
  protected void fixPointers(int local_i)
  {
    if (this.size == 0)
    {
      this.first = (this.last = -1);
      return;
    }
    if (this.first == local_i)
    {
      this.first = ((int)this.link[local_i]);
      if (0 <= this.first) {
        this.link[this.first] |= -4294967296L;
      }
      return;
    }
    if (this.last == local_i)
    {
      this.last = ((int)(this.link[local_i] >>> 32));
      if (0 <= this.last) {
        this.link[this.last] |= 4294967295L;
      }
      return;
    }
    long linki = this.link[local_i];
    int prev = (int)(linki >>> 32);
    int next = (int)linki;
    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
  }
  
  protected void fixPointers(int local_s, int local_d)
  {
    if (this.size == 1)
    {
      this.first = (this.last = local_d);
      this.link[local_d] = -1L;
      return;
    }
    if (this.first == local_s)
    {
      this.first = local_d;
      this.link[((int)this.link[local_s])] ^= (this.link[((int)this.link[local_s])] ^ (local_d & 0xFFFFFFFF) << 32) & 0x0;
      this.link[local_d] = this.link[local_s];
      return;
    }
    if (this.last == local_s)
    {
      this.last = local_d;
      this.link[((int)(this.link[local_s] >>> 32))] ^= (this.link[((int)(this.link[local_s] >>> 32))] ^ local_d & 0xFFFFFFFF) & 0xFFFFFFFF;
      this.link[local_d] = this.link[local_s];
      return;
    }
    long links = this.link[local_s];
    int prev = (int)(links >>> 32);
    int next = (int)links;
    this.link[prev] ^= (this.link[prev] ^ local_d & 0xFFFFFFFF) & 0xFFFFFFFF;
    this.link[next] ^= (this.link[next] ^ (local_d & 0xFFFFFFFF) << 32) & 0x0;
    this.link[local_d] = links;
  }
  
  public char firstCharKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.first];
  }
  
  public char lastCharKey()
  {
    if (this.size == 0) {
      throw new NoSuchElementException();
    }
    return this.key[this.last];
  }
  
  public CharComparator comparator()
  {
    return null;
  }
  
  public Char2CharSortedMap tailMap(char from)
  {
    throw new UnsupportedOperationException();
  }
  
  public Char2CharSortedMap headMap(char local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Char2CharSortedMap subMap(char from, char local_to)
  {
    throw new UnsupportedOperationException();
  }
  
  public Char2CharSortedMap.FastSortedEntrySet char2CharEntrySet()
  {
    if (this.entries == null) {
      this.entries = new MapEntrySet(null);
    }
    return this.entries;
  }
  
  public CharSortedSet keySet()
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
          return new Char2CharLinkedOpenHashMap.ValueIterator(Char2CharLinkedOpenHashMap.this);
        }
        
        public int size()
        {
          return Char2CharLinkedOpenHashMap.this.size;
        }
        
        public boolean contains(char local_v)
        {
          return Char2CharLinkedOpenHashMap.this.containsValue(local_v);
        }
        
        public void clear()
        {
          Char2CharLinkedOpenHashMap.this.clear();
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
    int local_i = this.first;
    int prev = -1;
    int newPrev = -1;
    char[] key = this.key;
    char[] value = this.value;
    int newMask = newN - 1;
    char[] newKey = new char[newN];
    char[] newValue = new char[newN];
    boolean[] newUsed = new boolean[newN];
    long[] link = this.link;
    long[] newLink = new long[newN];
    this.first = -1;
    int local_j = this.size;
    while (local_j-- != 0)
    {
      char local_k = key[local_i];
      for (int pos = HashCommon.murmurHash3(local_k) & newMask; newUsed[pos] != 0; pos = pos + 1 & newMask) {}
      newUsed[pos] = true;
      newKey[pos] = local_k;
      newValue[pos] = value[local_i];
      if (prev != -1)
      {
        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
        newPrev = pos;
      }
      else
      {
        newPrev = this.first = pos;
        newLink[pos] = -1L;
      }
      int local_t = local_i;
      local_i = (int)link[local_i];
      prev = local_t;
    }
    this.field_49 = newN;
    this.mask = newMask;
    this.maxFill = HashCommon.maxFill(this.field_49, this.field_48);
    this.key = newKey;
    this.value = newValue;
    this.used = newUsed;
    this.link = newLink;
    this.last = newPrev;
    if (newPrev != -1) {
      newLink[newPrev] |= 4294967295L;
    }
  }
  
  public Char2CharLinkedOpenHashMap clone()
  {
    Char2CharLinkedOpenHashMap local_c;
    try
    {
      local_c = (Char2CharLinkedOpenHashMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.key = ((char[])this.key.clone());
    local_c.value = ((char[])this.value.clone());
    local_c.used = ((boolean[])this.used.clone());
    local_c.link = ((long[])this.link.clone());
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
      local_t ^= this.value[local_i];
      local_h += local_t;
      local_i++;
    }
    return local_h;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    char[] key = this.key;
    char[] value = this.value;
    MapIterator local_i = new MapIterator(null);
    local_s.defaultWriteObject();
    int local_j = this.size;
    while (local_j-- != 0)
    {
      int local_e = local_i.nextEntry();
      local_s.writeChar(key[local_e]);
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
    char[] key = this.key = new char[this.field_49];
    char[] value = this.value = new char[this.field_49];
    boolean[] used = this.used = new boolean[this.field_49];
    long[] link = this.link = new long[this.field_49];
    int prev = -1;
    this.first = (this.last = -1);
    int local_i = this.size;
    int pos = 0;
    while (local_i-- != 0)
    {
      char local_k = local_s.readChar();
      char local_v = local_s.readChar();
      for (pos = HashCommon.murmurHash3(local_k) & this.mask; used[pos] != 0; pos = pos + 1 & this.mask) {}
      used[pos] = true;
      key[pos] = local_k;
      value[pos] = local_v;
      if (this.first != -1)
      {
        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
        prev = pos;
      }
      else
      {
        prev = this.first = pos;
        link[pos] |= -4294967296L;
      }
    }
    this.last = prev;
    if (prev != -1) {
      link[prev] |= 4294967295L;
    }
  }
  
  private void checkTable() {}
  
  private final class ValueIterator
    extends Char2CharLinkedOpenHashMap.MapIterator
    implements CharListIterator
  {
    public char previousChar()
    {
      return Char2CharLinkedOpenHashMap.this.value[previousEntry()];
    }
    
    public Character previous()
    {
      return Character.valueOf(Char2CharLinkedOpenHashMap.this.value[previousEntry()]);
    }
    
    public void set(Character local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Character local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void set(char local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(char local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public ValueIterator()
    {
      super(null);
    }
    
    public char nextChar()
    {
      return Char2CharLinkedOpenHashMap.this.value[nextEntry()];
    }
    
    public Character next()
    {
      return Character.valueOf(Char2CharLinkedOpenHashMap.this.value[nextEntry()]);
    }
  }
  
  private final class KeySet
    extends AbstractCharSortedSet
  {
    private KeySet() {}
    
    public CharListIterator iterator(char from)
    {
      return new Char2CharLinkedOpenHashMap.KeyIterator(Char2CharLinkedOpenHashMap.this, from);
    }
    
    public CharListIterator iterator()
    {
      return new Char2CharLinkedOpenHashMap.KeyIterator(Char2CharLinkedOpenHashMap.this);
    }
    
    public int size()
    {
      return Char2CharLinkedOpenHashMap.this.size;
    }
    
    public boolean contains(char local_k)
    {
      return Char2CharLinkedOpenHashMap.this.containsKey(local_k);
    }
    
    public boolean remove(char local_k)
    {
      int oldSize = Char2CharLinkedOpenHashMap.this.size;
      Char2CharLinkedOpenHashMap.this.remove(local_k);
      return Char2CharLinkedOpenHashMap.this.size != oldSize;
    }
    
    public void clear()
    {
      Char2CharLinkedOpenHashMap.this.clear();
    }
    
    public char firstChar()
    {
      if (Char2CharLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Char2CharLinkedOpenHashMap.this.key[Char2CharLinkedOpenHashMap.this.first];
    }
    
    public char lastChar()
    {
      if (Char2CharLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return Char2CharLinkedOpenHashMap.this.key[Char2CharLinkedOpenHashMap.this.last];
    }
    
    public CharComparator comparator()
    {
      return null;
    }
    
    public final CharSortedSet tailSet(char from)
    {
      throw new UnsupportedOperationException();
    }
    
    public final CharSortedSet headSet(char local_to)
    {
      throw new UnsupportedOperationException();
    }
    
    public final CharSortedSet subSet(char from, char local_to)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private final class KeyIterator
    extends Char2CharLinkedOpenHashMap.MapIterator
    implements CharListIterator
  {
    public KeyIterator(char local_k)
    {
      super(local_k, null);
    }
    
    public char previousChar()
    {
      return Char2CharLinkedOpenHashMap.this.key[previousEntry()];
    }
    
    public void set(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character previous()
    {
      return Character.valueOf(Char2CharLinkedOpenHashMap.this.key[previousEntry()]);
    }
    
    public void set(Character local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Character local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public KeyIterator()
    {
      super(null);
    }
    
    public char nextChar()
    {
      return Char2CharLinkedOpenHashMap.this.key[nextEntry()];
    }
    
    public Character next()
    {
      return Character.valueOf(Char2CharLinkedOpenHashMap.this.key[nextEntry()]);
    }
  }
  
  private final class MapEntrySet
    extends AbstractObjectSortedSet<Char2CharMap.Entry>
    implements Char2CharSortedMap.FastSortedEntrySet
  {
    private MapEntrySet() {}
    
    public ObjectBidirectionalIterator<Char2CharMap.Entry> iterator()
    {
      return new Char2CharLinkedOpenHashMap.EntryIterator(Char2CharLinkedOpenHashMap.this);
    }
    
    public Comparator<? super Char2CharMap.Entry> comparator()
    {
      return null;
    }
    
    public ObjectSortedSet<Char2CharMap.Entry> subSet(Char2CharMap.Entry fromElement, Char2CharMap.Entry toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Char2CharMap.Entry> headSet(Char2CharMap.Entry toElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public ObjectSortedSet<Char2CharMap.Entry> tailSet(Char2CharMap.Entry fromElement)
    {
      throw new UnsupportedOperationException();
    }
    
    public Char2CharMap.Entry first()
    {
      if (Char2CharLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Char2CharLinkedOpenHashMap.MapEntry(Char2CharLinkedOpenHashMap.this, Char2CharLinkedOpenHashMap.this.first);
    }
    
    public Char2CharMap.Entry last()
    {
      if (Char2CharLinkedOpenHashMap.this.size == 0) {
        throw new NoSuchElementException();
      }
      return new Char2CharLinkedOpenHashMap.MapEntry(Char2CharLinkedOpenHashMap.this, Char2CharLinkedOpenHashMap.this.last);
    }
    
    public boolean contains(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Character, Character> local_e = (Map.Entry)local_o;
      char local_k = ((Character)local_e.getKey()).charValue();
      for (int pos = HashCommon.murmurHash3(local_k) & Char2CharLinkedOpenHashMap.this.mask; Char2CharLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Char2CharLinkedOpenHashMap.this.mask) {
        if (Char2CharLinkedOpenHashMap.this.key[pos] == local_k) {
          return Char2CharLinkedOpenHashMap.this.value[pos] == ((Character)local_e.getValue()).charValue();
        }
      }
      return false;
    }
    
    public boolean remove(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<Character, Character> local_e = (Map.Entry)local_o;
      char local_k = ((Character)local_e.getKey()).charValue();
      for (int pos = HashCommon.murmurHash3(local_k) & Char2CharLinkedOpenHashMap.this.mask; Char2CharLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Char2CharLinkedOpenHashMap.this.mask) {
        if (Char2CharLinkedOpenHashMap.this.key[pos] == local_k)
        {
          Char2CharLinkedOpenHashMap.this.remove(local_e.getKey());
          return true;
        }
      }
      return false;
    }
    
    public int size()
    {
      return Char2CharLinkedOpenHashMap.this.size;
    }
    
    public void clear()
    {
      Char2CharLinkedOpenHashMap.this.clear();
    }
    
    public ObjectBidirectionalIterator<Char2CharMap.Entry> iterator(Char2CharMap.Entry from)
    {
      return new Char2CharLinkedOpenHashMap.EntryIterator(Char2CharLinkedOpenHashMap.this, ((Character)from.getKey()).charValue());
    }
    
    public ObjectBidirectionalIterator<Char2CharMap.Entry> fastIterator()
    {
      return new Char2CharLinkedOpenHashMap.FastEntryIterator(Char2CharLinkedOpenHashMap.this);
    }
    
    public ObjectBidirectionalIterator<Char2CharMap.Entry> fastIterator(Char2CharMap.Entry from)
    {
      return new Char2CharLinkedOpenHashMap.FastEntryIterator(Char2CharLinkedOpenHashMap.this, ((Character)from.getKey()).charValue());
    }
  }
  
  private class FastEntryIterator
    extends Char2CharLinkedOpenHashMap.MapIterator
    implements ObjectListIterator<Char2CharMap.Entry>
  {
    final AbstractChar2CharMap.BasicEntry entry = new AbstractChar2CharMap.BasicEntry('\000', '\000');
    
    public FastEntryIterator()
    {
      super(null);
    }
    
    public FastEntryIterator(char from)
    {
      super(from, null);
    }
    
    public AbstractChar2CharMap.BasicEntry next()
    {
      int local_e = nextEntry();
      this.entry.key = Char2CharLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Char2CharLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public AbstractChar2CharMap.BasicEntry previous()
    {
      int local_e = previousEntry();
      this.entry.key = Char2CharLinkedOpenHashMap.this.key[local_e];
      this.entry.value = Char2CharLinkedOpenHashMap.this.value[local_e];
      return this.entry;
    }
    
    public void set(Char2CharMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Char2CharMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Char2CharLinkedOpenHashMap.MapIterator
    implements ObjectListIterator<Char2CharMap.Entry>
  {
    private Char2CharLinkedOpenHashMap.MapEntry entry;
    
    public EntryIterator()
    {
      super(null);
    }
    
    public EntryIterator(char from)
    {
      super(from, null);
    }
    
    public Char2CharLinkedOpenHashMap.MapEntry next()
    {
      return this.entry = new Char2CharLinkedOpenHashMap.MapEntry(Char2CharLinkedOpenHashMap.this, nextEntry());
    }
    
    public Char2CharLinkedOpenHashMap.MapEntry previous()
    {
      return this.entry = new Char2CharLinkedOpenHashMap.MapEntry(Char2CharLinkedOpenHashMap.this, previousEntry());
    }
    
    public void remove()
    {
      super.remove();
      Char2CharLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
    }
    
    public void set(Char2CharMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Char2CharMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class MapIterator
  {
    int prev = -1;
    int next = -1;
    int curr = -1;
    int index = -1;
    
    private MapIterator()
    {
      this.next = Char2CharLinkedOpenHashMap.this.first;
      this.index = 0;
    }
    
    private MapIterator(char from)
    {
      if (Char2CharLinkedOpenHashMap.this.key[Char2CharLinkedOpenHashMap.this.last] == from)
      {
        this.prev = Char2CharLinkedOpenHashMap.this.last;
        this.index = Char2CharLinkedOpenHashMap.this.size;
      }
      else
      {
        for (int pos = HashCommon.murmurHash3(from) & Char2CharLinkedOpenHashMap.this.mask; Char2CharLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Char2CharLinkedOpenHashMap.this.mask) {
          if (Char2CharLinkedOpenHashMap.this.key[pos] == from)
          {
            this.next = ((int)Char2CharLinkedOpenHashMap.this.link[pos]);
            this.prev = pos;
            return;
          }
        }
        throw new NoSuchElementException("The key " + from + " does not belong to this map.");
      }
    }
    
    public boolean hasNext()
    {
      return this.next != -1;
    }
    
    public boolean hasPrevious()
    {
      return this.prev != -1;
    }
    
    private final void ensureIndexKnown()
    {
      if (this.index >= 0) {
        return;
      }
      if (this.prev == -1)
      {
        this.index = 0;
        return;
      }
      if (this.next == -1)
      {
        this.index = Char2CharLinkedOpenHashMap.this.size;
        return;
      }
      int pos = Char2CharLinkedOpenHashMap.this.first;
      for (this.index = 1; pos != this.prev; this.index += 1) {
        pos = (int)Char2CharLinkedOpenHashMap.this.link[pos];
      }
    }
    
    public int nextIndex()
    {
      ensureIndexKnown();
      return this.index;
    }
    
    public int previousIndex()
    {
      ensureIndexKnown();
      return this.index - 1;
    }
    
    public int nextEntry()
    {
      if (!hasNext()) {
        return Char2CharLinkedOpenHashMap.this.size();
      }
      this.curr = this.next;
      this.next = ((int)Char2CharLinkedOpenHashMap.this.link[this.curr]);
      this.prev = this.curr;
      if (this.index >= 0) {
        this.index += 1;
      }
      return this.curr;
    }
    
    public int previousEntry()
    {
      if (!hasPrevious()) {
        return -1;
      }
      this.curr = this.prev;
      this.prev = ((int)(Char2CharLinkedOpenHashMap.this.link[this.curr] >>> 32));
      this.next = this.curr;
      if (this.index >= 0) {
        this.index -= 1;
      }
      return this.curr;
    }
    
    public void remove()
    {
      ensureIndexKnown();
      if (this.curr == -1) {
        throw new IllegalStateException();
      }
      if (this.curr == this.prev)
      {
        this.index -= 1;
        this.prev = ((int)(Char2CharLinkedOpenHashMap.this.link[this.curr] >>> 32));
      }
      else
      {
        this.next = ((int)Char2CharLinkedOpenHashMap.this.link[this.curr]);
      }
      Char2CharLinkedOpenHashMap.this.size -= 1;
      if (this.prev == -1) {
        Char2CharLinkedOpenHashMap.this.first = this.next;
      } else {
        Char2CharLinkedOpenHashMap.this.link[this.prev] ^= (Char2CharLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
      }
      if (this.next == -1) {
        Char2CharLinkedOpenHashMap.this.last = this.prev;
      } else {
        Char2CharLinkedOpenHashMap.this.link[this.next] ^= (Char2CharLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
      }
      int pos = this.curr;
      int last;
      for (;;)
      {
        for (pos = (last = pos) + 1 & Char2CharLinkedOpenHashMap.this.mask; Char2CharLinkedOpenHashMap.this.used[pos] != 0; pos = pos + 1 & Char2CharLinkedOpenHashMap.this.mask)
        {
          int slot = HashCommon.murmurHash3(Char2CharLinkedOpenHashMap.this.key[pos]) & Char2CharLinkedOpenHashMap.this.mask;
          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) {
            break;
          }
        }
        if (Char2CharLinkedOpenHashMap.this.used[pos] == 0) {
          break;
        }
        Char2CharLinkedOpenHashMap.this.key[last] = Char2CharLinkedOpenHashMap.this.key[pos];
        Char2CharLinkedOpenHashMap.this.value[last] = Char2CharLinkedOpenHashMap.this.value[pos];
        if (this.next == pos) {
          this.next = last;
        }
        if (this.prev == pos) {
          this.prev = last;
        }
        Char2CharLinkedOpenHashMap.this.fixPointers(pos, last);
      }
      Char2CharLinkedOpenHashMap.this.used[last] = false;
      this.curr = -1;
    }
    
    public int skip(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasNext())) {
        nextEntry();
      }
      return local_n - local_i - 1;
    }
    
    public int back(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasPrevious())) {
        previousEntry();
      }
      return local_n - local_i - 1;
    }
  }
  
  private final class MapEntry
    implements Char2CharMap.Entry, Map.Entry<Character, Character>
  {
    private int index;
    
    MapEntry(int index)
    {
      this.index = index;
    }
    
    public Character getKey()
    {
      return Character.valueOf(Char2CharLinkedOpenHashMap.this.key[this.index]);
    }
    
    public char getCharKey()
    {
      return Char2CharLinkedOpenHashMap.this.key[this.index];
    }
    
    public Character getValue()
    {
      return Character.valueOf(Char2CharLinkedOpenHashMap.this.value[this.index]);
    }
    
    public char getCharValue()
    {
      return Char2CharLinkedOpenHashMap.this.value[this.index];
    }
    
    public char setValue(char local_v)
    {
      char oldValue = Char2CharLinkedOpenHashMap.this.value[this.index];
      Char2CharLinkedOpenHashMap.this.value[this.index] = local_v;
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
      Map.Entry<Character, Character> local_e = (Map.Entry)local_o;
      return (Char2CharLinkedOpenHashMap.this.key[this.index] == ((Character)local_e.getKey()).charValue()) && (Char2CharLinkedOpenHashMap.this.value[this.index] == ((Character)local_e.getValue()).charValue());
    }
    
    public int hashCode()
    {
      return Char2CharLinkedOpenHashMap.this.key[this.index] ^ Char2CharLinkedOpenHashMap.this.value[this.index];
    }
    
    public String toString()
    {
      return Char2CharLinkedOpenHashMap.this.key[this.index] + "=>" + Char2CharLinkedOpenHashMap.this.value[this.index];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2CharLinkedOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */