/*     */ package org.schema.schine.network.commands;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import org.schema.schine.network.Command;
/*     */ import org.schema.schine.network.NetworkProcessor;
/*     */ import org.schema.schine.network.client.ClientStateInterface;
/*     */ import org.schema.schine.network.server.ServerProcessor;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public class GetInfo extends Command
/*     */ {
/*     */   private static final byte INFO_VERSION = 2;
/*     */   private long started;
/*     */ 
/*     */   public GetInfo()
/*     */   {
/*  74 */     this.mode = 1;
/*     */   }
/*     */ 
/*     */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*     */   {
/*  79 */     ((Byte)paramArrayOfObject[0]).byteValue();
/*  80 */     paramClientStateInterface = ((Float)paramArrayOfObject[1]).floatValue();
/*  81 */     paramShort = (String)paramArrayOfObject[2];
/*  82 */     String str = (String)paramArrayOfObject[3];
/*  83 */     long l = ((Long)paramArrayOfObject[4]).longValue();
/*  84 */     int i = ((Integer)paramArrayOfObject[5]).intValue();
/*  85 */     paramArrayOfObject = ((Integer)paramArrayOfObject[6]).intValue();
/*     */ 
/*  87 */     System.currentTimeMillis();
/*     */ 
/*  90 */     System.out.println("[CLIENT][INFO]: CLIENT INFO ");
/*  91 */     System.out.println("[CLIENT][INFO]: Version: " + paramClientStateInterface);
/*  92 */     System.out.println("[CLIENT][INFO]: Name: " + paramShort);
/*  93 */     System.out.println("[CLIENT][INFO]: Description: " + str);
/*  94 */     System.out.println("[CLIENT][INFO]: Started: " + l);
/*  95 */     System.out.println("[CLIENT][INFO]: Players: " + i + "/" + paramArrayOfObject);
/*     */   }
/*     */ 
/*     */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*     */   {
/* 104 */     paramArrayOfObject = paramServerStateInterface.getVersion();
/*     */ 
/* 106 */     String str1 = paramServerStateInterface.getServerName();
/* 107 */     String str2 = paramServerStateInterface.getServerDesc();
/* 108 */     long l = paramServerStateInterface.getStartTime();
/* 109 */     int i = paramServerStateInterface.getClients().size();
/* 110 */     int j = paramServerStateInterface.getMaxClients();
/*     */ 
/* 112 */     createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Byte.valueOf(2), Float.valueOf(paramArrayOfObject), str1, str2, Long.valueOf(l), Integer.valueOf(i), Integer.valueOf(j) });
/*     */ 
/* 114 */     paramServerProcessor.disconnectAfterSent();
/*     */   }
/*     */ 
/*     */   public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
/*     */   {
/* 120 */     this.started = System.currentTimeMillis();
/*     */ 
/* 122 */     super.writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramShort, paramNetworkProcessor);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.GetInfo
 * JD-Core Version:    0.6.2
 */