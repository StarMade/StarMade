package net.rudp;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.rudp.impl.ACKSegment;
import net.rudp.impl.DATSegment;
import net.rudp.impl.EAKSegment;
import net.rudp.impl.FINSegment;
import net.rudp.impl.NULSegment;
import net.rudp.impl.RSTSegment;
import net.rudp.impl.SYNSegment;
import net.rudp.impl.Segment;
import net.rudp.impl.Timer;

public class ReliableSocket
  extends Socket
{
  protected DatagramSocket _sock;
  protected SocketAddress _endpoint;
  protected ReliableSocketInputStream _in;
  protected ReliableSocketOutputStream _out;
  private byte[] _recvbuffer = new byte[65535];
  private boolean _closed = false;
  private boolean _connected = false;
  private boolean _reset = false;
  private boolean _keepAlive = true;
  private int _state = 0;
  private int _timeout = 0;
  private boolean _shutIn = false;
  private boolean _shutOut = false;
  private Object _closeLock = new Object();
  private Object _resetLock = new Object();
  private ArrayList _listeners = new ArrayList();
  private ArrayList _stateListeners = new ArrayList();
  private ShutdownHook _shutdownHook;
  private ReliableSocketProfile _profile = new ReliableSocketProfile();
  private ArrayList _unackedSentQueue = new ArrayList();
  private ArrayList _outSeqRecvQueue = new ArrayList();
  private ArrayList _inSeqRecvQueue = new ArrayList();
  private Object _recvQueueLock = new Object();
  private Counters _counters = new Counters();
  private Thread _sockThread = new ReliableSocketThread();
  private int _sendQueueSize = 32;
  private int _recvQueueSize = 32;
  private int _sendBufferSize;
  private int _recvBufferSize;
  private Timer _nullSegmentTimer = new Timer("ReliableSocket-NullSegmentTimer", new NullSegmentTimerTask(null));
  private Timer _retransmissionTimer = new Timer("ReliableSocket-RetransmissionTimer", new RetransmissionTimerTask(null));
  private Timer _cumulativeAckTimer = new Timer("ReliableSocket-CumulativeAckTimer", new CumulativeAckTimerTask(null));
  private Timer _keepAliveTimer = new Timer("ReliableSocket-KeepAliveTimer", new KeepAliveTimerTask(null));
  private static final int MAX_SEQUENCE_NUMBER = 255;
  private static final int CLOSED = 0;
  private static final int SYN_RCVD = 1;
  private static final int SYN_SENT = 2;
  private static final int ESTABLISHED = 3;
  private static final int CLOSE_WAIT = 4;
  private static final boolean DEBUG = Boolean.getBoolean("net.rudp.debug");
  
  public ReliableSocket()
    throws IOException
  {
    this(new ReliableSocketProfile());
  }
  
  public ReliableSocket(ReliableSocketProfile paramReliableSocketProfile)
    throws IOException
  {
    this(new DatagramSocket(), paramReliableSocketProfile);
  }
  
  public ReliableSocket(String paramString, int paramInt)
    throws UnknownHostException, IOException
  {
    this(new InetSocketAddress(paramString, paramInt), null);
  }
  
  public ReliableSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2)
    throws IOException
  {
    this(new InetSocketAddress(paramInetAddress1, paramInt1), new InetSocketAddress(paramInetAddress2, paramInt2));
  }
  
  public ReliableSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
    throws IOException
  {
    this(new InetSocketAddress(paramString, paramInt1), new InetSocketAddress(paramInetAddress, paramInt2));
  }
  
  public ReliableSocket(InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2)
    throws IOException
  {
    this(new DatagramSocket(paramInetSocketAddress2), new ReliableSocketProfile());
    connect(paramInetSocketAddress1);
  }
  
  public ReliableSocket(DatagramSocket paramDatagramSocket)
  {
    this(paramDatagramSocket, new ReliableSocketProfile());
  }
  
  public ReliableSocket(DatagramSocket paramDatagramSocket, ReliableSocketProfile paramReliableSocketProfile)
  {
    if (paramDatagramSocket == null) {
      throw new NullPointerException("sock");
    }
    init(paramDatagramSocket, paramReliableSocketProfile);
  }
  
  protected void init(DatagramSocket paramDatagramSocket, ReliableSocketProfile paramReliableSocketProfile)
  {
    this._sock = paramDatagramSocket;
    this._profile = paramReliableSocketProfile;
    this._shutdownHook = new ShutdownHook();
    this._sendBufferSize = ((this._profile.maxSegmentSize() - 6) * 32);
    this._recvBufferSize = ((this._profile.maxSegmentSize() - 6) * 32);
    try
    {
      Runtime.getRuntime().addShutdownHook(this._shutdownHook);
    }
    catch (IllegalStateException localIllegalStateException)
    {
      if (DEBUG) {
        localIllegalStateException.printStackTrace();
      }
    }
    this._sockThread.start();
  }
  
  public void bind(SocketAddress paramSocketAddress)
    throws IOException
  {
    this._sock.bind(paramSocketAddress);
  }
  
  public void connect(SocketAddress paramSocketAddress)
    throws IOException
  {
    connect(paramSocketAddress, 0);
  }
  
  public void connect(SocketAddress paramSocketAddress, int paramInt)
    throws IOException
  {
    if (paramSocketAddress == null) {
      throw new IllegalArgumentException("connect: The address can't be null");
    }
    if (paramInt < 0) {
      throw new IllegalArgumentException("connect: timeout can't be negative");
    }
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    if (isConnected()) {
      throw new SocketException("already connected");
    }
    if (!(paramSocketAddress instanceof InetSocketAddress)) {
      throw new IllegalArgumentException("Unsupported address type");
    }
    this._endpoint = ((InetSocketAddress)paramSocketAddress);
    this._state = 2;
    Random localRandom = new Random(System.currentTimeMillis());
    SYNSegment localSYNSegment = new SYNSegment(this._counters.setSequenceNumber(localRandom.nextInt(255)), this._profile.maxOutstandingSegs(), this._profile.maxSegmentSize(), this._profile.retransmissionTimeout(), this._profile.cumulativeAckTimeout(), this._profile.nullSegmentTimeout(), this._profile.maxRetrans(), this._profile.maxCumulativeAcks(), this._profile.maxOutOfSequence(), this._profile.maxAutoReset());
    sendAndQueueSegment(localSYNSegment);
    int i = 0;
    synchronized (this)
    {
      if (!isConnected()) {
        try
        {
          if (paramInt == 0)
          {
            wait();
          }
          else
          {
            long l = System.currentTimeMillis();
            wait(paramInt);
            if (System.currentTimeMillis() - l >= paramInt) {
              i = 1;
            }
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }
    if (this._state == 3) {
      return;
    }
    synchronized (this._unackedSentQueue)
    {
      this._unackedSentQueue.clear();
      this._unackedSentQueue.notifyAll();
    }
    this._counters.reset();
    this._retransmissionTimer.cancel();
    switch (this._state)
    {
    case 2: 
      connectionRefused();
      this._state = 0;
      if (i != 0) {
        throw new SocketTimeoutException();
      }
      throw new SocketException("Connection refused");
    case 0: 
    case 4: 
      this._state = 0;
      throw new SocketException("Socket closed");
    }
  }
  
  public SocketChannel getChannel()
  {
    return null;
  }
  
  public InetAddress getInetAddress()
  {
    if (!isConnected()) {
      return null;
    }
    return ((InetSocketAddress)this._endpoint).getAddress();
  }
  
  public int getPort()
  {
    if (!isConnected()) {
      return 0;
    }
    return ((InetSocketAddress)this._endpoint).getPort();
  }
  
  public SocketAddress getRemoteSocketAddress()
  {
    if (!isConnected()) {
      return null;
    }
    return new InetSocketAddress(getInetAddress(), getPort());
  }
  
  public InetAddress getLocalAddress()
  {
    return this._sock.getLocalAddress();
  }
  
  public int getLocalPort()
  {
    return this._sock.getLocalPort();
  }
  
  public SocketAddress getLocalSocketAddress()
  {
    return this._sock.getLocalSocketAddress();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    if (!isConnected()) {
      throw new SocketException("Socket is not connected");
    }
    if (isInputShutdown()) {
      throw new SocketException("Socket input is shutdown");
    }
    return this._in;
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    if (!isConnected()) {
      throw new SocketException("Socket is not connected");
    }
    if (isOutputShutdown()) {
      throw new SocketException("Socket output is shutdown");
    }
    return this._out;
  }
  
  public synchronized void close()
    throws IOException
  {
    synchronized (this._closeLock)
    {
      if (isClosed()) {
        return;
      }
      try
      {
        Runtime.getRuntime().removeShutdownHook(this._shutdownHook);
      }
      catch (IllegalStateException localIllegalStateException)
      {
        if (DEBUG) {
          localIllegalStateException.printStackTrace();
        }
      }
      switch (this._state)
      {
      case 2: 
        synchronized (this)
        {
          notify();
        }
        break;
      case 1: 
      case 3: 
      case 4: 
        sendSegment(new FINSegment(this._counters.nextSequenceNumber()));
        closeImpl();
        break;
      case 0: 
        this._retransmissionTimer.destroy();
        this._cumulativeAckTimer.destroy();
        this._keepAliveTimer.destroy();
        this._nullSegmentTimer.destroy();
        this._sock.close();
      }
      this._closed = true;
      this._state = 0;
      synchronized (this._unackedSentQueue)
      {
        this._unackedSentQueue.notify();
      }
      synchronized (this._inSeqRecvQueue)
      {
        this._inSeqRecvQueue.notify();
      }
    }
  }
  
  public boolean isBound()
  {
    return this._sock.isBound();
  }
  
  public boolean isConnected()
  {
    return this._connected;
  }
  
  public boolean isClosed()
  {
    synchronized (this._closeLock)
    {
      return this._closed;
    }
  }
  
  public void setSoTimeout(int paramInt)
    throws SocketException
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("timeout < 0");
    }
    this._timeout = paramInt;
  }
  
  public synchronized void setSendBufferSize(int paramInt)
    throws SocketException
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("negative receive size");
    }
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    if (isConnected()) {
      return;
    }
    this._sendBufferSize = paramInt;
  }
  
  public synchronized int getSendBufferSize()
    throws SocketException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    return this._sendBufferSize;
  }
  
  public synchronized void setReceiveBufferSize(int paramInt)
    throws SocketException
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("negative send size");
    }
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    if (isConnected()) {
      return;
    }
    this._recvBufferSize = paramInt;
  }
  
  public synchronized int getReceiveBufferSize()
    throws SocketException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    return this._recvBufferSize;
  }
  
  public void setTcpNoDelay(boolean paramBoolean)
    throws SocketException
  {
    throw new SocketException("Socket option not supported");
  }
  
  public boolean getTcpNoDelay()
  {
    return false;
  }
  
  public synchronized void setKeepAlive(boolean paramBoolean)
    throws SocketException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    if (!(this._keepAlive ^ paramBoolean)) {
      return;
    }
    this._keepAlive = paramBoolean;
    if (isConnected()) {
      if (this._keepAlive) {
        this._keepAliveTimer.schedule(this._profile.nullSegmentTimeout() * 6, this._profile.nullSegmentTimeout() * 6);
      } else {
        this._keepAliveTimer.cancel();
      }
    }
  }
  
  public synchronized boolean getKeepAlive()
    throws SocketException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    return this._keepAlive;
  }
  
  public void shutdownInput()
    throws IOException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    if (!isConnected()) {
      throw new SocketException("Socket is not connected");
    }
    if (isInputShutdown()) {
      throw new SocketException("Socket input is already shutdown");
    }
    this._shutIn = true;
    synchronized (this._recvQueueLock)
    {
      this._recvQueueLock.notify();
    }
  }
  
  public void shutdownOutput()
    throws IOException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    if (!isConnected()) {
      throw new SocketException("Socket is not connected");
    }
    if (isOutputShutdown()) {
      throw new SocketException("Socket output is already shutdown");
    }
    this._shutOut = true;
    synchronized (this._unackedSentQueue)
    {
      this._unackedSentQueue.notifyAll();
    }
  }
  
  public boolean isInputShutdown()
  {
    return this._shutIn;
  }
  
  public boolean isOutputShutdown()
  {
    return this._shutOut;
  }
  
  public void reset()
    throws IOException
  {
    reset(null);
  }
  
  public void reset(ReliableSocketProfile paramReliableSocketProfile)
    throws IOException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    if (!isConnected()) {
      throw new SocketException("Socket is not connected");
    }
    synchronized (this._resetLock)
    {
      this._reset = true;
      sendAndQueueSegment(new RSTSegment(this._counters.nextSequenceNumber()));
      synchronized (this._unackedSentQueue)
      {
        while (!this._unackedSentQueue.isEmpty()) {
          try
          {
            this._unackedSentQueue.wait();
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
      }
    }
    connectionReset();
    if (paramReliableSocketProfile != null) {
      this._profile = paramReliableSocketProfile;
    }
    this._state = 2;
    ??? = new Random(System.currentTimeMillis());
    ??? = new SYNSegment(this._counters.setSequenceNumber(((Random)???).nextInt(255)), this._profile.maxOutstandingSegs(), this._profile.maxSegmentSize(), this._profile.retransmissionTimeout(), this._profile.cumulativeAckTimeout(), this._profile.nullSegmentTimeout(), this._profile.maxRetrans(), this._profile.maxCumulativeAcks(), this._profile.maxOutOfSequence(), this._profile.maxAutoReset());
    sendAndQueueSegment((Segment)???);
  }
  
  protected void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    if (isOutputShutdown()) {
      throw new IOException("Socket output is shutdown");
    }
    if (!isConnected()) {
      throw new SocketException("Connection reset");
    }
    int i = 0;
    while (i < paramInt2) {
      synchronized (this._resetLock)
      {
        while (this._reset) {
          try
          {
            this._resetLock.wait();
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
        int j = Math.min(this._profile.maxSegmentSize() - 6, paramInt2 - i);
        sendAndQueueSegment(new DATSegment(this._counters.nextSequenceNumber(), this._counters.getLastInSequence(), paramArrayOfByte, paramInt1 + i, j));
        i += j;
      }
    }
  }
  
  protected int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = 0;
    synchronized (this._recvQueueLock)
    {
      do
      {
        while (this._inSeqRecvQueue.isEmpty())
        {
          if (isClosed()) {
            throw new SocketException("Socket is closed");
          }
          if (isInputShutdown()) {
            throw new EOFException();
          }
          if (!isConnected()) {
            throw new SocketException("Connection reset");
          }
          try
          {
            if (this._timeout == 0)
            {
              this._recvQueueLock.wait();
            }
            else
            {
              long l = System.currentTimeMillis();
              this._recvQueueLock.wait(this._timeout);
              if (System.currentTimeMillis() - l >= this._timeout) {
                throw new SocketTimeoutException();
              }
            }
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
        Iterator localIterator = this._inSeqRecvQueue.iterator();
        while (localIterator.hasNext())
        {
          Segment localSegment = (Segment)localIterator.next();
          if ((localSegment instanceof RSTSegment))
          {
            localIterator.remove();
            break;
          }
          if ((localSegment instanceof FINSegment))
          {
            if (i > 0) {
              break;
            }
            localIterator.remove();
            return -1;
          }
          if ((localSegment instanceof DATSegment))
          {
            byte[] arrayOfByte = ((DATSegment)localSegment).getData();
            if (arrayOfByte.length + i > paramInt2)
            {
              if (i > 0) {
                break;
              }
              throw new IOException("insufficient buffer space");
            }
            System.arraycopy(arrayOfByte, 0, paramArrayOfByte, paramInt1 + i, arrayOfByte.length);
            i += arrayOfByte.length;
            localIterator.remove();
          }
        }
      } while (i <= 0);
      return i;
    }
  }
  
  public void addListener(ReliableSocketListener paramReliableSocketListener)
  {
    if (paramReliableSocketListener == null) {
      throw new NullPointerException("listener");
    }
    synchronized (this._listeners)
    {
      if (!this._listeners.contains(paramReliableSocketListener)) {
        this._listeners.add(paramReliableSocketListener);
      }
    }
  }
  
  public void removeListener(ReliableSocketListener paramReliableSocketListener)
  {
    if (paramReliableSocketListener == null) {
      throw new NullPointerException("listener");
    }
    synchronized (this._listeners)
    {
      this._listeners.remove(paramReliableSocketListener);
    }
  }
  
  public void addStateListener(ReliableSocketStateListener paramReliableSocketStateListener)
  {
    if (paramReliableSocketStateListener == null) {
      throw new NullPointerException("stateListener");
    }
    synchronized (this._stateListeners)
    {
      if (!this._stateListeners.contains(paramReliableSocketStateListener)) {
        this._stateListeners.add(paramReliableSocketStateListener);
      }
    }
  }
  
  public void removeStateListener(ReliableSocketStateListener paramReliableSocketStateListener)
  {
    if (paramReliableSocketStateListener == null) {
      throw new NullPointerException("stateListener");
    }
    synchronized (this._stateListeners)
    {
      this._stateListeners.remove(paramReliableSocketStateListener);
    }
  }
  
  private void sendSegment(Segment paramSegment)
    throws IOException
  {
    if (((paramSegment instanceof DATSegment)) || ((paramSegment instanceof RSTSegment)) || ((paramSegment instanceof FINSegment)) || ((paramSegment instanceof NULSegment))) {
      checkAndSetAck(paramSegment);
    }
    if (((paramSegment instanceof DATSegment)) || ((paramSegment instanceof RSTSegment)) || ((paramSegment instanceof FINSegment))) {
      this._nullSegmentTimer.reset();
    }
    if (DEBUG) {
      log("sent " + paramSegment);
    }
    sendSegmentImpl(paramSegment);
  }
  
  private Segment receiveSegment()
    throws IOException
  {
    Segment localSegment;
    if ((localSegment = receiveSegmentImpl()) != null)
    {
      if (DEBUG) {
        log("recv " + localSegment);
      }
      if (((localSegment instanceof DATSegment)) || ((localSegment instanceof NULSegment)) || ((localSegment instanceof RSTSegment)) || ((localSegment instanceof FINSegment)) || ((localSegment instanceof SYNSegment))) {
        this._counters.incCumulativeAckCounter();
      }
      if (this._keepAlive) {
        this._keepAliveTimer.reset();
      }
    }
    return localSegment;
  }
  
  private void sendAndQueueSegment(Segment paramSegment)
    throws IOException
  {
    synchronized (this._unackedSentQueue)
    {
      while ((this._unackedSentQueue.size() >= this._sendQueueSize) || (this._counters.getOutstandingSegsCounter() > this._profile.maxOutstandingSegs())) {
        try
        {
          this._unackedSentQueue.wait();
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
      }
      this._counters.incOutstandingSegsCounter();
      this._unackedSentQueue.add(paramSegment);
    }
    if (this._closed) {
      throw new SocketException("Socket is closed");
    }
    if ((!(paramSegment instanceof EAKSegment)) && (!(paramSegment instanceof ACKSegment))) {
      synchronized (this._retransmissionTimer)
      {
        if (this._retransmissionTimer.isIdle()) {
          this._retransmissionTimer.schedule(this._profile.retransmissionTimeout(), this._profile.retransmissionTimeout());
        }
      }
    }
    sendSegment(paramSegment);
    if ((paramSegment instanceof DATSegment)) {
      synchronized (this._listeners)
      {
        Iterator localIterator = this._listeners.iterator();
        while (localIterator.hasNext())
        {
          ReliableSocketListener localReliableSocketListener = (ReliableSocketListener)localIterator.next();
          localReliableSocketListener.packetSent();
        }
      }
    }
  }
  
  private void retransmitSegment(Segment paramSegment)
    throws IOException
  {
    if (this._profile.maxRetrans() > 0) {
      paramSegment.setRetxCounter(paramSegment.getRetxCounter() + 1);
    }
    if ((this._profile.maxRetrans() != 0) && (paramSegment.getRetxCounter() > this._profile.maxRetrans()))
    {
      connectionFailure();
      return;
    }
    sendSegment(paramSegment);
    if ((paramSegment instanceof DATSegment)) {
      synchronized (this._listeners)
      {
        Iterator localIterator = this._listeners.iterator();
        while (localIterator.hasNext())
        {
          ReliableSocketListener localReliableSocketListener = (ReliableSocketListener)localIterator.next();
          localReliableSocketListener.packetRetransmitted();
        }
      }
    }
  }
  
  private void connectionOpened()
  {
    if (isConnected())
    {
      this._nullSegmentTimer.cancel();
      if (this._keepAlive) {
        this._keepAliveTimer.cancel();
      }
      synchronized (this._resetLock)
      {
        this._reset = false;
        this._resetLock.notify();
      }
    }
    else
    {
      synchronized (this)
      {
        try
        {
          this._in = new ReliableSocketInputStream(this);
          this._out = new ReliableSocketOutputStream(this);
          this._connected = true;
          this._state = 3;
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
        notify();
      }
      synchronized (this._stateListeners)
      {
        Iterator localIterator = this._stateListeners.iterator();
        while (localIterator.hasNext())
        {
          ReliableSocketStateListener localReliableSocketStateListener = (ReliableSocketStateListener)localIterator.next();
          localReliableSocketStateListener.connectionOpened(this);
        }
      }
    }
    this._nullSegmentTimer.schedule(0L, this._profile.nullSegmentTimeout());
    if (this._keepAlive) {
      this._keepAliveTimer.schedule(this._profile.nullSegmentTimeout() * 6, this._profile.nullSegmentTimeout() * 6);
    }
  }
  
  private void connectionRefused()
  {
    synchronized (this._stateListeners)
    {
      Iterator localIterator = this._stateListeners.iterator();
      while (localIterator.hasNext())
      {
        ReliableSocketStateListener localReliableSocketStateListener = (ReliableSocketStateListener)localIterator.next();
        localReliableSocketStateListener.connectionRefused(this);
      }
    }
  }
  
  private void connectionClosed()
  {
    synchronized (this._stateListeners)
    {
      Iterator localIterator = this._stateListeners.iterator();
      while (localIterator.hasNext())
      {
        ReliableSocketStateListener localReliableSocketStateListener = (ReliableSocketStateListener)localIterator.next();
        localReliableSocketStateListener.connectionClosed(this);
      }
    }
  }
  
  private void connectionFailure()
  {
    synchronized (this._closeLock)
    {
      if (isClosed()) {
        return;
      }
      switch (this._state)
      {
      case 2: 
        synchronized (this)
        {
          notify();
        }
        break;
      case 1: 
      case 3: 
      case 4: 
        this._connected = false;
        synchronized (this._unackedSentQueue)
        {
          this._unackedSentQueue.notifyAll();
        }
        synchronized (this._recvQueueLock)
        {
          this._recvQueueLock.notify();
        }
        closeImpl();
      }
      this._state = 0;
      this._closed = true;
    }
    synchronized (this._stateListeners)
    {
      ??? = this._stateListeners.iterator();
      while (((Iterator)???).hasNext())
      {
        ReliableSocketStateListener localReliableSocketStateListener = (ReliableSocketStateListener)((Iterator)???).next();
        localReliableSocketStateListener.connectionFailure(this);
      }
    }
  }
  
  private void connectionReset()
  {
    synchronized (this._stateListeners)
    {
      Iterator localIterator = this._stateListeners.iterator();
      while (localIterator.hasNext())
      {
        ReliableSocketStateListener localReliableSocketStateListener = (ReliableSocketStateListener)localIterator.next();
        localReliableSocketStateListener.connectionReset(this);
      }
    }
  }
  
  private void handleSYNSegment(SYNSegment paramSYNSegment)
  {
    try
    {
      switch (this._state)
      {
      case 0: 
        this._counters.setLastInSequence(paramSYNSegment.seq());
        this._state = 1;
        Random localRandom = new Random(System.currentTimeMillis());
        this._profile = new ReliableSocketProfile(this._sendQueueSize, this._recvQueueSize, paramSYNSegment.getMaxSegmentSize(), paramSYNSegment.getMaxOutstandingSegments(), paramSYNSegment.getMaxRetransmissions(), paramSYNSegment.getMaxCumulativeAcks(), paramSYNSegment.getMaxOutOfSequence(), paramSYNSegment.getMaxAutoReset(), paramSYNSegment.getNulSegmentTimeout(), paramSYNSegment.getRetransmissionTimeout(), paramSYNSegment.getCummulativeAckTimeout());
        SYNSegment localSYNSegment = new SYNSegment(this._counters.setSequenceNumber(localRandom.nextInt(255)), this._profile.maxOutstandingSegs(), this._profile.maxSegmentSize(), this._profile.retransmissionTimeout(), this._profile.cumulativeAckTimeout(), this._profile.nullSegmentTimeout(), this._profile.maxRetrans(), this._profile.maxCumulativeAcks(), this._profile.maxOutOfSequence(), this._profile.maxAutoReset());
        localSYNSegment.setAck(paramSYNSegment.seq());
        sendAndQueueSegment(localSYNSegment);
        break;
      case 2: 
        this._counters.setLastInSequence(paramSYNSegment.seq());
        this._state = 3;
        sendAck();
        connectionOpened();
      }
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  private void handleEAKSegment(EAKSegment paramEAKSegment)
  {
    int[] arrayOfInt = paramEAKSegment.getACKs();
    int i = paramEAKSegment.getAck();
    int j = arrayOfInt[(arrayOfInt.length - 1)];
    synchronized (this._unackedSentQueue)
    {
      Iterator localIterator = this._unackedSentQueue.iterator();
      Segment localSegment;
      while (localIterator.hasNext())
      {
        localSegment = (Segment)localIterator.next();
        if (compareSequenceNumbers(localSegment.seq(), i) <= 0) {
          localIterator.remove();
        } else {
          for (int k = 0; k < arrayOfInt.length; k++) {
            if (compareSequenceNumbers(localSegment.seq(), arrayOfInt[k]) == 0)
            {
              localIterator.remove();
              break;
            }
          }
        }
      }
      localIterator = this._unackedSentQueue.iterator();
      while (localIterator.hasNext())
      {
        localSegment = (Segment)localIterator.next();
        if ((compareSequenceNumbers(i, localSegment.seq()) < 0) && (compareSequenceNumbers(j, localSegment.seq()) > 0)) {
          try
          {
            retransmitSegment(localSegment);
          }
          catch (IOException localIOException)
          {
            localIOException.printStackTrace();
          }
        }
      }
      this._unackedSentQueue.notifyAll();
    }
  }
  
  private void handleSegment(Segment paramSegment)
  {
    if ((paramSegment instanceof RSTSegment))
    {
      synchronized (this._resetLock)
      {
        this._reset = true;
      }
      connectionReset();
    }
    if ((paramSegment instanceof FINSegment)) {
      switch (this._state)
      {
      case 2: 
        synchronized (this)
        {
          notify();
        }
        break;
      case 0: 
        break;
      default: 
        this._state = 4;
      }
    }
    int i = 0;
    synchronized (this._recvQueueLock)
    {
      if (compareSequenceNumbers(paramSegment.seq(), this._counters.getLastInSequence()) > 0)
      {
        Object localObject3;
        if (compareSequenceNumbers(paramSegment.seq(), nextSequenceNumber(this._counters.getLastInSequence())) == 0)
        {
          i = 1;
          if ((this._inSeqRecvQueue.size() == 0) || (this._inSeqRecvQueue.size() + this._outSeqRecvQueue.size() < this._recvQueueSize))
          {
            this._counters.setLastInSequence(paramSegment.seq());
            if (((paramSegment instanceof DATSegment)) || ((paramSegment instanceof RSTSegment)) || ((paramSegment instanceof FINSegment))) {
              this._inSeqRecvQueue.add(paramSegment);
            }
            if ((paramSegment instanceof DATSegment)) {
              synchronized (this._listeners)
              {
                Iterator localIterator = this._listeners.iterator();
                while (localIterator.hasNext())
                {
                  localObject3 = (ReliableSocketListener)localIterator.next();
                  ((ReliableSocketListener)localObject3).packetReceivedInOrder();
                }
              }
            }
            checkRecvQueues();
          }
        }
        else if (this._inSeqRecvQueue.size() + this._outSeqRecvQueue.size() < this._recvQueueSize)
        {
          int j = 0;
          for (int k = 0; (k < this._outSeqRecvQueue.size()) && (j == 0); k++)
          {
            localObject3 = (Segment)this._outSeqRecvQueue.get(k);
            int m = compareSequenceNumbers(paramSegment.seq(), ((Segment)localObject3).seq());
            if (m == 0)
            {
              j = 1;
            }
            else if (m < 0)
            {
              this._outSeqRecvQueue.add(k, paramSegment);
              j = 1;
            }
          }
          if (j == 0) {
            this._outSeqRecvQueue.add(paramSegment);
          }
          this._counters.incOutOfSequenceCounter();
          if ((paramSegment instanceof DATSegment)) {
            synchronized (this._listeners)
            {
              localObject3 = this._listeners.iterator();
              while (((Iterator)localObject3).hasNext())
              {
                ReliableSocketListener localReliableSocketListener = (ReliableSocketListener)((Iterator)localObject3).next();
                localReliableSocketListener.packetReceivedOutOfOrder();
              }
            }
          }
        }
      }
      if ((i != 0) && (((paramSegment instanceof RSTSegment)) || ((paramSegment instanceof NULSegment)) || ((paramSegment instanceof FINSegment)))) {
        sendAck();
      } else if ((this._counters.getOutOfSequenceCounter() > 0) && ((this._profile.maxOutOfSequence() == 0) || (this._counters.getOutOfSequenceCounter() > this._profile.maxOutOfSequence()))) {
        sendExtendedAck();
      } else if ((this._counters.getCumulativeAckCounter() > 0) && ((this._profile.maxCumulativeAcks() == 0) || (this._counters.getCumulativeAckCounter() > this._profile.maxCumulativeAcks()))) {
        sendSingleAck();
      } else {
        synchronized (this._cumulativeAckTimer)
        {
          if (this._cumulativeAckTimer.isIdle()) {
            this._cumulativeAckTimer.schedule(this._profile.cumulativeAckTimeout());
          }
        }
      }
    }
  }
  
  private void sendAck()
  {
    synchronized (this._recvQueueLock)
    {
      if (!this._outSeqRecvQueue.isEmpty())
      {
        sendExtendedAck();
        return;
      }
      sendSingleAck();
    }
  }
  
  private void sendExtendedAck()
  {
    synchronized (this._recvQueueLock)
    {
      if (this._outSeqRecvQueue.isEmpty()) {
        return;
      }
      this._counters.getAndResetCumulativeAckCounter();
      this._counters.getAndResetOutOfSequenceCounter();
      int[] arrayOfInt = new int[this._outSeqRecvQueue.size()];
      for (int i = 0; i < arrayOfInt.length; i++)
      {
        Segment localSegment = (Segment)this._outSeqRecvQueue.get(i);
        arrayOfInt[i] = localSegment.seq();
      }
      try
      {
        i = this._counters.getLastInSequence();
        sendSegment(new EAKSegment(nextSequenceNumber(i), i, arrayOfInt));
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  private void sendSingleAck()
  {
    if (this._counters.getAndResetCumulativeAckCounter() == 0) {
      return;
    }
    try
    {
      int i = this._counters.getLastInSequence();
      sendSegment(new ACKSegment(nextSequenceNumber(i), i));
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  private void checkAndSetAck(Segment paramSegment)
  {
    if (this._counters.getAndResetCumulativeAckCounter() == 0) {
      return;
    }
    paramSegment.setAck(this._counters.getLastInSequence());
  }
  
  private void checkAndGetAck(Segment paramSegment)
  {
    int i = paramSegment.getAck();
    if (i < 0) {
      return;
    }
    this._counters.getAndResetOutstandingSegsCounter();
    if (this._state == 1)
    {
      this._state = 3;
      connectionOpened();
    }
    synchronized (this._unackedSentQueue)
    {
      Iterator localIterator = this._unackedSentQueue.iterator();
      while (localIterator.hasNext())
      {
        Segment localSegment = (Segment)localIterator.next();
        if (compareSequenceNumbers(localSegment.seq(), i) <= 0) {
          localIterator.remove();
        }
      }
      if (this._unackedSentQueue.isEmpty()) {
        this._retransmissionTimer.cancel();
      }
      this._unackedSentQueue.notifyAll();
    }
  }
  
  private void checkRecvQueues()
  {
    synchronized (this._recvQueueLock)
    {
      Iterator localIterator = this._outSeqRecvQueue.iterator();
      while (localIterator.hasNext())
      {
        Segment localSegment = (Segment)localIterator.next();
        if (compareSequenceNumbers(localSegment.seq(), nextSequenceNumber(this._counters.getLastInSequence())) == 0)
        {
          this._counters.setLastInSequence(localSegment.seq());
          if (((localSegment instanceof DATSegment)) || ((localSegment instanceof RSTSegment)) || ((localSegment instanceof FINSegment))) {
            this._inSeqRecvQueue.add(localSegment);
          }
          localIterator.remove();
        }
      }
      this._recvQueueLock.notify();
    }
  }
  
  protected void sendSegmentImpl(Segment paramSegment)
    throws IOException
  {
    try
    {
      DatagramPacket localDatagramPacket = new DatagramPacket(paramSegment.getBytes(), paramSegment.length(), this._endpoint);
      this._sock.send(localDatagramPacket);
    }
    catch (IOException localIOException)
    {
      if (!isClosed()) {
        localIOException.printStackTrace();
      }
    }
  }
  
  protected Segment receiveSegmentImpl()
    throws IOException
  {
    try
    {
      DatagramPacket localDatagramPacket = new DatagramPacket(this._recvbuffer, this._recvbuffer.length);
      this._sock.receive(localDatagramPacket);
      return Segment.parse(localDatagramPacket.getData(), 0, localDatagramPacket.getLength());
    }
    catch (IOException localIOException)
    {
      if (!isClosed()) {
        localIOException.printStackTrace();
      }
    }
    return null;
  }
  
  protected void closeSocket()
  {
    this._sock.close();
  }
  
  protected void closeImpl()
  {
    this._nullSegmentTimer.cancel();
    this._keepAliveTimer.cancel();
    this._state = 4;
    Thread local1 = new Thread()
    {
      public void run()
      {
        ReliableSocket.this._keepAliveTimer.destroy();
        ReliableSocket.this._nullSegmentTimer.destroy();
        try
        {
          Thread.sleep(ReliableSocket.this._profile.nullSegmentTimeout() * 2);
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
        ReliableSocket.this._retransmissionTimer.destroy();
        ReliableSocket.this._cumulativeAckTimer.destroy();
        ReliableSocket.this.closeSocket();
        ReliableSocket.this.connectionClosed();
      }
    };
    local1.setName("ReliableSocket-Closing");
    local1.setDaemon(true);
    local1.start();
  }
  
  protected void log(String paramString)
  {
    System.out.println(getLocalPort() + ": " + paramString);
  }
  
  private static int nextSequenceNumber(int paramInt)
  {
    return (paramInt + 1) % 255;
  }
  
  private int compareSequenceNumbers(int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2) {
      return 0;
    }
    if (((paramInt1 < paramInt2) && (paramInt2 - paramInt1 > 127)) || ((paramInt1 > paramInt2) && (paramInt1 - paramInt2 < 127))) {
      return 1;
    }
    return -1;
  }
  
  private class ShutdownHook
    extends Thread
  {
    public ShutdownHook()
    {
      super();
    }
    
    public void run()
    {
      try
      {
        switch (ReliableSocket.this._state)
        {
        case 0: 
          return;
        }
        ReliableSocket.this.sendSegment(new FINSegment(ReliableSocket.this._counters.nextSequenceNumber()));
      }
      catch (Throwable localThrowable) {}
    }
  }
  
  private class KeepAliveTimerTask
    implements Runnable
  {
    private KeepAliveTimerTask() {}
    
    public void run()
    {
      ReliableSocket.this.connectionFailure();
    }
  }
  
  private class CumulativeAckTimerTask
    implements Runnable
  {
    private CumulativeAckTimerTask() {}
    
    public void run()
    {
      ReliableSocket.this.sendAck();
    }
  }
  
  private class RetransmissionTimerTask
    implements Runnable
  {
    private RetransmissionTimerTask() {}
    
    public void run()
    {
      synchronized (ReliableSocket.this._unackedSentQueue)
      {
        Iterator localIterator = ReliableSocket.this._unackedSentQueue.iterator();
        while (localIterator.hasNext())
        {
          Segment localSegment = (Segment)localIterator.next();
          try
          {
            ReliableSocket.this.retransmitSegment(localSegment);
          }
          catch (IOException localIOException)
          {
            localIOException.printStackTrace();
          }
        }
      }
    }
  }
  
  private class NullSegmentTimerTask
    implements Runnable
  {
    private NullSegmentTimerTask() {}
    
    public void run()
    {
      synchronized (ReliableSocket.this._unackedSentQueue)
      {
        if (ReliableSocket.this._unackedSentQueue.isEmpty()) {
          try
          {
            ReliableSocket.this.sendAndQueueSegment(new NULSegment(ReliableSocket.this._counters.nextSequenceNumber()));
          }
          catch (IOException localIOException)
          {
            if (ReliableSocket.DEBUG) {
              localIOException.printStackTrace();
            }
          }
        }
      }
    }
  }
  
  private class ReliableSocketThread
    extends Thread
  {
    public ReliableSocketThread()
    {
      super();
      setDaemon(true);
    }
    
    public void run()
    {
      try
      {
        Segment localSegment;
        while ((localSegment = ReliableSocket.this.receiveSegment()) != null)
        {
          if ((localSegment instanceof SYNSegment)) {
            ReliableSocket.this.handleSYNSegment((SYNSegment)localSegment);
          } else if ((localSegment instanceof EAKSegment)) {
            ReliableSocket.this.handleEAKSegment((EAKSegment)localSegment);
          } else if (!(localSegment instanceof ACKSegment)) {
            ReliableSocket.this.handleSegment(localSegment);
          }
          ReliableSocket.this.checkAndGetAck(localSegment);
        }
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  private class Counters
  {
    private int _seqn;
    private int _lastInSequence;
    private int _cumAckCounter;
    private int _outOfSeqCounter;
    private int _outSegsCounter;
    
    public Counters() {}
    
    public synchronized int nextSequenceNumber()
    {
      return this._seqn = ReliableSocket.nextSequenceNumber(this._seqn);
    }
    
    public synchronized int setSequenceNumber(int paramInt)
    {
      this._seqn = paramInt;
      return this._seqn;
    }
    
    public synchronized int setLastInSequence(int paramInt)
    {
      this._lastInSequence = paramInt;
      return this._lastInSequence;
    }
    
    public synchronized int getLastInSequence()
    {
      return this._lastInSequence;
    }
    
    public synchronized void incCumulativeAckCounter()
    {
      this._cumAckCounter += 1;
    }
    
    public synchronized int getCumulativeAckCounter()
    {
      return this._cumAckCounter;
    }
    
    public synchronized int getAndResetCumulativeAckCounter()
    {
      int i = this._cumAckCounter;
      this._cumAckCounter = 0;
      return i;
    }
    
    public synchronized void incOutOfSequenceCounter()
    {
      this._outOfSeqCounter += 1;
    }
    
    public synchronized int getOutOfSequenceCounter()
    {
      return this._outOfSeqCounter;
    }
    
    public synchronized int getAndResetOutOfSequenceCounter()
    {
      int i = this._outOfSeqCounter;
      this._outOfSeqCounter = 0;
      return i;
    }
    
    public synchronized void incOutstandingSegsCounter()
    {
      this._outSegsCounter += 1;
    }
    
    public synchronized int getOutstandingSegsCounter()
    {
      return this._outSegsCounter;
    }
    
    public synchronized int getAndResetOutstandingSegsCounter()
    {
      int i = this._outSegsCounter;
      this._outSegsCounter = 0;
      return i;
    }
    
    public synchronized void reset()
    {
      this._outOfSeqCounter = 0;
      this._outSegsCounter = 0;
      this._cumAckCounter = 0;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.ReliableSocket
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */