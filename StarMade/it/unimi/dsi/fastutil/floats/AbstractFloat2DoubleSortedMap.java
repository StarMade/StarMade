/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*   4:    */import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*   5:    */import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*   6:    */import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractFloat2DoubleSortedMap
/*  52:    */  extends AbstractFloat2DoubleMap
/*  53:    */  implements Float2DoubleSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Float2DoubleSortedMap headMap(Float to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.floatValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Float2DoubleSortedMap tailMap(Float from) {
/*  63: 63 */    return tailMap(from.floatValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Float2DoubleSortedMap subMap(Float from, Float to) {
/*  67: 67 */    return subMap(from.floatValue(), to.floatValue());
/*  68:    */  }
/*  69:    */  
/*  70:    */  public Float firstKey() {
/*  71: 71 */    return Float.valueOf(firstFloatKey());
/*  72:    */  }
/*  73:    */  
/*  74:    */  public Float lastKey() {
/*  75: 75 */    return Float.valueOf(lastFloatKey());
/*  76:    */  }
/*  77:    */  
/*  89: 89 */  public FloatSortedSet keySet() { return new KeySet(); }
/*  90:    */  
/*  91:    */  protected class KeySet extends AbstractFloatSortedSet { protected KeySet() {}
/*  92:    */    
/*  93: 93 */    public boolean contains(float k) { return AbstractFloat2DoubleSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractFloat2DoubleSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractFloat2DoubleSortedMap.this.clear(); }
/*  96: 96 */    public FloatComparator comparator() { return AbstractFloat2DoubleSortedMap.this.comparator(); }
/*  97: 97 */    public float firstFloat() { return AbstractFloat2DoubleSortedMap.this.firstFloatKey(); }
/*  98: 98 */    public float lastFloat() { return AbstractFloat2DoubleSortedMap.this.lastFloatKey(); }
/*  99: 99 */    public FloatSortedSet headSet(float to) { return AbstractFloat2DoubleSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public FloatSortedSet tailSet(float from) { return AbstractFloat2DoubleSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public FloatSortedSet subSet(float from, float to) { return AbstractFloat2DoubleSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public FloatBidirectionalIterator iterator(float from) { return new AbstractFloat2DoubleSortedMap.KeySetIterator(AbstractFloat2DoubleSortedMap.this.entrySet().iterator(new AbstractFloat2DoubleMap.BasicEntry(from, 0.0D))); }
/* 104:104 */    public FloatBidirectionalIterator iterator() { return new AbstractFloat2DoubleSortedMap.KeySetIterator(AbstractFloat2DoubleSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractFloatBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Float, Double>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Double>> i)
/* 117:    */    {
/* 118:118 */      this.i = i;
/* 119:    */    }
/* 120:    */    
/* 121:121 */    public float nextFloat() { return ((Float)((Map.Entry)this.i.next()).getKey()).floatValue(); }
/* 122:122 */    public float previousFloat() { return ((Float)((Map.Entry)this.i.previous()).getKey()).floatValue(); }
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
/* 148:148 */    public DoubleIterator iterator() { return new AbstractFloat2DoubleSortedMap.ValuesIterator(AbstractFloat2DoubleSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(double k) { return AbstractFloat2DoubleSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractFloat2DoubleSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractFloat2DoubleSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractDoubleIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Float, Double>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Double>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public double nextDouble() { return ((Double)((Map.Entry)this.i.next()).getValue()).doubleValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Float, Double>> entrySet()
/* 173:    */  {
/* 174:174 */    return float2DoubleEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2DoubleSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */