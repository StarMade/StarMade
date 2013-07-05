/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractChar2ShortSortedMap extends AbstractChar2ShortMap
/*     */   implements Char2ShortSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Char2ShortSortedMap headMap(Character to)
/*     */   {
/*  59 */     return headMap(to.charValue());
/*     */   }
/*     */ 
/*     */   public Char2ShortSortedMap tailMap(Character from) {
/*  63 */     return tailMap(from.charValue());
/*     */   }
/*     */ 
/*     */   public Char2ShortSortedMap subMap(Character from, Character to) {
/*  67 */     return subMap(from.charValue(), to.charValue());
/*     */   }
/*     */ 
/*     */   public Character firstKey() {
/*  71 */     return Character.valueOf(firstCharKey());
/*     */   }
/*     */ 
/*     */   public Character lastKey() {
/*  75 */     return Character.valueOf(lastCharKey());
/*     */   }
/*     */ 
/*     */   public CharSortedSet keySet()
/*     */   {
/*  89 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public ShortCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Character, Short>> entrySet()
/*     */   {
/* 174 */     return char2ShortEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractShortIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Character, Short>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Short>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public short nextShort() {
/* 168 */       return ((Short)((Map.Entry)this.i.next()).getValue()).shortValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractShortCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ShortIterator iterator()
/*     */     {
/* 148 */       return new AbstractChar2ShortSortedMap.ValuesIterator(AbstractChar2ShortSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(short k) { return AbstractChar2ShortSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractChar2ShortSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractChar2ShortSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractCharBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Character, Short>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Short>> i)
/*     */     {
/* 118 */       this.i = i;
/*     */     }
/*     */     public char nextChar() {
/* 121 */       return ((Character)((Map.Entry)this.i.next()).getKey()).charValue(); } 
/* 122 */     public char previousChar() { return ((Character)((Map.Entry)this.i.previous()).getKey()).charValue(); } 
/*     */     public boolean hasNext() {
/* 124 */       return this.i.hasNext(); } 
/* 125 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractCharSortedSet
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(char k)
/*     */     {
/*  93 */       return AbstractChar2ShortSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractChar2ShortSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractChar2ShortSortedMap.this.clear(); } 
/*  96 */     public CharComparator comparator() { return AbstractChar2ShortSortedMap.this.comparator(); } 
/*  97 */     public char firstChar() { return AbstractChar2ShortSortedMap.this.firstCharKey(); } 
/*  98 */     public char lastChar() { return AbstractChar2ShortSortedMap.this.lastCharKey(); } 
/*  99 */     public CharSortedSet headSet(char to) { return AbstractChar2ShortSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public CharSortedSet tailSet(char from) { return AbstractChar2ShortSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public CharSortedSet subSet(char from, char to) { return AbstractChar2ShortSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public CharBidirectionalIterator iterator(char from) {
/* 103 */       return new AbstractChar2ShortSortedMap.KeySetIterator(AbstractChar2ShortSortedMap.this.entrySet().iterator(new AbstractChar2ShortMap.BasicEntry(from, (short)0))); } 
/* 104 */     public CharBidirectionalIterator iterator() { return new AbstractChar2ShortSortedMap.KeySetIterator(AbstractChar2ShortSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ShortSortedMap
 * JD-Core Version:    0.6.2
 */