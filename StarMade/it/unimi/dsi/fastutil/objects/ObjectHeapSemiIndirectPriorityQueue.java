/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.AbstractIndirectPriorityQueue;
/*     */ import it.unimi.dsi.fastutil.IndirectPriorityQueue;
/*     */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ObjectHeapSemiIndirectPriorityQueue<K> extends AbstractIndirectPriorityQueue<K>
/*     */   implements IndirectPriorityQueue<K>
/*     */ {
/*     */   protected K[] refArray;
/*  63 */   protected int[] heap = IntArrays.EMPTY_ARRAY;
/*     */   protected int size;
/*     */   protected Comparator<? super K> c;
/*     */ 
/*     */   public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int capacity, Comparator<? super K> c)
/*     */   {
/*  75 */     if (capacity > 0) this.heap = new int[capacity];
/*  76 */     this.refArray = refArray;
/*  77 */     this.c = c;
/*     */   }
/*     */ 
/*     */   public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int capacity)
/*     */   {
/*  85 */     this(refArray, capacity, null);
/*     */   }
/*     */ 
/*     */   public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, Comparator<? super K> c)
/*     */   {
/*  93 */     this(refArray, refArray.length, c);
/*     */   }
/*     */ 
/*     */   public ObjectHeapSemiIndirectPriorityQueue(K[] refArray)
/*     */   {
/*  99 */     this(refArray, refArray.length, null);
/*     */   }
/*     */ 
/*     */   public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] a, int size, Comparator<? super K> c)
/*     */   {
/* 113 */     this(refArray, 0, c);
/* 114 */     this.heap = a;
/* 115 */     this.size = size;
/* 116 */     ObjectSemiIndirectHeaps.makeHeap(refArray, a, size, c);
/*     */   }
/*     */ 
/*     */   public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] a, Comparator<? super K> c)
/*     */   {
/* 129 */     this(refArray, a, a.length, c);
/*     */   }
/*     */ 
/*     */   public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] a, int size)
/*     */   {
/* 142 */     this(refArray, a, size, null);
/*     */   }
/*     */ 
/*     */   public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] a)
/*     */   {
/* 154 */     this(refArray, a, a.length);
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
/* 172 */     ObjectSemiIndirectHeaps.upHeap(this.refArray, this.heap, this.size, this.size - 1, this.c);
/*     */   }
/*     */ 
/*     */   public int dequeue() {
/* 176 */     if (this.size == 0) throw new NoSuchElementException();
/* 177 */     int result = this.heap[0];
/* 178 */     this.heap[0] = this.heap[(--this.size)];
/* 179 */     if (this.size != 0) ObjectSemiIndirectHeaps.downHeap(this.refArray, this.heap, this.size, 0, this.c);
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
/* 197 */     ObjectSemiIndirectHeaps.downHeap(this.refArray, this.heap, this.size, 0, this.c);
/*     */   }
/*     */ 
/*     */   public void allChanged()
/*     */   {
/* 204 */     ObjectSemiIndirectHeaps.makeHeap(this.refArray, this.heap, this.size, this.c);
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
/*     */   public Comparator<? super K> comparator() {
/* 218 */     return this.c;
/*     */   }
/*     */   public int front(int[] a) {
/* 221 */     return ObjectSemiIndirectHeaps.front(this.refArray, this.heap, this.size, a);
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectHeapSemiIndirectPriorityQueue
 * JD-Core Version:    0.6.2
 */