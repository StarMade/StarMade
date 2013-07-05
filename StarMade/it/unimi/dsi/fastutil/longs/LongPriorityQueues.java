/*    */ package it.unimi.dsi.fastutil.longs;
/*    */ 
/*    */ public class LongPriorityQueues
/*    */ {
/*    */   public static LongPriorityQueue synchronize(LongPriorityQueue q)
/*    */   {
/* 83 */     return new SynchronizedPriorityQueue(q);
/*    */   }
/*    */ 
/*    */   public static LongPriorityQueue synchronize(LongPriorityQueue q, Object sync)
/*    */   {
/* 90 */     return new SynchronizedPriorityQueue(q, sync);
/*    */   }
/*    */ 
/*    */   public static class SynchronizedPriorityQueue
/*    */     implements LongPriorityQueue
/*    */   {
/*    */     public static final long serialVersionUID = -7046029254386353129L;
/*    */     protected final LongPriorityQueue q;
/*    */     protected final Object sync;
/*    */ 
/*    */     protected SynchronizedPriorityQueue(LongPriorityQueue q, Object sync)
/*    */     {
/* 57 */       this.q = q;
/* 58 */       this.sync = sync;
/*    */     }
/*    */     protected SynchronizedPriorityQueue(LongPriorityQueue q) {
/* 61 */       this.q = q;
/* 62 */       this.sync = this;
/*    */     }
/* 64 */     public void enqueue(long x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 65 */     public long dequeueLong() { synchronized (this.sync) { return this.q.dequeueLong(); }  } 
/* 66 */     public long firstLong() { synchronized (this.sync) { return this.q.firstLong(); }  } 
/* 67 */     public long lastLong() { synchronized (this.sync) { return this.q.lastLong(); }  } 
/* 68 */     public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); }  } 
/* 69 */     public int size() { synchronized (this.sync) { return this.q.size(); }  } 
/* 70 */     public void clear() { synchronized (this.sync) { this.q.clear(); }  } 
/* 71 */     public void changed() { synchronized (this.sync) { this.q.changed(); }  } 
/* 72 */     public LongComparator comparator() { synchronized (this.sync) { return this.q.comparator(); }  } 
/* 73 */     public void enqueue(Long x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 74 */     public Long dequeue() { synchronized (this.sync) { return (Long)this.q.dequeue(); }  } 
/* 75 */     public Long first() { synchronized (this.sync) { return (Long)this.q.first(); }  } 
/* 76 */     public Long last() { synchronized (this.sync) { return (Long)this.q.last(); }
/*    */ 
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongPriorityQueues
 * JD-Core Version:    0.6.2
 */