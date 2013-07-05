/*    */ package org.schema.game.network.commands;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import kd;
/*    */ import ki;
/*    */ import kn;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import ve;
/*    */ import vg;
/*    */ 
/*    */ public class CreateNewShip extends Command
/*    */ {
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor arg1, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 28 */     paramShort = null;
/*    */ 
/* 30 */     if (paramArrayOfObject[(paramArrayOfObject.length - 1)].equals("Ship")) {
/* 31 */       paramShort = new ve(???.getClient().getId(), paramArrayOfObject, kd.class);
/*    */     }
/* 33 */     if (paramArrayOfObject[(paramArrayOfObject.length - 1)].equals("Station")) {
/* 34 */       paramShort = new ve(???.getClient().getId(), paramArrayOfObject, ki.class);
/*    */     }
/* 36 */     if (paramArrayOfObject[(paramArrayOfObject.length - 1)].equals("Vehicle")) {
/* 37 */       System.err.println("REQUESTING ON SERVER VEHICLE");
/* 38 */       paramShort = new ve(???.getClient().getId(), paramArrayOfObject, kn.class);
/*    */     }
/* 40 */     assert (paramShort != null);
/* 41 */     synchronized (((vg)paramServerStateInterface).c()) {
/* 42 */       ((vg)paramServerStateInterface).c().add(paramShort);
/*    */       return;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.CreateNewShip
 * JD-Core Version:    0.6.2
 */