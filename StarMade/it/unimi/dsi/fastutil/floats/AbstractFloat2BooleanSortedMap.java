/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*   4:    */import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*   6:    */import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractFloat2BooleanSortedMap
/*  52:    */  extends AbstractFloat2BooleanMap
/*  53:    */  implements Float2BooleanSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Float2BooleanSortedMap headMap(Float to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.floatValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Float2BooleanSortedMap tailMap(Float from) {
/*  63: 63 */    return tailMap(from.floatValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Float2BooleanSortedMap subMap(Float from, Float to) {
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
/*  93: 93 */    public boolean contains(float k) { return AbstractFloat2BooleanSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractFloat2BooleanSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractFloat2BooleanSortedMap.this.clear(); }
/*  96: 96 */    public FloatComparator comparator() { return AbstractFloat2BooleanSortedMap.this.comparator(); }
/*  97: 97 */    public float firstFloat() { return AbstractFloat2BooleanSortedMap.this.firstFloatKey(); }
/*  98: 98 */    public float lastFloat() { return AbstractFloat2BooleanSortedMap.this.lastFloatKey(); }
/*  99: 99 */    public FloatSortedSet headSet(float to) { return AbstractFloat2BooleanSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public FloatSortedSet tailSet(float from) { return AbstractFloat2BooleanSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public FloatSortedSet subSet(float from, float to) { return AbstractFloat2BooleanSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public FloatBidirectionalIterator iterator(float from) { return new AbstractFloat2BooleanSortedMap.KeySetIterator(AbstractFloat2BooleanSortedMap.this.entrySet().iterator(new AbstractFloat2BooleanMap.BasicEntry(from, false))); }
/* 104:104 */    public FloatBidirectionalIterator iterator() { return new AbstractFloat2BooleanSortedMap.KeySetIterator(AbstractFloat2BooleanSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractFloatBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> i)
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
/* 143:143 */  public BooleanCollection values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractBooleanCollection {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public BooleanIterator iterator() { return new AbstractFloat2BooleanSortedMap.ValuesIterator(AbstractFloat2BooleanSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(boolean k) { return AbstractFloat2BooleanSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractFloat2BooleanSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractFloat2BooleanSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractBooleanIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public boolean nextBoolean() { return ((Boolean)((Map.Entry)this.i.next()).getValue()).booleanValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Float, Boolean>> entrySet()
/* 173:    */  {
/* 174:174 */    return float2BooleanEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */