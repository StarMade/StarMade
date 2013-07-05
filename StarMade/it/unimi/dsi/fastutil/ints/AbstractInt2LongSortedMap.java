/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*     */ import it.unimi.dsi.fastutil.longs.LongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractInt2LongSortedMap extends AbstractInt2LongMap
/*     */   implements Int2LongSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Int2LongSortedMap headMap(Integer to)
/*     */   {
/*  59 */     return headMap(to.intValue());
/*     */   }
/*     */ 
/*     */   public Int2LongSortedMap tailMap(Integer from) {
/*  63 */     return tailMap(from.intValue());
/*     */   }
/*     */ 
/*     */   public Int2LongSortedMap subMap(Integer from, Integer to) {
/*  67 */     return subMap(from.intValue(), to.intValue());
/*     */   }
/*     */ 
/*     */   public Integer firstKey() {
/*  71 */     return Integer.valueOf(firstIntKey());
/*     */   }
/*     */ 
/*     */   public Integer lastKey() {
/*  75 */     return Integer.valueOf(lastIntKey());
/*     */   }
/*     */ 
/*     */   public IntSortedSet keySet()
/*     */   {
/*  89 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public LongCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Integer, Long>> entrySet()
/*     */   {
/* 174 */     return int2LongEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractLongIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Integer, Long>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Long>> i)
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
/* 148 */       return new AbstractInt2LongSortedMap.ValuesIterator(AbstractInt2LongSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(long k) { return AbstractInt2LongSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractInt2LongSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractInt2LongSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractIntBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Integer, Long>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Long>> i)
/*     */     {
/* 118 */       this.i = i;
/*     */     }
/*     */     public int nextInt() {
/* 121 */       return ((Integer)((Map.Entry)this.i.next()).getKey()).intValue(); } 
/* 122 */     public int previousInt() { return ((Integer)((Map.Entry)this.i.previous()).getKey()).intValue(); } 
/*     */     public boolean hasNext() {
/* 124 */       return this.i.hasNext(); } 
/* 125 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractIntSortedSet
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(int k)
/*     */     {
/*  93 */       return AbstractInt2LongSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractInt2LongSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractInt2LongSortedMap.this.clear(); } 
/*  96 */     public IntComparator comparator() { return AbstractInt2LongSortedMap.this.comparator(); } 
/*  97 */     public int firstInt() { return AbstractInt2LongSortedMap.this.firstIntKey(); } 
/*  98 */     public int lastInt() { return AbstractInt2LongSortedMap.this.lastIntKey(); } 
/*  99 */     public IntSortedSet headSet(int to) { return AbstractInt2LongSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public IntSortedSet tailSet(int from) { return AbstractInt2LongSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public IntSortedSet subSet(int from, int to) { return AbstractInt2LongSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public IntBidirectionalIterator iterator(int from) {
/* 103 */       return new AbstractInt2LongSortedMap.KeySetIterator(AbstractInt2LongSortedMap.this.entrySet().iterator(new AbstractInt2LongMap.BasicEntry(from, 0L))); } 
/* 104 */     public IntBidirectionalIterator iterator() { return new AbstractInt2LongSortedMap.KeySetIterator(AbstractInt2LongSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2LongSortedMap
 * JD-Core Version:    0.6.2
 */