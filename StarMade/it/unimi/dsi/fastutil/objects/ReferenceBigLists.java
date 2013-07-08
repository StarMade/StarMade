/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.BigList;
/*   4:    */import java.io.Serializable;
/*   5:    */import java.util.Collection;
/*   6:    */import java.util.Random;
/*   7:    */
/*  58:    */public class ReferenceBigLists
/*  59:    */{
/*  60:    */  public static <K> ReferenceBigList<K> shuffle(ReferenceBigList<K> l, Random random)
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
/*  73:    */    extends ReferenceCollections.EmptyCollection<K>
/*  74:    */    implements ReferenceBigList<K>, Serializable, Cloneable
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
/*  95: 95 */    public ReferenceBigList<K> subList(long from, long to) { if ((from == 0L) && (to == 0L)) return this; throw new IndexOutOfBoundsException(); }
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
/* 106:106 */    private Object readResolve() { return ReferenceBigLists.EMPTY_BIG_LIST; }
/* 107:107 */    public Object clone() { return ReferenceBigLists.EMPTY_BIG_LIST; }
/* 108:    */  }
/* 109:    */  
/* 116:116 */  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
/* 117:    */  
/* 119:    */  public static class Singleton<K>
/* 120:    */    extends AbstractReferenceBigList<K>
/* 121:    */    implements Serializable, Cloneable
/* 122:    */  {
/* 123:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 124:    */    private final K element;
/* 125:    */    
/* 126:126 */    private Singleton(K element) { this.element = element; }
/* 127:    */    
/* 128:128 */    public K get(long i) { if (i == 0L) return this.element; throw new IndexOutOfBoundsException(); }
/* 129:129 */    public K remove(long i) { throw new UnsupportedOperationException(); }
/* 130:130 */    public boolean contains(Object k) { return k == this.element; }
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
/* 151:    */    public ReferenceBigList<K> subList(long from, long to) {
/* 152:152 */      ensureIndex(from);
/* 153:153 */      ensureIndex(to);
/* 154:154 */      if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 155:155 */      if ((from != 0L) || (to != 1L)) return ReferenceBigLists.EMPTY_BIG_LIST;
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
/* 171:171 */  public static <K> ReferenceBigList<K> singleton(K element) { return new Singleton(element, null); }
/* 172:    */  
/* 173:    */  public static class SynchronizedBigList<K> extends ReferenceCollections.SynchronizedCollection<K> implements ReferenceBigList<K>, Serializable {
/* 174:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 175:    */    protected final ReferenceBigList<K> list;
/* 176:    */    
/* 177:177 */    protected SynchronizedBigList(ReferenceBigList<K> l, Object sync) { super(sync);
/* 178:178 */      this.list = l;
/* 179:    */    }
/* 180:    */    
/* 181:181 */    protected SynchronizedBigList(ReferenceBigList<K> l) { super();
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
/* 200:200 */    public ReferenceBigList<K> subList(long from, long to) { synchronized (this.sync) { return ReferenceBigLists.synchronize(this.list.subList(from, to), this.sync); } }
/* 201:201 */    public boolean equals(Object o) { synchronized (this.sync) { return this.list.equals(o); } }
/* 202:202 */    public int hashCode() { synchronized (this.sync) { return this.list.hashCode();
/* 203:    */      }
/* 204:    */    }
/* 205:    */  }
/* 206:    */  
/* 208:    */  public static <K> ReferenceBigList<K> synchronize(ReferenceBigList<K> l)
/* 209:    */  {
/* 210:210 */    return new SynchronizedBigList(l);
/* 211:    */  }
/* 212:    */  
/* 218:218 */  public static <K> ReferenceBigList<K> synchronize(ReferenceBigList<K> l, Object sync) { return new SynchronizedBigList(l, sync); }
/* 219:    */  
/* 220:    */  public static class UnmodifiableBigList<K> extends ReferenceCollections.UnmodifiableCollection<K> implements ReferenceBigList<K>, Serializable {
/* 221:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 222:    */    protected final ReferenceBigList<K> list;
/* 223:    */    
/* 224:224 */    protected UnmodifiableBigList(ReferenceBigList<K> l) { super();
/* 225:225 */      this.list = l; }
/* 226:    */    
/* 227:227 */    public K get(long i) { return this.list.get(i); }
/* 228:228 */    public K set(long i, K k) { throw new UnsupportedOperationException(); }
/* 229:229 */    public void add(long i, K k) { throw new UnsupportedOperationException(); }
/* 230:230 */    public K remove(long i) { throw new UnsupportedOperationException(); }
/* 231:231 */    public long indexOf(Object k) { return this.list.indexOf(k); }
/* 232:232 */    public long lastIndexOf(Object k) { return this.list.lastIndexOf(k); }
/* 233:233 */    public boolean addAll(long index, Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/* 234:234 */    public void getElements(long from, Object[][] a, long offset, long length) { this.list.getElements(from, a, offset, length); }
/* 235:235 */    public void removeElements(long from, long to) { throw new UnsupportedOperationException(); }
/* 236:236 */    public void addElements(long index, K[][] a, long offset, long length) { throw new UnsupportedOperationException(); }
/* 237:237 */    public void addElements(long index, K[][] a) { throw new UnsupportedOperationException(); }
/* 238:238 */    public void size(long size) { this.list.size(size); }
/* 239:239 */    public long size64() { return this.list.size64(); }
/* 240:240 */    public ObjectBigListIterator<K> iterator() { return listIterator(); }
/* 241:241 */    public ObjectBigListIterator<K> listIterator() { return ObjectBigListIterators.unmodifiable(this.list.listIterator()); }
/* 242:242 */    public ObjectBigListIterator<K> listIterator(long i) { return ObjectBigListIterators.unmodifiable(this.list.listIterator(i)); }
/* 243:243 */    public ReferenceBigList<K> subList(long from, long to) { return ReferenceBigLists.unmodifiable(this.list.subList(from, to)); }
/* 244:244 */    public boolean equals(Object o) { return this.list.equals(o); }
/* 245:245 */    public int hashCode() { return this.list.hashCode(); }
/* 246:    */  }
/* 247:    */  
/* 253:253 */  public static <K> ReferenceBigList<K> unmodifiable(ReferenceBigList<K> l) { return new UnmodifiableBigList(l); }
/* 254:    */  
/* 255:    */  public static class ListBigList<K> extends AbstractReferenceBigList<K> implements Serializable {
/* 256:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 257:    */    private final ReferenceList<K> list;
/* 258:    */    
/* 259:259 */    protected ListBigList(ReferenceList<K> list) { this.list = list; }
/* 260:    */    
/* 261:    */    private int intIndex(long index) {
/* 262:262 */      if (index >= 2147483647L) throw new IndexOutOfBoundsException("This big list is restricted to 32-bit indices");
/* 263:263 */      return (int)index; }
/* 264:    */    
/* 265:265 */    public long size64() { return this.list.size(); }
/* 266:    */    @Deprecated
/* 267:267 */    public int size() { return this.list.size(); }
/* 268:268 */    public void size(long size) { this.list.size(intIndex(size)); }
/* 269:269 */    public ObjectBigListIterator<K> iterator() { return ObjectBigListIterators.asBigListIterator(this.list.iterator()); }
/* 270:270 */    public ObjectBigListIterator<K> listIterator() { return ObjectBigListIterators.asBigListIterator(this.list.listIterator()); }
/* 271:271 */    public boolean addAll(long index, Collection<? extends K> c) { return this.list.addAll(intIndex(index), c); }
/* 272:272 */    public ObjectBigListIterator<K> listIterator(long index) { return ObjectBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index))); }
/* 273:273 */    public ReferenceBigList<K> subList(long from, long to) { return new ListBigList(this.list.subList(intIndex(from), intIndex(to))); }
/* 274:274 */    public boolean contains(Object key) { return this.list.contains(key); }
/* 275:275 */    public Object[] toArray() { return this.list.toArray(); }
/* 276:276 */    public void removeElements(long from, long to) { this.list.removeElements(intIndex(from), intIndex(to)); }
/* 277:277 */    public void add(long index, K key) { this.list.add(intIndex(index), key); }
/* 278:278 */    public boolean addAll(long index, ReferenceCollection<K> c) { return this.list.addAll(intIndex(index), c); }
/* 279:279 */    public boolean addAll(long index, ReferenceBigList<K> c) { return this.list.addAll(intIndex(index), c); }
/* 280:280 */    public boolean add(K key) { return this.list.add(key); }
/* 281:281 */    public boolean addAll(ReferenceBigList<K> c) { return this.list.addAll(c); }
/* 282:282 */    public K get(long index) { return this.list.get(intIndex(index)); }
/* 283:283 */    public long indexOf(Object k) { return this.list.indexOf(k); }
/* 284:284 */    public long lastIndexOf(Object k) { return this.list.lastIndexOf(k); }
/* 285:285 */    public K remove(long index) { return this.list.remove(intIndex(index)); }
/* 286:286 */    public K set(long index, K k) { return this.list.set(intIndex(index), k); }
/* 287:287 */    public boolean addAll(ReferenceCollection<K> c) { return this.list.addAll(c); }
/* 288:288 */    public boolean containsAll(ReferenceCollection<K> c) { return this.list.containsAll(c); }
/* 289:289 */    public boolean removeAll(ReferenceCollection<K> c) { return this.list.removeAll(c); }
/* 290:290 */    public boolean retainAll(ReferenceCollection<K> c) { return this.list.retainAll(c); }
/* 291:291 */    public boolean isEmpty() { return this.list.isEmpty(); }
/* 292:292 */    public <T> T[] toArray(T[] a) { return this.list.toArray(a); }
/* 293:293 */    public boolean containsAll(Collection<?> c) { return this.list.containsAll(c); }
/* 294:294 */    public boolean addAll(Collection<? extends K> c) { return this.list.addAll(c); }
/* 295:295 */    public boolean removeAll(Collection<?> c) { return this.list.removeAll(c); }
/* 296:296 */    public boolean retainAll(Collection<?> c) { return this.list.retainAll(c); }
/* 297:297 */    public void clear() { this.list.clear(); }
/* 298:298 */    public int hashCode() { return this.list.hashCode(); }
/* 299:    */  }
/* 300:    */  
/* 303:    */  public static <K> ReferenceBigList<K> asBigList(ReferenceList<K> list)
/* 304:    */  {
/* 305:305 */    return new ListBigList(list);
/* 306:    */  }
/* 307:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */