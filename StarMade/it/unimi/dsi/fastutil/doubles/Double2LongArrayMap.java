/*   1:    */package it.unimi.dsi.fastutil.doubles;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.longs.LongArraySet;
/*   4:    */import it.unimi.dsi.fastutil.longs.LongArrays;
/*   5:    */import it.unimi.dsi.fastutil.longs.LongCollection;
/*   6:    */import it.unimi.dsi.fastutil.longs.LongCollections;
/*   7:    */import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
/*   9:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*  10:    */import java.io.IOException;
/*  11:    */import java.io.ObjectInputStream;
/*  12:    */import java.io.ObjectOutputStream;
/*  13:    */import java.io.Serializable;
/*  14:    */import java.util.Map;
/*  15:    */import java.util.Map.Entry;
/*  16:    */import java.util.NoSuchElementException;
/*  17:    */
/*  66:    */public class Double2LongArrayMap
/*  67:    */  extends AbstractDouble2LongMap
/*  68:    */  implements Serializable, Cloneable
/*  69:    */{
/*  70:    */  private static final long serialVersionUID = 1L;
/*  71:    */  private transient double[] key;
/*  72:    */  private transient long[] value;
/*  73:    */  private int size;
/*  74:    */  
/*  75:    */  public Double2LongArrayMap(double[] key, long[] value)
/*  76:    */  {
/*  77: 77 */    this.key = key;
/*  78: 78 */    this.value = value;
/*  79: 79 */    this.size = key.length;
/*  80: 80 */    if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/*  81:    */  }
/*  82:    */  
/*  83:    */  public Double2LongArrayMap()
/*  84:    */  {
/*  85: 85 */    this.key = DoubleArrays.EMPTY_ARRAY;
/*  86: 86 */    this.value = LongArrays.EMPTY_ARRAY;
/*  87:    */  }
/*  88:    */  
/*  91:    */  public Double2LongArrayMap(int capacity)
/*  92:    */  {
/*  93: 93 */    this.key = new double[capacity];
/*  94: 94 */    this.value = new long[capacity];
/*  95:    */  }
/*  96:    */  
/*  99:    */  public Double2LongArrayMap(Double2LongMap m)
/* 100:    */  {
/* 101:101 */    this(m.size());
/* 102:102 */    putAll(m);
/* 103:    */  }
/* 104:    */  
/* 107:    */  public Double2LongArrayMap(Map<? extends Double, ? extends Long> m)
/* 108:    */  {
/* 109:109 */    this(m.size());
/* 110:110 */    putAll(m);
/* 111:    */  }
/* 112:    */  
/* 119:    */  public Double2LongArrayMap(double[] key, long[] value, int size)
/* 120:    */  {
/* 121:121 */    this.key = key;
/* 122:122 */    this.value = value;
/* 123:123 */    this.size = size;
/* 124:124 */    if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/* 125:125 */    if (size > key.length) throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")");
/* 126:    */  }
/* 127:    */  
/* 128:    */  private final class EntrySet extends AbstractObjectSet<Double2LongMap.Entry> implements Double2LongMap.FastEntrySet { private EntrySet() {}
/* 129:    */    
/* 130:130 */    public ObjectIterator<Double2LongMap.Entry> iterator() { new AbstractObjectIterator() {
/* 131:131 */        int next = 0;
/* 132:    */        
/* 133:133 */        public boolean hasNext() { return this.next < Double2LongArrayMap.this.size; }
/* 134:    */        
/* 135:    */        public Double2LongMap.Entry next()
/* 136:    */        {
/* 137:137 */          if (!hasNext()) throw new NoSuchElementException();
/* 138:138 */          return new AbstractDouble2LongMap.BasicEntry(Double2LongArrayMap.this.key[this.next], Double2LongArrayMap.this.value[(this.next++)]);
/* 139:    */        }
/* 140:    */      }; }
/* 141:    */    
/* 142:    */    public ObjectIterator<Double2LongMap.Entry> fastIterator() {
/* 143:143 */      new AbstractObjectIterator() {
/* 144:144 */        int next = 0;
/* 145:145 */        final AbstractDouble2LongMap.BasicEntry entry = new AbstractDouble2LongMap.BasicEntry(0.0D, 0L);
/* 146:    */        
/* 147:147 */        public boolean hasNext() { return this.next < Double2LongArrayMap.this.size; }
/* 148:    */        
/* 149:    */        public Double2LongMap.Entry next()
/* 150:    */        {
/* 151:151 */          if (!hasNext()) throw new NoSuchElementException();
/* 152:152 */          this.entry.key = Double2LongArrayMap.this.key[this.next];
/* 153:153 */          this.entry.value = Double2LongArrayMap.this.value[(this.next++)];
/* 154:154 */          return this.entry;
/* 155:    */        }
/* 156:    */      };
/* 157:    */    }
/* 158:    */    
/* 159:159 */    public int size() { return Double2LongArrayMap.this.size; }
/* 160:    */    
/* 161:    */    public boolean contains(Object o)
/* 162:    */    {
/* 163:163 */      if (!(o instanceof Map.Entry)) return false;
/* 164:164 */      Map.Entry<Double, Long> e = (Map.Entry)o;
/* 165:165 */      double k = ((Double)e.getKey()).doubleValue();
/* 166:166 */      return (Double2LongArrayMap.this.containsKey(k)) && (Double2LongArrayMap.this.get(k) == ((Long)e.getValue()).longValue());
/* 167:    */    }
/* 168:    */  }
/* 169:    */  
/* 170:170 */  public Double2LongMap.FastEntrySet double2LongEntrySet() { return new EntrySet(null); }
/* 171:    */  
/* 172:    */  private int findKey(double k) {
/* 173:173 */    double[] key = this.key;
/* 174:174 */    for (int i = this.size; i-- != 0;) if (key[i] == k) return i;
/* 175:175 */    return -1;
/* 176:    */  }
/* 177:    */  
/* 182:    */  public long get(double k)
/* 183:    */  {
/* 184:184 */    double[] key = this.key;
/* 185:185 */    for (int i = this.size; i-- != 0;) if (key[i] == k) return this.value[i];
/* 186:186 */    return this.defRetValue;
/* 187:    */  }
/* 188:    */  
/* 189:    */  public int size() {
/* 190:190 */    return this.size;
/* 191:    */  }
/* 192:    */  
/* 193:    */  public void clear()
/* 194:    */  {
/* 195:195 */    this.size = 0;
/* 196:    */  }
/* 197:    */  
/* 198:    */  public boolean containsKey(double k) {
/* 199:199 */    return findKey(k) != -1;
/* 200:    */  }
/* 201:    */  
/* 202:    */  public boolean containsValue(long v)
/* 203:    */  {
/* 204:204 */    for (int i = this.size; i-- != 0;) if (this.value[i] == v) return true;
/* 205:205 */    return false;
/* 206:    */  }
/* 207:    */  
/* 208:    */  public boolean isEmpty() {
/* 209:209 */    return this.size == 0;
/* 210:    */  }
/* 211:    */  
/* 212:    */  public long put(double k, long v)
/* 213:    */  {
/* 214:214 */    int oldKey = findKey(k);
/* 215:215 */    if (oldKey != -1) {
/* 216:216 */      long oldValue = this.value[oldKey];
/* 217:217 */      this.value[oldKey] = v;
/* 218:218 */      return oldValue;
/* 219:    */    }
/* 220:220 */    if (this.size == this.key.length) {
/* 221:221 */      double[] newKey = new double[this.size == 0 ? 2 : this.size * 2];
/* 222:222 */      long[] newValue = new long[this.size == 0 ? 2 : this.size * 2];
/* 223:223 */      for (int i = this.size; i-- != 0;) {
/* 224:224 */        newKey[i] = this.key[i];
/* 225:225 */        newValue[i] = this.value[i];
/* 226:    */      }
/* 227:227 */      this.key = newKey;
/* 228:228 */      this.value = newValue;
/* 229:    */    }
/* 230:230 */    this.key[this.size] = k;
/* 231:231 */    this.value[this.size] = v;
/* 232:232 */    this.size += 1;
/* 233:233 */    return this.defRetValue;
/* 234:    */  }
/* 235:    */  
/* 236:    */  public long remove(double k)
/* 237:    */  {
/* 238:238 */    int oldPos = findKey(k);
/* 239:239 */    if (oldPos == -1) return this.defRetValue;
/* 240:240 */    long oldValue = this.value[oldPos];
/* 241:241 */    int tail = this.size - oldPos - 1;
/* 242:242 */    for (int i = 0; i < tail; i++) {
/* 243:243 */      this.key[(oldPos + i)] = this.key[(oldPos + i + 1)];
/* 244:244 */      this.value[(oldPos + i)] = this.value[(oldPos + i + 1)];
/* 245:    */    }
/* 246:246 */    this.size -= 1;
/* 247:247 */    return oldValue;
/* 248:    */  }
/* 249:    */  
/* 250:    */  public DoubleSet keySet()
/* 251:    */  {
/* 252:252 */    return new DoubleArraySet(this.key, this.size);
/* 253:    */  }
/* 254:    */  
/* 255:    */  public LongCollection values() {
/* 256:256 */    return LongCollections.unmodifiable(new LongArraySet(this.value, this.size));
/* 257:    */  }
/* 258:    */  
/* 262:    */  public Double2LongArrayMap clone()
/* 263:    */  {
/* 264:    */    Double2LongArrayMap c;
/* 265:    */    
/* 267:    */    try
/* 268:    */    {
/* 269:269 */      c = (Double2LongArrayMap)super.clone();
/* 270:    */    }
/* 271:    */    catch (CloneNotSupportedException cantHappen) {
/* 272:272 */      throw new InternalError();
/* 273:    */    }
/* 274:274 */    c.key = ((double[])this.key.clone());
/* 275:275 */    c.value = ((long[])this.value.clone());
/* 276:276 */    return c;
/* 277:    */  }
/* 278:    */  
/* 279:279 */  private void writeObject(ObjectOutputStream s) throws IOException { s.defaultWriteObject();
/* 280:280 */    for (int i = 0; i < this.size; i++) {
/* 281:281 */      s.writeDouble(this.key[i]);
/* 282:282 */      s.writeLong(this.value[i]);
/* 283:    */    }
/* 284:    */  }
/* 285:    */  
/* 286:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 287:287 */    s.defaultReadObject();
/* 288:288 */    this.key = new double[this.size];
/* 289:289 */    this.value = new long[this.size];
/* 290:290 */    for (int i = 0; i < this.size; i++) {
/* 291:291 */      this.key[i] = s.readDouble();
/* 292:292 */      this.value[i] = s.readLong();
/* 293:    */    }
/* 294:    */  }
/* 295:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2LongArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */