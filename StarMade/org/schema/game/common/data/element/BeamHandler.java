/*     */ package org.schema.game.common.data.element;
/*     */ 
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import ct;
/*     */ import dm;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import javax.vecmath.Vector3f;
/*     */ import jq;
/*     */ import lE;
/*     */ import le;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*     */ import org.schema.game.common.data.physics.PhysicsExt;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import q;
/*     */ import x;
/*     */ import xq;
/*     */ 
/*     */ public abstract class BeamHandler
/*     */ {
/*     */   private final SegmentController segmentController;
/*     */   private jq owner;
/* 113 */   private final Long2ObjectOpenHashMap beamStates = new Long2ObjectOpenHashMap();
/* 114 */   BeamHandler.BeamState sTmp = new BeamHandler.BeamState(this, new q(), new Vector3f(), new Vector3f(), null, 0.0F);
/* 115 */   Vector3f start = new Vector3f();
/* 116 */   Vector3f end = new Vector3f();
/*     */   private boolean lastActive;
/* 118 */   String[] cannotHitReason = new String[1];
/*     */ 
/* 211 */   private q posTmp = new q();
/*     */ 
/* 213 */   private final HashSet updatedSegments = new HashSet();
/*     */ 
/* 264 */   private Vector3f dirTmp = new Vector3f();
/*     */   private dm drawer;
/*     */ 
/*     */   public BeamHandler(SegmentController paramSegmentController, jq paramjq)
/*     */   {
/* 120 */     this.segmentController = paramSegmentController;
/* 121 */     this.owner = paramjq;
/*     */   }
/*     */ 
/*     */   public void clearStates() {
/* 125 */     this.beamStates.clear();
/* 126 */     if (this.drawer != null)
/* 127 */       this.drawer.a(this, false);
/*     */   }
/*     */ 
/*     */   public void addBeam(q paramq, Vector3f paramVector3f1, Vector3f paramVector3f2, lE paramlE, float paramFloat)
/*     */   {
/* 135 */     long l = ElementCollection.getIndex(paramq);
/*     */ 
/* 137 */     if (!this.beamStates.containsKey(l)) {
/* 138 */       (
/* 139 */         paramq = new BeamHandler.BeamState(this, paramq, paramVector3f1, paramVector3f2, paramlE, paramFloat)).timeRunning = 
/* 139 */         0.0F;
/* 140 */       this.beamStates.put(l, paramq);
/* 141 */       return;
/* 142 */     }(
/* 143 */       paramq = (BeamHandler.BeamState)this.beamStates.get(l)).from
/* 143 */       .set(paramVector3f1);
/* 144 */     paramq.to.set(paramVector3f2);
/* 145 */     paramq.timeRunning = 0.0F;
/* 146 */     paramq.playerState = paramlE;
/*     */   }
/*     */ 
/*     */   public int beamHit(BeamHandler.BeamState paramBeamState, le paramle)
/*     */   {
/* 155 */     assert (paramle != null);
/* 156 */     if (paramle.equals(paramBeamState.segmentHit)) {
/* 157 */       paramBeamState.hitOneSegment += paramBeamState.timeSpent;
/* 158 */       paramBeamState.timeSpent = 0.0F;
/*     */     } else {
/* 160 */       paramBeamState.segmentHit = paramle;
/* 161 */       paramBeamState.hitOneSegment = 0.0F;
/*     */     }
/* 163 */     float f = getBeamToHitInSecs(paramBeamState);
/*     */ 
/* 170 */     paramle = FastMath.a(paramBeamState.hitOneSegment / f);
/* 171 */     paramBeamState.hitOneSegment -= paramle * f;
/* 172 */     this.cannotHitReason[0] = "";
/* 173 */     return paramle;
/*     */   }
/*     */ 
/*     */   public abstract boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, q paramq);
/*     */ 
/*     */   public synchronized BeamHandler.BeamState getBeam(q paramq)
/*     */   {
/* 187 */     if ((
/* 187 */       paramq = (BeamHandler.BeamState)getBeamStates().get(ElementCollection.getIndex(paramq))) != null)
/*     */     {
/* 188 */       return paramq;
/*     */     }
/* 190 */     throw new BeamHandler.ElementNotFoundException(this);
/*     */   }
/*     */ 
/*     */   public Long2ObjectOpenHashMap getBeamStates()
/*     */   {
/* 197 */     return this.beamStates;
/*     */   }
/*     */ 
/*     */   public abstract float getBeamTimeoutInSecs();
/*     */ 
/*     */   public abstract float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState);
/*     */ 
/*     */   public SegmentController getSegmentController()
/*     */   {
/* 206 */     return this.segmentController;
/*     */   }
/*     */   public boolean isAnyBeamActiveActive() {
/* 209 */     return !this.beamStates.isEmpty();
/*     */   }
/*     */ 
/*     */   public abstract void onBeamHit(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, xq paramxq, Collection paramCollection);
/*     */ 
/*     */   public void update(xq paramxq)
/*     */   {
/* 216 */     if (getBeamStates().isEmpty()) {
/* 217 */       return;
/*     */     }
/*     */ 
/* 222 */     System.currentTimeMillis();
/* 223 */     this.updatedSegments.clear();
/* 224 */     ObjectIterator localObjectIterator = getBeamStates().values().iterator();
/* 225 */     float f = paramxq.a();
/* 226 */     while (localObjectIterator.hasNext())
/*     */     {
/* 228 */       if ((
/* 228 */         localObject = (BeamHandler.BeamState)localObjectIterator.next()).timeRunning > 
/* 228 */         getBeamTimeoutInSecs()) {
/* 229 */         localObjectIterator.remove();
/*     */       }
/*     */       else {
/* 232 */         this.posTmp.b(((BeamHandler.BeamState)localObject).elementPos.a - 8, ((BeamHandler.BeamState)localObject).elementPos.b - 8, ((BeamHandler.BeamState)localObject).elementPos.c - 8);
/*     */ 
/* 235 */         getSegmentController().getAbsoluteElementWorldPosition(this.posTmp, ((BeamHandler.BeamState)localObject).from);
/* 236 */         this.start.set(((BeamHandler.BeamState)localObject).from);
/* 237 */         this.end.set(((BeamHandler.BeamState)localObject).to);
/* 238 */         updateBeam(this.start, this.end, (BeamHandler.BeamState)localObject, paramxq, this.updatedSegments);
/*     */       }
/* 240 */       localObject.timeRunning += f;
/*     */     }
/*     */ 
/* 245 */     for (Object localObject = this.updatedSegments.iterator(); ((Iterator)localObject).hasNext(); ) {
/* 246 */       if ((
/* 246 */         paramxq = (Segment)((Iterator)localObject).next())
/* 246 */         .a() != null) {
/* 247 */         paramxq.a().restructBB(true);
/*     */       }
/*     */     }
/* 250 */     System.currentTimeMillis();
/*     */ 
/* 253 */     if (this.lastActive != isAnyBeamActiveActive())
/*     */     {
/* 255 */       if (this.drawer != null)
/*     */       {
/* 257 */         this.drawer.a(this, isAnyBeamActiveActive());
/*     */       }
/*     */     }
/*     */ 
/* 261 */     this.lastActive = isAnyBeamActiveActive();
/*     */   }
/*     */ 
/*     */   public void updateBeam(Vector3f paramVector3f1, Vector3f paramVector3f2, BeamHandler.BeamState paramBeamState, xq paramxq, Collection paramCollection)
/*     */   {
/* 267 */     Object localObject = getSegmentController();
/*     */ 
/* 269 */     paramBeamState.timeSpent += paramxq.a();
/* 270 */     if (!paramBeamState.checkNecessary(paramxq, paramBeamState)) {
/* 271 */       if (paramBeamState.hitPoint != null) {
/* 272 */         paramBeamState.hitPoint.sub(paramVector3f1);
/* 273 */         this.dirTmp.set(paramVector3f2);
/* 274 */         this.dirTmp.sub(paramVector3f1);
/* 275 */         this.dirTmp.normalize();
/* 276 */         this.dirTmp.scale(paramBeamState.hitPoint.length());
/* 277 */         this.dirTmp.add(paramVector3f1);
/* 278 */         paramBeamState.hitPoint.set(this.dirTmp);
/*     */       }
/* 280 */       return;
/*     */     }
/*     */ 
/* 288 */     if (((
/* 288 */       localObject = ((SegmentController)localObject).getPhysics().testRayCollisionPoint(paramVector3f1, paramVector3f2, false, (SegmentController)localObject, null, false, null, true))
/* 288 */       .hasHit()) && (((CollisionWorld.ClosestRayResultCallback)localObject).collisionObject != null))
/*     */     {
/* 290 */       paramBeamState.hitPoint = ((CollisionWorld.ClosestRayResultCallback)localObject).hitPointWorld;
/*     */ 
/* 297 */       if (((localObject instanceof CubeRayCastResult)) && (((CubeRayCastResult)localObject).getSegment() != null))
/*     */       {
/* 299 */         SegmentController localSegmentController = (
/* 299 */           localObject = (CubeRayCastResult)localObject)
/* 299 */           .getSegment().a();
/* 300 */         paramBeamState.cachedLastSegment = ((CubeRayCastResult)localObject).getSegment();
/* 301 */         this.cannotHitReason[0] = "";
/* 302 */         le localle = new le(((CubeRayCastResult)localObject).getSegment(), ((CubeRayCastResult)localObject).cubePos);
/* 303 */         if (canhit(localSegmentController, this.cannotHitReason, localle.a(new q()))) {
/* 304 */           onBeamHit(paramBeamState, this.owner, paramBeamState.elementPos, ((CubeRayCastResult)localObject).getSegment(), paramVector3f1, paramVector3f2, (CubeRayCastResult)localObject, paramxq, paramCollection);
/* 305 */           return;
/*     */         }
/* 307 */         if (!localSegmentController.isOnServer())
/*     */         {
/* 309 */           if (((ct)getSegmentController().getState()).a() == getSegmentController()) {
/* 310 */             ((x)localSegmentController.getState().getController()).d(localSegmentController.toNiceString() + "\ncannot be hit by this beam.\n" + this.cannotHitReason[0]);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 318 */       paramBeamState.hitPoint = null;
/* 319 */       paramBeamState.cachedLastSegment = null;
/*     */     }
/* 321 */     paramBeamState.segmentHit = null;
/* 322 */     paramBeamState.hitOneSegment = 0.0F;
/*     */   }
/*     */ 
/*     */   public void setDrawer(dm paramdm) {
/* 326 */     this.drawer = paramdm;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.BeamHandler
 * JD-Core Version:    0.6.2
 */