/*   1:    */package it.unimi.dsi.fastutil.doubles;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*   4:    */import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   8:    */import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  50:    */public abstract class AbstractDouble2ReferenceSortedMap<V>
/*  51:    */  extends AbstractDouble2ReferenceMap<V>
/*  52:    */  implements Double2ReferenceSortedMap<V>
/*  53:    */{
/*  54:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  55:    */  
/*  56:    */  public Double2ReferenceSortedMap<V> headMap(Double to)
/*  57:    */  {
/*  58: 58 */    return headMap(to.doubleValue());
/*  59:    */  }
/*  60:    */  
/*  61:    */  public Double2ReferenceSortedMap<V> tailMap(Double from) {
/*  62: 62 */    return tailMap(from.doubleValue());
/*  63:    */  }
/*  64:    */  
/*  65:    */  public Double2ReferenceSortedMap<V> subMap(Double from, Double to) {
/*  66: 66 */    return subMap(from.doubleValue(), to.doubleValue());
/*  67:    */  }
/*  68:    */  
/*  69:    */  public Double firstKey() {
/*  70: 70 */    return Double.valueOf(firstDoubleKey());
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Double lastKey() {
/*  74: 74 */    return Double.valueOf(lastDoubleKey());
/*  75:    */  }
/*  76:    */  
/*  88: 88 */  public DoubleSortedSet keySet() { return new KeySet(); }
/*  89:    */  
/*  90:    */  protected class KeySet extends AbstractDoubleSortedSet { protected KeySet() {}
/*  91:    */    
/*  92: 92 */    public boolean contains(double k) { return AbstractDouble2ReferenceSortedMap.this.containsKey(k); }
/*  93: 93 */    public int size() { return AbstractDouble2ReferenceSortedMap.this.size(); }
/*  94: 94 */    public void clear() { AbstractDouble2ReferenceSortedMap.this.clear(); }
/*  95: 95 */    public DoubleComparator comparator() { return AbstractDouble2ReferenceSortedMap.this.comparator(); }
/*  96: 96 */    public double firstDouble() { return AbstractDouble2ReferenceSortedMap.this.firstDoubleKey(); }
/*  97: 97 */    public double lastDouble() { return AbstractDouble2ReferenceSortedMap.this.lastDoubleKey(); }
/*  98:    */    
/*  99: 99 */    public DoubleSortedSet headSet(double to) { return AbstractDouble2ReferenceSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public DoubleSortedSet tailSet(double from) { return AbstractDouble2ReferenceSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public DoubleSortedSet subSet(double from, double to) { return AbstractDouble2ReferenceSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public DoubleBidirectionalIterator iterator(double from) { return new AbstractDouble2ReferenceSortedMap.KeySetIterator(AbstractDouble2ReferenceSortedMap.this.entrySet().iterator(new AbstractDouble2ReferenceMap.BasicEntry(from, null))); }
/* 104:104 */    public DoubleBidirectionalIterator iterator() { return new AbstractDouble2ReferenceSortedMap.KeySetIterator(AbstractDouble2ReferenceSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator<V>
/* 110:    */    extends AbstractDoubleBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Double, V>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, V>> i)
/* 117:    */    {
/* 118:118 */      this.i = i;
/* 119:    */    }
/* 120:    */    
/* 121:121 */    public double nextDouble() { return ((Double)((Map.Entry)this.i.next()).getKey()).doubleValue(); }
/* 122:122 */    public double previousDouble() { return ((Double)((Map.Entry)this.i.previous()).getKey()).doubleValue(); }
/* 123:    */    
/* 124:124 */    public boolean hasNext() { return this.i.hasNext(); }
/* 125:125 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 126:    */  }
/* 127:    */  
/* 143:143 */  public ReferenceCollection<V> values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractReferenceCollection<V> {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public ObjectIterator<V> iterator() { return new AbstractDouble2ReferenceSortedMap.ValuesIterator(AbstractDouble2ReferenceSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(Object k) { return AbstractDouble2ReferenceSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractDouble2ReferenceSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractDouble2ReferenceSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator<V>
/* 157:    */    extends AbstractObjectIterator<V>
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Double, V>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, V>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public V next() { return ((Map.Entry)this.i.next()).getValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Double, V>> entrySet()
/* 173:    */  {
/* 174:174 */    return double2ReferenceEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */