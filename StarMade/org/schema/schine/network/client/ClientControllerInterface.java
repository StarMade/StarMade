package org.schema.schine.network.client;

import org.schema.schine.network.ControllerInterface;
import xq;
import yz;

public abstract interface ClientControllerInterface extends ControllerInterface
{
  public abstract void alertMessage(String paramString);

  public abstract void aquireFreeIds();

  public abstract void handleBrokeConnection();

  public abstract void handleGUIMouseEvent(yz paramyz, int paramInt);

  public abstract void kick(String paramString);

  public abstract void login(String paramString1, float paramFloat, String paramString2, String paramString3);

  public abstract void logout(String paramString);

  public abstract void requestSynchronizeAll();

  public abstract void synchronize();

  public abstract void update(xq paramxq);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientControllerInterface
 * JD-Core Version:    0.6.2
 */