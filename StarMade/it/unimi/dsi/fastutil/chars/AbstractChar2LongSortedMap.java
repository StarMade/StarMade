/*   1:    */package it.unimi.dsi.fastutil.chars;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*   4:    */import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*   5:    */import it.unimi.dsi.fastutil.longs.LongCollection;
/*   6:    */import it.unimi.dsi.fastutil.longs.LongIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractChar2LongSortedMap
/*  52:    */  extends AbstractChar2LongMap
/*  53:    */  implements Char2LongSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Char2LongSortedMap headMap(Character to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.charValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Char2LongSortedMap tailMap(Character from) {
/*  63: 63 */    return tailMap(from.charValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Char2LongSortedMap subMap(Character from, Character to) {
/*  67: 67 */    return subMap(from.charValue(), to.charValue());
/*  68:    */  }
/*  69:    */  
/*  70:    */  public Character firstKey() {
/*  71: 71 */    return Character.valueOf(firstCharKey());
/*  72:    */  }
/*  73:    */  
/*  74:    */  public Character lastKey() {
/*  75: 75 */    return Character.valueOf(lastCharKey());
/*  76:    */  }
/*  77:    */  
/*  89: 89 */  public CharSortedSet keySet() { return new KeySet(); }
/*  90:    */  
/*  91:    */  protected class KeySet extends AbstractCharSortedSet { protected KeySet() {}
/*  92:    */    
/*  93: 93 */    public boolean contains(char k) { return AbstractChar2LongSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractChar2LongSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractChar2LongSortedMap.this.clear(); }
/*  96: 96 */    public CharComparator comparator() { return AbstractChar2LongSortedMap.this.comparator(); }
/*  97: 97 */    public char firstChar() { return AbstractChar2LongSortedMap.this.firstCharKey(); }
/*  98: 98 */    public char lastChar() { return AbstractChar2LongSortedMap.this.lastCharKey(); }
/*  99: 99 */    public CharSortedSet headSet(char to) { return AbstractChar2LongSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public CharSortedSet tailSet(char from) { return AbstractChar2LongSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public CharSortedSet subSet(char from, char to) { return AbstractChar2LongSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public CharBidirectionalIterator iterator(char from) { return new AbstractChar2LongSortedMap.KeySetIterator(AbstractChar2LongSortedMap.this.entrySet().iterator(new AbstractChar2LongMap.BasicEntry(from, 0L))); }
/* 104:104 */    public CharBidirectionalIterator iterator() { return new AbstractChar2LongSortedMap.KeySetIterator(AbstractChar2LongSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractCharBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Character, Long>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Long>> i)
/* 117:    */    {
/* 118:118 */      this.i = i;
/* 119:    */    }
/* 120:    */    
/* 121:121 */    public char nextChar() { return ((Character)((Map.Entry)this.i.next()).getKey()).charValue(); }
/* 122:122 */    public char previousChar() { return ((Character)((Map.Entry)this.i.previous()).getKey()).charValue(); }
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
/* 148:148 */    public LongIterator iterator() { return new AbstractChar2LongSortedMap.ValuesIterator(AbstractChar2LongSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(long k) { return AbstractChar2LongSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractChar2LongSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractChar2LongSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractLongIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Character, Long>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Long>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public long nextLong() { return ((Long)((Map.Entry)this.i.next()).getValue()).longValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Character, Long>> entrySet()
/* 173:    */  {
/* 174:174 */    return char2LongEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2LongSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */