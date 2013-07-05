/*    */ package it.unimi.dsi.fastutil.doubles;
/*    */ 
/*    */ public class DoublePriorityQueues
/*    */ {
/*    */   public static DoublePriorityQueue synchronize(DoublePriorityQueue q)
/*    */   {
/* 83 */     return new SynchronizedPriorityQueue(q);
/*    */   }
/*    */ 
/*    */   public static DoublePriorityQueue synchronize(DoublePriorityQueue q, Object sync)
/*    */   {
/* 90 */     return new SynchronizedPriorityQueue(q, sync);
/*    */   }
/*    */ 
/*    */   public static class SynchronizedPriorityQueue
/*    */     implements DoublePriorityQueue
/*    */   {
/*    */     public static final long serialVersionUID = -7046029254386353129L;
/*    */     protected final DoublePriorityQueue q;
/*    */     protected final Object sync;
/*    */ 
/*    */     protected SynchronizedPriorityQueue(DoublePriorityQueue q, Object sync)
/*    */     {
/* 57 */       this.q = q;
/* 58 */       this.sync = sync;
/*    */     }
/*    */     protected SynchronizedPriorityQueue(DoublePriorityQueue q) {
/* 61 */       this.q = q;
/* 62 */       this.sync = this;
/*    */     }
/* 64 */     public void enqueue(double x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 65 */     public double dequeueDouble() { synchronized (this.sync) { return this.q.dequeueDouble(); }  } 
/* 66 */     public double firstDouble() { synchronized (this.sync) { return this.q.firstDouble(); }  } 
/* 67 */     public double lastDouble() { synchronized (this.sync) { return this.q.lastDouble(); }  } 
/* 68 */     public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); }  } 
/* 69 */     public int size() { synchronized (this.sync) { return this.q.size(); }  } 
/* 70 */     public void clear() { synchronized (this.sync) { this.q.clear(); }  } 
/* 71 */     public void changed() { synchronized (this.sync) { this.q.changed(); }  } 
/* 72 */     public DoubleComparator comparator() { synchronized (this.sync) { return this.q.comparator(); }  } 
/* 73 */     public void enqueue(Double x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 74 */     public Double dequeue() { synchronized (this.sync) { return (Double)this.q.dequeue(); }  } 
/* 75 */     public Double first() { synchronized (this.sync) { return (Double)this.q.first(); }  } 
/* 76 */     public Double last() { synchronized (this.sync) { return (Double)this.q.last(); }
/*    */ 
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoublePriorityQueues
 * JD-Core Version:    0.6.2
 */