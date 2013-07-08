package org.schema.schine.network.client;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ClientCommunicator
{
  private ClientProcessor clientProcessor;
  private ClientStateInterface state;
  private boolean connected;
  private Socket connection;
  private Thread clientProcessorThread;
  
  public ClientCommunicator(ClientStateInterface paramClientStateInterface, ClientToServerConnection paramClientToServerConnection)
  {
    setState(paramClientStateInterface);
    setConnection(paramClientToServerConnection.getConnection());
    while ((!getConnection().isConnected()) || (!getConnection().isBound()) || (getConnection().isInputShutdown()) || (getConnection().isOutputShutdown())) {
      System.err.println("CLIENT WAITING FOR CONNECTION");
    }
    System.out.println("CLIENT SOCKET CONNECTED TO SERVER");
    paramClientToServerConnection.setOutput(new DataOutputStream(new BufferedOutputStream(getConnection().getOutputStream(), 8192)));
    this.clientProcessor = new ClientProcessor(paramClientToServerConnection, paramClientStateInterface);
    this.clientProcessorThread = new Thread(this.clientProcessor);
    this.clientProcessorThread.setName("client Processor: " + paramClientStateInterface.getId());
    this.clientProcessor.setThread(this.clientProcessorThread);
    this.clientProcessorThread.start();
    this.connected = true;
    paramClientStateInterface.setClientConnection(this);
  }
  
  public ClientProcessor getClientProcessor()
  {
    return this.clientProcessor;
  }
  
  public Thread getClientProcessorThread()
  {
    return this.clientProcessorThread;
  }
  
  public Socket getConnection()
  {
    return this.connection;
  }
  
  public ClientStateInterface getState()
  {
    return this.state;
  }
  
  public boolean isConnected()
  {
    return this.connected;
  }
  
  public void setClientProcessorThread(Thread paramThread)
  {
    this.clientProcessorThread = paramThread;
  }
  
  public void setConnection(Socket paramSocket)
  {
    this.connection = paramSocket;
  }
  
  public void setState(ClientStateInterface paramClientStateInterface)
  {
    this.state = paramClientStateInterface;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.client.ClientCommunicator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */