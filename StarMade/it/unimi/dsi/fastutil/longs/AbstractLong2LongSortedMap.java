/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractLong2LongSortedMap extends AbstractLong2LongMap
/*     */   implements Long2LongSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Long2LongSortedMap headMap(Long to)
/*     */   {
/*  59 */     return headMap(to.longValue());
/*     */   }
/*     */ 
/*     */   public Long2LongSortedMap tailMap(Long from) {
/*  63 */     return tailMap(from.longValue());
/*     */   }
/*     */ 
/*     */   public Long2LongSortedMap subMap(Long from, Long to) {
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
/*     */   public LongCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Long, Long>> entrySet()
/*     */   {
/* 174 */     return long2LongEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractLongIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Long>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Long>> i)
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
/* 148 */       return new AbstractLong2LongSortedMap.ValuesIterator(AbstractLong2LongSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(long k) { return AbstractLong2LongSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractLong2LongSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractLong2LongSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractLongBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Long>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Long>> i)
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
/*  93 */       return AbstractLong2LongSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractLong2LongSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractLong2LongSortedMap.this.clear(); } 
/*  96 */     public LongComparator comparator() { return AbstractLong2LongSortedMap.this.comparator(); } 
/*  97 */     public long firstLong() { return AbstractLong2LongSortedMap.this.firstLongKey(); } 
/*  98 */     public long lastLong() { return AbstractLong2LongSortedMap.this.lastLongKey(); } 
/*  99 */     public LongSortedSet headSet(long to) { return AbstractLong2LongSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public LongSortedSet tailSet(long from) { return AbstractLong2LongSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public LongSortedSet subSet(long from, long to) { return AbstractLong2LongSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public LongBidirectionalIterator iterator(long from) {
/* 103 */       return new AbstractLong2LongSortedMap.KeySetIterator(AbstractLong2LongSortedMap.this.entrySet().iterator(new AbstractLong2LongMap.BasicEntry(from, 0L))); } 
/* 104 */     public LongBidirectionalIterator iterator() { return new AbstractLong2LongSortedMap.KeySetIterator(AbstractLong2LongSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2LongSortedMap
 * JD-Core Version:    0.6.2
 */