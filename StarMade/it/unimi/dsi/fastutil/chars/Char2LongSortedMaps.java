/*   1:    */package it.unimi.dsi.fastutil.chars;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*   5:    */import java.io.Serializable;
/*   6:    */import java.util.Comparator;
/*   7:    */import java.util.Map.Entry;
/*   8:    */import java.util.NoSuchElementException;
/*   9:    */
/*  60:    */public class Char2LongSortedMaps
/*  61:    */{
/*  62:    */  public static Comparator<? super Map.Entry<Character, ?>> entryComparator(CharComparator comparator)
/*  63:    */  {
/*  64: 64 */    new Comparator() {
/*  65:    */      public int compare(Map.Entry<Character, ?> x, Map.Entry<Character, ?> y) {
/*  66: 66 */        return this.val$comparator.compare(x.getKey(), y.getKey());
/*  67:    */      }
/*  68:    */    };
/*  69:    */  }
/*  70:    */  
/*  72:    */  public static class EmptySortedMap
/*  73:    */    extends Char2LongMaps.EmptyMap
/*  74:    */    implements Char2LongSortedMap, Serializable, Cloneable
/*  75:    */  {
/*  76:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  77:    */    
/*  78: 78 */    public CharComparator comparator() { return null; }
/*  79:    */    
/*  80: 80 */    public ObjectSortedSet<Char2LongMap.Entry> char2LongEntrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    public ObjectSortedSet<Map.Entry<Character, Long>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  83:    */    
/*  84: 84 */    public CharSortedSet keySet() { return CharSortedSets.EMPTY_SET; }
/*  85:    */    
/*  86: 86 */    public Char2LongSortedMap subMap(char from, char to) { return Char2LongSortedMaps.EMPTY_MAP; }
/*  87:    */    
/*  88: 88 */    public Char2LongSortedMap headMap(char to) { return Char2LongSortedMaps.EMPTY_MAP; }
/*  89:    */    
/*  90: 90 */    public Char2LongSortedMap tailMap(char from) { return Char2LongSortedMaps.EMPTY_MAP; }
/*  91: 91 */    public char firstCharKey() { throw new NoSuchElementException(); }
/*  92: 92 */    public char lastCharKey() { throw new NoSuchElementException(); }
/*  93: 93 */    public Char2LongSortedMap headMap(Character oto) { return headMap(oto.charValue()); }
/*  94: 94 */    public Char2LongSortedMap tailMap(Character ofrom) { return tailMap(ofrom.charValue()); }
/*  95: 95 */    public Char2LongSortedMap subMap(Character ofrom, Character oto) { return subMap(ofrom.charValue(), oto.charValue()); }
/*  96: 96 */    public Character firstKey() { return Character.valueOf(firstCharKey()); }
/*  97: 97 */    public Character lastKey() { return Character.valueOf(lastCharKey()); }
/*  98:    */  }
/*  99:    */  
/* 103:103 */  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/* 104:    */  
/* 107:    */  public static class Singleton
/* 108:    */    extends Char2LongMaps.Singleton
/* 109:    */    implements Char2LongSortedMap, Serializable, Cloneable
/* 110:    */  {
/* 111:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 112:    */    
/* 114:    */    protected final CharComparator comparator;
/* 115:    */    
/* 117:    */    protected Singleton(char key, long value, CharComparator comparator)
/* 118:    */    {
/* 119:119 */      super(value);
/* 120:120 */      this.comparator = comparator;
/* 121:    */    }
/* 122:    */    
/* 123:    */    protected Singleton(char key, long value) {
/* 124:124 */      this(key, value, null);
/* 125:    */    }
/* 126:    */    
/* 127:    */    final int compare(char k1, char k2)
/* 128:    */    {
/* 129:129 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public CharComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public ObjectSortedSet<Char2LongMap.Entry> char2LongEntrySet() {
/* 135:135 */      if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Char2LongMaps.Singleton.SingletonEntry(this), Char2LongSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries; }
/* 136:    */    
/* 137:137 */    public ObjectSortedSet<Map.Entry<Character, Long>> entrySet() { return char2LongEntrySet(); }
/* 138:    */    
/* 139:139 */    public CharSortedSet keySet() { if (this.keys == null) this.keys = CharSortedSets.singleton(this.key, this.comparator); return (CharSortedSet)this.keys;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    public Char2LongSortedMap subMap(char from, char to) { if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Char2LongSortedMaps.EMPTY_MAP;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    public Char2LongSortedMap headMap(char to) { if (compare(this.key, to) < 0) return this; return Char2LongSortedMaps.EMPTY_MAP;
/* 146:    */    }
/* 147:    */    
/* 148:148 */    public Char2LongSortedMap tailMap(char from) { if (compare(from, this.key) <= 0) return this; return Char2LongSortedMaps.EMPTY_MAP; }
/* 149:    */    
/* 150:150 */    public char firstCharKey() { return this.key; }
/* 151:151 */    public char lastCharKey() { return this.key; }
/* 152:    */    
/* 154:154 */    public Char2LongSortedMap headMap(Character oto) { return headMap(oto.charValue()); }
/* 155:155 */    public Char2LongSortedMap tailMap(Character ofrom) { return tailMap(ofrom.charValue()); }
/* 156:156 */    public Char2LongSortedMap subMap(Character ofrom, Character oto) { return subMap(ofrom.charValue(), oto.charValue()); }
/* 157:    */    
/* 158:158 */    public Character firstKey() { return Character.valueOf(firstCharKey()); }
/* 159:159 */    public Character lastKey() { return Character.valueOf(lastCharKey()); }
/* 160:    */  }
/* 161:    */  
/* 171:    */  public static Char2LongSortedMap singleton(Character key, Long value)
/* 172:    */  {
/* 173:173 */    return new Singleton(key.charValue(), value.longValue());
/* 174:    */  }
/* 175:    */  
/* 185:    */  public static Char2LongSortedMap singleton(Character key, Long value, CharComparator comparator)
/* 186:    */  {
/* 187:187 */    return new Singleton(key.charValue(), value.longValue(), comparator);
/* 188:    */  }
/* 189:    */  
/* 200:    */  public static Char2LongSortedMap singleton(char key, long value)
/* 201:    */  {
/* 202:202 */    return new Singleton(key, value);
/* 203:    */  }
/* 204:    */  
/* 214:    */  public static Char2LongSortedMap singleton(char key, long value, CharComparator comparator)
/* 215:    */  {
/* 216:216 */    return new Singleton(key, value, comparator);
/* 217:    */  }
/* 218:    */  
/* 220:    */  public static class SynchronizedSortedMap
/* 221:    */    extends Char2LongMaps.SynchronizedMap
/* 222:    */    implements Char2LongSortedMap, Serializable
/* 223:    */  {
/* 224:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 225:    */    
/* 226:    */    protected final Char2LongSortedMap sortedMap;
/* 227:    */    
/* 229:    */    protected SynchronizedSortedMap(Char2LongSortedMap m, Object sync)
/* 230:    */    {
/* 231:231 */      super(sync);
/* 232:232 */      this.sortedMap = m;
/* 233:    */    }
/* 234:    */    
/* 235:    */    protected SynchronizedSortedMap(Char2LongSortedMap m) {
/* 236:236 */      super();
/* 237:237 */      this.sortedMap = m;
/* 238:    */    }
/* 239:    */    
/* 240:240 */    public CharComparator comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); } }
/* 241:    */    
/* 242:242 */    public ObjectSortedSet<Char2LongMap.Entry> char2LongEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.char2LongEntrySet(), this.sync); return (ObjectSortedSet)this.entries; }
/* 243:    */    
/* 244:244 */    public ObjectSortedSet<Map.Entry<Character, Long>> entrySet() { return char2LongEntrySet(); }
/* 245:245 */    public CharSortedSet keySet() { if (this.keys == null) this.keys = CharSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (CharSortedSet)this.keys; }
/* 246:    */    
/* 247:247 */    public Char2LongSortedMap subMap(char from, char to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 248:248 */    public Char2LongSortedMap headMap(char to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 249:249 */    public Char2LongSortedMap tailMap(char from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 250:    */    
/* 251:251 */    public char firstCharKey() { synchronized (this.sync) { return this.sortedMap.firstCharKey(); } }
/* 252:252 */    public char lastCharKey() { synchronized (this.sync) { return this.sortedMap.lastCharKey();
/* 253:    */      } }
/* 254:    */    
/* 255:255 */    public Character firstKey() { synchronized (this.sync) { return (Character)this.sortedMap.firstKey(); } }
/* 256:256 */    public Character lastKey() { synchronized (this.sync) { return (Character)this.sortedMap.lastKey(); } }
/* 257:    */    
/* 258:258 */    public Char2LongSortedMap subMap(Character from, Character to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 259:259 */    public Char2LongSortedMap headMap(Character to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 260:260 */    public Char2LongSortedMap tailMap(Character from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 261:    */  }
/* 262:    */  
/* 270:    */  public static Char2LongSortedMap synchronize(Char2LongSortedMap m)
/* 271:    */  {
/* 272:272 */    return new SynchronizedSortedMap(m);
/* 273:    */  }
/* 274:    */  
/* 280:    */  public static Char2LongSortedMap synchronize(Char2LongSortedMap m, Object sync)
/* 281:    */  {
/* 282:282 */    return new SynchronizedSortedMap(m, sync);
/* 283:    */  }
/* 284:    */  
/* 286:    */  public static class UnmodifiableSortedMap
/* 287:    */    extends Char2LongMaps.UnmodifiableMap
/* 288:    */    implements Char2LongSortedMap, Serializable
/* 289:    */  {
/* 290:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 291:    */    
/* 292:    */    protected final Char2LongSortedMap sortedMap;
/* 293:    */    
/* 294:    */    protected UnmodifiableSortedMap(Char2LongSortedMap m)
/* 295:    */    {
/* 296:296 */      super();
/* 297:297 */      this.sortedMap = m;
/* 298:    */    }
/* 299:    */    
/* 300:300 */    public CharComparator comparator() { return this.sortedMap.comparator(); }
/* 301:    */    
/* 302:302 */    public ObjectSortedSet<Char2LongMap.Entry> char2LongEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.char2LongEntrySet()); return (ObjectSortedSet)this.entries; }
/* 303:    */    
/* 304:304 */    public ObjectSortedSet<Map.Entry<Character, Long>> entrySet() { return char2LongEntrySet(); }
/* 305:305 */    public CharSortedSet keySet() { if (this.keys == null) this.keys = CharSortedSets.unmodifiable(this.sortedMap.keySet()); return (CharSortedSet)this.keys; }
/* 306:    */    
/* 307:307 */    public Char2LongSortedMap subMap(char from, char to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 308:308 */    public Char2LongSortedMap headMap(char to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 309:309 */    public Char2LongSortedMap tailMap(char from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 310:    */    
/* 311:311 */    public char firstCharKey() { return this.sortedMap.firstCharKey(); }
/* 312:312 */    public char lastCharKey() { return this.sortedMap.lastCharKey(); }
/* 313:    */    
/* 315:315 */    public Character firstKey() { return (Character)this.sortedMap.firstKey(); }
/* 316:316 */    public Character lastKey() { return (Character)this.sortedMap.lastKey(); }
/* 317:    */    
/* 318:318 */    public Char2LongSortedMap subMap(Character from, Character to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 319:319 */    public Char2LongSortedMap headMap(Character to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 320:320 */    public Char2LongSortedMap tailMap(Character from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 321:    */  }
/* 322:    */  
/* 330:    */  public static Char2LongSortedMap unmodifiable(Char2LongSortedMap m)
/* 331:    */  {
/* 332:332 */    return new UnmodifiableSortedMap(m);
/* 333:    */  }
/* 334:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2LongSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */