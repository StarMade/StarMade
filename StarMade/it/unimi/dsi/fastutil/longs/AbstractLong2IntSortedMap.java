/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
/*     */ import it.unimi.dsi.fastutil.ints.IntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractLong2IntSortedMap extends AbstractLong2IntMap
/*     */   implements Long2IntSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Long2IntSortedMap headMap(Long to)
/*     */   {
/*  59 */     return headMap(to.longValue());
/*     */   }
/*     */ 
/*     */   public Long2IntSortedMap tailMap(Long from) {
/*  63 */     return tailMap(from.longValue());
/*     */   }
/*     */ 
/*     */   public Long2IntSortedMap subMap(Long from, Long to) {
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
/*     */   public IntCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Long, Integer>> entrySet()
/*     */   {
/* 174 */     return long2IntEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractIntIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Integer>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Integer>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public int nextInt() {
/* 168 */       return ((Integer)((Map.Entry)this.i.next()).getValue()).intValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractIntCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public IntIterator iterator()
/*     */     {
/* 148 */       return new AbstractLong2IntSortedMap.ValuesIterator(AbstractLong2IntSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(int k) { return AbstractLong2IntSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractLong2IntSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractLong2IntSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractLongBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Integer>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Integer>> i)
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
/*  93 */       return AbstractLong2IntSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractLong2IntSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractLong2IntSortedMap.this.clear(); } 
/*  96 */     public LongComparator comparator() { return AbstractLong2IntSortedMap.this.comparator(); } 
/*  97 */     public long firstLong() { return AbstractLong2IntSortedMap.this.firstLongKey(); } 
/*  98 */     public long lastLong() { return AbstractLong2IntSortedMap.this.lastLongKey(); } 
/*  99 */     public LongSortedSet headSet(long to) { return AbstractLong2IntSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public LongSortedSet tailSet(long from) { return AbstractLong2IntSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public LongSortedSet subSet(long from, long to) { return AbstractLong2IntSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public LongBidirectionalIterator iterator(long from) {
/* 103 */       return new AbstractLong2IntSortedMap.KeySetIterator(AbstractLong2IntSortedMap.this.entrySet().iterator(new AbstractLong2IntMap.BasicEntry(from, 0))); } 
/* 104 */     public LongBidirectionalIterator iterator() { return new AbstractLong2IntSortedMap.KeySetIterator(AbstractLong2IntSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2IntSortedMap
 * JD-Core Version:    0.6.2
 */