/*    */ package it.unimi.dsi.fastutil.bytes;
/*    */ 
/*    */ public class BytePriorityQueues
/*    */ {
/*    */   public static BytePriorityQueue synchronize(BytePriorityQueue q)
/*    */   {
/* 83 */     return new SynchronizedPriorityQueue(q);
/*    */   }
/*    */ 
/*    */   public static BytePriorityQueue synchronize(BytePriorityQueue q, Object sync)
/*    */   {
/* 90 */     return new SynchronizedPriorityQueue(q, sync);
/*    */   }
/*    */ 
/*    */   public static class SynchronizedPriorityQueue
/*    */     implements BytePriorityQueue
/*    */   {
/*    */     public static final long serialVersionUID = -7046029254386353129L;
/*    */     protected final BytePriorityQueue q;
/*    */     protected final Object sync;
/*    */ 
/*    */     protected SynchronizedPriorityQueue(BytePriorityQueue q, Object sync)
/*    */     {
/* 57 */       this.q = q;
/* 58 */       this.sync = sync;
/*    */     }
/*    */     protected SynchronizedPriorityQueue(BytePriorityQueue q) {
/* 61 */       this.q = q;
/* 62 */       this.sync = this;
/*    */     }
/* 64 */     public void enqueue(byte x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 65 */     public byte dequeueByte() { synchronized (this.sync) { return this.q.dequeueByte(); }  } 
/* 66 */     public byte firstByte() { synchronized (this.sync) { return this.q.firstByte(); }  } 
/* 67 */     public byte lastByte() { synchronized (this.sync) { return this.q.lastByte(); }  } 
/* 68 */     public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); }  } 
/* 69 */     public int size() { synchronized (this.sync) { return this.q.size(); }  } 
/* 70 */     public void clear() { synchronized (this.sync) { this.q.clear(); }  } 
/* 71 */     public void changed() { synchronized (this.sync) { this.q.changed(); }  } 
/* 72 */     public ByteComparator comparator() { synchronized (this.sync) { return this.q.comparator(); }  } 
/* 73 */     public void enqueue(Byte x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 74 */     public Byte dequeue() { synchronized (this.sync) { return (Byte)this.q.dequeue(); }  } 
/* 75 */     public Byte first() { synchronized (this.sync) { return (Byte)this.q.first(); }  } 
/* 76 */     public Byte last() { synchronized (this.sync) { return (Byte)this.q.last(); }
/*    */ 
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.BytePriorityQueues
 * JD-Core Version:    0.6.2
 */