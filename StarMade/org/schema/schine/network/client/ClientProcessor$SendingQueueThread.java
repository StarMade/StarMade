/*   1:    */package org.schema.schine.network.client;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
/*   4:    */import java.io.DataOutputStream;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.net.Socket;
/*   7:    */import java.util.ArrayList;
/*   8:    */import java.util.Iterator;
/*   9:    */import org.schema.schine.network.Pinger;
/*  10:    */
/* 280:    */class ClientProcessor$SendingQueueThread
/* 281:    */  extends Thread
/* 282:    */{
/* 283:    */  private int totalPackagesQueued;
/* 284:    */  
/* 285:    */  private ClientProcessor$SendingQueueThread(ClientProcessor paramClientProcessor) {}
/* 286:    */  
/* 287:    */  public void run()
/* 288:    */  {
/* 289:    */    try
/* 290:    */    {
/* 291:291 */      while ((this.this$0.listening) && (!ClientProcessor.access$700(this.this$0).isClosed())) {
/* 292:292 */        setName("ClientSendingQueueThread(" + getId() + ")");
/* 293:    */        
/* 294:294 */        synchronized (ClientProcessor.access$400(this.this$0)) {
/* 295:295 */          while ((ClientProcessor.access$400(this.this$0).isEmpty()) && (this.this$0.listening)) {
/* 296:296 */            ClientProcessor.access$100(this.this$0).getOutput().flush();
/* 297:297 */            ClientProcessor.access$400(this.this$0).wait(10000L);
/* 298:298 */            checkPingPong();
/* 299:299 */            if (!this.this$0.listening) break;
/* 300:300 */            if (ClientProcessor.access$400(this.this$0).size() > 700)
/* 301:    */            {
/* 303:303 */              this.this$0.listening = false;
/* 304:    */            }
/* 305:    */          }
/* 306:    */          
/* 307:307 */          if (!this.this$0.listening) {
/* 308:    */            break;
/* 309:    */          }
/* 310:310 */          FastByteArrayOutputStream localFastByteArrayOutputStream1 = (FastByteArrayOutputStream)ClientProcessor.access$400(this.this$0).remove(0);
/* 311:311 */          this.totalPackagesQueued -= 1;
/* 312:    */        }
/* 313:313 */        checkPingPong();
/* 314:314 */        this.this$0.sendPacket(localFastByteArrayOutputStream2);
/* 315:315 */        ClientProcessor.releasePacket(localFastByteArrayOutputStream2);
/* 316:    */        try {
/* 317:317 */          sleep(2L);
/* 318:    */        }
/* 319:    */        catch (InterruptedException localInterruptedException) {}
/* 320:    */      }
/* 321:321 */      this.this$0.listening = false;
/* 322:    */    } catch (Exception localException) {
/* 323:323 */      System.err.println("SENDING THREAD ENDED of " + this);
/* 324:    */      
/* 325:325 */      localException.printStackTrace();
/* 326:    */    } finally {
/* 327:327 */      this.this$0.listening = false;
/* 328:    */    }
/* 329:    */    
/* 330:330 */    synchronized (ClientProcessor.access$400(this.this$0)) {
/* 331:331 */      for (??? = ClientProcessor.access$400(this.this$0).iterator(); ((Iterator)???).hasNext();) {
/* 332:332 */        ClientProcessor.releasePacket((FastByteArrayOutputStream)((Iterator)???).next());
/* 333:333 */        this.totalPackagesQueued -= 1;
/* 334:    */      }
/* 335:335 */      ClientProcessor.access$400(this.this$0).clear(); return;
/* 336:    */    }
/* 337:    */  }
/* 338:    */  
/* 339:    */  private void checkPingPong() { byte[] arrayOfByte;
/* 340:340 */    if (ClientProcessor.access$300(this.this$0))
/* 341:    */    {
/* 342:342 */      arrayOfByte = Pinger.createPing();
/* 343:    */      
/* 344:344 */      ClientProcessor.access$100(this.this$0).getOutput().writeInt(arrayOfByte.length);
/* 345:345 */      ClientProcessor.access$100(this.this$0).getOutput().write(arrayOfByte);
/* 346:    */      
/* 347:347 */      ClientProcessor.access$302(this.this$0, false);
/* 348:    */    }
/* 349:    */    
/* 350:350 */    if (ClientProcessor.access$500(this.this$0))
/* 351:    */    {
/* 352:352 */      arrayOfByte = Pinger.createPong();
/* 353:353 */      ClientProcessor.access$100(this.this$0).getOutput().writeInt(arrayOfByte.length);
/* 354:354 */      ClientProcessor.access$100(this.this$0).getOutput().write(arrayOfByte);
/* 355:    */      
/* 356:356 */      ClientProcessor.access$502(this.this$0, false);
/* 357:    */    }
/* 358:    */  }
/* 359:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientProcessor.SendingQueueThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */