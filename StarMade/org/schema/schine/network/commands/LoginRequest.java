/*  1:   */package org.schema.schine.network.commands;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import org.schema.schine.network.RegisteredClientOnServer;
/*  5:   */import org.schema.schine.network.server.ServerControllerInterface;
/*  6:   */import org.schema.schine.network.server.ServerProcessor;
/*  7:   */import org.schema.schine.network.server.ServerStateInterface;
/*  8:   */
/*  9:   */public class LoginRequest implements Runnable
/* 10:   */{
/* 11:   */  public String playerName;
/* 12:   */  public float version;
/* 13:   */  public String sessionId;
/* 14:   */  public String sessionName;
/* 15:   */  public int id;
/* 16:   */  public ServerProcessor serverProcessor;
/* 17:   */  public short packetid;
/* 18:   */  public Login login;
/* 19:   */  private int returnCode;
/* 20:20 */  private boolean authd = false;
/* 21:   */  
/* 22:   */  public ServerStateInterface state;
/* 23:   */  private RegisteredClientOnServer c;
/* 24:   */  
/* 25:   */  public void prepare() {}
/* 26:   */  
/* 27:   */  public void run()
/* 28:   */  {
/* 29:29 */    this.c = new RegisteredClientOnServer(this.id, this.playerName, this.state);
/* 30:30 */    this.c.setProcessor(this.serverProcessor);
/* 31:31 */    this.serverProcessor.setClient(this.c);
/* 32:   */    
/* 33:33 */    if (!this.state.getController().authenticate(this.playerName, this.sessionId, this.sessionName)) {
/* 34:34 */      this.returnCode = -7;
/* 35:   */    } else {
/* 36:36 */      this.authd = true;
/* 37:   */    }
/* 38:   */    
/* 39:39 */    if (this.playerName.length() <= 0) {
/* 40:40 */      this.returnCode = -9;
/* 41:41 */    } else if (this.playerName.length() > 32) {
/* 42:42 */      this.returnCode = -9;
/* 43:43 */    } else if (this.playerName.length() <= 2) {
/* 44:44 */      this.returnCode = -9;
/* 45:45 */    } else if (this.playerName.matches("[_-]+")) {
/* 46:46 */      this.returnCode = -9;
/* 47:   */    }
/* 48:48 */    else if (!this.playerName.matches("[a-zA-Z0-9_-]+")) {
/* 49:49 */      this.returnCode = -9;
/* 50:   */    }
/* 51:   */    
/* 52:52 */    synchronized (this.state) {
/* 53:53 */      synchronized (this.serverProcessor.getLock()) {
/* 54:   */        try {
/* 55:55 */          if (this.authd) {
/* 56:56 */            this.state.getController().protectUserName(this.playerName, this.sessionId, this.sessionName);
/* 57:57 */            this.returnCode = this.state.getController().registerClient(this.c, this.version);
/* 58:   */          }
/* 59:   */          
/* 60:60 */          if (this.returnCode < 0) {
/* 61:61 */            System.err.println("[SERVER][LOGIN] login failed (" + this.returnCode + "): SET CLIENT TO NULL");
/* 62:62 */            this.serverProcessor.setClient(null);
/* 63:   */          }
/* 64:   */          
/* 70:70 */          float f = this.state.getVersion();
/* 71:71 */          System.out.println("[SERVER][LOGIN] login received. returning login info for " + this.serverProcessor.getClient() + ": returnCode: " + this.returnCode);
/* 72:72 */          if (!this.state.filterJoinMessages()) {
/* 73:73 */            this.state.getController().broadcastMessage(this.playerName + " has joined the game", 0);
/* 74:   */          }
/* 75:75 */          this.login.createReturnToClient(this.state, this.serverProcessor, this.packetid, new Object[] { Integer.valueOf(this.returnCode), Integer.valueOf(this.id), Long.valueOf(System.currentTimeMillis()), Float.valueOf(f) });
/* 76:76 */          if (this.returnCode < 0) {
/* 77:77 */            this.serverProcessor.disconnectDelayed();
/* 78:78 */            if (!this.state.filterJoinMessages()) {
/* 79:79 */              this.state.getController().broadcastMessage(this.playerName + "'s connection failed (" + this.returnCode + ")", 0);
/* 80:   */            }
/* 81:   */          }
/* 82:   */        } catch (Exception localException) {
/* 83:83 */          
/* 84:   */          
/* 85:85 */            localException;
/* 86:   */        }
/* 87:   */      }
/* 88:   */      return;
/* 89:   */    }
/* 90:   */  }
/* 91:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.LoginRequest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */