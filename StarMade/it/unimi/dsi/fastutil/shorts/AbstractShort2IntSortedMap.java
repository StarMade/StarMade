/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
/*     */ import it.unimi.dsi.fastutil.ints.IntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractShort2IntSortedMap extends AbstractShort2IntMap
/*     */   implements Short2IntSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Short2IntSortedMap headMap(Short to)
/*     */   {
/*  59 */     return headMap(to.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2IntSortedMap tailMap(Short from) {
/*  63 */     return tailMap(from.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2IntSortedMap subMap(Short from, Short to) {
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
/*     */   public IntCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Short, Integer>> entrySet()
/*     */   {
/* 174 */     return short2IntEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractIntIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, Integer>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Integer>> i)
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
/* 148 */       return new AbstractShort2IntSortedMap.ValuesIterator(AbstractShort2IntSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(int k) { return AbstractShort2IntSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractShort2IntSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractShort2IntSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractShortBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, Integer>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Integer>> i)
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
/*  93 */       return AbstractShort2IntSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractShort2IntSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractShort2IntSortedMap.this.clear(); } 
/*  96 */     public ShortComparator comparator() { return AbstractShort2IntSortedMap.this.comparator(); } 
/*  97 */     public short firstShort() { return AbstractShort2IntSortedMap.this.firstShortKey(); } 
/*  98 */     public short lastShort() { return AbstractShort2IntSortedMap.this.lastShortKey(); } 
/*  99 */     public ShortSortedSet headSet(short to) { return AbstractShort2IntSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ShortSortedSet tailSet(short from) { return AbstractShort2IntSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ShortSortedSet subSet(short from, short to) { return AbstractShort2IntSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ShortBidirectionalIterator iterator(short from) {
/* 103 */       return new AbstractShort2IntSortedMap.KeySetIterator(AbstractShort2IntSortedMap.this.entrySet().iterator(new AbstractShort2IntMap.BasicEntry(from, 0))); } 
/* 104 */     public ShortBidirectionalIterator iterator() { return new AbstractShort2IntSortedMap.KeySetIterator(AbstractShort2IntSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2IntSortedMap
 * JD-Core Version:    0.6.2
 */