/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*   4:    */import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*   5:    */import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*   6:    */import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractInt2DoubleSortedMap
/*  52:    */  extends AbstractInt2DoubleMap
/*  53:    */  implements Int2DoubleSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Int2DoubleSortedMap headMap(Integer to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.intValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Int2DoubleSortedMap tailMap(Integer from) {
/*  63: 63 */    return tailMap(from.intValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Int2DoubleSortedMap subMap(Integer from, Integer to) {
/*  67: 67 */    return subMap(from.intValue(), to.intValue());
/*  68:    */  }
/*  69:    */  
/*  70:    */  public Integer firstKey() {
/*  71: 71 */    return Integer.valueOf(firstIntKey());
/*  72:    */  }
/*  73:    */  
/*  74:    */  public Integer lastKey() {
/*  75: 75 */    return Integer.valueOf(lastIntKey());
/*  76:    */  }
/*  77:    */  
/*  89: 89 */  public IntSortedSet keySet() { return new KeySet(); }
/*  90:    */  
/*  91:    */  protected class KeySet extends AbstractIntSortedSet { protected KeySet() {}
/*  92:    */    
/*  93: 93 */    public boolean contains(int k) { return AbstractInt2DoubleSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractInt2DoubleSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractInt2DoubleSortedMap.this.clear(); }
/*  96: 96 */    public IntComparator comparator() { return AbstractInt2DoubleSortedMap.this.comparator(); }
/*  97: 97 */    public int firstInt() { return AbstractInt2DoubleSortedMap.this.firstIntKey(); }
/*  98: 98 */    public int lastInt() { return AbstractInt2DoubleSortedMap.this.lastIntKey(); }
/*  99: 99 */    public IntSortedSet headSet(int to) { return AbstractInt2DoubleSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public IntSortedSet tailSet(int from) { return AbstractInt2DoubleSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public IntSortedSet subSet(int from, int to) { return AbstractInt2DoubleSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public IntBidirectionalIterator iterator(int from) { return new AbstractInt2DoubleSortedMap.KeySetIterator(AbstractInt2DoubleSortedMap.this.entrySet().iterator(new AbstractInt2DoubleMap.BasicEntry(from, 0.0D))); }
/* 104:104 */    public IntBidirectionalIterator iterator() { return new AbstractInt2DoubleSortedMap.KeySetIterator(AbstractInt2DoubleSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractIntBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Double>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Double>> i)
/* 117:    */    {
/* 118:118 */      this.i = i;
/* 119:    */    }
/* 120:    */    
/* 121:121 */    public int nextInt() { return ((Integer)((Map.Entry)this.i.next()).getKey()).intValue(); }
/* 122:122 */    public int previousInt() { return ((Integer)((Map.Entry)this.i.previous()).getKey()).intValue(); }
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
/* 148:148 */    public DoubleIterator iterator() { return new AbstractInt2DoubleSortedMap.ValuesIterator(AbstractInt2DoubleSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(double k) { return AbstractInt2DoubleSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractInt2DoubleSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractInt2DoubleSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractDoubleIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Double>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Double>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public double nextDouble() { return ((Double)((Map.Entry)this.i.next()).getValue()).doubleValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Integer, Double>> entrySet()
/* 173:    */  {
/* 174:174 */    return int2DoubleEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */