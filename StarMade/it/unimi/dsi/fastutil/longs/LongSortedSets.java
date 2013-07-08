/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  53:    */public class LongSortedSets
/*  54:    */{
/*  55:    */  public static class EmptySet
/*  56:    */    extends LongSets.EmptySet
/*  57:    */    implements LongSortedSet, Serializable, Cloneable
/*  58:    */  {
/*  59:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  60:    */    
/*  61: 61 */    public boolean remove(long ok) { throw new UnsupportedOperationException(); }
/*  62:    */    @Deprecated
/*  63: 63 */    public LongBidirectionalIterator longIterator() { return iterator(); }
/*  64:    */    
/*  65: 65 */    public LongBidirectionalIterator iterator(long from) { return LongIterators.EMPTY_ITERATOR; }
/*  66:    */    
/*  67: 67 */    public LongSortedSet subSet(long from, long to) { return LongSortedSets.EMPTY_SET; }
/*  68:    */    
/*  69: 69 */    public LongSortedSet headSet(long from) { return LongSortedSets.EMPTY_SET; }
/*  70:    */    
/*  71: 71 */    public LongSortedSet tailSet(long to) { return LongSortedSets.EMPTY_SET; }
/*  72: 72 */    public long firstLong() { throw new NoSuchElementException(); }
/*  73: 73 */    public long lastLong() { throw new NoSuchElementException(); }
/*  74: 74 */    public LongComparator comparator() { return null; }
/*  75: 75 */    public LongSortedSet subSet(Long from, Long to) { return LongSortedSets.EMPTY_SET; }
/*  76: 76 */    public LongSortedSet headSet(Long from) { return LongSortedSets.EMPTY_SET; }
/*  77: 77 */    public LongSortedSet tailSet(Long to) { return LongSortedSets.EMPTY_SET; }
/*  78: 78 */    public Long first() { throw new NoSuchElementException(); }
/*  79: 79 */    public Long last() { throw new NoSuchElementException(); }
/*  80: 80 */    public Object clone() { return LongSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    private Object readResolve() { return LongSortedSets.EMPTY_SET; }
/*  83:    */  }
/*  84:    */  
/*  93: 93 */  public static final EmptySet EMPTY_SET = new EmptySet();
/*  94:    */  
/*  97:    */  public static class Singleton
/*  98:    */    extends LongSets.Singleton
/*  99:    */    implements LongSortedSet, Serializable, Cloneable
/* 100:    */  {
/* 101:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 102:    */    
/* 103:    */    final LongComparator comparator;
/* 104:    */    
/* 106:    */    private Singleton(long element, LongComparator comparator)
/* 107:    */    {
/* 108:108 */      super();
/* 109:109 */      this.comparator = comparator;
/* 110:    */    }
/* 111:    */    
/* 112:    */    private Singleton(long element) {
/* 113:113 */      this(element, null);
/* 114:    */    }
/* 115:    */    
/* 116:    */    final int compare(long k1, long k2)
/* 117:    */    {
/* 118:118 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 119:    */    }
/* 120:    */    
/* 121:    */    @Deprecated
/* 122:    */    public LongBidirectionalIterator longIterator() {
/* 123:123 */      return iterator();
/* 124:    */    }
/* 125:    */    
/* 126:    */    public LongBidirectionalIterator iterator(long from) {
/* 127:127 */      LongBidirectionalIterator i = iterator();
/* 128:128 */      if (compare(this.element, from) <= 0) i.next();
/* 129:129 */      return i;
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public LongComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public LongSortedSet subSet(long from, long to) {
/* 135:135 */      if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return LongSortedSets.EMPTY_SET;
/* 136:    */    }
/* 137:    */    
/* 138:138 */    public LongSortedSet headSet(long to) { if (compare(this.element, to) < 0) return this; return LongSortedSets.EMPTY_SET;
/* 139:    */    }
/* 140:    */    
/* 141:141 */    public LongSortedSet tailSet(long from) { if (compare(from, this.element) <= 0) return this; return LongSortedSets.EMPTY_SET; }
/* 142:    */    
/* 143:143 */    public long firstLong() { return this.element; }
/* 144:144 */    public long lastLong() { return this.element; }
/* 145:    */    
/* 147:147 */    public Long first() { return Long.valueOf(this.element); }
/* 148:148 */    public Long last() { return Long.valueOf(this.element); }
/* 149:    */    
/* 151:151 */    public LongSortedSet subSet(Long from, Long to) { return subSet(from.longValue(), to.longValue()); }
/* 152:152 */    public LongSortedSet headSet(Long to) { return headSet(to.longValue()); }
/* 153:153 */    public LongSortedSet tailSet(Long from) { return tailSet(from.longValue()); }
/* 154:    */  }
/* 155:    */  
/* 163:    */  public static LongSortedSet singleton(long element)
/* 164:    */  {
/* 165:165 */    return new Singleton(element, null);
/* 166:    */  }
/* 167:    */  
/* 174:    */  public static LongSortedSet singleton(long element, LongComparator comparator)
/* 175:    */  {
/* 176:176 */    return new Singleton(element, comparator, null);
/* 177:    */  }
/* 178:    */  
/* 186:    */  public static LongSortedSet singleton(Object element)
/* 187:    */  {
/* 188:188 */    return new Singleton(((Long)element).longValue(), null);
/* 189:    */  }
/* 190:    */  
/* 197:    */  public static LongSortedSet singleton(Object element, LongComparator comparator)
/* 198:    */  {
/* 199:199 */    return new Singleton(((Long)element).longValue(), comparator, null);
/* 200:    */  }
/* 201:    */  
/* 203:    */  public static class SynchronizedSortedSet
/* 204:    */    extends LongSets.SynchronizedSet
/* 205:    */    implements LongSortedSet, Serializable
/* 206:    */  {
/* 207:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 208:    */    
/* 209:    */    protected final LongSortedSet sortedSet;
/* 210:    */    
/* 211:    */    protected SynchronizedSortedSet(LongSortedSet s, Object sync)
/* 212:    */    {
/* 213:213 */      super(sync);
/* 214:214 */      this.sortedSet = s;
/* 215:    */    }
/* 216:    */    
/* 217:    */    protected SynchronizedSortedSet(LongSortedSet s) {
/* 218:218 */      super();
/* 219:219 */      this.sortedSet = s;
/* 220:    */    }
/* 221:    */    
/* 222:222 */    public LongComparator comparator() { synchronized (this.sync) { return this.sortedSet.comparator(); } }
/* 223:    */    
/* 224:224 */    public LongSortedSet subSet(long from, long to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 225:225 */    public LongSortedSet headSet(long to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 226:226 */    public LongSortedSet tailSet(long from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 227:    */    
/* 228:228 */    public LongBidirectionalIterator iterator() { return this.sortedSet.iterator(); }
/* 229:229 */    public LongBidirectionalIterator iterator(long from) { return this.sortedSet.iterator(from); }
/* 230:    */    
/* 231:    */    @Deprecated
/* 232:232 */    public LongBidirectionalIterator longIterator() { return this.sortedSet.iterator(); }
/* 233:    */    
/* 234:234 */    public long firstLong() { synchronized (this.sync) { return this.sortedSet.firstLong(); } }
/* 235:235 */    public long lastLong() { synchronized (this.sync) { return this.sortedSet.lastLong();
/* 236:    */      } }
/* 237:    */    
/* 238:238 */    public Long first() { synchronized (this.sync) { return (Long)this.sortedSet.first(); } }
/* 239:239 */    public Long last() { synchronized (this.sync) { return (Long)this.sortedSet.last(); } }
/* 240:    */    
/* 241:241 */    public LongSortedSet subSet(Long from, Long to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 242:242 */    public LongSortedSet headSet(Long to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 243:243 */    public LongSortedSet tailSet(Long from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 244:    */  }
/* 245:    */  
/* 252:    */  public static LongSortedSet synchronize(LongSortedSet s)
/* 253:    */  {
/* 254:254 */    return new SynchronizedSortedSet(s);
/* 255:    */  }
/* 256:    */  
/* 262:    */  public static LongSortedSet synchronize(LongSortedSet s, Object sync)
/* 263:    */  {
/* 264:264 */    return new SynchronizedSortedSet(s, sync);
/* 265:    */  }
/* 266:    */  
/* 268:    */  public static class UnmodifiableSortedSet
/* 269:    */    extends LongSets.UnmodifiableSet
/* 270:    */    implements LongSortedSet, Serializable
/* 271:    */  {
/* 272:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 273:    */    
/* 274:    */    protected final LongSortedSet sortedSet;
/* 275:    */    
/* 277:    */    protected UnmodifiableSortedSet(LongSortedSet s)
/* 278:    */    {
/* 279:279 */      super();
/* 280:280 */      this.sortedSet = s;
/* 281:    */    }
/* 282:    */    
/* 283:283 */    public LongComparator comparator() { return this.sortedSet.comparator(); }
/* 284:    */    
/* 285:285 */    public LongSortedSet subSet(long from, long to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 286:286 */    public LongSortedSet headSet(long to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 287:287 */    public LongSortedSet tailSet(long from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 288:    */    
/* 289:289 */    public LongBidirectionalIterator iterator() { return LongIterators.unmodifiable(this.sortedSet.iterator()); }
/* 290:290 */    public LongBidirectionalIterator iterator(long from) { return LongIterators.unmodifiable(this.sortedSet.iterator(from)); }
/* 291:    */    
/* 292:    */    @Deprecated
/* 293:293 */    public LongBidirectionalIterator longIterator() { return iterator(); }
/* 294:    */    
/* 295:295 */    public long firstLong() { return this.sortedSet.firstLong(); }
/* 296:296 */    public long lastLong() { return this.sortedSet.lastLong(); }
/* 297:    */    
/* 299:299 */    public Long first() { return (Long)this.sortedSet.first(); }
/* 300:300 */    public Long last() { return (Long)this.sortedSet.last(); }
/* 301:    */    
/* 302:302 */    public LongSortedSet subSet(Long from, Long to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 303:303 */    public LongSortedSet headSet(Long to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 304:304 */    public LongSortedSet tailSet(Long from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 305:    */  }
/* 306:    */  
/* 313:    */  public static LongSortedSet unmodifiable(LongSortedSet s)
/* 314:    */  {
/* 315:315 */    return new UnmodifiableSortedSet(s);
/* 316:    */  }
/* 317:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */