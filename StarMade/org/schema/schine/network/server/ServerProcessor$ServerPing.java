/*   1:    */package org.schema.schine.network.server;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.net.Socket;
/*   6:    */import org.schema.schine.network.RegisteredClientOnServer;
/*   7:    */import org.schema.schine.network.StateInterface;
/*   8:    */
/*  92:    */class ServerProcessor$ServerPing
/*  93:    */  implements Runnable
/*  94:    */{
/*  95:    */  private static final long PING_WAIT = 1000L;
/*  96:    */  private long lastPingSend;
/*  97: 97 */  private long firstTry = 0L;
/*  98: 98 */  private int NULL_TIMEOUT_IN_MS = 10000;
/*  99:    */  
/* 100:    */  private ServerProcessor$ServerPing(ServerProcessor paramServerProcessor) {}
/* 101:    */  
/* 102:102 */  public void execute() { if (this.firstTry == 0L) {
/* 103:103 */      this.firstTry = System.currentTimeMillis();
/* 104:    */    }
/* 105:    */    
/* 106:106 */    if (this.this$0.getClient() != null)
/* 107:    */    {
/* 108:108 */      if ((!this.this$0.waitingForPong.booleanValue()) && (System.currentTimeMillis() - this.lastPingSend > 1000L))
/* 109:    */      {
/* 110:110 */        synchronized (this.this$0.getLock()) {
/* 111:111 */          sendPing(ServerProcessor.access$000(this.this$0).getId());
/* 112:    */        }
/* 113:113 */        if (ServerProcessor.access$100(this.this$0) < 12) {
/* 114:114 */          System.err.println("[SERVER] Std Ping; retries: " + ServerProcessor.access$100(this.this$0) + " to " + this.this$0.getClient());
/* 115:    */        }
/* 116:    */        
/* 118:118 */        ServerProcessor.access$202(this.this$0, System.currentTimeMillis());
/* 119:119 */        ServerProcessor.access$302(this.this$0, System.currentTimeMillis());
/* 120:120 */        this.lastPingSend = System.currentTimeMillis();
/* 121:121 */        this.this$0.waitingForPong = Boolean.valueOf(true);return;
/* 122:    */      }
/* 123:    */      
/* 124:124 */      if ((ServerProcessor.access$100(this.this$0) >= 0) && (this.this$0.waitingForPong.booleanValue()) && (System.currentTimeMillis() > ServerProcessor.access$300(this.this$0) + 10000L))
/* 125:    */      {
/* 128:128 */        System.err.println("[SERVERPROCESSOR][WARNING} PING timeout warning. resending ping to " + this.this$0.getClient() + " Retries left: " + ServerProcessor.access$100(this.this$0) + "; socket connected: " + this.this$0.commandSocket.isConnected() + "; socket closed: " + this.this$0.commandSocket.isClosed() + "; inputShutdown: " + this.this$0.commandSocket.isInputShutdown() + "; outputShutdown: " + this.this$0.commandSocket.isOutputShutdown());
/* 129:    */        
/* 131:131 */        synchronized (this.this$0.getLock()) {
/* 132:132 */          sendPing(ServerProcessor.access$000(this.this$0).getId());
/* 133:    */        }
/* 134:134 */        this.this$0.waitingForPong = Boolean.valueOf(true);
/* 135:135 */        System.err.println("[SERVERPROCESSOR][WARNING} PING has been resent to " + ServerProcessor.access$400(this.this$0));
/* 136:136 */        ServerProcessor.access$302(this.this$0, System.currentTimeMillis());
/* 137:    */        
/* 138:138 */        ServerProcessor.access$110(this.this$0);
/* 139:    */        
/* 140:140 */        if ((!this.this$0.commandSocket.isConnected()) || (this.this$0.commandSocket.isClosed())) {
/* 141:141 */          throw new IOException("Connection Closed");
/* 142:    */        }
/* 143:    */      }
/* 144:144 */      else if (ServerProcessor.access$100(this.this$0) < 12) {
/* 145:145 */        System.err.println("RETRY STATUS: Retries: " + ServerProcessor.access$100(this.this$0) + "; waiting for pong " + this.this$0.waitingForPong + " (" + System.currentTimeMillis() + "/" + (ServerProcessor.access$300(this.this$0) + 10000L) + ")");
/* 146:    */      }
/* 147:    */      
/* 149:149 */      if (ServerProcessor.access$100(this.this$0) < 0) {
/* 150:150 */        System.out.println("[SERVERPROCESSOR][ERROR] ping timeout (" + (System.currentTimeMillis() - ServerProcessor.access$200(this.this$0)) + ") from client -> DISCONNECT" + this.this$0.getClient().getId());
/* 151:    */        
/* 157:157 */        ServerProcessor.access$502(this.this$0, false);
/* 158:    */        
/* 160:160 */        ((ServerController)this.this$0.getState().getController()).unregister(ServerProcessor.access$400(this.this$0).getId());
/* 161:161 */        System.err.println("[SERVER] PING TIMEOUT logged out client " + this.this$0.getClient().getId());
/* 162:    */        
/* 164:164 */        if (!this.this$0.commandSocket.isClosed()) {
/* 165:165 */          this.this$0.commandSocket.close();
/* 166:    */        }
/* 167:    */        
/* 168:    */      }
/* 169:    */      
/* 171:    */    }
/* 172:172 */    else if (this.firstTry > 0L) {
/* 173:173 */      System.err.println("not executing server ping for null client yet: " + (System.currentTimeMillis() - this.firstTry) + " / " + this.NULL_TIMEOUT_IN_MS);
/* 174:174 */      if (System.currentTimeMillis() - this.firstTry > this.NULL_TIMEOUT_IN_MS) {
/* 175:175 */        System.err.println("[SERVER] NULL CLIENT TIMED OUT: DISCONENCTING");
/* 176:176 */        ServerProcessor.access$502(this.this$0, false);
/* 177:177 */        this.this$0.commandSocket.close();
/* 178:    */      }
/* 179:    */    } else {
/* 180:180 */      System.err.println("Client null and not first try");
/* 181:    */    }
/* 182:    */  }
/* 183:    */  
/* 187:    */  public void run()
/* 188:    */  {
/* 189:189 */    while (ServerProcessor.access$500(this.this$0)) {
/* 190:    */      try {
/* 191:191 */        execute();
/* 192:192 */        Thread.sleep(1000L);
/* 193:    */      } catch (Exception localException2) { Exception localException1;
/* 194:194 */        (localException1 = 
/* 195:    */        
/* 212:212 */          localException2).printStackTrace();System.out.println("[SERVER] client (ping processor) " + this.this$0.getClient() + " disconnected : " + localException1.getMessage());ServerProcessor.access$502(this.this$0, false);
/* 213:199 */        if (ServerProcessor.access$400(this.this$0) != null) {
/* 214:    */          try {
/* 215:201 */            System.err.println("[ERROR] SERVER PING FAILED FOR CLIENT " + ServerProcessor.access$400(this.this$0) + " -> LOGGING OUT CLIENT");
/* 216:202 */            ((ServerController)this.this$0.getState().getController()).unregister(ServerProcessor.access$400(this.this$0).getId());
/* 217:203 */          } catch (Exception localException3) { 
/* 218:    */            
/* 219:205 */              localException3;
/* 220:    */          }
/* 221:    */        }
/* 222:    */        try
/* 223:    */        {
/* 224:208 */          ServerProcessor.access$600(this.this$0);
/* 225:209 */        } catch (IOException localIOException) { 
/* 226:    */          
/* 227:211 */            localIOException;
/* 228:    */        }
/* 229:    */      }
/* 230:    */    }
/* 231:    */  }
/* 232:    */  
/* 240:    */  private void sendPing(int arg1)
/* 241:    */  {
/* 242:224 */    ServerProcessor.access$702(this.this$0, true);
/* 243:225 */    synchronized (ServerProcessor.access$800(this.this$0)) {
/* 244:226 */      ServerProcessor.access$800(this.this$0).notify(); return;
/* 245:    */    }
/* 246:    */  }
/* 247:    */  
/* 248:230 */  public void sendPong() { ServerProcessor.access$902(this.this$0, true);
/* 249:231 */    synchronized (ServerProcessor.access$800(this.this$0)) {
/* 250:232 */      ServerProcessor.access$800(this.this$0).notify(); return;
/* 251:    */    }
/* 252:    */  }
/* 253:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor.ServerPing
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */