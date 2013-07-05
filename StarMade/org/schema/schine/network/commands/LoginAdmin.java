/*     */ package org.schema.schine.network.commands;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import org.schema.schine.network.Command;
/*     */ import org.schema.schine.network.IdGen;
/*     */ import org.schema.schine.network.NetworkProcessor;
/*     */ import org.schema.schine.network.RegisteredClientOnServer;
/*     */ import org.schema.schine.network.client.ClientStateInterface;
/*     */ import org.schema.schine.network.server.ServerControllerInterface;
/*     */ import org.schema.schine.network.server.ServerProcessor;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public class LoginAdmin extends Command
/*     */ {
/*     */   public static final int SUCCESS_LOGGED_IN = 0;
/*     */   public static final int ERROR_GENRAL_ERROR = -1;
/*     */   public static final int ERROR_ALREADY_LOGGED_IN = -2;
/*     */   public static final int ERROR_ACCESS_DENIED = -3;
/*     */   public static final int ERROR_SERVER_FULL = -4;
/*     */   public static final int ERROR_WRONG_CLIENT_VERSION = -5;
/*     */   public static final int ERROR_YOU_ARE_BANNED = -6;
/*     */   public static final int ERROR_AUTHENTICATION_FAILED = -7;
/*     */   public static final int NOT_LOGGED_IN = -4242;
/*     */   private long started;
/*     */ 
/*     */   public LoginAdmin()
/*     */   {
/*  80 */     this.mode = 1;
/*     */   }
/*     */ 
/*     */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*     */   {
/*  85 */     paramShort = ((Integer)paramArrayOfObject[0]).intValue();
/*  86 */     float f = ((Float)paramArrayOfObject[3]).floatValue();
/*  87 */     paramClientStateInterface.setServerVersion(f);
/*  88 */     if (paramShort < 0) {
/*  89 */       switch (paramShort) {
/*     */       case -2:
/*  91 */         System.err.println("[Client] [LOGIN]: ERROR: Already logged in");
/*  92 */         paramClientStateInterface.setId(paramShort);
/*  93 */         return;
/*     */       case -7:
/*  95 */         System.err.println("[Client] [LOGIN]: ERROR: Authentication Failed");
/*  96 */         paramClientStateInterface.setId(paramShort);
/*  97 */         return;
/*     */       case -3:
/*  99 */         System.err.println("[Client] [LOGIN]: ERROR: Access Denied");
/* 100 */         paramClientStateInterface.setId(paramShort);
/* 101 */         return;
/*     */       case -1:
/* 103 */         System.err.println("[Client] [LOGIN]: ERROR: General Error");
/* 104 */         paramClientStateInterface.setId(paramShort);
/* 105 */         return;
/*     */       case -5:
/* 107 */         System.err.println("[Client] [LOGIN]: ERROR: The version of your client is too old. Try updating with the StarMade-Starter. (server version: " + f + ")");
/* 108 */         paramClientStateInterface.setId(paramShort);
/* 109 */         return;
/*     */       case -4:
/* 111 */         System.err.println("[Client] [LOGIN]: ERROR: Server FULL Error");
/* 112 */         paramClientStateInterface.setId(paramShort);
/* 113 */         return;
/*     */       case -6:
/* 115 */         System.err.println("[Client] [LOGIN]: ERROR: You are banned from this server");
/* 116 */         paramClientStateInterface.setId(paramShort);
/* 117 */         return;
/*     */       }
/* 119 */       if (!$assertionsDisabled) throw new AssertionError("something went wrong: " + paramShort);
/*     */     }
/*     */     else
/*     */     {
/* 123 */       paramClientStateInterface.setId(((Integer)paramArrayOfObject[1]).intValue());
/*     */ 
/* 126 */       long l = System.currentTimeMillis() - 
/* 126 */         this.started;
/*     */ 
/* 132 */       paramClientStateInterface.setServerTimeOnLogin(((Long)paramArrayOfObject[2]).longValue() + l / 2L);
/*     */ 
/* 134 */       System.err.println("[Client] [LOGIN]: Client sucessfully registered with id: " + paramClientStateInterface.getId());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*     */   {
/* 143 */     String str1 = (String)paramArrayOfObject[0];
/* 144 */     float f1 = paramArrayOfObject.length > 1 ? ((Float)paramArrayOfObject[1]).floatValue() : 0.0F;
/* 145 */     String str2 = paramArrayOfObject.length > 2 ? (String)paramArrayOfObject[2] : "";
/* 146 */     String str3 = paramArrayOfObject.length > 3 ? (String)paramArrayOfObject[3] : "";
/* 147 */     int i = IdGen.getFreeStateId();
/*     */ 
/* 149 */     if (paramArrayOfObject.length > 4)
/*     */     {
/* 152 */       paramArrayOfObject = -5;
/*     */     }
/*     */     else {
/* 155 */       System.err.println("[SERVER][LOGIN] new client connected. given id: " + i + ": description: " + str1);
/*     */       RegisteredClientOnServer localRegisteredClientOnServer;
/* 158 */       (
/* 159 */         localRegisteredClientOnServer = new RegisteredClientOnServer(i, str1, paramServerStateInterface))
/* 159 */         .setProcessor(paramServerProcessor);
/* 160 */       paramServerProcessor.setClient(localRegisteredClientOnServer);
/*     */ 
/* 162 */       if (!paramServerStateInterface.getController().authenticate(str1, str2, str3)) {
/* 163 */         paramArrayOfObject = -7;
/*     */       } else {
/* 165 */         paramServerStateInterface.getController().protectUserName(str1, str2, str3);
/* 166 */         paramArrayOfObject = paramServerStateInterface.getController().registerClient(localRegisteredClientOnServer, f1);
/*     */       }
/* 168 */       System.err.println("[SERVER][LOGIN] return code " + paramArrayOfObject);
/*     */     }
/*     */ 
/* 173 */     if (paramArrayOfObject < 0) {
/* 174 */       System.err.println("[SERVER][LOGIN] login failed (" + paramArrayOfObject + "): SET CLIENT TO NULL");
/* 175 */       paramServerProcessor.setClient(null);
/*     */     }
/*     */ 
/* 183 */     float f2 = paramServerStateInterface.getVersion();
/* 184 */     System.out.println("[SERVER][LOGIN] login received. returning login info for " + paramServerProcessor.getClient() + ": returnCode: " + paramArrayOfObject);
/* 185 */     paramServerStateInterface.getController().broadcastMessage(str1 + " has joined the game", 0);
/* 186 */     createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Integer.valueOf(paramArrayOfObject), Integer.valueOf(i), Long.valueOf(System.currentTimeMillis()), Float.valueOf(f2) });
/* 187 */     if (paramArrayOfObject < 0) {
/* 188 */       paramServerProcessor.disconnectDelayed();
/* 189 */       paramServerStateInterface.getController().broadcastMessage(str1 + "'s connection failed (" + paramArrayOfObject + ")", 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
/*     */   {
/* 196 */     this.started = System.currentTimeMillis();
/*     */ 
/* 198 */     super.writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramShort, paramNetworkProcessor);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.LoginAdmin
 * JD-Core Version:    0.6.2
 */