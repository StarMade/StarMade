/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Comparator;
/*   5:    */import java.util.NoSuchElementException;
/*   6:    */
/*  53:    */public class ObjectSortedSets
/*  54:    */{
/*  55:    */  public static class EmptySet<K>
/*  56:    */    extends ObjectSets.EmptySet<K>
/*  57:    */    implements ObjectSortedSet<K>, Serializable, Cloneable
/*  58:    */  {
/*  59:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  60:    */    
/*  61: 61 */    public boolean remove(Object ok) { throw new UnsupportedOperationException(); }
/*  62:    */    @Deprecated
/*  63: 63 */    public ObjectBidirectionalIterator<K> objectIterator() { return iterator(); }
/*  64:    */    
/*  65: 65 */    public ObjectBidirectionalIterator<K> iterator(K from) { return ObjectIterators.EMPTY_ITERATOR; }
/*  66:    */    
/*  67: 67 */    public ObjectSortedSet<K> subSet(K from, K to) { return ObjectSortedSets.EMPTY_SET; }
/*  68:    */    
/*  69: 69 */    public ObjectSortedSet<K> headSet(K from) { return ObjectSortedSets.EMPTY_SET; }
/*  70:    */    
/*  71: 71 */    public ObjectSortedSet<K> tailSet(K to) { return ObjectSortedSets.EMPTY_SET; }
/*  72: 72 */    public K first() { throw new NoSuchElementException(); }
/*  73: 73 */    public K last() { throw new NoSuchElementException(); }
/*  74: 74 */    public Comparator<? super K> comparator() { return null; }
/*  75:    */    
/*  80: 80 */    public Object clone() { return ObjectSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    private Object readResolve() { return ObjectSortedSets.EMPTY_SET; }
/*  83:    */  }
/*  84:    */  
/*  93: 93 */  public static final EmptySet EMPTY_SET = new EmptySet();
/*  94:    */  
/*  97:    */  public static class Singleton<K>
/*  98:    */    extends ObjectSets.Singleton<K>
/*  99:    */    implements ObjectSortedSet<K>, Serializable, Cloneable
/* 100:    */  {
/* 101:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 102:    */    
/* 103:    */    final Comparator<? super K> comparator;
/* 104:    */    
/* 106:    */    private Singleton(K element, Comparator<? super K> comparator)
/* 107:    */    {
/* 108:108 */      super();
/* 109:109 */      this.comparator = comparator;
/* 110:    */    }
/* 111:    */    
/* 112:    */    private Singleton(K element) {
/* 113:113 */      this(element, null);
/* 114:    */    }
/* 115:    */    
/* 116:    */    final int compare(K k1, K k2)
/* 117:    */    {
/* 118:118 */      return this.comparator == null ? ((Comparable)k1).compareTo(k2) : this.comparator.compare(k1, k2);
/* 119:    */    }
/* 120:    */    
/* 121:    */    @Deprecated
/* 122:    */    public ObjectBidirectionalIterator<K> objectIterator() {
/* 123:123 */      return iterator();
/* 124:    */    }
/* 125:    */    
/* 126:    */    public ObjectBidirectionalIterator<K> iterator(K from) {
/* 127:127 */      ObjectBidirectionalIterator<K> i = iterator();
/* 128:128 */      if (compare(this.element, from) <= 0) i.next();
/* 129:129 */      return i;
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public Comparator<? super K> comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public ObjectSortedSet<K> subSet(K from, K to) {
/* 135:135 */      if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return ObjectSortedSets.EMPTY_SET;
/* 136:    */    }
/* 137:    */    
/* 138:138 */    public ObjectSortedSet<K> headSet(K to) { if (compare(this.element, to) < 0) return this; return ObjectSortedSets.EMPTY_SET;
/* 139:    */    }
/* 140:    */    
/* 141:141 */    public ObjectSortedSet<K> tailSet(K from) { if (compare(from, this.element) <= 0) return this; return ObjectSortedSets.EMPTY_SET; }
/* 142:    */    
/* 143:143 */    public K first() { return this.element; }
/* 144:144 */    public K last() { return this.element; }
/* 145:    */  }
/* 146:    */  
/* 150:    */  public static <K> ObjectSortedSet<K> singleton(K element)
/* 151:    */  {
/* 152:152 */    return new Singleton(element, null);
/* 153:    */  }
/* 154:    */  
/* 161:161 */  public static <K> ObjectSortedSet<K> singleton(K element, Comparator<? super K> comparator) { return new Singleton(element, comparator, null); }
/* 162:    */  
/* 163:    */  public static class SynchronizedSortedSet<K> extends ObjectSets.SynchronizedSet<K> implements ObjectSortedSet<K>, Serializable {
/* 164:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 165:    */    protected final ObjectSortedSet<K> sortedSet;
/* 166:    */    
/* 167:    */    protected SynchronizedSortedSet(ObjectSortedSet<K> s, Object sync) {
/* 168:168 */      super(sync);
/* 169:169 */      this.sortedSet = s;
/* 170:    */    }
/* 171:    */    
/* 172:172 */    protected SynchronizedSortedSet(ObjectSortedSet<K> s) { super();
/* 173:173 */      this.sortedSet = s; }
/* 174:    */    
/* 175:175 */    public Comparator<? super K> comparator() { synchronized (this.sync) { return this.sortedSet.comparator(); } }
/* 176:176 */    public ObjectSortedSet<K> subSet(K from, K to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 177:177 */    public ObjectSortedSet<K> headSet(K to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 178:178 */    public ObjectSortedSet<K> tailSet(K from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 179:179 */    public ObjectBidirectionalIterator<K> iterator() { return this.sortedSet.iterator(); }
/* 180:180 */    public ObjectBidirectionalIterator<K> iterator(K from) { return this.sortedSet.iterator(from); }
/* 181:    */    @Deprecated
/* 182:182 */    public ObjectBidirectionalIterator<K> objectIterator() { return this.sortedSet.iterator(); }
/* 183:183 */    public K first() { synchronized (this.sync) { return this.sortedSet.first(); } }
/* 184:184 */    public K last() { synchronized (this.sync) { return this.sortedSet.last();
/* 185:    */      }
/* 186:    */    }
/* 187:    */  }
/* 188:    */  
/* 190:    */  public static <K> ObjectSortedSet<K> synchronize(ObjectSortedSet<K> s)
/* 191:    */  {
/* 192:192 */    return new SynchronizedSortedSet(s);
/* 193:    */  }
/* 194:    */  
/* 200:200 */  public static <K> ObjectSortedSet<K> synchronize(ObjectSortedSet<K> s, Object sync) { return new SynchronizedSortedSet(s, sync); }
/* 201:    */  
/* 202:    */  public static class UnmodifiableSortedSet<K> extends ObjectSets.UnmodifiableSet<K> implements ObjectSortedSet<K>, Serializable {
/* 203:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 204:    */    protected final ObjectSortedSet<K> sortedSet;
/* 205:    */    
/* 206:206 */    protected UnmodifiableSortedSet(ObjectSortedSet<K> s) { super();
/* 207:207 */      this.sortedSet = s; }
/* 208:    */    
/* 209:209 */    public Comparator<? super K> comparator() { return this.sortedSet.comparator(); }
/* 210:210 */    public ObjectSortedSet<K> subSet(K from, K to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 211:211 */    public ObjectSortedSet<K> headSet(K to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 212:212 */    public ObjectSortedSet<K> tailSet(K from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 213:213 */    public ObjectBidirectionalIterator<K> iterator() { return ObjectIterators.unmodifiable(this.sortedSet.iterator()); }
/* 214:214 */    public ObjectBidirectionalIterator<K> iterator(K from) { return ObjectIterators.unmodifiable(this.sortedSet.iterator(from)); }
/* 215:    */    @Deprecated
/* 216:216 */    public ObjectBidirectionalIterator<K> objectIterator() { return iterator(); }
/* 217:217 */    public K first() { return this.sortedSet.first(); }
/* 218:218 */    public K last() { return this.sortedSet.last(); }
/* 219:    */  }
/* 220:    */  
/* 224:    */  public static <K> ObjectSortedSet<K> unmodifiable(ObjectSortedSet<K> s)
/* 225:    */  {
/* 226:226 */    return new UnmodifiableSortedSet(s);
/* 227:    */  }
/* 228:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */