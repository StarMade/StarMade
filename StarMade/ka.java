/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ManagerContainer;
/*     */ import org.schema.game.common.controller.elements.PowerAddOn;
/*     */ import org.schema.game.common.controller.elements.PowerManagerInterface;
/*     */ import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*     */ import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import org.schema.game.network.objects.NetworkSegmentController;
/*     */ import org.schema.game.network.objects.NetworkSegmentProvider;
/*     */ import org.schema.game.network.objects.remote.RemoteSegmentPiece;
/*     */ import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
/*     */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.SynchronizationContainerController;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/*     */ import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
/*     */ import org.schema.schine.network.objects.remote.RemoteString;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3f;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3i;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector4i;
/*     */ 
/*     */ public abstract class ka extends SegmentController
/*     */   implements Sendable
/*     */ {
/*     */   private NetworkSegmentController networkEntity;
/*  45 */   private boolean first = true;
/*  46 */   private final ObjectArrayFIFOQueue blockActivationBuffer = new ObjectArrayFIFOQueue();
/*     */   private int lastModifierId;
/*     */   private boolean lastModifierChanged;
/*     */   private kc serverSendableSegmentProvider;
/*     */ 
/*     */   public ka(StateInterface paramStateInterface)
/*     */   {
/*  57 */     super(paramStateInterface);
/*  58 */     getControlElementMap().setSendableSegmentController(this);
/*     */   }
/*     */ 
/*     */   public ObjectArrayFIFOQueue getBlockActivationBuffer()
/*     */   {
/*  65 */     return this.blockActivationBuffer;
/*     */   }
/*     */ 
/*     */   public NetworkSegmentController getNetworkObject()
/*     */   {
/*  74 */     return this.networkEntity;
/*     */   }
/*     */ 
/*     */   public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void handleHitMissile(ln paramln, Transform paramTransform)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void handleNTDockChanged()
/*     */   {
/*  99 */     getDockingController().a(getNetworkObject());
/*     */   }
/*     */ 
/*     */   private void handleReceivedBlockActivations(NetworkSegmentController paramNetworkSegmentController)
/*     */   {
/* 105 */     for (int i = 0; i < paramNetworkSegmentController.blockActivationBuffer.getReceiveBuffer().size(); i++) {
/* 106 */       s locals = ((RemoteVector4i)paramNetworkSegmentController.blockActivationBuffer.getReceiveBuffer().get(i)).getVector(new s());
/* 107 */       synchronized (getBlockActivationBuffer()) {
/* 108 */         System.err.println("[SERVER] RECEIVED BLOCK ACTIVATION");
/* 109 */         getBlockActivationBuffer().enqueue(locals);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void handleReceivedControllers(NetworkSegmentController paramNetworkSegmentController)
/*     */   {
/* 116 */     getControlElementMap().handleReceived();
/*     */   }
/*     */   private void handleReceivedDirty(NetworkSegmentController paramNetworkSegmentController) {
/* 119 */     if (!isOnServer())
/* 120 */       synchronized (paramNetworkSegmentController.dirtySegmentBuffer.getReceiveBuffer()) {
/* 121 */         for (int i = 0; i < paramNetworkSegmentController.dirtySegmentBuffer.getReceiveBuffer().size(); i++) {
/* 122 */           q localq = ((RemoteVector3i)paramNetworkSegmentController.dirtySegmentBuffer.getReceiveBuffer().get(i)).getVector(new q());
/*     */           Segment localSegment;
/* 124 */           if ((
/* 124 */             localSegment = getSegmentBuffer().a(localq)) != null)
/*     */           {
/* 125 */             assert (localq.equals(localSegment.a));
/*     */ 
/* 131 */             ((mw)localSegment).a(-1L);
/* 132 */             ((Q)getSegmentProvider()).a(localq);
/*     */           } else {
/* 134 */             System.err.println("[CLIENT] WARNING: received dirty null segment " + localq + " on " + this);
/*     */           }
/*     */         }
/* 137 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   private void handleReceivedExplosions(NetworkSegmentController paramNetworkSegmentController) {
/* 142 */     if (((ct)getState()).a() == getSectorId())
/* 143 */       for (int i = 0; i < paramNetworkSegmentController.explosions.getReceiveBuffer().size(); i++) {
/* 144 */         Vector3f localVector3f = ((RemoteVector3f)paramNetworkSegmentController.explosions.getReceiveBuffer().get(i)).getVector();
/* 145 */         ((ct)getState()).a().a().a(localVector3f, 15.0F);
/*     */         Transform localTransform;
/* 146 */         (
/* 147 */           localTransform = new Transform())
/* 147 */           .setIdentity();
/* 148 */         localTransform.origin.set(localVector3f);
/* 149 */         xe.a("0022_explosion", localTransform, 10.0F);
/*     */       }
/*     */   }
/*     */ 
/*     */   private void handleReceivedHarvestConnections(NetworkSegmentController paramNetworkSegmentController)
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void handleReceivedModifications(NetworkSegmentController paramNetworkSegmentController)
/*     */   {
/* 165 */     if (paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().size() > 32) {
/* 166 */       System.err.println(getState() + "; " + this + " [WARNING] !!!!! BIG MODIFICATION RECEIVED: " + paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().size());
/*     */     }
/* 168 */     synchronized (paramNetworkSegmentController.modificationBuffer.getReceiveBuffer()) {
/* 169 */       for (int i = 0; i < paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().size(); i++)
/*     */       {
/* 171 */         le localle = (le)((RemoteSegmentPiece)paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().get(i)).get();
/* 172 */         assert ((!(getState() instanceof vg)) || (localle != null)) : "Implication: [serverState -> segmentPiece not null] failed. server pieces must not be null";
/* 173 */         if (localle != null) {
/* 174 */           if ((this instanceof km)) {
/* 175 */             ((km)this).a(true);
/*     */           }
/* 177 */           localle.a().a(localle);
/*     */         }
/*     */ 
/* 181 */         if (isOnServer())
/*     */         {
/* 192 */           paramNetworkSegmentController.modificationBuffer.add(new RemoteSegmentPiece(localle, this, getNetworkObject()));
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void initFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 203 */     super.initFromNetworkObject(paramNetworkObject);
/* 204 */     paramNetworkObject = (NetworkSegmentController)paramNetworkObject;
/* 205 */     if (!isOnServer()) {
/* 206 */       getMinPos().b(paramNetworkObject.minSize.getVector());
/* 207 */       getMaxPos().b(paramNetworkObject.maxSize.getVector());
/*     */ 
/* 209 */       if (((this instanceof ld)) && ((((ld)this).a() instanceof PowerManagerInterface))) {
/* 210 */         ((PowerManagerInterface)((ld)this).a()).getPowerAddOn()
/* 211 */           .setInitialPower(getNetworkObject().initialPower.getLong());
/*     */       }
/* 213 */       if (((this instanceof ld)) && ((((ld)this).a() instanceof ShieldContainerInterface))) {
/* 214 */         ((ShieldContainerInterface)((ld)this).a()).getShieldManager()
/* 216 */           .setInitialShields(getNetworkObject().initialShields.getLong());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 222 */     setRealName((String)paramNetworkObject.realName.get());
/* 223 */     setUniqueIdentifier((String)getNetworkObject().uniqueIdentifier.get());
/*     */   }
/*     */ 
/*     */   public boolean isVolatile()
/*     */   {
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */   public void newNetworkObject()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onCollision(ManifoldPoint paramManifoldPoint, Sendable paramSendable)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void cleanUpOnEntityDelete()
/*     */   {
/* 258 */     if (!isOnServer())
/*     */     {
/*     */       kc localkc;
/* 260 */       if (((
/* 260 */         localkc = ((Q)getSegmentProvider()).a()) != null) && 
/* 260 */         (localkc.a() != null)) {
/* 261 */         localkc.a().signalDelete.set(Boolean.valueOf(true), true);
/*     */       }
/*     */     }
/* 264 */     else if (this.serverSendableSegmentProvider != null) {
/* 265 */       this.serverSendableSegmentProvider.markedForPermanentDelete(true);
/* 266 */       this.serverSendableSegmentProvider = null;
/*     */     }
/*     */ 
/* 270 */     super.cleanUpOnEntityDelete();
/*     */   }
/*     */ 
/*     */   public void handleMovingInput(xq paramxq, lA paramlA)
/*     */   {
/*     */   }
/*     */ 
/*     */   public String toNiceString()
/*     */   {
/* 289 */     return null;
/*     */   }
/*     */ 
/*     */   public void setNetworkObject(NetworkSegmentController paramNetworkSegmentController) {
/* 293 */     this.networkEntity = paramNetworkSegmentController;
/*     */   }
/*     */ 
/*     */   public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 298 */     super.updateFromNetworkObject(paramNetworkObject);
/* 299 */     paramNetworkObject = (NetworkSegmentController)paramNetworkObject;
/*     */ 
/* 301 */     handleReceivedModifications(paramNetworkObject);
/* 302 */     handleReceivedDirty(paramNetworkObject);
/* 303 */     handleReceivedBlockActivations(paramNetworkObject);
/*     */ 
/* 306 */     getControlElementMap().handleReceived();
/* 307 */     if (!isOnServer())
/*     */     {
/* 310 */       if (getSectorId() == ((ct)getState()).a()) {
/* 311 */         handleReceivedExplosions(paramNetworkObject);
/*     */       }
/* 313 */       paramNetworkObject.minSize.getVector(getMinPos());
/* 314 */       paramNetworkObject.maxSize.getVector(getMaxPos());
/*     */     }
/* 317 */     else if (this.lastModifierId != paramNetworkObject.lastModifiedPlayerId.getInt()) {
/* 318 */       int i = this.lastModifierId;
/* 319 */       this.lastModifierId = paramNetworkObject.lastModifiedPlayerId.getInt();
/* 320 */       System.err.println("[SERVER][SEGMENTCONTROLLER] LAST MODIFIER CHANGED TO PID: " + this.lastModifierId + " (from " + i + ")");
/* 321 */       this.lastModifierChanged = true;
/*     */     }
/*     */ 
/* 324 */     setRealName((String)paramNetworkObject.realName.get());
/*     */ 
/* 326 */     getDockingController().b(paramNetworkObject);
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq paramxq)
/*     */   {
/*     */     Object localObject2;
/* 333 */     if (this.first) {
/* 334 */       if (!isOnServer()) {
/* 335 */         long l1 = System.currentTimeMillis();
/* 336 */         (
/* 337 */           localObject2 = new kc(getState()))
/* 337 */           .initialize();
/* 338 */         ((kc)localObject2).a(this);
/* 339 */         ((kc)localObject2).setId(getId());
/* 340 */         ((Q)getSegmentProvider()).a((kc)localObject2);
/* 341 */         ((x)getState().getController()).a().addNewSynchronizedObjectQueued((Sendable)localObject2);
/*     */         long l4;
/* 344 */         if ((
/* 344 */           l4 = System.currentTimeMillis() - l1) > 
/* 344 */           5L) {
/* 345 */           System.err.println("[SENSEGMENTCONTROLLER][CLIENT] WARNING: initializing segmentprovider of " + this + " took " + l4 + " ms");
/*     */         }
/*     */       }
/* 348 */       this.first = false;
/*     */     }
/* 350 */     if ((isOnServer()) && (this.lastModifierChanged) && (this.lastModifierId != 0))
/*     */     {
/*     */       try {
/* 353 */         locallE = (lE)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(this.lastModifierId);
/* 354 */         setLastModifier(locallE.getUniqueIdentifier());
/* 355 */         System.err.println("[SERVER][SENSEGMENTCONTROLLER] LAST MODIFIER CHANGED TO " + locallE); } catch (Exception localException2) {
/* 356 */         lE locallE = null;
/*     */ 
/* 358 */         localException2.printStackTrace();
/*     */       }
/*     */ 
/* 360 */       this.lastModifierChanged = false;
/*     */     }
/*     */     try
/*     */     {
/* 364 */       if (!getBlockActivationBuffer().isEmpty()) {
/* 365 */         assert (isOnServer());
/* 366 */         synchronized (getBlockActivationBuffer()) {
/* 367 */           while (!getBlockActivationBuffer().isEmpty()) {
/* 368 */             s locals = (s)getBlockActivationBuffer().dequeue();
/* 369 */             localObject2 = new q(locals.a, locals.b, locals.c);
/* 370 */             le localle = getSegmentBuffer().a((q)localObject2, true);
/*     */             int i;
/*     */             boolean bool;
/* 380 */             if ((
/* 380 */               i = Math.abs(locals.d) < 2 ? 1 : 0) == 0)
/*     */             {
/* 382 */               System.err.println("[SERVER] NOT DELEGATING REQUEST " + localObject2 + " act(" + locals + ")");
/* 383 */               bool = locals.d != -2;
/*     */             } else {
/* 385 */               System.err.println("[SERVER] DELEGATING REQUEST " + localObject2 + " act(" + bool + ")");
/*     */ 
/* 387 */               bool = bool.d != 0;
/*     */             }
/*     */ 
/* 390 */             if ((localle.a() == 56) && ((localle.a().a() instanceof jA))) {
/* 391 */               System.err.println("[SERVER] NOT ACTIVATING GRAVITY BLOCK ON PLANET" + bool);
/*     */             }
/*     */             else
/*     */             {
/* 395 */               localle.a(bool);
/* 396 */               System.err.println("[SERVER] ACTIVATING BLOCK " + bool);
/* 397 */               localle.a().a().applySegmentData(localle.a(new o()), localle.a());
/*     */ 
/* 399 */               ((mw)localle.a()).a(System.currentTimeMillis());
/*     */ 
/* 401 */               getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(localle, this, getNetworkObject()));
/*     */ 
/* 403 */               if ((i != 0) && ((this instanceof ld)))
/*     */               {
/* 405 */                 ((ld)this).a().handleBlockActivate(localle, bool);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 410 */       long l2 = System.currentTimeMillis();
/* 411 */       getControlElementMap().updateLocal(paramxq);
/*     */       long l3;
/* 413 */       if ((
/* 413 */         l3 = System.currentTimeMillis() - l2) > 
/* 413 */         20L) {
/* 414 */         System.err.println("[SENSEGMENTCONTROLLER][" + getState() + "] WARNING: getControlElementMap().updateLocal(timer) of " + this + " took " + l3 + " ms");
/*     */       }
/*     */     }
/*     */     catch (Exception localException1)
/*     */     {
/* 419 */       if (!isOnServer()) {
/* 420 */         localException1.printStackTrace();
/* 421 */         throw new ErrorDialogException("CLIENT EXCEPTION: " + localException1.getClass().toString() + ": " + localException1.getMessage());
/*     */       }
/* 423 */       System.err.println("SERVER EXCEPTION IN SENDABLESEGMENT CONTROLLER");
/* 424 */       localException1.printStackTrace();
/*     */     }
/*     */ 
/* 429 */     super.updateLocal(paramxq);
/*     */   }
/*     */ 
/*     */   public void updateToFullNetworkObject()
/*     */   {
/* 440 */     super.updateToFullNetworkObject();
/*     */ 
/* 443 */     assert (getUniqueIdentifier() != null);
/* 444 */     getNetworkObject().uniqueIdentifier.set(getUniqueIdentifier());
/*     */     Object localObject;
/* 445 */     if (((this instanceof ld)) && ((((ld)this).a() instanceof PowerManagerInterface))) {
/* 446 */       localObject = ((PowerManagerInterface)((ld)this).a()).getPowerAddOn();
/* 447 */       getNetworkObject().initialPower.set(()((PowerAddOn)localObject).getInitialPower());
/*     */     }
/* 449 */     if (((this instanceof ld)) && ((((ld)this).a() instanceof ShieldContainerInterface))) {
/* 450 */       localObject = ((ShieldContainerInterface)((ld)this).a()).getShieldManager();
/* 451 */       getNetworkObject().initialShields.set(()((ShieldCollectionManager)localObject).getInitialShields());
/*     */     }
/*     */ 
/* 456 */     updateToNetworkObject();
/*     */   }
/*     */ 
/*     */   public void updateToNetworkObject()
/*     */   {
/* 474 */     super.updateToNetworkObject();
/*     */ 
/* 476 */     assert (getMinPos() != null);
/* 477 */     if (isOnServer()) {
/* 478 */       getNetworkObject().minSize.set(getMinPos());
/* 479 */       getNetworkObject().maxSize.set(getMaxPos());
/*     */     }
/* 481 */     getNetworkObject().realName.set(getRealName());
/*     */   }
/*     */ 
/*     */   public void writeAllBufferedSegmentsToDatabase()
/*     */   {
/* 489 */     long l1 = System.currentTimeMillis();
/* 490 */     synchronized (getSegmentProvider().a)
/*     */     {
/* 492 */       getSegmentBuffer().a(new kb(this), true);
/*     */     }
/*     */     long l2;
/* 510 */     if ((
/* 510 */       l2 = System.currentTimeMillis() - l1) > 
/* 510 */       10L)
/* 511 */       System.err.println("[SENDABLESEGMENTVONTROLLER][WRITE] WARNING: segment writing of " + this + " on " + getState() + " took: " + l2 + " ms");
/*     */   }
/*     */ 
/*     */   public void setServerSendableSegmentController(kc paramkc)
/*     */   {
/* 517 */     this.serverSendableSegmentProvider = paramkc;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ka
 * JD-Core Version:    0.6.2
 */