package org.schema.schine.network.client;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.Header;
import org.schema.schine.network.IdGen;
import org.schema.schine.network.LoginFailedException;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.commands.GetNextFreeObjectId;
import org.schema.schine.network.commands.Login;
import org.schema.schine.network.commands.RequestServerTime;
import org.schema.schine.network.commands.RequestSynchronizeAll;
import org.schema.schine.network.commands.Synchronize;
import org.schema.schine.network.commands.SynchronizePrivateChannel;
import org.schema.schine.network.synchronization.SynchronizationReceiver;
import org.schema.schine.network.synchronization.SynchronizationSender;

public abstract class ClientController
  implements ClientControllerInterface
{
  private ClientToServerConnection connection;
  private ClientStateInterface state;
  private IntOpenHashSet delHelper = new IntOpenHashSet();
  private long lastSynchronize;
  
  public ClientController(ClientStateInterface paramClientStateInterface)
  {
    this.state = paramClientStateInterface;
    Runtime.getRuntime().addShutdownHook(new ClientController.1(this));
  }
  
  public abstract void afterFullResynchronize();
  
  public void aquireFreeIds()
  {
    System.err.println("[CLIENT] " + this.state + " asking for new IDS");
    this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], GetNextFreeObjectId.class, new Object[] { ClientState.NEW_ID_RANGE });
    System.err.println("[CLIENT] " + this.state + " received new IDS");
  }
  
  public void connect(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
  {
    setGuiConnectionState("connecting to " + paramString1 + ":" + paramInt);
    this.connection = new ClientToServerConnection(this.state);
    this.connection.connect(paramString1, paramInt);
    login(paramString2, this.state.getVersion(), paramString3, paramString4);
    System.out.println("[CLIENT] logged in as: " + this.state);
    onLogin();
    System.out.println("[CLIENT] synchronizing ALL " + this.state);
    setGuiConnectionState("requesting synchronize...");
    requestSynchronizeAll();
    setGuiConnectionState("client synchronized...");
  }
  
  public ClientToServerConnection getConnection()
  {
    return this.connection;
  }
  
  public void handleBrokeConnection()
  {
    this.connection.disconnect();
    System.err.println("[CLIENT] " + this.state + " CLIENT LOST CONNECTION -> BACK TO login SCREEN");
  }
  
  public void handleErrorDialogException(ErrorDialogException paramErrorDialogException)
  {
    Object[] arrayOfObject = { "Retry", "Exit" };
    paramErrorDialogException.printStackTrace();
    String str = "Critical Error";
    JFrame localJFrame;
    (localJFrame = new JFrame(str)).setUndecorated(true);
    localJFrame.setVisible(true);
    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
    localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
    switch (JOptionPane.showOptionDialog(localJFrame, paramErrorDialogException.getClass().getSimpleName() + ": " + paramErrorDialogException.getMessage(), str, 1, 0, null, arrayOfObject, arrayOfObject[0]))
    {
    case 0: 
      break;
    case 1: 
      System.err.println("[GLFrame] Error Message: " + paramErrorDialogException.getMessage());
      System.exit(0);
    }
    localJFrame.dispose();
  }
  
  public void login(String paramString1, float paramFloat, String paramString2, String paramString3)
  {
    setGuiConnectionState("logging in as " + paramString1 + "    (Version " + paramFloat + ")");
    System.out.println("[CLIENT] logging in now... " + paramString1);
    this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], Login.class, new Object[] { paramString1, Float.valueOf(paramFloat), paramString2, paramString3 });
    if (this.state.getId() < 0)
    {
      ClientState.loginFailed = true;
      throw new LoginFailedException(this.state.getId());
    }
    setGuiConnectionState("login successfull...");
    this.state.setPlayerName(paramString1);
  }
  
  public void logout(String paramString)
  {
    System.err.println("logout received. exiting");
    kick(paramString);
  }
  
  protected abstract void onLogin();
  
  protected abstract void onResynchRequest();
  
  protected abstract void onShutDown();
  
  public void requestServerTime()
  {
    this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], RequestServerTime.class, new Object[0]);
  }
  
  public void requestSynchronizeAll()
  {
    this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], RequestSynchronizeAll.class, new Object[0]);
    this.lastSynchronize = System.currentTimeMillis();
    afterFullResynchronize();
    this.state.setSynchronized(true);
    System.out.println("[CLIENT] RE- synchronized client: " + this.state.getId());
  }
  
  public void setConnection(ClientToServerConnection paramClientToServerConnection)
  {
    this.connection = paramClientToServerConnection;
  }
  
  public abstract void setGuiConnectionState(String paramString);
  
  public void synchronize()
  {
    Header localHeader = new Header(Synchronize.class, this.state.getId(), 0, IdGen.getNewPacketId(), (byte)123);
    synchronized (this.state.getProcessor().getLock())
    {
      localHeader.write(this.state.getProcessor().getOut());
      int i = 0;
      if (SynchronizationSender.encodeNetworkObjects(this.state.getLocalAndRemoteObjectContainer(), this.state, this.state.getProcessor().getOut(), false) == 1)
      {
        long l1 = System.currentTimeMillis();
        i = this.state.getProcessor().getCurrentSize();
        this.state.getProcessor().flushDoubleOutBuffer();
        long l2;
        if ((l2 = System.currentTimeMillis() - l1) > 10L) {
          System.err.println("[WARNING][CLIENT] SLOW: synchronized flush took " + l2 + " ms, size " + i);
        }
      }
      else
      {
        this.state.getProcessor().resetDoubleOutBuffer();
      }
    }
    SynchronizationReceiver.handleDeleted(this.state.getLocalAndRemoteObjectContainer(), this.state, this.delHelper);
    synchronizePrivate();
  }
  
  public void synchronizePrivate()
  {
    Header localHeader = new Header(SynchronizePrivateChannel.class, this.state.getId(), 0, IdGen.getNewPacketId(), (byte)123);
    synchronized (this.state.getProcessor().getLock())
    {
      localHeader.write(this.state.getProcessor().getOut());
      if (SynchronizationSender.encodeNetworkObjects(this.state.getPrivateLocalAndRemoteObjectContainer(), this.state, this.state.getProcessor().getOut(), false) == 1) {
        this.state.getProcessor().flushDoubleOutBuffer();
      } else {
        this.state.getProcessor().resetDoubleOutBuffer();
      }
    }
    SynchronizationReceiver.handleDeleted(this.state.getPrivateLocalAndRemoteObjectContainer(), this.state, this.delHelper);
  }
  
  public void updateSynchronization()
  {
    if (!this.state.getProcessor().isAlive())
    {
      System.err.println("EXIT: PROCESSOR DEAD");
      System.exit(0);
    }
    this.state.getProcessor().updatePing();
    if (this.state.isSynchronized())
    {
      if (this.lastSynchronize + 30L < System.currentTimeMillis())
      {
        synchronize();
        this.lastSynchronize = System.currentTimeMillis();
      }
    }
    else
    {
      onResynchRequest();
      System.err.println("[ERROR] Updating. RESYNCHING WITH SERVER " + this.state);
      requestSynchronizeAll();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.client.ClientController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */