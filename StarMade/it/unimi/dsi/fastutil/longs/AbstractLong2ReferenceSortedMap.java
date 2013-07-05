/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractLong2ReferenceSortedMap<V> extends AbstractLong2ReferenceMap<V>
/*     */   implements Long2ReferenceSortedMap<V>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Long2ReferenceSortedMap<V> headMap(Long to)
/*     */   {
/*  58 */     return headMap(to.longValue());
/*     */   }
/*     */ 
/*     */   public Long2ReferenceSortedMap<V> tailMap(Long from) {
/*  62 */     return tailMap(from.longValue());
/*     */   }
/*     */ 
/*     */   public Long2ReferenceSortedMap<V> subMap(Long from, Long to) {
/*  66 */     return subMap(from.longValue(), to.longValue());
/*     */   }
/*     */ 
/*     */   public Long firstKey() {
/*  70 */     return Long.valueOf(firstLongKey());
/*     */   }
/*     */ 
/*     */   public Long lastKey() {
/*  74 */     return Long.valueOf(lastLongKey());
/*     */   }
/*     */ 
/*     */   public LongSortedSet keySet()
/*     */   {
/*  88 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public ReferenceCollection<V> values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Long, V>> entrySet()
/*     */   {
/* 174 */     return long2ReferenceEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator<V> extends AbstractObjectIterator<V>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, V>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, V>> i)
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
/* 148 */       return new AbstractLong2ReferenceSortedMap.ValuesIterator(AbstractLong2ReferenceSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(Object k) { return AbstractLong2ReferenceSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractLong2ReferenceSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractLong2ReferenceSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator<V> extends AbstractLongBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, V>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, V>> i)
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
/*  92 */       return AbstractLong2ReferenceSortedMap.this.containsKey(k); } 
/*  93 */     public int size() { return AbstractLong2ReferenceSortedMap.this.size(); } 
/*  94 */     public void clear() { AbstractLong2ReferenceSortedMap.this.clear(); } 
/*  95 */     public LongComparator comparator() { return AbstractLong2ReferenceSortedMap.this.comparator(); } 
/*  96 */     public long firstLong() { return AbstractLong2ReferenceSortedMap.this.firstLongKey(); } 
/*  97 */     public long lastLong() { return AbstractLong2ReferenceSortedMap.this.lastLongKey(); } 
/*     */     public LongSortedSet headSet(long to) {
/*  99 */       return AbstractLong2ReferenceSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public LongSortedSet tailSet(long from) { return AbstractLong2ReferenceSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public LongSortedSet subSet(long from, long to) { return AbstractLong2ReferenceSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public LongBidirectionalIterator iterator(long from) {
/* 103 */       return new AbstractLong2ReferenceSortedMap.KeySetIterator(AbstractLong2ReferenceSortedMap.this.entrySet().iterator(new AbstractLong2ReferenceMap.BasicEntry(from, null))); } 
/* 104 */     public LongBidirectionalIterator iterator() { return new AbstractLong2ReferenceSortedMap.KeySetIterator(AbstractLong2ReferenceSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2ReferenceSortedMap
 * JD-Core Version:    0.6.2
 */