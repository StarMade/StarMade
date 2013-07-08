package org.schema.schine.network.server;

import class_29;
import java.io.IOException;
import java.io.PrintStream;

class ServerController$1
  extends Thread
{
  ServerController$1(ServerController paramServerController) {}
  
  public void run()
  {
    try
    {
      ServerState.shutdown = true;
      this.this$0.onShutDown();
      class_29.a();
      return;
    }
    catch (IOException localIOException)
    {
      System.err.println("[ERROR] SERVER SHUTDOWN. Failed to save ServerState!");
      localIOException.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.server.ServerController.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */