package org.schema.schine.network.server;

import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import org.schema.schine.network.Command;
import org.schema.schine.network.CommandMap;
import org.schema.schine.network.Header;
import org.schema.schine.network.NetUtil;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.Pinger;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.StateInterface;

public class ServerProcessor
  extends Pinger
  implements Runnable, NetworkProcessor
{
  Socket commandSocket;
  private ServerStateInterface state;
  private RegisteredClientOnServer client;
  public static int connections;
  private long heartBeatTimeStamp;
  private long heartBeatTimeStampTimeoutHelper;
  Boolean waitingForPong = Boolean.valueOf(false);
  private Object lock = new Object();
  private static final int heartbeatTimeOut = 10000;
  private long connectionStartTime;
  private boolean pong;
  private boolean connected = false;
  private static final int MAX_PING_RETRYS = 12;
  private int pingRetrys = 12;
  private long pingTime;
  private DataInputStream dataInputStream;
  private DataOutputStream out;
  private FastByteArrayOutputStream byteArrayOutDoubleBuffer;
  private DataOutputStream outDoubleBuffer;
  public static int MAX_PACKET_SIZE = 20480;
  private static final int MAX_PACKET_POOL_SIZE = 1000;
  private DataInputStream inDoubleBuffer;
  byte[] receiveBuffer = new byte[MAX_PACKET_SIZE];
  private boolean ping;
  private Header tmpHeader;
  private Thread thread;
  private ServerProcessor.ServerPing serverPing;
  private Thread serverPingThread;
  private boolean stopTransmit;
  private final ArrayList sendingQueue = new ArrayList();
  private ServerProcessor.SendingQueueThread sendingQueueThread;
  private boolean disconnectAfterSent;
  public static int totalPackagesQueued;
  private static final ArrayList packetPool = new ArrayList();
  public long field_392 = 0L;
  
  public ServerProcessor(Socket paramSocket, ServerStateInterface paramServerStateInterface)
  {
    this.commandSocket = paramSocket;
    this.state = paramServerStateInterface;
    this.heartBeatTimeStamp = System.currentTimeMillis();
    this.heartBeatTimeStampTimeoutHelper = System.currentTimeMillis();
    this.connectionStartTime = System.currentTimeMillis();
    this.outDoubleBuffer = new DataOutputStream(this.byteArrayOutDoubleBuffer = new FastByteArrayOutputStream(102400));
  }
  
  private boolean checkPing(InputStream paramInputStream)
  {
    if ((paramInputStream = (byte)paramInputStream.read()) == -1)
    {
      this.serverPing.sendPong();
      return true;
    }
    if (paramInputStream == -2)
    {
      setPingTime(System.currentTimeMillis() - this.heartBeatTimeStamp);
      this.waitingForPong = Boolean.valueOf(false);
      if (this.pingRetrys != 12) {
        System.err.println("[SERVER][WARNING] Recovered Ping for " + this.client + "; Retries left: " + this.pingRetrys + "; retries resetting");
      }
      this.pingRetrys = 12;
      setChanged();
      notifyObservers();
      return true;
    }
    return false;
  }
  
  public void closeSocket()
  {
    this.commandSocket.close();
  }
  
  public void disconnectDelayed()
  {
    new ServerProcessor.1(this).start();
  }
  
  public void disconnect()
  {
    this.connected = false;
    setStopTransmit(true);
    synchronized (this.sendingQueue)
    {
      this.sendingQueue.notify();
    }
    try
    {
      closeSocket();
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    deleteObservers();
  }
  
  public static FastByteArrayOutputStream getNewPacket()
  {
    synchronized (packetPool)
    {
      if (!packetPool.isEmpty()) {
        return (FastByteArrayOutputStream)packetPool.remove(0);
      }
    }
    return new FastByteArrayOutputStream(MAX_PACKET_SIZE);
  }
  
  public static void releasePacket(FastByteArrayOutputStream paramFastByteArrayOutputStream)
  {
    paramFastByteArrayOutputStream.reset();
    synchronized (packetPool)
    {
      if (packetPool.size() < 1000) {
        packetPool.add(paramFastByteArrayOutputStream);
      }
      return;
    }
  }
  
  public void flushDoubleOutBuffer()
  {
    assert (this.byteArrayOutDoubleBuffer.position() > 0L);
    FastByteArrayOutputStream localFastByteArrayOutputStream;
    (localFastByteArrayOutputStream = getNewPacket()).write(this.byteArrayOutDoubleBuffer.array, 0, (int)this.byteArrayOutDoubleBuffer.position());
    resetDoubleOutBuffer();
    synchronized (this.sendingQueue)
    {
      if (this.connected)
      {
        this.sendingQueue.add(localFastByteArrayOutputStream);
        totalPackagesQueued += 1;
        this.sendingQueue.notify();
      }
      return;
    }
  }
  
  public void sendPacket(FastByteArrayOutputStream paramFastByteArrayOutputStream)
  {
    assert (paramFastByteArrayOutputStream.position() > 0L);
    System.currentTimeMillis();
    this.out.writeInt((int)paramFastByteArrayOutputStream.position());
    this.out.writeLong(System.currentTimeMillis());
    this.out.write(paramFastByteArrayOutputStream.array, 0, (int)paramFastByteArrayOutputStream.position());
  }
  
  public RegisteredClientOnServer getClient()
  {
    return this.client;
  }
  
  public InetAddress getClientIp()
  {
    return this.commandSocket.getInetAddress();
  }
  
  public long getConnectionTime()
  {
    return System.currentTimeMillis() - this.connectionStartTime;
  }
  
  public int getCurrentSize()
  {
    return (int)this.byteArrayOutDoubleBuffer.position();
  }
  
  public DataInputStream getIn()
  {
    return this.inDoubleBuffer;
  }
  
  public InputStream getInRaw()
  {
    return this.commandSocket.getInputStream();
  }
  
  public String getIp()
  {
    if (this.commandSocket.isConnected()) {
      return this.commandSocket.getRemoteSocketAddress().toString();
    }
    return "n/a";
  }
  
  public Object getLock()
  {
    return this.lock;
  }
  
  public DataOutputStream getOut()
  {
    return this.outDoubleBuffer;
  }
  
  public OutputStream getOutRaw()
  {
    return this.commandSocket.getOutputStream();
  }
  
  public long getPingTime()
  {
    return this.pingTime;
  }
  
  public int getPort()
  {
    if (this.commandSocket.isConnected()) {
      return this.commandSocket.getLocalPort();
    }
    return -1;
  }
  
  public StateInterface getState()
  {
    return this.state;
  }
  
  public boolean isAlive()
  {
    return this.thread.isAlive();
  }
  
  public void notifyPacketReceived(short paramShort, Command paramCommand) {}
  
  private void onError()
  {
    deleteObservers();
    if (getClient() != null) {
      this.state.getController().unregister(getClient().getId());
    } else {
      System.err.println("COULD NOT UNREGISTER CLIENT " + getClient());
    }
    if (!this.commandSocket.isClosed()) {
      this.commandSocket.close();
    }
    connections -= 1;
    if (getClient() == null) {
      return;
    }
    System.err.println("[Server] Client <" + getClient().getId() + "> logged out from server. connections: " + connections);
    if (!this.commandSocket.isClosed()) {
      System.err.println("[Server] ERROR: socket still open!");
    }
  }
  
  private void parseNextPacket(DataInputStream paramDataInputStream)
  {
    if ((getClient() != null) && (!getClient().isConnected()))
    {
      this.connected = false;
      System.err.println("ERROR: client not connected!");
      onError();
      return;
    }
    this.tmpHeader.read(paramDataInputStream);
    if (this.tmpHeader.getType() == 111)
    {
      Object[] arrayOfObject = NetUtil.commands.getById(this.tmpHeader.getCommandId()).readParameters(this.tmpHeader, paramDataInputStream);
      NetUtil.commands.getById(this.tmpHeader.getCommandId()).serverProcess(this, arrayOfObject, this.state, this.tmpHeader.packetId);
    }
    else if (this.tmpHeader.getType() == 123)
    {
      NetUtil.commands.getById(this.tmpHeader.getCommandId()).serverProcess(this, null, this.state, this.tmpHeader.packetId);
    }
    paramDataInputStream.available();
  }
  
  public void resetDoubleOutBuffer()
  {
    this.outDoubleBuffer.flush();
    this.byteArrayOutDoubleBuffer.reset();
  }
  
  public void run()
  {
    connections += 1;
    while (!this.commandSocket.isConnected()) {
      try
      {
        System.err.println("[SERVER] waiting for socket to connect: " + this.commandSocket);
        Thread.sleep(100L);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
    }
    int i = 0;
    try
    {
      this.connected = true;
      setup();
      System.currentTimeMillis();
      System.out.println("[SERVER][PROCESSOR] client setup completed. listening for input");
      while (this.connected)
      {
        this.thread.setName("SERVER-PROCESSOR: " + this.client);
        this.serverPingThread.setName("ServerPing " + this.client);
        int j;
        if ((j = this.dataInputStream.readInt()) > this.receiveBuffer.length)
        {
          System.err.println("[SERVER] Exception: Unusual big update from client " + getClient() + ": growing receive buffer: " + j);
          this.receiveBuffer = new byte[j];
        }
        assert (j > 0) : (" Empty update! " + j);
        try
        {
          this.dataInputStream.readFully(this.receiveBuffer, 0, j);
        }
        catch (Exception localException2)
        {
          System.err.println("Exception happened with size " + j);
          throw localException2;
        }
        FastByteArrayInputStream localFastByteArrayInputStream = new FastByteArrayInputStream(this.receiveBuffer, 0, j);
        this.inDoubleBuffer = new DataInputStream(localFastByteArrayInputStream);
        int k;
        if ((k = this.inDoubleBuffer.read()) >= 0)
        {
          if ((k != 42) && (k != 23) && (k != 100) && (k != 65)) {
            throw new IOException("SERVER CHECK FAILED: " + k + " for client " + getClient() + ": Received: " + j + ": " + Arrays.toString(Arrays.copyOf(this.receiveBuffer, j)) + ": available: " + localFastByteArrayInputStream.available());
          }
          if (k == 100)
          {
            this.connected = false;
            i = 1;
            System.err.println("[SERVER] Probe was made CODE (#100). Closing connection!");
            this.out.write(100);
            this.out.flush();
          }
          else if (k == 65)
          {
            System.err.println("[SERVER][LOGOUT]#### soft client logout received. Logging out client: " + this.client);
            this.connected = false;
          }
          else
          {
            synchronized (this.state)
            {
              synchronized (getLock())
              {
                boolean bool;
                if (k == 23)
                {
                  bool = checkPing(getIn());
                  assert (bool);
                }
                else if (bool == true)
                {
                  parseNextPacket(getIn());
                }
                if (this.byteArrayOutDoubleBuffer.position() > 0L) {
                  flushDoubleOutBuffer();
                }
              }
            }
            if (this.inDoubleBuffer.available() > 0) {
              throw new IOException("MORE BYTES AVAILABLE: " + this.inDoubleBuffer.available() + "; total size" + localObject4);
            }
          }
        }
      }
    }
    catch (Exception localException1)
    {
      if (!this.disconnectAfterSent) {
        localException1.printStackTrace();
      } else {
        i = 1;
      }
      this.connected = false;
    }
    finally
    {
      this.connected = false;
    }
    System.err.println("CONNECTION FOR " + this.client + " HAS BEEN DISCONNECTED . PROBE: " + localObject1);
    try
    {
      if (localObject1 == 0) {
        onError();
      }
    }
    catch (IOException localIOException) {}finally
    {
      deleteObservers();
      if (getClient() != null)
      {
        System.err.println("UNREGISTER CLIENT " + getClient());
        this.state.getController().unregister(getClient().getId());
        System.err.println("UNREGISTER DONE FOR CLIENT " + getClient());
      }
      else
      {
        System.err.println("COULD NOT UNREGISTER CLIENT " + getClient());
      }
      if ((getClient() != null) && (!this.state.filterJoinMessages())) {
        ((ServerControllerInterface)getState().getController()).broadcastMessage(getClient().getPlayerName() + " left the game", 0);
      }
    }
    if (localObject2 != 0)
    {
      System.err.println("[SERVER] PROBE SUCCESSFULLY EXECUTED. STOPPING PROCESSOR. (Ping of a Starter to start server)");
      return;
    }
    System.err.println("[SERVER] SERVER PROCESSOR STOPPED FOR " + this.client);
  }
  
  public void serverCommand(byte paramByte, int paramInt, Object... paramVarArgs)
  {
    System.err.println("SERVER COMMAND: " + Arrays.toString(paramVarArgs));
    NetUtil.commands.getById(paramByte).writeAndCommitParametriziedCommand(paramVarArgs, paramInt, getClient().getId(), (short)-32768, getClient().getProcessor());
    Command.sendingTime = System.currentTimeMillis();
  }
  
  public void setClient(RegisteredClientOnServer paramRegisteredClientOnServer)
  {
    this.client = paramRegisteredClientOnServer;
  }
  
  public void setPingTime(long paramLong)
  {
    this.pingTime = paramLong;
  }
  
  public void setThread(Thread paramThread)
  {
    this.thread = paramThread;
  }
  
  private void setup()
  {
    while ((!this.commandSocket.isConnected()) || (!this.commandSocket.isBound()) || (this.commandSocket.isInputShutdown()) || (this.commandSocket.isOutputShutdown())) {
      System.err.println("Waiting for command socket! ");
    }
    this.dataInputStream = new DataInputStream(this.commandSocket.getInputStream());
    this.out = new DataOutputStream(new BufferedOutputStream(this.commandSocket.getOutputStream(), 8192));
    this.serverPing = new ServerProcessor.ServerPing(this, null);
    this.serverPingThread = new Thread(this.serverPing, "ServerPing");
    this.serverPingThread.start();
    this.tmpHeader = new Header();
    this.sendingQueueThread = new ServerProcessor.SendingQueueThread(this, null);
    this.sendingQueueThread.start();
  }
  
  public void updatePing()
  {
    throw new IllegalStateException("METHOD NOT AVAILABLE");
  }
  
  public boolean isStopTransmit()
  {
    return this.stopTransmit;
  }
  
  public void setStopTransmit(boolean paramBoolean)
  {
    this.stopTransmit = paramBoolean;
  }
  
  public boolean isConnectionAlive()
  {
    return (this.commandSocket != null) && (this.commandSocket.isConnected());
  }
  
  public void disconnectAfterSent()
  {
    this.disconnectAfterSent = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */