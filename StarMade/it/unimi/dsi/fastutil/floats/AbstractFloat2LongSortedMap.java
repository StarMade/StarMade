/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*   4:    */import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*   5:    */import it.unimi.dsi.fastutil.longs.LongCollection;
/*   6:    */import it.unimi.dsi.fastutil.longs.LongIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractFloat2LongSortedMap
/*  52:    */  extends AbstractFloat2LongMap
/*  53:    */  implements Float2LongSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Float2LongSortedMap headMap(Float to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.floatValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Float2LongSortedMap tailMap(Float from) {
/*  63: 63 */    return tailMap(from.floatValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Float2LongSortedMap subMap(Float from, Float to) {
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
/*  93: 93 */    public boolean contains(float k) { return AbstractFloat2LongSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractFloat2LongSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractFloat2LongSortedMap.this.clear(); }
/*  96: 96 */    public FloatComparator comparator() { return AbstractFloat2LongSortedMap.this.comparator(); }
/*  97: 97 */    public float firstFloat() { return AbstractFloat2LongSortedMap.this.firstFloatKey(); }
/*  98: 98 */    public float lastFloat() { return AbstractFloat2LongSortedMap.this.lastFloatKey(); }
/*  99: 99 */    public FloatSortedSet headSet(float to) { return AbstractFloat2LongSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public FloatSortedSet tailSet(float from) { return AbstractFloat2LongSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public FloatSortedSet subSet(float from, float to) { return AbstractFloat2LongSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public FloatBidirectionalIterator iterator(float from) { return new AbstractFloat2LongSortedMap.KeySetIterator(AbstractFloat2LongSortedMap.this.entrySet().iterator(new AbstractFloat2LongMap.BasicEntry(from, 0L))); }
/* 104:104 */    public FloatBidirectionalIterator iterator() { return new AbstractFloat2LongSortedMap.KeySetIterator(AbstractFloat2LongSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractFloatBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Float, Long>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Long>> i)
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
/* 143:143 */  public LongCollection values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractLongCollection {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public LongIterator iterator() { return new AbstractFloat2LongSortedMap.ValuesIterator(AbstractFloat2LongSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(long k) { return AbstractFloat2LongSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractFloat2LongSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractFloat2LongSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractLongIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Float, Long>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Long>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public long nextLong() { return ((Long)((Map.Entry)this.i.next()).getValue()).longValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Float, Long>> entrySet()
/* 173:    */  {
/* 174:174 */    return float2LongEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */