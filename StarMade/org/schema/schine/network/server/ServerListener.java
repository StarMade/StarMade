/*     */ package org.schema.schine.network.server;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import org.schema.schine.network.exception.ServerPortNotAvailableException;
/*     */ 
/*     */ public class ServerListener extends Observable
/*     */   implements Runnable
/*     */ {
/*     */   private final int port;
/*     */   private final ServerSocket serverSocket;
/*     */   private final ServerStateInterface state;
/*     */   private boolean listening;
/*     */   private final String host;
/*     */ 
/*     */   public ServerListener(String paramString, int paramInt, ServerStateInterface paramServerStateInterface)
/*     */   {
/* 102 */     this.host = paramString;
/* 103 */     this.port = paramInt;
/* 104 */     this.state = paramServerStateInterface;
/* 105 */     int i = 0;
/* 106 */     boolean bool = false;
/*     */     try
/*     */     {
/* 112 */       (
/* 113 */         paramString = new Socket(paramString, this.port))
/* 113 */         .setSoTimeout(3000);
/* 114 */       DataOutputStream localDataOutputStream = new DataOutputStream(new BufferedOutputStream(paramString.getOutputStream()));
/* 115 */       DataInputStream localDataInputStream = new DataInputStream(new BufferedInputStream(paramString.getInputStream()));
/* 116 */       localDataOutputStream.writeInt(1);
/* 117 */       localDataOutputStream.write(100);
/* 118 */       localDataOutputStream.flush();
/*     */ 
/* 120 */       if ((byte)localDataInputStream.read() == 
/* 120 */         100) {
/* 121 */         bool = true;
/* 122 */         System.out.println("[SERVER] A schine server is already running");
/*     */       }
/* 124 */       paramString.shutdownInput();
/* 125 */       paramString.shutdownOutput();
/* 126 */       paramString.close();
/*     */     } catch (Exception localException) {
/* 128 */       if (((
/* 128 */         paramString = 
/* 133 */         localException) instanceof SocketTimeoutException))
/* 129 */         paramString.printStackTrace();
/*     */       else {
/* 131 */         i = 1;
/*     */       }
/*     */     }
/*     */ 
/* 135 */     if ((i != 0) && (!bool)) {
/* 136 */       System.err.println("[SERVER] port " + paramInt + " is open");
/* 137 */       if ((paramServerStateInterface.getAcceptingIP() == null) || (paramServerStateInterface.getAcceptingIP().equals("all")))
/* 138 */         this.serverSocket = new ServerSocket(this.port, 64);
/*     */       else {
/* 140 */         this.serverSocket = new ServerSocket(this.port, 64, InetAddress.getByName(paramServerStateInterface.getAcceptingIP()));
/*     */       }
/* 142 */       this.serverSocket.setReceiveBufferSize(paramServerStateInterface.getSocketBufferSize()); return;
/*     */     }
/* 144 */     System.err.println("THROWING EXCEPTION");
/* 145 */     (
/* 146 */       paramString = new ServerPortNotAvailableException("localhost port " + this.port + " is closed or already in use"))
/* 146 */       .setInstanceRunning(bool);
/* 147 */     throw paramString;
/*     */   }
/*     */ 
/*     */   public boolean isListening()
/*     */   {
/* 154 */     return this.listening;
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/* 161 */     System.err.println("[ServerListener] Server initialization OK... now waiting for connections");
/*     */     while (true)
/*     */       try {
/* 164 */         if (this.serverSocket.isClosed()) {
/* 165 */           System.err.println("server socket is closed!");
/*     */         }
/* 167 */         this.listening = true;
/* 168 */         Object localObject = this.serverSocket.accept();
/*     */ 
/* 173 */         System.err.println("connection made. starting new processor " + ((Socket)localObject).getPort() + ", " + ((Socket)localObject).getInetAddress() + "; local: " + ((Socket)localObject).getLocalPort() + ", " + ((Socket)localObject).getLocalAddress() + ", keepalive " + ((Socket)localObject).getKeepAlive());
/*     */ 
/* 175 */         ((Socket)localObject).setKeepAlive(true);
/* 176 */         ((Socket)localObject).setTcpNoDelay(true);
/* 177 */         ((Socket)localObject).setTrafficClass(24);
/*     */ 
/* 180 */         ((Socket)localObject).setSendBufferSize(this.state.getSocketBufferSize());
/*     */ 
/* 182 */         localObject = new ServerProcessor((Socket)localObject, this.state);
/* 183 */         if ((this.state.getController() instanceof Observer)) {
/* 184 */           ((ServerProcessor)localObject).addObserver((Observer)this.state.getController());
/*     */         }
/* 186 */         Thread localThread = new Thread((Runnable)localObject);
/* 187 */         ((ServerProcessor)localObject).setThread(localThread);
/* 188 */         localThread.setName("SERVER-Listener Thread (unknownId)");
/* 189 */         localThread.start();
/*     */ 
/* 192 */         setChanged();
/* 193 */         notifyObservers(localObject);
/*     */ 
/* 195 */         System.out.println("[SERVER] connection registered");
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/* 199 */         localIOException.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public String getHost()
/*     */   {
/* 208 */     return this.host;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerListener
 * JD-Core Version:    0.6.2
 */