/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractFloat2BooleanSortedMap extends AbstractFloat2BooleanMap
/*     */   implements Float2BooleanSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Float2BooleanSortedMap headMap(Float to)
/*     */   {
/*  59 */     return headMap(to.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2BooleanSortedMap tailMap(Float from) {
/*  63 */     return tailMap(from.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2BooleanSortedMap subMap(Float from, Float to) {
/*  67 */     return subMap(from.floatValue(), to.floatValue());
/*     */   }
/*     */ 
/*     */   public Float firstKey() {
/*  71 */     return Float.valueOf(firstFloatKey());
/*     */   }
/*     */ 
/*     */   public Float lastKey() {
/*  75 */     return Float.valueOf(lastFloatKey());
/*     */   }
/*     */ 
/*     */   public FloatSortedSet keySet()
/*     */   {
/*  89 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public BooleanCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Float, Boolean>> entrySet()
/*     */   {
/* 174 */     return float2BooleanEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractBooleanIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> i)
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
/* 148 */       return new AbstractFloat2BooleanSortedMap.ValuesIterator(AbstractFloat2BooleanSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(boolean k) { return AbstractFloat2BooleanSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractFloat2BooleanSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractFloat2BooleanSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractFloatBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, Boolean>> i)
/*     */     {
/* 118 */       this.i = i;
/*     */     }
/*     */     public float nextFloat() {
/* 121 */       return ((Float)((Map.Entry)this.i.next()).getKey()).floatValue(); } 
/* 122 */     public float previousFloat() { return ((Float)((Map.Entry)this.i.previous()).getKey()).floatValue(); } 
/*     */     public boolean hasNext() {
/* 124 */       return this.i.hasNext(); } 
/* 125 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractFloatSortedSet
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(float k)
/*     */     {
/*  93 */       return AbstractFloat2BooleanSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractFloat2BooleanSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractFloat2BooleanSortedMap.this.clear(); } 
/*  96 */     public FloatComparator comparator() { return AbstractFloat2BooleanSortedMap.this.comparator(); } 
/*  97 */     public float firstFloat() { return AbstractFloat2BooleanSortedMap.this.firstFloatKey(); } 
/*  98 */     public float lastFloat() { return AbstractFloat2BooleanSortedMap.this.lastFloatKey(); } 
/*  99 */     public FloatSortedSet headSet(float to) { return AbstractFloat2BooleanSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public FloatSortedSet tailSet(float from) { return AbstractFloat2BooleanSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public FloatSortedSet subSet(float from, float to) { return AbstractFloat2BooleanSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public FloatBidirectionalIterator iterator(float from) {
/* 103 */       return new AbstractFloat2BooleanSortedMap.KeySetIterator(AbstractFloat2BooleanSortedMap.this.entrySet().iterator(new AbstractFloat2BooleanMap.BasicEntry(from, false))); } 
/* 104 */     public FloatBidirectionalIterator iterator() { return new AbstractFloat2BooleanSortedMap.KeySetIterator(AbstractFloat2BooleanSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2BooleanSortedMap
 * JD-Core Version:    0.6.2
 */