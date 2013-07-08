/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.bytes.ByteArraySet;
/*   4:    */import it.unimi.dsi.fastutil.bytes.ByteArrays;
/*   5:    */import it.unimi.dsi.fastutil.bytes.ByteCollection;
/*   6:    */import it.unimi.dsi.fastutil.bytes.ByteCollections;
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
/*  66:    */public class Int2ByteArrayMap
/*  67:    */  extends AbstractInt2ByteMap
/*  68:    */  implements Serializable, Cloneable
/*  69:    */{
/*  70:    */  private static final long serialVersionUID = 1L;
/*  71:    */  private transient int[] key;
/*  72:    */  private transient byte[] value;
/*  73:    */  private int size;
/*  74:    */  
/*  75:    */  public Int2ByteArrayMap(int[] key, byte[] value)
/*  76:    */  {
/*  77: 77 */    this.key = key;
/*  78: 78 */    this.value = value;
/*  79: 79 */    this.size = key.length;
/*  80: 80 */    if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/*  81:    */  }
/*  82:    */  
/*  83:    */  public Int2ByteArrayMap()
/*  84:    */  {
/*  85: 85 */    this.key = IntArrays.EMPTY_ARRAY;
/*  86: 86 */    this.value = ByteArrays.EMPTY_ARRAY;
/*  87:    */  }
/*  88:    */  
/*  91:    */  public Int2ByteArrayMap(int capacity)
/*  92:    */  {
/*  93: 93 */    this.key = new int[capacity];
/*  94: 94 */    this.value = new byte[capacity];
/*  95:    */  }
/*  96:    */  
/*  99:    */  public Int2ByteArrayMap(Int2ByteMap m)
/* 100:    */  {
/* 101:101 */    this(m.size());
/* 102:102 */    putAll(m);
/* 103:    */  }
/* 104:    */  
/* 107:    */  public Int2ByteArrayMap(Map<? extends Integer, ? extends Byte> m)
/* 108:    */  {
/* 109:109 */    this(m.size());
/* 110:110 */    putAll(m);
/* 111:    */  }
/* 112:    */  
/* 119:    */  public Int2ByteArrayMap(int[] key, byte[] value, int size)
/* 120:    */  {
/* 121:121 */    this.key = key;
/* 122:122 */    this.value = value;
/* 123:123 */    this.size = size;
/* 124:124 */    if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/* 125:125 */    if (size > key.length) throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")");
/* 126:    */  }
/* 127:    */  
/* 128:    */  private final class EntrySet extends AbstractObjectSet<Int2ByteMap.Entry> implements Int2ByteMap.FastEntrySet { private EntrySet() {}
/* 129:    */    
/* 130:130 */    public ObjectIterator<Int2ByteMap.Entry> iterator() { new AbstractObjectIterator() {
/* 131:131 */        int next = 0;
/* 132:    */        
/* 133:133 */        public boolean hasNext() { return this.next < Int2ByteArrayMap.this.size; }
/* 134:    */        
/* 135:    */        public Int2ByteMap.Entry next()
/* 136:    */        {
/* 137:137 */          if (!hasNext()) throw new NoSuchElementException();
/* 138:138 */          return new AbstractInt2ByteMap.BasicEntry(Int2ByteArrayMap.this.key[this.next], Int2ByteArrayMap.this.value[(this.next++)]);
/* 139:    */        }
/* 140:    */      }; }
/* 141:    */    
/* 142:    */    public ObjectIterator<Int2ByteMap.Entry> fastIterator() {
/* 143:143 */      new AbstractObjectIterator() {
/* 144:144 */        int next = 0;
/* 145:145 */        final AbstractInt2ByteMap.BasicEntry entry = new AbstractInt2ByteMap.BasicEntry(0, (byte)0);
/* 146:    */        
/* 147:147 */        public boolean hasNext() { return this.next < Int2ByteArrayMap.this.size; }
/* 148:    */        
/* 149:    */        public Int2ByteMap.Entry next()
/* 150:    */        {
/* 151:151 */          if (!hasNext()) throw new NoSuchElementException();
/* 152:152 */          this.entry.key = Int2ByteArrayMap.this.key[this.next];
/* 153:153 */          this.entry.value = Int2ByteArrayMap.this.value[(this.next++)];
/* 154:154 */          return this.entry;
/* 155:    */        }
/* 156:    */      };
/* 157:    */    }
/* 158:    */    
/* 159:159 */    public int size() { return Int2ByteArrayMap.this.size; }
/* 160:    */    
/* 161:    */    public boolean contains(Object o)
/* 162:    */    {
/* 163:163 */      if (!(o instanceof Map.Entry)) return false;
/* 164:164 */      Map.Entry<Integer, Byte> e = (Map.Entry)o;
/* 165:165 */      int k = ((Integer)e.getKey()).intValue();
/* 166:166 */      return (Int2ByteArrayMap.this.containsKey(k)) && (Int2ByteArrayMap.this.get(k) == ((Byte)e.getValue()).byteValue());
/* 167:    */    }
/* 168:    */  }
/* 169:    */  
/* 170:170 */  public Int2ByteMap.FastEntrySet int2ByteEntrySet() { return new EntrySet(null); }
/* 171:    */  
/* 172:    */  private int findKey(int k) {
/* 173:173 */    int[] key = this.key;
/* 174:174 */    for (int i = this.size; i-- != 0;) if (key[i] == k) return i;
/* 175:175 */    return -1;
/* 176:    */  }
/* 177:    */  
/* 182:    */  public byte get(int k)
/* 183:    */  {
/* 184:184 */    int[] key = this.key;
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
/* 198:    */  public boolean containsKey(int k) {
/* 199:199 */    return findKey(k) != -1;
/* 200:    */  }
/* 201:    */  
/* 202:    */  public boolean containsValue(byte v)
/* 203:    */  {
/* 204:204 */    for (int i = this.size; i-- != 0;) if (this.value[i] == v) return true;
/* 205:205 */    return false;
/* 206:    */  }
/* 207:    */  
/* 208:    */  public boolean isEmpty() {
/* 209:209 */    return this.size == 0;
/* 210:    */  }
/* 211:    */  
/* 212:    */  public byte put(int k, byte v)
/* 213:    */  {
/* 214:214 */    int oldKey = findKey(k);
/* 215:215 */    if (oldKey != -1) {
/* 216:216 */      byte oldValue = this.value[oldKey];
/* 217:217 */      this.value[oldKey] = v;
/* 218:218 */      return oldValue;
/* 219:    */    }
/* 220:220 */    if (this.size == this.key.length) {
/* 221:221 */      int[] newKey = new int[this.size == 0 ? 2 : this.size * 2];
/* 222:222 */      byte[] newValue = new byte[this.size == 0 ? 2 : this.size * 2];
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
/* 236:    */  public byte remove(int k)
/* 237:    */  {
/* 238:238 */    int oldPos = findKey(k);
/* 239:239 */    if (oldPos == -1) return this.defRetValue;
/* 240:240 */    byte oldValue = this.value[oldPos];
/* 241:241 */    int tail = this.size - oldPos - 1;
/* 242:242 */    for (int i = 0; i < tail; i++) {
/* 243:243 */      this.key[(oldPos + i)] = this.key[(oldPos + i + 1)];
/* 244:244 */      this.value[(oldPos + i)] = this.value[(oldPos + i + 1)];
/* 245:    */    }
/* 246:246 */    this.size -= 1;
/* 247:247 */    return oldValue;
/* 248:    */  }
/* 249:    */  
/* 250:    */  public IntSet keySet()
/* 251:    */  {
/* 252:252 */    return new IntArraySet(this.key, this.size);
/* 253:    */  }
/* 254:    */  
/* 255:    */  public ByteCollection values() {
/* 256:256 */    return ByteCollections.unmodifiable(new ByteArraySet(this.value, this.size));
/* 257:    */  }
/* 258:    */  
/* 262:    */  public Int2ByteArrayMap clone()
/* 263:    */  {
/* 264:    */    Int2ByteArrayMap c;
/* 265:    */    
/* 267:    */    try
/* 268:    */    {
/* 269:269 */      c = (Int2ByteArrayMap)super.clone();
/* 270:    */    }
/* 271:    */    catch (CloneNotSupportedException cantHappen) {
/* 272:272 */      throw new InternalError();
/* 273:    */    }
/* 274:274 */    c.key = ((int[])this.key.clone());
/* 275:275 */    c.value = ((byte[])this.value.clone());
/* 276:276 */    return c;
/* 277:    */  }
/* 278:    */  
/* 279:279 */  private void writeObject(ObjectOutputStream s) throws IOException { s.defaultWriteObject();
/* 280:280 */    for (int i = 0; i < this.size; i++) {
/* 281:281 */      s.writeInt(this.key[i]);
/* 282:282 */      s.writeByte(this.value[i]);
/* 283:    */    }
/* 284:    */  }
/* 285:    */  
/* 286:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 287:287 */    s.defaultReadObject();
/* 288:288 */    this.key = new int[this.size];
/* 289:289 */    this.value = new byte[this.size];
/* 290:290 */    for (int i = 0; i < this.size; i++) {
/* 291:291 */      this.key[i] = s.readInt();
/* 292:292 */      this.value[i] = s.readByte();
/* 293:    */    }
/* 294:    */  }
/* 295:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ByteArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */