/*   1:    */package org.schema.schine.network.client;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
/*   4:    */import java.io.ByteArrayInputStream;
/*   5:    */import java.io.DataInputStream;
/*   6:    */import java.io.DataOutputStream;
/*   7:    */import java.io.EOFException;
/*   8:    */import java.io.IOException;
/*   9:    */import java.io.InputStream;
/*  10:    */import java.io.OutputStream;
/*  11:    */import java.io.PrintStream;
/*  12:    */import java.net.Socket;
/*  13:    */import java.net.SocketException;
/*  14:    */import java.util.ArrayList;
/*  15:    */import java.util.Arrays;
/*  16:    */import java.util.HashMap;
/*  17:    */import java.util.Map;
/*  18:    */import org.schema.schine.network.Command;
/*  19:    */import org.schema.schine.network.CommandMap;
/*  20:    */import org.schema.schine.network.Header;
/*  21:    */import org.schema.schine.network.NetUtil;
/*  22:    */import org.schema.schine.network.NetworkProcessor;
/*  23:    */import org.schema.schine.network.Request;
/*  24:    */import org.schema.schine.network.StateInterface;
/*  25:    */import org.schema.schine.network.exception.DisconnectException;
/*  26:    */import xm;
/*  27:    */
/*  76:    */public class ClientProcessor
/*  77:    */  implements Runnable, NetworkProcessor
/*  78:    */{
/*  79:    */  private static final int MAX_PACKET_POOL_SIZE = 1000;
/*  80: 80 */  public static int MAX_PACKET_SIZE = 20480;
/*  81:    */  
/* 142:    */  public static final boolean DEBUG_BIG_CHUNKS = false;
/* 143:    */  
/* 203:203 */  private final Map pendingRequests = new HashMap();
/* 204:    */  
/* 205:205 */  private Object lock = new Object();
/* 206:    */  
/* 207:    */  private FastByteArrayOutputStream byteArrayOutDoubleBuffer;
/* 208:    */  
/* 209:    */  private DataOutputStream outDoubleBuffer;
/* 210:    */  
/* 211:    */  private DataInputStream inDoubleBuffer;
/* 212:    */  
/* 213:    */  private Socket receive;
/* 214:    */  
/* 215:215 */  boolean listening = true;
/* 216:    */  
/* 218:    */  private ClientStateInterface state;
/* 219:    */  
/* 221:    */  private ClientToServerConnection clientToServerConnection;
/* 222:    */  
/* 223:    */  private DataInputStream dataInputStream;
/* 224:    */  
/* 225:225 */  byte[] receiveBuffer = new byte[1024000];
/* 226:    */  
/* 228:    */  private Header headerTmp;
/* 229:    */  
/* 231:    */  private ClientProcessor.Pinger pinger;
/* 232:    */  
/* 234:    */  private Thread thread;
/* 235:    */  
/* 237:    */  public long lastPacketId;
/* 238:    */  
/* 239:    */  private Command lastCommand;
/* 240:    */  
/* 241:    */  private boolean ping;
/* 242:    */  
/* 243:    */  private boolean pong;
/* 244:    */  
/* 245:    */  private long serverPacketSentTimestamp;
/* 246:    */  
/* 247:    */  private ClientProcessor.SendingQueueThread sendingQueueThread;
/* 248:    */  
/* 250:    */  public ClientProcessor(ClientToServerConnection paramClientToServerConnection, ClientStateInterface paramClientStateInterface)
/* 251:    */  {
/* 252:252 */    this.outDoubleBuffer = new DataOutputStream(this.byteArrayOutDoubleBuffer = new FastByteArrayOutputStream(307200));
/* 253:    */    
/* 255:255 */    this.state = paramClientStateInterface;
/* 256:256 */    this.clientToServerConnection = paramClientToServerConnection;
/* 257:    */    
/* 260:260 */    this.receive = paramClientToServerConnection.getConnection();
/* 261:    */    
/* 262:262 */    this.sendingQueueThread = new ClientProcessor.SendingQueueThread(this, null);
/* 263:263 */    this.sendingQueueThread.start();
/* 264:    */  }
/* 265:    */  
/* 267:    */  public void closeSocket()
/* 268:    */  {
/* 269:269 */    synchronized (this.lock) {
/* 270:270 */      sendLogout();
/* 271:    */    }
/* 272:272 */    System.err.println("[CLIENT] CLOSING SOCKET");
/* 273:273 */    if (!this.clientToServerConnection.getConnection().isClosed()) {
/* 274:274 */      System.err.println("[CLIENT] CLOSING SOCKET");
/* 275:275 */      this.clientToServerConnection.getConnection().close();
/* 276:    */    }
/* 277:    */  }
/* 278:    */  
/* 279:279 */  private final ArrayList sendingQueue = new ArrayList();
/* 280:    */  
/* 320:    */  private int totalPackagesQueued;
/* 321:    */  
/* 360:360 */  private static final ArrayList packetPool = new ArrayList();
/* 361:    */  
/* 362:    */  public static void releasePacket(FastByteArrayOutputStream paramFastByteArrayOutputStream) {
/* 363:363 */    paramFastByteArrayOutputStream.reset();
/* 364:364 */    synchronized (packetPool) {
/* 365:365 */      if (packetPool.size() < 1000) {
/* 366:366 */        packetPool.add(paramFastByteArrayOutputStream);
/* 367:    */      }
/* 368:    */      return;
/* 369:    */    }
/* 370:    */  }
/* 371:    */  
/* 372:    */  public static FastByteArrayOutputStream getNewPacket() {
/* 373:373 */    synchronized (packetPool) {
/* 374:374 */      if (!packetPool.isEmpty()) {
/* 375:375 */        return (FastByteArrayOutputStream)packetPool.remove(0);
/* 376:    */      }
/* 377:    */    }
/* 378:378 */    return new FastByteArrayOutputStream(MAX_PACKET_SIZE);
/* 379:    */  }
/* 380:    */  
/* 382:    */  public void flushDoubleOutBuffer()
/* 383:    */  {
/* 384:384 */    assert (this.byteArrayOutDoubleBuffer.position() > 0L);
/* 385:    */    FastByteArrayOutputStream localFastByteArrayOutputStream;
/* 386:386 */    (localFastByteArrayOutputStream = getNewPacket()).write(this.byteArrayOutDoubleBuffer.array, 0, (int)this.byteArrayOutDoubleBuffer.position());
/* 387:387 */    resetDoubleOutBuffer();
/* 388:388 */    synchronized (this.sendingQueue) {
/* 389:389 */      if (this.listening)
/* 390:    */      {
/* 391:391 */        this.sendingQueue.add(localFastByteArrayOutputStream);
/* 392:392 */        this.totalPackagesQueued += 1;
/* 393:393 */        this.sendingQueue.notify();
/* 394:    */      }
/* 395:    */      
/* 399:    */      return;
/* 400:    */    }
/* 401:    */  }
/* 402:    */  
/* 407:    */  public void sendPacket(FastByteArrayOutputStream paramFastByteArrayOutputStream)
/* 408:    */  {
/* 409:409 */    assert (paramFastByteArrayOutputStream.position() > 0L);
/* 410:410 */    System.currentTimeMillis();
/* 411:411 */    this.clientToServerConnection.getOutput().writeInt((int)paramFastByteArrayOutputStream.position());
/* 412:412 */    this.clientToServerConnection.getOutput().write(paramFastByteArrayOutputStream.array, 0, (int)paramFastByteArrayOutputStream.position());
/* 413:    */  }
/* 414:    */  
/* 418:    */  public int getCurrentSize()
/* 419:    */  {
/* 420:420 */    return (int)this.byteArrayOutDoubleBuffer.position();
/* 421:    */  }
/* 422:    */  
/* 423:    */  public DataInputStream getIn() {
/* 424:424 */    return this.inDoubleBuffer;
/* 425:    */  }
/* 426:    */  
/* 427:    */  public InputStream getInRaw()
/* 428:    */  {
/* 429:429 */    return this.receive.getInputStream();
/* 430:    */  }
/* 431:    */  
/* 432:    */  public Object getLock()
/* 433:    */  {
/* 434:434 */    return this.lock;
/* 435:    */  }
/* 436:    */  
/* 437:    */  public DataOutputStream getOut()
/* 438:    */  {
/* 439:439 */    return this.outDoubleBuffer;
/* 440:    */  }
/* 441:    */  
/* 442:    */  public OutputStream getOutRaw()
/* 443:    */  {
/* 444:444 */    return this.receive.getOutputStream();
/* 445:    */  }
/* 446:    */  
/* 447:    */  public Map getPendingRequests() {
/* 448:448 */    return this.pendingRequests;
/* 449:    */  }
/* 450:    */  
/* 451:    */  public StateInterface getState()
/* 452:    */  {
/* 453:453 */    return this.state;
/* 454:    */  }
/* 455:    */  
/* 456:    */  public Thread getThread() {
/* 457:457 */    return this.thread;
/* 458:    */  }
/* 459:    */  
/* 460:    */  public boolean isAlive()
/* 461:    */  {
/* 462:462 */    return this.clientToServerConnection.isAlive();
/* 463:    */  }
/* 464:    */  
/* 465:    */  public void notifyPacketReceived(short arg1, Command paramCommand)
/* 466:    */  {
/* 467:467 */    if (paramCommand.getMode() == 1) {
/* 468:468 */      Request localRequest = null;
/* 469:    */      
/* 470:470 */      synchronized (getPendingRequests())
/* 471:    */      {
/* 472:472 */        localRequest = (Request)getPendingRequests().remove(Short.valueOf(???));
/* 473:473 */        assert (localRequest != null) : ("Request #" + ??? + " not pending!!");
/* 474:474 */        assert (paramCommand.getMode() == 1) : ("COMMAND DOES NOT RETURN BUT SENT RETURN VALUE: " + paramCommand);
/* 475:    */        
/* 477:477 */        synchronized (localRequest) {
/* 478:478 */          assert (!getPendingRequests().containsKey(localRequest));
/* 479:479 */          localRequest.notify();
/* 480:    */        }
/* 481:    */        
/* 484:484 */        return;
/* 485:    */      }
/* 486:    */    }
/* 487:    */  }
/* 488:    */  
/* 495:    */  public void parseNextPacket()
/* 496:    */  {
/* 497:497 */    this.headerTmp.read(getIn());
/* 498:    */    
/* 499:499 */    Command localCommand = NetUtil.commands.getById(this.headerTmp.getCommandId());
/* 500:    */    
/* 501:501 */    assert (localCommand != null) : ("could not find " + this.headerTmp.getCommandId());
/* 502:    */    
/* 503:503 */    this.lastCommand = localCommand;
/* 504:504 */    if (this.headerTmp.getType() == 111)
/* 505:    */    {
/* 508:508 */      Object[] arrayOfObject = localCommand.readParameters(this.headerTmp, getIn());
/* 509:    */      
/* 514:514 */      localCommand.clientAnswerProcess(arrayOfObject, this.state, this.headerTmp.packetId);
/* 516:    */    }
/* 517:517 */    else if (this.headerTmp.getType() == 123) {
/* 518:518 */      localCommand.clientAnswerProcess(null, this.state, this.headerTmp.packetId);
/* 519:    */    }
/* 520:    */    else {
/* 521:521 */      System.err.println("[CRITICAL][ERROR] HEADER TYPE IS UNKNOWN");
/* 522:    */    }
/* 523:523 */    if (NetUtil.commands.getById(this.headerTmp.getCommandId()) == null) {
/* 524:524 */      throw new IOException("[CRITICAL][ERROR] Could not find command " + this.headerTmp.getCommandId());
/* 525:    */    }
/* 526:    */    
/* 529:529 */    notifyPacketReceived(this.headerTmp.packetId, NetUtil.commands.getById(this.headerTmp.getCommandId()));
/* 530:    */  }
/* 531:    */  
/* 534:    */  public void resetDoubleOutBuffer()
/* 535:    */  {
/* 536:536 */    this.outDoubleBuffer.flush();
/* 537:537 */    this.byteArrayOutDoubleBuffer.reset();
/* 538:    */  }
/* 539:    */  
/* 546:    */  public void run()
/* 547:    */  {
/* 548:548 */    setup();
/* 549:    */    try
/* 550:    */    {
/* 551:551 */      this.dataInputStream = new DataInputStream(this.receive.getInputStream());
/* 552:    */      
/* 554:554 */      while (!this.receive.isConnected()) {
/* 555:555 */        System.err.println("waiting for connection " + this.state);
/* 556:    */      }
/* 557:557 */      while ((this.listening) && (!this.receive.isClosed())) {
/* 558:558 */        getThread().setName("client Processor: " + this.state.getId());
/* 559:559 */        int i = this.dataInputStream.readInt();
/* 560:560 */        this.serverPacketSentTimestamp = this.dataInputStream.readLong();
/* 561:561 */        ((ClientState)this.state).setDebugBigChunk(i > 4000);
/* 562:562 */        if (this.state.isReadingBigChunk()) {
/* 563:563 */          System.err.println("[CLIENT] WARNING: Received big chunk: " + i + " bytes");
/* 564:    */        }
/* 565:    */        
/* 566:566 */        assert (i > 0) : " Empty update!";
/* 567:567 */        if ((i > this.receiveBuffer.length) || (i < 0)) {
/* 568:568 */          System.err.println("Exception CRITICAL: received size: " + i + " / " + this.receiveBuffer.length);
/* 569:569 */          this.receiveBuffer = new byte[i];
/* 570:    */        }
/* 571:571 */        if (i > 102400) {
/* 572:572 */          System.err.println("Exception WARNING CRITICAL: received very BIG size: " + i + " / " + this.receiveBuffer.length);
/* 573:    */        }
/* 574:574 */        this.dataInputStream.readFully(this.receiveBuffer, 0, i);
/* 575:575 */        synchronized (this.state) {
/* 576:576 */          synchronized (this.lock) {
/* 577:577 */            ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(this.receiveBuffer, 0, i);
/* 578:578 */            this.inDoubleBuffer = new DataInputStream(localByteArrayInputStream);
/* 579:    */            int j;
/* 580:580 */            if ((j = getIn().read()) >= 0)
/* 581:    */            {
/* 586:586 */              assert ((j == 42) || (j == 23)) : ("CLIENT CHECK FAILED: " + j + " " + Arrays.toString(Arrays.copyOf(this.receiveBuffer, i)) + "; available: " + localByteArrayInputStream.available() + ", happend on object 0");
/* 587:    */              
/* 591:591 */              if (j == 23) {
/* 592:592 */                boolean bool = this.pinger.checkPing(getIn());
/* 593:593 */                assert (bool);
/* 594:594 */              } else if (j == 42) {
/* 595:595 */                parseNextPacket();
/* 596:    */              } else {
/* 597:597 */                System.err.println("WARNING: FAULTY PACKET " + localByteArrayInputStream.available());
/* 598:    */              }
/* 599:    */              
/* 600:600 */              if (this.byteArrayOutDoubleBuffer.position() > 0L) {
/* 601:601 */                System.err.println("sending update: " + j);
/* 602:602 */                flushDoubleOutBuffer();
/* 603:    */              } }
/* 604:604 */            if (localByteArrayInputStream.available() > 0)
/* 605:    */            {
/* 607:607 */              System.err.println("[CLIENT] WARNING: PACKET NOT FULLY READ ( " + localByteArrayInputStream.available() + "). last check: " + j + ": synched: " + this.state.isSynchronized() + "; last command " + this.lastCommand);
/* 608:608 */              if (!this.state.isSynchronized()) {
/* 609:609 */                System.err.println("[CLIENT] OK. state got out of synch. Already resynching!");
/* 610:    */              } else {
/* 611:611 */                throw new IOException("[CRITICAL ERROR] PARSING PACKET NOT COMPLETED. BUT CLIENT WAS SYNCHRONIZED!");
/* 612:    */              }
/* 613:    */              
/* 614:    */            }
/* 615:    */          }
/* 616:    */        }
/* 617:    */      }
/* 618:    */    }
/* 619:    */    catch (EOFException localEOFException)
/* 620:    */    {
/* 621:621 */      (localObject3 = localEOFException).printStackTrace();
/* 622:622 */      if (!ClientState.loginFailed) {
/* 623:623 */        xm.a(new DisconnectException("You have been disconnected from the Server \n(either connection problem or server crash)\nActualException: " + localObject3.getClass().getSimpleName()));
/* 624:    */      } else {
/* 625:625 */        ClientState.loginFailed = false;
/* 626:    */      }
/* 627:    */    }
/* 628:    */    catch (IOException localIOException)
/* 629:    */    {
/* 630:630 */      (localObject3 = 
/* 631:    */      
/* 643:643 */        localIOException).printStackTrace();
/* 644:631 */      if ((localObject3 instanceof SocketException)) {
/* 645:632 */        xm.b(new DisconnectException("You have been disconnected from the Server \n(either connection problem or server crash)\nActualException: " + localObject3.getClass().getSimpleName()));
/* 646:    */      } else
/* 647:634 */        xm.a((Exception)localObject3);
/* 648:    */    } catch (Exception localException) {
/* 649:    */      Object localObject3;
/* 650:637 */      (localObject3 = 
/* 651:    */      
/* 656:643 */        localException).printStackTrace();
/* 657:638 */      if ((localObject3 instanceof SocketException)) {
/* 658:639 */        xm.b(new DisconnectException("You have been disconnected from the Server \n(either connection problem or server crash)\nActualException: " + localObject3.getClass().getSimpleName()));
/* 659:    */      } else {
/* 660:641 */        xm.a((Exception)localObject3);
/* 661:    */      }
/* 662:    */    }
/* 663:644 */    System.out.println("[ClientProcessor] EXIT: Input Stream closed. Terminating Client Processor");
/* 664:    */  }
/* 665:    */  
/* 666:    */  public void sendLogout() {
/* 667:648 */    this.outDoubleBuffer.writeByte(65);
/* 668:649 */    flushDoubleOutBuffer();
/* 669:    */  }
/* 670:    */  
/* 671:    */  public void setThread(Thread paramThread) {
/* 672:653 */    this.thread = paramThread;
/* 673:    */  }
/* 674:    */  
/* 675:    */  private void setup() {
/* 676:657 */    this.headerTmp = new Header();
/* 677:658 */    this.pinger = new ClientProcessor.Pinger(this, null);
/* 678:    */  }
/* 679:    */  
/* 680:    */  public void updatePing()
/* 681:    */  {
/* 682:663 */    this.pinger.execute();
/* 683:    */  }
/* 684:    */  
/* 687:    */  public long getServerPacketSentTimestamp()
/* 688:    */  {
/* 689:670 */    return this.serverPacketSentTimestamp;
/* 690:    */  }
/* 691:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */