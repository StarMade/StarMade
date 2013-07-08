package org.schema.game.network.objects.remote;

import class_749;
import class_753;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteField;

public class RemoteControlStructure
  extends RemoteField
{
  private class_749 segmentController;
  
  public RemoteControlStructure(class_749 paramclass_749, NetworkObject paramNetworkObject)
  {
    super(Boolean.valueOf(true), paramNetworkObject);
    this.segmentController = paramclass_749;
  }
  
  public RemoteControlStructure(class_749 paramclass_749, boolean paramBoolean)
  {
    super(Boolean.valueOf(true), paramBoolean);
    this.segmentController = paramclass_749;
  }
  
  public boolean initialSynchUpdateOnly()
  {
    return true;
  }
  
  public int byteLength()
  {
    return 0;
  }
  
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
  {
    this.segmentController.a48().getControlElementMap().deserializeZipped(paramDataInputStream);
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    this.segmentController.a48().getControlElementMap().serializeZipped(paramDataOutputStream);
    return 1;
  }
  
  public int toByteStream(DataOutputStream paramDataOutputStream, class_753 paramclass_753)
  {
    paramclass_753.getControlElementMap().serializeZipped(paramDataOutputStream);
    return 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteControlStructure
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */