package org.schema.game.network.objects;

import class_367;
import class_643;
import class_747;
import class_748;
import java.io.PrintStream;
import org.lwjgl.input.Mouse;
import org.schema.game.network.objects.remote.RemoteCatalogEntryBuffer;
import org.schema.game.network.objects.remote.RemoteControlledFileStream;
import org.schema.game.network.objects.remote.RemoteFactionBuffer;
import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
import org.schema.game.network.objects.remote.RemoteProximitySector;
import org.schema.game.network.objects.remote.RemoteProximitySystem;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBooleanArray;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteByte;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteFloat;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteIntegerArray;
import org.schema.schine.network.objects.remote.RemoteLong;
import org.schema.schine.network.objects.remote.RemoteShort;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteStringArray;
import org.schema.schine.network.objects.remote.RemoteVector3f;
import org.schema.schine.network.objects.remote.RemoteVector3i;
import org.schema.schine.network.objects.remote.RemoteVector4f;

public class NetworkPlayer
  extends NetworkObject
  implements class_643
{
  public RemoteFloat health = new RemoteFloat(this);
  public RemoteInteger sectorId = new RemoteInteger(this);
  public RemoteVector3i sectorPos = new RemoteVector3i(this);
  public RemoteInteger credits = new RemoteInteger(this);
  public RemoteInteger kills = new RemoteInteger(this);
  public RemoteLong serverStartTime = new RemoteLong(this);
  public RemoteLong serverModTime = new RemoteLong(this);
  public RemoteInteger deaths = new RemoteInteger(this);
  public RemoteBoolean isAdminClient = new RemoteBoolean(this);
  public RemoteInteger aquiredTargetId = new RemoteInteger(Integer.valueOf(-1), this);
  public RemoteArrayBuffer factionEntityIdChangeBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
  public RemoteInteger selectedEntityId = new RemoteInteger(Integer.valueOf(-1), this);
  public RemoteInteger ping = new RemoteInteger(this);
  public RemoteInteger shipControllerSlot = new RemoteInteger(this);
  public RemoteString skinName = new RemoteString(this);
  public RemoteString playerName = new RemoteString(this);
  public RemoteInteger factionId = new RemoteInteger(this);
  public RemoteFactionBuffer factionCreateBuffer = new RemoteFactionBuffer(this);
  public RemoteBuffer factionLeaveBuffer = new RemoteBuffer(RemoteInteger.class, this);
  public RemoteBuffer factionJoinBuffer = new RemoteBuffer(RemoteInteger.class, this);
  public RemoteBuffer factionDescriptionEditRequest = new RemoteBuffer(RemoteString.class, this);
  public RemoteBuffer factionChatRequests = new RemoteBuffer(RemoteString.class, this);
  public RemoteArrayBuffer roundEndBuffer = new RemoteArrayBuffer(3, RemoteIntegerArray.class, this);
  public RemoteArrayBuffer killedBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
  public RemoteBuffer shipUploadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
  public RemoteBuffer skinUploadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
  public RemoteBuffer skinDownloadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
  public RemoteBooleanArray activeControllerMask = new RemoteBooleanArray(4, this);
  public RemoteArrayBuffer controlRequestParameterBuffer = new RemoteArrayBuffer(9, RemoteIntegerArray.class, this);
  public RemoteBuffer creditTransactionBuffer = new RemoteBuffer(RemoteInteger.class, this);
  public RemoteBuffer dropOrPickupSlots = new RemoteBuffer(RemoteInteger.class, this);
  public RemoteBuffer messages = new RemoteBuffer(RemoteString.class, this);
  public RemoteBuffer skinRequestBuffer = new RemoteBuffer(RemoteString.class, this);
  public RemoteArrayBuffer catalogBuyBuffer = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
  public RemoteArrayBuffer buyBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
  public RemoteArrayBuffer sellBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
  public RemoteBuffer spawnRequest = new RemoteBuffer(RemoteBoolean.class, this);
  public RemoteInventoryBuffer inventoryBuffer = new RemoteInventoryBuffer(paramclass_748, this);
  public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(3, this);
  public RemoteVector3f spawnPoint = new RemoteVector3f(this);
  public RemoteBuffer spawnPointSetBuffer = new RemoteBuffer(RemoteVector3f.class, this);
  public RemoteProximitySector proximitySector = new RemoteProximitySector(paramclass_748.a136(), this);
  public RemoteProximitySystem proximitySystem = new RemoteProximitySystem(paramclass_748.a137(), this);
  public RemoteBuffer creditsDropBuffer = new RemoteBuffer(RemoteInteger.class, this);
  public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
  public RemoteArrayBuffer controllerKeyBuffer = new RemoteArrayBuffer(4, RemoteIntegerArray.class, this);
  public RemoteBuffer controllerKeyNameBuffer = new RemoteBuffer(RemoteString.class, this);
  public RemoteCatalogEntryBuffer catalogBuffer = new RemoteCatalogEntryBuffer(this);
  public RemoteShort keyboardOfController = new RemoteShort(this);
  public RemoteBooleanArray mouseOfController = new RemoteBooleanArray(4, this);
  public RemoteBuffer keyboardOfControllerBuffer = new RemoteBuffer(RemoteShort.class, this);
  public RemoteBuffer mouseOfControllerBuffer = new RemoteBuffer(RemoteByte.class, this);
  public RemoteVector4f camOrientation = new RemoteVector4f(this);
  public RemoteVector3i cockpit = new RemoteVector3i(class_747.field_136, this);
  public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
  public RemoteBuffer textureChangedBroadcastBuffer = new RemoteBuffer(RemoteLong.class, this);
  public RemoteBuffer requestFactionOpenToJoin = new RemoteBuffer(RemoteBoolean.class, this);
  public RemoteBuffer requestAttackNeutral = new RemoteBuffer(RemoteBoolean.class, this);
  public RemoteBuffer requestAutoDeclareWar = new RemoteBuffer(RemoteBoolean.class, this);
  
  public RemoteIntArrayBuffer getInventoryActivateBuffer()
  {
    return this.inventoryActivateBuffer;
  }
  
  public NetworkPlayer(StateInterface paramStateInterface, class_748 paramclass_748)
  {
    super(paramStateInterface);
  }
  
  public RemoteInventoryBuffer getInventoriesChangeBuffer()
  {
    return this.inventoryBuffer;
  }
  
  public RemoteIntArrayBuffer getInventoryUpdateBuffer()
  {
    return this.inventoryUpdateBuffer;
  }
  
  public void handleKeyEvent(boolean paramBoolean, int paramInt)
  {
    for (int i = 0; i < class_367.field_711.length; i++) {
      if (class_367.field_711[i].a5() == paramInt) {
        class_367.field_711[i].a8(this.keyboardOfControllerBuffer, paramBoolean, isOnServer());
      }
    }
  }
  
  public void handleMouseEvent(boolean paramBoolean, int paramInt)
  {
    paramBoolean = (byte)(paramBoolean ? paramInt : -paramInt - 1);
    this.mouseOfControllerBuffer.add(new RemoteByte(Byte.valueOf(paramBoolean), this));
  }
  
  public boolean isMouseDown(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < ((RemoteField[])this.mouseOfController.get()).length)) {
      return ((Boolean)((RemoteField[])this.mouseOfController.get())[paramInt].get()).booleanValue();
    }
    System.err.println("[WARNING] Mouse button not registered! " + paramInt);
    return false;
  }
  
  public void onDelete(StateInterface paramStateInterface) {}
  
  public void onInit(StateInterface paramStateInterface) {}
  
  public void setMouseDown()
  {
    if (!Mouse.isCreated()) {
      return;
    }
    for (int i = 0; i < ((RemoteField[])this.mouseOfController.get()).length; i++) {
      ((RemoteField[])this.mouseOfController.get())[i].set(Boolean.valueOf(Mouse.isButtonDown(i)), true);
    }
  }
  
  public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
  {
    return this.inventoryMultModBuffer;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.objects.NetworkPlayer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */