/*   1:    */package it.unimi.dsi.fastutil.bytes;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*   4:    */import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*   5:    */import it.unimi.dsi.fastutil.longs.LongCollection;
/*   6:    */import it.unimi.dsi.fastutil.longs.LongIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractByte2LongSortedMap
/*  52:    */  extends AbstractByte2LongMap
/*  53:    */  implements Byte2LongSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Byte2LongSortedMap headMap(Byte to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.byteValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Byte2LongSortedMap tailMap(Byte from) {
/*  63: 63 */    return tailMap(from.byteValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Byte2LongSortedMap subMap(Byte from, Byte to) {
/*  67: 67 */    return subMap(from.byteValue(), to.byteValue());
/*  68:    */  }
/*  69:    */  
/*  70:    */  public Byte firstKey() {
/*  71: 71 */    return Byte.valueOf(firstByteKey());
/*  72:    */  }
/*  73:    */  
/*  74:    */  public Byte lastKey() {
/*  75: 75 */    return Byte.valueOf(lastByteKey());
/*  76:    */  }
/*  77:    */  
/*  89: 89 */  public ByteSortedSet keySet() { return new KeySet(); }
/*  90:    */  
/*  91:    */  protected class KeySet extends AbstractByteSortedSet { protected KeySet() {}
/*  92:    */    
/*  93: 93 */    public boolean contains(byte k) { return AbstractByte2LongSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractByte2LongSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractByte2LongSortedMap.this.clear(); }
/*  96: 96 */    public ByteComparator comparator() { return AbstractByte2LongSortedMap.this.comparator(); }
/*  97: 97 */    public byte firstByte() { return AbstractByte2LongSortedMap.this.firstByteKey(); }
/*  98: 98 */    public byte lastByte() { return AbstractByte2LongSortedMap.this.lastByteKey(); }
/*  99: 99 */    public ByteSortedSet headSet(byte to) { return AbstractByte2LongSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public ByteSortedSet tailSet(byte from) { return AbstractByte2LongSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public ByteSortedSet subSet(byte from, byte to) { return AbstractByte2LongSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public ByteBidirectionalIterator iterator(byte from) { return new AbstractByte2LongSortedMap.KeySetIterator(AbstractByte2LongSortedMap.this.entrySet().iterator(new AbstractByte2LongMap.BasicEntry(from, 0L))); }
/* 104:104 */    public ByteBidirectionalIterator iterator() { return new AbstractByte2LongSortedMap.KeySetIterator(AbstractByte2LongSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractByteBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Long>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Long>> i)
/* 117:    */    {
/* 118:118 */      this.i = i;
/* 119:    */    }
/* 120:    */    
/* 121:121 */    public byte nextByte() { return ((Byte)((Map.Entry)this.i.next()).getKey()).byteValue(); }
/* 122:122 */    public byte previousByte() { return ((Byte)((Map.Entry)this.i.previous()).getKey()).byteValue(); }
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
/* 148:148 */    public LongIterator iterator() { return new AbstractByte2LongSortedMap.ValuesIterator(AbstractByte2LongSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(long k) { return AbstractByte2LongSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractByte2LongSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractByte2LongSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractLongIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Long>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Long>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public long nextLong() { return ((Long)((Map.Entry)this.i.next()).getValue()).longValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Byte, Long>> entrySet()
/* 173:    */  {
/* 174:174 */    return byte2LongEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */