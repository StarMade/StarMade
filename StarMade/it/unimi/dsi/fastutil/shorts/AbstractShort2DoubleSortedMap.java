/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*     */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractShort2DoubleSortedMap extends AbstractShort2DoubleMap
/*     */   implements Short2DoubleSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Short2DoubleSortedMap headMap(Short to)
/*     */   {
/*  59 */     return headMap(to.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2DoubleSortedMap tailMap(Short from) {
/*  63 */     return tailMap(from.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2DoubleSortedMap subMap(Short from, Short to) {
/*  67 */     return subMap(from.shortValue(), to.shortValue());
/*     */   }
/*     */ 
/*     */   public Short firstKey() {
/*  71 */     return Short.valueOf(firstShortKey());
/*     */   }
/*     */ 
/*     */   public Short lastKey() {
/*  75 */     return Short.valueOf(lastShortKey());
/*     */   }
/*     */ 
/*     */   public ShortSortedSet keySet()
/*     */   {
/*  89 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public DoubleCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Short, Double>> entrySet()
/*     */   {
/* 174 */     return short2DoubleEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractDoubleIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, Double>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Double>> i)
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
/* 148 */       return new AbstractShort2DoubleSortedMap.ValuesIterator(AbstractShort2DoubleSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(double k) { return AbstractShort2DoubleSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractShort2DoubleSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractShort2DoubleSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractShortBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, Double>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Double>> i)
/*     */     {
/* 118 */       this.i = i;
/*     */     }
/*     */     public short nextShort() {
/* 121 */       return ((Short)((Map.Entry)this.i.next()).getKey()).shortValue(); } 
/* 122 */     public short previousShort() { return ((Short)((Map.Entry)this.i.previous()).getKey()).shortValue(); } 
/*     */     public boolean hasNext() {
/* 124 */       return this.i.hasNext(); } 
/* 125 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractShortSortedSet
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(short k)
/*     */     {
/*  93 */       return AbstractShort2DoubleSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractShort2DoubleSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractShort2DoubleSortedMap.this.clear(); } 
/*  96 */     public ShortComparator comparator() { return AbstractShort2DoubleSortedMap.this.comparator(); } 
/*  97 */     public short firstShort() { return AbstractShort2DoubleSortedMap.this.firstShortKey(); } 
/*  98 */     public short lastShort() { return AbstractShort2DoubleSortedMap.this.lastShortKey(); } 
/*  99 */     public ShortSortedSet headSet(short to) { return AbstractShort2DoubleSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ShortSortedSet tailSet(short from) { return AbstractShort2DoubleSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ShortSortedSet subSet(short from, short to) { return AbstractShort2DoubleSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ShortBidirectionalIterator iterator(short from) {
/* 103 */       return new AbstractShort2DoubleSortedMap.KeySetIterator(AbstractShort2DoubleSortedMap.this.entrySet().iterator(new AbstractShort2DoubleMap.BasicEntry(from, 0.0D))); } 
/* 104 */     public ShortBidirectionalIterator iterator() { return new AbstractShort2DoubleSortedMap.KeySetIterator(AbstractShort2DoubleSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2DoubleSortedMap
 * JD-Core Version:    0.6.2
 */