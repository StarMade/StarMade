/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractShort2ShortSortedMap extends AbstractShort2ShortMap
/*     */   implements Short2ShortSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Short2ShortSortedMap headMap(Short to)
/*     */   {
/*  59 */     return headMap(to.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2ShortSortedMap tailMap(Short from) {
/*  63 */     return tailMap(from.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2ShortSortedMap subMap(Short from, Short to) {
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
/*     */   public ShortCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Short, Short>> entrySet()
/*     */   {
/* 174 */     return short2ShortEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractShortIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, Short>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Short>> i)
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
/* 148 */       return new AbstractShort2ShortSortedMap.ValuesIterator(AbstractShort2ShortSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(short k) { return AbstractShort2ShortSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractShort2ShortSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractShort2ShortSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractShortBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, Short>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Short>> i)
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
/*  93 */       return AbstractShort2ShortSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractShort2ShortSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractShort2ShortSortedMap.this.clear(); } 
/*  96 */     public ShortComparator comparator() { return AbstractShort2ShortSortedMap.this.comparator(); } 
/*  97 */     public short firstShort() { return AbstractShort2ShortSortedMap.this.firstShortKey(); } 
/*  98 */     public short lastShort() { return AbstractShort2ShortSortedMap.this.lastShortKey(); } 
/*  99 */     public ShortSortedSet headSet(short to) { return AbstractShort2ShortSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ShortSortedSet tailSet(short from) { return AbstractShort2ShortSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ShortSortedSet subSet(short from, short to) { return AbstractShort2ShortSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ShortBidirectionalIterator iterator(short from) {
/* 103 */       return new AbstractShort2ShortSortedMap.KeySetIterator(AbstractShort2ShortSortedMap.this.entrySet().iterator(new AbstractShort2ShortMap.BasicEntry(from, (short)0))); } 
/* 104 */     public ShortBidirectionalIterator iterator() { return new AbstractShort2ShortSortedMap.KeySetIterator(AbstractShort2ShortSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ShortSortedMap
 * JD-Core Version:    0.6.2
 */