/*   1:    */package it.unimi.dsi.fastutil.chars;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  53:    */public class CharSortedSets
/*  54:    */{
/*  55:    */  public static class EmptySet
/*  56:    */    extends CharSets.EmptySet
/*  57:    */    implements CharSortedSet, Serializable, Cloneable
/*  58:    */  {
/*  59:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  60:    */    
/*  61: 61 */    public boolean remove(char ok) { throw new UnsupportedOperationException(); }
/*  62:    */    @Deprecated
/*  63: 63 */    public CharBidirectionalIterator charIterator() { return iterator(); }
/*  64:    */    
/*  65: 65 */    public CharBidirectionalIterator iterator(char from) { return CharIterators.EMPTY_ITERATOR; }
/*  66:    */    
/*  67: 67 */    public CharSortedSet subSet(char from, char to) { return CharSortedSets.EMPTY_SET; }
/*  68:    */    
/*  69: 69 */    public CharSortedSet headSet(char from) { return CharSortedSets.EMPTY_SET; }
/*  70:    */    
/*  71: 71 */    public CharSortedSet tailSet(char to) { return CharSortedSets.EMPTY_SET; }
/*  72: 72 */    public char firstChar() { throw new NoSuchElementException(); }
/*  73: 73 */    public char lastChar() { throw new NoSuchElementException(); }
/*  74: 74 */    public CharComparator comparator() { return null; }
/*  75: 75 */    public CharSortedSet subSet(Character from, Character to) { return CharSortedSets.EMPTY_SET; }
/*  76: 76 */    public CharSortedSet headSet(Character from) { return CharSortedSets.EMPTY_SET; }
/*  77: 77 */    public CharSortedSet tailSet(Character to) { return CharSortedSets.EMPTY_SET; }
/*  78: 78 */    public Character first() { throw new NoSuchElementException(); }
/*  79: 79 */    public Character last() { throw new NoSuchElementException(); }
/*  80: 80 */    public Object clone() { return CharSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    private Object readResolve() { return CharSortedSets.EMPTY_SET; }
/*  83:    */  }
/*  84:    */  
/*  93: 93 */  public static final EmptySet EMPTY_SET = new EmptySet();
/*  94:    */  
/*  97:    */  public static class Singleton
/*  98:    */    extends CharSets.Singleton
/*  99:    */    implements CharSortedSet, Serializable, Cloneable
/* 100:    */  {
/* 101:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 102:    */    
/* 103:    */    final CharComparator comparator;
/* 104:    */    
/* 106:    */    private Singleton(char element, CharComparator comparator)
/* 107:    */    {
/* 108:108 */      super();
/* 109:109 */      this.comparator = comparator;
/* 110:    */    }
/* 111:    */    
/* 112:    */    private Singleton(char element) {
/* 113:113 */      this(element, null);
/* 114:    */    }
/* 115:    */    
/* 116:    */    final int compare(char k1, char k2)
/* 117:    */    {
/* 118:118 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 119:    */    }
/* 120:    */    
/* 121:    */    @Deprecated
/* 122:    */    public CharBidirectionalIterator charIterator() {
/* 123:123 */      return iterator();
/* 124:    */    }
/* 125:    */    
/* 126:    */    public CharBidirectionalIterator iterator(char from) {
/* 127:127 */      CharBidirectionalIterator i = iterator();
/* 128:128 */      if (compare(this.element, from) <= 0) i.next();
/* 129:129 */      return i;
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public CharComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public CharSortedSet subSet(char from, char to) {
/* 135:135 */      if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return CharSortedSets.EMPTY_SET;
/* 136:    */    }
/* 137:    */    
/* 138:138 */    public CharSortedSet headSet(char to) { if (compare(this.element, to) < 0) return this; return CharSortedSets.EMPTY_SET;
/* 139:    */    }
/* 140:    */    
/* 141:141 */    public CharSortedSet tailSet(char from) { if (compare(from, this.element) <= 0) return this; return CharSortedSets.EMPTY_SET; }
/* 142:    */    
/* 143:143 */    public char firstChar() { return this.element; }
/* 144:144 */    public char lastChar() { return this.element; }
/* 145:    */    
/* 147:147 */    public Character first() { return Character.valueOf(this.element); }
/* 148:148 */    public Character last() { return Character.valueOf(this.element); }
/* 149:    */    
/* 151:151 */    public CharSortedSet subSet(Character from, Character to) { return subSet(from.charValue(), to.charValue()); }
/* 152:152 */    public CharSortedSet headSet(Character to) { return headSet(to.charValue()); }
/* 153:153 */    public CharSortedSet tailSet(Character from) { return tailSet(from.charValue()); }
/* 154:    */  }
/* 155:    */  
/* 163:    */  public static CharSortedSet singleton(char element)
/* 164:    */  {
/* 165:165 */    return new Singleton(element, null);
/* 166:    */  }
/* 167:    */  
/* 174:    */  public static CharSortedSet singleton(char element, CharComparator comparator)
/* 175:    */  {
/* 176:176 */    return new Singleton(element, comparator, null);
/* 177:    */  }
/* 178:    */  
/* 186:    */  public static CharSortedSet singleton(Object element)
/* 187:    */  {
/* 188:188 */    return new Singleton(((Character)element).charValue(), null);
/* 189:    */  }
/* 190:    */  
/* 197:    */  public static CharSortedSet singleton(Object element, CharComparator comparator)
/* 198:    */  {
/* 199:199 */    return new Singleton(((Character)element).charValue(), comparator, null);
/* 200:    */  }
/* 201:    */  
/* 203:    */  public static class SynchronizedSortedSet
/* 204:    */    extends CharSets.SynchronizedSet
/* 205:    */    implements CharSortedSet, Serializable
/* 206:    */  {
/* 207:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 208:    */    
/* 209:    */    protected final CharSortedSet sortedSet;
/* 210:    */    
/* 211:    */    protected SynchronizedSortedSet(CharSortedSet s, Object sync)
/* 212:    */    {
/* 213:213 */      super(sync);
/* 214:214 */      this.sortedSet = s;
/* 215:    */    }
/* 216:    */    
/* 217:    */    protected SynchronizedSortedSet(CharSortedSet s) {
/* 218:218 */      super();
/* 219:219 */      this.sortedSet = s;
/* 220:    */    }
/* 221:    */    
/* 222:222 */    public CharComparator comparator() { synchronized (this.sync) { return this.sortedSet.comparator(); } }
/* 223:    */    
/* 224:224 */    public CharSortedSet subSet(char from, char to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 225:225 */    public CharSortedSet headSet(char to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 226:226 */    public CharSortedSet tailSet(char from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 227:    */    
/* 228:228 */    public CharBidirectionalIterator iterator() { return this.sortedSet.iterator(); }
/* 229:229 */    public CharBidirectionalIterator iterator(char from) { return this.sortedSet.iterator(from); }
/* 230:    */    
/* 231:    */    @Deprecated
/* 232:232 */    public CharBidirectionalIterator charIterator() { return this.sortedSet.iterator(); }
/* 233:    */    
/* 234:234 */    public char firstChar() { synchronized (this.sync) { return this.sortedSet.firstChar(); } }
/* 235:235 */    public char lastChar() { synchronized (this.sync) { return this.sortedSet.lastChar();
/* 236:    */      } }
/* 237:    */    
/* 238:238 */    public Character first() { synchronized (this.sync) { return (Character)this.sortedSet.first(); } }
/* 239:239 */    public Character last() { synchronized (this.sync) { return (Character)this.sortedSet.last(); } }
/* 240:    */    
/* 241:241 */    public CharSortedSet subSet(Character from, Character to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); }
/* 242:242 */    public CharSortedSet headSet(Character to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); }
/* 243:243 */    public CharSortedSet tailSet(Character from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/* 244:    */  }
/* 245:    */  
/* 252:    */  public static CharSortedSet synchronize(CharSortedSet s)
/* 253:    */  {
/* 254:254 */    return new SynchronizedSortedSet(s);
/* 255:    */  }
/* 256:    */  
/* 262:    */  public static CharSortedSet synchronize(CharSortedSet s, Object sync)
/* 263:    */  {
/* 264:264 */    return new SynchronizedSortedSet(s, sync);
/* 265:    */  }
/* 266:    */  
/* 268:    */  public static class UnmodifiableSortedSet
/* 269:    */    extends CharSets.UnmodifiableSet
/* 270:    */    implements CharSortedSet, Serializable
/* 271:    */  {
/* 272:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 273:    */    
/* 274:    */    protected final CharSortedSet sortedSet;
/* 275:    */    
/* 277:    */    protected UnmodifiableSortedSet(CharSortedSet s)
/* 278:    */    {
/* 279:279 */      super();
/* 280:280 */      this.sortedSet = s;
/* 281:    */    }
/* 282:    */    
/* 283:283 */    public CharComparator comparator() { return this.sortedSet.comparator(); }
/* 284:    */    
/* 285:285 */    public CharSortedSet subSet(char from, char to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 286:286 */    public CharSortedSet headSet(char to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 287:287 */    public CharSortedSet tailSet(char from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 288:    */    
/* 289:289 */    public CharBidirectionalIterator iterator() { return CharIterators.unmodifiable(this.sortedSet.iterator()); }
/* 290:290 */    public CharBidirectionalIterator iterator(char from) { return CharIterators.unmodifiable(this.sortedSet.iterator(from)); }
/* 291:    */    
/* 292:    */    @Deprecated
/* 293:293 */    public CharBidirectionalIterator charIterator() { return iterator(); }
/* 294:    */    
/* 295:295 */    public char firstChar() { return this.sortedSet.firstChar(); }
/* 296:296 */    public char lastChar() { return this.sortedSet.lastChar(); }
/* 297:    */    
/* 299:299 */    public Character first() { return (Character)this.sortedSet.first(); }
/* 300:300 */    public Character last() { return (Character)this.sortedSet.last(); }
/* 301:    */    
/* 302:302 */    public CharSortedSet subSet(Character from, Character to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); }
/* 303:303 */    public CharSortedSet headSet(Character to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); }
/* 304:304 */    public CharSortedSet tailSet(Character from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/* 305:    */  }
/* 306:    */  
/* 313:    */  public static CharSortedSet unmodifiable(CharSortedSet s)
/* 314:    */  {
/* 315:315 */    return new UnmodifiableSortedSet(s);
/* 316:    */  }
/* 317:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharSortedSets
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */