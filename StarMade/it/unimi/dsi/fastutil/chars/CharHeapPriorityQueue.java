/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class CharHeapPriorityQueue extends AbstractCharPriorityQueue
/*     */ {
/*  53 */   protected char[] heap = CharArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected CharComparator c;
/*     */ 
/*     */   public CharHeapPriorityQueue(int capacity, CharComparator c)
/*     */   {
/*  66 */     if (capacity > 0) this.heap = new char[capacity];
/*  67 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public CharHeapPriorityQueue(int capacity)
/*     */   {
/*  74 */     this(capacity, null);
/*     */   }
/*     */ 
/*     */   public CharHeapPriorityQueue(CharComparator c)
/*     */   {
/*  81 */     this(0, c);
/*     */   }
/*     */ 
/*     */   public CharHeapPriorityQueue()
/*     */   {
/*  86 */     this(0, null);
/*     */   }
/*     */ 
/*     */   public CharHeapPriorityQueue(char[] a, int size, CharComparator c)
/*     */   {
/*  99 */     this(c);
/* 100 */     this.heap = a;
/* 101 */     this.size = size;
/* 102 */     CharHeaps.makeHeap(a, size, c);
/*     */   }
/*     */ 
/*     */   public CharHeapPriorityQueue(char[] a, CharComparator c)
/*     */   {
/* 114 */     this(a, a.length, c);
/*     */   }
/*     */ 
/*     */   public CharHeapPriorityQueue(char[] a, int size)
/*     */   {
/* 126 */     this(a, size, null);
/*     */   }
/*     */ 
/*     */   public CharHeapPriorityQueue(char[] a)
/*     */   {
/* 137 */     this(a, a.length);
/*     */   }
/*     */   public void enqueue(char x) {
/* 140 */     if (this.size == this.heap.length) this.heap = CharArrays.grow(this.heap, this.size + 1);
/*     */ 
/* 142 */     this.heap[(this.size++)] = x;
/* 143 */     CharHeaps.upHeap(this.heap, this.size, this.size - 1, this.c);
/*     */   }
/*     */ 
/*     */   public char dequeueChar() {
/* 147 */     if (this.size == 0) throw new NoSuchElementException();
/*     */ 
/* 149 */     char result = this.heap[0];
/* 150 */     this.heap[0] = this.heap[(--this.size)];
/*     */ 
/* 154 */     if (this.size != 0) CharHeaps.downHeap(this.heap, this.size, 0, this.c);
/* 155 */     return result;
/*     */   }
/*     */ 
/*     */   public char firstChar() {
/* 159 */     if (this.size == 0) throw new NoSuchElementException();
/* 160 */     return this.heap[0];
/*     */   }
/*     */ 
/*     */   public void changed() {
/* 164 */     CharHeaps.downHeap(this.heap, this.size, 0, this.c);
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
/* 180 */     this.heap = CharArrays.trim(this.heap, this.size);
/*     */   }
/*     */   public CharComparator comparator() {
/* 183 */     return this.c;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharHeapPriorityQueue
 * JD-Core Version:    0.6.2
 */