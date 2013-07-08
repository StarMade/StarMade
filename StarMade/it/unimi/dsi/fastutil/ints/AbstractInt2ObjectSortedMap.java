/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*   4:    */import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  50:    */public abstract class AbstractInt2ObjectSortedMap<V>
/*  51:    */  extends AbstractInt2ObjectMap<V>
/*  52:    */  implements Int2ObjectSortedMap<V>
/*  53:    */{
/*  54:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  55:    */  
/*  56:    */  public Int2ObjectSortedMap<V> headMap(Integer to)
/*  57:    */  {
/*  58: 58 */    return headMap(to.intValue());
/*  59:    */  }
/*  60:    */  
/*  61:    */  public Int2ObjectSortedMap<V> tailMap(Integer from) {
/*  62: 62 */    return tailMap(from.intValue());
/*  63:    */  }
/*  64:    */  
/*  65:    */  public Int2ObjectSortedMap<V> subMap(Integer from, Integer to) {
/*  66: 66 */    return subMap(from.intValue(), to.intValue());
/*  67:    */  }
/*  68:    */  
/*  69:    */  public Integer firstKey() {
/*  70: 70 */    return Integer.valueOf(firstIntKey());
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Integer lastKey() {
/*  74: 74 */    return Integer.valueOf(lastIntKey());
/*  75:    */  }
/*  76:    */  
/*  88: 88 */  public IntSortedSet keySet() { return new KeySet(); }
/*  89:    */  
/*  90:    */  protected class KeySet extends AbstractIntSortedSet { protected KeySet() {}
/*  91:    */    
/*  92: 92 */    public boolean contains(int k) { return AbstractInt2ObjectSortedMap.this.containsKey(k); }
/*  93: 93 */    public int size() { return AbstractInt2ObjectSortedMap.this.size(); }
/*  94: 94 */    public void clear() { AbstractInt2ObjectSortedMap.this.clear(); }
/*  95: 95 */    public IntComparator comparator() { return AbstractInt2ObjectSortedMap.this.comparator(); }
/*  96: 96 */    public int firstInt() { return AbstractInt2ObjectSortedMap.this.firstIntKey(); }
/*  97: 97 */    public int lastInt() { return AbstractInt2ObjectSortedMap.this.lastIntKey(); }
/*  98:    */    
/*  99: 99 */    public IntSortedSet headSet(int to) { return AbstractInt2ObjectSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public IntSortedSet tailSet(int from) { return AbstractInt2ObjectSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public IntSortedSet subSet(int from, int to) { return AbstractInt2ObjectSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public IntBidirectionalIterator iterator(int from) { return new AbstractInt2ObjectSortedMap.KeySetIterator(AbstractInt2ObjectSortedMap.this.entrySet().iterator(new AbstractInt2ObjectMap.BasicEntry(from, null))); }
/* 104:104 */    public IntBidirectionalIterator iterator() { return new AbstractInt2ObjectSortedMap.KeySetIterator(AbstractInt2ObjectSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator<V>
/* 110:    */    extends AbstractIntBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Integer, V>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, V>> i)
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
/* 143:143 */  public ObjectCollection<V> values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractObjectCollection<V> {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public ObjectIterator<V> iterator() { return new AbstractInt2ObjectSortedMap.ValuesIterator(AbstractInt2ObjectSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(Object k) { return AbstractInt2ObjectSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractInt2ObjectSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractInt2ObjectSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator<V>
/* 157:    */    extends AbstractObjectIterator<V>
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Integer, V>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, V>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public V next() { return ((Map.Entry)this.i.next()).getValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Integer, V>> entrySet()
/* 173:    */  {
/* 174:174 */    return int2ObjectEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */