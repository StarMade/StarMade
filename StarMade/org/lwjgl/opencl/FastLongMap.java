/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */
/*  21:    */final class FastLongMap<V>
/*  22:    */  implements Iterable<Entry<V>>
/*  23:    */{
/*  24:    */  private Entry[] table;
/*  25:    */  private int size;
/*  26:    */  private int mask;
/*  27:    */  private int capacity;
/*  28:    */  private int threshold;
/*  29:    */  
/*  30:    */  FastLongMap()
/*  31:    */  {
/*  32: 32 */    this(16, 0.75F);
/*  33:    */  }
/*  34:    */  
/*  35:    */  FastLongMap(int initialCapacity)
/*  36:    */  {
/*  37: 37 */    this(initialCapacity, 0.75F);
/*  38:    */  }
/*  39:    */  
/*  40:    */  FastLongMap(int initialCapacity, float loadFactor) {
/*  41: 41 */    if (initialCapacity > 1073741824) throw new IllegalArgumentException("initialCapacity is too large.");
/*  42: 42 */    if (initialCapacity < 0) throw new IllegalArgumentException("initialCapacity must be greater than zero.");
/*  43: 43 */    if (loadFactor <= 0.0F) throw new IllegalArgumentException("initialCapacity must be greater than zero.");
/*  44: 44 */    this.capacity = 1;
/*  45: 45 */    while (this.capacity < initialCapacity)
/*  46: 46 */      this.capacity <<= 1;
/*  47: 47 */    this.threshold = ((int)(this.capacity * loadFactor));
/*  48: 48 */    this.table = new Entry[this.capacity];
/*  49: 49 */    this.mask = (this.capacity - 1);
/*  50:    */  }
/*  51:    */  
/*  52:    */  private int index(long key) {
/*  53: 53 */    return index(key, this.mask);
/*  54:    */  }
/*  55:    */  
/*  56:    */  private static int index(long key, int mask) {
/*  57: 57 */    int hash = (int)(key ^ key >>> 32);
/*  58: 58 */    return hash & mask;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public V put(long key, V value) {
/*  62: 62 */    Entry<V>[] table = this.table;
/*  63: 63 */    int index = index(key);
/*  64:    */    
/*  66: 66 */    for (Entry<V> e = table[index]; e != null; e = e.next) {
/*  67: 67 */      if (e.key == key) {
/*  68: 68 */        V oldValue = e.value;
/*  69: 69 */        e.value = value;
/*  70: 70 */        return oldValue;
/*  71:    */      }
/*  72:    */    }
/*  73: 73 */    table[index] = new Entry(key, value, table[index]);
/*  74:    */    
/*  75: 75 */    if (this.size++ >= this.threshold) {
/*  76: 76 */      rehash(table);
/*  77:    */    }
/*  78: 78 */    return null;
/*  79:    */  }
/*  80:    */  
/*  81:    */  private void rehash(Entry<V>[] table) {
/*  82: 82 */    int newCapacity = 2 * this.capacity;
/*  83: 83 */    int newMask = newCapacity - 1;
/*  84:    */    
/*  85: 85 */    Entry<V>[] newTable = new Entry[newCapacity];
/*  86:    */    
/*  87: 87 */    for (int i = 0; i < table.length; i++) {
/*  88: 88 */      Entry<V> e = table[i];
/*  89: 89 */      if (e != null) {
/*  90:    */        do {
/*  91: 91 */          Entry<V> next = e.next;
/*  92: 92 */          int index = index(e.key, newMask);
/*  93: 93 */          e.next = newTable[index];
/*  94: 94 */          newTable[index] = e;
/*  95: 95 */          e = next;
/*  96: 96 */        } while (e != null);
/*  97:    */      }
/*  98:    */    }
/*  99: 99 */    this.table = newTable;
/* 100:100 */    this.capacity = newCapacity;
/* 101:101 */    this.mask = newMask;
/* 102:102 */    this.threshold *= 2;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public V get(long key) {
/* 106:106 */    int index = index(key);
/* 107:107 */    for (Entry<V> e = this.table[index]; e != null; e = e.next)
/* 108:108 */      if (e.key == key) return e.value;
/* 109:109 */    return null;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public boolean containsValue(Object value) {
/* 113:113 */    Entry<V>[] table = this.table;
/* 114:114 */    for (int i = table.length - 1; i >= 0; i--)
/* 115:115 */      for (Entry<V> e = table[i]; e != null; e = e.next)
/* 116:116 */        if (e.value.equals(value)) return true;
/* 117:117 */    return false;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public boolean containsKey(long key) {
/* 121:121 */    int index = index(key);
/* 122:122 */    for (Entry<V> e = this.table[index]; e != null; e = e.next)
/* 123:123 */      if (e.key == key) return true;
/* 124:124 */    return false;
/* 125:    */  }
/* 126:    */  
/* 127:    */  public V remove(long key) {
/* 128:128 */    int index = index(key);
/* 129:    */    
/* 130:130 */    Entry<V> prev = this.table[index];
/* 131:131 */    Entry<V> e = prev;
/* 132:132 */    while (e != null) {
/* 133:133 */      Entry<V> next = e.next;
/* 134:134 */      if (e.key == key) {
/* 135:135 */        this.size -= 1;
/* 136:136 */        if (prev == e) {
/* 137:137 */          this.table[index] = next;
/* 138:    */        } else
/* 139:139 */          prev.next = next;
/* 140:140 */        return e.value;
/* 141:    */      }
/* 142:142 */      prev = e;
/* 143:143 */      e = next;
/* 144:    */    }
/* 145:145 */    return null;
/* 146:    */  }
/* 147:    */  
/* 148:    */  public int size() {
/* 149:149 */    return this.size;
/* 150:    */  }
/* 151:    */  
/* 152:    */  public boolean isEmpty() {
/* 153:153 */    return this.size == 0;
/* 154:    */  }
/* 155:    */  
/* 156:    */  public void clear() {
/* 157:157 */    Entry<V>[] table = this.table;
/* 158:158 */    for (int index = table.length - 1; index >= 0; index--)
/* 159:159 */      table[index] = null;
/* 160:160 */    this.size = 0;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public FastLongMap<V>.EntryIterator iterator() {
/* 164:164 */    return new EntryIterator();
/* 165:    */  }
/* 166:    */  
/* 167:    */  public class EntryIterator implements Iterator<FastLongMap.Entry<V>>
/* 168:    */  {
/* 169:    */    private int nextIndex;
/* 170:    */    private FastLongMap.Entry<V> current;
/* 171:    */    
/* 172:    */    EntryIterator() {
/* 173:173 */      reset();
/* 174:    */    }
/* 175:    */    
/* 176:    */    public void reset() {
/* 177:177 */      this.current = null;
/* 178:    */      
/* 179:179 */      FastLongMap.Entry<V>[] table = FastLongMap.this.table;
/* 180:    */      
/* 181:181 */      for (int i = table.length - 1; i >= 0; i--)
/* 182:182 */        if (table[i] != null) break;
/* 183:183 */      this.nextIndex = i;
/* 184:    */    }
/* 185:    */    
/* 186:    */    public boolean hasNext() {
/* 187:187 */      if (this.nextIndex >= 0) return true;
/* 188:188 */      FastLongMap.Entry e = this.current;
/* 189:189 */      return (e != null) && (e.next != null);
/* 190:    */    }
/* 191:    */    
/* 192:    */    public FastLongMap.Entry<V> next()
/* 193:    */    {
/* 194:194 */      FastLongMap.Entry<V> e = this.current;
/* 195:195 */      if (e != null) {
/* 196:196 */        e = e.next;
/* 197:197 */        if (e != null) {
/* 198:198 */          this.current = e;
/* 199:199 */          return e;
/* 200:    */        }
/* 201:    */      }
/* 202:    */      
/* 203:203 */      FastLongMap.Entry<V>[] table = FastLongMap.this.table;
/* 204:204 */      int i = this.nextIndex;
/* 205:205 */      e = this.current = table[i];
/* 206:206 */      for (;;) { i--; if (i >= 0)
/* 207:207 */          if (table[i] != null) break; }
/* 208:208 */      this.nextIndex = i;
/* 209:209 */      return e;
/* 210:    */    }
/* 211:    */    
/* 212:    */    public void remove() {
/* 213:213 */      FastLongMap.this.remove(this.current.key);
/* 214:    */    }
/* 215:    */  }
/* 216:    */  
/* 217:    */  static final class Entry<T>
/* 218:    */  {
/* 219:    */    final long key;
/* 220:    */    T value;
/* 221:    */    Entry<T> next;
/* 222:    */    
/* 223:    */    Entry(long key, T value, Entry<T> next) {
/* 224:224 */      this.key = key;
/* 225:225 */      this.value = value;
/* 226:226 */      this.next = next;
/* 227:    */    }
/* 228:    */    
/* 229:    */    public long getKey() {
/* 230:230 */      return this.key;
/* 231:    */    }
/* 232:    */    
/* 233:    */    public T getValue() {
/* 234:234 */      return this.value;
/* 235:    */    }
/* 236:    */  }
/* 237:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.FastLongMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */