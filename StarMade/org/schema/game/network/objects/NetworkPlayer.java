/*   1:    */package org.schema.game.network.objects;
/*   2:    */
/*   3:    */import cv;
/*   4:    */import java.io.PrintStream;
/*   5:    */import kd;
/*   6:    */import lE;
/*   7:    */import ml;
/*   8:    */import org.lwjgl.input.Mouse;
/*   9:    */import org.schema.game.network.objects.remote.RemoteCatalogEntryBuffer;
/*  10:    */import org.schema.game.network.objects.remote.RemoteControlledFileStream;
/*  11:    */import org.schema.game.network.objects.remote.RemoteFactionBuffer;
/*  12:    */import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*  13:    */import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*  14:    */import org.schema.game.network.objects.remote.RemoteProximitySector;
/*  15:    */import org.schema.game.network.objects.remote.RemoteProximitySystem;
/*  16:    */import org.schema.schine.network.StateInterface;
/*  17:    */import org.schema.schine.network.objects.NetworkObject;
/*  18:    */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*  19:    */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  20:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  21:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  22:    */import org.schema.schine.network.objects.remote.RemoteByte;
/*  23:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  24:    */import org.schema.schine.network.objects.remote.RemoteFloat;
/*  25:    */import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*  26:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  27:    */import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/*  28:    */import org.schema.schine.network.objects.remote.RemoteLong;
/*  29:    */import org.schema.schine.network.objects.remote.RemoteShort;
/*  30:    */import org.schema.schine.network.objects.remote.RemoteString;
/*  31:    */import org.schema.schine.network.objects.remote.RemoteStringArray;
/*  32:    */import org.schema.schine.network.objects.remote.RemoteVector3f;
/*  33:    */import org.schema.schine.network.objects.remote.RemoteVector3i;
/*  34:    */import org.schema.schine.network.objects.remote.RemoteVector4f;
/*  35:    */
/*  37:    */public class NetworkPlayer
/*  38:    */  extends NetworkObject
/*  39:    */  implements ml
/*  40:    */{
/*  41: 41 */  public RemoteFloat health = new RemoteFloat(this);
/*  42:    */  
/*  43: 43 */  public RemoteInteger sectorId = new RemoteInteger(this);
/*  44: 44 */  public RemoteVector3i sectorPos = new RemoteVector3i(this);
/*  45: 45 */  public RemoteInteger credits = new RemoteInteger(this);
/*  46:    */  
/*  47: 47 */  public RemoteInteger kills = new RemoteInteger(this);
/*  48: 48 */  public RemoteLong serverStartTime = new RemoteLong(this);
/*  49: 49 */  public RemoteLong serverModTime = new RemoteLong(this);
/*  50:    */  
/*  51: 51 */  public RemoteInteger deaths = new RemoteInteger(this);
/*  52:    */  
/*  53: 53 */  public RemoteBoolean isAdminClient = new RemoteBoolean(this);
/*  54:    */  
/*  55: 55 */  public RemoteInteger aquiredTargetId = new RemoteInteger(Integer.valueOf(-1), this);
/*  56:    */  
/*  57: 57 */  public RemoteArrayBuffer factionEntityIdChangeBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
/*  58: 58 */  public RemoteInteger selectedEntityId = new RemoteInteger(Integer.valueOf(-1), this);
/*  59:    */  
/*  60: 60 */  public RemoteInteger ping = new RemoteInteger(this);
/*  61:    */  
/*  62: 62 */  public RemoteInteger shipControllerSlot = new RemoteInteger(this);
/*  63:    */  
/*  65: 65 */  public RemoteString skinName = new RemoteString(this);
/*  66:    */  
/*  67: 67 */  public RemoteString playerName = new RemoteString(this);
/*  68: 68 */  public RemoteInteger factionId = new RemoteInteger(this);
/*  69:    */  
/*  70: 70 */  public RemoteFactionBuffer factionCreateBuffer = new RemoteFactionBuffer(this);
/*  71: 71 */  public RemoteBuffer factionLeaveBuffer = new RemoteBuffer(RemoteInteger.class, this);
/*  72: 72 */  public RemoteBuffer factionJoinBuffer = new RemoteBuffer(RemoteInteger.class, this);
/*  73: 73 */  public RemoteBuffer factionDescriptionEditRequest = new RemoteBuffer(RemoteString.class, this);
/*  74: 74 */  public RemoteBuffer factionChatRequests = new RemoteBuffer(RemoteString.class, this);
/*  75:    */  
/*  76: 76 */  public RemoteArrayBuffer roundEndBuffer = new RemoteArrayBuffer(3, RemoteIntegerArray.class, this);
/*  77:    */  
/*  78: 78 */  public RemoteArrayBuffer killedBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
/*  79:    */  
/*  80: 80 */  public RemoteBuffer shipUploadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
/*  81: 81 */  public RemoteBuffer skinUploadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
/*  82: 82 */  public RemoteBuffer skinDownloadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
/*  83:    */  
/*  84: 84 */  public RemoteBooleanArray activeControllerMask = new RemoteBooleanArray(4, this);
/*  85:    */  
/*  86: 86 */  public RemoteArrayBuffer controlRequestParameterBuffer = new RemoteArrayBuffer(9, RemoteIntegerArray.class, this);
/*  87:    */  
/*  89: 89 */  public RemoteBuffer creditTransactionBuffer = new RemoteBuffer(RemoteInteger.class, this);
/*  90:    */  
/*  91: 91 */  public RemoteBuffer dropOrPickupSlots = new RemoteBuffer(RemoteInteger.class, this);
/*  92:    */  
/*  93: 93 */  public RemoteBuffer messages = new RemoteBuffer(RemoteString.class, this);
/*  94:    */  
/*  95: 95 */  public RemoteBuffer skinRequestBuffer = new RemoteBuffer(RemoteString.class, this);
/*  96:    */  
/*  99: 99 */  public RemoteArrayBuffer catalogBuyBuffer = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
/* 100:    */  
/* 101:101 */  public RemoteArrayBuffer buyBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
/* 102:102 */  public RemoteArrayBuffer sellBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
/* 103:103 */  public RemoteBuffer spawnRequest = new RemoteBuffer(RemoteBoolean.class, this);
/* 104:    */  
/* 105:    */  public RemoteInventoryBuffer inventoryBuffer;
/* 106:    */  
/* 107:107 */  public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(3, this);
/* 108:108 */  public RemoteVector3f spawnPoint = new RemoteVector3f(this);
/* 109:    */  
/* 110:110 */  public RemoteBuffer spawnPointSetBuffer = new RemoteBuffer(RemoteVector3f.class, this);
/* 111:    */  
/* 112:    */  public RemoteProximitySector proximitySector;
/* 113:    */  
/* 114:    */  public RemoteProximitySystem proximitySystem;
/* 115:115 */  public RemoteBuffer creditsDropBuffer = new RemoteBuffer(RemoteInteger.class, this);
/* 116:    */  
/* 117:117 */  public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
/* 118:    */  
/* 119:119 */  public RemoteArrayBuffer controllerKeyBuffer = new RemoteArrayBuffer(4, RemoteIntegerArray.class, this);
/* 120:    */  
/* 121:121 */  public RemoteBuffer controllerKeyNameBuffer = new RemoteBuffer(RemoteString.class, this);
/* 122:    */  
/* 123:123 */  public RemoteCatalogEntryBuffer catalogBuffer = new RemoteCatalogEntryBuffer(this);
/* 124:    */  
/* 125:125 */  public RemoteShort keyboardOfController = new RemoteShort(this);
/* 126:    */  
/* 127:127 */  public RemoteBooleanArray mouseOfController = new RemoteBooleanArray(4, this);
/* 128:    */  
/* 129:129 */  public RemoteBuffer keyboardOfControllerBuffer = new RemoteBuffer(RemoteShort.class, this);
/* 130:130 */  public RemoteBuffer mouseOfControllerBuffer = new RemoteBuffer(RemoteByte.class, this);
/* 131:    */  
/* 137:137 */  public RemoteVector4f camOrientation = new RemoteVector4f(this);
/* 138:    */  
/* 140:140 */  public RemoteVector3i cockpit = new RemoteVector3i(kd.a, this);
/* 141:141 */  public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
/* 142:    */  
/* 143:143 */  public RemoteBuffer textureChangedBroadcastBuffer = new RemoteBuffer(RemoteLong.class, this);
/* 144:144 */  public RemoteBuffer requestFactionOpenToJoin = new RemoteBuffer(RemoteBoolean.class, this);
/* 145:145 */  public RemoteBuffer requestAttackNeutral = new RemoteBuffer(RemoteBoolean.class, this);
/* 146:146 */  public RemoteBuffer requestAutoDeclareWar = new RemoteBuffer(RemoteBoolean.class, this);
/* 147:    */  
/* 148:    */  public RemoteIntArrayBuffer getInventoryActivateBuffer()
/* 149:    */  {
/* 150:150 */    return this.inventoryActivateBuffer;
/* 151:    */  }
/* 152:    */  
/* 153:    */  public NetworkPlayer(StateInterface paramStateInterface, lE paramlE) {
/* 154:154 */    super(paramStateInterface);
/* 155:155 */    this.inventoryBuffer = new RemoteInventoryBuffer(paramlE, this);
/* 156:156 */    this.proximitySector = new RemoteProximitySector(paramlE.a(), this);
/* 157:157 */    this.proximitySystem = new RemoteProximitySystem(paramlE.a(), this);
/* 158:    */  }
/* 159:    */  
/* 165:    */  public RemoteInventoryBuffer getInventoriesChangeBuffer()
/* 166:    */  {
/* 167:167 */    return this.inventoryBuffer;
/* 168:    */  }
/* 169:    */  
/* 172:    */  public RemoteIntArrayBuffer getInventoryUpdateBuffer()
/* 173:    */  {
/* 174:174 */    return this.inventoryUpdateBuffer;
/* 175:    */  }
/* 176:    */  
/* 177:    */  public void handleKeyEvent(boolean paramBoolean, int paramInt) {
/* 178:178 */    for (int i = 0; i < cv.a.length; i++) {
/* 179:179 */      if (cv.a[i].a() == paramInt) {
/* 180:180 */        cv.a[i].a(this.keyboardOfControllerBuffer, paramBoolean, isOnServer());
/* 181:    */      }
/* 182:    */    }
/* 183:    */  }
/* 184:    */  
/* 198:    */  public void handleMouseEvent(boolean paramBoolean, int paramInt)
/* 199:    */  {
/* 200:200 */    paramBoolean = (byte)(paramBoolean ? paramInt : -paramInt - 1);
/* 201:201 */    this.mouseOfControllerBuffer.add(new RemoteByte(Byte.valueOf(paramBoolean), this));
/* 202:    */  }
/* 203:    */  
/* 207:    */  public boolean isMouseDown(int paramInt)
/* 208:    */  {
/* 209:209 */    if ((paramInt >= 0) && (paramInt < ((RemoteField[])this.mouseOfController.get()).length)) {
/* 210:210 */      return ((Boolean)((RemoteField[])this.mouseOfController.get())[paramInt].get()).booleanValue();
/* 211:    */    }
/* 212:212 */    System.err.println("[WARNING] Mouse button not registered! " + paramInt);
/* 213:213 */    return false;
/* 214:    */  }
/* 215:    */  
/* 219:    */  public void onDelete(StateInterface paramStateInterface) {}
/* 220:    */  
/* 224:    */  public void onInit(StateInterface paramStateInterface) {}
/* 225:    */  
/* 229:    */  public void setMouseDown()
/* 230:    */  {
/* 231:231 */    if (!Mouse.isCreated()) {
/* 232:232 */      return;
/* 233:    */    }
/* 234:234 */    for (int i = 0; i < ((RemoteField[])this.mouseOfController.get()).length; i++) {
/* 235:235 */      ((RemoteField[])this.mouseOfController.get())[i].set(Boolean.valueOf(Mouse.isButtonDown(i)), true);
/* 236:    */    }
/* 237:    */  }
/* 238:    */  
/* 239:    */  public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
/* 240:    */  {
/* 241:241 */    return this.inventoryMultModBuffer;
/* 242:    */  }
/* 243:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkPlayer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */