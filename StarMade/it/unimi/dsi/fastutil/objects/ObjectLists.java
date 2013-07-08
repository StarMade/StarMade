/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Collection;
/*   5:    */import java.util.List;
/*   6:    */import java.util.Random;
/*   7:    */
/*  56:    */public class ObjectLists
/*  57:    */{
/*  58:    */  public static <K> ObjectList<K> shuffle(ObjectList<K> l, Random random)
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
/*  71:    */    extends ObjectCollections.EmptyCollection<K>
/*  72:    */    implements ObjectList<K>, Serializable, Cloneable
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
/* 101:101 */    public ObjectList<K> subList(int from, int to) { if ((from == 0) && (to == 0)) return this; throw new IndexOutOfBoundsException(); }
/* 102:    */    @Deprecated
/* 103:103 */    public ObjectList<K> objectSubList(int from, int to) { return subList(from, to); }
/* 104:104 */    public void getElements(int from, Object[] a, int offset, int length) { if ((from == 0) && (length == 0) && (offset >= 0) && (offset <= a.length)) return; throw new IndexOutOfBoundsException(); }
/* 105:105 */    public void removeElements(int from, int to) { throw new UnsupportedOperationException(); }
/* 106:106 */    public void addElements(int index, K[] a, int offset, int length) { throw new UnsupportedOperationException(); }
/* 107:107 */    public void addElements(int index, K[] a) { throw new UnsupportedOperationException(); }
/* 108:108 */    public void size(int s) { throw new UnsupportedOperationException(); }
/* 109:    */    
/* 110:110 */    public int compareTo(List<? extends K> o) { if (o == this) return 0;
/* 111:111 */      return o.isEmpty() ? 0 : -1; }
/* 112:    */    
/* 113:113 */    private Object readResolve() { return ObjectLists.EMPTY_LIST; }
/* 114:114 */    public Object clone() { return ObjectLists.EMPTY_LIST; }
/* 115:    */  }
/* 116:    */  
/* 123:123 */  public static final EmptyList EMPTY_LIST = new EmptyList();
/* 124:    */  
/* 126:    */  public static class Singleton<K>
/* 127:    */    extends AbstractObjectList<K>
/* 128:    */    implements Serializable, Cloneable
/* 129:    */  {
/* 130:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 131:    */    private final K element;
/* 132:    */    
/* 133:133 */    private Singleton(K element) { this.element = element; }
/* 134:    */    
/* 135:135 */    public K get(int i) { if (i == 0) return this.element; throw new IndexOutOfBoundsException(); }
/* 136:136 */    public K remove(int i) { throw new UnsupportedOperationException(); }
/* 137:137 */    public boolean contains(Object k) { return k == null ? false : this.element == null ? true : k.equals(this.element); }
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
/* 158:    */    public ObjectList<K> subList(int from, int to) {
/* 159:159 */      ensureIndex(from);
/* 160:160 */      ensureIndex(to);
/* 161:161 */      if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 162:162 */      if ((from != 0) || (to != 1)) return ObjectLists.EMPTY_LIST;
/* 163:163 */      return this; }
/* 164:    */    
/* 165:165 */    public int size() { return 1; }
/* 166:166 */    public void size(int size) { throw new UnsupportedOperationException(); }
/* 167:167 */    public void clear() { throw new UnsupportedOperationException(); }
/* 168:168 */    public Object clone() { return this; }
/* 169:169 */    public boolean remove(Object k) { throw new UnsupportedOperationException(); }
/* 170:    */  }
/* 171:    */  
/* 176:176 */  public static <K> ObjectList<K> singleton(K element) { return new Singleton(element, null); }
/* 177:    */  
/* 178:    */  public static class SynchronizedList<K> extends ObjectCollections.SynchronizedCollection<K> implements ObjectList<K>, Serializable {
/* 179:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 180:    */    protected final ObjectList<K> list;
/* 181:    */    
/* 182:182 */    protected SynchronizedList(ObjectList<K> l, Object sync) { super(sync);
/* 183:183 */      this.list = l;
/* 184:    */    }
/* 185:    */    
/* 186:186 */    protected SynchronizedList(ObjectList<K> l) { super();
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
/* 208:208 */    public ObjectList<K> subList(int from, int to) { synchronized (this.sync) { return ObjectLists.synchronize(this.list.subList(from, to), this.sync); } }
/* 209:    */    @Deprecated
/* 210:210 */    public ObjectList<K> objectSubList(int from, int to) { return subList(from, to); }
/* 211:211 */    public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); } }
/* 212:212 */    public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); } }
/* 213:213 */    public int compareTo(List<? extends K> o) { synchronized (this.sync) { return this.list.compareTo(o);
/* 214:    */      }
/* 215:    */    }
/* 216:    */  }
/* 217:    */  
/* 219:    */  public static <K> ObjectList<K> synchronize(ObjectList<K> l)
/* 220:    */  {
/* 221:221 */    return new SynchronizedList(l);
/* 222:    */  }
/* 223:    */  
/* 229:229 */  public static <K> ObjectList<K> synchronize(ObjectList<K> l, Object sync) { return new SynchronizedList(l, sync); }
/* 230:    */  
/* 231:    */  public static class UnmodifiableList<K> extends ObjectCollections.UnmodifiableCollection<K> implements ObjectList<K>, Serializable {
/* 232:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 233:    */    protected final ObjectList<K> list;
/* 234:    */    
/* 235:235 */    protected UnmodifiableList(ObjectList<K> l) { super();
/* 236:236 */      this.list = l; }
/* 237:    */    
/* 238:238 */    public K get(int i) { return this.list.get(i); }
/* 239:239 */    public K set(int i, K k) { throw new UnsupportedOperationException(); }
/* 240:240 */    public void add(int i, K k) { throw new UnsupportedOperationException(); }
/* 241:241 */    public K remove(int i) { throw new UnsupportedOperationException(); }
/* 242:242 */    public int indexOf(Object k) { return this.list.indexOf(k); }
/* 243:243 */    public int lastIndexOf(Object k) { return this.list.lastIndexOf(k); }
/* 244:244 */    public boolean addAll(int index, Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/* 245:245 */    public void getElements(int from, Object[] a, int offset, int length) { this.list.getElements(from, a, offset, length); }
/* 246:246 */    public void removeElements(int from, int to) { throw new UnsupportedOperationException(); }
/* 247:247 */    public void addElements(int index, K[] a, int offset, int length) { throw new UnsupportedOperationException(); }
/* 248:248 */    public void addElements(int index, K[] a) { throw new UnsupportedOperationException(); }
/* 249:249 */    public void size(int size) { this.list.size(size); }
/* 250:250 */    public ObjectListIterator<K> iterator() { return listIterator(); }
/* 251:251 */    public ObjectListIterator<K> listIterator() { return ObjectIterators.unmodifiable(this.list.listIterator()); }
/* 252:252 */    public ObjectListIterator<K> listIterator(int i) { return ObjectIterators.unmodifiable(this.list.listIterator(i)); }
/* 253:    */    @Deprecated
/* 254:254 */    public ObjectListIterator<K> objectListIterator() { return listIterator(); }
/* 255:    */    @Deprecated
/* 256:256 */    public ObjectListIterator<K> objectListIterator(int i) { return listIterator(i); }
/* 257:257 */    public ObjectList<K> subList(int from, int to) { return ObjectLists.unmodifiable(this.list.subList(from, to)); }
/* 258:    */    @Deprecated
/* 259:259 */    public ObjectList<K> objectSubList(int from, int to) { return subList(from, to); }
/* 260:260 */    public boolean equals(Object o) { return this.collection.equals(o); }
/* 261:261 */    public int hashCode() { return this.collection.hashCode(); }
/* 262:262 */    public int compareTo(List<? extends K> o) { return this.list.compareTo(o); }
/* 263:    */  }
/* 264:    */  
/* 268:    */  public static <K> ObjectList<K> unmodifiable(ObjectList<K> l)
/* 269:    */  {
/* 270:270 */    return new UnmodifiableList(l);
/* 271:    */  }
/* 272:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */