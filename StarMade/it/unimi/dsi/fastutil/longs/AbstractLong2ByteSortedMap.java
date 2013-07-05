/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
/*     */ import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
/*     */ import it.unimi.dsi.fastutil.bytes.ByteCollection;
/*     */ import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractLong2ByteSortedMap extends AbstractLong2ByteMap
/*     */   implements Long2ByteSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Long2ByteSortedMap headMap(Long to)
/*     */   {
/*  59 */     return headMap(to.longValue());
/*     */   }
/*     */ 
/*     */   public Long2ByteSortedMap tailMap(Long from) {
/*  63 */     return tailMap(from.longValue());
/*     */   }
/*     */ 
/*     */   public Long2ByteSortedMap subMap(Long from, Long to) {
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
/*     */   public ByteCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Long, Byte>> entrySet()
/*     */   {
/* 174 */     return long2ByteEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractByteIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Byte>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Byte>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public byte nextByte() {
/* 168 */       return ((Byte)((Map.Entry)this.i.next()).getValue()).byteValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractByteCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ByteIterator iterator()
/*     */     {
/* 148 */       return new AbstractLong2ByteSortedMap.ValuesIterator(AbstractLong2ByteSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(byte k) { return AbstractLong2ByteSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractLong2ByteSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractLong2ByteSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractLongBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Byte>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Byte>> i)
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
/*  93 */       return AbstractLong2ByteSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractLong2ByteSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractLong2ByteSortedMap.this.clear(); } 
/*  96 */     public LongComparator comparator() { return AbstractLong2ByteSortedMap.this.comparator(); } 
/*  97 */     public long firstLong() { return AbstractLong2ByteSortedMap.this.firstLongKey(); } 
/*  98 */     public long lastLong() { return AbstractLong2ByteSortedMap.this.lastLongKey(); } 
/*  99 */     public LongSortedSet headSet(long to) { return AbstractLong2ByteSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public LongSortedSet tailSet(long from) { return AbstractLong2ByteSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public LongSortedSet subSet(long from, long to) { return AbstractLong2ByteSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public LongBidirectionalIterator iterator(long from) {
/* 103 */       return new AbstractLong2ByteSortedMap.KeySetIterator(AbstractLong2ByteSortedMap.this.entrySet().iterator(new AbstractLong2ByteMap.BasicEntry(from, (byte)0))); } 
/* 104 */     public LongBidirectionalIterator iterator() { return new AbstractLong2ByteSortedMap.KeySetIterator(AbstractLong2ByteSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2ByteSortedMap
 * JD-Core Version:    0.6.2
 */