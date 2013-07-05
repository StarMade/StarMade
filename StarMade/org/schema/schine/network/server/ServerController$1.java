/*    */ package org.schema.schine.network.server;
/*    */ 
/*    */ import d;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ class ServerController$1 extends Thread
/*    */ {
/*    */   ServerController$1(ServerController paramServerController)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/*    */     try
/*    */     {
/* 52 */       ServerState.shutdown = true;
/* 53 */       this.this$0.onShutDown();
/* 54 */       d.a();
/*    */       return;
/*    */     }
/*    */     catch (IOException localIOException)
/*    */     {
/* 57 */       System.err.println("[ERROR] SERVER SHUTDOWN. Failed to save ServerState!");
/*    */ 
/* 59 */       localIOException.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerController.1
 * JD-Core Version:    0.6.2
 */