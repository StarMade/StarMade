package org.schema.schine.network.client;

import class_933;
import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.schema.schine.network.Command;
import org.schema.schine.network.CommandMap;
import org.schema.schine.network.Header;
import org.schema.schine.network.NetUtil;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.Request;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.exception.DisconnectException;

public class ClientProcessor
  implements Runnable, NetworkProcessor
{
  private static final int MAX_PACKET_POOL_SIZE = 1000;
  public static int MAX_PACKET_SIZE = 20480;
  public static final boolean DEBUG_BIG_CHUNKS = false;
  private final Map pendingRequests = new HashMap();
  private Object lock = new Object();
  private FastByteArrayOutputStream byteArrayOutDoubleBuffer;
  private DataOutputStream outDoubleBuffer = new DataOutputStream(this.byteArrayOutDoubleBuffer = new FastByteArrayOutputStream(307200));
  private DataInputStream inDoubleBuffer;
  private Socket receive;
  boolean listening = true;
  private ClientStateInterface state;
  private ClientToServerConnection clientToServerConnection;
  private DataInputStream dataInputStream;
  byte[] receiveBuffer = new byte[1024000];
  private Header headerTmp;
  private ClientProcessor.Pinger pinger;
  private Thread thread;
  public long lastPacketId;
  private Command lastCommand;
  private boolean ping;
  private boolean pong;
  private long serverPacketSentTimestamp;
  private ClientProcessor.SendingQueueThread sendingQueueThread;
  private final ArrayList sendingQueue = new ArrayList();
  private int totalPackagesQueued;
  private static final ArrayList packetPool = new ArrayList();
  
  public ClientProcessor(ClientToServerConnection paramClientToServerConnection, ClientStateInterface paramClientStateInterface)
  {
    this.state = paramClientStateInterface;
    this.clientToServerConnection = paramClientToServerConnection;
    this.receive = paramClientToServerConnection.getConnection();
    this.sendingQueueThread = new ClientProcessor.SendingQueueThread(this, null);
    this.sendingQueueThread.start();
  }
  
  public void closeSocket()
  {
    synchronized (this.lock)
    {
      sendLogout();
    }
    System.err.println("[CLIENT] CLOSING SOCKET");
    if (!this.clientToServerConnection.getConnection().isClosed())
    {
      System.err.println("[CLIENT] CLOSING SOCKET");
      this.clientToServerConnection.getConnection().close();
    }
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
  
  public void flushDoubleOutBuffer()
  {
    assert (this.byteArrayOutDoubleBuffer.position() > 0L);
    FastByteArrayOutputStream localFastByteArrayOutputStream;
    (localFastByteArrayOutputStream = getNewPacket()).write(this.byteArrayOutDoubleBuffer.array, 0, (int)this.byteArrayOutDoubleBuffer.position());
    resetDoubleOutBuffer();
    synchronized (this.sendingQueue)
    {
      if (this.listening)
      {
        this.sendingQueue.add(localFastByteArrayOutputStream);
        this.totalPackagesQueued += 1;
        this.sendingQueue.notify();
      }
      return;
    }
  }
  
  public void sendPacket(FastByteArrayOutputStream paramFastByteArrayOutputStream)
  {
    assert (paramFastByteArrayOutputStream.position() > 0L);
    System.currentTimeMillis();
    this.clientToServerConnection.getOutput().writeInt((int)paramFastByteArrayOutputStream.position());
    this.clientToServerConnection.getOutput().write(paramFastByteArrayOutputStream.array, 0, (int)paramFastByteArrayOutputStream.position());
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
    return this.receive.getInputStream();
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
    return this.receive.getOutputStream();
  }
  
  public Map getPendingRequests()
  {
    return this.pendingRequests;
  }
  
  public StateInterface getState()
  {
    return this.state;
  }
  
  public Thread getThread()
  {
    return this.thread;
  }
  
  public boolean isAlive()
  {
    return this.clientToServerConnection.isAlive();
  }
  
  public void notifyPacketReceived(short arg1, Command paramCommand)
  {
    if (paramCommand.getMode() == 1)
    {
      Request localRequest = null;
      synchronized (getPendingRequests())
      {
        localRequest = (Request)getPendingRequests().remove(Short.valueOf(???));
        assert (localRequest != null) : ("Request #" + ??? + " not pending!!");
        assert (paramCommand.getMode() == 1) : ("COMMAND DOES NOT RETURN BUT SENT RETURN VALUE: " + paramCommand);
        synchronized (localRequest)
        {
          assert (!getPendingRequests().containsKey(localRequest));
          localRequest.notify();
        }
        return;
      }
    }
  }
  
  public void parseNextPacket()
  {
    this.headerTmp.read(getIn());
    Command localCommand = NetUtil.commands.getById(this.headerTmp.getCommandId());
    assert (localCommand != null) : ("could not find " + this.headerTmp.getCommandId());
    this.lastCommand = localCommand;
    if (this.headerTmp.getType() == 111)
    {
      Object[] arrayOfObject = localCommand.readParameters(this.headerTmp, getIn());
      localCommand.clientAnswerProcess(arrayOfObject, this.state, this.headerTmp.packetId);
    }
    else if (this.headerTmp.getType() == 123)
    {
      localCommand.clientAnswerProcess(null, this.state, this.headerTmp.packetId);
    }
    else
    {
      System.err.println("[CRITICAL][ERROR] HEADER TYPE IS UNKNOWN");
    }
    if (NetUtil.commands.getById(this.headerTmp.getCommandId()) == null) {
      throw new IOException("[CRITICAL][ERROR] Could not find command " + this.headerTmp.getCommandId());
    }
    notifyPacketReceived(this.headerTmp.packetId, NetUtil.commands.getById(this.headerTmp.getCommandId()));
  }
  
  public void resetDoubleOutBuffer()
  {
    this.outDoubleBuffer.flush();
    this.byteArrayOutDoubleBuffer.reset();
  }
  
  public void run()
  {
    setup();
    try
    {
      this.dataInputStream = new DataInputStream(this.receive.getInputStream());
      while (!this.receive.isConnected()) {
        System.err.println("waiting for connection " + this.state);
      }
      while ((this.listening) && (!this.receive.isClosed()))
      {
        getThread().setName("client Processor: " + this.state.getId());
        int i = this.dataInputStream.readInt();
        this.serverPacketSentTimestamp = this.dataInputStream.readLong();
        ((ClientState)this.state).setDebugBigChunk(i > 4000);
        if (this.state.isReadingBigChunk()) {
          System.err.println("[CLIENT] WARNING: Received big chunk: " + i + " bytes");
        }
        assert (i > 0) : " Empty update!";
        if ((i > this.receiveBuffer.length) || (i < 0))
        {
          System.err.println("Exception CRITICAL: received size: " + i + " / " + this.receiveBuffer.length);
          this.receiveBuffer = new byte[i];
        }
        if (i > 102400) {
          System.err.println("Exception WARNING CRITICAL: received very BIG size: " + i + " / " + this.receiveBuffer.length);
        }
        this.dataInputStream.readFully(this.receiveBuffer, 0, i);
        synchronized (this.state)
        {
          synchronized (this.lock)
          {
            ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(this.receiveBuffer, 0, i);
            this.inDoubleBuffer = new DataInputStream(localByteArrayInputStream);
            int j;
            if ((j = getIn().read()) >= 0)
            {
              assert ((j == 42) || (j == 23)) : ("CLIENT CHECK FAILED: " + j + " " + Arrays.toString(Arrays.copyOf(this.receiveBuffer, i)) + "; available: " + localByteArrayInputStream.available() + ", happend on object 0");
              if (j == 23)
              {
                boolean bool = this.pinger.checkPing(getIn());
                assert (bool);
              }
              else if (j == 42)
              {
                parseNextPacket();
              }
              else
              {
                System.err.println("WARNING: FAULTY PACKET " + localByteArrayInputStream.available());
              }
              if (this.byteArrayOutDoubleBuffer.position() > 0L)
              {
                System.err.println("sending update: " + j);
                flushDoubleOutBuffer();
              }
            }
            if (localByteArrayInputStream.available() > 0)
            {
              System.err.println("[CLIENT] WARNING: PACKET NOT FULLY READ ( " + localByteArrayInputStream.available() + "). last check: " + j + ": synched: " + this.state.isSynchronized() + "; last command " + this.lastCommand);
              if (!this.state.isSynchronized()) {
                System.err.println("[CLIENT] OK. state got out of synch. Already resynching!");
              } else {
                throw new IOException("[CRITICAL ERROR] PARSING PACKET NOT COMPLETED. BUT CLIENT WAS SYNCHRONIZED!");
              }
            }
          }
        }
      }
    }
    catch (EOFException localEOFException)
    {
      (localObject3 = localEOFException).printStackTrace();
      if (!ClientState.loginFailed) {
        class_933.a2(new DisconnectException("You have been disconnected from the Server \n(either connection problem or server crash)\nActualException: " + localObject3.getClass().getSimpleName()));
      } else {
        ClientState.loginFailed = false;
      }
    }
    catch (IOException localIOException)
    {
      (localObject3 = localIOException).printStackTrace();
      if ((localObject3 instanceof SocketException)) {
        class_933.b2(new DisconnectException("You have been disconnected from the Server \n(either connection problem or server crash)\nActualException: " + localObject3.getClass().getSimpleName()));
      } else {
        class_933.a2((Exception)localObject3);
      }
    }
    catch (Exception localException)
    {
      Object localObject3;
      (localObject3 = localException).printStackTrace();
      if ((localObject3 instanceof SocketException)) {
        class_933.b2(new DisconnectException("You have been disconnected from the Server \n(either connection problem or server crash)\nActualException: " + localObject3.getClass().getSimpleName()));
      } else {
        class_933.a2((Exception)localObject3);
      }
    }
    System.out.println("[ClientProcessor] EXIT: Input Stream closed. Terminating Client Processor");
  }
  
  public void sendLogout()
  {
    this.outDoubleBuffer.writeByte(65);
    flushDoubleOutBuffer();
  }
  
  public void setThread(Thread paramThread)
  {
    this.thread = paramThread;
  }
  
  private void setup()
  {
    this.headerTmp = new Header();
    this.pinger = new ClientProcessor.Pinger(this, null);
  }
  
  public void updatePing()
  {
    this.pinger.execute();
  }
  
  public long getServerPacketSentTimestamp()
  {
    return this.serverPacketSentTimestamp;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.client.ClientProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */