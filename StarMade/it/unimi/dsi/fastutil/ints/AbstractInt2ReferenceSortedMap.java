/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractInt2ReferenceSortedMap<V> extends AbstractInt2ReferenceMap<V>
/*     */   implements Int2ReferenceSortedMap<V>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Int2ReferenceSortedMap<V> headMap(Integer to)
/*     */   {
/*  58 */     return headMap(to.intValue());
/*     */   }
/*     */ 
/*     */   public Int2ReferenceSortedMap<V> tailMap(Integer from) {
/*  62 */     return tailMap(from.intValue());
/*     */   }
/*     */ 
/*     */   public Int2ReferenceSortedMap<V> subMap(Integer from, Integer to) {
/*  66 */     return subMap(from.intValue(), to.intValue());
/*     */   }
/*     */ 
/*     */   public Integer firstKey() {
/*  70 */     return Integer.valueOf(firstIntKey());
/*     */   }
/*     */ 
/*     */   public Integer lastKey() {
/*  74 */     return Integer.valueOf(lastIntKey());
/*     */   }
/*     */ 
/*     */   public IntSortedSet keySet()
/*     */   {
/*  88 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public ReferenceCollection<V> values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Integer, V>> entrySet()
/*     */   {
/* 174 */     return int2ReferenceEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator<V> extends AbstractObjectIterator<V>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Integer, V>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, V>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public V next() {
/* 168 */       return ((Map.Entry)this.i.next()).getValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractReferenceCollection<V>
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<V> iterator()
/*     */     {
/* 148 */       return new AbstractInt2ReferenceSortedMap.ValuesIterator(AbstractInt2ReferenceSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(Object k) { return AbstractInt2ReferenceSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractInt2ReferenceSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractInt2ReferenceSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator<V> extends AbstractIntBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Integer, V>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, V>> i)
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
/*  92 */       return AbstractInt2ReferenceSortedMap.this.containsKey(k); } 
/*  93 */     public int size() { return AbstractInt2ReferenceSortedMap.this.size(); } 
/*  94 */     public void clear() { AbstractInt2ReferenceSortedMap.this.clear(); } 
/*  95 */     public IntComparator comparator() { return AbstractInt2ReferenceSortedMap.this.comparator(); } 
/*  96 */     public int firstInt() { return AbstractInt2ReferenceSortedMap.this.firstIntKey(); } 
/*  97 */     public int lastInt() { return AbstractInt2ReferenceSortedMap.this.lastIntKey(); } 
/*     */     public IntSortedSet headSet(int to) {
/*  99 */       return AbstractInt2ReferenceSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public IntSortedSet tailSet(int from) { return AbstractInt2ReferenceSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public IntSortedSet subSet(int from, int to) { return AbstractInt2ReferenceSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public IntBidirectionalIterator iterator(int from) {
/* 103 */       return new AbstractInt2ReferenceSortedMap.KeySetIterator(AbstractInt2ReferenceSortedMap.this.entrySet().iterator(new AbstractInt2ReferenceMap.BasicEntry(from, null))); } 
/* 104 */     public IntBidirectionalIterator iterator() { return new AbstractInt2ReferenceSortedMap.KeySetIterator(AbstractInt2ReferenceSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ReferenceSortedMap
 * JD-Core Version:    0.6.2
 */