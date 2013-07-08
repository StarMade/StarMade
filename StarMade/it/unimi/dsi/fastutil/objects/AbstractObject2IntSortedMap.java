/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
/*   4:    */import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
/*   5:    */import it.unimi.dsi.fastutil.ints.IntCollection;
/*   6:    */import it.unimi.dsi.fastutil.ints.IntIterator;
/*   7:    */import java.util.Comparator;
/*   8:    */import java.util.Map.Entry;
/*   9:    */
/*  63:    */public abstract class AbstractObject2IntSortedMap<K>
/*  64:    */  extends AbstractObject2IntMap<K>
/*  65:    */  implements Object2IntSortedMap<K>
/*  66:    */{
/*  67:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  68:    */  
/*  69: 69 */  public ObjectSortedSet<K> keySet() { return new KeySet(); }
/*  70:    */  
/*  71:    */  protected class KeySet extends AbstractObjectSortedSet<K> { protected KeySet() {}
/*  72:    */    
/*  73: 73 */    public boolean contains(Object k) { return AbstractObject2IntSortedMap.this.containsKey(k); }
/*  74: 74 */    public int size() { return AbstractObject2IntSortedMap.this.size(); }
/*  75: 75 */    public void clear() { AbstractObject2IntSortedMap.this.clear(); }
/*  76: 76 */    public Comparator<? super K> comparator() { return AbstractObject2IntSortedMap.this.comparator(); }
/*  77: 77 */    public K first() { return AbstractObject2IntSortedMap.this.firstKey(); }
/*  78: 78 */    public K last() { return AbstractObject2IntSortedMap.this.lastKey(); }
/*  79: 79 */    public ObjectSortedSet<K> headSet(K to) { return AbstractObject2IntSortedMap.this.headMap(to).keySet(); }
/*  80: 80 */    public ObjectSortedSet<K> tailSet(K from) { return AbstractObject2IntSortedMap.this.tailMap(from).keySet(); }
/*  81: 81 */    public ObjectSortedSet<K> subSet(K from, K to) { return AbstractObject2IntSortedMap.this.subMap(from, to).keySet(); }
/*  82: 82 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new AbstractObject2IntSortedMap.KeySetIterator(AbstractObject2IntSortedMap.this.entrySet().iterator(new AbstractObject2IntMap.BasicEntry(from, 0))); }
/*  83: 83 */    public ObjectBidirectionalIterator<K> iterator() { return new AbstractObject2IntSortedMap.KeySetIterator(AbstractObject2IntSortedMap.this.entrySet().iterator()); }
/*  84:    */  }
/*  85:    */  
/*  87:    */  protected static class KeySetIterator<K>
/*  88:    */    extends AbstractObjectBidirectionalIterator<K>
/*  89:    */  {
/*  90:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, Integer>> i;
/*  91:    */    
/*  93: 93 */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Integer>> i) { this.i = i; }
/*  94:    */    
/*  95: 95 */    public K next() { return ((Map.Entry)this.i.next()).getKey(); }
/*  96: 96 */    public K previous() { return ((Map.Entry)this.i.previous()).getKey(); }
/*  97: 97 */    public boolean hasNext() { return this.i.hasNext(); }
/*  98: 98 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/*  99:    */  }
/* 100:    */  
/* 112:112 */  public IntCollection values() { return new ValuesCollection(); }
/* 113:    */  
/* 114:    */  protected class ValuesCollection extends AbstractIntCollection { protected ValuesCollection() {}
/* 115:    */    
/* 116:116 */    public IntIterator iterator() { return new AbstractObject2IntSortedMap.ValuesIterator(AbstractObject2IntSortedMap.this.entrySet().iterator()); }
/* 117:117 */    public boolean contains(int k) { return AbstractObject2IntSortedMap.this.containsValue(k); }
/* 118:118 */    public int size() { return AbstractObject2IntSortedMap.this.size(); }
/* 119:119 */    public void clear() { AbstractObject2IntSortedMap.this.clear(); }
/* 120:    */  }
/* 121:    */  
/* 123:    */  protected static class ValuesIterator<K>
/* 124:    */    extends AbstractIntIterator
/* 125:    */  {
/* 126:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, Integer>> i;
/* 127:    */    
/* 129:129 */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Integer>> i) { this.i = i; }
/* 130:    */    
/* 131:131 */    public int nextInt() { return ((Integer)((Map.Entry)this.i.next()).getValue()).intValue(); }
/* 132:132 */    public boolean hasNext() { return this.i.hasNext(); }
/* 133:    */  }
/* 134:    */  
/* 135:    */  public ObjectSortedSet<Map.Entry<K, Integer>> entrySet() {
/* 136:136 */    return object2IntEntrySet();
/* 137:    */  }
/* 138:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2IntSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */