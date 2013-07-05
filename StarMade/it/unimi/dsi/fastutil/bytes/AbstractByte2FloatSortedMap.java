/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*     */ import it.unimi.dsi.fastutil.floats.FloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.FloatIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractByte2FloatSortedMap extends AbstractByte2FloatMap
/*     */   implements Byte2FloatSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Byte2FloatSortedMap headMap(Byte to)
/*     */   {
/*  59 */     return headMap(to.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2FloatSortedMap tailMap(Byte from) {
/*  63 */     return tailMap(from.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2FloatSortedMap subMap(Byte from, Byte to) {
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
/*     */   public FloatCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Byte, Float>> entrySet()
/*     */   {
/* 174 */     return byte2FloatEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractFloatIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, Float>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Float>> i)
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
/* 148 */       return new AbstractByte2FloatSortedMap.ValuesIterator(AbstractByte2FloatSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(float k) { return AbstractByte2FloatSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractByte2FloatSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractByte2FloatSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractByteBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, Float>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Float>> i)
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
/*  93 */       return AbstractByte2FloatSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractByte2FloatSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractByte2FloatSortedMap.this.clear(); } 
/*  96 */     public ByteComparator comparator() { return AbstractByte2FloatSortedMap.this.comparator(); } 
/*  97 */     public byte firstByte() { return AbstractByte2FloatSortedMap.this.firstByteKey(); } 
/*  98 */     public byte lastByte() { return AbstractByte2FloatSortedMap.this.lastByteKey(); } 
/*  99 */     public ByteSortedSet headSet(byte to) { return AbstractByte2FloatSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ByteSortedSet tailSet(byte from) { return AbstractByte2FloatSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ByteSortedSet subSet(byte from, byte to) { return AbstractByte2FloatSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ByteBidirectionalIterator iterator(byte from) {
/* 103 */       return new AbstractByte2FloatSortedMap.KeySetIterator(AbstractByte2FloatSortedMap.this.entrySet().iterator(new AbstractByte2FloatMap.BasicEntry(from, 0.0F))); } 
/* 104 */     public ByteBidirectionalIterator iterator() { return new AbstractByte2FloatSortedMap.KeySetIterator(AbstractByte2FloatSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2FloatSortedMap
 * JD-Core Version:    0.6.2
 */