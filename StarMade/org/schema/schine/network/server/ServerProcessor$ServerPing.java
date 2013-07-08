package org.schema.schine.network.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.StateInterface;

class ServerProcessor$ServerPing
  implements Runnable
{
  private static final long PING_WAIT = 1000L;
  private long lastPingSend;
  private long firstTry = 0L;
  private int NULL_TIMEOUT_IN_MS = 10000;
  
  private ServerProcessor$ServerPing(ServerProcessor paramServerProcessor) {}
  
  public void execute()
  {
    if (this.firstTry == 0L) {
      this.firstTry = System.currentTimeMillis();
    }
    if (this.this$0.getClient() != null)
    {
      if ((!this.this$0.waitingForPong.booleanValue()) && (System.currentTimeMillis() - this.lastPingSend > 1000L))
      {
        synchronized (this.this$0.getLock())
        {
          sendPing(ServerProcessor.access$000(this.this$0).getId());
        }
        if (ServerProcessor.access$100(this.this$0) < 12) {
          System.err.println("[SERVER] Std Ping; retries: " + ServerProcessor.access$100(this.this$0) + " to " + this.this$0.getClient());
        }
        ServerProcessor.access$202(this.this$0, System.currentTimeMillis());
        ServerProcessor.access$302(this.this$0, System.currentTimeMillis());
        this.lastPingSend = System.currentTimeMillis();
        this.this$0.waitingForPong = Boolean.valueOf(true);
        return;
      }
      if ((ServerProcessor.access$100(this.this$0) >= 0) && (this.this$0.waitingForPong.booleanValue()) && (System.currentTimeMillis() > ServerProcessor.access$300(this.this$0) + 10000L))
      {
        System.err.println("[SERVERPROCESSOR][WARNING} PING timeout warning. resending ping to " + this.this$0.getClient() + " Retries left: " + ServerProcessor.access$100(this.this$0) + "; socket connected: " + this.this$0.commandSocket.isConnected() + "; socket closed: " + this.this$0.commandSocket.isClosed() + "; inputShutdown: " + this.this$0.commandSocket.isInputShutdown() + "; outputShutdown: " + this.this$0.commandSocket.isOutputShutdown());
        synchronized (this.this$0.getLock())
        {
          sendPing(ServerProcessor.access$000(this.this$0).getId());
        }
        this.this$0.waitingForPong = Boolean.valueOf(true);
        System.err.println("[SERVERPROCESSOR][WARNING} PING has been resent to " + ServerProcessor.access$400(this.this$0));
        ServerProcessor.access$302(this.this$0, System.currentTimeMillis());
        ServerProcessor.access$110(this.this$0);
        if ((!this.this$0.commandSocket.isConnected()) || (this.this$0.commandSocket.isClosed())) {
          throw new IOException("Connection Closed");
        }
      }
      else if (ServerProcessor.access$100(this.this$0) < 12)
      {
        System.err.println("RETRY STATUS: Retries: " + ServerProcessor.access$100(this.this$0) + "; waiting for pong " + this.this$0.waitingForPong + " (" + System.currentTimeMillis() + "/" + (ServerProcessor.access$300(this.this$0) + 10000L) + ")");
      }
      if (ServerProcessor.access$100(this.this$0) < 0)
      {
        System.out.println("[SERVERPROCESSOR][ERROR] ping timeout (" + (System.currentTimeMillis() - ServerProcessor.access$200(this.this$0)) + ") from client -> DISCONNECT" + this.this$0.getClient().getId());
        ServerProcessor.access$502(this.this$0, false);
        ((ServerController)this.this$0.getState().getController()).unregister(ServerProcessor.access$400(this.this$0).getId());
        System.err.println("[SERVER] PING TIMEOUT logged out client " + this.this$0.getClient().getId());
        if (!this.this$0.commandSocket.isClosed()) {
          this.this$0.commandSocket.close();
        }
      }
    }
    else if (this.firstTry > 0L)
    {
      System.err.println("not executing server ping for null client yet: " + (System.currentTimeMillis() - this.firstTry) + " / " + this.NULL_TIMEOUT_IN_MS);
      if (System.currentTimeMillis() - this.firstTry > this.NULL_TIMEOUT_IN_MS)
      {
        System.err.println("[SERVER] NULL CLIENT TIMED OUT: DISCONENCTING");
        ServerProcessor.access$502(this.this$0, false);
        this.this$0.commandSocket.close();
      }
    }
    else
    {
      System.err.println("Client null and not first try");
    }
  }
  
  public void run()
  {
    while (ServerProcessor.access$500(this.this$0)) {
      try
      {
        execute();
        Thread.sleep(1000L);
      }
      catch (Exception localException2)
      {
        Exception localException1;
        (localException1 = localException2).printStackTrace();
        System.out.println("[SERVER] client (ping processor) " + this.this$0.getClient() + " disconnected : " + localException1.getMessage());
        ServerProcessor.access$502(this.this$0, false);
        if (ServerProcessor.access$400(this.this$0) != null) {
          try
          {
            System.err.println("[ERROR] SERVER PING FAILED FOR CLIENT " + ServerProcessor.access$400(this.this$0) + " -> LOGGING OUT CLIENT");
            ((ServerController)this.this$0.getState().getController()).unregister(ServerProcessor.access$400(this.this$0).getId());
          }
          catch (Exception localException3)
          {
            localException3;
          }
        }
        try
        {
          ServerProcessor.access$600(this.this$0);
        }
        catch (IOException localIOException)
        {
          localIOException;
        }
      }
    }
  }
  
  private void sendPing(int arg1)
  {
    ServerProcessor.access$702(this.this$0, true);
    synchronized (ServerProcessor.access$800(this.this$0))
    {
      ServerProcessor.access$800(this.this$0).notify();
      return;
    }
  }
  
  public void sendPong()
  {
    ServerProcessor.access$902(this.this$0, true);
    synchronized (ServerProcessor.access$800(this.this$0))
    {
      ServerProcessor.access$800(this.this$0).notify();
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor.ServerPing
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */