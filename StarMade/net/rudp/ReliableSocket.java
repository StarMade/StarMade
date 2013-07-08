/*    1:     */package net.rudp;
/*    2:     */
/*    3:     */import java.io.EOFException;
/*    4:     */import java.io.IOException;
/*    5:     */import java.io.InputStream;
/*    6:     */import java.io.OutputStream;
/*    7:     */import java.io.PrintStream;
/*    8:     */import java.net.DatagramPacket;
/*    9:     */import java.net.DatagramSocket;
/*   10:     */import java.net.InetAddress;
/*   11:     */import java.net.InetSocketAddress;
/*   12:     */import java.net.Socket;
/*   13:     */import java.net.SocketAddress;
/*   14:     */import java.net.SocketException;
/*   15:     */import java.net.SocketTimeoutException;
/*   16:     */import java.net.UnknownHostException;
/*   17:     */import java.nio.channels.SocketChannel;
/*   18:     */import java.util.ArrayList;
/*   19:     */import java.util.Iterator;
/*   20:     */import java.util.Random;
/*   21:     */import net.rudp.impl.ACKSegment;
/*   22:     */import net.rudp.impl.DATSegment;
/*   23:     */import net.rudp.impl.EAKSegment;
/*   24:     */import net.rudp.impl.FINSegment;
/*   25:     */import net.rudp.impl.NULSegment;
/*   26:     */import net.rudp.impl.RSTSegment;
/*   27:     */import net.rudp.impl.SYNSegment;
/*   28:     */import net.rudp.impl.Segment;
/*   29:     */import net.rudp.impl.Timer;
/*   30:     */
/*   69:     */public class ReliableSocket
/*   70:     */  extends Socket
/*   71:     */{
/*   72:     */  protected DatagramSocket _sock;
/*   73:     */  protected SocketAddress _endpoint;
/*   74:     */  protected ReliableSocketInputStream _in;
/*   75:     */  protected ReliableSocketOutputStream _out;
/*   76:     */  
/*   77:     */  public ReliableSocket()
/*   78:     */    throws IOException
/*   79:     */  {
/*   80:  80 */    this(new ReliableSocketProfile());
/*   81:     */  }
/*   82:     */  
/*   89:     */  public ReliableSocket(ReliableSocketProfile paramReliableSocketProfile)
/*   90:     */    throws IOException
/*   91:     */  {
/*   92:  92 */    this(new DatagramSocket(), paramReliableSocketProfile);
/*   93:     */  }
/*   94:     */  
/*  113:     */  public ReliableSocket(String paramString, int paramInt)
/*  114:     */    throws UnknownHostException, IOException
/*  115:     */  {
/*  116: 116 */    this(new InetSocketAddress(paramString, paramInt), null);
/*  117:     */  }
/*  118:     */  
/*  141:     */  public ReliableSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2)
/*  142:     */    throws IOException
/*  143:     */  {
/*  144: 144 */    this(new InetSocketAddress(paramInetAddress1, paramInt1), new InetSocketAddress(paramInetAddress2, paramInt2));
/*  145:     */  }
/*  146:     */  
/*  171:     */  public ReliableSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
/*  172:     */    throws IOException
/*  173:     */  {
/*  174: 174 */    this(new InetSocketAddress(paramString, paramInt1), new InetSocketAddress(paramInetAddress, paramInt2));
/*  175:     */  }
/*  176:     */  
/*  186:     */  public ReliableSocket(InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2)
/*  187:     */    throws IOException
/*  188:     */  {
/*  189: 189 */    this(new DatagramSocket(paramInetSocketAddress2), new ReliableSocketProfile());
/*  190: 190 */    connect(paramInetSocketAddress1);
/*  191:     */  }
/*  192:     */  
/*  198:     */  public ReliableSocket(DatagramSocket paramDatagramSocket)
/*  199:     */  {
/*  200: 200 */    this(paramDatagramSocket, new ReliableSocketProfile());
/*  201:     */  }
/*  202:     */  
/*  210:     */  public ReliableSocket(DatagramSocket paramDatagramSocket, ReliableSocketProfile paramReliableSocketProfile)
/*  211:     */  {
/*  212: 212 */    if (paramDatagramSocket == null) {
/*  213: 213 */      throw new NullPointerException("sock");
/*  214:     */    }
/*  215:     */    
/*  216: 216 */    init(paramDatagramSocket, paramReliableSocketProfile);
/*  217:     */  }
/*  218:     */  
/*  225:     */  protected void init(DatagramSocket paramDatagramSocket, ReliableSocketProfile paramReliableSocketProfile)
/*  226:     */  {
/*  227: 227 */    this._sock = paramDatagramSocket;
/*  228: 228 */    this._profile = paramReliableSocketProfile;
/*  229: 229 */    this._shutdownHook = new ShutdownHook();
/*  230:     */    
/*  231: 231 */    this._sendBufferSize = ((this._profile.maxSegmentSize() - 6) * 32);
/*  232: 232 */    this._recvBufferSize = ((this._profile.maxSegmentSize() - 6) * 32);
/*  233:     */    
/*  234:     */    try
/*  235:     */    {
/*  236: 236 */      Runtime.getRuntime().addShutdownHook(this._shutdownHook);
/*  237:     */    }
/*  238:     */    catch (IllegalStateException localIllegalStateException) {
/*  239: 239 */      if (DEBUG) {
/*  240: 240 */        localIllegalStateException.printStackTrace();
/*  241:     */      }
/*  242:     */    }
/*  243:     */    
/*  244: 244 */    this._sockThread.start();
/*  245:     */  }
/*  246:     */  
/*  247:     */  public void bind(SocketAddress paramSocketAddress)
/*  248:     */    throws IOException
/*  249:     */  {
/*  250: 250 */    this._sock.bind(paramSocketAddress);
/*  251:     */  }
/*  252:     */  
/*  253:     */  public void connect(SocketAddress paramSocketAddress)
/*  254:     */    throws IOException
/*  255:     */  {
/*  256: 256 */    connect(paramSocketAddress, 0);
/*  257:     */  }
/*  258:     */  
/*  259:     */  public void connect(SocketAddress paramSocketAddress, int paramInt)
/*  260:     */    throws IOException
/*  261:     */  {
/*  262: 262 */    if (paramSocketAddress == null) {
/*  263: 263 */      throw new IllegalArgumentException("connect: The address can't be null");
/*  264:     */    }
/*  265:     */    
/*  266: 266 */    if (paramInt < 0) {
/*  267: 267 */      throw new IllegalArgumentException("connect: timeout can't be negative");
/*  268:     */    }
/*  269:     */    
/*  270: 270 */    if (isClosed()) {
/*  271: 271 */      throw new SocketException("Socket is closed");
/*  272:     */    }
/*  273:     */    
/*  274: 274 */    if (isConnected()) {
/*  275: 275 */      throw new SocketException("already connected");
/*  276:     */    }
/*  277:     */    
/*  278: 278 */    if (!(paramSocketAddress instanceof InetSocketAddress)) {
/*  279: 279 */      throw new IllegalArgumentException("Unsupported address type");
/*  280:     */    }
/*  281:     */    
/*  282: 282 */    this._endpoint = ((InetSocketAddress)paramSocketAddress);
/*  283:     */    
/*  285: 285 */    this._state = 2;
/*  286: 286 */    Random localRandom = new Random(System.currentTimeMillis());
/*  287: 287 */    SYNSegment localSYNSegment = new SYNSegment(this._counters.setSequenceNumber(localRandom.nextInt(255)), this._profile.maxOutstandingSegs(), this._profile.maxSegmentSize(), this._profile.retransmissionTimeout(), this._profile.cumulativeAckTimeout(), this._profile.nullSegmentTimeout(), this._profile.maxRetrans(), this._profile.maxCumulativeAcks(), this._profile.maxOutOfSequence(), this._profile.maxAutoReset());
/*  288:     */    
/*  298: 298 */    sendAndQueueSegment(localSYNSegment);
/*  299:     */    
/*  301: 301 */    int i = 0;
/*  302: 302 */    synchronized (this) {
/*  303: 303 */      if (!isConnected()) {
/*  304:     */        try {
/*  305: 305 */          if (paramInt == 0) {
/*  306: 306 */            wait();
/*  307:     */          }
/*  308:     */          else {
/*  309: 309 */            long l = System.currentTimeMillis();
/*  310: 310 */            wait(paramInt);
/*  311: 311 */            if (System.currentTimeMillis() - l >= paramInt) {
/*  312: 312 */              i = 1;
/*  313:     */            }
/*  314:     */          }
/*  315:     */        }
/*  316:     */        catch (InterruptedException localInterruptedException) {
/*  317: 317 */          localInterruptedException.printStackTrace();
/*  318:     */        }
/*  319:     */      }
/*  320:     */    }
/*  321:     */    
/*  322: 322 */    if (this._state == 3) {
/*  323: 323 */      return;
/*  324:     */    }
/*  325:     */    
/*  326: 326 */    synchronized (this._unackedSentQueue) {
/*  327: 327 */      this._unackedSentQueue.clear();
/*  328: 328 */      this._unackedSentQueue.notifyAll();
/*  329:     */    }
/*  330:     */    
/*  331: 331 */    this._counters.reset();
/*  332: 332 */    this._retransmissionTimer.cancel();
/*  333:     */    
/*  334: 334 */    switch (this._state) {
/*  335:     */    case 2: 
/*  336: 336 */      connectionRefused();
/*  337: 337 */      this._state = 0;
/*  338: 338 */      if (i != 0) {
/*  339: 339 */        throw new SocketTimeoutException();
/*  340:     */      }
/*  341: 341 */      throw new SocketException("Connection refused");
/*  342:     */    case 0: 
/*  343:     */    case 4: 
/*  344: 344 */      this._state = 0;
/*  345: 345 */      throw new SocketException("Socket closed");
/*  346:     */    }
/*  347:     */  }
/*  348:     */  
/*  349:     */  public SocketChannel getChannel()
/*  350:     */  {
/*  351: 351 */    return null;
/*  352:     */  }
/*  353:     */  
/*  354:     */  public InetAddress getInetAddress()
/*  355:     */  {
/*  356: 356 */    if (!isConnected()) {
/*  357: 357 */      return null;
/*  358:     */    }
/*  359:     */    
/*  360: 360 */    return ((InetSocketAddress)this._endpoint).getAddress();
/*  361:     */  }
/*  362:     */  
/*  363:     */  public int getPort()
/*  364:     */  {
/*  365: 365 */    if (!isConnected()) {
/*  366: 366 */      return 0;
/*  367:     */    }
/*  368:     */    
/*  369: 369 */    return ((InetSocketAddress)this._endpoint).getPort();
/*  370:     */  }
/*  371:     */  
/*  373:     */  public SocketAddress getRemoteSocketAddress()
/*  374:     */  {
/*  375: 375 */    if (!isConnected()) {
/*  376: 376 */      return null;
/*  377:     */    }
/*  378:     */    
/*  379: 379 */    return new InetSocketAddress(getInetAddress(), getPort());
/*  380:     */  }
/*  381:     */  
/*  382:     */  public InetAddress getLocalAddress()
/*  383:     */  {
/*  384: 384 */    return this._sock.getLocalAddress();
/*  385:     */  }
/*  386:     */  
/*  387:     */  public int getLocalPort()
/*  388:     */  {
/*  389: 389 */    return this._sock.getLocalPort();
/*  390:     */  }
/*  391:     */  
/*  392:     */  public SocketAddress getLocalSocketAddress()
/*  393:     */  {
/*  394: 394 */    return this._sock.getLocalSocketAddress();
/*  395:     */  }
/*  396:     */  
/*  397:     */  public InputStream getInputStream()
/*  398:     */    throws IOException
/*  399:     */  {
/*  400: 400 */    if (isClosed()) {
/*  401: 401 */      throw new SocketException("Socket is closed");
/*  402:     */    }
/*  403:     */    
/*  404: 404 */    if (!isConnected()) {
/*  405: 405 */      throw new SocketException("Socket is not connected");
/*  406:     */    }
/*  407:     */    
/*  408: 408 */    if (isInputShutdown()) {
/*  409: 409 */      throw new SocketException("Socket input is shutdown");
/*  410:     */    }
/*  411:     */    
/*  412: 412 */    return this._in;
/*  413:     */  }
/*  414:     */  
/*  415:     */  public OutputStream getOutputStream()
/*  416:     */    throws IOException
/*  417:     */  {
/*  418: 418 */    if (isClosed()) {
/*  419: 419 */      throw new SocketException("Socket is closed");
/*  420:     */    }
/*  421:     */    
/*  422: 422 */    if (!isConnected()) {
/*  423: 423 */      throw new SocketException("Socket is not connected");
/*  424:     */    }
/*  425:     */    
/*  426: 426 */    if (isOutputShutdown()) {
/*  427: 427 */      throw new SocketException("Socket output is shutdown");
/*  428:     */    }
/*  429:     */    
/*  430: 430 */    return this._out;
/*  431:     */  }
/*  432:     */  
/*  433:     */  public synchronized void close()
/*  434:     */    throws IOException
/*  435:     */  {
/*  436: 436 */    synchronized (this._closeLock)
/*  437:     */    {
/*  438: 438 */      if (isClosed()) {
/*  439: 439 */        return;
/*  440:     */      }
/*  441:     */      try
/*  442:     */      {
/*  443: 443 */        Runtime.getRuntime().removeShutdownHook(this._shutdownHook);
/*  444:     */      }
/*  445:     */      catch (IllegalStateException localIllegalStateException) {
/*  446: 446 */        if (DEBUG) {
/*  447: 447 */          localIllegalStateException.printStackTrace();
/*  448:     */        }
/*  449:     */      }
/*  450:     */      
/*  451: 451 */      switch (this._state) {
/*  452:     */      case 2: 
/*  453: 453 */        synchronized (this) {
/*  454: 454 */          notify();
/*  455:     */        }
/*  456: 456 */        break;
/*  457:     */      case 1: 
/*  458:     */      case 3: 
/*  459:     */      case 4: 
/*  460: 460 */        sendSegment(new FINSegment(this._counters.nextSequenceNumber()));
/*  461: 461 */        closeImpl();
/*  462: 462 */        break;
/*  463:     */      case 0: 
/*  464: 464 */        this._retransmissionTimer.destroy();
/*  465: 465 */        this._cumulativeAckTimer.destroy();
/*  466: 466 */        this._keepAliveTimer.destroy();
/*  467: 467 */        this._nullSegmentTimer.destroy();
/*  468: 468 */        this._sock.close();
/*  469:     */      }
/*  470:     */      
/*  471:     */      
/*  472: 472 */      this._closed = true;
/*  473: 473 */      this._state = 0;
/*  474:     */      
/*  475: 475 */      synchronized (this._unackedSentQueue) {
/*  476: 476 */        this._unackedSentQueue.notify();
/*  477:     */      }
/*  478:     */      
/*  479: 479 */      synchronized (this._inSeqRecvQueue) {
/*  480: 480 */        this._inSeqRecvQueue.notify();
/*  481:     */      }
/*  482:     */    }
/*  483:     */  }
/*  484:     */  
/*  485:     */  public boolean isBound()
/*  486:     */  {
/*  487: 487 */    return this._sock.isBound();
/*  488:     */  }
/*  489:     */  
/*  490:     */  public boolean isConnected()
/*  491:     */  {
/*  492: 492 */    return this._connected;
/*  493:     */  }
/*  494:     */  
/*  495:     */  public boolean isClosed()
/*  496:     */  {
/*  497: 497 */    synchronized (this._closeLock) {
/*  498: 498 */      return this._closed;
/*  499:     */    }
/*  500:     */  }
/*  501:     */  
/*  502:     */  public void setSoTimeout(int paramInt)
/*  503:     */    throws SocketException
/*  504:     */  {
/*  505: 505 */    if (paramInt < 0) {
/*  506: 506 */      throw new IllegalArgumentException("timeout < 0");
/*  507:     */    }
/*  508:     */    
/*  509: 509 */    this._timeout = paramInt;
/*  510:     */  }
/*  511:     */  
/*  512:     */  public synchronized void setSendBufferSize(int paramInt)
/*  513:     */    throws SocketException
/*  514:     */  {
/*  515: 515 */    if (paramInt <= 0) {
/*  516: 516 */      throw new IllegalArgumentException("negative receive size");
/*  517:     */    }
/*  518:     */    
/*  519: 519 */    if (isClosed()) {
/*  520: 520 */      throw new SocketException("Socket is closed");
/*  521:     */    }
/*  522:     */    
/*  523: 523 */    if (isConnected()) {
/*  524: 524 */      return;
/*  525:     */    }
/*  526:     */    
/*  527: 527 */    this._sendBufferSize = paramInt;
/*  528:     */  }
/*  529:     */  
/*  530:     */  public synchronized int getSendBufferSize()
/*  531:     */    throws SocketException
/*  532:     */  {
/*  533: 533 */    if (isClosed()) {
/*  534: 534 */      throw new SocketException("Socket is closed");
/*  535:     */    }
/*  536:     */    
/*  537: 537 */    return this._sendBufferSize;
/*  538:     */  }
/*  539:     */  
/*  540:     */  public synchronized void setReceiveBufferSize(int paramInt)
/*  541:     */    throws SocketException
/*  542:     */  {
/*  543: 543 */    if (paramInt <= 0) {
/*  544: 544 */      throw new IllegalArgumentException("negative send size");
/*  545:     */    }
/*  546:     */    
/*  547: 547 */    if (isClosed()) {
/*  548: 548 */      throw new SocketException("Socket is closed");
/*  549:     */    }
/*  550:     */    
/*  551: 551 */    if (isConnected()) {
/*  552: 552 */      return;
/*  553:     */    }
/*  554:     */    
/*  555: 555 */    this._recvBufferSize = paramInt;
/*  556:     */  }
/*  557:     */  
/*  558:     */  public synchronized int getReceiveBufferSize()
/*  559:     */    throws SocketException
/*  560:     */  {
/*  561: 561 */    if (isClosed()) {
/*  562: 562 */      throw new SocketException("Socket is closed");
/*  563:     */    }
/*  564:     */    
/*  565: 565 */    return this._recvBufferSize;
/*  566:     */  }
/*  567:     */  
/*  568:     */  public void setTcpNoDelay(boolean paramBoolean)
/*  569:     */    throws SocketException
/*  570:     */  {
/*  571: 571 */    throw new SocketException("Socket option not supported");
/*  572:     */  }
/*  573:     */  
/*  574:     */  public boolean getTcpNoDelay()
/*  575:     */  {
/*  576: 576 */    return false;
/*  577:     */  }
/*  578:     */  
/*  579:     */  public synchronized void setKeepAlive(boolean paramBoolean)
/*  580:     */    throws SocketException
/*  581:     */  {
/*  582: 582 */    if (isClosed()) {
/*  583: 583 */      throw new SocketException("Socket is closed");
/*  584:     */    }
/*  585:     */    
/*  586: 586 */    if (!(this._keepAlive ^ paramBoolean)) {
/*  587: 587 */      return;
/*  588:     */    }
/*  589:     */    
/*  590: 590 */    this._keepAlive = paramBoolean;
/*  591:     */    
/*  592: 592 */    if (isConnected()) {
/*  593: 593 */      if (this._keepAlive) {
/*  594: 594 */        this._keepAliveTimer.schedule(this._profile.nullSegmentTimeout() * 6, this._profile.nullSegmentTimeout() * 6);
/*  595:     */      }
/*  596:     */      else
/*  597:     */      {
/*  598: 598 */        this._keepAliveTimer.cancel();
/*  599:     */      }
/*  600:     */    }
/*  601:     */  }
/*  602:     */  
/*  603:     */  public synchronized boolean getKeepAlive()
/*  604:     */    throws SocketException
/*  605:     */  {
/*  606: 606 */    if (isClosed()) {
/*  607: 607 */      throw new SocketException("Socket is closed");
/*  608:     */    }
/*  609:     */    
/*  610: 610 */    return this._keepAlive;
/*  611:     */  }
/*  612:     */  
/*  613:     */  public void shutdownInput()
/*  614:     */    throws IOException
/*  615:     */  {
/*  616: 616 */    if (isClosed()) {
/*  617: 617 */      throw new SocketException("Socket is closed");
/*  618:     */    }
/*  619:     */    
/*  620: 620 */    if (!isConnected()) {
/*  621: 621 */      throw new SocketException("Socket is not connected");
/*  622:     */    }
/*  623:     */    
/*  624: 624 */    if (isInputShutdown()) {
/*  625: 625 */      throw new SocketException("Socket input is already shutdown");
/*  626:     */    }
/*  627:     */    
/*  628: 628 */    this._shutIn = true;
/*  629:     */    
/*  630: 630 */    synchronized (this._recvQueueLock) {
/*  631: 631 */      this._recvQueueLock.notify();
/*  632:     */    }
/*  633:     */  }
/*  634:     */  
/*  635:     */  public void shutdownOutput()
/*  636:     */    throws IOException
/*  637:     */  {
/*  638: 638 */    if (isClosed()) {
/*  639: 639 */      throw new SocketException("Socket is closed");
/*  640:     */    }
/*  641:     */    
/*  642: 642 */    if (!isConnected()) {
/*  643: 643 */      throw new SocketException("Socket is not connected");
/*  644:     */    }
/*  645:     */    
/*  646: 646 */    if (isOutputShutdown()) {
/*  647: 647 */      throw new SocketException("Socket output is already shutdown");
/*  648:     */    }
/*  649:     */    
/*  650: 650 */    this._shutOut = true;
/*  651:     */    
/*  652: 652 */    synchronized (this._unackedSentQueue) {
/*  653: 653 */      this._unackedSentQueue.notifyAll();
/*  654:     */    }
/*  655:     */  }
/*  656:     */  
/*  657:     */  public boolean isInputShutdown()
/*  658:     */  {
/*  659: 659 */    return this._shutIn;
/*  660:     */  }
/*  661:     */  
/*  662:     */  public boolean isOutputShutdown()
/*  663:     */  {
/*  664: 664 */    return this._shutOut;
/*  665:     */  }
/*  666:     */  
/*  677:     */  public void reset()
/*  678:     */    throws IOException
/*  679:     */  {
/*  680: 680 */    reset(null);
/*  681:     */  }
/*  682:     */  
/*  696:     */  public void reset(ReliableSocketProfile paramReliableSocketProfile)
/*  697:     */    throws IOException
/*  698:     */  {
/*  699: 699 */    if (isClosed()) {
/*  700: 700 */      throw new SocketException("Socket is closed");
/*  701:     */    }
/*  702:     */    
/*  703: 703 */    if (!isConnected()) {
/*  704: 704 */      throw new SocketException("Socket is not connected");
/*  705:     */    }
/*  706:     */    
/*  707: 707 */    synchronized (this._resetLock) {
/*  708: 708 */      this._reset = true;
/*  709:     */      
/*  710: 710 */      sendAndQueueSegment(new RSTSegment(this._counters.nextSequenceNumber()));
/*  711:     */      
/*  713: 713 */      synchronized (this._unackedSentQueue) {
/*  714: 714 */        while (!this._unackedSentQueue.isEmpty()) {
/*  715:     */          try {
/*  716: 716 */            this._unackedSentQueue.wait();
/*  717:     */          }
/*  718:     */          catch (InterruptedException localInterruptedException) {
/*  719: 719 */            localInterruptedException.printStackTrace();
/*  720:     */          }
/*  721:     */        }
/*  722:     */      }
/*  723:     */    }
/*  724:     */    
/*  725: 725 */    connectionReset();
/*  726:     */    
/*  728: 728 */    if (paramReliableSocketProfile != null) {
/*  729: 729 */      this._profile = paramReliableSocketProfile;
/*  730:     */    }
/*  731:     */    
/*  733: 733 */    this._state = 2;
/*  734: 734 */    ??? = new Random(System.currentTimeMillis());
/*  735: 735 */    ??? = new SYNSegment(this._counters.setSequenceNumber(((Random)???).nextInt(255)), this._profile.maxOutstandingSegs(), this._profile.maxSegmentSize(), this._profile.retransmissionTimeout(), this._profile.cumulativeAckTimeout(), this._profile.nullSegmentTimeout(), this._profile.maxRetrans(), this._profile.maxCumulativeAcks(), this._profile.maxOutOfSequence(), this._profile.maxAutoReset());
/*  736:     */    
/*  746: 746 */    sendAndQueueSegment((Segment)???);
/*  747:     */  }
/*  748:     */  
/*  761:     */  protected void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*  762:     */    throws IOException
/*  763:     */  {
/*  764: 764 */    if (isClosed()) {
/*  765: 765 */      throw new SocketException("Socket is closed");
/*  766:     */    }
/*  767:     */    
/*  768: 768 */    if (isOutputShutdown()) {
/*  769: 769 */      throw new IOException("Socket output is shutdown");
/*  770:     */    }
/*  771:     */    
/*  772: 772 */    if (!isConnected()) {
/*  773: 773 */      throw new SocketException("Connection reset");
/*  774:     */    }
/*  775:     */    
/*  776: 776 */    int i = 0;
/*  777: 777 */    while (i < paramInt2) {
/*  778: 778 */      synchronized (this._resetLock) {
/*  779: 779 */        while (this._reset) {
/*  780:     */          try {
/*  781: 781 */            this._resetLock.wait();
/*  782:     */          }
/*  783:     */          catch (InterruptedException localInterruptedException) {
/*  784: 784 */            localInterruptedException.printStackTrace();
/*  785:     */          }
/*  786:     */        }
/*  787:     */        
/*  788: 788 */        int j = Math.min(this._profile.maxSegmentSize() - 6, paramInt2 - i);
/*  789:     */        
/*  791: 791 */        sendAndQueueSegment(new DATSegment(this._counters.nextSequenceNumber(), this._counters.getLastInSequence(), paramArrayOfByte, paramInt1 + i, j));
/*  792:     */        
/*  793: 793 */        i += j;
/*  794:     */      }
/*  795:     */    }
/*  796:     */  }
/*  797:     */  
/*  821:     */  protected int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*  822:     */    throws IOException
/*  823:     */  {
/*  824: 824 */    int i = 0;
/*  825:     */    
/*  826: 826 */    synchronized (this._recvQueueLock) {
/*  827:     */      Iterator localIterator;
/*  828:     */      do {
/*  829: 829 */        while (this._inSeqRecvQueue.isEmpty())
/*  830:     */        {
/*  831: 831 */          if (isClosed()) {
/*  832: 832 */            throw new SocketException("Socket is closed");
/*  833:     */          }
/*  834:     */          
/*  835: 835 */          if (isInputShutdown()) {
/*  836: 836 */            throw new EOFException();
/*  837:     */          }
/*  838:     */          
/*  839: 839 */          if (!isConnected()) {
/*  840: 840 */            throw new SocketException("Connection reset");
/*  841:     */          }
/*  842:     */          try
/*  843:     */          {
/*  844: 844 */            if (this._timeout == 0) {
/*  845: 845 */              this._recvQueueLock.wait();
/*  846:     */            }
/*  847:     */            else {
/*  848: 848 */              long l = System.currentTimeMillis();
/*  849: 849 */              this._recvQueueLock.wait(this._timeout);
/*  850: 850 */              if (System.currentTimeMillis() - l >= this._timeout) {
/*  851: 851 */                throw new SocketTimeoutException();
/*  852:     */              }
/*  853:     */            }
/*  854:     */          }
/*  855:     */          catch (InterruptedException localInterruptedException) {
/*  856: 856 */            localInterruptedException.printStackTrace();
/*  857:     */          }
/*  858:     */        }
/*  859:     */        
/*  860: 860 */        for (localIterator = this._inSeqRecvQueue.iterator(); localIterator.hasNext();) {
/*  861: 861 */          Segment localSegment = (Segment)localIterator.next();
/*  862:     */          
/*  863: 863 */          if ((localSegment instanceof RSTSegment)) {
/*  864: 864 */            localIterator.remove();
/*  865: 865 */            break;
/*  866:     */          }
/*  867: 867 */          if ((localSegment instanceof FINSegment)) {
/*  868: 868 */            if (i > 0) break;
/*  869: 869 */            localIterator.remove();
/*  870: 870 */            return -1;
/*  871:     */          }
/*  872:     */          
/*  874: 874 */          if ((localSegment instanceof DATSegment)) {
/*  875: 875 */            byte[] arrayOfByte = ((DATSegment)localSegment).getData();
/*  876: 876 */            if (arrayOfByte.length + i > paramInt2) {
/*  877: 877 */              if (i > 0) break;
/*  878: 878 */              throw new IOException("insufficient buffer space");
/*  879:     */            }
/*  880:     */            
/*  883: 883 */            System.arraycopy(arrayOfByte, 0, paramArrayOfByte, paramInt1 + i, arrayOfByte.length);
/*  884: 884 */            i += arrayOfByte.length;
/*  885: 885 */            localIterator.remove();
/*  886:     */          }
/*  887:     */          
/*  888:     */        }
/*  889: 889 */      } while (i <= 0);
/*  890: 890 */      return i;
/*  891:     */    }
/*  892:     */  }
/*  893:     */  
/*  902:     */  public void addListener(ReliableSocketListener paramReliableSocketListener)
/*  903:     */  {
/*  904: 904 */    if (paramReliableSocketListener == null) {
/*  905: 905 */      throw new NullPointerException("listener");
/*  906:     */    }
/*  907:     */    
/*  908: 908 */    synchronized (this._listeners) {
/*  909: 909 */      if (!this._listeners.contains(paramReliableSocketListener)) {
/*  910: 910 */        this._listeners.add(paramReliableSocketListener);
/*  911:     */      }
/*  912:     */    }
/*  913:     */  }
/*  914:     */  
/*  921:     */  public void removeListener(ReliableSocketListener paramReliableSocketListener)
/*  922:     */  {
/*  923: 923 */    if (paramReliableSocketListener == null) {
/*  924: 924 */      throw new NullPointerException("listener");
/*  925:     */    }
/*  926:     */    
/*  927: 927 */    synchronized (this._listeners) {
/*  928: 928 */      this._listeners.remove(paramReliableSocketListener);
/*  929:     */    }
/*  930:     */  }
/*  931:     */  
/*  938:     */  public void addStateListener(ReliableSocketStateListener paramReliableSocketStateListener)
/*  939:     */  {
/*  940: 940 */    if (paramReliableSocketStateListener == null) {
/*  941: 941 */      throw new NullPointerException("stateListener");
/*  942:     */    }
/*  943:     */    
/*  944: 944 */    synchronized (this._stateListeners) {
/*  945: 945 */      if (!this._stateListeners.contains(paramReliableSocketStateListener)) {
/*  946: 946 */        this._stateListeners.add(paramReliableSocketStateListener);
/*  947:     */      }
/*  948:     */    }
/*  949:     */  }
/*  950:     */  
/*  957:     */  public void removeStateListener(ReliableSocketStateListener paramReliableSocketStateListener)
/*  958:     */  {
/*  959: 959 */    if (paramReliableSocketStateListener == null) {
/*  960: 960 */      throw new NullPointerException("stateListener");
/*  961:     */    }
/*  962:     */    
/*  963: 963 */    synchronized (this._stateListeners) {
/*  964: 964 */      this._stateListeners.remove(paramReliableSocketStateListener);
/*  965:     */    }
/*  966:     */  }
/*  967:     */  
/*  976:     */  private void sendSegment(Segment paramSegment)
/*  977:     */    throws IOException
/*  978:     */  {
/*  979: 979 */    if (((paramSegment instanceof DATSegment)) || ((paramSegment instanceof RSTSegment)) || ((paramSegment instanceof FINSegment)) || ((paramSegment instanceof NULSegment))) {
/*  980: 980 */      checkAndSetAck(paramSegment);
/*  981:     */    }
/*  982:     */    
/*  984: 984 */    if (((paramSegment instanceof DATSegment)) || ((paramSegment instanceof RSTSegment)) || ((paramSegment instanceof FINSegment))) {
/*  985: 985 */      this._nullSegmentTimer.reset();
/*  986:     */    }
/*  987:     */    
/*  988: 988 */    if (DEBUG) {
/*  989: 989 */      log("sent " + paramSegment);
/*  990:     */    }
/*  991:     */    
/*  992: 992 */    sendSegmentImpl(paramSegment);
/*  993:     */  }
/*  994:     */  
/*  999:     */  private Segment receiveSegment()
/* 1000:     */    throws IOException
/* 1001:     */  {
/* 1002:     */    Segment localSegment;
/* 1003:     */    
/* 1007:1007 */    if ((localSegment = receiveSegmentImpl()) != null)
/* 1008:     */    {
/* 1009:1009 */      if (DEBUG) {
/* 1010:1010 */        log("recv " + localSegment);
/* 1011:     */      }
/* 1012:     */      
/* 1013:1013 */      if (((localSegment instanceof DATSegment)) || ((localSegment instanceof NULSegment)) || ((localSegment instanceof RSTSegment)) || ((localSegment instanceof FINSegment)) || ((localSegment instanceof SYNSegment)))
/* 1014:     */      {
/* 1016:1016 */        this._counters.incCumulativeAckCounter();
/* 1017:     */      }
/* 1018:     */      
/* 1019:1019 */      if (this._keepAlive) {
/* 1020:1020 */        this._keepAliveTimer.reset();
/* 1021:     */      }
/* 1022:     */    }
/* 1023:     */    
/* 1024:1024 */    return localSegment;
/* 1025:     */  }
/* 1026:     */  
/* 1034:     */  private void sendAndQueueSegment(Segment paramSegment)
/* 1035:     */    throws IOException
/* 1036:     */  {
/* 1037:1037 */    synchronized (this._unackedSentQueue) {
/* 1038:1038 */      while ((this._unackedSentQueue.size() >= this._sendQueueSize) || (this._counters.getOutstandingSegsCounter() > this._profile.maxOutstandingSegs())) {
/* 1039:     */        try
/* 1040:     */        {
/* 1041:1041 */          this._unackedSentQueue.wait();
/* 1042:     */        }
/* 1043:     */        catch (InterruptedException localInterruptedException) {
/* 1044:1044 */          localInterruptedException.printStackTrace();
/* 1045:     */        }
/* 1046:     */      }
/* 1047:     */      
/* 1048:1048 */      this._counters.incOutstandingSegsCounter();
/* 1049:1049 */      this._unackedSentQueue.add(paramSegment);
/* 1050:     */    }
/* 1051:     */    
/* 1052:1052 */    if (this._closed) {
/* 1053:1053 */      throw new SocketException("Socket is closed");
/* 1054:     */    }
/* 1055:     */    
/* 1057:1057 */    if ((!(paramSegment instanceof EAKSegment)) && (!(paramSegment instanceof ACKSegment))) {
/* 1058:1058 */      synchronized (this._retransmissionTimer) {
/* 1059:1059 */        if (this._retransmissionTimer.isIdle()) {
/* 1060:1060 */          this._retransmissionTimer.schedule(this._profile.retransmissionTimeout(), this._profile.retransmissionTimeout());
/* 1061:     */        }
/* 1062:     */      }
/* 1063:     */    }
/* 1064:     */    
/* 1066:1066 */    sendSegment(paramSegment);
/* 1067:     */    
/* 1068:1068 */    if ((paramSegment instanceof DATSegment)) {
/* 1069:1069 */      synchronized (this._listeners) {
/* 1070:1070 */        Iterator localIterator = this._listeners.iterator();
/* 1071:1071 */        while (localIterator.hasNext()) {
/* 1072:1072 */          ReliableSocketListener localReliableSocketListener = (ReliableSocketListener)localIterator.next();
/* 1073:1073 */          localReliableSocketListener.packetSent();
/* 1074:     */        }
/* 1075:     */      }
/* 1076:     */    }
/* 1077:     */  }
/* 1078:     */  
/* 1086:     */  private void retransmitSegment(Segment paramSegment)
/* 1087:     */    throws IOException
/* 1088:     */  {
/* 1089:1089 */    if (this._profile.maxRetrans() > 0) {
/* 1090:1090 */      paramSegment.setRetxCounter(paramSegment.getRetxCounter() + 1);
/* 1091:     */    }
/* 1092:     */    
/* 1093:1093 */    if ((this._profile.maxRetrans() != 0) && (paramSegment.getRetxCounter() > this._profile.maxRetrans())) {
/* 1094:1094 */      connectionFailure();
/* 1095:1095 */      return;
/* 1096:     */    }
/* 1097:     */    
/* 1098:1098 */    sendSegment(paramSegment);
/* 1099:     */    
/* 1100:1100 */    if ((paramSegment instanceof DATSegment)) {
/* 1101:1101 */      synchronized (this._listeners) {
/* 1102:1102 */        Iterator localIterator = this._listeners.iterator();
/* 1103:1103 */        while (localIterator.hasNext()) {
/* 1104:1104 */          ReliableSocketListener localReliableSocketListener = (ReliableSocketListener)localIterator.next();
/* 1105:1105 */          localReliableSocketListener.packetRetransmitted();
/* 1106:     */        }
/* 1107:     */      }
/* 1108:     */    }
/* 1109:     */  }
/* 1110:     */  
/* 1115:     */  private void connectionOpened()
/* 1116:     */  {
/* 1117:1117 */    if (isConnected())
/* 1118:     */    {
/* 1119:1119 */      this._nullSegmentTimer.cancel();
/* 1120:     */      
/* 1121:1121 */      if (this._keepAlive) {
/* 1122:1122 */        this._keepAliveTimer.cancel();
/* 1123:     */      }
/* 1124:     */      
/* 1125:1125 */      synchronized (this._resetLock) {
/* 1126:1126 */        this._reset = false;
/* 1127:1127 */        this._resetLock.notify();
/* 1128:     */      }
/* 1129:     */    }
/* 1130:     */    else {
/* 1131:1131 */      synchronized (this) {
/* 1132:     */        try {
/* 1133:1133 */          this._in = new ReliableSocketInputStream(this);
/* 1134:1134 */          this._out = new ReliableSocketOutputStream(this);
/* 1135:1135 */          this._connected = true;
/* 1136:1136 */          this._state = 3;
/* 1137:     */        }
/* 1138:     */        catch (IOException localIOException) {
/* 1139:1139 */          localIOException.printStackTrace();
/* 1140:     */        }
/* 1141:     */        
/* 1142:1142 */        notify();
/* 1143:     */      }
/* 1144:     */      
/* 1145:1145 */      synchronized (this._stateListeners) {
/* 1146:1146 */        Iterator localIterator = this._stateListeners.iterator();
/* 1147:1147 */        while (localIterator.hasNext()) {
/* 1148:1148 */          ReliableSocketStateListener localReliableSocketStateListener = (ReliableSocketStateListener)localIterator.next();
/* 1149:1149 */          localReliableSocketStateListener.connectionOpened(this);
/* 1150:     */        }
/* 1151:     */      }
/* 1152:     */    }
/* 1153:     */    
/* 1154:1154 */    this._nullSegmentTimer.schedule(0L, this._profile.nullSegmentTimeout());
/* 1155:     */    
/* 1156:1156 */    if (this._keepAlive) {
/* 1157:1157 */      this._keepAliveTimer.schedule(this._profile.nullSegmentTimeout() * 6, this._profile.nullSegmentTimeout() * 6);
/* 1158:     */    }
/* 1159:     */  }
/* 1160:     */  
/* 1166:     */  private void connectionRefused()
/* 1167:     */  {
/* 1168:1168 */    synchronized (this._stateListeners) {
/* 1169:1169 */      Iterator localIterator = this._stateListeners.iterator();
/* 1170:1170 */      while (localIterator.hasNext()) {
/* 1171:1171 */        ReliableSocketStateListener localReliableSocketStateListener = (ReliableSocketStateListener)localIterator.next();
/* 1172:1172 */        localReliableSocketStateListener.connectionRefused(this);
/* 1173:     */      }
/* 1174:     */    }
/* 1175:     */  }
/* 1176:     */  
/* 1181:     */  private void connectionClosed()
/* 1182:     */  {
/* 1183:1183 */    synchronized (this._stateListeners) {
/* 1184:1184 */      Iterator localIterator = this._stateListeners.iterator();
/* 1185:1185 */      while (localIterator.hasNext()) {
/* 1186:1186 */        ReliableSocketStateListener localReliableSocketStateListener = (ReliableSocketStateListener)localIterator.next();
/* 1187:1187 */        localReliableSocketStateListener.connectionClosed(this);
/* 1188:     */      }
/* 1189:     */    }
/* 1190:     */  }
/* 1191:     */  
/* 1196:     */  private void connectionFailure()
/* 1197:     */  {
/* 1198:1198 */    synchronized (this._closeLock)
/* 1199:     */    {
/* 1200:1200 */      if (isClosed()) {
/* 1201:1201 */        return;
/* 1202:     */      }
/* 1203:     */      
/* 1204:1204 */      switch (this._state) {
/* 1205:     */      case 2: 
/* 1206:1206 */        synchronized (this) {
/* 1207:1207 */          notify();
/* 1208:     */        }
/* 1209:1209 */        break;
/* 1210:     */      case 1: 
/* 1211:     */      case 3: 
/* 1212:     */      case 4: 
/* 1213:1213 */        this._connected = false;
/* 1214:1214 */        synchronized (this._unackedSentQueue) {
/* 1215:1215 */          this._unackedSentQueue.notifyAll();
/* 1216:     */        }
/* 1217:     */        
/* 1218:1218 */        synchronized (this._recvQueueLock) {
/* 1219:1219 */          this._recvQueueLock.notify();
/* 1220:     */        }
/* 1221:     */        
/* 1222:1222 */        closeImpl();
/* 1223:     */      }
/* 1224:     */      
/* 1225:     */      
/* 1226:1226 */      this._state = 0;
/* 1227:1227 */      this._closed = true;
/* 1228:     */    }
/* 1229:     */    
/* 1230:1230 */    synchronized (this._stateListeners) {
/* 1231:1231 */      ??? = this._stateListeners.iterator();
/* 1232:1232 */      while (((Iterator)???).hasNext()) {
/* 1233:1233 */        ReliableSocketStateListener localReliableSocketStateListener = (ReliableSocketStateListener)((Iterator)???).next();
/* 1234:1234 */        localReliableSocketStateListener.connectionFailure(this);
/* 1235:     */      }
/* 1236:     */    }
/* 1237:     */  }
/* 1238:     */  
/* 1243:     */  private void connectionReset()
/* 1244:     */  {
/* 1245:1245 */    synchronized (this._stateListeners) {
/* 1246:1246 */      Iterator localIterator = this._stateListeners.iterator();
/* 1247:1247 */      while (localIterator.hasNext()) {
/* 1248:1248 */        ReliableSocketStateListener localReliableSocketStateListener = (ReliableSocketStateListener)localIterator.next();
/* 1249:1249 */        localReliableSocketStateListener.connectionReset(this);
/* 1250:     */      }
/* 1251:     */    }
/* 1252:     */  }
/* 1253:     */  
/* 1267:     */  private void handleSYNSegment(SYNSegment paramSYNSegment)
/* 1268:     */  {
/* 1269:     */    try
/* 1270:     */    {
/* 1271:1271 */      switch (this._state) {
/* 1272:     */      case 0: 
/* 1273:1273 */        this._counters.setLastInSequence(paramSYNSegment.seq());
/* 1274:1274 */        this._state = 1;
/* 1275:     */        
/* 1276:1276 */        Random localRandom = new Random(System.currentTimeMillis());
/* 1277:1277 */        this._profile = new ReliableSocketProfile(this._sendQueueSize, this._recvQueueSize, paramSYNSegment.getMaxSegmentSize(), paramSYNSegment.getMaxOutstandingSegments(), paramSYNSegment.getMaxRetransmissions(), paramSYNSegment.getMaxCumulativeAcks(), paramSYNSegment.getMaxOutOfSequence(), paramSYNSegment.getMaxAutoReset(), paramSYNSegment.getNulSegmentTimeout(), paramSYNSegment.getRetransmissionTimeout(), paramSYNSegment.getCummulativeAckTimeout());
/* 1278:     */        
/* 1290:1290 */        SYNSegment localSYNSegment = new SYNSegment(this._counters.setSequenceNumber(localRandom.nextInt(255)), this._profile.maxOutstandingSegs(), this._profile.maxSegmentSize(), this._profile.retransmissionTimeout(), this._profile.cumulativeAckTimeout(), this._profile.nullSegmentTimeout(), this._profile.maxRetrans(), this._profile.maxCumulativeAcks(), this._profile.maxOutOfSequence(), this._profile.maxAutoReset());
/* 1291:     */        
/* 1301:1301 */        localSYNSegment.setAck(paramSYNSegment.seq());
/* 1302:1302 */        sendAndQueueSegment(localSYNSegment);
/* 1303:1303 */        break;
/* 1304:     */      case 2: 
/* 1305:1305 */        this._counters.setLastInSequence(paramSYNSegment.seq());
/* 1306:1306 */        this._state = 3;
/* 1307:     */        
/* 1311:1311 */        sendAck();
/* 1312:1312 */        connectionOpened();
/* 1313:     */      }
/* 1314:     */    }
/* 1315:     */    catch (IOException localIOException)
/* 1316:     */    {
/* 1317:1317 */      localIOException.printStackTrace();
/* 1318:     */    }
/* 1319:     */  }
/* 1320:     */  
/* 1335:     */  private void handleEAKSegment(EAKSegment paramEAKSegment)
/* 1336:     */  {
/* 1337:1337 */    int[] arrayOfInt = paramEAKSegment.getACKs();
/* 1338:     */    
/* 1339:1339 */    int i = paramEAKSegment.getAck();
/* 1340:1340 */    int j = arrayOfInt[(arrayOfInt.length - 1)];
/* 1341:     */    
/* 1342:1342 */    synchronized (this._unackedSentQueue)
/* 1343:     */    {
/* 1345:1345 */      for (Iterator localIterator = this._unackedSentQueue.iterator(); localIterator.hasNext();) {
/* 1346:1346 */        localSegment = (Segment)localIterator.next();
/* 1347:1347 */        if (compareSequenceNumbers(localSegment.seq(), i) <= 0) {
/* 1348:1348 */          localIterator.remove();
/* 1349:     */        }
/* 1350:     */        else
/* 1351:     */        {
/* 1352:1352 */          for (int k = 0; k < arrayOfInt.length; k++) {
/* 1353:1353 */            if (compareSequenceNumbers(localSegment.seq(), arrayOfInt[k]) == 0) {
/* 1354:1354 */              localIterator.remove();
/* 1355:1355 */              break;
/* 1356:     */            }
/* 1357:     */          }
/* 1358:     */        }
/* 1359:     */      }
/* 1360:     */      Segment localSegment;
/* 1361:1361 */      localIterator = this._unackedSentQueue.iterator();
/* 1362:1362 */      while (localIterator.hasNext()) {
/* 1363:1363 */        localSegment = (Segment)localIterator.next();
/* 1364:1364 */        if ((compareSequenceNumbers(i, localSegment.seq()) < 0) && (compareSequenceNumbers(j, localSegment.seq()) > 0))
/* 1365:     */        {
/* 1366:     */          try
/* 1367:     */          {
/* 1368:1368 */            retransmitSegment(localSegment);
/* 1369:     */          }
/* 1370:     */          catch (IOException localIOException) {
/* 1371:1371 */            localIOException.printStackTrace();
/* 1372:     */          }
/* 1373:     */        }
/* 1374:     */      }
/* 1375:     */      
/* 1376:1376 */      this._unackedSentQueue.notifyAll();
/* 1377:     */    }
/* 1378:     */  }
/* 1379:     */  
/* 1390:     */  private void handleSegment(Segment paramSegment)
/* 1391:     */  {
/* 1392:1392 */    if ((paramSegment instanceof RSTSegment)) {
/* 1393:1393 */      synchronized (this._resetLock) {
/* 1394:1394 */        this._reset = true;
/* 1395:     */      }
/* 1396:     */      
/* 1397:1397 */      connectionReset();
/* 1398:     */    }
/* 1399:     */    
/* 1404:1404 */    if ((paramSegment instanceof FINSegment)) {
/* 1405:1405 */      switch (this._state) {
/* 1406:     */      case 2: 
/* 1407:1407 */        synchronized (this) {
/* 1408:1408 */          notify();
/* 1409:     */        }
/* 1410:1410 */        break;
/* 1411:     */      case 0: 
/* 1412:1412 */        break;
/* 1413:     */      default: 
/* 1414:1414 */        this._state = 4;
/* 1415:     */      }
/* 1416:     */      
/* 1417:     */    }
/* 1418:1418 */    int i = 0;
/* 1419:1419 */    synchronized (this._recvQueueLock)
/* 1420:     */    {
/* 1421:1421 */      if (compareSequenceNumbers(paramSegment.seq(), this._counters.getLastInSequence()) > 0)
/* 1422:     */      {
/* 1423:     */        Object localObject3;
/* 1424:1424 */        if (compareSequenceNumbers(paramSegment.seq(), nextSequenceNumber(this._counters.getLastInSequence())) == 0) {
/* 1425:1425 */          i = 1;
/* 1426:1426 */          if ((this._inSeqRecvQueue.size() == 0) || (this._inSeqRecvQueue.size() + this._outSeqRecvQueue.size() < this._recvQueueSize))
/* 1427:     */          {
/* 1428:1428 */            this._counters.setLastInSequence(paramSegment.seq());
/* 1429:1429 */            if (((paramSegment instanceof DATSegment)) || ((paramSegment instanceof RSTSegment)) || ((paramSegment instanceof FINSegment))) {
/* 1430:1430 */              this._inSeqRecvQueue.add(paramSegment);
/* 1431:     */            }
/* 1432:     */            
/* 1433:1433 */            if ((paramSegment instanceof DATSegment)) {
/* 1434:1434 */              synchronized (this._listeners) {
/* 1435:1435 */                Iterator localIterator = this._listeners.iterator();
/* 1436:1436 */                while (localIterator.hasNext()) {
/* 1437:1437 */                  localObject3 = (ReliableSocketListener)localIterator.next();
/* 1438:1438 */                  ((ReliableSocketListener)localObject3).packetReceivedInOrder();
/* 1439:     */                }
/* 1440:     */              }
/* 1441:     */            }
/* 1442:     */            
/* 1443:1443 */            checkRecvQueues();
/* 1445:     */          }
/* 1446:     */          
/* 1448:     */        }
/* 1449:1449 */        else if (this._inSeqRecvQueue.size() + this._outSeqRecvQueue.size() < this._recvQueueSize)
/* 1450:     */        {
/* 1451:1451 */          int j = 0;
/* 1452:1452 */          for (int k = 0; (k < this._outSeqRecvQueue.size()) && (j == 0); k++) {
/* 1453:1453 */            localObject3 = (Segment)this._outSeqRecvQueue.get(k);
/* 1454:1454 */            int m = compareSequenceNumbers(paramSegment.seq(), ((Segment)localObject3).seq());
/* 1455:1455 */            if (m == 0)
/* 1456:     */            {
/* 1457:1457 */              j = 1;
/* 1458:     */            }
/* 1459:1459 */            else if (m < 0) {
/* 1460:1460 */              this._outSeqRecvQueue.add(k, paramSegment);
/* 1461:1461 */              j = 1;
/* 1462:     */            }
/* 1463:     */          }
/* 1464:     */          
/* 1465:1465 */          if (j == 0) {
/* 1466:1466 */            this._outSeqRecvQueue.add(paramSegment);
/* 1467:     */          }
/* 1468:     */          
/* 1469:1469 */          this._counters.incOutOfSequenceCounter();
/* 1470:     */          
/* 1471:1471 */          if ((paramSegment instanceof DATSegment)) {
/* 1472:1472 */            synchronized (this._listeners) {
/* 1473:1473 */              localObject3 = this._listeners.iterator();
/* 1474:1474 */              while (((Iterator)localObject3).hasNext()) {
/* 1475:1475 */                ReliableSocketListener localReliableSocketListener = (ReliableSocketListener)((Iterator)localObject3).next();
/* 1476:1476 */                localReliableSocketListener.packetReceivedOutOfOrder();
/* 1477:     */              }
/* 1478:     */            }
/* 1479:     */          }
/* 1480:     */        }
/* 1481:     */      }
/* 1482:1482 */      if ((i != 0) && (((paramSegment instanceof RSTSegment)) || ((paramSegment instanceof NULSegment)) || ((paramSegment instanceof FINSegment))))
/* 1483:     */      {
/* 1485:1485 */        sendAck();
/* 1486:     */      }
/* 1487:1487 */      else if ((this._counters.getOutOfSequenceCounter() > 0) && ((this._profile.maxOutOfSequence() == 0) || (this._counters.getOutOfSequenceCounter() > this._profile.maxOutOfSequence())))
/* 1488:     */      {
/* 1489:1489 */        sendExtendedAck();
/* 1490:     */      }
/* 1491:1491 */      else if ((this._counters.getCumulativeAckCounter() > 0) && ((this._profile.maxCumulativeAcks() == 0) || (this._counters.getCumulativeAckCounter() > this._profile.maxCumulativeAcks())))
/* 1492:     */      {
/* 1493:1493 */        sendSingleAck();
/* 1494:     */      }
/* 1495:     */      else {
/* 1496:1496 */        synchronized (this._cumulativeAckTimer) {
/* 1497:1497 */          if (this._cumulativeAckTimer.isIdle()) {
/* 1498:1498 */            this._cumulativeAckTimer.schedule(this._profile.cumulativeAckTimeout());
/* 1499:     */          }
/* 1500:     */        }
/* 1501:     */      }
/* 1502:     */    }
/* 1503:     */  }
/* 1504:     */  
/* 1510:     */  private void sendAck()
/* 1511:     */  {
/* 1512:1512 */    synchronized (this._recvQueueLock) {
/* 1513:1513 */      if (!this._outSeqRecvQueue.isEmpty()) {
/* 1514:1514 */        sendExtendedAck();
/* 1515:1515 */        return;
/* 1516:     */      }
/* 1517:     */      
/* 1518:1518 */      sendSingleAck();
/* 1519:     */    }
/* 1520:     */  }
/* 1521:     */  
/* 1526:     */  private void sendExtendedAck()
/* 1527:     */  {
/* 1528:1528 */    synchronized (this._recvQueueLock)
/* 1529:     */    {
/* 1530:1530 */      if (this._outSeqRecvQueue.isEmpty()) {
/* 1531:1531 */        return;
/* 1532:     */      }
/* 1533:     */      
/* 1534:1534 */      this._counters.getAndResetCumulativeAckCounter();
/* 1535:1535 */      this._counters.getAndResetOutOfSequenceCounter();
/* 1536:     */      
/* 1538:1538 */      int[] arrayOfInt = new int[this._outSeqRecvQueue.size()];
/* 1539:1539 */      for (int i = 0; i < arrayOfInt.length; i++) {
/* 1540:1540 */        Segment localSegment = (Segment)this._outSeqRecvQueue.get(i);
/* 1541:1541 */        arrayOfInt[i] = localSegment.seq();
/* 1542:     */      }
/* 1543:     */      try
/* 1544:     */      {
/* 1545:1545 */        i = this._counters.getLastInSequence();
/* 1546:1546 */        sendSegment(new EAKSegment(nextSequenceNumber(i), i, arrayOfInt));
/* 1547:     */      }
/* 1548:     */      catch (IOException localIOException)
/* 1549:     */      {
/* 1550:1550 */        localIOException.printStackTrace();
/* 1551:     */      }
/* 1552:     */    }
/* 1553:     */  }
/* 1554:     */  
/* 1560:     */  private void sendSingleAck()
/* 1561:     */  {
/* 1562:1562 */    if (this._counters.getAndResetCumulativeAckCounter() == 0) {
/* 1563:1563 */      return;
/* 1564:     */    }
/* 1565:     */    try
/* 1566:     */    {
/* 1567:1567 */      int i = this._counters.getLastInSequence();
/* 1568:1568 */      sendSegment(new ACKSegment(nextSequenceNumber(i), i));
/* 1569:     */    }
/* 1570:     */    catch (IOException localIOException) {
/* 1571:1571 */      localIOException.printStackTrace();
/* 1572:     */    }
/* 1573:     */  }
/* 1574:     */  
/* 1581:     */  private void checkAndSetAck(Segment paramSegment)
/* 1582:     */  {
/* 1583:1583 */    if (this._counters.getAndResetCumulativeAckCounter() == 0) {
/* 1584:1584 */      return;
/* 1585:     */    }
/* 1586:     */    
/* 1587:1587 */    paramSegment.setAck(this._counters.getLastInSequence());
/* 1588:     */  }
/* 1589:     */  
/* 1595:     */  private void checkAndGetAck(Segment paramSegment)
/* 1596:     */  {
/* 1597:1597 */    int i = paramSegment.getAck();
/* 1598:     */    
/* 1599:1599 */    if (i < 0) {
/* 1600:1600 */      return;
/* 1601:     */    }
/* 1602:     */    
/* 1603:1603 */    this._counters.getAndResetOutstandingSegsCounter();
/* 1604:     */    
/* 1605:1605 */    if (this._state == 1) {
/* 1606:1606 */      this._state = 3;
/* 1607:1607 */      connectionOpened();
/* 1608:     */    }
/* 1609:     */    
/* 1610:1610 */    synchronized (this._unackedSentQueue) {
/* 1611:1611 */      Iterator localIterator = this._unackedSentQueue.iterator();
/* 1612:1612 */      while (localIterator.hasNext()) {
/* 1613:1613 */        Segment localSegment = (Segment)localIterator.next();
/* 1614:1614 */        if (compareSequenceNumbers(localSegment.seq(), i) <= 0) {
/* 1615:1615 */          localIterator.remove();
/* 1616:     */        }
/* 1617:     */      }
/* 1618:     */      
/* 1619:1619 */      if (this._unackedSentQueue.isEmpty()) {
/* 1620:1620 */        this._retransmissionTimer.cancel();
/* 1621:     */      }
/* 1622:     */      
/* 1623:1623 */      this._unackedSentQueue.notifyAll();
/* 1624:     */    }
/* 1625:     */  }
/* 1626:     */  
/* 1631:     */  private void checkRecvQueues()
/* 1632:     */  {
/* 1633:1633 */    synchronized (this._recvQueueLock) {
/* 1634:1634 */      Iterator localIterator = this._outSeqRecvQueue.iterator();
/* 1635:1635 */      while (localIterator.hasNext()) {
/* 1636:1636 */        Segment localSegment = (Segment)localIterator.next();
/* 1637:1637 */        if (compareSequenceNumbers(localSegment.seq(), nextSequenceNumber(this._counters.getLastInSequence())) == 0) {
/* 1638:1638 */          this._counters.setLastInSequence(localSegment.seq());
/* 1639:1639 */          if (((localSegment instanceof DATSegment)) || ((localSegment instanceof RSTSegment)) || ((localSegment instanceof FINSegment))) {
/* 1640:1640 */            this._inSeqRecvQueue.add(localSegment);
/* 1641:     */          }
/* 1642:1642 */          localIterator.remove();
/* 1643:     */        }
/* 1644:     */      }
/* 1645:     */      
/* 1646:1646 */      this._recvQueueLock.notify();
/* 1647:     */    }
/* 1648:     */  }
/* 1649:     */  
/* 1656:     */  protected void sendSegmentImpl(Segment paramSegment)
/* 1657:     */    throws IOException
/* 1658:     */  {
/* 1659:     */    try
/* 1660:     */    {
/* 1661:1661 */      DatagramPacket localDatagramPacket = new DatagramPacket(paramSegment.getBytes(), paramSegment.length(), this._endpoint);
/* 1662:     */      
/* 1663:1663 */      this._sock.send(localDatagramPacket);
/* 1664:     */    }
/* 1665:     */    catch (IOException localIOException) {
/* 1666:1666 */      if (!isClosed()) {
/* 1667:1667 */        localIOException.printStackTrace();
/* 1668:     */      }
/* 1669:     */    }
/* 1670:     */  }
/* 1671:     */  
/* 1678:     */  protected Segment receiveSegmentImpl()
/* 1679:     */    throws IOException
/* 1680:     */  {
/* 1681:     */    try
/* 1682:     */    {
/* 1683:1683 */      DatagramPacket localDatagramPacket = new DatagramPacket(this._recvbuffer, this._recvbuffer.length);
/* 1684:1684 */      this._sock.receive(localDatagramPacket);
/* 1685:1685 */      return Segment.parse(localDatagramPacket.getData(), 0, localDatagramPacket.getLength());
/* 1686:     */    }
/* 1687:     */    catch (IOException localIOException) {
/* 1688:1688 */      if (!isClosed()) {
/* 1689:1689 */        localIOException.printStackTrace();
/* 1690:     */      }
/* 1691:     */    }
/* 1692:     */    
/* 1693:1693 */    return null;
/* 1694:     */  }
/* 1695:     */  
/* 1699:     */  protected void closeSocket()
/* 1700:     */  {
/* 1701:1701 */    this._sock.close();
/* 1702:     */  }
/* 1703:     */  
/* 1707:     */  protected void closeImpl()
/* 1708:     */  {
/* 1709:1709 */    this._nullSegmentTimer.cancel();
/* 1710:1710 */    this._keepAliveTimer.cancel();
/* 1711:1711 */    this._state = 4;
/* 1712:     */    
/* 1713:1713 */    Thread local1 = new Thread()
/* 1714:     */    {
/* 1715:     */      public void run() {
/* 1716:1716 */        ReliableSocket.this._keepAliveTimer.destroy();
/* 1717:1717 */        ReliableSocket.this._nullSegmentTimer.destroy();
/* 1718:     */        try
/* 1719:     */        {
/* 1720:1720 */          Thread.sleep(ReliableSocket.this._profile.nullSegmentTimeout() * 2);
/* 1721:     */        }
/* 1722:     */        catch (InterruptedException localInterruptedException) {
/* 1723:1723 */          localInterruptedException.printStackTrace();
/* 1724:     */        }
/* 1725:     */        
/* 1726:1726 */        ReliableSocket.this._retransmissionTimer.destroy();
/* 1727:1727 */        ReliableSocket.this._cumulativeAckTimer.destroy();
/* 1728:     */        
/* 1729:1729 */        ReliableSocket.this.closeSocket();
/* 1730:1730 */        ReliableSocket.this.connectionClosed();
/* 1731:     */      }
/* 1732:1732 */    };
/* 1733:1733 */    local1.setName("ReliableSocket-Closing");
/* 1734:1734 */    local1.setDaemon(true);
/* 1735:1735 */    local1.start();
/* 1736:     */  }
/* 1737:     */  
/* 1741:     */  protected void log(String paramString)
/* 1742:     */  {
/* 1743:1743 */    System.out.println(getLocalPort() + ": " + paramString);
/* 1744:     */  }
/* 1745:     */  
/* 1751:     */  private static int nextSequenceNumber(int paramInt)
/* 1752:     */  {
/* 1753:1753 */    return (paramInt + 1) % 255;
/* 1754:     */  }
/* 1755:     */  
/* 1763:     */  private int compareSequenceNumbers(int paramInt1, int paramInt2)
/* 1764:     */  {
/* 1765:1765 */    if (paramInt1 == paramInt2) {
/* 1766:1766 */      return 0;
/* 1767:     */    }
/* 1768:1768 */    if (((paramInt1 < paramInt2) && (paramInt2 - paramInt1 > 127)) || ((paramInt1 > paramInt2) && (paramInt1 - paramInt2 < 127)))
/* 1769:     */    {
/* 1770:1770 */      return 1;
/* 1771:     */    }
/* 1772:     */    
/* 1773:1773 */    return -1;
/* 1774:     */  }
/* 1775:     */  
/* 1782:1782 */  private byte[] _recvbuffer = new byte[65535];
/* 1783:     */  
/* 1784:1784 */  private boolean _closed = false;
/* 1785:1785 */  private boolean _connected = false;
/* 1786:1786 */  private boolean _reset = false;
/* 1787:1787 */  private boolean _keepAlive = true;
/* 1788:1788 */  private int _state = 0;
/* 1789:1789 */  private int _timeout = 0;
/* 1790:1790 */  private boolean _shutIn = false;
/* 1791:1791 */  private boolean _shutOut = false;
/* 1792:     */  
/* 1793:1793 */  private Object _closeLock = new Object();
/* 1794:1794 */  private Object _resetLock = new Object();
/* 1795:     */  
/* 1796:1796 */  private ArrayList _listeners = new ArrayList();
/* 1797:1797 */  private ArrayList _stateListeners = new ArrayList();
/* 1798:     */  
/* 1800:     */  private ShutdownHook _shutdownHook;
/* 1801:     */  
/* 1802:1802 */  private ReliableSocketProfile _profile = new ReliableSocketProfile();
/* 1803:     */  
/* 1804:1804 */  private ArrayList _unackedSentQueue = new ArrayList();
/* 1805:1805 */  private ArrayList _outSeqRecvQueue = new ArrayList();
/* 1806:1806 */  private ArrayList _inSeqRecvQueue = new ArrayList();
/* 1807:     */  
/* 1808:1808 */  private Object _recvQueueLock = new Object();
/* 1809:1809 */  private Counters _counters = new Counters();
/* 1810:     */  
/* 1811:1811 */  private Thread _sockThread = new ReliableSocketThread();
/* 1812:     */  
/* 1813:1813 */  private int _sendQueueSize = 32;
/* 1814:1814 */  private int _recvQueueSize = 32;
/* 1815:     */  
/* 1818:     */  private int _sendBufferSize;
/* 1819:     */  
/* 1821:     */  private int _recvBufferSize;
/* 1822:     */  
/* 1824:1824 */  private Timer _nullSegmentTimer = new Timer("ReliableSocket-NullSegmentTimer", new NullSegmentTimerTask(null));
/* 1825:     */  
/* 1836:1836 */  private Timer _retransmissionTimer = new Timer("ReliableSocket-RetransmissionTimer", new RetransmissionTimerTask(null));
/* 1837:     */  
/* 1850:1850 */  private Timer _cumulativeAckTimer = new Timer("ReliableSocket-CumulativeAckTimer", new CumulativeAckTimerTask(null));
/* 1851:     */  
/* 1856:1856 */  private Timer _keepAliveTimer = new Timer("ReliableSocket-KeepAliveTimer", new KeepAliveTimerTask(null));
/* 1857:     */  
/* 1858:     */  private static final int MAX_SEQUENCE_NUMBER = 255;
/* 1859:     */  
/* 1860:     */  private static final int CLOSED = 0;
/* 1861:     */  
/* 1862:     */  private static final int SYN_RCVD = 1;
/* 1863:     */  
/* 1864:     */  private static final int SYN_SENT = 2;
/* 1865:     */  private static final int ESTABLISHED = 3;
/* 1866:     */  private static final int CLOSE_WAIT = 4;
/* 1867:1867 */  private static final boolean DEBUG = Boolean.getBoolean("net.rudp.debug");
/* 1868:     */  
/* 1870:     */  private class Counters
/* 1871:     */  {
/* 1872:     */    private int _seqn;
/* 1873:     */    
/* 1874:     */    private int _lastInSequence;
/* 1875:     */    private int _cumAckCounter;
/* 1876:     */    private int _outOfSeqCounter;
/* 1877:     */    private int _outSegsCounter;
/* 1878:     */    
/* 1879:     */    public Counters() {}
/* 1880:     */    
/* 1881:     */    public synchronized int nextSequenceNumber()
/* 1882:     */    {
/* 1883:1883 */      return this._seqn = ReliableSocket.nextSequenceNumber(this._seqn);
/* 1884:     */    }
/* 1885:     */    
/* 1886:     */    public synchronized int setSequenceNumber(int paramInt)
/* 1887:     */    {
/* 1888:1888 */      this._seqn = paramInt;
/* 1889:1889 */      return this._seqn;
/* 1890:     */    }
/* 1891:     */    
/* 1892:     */    public synchronized int setLastInSequence(int paramInt)
/* 1893:     */    {
/* 1894:1894 */      this._lastInSequence = paramInt;
/* 1895:1895 */      return this._lastInSequence;
/* 1896:     */    }
/* 1897:     */    
/* 1898:     */    public synchronized int getLastInSequence()
/* 1899:     */    {
/* 1900:1900 */      return this._lastInSequence;
/* 1901:     */    }
/* 1902:     */    
/* 1903:     */    public synchronized void incCumulativeAckCounter()
/* 1904:     */    {
/* 1905:1905 */      this._cumAckCounter += 1;
/* 1906:     */    }
/* 1907:     */    
/* 1908:     */    public synchronized int getCumulativeAckCounter()
/* 1909:     */    {
/* 1910:1910 */      return this._cumAckCounter;
/* 1911:     */    }
/* 1912:     */    
/* 1913:     */    public synchronized int getAndResetCumulativeAckCounter()
/* 1914:     */    {
/* 1915:1915 */      int i = this._cumAckCounter;
/* 1916:1916 */      this._cumAckCounter = 0;
/* 1917:1917 */      return i;
/* 1918:     */    }
/* 1919:     */    
/* 1920:     */    public synchronized void incOutOfSequenceCounter()
/* 1921:     */    {
/* 1922:1922 */      this._outOfSeqCounter += 1;
/* 1923:     */    }
/* 1924:     */    
/* 1925:     */    public synchronized int getOutOfSequenceCounter()
/* 1926:     */    {
/* 1927:1927 */      return this._outOfSeqCounter;
/* 1928:     */    }
/* 1929:     */    
/* 1930:     */    public synchronized int getAndResetOutOfSequenceCounter()
/* 1931:     */    {
/* 1932:1932 */      int i = this._outOfSeqCounter;
/* 1933:1933 */      this._outOfSeqCounter = 0;
/* 1934:1934 */      return i;
/* 1935:     */    }
/* 1936:     */    
/* 1937:     */    public synchronized void incOutstandingSegsCounter()
/* 1938:     */    {
/* 1939:1939 */      this._outSegsCounter += 1;
/* 1940:     */    }
/* 1941:     */    
/* 1942:     */    public synchronized int getOutstandingSegsCounter()
/* 1943:     */    {
/* 1944:1944 */      return this._outSegsCounter;
/* 1945:     */    }
/* 1946:     */    
/* 1947:     */    public synchronized int getAndResetOutstandingSegsCounter()
/* 1948:     */    {
/* 1949:1949 */      int i = this._outSegsCounter;
/* 1950:1950 */      this._outSegsCounter = 0;
/* 1951:1951 */      return i;
/* 1952:     */    }
/* 1953:     */    
/* 1954:     */    public synchronized void reset()
/* 1955:     */    {
/* 1956:1956 */      this._outOfSeqCounter = 0;
/* 1957:1957 */      this._outSegsCounter = 0;
/* 1958:1958 */      this._cumAckCounter = 0;
/* 1959:     */    }
/* 1960:     */  }
/* 1961:     */  
/* 1992:     */  private class ReliableSocketThread
/* 1993:     */    extends Thread
/* 1994:     */  {
/* 1995:     */    public ReliableSocketThread()
/* 1996:     */    {
/* 1997:1997 */      super();
/* 1998:1998 */      setDaemon(true);
/* 1999:     */    }
/* 2000:     */    
/* 2001:     */    public void run()
/* 2002:     */    {
/* 2003:     */      try {
/* 2004:     */        Segment localSegment;
/* 2005:2005 */        while ((localSegment = ReliableSocket.this.receiveSegment()) != null)
/* 2006:     */        {
/* 2007:2007 */          if ((localSegment instanceof SYNSegment)) {
/* 2008:2008 */            ReliableSocket.this.handleSYNSegment((SYNSegment)localSegment);
/* 2009:     */          }
/* 2010:2010 */          else if ((localSegment instanceof EAKSegment)) {
/* 2011:2011 */            ReliableSocket.this.handleEAKSegment((EAKSegment)localSegment);
/* 2012:     */          }
/* 2013:2013 */          else if (!(localSegment instanceof ACKSegment))
/* 2014:     */          {
/* 2017:2017 */            ReliableSocket.this.handleSegment(localSegment);
/* 2018:     */          }
/* 2019:     */          
/* 2020:2020 */          ReliableSocket.this.checkAndGetAck(localSegment);
/* 2021:     */        }
/* 2022:     */      }
/* 2023:     */      catch (IOException localIOException) {
/* 2024:2024 */        localIOException.printStackTrace();
/* 2025:     */      }
/* 2026:     */    }
/* 2027:     */  }
/* 2028:     */  
/* 2029:     */  private class NullSegmentTimerTask implements Runnable
/* 2030:     */  {
/* 2031:     */    private NullSegmentTimerTask() {}
/* 2032:     */    
/* 2033:     */    public void run() {
/* 2034:2034 */      synchronized (ReliableSocket.this._unackedSentQueue) {
/* 2035:2035 */        if (ReliableSocket.this._unackedSentQueue.isEmpty()) {
/* 2036:     */          try {
/* 2037:2037 */            ReliableSocket.this.sendAndQueueSegment(new NULSegment(ReliableSocket.this._counters.nextSequenceNumber()));
/* 2038:     */          }
/* 2039:     */          catch (IOException localIOException) {
/* 2040:2040 */            if (ReliableSocket.DEBUG) {
/* 2041:2041 */              localIOException.printStackTrace();
/* 2042:     */            }
/* 2043:     */          }
/* 2044:     */        }
/* 2045:     */      }
/* 2046:     */    }
/* 2047:     */  }
/* 2048:     */  
/* 2049:     */  private class RetransmissionTimerTask implements Runnable {
/* 2050:     */    private RetransmissionTimerTask() {}
/* 2051:     */    
/* 2052:     */    public void run() {
/* 2053:2053 */      synchronized (ReliableSocket.this._unackedSentQueue) {
/* 2054:2054 */        Iterator localIterator = ReliableSocket.this._unackedSentQueue.iterator();
/* 2055:2055 */        while (localIterator.hasNext()) {
/* 2056:2056 */          Segment localSegment = (Segment)localIterator.next();
/* 2057:     */          try {
/* 2058:2058 */            ReliableSocket.this.retransmitSegment(localSegment);
/* 2059:     */          }
/* 2060:     */          catch (IOException localIOException) {
/* 2061:2061 */            localIOException.printStackTrace();
/* 2062:     */          }
/* 2063:     */        }
/* 2064:     */      }
/* 2065:     */    }
/* 2066:     */  }
/* 2067:     */  
/* 2068:     */  private class CumulativeAckTimerTask implements Runnable {
/* 2069:     */    private CumulativeAckTimerTask() {}
/* 2070:     */    
/* 2071:     */    public void run() {
/* 2072:2072 */      ReliableSocket.this.sendAck();
/* 2073:     */    }
/* 2074:     */  }
/* 2075:     */  
/* 2076:     */  private class KeepAliveTimerTask implements Runnable {
/* 2077:     */    private KeepAliveTimerTask() {}
/* 2078:     */    
/* 2079:     */    public void run() {
/* 2080:2080 */      ReliableSocket.this.connectionFailure();
/* 2081:     */    }
/* 2082:     */  }
/* 2083:     */  
/* 2084:     */  private class ShutdownHook extends Thread
/* 2085:     */  {
/* 2086:     */    public ShutdownHook()
/* 2087:     */    {
/* 2088:2088 */      super();
/* 2089:     */    }
/* 2090:     */    
/* 2091:     */    public void run()
/* 2092:     */    {
/* 2093:     */      try {
/* 2094:2094 */        switch (ReliableSocket.this._state) {
/* 2095:     */        case 0: 
/* 2096:2096 */          return;
/* 2097:     */        }
/* 2098:2098 */        ReliableSocket.this.sendSegment(new FINSegment(ReliableSocket.this._counters.nextSequenceNumber()));
/* 2099:     */      }
/* 2100:     */      catch (Throwable localThrowable) {}
/* 2101:     */    }
/* 2102:     */  }
/* 2103:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.ReliableSocket
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */