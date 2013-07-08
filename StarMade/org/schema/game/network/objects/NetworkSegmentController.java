package org.schema.game.network.objects;

import class_753;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkEntity;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
import org.schema.schine.network.objects.remote.RemoteIntegerArray;
import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteVector3f;
import org.schema.schine.network.objects.remote.RemoteVector3i;
import org.schema.schine.network.objects.remote.RemoteVector4i;

public class NetworkSegmentController
  extends NetworkEntity
{
  public RemoteVector3i minSize = new RemoteVector3i(this);
  public RemoteVector3i maxSize = new RemoteVector3i(this);
  public RemoteIntPrimitive lastModifiedPlayerId = new RemoteIntPrimitive(0, this);
  public RemoteString uniqueIdentifier = new RemoteString(this);
  public RemoteString realName = new RemoteString(this);
  public RemoteBuffer blockActivationBuffer = new RemoteBuffer(RemoteVector4i.class, this);
  public RemoteBuffer dirtySegmentBuffer = new RemoteBuffer(RemoteVector3i.class, this);
  public RemoteSegmentPieceBuffer modificationBuffer;
  public RemoteLongPrimitive initialPower = new RemoteLongPrimitive(0L, this);
  public RemoteLongPrimitive initialShields = new RemoteLongPrimitive(0L, this);
  public RemoteArrayBuffer controlledByBuffer = new RemoteArrayBuffer(8, RemoteIntegerArray.class, this);
  public RemoteString dockedTo = new RemoteString("NONE", this);
  public RemoteVector3f dockingSize = new RemoteVector3f(this);
  public RemoteVector4i dockedElement = new RemoteVector4i(this);
  public RemoteBuffer explosions = new RemoteBuffer(RemoteVector3f.class, this);
  
  public NetworkSegmentController(StateInterface paramStateInterface, class_753 paramclass_753)
  {
    super(paramStateInterface);
    if (paramclass_753.getState().getLocalAndRemoteObjectContainer().getRemoteObjects().containsKey(paramclass_753.getId())) {
      System.err.println("[WARNING][SEGCONTROLLER] making a new instance of existing NT object " + paramclass_753 + ", " + paramclass_753.getState());
    }
    if (paramclass_753.getNetworkObject() != null) {
      System.err.println("[WARNING][SEGCONTROLLER] overwriting ship's existing NT object " + paramclass_753 + ", " + paramclass_753.getState());
    }
    this.modificationBuffer = new RemoteSegmentPieceBuffer(paramclass_753, this);
  }
  
  public void onDelete(StateInterface paramStateInterface) {}
  
  public void onInit(StateInterface paramStateInterface) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.NetworkSegmentController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */