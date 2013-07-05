/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
/*     */ import it.unimi.dsi.fastutil.chars.CharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.CharIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractDouble2CharSortedMap extends AbstractDouble2CharMap
/*     */   implements Double2CharSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Double2CharSortedMap headMap(Double to)
/*     */   {
/*  59 */     return headMap(to.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2CharSortedMap tailMap(Double from) {
/*  63 */     return tailMap(from.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double2CharSortedMap subMap(Double from, Double to) {
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
/*     */   public CharCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Double, Character>> entrySet()
/*     */   {
/* 174 */     return double2CharEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractCharIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, Character>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Double, Character>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public char nextChar() {
/* 168 */       return ((Character)((Map.Entry)this.i.next()).getValue()).charValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractCharCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public CharIterator iterator()
/*     */     {
/* 148 */       return new AbstractDouble2CharSortedMap.ValuesIterator(AbstractDouble2CharSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(char k) { return AbstractDouble2CharSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractDouble2CharSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractDouble2CharSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractDoubleBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Double, Character>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Double, Character>> i)
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
/*  93 */       return AbstractDouble2CharSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractDouble2CharSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractDouble2CharSortedMap.this.clear(); } 
/*  96 */     public DoubleComparator comparator() { return AbstractDouble2CharSortedMap.this.comparator(); } 
/*  97 */     public double firstDouble() { return AbstractDouble2CharSortedMap.this.firstDoubleKey(); } 
/*  98 */     public double lastDouble() { return AbstractDouble2CharSortedMap.this.lastDoubleKey(); } 
/*  99 */     public DoubleSortedSet headSet(double to) { return AbstractDouble2CharSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public DoubleSortedSet tailSet(double from) { return AbstractDouble2CharSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public DoubleSortedSet subSet(double from, double to) { return AbstractDouble2CharSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public DoubleBidirectionalIterator iterator(double from) {
/* 103 */       return new AbstractDouble2CharSortedMap.KeySetIterator(AbstractDouble2CharSortedMap.this.entrySet().iterator(new AbstractDouble2CharMap.BasicEntry(from, '\000'))); } 
/* 104 */     public DoubleBidirectionalIterator iterator() { return new AbstractDouble2CharSortedMap.KeySetIterator(AbstractDouble2CharSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2CharSortedMap
 * JD-Core Version:    0.6.2
 */