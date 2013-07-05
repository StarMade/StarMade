/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ShortHeapPriorityQueue extends AbstractShortPriorityQueue
/*     */ {
/*  53 */   protected short[] heap = ShortArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected ShortComparator c;
/*     */ 
/*     */   public ShortHeapPriorityQueue(int capacity, ShortComparator c)
/*     */   {
/*  66 */     if (capacity > 0) this.heap = new short[capacity];
/*  67 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public ShortHeapPriorityQueue(int capacity)
/*     */   {
/*  74 */     this(capacity, null);
/*     */   }
/*     */ 
/*     */   public ShortHeapPriorityQueue(ShortComparator c)
/*     */   {
/*  81 */     this(0, c);
/*     */   }
/*     */ 
/*     */   public ShortHeapPriorityQueue()
/*     */   {
/*  86 */     this(0, null);
/*     */   }
/*     */ 
/*     */   public ShortHeapPriorityQueue(short[] a, int size, ShortComparator c)
/*     */   {
/*  99 */     this(c);
/* 100 */     this.heap = a;
/* 101 */     this.size = size;
/* 102 */     ShortHeaps.makeHeap(a, size, c);
/*     */   }
/*     */ 
/*     */   public ShortHeapPriorityQueue(short[] a, ShortComparator c)
/*     */   {
/* 114 */     this(a, a.length, c);
/*     */   }
/*     */ 
/*     */   public ShortHeapPriorityQueue(short[] a, int size)
/*     */   {
/* 126 */     this(a, size, null);
/*     */   }
/*     */ 
/*     */   public ShortHeapPriorityQueue(short[] a)
/*     */   {
/* 137 */     this(a, a.length);
/*     */   }
/*     */   public void enqueue(short x) {
/* 140 */     if (this.size == this.heap.length) this.heap = ShortArrays.grow(this.heap, this.size + 1);
/*     */ 
/* 142 */     this.heap[(this.size++)] = x;
/* 143 */     ShortHeaps.upHeap(this.heap, this.size, this.size - 1, this.c);
/*     */   }
/*     */ 
/*     */   public short dequeueShort() {
/* 147 */     if (this.size == 0) throw new NoSuchElementException();
/*     */ 
/* 149 */     short result = this.heap[0];
/* 150 */     this.heap[0] = this.heap[(--this.size)];
/*     */ 
/* 154 */     if (this.size != 0) ShortHeaps.downHeap(this.heap, this.size, 0, this.c);
/* 155 */     return result;
/*     */   }
/*     */ 
/*     */   public short firstShort() {
/* 159 */     if (this.size == 0) throw new NoSuchElementException();
/* 160 */     return this.heap[0];
/*     */   }
/*     */ 
/*     */   public void changed() {
/* 164 */     ShortHeaps.downHeap(this.heap, this.size, 0, this.c);
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
/* 180 */     this.heap = ShortArrays.trim(this.heap, this.size);
/*     */   }
/*     */   public ShortComparator comparator() {
/* 183 */     return this.c;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortHeapPriorityQueue
 * JD-Core Version:    0.6.2
 */