/*     */ package it.unimi.dsi.fastutil;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class IndirectPriorityQueues
/*     */ {
/*  63 */   public static final EmptyIndirectPriorityQueue EMPTY_QUEUE = new EmptyIndirectPriorityQueue();
/*     */ 
/*     */   public static <K> IndirectPriorityQueue<K> synchronize(IndirectPriorityQueue<K> q)
/*     */   {
/* 107 */     return new SynchronizedIndirectPriorityQueue(q);
/*     */   }
/*     */ 
/*     */   public static <K> IndirectPriorityQueue<K> synchronize(IndirectPriorityQueue<K> q, Object sync)
/*     */   {
/* 116 */     return new SynchronizedIndirectPriorityQueue(q, sync);
/*     */   }
/*     */ 
/*     */   public static class SynchronizedIndirectPriorityQueue<K>
/*     */     implements IndirectPriorityQueue<K>
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final IndirectPriorityQueue<K> q;
/*     */     protected final Object sync;
/*     */ 
/*     */     protected SynchronizedIndirectPriorityQueue(IndirectPriorityQueue<K> q, Object sync)
/*     */     {
/*  76 */       this.q = q;
/*  77 */       this.sync = sync;
/*     */     }
/*     */ 
/*     */     protected SynchronizedIndirectPriorityQueue(IndirectPriorityQueue<K> q) {
/*  81 */       this.q = q;
/*  82 */       this.sync = this;
/*     */     }
/*     */     public void enqueue(int x) {
/*  85 */       synchronized (this.sync) { this.q.enqueue(x); }  } 
/*  86 */     public int dequeue() { synchronized (this.sync) { return this.q.dequeue(); }  } 
/*  87 */     public boolean contains(int index) { synchronized (this.sync) { return this.q.contains(index); }  } 
/*  88 */     public int first() { synchronized (this.sync) { return this.q.first(); }  } 
/*  89 */     public int last() { synchronized (this.sync) { return this.q.last(); }  } 
/*  90 */     public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); }  } 
/*  91 */     public int size() { synchronized (this.sync) { return this.q.size(); }  } 
/*  92 */     public void clear() { synchronized (this.sync) { this.q.clear(); }  } 
/*  93 */     public void changed() { synchronized (this.sync) { this.q.changed(); }  } 
/*  94 */     public void allChanged() { synchronized (this.sync) { this.q.allChanged(); }  } 
/*  95 */     public void changed(int i) { synchronized (this.sync) { this.q.changed(i); }  } 
/*  96 */     public boolean remove(int i) { synchronized (this.sync) { return this.q.remove(i); }  } 
/*  97 */     public Comparator<? super K> comparator() { synchronized (this.sync) { return this.q.comparator(); }  } 
/*  98 */     public int front(int[] a) { return this.q.front(a); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyIndirectPriorityQueue extends AbstractIndirectPriorityQueue
/*     */   {
/*     */     public void enqueue(int i)
/*     */     {
/*  43 */       throw new UnsupportedOperationException(); } 
/*  44 */     public int dequeue() { throw new NoSuchElementException(); } 
/*  45 */     public boolean isEmpty() { return true; } 
/*  46 */     public int size() { return 0; } 
/*  47 */     public boolean contains(int index) { return false; } 
/*     */     public void clear() {  } 
/*  49 */     public int first() { throw new NoSuchElementException(); } 
/*  50 */     public int last() { throw new NoSuchElementException(); } 
/*  51 */     public void changed() { throw new NoSuchElementException(); } 
/*     */     public void allChanged() {  } 
/*  53 */     public Comparator<?> comparator() { return null; } 
/*  54 */     public void changed(int i) { throw new IllegalArgumentException("Index " + i + " is not in the queue"); } 
/*  55 */     public boolean remove(int i) { return false; } 
/*  56 */     public int front(int[] a) { return 0; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.IndirectPriorityQueues
 * JD-Core Version:    0.6.2
 */