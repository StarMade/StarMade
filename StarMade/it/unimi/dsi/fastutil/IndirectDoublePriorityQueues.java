/*     */ package it.unimi.dsi.fastutil;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class IndirectDoublePriorityQueues
/*     */ {
/*  51 */   public static final EmptyIndirectDoublePriorityQueue EMPTY_QUEUE = new EmptyIndirectDoublePriorityQueue();
/*     */ 
/*     */   public static <K> IndirectDoublePriorityQueue<K> synchronize(IndirectDoublePriorityQueue<K> q)
/*     */   {
/*  99 */     return new SynchronizedIndirectDoublePriorityQueue(q);
/*     */   }
/*     */ 
/*     */   public static <K> IndirectDoublePriorityQueue<K> synchronize(IndirectDoublePriorityQueue<K> q, Object sync)
/*     */   {
/* 108 */     return new SynchronizedIndirectDoublePriorityQueue(q, sync);
/*     */   }
/*     */ 
/*     */   public static class SynchronizedIndirectDoublePriorityQueue<K>
/*     */     implements IndirectDoublePriorityQueue<K>
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final IndirectDoublePriorityQueue<K> q;
/*     */     protected final Object sync;
/*     */ 
/*     */     protected SynchronizedIndirectDoublePriorityQueue(IndirectDoublePriorityQueue<K> q, Object sync)
/*     */     {
/*  64 */       this.q = q;
/*  65 */       this.sync = sync;
/*     */     }
/*     */ 
/*     */     protected SynchronizedIndirectDoublePriorityQueue(IndirectDoublePriorityQueue<K> q) {
/*  69 */       this.q = q;
/*  70 */       this.sync = this;
/*     */     }
/*     */     public void enqueue(int index) {
/*  73 */       synchronized (this.sync) { this.q.enqueue(index); }  } 
/*  74 */     public int dequeue() { synchronized (this.sync) { return this.q.dequeue(); }  } 
/*  75 */     public int first() { synchronized (this.sync) { return this.q.first(); }  } 
/*  76 */     public int last() { synchronized (this.sync) { return this.q.last(); }  } 
/*  77 */     public boolean contains(int index) { synchronized (this.sync) { return this.q.contains(index); }  } 
/*  78 */     public int secondaryFirst() { synchronized (this.sync) { return this.q.secondaryFirst(); }  } 
/*  79 */     public int secondaryLast() { synchronized (this.sync) { return this.q.secondaryLast(); }  } 
/*  80 */     public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); }  } 
/*  81 */     public int size() { synchronized (this.sync) { return this.q.size(); }  } 
/*  82 */     public void clear() { synchronized (this.sync) { this.q.clear(); }  } 
/*  83 */     public void changed() { synchronized (this.sync) { this.q.changed(); }  } 
/*  84 */     public void allChanged() { synchronized (this.sync) { this.q.allChanged(); }  } 
/*  85 */     public void changed(int i) { synchronized (this.sync) { this.q.changed(i); }  } 
/*  86 */     public boolean remove(int i) { synchronized (this.sync) { return this.q.remove(i); }  } 
/*  87 */     public Comparator<? super K> comparator() { synchronized (this.sync) { return this.q.comparator(); }  } 
/*  88 */     public Comparator<? super K> secondaryComparator() { synchronized (this.sync) { return this.q.secondaryComparator(); }  } 
/*  89 */     public int secondaryFront(int[] a) { return this.q.secondaryFront(a); } 
/*  90 */     public int front(int[] a) { return this.q.front(a); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyIndirectDoublePriorityQueue extends IndirectPriorityQueues.EmptyIndirectPriorityQueue
/*     */   {
/*     */     public int secondaryFirst()
/*     */     {
/*  42 */       throw new NoSuchElementException(); } 
/*  43 */     public int secondaryLast() { throw new NoSuchElementException(); } 
/*  44 */     public Comparator<?> secondaryComparator() { return null; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.IndirectDoublePriorityQueues
 * JD-Core Version:    0.6.2
 */