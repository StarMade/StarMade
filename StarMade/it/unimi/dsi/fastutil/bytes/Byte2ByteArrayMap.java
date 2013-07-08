/*   1:    */package it.unimi.dsi.fastutil.bytes;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*   4:    */import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.ObjectInputStream;
/*   8:    */import java.io.ObjectOutputStream;
/*   9:    */import java.io.Serializable;
/*  10:    */import java.util.Map;
/*  11:    */import java.util.Map.Entry;
/*  12:    */import java.util.NoSuchElementException;
/*  13:    */
/*  66:    */public class Byte2ByteArrayMap
/*  67:    */  extends AbstractByte2ByteMap
/*  68:    */  implements Serializable, Cloneable
/*  69:    */{
/*  70:    */  private static final long serialVersionUID = 1L;
/*  71:    */  private transient byte[] key;
/*  72:    */  private transient byte[] value;
/*  73:    */  private int size;
/*  74:    */  
/*  75:    */  public Byte2ByteArrayMap(byte[] key, byte[] value)
/*  76:    */  {
/*  77: 77 */    this.key = key;
/*  78: 78 */    this.value = value;
/*  79: 79 */    this.size = key.length;
/*  80: 80 */    if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/*  81:    */  }
/*  82:    */  
/*  83:    */  public Byte2ByteArrayMap()
/*  84:    */  {
/*  85: 85 */    this.key = ByteArrays.EMPTY_ARRAY;
/*  86: 86 */    this.value = ByteArrays.EMPTY_ARRAY;
/*  87:    */  }
/*  88:    */  
/*  91:    */  public Byte2ByteArrayMap(int capacity)
/*  92:    */  {
/*  93: 93 */    this.key = new byte[capacity];
/*  94: 94 */    this.value = new byte[capacity];
/*  95:    */  }
/*  96:    */  
/*  99:    */  public Byte2ByteArrayMap(Byte2ByteMap m)
/* 100:    */  {
/* 101:101 */    this(m.size());
/* 102:102 */    putAll(m);
/* 103:    */  }
/* 104:    */  
/* 107:    */  public Byte2ByteArrayMap(Map<? extends Byte, ? extends Byte> m)
/* 108:    */  {
/* 109:109 */    this(m.size());
/* 110:110 */    putAll(m);
/* 111:    */  }
/* 112:    */  
/* 119:    */  public Byte2ByteArrayMap(byte[] key, byte[] value, int size)
/* 120:    */  {
/* 121:121 */    this.key = key;
/* 122:122 */    this.value = value;
/* 123:123 */    this.size = size;
/* 124:124 */    if (key.length != value.length) throw new IllegalArgumentException("Keys and values have different lengths (" + key.length + ", " + value.length + ")");
/* 125:125 */    if (size > key.length) throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the backing-arrays size (" + key.length + ")");
/* 126:    */  }
/* 127:    */  
/* 128:    */  private final class EntrySet extends AbstractObjectSet<Byte2ByteMap.Entry> implements Byte2ByteMap.FastEntrySet { private EntrySet() {}
/* 129:    */    
/* 130:130 */    public ObjectIterator<Byte2ByteMap.Entry> iterator() { new AbstractObjectIterator() {
/* 131:131 */        int next = 0;
/* 132:    */        
/* 133:133 */        public boolean hasNext() { return this.next < Byte2ByteArrayMap.this.size; }
/* 134:    */        
/* 135:    */        public Byte2ByteMap.Entry next()
/* 136:    */        {
/* 137:137 */          if (!hasNext()) throw new NoSuchElementException();
/* 138:138 */          return new AbstractByte2ByteMap.BasicEntry(Byte2ByteArrayMap.this.key[this.next], Byte2ByteArrayMap.this.value[(this.next++)]);
/* 139:    */        }
/* 140:    */      }; }
/* 141:    */    
/* 142:    */    public ObjectIterator<Byte2ByteMap.Entry> fastIterator() {
/* 143:143 */      new AbstractObjectIterator() {
/* 144:144 */        int next = 0;
/* 145:145 */        final AbstractByte2ByteMap.BasicEntry entry = new AbstractByte2ByteMap.BasicEntry((byte)0, (byte)0);
/* 146:    */        
/* 147:147 */        public boolean hasNext() { return this.next < Byte2ByteArrayMap.this.size; }
/* 148:    */        
/* 149:    */        public Byte2ByteMap.Entry next()
/* 150:    */        {
/* 151:151 */          if (!hasNext()) throw new NoSuchElementException();
/* 152:152 */          this.entry.key = Byte2ByteArrayMap.this.key[this.next];
/* 153:153 */          this.entry.value = Byte2ByteArrayMap.this.value[(this.next++)];
/* 154:154 */          return this.entry;
/* 155:    */        }
/* 156:    */      };
/* 157:    */    }
/* 158:    */    
/* 159:159 */    public int size() { return Byte2ByteArrayMap.this.size; }
/* 160:    */    
/* 161:    */    public boolean contains(Object o)
/* 162:    */    {
/* 163:163 */      if (!(o instanceof Map.Entry)) return false;
/* 164:164 */      Map.Entry<Byte, Byte> e = (Map.Entry)o;
/* 165:165 */      byte k = ((Byte)e.getKey()).byteValue();
/* 166:166 */      return (Byte2ByteArrayMap.this.containsKey(k)) && (Byte2ByteArrayMap.this.get(k) == ((Byte)e.getValue()).byteValue());
/* 167:    */    }
/* 168:    */  }
/* 169:    */  
/* 170:170 */  public Byte2ByteMap.FastEntrySet byte2ByteEntrySet() { return new EntrySet(null); }
/* 171:    */  
/* 172:    */  private int findKey(byte k) {
/* 173:173 */    byte[] key = this.key;
/* 174:174 */    for (int i = this.size; i-- != 0;) if (key[i] == k) return i;
/* 175:175 */    return -1;
/* 176:    */  }
/* 177:    */  
/* 182:    */  public byte get(byte k)
/* 183:    */  {
/* 184:184 */    byte[] key = this.key;
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
/* 198:    */  public boolean containsKey(byte k) {
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
/* 212:    */  public byte put(byte k, byte v)
/* 213:    */  {
/* 214:214 */    int oldKey = findKey(k);
/* 215:215 */    if (oldKey != -1) {
/* 216:216 */      byte oldValue = this.value[oldKey];
/* 217:217 */      this.value[oldKey] = v;
/* 218:218 */      return oldValue;
/* 219:    */    }
/* 220:220 */    if (this.size == this.key.length) {
/* 221:221 */      byte[] newKey = new byte[this.size == 0 ? 2 : this.size * 2];
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
/* 236:    */  public byte remove(byte k)
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
/* 250:    */  public ByteSet keySet()
/* 251:    */  {
/* 252:252 */    return new ByteArraySet(this.key, this.size);
/* 253:    */  }
/* 254:    */  
/* 255:    */  public ByteCollection values() {
/* 256:256 */    return ByteCollections.unmodifiable(new ByteArraySet(this.value, this.size));
/* 257:    */  }
/* 258:    */  
/* 262:    */  public Byte2ByteArrayMap clone()
/* 263:    */  {
/* 264:    */    Byte2ByteArrayMap c;
/* 265:    */    
/* 267:    */    try
/* 268:    */    {
/* 269:269 */      c = (Byte2ByteArrayMap)super.clone();
/* 270:    */    }
/* 271:    */    catch (CloneNotSupportedException cantHappen) {
/* 272:272 */      throw new InternalError();
/* 273:    */    }
/* 274:274 */    c.key = ((byte[])this.key.clone());
/* 275:275 */    c.value = ((byte[])this.value.clone());
/* 276:276 */    return c;
/* 277:    */  }
/* 278:    */  
/* 279:279 */  private void writeObject(ObjectOutputStream s) throws IOException { s.defaultWriteObject();
/* 280:280 */    for (int i = 0; i < this.size; i++) {
/* 281:281 */      s.writeByte(this.key[i]);
/* 282:282 */      s.writeByte(this.value[i]);
/* 283:    */    }
/* 284:    */  }
/* 285:    */  
/* 286:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 287:287 */    s.defaultReadObject();
/* 288:288 */    this.key = new byte[this.size];
/* 289:289 */    this.value = new byte[this.size];
/* 290:290 */    for (int i = 0; i < this.size; i++) {
/* 291:291 */      this.key[i] = s.readByte();
/* 292:292 */      this.value[i] = s.readByte();
/* 293:    */    }
/* 294:    */  }
/* 295:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ByteArrayMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */