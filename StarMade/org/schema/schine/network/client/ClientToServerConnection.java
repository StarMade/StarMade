package org.schema.schine.network.client;

import class_949;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import net.rudp.ReliableSocket;
import org.schema.schine.network.Command;
import org.schema.schine.network.CommandMap;
import org.schema.schine.network.IdGen;
import org.schema.schine.network.NetUtil;
import org.schema.schine.network.Pinger;
import org.schema.schine.network.Recipient;
import org.schema.schine.network.Request;

public class ClientToServerConnection
  extends Pinger
  implements Recipient
{
  private String host;
  private String name;
  private boolean updateStarted = false;
  private Socket connection;
  private int port;
  private ClientCommunicator communicator;
  private ClientStateInterface state;
  private DataOutputStream output;
  private Object waitingForPendingLock = new Object();
  
  public ClientToServerConnection(ClientStateInterface paramClientStateInterface)
  {
    this.state = paramClientStateInterface;
  }
  
  public void connect(String paramString, int paramInt)
  {
    setHost(paramString);
    setPort(paramInt);
    if (this.connection == null)
    {
      System.out.println("[CLIENT] establishing new socket connection to " + paramString + ":" + paramInt);
      paramString = new InetSocketAddress(paramString, paramInt);
      try
      {
        System.err.println("[CLIENT] Trying TCP...");
        this.connection = new Socket();
        if (class_949.field_1254.b1()) {
          this.connection.setTrafficClass(24);
        }
        this.connection.setReceiveBufferSize(((Integer)class_949.field_1253.a4()).intValue());
        this.connection.setSendBufferSize(((Integer)class_949.field_1253.a4()).intValue());
        this.connection.setTcpNoDelay(true);
        this.connection.connect(paramString);
      }
      catch (Exception paramInt)
      {
        if (this.connection != null) {
          try
          {
            this.connection.close();
          }
          catch (Exception localException)
          {
            localException;
          }
        }
        System.err.println("[CLIENT] TCP connection failed: " + paramInt.getClass().getSimpleName() + "; " + paramInt.getMessage());
        System.err.println("[CLIENT] Trying UDP...");
        this.connection = new ReliableSocket();
        if (class_949.field_1254.b1()) {
          this.connection.setTrafficClass(24);
        }
        this.connection.setReceiveBufferSize(((Integer)class_949.field_1253.a4()).intValue());
        this.connection.setSendBufferSize(((Integer)class_949.field_1253.a4()).intValue());
        this.connection.connect(paramString);
        System.err.println("[CLIENT] UDP CONNECTION SUCCESSFULL");
      }
    }
    this.communicator = new ClientCommunicator(this.state, this);
  }
  
  public void disconnect()
  {
    try
    {
      this.connection.shutdownInput();
      this.connection.shutdownOutput();
      this.connection.close();
      System.out.println("Client Socket connection has been closed");
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  public Socket getConnection()
  {
    return this.connection;
  }
  
  public String getHost()
  {
    return this.host;
  }
  
  public int getId()
  {
    return this.state.getId();
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public DataOutputStream getOutput()
  {
    return this.output;
  }
  
  public int getPort()
  {
    return this.port;
  }
  
  public boolean isAlive()
  {
    return this.communicator.getClientProcessorThread().isAlive();
  }
  
  public boolean isUpdateStarted()
  {
    return this.updateStarted;
  }
  
  public void sendCommand(int paramInt, Class paramClass, Object... paramVarArgs)
  {
    short s = IdGen.getNewPacketId();
    sendCommand(paramInt, s, paramClass, paramVarArgs);
  }
  
  public void sendCommand(int paramInt, short paramShort, Class paramClass, Object... paramVarArgs)
  {
    Command localCommand;
    if ((localCommand = NetUtil.commands.getByClass(paramClass)).getMode() == 1)
    {
      Request localRequest = new Request(paramShort);
      synchronized (this.waitingForPendingLock)
      {
        synchronized (localRequest)
        {
          synchronized (this.communicator.getClientProcessor().getPendingRequests())
          {
            assert (!this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) : this.communicator.getClientProcessor().getPendingRequests();
            this.communicator.getClientProcessor().getPendingRequests().put(Short.valueOf(paramShort), localRequest);
            localCommand.writeAndCommitParametriziedCommand(paramVarArgs, getId(), paramInt, paramShort, this.state.getProcessor());
          }
          while (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort)))
          {
            localRequest.wait(60000L);
            if (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) {
              System.err.println("[WARNING] PACKET ID: #" + paramShort + " IS STILL PENDING");
            }
          }
          if ((!$assertionsDisabled) && (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort)))) {
            throw new AssertionError("waiting for packet " + paramShort + " on command " + paramClass + ": " + this.communicator.getClientProcessor().getPendingRequests());
          }
        }
        return;
      }
    }
    localCommand.writeAndCommitParametriziedCommand(paramVarArgs, getId(), paramInt, paramShort, this.state.getProcessor());
  }
  
  public Object[] sendReturnedCommand(int paramInt, short paramShort, Class paramClass, Object... paramVarArgs)
  {
    Command localCommand = NetUtil.commands.getByClass(paramClass);
    assert (localCommand.getMode() == 1);
    Request localRequest = new Request(paramShort);
    synchronized (this.waitingForPendingLock)
    {
      synchronized (localRequest)
      {
        synchronized (this.communicator.getClientProcessor().getPendingRequests())
        {
          assert (!this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) : this.communicator.getClientProcessor().getPendingRequests();
          this.communicator.getClientProcessor().getPendingRequests().put(Short.valueOf(paramShort), localRequest);
          localCommand.writeAndCommitParametriziedCommand(paramVarArgs, getId(), paramInt, paramShort, this.state.getProcessor());
        }
        while (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort)))
        {
          localRequest.wait(60000L);
          if (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) {
            System.err.println("[WARNING] PACKET ID: #" + paramShort + " IS STILL PENDING");
          }
        }
        assert (!this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) : ("waiting for packet " + paramShort + " on command " + paramClass + "; " + this.communicator.getClientProcessor().getPendingRequests());
        ??? = this.state.getReturn(paramShort);
        assert (??? != null);
        return ???;
      }
    }
  }
  
  public void setHost(String paramString)
  {
    this.host = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setOutput(DataOutputStream paramDataOutputStream)
  {
    this.output = paramDataOutputStream;
  }
  
  public void setPort(int paramInt)
  {
    this.port = paramInt;
  }
  
  public void setUpdateStarted(boolean paramBoolean)
  {
    this.updateStarted = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.client.ClientToServerConnection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */