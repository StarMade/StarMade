/*   1:    */package it.unimi.dsi.fastutil.shorts;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  53:    */public class ShortSortedSets
/*  54:    */{
/*  55:    */  public static class EmptySet
/*  56:    */    extends ShortSets.EmptySet
/*  57:    */    implements ShortSortedSet, Serializable, Cloneable
/*  58:    */  {
/*  59:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  60:    */    
/*  61: 61 */    public boolean remove(short ok) { throw new UnsupportedOperationException(); }
/*  62:    */    @Deprecated
/*  63: 63 */    public ShortBidirectionalIterator shortIterator() { return iterator(); }
/*  64:    */    
/*  65: 65 */    public ShortBidirectionalIterator iterator(short from) { return ShortIterators.EMPTY_ITERATOR; }
/*  66:    */    
/*  67: 67 */    public ShortSortedSet subSet(short from, short to) { return ShortSortedSets.EMPTY_SET; }
/*  68:    */    
/*  69: 69 */    public ShortSortedSet headSet(short from) { return ShortSortedSets.EMPTY_SET; }
/*  70:    */    
/*  71: 71 */    public ShortSortedSet tailSet(short to) { return ShortSortedSets.EMPTY_SET; }
/*  72: 72 */    public short firstShort() { throw new NoSuchElementException(); }
/*  73: 73 */    public short lastShort() { throw new NoSuchElementException(); }
/*  74: 74 */    public ShortComparator comparator() { return null; }
/*  75: 75 */    public ShortSortedSet subSet(Short from, Short to) { return ShortSortedSets.EMPTY_SET; }
/*  76: 76 */    public ShortSortedSet headSet(Short from) { return ShortSortedSets.EMPTY_SET; }
/*  77: 77 */    public ShortSortedSet tailSet(Short to) { return ShortSortedSets.EMPTY_SET; }
/*  78: 78 */    public Short first() { throw new NoSuchElementException(); }
/*  79: 79 */    public Short last() { throw new NoSuchElementException(); }
/*  80: 80 */    public Object clone() { return ShortSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    private Object readResolve() { return ShortSortedSets.EMPTY_SET; }
/*  83:    */  }
/*  84:    */  
/*  93: 93 */  public static final EmptySet EMPTY_SET = new EmptySet();
/*  94:    */  
/*  97:    */  public static class Singleton
/*  98:    */    extends ShortSets.Singleton
/*  99:    */    implements ShortSortedSet, Serializable, Cloneable
/* 100:    */  {
/* 101:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 102:    */    
/* 103:    */    final ShortComparator comparator;
/* 104:    */    
/* 106:    */    private Singleton(short element, ShortComparator comparator)
/* 107:    */    {
/* 108:108 */      super();
/* 109:109 */      this.comparator = comparator;
/* 110:    */    }
/* 111:    */    
/* 112:    */    private Singleton(short element) {
/* 113:113 */      this(element, null);
/* 114:    */    }
/* 115:    */    
/* 116:    */    final int compare(short k1, short k2)
/* 117:    */    {
/* 118:118 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 119:    */    }
/* 120:    */    
/* 121:    */    @Deprecated
/* 122:    */    public ShortBidirectionalIterator shortIterator() {
/* 123:123 */      return iterator();
/* 124:    */    }
/* 125:    */    
/* 126:    */    public ShortBidirectionalIterator iterator(short from) {
/* 127:127 */      ShortBidirectionalIterator i = iterator();
/* 128:128 */      if (compare(this.element, from) <= 0) i.next();
/* 129:129 */      return i;
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public ShortComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public ShortSortedSet subSet(short from, short to) {
/* 135:135 */      if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return ShortSortedSets.EMPTY_SET;
/* 136:    */    }
/* 137:    */    
/* 138:138 */    public ShortSortedSet headSet(short to) { if (compare(this.element, to) < 0) return this; return ShortSortedSets.EMPTY_SET;
/* 139:    */    }
/* 140:    */    
/* 141:141 */    public ShortSortedSet tailSet(short from) { if (compare(from, this.element) <= 0) return this; return ShortSortedSets.EMPTY_SET; }
/* 142:    */    
/* 143:143 */    public short firstShort() { return this.element; }
/* 144:144 */    public short lastShort() { return this.element; }
/* 145:    */    
/* 147:147 */    public Short first() { return Short.valueOf(this.element); }
/* 148:148 */    public Short last() { return Short.valueOf(this.element); }
/* 149:    */    
/* 151:151 */    public ShortSortedSet subSet(Short from, Short to) { return subSet(from.shortValue(), to.shortValue()); }
/* 152:152 */    public ShortSortedSet headSet(Short to) { return headSet(to.shortValue()); }
/* 153:153 */    public ShortSortedSet tailSet(Short from) { return tailSet(from.shortValue()); }
/* 154:    */  }
/* 155:    */  
/* 163:    */  public static ShortSortedSet singleton(short element)
/* 164:    */  {
/* 165:165 */    return new Singleton(element, null);
/* 166:    */  }
/* 167:    */  
/* 174:    */  public static ShortSortedSet singleton(short element, ShortComparator comparator)
/* 175:    */  {
/* 176:176 */    return new Singleton(element, comparator, null);
/* 177:    */  }
/* 178:    */  
/* 186:    */  public static ShortSortedSet singleton(Object element)
/* 187:    */  {
/* 188:188 */    return new Singleton(((Short)element).shortValue(), null);
/* 189:    */  }
/* 190:    */  
/* 197:    */  public static ShortSortedSet singleton(Object element, ShortComparator comparator)
/* 198:    */  {
/* 199:199 */    return new Singleton(((Short)element).shortValue(), comparator, null);
/* 200:    */  }
/* 201:    */  
/* 203:    */  public static class SynchronizedSortedSet
/* 204:    */    extends ShortSets.SynchronizedSet
/* 205:    */    implements ShortSortedSet, Serializable
/* 206:    */  {
/* 207:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 208:    */    
/* 209:    */    protected final ShortSortedSet sortedSet;
/* 210:    */    
/* 211:    */    protected SynchronizedSortedSet(ShortSortedSet s, Object sync)
/* 212:    */    {
/* 213:213 */      super(sync);
/* 214:214 */      this.sortedSet = s;
/* 215:    */    }
/* 216:    */    
/* 217:    */    protected SynchronizedSortedSet(ShortSortedSet s) {
/* 218:218 */      super();
/* 219:219 */      this.sortedSet = s;
/* 220:    */    }
/* 221:    */    
/* 222:222 */    public ShortComparator comparator() { synchronized (this.sync) { return this.sortedSet.comparator(); } }
/* 223:    */    
/* 224:224 */    public ShortSortedSet subSet(short from, short to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 225:225 */    public ShortSortedSet headSet(short to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 226:226 */    public ShortSortedSet tailSet(short from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 227:    */    
/* 228:228 */    public ShortBidirectionalIterator iterator() { return this.sortedSet.iterator(); }
/* 229:229 */    public ShortBidirectionalIterator iterator(short from) { return this.sortedSet.iterator(from); }
/* 230:    */    
/* 231:    */    @Deprecated
/* 232:232 */    public ShortBidirectionalIterator shortIterator() { return this.sortedSet.iterator(); }
/* 233:    */    
/* 234:234 */    public short firstShort() { synchronized (this.sync) { return this.sortedSet.firstShort(); } }
/* 235:235 */    public short lastShort() { synchronized (this.sync) { return this.sortedSet.lastShort();
/* 236:    */      } }
/* 237:    */    
/* 238:238 */    public Short first() { synchronized (this.sync) { return (Short)this.sortedSet.first(); } }
/* 239:239 */    public Short last() { synchronized (this.sync) { return (Short)this.sortedSet.last(); } }
/* 240:    */    
/* 241:241 */    public ShortSortedSet subSet(Short from, Short to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 242:242 */    public ShortSortedSet headSet(Short to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 243:243 */    public ShortSortedSet tailSet(Short from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 244:    */  }
/* 245:    */  
/* 252:    */  public static ShortSortedSet synchronize(ShortSortedSet s)
/* 253:    */  {
/* 254:254 */    return new SynchronizedSortedSet(s);
/* 255:    */  }
/* 256:    */  
/* 262:    */  public static ShortSortedSet synchronize(ShortSortedSet s, Object sync)
/* 263:    */  {
/* 264:264 */    return new SynchronizedSortedSet(s, sync);
/* 265:    */  }
/* 266:    */  
/* 268:    */  public static class UnmodifiableSortedSet
/* 269:    */    extends ShortSets.UnmodifiableSet
/* 270:    */    implements ShortSortedSet, Serializable
/* 271:    */  {
/* 272:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 273:    */    
/* 274:    */    protected final ShortSortedSet sortedSet;
/* 275:    */    
/* 277:    */    protected UnmodifiableSortedSet(ShortSortedSet s)
/* 278:    */    {
/* 279:279 */      super();
/* 280:280 */      this.sortedSet = s;
/* 281:    */    }
/* 282:    */    
/* 283:283 */    public ShortComparator comparator() { return this.sortedSet.comparator(); }
/* 284:    */    
/* 285:285 */    public ShortSortedSet subSet(short from, short to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 286:286 */    public ShortSortedSet headSet(short to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 287:287 */    public ShortSortedSet tailSet(short from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 288:    */    
/* 289:289 */    public ShortBidirectionalIterator iterator() { return ShortIterators.unmodifiable(this.sortedSet.iterator()); }
/* 290:290 */    public ShortBidirectionalIterator iterator(short from) { return ShortIterators.unmodifiable(this.sortedSet.iterator(from)); }
/* 291:    */    
/* 292:    */    @Deprecated
/* 293:293 */    public ShortBidirectionalIterator shortIterator() { return iterator(); }
/* 294:    */    
/* 295:295 */    public short firstShort() { return this.sortedSet.firstShort(); }
/* 296:296 */    public short lastShort() { return this.sortedSet.lastShort(); }
/* 297:    */    
/* 299:299 */    public Short first() { return (Short)this.sortedSet.first(); }
/* 300:300 */    public Short last() { return (Short)this.sortedSet.last(); }
/* 301:    */    
/* 302:302 */    public ShortSortedSet subSet(Short from, Short to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 303:303 */    public ShortSortedSet headSet(Short to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 304:304 */    public ShortSortedSet tailSet(Short from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 305:    */  }
/* 306:    */  
/* 313:    */  public static ShortSortedSet unmodifiable(ShortSortedSet s)
/* 314:    */  {
/* 315:315 */    return new UnmodifiableSortedSet(s);
/* 316:    */  }
/* 317:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */