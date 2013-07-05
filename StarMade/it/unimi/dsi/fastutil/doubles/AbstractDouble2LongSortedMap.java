/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*     */ import it.unimi.dsi.fastutil.longs.LongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractDouble2LongSortedMap extends AbstractDouble2LongMap
/*     */   implements Double2LongSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Double2LongSortedMap headMap(Double to)
/*     */   {
/*  59 */     return headMap(to.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2LongSortedMap tailMap(Double from) {
/*  63 */     return tailMap(from.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2LongSortedMap subMap(Double from, Double to) {
/*  67 */     return subMap(from.doubleValue(), to.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double firstKey() {
/*  71 */     return Double.valueOf(firstDoubleKey());
/*     */   }
/*     */ 
/*     */   public Double lastKey() {
/*  75 */     return Double.valueOf(lastDoubleKey());
/*     */   }
/*     */ 
/*     */   public DoubleSortedSet keySet()
/*     */   {
/*  89 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public LongCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Double, Long>> entrySet()
/*     */   {
/* 174 */     return double2LongEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractLongIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, Long>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Long>> i)
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
/* 148 */       return new AbstractDouble2LongSortedMap.ValuesIterator(AbstractDouble2LongSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(long k) { return AbstractDouble2LongSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractDouble2LongSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractDouble2LongSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractDoubleBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, Long>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Long>> i)
/*     */     {
/* 118 */       this.i = i;
/*     */     }
/*     */     public double nextDouble() {
/* 121 */       return ((Double)((Map.Entry)this.i.next()).getKey()).doubleValue(); } 
/* 122 */     public double previousDouble() { return ((Double)((Map.Entry)this.i.previous()).getKey()).doubleValue(); } 
/*     */     public boolean hasNext() {
/* 124 */       return this.i.hasNext(); } 
/* 125 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractDoubleSortedSet
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(double k)
/*     */     {
/*  93 */       return AbstractDouble2LongSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractDouble2LongSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractDouble2LongSortedMap.this.clear(); } 
/*  96 */     public DoubleComparator comparator() { return AbstractDouble2LongSortedMap.this.comparator(); } 
/*  97 */     public double firstDouble() { return AbstractDouble2LongSortedMap.this.firstDoubleKey(); } 
/*  98 */     public double lastDouble() { return AbstractDouble2LongSortedMap.this.lastDoubleKey(); } 
/*  99 */     public DoubleSortedSet headSet(double to) { return AbstractDouble2LongSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public DoubleSortedSet tailSet(double from) { return AbstractDouble2LongSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public DoubleSortedSet subSet(double from, double to) { return AbstractDouble2LongSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public DoubleBidirectionalIterator iterator(double from) {
/* 103 */       return new AbstractDouble2LongSortedMap.KeySetIterator(AbstractDouble2LongSortedMap.this.entrySet().iterator(new AbstractDouble2LongMap.BasicEntry(from, 0L))); } 
/* 104 */     public DoubleBidirectionalIterator iterator() { return new AbstractDouble2LongSortedMap.KeySetIterator(AbstractDouble2LongSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2LongSortedMap
 * JD-Core Version:    0.6.2
 */