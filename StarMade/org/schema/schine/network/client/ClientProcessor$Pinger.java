/*   1:    */package org.schema.schine.network.client;
/*   2:    */
/*   3:    */import java.io.InputStream;
/*   4:    */import java.io.PrintStream;
/*   5:    */import org.schema.schine.network.StateInterface;
/*   6:    */
/*  83:    */class ClientProcessor$Pinger
/*  84:    */{
/*  85:    */  private boolean waitingForPong;
/*  86:    */  private long lastPingTime;
/*  87:    */  private long lastPong;
/*  88:    */  
/*  89:    */  private ClientProcessor$Pinger(ClientProcessor paramClientProcessor) {}
/*  90:    */  
/*  91:    */  public boolean checkPing(InputStream paramInputStream)
/*  92:    */  {
/*  93: 93 */    if ((paramInputStream = (byte)paramInputStream.read()) == -2)
/*  94:    */    {
/*  96: 96 */      this.waitingForPong = false;
/*  97:    */      
/*  98: 98 */      ClientProcessor.access$000(this.this$0).setPing(System.currentTimeMillis() - this.lastPingTime);
/*  99: 99 */      this.lastPong = System.currentTimeMillis();
/* 100:    */      
/* 103:103 */      return true; }
/* 104:104 */    if (paramInputStream == -1)
/* 105:    */    {
/* 107:107 */      sendPong();
/* 108:    */      
/* 109:109 */      return true;
/* 110:    */    }
/* 111:111 */    return false;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public void execute()
/* 115:    */  {
/* 116:116 */    if (ClientProcessor.access$100(this.this$0).getId() > 0) {
/* 117:117 */      synchronized (ClientProcessor.access$200(this.this$0)) {
/* 118:118 */        if ((!this.waitingForPong) && (this.lastPingTime + 1000L < System.currentTimeMillis()))
/* 119:    */        {
/* 124:124 */          if (sendPing()) {
/* 125:125 */            this.lastPingTime = System.currentTimeMillis();
/* 126:126 */            this.waitingForPong = true;
/* 127:    */          }
/* 128:    */        }
/* 129:    */        
/* 130:130 */        long l1 = System.currentTimeMillis() - this.lastPingTime;
/* 131:131 */        long l2 = System.currentTimeMillis() - this.lastPong;
/* 132:132 */        if ((this.waitingForPong) && (l1 > 5000L)) {
/* 133:133 */          ((ClientControllerInterface)this.this$0.getState().getController()).alertMessage("WARNING: Server doesn't answer! (" + l2 / 1000L + " sec)\nServer is temporary under high load!.\n\nif this message does not go away\nthe server might be frozen\nPlease tell the admin to send a report!");
/* 134:134 */          System.err.println("[CLIENTPROCESSOR][WARNING] Ping time of client (" + ClientProcessor.access$000(this.this$0).getId() + ") exceeded time limit: retrying! pending requests: " + this.this$0.getPendingRequests());
/* 135:    */          
/* 137:137 */          this.waitingForPong = false;
/* 138:    */        }
/* 139:139 */        return;
/* 140:    */      }
/* 141:    */    }
/* 142:    */  }
/* 143:    */  
/* 148:    */  public boolean sendPing()
/* 149:    */  {
/* 150:150 */    if (ClientProcessor.access$000(this.this$0).getId() < 0) {
/* 151:151 */      System.err.println("[CLIENT] not logged int to server: discarding ping");
/* 152:    */      
/* 153:153 */      return false;
/* 154:    */    }
/* 155:155 */    ClientProcessor.access$302(this.this$0, true);
/* 156:156 */    synchronized (ClientProcessor.access$400(this.this$0)) {
/* 157:157 */      ClientProcessor.access$400(this.this$0).notify();
/* 158:    */    }
/* 159:    */    
/* 168:168 */    return true;
/* 169:    */  }
/* 170:    */  
/* 174:    */  public void sendPong()
/* 175:    */  {
/* 176:176 */    if (ClientProcessor.access$000(this.this$0).getId() < 0) {
/* 177:177 */      System.err.println("[CLIENT] not logged int to server: discarding pong");
/* 178:    */      
/* 179:179 */      return;
/* 180:    */    }
/* 181:181 */    ClientProcessor.access$502(this.this$0, true);
/* 182:182 */    synchronized (ClientProcessor.access$400(this.this$0)) {
/* 183:183 */      ClientProcessor.access$400(this.this$0).notify(); return;
/* 184:    */    }
/* 185:    */  }
/* 186:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientProcessor.Pinger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */