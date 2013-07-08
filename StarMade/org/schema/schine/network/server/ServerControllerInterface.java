package org.schema.schine.network.server;

import class_941;
import org.schema.schine.network.ControllerInterface;
import org.schema.schine.network.RegisteredClientOnServer;

public abstract interface ServerControllerInterface
  extends ControllerInterface
{
  public abstract void broadcastMessage(String paramString, int paramInt);
  
  public abstract boolean isListenting();
  
  public abstract int registerClient(RegisteredClientOnServer paramRegisteredClientOnServer, float paramFloat);
  
  public abstract void unregister(int paramInt);
  
  public abstract void update(class_941 paramclass_941);
  
  public abstract boolean authenticate(String paramString1, String paramString2, String paramString3);
  
  public abstract void protectUserName(String paramString1, String paramString2, String paramString3);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.server.ServerControllerInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */