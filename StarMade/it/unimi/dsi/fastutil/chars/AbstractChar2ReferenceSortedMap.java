/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractChar2ReferenceSortedMap<V> extends AbstractChar2ReferenceMap<V>
/*     */   implements Char2ReferenceSortedMap<V>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Char2ReferenceSortedMap<V> headMap(Character to)
/*     */   {
/*  58 */     return headMap(to.charValue());
/*     */   }
/*     */ 
/*     */   public Char2ReferenceSortedMap<V> tailMap(Character from) {
/*  62 */     return tailMap(from.charValue());
/*     */   }
/*     */ 
/*     */   public Char2ReferenceSortedMap<V> subMap(Character from, Character to) {
/*  66 */     return subMap(from.charValue(), to.charValue());
/*     */   }
/*     */ 
/*     */   public Character firstKey() {
/*  70 */     return Character.valueOf(firstCharKey());
/*     */   }
/*     */ 
/*     */   public Character lastKey() {
/*  74 */     return Character.valueOf(lastCharKey());
/*     */   }
/*     */ 
/*     */   public CharSortedSet keySet()
/*     */   {
/*  88 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public ReferenceCollection<V> values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Character, V>> entrySet()
/*     */   {
/* 174 */     return char2ReferenceEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator<V> extends AbstractObjectIterator<V>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Character, V>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, V>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public V next() {
/* 168 */       return ((Map.Entry)this.i.next()).getValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractReferenceCollection<V>
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<V> iterator()
/*     */     {
/* 148 */       return new AbstractChar2ReferenceSortedMap.ValuesIterator(AbstractChar2ReferenceSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(Object k) { return AbstractChar2ReferenceSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractChar2ReferenceSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractChar2ReferenceSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator<V> extends AbstractCharBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Character, V>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, V>> i)
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
/*  92 */       return AbstractChar2ReferenceSortedMap.this.containsKey(k); } 
/*  93 */     public int size() { return AbstractChar2ReferenceSortedMap.this.size(); } 
/*  94 */     public void clear() { AbstractChar2ReferenceSortedMap.this.clear(); } 
/*  95 */     public CharComparator comparator() { return AbstractChar2ReferenceSortedMap.this.comparator(); } 
/*  96 */     public char firstChar() { return AbstractChar2ReferenceSortedMap.this.firstCharKey(); } 
/*  97 */     public char lastChar() { return AbstractChar2ReferenceSortedMap.this.lastCharKey(); } 
/*     */     public CharSortedSet headSet(char to) {
/*  99 */       return AbstractChar2ReferenceSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public CharSortedSet tailSet(char from) { return AbstractChar2ReferenceSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public CharSortedSet subSet(char from, char to) { return AbstractChar2ReferenceSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public CharBidirectionalIterator iterator(char from) {
/* 103 */       return new AbstractChar2ReferenceSortedMap.KeySetIterator(AbstractChar2ReferenceSortedMap.this.entrySet().iterator(new AbstractChar2ReferenceMap.BasicEntry(from, null))); } 
/* 104 */     public CharBidirectionalIterator iterator() { return new AbstractChar2ReferenceSortedMap.KeySetIterator(AbstractChar2ReferenceSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ReferenceSortedMap
 * JD-Core Version:    0.6.2
 */