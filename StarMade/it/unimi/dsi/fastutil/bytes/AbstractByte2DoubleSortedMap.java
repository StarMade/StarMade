/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*     */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractByte2DoubleSortedMap extends AbstractByte2DoubleMap
/*     */   implements Byte2DoubleSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Byte2DoubleSortedMap headMap(Byte to)
/*     */   {
/*  59 */     return headMap(to.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2DoubleSortedMap tailMap(Byte from) {
/*  63 */     return tailMap(from.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2DoubleSortedMap subMap(Byte from, Byte to) {
/*  67 */     return subMap(from.byteValue(), to.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte firstKey() {
/*  71 */     return Byte.valueOf(firstByteKey());
/*     */   }
/*     */ 
/*     */   public Byte lastKey() {
/*  75 */     return Byte.valueOf(lastByteKey());
/*     */   }
/*     */ 
/*     */   public ByteSortedSet keySet()
/*     */   {
/*  89 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public DoubleCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Byte, Double>> entrySet()
/*     */   {
/* 174 */     return byte2DoubleEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractDoubleIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, Double>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Double>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public double nextDouble() {
/* 168 */       return ((Double)((Map.Entry)this.i.next()).getValue()).doubleValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractDoubleCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public DoubleIterator iterator()
/*     */     {
/* 148 */       return new AbstractByte2DoubleSortedMap.ValuesIterator(AbstractByte2DoubleSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(double k) { return AbstractByte2DoubleSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractByte2DoubleSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractByte2DoubleSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractByteBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, Double>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Double>> i)
/*     */     {
/* 118 */       this.i = i;
/*     */     }
/*     */     public byte nextByte() {
/* 121 */       return ((Byte)((Map.Entry)this.i.next()).getKey()).byteValue(); } 
/* 122 */     public byte previousByte() { return ((Byte)((Map.Entry)this.i.previous()).getKey()).byteValue(); } 
/*     */     public boolean hasNext() {
/* 124 */       return this.i.hasNext(); } 
/* 125 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractByteSortedSet
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(byte k)
/*     */     {
/*  93 */       return AbstractByte2DoubleSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractByte2DoubleSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractByte2DoubleSortedMap.this.clear(); } 
/*  96 */     public ByteComparator comparator() { return AbstractByte2DoubleSortedMap.this.comparator(); } 
/*  97 */     public byte firstByte() { return AbstractByte2DoubleSortedMap.this.firstByteKey(); } 
/*  98 */     public byte lastByte() { return AbstractByte2DoubleSortedMap.this.lastByteKey(); } 
/*  99 */     public ByteSortedSet headSet(byte to) { return AbstractByte2DoubleSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ByteSortedSet tailSet(byte from) { return AbstractByte2DoubleSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ByteSortedSet subSet(byte from, byte to) { return AbstractByte2DoubleSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ByteBidirectionalIterator iterator(byte from) {
/* 103 */       return new AbstractByte2DoubleSortedMap.KeySetIterator(AbstractByte2DoubleSortedMap.this.entrySet().iterator(new AbstractByte2DoubleMap.BasicEntry(from, 0.0D))); } 
/* 104 */     public ByteBidirectionalIterator iterator() { return new AbstractByte2DoubleSortedMap.KeySetIterator(AbstractByte2DoubleSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2DoubleSortedMap
 * JD-Core Version:    0.6.2
 */