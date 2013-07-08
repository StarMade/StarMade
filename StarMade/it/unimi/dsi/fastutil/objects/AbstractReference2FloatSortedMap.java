/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*   4:    */import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*   5:    */import it.unimi.dsi.fastutil.floats.FloatCollection;
/*   6:    */import it.unimi.dsi.fastutil.floats.FloatIterator;
/*   7:    */import java.util.Comparator;
/*   8:    */import java.util.Map.Entry;
/*   9:    */
/*  63:    */public abstract class AbstractReference2FloatSortedMap<K>
/*  64:    */  extends AbstractReference2FloatMap<K>
/*  65:    */  implements Reference2FloatSortedMap<K>
/*  66:    */{
/*  67:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  68:    */  
/*  69: 69 */  public ReferenceSortedSet<K> keySet() { return new KeySet(); }
/*  70:    */  
/*  71:    */  protected class KeySet extends AbstractReferenceSortedSet<K> { protected KeySet() {}
/*  72:    */    
/*  73: 73 */    public boolean contains(Object k) { return AbstractReference2FloatSortedMap.this.containsKey(k); }
/*  74: 74 */    public int size() { return AbstractReference2FloatSortedMap.this.size(); }
/*  75: 75 */    public void clear() { AbstractReference2FloatSortedMap.this.clear(); }
/*  76: 76 */    public Comparator<? super K> comparator() { return AbstractReference2FloatSortedMap.this.comparator(); }
/*  77: 77 */    public K first() { return AbstractReference2FloatSortedMap.this.firstKey(); }
/*  78: 78 */    public K last() { return AbstractReference2FloatSortedMap.this.lastKey(); }
/*  79: 79 */    public ReferenceSortedSet<K> headSet(K to) { return AbstractReference2FloatSortedMap.this.headMap(to).keySet(); }
/*  80: 80 */    public ReferenceSortedSet<K> tailSet(K from) { return AbstractReference2FloatSortedMap.this.tailMap(from).keySet(); }
/*  81: 81 */    public ReferenceSortedSet<K> subSet(K from, K to) { return AbstractReference2FloatSortedMap.this.subMap(from, to).keySet(); }
/*  82: 82 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new AbstractReference2FloatSortedMap.KeySetIterator(AbstractReference2FloatSortedMap.this.entrySet().iterator(new AbstractReference2FloatMap.BasicEntry(from, 0.0F))); }
/*  83: 83 */    public ObjectBidirectionalIterator<K> iterator() { return new AbstractReference2FloatSortedMap.KeySetIterator(AbstractReference2FloatSortedMap.this.entrySet().iterator()); }
/*  84:    */  }
/*  85:    */  
/*  87:    */  protected static class KeySetIterator<K>
/*  88:    */    extends AbstractObjectBidirectionalIterator<K>
/*  89:    */  {
/*  90:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, Float>> i;
/*  91:    */    
/*  93: 93 */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Float>> i) { this.i = i; }
/*  94:    */    
/*  95: 95 */    public K next() { return ((Map.Entry)this.i.next()).getKey(); }
/*  96: 96 */    public K previous() { return ((Map.Entry)this.i.previous()).getKey(); }
/*  97: 97 */    public boolean hasNext() { return this.i.hasNext(); }
/*  98: 98 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/*  99:    */  }
/* 100:    */  
/* 112:112 */  public FloatCollection values() { return new ValuesCollection(); }
/* 113:    */  
/* 114:    */  protected class ValuesCollection extends AbstractFloatCollection { protected ValuesCollection() {}
/* 115:    */    
/* 116:116 */    public FloatIterator iterator() { return new AbstractReference2FloatSortedMap.ValuesIterator(AbstractReference2FloatSortedMap.this.entrySet().iterator()); }
/* 117:117 */    public boolean contains(float k) { return AbstractReference2FloatSortedMap.this.containsValue(k); }
/* 118:118 */    public int size() { return AbstractReference2FloatSortedMap.this.size(); }
/* 119:119 */    public void clear() { AbstractReference2FloatSortedMap.this.clear(); }
/* 120:    */  }
/* 121:    */  
/* 123:    */  protected static class ValuesIterator<K>
/* 124:    */    extends AbstractFloatIterator
/* 125:    */  {
/* 126:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, Float>> i;
/* 127:    */    
/* 129:129 */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Float>> i) { this.i = i; }
/* 130:    */    
/* 131:131 */    public float nextFloat() { return ((Float)((Map.Entry)this.i.next()).getValue()).floatValue(); }
/* 132:132 */    public boolean hasNext() { return this.i.hasNext(); }
/* 133:    */  }
/* 134:    */  
/* 135:    */  public ObjectSortedSet<Map.Entry<K, Float>> entrySet() {
/* 136:136 */    return reference2FloatEntrySet();
/* 137:    */  }
/* 138:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */