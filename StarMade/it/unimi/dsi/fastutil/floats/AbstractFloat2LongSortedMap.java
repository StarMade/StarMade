/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*     */ import it.unimi.dsi.fastutil.longs.LongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractFloat2LongSortedMap extends AbstractFloat2LongMap
/*     */   implements Float2LongSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Float2LongSortedMap headMap(Float to)
/*     */   {
/*  59 */     return headMap(to.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2LongSortedMap tailMap(Float from) {
/*  63 */     return tailMap(from.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2LongSortedMap subMap(Float from, Float to) {
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
/*     */   public LongCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Float, Long>> entrySet()
/*     */   {
/* 174 */     return float2LongEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractLongIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, Long>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Long>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public long nextLong() {
/* 168 */       return ((Long)((Map.Entry)this.i.next()).getValue()).longValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractLongCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public LongIterator iterator()
/*     */     {
/* 148 */       return new AbstractFloat2LongSortedMap.ValuesIterator(AbstractFloat2LongSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(long k) { return AbstractFloat2LongSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractFloat2LongSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractFloat2LongSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractFloatBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, Long>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Long>> i)
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
/*  93 */       return AbstractFloat2LongSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractFloat2LongSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractFloat2LongSortedMap.this.clear(); } 
/*  96 */     public FloatComparator comparator() { return AbstractFloat2LongSortedMap.this.comparator(); } 
/*  97 */     public float firstFloat() { return AbstractFloat2LongSortedMap.this.firstFloatKey(); } 
/*  98 */     public float lastFloat() { return AbstractFloat2LongSortedMap.this.lastFloatKey(); } 
/*  99 */     public FloatSortedSet headSet(float to) { return AbstractFloat2LongSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public FloatSortedSet tailSet(float from) { return AbstractFloat2LongSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public FloatSortedSet subSet(float from, float to) { return AbstractFloat2LongSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public FloatBidirectionalIterator iterator(float from) {
/* 103 */       return new AbstractFloat2LongSortedMap.KeySetIterator(AbstractFloat2LongSortedMap.this.entrySet().iterator(new AbstractFloat2LongMap.BasicEntry(from, 0L))); } 
/* 104 */     public FloatBidirectionalIterator iterator() { return new AbstractFloat2LongSortedMap.KeySetIterator(AbstractFloat2LongSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2LongSortedMap
 * JD-Core Version:    0.6.2
 */