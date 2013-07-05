/*     */ package org.schema.game.network.objects;
/*     */ 
/*     */ import kd;
/*     */ import kr;
/*     */ import ml;
/*     */ import org.schema.game.common.controller.elements.DistributionInterface;
/*     */ import org.schema.game.common.controller.elements.shield.NTShieldManagerInterface;
/*     */ import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*     */ import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
/*     */ import org.schema.schine.network.objects.remote.RemoteString;
/*     */ import org.schema.schine.network.objects.remote.RemoteStringArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3f;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector4i;
/*     */ 
/*     */ public class NetworkShip extends NetworkSegmentController
/*     */   implements kr, ml, DistributionInterface, NTShieldManagerInterface, NetworkDoorInterface
/*     */ {
/*  26 */   private RemoteIntArrayBuffer shieldUpdate = new RemoteIntArrayBuffer(4, this);
/*     */ 
/*  28 */   public RemoteVector3f moveDir = new RemoteVector3f(this);
/*  29 */   public RemoteVector3f orientationDir = new RemoteVector3f(this);
/*  30 */   public RemoteVector3f targetPosition = new RemoteVector3f(this);
/*  31 */   public RemoteVector3f targetVelocity = new RemoteVector3f(this);
/*     */ 
/*  33 */   public RemoteLongPrimitive coreDestructionStarted = new RemoteLongPrimitive(-1L, this);
/*  34 */   public RemoteLongPrimitive coreDestructionDuration = new RemoteLongPrimitive(-1L, this);
/*  35 */   public RemoteBuffer onHitNotices = new RemoteBuffer(RemoteBoolean.class, this);
/*  36 */   public RemoteBuffer doorActivate = new RemoteBuffer(RemoteVector4i.class, this);
/*  37 */   public RemoteString debugState = new RemoteString("", this);
/*  38 */   public RemoteArrayBuffer aiSettingsModification = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
/*     */ 
/*  40 */   public RemoteBoolean jamming = new RemoteBoolean(this);
/*  41 */   public RemoteBoolean cloaked = new RemoteBoolean(this);
/*     */ 
/*  43 */   public RemoteIntArrayBuffer distributionModification = new RemoteIntArrayBuffer(9, this);
/*     */   public RemoteInventoryBuffer inventoryBuffer;
/*  46 */   public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
/*     */ 
/*  48 */   public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(6, this);
/*  49 */   public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
/*     */ 
/*     */   public RemoteIntArrayBuffer getInventoryActivateBuffer() {
/*  52 */     return this.inventoryActivateBuffer;
/*     */   }
/*     */ 
/*     */   public NetworkShip(StateInterface paramStateInterface, kd paramkd)
/*     */   {
/*  57 */     super(paramStateInterface, paramkd);
/*  58 */     this.inventoryBuffer = new RemoteInventoryBuffer(paramkd.a(), this);
/*     */   }
/*     */ 
/*     */   public RemoteArrayBuffer getAiSettingsModification()
/*     */   {
/*  67 */     return this.aiSettingsModification;
/*     */   }
/*     */ 
/*     */   public RemoteString getDebugState()
/*     */   {
/*  73 */     return this.debugState;
/*     */   }
/*     */ 
/*     */   public RemoteIntArrayBuffer getDistributionModification()
/*     */   {
/*  78 */     return this.distributionModification;
/*     */   }
/*     */ 
/*     */   public RemoteInventoryBuffer getInventoriesChangeBuffer()
/*     */   {
/*  89 */     return this.inventoryBuffer;
/*     */   }
/*     */ 
/*     */   public RemoteIntArrayBuffer getInventoryUpdateBuffer()
/*     */   {
/*  98 */     return this.inventoryUpdateBuffer;
/*     */   }
/*     */ 
/*     */   public RemoteIntArrayBuffer getShieldUpdate()
/*     */   {
/* 107 */     return this.shieldUpdate;
/*     */   }
/*     */ 
/*     */   public RemoteBuffer getDoorActivate()
/*     */   {
/* 113 */     return this.doorActivate;
/*     */   }
/*     */ 
/*     */   public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
/*     */   {
/* 119 */     return this.inventoryMultModBuffer;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkShip
 * JD-Core Version:    0.6.2
 */