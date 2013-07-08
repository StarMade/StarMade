/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*   4:    */import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*   5:    */import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*   6:    */import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*   7:    */import java.util.Comparator;
/*   8:    */import java.util.Map.Entry;
/*   9:    */
/*  63:    */public abstract class AbstractReference2DoubleSortedMap<K>
/*  64:    */  extends AbstractReference2DoubleMap<K>
/*  65:    */  implements Reference2DoubleSortedMap<K>
/*  66:    */{
/*  67:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  68:    */  
/*  69: 69 */  public ReferenceSortedSet<K> keySet() { return new KeySet(); }
/*  70:    */  
/*  71:    */  protected class KeySet extends AbstractReferenceSortedSet<K> { protected KeySet() {}
/*  72:    */    
/*  73: 73 */    public boolean contains(Object k) { return AbstractReference2DoubleSortedMap.this.containsKey(k); }
/*  74: 74 */    public int size() { return AbstractReference2DoubleSortedMap.this.size(); }
/*  75: 75 */    public void clear() { AbstractReference2DoubleSortedMap.this.clear(); }
/*  76: 76 */    public Comparator<? super K> comparator() { return AbstractReference2DoubleSortedMap.this.comparator(); }
/*  77: 77 */    public K first() { return AbstractReference2DoubleSortedMap.this.firstKey(); }
/*  78: 78 */    public K last() { return AbstractReference2DoubleSortedMap.this.lastKey(); }
/*  79: 79 */    public ReferenceSortedSet<K> headSet(K to) { return AbstractReference2DoubleSortedMap.this.headMap(to).keySet(); }
/*  80: 80 */    public ReferenceSortedSet<K> tailSet(K from) { return AbstractReference2DoubleSortedMap.this.tailMap(from).keySet(); }
/*  81: 81 */    public ReferenceSortedSet<K> subSet(K from, K to) { return AbstractReference2DoubleSortedMap.this.subMap(from, to).keySet(); }
/*  82: 82 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new AbstractReference2DoubleSortedMap.KeySetIterator(AbstractReference2DoubleSortedMap.this.entrySet().iterator(new AbstractReference2DoubleMap.BasicEntry(from, 0.0D))); }
/*  83: 83 */    public ObjectBidirectionalIterator<K> iterator() { return new AbstractReference2DoubleSortedMap.KeySetIterator(AbstractReference2DoubleSortedMap.this.entrySet().iterator()); }
/*  84:    */  }
/*  85:    */  
/*  87:    */  protected static class KeySetIterator<K>
/*  88:    */    extends AbstractObjectBidirectionalIterator<K>
/*  89:    */  {
/*  90:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, Double>> i;
/*  91:    */    
/*  93: 93 */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Double>> i) { this.i = i; }
/*  94:    */    
/*  95: 95 */    public K next() { return ((Map.Entry)this.i.next()).getKey(); }
/*  96: 96 */    public K previous() { return ((Map.Entry)this.i.previous()).getKey(); }
/*  97: 97 */    public boolean hasNext() { return this.i.hasNext(); }
/*  98: 98 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/*  99:    */  }
/* 100:    */  
/* 112:112 */  public DoubleCollection values() { return new ValuesCollection(); }
/* 113:    */  
/* 114:    */  protected class ValuesCollection extends AbstractDoubleCollection { protected ValuesCollection() {}
/* 115:    */    
/* 116:116 */    public DoubleIterator iterator() { return new AbstractReference2DoubleSortedMap.ValuesIterator(AbstractReference2DoubleSortedMap.this.entrySet().iterator()); }
/* 117:117 */    public boolean contains(double k) { return AbstractReference2DoubleSortedMap.this.containsValue(k); }
/* 118:118 */    public int size() { return AbstractReference2DoubleSortedMap.this.size(); }
/* 119:119 */    public void clear() { AbstractReference2DoubleSortedMap.this.clear(); }
/* 120:    */  }
/* 121:    */  
/* 123:    */  protected static class ValuesIterator<K>
/* 124:    */    extends AbstractDoubleIterator
/* 125:    */  {
/* 126:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, Double>> i;
/* 127:    */    
/* 129:129 */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Double>> i) { this.i = i; }
/* 130:    */    
/* 131:131 */    public double nextDouble() { return ((Double)((Map.Entry)this.i.next()).getValue()).doubleValue(); }
/* 132:132 */    public boolean hasNext() { return this.i.hasNext(); }
/* 133:    */  }
/* 134:    */  
/* 135:    */  public ObjectSortedSet<Map.Entry<K, Double>> entrySet() {
/* 136:136 */    return reference2DoubleEntrySet();
/* 137:    */  }
/* 138:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */