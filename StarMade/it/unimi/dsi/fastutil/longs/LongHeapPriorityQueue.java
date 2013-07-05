/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class LongHeapPriorityQueue extends AbstractLongPriorityQueue
/*     */ {
/*  53 */   protected long[] heap = LongArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected LongComparator c;
/*     */ 
/*     */   public LongHeapPriorityQueue(int capacity, LongComparator c)
/*     */   {
/*  66 */     if (capacity > 0) this.heap = new long[capacity];
/*  67 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public LongHeapPriorityQueue(int capacity)
/*     */   {
/*  74 */     this(capacity, null);
/*     */   }
/*     */ 
/*     */   public LongHeapPriorityQueue(LongComparator c)
/*     */   {
/*  81 */     this(0, c);
/*     */   }
/*     */ 
/*     */   public LongHeapPriorityQueue()
/*     */   {
/*  86 */     this(0, null);
/*     */   }
/*     */ 
/*     */   public LongHeapPriorityQueue(long[] a, int size, LongComparator c)
/*     */   {
/*  99 */     this(c);
/* 100 */     this.heap = a;
/* 101 */     this.size = size;
/* 102 */     LongHeaps.makeHeap(a, size, c);
/*     */   }
/*     */ 
/*     */   public LongHeapPriorityQueue(long[] a, LongComparator c)
/*     */   {
/* 114 */     this(a, a.length, c);
/*     */   }
/*     */ 
/*     */   public LongHeapPriorityQueue(long[] a, int size)
/*     */   {
/* 126 */     this(a, size, null);
/*     */   }
/*     */ 
/*     */   public LongHeapPriorityQueue(long[] a)
/*     */   {
/* 137 */     this(a, a.length);
/*     */   }
/*     */   public void enqueue(long x) {
/* 140 */     if (this.size == this.heap.length) this.heap = LongArrays.grow(this.heap, this.size + 1);
/*     */ 
/* 142 */     this.heap[(this.size++)] = x;
/* 143 */     LongHeaps.upHeap(this.heap, this.size, this.size - 1, this.c);
/*     */   }
/*     */ 
/*     */   public long dequeueLong() {
/* 147 */     if (this.size == 0) throw new NoSuchElementException();
/*     */ 
/* 149 */     long result = this.heap[0];
/* 150 */     this.heap[0] = this.heap[(--this.size)];
/*     */ 
/* 154 */     if (this.size != 0) LongHeaps.downHeap(this.heap, this.size, 0, this.c);
/* 155 */     return result;
/*     */   }
/*     */ 
/*     */   public long firstLong() {
/* 159 */     if (this.size == 0) throw new NoSuchElementException();
/* 160 */     return this.heap[0];
/*     */   }
/*     */ 
/*     */   public void changed() {
/* 164 */     LongHeaps.downHeap(this.heap, this.size, 0, this.c);
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
/* 180 */     this.heap = LongArrays.trim(this.heap, this.size);
/*     */   }
/*     */   public LongComparator comparator() {
/* 183 */     return this.c;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongHeapPriorityQueue
 * JD-Core Version:    0.6.2
 */