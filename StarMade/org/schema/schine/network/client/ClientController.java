/*     */ package org.schema.schine.network.client;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Toolkit;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*     */ import org.schema.schine.network.Header;
/*     */ import org.schema.schine.network.IdGen;
/*     */ import org.schema.schine.network.LoginFailedException;
/*     */ import org.schema.schine.network.NetworkProcessor;
/*     */ import org.schema.schine.network.commands.GetNextFreeObjectId;
/*     */ import org.schema.schine.network.commands.Login;
/*     */ import org.schema.schine.network.commands.RequestServerTime;
/*     */ import org.schema.schine.network.commands.RequestSynchronizeAll;
/*     */ import org.schema.schine.network.commands.Synchronize;
/*     */ import org.schema.schine.network.commands.SynchronizePrivateChannel;
/*     */ import org.schema.schine.network.synchronization.SynchronizationReceiver;
/*     */ import org.schema.schine.network.synchronization.SynchronizationSender;
/*     */ 
/*     */ public abstract class ClientController
/*     */   implements ClientControllerInterface
/*     */ {
/*     */   private ClientToServerConnection connection;
/*     */   private ClientStateInterface state;
/*  43 */   private IntOpenHashSet delHelper = new IntOpenHashSet();
/*     */   private long lastSynchronize;
/*     */ 
/*     */   public ClientController(ClientStateInterface paramClientStateInterface)
/*     */   {
/*  46 */     this.state = paramClientStateInterface;
/*  47 */     Runtime.getRuntime().addShutdownHook(new ClientController.1(this));
/*     */   }
/*     */ 
/*     */   public abstract void afterFullResynchronize();
/*     */ 
/*     */   public void aquireFreeIds()
/*     */   {
/*  76 */     System.err.println("[CLIENT] " + this.state + " asking for new IDS");
/*  77 */     this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], GetNextFreeObjectId.class, new Object[] { ClientState.NEW_ID_RANGE });
/*     */ 
/*  79 */     System.err.println("[CLIENT] " + this.state + " received new IDS");
/*     */   }
/*     */ 
/*     */   public void connect(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
/*     */   {
/*  92 */     setGuiConnectionState("connecting to " + paramString1 + ":" + paramInt);
/*  93 */     this.connection = new ClientToServerConnection(this.state);
/*  94 */     this.connection.connect(paramString1, paramInt);
/*     */ 
/*  96 */     login(paramString2, this.state.getVersion(), paramString3, paramString4);
/*     */ 
/*  99 */     System.out.println("[CLIENT] logged in as: " + this.state);
/* 100 */     onLogin();
/* 101 */     System.out.println("[CLIENT] synchronizing ALL " + this.state);
/* 102 */     setGuiConnectionState("requesting synchronize...");
/* 103 */     requestSynchronizeAll();
/* 104 */     setGuiConnectionState("client synchronized...");
/*     */   }
/*     */   public ClientToServerConnection getConnection() {
/* 107 */     return this.connection;
/*     */   }
/*     */ 
/*     */   public void handleBrokeConnection()
/*     */   {
/* 116 */     this.connection.disconnect();
/* 117 */     System.err.println("[CLIENT] " + this.state + " CLIENT LOST CONNECTION -> BACK TO login SCREEN");
/*     */   }
/*     */ 
/*     */   public void handleErrorDialogException(ErrorDialogException paramErrorDialogException)
/*     */   {
/* 123 */     Object[] arrayOfObject = { "Retry", "Exit" };
/* 124 */     paramErrorDialogException.printStackTrace();
/* 125 */     String str = "Critical Error";
/*     */     JFrame localJFrame;
/* 126 */     (
/* 127 */       localJFrame = new JFrame(str))
/* 127 */       .setUndecorated(true);
/*     */ 
/* 129 */     localJFrame.setVisible(true);
/*     */ 
/* 131 */     Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
/* 132 */     localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
/* 133 */     switch (JOptionPane.showOptionDialog(localJFrame, paramErrorDialogException.getClass().getSimpleName() + ": " + paramErrorDialogException.getMessage(), str, 1, 0, null, arrayOfObject, arrayOfObject[0]))
/*     */     {
/*     */     case 0:
/* 138 */       break;
/*     */     case 1:
/* 140 */       System.err.println("[GLFrame] Error Message: " + paramErrorDialogException.getMessage());
/* 141 */       System.exit(0);
/*     */     case 2:
/*     */     }
/*     */ 
/* 146 */     localJFrame.dispose();
/*     */   }
/*     */ 
/*     */   public void login(String paramString1, float paramFloat, String paramString2, String paramString3)
/*     */   {
/* 158 */     setGuiConnectionState("logging in as " + paramString1 + "    (Version " + paramFloat + ")");
/* 159 */     System.out.println("[CLIENT] logging in now... " + paramString1);
/* 160 */     this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], Login.class, new Object[] { paramString1, Float.valueOf(paramFloat), paramString2, paramString3 });
/*     */ 
/* 162 */     if (this.state.getId() < 0) {
/* 163 */       ClientState.loginFailed = true;
/* 164 */       throw new LoginFailedException(this.state.getId());
/*     */     }
/*     */ 
/* 167 */     setGuiConnectionState("login successfull...");
/* 168 */     this.state.setPlayerName(paramString1);
/*     */   }
/*     */ 
/*     */   public void logout(String paramString)
/*     */   {
/* 175 */     System.err.println("logout received. exiting");
/* 176 */     kick(paramString);
/*     */   }
/*     */ 
/*     */   protected abstract void onLogin();
/*     */ 
/*     */   protected abstract void onResynchRequest();
/*     */ 
/*     */   protected abstract void onShutDown();
/*     */ 
/*     */   public void requestServerTime() {
/* 186 */     this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], RequestServerTime.class, new Object[0]);
/*     */   }
/*     */ 
/*     */   public void requestSynchronizeAll()
/*     */   {
/* 192 */     this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], RequestSynchronizeAll.class, new Object[0]);
/* 193 */     this.lastSynchronize = System.currentTimeMillis();
/*     */ 
/* 195 */     afterFullResynchronize();
/*     */ 
/* 197 */     this.state.setSynchronized(true);
/* 198 */     System.out.println("[CLIENT] RE- synchronized client: " + this.state.getId());
/*     */   }
/*     */   public void setConnection(ClientToServerConnection paramClientToServerConnection) {
/* 201 */     this.connection = paramClientToServerConnection;
/*     */   }
/*     */ 
/*     */   public abstract void setGuiConnectionState(String paramString);
/*     */ 
/*     */   public void synchronize()
/*     */   {
/* 209 */     Header localHeader = new Header(Synchronize.class, this.state.getId(), 0, IdGen.getNewPacketId(), (byte)123);
/* 210 */     synchronized (this.state.getProcessor().getLock())
/*     */     {
/* 212 */       localHeader.write(this.state.getProcessor().getOut());
/*     */ 
/* 216 */       int i = 0; if (SynchronizationSender.encodeNetworkObjects(this.state.getLocalAndRemoteObjectContainer(), this.state, this.state.getProcessor().getOut(), false) == 
/* 216 */         1) {
/* 217 */         long l1 = System.currentTimeMillis();
/* 218 */         i = this.state.getProcessor().getCurrentSize();
/* 219 */         this.state.getProcessor().flushDoubleOutBuffer();
/*     */         long l2;
/* 221 */         if ((
/* 221 */           l2 = System.currentTimeMillis() - l1) > 
/* 221 */           10L)
/* 222 */           System.err.println("[WARNING][CLIENT] SLOW: synchronized flush took " + l2 + " ms, size " + i);
/*     */       }
/*     */       else {
/* 225 */         this.state.getProcessor().resetDoubleOutBuffer();
/*     */       }
/*     */     }
/* 228 */     SynchronizationReceiver.handleDeleted(this.state.getLocalAndRemoteObjectContainer(), this.state, this.delHelper);
/* 229 */     synchronizePrivate();
/*     */   }
/*     */ 
/*     */   public void synchronizePrivate()
/*     */   {
/* 235 */     Header localHeader = new Header(SynchronizePrivateChannel.class, this.state.getId(), 0, IdGen.getNewPacketId(), (byte)123);
/* 236 */     synchronized (this.state.getProcessor().getLock())
/*     */     {
/* 238 */       localHeader.write(this.state.getProcessor().getOut());
/*     */ 
/* 242 */       if (SynchronizationSender.encodeNetworkObjects(this.state.getPrivateLocalAndRemoteObjectContainer(), this.state, this.state.getProcessor().getOut(), false) == 
/* 242 */         1)
/* 243 */         this.state.getProcessor().flushDoubleOutBuffer();
/*     */       else {
/* 245 */         this.state.getProcessor().resetDoubleOutBuffer();
/*     */       }
/*     */     }
/* 248 */     SynchronizationReceiver.handleDeleted(this.state.getPrivateLocalAndRemoteObjectContainer(), this.state, this.delHelper);
/*     */   }
/*     */ 
/*     */   public void updateSynchronization()
/*     */   {
/* 253 */     if (!this.state.getProcessor().isAlive()) {
/* 254 */       System.err.println("EXIT: PROCESSOR DEAD");
/* 255 */       System.exit(0);
/*     */     }
/* 257 */     this.state.getProcessor().updatePing();
/*     */ 
/* 259 */     if (this.state.isSynchronized()) {
/* 260 */       if (this.lastSynchronize + 30L < System.currentTimeMillis())
/*     */       {
/* 262 */         synchronize();
/*     */ 
/* 271 */         this.lastSynchronize = System.currentTimeMillis();
/*     */       }
/*     */     }
/*     */     else {
/* 275 */       onResynchRequest();
/*     */ 
/* 277 */       System.err.println("[ERROR] Updating. RESYNCHING WITH SERVER " + this.state);
/* 278 */       requestSynchronizeAll();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientController
 * JD-Core Version:    0.6.2
 */