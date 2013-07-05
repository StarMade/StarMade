/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractByte2ShortSortedMap extends AbstractByte2ShortMap
/*     */   implements Byte2ShortSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Byte2ShortSortedMap headMap(Byte to)
/*     */   {
/*  59 */     return headMap(to.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2ShortSortedMap tailMap(Byte from) {
/*  63 */     return tailMap(from.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2ShortSortedMap subMap(Byte from, Byte to) {
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
/*     */   public ShortCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Byte, Short>> entrySet()
/*     */   {
/* 174 */     return byte2ShortEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractShortIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, Short>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Short>> i)
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
/* 148 */       return new AbstractByte2ShortSortedMap.ValuesIterator(AbstractByte2ShortSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(short k) { return AbstractByte2ShortSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractByte2ShortSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractByte2ShortSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractByteBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, Short>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Short>> i)
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
/*  93 */       return AbstractByte2ShortSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractByte2ShortSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractByte2ShortSortedMap.this.clear(); } 
/*  96 */     public ByteComparator comparator() { return AbstractByte2ShortSortedMap.this.comparator(); } 
/*  97 */     public byte firstByte() { return AbstractByte2ShortSortedMap.this.firstByteKey(); } 
/*  98 */     public byte lastByte() { return AbstractByte2ShortSortedMap.this.lastByteKey(); } 
/*  99 */     public ByteSortedSet headSet(byte to) { return AbstractByte2ShortSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ByteSortedSet tailSet(byte from) { return AbstractByte2ShortSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ByteSortedSet subSet(byte from, byte to) { return AbstractByte2ShortSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ByteBidirectionalIterator iterator(byte from) {
/* 103 */       return new AbstractByte2ShortSortedMap.KeySetIterator(AbstractByte2ShortSortedMap.this.entrySet().iterator(new AbstractByte2ShortMap.BasicEntry(from, (short)0))); } 
/* 104 */     public ByteBidirectionalIterator iterator() { return new AbstractByte2ShortSortedMap.KeySetIterator(AbstractByte2ShortSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2ShortSortedMap
 * JD-Core Version:    0.6.2
 */