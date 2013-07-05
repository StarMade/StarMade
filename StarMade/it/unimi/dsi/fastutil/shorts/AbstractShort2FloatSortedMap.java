/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*     */ import it.unimi.dsi.fastutil.floats.FloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.FloatIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractShort2FloatSortedMap extends AbstractShort2FloatMap
/*     */   implements Short2FloatSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Short2FloatSortedMap headMap(Short to)
/*     */   {
/*  59 */     return headMap(to.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2FloatSortedMap tailMap(Short from) {
/*  63 */     return tailMap(from.shortValue());
/*     */   }
/*     */ 
/*     */   public Short2FloatSortedMap subMap(Short from, Short to) {
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
/*     */   public FloatCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Short, Float>> entrySet()
/*     */   {
/* 174 */     return short2FloatEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractFloatIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, Float>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Short, Float>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public float nextFloat() {
/* 168 */       return ((Float)((Map.Entry)this.i.next()).getValue()).floatValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractFloatCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public FloatIterator iterator()
/*     */     {
/* 148 */       return new AbstractShort2FloatSortedMap.ValuesIterator(AbstractShort2FloatSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(float k) { return AbstractShort2FloatSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractShort2FloatSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractShort2FloatSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractShortBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Short, Float>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Short, Float>> i)
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
/*  93 */       return AbstractShort2FloatSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractShort2FloatSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractShort2FloatSortedMap.this.clear(); } 
/*  96 */     public ShortComparator comparator() { return AbstractShort2FloatSortedMap.this.comparator(); } 
/*  97 */     public short firstShort() { return AbstractShort2FloatSortedMap.this.firstShortKey(); } 
/*  98 */     public short lastShort() { return AbstractShort2FloatSortedMap.this.lastShortKey(); } 
/*  99 */     public ShortSortedSet headSet(short to) { return AbstractShort2FloatSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ShortSortedSet tailSet(short from) { return AbstractShort2FloatSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ShortSortedSet subSet(short from, short to) { return AbstractShort2FloatSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ShortBidirectionalIterator iterator(short from) {
/* 103 */       return new AbstractShort2FloatSortedMap.KeySetIterator(AbstractShort2FloatSortedMap.this.entrySet().iterator(new AbstractShort2FloatMap.BasicEntry(from, 0.0F))); } 
/* 104 */     public ShortBidirectionalIterator iterator() { return new AbstractShort2FloatSortedMap.KeySetIterator(AbstractShort2FloatSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2FloatSortedMap
 * JD-Core Version:    0.6.2
 */