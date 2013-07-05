/*    */ package it.unimi.dsi.fastutil.shorts;
/*    */ 
/*    */ public class ShortPriorityQueues
/*    */ {
/*    */   public static ShortPriorityQueue synchronize(ShortPriorityQueue q)
/*    */   {
/* 83 */     return new SynchronizedPriorityQueue(q);
/*    */   }
/*    */ 
/*    */   public static ShortPriorityQueue synchronize(ShortPriorityQueue q, Object sync)
/*    */   {
/* 90 */     return new SynchronizedPriorityQueue(q, sync);
/*    */   }
/*    */ 
/*    */   public static class SynchronizedPriorityQueue
/*    */     implements ShortPriorityQueue
/*    */   {
/*    */     public static final long serialVersionUID = -7046029254386353129L;
/*    */     protected final ShortPriorityQueue q;
/*    */     protected final Object sync;
/*    */ 
/*    */     protected SynchronizedPriorityQueue(ShortPriorityQueue q, Object sync)
/*    */     {
/* 57 */       this.q = q;
/* 58 */       this.sync = sync;
/*    */     }
/*    */     protected SynchronizedPriorityQueue(ShortPriorityQueue q) {
/* 61 */       this.q = q;
/* 62 */       this.sync = this;
/*    */     }
/* 64 */     public void enqueue(short x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 65 */     public short dequeueShort() { synchronized (this.sync) { return this.q.dequeueShort(); }  } 
/* 66 */     public short firstShort() { synchronized (this.sync) { return this.q.firstShort(); }  } 
/* 67 */     public short lastShort() { synchronized (this.sync) { return this.q.lastShort(); }  } 
/* 68 */     public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); }  } 
/* 69 */     public int size() { synchronized (this.sync) { return this.q.size(); }  } 
/* 70 */     public void clear() { synchronized (this.sync) { this.q.clear(); }  } 
/* 71 */     public void changed() { synchronized (this.sync) { this.q.changed(); }  } 
/* 72 */     public ShortComparator comparator() { synchronized (this.sync) { return this.q.comparator(); }  } 
/* 73 */     public void enqueue(Short x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 74 */     public Short dequeue() { synchronized (this.sync) { return (Short)this.q.dequeue(); }  } 
/* 75 */     public Short first() { synchronized (this.sync) { return (Short)this.q.first(); }  } 
/* 76 */     public Short last() { synchronized (this.sync) { return (Short)this.q.last(); }
/*    */ 
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortPriorityQueues
 * JD-Core Version:    0.6.2
 */