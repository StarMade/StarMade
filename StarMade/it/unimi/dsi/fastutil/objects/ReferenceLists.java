/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Collection;
/*   5:    */import java.util.List;
/*   6:    */import java.util.Random;
/*   7:    */
/*  56:    */public class ReferenceLists
/*  57:    */{
/*  58:    */  public static <K> ReferenceList<K> shuffle(ReferenceList<K> l, Random random)
/*  59:    */  {
/*  60: 60 */    for (int i = l.size(); i-- != 0;) {
/*  61: 61 */      int p = random.nextInt(i + 1);
/*  62: 62 */      K t = l.get(i);
/*  63: 63 */      l.set(i, l.get(p));
/*  64: 64 */      l.set(p, t);
/*  65:    */    }
/*  66: 66 */    return l;
/*  67:    */  }
/*  68:    */  
/*  70:    */  public static class EmptyList<K>
/*  71:    */    extends ReferenceCollections.EmptyCollection<K>
/*  72:    */    implements ReferenceList<K>, Serializable, Cloneable
/*  73:    */  {
/*  74:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  75:    */    
/*  76: 76 */    public void add(int index, K k) { throw new UnsupportedOperationException(); }
/*  77: 77 */    public boolean add(K k) { throw new UnsupportedOperationException(); }
/*  78: 78 */    public K remove(int i) { throw new UnsupportedOperationException(); }
/*  79: 79 */    public K set(int index, K k) { throw new UnsupportedOperationException(); }
/*  80: 80 */    public int indexOf(Object k) { return -1; }
/*  81: 81 */    public int lastIndexOf(Object k) { return -1; }
/*  82: 82 */    public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/*  83: 83 */    public boolean addAll(int i, Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/*  84: 84 */    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*  85: 85 */    public K get(int i) { throw new IndexOutOfBoundsException(); }
/*  86:    */    
/*  89:    */    @Deprecated
/*  90: 90 */    public ObjectIterator<K> objectIterator() { return ObjectIterators.EMPTY_ITERATOR; }
/*  91:    */    
/*  92: 92 */    public ObjectListIterator<K> listIterator() { return ObjectIterators.EMPTY_ITERATOR; }
/*  93:    */    
/*  94: 94 */    public ObjectListIterator<K> iterator() { return ObjectIterators.EMPTY_ITERATOR; }
/*  95:    */    
/*  96: 96 */    public ObjectListIterator<K> listIterator(int i) { if (i == 0) return ObjectIterators.EMPTY_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i)); }
/*  97:    */    @Deprecated
/*  98: 98 */    public ObjectListIterator<K> objectListIterator() { return listIterator(); }
/*  99:    */    @Deprecated
/* 100:100 */    public ObjectListIterator<K> objectListIterator(int i) { return listIterator(i); }
/* 101:101 */    public ReferenceList<K> subList(int from, int to) { if ((from == 0) && (to == 0)) return this; throw new IndexOutOfBoundsException(); }
/* 102:    */    @Deprecated
/* 103:103 */    public ReferenceList<K> referenceSubList(int from, int to) { return subList(from, to); }
/* 104:104 */    public void getElements(int from, Object[] a, int offset, int length) { if ((from == 0) && (length == 0) && (offset >= 0) && (offset <= a.length)) return; throw new IndexOutOfBoundsException(); }
/* 105:105 */    public void removeElements(int from, int to) { throw new UnsupportedOperationException(); }
/* 106:106 */    public void addElements(int index, K[] a, int offset, int length) { throw new UnsupportedOperationException(); }
/* 107:107 */    public void addElements(int index, K[] a) { throw new UnsupportedOperationException(); }
/* 108:108 */    public void size(int s) { throw new UnsupportedOperationException(); }
/* 109:    */    
/* 110:110 */    public int compareTo(List<? extends K> o) { if (o == this) return 0;
/* 111:111 */      return o.isEmpty() ? 0 : -1; }
/* 112:    */    
/* 113:113 */    private Object readResolve() { return ReferenceLists.EMPTY_LIST; }
/* 114:114 */    public Object clone() { return ReferenceLists.EMPTY_LIST; }
/* 115:    */  }
/* 116:    */  
/* 123:123 */  public static final EmptyList EMPTY_LIST = new EmptyList();
/* 124:    */  
/* 126:    */  public static class Singleton<K>
/* 127:    */    extends AbstractReferenceList<K>
/* 128:    */    implements Serializable, Cloneable
/* 129:    */  {
/* 130:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 131:    */    private final K element;
/* 132:    */    
/* 133:133 */    private Singleton(K element) { this.element = element; }
/* 134:    */    
/* 135:135 */    public K get(int i) { if (i == 0) return this.element; throw new IndexOutOfBoundsException(); }
/* 136:136 */    public K remove(int i) { throw new UnsupportedOperationException(); }
/* 137:137 */    public boolean contains(Object k) { return k == this.element; }
/* 138:138 */    public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/* 139:139 */    public boolean addAll(int i, Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/* 140:140 */    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/* 141:141 */    public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/* 142:    */    
/* 143:    */    public Object[] toArray() {
/* 144:144 */      Object[] a = new Object[1];
/* 145:145 */      a[0] = this.element;
/* 146:146 */      return a;
/* 147:    */    }
/* 148:    */    
/* 149:149 */    public ObjectListIterator<K> listIterator() { return ObjectIterators.singleton(this.element); }
/* 150:150 */    public ObjectListIterator<K> iterator() { return listIterator(); }
/* 151:    */    
/* 152:152 */    public ObjectListIterator<K> listIterator(int i) { if ((i > 1) || (i < 0)) throw new IndexOutOfBoundsException();
/* 153:153 */      ObjectListIterator<K> l = listIterator();
/* 154:154 */      if (i == 1) l.next();
/* 155:155 */      return l;
/* 156:    */    }
/* 157:    */    
/* 158:    */    public ReferenceList<K> subList(int from, int to) {
/* 159:159 */      ensureIndex(from);
/* 160:160 */      ensureIndex(to);
/* 161:161 */      if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 162:162 */      if ((from != 0) || (to != 1)) return ReferenceLists.EMPTY_LIST;
/* 163:163 */      return this; }
/* 164:    */    
/* 165:165 */    public int size() { return 1; }
/* 166:166 */    public void size(int size) { throw new UnsupportedOperationException(); }
/* 167:167 */    public void clear() { throw new UnsupportedOperationException(); }
/* 168:168 */    public Object clone() { return this; }
/* 169:169 */    public boolean remove(Object k) { throw new UnsupportedOperationException(); }
/* 170:    */  }
/* 171:    */  
/* 176:176 */  public static <K> ReferenceList<K> singleton(K element) { return new Singleton(element, null); }
/* 177:    */  
/* 178:    */  public static class SynchronizedList<K> extends ReferenceCollections.SynchronizedCollection<K> implements ReferenceList<K>, Serializable {
/* 179:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 180:    */    protected final ReferenceList<K> list;
/* 181:    */    
/* 182:182 */    protected SynchronizedList(ReferenceList<K> l, Object sync) { super(sync);
/* 183:183 */      this.list = l;
/* 184:    */    }
/* 185:    */    
/* 186:186 */    protected SynchronizedList(ReferenceList<K> l) { super();
/* 187:187 */      this.list = l; }
/* 188:    */    
/* 189:189 */    public K get(int i) { synchronized (this.sync) { return this.list.get(i); } }
/* 190:190 */    public K set(int i, K k) { synchronized (this.sync) { return this.list.set(i, k); } }
/* 191:191 */    public void add(int i, K k) { synchronized (this.sync) { this.list.add(i, k); } }
/* 192:192 */    public K remove(int i) { synchronized (this.sync) { return this.list.remove(i); } }
/* 193:193 */    public int indexOf(Object k) { synchronized (this.sync) { return this.list.indexOf(k); } }
/* 194:194 */    public int lastIndexOf(Object k) { synchronized (this.sync) { return this.list.lastIndexOf(k); } }
/* 195:195 */    public boolean addAll(int index, Collection<? extends K> c) { synchronized (this.sync) { return this.list.addAll(index, c); } }
/* 196:196 */    public void getElements(int from, Object[] a, int offset, int length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); } }
/* 197:197 */    public void removeElements(int from, int to) { synchronized (this.sync) { this.list.removeElements(from, to); } }
/* 198:198 */    public void addElements(int index, K[] a, int offset, int length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); } }
/* 199:199 */    public void addElements(int index, K[] a) { synchronized (this.sync) { this.list.addElements(index, a); } }
/* 200:200 */    public void size(int size) { synchronized (this.sync) { this.list.size(size); } }
/* 201:201 */    public ObjectListIterator<K> iterator() { return this.list.listIterator(); }
/* 202:202 */    public ObjectListIterator<K> listIterator() { return this.list.listIterator(); }
/* 203:203 */    public ObjectListIterator<K> listIterator(int i) { return this.list.listIterator(i); }
/* 204:    */    @Deprecated
/* 205:205 */    public ObjectListIterator<K> objectListIterator() { return listIterator(); }
/* 206:    */    @Deprecated
/* 207:207 */    public ObjectListIterator<K> objectListIterator(int i) { return listIterator(i); }
/* 208:208 */    public ReferenceList<K> subList(int from, int to) { synchronized (this.sync) { return ReferenceLists.synchronize(this.list.subList(from, to), this.sync); } }
/* 209:    */    @Deprecated
/* 210:210 */    public ReferenceList<K> referenceSubList(int from, int to) { return subList(from, to); }
/* 211:211 */    public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); } }
/* 212:212 */    public int hashCode() { synchronized (this.sync) { return this.collection.hashCode();
/* 213:    */      }
/* 214:    */    }
/* 215:    */  }
/* 216:    */  
/* 218:    */  public static <K> ReferenceList<K> synchronize(ReferenceList<K> l)
/* 219:    */  {
/* 220:220 */    return new SynchronizedList(l);
/* 221:    */  }
/* 222:    */  
/* 228:228 */  public static <K> ReferenceList<K> synchronize(ReferenceList<K> l, Object sync) { return new SynchronizedList(l, sync); }
/* 229:    */  
/* 230:    */  public static class UnmodifiableList<K> extends ReferenceCollections.UnmodifiableCollection<K> implements ReferenceList<K>, Serializable {
/* 231:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 232:    */    protected final ReferenceList<K> list;
/* 233:    */    
/* 234:234 */    protected UnmodifiableList(ReferenceList<K> l) { super();
/* 235:235 */      this.list = l; }
/* 236:    */    
/* 237:237 */    public K get(int i) { return this.list.get(i); }
/* 238:238 */    public K set(int i, K k) { throw new UnsupportedOperationException(); }
/* 239:239 */    public void add(int i, K k) { throw new UnsupportedOperationException(); }
/* 240:240 */    public K remove(int i) { throw new UnsupportedOperationException(); }
/* 241:241 */    public int indexOf(Object k) { return this.list.indexOf(k); }
/* 242:242 */    public int lastIndexOf(Object k) { return this.list.lastIndexOf(k); }
/* 243:243 */    public boolean addAll(int index, Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/* 244:244 */    public void getElements(int from, Object[] a, int offset, int length) { this.list.getElements(from, a, offset, length); }
/* 245:245 */    public void removeElements(int from, int to) { throw new UnsupportedOperationException(); }
/* 246:246 */    public void addElements(int index, K[] a, int offset, int length) { throw new UnsupportedOperationException(); }
/* 247:247 */    public void addElements(int index, K[] a) { throw new UnsupportedOperationException(); }
/* 248:248 */    public void size(int size) { this.list.size(size); }
/* 249:249 */    public ObjectListIterator<K> iterator() { return listIterator(); }
/* 250:250 */    public ObjectListIterator<K> listIterator() { return ObjectIterators.unmodifiable(this.list.listIterator()); }
/* 251:251 */    public ObjectListIterator<K> listIterator(int i) { return ObjectIterators.unmodifiable(this.list.listIterator(i)); }
/* 252:    */    @Deprecated
/* 253:253 */    public ObjectListIterator<K> objectListIterator() { return listIterator(); }
/* 254:    */    @Deprecated
/* 255:255 */    public ObjectListIterator<K> objectListIterator(int i) { return listIterator(i); }
/* 256:256 */    public ReferenceList<K> subList(int from, int to) { return ReferenceLists.unmodifiable(this.list.subList(from, to)); }
/* 257:    */    @Deprecated
/* 258:258 */    public ReferenceList<K> referenceSubList(int from, int to) { return subList(from, to); }
/* 259:259 */    public boolean equals(Object o) { return this.collection.equals(o); }
/* 260:260 */    public int hashCode() { return this.collection.hashCode(); }
/* 261:    */  }
/* 262:    */  
/* 266:    */  public static <K> ReferenceList<K> unmodifiable(ReferenceList<K> l)
/* 267:    */  {
/* 268:268 */    return new UnmodifiableList(l);
/* 269:    */  }
/* 270:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */