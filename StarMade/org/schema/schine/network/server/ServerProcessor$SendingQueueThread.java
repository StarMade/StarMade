/*   1:    */package org.schema.schine.network.server;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
/*   4:    */import java.io.DataOutputStream;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.Iterator;
/*   8:    */import org.schema.schine.network.Pinger;
/*   9:    */
/* 461:    */class ServerProcessor$SendingQueueThread
/* 462:    */  extends Thread
/* 463:    */{
/* 464:    */  private ServerProcessor$SendingQueueThread(ServerProcessor paramServerProcessor) {}
/* 465:    */  
/* 466:    */  public void run()
/* 467:    */  {
/* 468:    */    try
/* 469:    */    {
/* 470:470 */      while ((ServerProcessor.access$500(this.this$0)) && (!this.this$0.isStopTransmit())) {
/* 471:471 */        setName("SendingQueueThread(" + ServerProcessor.access$400(this.this$0) + ")");
/* 472:    */        
/* 473:473 */        synchronized (ServerProcessor.access$800(this.this$0)) {
/* 474:474 */          while ((ServerProcessor.access$800(this.this$0).isEmpty()) && (ServerProcessor.access$500(this.this$0))) {
/* 475:475 */            ServerProcessor.access$1000(this.this$0).flush();
/* 476:476 */            ServerProcessor.access$800(this.this$0).wait(10000L);
/* 477:477 */            checkPingPong();
/* 478:478 */            if ((!ServerProcessor.access$500(this.this$0)) || (this.this$0.isStopTransmit())) break;
/* 479:479 */            if (ServerProcessor.access$800(this.this$0).size() > 700)
/* 480:    */            {
/* 482:482 */              ServerProcessor.access$502(this.this$0, false);
/* 483:    */            }
/* 484:    */          }
/* 485:485 */          if ((ServerProcessor.access$500(this.this$0)) && (this.this$0.isStopTransmit())) {
/* 486:    */            break;
/* 487:    */          }
/* 488:488 */          FastByteArrayOutputStream localFastByteArrayOutputStream1 = (FastByteArrayOutputStream)ServerProcessor.access$800(this.this$0).remove(0);
/* 489:489 */          ServerProcessor.totalPackagesQueued -= 1;
/* 490:    */        }
/* 491:491 */        checkPingPong();
/* 492:492 */        this.this$0.sendPacket(localFastByteArrayOutputStream2);
/* 493:493 */        ServerProcessor.releasePacket(localFastByteArrayOutputStream2);
/* 494:494 */        if (ServerProcessor.access$1100(this.this$0)) {
/* 495:495 */          this.this$0.disconnect();
/* 496:    */        }
/* 497:    */        try {
/* 498:498 */          sleep(2L);
/* 499:    */        }
/* 500:    */        catch (InterruptedException localInterruptedException) {}
/* 501:    */      }
/* 502:502 */      ServerProcessor.access$502(this.this$0, false);
/* 503:    */    } catch (Exception localException) {
/* 504:504 */      System.err.println("SENDING THREAD ENDED of " + ServerProcessor.access$400(this.this$0));
/* 505:    */      
/* 506:506 */      localException.printStackTrace();
/* 507:    */    } finally {
/* 508:508 */      ServerProcessor.access$502(this.this$0, false);
/* 509:    */    }
/* 510:    */    
/* 511:511 */    synchronized (ServerProcessor.access$800(this.this$0)) {
/* 512:512 */      for (??? = ServerProcessor.access$800(this.this$0).iterator(); ((Iterator)???).hasNext();) {
/* 513:513 */        ServerProcessor.releasePacket((FastByteArrayOutputStream)((Iterator)???).next());
/* 514:514 */        ServerProcessor.totalPackagesQueued -= 1;
/* 515:    */      }
/* 516:516 */      ServerProcessor.access$800(this.this$0).clear(); return;
/* 517:    */    } }
/* 518:    */  
/* 519:    */  private void checkPingPong() { byte[] arrayOfByte;
/* 520:520 */    if (ServerProcessor.access$700(this.this$0))
/* 521:    */    {
/* 522:522 */      arrayOfByte = Pinger.createPing();
/* 523:    */      
/* 524:524 */      ServerProcessor.access$1000(this.this$0).writeInt(arrayOfByte.length);
/* 525:525 */      ServerProcessor.access$1000(this.this$0).writeLong(System.currentTimeMillis());
/* 526:526 */      ServerProcessor.access$1000(this.this$0).write(arrayOfByte);
/* 527:527 */      if (ServerProcessor.access$000(this.this$0).flushPingImmediately()) {
/* 528:528 */        ServerProcessor.access$1000(this.this$0).flush();
/* 529:    */      }
/* 530:530 */      ServerProcessor.access$702(this.this$0, false);
/* 531:    */    }
/* 532:    */    
/* 533:533 */    if (ServerProcessor.access$900(this.this$0))
/* 534:    */    {
/* 535:535 */      arrayOfByte = Pinger.createPong();
/* 536:    */      
/* 537:537 */      ServerProcessor.access$1000(this.this$0).writeInt(arrayOfByte.length);
/* 538:538 */      ServerProcessor.access$1000(this.this$0).writeLong(System.currentTimeMillis());
/* 539:539 */      ServerProcessor.access$1000(this.this$0).write(arrayOfByte);
/* 540:540 */      if (ServerProcessor.access$000(this.this$0).flushPingImmediately()) {
/* 541:541 */        ServerProcessor.access$1000(this.this$0).flush();
/* 542:    */      }
/* 543:543 */      ServerProcessor.access$902(this.this$0, false);
/* 544:    */    }
/* 545:    */  }
/* 546:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor.SendingQueueThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */