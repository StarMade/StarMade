/*   1:    */package it.unimi.dsi.fastutil.bytes;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*   4:    */import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   8:    */import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  50:    */public abstract class AbstractByte2ReferenceSortedMap<V>
/*  51:    */  extends AbstractByte2ReferenceMap<V>
/*  52:    */  implements Byte2ReferenceSortedMap<V>
/*  53:    */{
/*  54:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  55:    */  
/*  56:    */  public Byte2ReferenceSortedMap<V> headMap(Byte to)
/*  57:    */  {
/*  58: 58 */    return headMap(to.byteValue());
/*  59:    */  }
/*  60:    */  
/*  61:    */  public Byte2ReferenceSortedMap<V> tailMap(Byte from) {
/*  62: 62 */    return tailMap(from.byteValue());
/*  63:    */  }
/*  64:    */  
/*  65:    */  public Byte2ReferenceSortedMap<V> subMap(Byte from, Byte to) {
/*  66: 66 */    return subMap(from.byteValue(), to.byteValue());
/*  67:    */  }
/*  68:    */  
/*  69:    */  public Byte firstKey() {
/*  70: 70 */    return Byte.valueOf(firstByteKey());
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Byte lastKey() {
/*  74: 74 */    return Byte.valueOf(lastByteKey());
/*  75:    */  }
/*  76:    */  
/*  88: 88 */  public ByteSortedSet keySet() { return new KeySet(); }
/*  89:    */  
/*  90:    */  protected class KeySet extends AbstractByteSortedSet { protected KeySet() {}
/*  91:    */    
/*  92: 92 */    public boolean contains(byte k) { return AbstractByte2ReferenceSortedMap.this.containsKey(k); }
/*  93: 93 */    public int size() { return AbstractByte2ReferenceSortedMap.this.size(); }
/*  94: 94 */    public void clear() { AbstractByte2ReferenceSortedMap.this.clear(); }
/*  95: 95 */    public ByteComparator comparator() { return AbstractByte2ReferenceSortedMap.this.comparator(); }
/*  96: 96 */    public byte firstByte() { return AbstractByte2ReferenceSortedMap.this.firstByteKey(); }
/*  97: 97 */    public byte lastByte() { return AbstractByte2ReferenceSortedMap.this.lastByteKey(); }
/*  98:    */    
/*  99: 99 */    public ByteSortedSet headSet(byte to) { return AbstractByte2ReferenceSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public ByteSortedSet tailSet(byte from) { return AbstractByte2ReferenceSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public ByteSortedSet subSet(byte from, byte to) { return AbstractByte2ReferenceSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public ByteBidirectionalIterator iterator(byte from) { return new AbstractByte2ReferenceSortedMap.KeySetIterator(AbstractByte2ReferenceSortedMap.this.entrySet().iterator(new AbstractByte2ReferenceMap.BasicEntry(from, null))); }
/* 104:104 */    public ByteBidirectionalIterator iterator() { return new AbstractByte2ReferenceSortedMap.KeySetIterator(AbstractByte2ReferenceSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator<V>
/* 110:    */    extends AbstractByteBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Byte, V>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, V>> i)
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
/* 143:143 */  public ReferenceCollection<V> values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractReferenceCollection<V> {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public ObjectIterator<V> iterator() { return new AbstractByte2ReferenceSortedMap.ValuesIterator(AbstractByte2ReferenceSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(Object k) { return AbstractByte2ReferenceSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractByte2ReferenceSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractByte2ReferenceSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator<V>
/* 157:    */    extends AbstractObjectIterator<V>
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Byte, V>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, V>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public V next() { return ((Map.Entry)this.i.next()).getValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Byte, V>> entrySet()
/* 173:    */  {
/* 174:174 */    return byte2ReferenceEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */