/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractInt2ShortSortedMap extends AbstractInt2ShortMap
/*     */   implements Int2ShortSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Int2ShortSortedMap headMap(Integer to)
/*     */   {
/*  59 */     return headMap(to.intValue());
/*     */   }
/*     */ 
/*     */   public Int2ShortSortedMap tailMap(Integer from) {
/*  63 */     return tailMap(from.intValue());
/*     */   }
/*     */ 
/*     */   public Int2ShortSortedMap subMap(Integer from, Integer to) {
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
/*     */   public ShortCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Integer, Short>> entrySet()
/*     */   {
/* 174 */     return int2ShortEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractShortIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Integer, Short>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Short>> i)
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
/* 148 */       return new AbstractInt2ShortSortedMap.ValuesIterator(AbstractInt2ShortSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(short k) { return AbstractInt2ShortSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractInt2ShortSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractInt2ShortSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractIntBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Integer, Short>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Short>> i)
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
/*  93 */       return AbstractInt2ShortSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractInt2ShortSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractInt2ShortSortedMap.this.clear(); } 
/*  96 */     public IntComparator comparator() { return AbstractInt2ShortSortedMap.this.comparator(); } 
/*  97 */     public int firstInt() { return AbstractInt2ShortSortedMap.this.firstIntKey(); } 
/*  98 */     public int lastInt() { return AbstractInt2ShortSortedMap.this.lastIntKey(); } 
/*  99 */     public IntSortedSet headSet(int to) { return AbstractInt2ShortSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public IntSortedSet tailSet(int from) { return AbstractInt2ShortSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public IntSortedSet subSet(int from, int to) { return AbstractInt2ShortSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public IntBidirectionalIterator iterator(int from) {
/* 103 */       return new AbstractInt2ShortSortedMap.KeySetIterator(AbstractInt2ShortSortedMap.this.entrySet().iterator(new AbstractInt2ShortMap.BasicEntry(from, (short)0))); } 
/* 104 */     public IntBidirectionalIterator iterator() { return new AbstractInt2ShortSortedMap.KeySetIterator(AbstractInt2ShortSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ShortSortedMap
 * JD-Core Version:    0.6.2
 */