/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*   4:    */import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*   5:    */import it.unimi.dsi.fastutil.floats.FloatCollection;
/*   6:    */import it.unimi.dsi.fastutil.floats.FloatIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractInt2FloatSortedMap
/*  52:    */  extends AbstractInt2FloatMap
/*  53:    */  implements Int2FloatSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Int2FloatSortedMap headMap(Integer to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.intValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Int2FloatSortedMap tailMap(Integer from) {
/*  63: 63 */    return tailMap(from.intValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Int2FloatSortedMap subMap(Integer from, Integer to) {
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
/*  93: 93 */    public boolean contains(int k) { return AbstractInt2FloatSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractInt2FloatSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractInt2FloatSortedMap.this.clear(); }
/*  96: 96 */    public IntComparator comparator() { return AbstractInt2FloatSortedMap.this.comparator(); }
/*  97: 97 */    public int firstInt() { return AbstractInt2FloatSortedMap.this.firstIntKey(); }
/*  98: 98 */    public int lastInt() { return AbstractInt2FloatSortedMap.this.lastIntKey(); }
/*  99: 99 */    public IntSortedSet headSet(int to) { return AbstractInt2FloatSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public IntSortedSet tailSet(int from) { return AbstractInt2FloatSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public IntSortedSet subSet(int from, int to) { return AbstractInt2FloatSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public IntBidirectionalIterator iterator(int from) { return new AbstractInt2FloatSortedMap.KeySetIterator(AbstractInt2FloatSortedMap.this.entrySet().iterator(new AbstractInt2FloatMap.BasicEntry(from, 0.0F))); }
/* 104:104 */    public IntBidirectionalIterator iterator() { return new AbstractInt2FloatSortedMap.KeySetIterator(AbstractInt2FloatSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractIntBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Float>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Float>> i)
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
/* 143:143 */  public FloatCollection values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractFloatCollection {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public FloatIterator iterator() { return new AbstractInt2FloatSortedMap.ValuesIterator(AbstractInt2FloatSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(float k) { return AbstractInt2FloatSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractInt2FloatSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractInt2FloatSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractFloatIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Float>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Float>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public float nextFloat() { return ((Float)((Map.Entry)this.i.next()).getValue()).floatValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Integer, Float>> entrySet()
/* 173:    */  {
/* 174:174 */    return int2FloatEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */