/*  1:   */package org.schema.schine.network.server;
/*  2:   */
/*  3:   */import d;
/*  4:   */import java.io.IOException;
/*  5:   */import java.io.PrintStream;
/*  6:   */
/* 43:   */class ServerController$1
/* 44:   */  extends Thread
/* 45:   */{
/* 46:   */  ServerController$1(ServerController paramServerController) {}
/* 47:   */  
/* 48:   */  public void run()
/* 49:   */  {
/* 50:   */    try
/* 51:   */    {
/* 52:52 */      ServerState.shutdown = true;
/* 53:53 */      this.this$0.onShutDown();
/* 54:54 */      d.a(); return;
/* 55:   */    }
/* 56:   */    catch (IOException localIOException) {
/* 57:57 */      System.err.println("[ERROR] SERVER SHUTDOWN. Failed to save ServerState!");
/* 58:   */      
/* 59:59 */      localIOException.printStackTrace();
/* 60:   */    }
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerController.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */