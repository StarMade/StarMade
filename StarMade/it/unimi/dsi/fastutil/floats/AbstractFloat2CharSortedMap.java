/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
/*     */ import it.unimi.dsi.fastutil.chars.CharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.CharIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractFloat2CharSortedMap extends AbstractFloat2CharMap
/*     */   implements Float2CharSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Float2CharSortedMap headMap(Float to)
/*     */   {
/*  59 */     return headMap(to.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2CharSortedMap tailMap(Float from) {
/*  63 */     return tailMap(from.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2CharSortedMap subMap(Float from, Float to) {
/*  67 */     return subMap(from.floatValue(), to.floatValue());
/*     */   }
/*     */ 
/*     */   public Float firstKey() {
/*  71 */     return Float.valueOf(firstFloatKey());
/*     */   }
/*     */ 
/*     */   public Float lastKey() {
/*  75 */     return Float.valueOf(lastFloatKey());
/*     */   }
/*     */ 
/*     */   public FloatSortedSet keySet()
/*     */   {
/*  89 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public CharCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Float, Character>> entrySet()
/*     */   {
/* 174 */     return float2CharEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractCharIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, Character>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Character>> i)
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
/* 148 */       return new AbstractFloat2CharSortedMap.ValuesIterator(AbstractFloat2CharSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(char k) { return AbstractFloat2CharSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractFloat2CharSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractFloat2CharSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractFloatBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, Character>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Character>> i)
/*     */     {
/* 118 */       this.i = i;
/*     */     }
/*     */     public float nextFloat() {
/* 121 */       return ((Float)((Map.Entry)this.i.next()).getKey()).floatValue(); } 
/* 122 */     public float previousFloat() { return ((Float)((Map.Entry)this.i.previous()).getKey()).floatValue(); } 
/*     */     public boolean hasNext() {
/* 124 */       return this.i.hasNext(); } 
/* 125 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractFloatSortedSet
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(float k)
/*     */     {
/*  93 */       return AbstractFloat2CharSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractFloat2CharSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractFloat2CharSortedMap.this.clear(); } 
/*  96 */     public FloatComparator comparator() { return AbstractFloat2CharSortedMap.this.comparator(); } 
/*  97 */     public float firstFloat() { return AbstractFloat2CharSortedMap.this.firstFloatKey(); } 
/*  98 */     public float lastFloat() { return AbstractFloat2CharSortedMap.this.lastFloatKey(); } 
/*  99 */     public FloatSortedSet headSet(float to) { return AbstractFloat2CharSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public FloatSortedSet tailSet(float from) { return AbstractFloat2CharSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public FloatSortedSet subSet(float from, float to) { return AbstractFloat2CharSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public FloatBidirectionalIterator iterator(float from) {
/* 103 */       return new AbstractFloat2CharSortedMap.KeySetIterator(AbstractFloat2CharSortedMap.this.entrySet().iterator(new AbstractFloat2CharMap.BasicEntry(from, '\000'))); } 
/* 104 */     public FloatBidirectionalIterator iterator() { return new AbstractFloat2CharSortedMap.KeySetIterator(AbstractFloat2CharSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2CharSortedMap
 * JD-Core Version:    0.6.2
 */