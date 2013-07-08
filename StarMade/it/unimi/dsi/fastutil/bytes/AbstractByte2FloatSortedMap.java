/*   1:    */package it.unimi.dsi.fastutil.bytes;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*   4:    */import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*   5:    */import it.unimi.dsi.fastutil.floats.FloatCollection;
/*   6:    */import it.unimi.dsi.fastutil.floats.FloatIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractByte2FloatSortedMap
/*  52:    */  extends AbstractByte2FloatMap
/*  53:    */  implements Byte2FloatSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Byte2FloatSortedMap headMap(Byte to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.byteValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Byte2FloatSortedMap tailMap(Byte from) {
/*  63: 63 */    return tailMap(from.byteValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Byte2FloatSortedMap subMap(Byte from, Byte to) {
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
/*  93: 93 */    public boolean contains(byte k) { return AbstractByte2FloatSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractByte2FloatSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractByte2FloatSortedMap.this.clear(); }
/*  96: 96 */    public ByteComparator comparator() { return AbstractByte2FloatSortedMap.this.comparator(); }
/*  97: 97 */    public byte firstByte() { return AbstractByte2FloatSortedMap.this.firstByteKey(); }
/*  98: 98 */    public byte lastByte() { return AbstractByte2FloatSortedMap.this.lastByteKey(); }
/*  99: 99 */    public ByteSortedSet headSet(byte to) { return AbstractByte2FloatSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public ByteSortedSet tailSet(byte from) { return AbstractByte2FloatSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public ByteSortedSet subSet(byte from, byte to) { return AbstractByte2FloatSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public ByteBidirectionalIterator iterator(byte from) { return new AbstractByte2FloatSortedMap.KeySetIterator(AbstractByte2FloatSortedMap.this.entrySet().iterator(new AbstractByte2FloatMap.BasicEntry(from, 0.0F))); }
/* 104:104 */    public ByteBidirectionalIterator iterator() { return new AbstractByte2FloatSortedMap.KeySetIterator(AbstractByte2FloatSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractByteBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Float>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Float>> i)
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
/* 143:143 */  public FloatCollection values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractFloatCollection {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public FloatIterator iterator() { return new AbstractByte2FloatSortedMap.ValuesIterator(AbstractByte2FloatSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(float k) { return AbstractByte2FloatSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractByte2FloatSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractByte2FloatSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractFloatIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Float>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Float>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public float nextFloat() { return ((Float)((Map.Entry)this.i.next()).getValue()).floatValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Byte, Float>> entrySet()
/* 173:    */  {
/* 174:174 */    return byte2FloatEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2FloatSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */