/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
/*     */ import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
/*     */ import it.unimi.dsi.fastutil.bytes.ByteCollection;
/*     */ import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractInt2ByteSortedMap extends AbstractInt2ByteMap
/*     */   implements Int2ByteSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Int2ByteSortedMap headMap(Integer to)
/*     */   {
/*  59 */     return headMap(to.intValue());
/*     */   }
/*     */ 
/*     */   public Int2ByteSortedMap tailMap(Integer from) {
/*  63 */     return tailMap(from.intValue());
/*     */   }
/*     */ 
/*     */   public Int2ByteSortedMap subMap(Integer from, Integer to) {
/*  67 */     return subMap(from.intValue(), to.intValue());
/*     */   }
/*     */ 
/*     */   public Integer firstKey() {
/*  71 */     return Integer.valueOf(firstIntKey());
/*     */   }
/*     */ 
/*     */   public Integer lastKey() {
/*  75 */     return Integer.valueOf(lastIntKey());
/*     */   }
/*     */ 
/*     */   public IntSortedSet keySet()
/*     */   {
/*  89 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public ByteCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Integer, Byte>> entrySet()
/*     */   {
/* 174 */     return int2ByteEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractByteIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Integer, Byte>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Byte>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public byte nextByte() {
/* 168 */       return ((Byte)((Map.Entry)this.i.next()).getValue()).byteValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractByteCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ByteIterator iterator()
/*     */     {
/* 148 */       return new AbstractInt2ByteSortedMap.ValuesIterator(AbstractInt2ByteSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(byte k) { return AbstractInt2ByteSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractInt2ByteSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractInt2ByteSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractIntBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Integer, Byte>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Integer, Byte>> i)
/*     */     {
/* 118 */       this.i = i;
/*     */     }
/*     */     public int nextInt() {
/* 121 */       return ((Integer)((Map.Entry)this.i.next()).getKey()).intValue(); } 
/* 122 */     public int previousInt() { return ((Integer)((Map.Entry)this.i.previous()).getKey()).intValue(); } 
/*     */     public boolean hasNext() {
/* 124 */       return this.i.hasNext(); } 
/* 125 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractIntSortedSet
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(int k)
/*     */     {
/*  93 */       return AbstractInt2ByteSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractInt2ByteSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractInt2ByteSortedMap.this.clear(); } 
/*  96 */     public IntComparator comparator() { return AbstractInt2ByteSortedMap.this.comparator(); } 
/*  97 */     public int firstInt() { return AbstractInt2ByteSortedMap.this.firstIntKey(); } 
/*  98 */     public int lastInt() { return AbstractInt2ByteSortedMap.this.lastIntKey(); } 
/*  99 */     public IntSortedSet headSet(int to) { return AbstractInt2ByteSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public IntSortedSet tailSet(int from) { return AbstractInt2ByteSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public IntSortedSet subSet(int from, int to) { return AbstractInt2ByteSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public IntBidirectionalIterator iterator(int from) {
/* 103 */       return new AbstractInt2ByteSortedMap.KeySetIterator(AbstractInt2ByteSortedMap.this.entrySet().iterator(new AbstractInt2ByteMap.BasicEntry(from, (byte)0))); } 
/* 104 */     public IntBidirectionalIterator iterator() { return new AbstractInt2ByteSortedMap.KeySetIterator(AbstractInt2ByteSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ByteSortedMap
 * JD-Core Version:    0.6.2
 */