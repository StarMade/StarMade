/*   1:    */package it.unimi.dsi.fastutil.chars;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*   4:    */import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*   6:    */import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   9:    */import java.util.Map.Entry;
/*  10:    */
/*  51:    */public abstract class AbstractChar2BooleanSortedMap
/*  52:    */  extends AbstractChar2BooleanMap
/*  53:    */  implements Char2BooleanSortedMap
/*  54:    */{
/*  55:    */  public static final long serialVersionUID = -1773560792952436569L;
/*  56:    */  
/*  57:    */  public Char2BooleanSortedMap headMap(Character to)
/*  58:    */  {
/*  59: 59 */    return headMap(to.charValue());
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Char2BooleanSortedMap tailMap(Character from) {
/*  63: 63 */    return tailMap(from.charValue());
/*  64:    */  }
/*  65:    */  
/*  66:    */  public Char2BooleanSortedMap subMap(Character from, Character to) {
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
/*  93: 93 */    public boolean contains(char k) { return AbstractChar2BooleanSortedMap.this.containsKey(k); }
/*  94: 94 */    public int size() { return AbstractChar2BooleanSortedMap.this.size(); }
/*  95: 95 */    public void clear() { AbstractChar2BooleanSortedMap.this.clear(); }
/*  96: 96 */    public CharComparator comparator() { return AbstractChar2BooleanSortedMap.this.comparator(); }
/*  97: 97 */    public char firstChar() { return AbstractChar2BooleanSortedMap.this.firstCharKey(); }
/*  98: 98 */    public char lastChar() { return AbstractChar2BooleanSortedMap.this.lastCharKey(); }
/*  99: 99 */    public CharSortedSet headSet(char to) { return AbstractChar2BooleanSortedMap.this.headMap(to).keySet(); }
/* 100:100 */    public CharSortedSet tailSet(char from) { return AbstractChar2BooleanSortedMap.this.tailMap(from).keySet(); }
/* 101:101 */    public CharSortedSet subSet(char from, char to) { return AbstractChar2BooleanSortedMap.this.subMap(from, to).keySet(); }
/* 102:    */    
/* 103:103 */    public CharBidirectionalIterator iterator(char from) { return new AbstractChar2BooleanSortedMap.KeySetIterator(AbstractChar2BooleanSortedMap.this.entrySet().iterator(new AbstractChar2BooleanMap.BasicEntry(from, false))); }
/* 104:104 */    public CharBidirectionalIterator iterator() { return new AbstractChar2BooleanSortedMap.KeySetIterator(AbstractChar2BooleanSortedMap.this.entrySet().iterator()); }
/* 105:    */  }
/* 106:    */  
/* 109:    */  protected static class KeySetIterator
/* 110:    */    extends AbstractCharBidirectionalIterator
/* 111:    */  {
/* 112:    */    protected final ObjectBidirectionalIterator<Map.Entry<Character, Boolean>> i;
/* 113:    */    
/* 116:    */    public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Boolean>> i)
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
/* 143:143 */  public BooleanCollection values() { return new ValuesCollection(); }
/* 144:    */  
/* 145:    */  protected class ValuesCollection extends AbstractBooleanCollection {
/* 146:    */    protected ValuesCollection() {}
/* 147:    */    
/* 148:148 */    public BooleanIterator iterator() { return new AbstractChar2BooleanSortedMap.ValuesIterator(AbstractChar2BooleanSortedMap.this.entrySet().iterator()); }
/* 149:149 */    public boolean contains(boolean k) { return AbstractChar2BooleanSortedMap.this.containsValue(k); }
/* 150:150 */    public int size() { return AbstractChar2BooleanSortedMap.this.size(); }
/* 151:151 */    public void clear() { AbstractChar2BooleanSortedMap.this.clear(); }
/* 152:    */  }
/* 153:    */  
/* 156:    */  protected static class ValuesIterator
/* 157:    */    extends AbstractBooleanIterator
/* 158:    */  {
/* 159:    */    protected final ObjectBidirectionalIterator<Map.Entry<Character, Boolean>> i;
/* 160:    */    
/* 163:    */    public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Boolean>> i)
/* 164:    */    {
/* 165:165 */      this.i = i;
/* 166:    */    }
/* 167:    */    
/* 168:168 */    public boolean nextBoolean() { return ((Boolean)((Map.Entry)this.i.next()).getValue()).booleanValue(); }
/* 169:169 */    public boolean hasNext() { return this.i.hasNext(); }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public ObjectSortedSet<Map.Entry<Character, Boolean>> entrySet()
/* 173:    */  {
/* 174:174 */    return char2BooleanEntrySet();
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2BooleanSortedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */