/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*   4:    */import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  50:    */public abstract class AbstractFloat2ObjectSortedMap<V>
/*  51:    */  extends AbstractFloat2ObjectMap<V>
/*  52:    */  implements Float2ObjectSortedMap<V>
/*  53:    */{
/*  54:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  55:    */  
/*  56:    */  public Float2ObjectSortedMap<V> headMap(Float to)
/*  57:    */  {
/*  58: 58 */    return headMap(to.floatValue());
/*  59:    */  }
/*  60:    */  
/*  61:    */  public Float2ObjectSortedMap<V> tailMap(Float from) {
/*  62: 62 */    return tailMap(from.floatValue());
/*  63:    */  }
/*  64:    */  
/*  65:    */  public Float2ObjectSortedMap<V> subMap(Float from, Float to) {
/*  66: 66 */    return subMap(from.floatValue(), to.floatValue());
/*  67:    */  }
/*  68:    */  
/*  69:    */  public Float firstKey() {
/*  70: 70 */    return Float.valueOf(firstFloatKey());
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Float lastKey() {
/*  74: 74 */    return Float.valueOf(lastFloatKey());
/*  75:    */  }
/*  76:    */  
/*  88: 88 */  public FloatSortedSet keySet() { return new KeySet(); }
/*  89:    */  
/*  90:    */  protected class KeySet extends AbstractFloatSortedSet { protected KeySet() {}
/*  91:    */    
/*  92: 92 */    public boolean contains(float k) { return AbstractFloat2ObjectSortedMap.this.containsKey(k); }
/*  93: 93 */    public int size() { return AbstractFloat2ObjectSortedMap.this.size(); }
/*  94: 94 */    public void clear() { AbstractFloat2ObjectSortedMap.this.clear(); }
/*  95: 95 */    public FloatComparator comparator() { return AbstractFloat2ObjectSortedMap.this.comparator(); }
/*  96: 96 */    public float firstFloat() { return AbstractFloat2ObjectSortedMap.this.firstFloatKey(); }
/*  97: 97 */    public float lastFloat() { return AbstractFloat2ObjectSortedMap.this.lastFloatKey(); }
/*  98:    */    
/*  99: 99 */    public FloatSortedSet headSet(float to) { return AbstractFloat2ObjectSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public FloatSortedSet tailSet(float from) { return AbstractFloat2ObjectSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public FloatSortedSet subSet(float from, float to) { return AbstractFloat2ObjectSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public FloatBidirectionalIterator iterator(float from) { return new AbstractFloat2ObjectSortedMap.KeySetIterator(AbstractFloat2ObjectSortedMap.this.entrySet().iterator(new AbstractFloat2ObjectMap.BasicEntry(from, null))); }
/* 104:104 */    public FloatBidirectionalIterator iterator() { return new AbstractFloat2ObjectSortedMap.KeySetIterator(AbstractFloat2ObjectSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator<V>
/* 110:    */    extends AbstractFloatBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Float, V>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, V>> i)
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
/* 143:143 */  public ObjectCollection<V> values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractObjectCollection<V> {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public ObjectIterator<V> iterator() { return new AbstractFloat2ObjectSortedMap.ValuesIterator(AbstractFloat2ObjectSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(Object k) { return AbstractFloat2ObjectSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractFloat2ObjectSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractFloat2ObjectSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator<V>
/* 157:    */    extends AbstractObjectIterator<V>
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Float, V>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, V>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public V next() { return ((Map.Entry)this.i.next()).getValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Float, V>> entrySet()
/* 173:    */  {
/* 174:174 */    return float2ObjectEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */