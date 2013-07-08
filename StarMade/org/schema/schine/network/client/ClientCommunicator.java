/*   1:    */package org.schema.schine.network.client;
/*   2:    */
/*   3:    */import java.io.BufferedOutputStream;
/*   4:    */import java.io.DataOutputStream;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.net.Socket;
/*   7:    */
/*  72:    */public class ClientCommunicator
/*  73:    */{
/*  74:    */  private ClientProcessor clientProcessor;
/*  75:    */  private ClientStateInterface state;
/*  76:    */  private boolean connected;
/*  77:    */  private Socket connection;
/*  78:    */  private Thread clientProcessorThread;
/*  79:    */  
/*  80:    */  public ClientCommunicator(ClientStateInterface paramClientStateInterface, ClientToServerConnection paramClientToServerConnection)
/*  81:    */  {
/*  82: 82 */    setState(paramClientStateInterface);
/*  83: 83 */    setConnection(paramClientToServerConnection.getConnection());
/*  84: 84 */    while ((!getConnection().isConnected()) || (!getConnection().isBound()) || (getConnection().isInputShutdown()) || (getConnection().isOutputShutdown())) {
/*  85: 85 */      System.err.println("CLIENT WAITING FOR CONNECTION");
/*  86:    */    }
/*  87: 87 */    System.out.println("CLIENT SOCKET CONNECTED TO SERVER");
/*  88:    */    
/*  90: 90 */    paramClientToServerConnection.setOutput(new DataOutputStream(new BufferedOutputStream(getConnection().getOutputStream(), 8192)));
/*  91: 91 */    this.clientProcessor = new ClientProcessor(paramClientToServerConnection, paramClientStateInterface);
/*  92:    */    
/*  93: 93 */    this.clientProcessorThread = new Thread(this.clientProcessor);
/*  94: 94 */    this.clientProcessorThread.setName("client Processor: " + paramClientStateInterface.getId());
/*  95: 95 */    this.clientProcessor.setThread(this.clientProcessorThread);
/*  96: 96 */    this.clientProcessorThread.start();
/*  97:    */    
/*  98: 98 */    this.connected = true;
/*  99: 99 */    paramClientStateInterface.setClientConnection(this);
/* 100:    */  }
/* 101:    */  
/* 106:    */  public ClientProcessor getClientProcessor()
/* 107:    */  {
/* 108:108 */    return this.clientProcessor;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public Thread getClientProcessorThread() {
/* 112:112 */    return this.clientProcessorThread;
/* 113:    */  }
/* 114:    */  
/* 120:    */  public Socket getConnection()
/* 121:    */  {
/* 122:122 */    return this.connection;
/* 123:    */  }
/* 124:    */  
/* 129:    */  public ClientStateInterface getState()
/* 130:    */  {
/* 131:131 */    return this.state;
/* 132:    */  }
/* 133:    */  
/* 138:    */  public boolean isConnected()
/* 139:    */  {
/* 140:140 */    return this.connected;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public void setClientProcessorThread(Thread paramThread) {
/* 144:144 */    this.clientProcessorThread = paramThread;
/* 145:    */  }
/* 146:    */  
/* 151:    */  public void setConnection(Socket paramSocket)
/* 152:    */  {
/* 153:153 */    this.connection = paramSocket;
/* 154:    */  }
/* 155:    */  
/* 160:    */  public void setState(ClientStateInterface paramClientStateInterface)
/* 161:    */  {
/* 162:162 */    this.state = paramClientStateInterface;
/* 163:    */  }
/* 164:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientCommunicator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */