package org.schema.schine.network.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Observable;
import java.util.Observer;
import net.rudp.ReliableServerSocket;
import org.schema.schine.network.exception.ServerPortNotAvailableException;

public class ServerListener
  extends Observable
  implements Runnable
{
  private final int port;
  private final ServerSocket serverSocket;
  private final ServerStateInterface state;
  private boolean listening;
  private final String host;
  
  public ServerListener(String paramString, int paramInt, ServerStateInterface paramServerStateInterface)
  {
    this.host = paramString;
    this.port = paramInt;
    this.state = paramServerStateInterface;
    int i = 0;
    boolean bool = false;
    try
    {
      (paramString = new Socket(paramString, this.port)).setSoTimeout(3000);
      DataOutputStream localDataOutputStream = new DataOutputStream(new BufferedOutputStream(paramString.getOutputStream()));
      DataInputStream localDataInputStream = new DataInputStream(new BufferedInputStream(paramString.getInputStream()));
      localDataOutputStream.writeInt(1);
      localDataOutputStream.write(100);
      localDataOutputStream.flush();
      if ((byte)localDataInputStream.read() == 100)
      {
        bool = true;
        System.out.println("[SERVER] A schine server is already running");
      }
      paramString.shutdownInput();
      paramString.shutdownOutput();
      paramString.close();
    }
    catch (Exception localException)
    {
      if (((paramString = localException) instanceof SocketTimeoutException)) {
        paramString.printStackTrace();
      } else {
        i = 1;
      }
    }
    if ((i != 0) && (!bool))
    {
      System.err.println("[SERVER] port " + paramInt + " is open");
      if ((paramServerStateInterface.getAcceptingIP() == null) || (paramServerStateInterface.getAcceptingIP().equals("all")))
      {
        if (paramServerStateInterface.useUDP())
        {
          System.err.println("[SERVER] USING UDP... ");
          this.serverSocket = new ReliableServerSocket(this.port, 0);
          return;
        }
        System.err.println("[SERVER] USING TCP... ");
        this.serverSocket = new ServerSocket(this.port, 0);
        return;
      }
      if (paramServerStateInterface.useUDP())
      {
        System.err.println("[SERVER] USING UDP... LISTENING ON " + paramServerStateInterface.getAcceptingIP());
        this.serverSocket = new ReliableServerSocket(this.port, 0, InetAddress.getByName(paramServerStateInterface.getAcceptingIP()));
        return;
      }
      System.err.println("[SERVER] USING TCP... LISTENING ON " + paramServerStateInterface.getAcceptingIP());
      this.serverSocket = new ServerSocket(this.port, 0, InetAddress.getByName(paramServerStateInterface.getAcceptingIP()));
      return;
    }
    System.err.println("THROWING EXCEPTION");
    (paramString = new ServerPortNotAvailableException("localhost port " + this.port + " is closed or already in use")).setInstanceRunning(bool);
    throw paramString;
  }
  
  public boolean isListening()
  {
    return this.listening;
  }
  
  public void run()
  {
    System.err.println("[ServerListener] Server initialization OK... now waiting for connections");
    try
    {
      for (;;)
      {
        if (this.serverSocket.isClosed()) {
          System.err.println("server socket is closed!");
        }
        this.listening = true;
        this.serverSocket.setPerformancePreferences(0, 2, 1);
        this.serverSocket.setReceiveBufferSize(this.state.getSocketBufferSize());
        Object localObject = this.serverSocket.accept();
        System.err.println("connection made. starting new processor " + ((Socket)localObject).getPort() + ", " + ((Socket)localObject).getInetAddress() + "; local: " + ((Socket)localObject).getLocalPort() + ", " + ((Socket)localObject).getLocalAddress() + ", keepalive " + ((Socket)localObject).getKeepAlive());
        ((Socket)localObject).setKeepAlive(true);
        if (!this.state.useUDP()) {
          ((Socket)localObject).setTcpNoDelay(this.state.tcpNoDelay());
        }
        ((Socket)localObject).setTrafficClass(24);
        ((Socket)localObject).setSendBufferSize(this.state.getSocketBufferSize());
        localObject = new ServerProcessor((Socket)localObject, this.state);
        if ((this.state.getController() instanceof Observer)) {
          ((ServerProcessor)localObject).addObserver((Observer)this.state.getController());
        }
        Thread localThread = new Thread((Runnable)localObject);
        ((ServerProcessor)localObject).setThread(localThread);
        localThread.setName("SERVER-Listener Thread (unknownId)");
        localThread.start();
        setChanged();
        notifyObservers(localObject);
        System.out.println("[SERVER] connection registered");
      }
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  public String getHost()
  {
    return this.host;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.server.ServerListener
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */