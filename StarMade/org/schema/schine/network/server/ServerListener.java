/*   1:    */package org.schema.schine.network.server;
/*   2:    */
/*   3:    */import java.io.BufferedInputStream;
/*   4:    */import java.io.BufferedOutputStream;
/*   5:    */import java.io.DataInputStream;
/*   6:    */import java.io.DataOutputStream;
/*   7:    */import java.io.IOException;
/*   8:    */import java.io.PrintStream;
/*   9:    */import java.net.InetAddress;
/*  10:    */import java.net.ServerSocket;
/*  11:    */import java.net.Socket;
/*  12:    */import java.net.SocketTimeoutException;
/*  13:    */import java.util.Observable;
/*  14:    */import java.util.Observer;
/*  15:    */import net.rudp.ReliableServerSocket;
/*  16:    */import org.schema.schine.network.exception.ServerPortNotAvailableException;
/*  17:    */
/*  93:    */public class ServerListener
/*  94:    */  extends Observable
/*  95:    */  implements Runnable
/*  96:    */{
/*  97:    */  private final int port;
/*  98:    */  private final ServerSocket serverSocket;
/*  99:    */  private final ServerStateInterface state;
/* 100:    */  private boolean listening;
/* 101:    */  private final String host;
/* 102:    */  
/* 103:    */  public ServerListener(String paramString, int paramInt, ServerStateInterface paramServerStateInterface)
/* 104:    */  {
/* 105:105 */    this.host = paramString;
/* 106:106 */    this.port = paramInt;
/* 107:107 */    this.state = paramServerStateInterface;
/* 108:108 */    int i = 0;
/* 109:109 */    boolean bool = false;
/* 110:    */    
/* 114:    */    try
/* 115:    */    {
/* 116:116 */      (paramString = new Socket(paramString, this.port)).setSoTimeout(3000);
/* 117:117 */      DataOutputStream localDataOutputStream = new DataOutputStream(new BufferedOutputStream(paramString.getOutputStream()));
/* 118:118 */      DataInputStream localDataInputStream = new DataInputStream(new BufferedInputStream(paramString.getInputStream()));
/* 119:119 */      localDataOutputStream.writeInt(1);
/* 120:120 */      localDataOutputStream.write(100);
/* 121:121 */      localDataOutputStream.flush();
/* 122:    */      
/* 123:123 */      if ((byte)localDataInputStream.read() == 100) {
/* 124:124 */        bool = true;
/* 125:125 */        System.out.println("[SERVER] A schine server is already running");
/* 126:    */      }
/* 127:127 */      paramString.shutdownInput();
/* 128:128 */      paramString.shutdownOutput();
/* 129:129 */      paramString.close();
/* 130:    */    } catch (Exception localException) {
/* 131:131 */      if (((paramString = 
/* 132:    */      
/* 136:136 */        localException) instanceof SocketTimeoutException)) {
/* 137:132 */        paramString.printStackTrace();
/* 138:    */      } else {
/* 139:134 */        i = 1;
/* 140:    */      }
/* 141:    */    }
/* 142:    */    
/* 143:138 */    if ((i != 0) && (!bool)) {
/* 144:139 */      System.err.println("[SERVER] port " + paramInt + " is open");
/* 145:140 */      if ((paramServerStateInterface.getAcceptingIP() == null) || (paramServerStateInterface.getAcceptingIP().equals("all"))) {
/* 146:141 */        if (paramServerStateInterface.useUDP()) {
/* 147:142 */          System.err.println("[SERVER] USING UDP... ");
/* 148:143 */          this.serverSocket = new ReliableServerSocket(this.port, 0);return;
/* 149:    */        }
/* 150:145 */        System.err.println("[SERVER] USING TCP... ");
/* 151:146 */        this.serverSocket = new ServerSocket(this.port, 0);return;
/* 152:    */      }
/* 153:    */      
/* 154:149 */      if (paramServerStateInterface.useUDP()) {
/* 155:150 */        System.err.println("[SERVER] USING UDP... LISTENING ON " + paramServerStateInterface.getAcceptingIP());
/* 156:151 */        this.serverSocket = new ReliableServerSocket(this.port, 0, InetAddress.getByName(paramServerStateInterface.getAcceptingIP()));return;
/* 157:    */      }
/* 158:153 */      System.err.println("[SERVER] USING TCP... LISTENING ON " + paramServerStateInterface.getAcceptingIP());
/* 159:154 */      this.serverSocket = new ServerSocket(this.port, 0, InetAddress.getByName(paramServerStateInterface.getAcceptingIP()));return;
/* 160:    */    }
/* 161:    */    
/* 163:158 */    System.err.println("THROWING EXCEPTION");
/* 164:159 */    (
/* 165:160 */      paramString = new ServerPortNotAvailableException("localhost port " + this.port + " is closed or already in use")).setInstanceRunning(bool);
/* 166:161 */    throw paramString;
/* 167:    */  }
/* 168:    */  
/* 171:    */  public boolean isListening()
/* 172:    */  {
/* 173:168 */    return this.listening;
/* 174:    */  }
/* 175:    */  
/* 178:    */  public void run()
/* 179:    */  {
/* 180:175 */    System.err.println("[ServerListener] Server initialization OK... now waiting for connections");
/* 181:    */    try {
/* 182:    */      for (;;) {
/* 183:178 */        if (this.serverSocket.isClosed()) {
/* 184:179 */          System.err.println("server socket is closed!");
/* 185:    */        }
/* 186:181 */        this.listening = true;
/* 187:182 */        this.serverSocket.setPerformancePreferences(0, 2, 1);
/* 188:183 */        this.serverSocket.setReceiveBufferSize(this.state.getSocketBufferSize());
/* 189:184 */        Object localObject = this.serverSocket.accept();
/* 190:    */        
/* 194:189 */        System.err.println("connection made. starting new processor " + ((Socket)localObject).getPort() + ", " + ((Socket)localObject).getInetAddress() + "; local: " + ((Socket)localObject).getLocalPort() + ", " + ((Socket)localObject).getLocalAddress() + ", keepalive " + ((Socket)localObject).getKeepAlive());
/* 195:    */        
/* 196:191 */        ((Socket)localObject).setKeepAlive(true);
/* 197:192 */        if (!this.state.useUDP()) {
/* 198:193 */          ((Socket)localObject).setTcpNoDelay(this.state.tcpNoDelay());
/* 199:    */        }
/* 200:195 */        ((Socket)localObject).setTrafficClass(24);
/* 201:    */        
/* 202:197 */        ((Socket)localObject).setSendBufferSize(this.state.getSocketBufferSize());
/* 203:    */        
/* 204:199 */        localObject = new ServerProcessor((Socket)localObject, this.state);
/* 205:200 */        if ((this.state.getController() instanceof Observer)) {
/* 206:201 */          ((ServerProcessor)localObject).addObserver((Observer)this.state.getController());
/* 207:    */        }
/* 208:203 */        Thread localThread = new Thread((Runnable)localObject);
/* 209:204 */        ((ServerProcessor)localObject).setThread(localThread);
/* 210:205 */        localThread.setName("SERVER-Listener Thread (unknownId)");
/* 211:206 */        localThread.start();
/* 212:    */        
/* 214:209 */        setChanged();
/* 215:210 */        notifyObservers(localObject);
/* 216:    */        
/* 217:212 */        System.out.println("[SERVER] connection registered");
/* 218:    */      }
/* 219:214 */    } catch (IOException localIOException) { 
/* 220:    */      
/* 221:216 */        localIOException;
/* 222:    */    }
/* 223:    */  }
/* 224:    */  
/* 230:    */  public String getHost()
/* 231:    */  {
/* 232:225 */    return this.host;
/* 233:    */  }
/* 234:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerListener
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */