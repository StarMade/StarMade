/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*     */ import it.unimi.dsi.fastutil.floats.FloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.FloatIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractLong2FloatSortedMap extends AbstractLong2FloatMap
/*     */   implements Long2FloatSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Long2FloatSortedMap headMap(Long to)
/*     */   {
/*  59 */     return headMap(to.longValue());
/*     */   }
/*     */ 
/*     */   public Long2FloatSortedMap tailMap(Long from) {
/*  63 */     return tailMap(from.longValue());
/*     */   }
/*     */ 
/*     */   public Long2FloatSortedMap subMap(Long from, Long to) {
/*  67 */     return subMap(from.longValue(), to.longValue());
/*     */   }
/*     */ 
/*     */   public Long firstKey() {
/*  71 */     return Long.valueOf(firstLongKey());
/*     */   }
/*     */ 
/*     */   public Long lastKey() {
/*  75 */     return Long.valueOf(lastLongKey());
/*     */   }
/*     */ 
/*     */   public LongSortedSet keySet()
/*     */   {
/*  89 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public FloatCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Long, Float>> entrySet()
/*     */   {
/* 174 */     return long2FloatEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractFloatIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Float>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Float>> i)
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
/* 148 */       return new AbstractLong2FloatSortedMap.ValuesIterator(AbstractLong2FloatSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(float k) { return AbstractLong2FloatSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractLong2FloatSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractLong2FloatSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractLongBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Float>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Float>> i)
/*     */     {
/* 118 */       this.i = i;
/*     */     }
/*     */     public long nextLong() {
/* 121 */       return ((Long)((Map.Entry)this.i.next()).getKey()).longValue(); } 
/* 122 */     public long previousLong() { return ((Long)((Map.Entry)this.i.previous()).getKey()).longValue(); } 
/*     */     public boolean hasNext() {
/* 124 */       return this.i.hasNext(); } 
/* 125 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractLongSortedSet
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(long k)
/*     */     {
/*  93 */       return AbstractLong2FloatSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractLong2FloatSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractLong2FloatSortedMap.this.clear(); } 
/*  96 */     public LongComparator comparator() { return AbstractLong2FloatSortedMap.this.comparator(); } 
/*  97 */     public long firstLong() { return AbstractLong2FloatSortedMap.this.firstLongKey(); } 
/*  98 */     public long lastLong() { return AbstractLong2FloatSortedMap.this.lastLongKey(); } 
/*  99 */     public LongSortedSet headSet(long to) { return AbstractLong2FloatSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public LongSortedSet tailSet(long from) { return AbstractLong2FloatSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public LongSortedSet subSet(long from, long to) { return AbstractLong2FloatSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public LongBidirectionalIterator iterator(long from) {
/* 103 */       return new AbstractLong2FloatSortedMap.KeySetIterator(AbstractLong2FloatSortedMap.this.entrySet().iterator(new AbstractLong2FloatMap.BasicEntry(from, 0.0F))); } 
/* 104 */     public LongBidirectionalIterator iterator() { return new AbstractLong2FloatSortedMap.KeySetIterator(AbstractLong2FloatSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2FloatSortedMap
 * JD-Core Version:    0.6.2
 */