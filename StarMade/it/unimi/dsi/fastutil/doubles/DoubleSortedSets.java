/*   1:    */package it.unimi.dsi.fastutil.doubles;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  53:    */public class DoubleSortedSets
/*  54:    */{
/*  55:    */  public static class EmptySet
/*  56:    */    extends DoubleSets.EmptySet
/*  57:    */    implements DoubleSortedSet, Serializable, Cloneable
/*  58:    */  {
/*  59:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  60:    */    
/*  61: 61 */    public boolean remove(double ok) { throw new UnsupportedOperationException(); }
/*  62:    */    @Deprecated
/*  63: 63 */    public DoubleBidirectionalIterator doubleIterator() { return iterator(); }
/*  64:    */    
/*  65: 65 */    public DoubleBidirectionalIterator iterator(double from) { return DoubleIterators.EMPTY_ITERATOR; }
/*  66:    */    
/*  67: 67 */    public DoubleSortedSet subSet(double from, double to) { return DoubleSortedSets.EMPTY_SET; }
/*  68:    */    
/*  69: 69 */    public DoubleSortedSet headSet(double from) { return DoubleSortedSets.EMPTY_SET; }
/*  70:    */    
/*  71: 71 */    public DoubleSortedSet tailSet(double to) { return DoubleSortedSets.EMPTY_SET; }
/*  72: 72 */    public double firstDouble() { throw new NoSuchElementException(); }
/*  73: 73 */    public double lastDouble() { throw new NoSuchElementException(); }
/*  74: 74 */    public DoubleComparator comparator() { return null; }
/*  75: 75 */    public DoubleSortedSet subSet(Double from, Double to) { return DoubleSortedSets.EMPTY_SET; }
/*  76: 76 */    public DoubleSortedSet headSet(Double from) { return DoubleSortedSets.EMPTY_SET; }
/*  77: 77 */    public DoubleSortedSet tailSet(Double to) { return DoubleSortedSets.EMPTY_SET; }
/*  78: 78 */    public Double first() { throw new NoSuchElementException(); }
/*  79: 79 */    public Double last() { throw new NoSuchElementException(); }
/*  80: 80 */    public Object clone() { return DoubleSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    private Object readResolve() { return DoubleSortedSets.EMPTY_SET; }
/*  83:    */  }
/*  84:    */  
/*  93: 93 */  public static final EmptySet EMPTY_SET = new EmptySet();
/*  94:    */  
/*  97:    */  public static class Singleton
/*  98:    */    extends DoubleSets.Singleton
/*  99:    */    implements DoubleSortedSet, Serializable, Cloneable
/* 100:    */  {
/* 101:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 102:    */    
/* 103:    */    final DoubleComparator comparator;
/* 104:    */    
/* 106:    */    private Singleton(double element, DoubleComparator comparator)
/* 107:    */    {
/* 108:108 */      super();
/* 109:109 */      this.comparator = comparator;
/* 110:    */    }
/* 111:    */    
/* 112:    */    private Singleton(double element) {
/* 113:113 */      this(element, null);
/* 114:    */    }
/* 115:    */    
/* 116:    */    final int compare(double k1, double k2)
/* 117:    */    {
/* 118:118 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 119:    */    }
/* 120:    */    
/* 121:    */    @Deprecated
/* 122:    */    public DoubleBidirectionalIterator doubleIterator() {
/* 123:123 */      return iterator();
/* 124:    */    }
/* 125:    */    
/* 126:    */    public DoubleBidirectionalIterator iterator(double from) {
/* 127:127 */      DoubleBidirectionalIterator i = iterator();
/* 128:128 */      if (compare(this.element, from) <= 0) i.next();
/* 129:129 */      return i;
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public DoubleComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public DoubleSortedSet subSet(double from, double to) {
/* 135:135 */      if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return DoubleSortedSets.EMPTY_SET;
/* 136:    */    }
/* 137:    */    
/* 138:138 */    public DoubleSortedSet headSet(double to) { if (compare(this.element, to) < 0) return this; return DoubleSortedSets.EMPTY_SET;
/* 139:    */    }
/* 140:    */    
/* 141:141 */    public DoubleSortedSet tailSet(double from) { if (compare(from, this.element) <= 0) return this; return DoubleSortedSets.EMPTY_SET; }
/* 142:    */    
/* 143:143 */    public double firstDouble() { return this.element; }
/* 144:144 */    public double lastDouble() { return this.element; }
/* 145:    */    
/* 147:147 */    public Double first() { return Double.valueOf(this.element); }
/* 148:148 */    public Double last() { return Double.valueOf(this.element); }
/* 149:    */    
/* 151:151 */    public DoubleSortedSet subSet(Double from, Double to) { return subSet(from.doubleValue(), to.doubleValue()); }
/* 152:152 */    public DoubleSortedSet headSet(Double to) { return headSet(to.doubleValue()); }
/* 153:153 */    public DoubleSortedSet tailSet(Double from) { return tailSet(from.doubleValue()); }
/* 154:    */  }
/* 155:    */  
/* 163:    */  public static DoubleSortedSet singleton(double element)
/* 164:    */  {
/* 165:165 */    return new Singleton(element, null);
/* 166:    */  }
/* 167:    */  
/* 174:    */  public static DoubleSortedSet singleton(double element, DoubleComparator comparator)
/* 175:    */  {
/* 176:176 */    return new Singleton(element, comparator, null);
/* 177:    */  }
/* 178:    */  
/* 186:    */  public static DoubleSortedSet singleton(Object element)
/* 187:    */  {
/* 188:188 */    return new Singleton(((Double)element).doubleValue(), null);
/* 189:    */  }
/* 190:    */  
/* 197:    */  public static DoubleSortedSet singleton(Object element, DoubleComparator comparator)
/* 198:    */  {
/* 199:199 */    return new Singleton(((Double)element).doubleValue(), comparator, null);
/* 200:    */  }
/* 201:    */  
/* 203:    */  public static class SynchronizedSortedSet
/* 204:    */    extends DoubleSets.SynchronizedSet
/* 205:    */    implements DoubleSortedSet, Serializable
/* 206:    */  {
/* 207:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 208:    */    
/* 209:    */    protected final DoubleSortedSet sortedSet;
/* 210:    */    
/* 211:    */    protected SynchronizedSortedSet(DoubleSortedSet s, Object sync)
/* 212:    */    {
/* 213:213 */      super(sync);
/* 214:214 */      this.sortedSet = s;
/* 215:    */    }
/* 216:    */    
/* 217:    */    protected SynchronizedSortedSet(DoubleSortedSet s) {
/* 218:218 */      super();
/* 219:219 */      this.sortedSet = s;
/* 220:    */    }
/* 221:    */    
/* 222:222 */    public DoubleComparator comparator() { synchronized (this.sync) { return this.sortedSet.comparator(); } }
/* 223:    */    
/* 224:224 */    public DoubleSortedSet subSet(double from, double to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 225:225 */    public DoubleSortedSet headSet(double to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 226:226 */    public DoubleSortedSet tailSet(double from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 227:    */    
/* 228:228 */    public DoubleBidirectionalIterator iterator() { return this.sortedSet.iterator(); }
/* 229:229 */    public DoubleBidirectionalIterator iterator(double from) { return this.sortedSet.iterator(from); }
/* 230:    */    
/* 231:    */    @Deprecated
/* 232:232 */    public DoubleBidirectionalIterator doubleIterator() { return this.sortedSet.iterator(); }
/* 233:    */    
/* 234:234 */    public double firstDouble() { synchronized (this.sync) { return this.sortedSet.firstDouble(); } }
/* 235:235 */    public double lastDouble() { synchronized (this.sync) { return this.sortedSet.lastDouble();
/* 236:    */      } }
/* 237:    */    
/* 238:238 */    public Double first() { synchronized (this.sync) { return (Double)this.sortedSet.first(); } }
/* 239:239 */    public Double last() { synchronized (this.sync) { return (Double)this.sortedSet.last(); } }
/* 240:    */    
/* 241:241 */    public DoubleSortedSet subSet(Double from, Double to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 242:242 */    public DoubleSortedSet headSet(Double to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 243:243 */    public DoubleSortedSet tailSet(Double from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 244:    */  }
/* 245:    */  
/* 252:    */  public static DoubleSortedSet synchronize(DoubleSortedSet s)
/* 253:    */  {
/* 254:254 */    return new SynchronizedSortedSet(s);
/* 255:    */  }
/* 256:    */  
/* 262:    */  public static DoubleSortedSet synchronize(DoubleSortedSet s, Object sync)
/* 263:    */  {
/* 264:264 */    return new SynchronizedSortedSet(s, sync);
/* 265:    */  }
/* 266:    */  
/* 268:    */  public static class UnmodifiableSortedSet
/* 269:    */    extends DoubleSets.UnmodifiableSet
/* 270:    */    implements DoubleSortedSet, Serializable
/* 271:    */  {
/* 272:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 273:    */    
/* 274:    */    protected final DoubleSortedSet sortedSet;
/* 275:    */    
/* 277:    */    protected UnmodifiableSortedSet(DoubleSortedSet s)
/* 278:    */    {
/* 279:279 */      super();
/* 280:280 */      this.sortedSet = s;
/* 281:    */    }
/* 282:    */    
/* 283:283 */    public DoubleComparator comparator() { return this.sortedSet.comparator(); }
/* 284:    */    
/* 285:285 */    public DoubleSortedSet subSet(double from, double to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 286:286 */    public DoubleSortedSet headSet(double to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 287:287 */    public DoubleSortedSet tailSet(double from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 288:    */    
/* 289:289 */    public DoubleBidirectionalIterator iterator() { return DoubleIterators.unmodifiable(this.sortedSet.iterator()); }
/* 290:290 */    public DoubleBidirectionalIterator iterator(double from) { return DoubleIterators.unmodifiable(this.sortedSet.iterator(from)); }
/* 291:    */    
/* 292:    */    @Deprecated
/* 293:293 */    public DoubleBidirectionalIterator doubleIterator() { return iterator(); }
/* 294:    */    
/* 295:295 */    public double firstDouble() { return this.sortedSet.firstDouble(); }
/* 296:296 */    public double lastDouble() { return this.sortedSet.lastDouble(); }
/* 297:    */    
/* 299:299 */    public Double first() { return (Double)this.sortedSet.first(); }
/* 300:300 */    public Double last() { return (Double)this.sortedSet.last(); }
/* 301:    */    
/* 302:302 */    public DoubleSortedSet subSet(Double from, Double to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 303:303 */    public DoubleSortedSet headSet(Double to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 304:304 */    public DoubleSortedSet tailSet(Double from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 305:    */  }
/* 306:    */  
/* 313:    */  public static DoubleSortedSet unmodifiable(DoubleSortedSet s)
/* 314:    */  {
/* 315:315 */    return new UnmodifiableSortedSet(s);
/* 316:    */  }
/* 317:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */