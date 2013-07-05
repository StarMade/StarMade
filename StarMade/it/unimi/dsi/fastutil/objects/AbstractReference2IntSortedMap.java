/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
/*     */ import it.unimi.dsi.fastutil.ints.IntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractReference2IntSortedMap<K> extends AbstractReference2IntMap<K>
/*     */   implements Reference2IntSortedMap<K>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public ReferenceSortedSet<K> keySet()
/*     */   {
/*  69 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public IntCollection values()
/*     */   {
/* 112 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<K, Integer>> entrySet()
/*     */   {
/* 136 */     return reference2IntEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator<K> extends AbstractIntIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<K, Integer>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Integer>> i)
/*     */     {
/* 129 */       this.i = i;
/*     */     }
/* 131 */     public int nextInt() { return ((Integer)((Map.Entry)this.i.next()).getValue()).intValue(); } 
/* 132 */     public boolean hasNext() { return this.i.hasNext(); }
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
/* 116 */       return new AbstractReference2IntSortedMap.ValuesIterator(AbstractReference2IntSortedMap.this.entrySet().iterator()); } 
/* 117 */     public boolean contains(int k) { return AbstractReference2IntSortedMap.this.containsValue(k); } 
/* 118 */     public int size() { return AbstractReference2IntSortedMap.this.size(); } 
/* 119 */     public void clear() { AbstractReference2IntSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator<K> extends AbstractObjectBidirectionalIterator<K>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<K, Integer>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Integer>> i)
/*     */     {
/*  93 */       this.i = i;
/*     */     }
/*  95 */     public K next() { return ((Map.Entry)this.i.next()).getKey(); } 
/*  96 */     public K previous() { return ((Map.Entry)this.i.previous()).getKey(); } 
/*  97 */     public boolean hasNext() { return this.i.hasNext(); } 
/*  98 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractReferenceSortedSet<K>
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(Object k)
/*     */     {
/*  73 */       return AbstractReference2IntSortedMap.this.containsKey(k); } 
/*  74 */     public int size() { return AbstractReference2IntSortedMap.this.size(); } 
/*  75 */     public void clear() { AbstractReference2IntSortedMap.this.clear(); } 
/*  76 */     public Comparator<? super K> comparator() { return AbstractReference2IntSortedMap.this.comparator(); } 
/*  77 */     public K first() { return AbstractReference2IntSortedMap.this.firstKey(); } 
/*  78 */     public K last() { return AbstractReference2IntSortedMap.this.lastKey(); } 
/*  79 */     public ReferenceSortedSet<K> headSet(K to) { return AbstractReference2IntSortedMap.this.headMap(to).keySet(); } 
/*  80 */     public ReferenceSortedSet<K> tailSet(K from) { return AbstractReference2IntSortedMap.this.tailMap(from).keySet(); } 
/*  81 */     public ReferenceSortedSet<K> subSet(K from, K to) { return AbstractReference2IntSortedMap.this.subMap(from, to).keySet(); } 
/*  82 */     public ObjectBidirectionalIterator<K> iterator(K from) { return new AbstractReference2IntSortedMap.KeySetIterator(AbstractReference2IntSortedMap.this.entrySet().iterator(new AbstractReference2IntMap.BasicEntry(from, 0))); } 
/*  83 */     public ObjectBidirectionalIterator<K> iterator() { return new AbstractReference2IntSortedMap.KeySetIterator(AbstractReference2IntSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2IntSortedMap
 * JD-Core Version:    0.6.2
 */