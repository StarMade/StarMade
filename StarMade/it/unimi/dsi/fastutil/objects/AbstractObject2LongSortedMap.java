/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*   4:    */import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*   5:    */import it.unimi.dsi.fastutil.longs.LongCollection;
/*   6:    */import it.unimi.dsi.fastutil.longs.LongIterator;
/*   7:    */import java.util.Comparator;
/*   8:    */import java.util.Map.Entry;
/*   9:    */
/*  63:    */public abstract class AbstractObject2LongSortedMap<K>
/*  64:    */  extends AbstractObject2LongMap<K>
/*  65:    */  implements Object2LongSortedMap<K>
/*  66:    */{
/*  67:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  68:    */  
/*  69: 69 */  public ObjectSortedSet<K> keySet() { return new KeySet(); }
/*  70:    */  
/*  71:    */  protected class KeySet extends AbstractObjectSortedSet<K> { protected KeySet() {}
/*  72:    */    
/*  73: 73 */    public boolean contains(Object k) { return AbstractObject2LongSortedMap.this.containsKey(k); }
/*  74: 74 */    public int size() { return AbstractObject2LongSortedMap.this.size(); }
/*  75: 75 */    public void clear() { AbstractObject2LongSortedMap.this.clear(); }
/*  76: 76 */    public Comparator<? super K> comparator() { return AbstractObject2LongSortedMap.this.comparator(); }
/*  77: 77 */    public K first() { return AbstractObject2LongSortedMap.this.firstKey(); }
/*  78: 78 */    public K last() { return AbstractObject2LongSortedMap.this.lastKey(); }
/*  79: 79 */    public ObjectSortedSet<K> headSet(K to) { return AbstractObject2LongSortedMap.this.headMap(to).keySet(); }
/*  80: 80 */    public ObjectSortedSet<K> tailSet(K from) { return AbstractObject2LongSortedMap.this.tailMap(from).keySet(); }
/*  81: 81 */    public ObjectSortedSet<K> subSet(K from, K to) { return AbstractObject2LongSortedMap.this.subMap(from, to).keySet(); }
/*  82: 82 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new AbstractObject2LongSortedMap.KeySetIterator(AbstractObject2LongSortedMap.this.entrySet().iterator(new AbstractObject2LongMap.BasicEntry(from, 0L))); }
/*  83: 83 */    public ObjectBidirectionalIterator<K> iterator() { return new AbstractObject2LongSortedMap.KeySetIterator(AbstractObject2LongSortedMap.this.entrySet().iterator()); }
/*  84:    */  }
/*  85:    */  
/*  87:    */  protected static class KeySetIterator<K>
/*  88:    */    extends AbstractObjectBidirectionalIterator<K>
/*  89:    */  {
/*  90:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, Long>> i;
/*  91:    */    
/*  93: 93 */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Long>> i) { this.i = i; }
/*  94:    */    
/*  95: 95 */    public K next() { return ((Map.Entry)this.i.next()).getKey(); }
/*  96: 96 */    public K previous() { return ((Map.Entry)this.i.previous()).getKey(); }
/*  97: 97 */    public boolean hasNext() { return this.i.hasNext(); }
/*  98: 98 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/*  99:    */  }
/* 100:    */  
/* 112:112 */  public LongCollection values() { return new ValuesCollection(); }
/* 113:    */  
/* 114:    */  protected class ValuesCollection extends AbstractLongCollection { protected ValuesCollection() {}
/* 115:    */    
/* 116:116 */    public LongIterator iterator() { return new AbstractObject2LongSortedMap.ValuesIterator(AbstractObject2LongSortedMap.this.entrySet().iterator()); }
/* 117:117 */    public boolean contains(long k) { return AbstractObject2LongSortedMap.this.containsValue(k); }
/* 118:118 */    public int size() { return AbstractObject2LongSortedMap.this.size(); }
/* 119:119 */    public void clear() { AbstractObject2LongSortedMap.this.clear(); }
/* 120:    */  }
/* 121:    */  
/* 123:    */  protected static class ValuesIterator<K>
/* 124:    */    extends AbstractLongIterator
/* 125:    */  {
/* 126:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, Long>> i;
/* 127:    */    
/* 129:129 */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Long>> i) { this.i = i; }
/* 130:    */    
/* 131:131 */    public long nextLong() { return ((Long)((Map.Entry)this.i.next()).getValue()).longValue(); }
/* 132:132 */    public boolean hasNext() { return this.i.hasNext(); }
/* 133:    */  }
/* 134:    */  
/* 135:    */  public ObjectSortedSet<Map.Entry<K, Long>> entrySet() {
/* 136:136 */    return object2LongEntrySet();
/* 137:    */  }
/* 138:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */