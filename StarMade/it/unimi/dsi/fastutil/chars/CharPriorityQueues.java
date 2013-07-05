/*    */ package it.unimi.dsi.fastutil.chars;
/*    */ 
/*    */ public class CharPriorityQueues
/*    */ {
/*    */   public static CharPriorityQueue synchronize(CharPriorityQueue q)
/*    */   {
/* 83 */     return new SynchronizedPriorityQueue(q);
/*    */   }
/*    */ 
/*    */   public static CharPriorityQueue synchronize(CharPriorityQueue q, Object sync)
/*    */   {
/* 90 */     return new SynchronizedPriorityQueue(q, sync);
/*    */   }
/*    */ 
/*    */   public static class SynchronizedPriorityQueue
/*    */     implements CharPriorityQueue
/*    */   {
/*    */     public static final long serialVersionUID = -7046029254386353129L;
/*    */     protected final CharPriorityQueue q;
/*    */     protected final Object sync;
/*    */ 
/*    */     protected SynchronizedPriorityQueue(CharPriorityQueue q, Object sync)
/*    */     {
/* 57 */       this.q = q;
/* 58 */       this.sync = sync;
/*    */     }
/*    */     protected SynchronizedPriorityQueue(CharPriorityQueue q) {
/* 61 */       this.q = q;
/* 62 */       this.sync = this;
/*    */     }
/* 64 */     public void enqueue(char x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 65 */     public char dequeueChar() { synchronized (this.sync) { return this.q.dequeueChar(); }  } 
/* 66 */     public char firstChar() { synchronized (this.sync) { return this.q.firstChar(); }  } 
/* 67 */     public char lastChar() { synchronized (this.sync) { return this.q.lastChar(); }  } 
/* 68 */     public boolean isEmpty() { synchronized (this.sync) { return this.q.isEmpty(); }  } 
/* 69 */     public int size() { synchronized (this.sync) { return this.q.size(); }  } 
/* 70 */     public void clear() { synchronized (this.sync) { this.q.clear(); }  } 
/* 71 */     public void changed() { synchronized (this.sync) { this.q.changed(); }  } 
/* 72 */     public CharComparator comparator() { synchronized (this.sync) { return this.q.comparator(); }  } 
/* 73 */     public void enqueue(Character x) { synchronized (this.sync) { this.q.enqueue(x); }  } 
/* 74 */     public Character dequeue() { synchronized (this.sync) { return (Character)this.q.dequeue(); }  } 
/* 75 */     public Character first() { synchronized (this.sync) { return (Character)this.q.first(); }  } 
/* 76 */     public Character last() { synchronized (this.sync) { return (Character)this.q.last(); }
/*    */ 
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharPriorityQueues
 * JD-Core Version:    0.6.2
 */