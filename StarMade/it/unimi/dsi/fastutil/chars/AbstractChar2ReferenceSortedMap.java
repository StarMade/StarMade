/*   1:    */package it.unimi.dsi.fastutil.chars;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*   4:    */import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   8:    */import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  50:    */public abstract class AbstractChar2ReferenceSortedMap<V>
/*  51:    */  extends AbstractChar2ReferenceMap<V>
/*  52:    */  implements Char2ReferenceSortedMap<V>
/*  53:    */{
/*  54:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  55:    */  
/*  56:    */  public Char2ReferenceSortedMap<V> headMap(Character to)
/*  57:    */  {
/*  58: 58 */    return headMap(to.charValue());
/*  59:    */  }
/*  60:    */  
/*  61:    */  public Char2ReferenceSortedMap<V> tailMap(Character from) {
/*  62: 62 */    return tailMap(from.charValue());
/*  63:    */  }
/*  64:    */  
/*  65:    */  public Char2ReferenceSortedMap<V> subMap(Character from, Character to) {
/*  66: 66 */    return subMap(from.charValue(), to.charValue());
/*  67:    */  }
/*  68:    */  
/*  69:    */  public Character firstKey() {
/*  70: 70 */    return Character.valueOf(firstCharKey());
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Character lastKey() {
/*  74: 74 */    return Character.valueOf(lastCharKey());
/*  75:    */  }
/*  76:    */  
/*  88: 88 */  public CharSortedSet keySet() { return new KeySet(); }
/*  89:    */  
/*  90:    */  protected class KeySet extends AbstractCharSortedSet { protected KeySet() {}
/*  91:    */    
/*  92: 92 */    public boolean contains(char k) { return AbstractChar2ReferenceSortedMap.this.containsKey(k); }
/*  93: 93 */    public int size() { return AbstractChar2ReferenceSortedMap.this.size(); }
/*  94: 94 */    public void clear() { AbstractChar2ReferenceSortedMap.this.clear(); }
/*  95: 95 */    public CharComparator comparator() { return AbstractChar2ReferenceSortedMap.this.comparator(); }
/*  96: 96 */    public char firstChar() { return AbstractChar2ReferenceSortedMap.this.firstCharKey(); }
/*  97: 97 */    public char lastChar() { return AbstractChar2ReferenceSortedMap.this.lastCharKey(); }
/*  98:    */    
/*  99: 99 */    public CharSortedSet headSet(char to) { return AbstractChar2ReferenceSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public CharSortedSet tailSet(char from) { return AbstractChar2ReferenceSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public CharSortedSet subSet(char from, char to) { return AbstractChar2ReferenceSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public CharBidirectionalIterator iterator(char from) { return new AbstractChar2ReferenceSortedMap.KeySetIterator(AbstractChar2ReferenceSortedMap.this.entrySet().iterator(new AbstractChar2ReferenceMap.BasicEntry(from, null))); }
/* 104:104 */    public CharBidirectionalIterator iterator() { return new AbstractChar2ReferenceSortedMap.KeySetIterator(AbstractChar2ReferenceSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator<V>
/* 110:    */    extends AbstractCharBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Character, V>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, V>> i)
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
/* 143:143 */  public ReferenceCollection<V> values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractReferenceCollection<V> {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public ObjectIterator<V> iterator() { return new AbstractChar2ReferenceSortedMap.ValuesIterator(AbstractChar2ReferenceSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(Object k) { return AbstractChar2ReferenceSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractChar2ReferenceSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractChar2ReferenceSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator<V>
/* 157:    */    extends AbstractObjectIterator<V>
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Character, V>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, V>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public V next() { return ((Map.Entry)this.i.next()).getValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Character, V>> entrySet()
/* 173:    */  {
/* 174:174 */    return char2ReferenceEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ReferenceSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */