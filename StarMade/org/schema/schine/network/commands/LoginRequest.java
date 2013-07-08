package org.schema.schine.network.commands;

import java.io.PrintStream;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.server.ServerControllerInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class LoginRequest
  implements Runnable
{
  public String playerName;
  public float version;
  public String sessionId;
  public String sessionName;
  public int field_2069;
  public ServerProcessor serverProcessor;
  public short packetid;
  public Login login;
  private int returnCode;
  private boolean authd = false;
  public ServerStateInterface state;
  private RegisteredClientOnServer field_2070;
  
  public void prepare() {}
  
  public void run()
  {
    this.field_2070 = new RegisteredClientOnServer(this.field_2069, this.playerName, this.state);
    this.field_2070.setProcessor(this.serverProcessor);
    this.serverProcessor.setClient(this.field_2070);
    if (!this.state.getController().authenticate(this.playerName, this.sessionId, this.sessionName)) {
      this.returnCode = -7;
    } else {
      this.authd = true;
    }
    if (this.playerName.length() <= 0) {
      this.returnCode = -9;
    } else if (this.playerName.length() > 32) {
      this.returnCode = -9;
    } else if (this.playerName.length() <= 2) {
      this.returnCode = -9;
    } else if (this.playerName.matches("[_-]+")) {
      this.returnCode = -9;
    } else if (!this.playerName.matches("[a-zA-Z0-9_-]+")) {
      this.returnCode = -9;
    }
    synchronized (this.state)
    {
      synchronized (this.serverProcessor.getLock())
      {
        try
        {
          if (this.authd)
          {
            this.state.getController().protectUserName(this.playerName, this.sessionId, this.sessionName);
            this.returnCode = this.state.getController().registerClient(this.field_2070, this.version);
          }
          if (this.returnCode < 0)
          {
            System.err.println("[SERVER][LOGIN] login failed (" + this.returnCode + "): SET CLIENT TO NULL");
            this.serverProcessor.setClient(null);
          }
          float f = this.state.getVersion();
          System.out.println("[SERVER][LOGIN] login received. returning login info for " + this.serverProcessor.getClient() + ": returnCode: " + this.returnCode);
          if (!this.state.filterJoinMessages()) {
            this.state.getController().broadcastMessage(this.playerName + " has joined the game", 0);
          }
          this.login.createReturnToClient(this.state, this.serverProcessor, this.packetid, new Object[] { Integer.valueOf(this.returnCode), Integer.valueOf(this.field_2069), Long.valueOf(System.currentTimeMillis()), Float.valueOf(f) });
          if (this.returnCode < 0)
          {
            this.serverProcessor.disconnectDelayed();
            if (!this.state.filterJoinMessages()) {
              this.state.getController().broadcastMessage(this.playerName + "'s connection failed (" + this.returnCode + ")", 0);
            }
          }
        }
        catch (Exception localException)
        {
          localException;
        }
      }
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.commands.LoginRequest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */