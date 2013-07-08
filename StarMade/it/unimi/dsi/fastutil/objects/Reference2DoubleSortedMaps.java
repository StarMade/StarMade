/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Comparator;
/*   5:    */import java.util.Map.Entry;
/*   6:    */import java.util.NoSuchElementException;
/*   7:    */
/*  59:    */public class Reference2DoubleSortedMaps
/*  60:    */{
/*  61:    */  public static <K> Comparator<? super Map.Entry<K, ?>> entryComparator(Comparator<K> comparator)
/*  62:    */  {
/*  63: 63 */    new Comparator() {
/*  64:    */      public int compare(Map.Entry<K, ?> x, Map.Entry<K, ?> y) {
/*  65: 65 */        return this.val$comparator.compare(x.getKey(), y.getKey());
/*  66:    */      }
/*  67:    */    };
/*  68:    */  }
/*  69:    */  
/*  71:    */  public static class EmptySortedMap<K>
/*  72:    */    extends Reference2DoubleMaps.EmptyMap<K>
/*  73:    */    implements Reference2DoubleSortedMap<K>, Serializable, Cloneable
/*  74:    */  {
/*  75:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  76:    */    
/*  77: 77 */    public Comparator<? super K> comparator() { return null; }
/*  78:    */    
/*  79: 79 */    public ObjectSortedSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  80:    */    
/*  81: 81 */    public ObjectSortedSet<Map.Entry<K, Double>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  82:    */    
/*  83: 83 */    public ReferenceSortedSet<K> keySet() { return ReferenceSortedSets.EMPTY_SET; }
/*  84:    */    
/*  85: 85 */    public Reference2DoubleSortedMap<K> subMap(K from, K to) { return Reference2DoubleSortedMaps.EMPTY_MAP; }
/*  86:    */    
/*  87: 87 */    public Reference2DoubleSortedMap<K> headMap(K to) { return Reference2DoubleSortedMaps.EMPTY_MAP; }
/*  88:    */    
/*  89: 89 */    public Reference2DoubleSortedMap<K> tailMap(K from) { return Reference2DoubleSortedMaps.EMPTY_MAP; }
/*  90: 90 */    public K firstKey() { throw new NoSuchElementException(); }
/*  91: 91 */    public K lastKey() { throw new NoSuchElementException(); }
/*  92:    */  }
/*  93:    */  
/* 103:103 */  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/* 104:    */  
/* 107:    */  public static class Singleton<K>
/* 108:    */    extends Reference2DoubleMaps.Singleton<K>
/* 109:    */    implements Reference2DoubleSortedMap<K>, Serializable, Cloneable
/* 110:    */  {
/* 111:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 112:    */    
/* 114:    */    protected final Comparator<? super K> comparator;
/* 115:    */    
/* 117:    */    protected Singleton(K key, double value, Comparator<? super K> comparator)
/* 118:    */    {
/* 119:119 */      super(value);
/* 120:120 */      this.comparator = comparator;
/* 121:    */    }
/* 122:    */    
/* 123:    */    protected Singleton(K key, double value) {
/* 124:124 */      this(key, value, null);
/* 125:    */    }
/* 126:    */    
/* 127:    */    final int compare(K k1, K k2)
/* 128:    */    {
/* 129:129 */      return this.comparator == null ? ((Comparable)k1).compareTo(k2) : this.comparator.compare(k1, k2);
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public Comparator<? super K> comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public ObjectSortedSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet() {
/* 135:135 */      if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Reference2DoubleMaps.Singleton.SingletonEntry(this), Reference2DoubleSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries; }
/* 136:    */    
/* 137:137 */    public ObjectSortedSet<Map.Entry<K, Double>> entrySet() { return reference2DoubleEntrySet(); }
/* 138:    */    
/* 139:139 */    public ReferenceSortedSet<K> keySet() { if (this.keys == null) this.keys = ReferenceSortedSets.singleton(this.key, this.comparator); return (ReferenceSortedSet)this.keys;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    public Reference2DoubleSortedMap<K> subMap(K from, K to) { if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Reference2DoubleSortedMaps.EMPTY_MAP;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    public Reference2DoubleSortedMap<K> headMap(K to) { if (compare(this.key, to) < 0) return this; return Reference2DoubleSortedMaps.EMPTY_MAP;
/* 146:    */    }
/* 147:    */    
/* 148:148 */    public Reference2DoubleSortedMap<K> tailMap(K from) { if (compare(from, this.key) <= 0) return this; return Reference2DoubleSortedMaps.EMPTY_MAP; }
/* 149:    */    
/* 150:150 */    public K firstKey() { return this.key; }
/* 151:151 */    public K lastKey() { return this.key; }
/* 152:    */  }
/* 153:    */  
/* 160:    */  public static <K> Reference2DoubleSortedMap<K> singleton(K key, Double value)
/* 161:    */  {
/* 162:162 */    return new Singleton(key, value.doubleValue());
/* 163:    */  }
/* 164:    */  
/* 172:    */  public static <K> Reference2DoubleSortedMap<K> singleton(K key, Double value, Comparator<? super K> comparator)
/* 173:    */  {
/* 174:174 */    return new Singleton(key, value.doubleValue(), comparator);
/* 175:    */  }
/* 176:    */  
/* 183:    */  public static <K> Reference2DoubleSortedMap<K> singleton(K key, double value)
/* 184:    */  {
/* 185:185 */    return new Singleton(key, value);
/* 186:    */  }
/* 187:    */  
/* 197:197 */  public static <K> Reference2DoubleSortedMap<K> singleton(K key, double value, Comparator<? super K> comparator) { return new Singleton(key, value, comparator); }
/* 198:    */  
/* 199:    */  public static class SynchronizedSortedMap<K> extends Reference2DoubleMaps.SynchronizedMap<K> implements Reference2DoubleSortedMap<K>, Serializable {
/* 200:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 201:    */    protected final Reference2DoubleSortedMap<K> sortedMap;
/* 202:    */    
/* 203:    */    protected SynchronizedSortedMap(Reference2DoubleSortedMap<K> m, Object sync) {
/* 204:204 */      super(sync);
/* 205:205 */      this.sortedMap = m;
/* 206:    */    }
/* 207:    */    
/* 208:208 */    protected SynchronizedSortedMap(Reference2DoubleSortedMap<K> m) { super();
/* 209:209 */      this.sortedMap = m; }
/* 210:    */    
/* 211:211 */    public Comparator<? super K> comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); } }
/* 212:212 */    public ObjectSortedSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.reference2DoubleEntrySet(), this.sync); return (ObjectSortedSet)this.entries; }
/* 213:    */    
/* 214:214 */    public ObjectSortedSet<Map.Entry<K, Double>> entrySet() { return reference2DoubleEntrySet(); }
/* 215:215 */    public ReferenceSortedSet<K> keySet() { if (this.keys == null) this.keys = ReferenceSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (ReferenceSortedSet)this.keys; }
/* 216:216 */    public Reference2DoubleSortedMap<K> subMap(K from, K to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 217:217 */    public Reference2DoubleSortedMap<K> headMap(K to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 218:218 */    public Reference2DoubleSortedMap<K> tailMap(K from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 219:219 */    public K firstKey() { synchronized (this.sync) { return this.sortedMap.firstKey(); } }
/* 220:220 */    public K lastKey() { synchronized (this.sync) { return this.sortedMap.lastKey();
/* 221:    */      }
/* 222:    */    }
/* 223:    */  }
/* 224:    */  
/* 226:    */  public static <K> Reference2DoubleSortedMap<K> synchronize(Reference2DoubleSortedMap<K> m)
/* 227:    */  {
/* 228:228 */    return new SynchronizedSortedMap(m);
/* 229:    */  }
/* 230:    */  
/* 236:236 */  public static <K> Reference2DoubleSortedMap<K> synchronize(Reference2DoubleSortedMap<K> m, Object sync) { return new SynchronizedSortedMap(m, sync); }
/* 237:    */  
/* 238:    */  public static class UnmodifiableSortedMap<K> extends Reference2DoubleMaps.UnmodifiableMap<K> implements Reference2DoubleSortedMap<K>, Serializable {
/* 239:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 240:    */    protected final Reference2DoubleSortedMap<K> sortedMap;
/* 241:    */    
/* 242:242 */    protected UnmodifiableSortedMap(Reference2DoubleSortedMap<K> m) { super();
/* 243:243 */      this.sortedMap = m; }
/* 244:    */    
/* 245:245 */    public Comparator<? super K> comparator() { return this.sortedMap.comparator(); }
/* 246:246 */    public ObjectSortedSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.reference2DoubleEntrySet()); return (ObjectSortedSet)this.entries; }
/* 247:    */    
/* 248:248 */    public ObjectSortedSet<Map.Entry<K, Double>> entrySet() { return reference2DoubleEntrySet(); }
/* 249:249 */    public ReferenceSortedSet<K> keySet() { if (this.keys == null) this.keys = ReferenceSortedSets.unmodifiable(this.sortedMap.keySet()); return (ReferenceSortedSet)this.keys; }
/* 250:250 */    public Reference2DoubleSortedMap<K> subMap(K from, K to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 251:251 */    public Reference2DoubleSortedMap<K> headMap(K to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 252:252 */    public Reference2DoubleSortedMap<K> tailMap(K from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 253:253 */    public K firstKey() { return this.sortedMap.firstKey(); }
/* 254:254 */    public K lastKey() { return this.sortedMap.lastKey(); }
/* 255:    */  }
/* 256:    */  
/* 260:    */  public static <K> Reference2DoubleSortedMap<K> unmodifiable(Reference2DoubleSortedMap<K> m)
/* 261:    */  {
/* 262:262 */    return new UnmodifiableSortedMap(m);
/* 263:    */  }
/* 264:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2DoubleSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */