/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractLong2ObjectSortedMap<V> extends AbstractLong2ObjectMap<V>
/*     */   implements Long2ObjectSortedMap<V>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Long2ObjectSortedMap<V> headMap(Long to)
/*     */   {
/*  58 */     return headMap(to.longValue());
/*     */   }
/*     */ 
/*     */   public Long2ObjectSortedMap<V> tailMap(Long from) {
/*  62 */     return tailMap(from.longValue());
/*     */   }
/*     */ 
/*     */   public Long2ObjectSortedMap<V> subMap(Long from, Long to) {
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
/*     */   public ObjectCollection<V> values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Long, V>> entrySet()
/*     */   {
/* 174 */     return long2ObjectEntrySet();
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
/*     */   protected class ValuesCollection extends AbstractObjectCollection<V>
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<V> iterator()
/*     */     {
/* 148 */       return new AbstractLong2ObjectSortedMap.ValuesIterator(AbstractLong2ObjectSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(Object k) { return AbstractLong2ObjectSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractLong2ObjectSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractLong2ObjectSortedMap.this.clear(); }
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
/*  92 */       return AbstractLong2ObjectSortedMap.this.containsKey(k); } 
/*  93 */     public int size() { return AbstractLong2ObjectSortedMap.this.size(); } 
/*  94 */     public void clear() { AbstractLong2ObjectSortedMap.this.clear(); } 
/*  95 */     public LongComparator comparator() { return AbstractLong2ObjectSortedMap.this.comparator(); } 
/*  96 */     public long firstLong() { return AbstractLong2ObjectSortedMap.this.firstLongKey(); } 
/*  97 */     public long lastLong() { return AbstractLong2ObjectSortedMap.this.lastLongKey(); } 
/*     */     public LongSortedSet headSet(long to) {
/*  99 */       return AbstractLong2ObjectSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public LongSortedSet tailSet(long from) { return AbstractLong2ObjectSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public LongSortedSet subSet(long from, long to) { return AbstractLong2ObjectSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public LongBidirectionalIterator iterator(long from) {
/* 103 */       return new AbstractLong2ObjectSortedMap.KeySetIterator(AbstractLong2ObjectSortedMap.this.entrySet().iterator(new AbstractLong2ObjectMap.BasicEntry(from, null))); } 
/* 104 */     public LongBidirectionalIterator iterator() { return new AbstractLong2ObjectSortedMap.KeySetIterator(AbstractLong2ObjectSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2ObjectSortedMap
 * JD-Core Version:    0.6.2
 */