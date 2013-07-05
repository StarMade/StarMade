/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractFloat2ReferenceSortedMap<V> extends AbstractFloat2ReferenceMap<V>
/*     */   implements Float2ReferenceSortedMap<V>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Float2ReferenceSortedMap<V> headMap(Float to)
/*     */   {
/*  58 */     return headMap(to.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2ReferenceSortedMap<V> tailMap(Float from) {
/*  62 */     return tailMap(from.floatValue());
/*     */   }
/*     */ 
/*     */   public Float2ReferenceSortedMap<V> subMap(Float from, Float to) {
/*  66 */     return subMap(from.floatValue(), to.floatValue());
/*     */   }
/*     */ 
/*     */   public Float firstKey() {
/*  70 */     return Float.valueOf(firstFloatKey());
/*     */   }
/*     */ 
/*     */   public Float lastKey() {
/*  74 */     return Float.valueOf(lastFloatKey());
/*     */   }
/*     */ 
/*     */   public FloatSortedSet keySet()
/*     */   {
/*  88 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public ReferenceCollection<V> values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Float, V>> entrySet()
/*     */   {
/* 174 */     return float2ReferenceEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator<V> extends AbstractObjectIterator<V>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, V>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Float, V>> i)
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
/* 148 */       return new AbstractFloat2ReferenceSortedMap.ValuesIterator(AbstractFloat2ReferenceSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(Object k) { return AbstractFloat2ReferenceSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractFloat2ReferenceSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractFloat2ReferenceSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator<V> extends AbstractFloatBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Float, V>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Float, V>> i)
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
/*  92 */       return AbstractFloat2ReferenceSortedMap.this.containsKey(k); } 
/*  93 */     public int size() { return AbstractFloat2ReferenceSortedMap.this.size(); } 
/*  94 */     public void clear() { AbstractFloat2ReferenceSortedMap.this.clear(); } 
/*  95 */     public FloatComparator comparator() { return AbstractFloat2ReferenceSortedMap.this.comparator(); } 
/*  96 */     public float firstFloat() { return AbstractFloat2ReferenceSortedMap.this.firstFloatKey(); } 
/*  97 */     public float lastFloat() { return AbstractFloat2ReferenceSortedMap.this.lastFloatKey(); } 
/*     */     public FloatSortedSet headSet(float to) {
/*  99 */       return AbstractFloat2ReferenceSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public FloatSortedSet tailSet(float from) { return AbstractFloat2ReferenceSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public FloatSortedSet subSet(float from, float to) { return AbstractFloat2ReferenceSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public FloatBidirectionalIterator iterator(float from) {
/* 103 */       return new AbstractFloat2ReferenceSortedMap.KeySetIterator(AbstractFloat2ReferenceSortedMap.this.entrySet().iterator(new AbstractFloat2ReferenceMap.BasicEntry(from, null))); } 
/* 104 */     public FloatBidirectionalIterator iterator() { return new AbstractFloat2ReferenceSortedMap.KeySetIterator(AbstractFloat2ReferenceSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2ReferenceSortedMap
 * JD-Core Version:    0.6.2
 */