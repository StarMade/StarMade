/*    */ package it.unimi.dsi.fastutil.floats;
/*    */ 
/*    */ public class FloatPriorityQueues
/*    */ {
/*    */   public static FloatPriorityQueue synchronize(FloatPriorityQueue q)
/*    */   {
/* 83 */     return new SynchronizedPriorityQueue(q);
/*    */   }
/*    */ 
/*    */   public static FloatPriorityQueue synchronize(FloatPriorityQueue q, Object sync)
/*    */   {
/* 90 */     return new SynchronizedPriorityQueue(q, sync);
/*    */   }
/*    */ 
/*    */   public static class SynchronizedPriorityQueue
/*    */     implements FloatPriorityQueue
/*    */   {
/*    */     public static final long serialVersionUID = -7046029254386353129L;
/*    */     protected final FloatPriorityQueue q;
/*    */     protected final Object sync;
/*    */ 
/*    */     protected SynchronizedPriorityQueue(FloatPriorityQueue q, Object sync)
/*    */     {
/* 57 */       this.q = q;
/* 58 */       this.sync = sync;
/*    */     }
/*    */     protected SynchronizedPriorityQueue(FloatPriorityQueue q) {
/* 61 */       this.q = q;
/* 62 */       this.sync = this;
/*    */     }
/* 64 */     public void enqueue(float x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 65 */     public float dequeueFloat() { synchronized (this.sync) { return this.q.dequeueFloat(); }  } 
/* 66 */     public float firstFloat() { synchronized (this.sync) { return this.q.firstFloat(); }  } 
/* 67 */     public float lastFloat() { synchronized (this.sync) { return this.q.lastFloat(); }  } 
/* 68 */     public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); }  } 
/* 69 */     public int size() { synchronized (this.sync) { return this.q.size(); }  } 
/* 70 */     public void clear() { synchronized (this.sync) { this.q.clear(); }  } 
/* 71 */     public void changed() { synchronized (this.sync) { this.q.changed(); }  } 
/* 72 */     public FloatComparator comparator() { synchronized (this.sync) { return this.q.comparator(); }  } 
/* 73 */     public void enqueue(Float x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 74 */     public Float dequeue() { synchronized (this.sync) { return (Float)this.q.dequeue(); }  } 
/* 75 */     public Float first() { synchronized (this.sync) { return (Float)this.q.first(); }  } 
/* 76 */     public Float last() { synchronized (this.sync) { return (Float)this.q.last(); }
/*    */ 
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatPriorityQueues
 * JD-Core Version:    0.6.2
 */