/*  1:   */package org.schema.schine.network.server;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*  4:   */
/*  5:   */public class ServerEntityWriterThread
/*  6:   */  extends Thread
/*  7:   */{
/*  8: 8 */  private final ObjectArrayFIFOQueue queue = new ObjectArrayFIFOQueue();
/*  9:   */  
/* 10:   */  public ServerEntityWriterThread()
/* 11:   */  {
/* 12:12 */    super("ServerEntityWriterThread");
/* 13:   */  }
/* 14:   */  
/* 18:   */  public void run()
/* 19:   */  {
/* 20:   */    for (;;)
/* 21:   */    {
/* 22:22 */      synchronized (this.queue) {
/* 23:23 */        if (this.queue.isEmpty()) {
/* 24:   */          try {
/* 25:25 */            this.queue.wait();
/* 26:26 */          } catch (InterruptedException localInterruptedException) { 
/* 27:   */            
/* 28:28 */              localInterruptedException;
/* 29:   */          }
/* 30:28 */          continue;
/* 31:   */        }
/* 32:30 */        Runnable localRunnable = (Runnable)this.queue.dequeue();
/* 33:   */      }
/* 34:32 */      localObject1.run();
/* 35:   */    }
/* 36:   */  }
/* 37:   */  
/* 38:   */  public void enqueue(Runnable paramRunnable) {
/* 39:37 */    synchronized (this.queue) {
/* 40:38 */      this.queue.enqueue(paramRunnable);
/* 41:39 */      this.queue.notify();
/* 42:40 */      return;
/* 43:   */    }
/* 44:   */  }
/* 45:   */  
/* 46:44 */  public int getActiveCount() { return this.queue.size(); }
/* 47:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerEntityWriterThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */