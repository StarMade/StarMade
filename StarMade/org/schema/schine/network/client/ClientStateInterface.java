package org.schema.schine.network.client;

import java.util.ArrayList;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;

public abstract interface ClientStateInterface extends StateInterface
{
  public abstract void arrivedReturn(short paramShort, Object[] paramArrayOfObject);

  public abstract ClientControllerInterface getController();

  public abstract ArrayList getMouseEvents();

  public abstract long getPing();

  public abstract String getPlayerName();

  public abstract NetworkStateContainer getPrivateLocalAndRemoteObjectContainer();

  public abstract NetworkProcessor getProcessor();

  public abstract Object[] getReturn(short paramShort);

  public abstract int getServerTimeDifference();

  public abstract boolean isSynchronized();

  public abstract boolean isSynchronizing();

  public abstract void message(String paramString, Integer paramInteger);

  public abstract void setClientConnection(ClientCommunicator paramClientCommunicator);

  public abstract void setId(int paramInt);

  public abstract void setIdStartRange(int paramInt);

  public abstract void setPing(long paramLong);

  public abstract void setPlayerName(String paramString);

  public abstract void setServerTimeOnLogin(long paramLong);

  public abstract void setSynchronized(boolean paramBoolean);

  public abstract void setServerVersion(float paramFloat);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientStateInterface
 * JD-Core Version:    0.6.2
 */