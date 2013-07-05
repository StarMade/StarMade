/*     */ package org.schema.schine.network.commands;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import org.schema.schine.network.Command;
/*     */ import org.schema.schine.network.IdGen;
/*     */ import org.schema.schine.network.NetworkProcessor;
/*     */ import org.schema.schine.network.client.ClientStateInterface;
/*     */ import org.schema.schine.network.server.ServerProcessor;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public class Login extends Command
/*     */ {
/*     */   public static final int SUCCESS_LOGGED_IN = 0;
/*     */   public static final int ERROR_GENRAL_ERROR = -1;
/*     */   public static final int ERROR_ALREADY_LOGGED_IN = -2;
/*     */   public static final int ERROR_ACCESS_DENIED = -3;
/*     */   public static final int ERROR_SERVER_FULL = -4;
/*     */   public static final int ERROR_WRONG_CLIENT_VERSION = -5;
/*     */   public static final int ERROR_YOU_ARE_BANNED = -6;
/*     */   public static final int ERROR_AUTHENTICATION_FAILED = -7;
/*     */   public static final int ERROR_NOT_ON_WHITELIST = -8;
/*     */   public static final int NOT_LOGGED_IN = -4242;
/*     */   private long started;
/*     */ 
/*     */   public Login()
/*     */   {
/*  83 */     this.mode = 1;
/*     */   }
/*     */ 
/*     */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*     */   {
/*  88 */     paramShort = ((Integer)paramArrayOfObject[0]).intValue();
/*  89 */     float f = ((Float)paramArrayOfObject[3]).floatValue();
/*  90 */     paramClientStateInterface.setServerVersion(f);
/*  91 */     if (paramShort < 0) {
/*  92 */       switch (paramShort) {
/*     */       case -2:
/*  94 */         System.err.println("[Client] [LOGIN]: ERROR: Already logged in");
/*  95 */         paramClientStateInterface.setId(paramShort);
/*  96 */         return;
/*     */       case -7:
/*  98 */         System.err.println("[Client] [LOGIN]: ERROR: Authentication Failed");
/*  99 */         paramClientStateInterface.setId(paramShort);
/* 100 */         return;
/*     */       case -3:
/* 102 */         System.err.println("[Client] [LOGIN]: ERROR: Access Denied");
/* 103 */         paramClientStateInterface.setId(paramShort);
/* 104 */         return;
/*     */       case -1:
/* 106 */         System.err.println("[Client] [LOGIN]: ERROR: General Error");
/* 107 */         paramClientStateInterface.setId(paramShort);
/* 108 */         return;
/*     */       case -5:
/* 110 */         System.err.println("[Client] [LOGIN]: ERROR: The version of your client is too old. Try updating with the StarMade-Starter. (server version: " + f + ")");
/* 111 */         paramClientStateInterface.setId(paramShort);
/* 112 */         return;
/*     */       case -4:
/* 114 */         System.err.println("[Client] [LOGIN]: ERROR: Server FULL Error");
/* 115 */         paramClientStateInterface.setId(paramShort);
/* 116 */         return;
/*     */       case -6:
/* 118 */         System.err.println("[Client] [LOGIN]: ERROR: You are banned from this server");
/* 119 */         paramClientStateInterface.setId(paramShort);
/* 120 */         return;
/*     */       case -8:
/* 122 */         System.err.println("[Client] [LOGIN]: ERROR: You are not whitelisted on this server");
/* 123 */         paramClientStateInterface.setId(paramShort);
/* 124 */         return;
/*     */       }
/* 126 */       if (!$assertionsDisabled) throw new AssertionError("something went wrong: " + paramShort);
/*     */     }
/*     */     else
/*     */     {
/* 130 */       paramClientStateInterface.setId(((Integer)paramArrayOfObject[1]).intValue());
/*     */ 
/* 133 */       long l = System.currentTimeMillis() - 
/* 133 */         this.started;
/*     */ 
/* 139 */       paramClientStateInterface.setServerTimeOnLogin(((Long)paramArrayOfObject[2]).longValue() + l / 2L);
/*     */ 
/* 141 */       System.err.println("[Client] [LOGIN]: Client sucessfully registered with id: " + paramClientStateInterface.getId());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*     */   {
/* 151 */     LoginRequest localLoginRequest = new LoginRequest();
/*     */ 
/* 155 */     String str1 = (String)paramArrayOfObject[0];
/* 156 */     float f = paramArrayOfObject.length > 1 ? ((Float)paramArrayOfObject[1]).floatValue() : 0.0F;
/* 157 */     String str2 = paramArrayOfObject.length > 2 ? (String)paramArrayOfObject[2] : "";
/* 158 */     paramArrayOfObject = paramArrayOfObject.length > 3 ? (String)paramArrayOfObject[3] : "";
/*     */ 
/* 160 */     int i = IdGen.getFreeStateId();
/*     */ 
/* 164 */     System.err.println("[SERVER][LOGIN] new client connected. given id: " + i + ": description: " + str1);
/*     */ 
/* 166 */     localLoginRequest.state = paramServerStateInterface;
/* 167 */     localLoginRequest.playerName = str1;
/* 168 */     localLoginRequest.version = f;
/* 169 */     localLoginRequest.sessionId = str2;
/* 170 */     localLoginRequest.sessionName = paramArrayOfObject;
/* 171 */     localLoginRequest.id = i;
/* 172 */     localLoginRequest.serverProcessor = paramServerProcessor;
/* 173 */     localLoginRequest.packetid = paramShort;
/* 174 */     localLoginRequest.login = this;
/*     */ 
/* 176 */     paramServerStateInterface.addLoginRequest(localLoginRequest);
/*     */ 
/* 178 */     System.err.println("[SERVER][LOGIN] return code 0");
/*     */   }
/*     */ 
/*     */   public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
/*     */   {
/* 192 */     this.started = System.currentTimeMillis();
/*     */ 
/* 194 */     super.writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramShort, paramNetworkProcessor);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.Login
 * JD-Core Version:    0.6.2
 */