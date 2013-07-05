/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class DoubleHeapPriorityQueue extends AbstractDoublePriorityQueue
/*     */ {
/*  53 */   protected double[] heap = DoubleArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected DoubleComparator c;
/*     */ 
/*     */   public DoubleHeapPriorityQueue(int capacity, DoubleComparator c)
/*     */   {
/*  66 */     if (capacity > 0) this.heap = new double[capacity];
/*  67 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public DoubleHeapPriorityQueue(int capacity)
/*     */   {
/*  74 */     this(capacity, null);
/*     */   }
/*     */ 
/*     */   public DoubleHeapPriorityQueue(DoubleComparator c)
/*     */   {
/*  81 */     this(0, c);
/*     */   }
/*     */ 
/*     */   public DoubleHeapPriorityQueue()
/*     */   {
/*  86 */     this(0, null);
/*     */   }
/*     */ 
/*     */   public DoubleHeapPriorityQueue(double[] a, int size, DoubleComparator c)
/*     */   {
/*  99 */     this(c);
/* 100 */     this.heap = a;
/* 101 */     this.size = size;
/* 102 */     DoubleHeaps.makeHeap(a, size, c);
/*     */   }
/*     */ 
/*     */   public DoubleHeapPriorityQueue(double[] a, DoubleComparator c)
/*     */   {
/* 114 */     this(a, a.length, c);
/*     */   }
/*     */ 
/*     */   public DoubleHeapPriorityQueue(double[] a, int size)
/*     */   {
/* 126 */     this(a, size, null);
/*     */   }
/*     */ 
/*     */   public DoubleHeapPriorityQueue(double[] a)
/*     */   {
/* 137 */     this(a, a.length);
/*     */   }
/*     */   public void enqueue(double x) {
/* 140 */     if (this.size == this.heap.length) this.heap = DoubleArrays.grow(this.heap, this.size + 1);
/*     */ 
/* 142 */     this.heap[(this.size++)] = x;
/* 143 */     DoubleHeaps.upHeap(this.heap, this.size, this.size - 1, this.c);
/*     */   }
/*     */ 
/*     */   public double dequeueDouble() {
/* 147 */     if (this.size == 0) throw new NoSuchElementException();
/*     */ 
/* 149 */     double result = this.heap[0];
/* 150 */     this.heap[0] = this.heap[(--this.size)];
/*     */ 
/* 154 */     if (this.size != 0) DoubleHeaps.downHeap(this.heap, this.size, 0, this.c);
/* 155 */     return result;
/*     */   }
/*     */ 
/*     */   public double firstDouble() {
/* 159 */     if (this.size == 0) throw new NoSuchElementException();
/* 160 */     return this.heap[0];
/*     */   }
/*     */ 
/*     */   public void changed() {
/* 164 */     DoubleHeaps.downHeap(this.heap, this.size, 0, this.c);
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
/* 180 */     this.heap = DoubleArrays.trim(this.heap, this.size);
/*     */   }
/*     */   public DoubleComparator comparator() {
/* 183 */     return this.c;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleHeapPriorityQueue
 * JD-Core Version:    0.6.2
 */