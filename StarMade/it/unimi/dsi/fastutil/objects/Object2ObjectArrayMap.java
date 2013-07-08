/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.ObjectInputStream;
/*   5:    */import java.io.ObjectOutputStream;
/*   6:    */import java.io.Serializable;
/*   7:    */import java.util.Map;
/*   8:    */import java.util.Map.Entry;
/*   9:    */import java.util.NoSuchElementException;
/*  10:    */
/*  64:    */public class Object2ObjectArrayMap<K, V>
/*  65:    */  extends AbstractObject2ObjectMap<K, V>
/*  66:    */  implements Serializable, Cloneable
/*  67:    */{
/*  68:    */  private static final long serialVersionUID = 1L;
/*  69:    */  private transient Object[] key;
/*  70:    */  private transient Object[] value;
/*  71:    */  private int size;
/*  72:    */  
/*  73:    */  public Object2ObjectArrayMap(Object[] key, Object[] value)
/*  74:    */  {
/*  75: 75 */    this.key = key;
/*  76: 76 */    this.value = value;
/*  77: 77 */    this.size = key.length;
/*  78: 78 */    if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/*  79:    */  }
/*  80:    */  
/*  81:    */  public Object2ObjectArrayMap()
/*  82:    */  {
/*  83: 83 */    this.key = ObjectArrays.EMPTY_ARRAY;
/*  84: 84 */    this.value = ObjectArrays.EMPTY_ARRAY;
/*  85:    */  }
/*  86:    */  
/*  89:    */  public Object2ObjectArrayMap(int capacity)
/*  90:    */  {
/*  91: 91 */    this.key = new Object[capacity];
/*  92: 92 */    this.value = new Object[capacity];
/*  93:    */  }
/*  94:    */  
/*  97:    */  public Object2ObjectArrayMap(Object2ObjectMap<K, V> m)
/*  98:    */  {
/*  99: 99 */    this(m.size());
/* 100:100 */    putAll(m);
/* 101:    */  }
/* 102:    */  
/* 105:    */  public Object2ObjectArrayMap(Map<? extends K, ? extends V> m)
/* 106:    */  {
/* 107:107 */    this(m.size());
/* 108:108 */    putAll(m);
/* 109:    */  }
/* 110:    */  
/* 117:    */  public Object2ObjectArrayMap(Object[] key, Object[] value, int size)
/* 118:    */  {
/* 119:119 */    this.key = key;
/* 120:120 */    this.value = value;
/* 121:121 */    this.size = size;
/* 122:122 */    if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/* 123:123 */    if (size > key.length) throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")");
/* 124:    */  }
/* 125:    */  
/* 126:    */  private final class EntrySet extends AbstractObjectSet<Object2ObjectMap.Entry<K, V>> implements Object2ObjectMap.FastEntrySet<K, V> { private EntrySet() {}
/* 127:    */    
/* 128:128 */    public ObjectIterator<Object2ObjectMap.Entry<K, V>> iterator() { new AbstractObjectIterator() {
/* 129:129 */        int next = 0;
/* 130:    */        
/* 131:131 */        public boolean hasNext() { return this.next < Object2ObjectArrayMap.this.size; }
/* 132:    */        
/* 133:    */        public Object2ObjectMap.Entry<K, V> next()
/* 134:    */        {
/* 135:135 */          if (!hasNext()) throw new NoSuchElementException();
/* 136:136 */          return new AbstractObject2ObjectMap.BasicEntry(Object2ObjectArrayMap.this.key[this.next], Object2ObjectArrayMap.this.value[(this.next++)]);
/* 137:    */        }
/* 138:    */      }; }
/* 139:    */    
/* 140:    */    public ObjectIterator<Object2ObjectMap.Entry<K, V>> fastIterator() {
/* 141:141 */      new AbstractObjectIterator() {
/* 142:142 */        int next = 0;
/* 143:143 */        final AbstractObject2ObjectMap.BasicEntry<K, V> entry = new AbstractObject2ObjectMap.BasicEntry(null, null);
/* 144:    */        
/* 145:145 */        public boolean hasNext() { return this.next < Object2ObjectArrayMap.this.size; }
/* 146:    */        
/* 147:    */        public Object2ObjectMap.Entry<K, V> next()
/* 148:    */        {
/* 149:149 */          if (!hasNext()) throw new NoSuchElementException();
/* 150:150 */          this.entry.key = Object2ObjectArrayMap.this.key[this.next];
/* 151:151 */          this.entry.value = Object2ObjectArrayMap.this.value[(this.next++)];
/* 152:152 */          return this.entry;
/* 153:    */        }
/* 154:    */      };
/* 155:    */    }
/* 156:    */    
/* 157:157 */    public int size() { return Object2ObjectArrayMap.this.size; }
/* 158:    */    
/* 159:    */    public boolean contains(Object o)
/* 160:    */    {
/* 161:161 */      if (!(o instanceof Map.Entry)) return false;
/* 162:162 */      Map.Entry<K, V> e = (Map.Entry)o;
/* 163:163 */      K k = e.getKey();
/* 164:164 */      return (Object2ObjectArrayMap.this.containsKey(k)) && (Object2ObjectArrayMap.this.get(k) == null ? e.getValue() == null : Object2ObjectArrayMap.this.get(k).equals(e.getValue()));
/* 165:    */    }
/* 166:    */  }
/* 167:    */  
/* 168:    */  public Object2ObjectMap.FastEntrySet<K, V> object2ObjectEntrySet() {
/* 169:169 */    return new EntrySet(null);
/* 170:    */  }
/* 171:    */  
/* 172:    */  private int findKey(Object k) {
/* 173:173 */    Object[] key = this.key;
/* 174:174 */    for (int i = this.size; i-- != 0; return i) label10: if (key[i] == null ? k != null : !key[i].equals(k)) break label10;
/* 175:175 */    return -1;
/* 176:    */  }
/* 177:    */  
/* 182:    */  public V get(Object k)
/* 183:    */  {
/* 184:184 */    Object[] key = this.key;
/* 185:185 */    for (int i = this.size; i-- != 0; return this.value[i]) label10: if (key[i] == null ? k != null : !key[i].equals(k)) break label10;
/* 186:186 */    return this.defRetValue;
/* 187:    */  }
/* 188:    */  
/* 189:    */  public int size() {
/* 190:190 */    return this.size;
/* 191:    */  }
/* 192:    */  
/* 194:    */  public void clear()
/* 195:    */  {
/* 196:196 */    for (int i = this.size; i-- != 0;)
/* 197:    */    {
/* 198:198 */      this.key[i] = null;
/* 199:    */      
/* 201:201 */      this.value[i] = null;
/* 202:    */    }
/* 203:    */    
/* 205:205 */    this.size = 0;
/* 206:    */  }
/* 207:    */  
/* 208:    */  public boolean containsKey(Object k)
/* 209:    */  {
/* 210:210 */    return findKey(k) != -1;
/* 211:    */  }
/* 212:    */  
/* 214:    */  public boolean containsValue(Object v)
/* 215:    */  {
/* 216:216 */    for (int i = this.size; i-- != 0; return true) label5: if (this.value[i] == null ? v != null : !this.value[i].equals(v)) break label5;
/* 217:217 */    return false;
/* 218:    */  }
/* 219:    */  
/* 220:    */  public boolean isEmpty()
/* 221:    */  {
/* 222:222 */    return this.size == 0;
/* 223:    */  }
/* 224:    */  
/* 226:    */  public V put(K k, V v)
/* 227:    */  {
/* 228:228 */    int oldKey = findKey(k);
/* 229:229 */    if (oldKey != -1) {
/* 230:230 */      V oldValue = this.value[oldKey];
/* 231:231 */      this.value[oldKey] = v;
/* 232:232 */      return oldValue;
/* 233:    */    }
/* 234:234 */    if (this.size == this.key.length) {
/* 235:235 */      Object[] newKey = new Object[this.size == 0 ? 2 : this.size * 2];
/* 236:236 */      Object[] newValue = new Object[this.size == 0 ? 2 : this.size * 2];
/* 237:237 */      for (int i = this.size; i-- != 0;) {
/* 238:238 */        newKey[i] = this.key[i];
/* 239:239 */        newValue[i] = this.value[i];
/* 240:    */      }
/* 241:241 */      this.key = newKey;
/* 242:242 */      this.value = newValue;
/* 243:    */    }
/* 244:244 */    this.key[this.size] = k;
/* 245:245 */    this.value[this.size] = v;
/* 246:246 */    this.size += 1;
/* 247:247 */    return this.defRetValue;
/* 248:    */  }
/* 249:    */  
/* 256:    */  public V remove(Object k)
/* 257:    */  {
/* 258:258 */    int oldPos = findKey(k);
/* 259:259 */    if (oldPos == -1) return this.defRetValue;
/* 260:260 */    V oldValue = this.value[oldPos];
/* 261:261 */    int tail = this.size - oldPos - 1;
/* 262:262 */    for (int i = 0; i < tail; i++) {
/* 263:263 */      this.key[(oldPos + i)] = this.key[(oldPos + i + 1)];
/* 264:264 */      this.value[(oldPos + i)] = this.value[(oldPos + i + 1)];
/* 265:    */    }
/* 266:266 */    this.size -= 1;
/* 267:    */    
/* 268:268 */    this.key[this.size] = null;
/* 269:    */    
/* 271:271 */    this.value[this.size] = null;
/* 272:    */    
/* 273:273 */    return oldValue;
/* 274:    */  }
/* 275:    */  
/* 278:    */  public ObjectSet<K> keySet()
/* 279:    */  {
/* 280:280 */    return new ObjectArraySet(this.key, this.size);
/* 281:    */  }
/* 282:    */  
/* 283:    */  public ObjectCollection<V> values()
/* 284:    */  {
/* 285:285 */    return ObjectCollections.unmodifiable(new ObjectArraySet(this.value, this.size));
/* 286:    */  }
/* 287:    */  
/* 292:    */  public Object2ObjectArrayMap<K, V> clone()
/* 293:    */  {
/* 294:    */    Object2ObjectArrayMap<K, V> c;
/* 295:    */    
/* 298:    */    try
/* 299:    */    {
/* 300:300 */      c = (Object2ObjectArrayMap)super.clone();
/* 301:    */    }
/* 302:    */    catch (CloneNotSupportedException cantHappen) {
/* 303:303 */      throw new InternalError();
/* 304:    */    }
/* 305:305 */    c.key = ((Object[])this.key.clone());
/* 306:306 */    c.value = ((Object[])this.value.clone());
/* 307:307 */    return c;
/* 308:    */  }
/* 309:    */  
/* 310:    */  private void writeObject(ObjectOutputStream s) throws IOException {
/* 311:311 */    s.defaultWriteObject();
/* 312:312 */    for (int i = 0; i < this.size; i++) {
/* 313:313 */      s.writeObject(this.key[i]);
/* 314:314 */      s.writeObject(this.value[i]);
/* 315:    */    }
/* 316:    */  }
/* 317:    */  
/* 318:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
/* 319:    */  {
/* 320:320 */    s.defaultReadObject();
/* 321:321 */    this.key = new Object[this.size];
/* 322:322 */    this.value = new Object[this.size];
/* 323:323 */    for (int i = 0; i < this.size; i++) {
/* 324:324 */      this.key[i] = s.readObject();
/* 325:325 */      this.value[i] = s.readObject();
/* 326:    */    }
/* 327:    */  }
/* 328:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */