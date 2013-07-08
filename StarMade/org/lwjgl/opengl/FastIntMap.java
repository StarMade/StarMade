/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */
/*  21:    */final class FastIntMap<V>
/*  22:    */  implements Iterable<Entry<V>>
/*  23:    */{
/*  24:    */  private Entry[] table;
/*  25:    */  private int size;
/*  26:    */  private int mask;
/*  27:    */  private int capacity;
/*  28:    */  private int threshold;
/*  29:    */  
/*  30:    */  FastIntMap()
/*  31:    */  {
/*  32: 32 */    this(16, 0.75F);
/*  33:    */  }
/*  34:    */  
/*  35:    */  FastIntMap(int initialCapacity)
/*  36:    */  {
/*  37: 37 */    this(initialCapacity, 0.75F);
/*  38:    */  }
/*  39:    */  
/*  40:    */  FastIntMap(int initialCapacity, float loadFactor) {
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
/*  52:    */  private int index(int key) {
/*  53: 53 */    return index(key, this.mask);
/*  54:    */  }
/*  55:    */  
/*  56:    */  private static int index(int key, int mask) {
/*  57: 57 */    return key & mask;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public V put(int key, V value) {
/*  61: 61 */    Entry<V>[] table = this.table;
/*  62: 62 */    int index = index(key);
/*  63:    */    
/*  65: 65 */    for (Entry<V> e = table[index]; e != null; e = e.next) {
/*  66: 66 */      if (e.key == key) {
/*  67: 67 */        V oldValue = e.value;
/*  68: 68 */        e.value = value;
/*  69: 69 */        return oldValue;
/*  70:    */      }
/*  71:    */    }
/*  72: 72 */    table[index] = new Entry(key, value, table[index]);
/*  73:    */    
/*  74: 74 */    if (this.size++ >= this.threshold) {
/*  75: 75 */      rehash(table);
/*  76:    */    }
/*  77: 77 */    return null;
/*  78:    */  }
/*  79:    */  
/*  80:    */  private void rehash(Entry<V>[] table) {
/*  81: 81 */    int newCapacity = 2 * this.capacity;
/*  82: 82 */    int newMask = newCapacity - 1;
/*  83:    */    
/*  84: 84 */    Entry<V>[] newTable = new Entry[newCapacity];
/*  85:    */    
/*  86: 86 */    for (int i = 0; i < table.length; i++) {
/*  87: 87 */      Entry<V> e = table[i];
/*  88: 88 */      if (e != null) {
/*  89:    */        do {
/*  90: 90 */          Entry<V> next = e.next;
/*  91: 91 */          int index = index(e.key, newMask);
/*  92: 92 */          e.next = newTable[index];
/*  93: 93 */          newTable[index] = e;
/*  94: 94 */          e = next;
/*  95: 95 */        } while (e != null);
/*  96:    */      }
/*  97:    */    }
/*  98: 98 */    this.table = newTable;
/*  99: 99 */    this.capacity = newCapacity;
/* 100:100 */    this.mask = newMask;
/* 101:101 */    this.threshold *= 2;
/* 102:    */  }
/* 103:    */  
/* 104:    */  public V get(int key) {
/* 105:105 */    int index = index(key);
/* 106:106 */    for (Entry<V> e = this.table[index]; e != null; e = e.next)
/* 107:107 */      if (e.key == key) return e.value;
/* 108:108 */    return null;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public boolean containsValue(Object value) {
/* 112:112 */    Entry<V>[] table = this.table;
/* 113:113 */    for (int i = table.length - 1; i >= 0; i--)
/* 114:114 */      for (Entry<V> e = table[i]; e != null; e = e.next)
/* 115:115 */        if (e.value.equals(value)) return true;
/* 116:116 */    return false;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public boolean containsKey(int key) {
/* 120:120 */    int index = index(key);
/* 121:121 */    for (Entry<V> e = this.table[index]; e != null; e = e.next)
/* 122:122 */      if (e.key == key) return true;
/* 123:123 */    return false;
/* 124:    */  }
/* 125:    */  
/* 126:    */  public V remove(int key) {
/* 127:127 */    int index = index(key);
/* 128:    */    
/* 129:129 */    Entry<V> prev = this.table[index];
/* 130:130 */    Entry<V> e = prev;
/* 131:131 */    while (e != null) {
/* 132:132 */      Entry<V> next = e.next;
/* 133:133 */      if (e.key == key) {
/* 134:134 */        this.size -= 1;
/* 135:135 */        if (prev == e) {
/* 136:136 */          this.table[index] = next;
/* 137:    */        } else
/* 138:138 */          prev.next = next;
/* 139:139 */        return e.value;
/* 140:    */      }
/* 141:141 */      prev = e;
/* 142:142 */      e = next;
/* 143:    */    }
/* 144:144 */    return null;
/* 145:    */  }
/* 146:    */  
/* 147:    */  public int size() {
/* 148:148 */    return this.size;
/* 149:    */  }
/* 150:    */  
/* 151:    */  public boolean isEmpty() {
/* 152:152 */    return this.size == 0;
/* 153:    */  }
/* 154:    */  
/* 155:    */  public void clear() {
/* 156:156 */    Entry<V>[] table = this.table;
/* 157:157 */    for (int index = table.length - 1; index >= 0; index--)
/* 158:158 */      table[index] = null;
/* 159:159 */    this.size = 0;
/* 160:    */  }
/* 161:    */  
/* 162:    */  public FastIntMap<V>.EntryIterator iterator() {
/* 163:163 */    return new EntryIterator();
/* 164:    */  }
/* 165:    */  
/* 166:    */  public class EntryIterator implements Iterator<FastIntMap.Entry<V>>
/* 167:    */  {
/* 168:    */    private int nextIndex;
/* 169:    */    private FastIntMap.Entry<V> current;
/* 170:    */    
/* 171:    */    EntryIterator() {
/* 172:172 */      reset();
/* 173:    */    }
/* 174:    */    
/* 175:    */    public void reset() {
/* 176:176 */      this.current = null;
/* 177:    */      
/* 178:178 */      FastIntMap.Entry<V>[] table = FastIntMap.this.table;
/* 179:    */      
/* 180:180 */      for (int i = table.length - 1; i >= 0; i--)
/* 181:181 */        if (table[i] != null) break;
/* 182:182 */      this.nextIndex = i;
/* 183:    */    }
/* 184:    */    
/* 185:    */    public boolean hasNext() {
/* 186:186 */      if (this.nextIndex >= 0) return true;
/* 187:187 */      FastIntMap.Entry e = this.current;
/* 188:188 */      return (e != null) && (e.next != null);
/* 189:    */    }
/* 190:    */    
/* 191:    */    public FastIntMap.Entry<V> next()
/* 192:    */    {
/* 193:193 */      FastIntMap.Entry<V> e = this.current;
/* 194:194 */      if (e != null) {
/* 195:195 */        e = e.next;
/* 196:196 */        if (e != null) {
/* 197:197 */          this.current = e;
/* 198:198 */          return e;
/* 199:    */        }
/* 200:    */      }
/* 201:    */      
/* 202:202 */      FastIntMap.Entry<V>[] table = FastIntMap.this.table;
/* 203:203 */      int i = this.nextIndex;
/* 204:204 */      e = this.current = table[i];
/* 205:205 */      for (;;) { i--; if (i >= 0)
/* 206:206 */          if (table[i] != null) break; }
/* 207:207 */      this.nextIndex = i;
/* 208:208 */      return e;
/* 209:    */    }
/* 210:    */    
/* 211:    */    public void remove() {
/* 212:212 */      FastIntMap.this.remove(this.current.key);
/* 213:    */    }
/* 214:    */  }
/* 215:    */  
/* 216:    */  static final class Entry<T>
/* 217:    */  {
/* 218:    */    final int key;
/* 219:    */    T value;
/* 220:    */    Entry<T> next;
/* 221:    */    
/* 222:    */    Entry(int key, T value, Entry<T> next) {
/* 223:223 */      this.key = key;
/* 224:224 */      this.value = value;
/* 225:225 */      this.next = next;
/* 226:    */    }
/* 227:    */    
/* 228:    */    public int getKey() {
/* 229:229 */      return this.key;
/* 230:    */    }
/* 231:    */    
/* 232:    */    public T getValue() {
/* 233:233 */      return this.value;
/* 234:    */    }
/* 235:    */  }
/* 236:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.FastIntMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */