/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractShort2ReferenceSortedMap<V> extends AbstractShort2ReferenceMap<V>
/*     */   implements Short2ReferenceSortedMap<V>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Short2ReferenceSortedMap<V> headMap(Short to)
/*     */   {
/*  58 */     return headMap(to.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2ReferenceSortedMap<V> tailMap(Short from) {
/*  62 */     return tailMap(from.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2ReferenceSortedMap<V> subMap(Short from, Short to) {
/*  66 */     return subMap(from.shortValue(), to.shortValue());
/*     */   }
/*     */ 
/*     */   public Short firstKey() {
/*  70 */     return Short.valueOf(firstShortKey());
/*     */   }
/*     */ 
/*     */   public Short lastKey() {
/*  74 */     return Short.valueOf(lastShortKey());
/*     */   }
/*     */ 
/*     */   public ShortSortedSet keySet()
/*     */   {
/*  88 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public ReferenceCollection<V> values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Short, V>> entrySet()
/*     */   {
/* 174 */     return short2ReferenceEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator<V> extends AbstractObjectIterator<V>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, V>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, V>> i)
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
/* 148 */       return new AbstractShort2ReferenceSortedMap.ValuesIterator(AbstractShort2ReferenceSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(Object k) { return AbstractShort2ReferenceSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractShort2ReferenceSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractShort2ReferenceSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator<V> extends AbstractShortBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, V>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, V>> i)
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
/*  92 */       return AbstractShort2ReferenceSortedMap.this.containsKey(k); } 
/*  93 */     public int size() { return AbstractShort2ReferenceSortedMap.this.size(); } 
/*  94 */     public void clear() { AbstractShort2ReferenceSortedMap.this.clear(); } 
/*  95 */     public ShortComparator comparator() { return AbstractShort2ReferenceSortedMap.this.comparator(); } 
/*  96 */     public short firstShort() { return AbstractShort2ReferenceSortedMap.this.firstShortKey(); } 
/*  97 */     public short lastShort() { return AbstractShort2ReferenceSortedMap.this.lastShortKey(); } 
/*     */     public ShortSortedSet headSet(short to) {
/*  99 */       return AbstractShort2ReferenceSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ShortSortedSet tailSet(short from) { return AbstractShort2ReferenceSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ShortSortedSet subSet(short from, short to) { return AbstractShort2ReferenceSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ShortBidirectionalIterator iterator(short from) {
/* 103 */       return new AbstractShort2ReferenceSortedMap.KeySetIterator(AbstractShort2ReferenceSortedMap.this.entrySet().iterator(new AbstractShort2ReferenceMap.BasicEntry(from, null))); } 
/* 104 */     public ShortBidirectionalIterator iterator() { return new AbstractShort2ReferenceSortedMap.KeySetIterator(AbstractShort2ReferenceSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ReferenceSortedMap
 * JD-Core Version:    0.6.2
 */