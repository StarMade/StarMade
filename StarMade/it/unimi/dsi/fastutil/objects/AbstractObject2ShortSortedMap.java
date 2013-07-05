/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractObject2ShortSortedMap<K> extends AbstractObject2ShortMap<K>
/*     */   implements Object2ShortSortedMap<K>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public ObjectSortedSet<K> keySet()
/*     */   {
/*  69 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public ShortCollection values()
/*     */   {
/* 112 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<K, Short>> entrySet()
/*     */   {
/* 136 */     return object2ShortEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator<K> extends AbstractShortIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<K, Short>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<K, Short>> i)
/*     */     {
/* 129 */       this.i = i;
/*     */     }
/* 131 */     public short nextShort() { return ((Short)((Map.Entry)this.i.next()).getValue()).shortValue(); } 
/* 132 */     public boolean hasNext() { return this.i.hasNext(); }
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
/* 116 */       return new AbstractObject2ShortSortedMap.ValuesIterator(AbstractObject2ShortSortedMap.this.entrySet().iterator()); } 
/* 117 */     public boolean contains(short k) { return AbstractObject2ShortSortedMap.this.containsValue(k); } 
/* 118 */     public int size() { return AbstractObject2ShortSortedMap.this.size(); } 
/* 119 */     public void clear() { AbstractObject2ShortSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator<K> extends AbstractObjectBidirectionalIterator<K>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<K, Short>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<K, Short>> i)
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
/*     */   protected class KeySet extends AbstractObjectSortedSet<K>
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(Object k)
/*     */     {
/*  73 */       return AbstractObject2ShortSortedMap.this.containsKey(k); } 
/*  74 */     public int size() { return AbstractObject2ShortSortedMap.this.size(); } 
/*  75 */     public void clear() { AbstractObject2ShortSortedMap.this.clear(); } 
/*  76 */     public Comparator<? super K> comparator() { return AbstractObject2ShortSortedMap.this.comparator(); } 
/*  77 */     public K first() { return AbstractObject2ShortSortedMap.this.firstKey(); } 
/*  78 */     public K last() { return AbstractObject2ShortSortedMap.this.lastKey(); } 
/*  79 */     public ObjectSortedSet<K> headSet(K to) { return AbstractObject2ShortSortedMap.this.headMap(to).keySet(); } 
/*  80 */     public ObjectSortedSet<K> tailSet(K from) { return AbstractObject2ShortSortedMap.this.tailMap(from).keySet(); } 
/*  81 */     public ObjectSortedSet<K> subSet(K from, K to) { return AbstractObject2ShortSortedMap.this.subMap(from, to).keySet(); } 
/*  82 */     public ObjectBidirectionalIterator<K> iterator(K from) { return new AbstractObject2ShortSortedMap.KeySetIterator(AbstractObject2ShortSortedMap.this.entrySet().iterator(new AbstractObject2ShortMap.BasicEntry(from, (short)0))); } 
/*  83 */     public ObjectBidirectionalIterator<K> iterator() { return new AbstractObject2ShortSortedMap.KeySetIterator(AbstractObject2ShortSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2ShortSortedMap
 * JD-Core Version:    0.6.2
 */