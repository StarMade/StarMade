/*     */ package it.unimi.dsi.fastutil;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class PriorityQueues
/*     */ {
/*  60 */   public static final EmptyPriorityQueue EMPTY_QUEUE = new EmptyPriorityQueue();
/*     */ 
/*     */   public static <K> PriorityQueue<K> synchronize(PriorityQueue<K> q)
/*     */   {
/*  99 */     return new SynchronizedPriorityQueue(q);
/*     */   }
/*     */ 
/*     */   public static <K> PriorityQueue<K> synchronize(PriorityQueue<K> q, Object sync)
/*     */   {
/* 108 */     return new SynchronizedPriorityQueue(q, sync);
/*     */   }
/*     */ 
/*     */   public static class SynchronizedPriorityQueue<K>
/*     */     implements PriorityQueue<K>
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final PriorityQueue<K> q;
/*     */     protected final Object sync;
/*     */ 
/*     */     protected SynchronizedPriorityQueue(PriorityQueue<K> q, Object sync)
/*     */     {
/*  73 */       this.q = q;
/*  74 */       this.sync = sync;
/*     */     }
/*     */ 
/*     */     protected SynchronizedPriorityQueue(PriorityQueue<K> q) {
/*  78 */       this.q = q;
/*  79 */       this.sync = this;
/*     */     }
/*     */     public void enqueue(K x) {
/*  82 */       synchronized (this.sync) { this.q.enqueue(x); }  } 
/*  83 */     public K dequeue() { synchronized (this.sync) { return this.q.dequeue(); }  } 
/*  84 */     public K first() { synchronized (this.sync) { return this.q.first(); }  } 
/*  85 */     public K last() { synchronized (this.sync) { return this.q.last(); }  } 
/*  86 */     public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); }  } 
/*  87 */     public int size() { synchronized (this.sync) { return this.q.size(); }  } 
/*  88 */     public void clear() { synchronized (this.sync) { this.q.clear(); }  } 
/*  89 */     public void changed() { synchronized (this.sync) { this.q.changed(); }  } 
/*  90 */     public Comparator<? super K> comparator() { synchronized (this.sync) { return this.q.comparator(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyPriorityQueue extends AbstractPriorityQueue
/*     */   {
/*     */     public void enqueue(Object o)
/*     */     {
/*  45 */       throw new UnsupportedOperationException(); } 
/*  46 */     public Object dequeue() { throw new NoSuchElementException(); } 
/*  47 */     public boolean isEmpty() { return true; } 
/*  48 */     public int size() { return 0; } 
/*     */     public void clear() {  } 
/*  50 */     public Object first() { throw new NoSuchElementException(); } 
/*  51 */     public Object last() { throw new NoSuchElementException(); } 
/*  52 */     public void changed() { throw new NoSuchElementException(); } 
/*  53 */     public Comparator<?> comparator() { return null; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.PriorityQueues
 * JD-Core Version:    0.6.2
 */