/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractDouble2ObjectSortedMap<V> extends AbstractDouble2ObjectMap<V>
/*     */   implements Double2ObjectSortedMap<V>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Double2ObjectSortedMap<V> headMap(Double to)
/*     */   {
/*  58 */     return headMap(to.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2ObjectSortedMap<V> tailMap(Double from) {
/*  62 */     return tailMap(from.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2ObjectSortedMap<V> subMap(Double from, Double to) {
/*  66 */     return subMap(from.doubleValue(), to.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double firstKey() {
/*  70 */     return Double.valueOf(firstDoubleKey());
/*     */   }
/*     */ 
/*     */   public Double lastKey() {
/*  74 */     return Double.valueOf(lastDoubleKey());
/*     */   }
/*     */ 
/*     */   public DoubleSortedSet keySet()
/*     */   {
/*  88 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public ObjectCollection<V> values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Double, V>> entrySet()
/*     */   {
/* 174 */     return double2ObjectEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator<V> extends AbstractObjectIterator<V>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, V>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, V>> i)
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
/* 148 */       return new AbstractDouble2ObjectSortedMap.ValuesIterator(AbstractDouble2ObjectSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(Object k) { return AbstractDouble2ObjectSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractDouble2ObjectSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractDouble2ObjectSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator<V> extends AbstractDoubleBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, V>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, V>> i)
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
/*  92 */       return AbstractDouble2ObjectSortedMap.this.containsKey(k); } 
/*  93 */     public int size() { return AbstractDouble2ObjectSortedMap.this.size(); } 
/*  94 */     public void clear() { AbstractDouble2ObjectSortedMap.this.clear(); } 
/*  95 */     public DoubleComparator comparator() { return AbstractDouble2ObjectSortedMap.this.comparator(); } 
/*  96 */     public double firstDouble() { return AbstractDouble2ObjectSortedMap.this.firstDoubleKey(); } 
/*  97 */     public double lastDouble() { return AbstractDouble2ObjectSortedMap.this.lastDoubleKey(); } 
/*     */     public DoubleSortedSet headSet(double to) {
/*  99 */       return AbstractDouble2ObjectSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public DoubleSortedSet tailSet(double from) { return AbstractDouble2ObjectSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public DoubleSortedSet subSet(double from, double to) { return AbstractDouble2ObjectSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public DoubleBidirectionalIterator iterator(double from) {
/* 103 */       return new AbstractDouble2ObjectSortedMap.KeySetIterator(AbstractDouble2ObjectSortedMap.this.entrySet().iterator(new AbstractDouble2ObjectMap.BasicEntry(from, null))); } 
/* 104 */     public DoubleBidirectionalIterator iterator() { return new AbstractDouble2ObjectSortedMap.KeySetIterator(AbstractDouble2ObjectSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2ObjectSortedMap
 * JD-Core Version:    0.6.2
 */