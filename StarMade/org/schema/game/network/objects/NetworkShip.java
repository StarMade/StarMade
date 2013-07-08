/*   1:    */package org.schema.game.network.objects;
/*   2:    */
/*   3:    */import kd;
/*   4:    */import kr;
/*   5:    */import ml;
/*   6:    */import org.schema.game.common.controller.elements.DistributionInterface;
/*   7:    */import org.schema.game.common.controller.elements.shield.NTShieldManagerInterface;
/*   8:    */import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*   9:    */import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*  10:    */import org.schema.schine.network.StateInterface;
/*  11:    */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*  12:    */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  13:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  14:    */import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*  15:    */import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
/*  16:    */import org.schema.schine.network.objects.remote.RemoteString;
/*  17:    */import org.schema.schine.network.objects.remote.RemoteStringArray;
/*  18:    */import org.schema.schine.network.objects.remote.RemoteVector3f;
/*  19:    */import org.schema.schine.network.objects.remote.RemoteVector4i;
/*  20:    */
/*  22:    */public class NetworkShip
/*  23:    */  extends NetworkSegmentController
/*  24:    */  implements kr, ml, DistributionInterface, NTShieldManagerInterface, NetworkDoorInterface
/*  25:    */{
/*  26: 26 */  private RemoteIntArrayBuffer shieldUpdate = new RemoteIntArrayBuffer(4, this);
/*  27:    */  
/*  28: 28 */  public RemoteVector3f moveDir = new RemoteVector3f(this);
/*  29: 29 */  public RemoteVector3f orientationDir = new RemoteVector3f(this);
/*  30: 30 */  public RemoteVector3f targetPosition = new RemoteVector3f(this);
/*  31: 31 */  public RemoteVector3f targetVelocity = new RemoteVector3f(this);
/*  32:    */  
/*  33: 33 */  public RemoteLongPrimitive coreDestructionStarted = new RemoteLongPrimitive(-1L, this);
/*  34: 34 */  public RemoteLongPrimitive coreDestructionDuration = new RemoteLongPrimitive(-1L, this);
/*  35: 35 */  public RemoteBuffer onHitNotices = new RemoteBuffer(RemoteBoolean.class, this);
/*  36: 36 */  public RemoteBuffer doorActivate = new RemoteBuffer(RemoteVector4i.class, this);
/*  37: 37 */  public RemoteString debugState = new RemoteString("", this);
/*  38: 38 */  public RemoteArrayBuffer aiSettingsModification = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
/*  39:    */  
/*  40: 40 */  public RemoteBoolean jamming = new RemoteBoolean(this);
/*  41: 41 */  public RemoteBoolean cloaked = new RemoteBoolean(this);
/*  42:    */  
/*  43: 43 */  public RemoteIntArrayBuffer distributionModification = new RemoteIntArrayBuffer(9, this);
/*  44:    */  
/*  45:    */  public RemoteInventoryBuffer inventoryBuffer;
/*  46: 46 */  public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
/*  47:    */  
/*  48: 48 */  public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(6, this);
/*  49: 49 */  public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
/*  50:    */  
/*  51:    */  public RemoteIntArrayBuffer getInventoryActivateBuffer() {
/*  52: 52 */    return this.inventoryActivateBuffer;
/*  53:    */  }
/*  54:    */  
/*  55:    */  public NetworkShip(StateInterface paramStateInterface, kd paramkd)
/*  56:    */  {
/*  57: 57 */    super(paramStateInterface, paramkd);
/*  58: 58 */    this.inventoryBuffer = new RemoteInventoryBuffer(paramkd.a(), this);
/*  59:    */  }
/*  60:    */  
/*  65:    */  public RemoteArrayBuffer getAiSettingsModification()
/*  66:    */  {
/*  67: 67 */    return this.aiSettingsModification;
/*  68:    */  }
/*  69:    */  
/*  71:    */  public RemoteString getDebugState()
/*  72:    */  {
/*  73: 73 */    return this.debugState;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public RemoteIntArrayBuffer getDistributionModification()
/*  77:    */  {
/*  78: 78 */    return this.distributionModification;
/*  79:    */  }
/*  80:    */  
/*  87:    */  public RemoteInventoryBuffer getInventoriesChangeBuffer()
/*  88:    */  {
/*  89: 89 */    return this.inventoryBuffer;
/*  90:    */  }
/*  91:    */  
/*  96:    */  public RemoteIntArrayBuffer getInventoryUpdateBuffer()
/*  97:    */  {
/*  98: 98 */    return this.inventoryUpdateBuffer;
/*  99:    */  }
/* 100:    */  
/* 105:    */  public RemoteIntArrayBuffer getShieldUpdate()
/* 106:    */  {
/* 107:107 */    return this.shieldUpdate;
/* 108:    */  }
/* 109:    */  
/* 111:    */  public RemoteBuffer getDoorActivate()
/* 112:    */  {
/* 113:113 */    return this.doorActivate;
/* 114:    */  }
/* 115:    */  
/* 117:    */  public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
/* 118:    */  {
/* 119:119 */    return this.inventoryMultModBuffer;
/* 120:    */  }
/* 121:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkShip
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */