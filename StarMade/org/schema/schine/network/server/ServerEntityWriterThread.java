/*    */ package org.schema.schine.network.server;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*    */ 
/*    */ public class ServerEntityWriterThread extends Thread
/*    */ {
/*  8 */   private final ObjectArrayFIFOQueue queue = new ObjectArrayFIFOQueue();
/*    */ 
/*    */   public ServerEntityWriterThread()
/*    */   {
/* 12 */     super("ServerEntityWriterThread");
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/*    */     while (true)
/*    */     {
/* 22 */       synchronized (this.queue) {
/* 23 */         if (this.queue.isEmpty()) {
/*    */           try {
/* 25 */             this.queue.wait();
/*    */           }
/*    */           catch (InterruptedException localInterruptedException) {
/* 28 */             localInterruptedException.printStackTrace();
/*    */           }
/* 28 */           continue;
/*    */         }
/* 30 */         Runnable localRunnable = (Runnable)this.queue.dequeue();
/*    */       }
/* 32 */       localObject1.run();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enqueue(Runnable paramRunnable) {
/* 37 */     synchronized (this.queue) {
/* 38 */       this.queue.enqueue(paramRunnable);
/* 39 */       this.queue.notify();
/* 40 */       return;
/*    */     }
/*    */   }
/*    */ 
/* 44 */   public int getActiveCount() { return this.queue.size(); }
/*    */ 
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerEntityWriterThread
 * JD-Core Version:    0.6.2
 */