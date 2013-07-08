/*   1:    */package it.unimi.dsi.fastutil.bytes;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*   4:    */import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
/*   5:    */import it.unimi.dsi.fastutil.chars.CharCollection;
/*   6:    */import it.unimi.dsi.fastutil.chars.CharIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractByte2CharSortedMap
/*  52:    */  extends AbstractByte2CharMap
/*  53:    */  implements Byte2CharSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Byte2CharSortedMap headMap(Byte to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.byteValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Byte2CharSortedMap tailMap(Byte from) {
/*  63: 63 */    return tailMap(from.byteValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Byte2CharSortedMap subMap(Byte from, Byte to) {
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
/*  93: 93 */    public boolean contains(byte k) { return AbstractByte2CharSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractByte2CharSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractByte2CharSortedMap.this.clear(); }
/*  96: 96 */    public ByteComparator comparator() { return AbstractByte2CharSortedMap.this.comparator(); }
/*  97: 97 */    public byte firstByte() { return AbstractByte2CharSortedMap.this.firstByteKey(); }
/*  98: 98 */    public byte lastByte() { return AbstractByte2CharSortedMap.this.lastByteKey(); }
/*  99: 99 */    public ByteSortedSet headSet(byte to) { return AbstractByte2CharSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public ByteSortedSet tailSet(byte from) { return AbstractByte2CharSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public ByteSortedSet subSet(byte from, byte to) { return AbstractByte2CharSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public ByteBidirectionalIterator iterator(byte from) { return new AbstractByte2CharSortedMap.KeySetIterator(AbstractByte2CharSortedMap.this.entrySet().iterator(new AbstractByte2CharMap.BasicEntry(from, '\000'))); }
/* 104:104 */    public ByteBidirectionalIterator iterator() { return new AbstractByte2CharSortedMap.KeySetIterator(AbstractByte2CharSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractByteBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Character>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Character>> i)
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
/* 143:143 */  public CharCollection values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractCharCollection {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public CharIterator iterator() { return new AbstractByte2CharSortedMap.ValuesIterator(AbstractByte2CharSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(char k) { return AbstractByte2CharSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractByte2CharSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractByte2CharSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractCharIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Byte, Character>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Character>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public char nextChar() { return ((Character)((Map.Entry)this.i.next()).getValue()).charValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Byte, Character>> entrySet()
/* 173:    */  {
/* 174:174 */    return byte2CharEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2CharSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */