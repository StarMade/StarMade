package org.schema.schine.network.server;

import org.schema.schine.network.ControllerInterface;
import org.schema.schine.network.RegisteredClientOnServer;
import xq;

public abstract interface ServerControllerInterface extends ControllerInterface
{
  public abstract void broadcastMessage(String paramString, int paramInt);

  public abstract boolean isListenting();

  public abstract int registerClient(RegisteredClientOnServer paramRegisteredClientOnServer, float paramFloat);

  public abstract void unregister(int paramInt);

  public abstract void update(xq paramxq);

  public abstract boolean authenticate(String paramString1, String paramString2, String paramString3);

  public abstract void protectUserName(String paramString1, String paramString2, String paramString3);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerControllerInterface
 * JD-Core Version:    0.6.2
 */