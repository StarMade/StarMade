package org.schema.schine.network;

import org.schema.schine.network.objects.Sendable;

public abstract interface ControllerInterface
{
  public abstract void onRemoveEntity(Sendable paramSendable);
  
  public abstract long calculateStartTime();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.ControllerInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */