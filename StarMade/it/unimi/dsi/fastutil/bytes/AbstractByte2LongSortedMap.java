/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*     */ import it.unimi.dsi.fastutil.longs.LongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractByte2LongSortedMap extends AbstractByte2LongMap
/*     */   implements Byte2LongSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Byte2LongSortedMap headMap(Byte to)
/*     */   {
/*  59 */     return headMap(to.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2LongSortedMap tailMap(Byte from) {
/*  63 */     return tailMap(from.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2LongSortedMap subMap(Byte from, Byte to) {
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
/*     */   public LongCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Byte, Long>> entrySet()
/*     */   {
/* 174 */     return byte2LongEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractLongIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, Long>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Long>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public long nextLong() {
/* 168 */       return ((Long)((Map.Entry)this.i.next()).getValue()).longValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractLongCollection
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public LongIterator iterator()
/*     */     {
/* 148 */       return new AbstractByte2LongSortedMap.ValuesIterator(AbstractByte2LongSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(long k) { return AbstractByte2LongSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractByte2LongSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractByte2LongSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractByteBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, Long>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Long>> i)
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
/*  93 */       return AbstractByte2LongSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractByte2LongSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractByte2LongSortedMap.this.clear(); } 
/*  96 */     public ByteComparator comparator() { return AbstractByte2LongSortedMap.this.comparator(); } 
/*  97 */     public byte firstByte() { return AbstractByte2LongSortedMap.this.firstByteKey(); } 
/*  98 */     public byte lastByte() { return AbstractByte2LongSortedMap.this.lastByteKey(); } 
/*  99 */     public ByteSortedSet headSet(byte to) { return AbstractByte2LongSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ByteSortedSet tailSet(byte from) { return AbstractByte2LongSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ByteSortedSet subSet(byte from, byte to) { return AbstractByte2LongSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ByteBidirectionalIterator iterator(byte from) {
/* 103 */       return new AbstractByte2LongSortedMap.KeySetIterator(AbstractByte2LongSortedMap.this.entrySet().iterator(new AbstractByte2LongMap.BasicEntry(from, 0L))); } 
/* 104 */     public ByteBidirectionalIterator iterator() { return new AbstractByte2LongSortedMap.KeySetIterator(AbstractByte2LongSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2LongSortedMap
 * JD-Core Version:    0.6.2
 */