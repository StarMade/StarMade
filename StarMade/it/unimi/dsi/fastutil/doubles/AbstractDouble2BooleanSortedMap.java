/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractDouble2BooleanSortedMap extends AbstractDouble2BooleanMap
/*     */   implements Double2BooleanSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Double2BooleanSortedMap headMap(Double to)
/*     */   {
/*  59 */     return headMap(to.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2BooleanSortedMap tailMap(Double from) {
/*  63 */     return tailMap(from.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2BooleanSortedMap subMap(Double from, Double to) {
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
/*     */   public BooleanCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Double, Boolean>> entrySet()
/*     */   {
/* 174 */     return double2BooleanEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractBooleanIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, Boolean>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Boolean>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public boolean nextBoolean() {
/* 168 */       return ((Boolean)((Map.Entry)this.i.next()).getValue()).booleanValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractBooleanCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public BooleanIterator iterator()
/*     */     {
/* 148 */       return new AbstractDouble2BooleanSortedMap.ValuesIterator(AbstractDouble2BooleanSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(boolean k) { return AbstractDouble2BooleanSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractDouble2BooleanSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractDouble2BooleanSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractDoubleBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, Boolean>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Boolean>> i)
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
/*  93 */       return AbstractDouble2BooleanSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractDouble2BooleanSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractDouble2BooleanSortedMap.this.clear(); } 
/*  96 */     public DoubleComparator comparator() { return AbstractDouble2BooleanSortedMap.this.comparator(); } 
/*  97 */     public double firstDouble() { return AbstractDouble2BooleanSortedMap.this.firstDoubleKey(); } 
/*  98 */     public double lastDouble() { return AbstractDouble2BooleanSortedMap.this.lastDoubleKey(); } 
/*  99 */     public DoubleSortedSet headSet(double to) { return AbstractDouble2BooleanSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public DoubleSortedSet tailSet(double from) { return AbstractDouble2BooleanSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public DoubleSortedSet subSet(double from, double to) { return AbstractDouble2BooleanSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public DoubleBidirectionalIterator iterator(double from) {
/* 103 */       return new AbstractDouble2BooleanSortedMap.KeySetIterator(AbstractDouble2BooleanSortedMap.this.entrySet().iterator(new AbstractDouble2BooleanMap.BasicEntry(from, false))); } 
/* 104 */     public DoubleBidirectionalIterator iterator() { return new AbstractDouble2BooleanSortedMap.KeySetIterator(AbstractDouble2BooleanSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2BooleanSortedMap
 * JD-Core Version:    0.6.2
 */