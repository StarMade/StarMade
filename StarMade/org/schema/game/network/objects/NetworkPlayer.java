/*     */ package org.schema.game.network.objects;
/*     */ 
/*     */ import cv;
/*     */ import java.io.PrintStream;
/*     */ import kd;
/*     */ import lE;
/*     */ import ml;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.schema.game.network.objects.remote.RemoteCatalogEntryBuffer;
/*     */ import org.schema.game.network.objects.remote.RemoteControlledFileStream;
/*     */ import org.schema.game.network.objects.remote.RemoteFactionBuffer;
/*     */ import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*     */ import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*     */ import org.schema.game.network.objects.remote.RemoteProximitySector;
/*     */ import org.schema.game.network.objects.remote.RemoteProximitySystem;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*     */ import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteByte;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import org.schema.schine.network.objects.remote.RemoteFloat;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteLong;
/*     */ import org.schema.schine.network.objects.remote.RemoteShort;
/*     */ import org.schema.schine.network.objects.remote.RemoteString;
/*     */ import org.schema.schine.network.objects.remote.RemoteStringArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3f;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3i;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector4f;
/*     */ 
/*     */ public class NetworkPlayer extends NetworkObject
/*     */   implements ml
/*     */ {
/*  41 */   public RemoteFloat health = new RemoteFloat(this);
/*     */ 
/*  43 */   public RemoteInteger sectorId = new RemoteInteger(this);
/*  44 */   public RemoteVector3i sectorPos = new RemoteVector3i(this);
/*  45 */   public RemoteInteger credits = new RemoteInteger(this);
/*     */ 
/*  47 */   public RemoteInteger kills = new RemoteInteger(this);
/*  48 */   public RemoteLong serverStartTime = new RemoteLong(this);
/*  49 */   public RemoteLong serverModTime = new RemoteLong(this);
/*     */ 
/*  51 */   public RemoteInteger deaths = new RemoteInteger(this);
/*     */ 
/*  53 */   public RemoteBoolean isAdminClient = new RemoteBoolean(this);
/*     */ 
/*  55 */   public RemoteInteger aquiredTargetId = new RemoteInteger(Integer.valueOf(-1), this);
/*     */ 
/*  57 */   public RemoteArrayBuffer factionEntityIdChangeBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
/*  58 */   public RemoteInteger selectedEntityId = new RemoteInteger(Integer.valueOf(-1), this);
/*     */ 
/*  60 */   public RemoteInteger ping = new RemoteInteger(this);
/*     */ 
/*  62 */   public RemoteInteger shipControllerSlot = new RemoteInteger(this);
/*     */ 
/*  65 */   public RemoteString skinName = new RemoteString(this);
/*     */ 
/*  67 */   public RemoteString playerName = new RemoteString(this);
/*  68 */   public RemoteInteger factionId = new RemoteInteger(this);
/*     */ 
/*  70 */   public RemoteFactionBuffer factionCreateBuffer = new RemoteFactionBuffer(this);
/*  71 */   public RemoteBuffer factionLeaveBuffer = new RemoteBuffer(RemoteInteger.class, this);
/*  72 */   public RemoteBuffer factionJoinBuffer = new RemoteBuffer(RemoteInteger.class, this);
/*  73 */   public RemoteBuffer factionDescriptionEditRequest = new RemoteBuffer(RemoteString.class, this);
/*  74 */   public RemoteBuffer factionChatRequests = new RemoteBuffer(RemoteString.class, this);
/*     */ 
/*  76 */   public RemoteArrayBuffer roundEndBuffer = new RemoteArrayBuffer(3, RemoteIntegerArray.class, this);
/*     */ 
/*  78 */   public RemoteArrayBuffer killedBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
/*     */ 
/*  80 */   public RemoteBuffer shipUploadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
/*  81 */   public RemoteBuffer skinUploadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
/*  82 */   public RemoteBuffer skinDownloadBuffer = new RemoteBuffer(RemoteControlledFileStream.class, this);
/*     */ 
/*  84 */   public RemoteBooleanArray activeControllerMask = new RemoteBooleanArray(4, this);
/*     */ 
/*  86 */   public RemoteArrayBuffer controlRequestParameterBuffer = new RemoteArrayBuffer(9, RemoteIntegerArray.class, this);
/*     */ 
/*  89 */   public RemoteBuffer creditTransactionBuffer = new RemoteBuffer(RemoteInteger.class, this);
/*     */ 
/*  91 */   public RemoteBuffer dropOrPickupSlots = new RemoteBuffer(RemoteInteger.class, this);
/*     */ 
/*  93 */   public RemoteBuffer messages = new RemoteBuffer(RemoteString.class, this);
/*     */ 
/*  95 */   public RemoteBuffer skinRequestBuffer = new RemoteBuffer(RemoteString.class, this);
/*     */ 
/*  99 */   public RemoteArrayBuffer catalogBuyBuffer = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
/*     */ 
/* 101 */   public RemoteArrayBuffer buyBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
/* 102 */   public RemoteArrayBuffer sellBuffer = new RemoteArrayBuffer(2, RemoteIntegerArray.class, this);
/* 103 */   public RemoteBuffer spawnRequest = new RemoteBuffer(RemoteBoolean.class, this);
/*     */   public RemoteInventoryBuffer inventoryBuffer;
/* 107 */   public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(3, this);
/* 108 */   public RemoteVector3f spawnPoint = new RemoteVector3f(this);
/*     */ 
/* 110 */   public RemoteBuffer spawnPointSetBuffer = new RemoteBuffer(RemoteVector3f.class, this);
/*     */   public RemoteProximitySector proximitySector;
/*     */   public RemoteProximitySystem proximitySystem;
/* 115 */   public RemoteBuffer creditsDropBuffer = new RemoteBuffer(RemoteInteger.class, this);
/*     */ 
/* 117 */   public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
/*     */ 
/* 119 */   public RemoteArrayBuffer controllerKeyBuffer = new RemoteArrayBuffer(4, RemoteIntegerArray.class, this);
/*     */ 
/* 121 */   public RemoteBuffer controllerKeyNameBuffer = new RemoteBuffer(RemoteString.class, this);
/*     */ 
/* 123 */   public RemoteCatalogEntryBuffer catalogBuffer = new RemoteCatalogEntryBuffer(this);
/*     */ 
/* 125 */   public RemoteShort keyboardOfController = new RemoteShort(this);
/*     */ 
/* 127 */   public RemoteBooleanArray mouseOfController = new RemoteBooleanArray(4, this);
/*     */ 
/* 129 */   public RemoteBuffer keyboardOfControllerBuffer = new RemoteBuffer(RemoteShort.class, this);
/* 130 */   public RemoteBuffer mouseOfControllerBuffer = new RemoteBuffer(RemoteByte.class, this);
/*     */ 
/* 137 */   public RemoteVector4f camOrientation = new RemoteVector4f(this);
/*     */ 
/* 140 */   public RemoteVector3i cockpit = new RemoteVector3i(kd.a, this);
/* 141 */   public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
/*     */ 
/* 143 */   public RemoteBuffer textureChangedBroadcastBuffer = new RemoteBuffer(RemoteLong.class, this);
/* 144 */   public RemoteBuffer requestFactionOpenToJoin = new RemoteBuffer(RemoteBoolean.class, this);
/* 145 */   public RemoteBuffer requestAttackNeutral = new RemoteBuffer(RemoteBoolean.class, this);
/* 146 */   public RemoteBuffer requestAutoDeclareWar = new RemoteBuffer(RemoteBoolean.class, this);
/*     */ 
/*     */   public RemoteIntArrayBuffer getInventoryActivateBuffer()
/*     */   {
/* 150 */     return this.inventoryActivateBuffer;
/*     */   }
/*     */ 
/*     */   public NetworkPlayer(StateInterface paramStateInterface, lE paramlE) {
/* 154 */     super(paramStateInterface);
/* 155 */     this.inventoryBuffer = new RemoteInventoryBuffer(paramlE, this);
/* 156 */     this.proximitySector = new RemoteProximitySector(paramlE.a(), this);
/* 157 */     this.proximitySystem = new RemoteProximitySystem(paramlE.a(), this);
/*     */   }
/*     */ 
/*     */   public RemoteInventoryBuffer getInventoriesChangeBuffer()
/*     */   {
/* 167 */     return this.inventoryBuffer;
/*     */   }
/*     */ 
/*     */   public RemoteIntArrayBuffer getInventoryUpdateBuffer()
/*     */   {
/* 174 */     return this.inventoryUpdateBuffer;
/*     */   }
/*     */ 
/*     */   public void handleKeyEvent(boolean paramBoolean, int paramInt) {
/* 178 */     for (int i = 0; i < cv.a.length; i++)
/* 179 */       if (cv.a[i].a() == paramInt)
/* 180 */         cv.a[i].a(this.keyboardOfControllerBuffer, paramBoolean, isOnServer());
/*     */   }
/*     */ 
/*     */   public void handleMouseEvent(boolean paramBoolean, int paramInt)
/*     */   {
/* 200 */     paramBoolean = (byte)(paramBoolean ? paramInt : -paramInt - 1);
/* 201 */     this.mouseOfControllerBuffer.add(new RemoteByte(Byte.valueOf(paramBoolean), this));
/*     */   }
/*     */ 
/*     */   public boolean isMouseDown(int paramInt)
/*     */   {
/* 209 */     if ((paramInt >= 0) && (paramInt < ((RemoteField[])this.mouseOfController.get()).length)) {
/* 210 */       return ((Boolean)((RemoteField[])this.mouseOfController.get())[paramInt].get()).booleanValue();
/*     */     }
/* 212 */     System.err.println("[WARNING] Mouse button not registered! " + paramInt);
/* 213 */     return false;
/*     */   }
/*     */ 
/*     */   public void onDelete(StateInterface paramStateInterface)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onInit(StateInterface paramStateInterface)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setMouseDown()
/*     */   {
/* 231 */     if (!Mouse.isCreated()) {
/* 232 */       return;
/*     */     }
/* 234 */     for (int i = 0; i < ((RemoteField[])this.mouseOfController.get()).length; i++)
/* 235 */       ((RemoteField[])this.mouseOfController.get())[i].set(Boolean.valueOf(Mouse.isButtonDown(i)), true);
/*     */   }
/*     */ 
/*     */   public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
/*     */   {
/* 241 */     return this.inventoryMultModBuffer;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkPlayer
 * JD-Core Version:    0.6.2
 */