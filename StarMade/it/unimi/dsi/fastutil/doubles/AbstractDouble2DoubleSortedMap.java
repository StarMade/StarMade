/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractDouble2DoubleSortedMap extends AbstractDouble2DoubleMap
/*     */   implements Double2DoubleSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Double2DoubleSortedMap headMap(Double to)
/*     */   {
/*  59 */     return headMap(to.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2DoubleSortedMap tailMap(Double from) {
/*  63 */     return tailMap(from.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2DoubleSortedMap subMap(Double from, Double to) {
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
/*     */   public DoubleCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Double, Double>> entrySet()
/*     */   {
/* 174 */     return double2DoubleEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractDoubleIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, Double>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Double>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public double nextDouble() {
/* 168 */       return ((Double)((Map.Entry)this.i.next()).getValue()).doubleValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractDoubleCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public DoubleIterator iterator()
/*     */     {
/* 148 */       return new AbstractDouble2DoubleSortedMap.ValuesIterator(AbstractDouble2DoubleSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(double k) { return AbstractDouble2DoubleSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractDouble2DoubleSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractDouble2DoubleSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractDoubleBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, Double>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Double>> i)
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
/*  93 */       return AbstractDouble2DoubleSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractDouble2DoubleSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractDouble2DoubleSortedMap.this.clear(); } 
/*  96 */     public DoubleComparator comparator() { return AbstractDouble2DoubleSortedMap.this.comparator(); } 
/*  97 */     public double firstDouble() { return AbstractDouble2DoubleSortedMap.this.firstDoubleKey(); } 
/*  98 */     public double lastDouble() { return AbstractDouble2DoubleSortedMap.this.lastDoubleKey(); } 
/*  99 */     public DoubleSortedSet headSet(double to) { return AbstractDouble2DoubleSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public DoubleSortedSet tailSet(double from) { return AbstractDouble2DoubleSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public DoubleSortedSet subSet(double from, double to) { return AbstractDouble2DoubleSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public DoubleBidirectionalIterator iterator(double from) {
/* 103 */       return new AbstractDouble2DoubleSortedMap.KeySetIterator(AbstractDouble2DoubleSortedMap.this.entrySet().iterator(new AbstractDouble2DoubleMap.BasicEntry(from, 0.0D))); } 
/* 104 */     public DoubleBidirectionalIterator iterator() { return new AbstractDouble2DoubleSortedMap.KeySetIterator(AbstractDouble2DoubleSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2DoubleSortedMap
 * JD-Core Version:    0.6.2
 */