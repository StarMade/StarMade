/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Comparator;
/*   5:    */import java.util.Map.Entry;
/*   6:    */import java.util.NoSuchElementException;
/*   7:    */
/*  58:    */public class Object2ReferenceSortedMaps
/*  59:    */{
/*  60:    */  public static <K> Comparator<? super Map.Entry<K, ?>> entryComparator(Comparator<K> comparator)
/*  61:    */  {
/*  62: 62 */    new Comparator() {
/*  63:    */      public int compare(Map.Entry<K, ?> x, Map.Entry<K, ?> y) {
/*  64: 64 */        return this.val$comparator.compare(x.getKey(), y.getKey());
/*  65:    */      }
/*  66:    */    };
/*  67:    */  }
/*  68:    */  
/*  70:    */  public static class EmptySortedMap<K, V>
/*  71:    */    extends Object2ReferenceMaps.EmptyMap<K, V>
/*  72:    */    implements Object2ReferenceSortedMap<K, V>, Serializable, Cloneable
/*  73:    */  {
/*  74:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  75:    */    
/*  76: 76 */    public Comparator<? super K> comparator() { return null; }
/*  77:    */    
/*  78: 78 */    public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  79:    */    
/*  80: 80 */    public ObjectSortedSet<Map.Entry<K, V>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    public ObjectSortedSet<K> keySet() { return ObjectSortedSets.EMPTY_SET; }
/*  83:    */    
/*  84: 84 */    public Object2ReferenceSortedMap<K, V> subMap(K from, K to) { return Object2ReferenceSortedMaps.EMPTY_MAP; }
/*  85:    */    
/*  86: 86 */    public Object2ReferenceSortedMap<K, V> headMap(K to) { return Object2ReferenceSortedMaps.EMPTY_MAP; }
/*  87:    */    
/*  88: 88 */    public Object2ReferenceSortedMap<K, V> tailMap(K from) { return Object2ReferenceSortedMaps.EMPTY_MAP; }
/*  89: 89 */    public K firstKey() { throw new NoSuchElementException(); }
/*  90: 90 */    public K lastKey() { throw new NoSuchElementException(); }
/*  91:    */  }
/*  92:    */  
/* 103:103 */  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/* 104:    */  
/* 107:    */  public static class Singleton<K, V>
/* 108:    */    extends Object2ReferenceMaps.Singleton<K, V>
/* 109:    */    implements Object2ReferenceSortedMap<K, V>, Serializable, Cloneable
/* 110:    */  {
/* 111:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 112:    */    
/* 114:    */    protected final Comparator<? super K> comparator;
/* 115:    */    
/* 117:    */    protected Singleton(K key, V value, Comparator<? super K> comparator)
/* 118:    */    {
/* 119:119 */      super(value);
/* 120:120 */      this.comparator = comparator;
/* 121:    */    }
/* 122:    */    
/* 123:    */    protected Singleton(K key, V value) {
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
/* 134:    */    public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() {
/* 135:135 */      if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Object2ReferenceMaps.Singleton.SingletonEntry(this), Object2ReferenceSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries; }
/* 136:    */    
/* 137:137 */    public ObjectSortedSet<Map.Entry<K, V>> entrySet() { return object2ReferenceEntrySet(); }
/* 138:    */    
/* 139:139 */    public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = ObjectSortedSets.singleton(this.key, this.comparator); return (ObjectSortedSet)this.keys;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    public Object2ReferenceSortedMap<K, V> subMap(K from, K to) { if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Object2ReferenceSortedMaps.EMPTY_MAP;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    public Object2ReferenceSortedMap<K, V> headMap(K to) { if (compare(this.key, to) < 0) return this; return Object2ReferenceSortedMaps.EMPTY_MAP;
/* 146:    */    }
/* 147:    */    
/* 148:148 */    public Object2ReferenceSortedMap<K, V> tailMap(K from) { if (compare(from, this.key) <= 0) return this; return Object2ReferenceSortedMaps.EMPTY_MAP; }
/* 149:    */    
/* 150:150 */    public K firstKey() { return this.key; }
/* 151:151 */    public K lastKey() { return this.key; }
/* 152:    */  }
/* 153:    */  
/* 160:    */  public static <K, V> Object2ReferenceSortedMap<K, V> singleton(K key, V value)
/* 161:    */  {
/* 162:162 */    return new Singleton(key, value);
/* 163:    */  }
/* 164:    */  
/* 174:174 */  public static <K, V> Object2ReferenceSortedMap<K, V> singleton(K key, V value, Comparator<? super K> comparator) { return new Singleton(key, value, comparator); }
/* 175:    */  
/* 176:    */  public static class SynchronizedSortedMap<K, V> extends Object2ReferenceMaps.SynchronizedMap<K, V> implements Object2ReferenceSortedMap<K, V>, Serializable {
/* 177:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 178:    */    protected final Object2ReferenceSortedMap<K, V> sortedMap;
/* 179:    */    
/* 180:    */    protected SynchronizedSortedMap(Object2ReferenceSortedMap<K, V> m, Object sync) {
/* 181:181 */      super(sync);
/* 182:182 */      this.sortedMap = m;
/* 183:    */    }
/* 184:    */    
/* 185:185 */    protected SynchronizedSortedMap(Object2ReferenceSortedMap<K, V> m) { super();
/* 186:186 */      this.sortedMap = m; }
/* 187:    */    
/* 188:188 */    public Comparator<? super K> comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); } }
/* 189:189 */    public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.object2ReferenceEntrySet(), this.sync); return (ObjectSortedSet)this.entries; }
/* 190:    */    
/* 191:191 */    public ObjectSortedSet<Map.Entry<K, V>> entrySet() { return object2ReferenceEntrySet(); }
/* 192:192 */    public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = ObjectSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (ObjectSortedSet)this.keys; }
/* 193:193 */    public Object2ReferenceSortedMap<K, V> subMap(K from, K to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 194:194 */    public Object2ReferenceSortedMap<K, V> headMap(K to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 195:195 */    public Object2ReferenceSortedMap<K, V> tailMap(K from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 196:196 */    public K firstKey() { synchronized (this.sync) { return this.sortedMap.firstKey(); } }
/* 197:197 */    public K lastKey() { synchronized (this.sync) { return this.sortedMap.lastKey();
/* 198:    */      }
/* 199:    */    }
/* 200:    */  }
/* 201:    */  
/* 203:    */  public static <K, V> Object2ReferenceSortedMap<K, V> synchronize(Object2ReferenceSortedMap<K, V> m)
/* 204:    */  {
/* 205:205 */    return new SynchronizedSortedMap(m);
/* 206:    */  }
/* 207:    */  
/* 213:213 */  public static <K, V> Object2ReferenceSortedMap<K, V> synchronize(Object2ReferenceSortedMap<K, V> m, Object sync) { return new SynchronizedSortedMap(m, sync); }
/* 214:    */  
/* 215:    */  public static class UnmodifiableSortedMap<K, V> extends Object2ReferenceMaps.UnmodifiableMap<K, V> implements Object2ReferenceSortedMap<K, V>, Serializable {
/* 216:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 217:    */    protected final Object2ReferenceSortedMap<K, V> sortedMap;
/* 218:    */    
/* 219:219 */    protected UnmodifiableSortedMap(Object2ReferenceSortedMap<K, V> m) { super();
/* 220:220 */      this.sortedMap = m; }
/* 221:    */    
/* 222:222 */    public Comparator<? super K> comparator() { return this.sortedMap.comparator(); }
/* 223:223 */    public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.object2ReferenceEntrySet()); return (ObjectSortedSet)this.entries; }
/* 224:    */    
/* 225:225 */    public ObjectSortedSet<Map.Entry<K, V>> entrySet() { return object2ReferenceEntrySet(); }
/* 226:226 */    public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = ObjectSortedSets.unmodifiable(this.sortedMap.keySet()); return (ObjectSortedSet)this.keys; }
/* 227:227 */    public Object2ReferenceSortedMap<K, V> subMap(K from, K to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 228:228 */    public Object2ReferenceSortedMap<K, V> headMap(K to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 229:229 */    public Object2ReferenceSortedMap<K, V> tailMap(K from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 230:230 */    public K firstKey() { return this.sortedMap.firstKey(); }
/* 231:231 */    public K lastKey() { return this.sortedMap.lastKey(); }
/* 232:    */  }
/* 233:    */  
/* 237:    */  public static <K, V> Object2ReferenceSortedMap<K, V> unmodifiable(Object2ReferenceSortedMap<K, V> m)
/* 238:    */  {
/* 239:239 */    return new UnmodifiableSortedMap(m);
/* 240:    */  }
/* 241:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */