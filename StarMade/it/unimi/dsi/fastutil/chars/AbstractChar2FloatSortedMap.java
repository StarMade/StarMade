/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*     */ import it.unimi.dsi.fastutil.floats.FloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.FloatIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractChar2FloatSortedMap extends AbstractChar2FloatMap
/*     */   implements Char2FloatSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Char2FloatSortedMap headMap(Character to)
/*     */   {
/*  59 */     return headMap(to.charValue());
/*     */   }
/*     */ 
/*     */   public Char2FloatSortedMap tailMap(Character from) {
/*  63 */     return tailMap(from.charValue());
/*     */   }
/*     */ 
/*     */   public Char2FloatSortedMap subMap(Character from, Character to) {
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
/*     */   public FloatCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Character, Float>> entrySet()
/*     */   {
/* 174 */     return char2FloatEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractFloatIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Character, Float>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, Float>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public float nextFloat() {
/* 168 */       return ((Float)((Map.Entry)this.i.next()).getValue()).floatValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractFloatCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public FloatIterator iterator()
/*     */     {
/* 148 */       return new AbstractChar2FloatSortedMap.ValuesIterator(AbstractChar2FloatSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(float k) { return AbstractChar2FloatSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractChar2FloatSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractChar2FloatSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractCharBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Character, Float>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, Float>> i)
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
/*  93 */       return AbstractChar2FloatSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractChar2FloatSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractChar2FloatSortedMap.this.clear(); } 
/*  96 */     public CharComparator comparator() { return AbstractChar2FloatSortedMap.this.comparator(); } 
/*  97 */     public char firstChar() { return AbstractChar2FloatSortedMap.this.firstCharKey(); } 
/*  98 */     public char lastChar() { return AbstractChar2FloatSortedMap.this.lastCharKey(); } 
/*  99 */     public CharSortedSet headSet(char to) { return AbstractChar2FloatSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public CharSortedSet tailSet(char from) { return AbstractChar2FloatSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public CharSortedSet subSet(char from, char to) { return AbstractChar2FloatSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public CharBidirectionalIterator iterator(char from) {
/* 103 */       return new AbstractChar2FloatSortedMap.KeySetIterator(AbstractChar2FloatSortedMap.this.entrySet().iterator(new AbstractChar2FloatMap.BasicEntry(from, 0.0F))); } 
/* 104 */     public CharBidirectionalIterator iterator() { return new AbstractChar2FloatSortedMap.KeySetIterator(AbstractChar2FloatSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2FloatSortedMap
 * JD-Core Version:    0.6.2
 */