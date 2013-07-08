package org.schema.schine.network.client;

import class_1363;
import class_941;
import org.schema.schine.network.ControllerInterface;

public abstract interface ClientControllerInterface
  extends ControllerInterface
{
  public abstract void alertMessage(String paramString);
  
  public abstract void aquireFreeIds();
  
  public abstract void handleBrokeConnection();
  
  public abstract void handleGUIMouseEvent(class_1363 paramclass_1363, int paramInt);
  
  public abstract void kick(String paramString);
  
  public abstract void login(String paramString1, float paramFloat, String paramString2, String paramString3);
  
  public abstract void logout(String paramString);
  
  public abstract void requestSynchronizeAll();
  
  public abstract void synchronize();
  
  public abstract void update(class_941 paramclass_941);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.client.ClientControllerInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */