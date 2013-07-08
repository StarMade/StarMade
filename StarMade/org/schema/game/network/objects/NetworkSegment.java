package org.schema.game.network.objects;

import class_672;
import org.schema.game.network.objects.remote.RemoteSegmentGZipPackage;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;

@Deprecated
public class NetworkSegment
  extends NetworkObject
{
  public RemoteSegmentGZipPackage pack;
  private class_672 segment;
  
  public NetworkSegment(StateInterface paramStateInterface, class_672 paramclass_672)
  {
    super(paramStateInterface);
    this.segment = paramclass_672;
    this.pack = new RemoteSegmentGZipPackage(paramclass_672, this);
  }
  
  public void onDelete(StateInterface paramStateInterface) {}
  
  public void onInit(StateInterface paramStateInterface) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.NetworkSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */