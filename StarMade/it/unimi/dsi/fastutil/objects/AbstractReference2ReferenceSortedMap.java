/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.util.Comparator;
/*   4:    */import java.util.Map.Entry;
/*   5:    */
/*  62:    */public abstract class AbstractReference2ReferenceSortedMap<K, V>
/*  63:    */  extends AbstractReference2ReferenceMap<K, V>
/*  64:    */  implements Reference2ReferenceSortedMap<K, V>
/*  65:    */{
/*  66:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  67:    */  
/*  68: 68 */  public ReferenceSortedSet<K> keySet() { return new KeySet(); }
/*  69:    */  
/*  70:    */  protected class KeySet extends AbstractReferenceSortedSet<K> { protected KeySet() {}
/*  71:    */    
/*  72: 72 */    public boolean contains(Object k) { return AbstractReference2ReferenceSortedMap.this.containsKey(k); }
/*  73: 73 */    public int size() { return AbstractReference2ReferenceSortedMap.this.size(); }
/*  74: 74 */    public void clear() { AbstractReference2ReferenceSortedMap.this.clear(); }
/*  75: 75 */    public Comparator<? super K> comparator() { return AbstractReference2ReferenceSortedMap.this.comparator(); }
/*  76: 76 */    public K first() { return AbstractReference2ReferenceSortedMap.this.firstKey(); }
/*  77: 77 */    public K last() { return AbstractReference2ReferenceSortedMap.this.lastKey(); }
/*  78: 78 */    public ReferenceSortedSet<K> headSet(K to) { return AbstractReference2ReferenceSortedMap.this.headMap(to).keySet(); }
/*  79: 79 */    public ReferenceSortedSet<K> tailSet(K from) { return AbstractReference2ReferenceSortedMap.this.tailMap(from).keySet(); }
/*  80: 80 */    public ReferenceSortedSet<K> subSet(K from, K to) { return AbstractReference2ReferenceSortedMap.this.subMap(from, to).keySet(); }
/*  81: 81 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new AbstractReference2ReferenceSortedMap.KeySetIterator(AbstractReference2ReferenceSortedMap.this.entrySet().iterator(new AbstractReference2ReferenceMap.BasicEntry(from, null))); }
/*  82: 82 */    public ObjectBidirectionalIterator<K> iterator() { return new AbstractReference2ReferenceSortedMap.KeySetIterator(AbstractReference2ReferenceSortedMap.this.entrySet().iterator()); }
/*  83:    */  }
/*  84:    */  
/*  86:    */  protected static class KeySetIterator<K, V>
/*  87:    */    extends AbstractObjectBidirectionalIterator<K>
/*  88:    */  {
/*  89:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, V>> i;
/*  90:    */    
/*  92: 92 */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, V>> i) { this.i = i; }
/*  93:    */    
/*  94: 94 */    public K next() { return ((Map.Entry)this.i.next()).getKey(); }
/*  95: 95 */    public K previous() { return ((Map.Entry)this.i.previous()).getKey(); }
/*  96: 96 */    public boolean hasNext() { return this.i.hasNext(); }
/*  97: 97 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/*  98:    */  }
/*  99:    */  
/* 111:111 */  public ReferenceCollection<V> values() { return new ValuesCollection(); }
/* 112:    */  
/* 113:    */  protected class ValuesCollection extends AbstractReferenceCollection<V> { protected ValuesCollection() {}
/* 114:    */    
/* 115:115 */    public ObjectIterator<V> iterator() { return new AbstractReference2ReferenceSortedMap.ValuesIterator(AbstractReference2ReferenceSortedMap.this.entrySet().iterator()); }
/* 116:116 */    public boolean contains(Object k) { return AbstractReference2ReferenceSortedMap.this.containsValue(k); }
/* 117:117 */    public int size() { return AbstractReference2ReferenceSortedMap.this.size(); }
/* 118:118 */    public void clear() { AbstractReference2ReferenceSortedMap.this.clear(); }
/* 119:    */  }
/* 120:    */  
/* 122:    */  protected static class ValuesIterator<K, V>
/* 123:    */    extends AbstractObjectIterator<V>
/* 124:    */  {
/* 125:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, V>> i;
/* 126:    */    
/* 128:128 */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, V>> i) { this.i = i; }
/* 129:    */    
/* 130:130 */    public V next() { return ((Map.Entry)this.i.next()).getValue(); }
/* 131:131 */    public boolean hasNext() { return this.i.hasNext(); }
/* 132:    */  }
/* 133:    */  
/* 134:    */  public ObjectSortedSet<Map.Entry<K, V>> entrySet() {
/* 135:135 */    return reference2ReferenceEntrySet();
/* 136:    */  }
/* 137:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */