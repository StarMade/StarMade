/*    */ package org.schema.schine.network.client;
/*    */ 
/*    */ import d;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ class ClientController$1 extends Thread
/*    */ {
/*    */   ClientController$1(ClientController paramClientController)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/*    */     try
/*    */     {
/* 51 */       this.this$0.onShutDown();
/* 52 */       d.a();
/*    */       return;
/*    */     }
/*    */     catch (IOException localIOException)
/*    */     {
/* 54 */       System.err.println("[ERROR] CLIENT SHUTDOWN. Failed to save ServerState!");
/*    */ 
/* 56 */       localIOException.printStackTrace();
/*    */       return;
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 58 */       System.err.println("[ERROR] CLIENT SHUTDOWN. Failed to save ServerState!");
/*    */ 
/* 60 */       localException.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientController.1
 * JD-Core Version:    0.6.2
 */