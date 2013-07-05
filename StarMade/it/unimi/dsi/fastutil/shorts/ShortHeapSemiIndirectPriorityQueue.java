/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.AbstractIndirectPriorityQueue;
/*     */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ShortHeapSemiIndirectPriorityQueue extends AbstractIndirectPriorityQueue<Short>
/*     */   implements ShortIndirectPriorityQueue
/*     */ {
/*     */   protected short[] refArray;
/*  62 */   protected int[] heap = IntArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected ShortComparator c;
/*     */ 
/*     */   public ShortHeapSemiIndirectPriorityQueue(short[] refArray, int capacity, ShortComparator c)
/*     */   {
/*  74 */     if (capacity > 0) this.heap = new int[capacity];
/*  75 */     this.refArray = refArray;
/*  76 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public ShortHeapSemiIndirectPriorityQueue(short[] refArray, int capacity)
/*     */   {
/*  84 */     this(refArray, capacity, null);
/*     */   }
/*     */ 
/*     */   public ShortHeapSemiIndirectPriorityQueue(short[] refArray, ShortComparator c)
/*     */   {
/*  92 */     this(refArray, refArray.length, c);
/*     */   }
/*     */ 
/*     */   public ShortHeapSemiIndirectPriorityQueue(short[] refArray)
/*     */   {
/*  98 */     this(refArray, refArray.length, null);
/*     */   }
/*     */ 
/*     */   public ShortHeapSemiIndirectPriorityQueue(short[] refArray, int[] a, int size, ShortComparator c)
/*     */   {
/* 112 */     this(refArray, 0, c);
/* 113 */     this.heap = a;
/* 114 */     this.size = size;
/* 115 */     ShortSemiIndirectHeaps.makeHeap(refArray, a, size, c);
/*     */   }
/*     */ 
/*     */   public ShortHeapSemiIndirectPriorityQueue(short[] refArray, int[] a, ShortComparator c)
/*     */   {
/* 128 */     this(refArray, a, a.length, c);
/*     */   }
/*     */ 
/*     */   public ShortHeapSemiIndirectPriorityQueue(short[] refArray, int[] a, int size)
/*     */   {
/* 141 */     this(refArray, a, size, null);
/*     */   }
/*     */ 
/*     */   public ShortHeapSemiIndirectPriorityQueue(short[] refArray, int[] a)
/*     */   {
/* 153 */     this(refArray, a, a.length);
/*     */   }
/*     */ 
/*     */   protected void ensureElement(int index)
/*     */   {
/* 162 */     if (index < 0) throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
/* 163 */     if (index >= this.refArray.length) throw new IndexOutOfBoundsException("Index (" + index + ") is larger than or equal to reference array size (" + this.refArray.length + ")"); 
/*     */   }
/*     */ 
/*     */   public void enqueue(int x)
/*     */   {
/* 167 */     ensureElement(x);
/*     */ 
/* 169 */     if (this.size == this.heap.length) this.heap = IntArrays.grow(this.heap, this.size + 1);
/*     */ 
/* 171 */     this.heap[(this.size++)] = x;
/* 172 */     ShortSemiIndirectHeaps.upHeap(this.refArray, this.heap, this.size, this.size - 1, this.c);
/*     */   }
/*     */ 
/*     */   public int dequeue() {
/* 176 */     if (this.size == 0) throw new NoSuchElementException();
/* 177 */     int result = this.heap[0];
/* 178 */     this.heap[0] = this.heap[(--this.size)];
/* 179 */     if (this.size != 0) ShortSemiIndirectHeaps.downHeap(this.refArray, this.heap, this.size, 0, this.c);
/* 180 */     return result;
/*     */   }
/*     */ 
/*     */   public int first() {
/* 184 */     if (this.size == 0) throw new NoSuchElementException();
/* 185 */     return this.heap[0];
/*     */   }
/*     */ 
/*     */   public void changed()
/*     */   {
/* 197 */     ShortSemiIndirectHeaps.downHeap(this.refArray, this.heap, this.size, 0, this.c);
/*     */   }
/*     */ 
/*     */   public void allChanged()
/*     */   {
/* 204 */     ShortSemiIndirectHeaps.makeHeap(this.refArray, this.heap, this.size, this.c);
/*     */   }
/*     */   public int size() {
/* 207 */     return this.size;
/*     */   }
/* 209 */   public void clear() { this.size = 0; }
/*     */ 
/*     */ 
/*     */   public void trim()
/*     */   {
/* 215 */     this.heap = IntArrays.trim(this.heap, this.size);
/*     */   }
/*     */   public ShortComparator comparator() {
/* 218 */     return this.c;
/*     */   }
/*     */   public int front(int[] a) {
/* 221 */     return ShortSemiIndirectHeaps.front(this.refArray, this.heap, this.size, a);
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 225 */     StringBuffer s = new StringBuffer();
/* 226 */     s.append("[");
/* 227 */     for (int i = 0; i < this.size; i++) {
/* 228 */       if (i != 0) s.append(", ");
/* 229 */       s.append(this.refArray[this.heap[i]]);
/*     */     }
/* 231 */     s.append("]");
/* 232 */     return s.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortHeapSemiIndirectPriorityQueue
 * JD-Core Version:    0.6.2
 */