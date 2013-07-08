package org.schema.schine.network.objects;

import class_941;
import org.schema.schine.network.Identifiable;
import org.schema.schine.network.StateInterface;

public abstract interface Sendable
  extends Identifiable
{
  public abstract void cleanUpOnEntityDelete();
  
  public abstract NetworkObject getNetworkObject();
  
  public abstract StateInterface getState();
  
  public abstract void initFromNetworkObject(NetworkObject paramNetworkObject);
  
  public abstract void initialize();
  
  public abstract boolean isMarkedForDeleteVolatile();
  
  public abstract boolean isMarkedForDeleteVolatileSent();
  
  public abstract boolean isOnServer();
  
  public abstract void newNetworkObject();
  
  public abstract void setMarkedForDeleteVolatile(boolean paramBoolean);
  
  public abstract void setMarkedForDeleteVolatileSent(boolean paramBoolean);
  
  public abstract void updateFromNetworkObject(NetworkObject paramNetworkObject);
  
  public abstract void updateLocal(class_941 paramclass_941);
  
  public abstract void updateToFullNetworkObject();
  
  public abstract void updateToNetworkObject();
  
  public abstract void destroyPersistent();
  
  public abstract boolean isMarkedForPermanentDelete();
  
  public abstract void markedForPermanentDelete(boolean paramBoolean);
  
  public abstract boolean isUpdatable();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.Sendable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */