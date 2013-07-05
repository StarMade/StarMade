/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractInt2IntSortedMap extends AbstractInt2IntMap
/*     */   implements Int2IntSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Int2IntSortedMap headMap(Integer to)
/*     */   {
/*  59 */     return headMap(to.intValue());
/*     */   }
/*     */ 
/*     */   public Int2IntSortedMap tailMap(Integer from) {
/*  63 */     return tailMap(from.intValue());
/*     */   }
/*     */ 
/*     */   public Int2IntSortedMap subMap(Integer from, Integer to) {
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
/*     */   public IntCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Integer, Integer>> entrySet()
/*     */   {
/* 174 */     return int2IntEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractIntIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Integer, Integer>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Integer>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public int nextInt() {
/* 168 */       return ((Integer)((Map.Entry)this.i.next()).getValue()).intValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractIntCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public IntIterator iterator()
/*     */     {
/* 148 */       return new AbstractInt2IntSortedMap.ValuesIterator(AbstractInt2IntSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(int k) { return AbstractInt2IntSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractInt2IntSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractInt2IntSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractIntBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Integer, Integer>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Integer>> i)
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
/*  93 */       return AbstractInt2IntSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractInt2IntSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractInt2IntSortedMap.this.clear(); } 
/*  96 */     public IntComparator comparator() { return AbstractInt2IntSortedMap.this.comparator(); } 
/*  97 */     public int firstInt() { return AbstractInt2IntSortedMap.this.firstIntKey(); } 
/*  98 */     public int lastInt() { return AbstractInt2IntSortedMap.this.lastIntKey(); } 
/*  99 */     public IntSortedSet headSet(int to) { return AbstractInt2IntSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public IntSortedSet tailSet(int from) { return AbstractInt2IntSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public IntSortedSet subSet(int from, int to) { return AbstractInt2IntSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public IntBidirectionalIterator iterator(int from) {
/* 103 */       return new AbstractInt2IntSortedMap.KeySetIterator(AbstractInt2IntSortedMap.this.entrySet().iterator(new AbstractInt2IntMap.BasicEntry(from, 0))); } 
/* 104 */     public IntBidirectionalIterator iterator() { return new AbstractInt2IntSortedMap.KeySetIterator(AbstractInt2IntSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2IntSortedMap
 * JD-Core Version:    0.6.2
 */