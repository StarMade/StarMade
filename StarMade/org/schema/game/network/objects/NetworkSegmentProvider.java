package org.schema.game.network.objects;

import class_749;
import org.schema.game.network.objects.remote.RemoteControlStructureBuffer;
import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
import org.schema.game.network.objects.remote.RemoteSegmentRemoteObj;
import org.schema.game.network.objects.remote.RemoteSegmentSignature;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteVector3i;

public class NetworkSegmentProvider
  extends NetworkObject
{
  public RemoteBuffer signatureRequestBuffer = new RemoteBuffer(RemoteVector3i.class, this);
  public RemoteBuffer segmentRequestBuffer = new RemoteBuffer(RemoteVector3i.class, this);
  public RemoteBuffer signatureBuffer = new RemoteBuffer(RemoteSegmentSignature.class, this);
  public RemoteBuffer segmentBuffer = new RemoteBuffer(RemoteSegmentRemoteObj.class, this);
  public RemoteBoolean requestedInitialControlMap = new RemoteBoolean(this);
  public RemoteControlStructureBuffer initialControlMap = new RemoteControlStructureBuffer(paramclass_749, this);
  public RemoteInventoryBuffer invetoryBuffer;
  public RemoteBoolean connectionReady = new RemoteBoolean(false, this);
  public RemoteBoolean signalDelete = new RemoteBoolean(false, this);
  
  public NetworkSegmentProvider(StateInterface paramStateInterface, class_749 paramclass_749)
  {
    super(paramStateInterface);
  }
  
  public void onDelete(StateInterface paramStateInterface) {}
  
  public void onInit(StateInterface paramStateInterface) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.NetworkSegmentProvider
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */