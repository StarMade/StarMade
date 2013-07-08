/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*   4:    */import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*   5:    */import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*   6:    */import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractLong2DoubleSortedMap
/*  52:    */  extends AbstractLong2DoubleMap
/*  53:    */  implements Long2DoubleSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Long2DoubleSortedMap headMap(Long to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.longValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Long2DoubleSortedMap tailMap(Long from) {
/*  63: 63 */    return tailMap(from.longValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Long2DoubleSortedMap subMap(Long from, Long to) {
/*  67: 67 */    return subMap(from.longValue(), to.longValue());
/*  68:    */  }
/*  69:    */  
/*  70:    */  public Long firstKey() {
/*  71: 71 */    return Long.valueOf(firstLongKey());
/*  72:    */  }
/*  73:    */  
/*  74:    */  public Long lastKey() {
/*  75: 75 */    return Long.valueOf(lastLongKey());
/*  76:    */  }
/*  77:    */  
/*  89: 89 */  public LongSortedSet keySet() { return new KeySet(); }
/*  90:    */  
/*  91:    */  protected class KeySet extends AbstractLongSortedSet { protected KeySet() {}
/*  92:    */    
/*  93: 93 */    public boolean contains(long k) { return AbstractLong2DoubleSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractLong2DoubleSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractLong2DoubleSortedMap.this.clear(); }
/*  96: 96 */    public LongComparator comparator() { return AbstractLong2DoubleSortedMap.this.comparator(); }
/*  97: 97 */    public long firstLong() { return AbstractLong2DoubleSortedMap.this.firstLongKey(); }
/*  98: 98 */    public long lastLong() { return AbstractLong2DoubleSortedMap.this.lastLongKey(); }
/*  99: 99 */    public LongSortedSet headSet(long to) { return AbstractLong2DoubleSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public LongSortedSet tailSet(long from) { return AbstractLong2DoubleSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public LongSortedSet subSet(long from, long to) { return AbstractLong2DoubleSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public LongBidirectionalIterator iterator(long from) { return new AbstractLong2DoubleSortedMap.KeySetIterator(AbstractLong2DoubleSortedMap.this.entrySet().iterator(new AbstractLong2DoubleMap.BasicEntry(from, 0.0D))); }
/* 104:104 */    public LongBidirectionalIterator iterator() { return new AbstractLong2DoubleSortedMap.KeySetIterator(AbstractLong2DoubleSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractLongBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Long, Double>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Double>> i)
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
/* 143:143 */  public DoubleCollection values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractDoubleCollection {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public DoubleIterator iterator() { return new AbstractLong2DoubleSortedMap.ValuesIterator(AbstractLong2DoubleSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(double k) { return AbstractLong2DoubleSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractLong2DoubleSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractLong2DoubleSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractDoubleIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Long, Double>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Double>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public double nextDouble() { return ((Double)((Map.Entry)this.i.next()).getValue()).doubleValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Long, Double>> entrySet()
/* 173:    */  {
/* 174:174 */    return long2DoubleEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */