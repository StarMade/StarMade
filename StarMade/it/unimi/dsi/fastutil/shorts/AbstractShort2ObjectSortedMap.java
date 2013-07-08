/*   1:    */package it.unimi.dsi.fastutil.shorts;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*   4:    */import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  50:    */public abstract class AbstractShort2ObjectSortedMap<V>
/*  51:    */  extends AbstractShort2ObjectMap<V>
/*  52:    */  implements Short2ObjectSortedMap<V>
/*  53:    */{
/*  54:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  55:    */  
/*  56:    */  public Short2ObjectSortedMap<V> headMap(Short to)
/*  57:    */  {
/*  58: 58 */    return headMap(to.shortValue());
/*  59:    */  }
/*  60:    */  
/*  61:    */  public Short2ObjectSortedMap<V> tailMap(Short from) {
/*  62: 62 */    return tailMap(from.shortValue());
/*  63:    */  }
/*  64:    */  
/*  65:    */  public Short2ObjectSortedMap<V> subMap(Short from, Short to) {
/*  66: 66 */    return subMap(from.shortValue(), to.shortValue());
/*  67:    */  }
/*  68:    */  
/*  69:    */  public Short firstKey() {
/*  70: 70 */    return Short.valueOf(firstShortKey());
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Short lastKey() {
/*  74: 74 */    return Short.valueOf(lastShortKey());
/*  75:    */  }
/*  76:    */  
/*  88: 88 */  public ShortSortedSet keySet() { return new KeySet(); }
/*  89:    */  
/*  90:    */  protected class KeySet extends AbstractShortSortedSet { protected KeySet() {}
/*  91:    */    
/*  92: 92 */    public boolean contains(short k) { return AbstractShort2ObjectSortedMap.this.containsKey(k); }
/*  93: 93 */    public int size() { return AbstractShort2ObjectSortedMap.this.size(); }
/*  94: 94 */    public void clear() { AbstractShort2ObjectSortedMap.this.clear(); }
/*  95: 95 */    public ShortComparator comparator() { return AbstractShort2ObjectSortedMap.this.comparator(); }
/*  96: 96 */    public short firstShort() { return AbstractShort2ObjectSortedMap.this.firstShortKey(); }
/*  97: 97 */    public short lastShort() { return AbstractShort2ObjectSortedMap.this.lastShortKey(); }
/*  98:    */    
/*  99: 99 */    public ShortSortedSet headSet(short to) { return AbstractShort2ObjectSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public ShortSortedSet tailSet(short from) { return AbstractShort2ObjectSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public ShortSortedSet subSet(short from, short to) { return AbstractShort2ObjectSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public ShortBidirectionalIterator iterator(short from) { return new AbstractShort2ObjectSortedMap.KeySetIterator(AbstractShort2ObjectSortedMap.this.entrySet().iterator(new AbstractShort2ObjectMap.BasicEntry(from, null))); }
/* 104:104 */    public ShortBidirectionalIterator iterator() { return new AbstractShort2ObjectSortedMap.KeySetIterator(AbstractShort2ObjectSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator<V>
/* 110:    */    extends AbstractShortBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Short, V>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, V>> i)
/* 117:    */    {
/* 118:118 */      this.i = i;
/* 119:    */    }
/* 120:    */    
/* 121:121 */    public short nextShort() { return ((Short)((Map.Entry)this.i.next()).getKey()).shortValue(); }
/* 122:122 */    public short previousShort() { return ((Short)((Map.Entry)this.i.previous()).getKey()).shortValue(); }
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
/* 148:148 */    public ObjectIterator<V> iterator() { return new AbstractShort2ObjectSortedMap.ValuesIterator(AbstractShort2ObjectSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(Object k) { return AbstractShort2ObjectSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractShort2ObjectSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractShort2ObjectSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator<V>
/* 157:    */    extends AbstractObjectIterator<V>
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Short, V>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, V>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public V next() { return ((Map.Entry)this.i.next()).getValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Short, V>> entrySet()
/* 173:    */  {
/* 174:174 */    return short2ObjectEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ObjectSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */