/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractLong2ShortSortedMap extends AbstractLong2ShortMap
/*     */   implements Long2ShortSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Long2ShortSortedMap headMap(Long to)
/*     */   {
/*  59 */     return headMap(to.longValue());
/*     */   }
/*     */ 
/*     */   public Long2ShortSortedMap tailMap(Long from) {
/*  63 */     return tailMap(from.longValue());
/*     */   }
/*     */ 
/*     */   public Long2ShortSortedMap subMap(Long from, Long to) {
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
/*     */   public ShortCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Long, Short>> entrySet()
/*     */   {
/* 174 */     return long2ShortEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractShortIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Short>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Short>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public short nextShort() {
/* 168 */       return ((Short)((Map.Entry)this.i.next()).getValue()).shortValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractShortCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ShortIterator iterator()
/*     */     {
/* 148 */       return new AbstractLong2ShortSortedMap.ValuesIterator(AbstractLong2ShortSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(short k) { return AbstractLong2ShortSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractLong2ShortSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractLong2ShortSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractLongBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Short>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Short>> i)
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
/*  93 */       return AbstractLong2ShortSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractLong2ShortSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractLong2ShortSortedMap.this.clear(); } 
/*  96 */     public LongComparator comparator() { return AbstractLong2ShortSortedMap.this.comparator(); } 
/*  97 */     public long firstLong() { return AbstractLong2ShortSortedMap.this.firstLongKey(); } 
/*  98 */     public long lastLong() { return AbstractLong2ShortSortedMap.this.lastLongKey(); } 
/*  99 */     public LongSortedSet headSet(long to) { return AbstractLong2ShortSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public LongSortedSet tailSet(long from) { return AbstractLong2ShortSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public LongSortedSet subSet(long from, long to) { return AbstractLong2ShortSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public LongBidirectionalIterator iterator(long from) {
/* 103 */       return new AbstractLong2ShortSortedMap.KeySetIterator(AbstractLong2ShortSortedMap.this.entrySet().iterator(new AbstractLong2ShortMap.BasicEntry(from, (short)0))); } 
/* 104 */     public LongBidirectionalIterator iterator() { return new AbstractLong2ShortSortedMap.KeySetIterator(AbstractLong2ShortSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2ShortSortedMap
 * JD-Core Version:    0.6.2
 */