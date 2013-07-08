/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*   4:    */import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  50:    */public abstract class AbstractLong2ObjectSortedMap<V>
/*  51:    */  extends AbstractLong2ObjectMap<V>
/*  52:    */  implements Long2ObjectSortedMap<V>
/*  53:    */{
/*  54:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  55:    */  
/*  56:    */  public Long2ObjectSortedMap<V> headMap(Long to)
/*  57:    */  {
/*  58: 58 */    return headMap(to.longValue());
/*  59:    */  }
/*  60:    */  
/*  61:    */  public Long2ObjectSortedMap<V> tailMap(Long from) {
/*  62: 62 */    return tailMap(from.longValue());
/*  63:    */  }
/*  64:    */  
/*  65:    */  public Long2ObjectSortedMap<V> subMap(Long from, Long to) {
/*  66: 66 */    return subMap(from.longValue(), to.longValue());
/*  67:    */  }
/*  68:    */  
/*  69:    */  public Long firstKey() {
/*  70: 70 */    return Long.valueOf(firstLongKey());
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Long lastKey() {
/*  74: 74 */    return Long.valueOf(lastLongKey());
/*  75:    */  }
/*  76:    */  
/*  88: 88 */  public LongSortedSet keySet() { return new KeySet(); }
/*  89:    */  
/*  90:    */  protected class KeySet extends AbstractLongSortedSet { protected KeySet() {}
/*  91:    */    
/*  92: 92 */    public boolean contains(long k) { return AbstractLong2ObjectSortedMap.this.containsKey(k); }
/*  93: 93 */    public int size() { return AbstractLong2ObjectSortedMap.this.size(); }
/*  94: 94 */    public void clear() { AbstractLong2ObjectSortedMap.this.clear(); }
/*  95: 95 */    public LongComparator comparator() { return AbstractLong2ObjectSortedMap.this.comparator(); }
/*  96: 96 */    public long firstLong() { return AbstractLong2ObjectSortedMap.this.firstLongKey(); }
/*  97: 97 */    public long lastLong() { return AbstractLong2ObjectSortedMap.this.lastLongKey(); }
/*  98:    */    
/*  99: 99 */    public LongSortedSet headSet(long to) { return AbstractLong2ObjectSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public LongSortedSet tailSet(long from) { return AbstractLong2ObjectSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public LongSortedSet subSet(long from, long to) { return AbstractLong2ObjectSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public LongBidirectionalIterator iterator(long from) { return new AbstractLong2ObjectSortedMap.KeySetIterator(AbstractLong2ObjectSortedMap.this.entrySet().iterator(new AbstractLong2ObjectMap.BasicEntry(from, null))); }
/* 104:104 */    public LongBidirectionalIterator iterator() { return new AbstractLong2ObjectSortedMap.KeySetIterator(AbstractLong2ObjectSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator<V>
/* 110:    */    extends AbstractLongBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Long, V>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, V>> i)
/* 117:    */    {
/* 118:118 */      this.i = i;
/* 119:    */    }
/* 120:    */    
/* 121:121 */    public long nextLong() { return ((Long)((Map.Entry)this.i.next()).getKey()).longValue(); }
/* 122:122 */    public long previousLong() { return ((Long)((Map.Entry)this.i.previous()).getKey()).longValue(); }
/* 123:    */    
/* 124:124 */    public boolean hasNext() { return this.i.hasNext(); }
/* 125:125 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 126:    */  }
/* 127:    */  
/* 143:143 */  public ObjectCollection<V> values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractObjectCollection<V> {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public ObjectIterator<V> iterator() { return new AbstractLong2ObjectSortedMap.ValuesIterator(AbstractLong2ObjectSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(Object k) { return AbstractLong2ObjectSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractLong2ObjectSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractLong2ObjectSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator<V>
/* 157:    */    extends AbstractObjectIterator<V>
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Long, V>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, V>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public V next() { return ((Map.Entry)this.i.next()).getValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Long, V>> entrySet()
/* 173:    */  {
/* 174:174 */    return long2ObjectEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */