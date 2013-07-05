/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractByte2ObjectSortedMap<V> extends AbstractByte2ObjectMap<V>
/*     */   implements Byte2ObjectSortedMap<V>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Byte2ObjectSortedMap<V> headMap(Byte to)
/*     */   {
/*  58 */     return headMap(to.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2ObjectSortedMap<V> tailMap(Byte from) {
/*  62 */     return tailMap(from.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte2ObjectSortedMap<V> subMap(Byte from, Byte to) {
/*  66 */     return subMap(from.byteValue(), to.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte firstKey() {
/*  70 */     return Byte.valueOf(firstByteKey());
/*     */   }
/*     */ 
/*     */   public Byte lastKey() {
/*  74 */     return Byte.valueOf(lastByteKey());
/*     */   }
/*     */ 
/*     */   public ByteSortedSet keySet()
/*     */   {
/*  88 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public ObjectCollection<V> values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Byte, V>> entrySet()
/*     */   {
/* 174 */     return byte2ObjectEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator<V> extends AbstractObjectIterator<V>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, V>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Byte, V>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public V next() {
/* 168 */       return ((Map.Entry)this.i.next()).getValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractObjectCollection<V>
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<V> iterator()
/*     */     {
/* 148 */       return new AbstractByte2ObjectSortedMap.ValuesIterator(AbstractByte2ObjectSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(Object k) { return AbstractByte2ObjectSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractByte2ObjectSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractByte2ObjectSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator<V> extends AbstractByteBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Byte, V>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Byte, V>> i)
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
/*  92 */       return AbstractByte2ObjectSortedMap.this.containsKey(k); } 
/*  93 */     public int size() { return AbstractByte2ObjectSortedMap.this.size(); } 
/*  94 */     public void clear() { AbstractByte2ObjectSortedMap.this.clear(); } 
/*  95 */     public ByteComparator comparator() { return AbstractByte2ObjectSortedMap.this.comparator(); } 
/*  96 */     public byte firstByte() { return AbstractByte2ObjectSortedMap.this.firstByteKey(); } 
/*  97 */     public byte lastByte() { return AbstractByte2ObjectSortedMap.this.lastByteKey(); } 
/*     */     public ByteSortedSet headSet(byte to) {
/*  99 */       return AbstractByte2ObjectSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public ByteSortedSet tailSet(byte from) { return AbstractByte2ObjectSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public ByteSortedSet subSet(byte from, byte to) { return AbstractByte2ObjectSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public ByteBidirectionalIterator iterator(byte from) {
/* 103 */       return new AbstractByte2ObjectSortedMap.KeySetIterator(AbstractByte2ObjectSortedMap.this.entrySet().iterator(new AbstractByte2ObjectMap.BasicEntry(from, null))); } 
/* 104 */     public ByteBidirectionalIterator iterator() { return new AbstractByte2ObjectSortedMap.KeySetIterator(AbstractByte2ObjectSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2ObjectSortedMap
 * JD-Core Version:    0.6.2
 */