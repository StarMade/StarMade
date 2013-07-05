/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
/*     */ import it.unimi.dsi.fastutil.chars.CharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.CharIterator;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractReference2CharSortedMap<K> extends AbstractReference2CharMap<K>
/*     */   implements Reference2CharSortedMap<K>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public ReferenceSortedSet<K> keySet()
/*     */   {
/*  69 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public CharCollection values()
/*     */   {
/* 112 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<K, Character>> entrySet()
/*     */   {
/* 136 */     return reference2CharEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator<K> extends AbstractCharIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<K, Character>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Character>> i)
/*     */     {
/* 129 */       this.i = i;
/*     */     }
/* 131 */     public char nextChar() { return ((Character)((Map.Entry)this.i.next()).getValue()).charValue(); } 
/* 132 */     public boolean hasNext() { return this.i.hasNext(); }
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
/* 116 */       return new AbstractReference2CharSortedMap.ValuesIterator(AbstractReference2CharSortedMap.this.entrySet().iterator()); } 
/* 117 */     public boolean contains(char k) { return AbstractReference2CharSortedMap.this.containsValue(k); } 
/* 118 */     public int size() { return AbstractReference2CharSortedMap.this.size(); } 
/* 119 */     public void clear() { AbstractReference2CharSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator<K> extends AbstractObjectBidirectionalIterator<K>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<K, Character>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Character>> i)
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
/*  73 */       return AbstractReference2CharSortedMap.this.containsKey(k); } 
/*  74 */     public int size() { return AbstractReference2CharSortedMap.this.size(); } 
/*  75 */     public void clear() { AbstractReference2CharSortedMap.this.clear(); } 
/*  76 */     public Comparator<? super K> comparator() { return AbstractReference2CharSortedMap.this.comparator(); } 
/*  77 */     public K first() { return AbstractReference2CharSortedMap.this.firstKey(); } 
/*  78 */     public K last() { return AbstractReference2CharSortedMap.this.lastKey(); } 
/*  79 */     public ReferenceSortedSet<K> headSet(K to) { return AbstractReference2CharSortedMap.this.headMap(to).keySet(); } 
/*  80 */     public ReferenceSortedSet<K> tailSet(K from) { return AbstractReference2CharSortedMap.this.tailMap(from).keySet(); } 
/*  81 */     public ReferenceSortedSet<K> subSet(K from, K to) { return AbstractReference2CharSortedMap.this.subMap(from, to).keySet(); } 
/*  82 */     public ObjectBidirectionalIterator<K> iterator(K from) { return new AbstractReference2CharSortedMap.KeySetIterator(AbstractReference2CharSortedMap.this.entrySet().iterator(new AbstractReference2CharMap.BasicEntry(from, '\000'))); } 
/*  83 */     public ObjectBidirectionalIterator<K> iterator() { return new AbstractReference2CharSortedMap.KeySetIterator(AbstractReference2CharSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2CharSortedMap
 * JD-Core Version:    0.6.2
 */