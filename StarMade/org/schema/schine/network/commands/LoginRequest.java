/*    */ package org.schema.schine.network.commands;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.server.ServerControllerInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ 
/*    */ public class LoginRequest
/*    */   implements Runnable
/*    */ {
/*    */   public String playerName;
/*    */   public float version;
/*    */   public String sessionId;
/*    */   public String sessionName;
/*    */   public int id;
/*    */   public ServerProcessor serverProcessor;
/*    */   public short packetid;
/*    */   public Login login;
/*    */   private int returnCode;
/* 20 */   private boolean authd = false;
/*    */   public ServerStateInterface state;
/*    */   private RegisteredClientOnServer c;
/*    */ 
/*    */   public void prepare()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 29 */     this.c = new RegisteredClientOnServer(this.id, this.playerName, this.state);
/* 30 */     this.c.setProcessor(this.serverProcessor);
/* 31 */     this.serverProcessor.setClient(this.c);
/*    */ 
/* 33 */     if (!this.state.getController().authenticate(this.playerName, this.sessionId, this.sessionName))
/* 34 */       this.returnCode = -7;
/*    */     else {
/* 36 */       this.authd = true;
/*    */     }
/*    */ 
/* 40 */     synchronized (this.state) {
/* 41 */       synchronized (this.serverProcessor.getLock()) {
/*    */         try {
/* 43 */           if (this.authd) {
/* 44 */             this.state.getController().protectUserName(this.playerName, this.sessionId, this.sessionName);
/* 45 */             this.returnCode = this.state.getController().registerClient(this.c, this.version);
/*    */           }
/*    */ 
/* 48 */           if (this.returnCode < 0) {
/* 49 */             System.err.println("[SERVER][LOGIN] login failed (" + this.returnCode + "): SET CLIENT TO NULL");
/* 50 */             this.serverProcessor.setClient(null);
/*    */           }
/*    */ 
/* 58 */           float f = this.state.getVersion();
/* 59 */           System.out.println("[SERVER][LOGIN] login received. returning login info for " + this.serverProcessor.getClient() + ": returnCode: " + this.returnCode);
/* 60 */           if (!this.state.filterJoinMessages()) {
/* 61 */             this.state.getController().broadcastMessage(this.playerName + " has joined the game", 0);
/*    */           }
/* 63 */           this.login.createReturnToClient(this.state, this.serverProcessor, this.packetid, new Object[] { Integer.valueOf(this.returnCode), Integer.valueOf(this.id), Long.valueOf(System.currentTimeMillis()), Float.valueOf(f) });
/* 64 */           if (this.returnCode < 0) {
/* 65 */             this.serverProcessor.disconnectDelayed();
/* 66 */             if (!this.state.filterJoinMessages()) {
/* 67 */               this.state.getController().broadcastMessage(this.playerName + "'s connection failed (" + this.returnCode + ")", 0);
/*    */             }
/*    */           }
/*    */         }
/*    */         catch (Exception localException)
/*    */         {
/* 73 */           localException.printStackTrace();
/*    */         }
/*    */       }
/*    */       return;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.LoginRequest
 * JD-Core Version:    0.6.2
 */