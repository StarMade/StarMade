/*   1:    */package org.schema.schine.network.server;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
/*   4:    */import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
/*   5:    */import java.io.BufferedOutputStream;
/*   6:    */import java.io.DataInputStream;
/*   7:    */import java.io.DataOutputStream;
/*   8:    */import java.io.IOException;
/*   9:    */import java.io.InputStream;
/*  10:    */import java.io.OutputStream;
/*  11:    */import java.io.PrintStream;
/*  12:    */import java.net.InetAddress;
/*  13:    */import java.net.Socket;
/*  14:    */import java.util.ArrayList;
/*  15:    */import java.util.Arrays;
/*  16:    */import org.schema.schine.network.Command;
/*  17:    */import org.schema.schine.network.CommandMap;
/*  18:    */import org.schema.schine.network.Header;
/*  19:    */import org.schema.schine.network.NetUtil;
/*  20:    */import org.schema.schine.network.NetworkProcessor;
/*  21:    */import org.schema.schine.network.Pinger;
/*  22:    */import org.schema.schine.network.RegisteredClientOnServer;
/*  23:    */import org.schema.schine.network.StateInterface;
/*  24:    */
/* 288:    */public class ServerProcessor
/* 289:    */  extends Pinger
/* 290:    */  implements Runnable, NetworkProcessor
/* 291:    */{
/* 292:    */  Socket commandSocket;
/* 293:    */  private ServerStateInterface state;
/* 294:    */  private RegisteredClientOnServer client;
/* 295:    */  public static int connections;
/* 296:    */  private long heartBeatTimeStamp;
/* 297:    */  private long heartBeatTimeStampTimeoutHelper;
/* 298:298 */  Boolean waitingForPong = Boolean.valueOf(false);
/* 299:299 */  private Object lock = new Object();
/* 300:    */  
/* 302:    */  private static final int heartbeatTimeOut = 10000;
/* 303:    */  
/* 305:    */  private long connectionStartTime;
/* 306:    */  
/* 307:    */  private boolean pong;
/* 308:    */  
/* 309:309 */  private boolean connected = false;
/* 310:    */  private static final int MAX_PING_RETRYS = 12;
/* 311:311 */  private int pingRetrys = 12;
/* 312:    */  
/* 314:    */  private long pingTime;
/* 315:    */  
/* 316:    */  private DataInputStream dataInputStream;
/* 317:    */  
/* 318:    */  private DataOutputStream out;
/* 319:    */  
/* 320:    */  private FastByteArrayOutputStream byteArrayOutDoubleBuffer;
/* 321:    */  
/* 322:    */  private DataOutputStream outDoubleBuffer;
/* 323:    */  
/* 324:324 */  public static int MAX_PACKET_SIZE = 20480;
/* 325:    */  
/* 326:    */  private static final int MAX_PACKET_POOL_SIZE = 1000;
/* 327:    */  
/* 328:    */  private DataInputStream inDoubleBuffer;
/* 329:329 */  byte[] receiveBuffer = new byte[MAX_PACKET_SIZE];
/* 330:    */  
/* 332:    */  private boolean ping;
/* 333:    */  
/* 335:    */  private Header tmpHeader;
/* 336:    */  
/* 338:    */  private Thread thread;
/* 339:    */  
/* 341:    */  private ServerProcessor.ServerPing serverPing;
/* 342:    */  
/* 343:    */  private Thread serverPingThread;
/* 344:    */  
/* 345:    */  private boolean stopTransmit;
/* 346:    */  
/* 348:    */  public ServerProcessor(Socket paramSocket, ServerStateInterface paramServerStateInterface)
/* 349:    */  {
/* 350:350 */    this.commandSocket = paramSocket;
/* 351:351 */    this.state = paramServerStateInterface;
/* 352:352 */    this.heartBeatTimeStamp = System.currentTimeMillis();
/* 353:353 */    this.heartBeatTimeStampTimeoutHelper = System.currentTimeMillis();
/* 354:354 */    this.connectionStartTime = System.currentTimeMillis();
/* 355:    */    
/* 357:357 */    this.outDoubleBuffer = new DataOutputStream(this.byteArrayOutDoubleBuffer = new FastByteArrayOutputStream(102400));
/* 358:    */  }
/* 359:    */  
/* 362:    */  private boolean checkPing(InputStream paramInputStream)
/* 363:    */  {
/* 364:364 */    if ((paramInputStream = (byte)paramInputStream.read()) == -1)
/* 365:    */    {
/* 366:366 */      this.serverPing.sendPong();
/* 367:    */      
/* 368:368 */      return true; }
/* 369:369 */    if (paramInputStream == -2)
/* 370:    */    {
/* 371:371 */      setPingTime(System.currentTimeMillis() - this.heartBeatTimeStamp);
/* 372:372 */      this.waitingForPong = Boolean.valueOf(false);
/* 373:373 */      if (this.pingRetrys != 12) {
/* 374:374 */        System.err.println("[SERVER][WARNING] Recovered Ping for " + this.client + "; Retries left: " + this.pingRetrys + "; retries resetting");
/* 375:    */      }
/* 376:376 */      this.pingRetrys = 12;
/* 377:    */      
/* 378:378 */      setChanged();
/* 379:379 */      notifyObservers();
/* 380:380 */      return true;
/* 381:    */    }
/* 382:382 */    return false;
/* 383:    */  }
/* 384:    */  
/* 387:    */  public void closeSocket()
/* 388:    */  {
/* 389:389 */    this.commandSocket.close();
/* 390:    */  }
/* 391:    */  
/* 392:    */  public void disconnectDelayed() {
/* 393:393 */    new ServerProcessor.1(this).start();
/* 394:    */  }
/* 395:    */  
/* 412:    */  public void disconnect()
/* 413:    */  {
/* 414:414 */    this.connected = false;
/* 415:415 */    setStopTransmit(true);
/* 416:416 */    synchronized (this.sendingQueue) {
/* 417:417 */      this.sendingQueue.notify();
/* 418:    */    }
/* 419:    */    try {
/* 420:420 */      closeSocket();
/* 421:421 */    } catch (IOException localIOException) { 
/* 422:    */      
/* 423:423 */        localIOException;
/* 424:    */    }
/* 425:    */    
/* 426:424 */    deleteObservers();
/* 427:    */  }
/* 428:    */  
/* 430:428 */  private final ArrayList sendingQueue = new ArrayList();
/* 431:    */  
/* 434:    */  private ServerProcessor.SendingQueueThread sendingQueueThread;
/* 435:    */  
/* 437:    */  private boolean disconnectAfterSent;
/* 438:    */  
/* 440:    */  public static int totalPackagesQueued;
/* 441:    */  
/* 443:441 */  private static final ArrayList packetPool = new ArrayList();
/* 444:    */  
/* 448:    */  public static FastByteArrayOutputStream getNewPacket()
/* 449:    */  {
/* 450:448 */    synchronized (packetPool) {
/* 451:449 */      if (!packetPool.isEmpty()) {
/* 452:450 */        return (FastByteArrayOutputStream)packetPool.remove(0);
/* 453:    */      }
/* 454:    */    }
/* 455:453 */    return new FastByteArrayOutputStream(MAX_PACKET_SIZE);
/* 456:    */  }
/* 457:    */  
/* 458:    */  public static void releasePacket(FastByteArrayOutputStream paramFastByteArrayOutputStream) {
/* 459:457 */    paramFastByteArrayOutputStream.reset();
/* 460:458 */    synchronized (packetPool) {
/* 461:459 */      if (packetPool.size() < 1000) {
/* 462:460 */        packetPool.add(paramFastByteArrayOutputStream);
/* 463:    */      }
/* 464:    */      
/* 505:    */      return;
/* 506:    */    }
/* 507:    */  }
/* 508:    */  
/* 549:    */  public void flushDoubleOutBuffer()
/* 550:    */  {
/* 551:549 */    assert (this.byteArrayOutDoubleBuffer.position() > 0L);
/* 552:    */    FastByteArrayOutputStream localFastByteArrayOutputStream;
/* 553:551 */    (localFastByteArrayOutputStream = getNewPacket()).write(this.byteArrayOutDoubleBuffer.array, 0, (int)this.byteArrayOutDoubleBuffer.position());
/* 554:552 */    resetDoubleOutBuffer();
/* 555:553 */    synchronized (this.sendingQueue) {
/* 556:554 */      if (this.connected)
/* 557:    */      {
/* 558:556 */        this.sendingQueue.add(localFastByteArrayOutputStream);
/* 559:557 */        totalPackagesQueued += 1;
/* 560:558 */        this.sendingQueue.notify();
/* 561:    */      }
/* 562:    */      return; } }
/* 563:    */  
/* 564:562 */  public long p = 0L;
/* 565:    */  
/* 566:564 */  public void sendPacket(FastByteArrayOutputStream paramFastByteArrayOutputStream) { assert (paramFastByteArrayOutputStream.position() > 0L);
/* 567:565 */    System.currentTimeMillis();
/* 568:566 */    this.out.writeInt((int)paramFastByteArrayOutputStream.position());
/* 569:567 */    this.out.writeLong(System.currentTimeMillis());
/* 570:568 */    this.out.write(paramFastByteArrayOutputStream.array, 0, (int)paramFastByteArrayOutputStream.position());
/* 571:    */  }
/* 572:    */  
/* 576:    */  public RegisteredClientOnServer getClient()
/* 577:    */  {
/* 578:576 */    return this.client;
/* 579:    */  }
/* 580:    */  
/* 581:    */  public InetAddress getClientIp() {
/* 582:580 */    return this.commandSocket.getInetAddress();
/* 583:    */  }
/* 584:    */  
/* 587:    */  public long getConnectionTime()
/* 588:    */  {
/* 589:587 */    return System.currentTimeMillis() - this.connectionStartTime;
/* 590:    */  }
/* 591:    */  
/* 594:    */  public int getCurrentSize()
/* 595:    */  {
/* 596:594 */    return (int)this.byteArrayOutDoubleBuffer.position();
/* 597:    */  }
/* 598:    */  
/* 599:    */  public DataInputStream getIn() {
/* 600:598 */    return this.inDoubleBuffer;
/* 601:    */  }
/* 602:    */  
/* 603:    */  public InputStream getInRaw()
/* 604:    */  {
/* 605:603 */    return this.commandSocket.getInputStream();
/* 606:    */  }
/* 607:    */  
/* 608:    */  public String getIp() {
/* 609:607 */    if (this.commandSocket.isConnected()) {
/* 610:608 */      return this.commandSocket.getRemoteSocketAddress().toString();
/* 611:    */    }
/* 612:610 */    return "n/a";
/* 613:    */  }
/* 614:    */  
/* 615:    */  public Object getLock()
/* 616:    */  {
/* 617:615 */    return this.lock;
/* 618:    */  }
/* 619:    */  
/* 620:    */  public DataOutputStream getOut()
/* 621:    */  {
/* 622:620 */    return this.outDoubleBuffer;
/* 623:    */  }
/* 624:    */  
/* 625:    */  public OutputStream getOutRaw()
/* 626:    */  {
/* 627:625 */    return this.commandSocket.getOutputStream();
/* 628:    */  }
/* 629:    */  
/* 632:    */  public long getPingTime()
/* 633:    */  {
/* 634:632 */    return this.pingTime;
/* 635:    */  }
/* 636:    */  
/* 637:    */  public int getPort() {
/* 638:636 */    if (this.commandSocket.isConnected()) {
/* 639:637 */      return this.commandSocket.getLocalPort();
/* 640:    */    }
/* 641:639 */    return -1;
/* 642:    */  }
/* 643:    */  
/* 645:    */  public StateInterface getState()
/* 646:    */  {
/* 647:645 */    return this.state;
/* 648:    */  }
/* 649:    */  
/* 650:    */  public boolean isAlive()
/* 651:    */  {
/* 652:650 */    return this.thread.isAlive();
/* 653:    */  }
/* 654:    */  
/* 669:    */  public void notifyPacketReceived(short paramShort, Command paramCommand) {}
/* 670:    */  
/* 684:    */  private void onError()
/* 685:    */  {
/* 686:684 */    deleteObservers();
/* 687:685 */    if (getClient() != null) {
/* 688:686 */      this.state.getController().unregister(getClient().getId());
/* 689:    */    } else {
/* 690:688 */      System.err.println("COULD NOT UNREGISTER CLIENT " + getClient());
/* 691:    */    }
/* 692:690 */    if (!this.commandSocket.isClosed())
/* 693:    */    {
/* 694:692 */      this.commandSocket.close();
/* 695:    */    }
/* 696:694 */    connections -= 1;
/* 697:695 */    if (getClient() == null) {
/* 698:696 */      return;
/* 699:    */    }
/* 700:698 */    System.err.println("[Server] Client <" + getClient().getId() + "> logged out from server. connections: " + connections);
/* 701:    */    
/* 702:700 */    if (!this.commandSocket.isClosed()) {
/* 703:701 */      System.err.println("[Server] ERROR: socket still open!");
/* 704:    */    }
/* 705:    */  }
/* 706:    */  
/* 707:    */  private void parseNextPacket(DataInputStream paramDataInputStream)
/* 708:    */  {
/* 709:707 */    if ((getClient() != null) && (!getClient().isConnected())) {
/* 710:708 */      this.connected = false;
/* 711:709 */      System.err.println("ERROR: client not connected!");
/* 712:710 */      onError();
/* 713:711 */      return;
/* 714:    */    }
/* 715:    */    
/* 716:714 */    this.tmpHeader.read(paramDataInputStream);
/* 717:    */    
/* 723:721 */    if (this.tmpHeader.getType() == 111)
/* 724:    */    {
/* 726:724 */      Object[] arrayOfObject = NetUtil.commands.getById(this.tmpHeader.getCommandId()).readParameters(this.tmpHeader, paramDataInputStream);
/* 727:    */      
/* 728:726 */      NetUtil.commands.getById(this.tmpHeader.getCommandId()).serverProcess(this, arrayOfObject, this.state, this.tmpHeader.packetId);
/* 729:727 */    } else if (this.tmpHeader.getType() == 123)
/* 730:    */    {
/* 732:730 */      NetUtil.commands.getById(this.tmpHeader.getCommandId()).serverProcess(this, null, this.state, this.tmpHeader.packetId);
/* 733:    */    }
/* 734:732 */    paramDataInputStream.available();
/* 735:    */  }
/* 736:    */  
/* 742:    */  public void resetDoubleOutBuffer()
/* 743:    */  {
/* 744:742 */    this.outDoubleBuffer.flush();
/* 745:743 */    this.byteArrayOutDoubleBuffer.reset();
/* 746:    */  }
/* 747:    */  
/* 752:    */  public void run()
/* 753:    */  {
/* 754:752 */    connections += 1;
/* 755:753 */    while (!this.commandSocket.isConnected()) {
/* 756:    */      try {
/* 757:755 */        System.err.println("[SERVER] waiting for socket to connect: " + this.commandSocket);
/* 758:756 */        Thread.sleep(100L);
/* 759:757 */      } catch (InterruptedException localInterruptedException) { 
/* 760:    */        
/* 762:760 */          localInterruptedException;
/* 763:    */      }
/* 764:    */    }
/* 765:    */    
/* 767:762 */    int i = 0;
/* 768:    */    try
/* 769:    */    {
/* 770:765 */      this.connected = true;
/* 771:    */      
/* 773:768 */      setup();
/* 774:769 */      System.currentTimeMillis();
/* 775:    */      
/* 776:771 */      System.out.println("[SERVER][PROCESSOR] client setup completed. listening for input");
/* 777:772 */      while (this.connected)
/* 778:    */      {
/* 779:774 */        this.thread.setName("SERVER-PROCESSOR: " + this.client);
/* 780:775 */        this.serverPingThread.setName("ServerPing " + this.client);
/* 781:    */        
/* 782:    */        int j;
/* 783:    */        
/* 784:779 */        if ((j = this.dataInputStream.readInt()) > this.receiveBuffer.length) {
/* 785:780 */          System.err.println("[SERVER] Exception: Unusual big update from client " + getClient() + ": growing receive buffer: " + j);
/* 786:781 */          this.receiveBuffer = new byte[j];
/* 787:    */        }
/* 788:783 */        assert (j > 0) : (" Empty update! " + j);
/* 789:    */        try
/* 790:    */        {
/* 791:786 */          this.dataInputStream.readFully(this.receiveBuffer, 0, j);
/* 792:    */        } catch (Exception localException2) {
/* 793:788 */          System.err.println("Exception happened with size " + j);
/* 794:789 */          throw localException2;
/* 795:    */        }
/* 796:791 */        FastByteArrayInputStream localFastByteArrayInputStream = new FastByteArrayInputStream(this.receiveBuffer, 0, j);
/* 797:    */        
/* 798:793 */        this.inDoubleBuffer = new DataInputStream(localFastByteArrayInputStream);
/* 799:    */        int k;
/* 800:795 */        if ((k = this.inDoubleBuffer.read()) >= 0)
/* 801:    */        {
/* 808:803 */          if ((k != 42) && (k != 23) && (k != 100) && (k != 65))
/* 809:    */          {
/* 811:806 */            throw new IOException("SERVER CHECK FAILED: " + k + " for client " + getClient() + ": Received: " + j + ": " + Arrays.toString(Arrays.copyOf(this.receiveBuffer, j)) + ": available: " + localFastByteArrayInputStream.available());
/* 812:    */          }
/* 813:    */          
/* 817:812 */          if (k == 100) {
/* 818:813 */            this.connected = false;
/* 819:814 */            i = 1;
/* 820:815 */            System.err.println("[SERVER] Probe was made CODE (#100). Closing connection!");
/* 821:816 */            this.out.write(100);
/* 822:817 */            this.out.flush();
/* 823:    */          }
/* 824:819 */          else if (k == 65) {
/* 825:820 */            System.err.println("[SERVER][LOGOUT]#### soft client logout received. Logging out client: " + this.client);
/* 826:821 */            this.connected = false;
/* 827:    */          }
/* 828:    */          else
/* 829:    */          {
/* 830:825 */            synchronized (this.state) {
/* 831:826 */              synchronized (getLock()) { boolean bool;
/* 832:827 */                if (k == 23)
/* 833:    */                {
/* 834:829 */                  bool = checkPing(getIn());
/* 835:830 */                  assert (bool);
/* 836:831 */                } else if (bool == true) {
/* 837:832 */                  parseNextPacket(getIn());
/* 838:    */                }
/* 839:    */                
/* 840:835 */                if (this.byteArrayOutDoubleBuffer.position() > 0L) {
/* 841:836 */                  flushDoubleOutBuffer();
/* 842:    */                }
/* 843:    */              }
/* 844:    */            }
/* 845:    */            
/* 846:841 */            if (this.inDoubleBuffer.available() > 0)
/* 847:842 */              throw new IOException("MORE BYTES AVAILABLE: " + this.inDoubleBuffer.available() + "; total size" + localObject4);
/* 848:    */          }
/* 849:    */        }
/* 850:    */      }
/* 851:    */    } catch (Exception localException1) {
/* 852:847 */      if (!this.disconnectAfterSent) {
/* 853:848 */        localException1.printStackTrace();
/* 854:    */      } else {
/* 855:850 */        i = 1;
/* 856:    */      }
/* 857:852 */      this.connected = false;
/* 858:    */    }
/* 859:    */    finally {
/* 860:855 */      this.connected = false;
/* 861:    */    }
/* 862:    */    
/* 863:858 */    System.err.println("CONNECTION FOR " + this.client + " HAS BEEN DISCONNECTED . PROBE: " + localObject1);
/* 864:    */    try {
/* 865:860 */      if (localObject1 == 0) {
/* 866:861 */        onError();
/* 867:    */      }
/* 868:    */    }
/* 869:    */    catch (IOException localIOException) {}finally
/* 870:    */    {
/* 871:866 */      deleteObservers();
/* 872:867 */      if (getClient() != null) {
/* 873:868 */        System.err.println("UNREGISTER CLIENT " + getClient());
/* 874:869 */        this.state.getController().unregister(getClient().getId());
/* 875:870 */        System.err.println("UNREGISTER DONE FOR CLIENT " + getClient());
/* 876:    */      } else {
/* 877:872 */        System.err.println("COULD NOT UNREGISTER CLIENT " + getClient());
/* 878:    */      }
/* 879:    */      
/* 880:875 */      if ((getClient() != null) && 
/* 881:876 */        (!this.state.filterJoinMessages())) {
/* 882:877 */        ((ServerControllerInterface)getState().getController()).broadcastMessage(getClient().getPlayerName() + " left the game", 0);
/* 883:    */      }
/* 884:    */    }
/* 885:    */    
/* 887:882 */    if (localObject2 != 0) {
/* 888:883 */      System.err.println("[SERVER] PROBE SUCCESSFULLY EXECUTED. STOPPING PROCESSOR. (Ping of a Starter to start server)");return;
/* 889:    */    }
/* 890:885 */    System.err.println("[SERVER] SERVER PROCESSOR STOPPED FOR " + this.client);
/* 891:    */  }
/* 892:    */  
/* 903:    */  public void serverCommand(byte paramByte, int paramInt, Object... paramVarArgs)
/* 904:    */  {
/* 905:900 */    System.err.println("SERVER COMMAND: " + Arrays.toString(paramVarArgs));
/* 906:    */    
/* 907:902 */    NetUtil.commands.getById(paramByte)
/* 908:    */    
/* 909:904 */      .writeAndCommitParametriziedCommand(paramVarArgs, paramInt, getClient().getId(), (short)-32768, getClient().getProcessor());
/* 910:    */    
/* 911:906 */    Command.sendingTime = System.currentTimeMillis();
/* 912:    */  }
/* 913:    */  
/* 914:    */  public void setClient(RegisteredClientOnServer paramRegisteredClientOnServer)
/* 915:    */  {
/* 916:911 */    this.client = paramRegisteredClientOnServer;
/* 917:    */  }
/* 918:    */  
/* 921:    */  public void setPingTime(long paramLong)
/* 922:    */  {
/* 923:918 */    this.pingTime = paramLong;
/* 924:    */  }
/* 925:    */  
/* 926:    */  public void setThread(Thread paramThread) {
/* 927:922 */    this.thread = paramThread;
/* 928:    */  }
/* 929:    */  
/* 930:    */  private void setup()
/* 931:    */  {
/* 932:927 */    while ((!this.commandSocket.isConnected()) || (!this.commandSocket.isBound()) || (this.commandSocket.isInputShutdown()) || (this.commandSocket.isOutputShutdown())) {
/* 933:928 */      System.err.println("Waiting for command socket! ");
/* 934:    */    }
/* 935:    */    
/* 936:931 */    this.dataInputStream = new DataInputStream(this.commandSocket.getInputStream());
/* 937:    */    
/* 938:933 */    this.out = new DataOutputStream(new BufferedOutputStream(this.commandSocket.getOutputStream(), 8192));
/* 939:    */    
/* 943:938 */    this.serverPing = new ServerProcessor.ServerPing(this, null);
/* 944:939 */    this.serverPingThread = new Thread(this.serverPing, "ServerPing");
/* 945:940 */    this.serverPingThread.start();
/* 946:941 */    this.tmpHeader = new Header();
/* 947:    */    
/* 949:944 */    this.sendingQueueThread = new ServerProcessor.SendingQueueThread(this, null);
/* 950:945 */    this.sendingQueueThread.start();
/* 951:    */  }
/* 952:    */  
/* 954:    */  public void updatePing()
/* 955:    */  {
/* 956:951 */    throw new IllegalStateException("METHOD NOT AVAILABLE");
/* 957:    */  }
/* 958:    */  
/* 963:    */  public boolean isStopTransmit()
/* 964:    */  {
/* 965:960 */    return this.stopTransmit;
/* 966:    */  }
/* 967:    */  
/* 971:    */  public void setStopTransmit(boolean paramBoolean)
/* 972:    */  {
/* 973:968 */    this.stopTransmit = paramBoolean;
/* 974:    */  }
/* 975:    */  
/* 976:    */  public boolean isConnectionAlive()
/* 977:    */  {
/* 978:973 */    return (this.commandSocket != null) && (this.commandSocket.isConnected());
/* 979:    */  }
/* 980:    */  
/* 981:    */  public void disconnectAfterSent() {
/* 982:977 */    this.disconnectAfterSent = true;
/* 983:    */  }
/* 984:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */