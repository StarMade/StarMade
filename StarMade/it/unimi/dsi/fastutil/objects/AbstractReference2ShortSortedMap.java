/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
/*   4:    */import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
/*   5:    */import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*   6:    */import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*   7:    */import java.util.Comparator;
/*   8:    */import java.util.Map.Entry;
/*   9:    */
/*  63:    */public abstract class AbstractReference2ShortSortedMap<K>
/*  64:    */  extends AbstractReference2ShortMap<K>
/*  65:    */  implements Reference2ShortSortedMap<K>
/*  66:    */{
/*  67:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  68:    */  
/*  69: 69 */  public ReferenceSortedSet<K> keySet() { return new KeySet(); }
/*  70:    */  
/*  71:    */  protected class KeySet extends AbstractReferenceSortedSet<K> { protected KeySet() {}
/*  72:    */    
/*  73: 73 */    public boolean contains(Object k) { return AbstractReference2ShortSortedMap.this.containsKey(k); }
/*  74: 74 */    public int size() { return AbstractReference2ShortSortedMap.this.size(); }
/*  75: 75 */    public void clear() { AbstractReference2ShortSortedMap.this.clear(); }
/*  76: 76 */    public Comparator<? super K> comparator() { return AbstractReference2ShortSortedMap.this.comparator(); }
/*  77: 77 */    public K first() { return AbstractReference2ShortSortedMap.this.firstKey(); }
/*  78: 78 */    public K last() { return AbstractReference2ShortSortedMap.this.lastKey(); }
/*  79: 79 */    public ReferenceSortedSet<K> headSet(K to) { return AbstractReference2ShortSortedMap.this.headMap(to).keySet(); }
/*  80: 80 */    public ReferenceSortedSet<K> tailSet(K from) { return AbstractReference2ShortSortedMap.this.tailMap(from).keySet(); }
/*  81: 81 */    public ReferenceSortedSet<K> subSet(K from, K to) { return AbstractReference2ShortSortedMap.this.subMap(from, to).keySet(); }
/*  82: 82 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new AbstractReference2ShortSortedMap.KeySetIterator(AbstractReference2ShortSortedMap.this.entrySet().iterator(new AbstractReference2ShortMap.BasicEntry(from, (short)0))); }
/*  83: 83 */    public ObjectBidirectionalIterator<K> iterator() { return new AbstractReference2ShortSortedMap.KeySetIterator(AbstractReference2ShortSortedMap.this.entrySet().iterator()); }
/*  84:    */  }
/*  85:    */  
/*  87:    */  protected static class KeySetIterator<K>
/*  88:    */    extends AbstractObjectBidirectionalIterator<K>
/*  89:    */  {
/*  90:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, Short>> i;
/*  91:    */    
/*  93: 93 */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Short>> i) { this.i = i; }
/*  94:    */    
/*  95: 95 */    public K next() { return ((Map.Entry)this.i.next()).getKey(); }
/*  96: 96 */    public K previous() { return ((Map.Entry)this.i.previous()).getKey(); }
/*  97: 97 */    public boolean hasNext() { return this.i.hasNext(); }
/*  98: 98 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/*  99:    */  }
/* 100:    */  
/* 112:112 */  public ShortCollection values() { return new ValuesCollection(); }
/* 113:    */  
/* 114:    */  protected class ValuesCollection extends AbstractShortCollection { protected ValuesCollection() {}
/* 115:    */    
/* 116:116 */    public ShortIterator iterator() { return new AbstractReference2ShortSortedMap.ValuesIterator(AbstractReference2ShortSortedMap.this.entrySet().iterator()); }
/* 117:117 */    public boolean contains(short k) { return AbstractReference2ShortSortedMap.this.containsValue(k); }
/* 118:118 */    public int size() { return AbstractReference2ShortSortedMap.this.size(); }
/* 119:119 */    public void clear() { AbstractReference2ShortSortedMap.this.clear(); }
/* 120:    */  }
/* 121:    */  
/* 123:    */  protected static class ValuesIterator<K>
/* 124:    */    extends AbstractShortIterator
/* 125:    */  {
/* 126:    */    protected final ObjectBidirectionalIterator<Map.Entry<K, Short>> i;
/* 127:    */    
/* 129:129 */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Short>> i) { this.i = i; }
/* 130:    */    
/* 131:131 */    public short nextShort() { return ((Short)((Map.Entry)this.i.next()).getValue()).shortValue(); }
/* 132:132 */    public boolean hasNext() { return this.i.hasNext(); }
/* 133:    */  }
/* 134:    */  
/* 135:    */  public ObjectSortedSet<Map.Entry<K, Short>> entrySet() {
/* 136:136 */    return reference2ShortEntrySet();
/* 137:    */  }
/* 138:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2ShortSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */