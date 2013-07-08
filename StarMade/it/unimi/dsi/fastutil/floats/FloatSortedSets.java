/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  53:    */public class FloatSortedSets
/*  54:    */{
/*  55:    */  public static class EmptySet
/*  56:    */    extends FloatSets.EmptySet
/*  57:    */    implements FloatSortedSet, Serializable, Cloneable
/*  58:    */  {
/*  59:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  60:    */    
/*  61: 61 */    public boolean remove(float ok) { throw new UnsupportedOperationException(); }
/*  62:    */    @Deprecated
/*  63: 63 */    public FloatBidirectionalIterator floatIterator() { return iterator(); }
/*  64:    */    
/*  65: 65 */    public FloatBidirectionalIterator iterator(float from) { return FloatIterators.EMPTY_ITERATOR; }
/*  66:    */    
/*  67: 67 */    public FloatSortedSet subSet(float from, float to) { return FloatSortedSets.EMPTY_SET; }
/*  68:    */    
/*  69: 69 */    public FloatSortedSet headSet(float from) { return FloatSortedSets.EMPTY_SET; }
/*  70:    */    
/*  71: 71 */    public FloatSortedSet tailSet(float to) { return FloatSortedSets.EMPTY_SET; }
/*  72: 72 */    public float firstFloat() { throw new NoSuchElementException(); }
/*  73: 73 */    public float lastFloat() { throw new NoSuchElementException(); }
/*  74: 74 */    public FloatComparator comparator() { return null; }
/*  75: 75 */    public FloatSortedSet subSet(Float from, Float to) { return FloatSortedSets.EMPTY_SET; }
/*  76: 76 */    public FloatSortedSet headSet(Float from) { return FloatSortedSets.EMPTY_SET; }
/*  77: 77 */    public FloatSortedSet tailSet(Float to) { return FloatSortedSets.EMPTY_SET; }
/*  78: 78 */    public Float first() { throw new NoSuchElementException(); }
/*  79: 79 */    public Float last() { throw new NoSuchElementException(); }
/*  80: 80 */    public Object clone() { return FloatSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    private Object readResolve() { return FloatSortedSets.EMPTY_SET; }
/*  83:    */  }
/*  84:    */  
/*  93: 93 */  public static final EmptySet EMPTY_SET = new EmptySet();
/*  94:    */  
/*  97:    */  public static class Singleton
/*  98:    */    extends FloatSets.Singleton
/*  99:    */    implements FloatSortedSet, Serializable, Cloneable
/* 100:    */  {
/* 101:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 102:    */    
/* 103:    */    final FloatComparator comparator;
/* 104:    */    
/* 106:    */    private Singleton(float element, FloatComparator comparator)
/* 107:    */    {
/* 108:108 */      super();
/* 109:109 */      this.comparator = comparator;
/* 110:    */    }
/* 111:    */    
/* 112:    */    private Singleton(float element) {
/* 113:113 */      this(element, null);
/* 114:    */    }
/* 115:    */    
/* 116:    */    final int compare(float k1, float k2)
/* 117:    */    {
/* 118:118 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 119:    */    }
/* 120:    */    
/* 121:    */    @Deprecated
/* 122:    */    public FloatBidirectionalIterator floatIterator() {
/* 123:123 */      return iterator();
/* 124:    */    }
/* 125:    */    
/* 126:    */    public FloatBidirectionalIterator iterator(float from) {
/* 127:127 */      FloatBidirectionalIterator i = iterator();
/* 128:128 */      if (compare(this.element, from) <= 0) i.next();
/* 129:129 */      return i;
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public FloatComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public FloatSortedSet subSet(float from, float to) {
/* 135:135 */      if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return FloatSortedSets.EMPTY_SET;
/* 136:    */    }
/* 137:    */    
/* 138:138 */    public FloatSortedSet headSet(float to) { if (compare(this.element, to) < 0) return this; return FloatSortedSets.EMPTY_SET;
/* 139:    */    }
/* 140:    */    
/* 141:141 */    public FloatSortedSet tailSet(float from) { if (compare(from, this.element) <= 0) return this; return FloatSortedSets.EMPTY_SET; }
/* 142:    */    
/* 143:143 */    public float firstFloat() { return this.element; }
/* 144:144 */    public float lastFloat() { return this.element; }
/* 145:    */    
/* 147:147 */    public Float first() { return Float.valueOf(this.element); }
/* 148:148 */    public Float last() { return Float.valueOf(this.element); }
/* 149:    */    
/* 151:151 */    public FloatSortedSet subSet(Float from, Float to) { return subSet(from.floatValue(), to.floatValue()); }
/* 152:152 */    public FloatSortedSet headSet(Float to) { return headSet(to.floatValue()); }
/* 153:153 */    public FloatSortedSet tailSet(Float from) { return tailSet(from.floatValue()); }
/* 154:    */  }
/* 155:    */  
/* 163:    */  public static FloatSortedSet singleton(float element)
/* 164:    */  {
/* 165:165 */    return new Singleton(element, null);
/* 166:    */  }
/* 167:    */  
/* 174:    */  public static FloatSortedSet singleton(float element, FloatComparator comparator)
/* 175:    */  {
/* 176:176 */    return new Singleton(element, comparator, null);
/* 177:    */  }
/* 178:    */  
/* 186:    */  public static FloatSortedSet singleton(Object element)
/* 187:    */  {
/* 188:188 */    return new Singleton(((Float)element).floatValue(), null);
/* 189:    */  }
/* 190:    */  
/* 197:    */  public static FloatSortedSet singleton(Object element, FloatComparator comparator)
/* 198:    */  {
/* 199:199 */    return new Singleton(((Float)element).floatValue(), comparator, null);
/* 200:    */  }
/* 201:    */  
/* 203:    */  public static class SynchronizedSortedSet
/* 204:    */    extends FloatSets.SynchronizedSet
/* 205:    */    implements FloatSortedSet, Serializable
/* 206:    */  {
/* 207:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 208:    */    
/* 209:    */    protected final FloatSortedSet sortedSet;
/* 210:    */    
/* 211:    */    protected SynchronizedSortedSet(FloatSortedSet s, Object sync)
/* 212:    */    {
/* 213:213 */      super(sync);
/* 214:214 */      this.sortedSet = s;
/* 215:    */    }
/* 216:    */    
/* 217:    */    protected SynchronizedSortedSet(FloatSortedSet s) {
/* 218:218 */      super();
/* 219:219 */      this.sortedSet = s;
/* 220:    */    }
/* 221:    */    
/* 222:222 */    public FloatComparator comparator() { synchronized (this.sync) { return this.sortedSet.comparator(); } }
/* 223:    */    
/* 224:224 */    public FloatSortedSet subSet(float from, float to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 225:225 */    public FloatSortedSet headSet(float to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 226:226 */    public FloatSortedSet tailSet(float from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 227:    */    
/* 228:228 */    public FloatBidirectionalIterator iterator() { return this.sortedSet.iterator(); }
/* 229:229 */    public FloatBidirectionalIterator iterator(float from) { return this.sortedSet.iterator(from); }
/* 230:    */    
/* 231:    */    @Deprecated
/* 232:232 */    public FloatBidirectionalIterator floatIterator() { return this.sortedSet.iterator(); }
/* 233:    */    
/* 234:234 */    public float firstFloat() { synchronized (this.sync) { return this.sortedSet.firstFloat(); } }
/* 235:235 */    public float lastFloat() { synchronized (this.sync) { return this.sortedSet.lastFloat();
/* 236:    */      } }
/* 237:    */    
/* 238:238 */    public Float first() { synchronized (this.sync) { return (Float)this.sortedSet.first(); } }
/* 239:239 */    public Float last() { synchronized (this.sync) { return (Float)this.sortedSet.last(); } }
/* 240:    */    
/* 241:241 */    public FloatSortedSet subSet(Float from, Float to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 242:242 */    public FloatSortedSet headSet(Float to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 243:243 */    public FloatSortedSet tailSet(Float from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 244:    */  }
/* 245:    */  
/* 252:    */  public static FloatSortedSet synchronize(FloatSortedSet s)
/* 253:    */  {
/* 254:254 */    return new SynchronizedSortedSet(s);
/* 255:    */  }
/* 256:    */  
/* 262:    */  public static FloatSortedSet synchronize(FloatSortedSet s, Object sync)
/* 263:    */  {
/* 264:264 */    return new SynchronizedSortedSet(s, sync);
/* 265:    */  }
/* 266:    */  
/* 268:    */  public static class UnmodifiableSortedSet
/* 269:    */    extends FloatSets.UnmodifiableSet
/* 270:    */    implements FloatSortedSet, Serializable
/* 271:    */  {
/* 272:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 273:    */    
/* 274:    */    protected final FloatSortedSet sortedSet;
/* 275:    */    
/* 277:    */    protected UnmodifiableSortedSet(FloatSortedSet s)
/* 278:    */    {
/* 279:279 */      super();
/* 280:280 */      this.sortedSet = s;
/* 281:    */    }
/* 282:    */    
/* 283:283 */    public FloatComparator comparator() { return this.sortedSet.comparator(); }
/* 284:    */    
/* 285:285 */    public FloatSortedSet subSet(float from, float to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 286:286 */    public FloatSortedSet headSet(float to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 287:287 */    public FloatSortedSet tailSet(float from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 288:    */    
/* 289:289 */    public FloatBidirectionalIterator iterator() { return FloatIterators.unmodifiable(this.sortedSet.iterator()); }
/* 290:290 */    public FloatBidirectionalIterator iterator(float from) { return FloatIterators.unmodifiable(this.sortedSet.iterator(from)); }
/* 291:    */    
/* 292:    */    @Deprecated
/* 293:293 */    public FloatBidirectionalIterator floatIterator() { return iterator(); }
/* 294:    */    
/* 295:295 */    public float firstFloat() { return this.sortedSet.firstFloat(); }
/* 296:296 */    public float lastFloat() { return this.sortedSet.lastFloat(); }
/* 297:    */    
/* 299:299 */    public Float first() { return (Float)this.sortedSet.first(); }
/* 300:300 */    public Float last() { return (Float)this.sortedSet.last(); }
/* 301:    */    
/* 302:302 */    public FloatSortedSet subSet(Float from, Float to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 303:303 */    public FloatSortedSet headSet(Float to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 304:304 */    public FloatSortedSet tailSet(Float from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 305:    */  }
/* 306:    */  
/* 313:    */  public static FloatSortedSet unmodifiable(FloatSortedSet s)
/* 314:    */  {
/* 315:315 */    return new UnmodifiableSortedSet(s);
/* 316:    */  }
/* 317:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */