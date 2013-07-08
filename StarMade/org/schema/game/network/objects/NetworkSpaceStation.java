/*   1:    */package org.schema.game.network.objects;
/*   2:    */
/*   3:    */import ka;
/*   4:    */import kr;
/*   5:    */import ld;
/*   6:    */import ml;
/*   7:    */import org.schema.game.common.controller.elements.DistributionInterface;
/*   8:    */import org.schema.game.common.controller.elements.shield.NTShieldManagerInterface;
/*   9:    */import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*  10:    */import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*  11:    */import org.schema.schine.network.StateInterface;
/*  12:    */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*  13:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  14:    */import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*  15:    */import org.schema.schine.network.objects.remote.RemoteString;
/*  16:    */import org.schema.schine.network.objects.remote.RemoteStringArray;
/*  17:    */import org.schema.schine.network.objects.remote.RemoteVector4i;
/*  18:    */
/*  20:    */public class NetworkSpaceStation
/*  21:    */  extends NetworkSegmentController
/*  22:    */  implements kr, ml, DistributionInterface, NTShieldManagerInterface, NetworkDoorInterface, NetworkLiftInterface
/*  23:    */{
/*  24: 24 */  private RemoteIntArrayBuffer shieldUpdate = new RemoteIntArrayBuffer(4, this);
/*  25:    */  
/*  26: 26 */  public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
/*  27: 27 */  public RemoteBuffer liftActivate = new RemoteBuffer(RemoteVector4i.class, this);
/*  28:    */  
/*  29:    */  public RemoteInventoryBuffer inventoryBuffer;
/*  30: 30 */  public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(6, this);
/*  31:    */  
/*  32: 32 */  public RemoteIntArrayBuffer distributionModification = new RemoteIntArrayBuffer(9, this);
/*  33: 33 */  public RemoteBuffer doorActivate = new RemoteBuffer(RemoteVector4i.class, this);
/*  34: 34 */  public RemoteString debugState = new RemoteString("", this);
/*  35: 35 */  public RemoteArrayBuffer aiSettingsModification = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
/*  36: 36 */  public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
/*  37:    */  
/*  38:    */  public RemoteIntArrayBuffer getInventoryActivateBuffer() {
/*  39: 39 */    return this.inventoryActivateBuffer;
/*  40:    */  }
/*  41:    */  
/*  42:    */  public NetworkSpaceStation(StateInterface paramStateInterface, ka paramka) {
/*  43: 43 */    super(paramStateInterface, paramka);
/*  44: 44 */    this.inventoryBuffer = new RemoteInventoryBuffer(((ld)paramka).a(), this);
/*  45:    */  }
/*  46:    */  
/*  50:    */  public RemoteArrayBuffer getAiSettingsModification()
/*  51:    */  {
/*  52: 52 */    return this.aiSettingsModification;
/*  53:    */  }
/*  54:    */  
/*  57:    */  public RemoteString getDebugState()
/*  58:    */  {
/*  59: 59 */    return this.debugState;
/*  60:    */  }
/*  61:    */  
/*  66:    */  public RemoteBuffer getDoorActivate()
/*  67:    */  {
/*  68: 68 */    return this.doorActivate;
/*  69:    */  }
/*  70:    */  
/*  74:    */  public RemoteInventoryBuffer getInventoriesChangeBuffer()
/*  75:    */  {
/*  76: 76 */    return this.inventoryBuffer;
/*  77:    */  }
/*  78:    */  
/*  83:    */  public RemoteIntArrayBuffer getInventoryUpdateBuffer()
/*  84:    */  {
/*  85: 85 */    return this.inventoryUpdateBuffer;
/*  86:    */  }
/*  87:    */  
/*  91:    */  public RemoteBuffer getLiftActivate()
/*  92:    */  {
/*  93: 93 */    return this.liftActivate;
/*  94:    */  }
/*  95:    */  
/*  99:    */  public RemoteIntArrayBuffer getShieldUpdate()
/* 100:    */  {
/* 101:101 */    return this.shieldUpdate;
/* 102:    */  }
/* 103:    */  
/* 104:    */  public RemoteIntArrayBuffer getDistributionModification()
/* 105:    */  {
/* 106:106 */    return this.distributionModification;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
/* 110:    */  {
/* 111:111 */    return this.inventoryMultModBuffer;
/* 112:    */  }
/* 113:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkSpaceStation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */