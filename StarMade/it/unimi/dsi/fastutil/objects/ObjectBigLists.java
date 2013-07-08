/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.BigList;
/*   4:    */import java.io.Serializable;
/*   5:    */import java.util.Collection;
/*   6:    */import java.util.Random;
/*   7:    */
/*  58:    */public class ObjectBigLists
/*  59:    */{
/*  60:    */  public static <K> ObjectBigList<K> shuffle(ObjectBigList<K> l, Random random)
/*  61:    */  {
/*  62: 62 */    for (long i = l.size64(); i-- != 0L;) {
/*  63: 63 */      long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/*  64: 64 */      K t = l.get(i);
/*  65: 65 */      l.set(i, l.get(p));
/*  66: 66 */      l.set(p, t);
/*  67:    */    }
/*  68: 68 */    return l;
/*  69:    */  }
/*  70:    */  
/*  72:    */  public static class EmptyBigList<K>
/*  73:    */    extends ObjectCollections.EmptyCollection<K>
/*  74:    */    implements ObjectBigList<K>, Serializable, Cloneable
/*  75:    */  {
/*  76:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  77:    */    
/*  78: 78 */    public void add(long index, K k) { throw new UnsupportedOperationException(); }
/*  79: 79 */    public boolean add(K k) { throw new UnsupportedOperationException(); }
/*  80: 80 */    public K remove(long i) { throw new UnsupportedOperationException(); }
/*  81: 81 */    public K set(long index, K k) { throw new UnsupportedOperationException(); }
/*  82: 82 */    public long indexOf(Object k) { return -1L; }
/*  83: 83 */    public long lastIndexOf(Object k) { return -1L; }
/*  84: 84 */    public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/*  85: 85 */    public boolean addAll(long i, Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/*  86: 86 */    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*  87: 87 */    public K get(long i) { throw new IndexOutOfBoundsException(); }
/*  88: 88 */    public boolean remove(Object k) { throw new UnsupportedOperationException(); }
/*  89:    */    
/*  90: 90 */    public ObjectBigListIterator<K> listIterator() { return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/*  91:    */    
/*  92: 92 */    public ObjectBigListIterator<K> iterator() { return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/*  93:    */    
/*  94: 94 */    public ObjectBigListIterator<K> listIterator(long i) { if (i == 0L) return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i)); }
/*  95: 95 */    public ObjectBigList<K> subList(long from, long to) { if ((from == 0L) && (to == 0L)) return this; throw new IndexOutOfBoundsException(); }
/*  96: 96 */    public void getElements(long from, Object[][] a, long offset, long length) { ObjectBigArrays.ensureOffsetLength(a, offset, length); if (from != 0L) throw new IndexOutOfBoundsException(); }
/*  97: 97 */    public void removeElements(long from, long to) { throw new UnsupportedOperationException(); }
/*  98: 98 */    public void addElements(long index, K[][] a, long offset, long length) { throw new UnsupportedOperationException(); }
/*  99: 99 */    public void addElements(long index, K[][] a) { throw new UnsupportedOperationException(); }
/* 100:100 */    public void size(long s) { throw new UnsupportedOperationException(); }
/* 101:101 */    public long size64() { return 0L; }
/* 102:    */    
/* 103:103 */    public int compareTo(BigList<? extends K> o) { if (o == this) return 0;
/* 104:104 */      return o.isEmpty() ? 0 : -1; }
/* 105:    */    
/* 106:106 */    private Object readResolve() { return ObjectBigLists.EMPTY_BIG_LIST; }
/* 107:107 */    public Object clone() { return ObjectBigLists.EMPTY_BIG_LIST; }
/* 108:    */  }
/* 109:    */  
/* 116:116 */  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
/* 117:    */  
/* 119:    */  public static class Singleton<K>
/* 120:    */    extends AbstractObjectBigList<K>
/* 121:    */    implements Serializable, Cloneable
/* 122:    */  {
/* 123:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 124:    */    private final K element;
/* 125:    */    
/* 126:126 */    private Singleton(K element) { this.element = element; }
/* 127:    */    
/* 128:128 */    public K get(long i) { if (i == 0L) return this.element; throw new IndexOutOfBoundsException(); }
/* 129:129 */    public K remove(long i) { throw new UnsupportedOperationException(); }
/* 130:130 */    public boolean contains(Object k) { return k == null ? false : this.element == null ? true : k.equals(this.element); }
/* 131:131 */    public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/* 132:132 */    public boolean addAll(long i, Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/* 133:133 */    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/* 134:134 */    public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/* 135:    */    
/* 136:    */    public Object[] toArray() {
/* 137:137 */      Object[] a = new Object[1];
/* 138:138 */      a[0] = this.element;
/* 139:139 */      return a;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    public ObjectBigListIterator<K> listIterator() { return ObjectBigListIterators.singleton(this.element); }
/* 143:143 */    public ObjectBigListIterator<K> iterator() { return listIterator(); }
/* 144:    */    
/* 145:145 */    public ObjectBigListIterator<K> listIterator(long i) { if ((i > 1L) || (i < 0L)) throw new IndexOutOfBoundsException();
/* 146:146 */      ObjectBigListIterator<K> l = listIterator();
/* 147:147 */      if (i == 1L) l.next();
/* 148:148 */      return l;
/* 149:    */    }
/* 150:    */    
/* 151:    */    public ObjectBigList<K> subList(long from, long to) {
/* 152:152 */      ensureIndex(from);
/* 153:153 */      ensureIndex(to);
/* 154:154 */      if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 155:155 */      if ((from != 0L) || (to != 1L)) return ObjectBigLists.EMPTY_BIG_LIST;
/* 156:156 */      return this; }
/* 157:    */    
/* 158:    */    @Deprecated
/* 159:159 */    public int size() { return 1; }
/* 160:160 */    public long size64() { return 1L; }
/* 161:161 */    public void size(long size) { throw new UnsupportedOperationException(); }
/* 162:162 */    public void clear() { throw new UnsupportedOperationException(); }
/* 163:163 */    public Object clone() { return this; }
/* 164:164 */    public boolean remove(Object k) { throw new UnsupportedOperationException(); }
/* 165:    */  }
/* 166:    */  
/* 171:171 */  public static <K> ObjectBigList<K> singleton(K element) { return new Singleton(element, null); }
/* 172:    */  
/* 173:    */  public static class SynchronizedBigList<K> extends ObjectCollections.SynchronizedCollection<K> implements ObjectBigList<K>, Serializable {
/* 174:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 175:    */    protected final ObjectBigList<K> list;
/* 176:    */    
/* 177:177 */    protected SynchronizedBigList(ObjectBigList<K> l, Object sync) { super(sync);
/* 178:178 */      this.list = l;
/* 179:    */    }
/* 180:    */    
/* 181:181 */    protected SynchronizedBigList(ObjectBigList<K> l) { super();
/* 182:182 */      this.list = l; }
/* 183:    */    
/* 184:184 */    public K get(long i) { synchronized (this.sync) { return this.list.get(i); } }
/* 185:185 */    public K set(long i, K k) { synchronized (this.sync) { return this.list.set(i, k); } }
/* 186:186 */    public void add(long i, K k) { synchronized (this.sync) { this.list.add(i, k); } }
/* 187:187 */    public K remove(long i) { synchronized (this.sync) { return this.list.remove(i); } }
/* 188:188 */    public long indexOf(Object k) { synchronized (this.sync) { return this.list.indexOf(k); } }
/* 189:189 */    public long lastIndexOf(Object k) { synchronized (this.sync) { return this.list.lastIndexOf(k); } }
/* 190:190 */    public boolean addAll(long index, Collection<? extends K> c) { synchronized (this.sync) { return this.list.addAll(index, c); } }
/* 191:191 */    public void getElements(long from, Object[][] a, long offset, long length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); } }
/* 192:192 */    public void removeElements(long from, long to) { synchronized (this.sync) { this.list.removeElements(from, to); } }
/* 193:193 */    public void addElements(long index, K[][] a, long offset, long length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); } }
/* 194:194 */    public void addElements(long index, K[][] a) { synchronized (this.sync) { this.list.addElements(index, a); } }
/* 195:195 */    public void size(long size) { synchronized (this.sync) { this.list.size(size); } }
/* 196:196 */    public long size64() { synchronized (this.sync) { return this.list.size64(); } }
/* 197:197 */    public ObjectBigListIterator<K> iterator() { return this.list.listIterator(); }
/* 198:198 */    public ObjectBigListIterator<K> listIterator() { return this.list.listIterator(); }
/* 199:199 */    public ObjectBigListIterator<K> listIterator(long i) { return this.list.listIterator(i); }
/* 200:200 */    public ObjectBigList<K> subList(long from, long to) { synchronized (this.sync) { return ObjectBigLists.synchronize(this.list.subList(from, to), this.sync); } }
/* 201:201 */    public boolean equals(Object o) { synchronized (this.sync) { return this.list.equals(o); } }
/* 202:202 */    public int hashCode() { synchronized (this.sync) { return this.list.hashCode(); } }
/* 203:203 */    public int compareTo(BigList<? extends K> o) { synchronized (this.sync) { return this.list.compareTo(o);
/* 204:    */      }
/* 205:    */    }
/* 206:    */  }
/* 207:    */  
/* 209:    */  public static <K> ObjectBigList<K> synchronize(ObjectBigList<K> l)
/* 210:    */  {
/* 211:211 */    return new SynchronizedBigList(l);
/* 212:    */  }
/* 213:    */  
/* 219:219 */  public static <K> ObjectBigList<K> synchronize(ObjectBigList<K> l, Object sync) { return new SynchronizedBigList(l, sync); }
/* 220:    */  
/* 221:    */  public static class UnmodifiableBigList<K> extends ObjectCollections.UnmodifiableCollection<K> implements ObjectBigList<K>, Serializable {
/* 222:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 223:    */    protected final ObjectBigList<K> list;
/* 224:    */    
/* 225:225 */    protected UnmodifiableBigList(ObjectBigList<K> l) { super();
/* 226:226 */      this.list = l; }
/* 227:    */    
/* 228:228 */    public K get(long i) { return this.list.get(i); }
/* 229:229 */    public K set(long i, K k) { throw new UnsupportedOperationException(); }
/* 230:230 */    public void add(long i, K k) { throw new UnsupportedOperationException(); }
/* 231:231 */    public K remove(long i) { throw new UnsupportedOperationException(); }
/* 232:232 */    public long indexOf(Object k) { return this.list.indexOf(k); }
/* 233:233 */    public long lastIndexOf(Object k) { return this.list.lastIndexOf(k); }
/* 234:234 */    public boolean addAll(long index, Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/* 235:235 */    public void getElements(long from, Object[][] a, long offset, long length) { this.list.getElements(from, a, offset, length); }
/* 236:236 */    public void removeElements(long from, long to) { throw new UnsupportedOperationException(); }
/* 237:237 */    public void addElements(long index, K[][] a, long offset, long length) { throw new UnsupportedOperationException(); }
/* 238:238 */    public void addElements(long index, K[][] a) { throw new UnsupportedOperationException(); }
/* 239:239 */    public void size(long size) { this.list.size(size); }
/* 240:240 */    public long size64() { return this.list.size64(); }
/* 241:241 */    public ObjectBigListIterator<K> iterator() { return listIterator(); }
/* 242:242 */    public ObjectBigListIterator<K> listIterator() { return ObjectBigListIterators.unmodifiable(this.list.listIterator()); }
/* 243:243 */    public ObjectBigListIterator<K> listIterator(long i) { return ObjectBigListIterators.unmodifiable(this.list.listIterator(i)); }
/* 244:244 */    public ObjectBigList<K> subList(long from, long to) { return ObjectBigLists.unmodifiable(this.list.subList(from, to)); }
/* 245:245 */    public boolean equals(Object o) { return this.list.equals(o); }
/* 246:246 */    public int hashCode() { return this.list.hashCode(); }
/* 247:247 */    public int compareTo(BigList<? extends K> o) { return this.list.compareTo(o); }
/* 248:    */  }
/* 249:    */  
/* 255:255 */  public static <K> ObjectBigList<K> unmodifiable(ObjectBigList<K> l) { return new UnmodifiableBigList(l); }
/* 256:    */  
/* 257:    */  public static class ListBigList<K> extends AbstractObjectBigList<K> implements Serializable {
/* 258:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 259:    */    private final ObjectList<K> list;
/* 260:    */    
/* 261:261 */    protected ListBigList(ObjectList<K> list) { this.list = list; }
/* 262:    */    
/* 263:    */    private int intIndex(long index) {
/* 264:264 */      if (index >= 2147483647L) throw new IndexOutOfBoundsException("This big list is restricted to 32-bit indices");
/* 265:265 */      return (int)index; }
/* 266:    */    
/* 267:267 */    public long size64() { return this.list.size(); }
/* 268:    */    @Deprecated
/* 269:269 */    public int size() { return this.list.size(); }
/* 270:270 */    public void size(long size) { this.list.size(intIndex(size)); }
/* 271:271 */    public ObjectBigListIterator<K> iterator() { return ObjectBigListIterators.asBigListIterator(this.list.iterator()); }
/* 272:272 */    public ObjectBigListIterator<K> listIterator() { return ObjectBigListIterators.asBigListIterator(this.list.listIterator()); }
/* 273:273 */    public boolean addAll(long index, Collection<? extends K> c) { return this.list.addAll(intIndex(index), c); }
/* 274:274 */    public ObjectBigListIterator<K> listIterator(long index) { return ObjectBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index))); }
/* 275:275 */    public ObjectBigList<K> subList(long from, long to) { return new ListBigList(this.list.subList(intIndex(from), intIndex(to))); }
/* 276:276 */    public boolean contains(Object key) { return this.list.contains(key); }
/* 277:277 */    public Object[] toArray() { return this.list.toArray(); }
/* 278:278 */    public void removeElements(long from, long to) { this.list.removeElements(intIndex(from), intIndex(to)); }
/* 279:279 */    public void add(long index, K key) { this.list.add(intIndex(index), key); }
/* 280:280 */    public boolean addAll(long index, ObjectCollection<K> c) { return this.list.addAll(intIndex(index), c); }
/* 281:281 */    public boolean addAll(long index, ObjectBigList<K> c) { return this.list.addAll(intIndex(index), c); }
/* 282:282 */    public boolean add(K key) { return this.list.add(key); }
/* 283:283 */    public boolean addAll(ObjectBigList<K> c) { return this.list.addAll(c); }
/* 284:284 */    public K get(long index) { return this.list.get(intIndex(index)); }
/* 285:285 */    public long indexOf(Object k) { return this.list.indexOf(k); }
/* 286:286 */    public long lastIndexOf(Object k) { return this.list.lastIndexOf(k); }
/* 287:287 */    public K remove(long index) { return this.list.remove(intIndex(index)); }
/* 288:288 */    public K set(long index, K k) { return this.list.set(intIndex(index), k); }
/* 289:289 */    public boolean addAll(ObjectCollection<K> c) { return this.list.addAll(c); }
/* 290:290 */    public boolean containsAll(ObjectCollection<K> c) { return this.list.containsAll(c); }
/* 291:291 */    public boolean removeAll(ObjectCollection<K> c) { return this.list.removeAll(c); }
/* 292:292 */    public boolean retainAll(ObjectCollection<K> c) { return this.list.retainAll(c); }
/* 293:293 */    public boolean isEmpty() { return this.list.isEmpty(); }
/* 294:294 */    public <T> T[] toArray(T[] a) { return this.list.toArray(a); }
/* 295:295 */    public boolean containsAll(Collection<?> c) { return this.list.containsAll(c); }
/* 296:296 */    public boolean addAll(Collection<? extends K> c) { return this.list.addAll(c); }
/* 297:297 */    public boolean removeAll(Collection<?> c) { return this.list.removeAll(c); }
/* 298:298 */    public boolean retainAll(Collection<?> c) { return this.list.retainAll(c); }
/* 299:299 */    public void clear() { this.list.clear(); }
/* 300:300 */    public int hashCode() { return this.list.hashCode(); }
/* 301:    */  }
/* 302:    */  
/* 305:    */  public static <K> ObjectBigList<K> asBigList(ObjectList<K> list)
/* 306:    */  {
/* 307:307 */    return new ListBigList(list);
/* 308:    */  }
/* 309:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */