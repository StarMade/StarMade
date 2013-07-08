/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
/*   4:    */import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
/*   5:    */import it.unimi.dsi.fastutil.bytes.ByteCollection;
/*   6:    */import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractInt2ByteSortedMap
/*  52:    */  extends AbstractInt2ByteMap
/*  53:    */  implements Int2ByteSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Int2ByteSortedMap headMap(Integer to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.intValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Int2ByteSortedMap tailMap(Integer from) {
/*  63: 63 */    return tailMap(from.intValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Int2ByteSortedMap subMap(Integer from, Integer to) {
/*  67: 67 */    return subMap(from.intValue(), to.intValue());
/*  68:    */  }
/*  69:    */  
/*  70:    */  public Integer firstKey() {
/*  71: 71 */    return Integer.valueOf(firstIntKey());
/*  72:    */  }
/*  73:    */  
/*  74:    */  public Integer lastKey() {
/*  75: 75 */    return Integer.valueOf(lastIntKey());
/*  76:    */  }
/*  77:    */  
/*  89: 89 */  public IntSortedSet keySet() { return new KeySet(); }
/*  90:    */  
/*  91:    */  protected class KeySet extends AbstractIntSortedSet { protected KeySet() {}
/*  92:    */    
/*  93: 93 */    public boolean contains(int k) { return AbstractInt2ByteSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractInt2ByteSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractInt2ByteSortedMap.this.clear(); }
/*  96: 96 */    public IntComparator comparator() { return AbstractInt2ByteSortedMap.this.comparator(); }
/*  97: 97 */    public int firstInt() { return AbstractInt2ByteSortedMap.this.firstIntKey(); }
/*  98: 98 */    public int lastInt() { return AbstractInt2ByteSortedMap.this.lastIntKey(); }
/*  99: 99 */    public IntSortedSet headSet(int to) { return AbstractInt2ByteSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public IntSortedSet tailSet(int from) { return AbstractInt2ByteSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public IntSortedSet subSet(int from, int to) { return AbstractInt2ByteSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public IntBidirectionalIterator iterator(int from) { return new AbstractInt2ByteSortedMap.KeySetIterator(AbstractInt2ByteSortedMap.this.entrySet().iterator(new AbstractInt2ByteMap.BasicEntry(from, (byte)0))); }
/* 104:104 */    public IntBidirectionalIterator iterator() { return new AbstractInt2ByteSortedMap.KeySetIterator(AbstractInt2ByteSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractIntBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Byte>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Byte>> i)
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
/* 143:143 */  public ByteCollection values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractByteCollection {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public ByteIterator iterator() { return new AbstractInt2ByteSortedMap.ValuesIterator(AbstractInt2ByteSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(byte k) { return AbstractInt2ByteSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractInt2ByteSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractInt2ByteSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractByteIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Integer, Byte>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Byte>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public byte nextByte() { return ((Byte)((Map.Entry)this.i.next()).getValue()).byteValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Integer, Byte>> entrySet()
/* 173:    */  {
/* 174:174 */    return int2ByteEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ByteSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */