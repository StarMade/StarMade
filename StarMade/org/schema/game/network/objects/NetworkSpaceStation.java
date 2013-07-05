/*     */ package org.schema.game.network.objects;
/*     */ 
/*     */ import ka;
/*     */ import kr;
/*     */ import ld;
/*     */ import ml;
/*     */ import org.schema.game.common.controller.elements.DistributionInterface;
/*     */ import org.schema.game.common.controller.elements.shield.NTShieldManagerInterface;
/*     */ import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*     */ import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteString;
/*     */ import org.schema.schine.network.objects.remote.RemoteStringArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector4i;
/*     */ 
/*     */ public class NetworkSpaceStation extends NetworkSegmentController
/*     */   implements kr, ml, DistributionInterface, NTShieldManagerInterface, NetworkDoorInterface, NetworkLiftInterface
/*     */ {
/*  24 */   private RemoteIntArrayBuffer shieldUpdate = new RemoteIntArrayBuffer(4, this);
/*     */ 
/*  26 */   public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
/*  27 */   public RemoteBuffer liftActivate = new RemoteBuffer(RemoteVector4i.class, this);
/*     */   public RemoteInventoryBuffer inventoryBuffer;
/*  30 */   public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(6, this);
/*     */ 
/*  32 */   public RemoteIntArrayBuffer distributionModification = new RemoteIntArrayBuffer(9, this);
/*  33 */   public RemoteBuffer doorActivate = new RemoteBuffer(RemoteVector4i.class, this);
/*  34 */   public RemoteString debugState = new RemoteString("", this);
/*  35 */   public RemoteArrayBuffer aiSettingsModification = new RemoteArrayBuffer(2, RemoteStringArray.class, this);
/*  36 */   public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
/*     */ 
/*     */   public RemoteIntArrayBuffer getInventoryActivateBuffer() {
/*  39 */     return this.inventoryActivateBuffer;
/*     */   }
/*     */ 
/*     */   public NetworkSpaceStation(StateInterface paramStateInterface, ka paramka) {
/*  43 */     super(paramStateInterface, paramka);
/*  44 */     this.inventoryBuffer = new RemoteInventoryBuffer(((ld)paramka).a(), this);
/*     */   }
/*     */ 
/*     */   public RemoteArrayBuffer getAiSettingsModification()
/*     */   {
/*  52 */     return this.aiSettingsModification;
/*     */   }
/*     */ 
/*     */   public RemoteString getDebugState()
/*     */   {
/*  59 */     return this.debugState;
/*     */   }
/*     */ 
/*     */   public RemoteBuffer getDoorActivate()
/*     */   {
/*  68 */     return this.doorActivate;
/*     */   }
/*     */ 
/*     */   public RemoteInventoryBuffer getInventoriesChangeBuffer()
/*     */   {
/*  76 */     return this.inventoryBuffer;
/*     */   }
/*     */ 
/*     */   public RemoteIntArrayBuffer getInventoryUpdateBuffer()
/*     */   {
/*  85 */     return this.inventoryUpdateBuffer;
/*     */   }
/*     */ 
/*     */   public RemoteBuffer getLiftActivate()
/*     */   {
/*  93 */     return this.liftActivate;
/*     */   }
/*     */ 
/*     */   public RemoteIntArrayBuffer getShieldUpdate()
/*     */   {
/* 101 */     return this.shieldUpdate;
/*     */   }
/*     */ 
/*     */   public RemoteIntArrayBuffer getDistributionModification()
/*     */   {
/* 106 */     return this.distributionModification;
/*     */   }
/*     */ 
/*     */   public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
/*     */   {
/* 111 */     return this.inventoryMultModBuffer;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkSpaceStation
 * JD-Core Version:    0.6.2
 */