/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractByte2BooleanSortedMap extends AbstractByte2BooleanMap
/*     */   implements Byte2BooleanSortedMap
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Byte2BooleanSortedMap headMap(Byte to)
/*     */   {
/*  59 */     return headMap(to.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2BooleanSortedMap tailMap(Byte from) {
/*  63 */     return tailMap(from.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2BooleanSortedMap subMap(Byte from, Byte to) {
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
/*     */   public BooleanCollection values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Byte, Boolean>> entrySet()
/*     */   {
/* 174 */     return byte2BooleanEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator extends AbstractBooleanIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, Boolean>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Boolean>> i)
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
/* 148 */       return new AbstractByte2BooleanSortedMap.ValuesIterator(AbstractByte2BooleanSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(boolean k) { return AbstractByte2BooleanSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractByte2BooleanSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractByte2BooleanSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator extends AbstractByteBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, Boolean>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, Boolean>> i)
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
/*  93 */       return AbstractByte2BooleanSortedMap.this.containsKey(k); } 
/*  94 */     public int size() { return AbstractByte2BooleanSortedMap.this.size(); } 
/*  95 */     public void clear() { AbstractByte2BooleanSortedMap.this.clear(); } 
/*  96 */     public ByteComparator comparator() { return AbstractByte2BooleanSortedMap.this.comparator(); } 
/*  97 */     public byte firstByte() { return AbstractByte2BooleanSortedMap.this.firstByteKey(); } 
/*  98 */     public byte lastByte() { return AbstractByte2BooleanSortedMap.this.lastByteKey(); } 
/*  99 */     public ByteSortedSet headSet(byte to) { return AbstractByte2BooleanSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ByteSortedSet tailSet(byte from) { return AbstractByte2BooleanSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ByteSortedSet subSet(byte from, byte to) { return AbstractByte2BooleanSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ByteBidirectionalIterator iterator(byte from) {
/* 103 */       return new AbstractByte2BooleanSortedMap.KeySetIterator(AbstractByte2BooleanSortedMap.this.entrySet().iterator(new AbstractByte2BooleanMap.BasicEntry(from, false))); } 
/* 104 */     public ByteBidirectionalIterator iterator() { return new AbstractByte2BooleanSortedMap.KeySetIterator(AbstractByte2BooleanSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2BooleanSortedMap
 * JD-Core Version:    0.6.2
 */