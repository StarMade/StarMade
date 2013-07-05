/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ByteHeapPriorityQueue extends AbstractBytePriorityQueue
/*     */ {
/*  53 */   protected byte[] heap = ByteArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected ByteComparator c;
/*     */ 
/*     */   public ByteHeapPriorityQueue(int capacity, ByteComparator c)
/*     */   {
/*  66 */     if (capacity > 0) this.heap = new byte[capacity];
/*  67 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public ByteHeapPriorityQueue(int capacity)
/*     */   {
/*  74 */     this(capacity, null);
/*     */   }
/*     */ 
/*     */   public ByteHeapPriorityQueue(ByteComparator c)
/*     */   {
/*  81 */     this(0, c);
/*     */   }
/*     */ 
/*     */   public ByteHeapPriorityQueue()
/*     */   {
/*  86 */     this(0, null);
/*     */   }
/*     */ 
/*     */   public ByteHeapPriorityQueue(byte[] a, int size, ByteComparator c)
/*     */   {
/*  99 */     this(c);
/* 100 */     this.heap = a;
/* 101 */     this.size = size;
/* 102 */     ByteHeaps.makeHeap(a, size, c);
/*     */   }
/*     */ 
/*     */   public ByteHeapPriorityQueue(byte[] a, ByteComparator c)
/*     */   {
/* 114 */     this(a, a.length, c);
/*     */   }
/*     */ 
/*     */   public ByteHeapPriorityQueue(byte[] a, int size)
/*     */   {
/* 126 */     this(a, size, null);
/*     */   }
/*     */ 
/*     */   public ByteHeapPriorityQueue(byte[] a)
/*     */   {
/* 137 */     this(a, a.length);
/*     */   }
/*     */   public void enqueue(byte x) {
/* 140 */     if (this.size == this.heap.length) this.heap = ByteArrays.grow(this.heap, this.size + 1);
/*     */ 
/* 142 */     this.heap[(this.size++)] = x;
/* 143 */     ByteHeaps.upHeap(this.heap, this.size, this.size - 1, this.c);
/*     */   }
/*     */ 
/*     */   public byte dequeueByte() {
/* 147 */     if (this.size == 0) throw new NoSuchElementException();
/*     */ 
/* 149 */     byte result = this.heap[0];
/* 150 */     this.heap[0] = this.heap[(--this.size)];
/*     */ 
/* 154 */     if (this.size != 0) ByteHeaps.downHeap(this.heap, this.size, 0, this.c);
/* 155 */     return result;
/*     */   }
/*     */ 
/*     */   public byte firstByte() {
/* 159 */     if (this.size == 0) throw new NoSuchElementException();
/* 160 */     return this.heap[0];
/*     */   }
/*     */ 
/*     */   public void changed() {
/* 164 */     ByteHeaps.downHeap(this.heap, this.size, 0, this.c);
/*     */   }
/*     */   public int size() {
/* 167 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 173 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public void trim()
/*     */   {
/* 180 */     this.heap = ByteArrays.trim(this.heap, this.size);
/*     */   }
/*     */   public ByteComparator comparator() {
/* 183 */     return this.c;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteHeapPriorityQueue
 * JD-Core Version:    0.6.2
 */