/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*     */ import java.util.Comparator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ObjectHeapPriorityQueue<K> extends AbstractPriorityQueue<K>
/*     */ {
/*  54 */   protected K[] heap = (Object[])ObjectArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected Comparator<? super K> c;
/*     */ 
/*     */   public ObjectHeapPriorityQueue(int capacity, Comparator<? super K> c)
/*     */   {
/*  67 */     if (capacity > 0) this.heap = ((Object[])new Object[capacity]);
/*  68 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public ObjectHeapPriorityQueue(int capacity)
/*     */   {
/*  75 */     this(capacity, null);
/*     */   }
/*     */ 
/*     */   public ObjectHeapPriorityQueue(Comparator<? super K> c)
/*     */   {
/*  82 */     this(0, c);
/*     */   }
/*     */ 
/*     */   public ObjectHeapPriorityQueue()
/*     */   {
/*  87 */     this(0, null);
/*     */   }
/*     */ 
/*     */   public ObjectHeapPriorityQueue(K[] a, int size, Comparator<? super K> c)
/*     */   {
/* 100 */     this(c);
/* 101 */     this.heap = a;
/* 102 */     this.size = size;
/* 103 */     ObjectHeaps.makeHeap(a, size, c);
/*     */   }
/*     */ 
/*     */   public ObjectHeapPriorityQueue(K[] a, Comparator<? super K> c)
/*     */   {
/* 115 */     this(a, a.length, c);
/*     */   }
/*     */ 
/*     */   public ObjectHeapPriorityQueue(K[] a, int size)
/*     */   {
/* 127 */     this(a, size, null);
/*     */   }
/*     */ 
/*     */   public ObjectHeapPriorityQueue(K[] a)
/*     */   {
/* 138 */     this(a, a.length);
/*     */   }
/*     */   public void enqueue(K x) {
/* 141 */     if (this.size == this.heap.length) this.heap = ObjectArrays.grow(this.heap, this.size + 1);
/* 142 */     this.heap[(this.size++)] = x;
/* 143 */     ObjectHeaps.upHeap(this.heap, this.size, this.size - 1, this.c);
/*     */   }
/*     */ 
/*     */   public K dequeue() {
/* 147 */     if (this.size == 0) throw new NoSuchElementException();
/*     */ 
/* 149 */     Object result = this.heap[0];
/* 150 */     this.heap[0] = this.heap[(--this.size)];
/*     */ 
/* 152 */     this.heap[this.size] = null;
/*     */ 
/* 154 */     if (this.size != 0) ObjectHeaps.downHeap(this.heap, this.size, 0, this.c);
/* 155 */     return result;
/*     */   }
/*     */ 
/*     */   public K first() {
/* 159 */     if (this.size == 0) throw new NoSuchElementException();
/* 160 */     return this.heap[0];
/*     */   }
/*     */ 
/*     */   public void changed() {
/* 164 */     ObjectHeaps.downHeap(this.heap, this.size, 0, this.c);
/*     */   }
/*     */   public int size() {
/* 167 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 171 */     ObjectArrays.fill(this.heap, 0, this.size, null);
/*     */ 
/* 173 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public void trim()
/*     */   {
/* 180 */     this.heap = ObjectArrays.trim(this.heap, this.size);
/*     */   }
/*     */   public Comparator<? super K> comparator() {
/* 183 */     return this.c;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue
 * JD-Core Version:    0.6.2
 */