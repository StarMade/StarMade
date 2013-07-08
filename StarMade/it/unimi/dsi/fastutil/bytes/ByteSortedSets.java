/*   1:    */package it.unimi.dsi.fastutil.bytes;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  53:    */public class ByteSortedSets
/*  54:    */{
/*  55:    */  public static class EmptySet
/*  56:    */    extends ByteSets.EmptySet
/*  57:    */    implements ByteSortedSet, Serializable, Cloneable
/*  58:    */  {
/*  59:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  60:    */    
/*  61: 61 */    public boolean remove(byte ok) { throw new UnsupportedOperationException(); }
/*  62:    */    @Deprecated
/*  63: 63 */    public ByteBidirectionalIterator byteIterator() { return iterator(); }
/*  64:    */    
/*  65: 65 */    public ByteBidirectionalIterator iterator(byte from) { return ByteIterators.EMPTY_ITERATOR; }
/*  66:    */    
/*  67: 67 */    public ByteSortedSet subSet(byte from, byte to) { return ByteSortedSets.EMPTY_SET; }
/*  68:    */    
/*  69: 69 */    public ByteSortedSet headSet(byte from) { return ByteSortedSets.EMPTY_SET; }
/*  70:    */    
/*  71: 71 */    public ByteSortedSet tailSet(byte to) { return ByteSortedSets.EMPTY_SET; }
/*  72: 72 */    public byte firstByte() { throw new NoSuchElementException(); }
/*  73: 73 */    public byte lastByte() { throw new NoSuchElementException(); }
/*  74: 74 */    public ByteComparator comparator() { return null; }
/*  75: 75 */    public ByteSortedSet subSet(Byte from, Byte to) { return ByteSortedSets.EMPTY_SET; }
/*  76: 76 */    public ByteSortedSet headSet(Byte from) { return ByteSortedSets.EMPTY_SET; }
/*  77: 77 */    public ByteSortedSet tailSet(Byte to) { return ByteSortedSets.EMPTY_SET; }
/*  78: 78 */    public Byte first() { throw new NoSuchElementException(); }
/*  79: 79 */    public Byte last() { throw new NoSuchElementException(); }
/*  80: 80 */    public Object clone() { return ByteSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    private Object readResolve() { return ByteSortedSets.EMPTY_SET; }
/*  83:    */  }
/*  84:    */  
/*  93: 93 */  public static final EmptySet EMPTY_SET = new EmptySet();
/*  94:    */  
/*  97:    */  public static class Singleton
/*  98:    */    extends ByteSets.Singleton
/*  99:    */    implements ByteSortedSet, Serializable, Cloneable
/* 100:    */  {
/* 101:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 102:    */    
/* 103:    */    final ByteComparator comparator;
/* 104:    */    
/* 106:    */    private Singleton(byte element, ByteComparator comparator)
/* 107:    */    {
/* 108:108 */      super();
/* 109:109 */      this.comparator = comparator;
/* 110:    */    }
/* 111:    */    
/* 112:    */    private Singleton(byte element) {
/* 113:113 */      this(element, null);
/* 114:    */    }
/* 115:    */    
/* 116:    */    final int compare(byte k1, byte k2)
/* 117:    */    {
/* 118:118 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 119:    */    }
/* 120:    */    
/* 121:    */    @Deprecated
/* 122:    */    public ByteBidirectionalIterator byteIterator() {
/* 123:123 */      return iterator();
/* 124:    */    }
/* 125:    */    
/* 126:    */    public ByteBidirectionalIterator iterator(byte from) {
/* 127:127 */      ByteBidirectionalIterator i = iterator();
/* 128:128 */      if (compare(this.element, from) <= 0) i.next();
/* 129:129 */      return i;
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public ByteComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public ByteSortedSet subSet(byte from, byte to) {
/* 135:135 */      if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return ByteSortedSets.EMPTY_SET;
/* 136:    */    }
/* 137:    */    
/* 138:138 */    public ByteSortedSet headSet(byte to) { if (compare(this.element, to) < 0) return this; return ByteSortedSets.EMPTY_SET;
/* 139:    */    }
/* 140:    */    
/* 141:141 */    public ByteSortedSet tailSet(byte from) { if (compare(from, this.element) <= 0) return this; return ByteSortedSets.EMPTY_SET; }
/* 142:    */    
/* 143:143 */    public byte firstByte() { return this.element; }
/* 144:144 */    public byte lastByte() { return this.element; }
/* 145:    */    
/* 147:147 */    public Byte first() { return Byte.valueOf(this.element); }
/* 148:148 */    public Byte last() { return Byte.valueOf(this.element); }
/* 149:    */    
/* 151:151 */    public ByteSortedSet subSet(Byte from, Byte to) { return subSet(from.byteValue(), to.byteValue()); }
/* 152:152 */    public ByteSortedSet headSet(Byte to) { return headSet(to.byteValue()); }
/* 153:153 */    public ByteSortedSet tailSet(Byte from) { return tailSet(from.byteValue()); }
/* 154:    */  }
/* 155:    */  
/* 163:    */  public static ByteSortedSet singleton(byte element)
/* 164:    */  {
/* 165:165 */    return new Singleton(element, null);
/* 166:    */  }
/* 167:    */  
/* 174:    */  public static ByteSortedSet singleton(byte element, ByteComparator comparator)
/* 175:    */  {
/* 176:176 */    return new Singleton(element, comparator, null);
/* 177:    */  }
/* 178:    */  
/* 186:    */  public static ByteSortedSet singleton(Object element)
/* 187:    */  {
/* 188:188 */    return new Singleton(((Byte)element).byteValue(), null);
/* 189:    */  }
/* 190:    */  
/* 197:    */  public static ByteSortedSet singleton(Object element, ByteComparator comparator)
/* 198:    */  {
/* 199:199 */    return new Singleton(((Byte)element).byteValue(), comparator, null);
/* 200:    */  }
/* 201:    */  
/* 203:    */  public static class SynchronizedSortedSet
/* 204:    */    extends ByteSets.SynchronizedSet
/* 205:    */    implements ByteSortedSet, Serializable
/* 206:    */  {
/* 207:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 208:    */    
/* 209:    */    protected final ByteSortedSet sortedSet;
/* 210:    */    
/* 211:    */    protected SynchronizedSortedSet(ByteSortedSet s, Object sync)
/* 212:    */    {
/* 213:213 */      super(sync);
/* 214:214 */      this.sortedSet = s;
/* 215:    */    }
/* 216:    */    
/* 217:    */    protected SynchronizedSortedSet(ByteSortedSet s) {
/* 218:218 */      super();
/* 219:219 */      this.sortedSet = s;
/* 220:    */    }
/* 221:    */    
/* 222:222 */    public ByteComparator comparator() { synchronized (this.sync) { return this.sortedSet.comparator(); } }
/* 223:    */    
/* 224:224 */    public ByteSortedSet subSet(byte from, byte to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 225:225 */    public ByteSortedSet headSet(byte to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 226:226 */    public ByteSortedSet tailSet(byte from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 227:    */    
/* 228:228 */    public ByteBidirectionalIterator iterator() { return this.sortedSet.iterator(); }
/* 229:229 */    public ByteBidirectionalIterator iterator(byte from) { return this.sortedSet.iterator(from); }
/* 230:    */    
/* 231:    */    @Deprecated
/* 232:232 */    public ByteBidirectionalIterator byteIterator() { return this.sortedSet.iterator(); }
/* 233:    */    
/* 234:234 */    public byte firstByte() { synchronized (this.sync) { return this.sortedSet.firstByte(); } }
/* 235:235 */    public byte lastByte() { synchronized (this.sync) { return this.sortedSet.lastByte();
/* 236:    */      } }
/* 237:    */    
/* 238:238 */    public Byte first() { synchronized (this.sync) { return (Byte)this.sortedSet.first(); } }
/* 239:239 */    public Byte last() { synchronized (this.sync) { return (Byte)this.sortedSet.last(); } }
/* 240:    */    
/* 241:241 */    public ByteSortedSet subSet(Byte from, Byte to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 242:242 */    public ByteSortedSet headSet(Byte to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 243:243 */    public ByteSortedSet tailSet(Byte from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 244:    */  }
/* 245:    */  
/* 252:    */  public static ByteSortedSet synchronize(ByteSortedSet s)
/* 253:    */  {
/* 254:254 */    return new SynchronizedSortedSet(s);
/* 255:    */  }
/* 256:    */  
/* 262:    */  public static ByteSortedSet synchronize(ByteSortedSet s, Object sync)
/* 263:    */  {
/* 264:264 */    return new SynchronizedSortedSet(s, sync);
/* 265:    */  }
/* 266:    */  
/* 268:    */  public static class UnmodifiableSortedSet
/* 269:    */    extends ByteSets.UnmodifiableSet
/* 270:    */    implements ByteSortedSet, Serializable
/* 271:    */  {
/* 272:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 273:    */    
/* 274:    */    protected final ByteSortedSet sortedSet;
/* 275:    */    
/* 277:    */    protected UnmodifiableSortedSet(ByteSortedSet s)
/* 278:    */    {
/* 279:279 */      super();
/* 280:280 */      this.sortedSet = s;
/* 281:    */    }
/* 282:    */    
/* 283:283 */    public ByteComparator comparator() { return this.sortedSet.comparator(); }
/* 284:    */    
/* 285:285 */    public ByteSortedSet subSet(byte from, byte to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 286:286 */    public ByteSortedSet headSet(byte to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 287:287 */    public ByteSortedSet tailSet(byte from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 288:    */    
/* 289:289 */    public ByteBidirectionalIterator iterator() { return ByteIterators.unmodifiable(this.sortedSet.iterator()); }
/* 290:290 */    public ByteBidirectionalIterator iterator(byte from) { return ByteIterators.unmodifiable(this.sortedSet.iterator(from)); }
/* 291:    */    
/* 292:    */    @Deprecated
/* 293:293 */    public ByteBidirectionalIterator byteIterator() { return iterator(); }
/* 294:    */    
/* 295:295 */    public byte firstByte() { return this.sortedSet.firstByte(); }
/* 296:296 */    public byte lastByte() { return this.sortedSet.lastByte(); }
/* 297:    */    
/* 299:299 */    public Byte first() { return (Byte)this.sortedSet.first(); }
/* 300:300 */    public Byte last() { return (Byte)this.sortedSet.last(); }
/* 301:    */    
/* 302:302 */    public ByteSortedSet subSet(Byte from, Byte to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 303:303 */    public ByteSortedSet headSet(Byte to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 304:304 */    public ByteSortedSet tailSet(Byte from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 305:    */  }
/* 306:    */  
/* 313:    */  public static ByteSortedSet unmodifiable(ByteSortedSet s)
/* 314:    */  {
/* 315:315 */    return new UnmodifiableSortedSet(s);
/* 316:    */  }
/* 317:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */