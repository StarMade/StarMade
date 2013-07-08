/*   1:    */package it.unimi.dsi.fastutil.shorts;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*   4:    */import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*   6:    */import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractShort2BooleanSortedMap
/*  52:    */  extends AbstractShort2BooleanMap
/*  53:    */  implements Short2BooleanSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Short2BooleanSortedMap headMap(Short to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.shortValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Short2BooleanSortedMap tailMap(Short from) {
/*  63: 63 */    return tailMap(from.shortValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Short2BooleanSortedMap subMap(Short from, Short to) {
/*  67: 67 */    return subMap(from.shortValue(), to.shortValue());
/*  68:    */  }
/*  69:    */  
/*  70:    */  public Short firstKey() {
/*  71: 71 */    return Short.valueOf(firstShortKey());
/*  72:    */  }
/*  73:    */  
/*  74:    */  public Short lastKey() {
/*  75: 75 */    return Short.valueOf(lastShortKey());
/*  76:    */  }
/*  77:    */  
/*  89: 89 */  public ShortSortedSet keySet() { return new KeySet(); }
/*  90:    */  
/*  91:    */  protected class KeySet extends AbstractShortSortedSet { protected KeySet() {}
/*  92:    */    
/*  93: 93 */    public boolean contains(short k) { return AbstractShort2BooleanSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractShort2BooleanSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractShort2BooleanSortedMap.this.clear(); }
/*  96: 96 */    public ShortComparator comparator() { return AbstractShort2BooleanSortedMap.this.comparator(); }
/*  97: 97 */    public short firstShort() { return AbstractShort2BooleanSortedMap.this.firstShortKey(); }
/*  98: 98 */    public short lastShort() { return AbstractShort2BooleanSortedMap.this.lastShortKey(); }
/*  99: 99 */    public ShortSortedSet headSet(short to) { return AbstractShort2BooleanSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public ShortSortedSet tailSet(short from) { return AbstractShort2BooleanSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public ShortSortedSet subSet(short from, short to) { return AbstractShort2BooleanSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public ShortBidirectionalIterator iterator(short from) { return new AbstractShort2BooleanSortedMap.KeySetIterator(AbstractShort2BooleanSortedMap.this.entrySet().iterator(new AbstractShort2BooleanMap.BasicEntry(from, false))); }
/* 104:104 */    public ShortBidirectionalIterator iterator() { return new AbstractShort2BooleanSortedMap.KeySetIterator(AbstractShort2BooleanSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractShortBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> i)
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
/* 143:143 */  public BooleanCollection values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractBooleanCollection {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public BooleanIterator iterator() { return new AbstractShort2BooleanSortedMap.ValuesIterator(AbstractShort2BooleanSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(boolean k) { return AbstractShort2BooleanSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractShort2BooleanSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractShort2BooleanSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractBooleanIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public boolean nextBoolean() { return ((Boolean)((Map.Entry)this.i.next()).getValue()).booleanValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Short, Boolean>> entrySet()
/* 173:    */  {
/* 174:174 */    return short2BooleanEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */