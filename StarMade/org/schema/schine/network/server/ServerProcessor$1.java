/*   1:    */package org.schema.schine.network.server;
/*   2:    */
/* 196:    */class ServerProcessor$1
/* 197:    */  extends Thread
/* 198:    */{
/* 199:    */  ServerProcessor$1(ServerProcessor paramServerProcessor) {}
/* 200:    */  
/* 394:    */  public void run()
/* 395:    */  {
/* 396:    */    try
/* 397:    */    {
/* 398:398 */      this.this$0.setStopTransmit(true);
/* 399:399 */      synchronized (ServerProcessor.access$800(this.this$0)) {
/* 400:400 */        ServerProcessor.access$800(this.this$0).notify();
/* 401:    */      }
/* 402:402 */      Thread.sleep(3000L);
/* 403:403 */    } catch (InterruptedException localInterruptedException) { ??? = null;
/* 404:    */      
/* 405:405 */      localInterruptedException.printStackTrace();
/* 406:    */    }
/* 407:    */    
/* 408:406 */    this.this$0.disconnect();
/* 409:    */  }
/* 410:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */