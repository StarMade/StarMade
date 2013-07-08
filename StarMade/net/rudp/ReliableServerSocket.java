package net.rudp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import net.rudp.impl.SYNSegment;
import net.rudp.impl.Segment;

public class ReliableServerSocket
  extends ServerSocket
{
  private DatagramSocket _serverSock;
  private int _timeout;
  private int _backlogSize;
  private boolean _closed;
  private ArrayList _backlog;
  private HashMap _clientSockTable;
  private ReliableSocketStateListener _stateListener;
  private static final int DEFAULT_BACKLOG_SIZE = 50;
  
  public ReliableServerSocket()
    throws IOException
  {
    this(0, 0, null);
  }
  
  public ReliableServerSocket(int paramInt)
    throws IOException
  {
    this(paramInt, 0, null);
  }
  
  public ReliableServerSocket(int paramInt1, int paramInt2)
    throws IOException
  {
    this(paramInt1, paramInt2, null);
  }
  
  public ReliableServerSocket(int paramInt1, int paramInt2, InetAddress paramInetAddress)
    throws IOException
  {
    this(new DatagramSocket(new InetSocketAddress(paramInetAddress, paramInt1)), paramInt2);
  }
  
  public ReliableServerSocket(DatagramSocket paramDatagramSocket, int paramInt)
    throws IOException
  {
    if (paramDatagramSocket == null) {
      throw new NullPointerException("sock");
    }
    this._serverSock = paramDatagramSocket;
    this._backlogSize = (paramInt <= 0 ? 50 : paramInt);
    this._backlog = new ArrayList(this._backlogSize);
    this._clientSockTable = new HashMap();
    this._stateListener = new StateListener(null);
    this._timeout = 0;
    this._closed = false;
    new ReceiverThread().start();
  }
  
  public Socket accept()
    throws IOException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    synchronized (this._backlog)
    {
      while (this._backlog.isEmpty())
      {
        try
        {
          if (this._timeout == 0)
          {
            this._backlog.wait();
          }
          else
          {
            long l = System.currentTimeMillis();
            this._backlog.wait(this._timeout);
            if (System.currentTimeMillis() - l >= this._timeout) {
              throw new SocketTimeoutException();
            }
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
        if (isClosed()) {
          throw new IOException();
        }
      }
      return (Socket)this._backlog.remove(0);
    }
  }
  
  public synchronized void bind(SocketAddress paramSocketAddress)
    throws IOException
  {
    bind(paramSocketAddress, 0);
  }
  
  public synchronized void bind(SocketAddress paramSocketAddress, int paramInt)
    throws IOException
  {
    if (isClosed()) {
      throw new SocketException("Socket is closed");
    }
    this._serverSock.bind(paramSocketAddress);
  }
  
  public synchronized void close()
  {
    if (isClosed()) {
      return;
    }
    this._closed = true;
    synchronized (this._backlog)
    {
      this._backlog.clear();
      this._backlog.notify();
    }
    if (this._clientSockTable.isEmpty()) {
      this._serverSock.close();
    }
  }
  
  public InetAddress getInetAddress()
  {
    return this._serverSock.getInetAddress();
  }
  
  public int getLocalPort()
  {
    return this._serverSock.getLocalPort();
  }
  
  public SocketAddress getLocalSocketAddress()
  {
    return this._serverSock.getLocalSocketAddress();
  }
  
  public boolean isBound()
  {
    return this._serverSock.isBound();
  }
  
  public boolean isClosed()
  {
    return this._closed;
  }
  
  public void setSoTimeout(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("timeout < 0");
    }
    this._timeout = paramInt;
  }
  
  public int getSoTimeout()
  {
    return this._timeout;
  }
  
  private ReliableClientSocket addClientSocket(SocketAddress paramSocketAddress)
  {
    synchronized (this._clientSockTable)
    {
      ReliableClientSocket localReliableClientSocket = (ReliableClientSocket)this._clientSockTable.get(paramSocketAddress);
      if (localReliableClientSocket == null) {
        try
        {
          localReliableClientSocket = new ReliableClientSocket(this._serverSock, paramSocketAddress);
          localReliableClientSocket.addStateListener(this._stateListener);
          this._clientSockTable.put(paramSocketAddress, localReliableClientSocket);
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
      }
      return localReliableClientSocket;
    }
  }
  
  private ReliableClientSocket removeClientSocket(SocketAddress paramSocketAddress)
  {
    synchronized (this._clientSockTable)
    {
      ReliableClientSocket localReliableClientSocket = (ReliableClientSocket)this._clientSockTable.remove(paramSocketAddress);
      if ((this._clientSockTable.isEmpty()) && (isClosed())) {
        this._serverSock.close();
      }
      return localReliableClientSocket;
    }
  }
  
  private class StateListener
    implements ReliableSocketStateListener
  {
    private StateListener() {}
    
    public void connectionOpened(ReliableSocket paramReliableSocket)
    {
      if ((paramReliableSocket instanceof ReliableServerSocket.ReliableClientSocket)) {
        synchronized (ReliableServerSocket.this._backlog)
        {
          while (ReliableServerSocket.this._backlog.size() > 50) {
            try
            {
              ReliableServerSocket.this._backlog.wait();
            }
            catch (InterruptedException localInterruptedException)
            {
              localInterruptedException.printStackTrace();
            }
          }
          ReliableServerSocket.this._backlog.add(paramReliableSocket);
          ReliableServerSocket.this._backlog.notify();
        }
      }
    }
    
    public void connectionRefused(ReliableSocket paramReliableSocket) {}
    
    public void connectionClosed(ReliableSocket paramReliableSocket)
    {
      if ((paramReliableSocket instanceof ReliableServerSocket.ReliableClientSocket)) {
        ReliableServerSocket.this.removeClientSocket(paramReliableSocket.getRemoteSocketAddress());
      }
    }
    
    public void connectionFailure(ReliableSocket paramReliableSocket)
    {
      if ((paramReliableSocket instanceof ReliableServerSocket.ReliableClientSocket)) {
        ReliableServerSocket.this.removeClientSocket(paramReliableSocket.getRemoteSocketAddress());
      }
    }
    
    public void connectionReset(ReliableSocket paramReliableSocket) {}
  }
  
  private class ReliableClientSocket
    extends ReliableSocket
  {
    private ArrayList _queue;
    
    public ReliableClientSocket(DatagramSocket paramDatagramSocket, SocketAddress paramSocketAddress)
      throws IOException
    {
      super();
      this._endpoint = paramSocketAddress;
    }
    
    protected void init(DatagramSocket paramDatagramSocket, ReliableSocketProfile paramReliableSocketProfile)
    {
      this._queue = new ArrayList();
      super.init(paramDatagramSocket, paramReliableSocketProfile);
    }
    
    protected Segment receiveSegmentImpl()
    {
      synchronized (this._queue)
      {
        while (this._queue.isEmpty()) {
          try
          {
            this._queue.wait();
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
        return (Segment)this._queue.remove(0);
      }
    }
    
    protected void segmentReceived(Segment paramSegment)
    {
      synchronized (this._queue)
      {
        this._queue.add(paramSegment);
        this._queue.notify();
      }
    }
    
    protected void closeSocket()
    {
      synchronized (this._queue)
      {
        this._queue.clear();
        this._queue.add(null);
        this._queue.notify();
      }
    }
    
    protected void log(String paramString)
    {
      System.out.println(getPort() + ": " + paramString);
    }
  }
  
  private class ReceiverThread
    extends Thread
  {
    public ReceiverThread()
    {
      super();
      setDaemon(true);
    }
    
    public void run()
    {
      byte[] arrayOfByte = new byte[65535];
      for (;;)
      {
        DatagramPacket localDatagramPacket = new DatagramPacket(arrayOfByte, arrayOfByte.length);
        ReliableServerSocket.ReliableClientSocket localReliableClientSocket = null;
        try
        {
          ReliableServerSocket.this._serverSock.receive(localDatagramPacket);
          SocketAddress localSocketAddress = localDatagramPacket.getSocketAddress();
          Segment localSegment = Segment.parse(localDatagramPacket.getData(), 0, localDatagramPacket.getLength());
          synchronized (ReliableServerSocket.this._clientSockTable)
          {
            if ((!ReliableServerSocket.this.isClosed()) && ((localSegment instanceof SYNSegment)) && (!ReliableServerSocket.this._clientSockTable.containsKey(localSocketAddress))) {
              localReliableClientSocket = ReliableServerSocket.this.addClientSocket(localSocketAddress);
            }
            localReliableClientSocket = (ReliableServerSocket.ReliableClientSocket)ReliableServerSocket.this._clientSockTable.get(localSocketAddress);
          }
          if (localReliableClientSocket != null) {
            localReliableClientSocket.segmentReceived(localSegment);
          }
        }
        catch (IOException localIOException)
        {
          if (!ReliableServerSocket.this.isClosed()) {
            break label161;
          }
        }
        break;
        label161:
        localIOException.printStackTrace();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.ReliableServerSocket
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */