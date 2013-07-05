/*    */ package it.unimi.dsi.fastutil.ints;
/*    */ 
/*    */ public class IntPriorityQueues
/*    */ {
/*    */   public static IntPriorityQueue synchronize(IntPriorityQueue q)
/*    */   {
/* 83 */     return new SynchronizedPriorityQueue(q);
/*    */   }
/*    */ 
/*    */   public static IntPriorityQueue synchronize(IntPriorityQueue q, Object sync)
/*    */   {
/* 90 */     return new SynchronizedPriorityQueue(q, sync);
/*    */   }
/*    */ 
/*    */   public static class SynchronizedPriorityQueue
/*    */     implements IntPriorityQueue
/*    */   {
/*    */     public static final long serialVersionUID = -7046029254386353129L;
/*    */     protected final IntPriorityQueue q;
/*    */     protected final Object sync;
/*    */ 
/*    */     protected SynchronizedPriorityQueue(IntPriorityQueue q, Object sync)
/*    */     {
/* 57 */       this.q = q;
/* 58 */       this.sync = sync;
/*    */     }
/*    */     protected SynchronizedPriorityQueue(IntPriorityQueue q) {
/* 61 */       this.q = q;
/* 62 */       this.sync = this;
/*    */     }
/* 64 */     public void enqueue(int x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 65 */     public int dequeueInt() { synchronized (this.sync) { return this.q.dequeueInt(); }  } 
/* 66 */     public int firstInt() { synchronized (this.sync) { return this.q.firstInt(); }  } 
/* 67 */     public int lastInt() { synchronized (this.sync) { return this.q.lastInt(); }  } 
/* 68 */     public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); }  } 
/* 69 */     public int size() { synchronized (this.sync) { return this.q.size(); }  } 
/* 70 */     public void clear() { synchronized (this.sync) { this.q.clear(); }  } 
/* 71 */     public void changed() { synchronized (this.sync) { this.q.changed(); }  } 
/* 72 */     public IntComparator comparator() { synchronized (this.sync) { return this.q.comparator(); }  } 
/* 73 */     public void enqueue(Integer x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 74 */     public Integer dequeue() { synchronized (this.sync) { return (Integer)this.q.dequeue(); }  } 
/* 75 */     public Integer first() { synchronized (this.sync) { return (Integer)this.q.first(); }  } 
/* 76 */     public Integer last() { synchronized (this.sync) { return (Integer)this.q.last(); }
/*    */ 
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntPriorityQueues
 * JD-Core Version:    0.6.2
 */