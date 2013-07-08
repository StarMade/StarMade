/*  1:   */package org.schema.schine.network.client;
/*  2:   */
/*  3:   */import d;
/*  4:   */import java.io.IOException;
/*  5:   */import java.io.PrintStream;
/*  6:   */
/* 42:   */class ClientController$1
/* 43:   */  extends Thread
/* 44:   */{
/* 45:   */  ClientController$1(ClientController paramClientController) {}
/* 46:   */  
/* 47:   */  public void run()
/* 48:   */  {
/* 49:   */    try
/* 50:   */    {
/* 51:51 */      this.this$0.onShutDown();
/* 52:52 */      d.a(); return;
/* 53:   */    } catch (IOException localIOException) {
/* 54:54 */      System.err.println("[ERROR] CLIENT SHUTDOWN. Failed to save ServerState!");
/* 55:   */      
/* 56:56 */      localIOException.printStackTrace(); return;
/* 57:   */    } catch (Exception localException) {
/* 58:58 */      System.err.println("[ERROR] CLIENT SHUTDOWN. Failed to save ServerState!");
/* 59:   */      
/* 60:60 */      localException.printStackTrace();
/* 61:   */    }
/* 62:   */  }
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientController.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */