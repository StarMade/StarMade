/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class FloatHeapPriorityQueue extends AbstractFloatPriorityQueue
/*     */ {
/*  53 */   protected float[] heap = FloatArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected FloatComparator c;
/*     */ 
/*     */   public FloatHeapPriorityQueue(int capacity, FloatComparator c)
/*     */   {
/*  66 */     if (capacity > 0) this.heap = new float[capacity];
/*  67 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public FloatHeapPriorityQueue(int capacity)
/*     */   {
/*  74 */     this(capacity, null);
/*     */   }
/*     */ 
/*     */   public FloatHeapPriorityQueue(FloatComparator c)
/*     */   {
/*  81 */     this(0, c);
/*     */   }
/*     */ 
/*     */   public FloatHeapPriorityQueue()
/*     */   {
/*  86 */     this(0, null);
/*     */   }
/*     */ 
/*     */   public FloatHeapPriorityQueue(float[] a, int size, FloatComparator c)
/*     */   {
/*  99 */     this(c);
/* 100 */     this.heap = a;
/* 101 */     this.size = size;
/* 102 */     FloatHeaps.makeHeap(a, size, c);
/*     */   }
/*     */ 
/*     */   public FloatHeapPriorityQueue(float[] a, FloatComparator c)
/*     */   {
/* 114 */     this(a, a.length, c);
/*     */   }
/*     */ 
/*     */   public FloatHeapPriorityQueue(float[] a, int size)
/*     */   {
/* 126 */     this(a, size, null);
/*     */   }
/*     */ 
/*     */   public FloatHeapPriorityQueue(float[] a)
/*     */   {
/* 137 */     this(a, a.length);
/*     */   }
/*     */   public void enqueue(float x) {
/* 140 */     if (this.size == this.heap.length) this.heap = FloatArrays.grow(this.heap, this.size + 1);
/*     */ 
/* 142 */     this.heap[(this.size++)] = x;
/* 143 */     FloatHeaps.upHeap(this.heap, this.size, this.size - 1, this.c);
/*     */   }
/*     */ 
/*     */   public float dequeueFloat() {
/* 147 */     if (this.size == 0) throw new NoSuchElementException();
/*     */ 
/* 149 */     float result = this.heap[0];
/* 150 */     this.heap[0] = this.heap[(--this.size)];
/*     */ 
/* 154 */     if (this.size != 0) FloatHeaps.downHeap(this.heap, this.size, 0, this.c);
/* 155 */     return result;
/*     */   }
/*     */ 
/*     */   public float firstFloat() {
/* 159 */     if (this.size == 0) throw new NoSuchElementException();
/* 160 */     return this.heap[0];
/*     */   }
/*     */ 
/*     */   public void changed() {
/* 164 */     FloatHeaps.downHeap(this.heap, this.size, 0, this.c);
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
/* 180 */     this.heap = FloatArrays.trim(this.heap, this.size);
/*     */   }
/*     */   public FloatComparator comparator() {
/* 183 */     return this.c;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatHeapPriorityQueue
 * JD-Core Version:    0.6.2
 */