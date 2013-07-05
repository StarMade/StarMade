/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*     */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractFloat2DoubleSortedMap extends AbstractFloat2DoubleMap
/*     */   implements Float2DoubleSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Float2DoubleSortedMap headMap(Float to)
/*     */   {
/*  59 */     return headMap(to.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2DoubleSortedMap tailMap(Float from) {
/*  63 */     return tailMap(from.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2DoubleSortedMap subMap(Float from, Float to) {
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
/*     */   public DoubleCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Float, Double>> entrySet()
/*     */   {
/* 174 */     return float2DoubleEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractDoubleIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, Double>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Double>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public double nextDouble() {
/* 168 */       return ((Double)((Map.Entry)this.i.next()).getValue()).doubleValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractDoubleCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public DoubleIterator iterator()
/*     */     {
/* 148 */       return new AbstractFloat2DoubleSortedMap.ValuesIterator(AbstractFloat2DoubleSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(double k) { return AbstractFloat2DoubleSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractFloat2DoubleSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractFloat2DoubleSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractFloatBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, Double>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Double>> i)
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
/*  93 */       return AbstractFloat2DoubleSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractFloat2DoubleSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractFloat2DoubleSortedMap.this.clear(); } 
/*  96 */     public FloatComparator comparator() { return AbstractFloat2DoubleSortedMap.this.comparator(); } 
/*  97 */     public float firstFloat() { return AbstractFloat2DoubleSortedMap.this.firstFloatKey(); } 
/*  98 */     public float lastFloat() { return AbstractFloat2DoubleSortedMap.this.lastFloatKey(); } 
/*  99 */     public FloatSortedSet headSet(float to) { return AbstractFloat2DoubleSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public FloatSortedSet tailSet(float from) { return AbstractFloat2DoubleSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public FloatSortedSet subSet(float from, float to) { return AbstractFloat2DoubleSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public FloatBidirectionalIterator iterator(float from) {
/* 103 */       return new AbstractFloat2DoubleSortedMap.KeySetIterator(AbstractFloat2DoubleSortedMap.this.entrySet().iterator(new AbstractFloat2DoubleMap.BasicEntry(from, 0.0D))); } 
/* 104 */     public FloatBidirectionalIterator iterator() { return new AbstractFloat2DoubleSortedMap.KeySetIterator(AbstractFloat2DoubleSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2DoubleSortedMap
 * JD-Core Version:    0.6.2
 */