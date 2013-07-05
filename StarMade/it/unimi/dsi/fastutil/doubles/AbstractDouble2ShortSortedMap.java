/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractDouble2ShortSortedMap extends AbstractDouble2ShortMap
/*     */   implements Double2ShortSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Double2ShortSortedMap headMap(Double to)
/*     */   {
/*  59 */     return headMap(to.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2ShortSortedMap tailMap(Double from) {
/*  63 */     return tailMap(from.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2ShortSortedMap subMap(Double from, Double to) {
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
/*     */   public ShortCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Double, Short>> entrySet()
/*     */   {
/* 174 */     return double2ShortEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractShortIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, Short>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Short>> i)
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
/* 148 */       return new AbstractDouble2ShortSortedMap.ValuesIterator(AbstractDouble2ShortSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(short k) { return AbstractDouble2ShortSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractDouble2ShortSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractDouble2ShortSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractDoubleBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, Short>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Short>> i)
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
/*  93 */       return AbstractDouble2ShortSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractDouble2ShortSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractDouble2ShortSortedMap.this.clear(); } 
/*  96 */     public DoubleComparator comparator() { return AbstractDouble2ShortSortedMap.this.comparator(); } 
/*  97 */     public double firstDouble() { return AbstractDouble2ShortSortedMap.this.firstDoubleKey(); } 
/*  98 */     public double lastDouble() { return AbstractDouble2ShortSortedMap.this.lastDoubleKey(); } 
/*  99 */     public DoubleSortedSet headSet(double to) { return AbstractDouble2ShortSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public DoubleSortedSet tailSet(double from) { return AbstractDouble2ShortSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public DoubleSortedSet subSet(double from, double to) { return AbstractDouble2ShortSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public DoubleBidirectionalIterator iterator(double from) {
/* 103 */       return new AbstractDouble2ShortSortedMap.KeySetIterator(AbstractDouble2ShortSortedMap.this.entrySet().iterator(new AbstractDouble2ShortMap.BasicEntry(from, (short)0))); } 
/* 104 */     public DoubleBidirectionalIterator iterator() { return new AbstractDouble2ShortSortedMap.KeySetIterator(AbstractDouble2ShortSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2ShortSortedMap
 * JD-Core Version:    0.6.2
 */