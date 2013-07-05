/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractShort2BooleanSortedMap extends AbstractShort2BooleanMap
/*     */   implements Short2BooleanSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Short2BooleanSortedMap headMap(Short to)
/*     */   {
/*  59 */     return headMap(to.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2BooleanSortedMap tailMap(Short from) {
/*  63 */     return tailMap(from.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2BooleanSortedMap subMap(Short from, Short to) {
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
/*     */   public BooleanCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Short, Boolean>> entrySet()
/*     */   {
/* 174 */     return short2BooleanEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractBooleanIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public boolean nextBoolean() {
/* 168 */       return ((Boolean)((Map.Entry)this.i.next()).getValue()).booleanValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractBooleanCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public BooleanIterator iterator()
/*     */     {
/* 148 */       return new AbstractShort2BooleanSortedMap.ValuesIterator(AbstractShort2BooleanSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(boolean k) { return AbstractShort2BooleanSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractShort2BooleanSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractShort2BooleanSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractShortBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Boolean>> i)
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
/*  93 */       return AbstractShort2BooleanSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractShort2BooleanSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractShort2BooleanSortedMap.this.clear(); } 
/*  96 */     public ShortComparator comparator() { return AbstractShort2BooleanSortedMap.this.comparator(); } 
/*  97 */     public short firstShort() { return AbstractShort2BooleanSortedMap.this.firstShortKey(); } 
/*  98 */     public short lastShort() { return AbstractShort2BooleanSortedMap.this.lastShortKey(); } 
/*  99 */     public ShortSortedSet headSet(short to) { return AbstractShort2BooleanSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ShortSortedSet tailSet(short from) { return AbstractShort2BooleanSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ShortSortedSet subSet(short from, short to) { return AbstractShort2BooleanSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ShortBidirectionalIterator iterator(short from) {
/* 103 */       return new AbstractShort2BooleanSortedMap.KeySetIterator(AbstractShort2BooleanSortedMap.this.entrySet().iterator(new AbstractShort2BooleanMap.BasicEntry(from, false))); } 
/* 104 */     public ShortBidirectionalIterator iterator() { return new AbstractShort2BooleanSortedMap.KeySetIterator(AbstractShort2BooleanSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2BooleanSortedMap
 * JD-Core Version:    0.6.2
 */