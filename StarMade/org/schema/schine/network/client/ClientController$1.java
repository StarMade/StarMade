package org.schema.schine.network.client;

import class_29;
import java.io.IOException;
import java.io.PrintStream;

class ClientController$1
  extends Thread
{
  ClientController$1(ClientController paramClientController) {}
  
  public void run()
  {
    try
    {
      this.this$0.onShutDown();
      class_29.a();
      return;
    }
    catch (IOException localIOException)
    {
      System.err.println("[ERROR] CLIENT SHUTDOWN. Failed to save ServerState!");
      localIOException.printStackTrace();
      return;
    }
    catch (Exception localException)
    {
      System.err.println("[ERROR] CLIENT SHUTDOWN. Failed to save ServerState!");
      localException.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.client.ClientController.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */