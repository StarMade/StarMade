/*    */ package org.schema.game.network.commands;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import kC;
/*    */ import lE;
/*    */ import mx;
/*    */ import org.schema.game.common.data.world.Universe;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import q;
/*    */ import vg;
/*    */ 
/*    */ public class RequestGameMode extends Command
/*    */ {
/*    */   public RequestGameMode()
/*    */   {
/* 15 */     this.mode = 1;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 21 */     paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 29 */     paramArrayOfObject = (vg)paramServerStateInterface;
/*    */     Object localObject;
/* 35 */     if ((
/* 35 */       localObject = paramServerProcessor.getClient()) != null)
/*    */     {
/* 36 */       localObject = (lE)((RegisteredClientOnServer)localObject).getPlayerObject();
/*    */ 
/* 38 */       localObject = paramArrayOfObject.a().getSector(((lE)localObject).a());
/*    */ 
/* 40 */       createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { paramArrayOfObject.a().toString(), Integer.valueOf(((mx)localObject).a()), Integer.valueOf(((mx)localObject).a.a), Integer.valueOf(((mx)localObject).a.b), Integer.valueOf(((mx)localObject).a.c), paramArrayOfObject.a(), paramArrayOfObject.b() });
/*    */ 
/* 49 */       return;
/* 50 */     }System.err.println("[SERVER][REQUESTGAMEMODE] not sending gameMode to invalid client");
/*    */   }
/*    */ 
/*    */   public void execute()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestGameMode
 * JD-Core Version:    0.6.2
 */