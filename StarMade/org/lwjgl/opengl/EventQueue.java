/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ 
/*    */ class EventQueue
/*    */ {
/*    */   private static final int QUEUE_SIZE = 200;
/*    */   private final int event_size;
/*    */   private final ByteBuffer queue;
/*    */ 
/*    */   protected EventQueue(int event_size)
/*    */   {
/* 49 */     this.event_size = event_size;
/* 50 */     this.queue = ByteBuffer.allocate(200 * event_size);
/*    */   }
/*    */ 
/*    */   protected synchronized void clearEvents() {
/* 54 */     this.queue.clear();
/*    */   }
/*    */ 
/*    */   public synchronized void copyEvents(ByteBuffer dest)
/*    */   {
/* 61 */     this.queue.flip();
/* 62 */     int old_limit = this.queue.limit();
/* 63 */     if (dest.remaining() < this.queue.remaining())
/* 64 */       this.queue.limit(dest.remaining() + this.queue.position());
/* 65 */     dest.put(this.queue);
/* 66 */     this.queue.limit(old_limit);
/* 67 */     this.queue.compact();
/*    */   }
/*    */ 
/*    */   public synchronized boolean putEvent(ByteBuffer event)
/*    */   {
/* 75 */     if (event.remaining() != this.event_size)
/* 76 */       throw new IllegalArgumentException("Internal error: event size " + this.event_size + " does not equal the given event size " + event.remaining());
/* 77 */     if (this.queue.remaining() >= event.remaining()) {
/* 78 */       this.queue.put(event);
/* 79 */       return true;
/*    */     }
/* 81 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EventQueue
 * JD-Core Version:    0.6.2
 */