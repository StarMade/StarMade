/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractChar2CharSortedMap extends AbstractChar2CharMap
/*     */   implements Char2CharSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Char2CharSortedMap headMap(Character to)
/*     */   {
/*  59 */     return headMap(to.charValue());
/*     */   }
/*     */ 
/*     */   public Char2CharSortedMap tailMap(Character from) {
/*  63 */     return tailMap(from.charValue());
/*     */   }
/*     */ 
/*     */   public Char2CharSortedMap subMap(Character from, Character to) {
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
/*     */   public CharCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Character, Character>> entrySet()
/*     */   {
/* 174 */     return char2CharEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractCharIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Character, Character>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Character>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public char nextChar() {
/* 168 */       return ((Character)((Map.Entry)this.i.next()).getValue()).charValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractCharCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public CharIterator iterator()
/*     */     {
/* 148 */       return new AbstractChar2CharSortedMap.ValuesIterator(AbstractChar2CharSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(char k) { return AbstractChar2CharSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractChar2CharSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractChar2CharSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractCharBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Character, Character>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Character>> i)
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
/*  93 */       return AbstractChar2CharSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractChar2CharSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractChar2CharSortedMap.this.clear(); } 
/*  96 */     public CharComparator comparator() { return AbstractChar2CharSortedMap.this.comparator(); } 
/*  97 */     public char firstChar() { return AbstractChar2CharSortedMap.this.firstCharKey(); } 
/*  98 */     public char lastChar() { return AbstractChar2CharSortedMap.this.lastCharKey(); } 
/*  99 */     public CharSortedSet headSet(char to) { return AbstractChar2CharSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public CharSortedSet tailSet(char from) { return AbstractChar2CharSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public CharSortedSet subSet(char from, char to) { return AbstractChar2CharSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public CharBidirectionalIterator iterator(char from) {
/* 103 */       return new AbstractChar2CharSortedMap.KeySetIterator(AbstractChar2CharSortedMap.this.entrySet().iterator(new AbstractChar2CharMap.BasicEntry(from, '\000'))); } 
/* 104 */     public CharBidirectionalIterator iterator() { return new AbstractChar2CharSortedMap.KeySetIterator(AbstractChar2CharSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2CharSortedMap
 * JD-Core Version:    0.6.2
 */