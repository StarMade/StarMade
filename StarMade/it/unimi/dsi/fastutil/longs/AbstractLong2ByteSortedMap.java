/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
/*   4:    */import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
/*   5:    */import it.unimi.dsi.fastutil.bytes.ByteCollection;
/*   6:    */import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractLong2ByteSortedMap
/*  52:    */  extends AbstractLong2ByteMap
/*  53:    */  implements Long2ByteSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Long2ByteSortedMap headMap(Long to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.longValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Long2ByteSortedMap tailMap(Long from) {
/*  63: 63 */    return tailMap(from.longValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Long2ByteSortedMap subMap(Long from, Long to) {
/*  67: 67 */    return subMap(from.longValue(), to.longValue());
/*  68:    */  }
/*  69:    */  
/*  70:    */  public Long firstKey() {
/*  71: 71 */    return Long.valueOf(firstLongKey());
/*  72:    */  }
/*  73:    */  
/*  74:    */  public Long lastKey() {
/*  75: 75 */    return Long.valueOf(lastLongKey());
/*  76:    */  }
/*  77:    */  
/*  89: 89 */  public LongSortedSet keySet() { return new KeySet(); }
/*  90:    */  
/*  91:    */  protected class KeySet extends AbstractLongSortedSet { protected KeySet() {}
/*  92:    */    
/*  93: 93 */    public boolean contains(long k) { return AbstractLong2ByteSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractLong2ByteSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractLong2ByteSortedMap.this.clear(); }
/*  96: 96 */    public LongComparator comparator() { return AbstractLong2ByteSortedMap.this.comparator(); }
/*  97: 97 */    public long firstLong() { return AbstractLong2ByteSortedMap.this.firstLongKey(); }
/*  98: 98 */    public long lastLong() { return AbstractLong2ByteSortedMap.this.lastLongKey(); }
/*  99: 99 */    public LongSortedSet headSet(long to) { return AbstractLong2ByteSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public LongSortedSet tailSet(long from) { return AbstractLong2ByteSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public LongSortedSet subSet(long from, long to) { return AbstractLong2ByteSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public LongBidirectionalIterator iterator(long from) { return new AbstractLong2ByteSortedMap.KeySetIterator(AbstractLong2ByteSortedMap.this.entrySet().iterator(new AbstractLong2ByteMap.BasicEntry(from, (byte)0))); }
/* 104:104 */    public LongBidirectionalIterator iterator() { return new AbstractLong2ByteSortedMap.KeySetIterator(AbstractLong2ByteSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractLongBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Long, Byte>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Byte>> i)
/* 117:    */    {
/* 118:118 */      this.i = i;
/* 119:    */    }
/* 120:    */    
/* 121:121 */    public long nextLong() { return ((Long)((Map.Entry)this.i.next()).getKey()).longValue(); }
/* 122:122 */    public long previousLong() { return ((Long)((Map.Entry)this.i.previous()).getKey()).longValue(); }
/* 123:    */    
/* 124:124 */    public boolean hasNext() { return this.i.hasNext(); }
/* 125:125 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 126:    */  }
/* 127:    */  
/* 143:143 */  public ByteCollection values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractByteCollection {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public ByteIterator iterator() { return new AbstractLong2ByteSortedMap.ValuesIterator(AbstractLong2ByteSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(byte k) { return AbstractLong2ByteSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractLong2ByteSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractLong2ByteSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractByteIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Long, Byte>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Byte>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public byte nextByte() { return ((Byte)((Map.Entry)this.i.next()).getValue()).byteValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Long, Byte>> entrySet()
/* 173:    */  {
/* 174:174 */    return long2ByteEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */