/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractFloat2FloatSortedMap extends AbstractFloat2FloatMap
/*     */   implements Float2FloatSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Float2FloatSortedMap headMap(Float to)
/*     */   {
/*  59 */     return headMap(to.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2FloatSortedMap tailMap(Float from) {
/*  63 */     return tailMap(from.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2FloatSortedMap subMap(Float from, Float to) {
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
/*     */   public FloatCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Float, Float>> entrySet()
/*     */   {
/* 174 */     return float2FloatEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractFloatIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, Float>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Float>> i)
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
/* 148 */       return new AbstractFloat2FloatSortedMap.ValuesIterator(AbstractFloat2FloatSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(float k) { return AbstractFloat2FloatSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractFloat2FloatSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractFloat2FloatSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractFloatBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, Float>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Float>> i)
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
/*  93 */       return AbstractFloat2FloatSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractFloat2FloatSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractFloat2FloatSortedMap.this.clear(); } 
/*  96 */     public FloatComparator comparator() { return AbstractFloat2FloatSortedMap.this.comparator(); } 
/*  97 */     public float firstFloat() { return AbstractFloat2FloatSortedMap.this.firstFloatKey(); } 
/*  98 */     public float lastFloat() { return AbstractFloat2FloatSortedMap.this.lastFloatKey(); } 
/*  99 */     public FloatSortedSet headSet(float to) { return AbstractFloat2FloatSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public FloatSortedSet tailSet(float from) { return AbstractFloat2FloatSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public FloatSortedSet subSet(float from, float to) { return AbstractFloat2FloatSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public FloatBidirectionalIterator iterator(float from) {
/* 103 */       return new AbstractFloat2FloatSortedMap.KeySetIterator(AbstractFloat2FloatSortedMap.this.entrySet().iterator(new AbstractFloat2FloatMap.BasicEntry(from, 0.0F))); } 
/* 104 */     public FloatBidirectionalIterator iterator() { return new AbstractFloat2FloatSortedMap.KeySetIterator(AbstractFloat2FloatSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2FloatSortedMap
 * JD-Core Version:    0.6.2
 */