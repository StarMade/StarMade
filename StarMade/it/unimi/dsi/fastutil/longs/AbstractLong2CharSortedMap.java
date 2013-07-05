/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
/*     */ import it.unimi.dsi.fastutil.chars.CharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.CharIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractLong2CharSortedMap extends AbstractLong2CharMap
/*     */   implements Long2CharSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Long2CharSortedMap headMap(Long to)
/*     */   {
/*  59 */     return headMap(to.longValue());
/*     */   }
/*     */ 
/*     */   public Long2CharSortedMap tailMap(Long from) {
/*  63 */     return tailMap(from.longValue());
/*     */   }
/*     */ 
/*     */   public Long2CharSortedMap subMap(Long from, Long to) {
/*  67 */     return subMap(from.longValue(), to.longValue());
/*     */   }
/*     */ 
/*     */   public Long firstKey() {
/*  71 */     return Long.valueOf(firstLongKey());
/*     */   }
/*     */ 
/*     */   public Long lastKey() {
/*  75 */     return Long.valueOf(lastLongKey());
/*     */   }
/*     */ 
/*     */   public LongSortedSet keySet()
/*     */   {
/*  89 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public CharCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Long, Character>> entrySet()
/*     */   {
/* 174 */     return long2CharEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractCharIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Character>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Long, Character>> i)
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
/* 148 */       return new AbstractLong2CharSortedMap.ValuesIterator(AbstractLong2CharSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(char k) { return AbstractLong2CharSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractLong2CharSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractLong2CharSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractLongBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Long, Character>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Long, Character>> i)
/*     */     {
/* 118 */       this.i = i;
/*     */     }
/*     */     public long nextLong() {
/* 121 */       return ((Long)((Map.Entry)this.i.next()).getKey()).longValue(); } 
/* 122 */     public long previousLong() { return ((Long)((Map.Entry)this.i.previous()).getKey()).longValue(); } 
/*     */     public boolean hasNext() {
/* 124 */       return this.i.hasNext(); } 
/* 125 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractLongSortedSet
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(long k)
/*     */     {
/*  93 */       return AbstractLong2CharSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractLong2CharSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractLong2CharSortedMap.this.clear(); } 
/*  96 */     public LongComparator comparator() { return AbstractLong2CharSortedMap.this.comparator(); } 
/*  97 */     public long firstLong() { return AbstractLong2CharSortedMap.this.firstLongKey(); } 
/*  98 */     public long lastLong() { return AbstractLong2CharSortedMap.this.lastLongKey(); } 
/*  99 */     public LongSortedSet headSet(long to) { return AbstractLong2CharSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public LongSortedSet tailSet(long from) { return AbstractLong2CharSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public LongSortedSet subSet(long from, long to) { return AbstractLong2CharSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public LongBidirectionalIterator iterator(long from) {
/* 103 */       return new AbstractLong2CharSortedMap.KeySetIterator(AbstractLong2CharSortedMap.this.entrySet().iterator(new AbstractLong2CharMap.BasicEntry(from, '\000'))); } 
/* 104 */     public LongBidirectionalIterator iterator() { return new AbstractLong2CharSortedMap.KeySetIterator(AbstractLong2CharSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2CharSortedMap
 * JD-Core Version:    0.6.2
 */