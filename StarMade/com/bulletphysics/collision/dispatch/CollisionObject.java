/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class CollisionObject
/*     */ {
/*     */   public static final int ACTIVE_TAG = 1;
/*     */   public static final int ISLAND_SLEEPING = 2;
/*     */   public static final int WANTS_DEACTIVATION = 3;
/*     */   public static final int DISABLE_DEACTIVATION = 4;
/*     */   public static final int DISABLE_SIMULATION = 5;
/*  48 */   protected Transform worldTransform = new Transform();
/*     */ 
/*  52 */   protected final Transform interpolationWorldTransform = new Transform();
/*     */ 
/*  55 */   protected final Vector3f interpolationLinearVelocity = new Vector3f();
/*  56 */   protected final Vector3f interpolationAngularVelocity = new Vector3f();
/*     */   protected BroadphaseProxy broadphaseHandle;
/*     */   protected CollisionShape collisionShape;
/*     */   protected CollisionShape rootCollisionShape;
/*     */   protected int collisionFlags;
/*     */   protected int islandTag1;
/*     */   protected int companionId;
/*     */   protected int activationState1;
/*     */   protected float deactivationTime;
/*     */   protected float friction;
/*     */   protected float restitution;
/*     */   protected Object userObjectPointer;
/*  78 */   protected CollisionObjectType internalType = CollisionObjectType.COLLISION_OBJECT;
/*     */   protected float hitFraction;
/*     */   protected float ccdSweptSphereRadius;
/*  86 */   protected float ccdMotionThreshold = 0.0F;
/*     */   protected boolean checkCollideWith;
/*     */ 
/*     */   public CollisionObject()
/*     */   {
/*  91 */     this.collisionFlags = 1;
/*  92 */     this.islandTag1 = -1;
/*  93 */     this.companionId = -1;
/*  94 */     this.activationState1 = 1;
/*  95 */     this.friction = 0.5F;
/*  96 */     this.hitFraction = 1.0F;
/*     */   }
/*     */ 
/*     */   public boolean checkCollideWithOverride(CollisionObject co) {
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean mergesSimulationIslands()
/*     */   {
/* 105 */     return (this.collisionFlags & 0x7) == 0;
/*     */   }
/*     */ 
/*     */   public boolean isStaticObject() {
/* 109 */     return (this.collisionFlags & 0x1) != 0;
/*     */   }
/*     */ 
/*     */   public boolean isKinematicObject() {
/* 113 */     return (this.collisionFlags & 0x2) != 0;
/*     */   }
/*     */ 
/*     */   public boolean isStaticOrKinematicObject() {
/* 117 */     return (this.collisionFlags & 0x3) != 0;
/*     */   }
/*     */ 
/*     */   public boolean hasContactResponse() {
/* 121 */     return (this.collisionFlags & 0x4) == 0;
/*     */   }
/*     */ 
/*     */   public CollisionShape getCollisionShape() {
/* 125 */     return this.collisionShape;
/*     */   }
/*     */ 
/*     */   public void setCollisionShape(CollisionShape collisionShape) {
/* 129 */     this.collisionShape = collisionShape;
/* 130 */     this.rootCollisionShape = collisionShape;
/*     */   }
/*     */ 
/*     */   public CollisionShape getRootCollisionShape() {
/* 134 */     return this.rootCollisionShape;
/*     */   }
/*     */ 
/*     */   public void internalSetTemporaryCollisionShape(CollisionShape collisionShape)
/*     */   {
/* 142 */     this.collisionShape = collisionShape;
/*     */   }
/*     */ 
/*     */   public int getActivationState() {
/* 146 */     return this.activationState1;
/*     */   }
/*     */ 
/*     */   public void setActivationState(int newState) {
/* 150 */     if ((this.activationState1 != 4) && (this.activationState1 != 5))
/* 151 */       this.activationState1 = newState;
/*     */   }
/*     */ 
/*     */   public float getDeactivationTime()
/*     */   {
/* 156 */     return this.deactivationTime;
/*     */   }
/*     */ 
/*     */   public void setDeactivationTime(float deactivationTime) {
/* 160 */     this.deactivationTime = deactivationTime;
/*     */   }
/*     */ 
/*     */   public void forceActivationState(int newState) {
/* 164 */     this.activationState1 = newState;
/*     */   }
/*     */ 
/*     */   public void activate() {
/* 168 */     activate(false);
/*     */   }
/*     */ 
/*     */   public void activate(boolean forceActivation) {
/* 172 */     if ((forceActivation) || ((this.collisionFlags & 0x3) == 0)) {
/* 173 */       setActivationState(1);
/* 174 */       this.deactivationTime = 0.0F;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isActive() {
/* 179 */     return (getActivationState() != 2) && (getActivationState() != 5);
/*     */   }
/*     */ 
/*     */   public float getRestitution() {
/* 183 */     return this.restitution;
/*     */   }
/*     */ 
/*     */   public void setRestitution(float restitution) {
/* 187 */     this.restitution = restitution;
/*     */   }
/*     */ 
/*     */   public float getFriction() {
/* 191 */     return this.friction;
/*     */   }
/*     */ 
/*     */   public void setFriction(float friction) {
/* 195 */     this.friction = friction;
/*     */   }
/*     */ 
/*     */   public CollisionObjectType getInternalType()
/*     */   {
/* 200 */     return this.internalType;
/*     */   }
/*     */ 
/*     */   public Transform getWorldTransform(Transform out) {
/* 204 */     out.set(this.worldTransform);
/* 205 */     return out;
/*     */   }
/*     */ 
/*     */   public void setWorldTransform(Transform worldTransform) {
/* 209 */     this.worldTransform.set(worldTransform);
/*     */   }
/*     */ 
/*     */   public BroadphaseProxy getBroadphaseHandle() {
/* 213 */     return this.broadphaseHandle;
/*     */   }
/*     */ 
/*     */   public void setBroadphaseHandle(BroadphaseProxy broadphaseHandle) {
/* 217 */     this.broadphaseHandle = broadphaseHandle;
/*     */   }
/*     */ 
/*     */   public Transform getInterpolationWorldTransform(Transform out) {
/* 221 */     out.set(this.interpolationWorldTransform);
/* 222 */     return out;
/*     */   }
/*     */ 
/*     */   public void setInterpolationWorldTransform(Transform interpolationWorldTransform) {
/* 226 */     this.interpolationWorldTransform.set(interpolationWorldTransform);
/*     */   }
/*     */ 
/*     */   public void setInterpolationLinearVelocity(Vector3f linvel) {
/* 230 */     this.interpolationLinearVelocity.set(linvel);
/*     */   }
/*     */ 
/*     */   public void setInterpolationAngularVelocity(Vector3f angvel) {
/* 234 */     this.interpolationAngularVelocity.set(angvel);
/*     */   }
/*     */ 
/*     */   public Vector3f getInterpolationLinearVelocity(Vector3f out) {
/* 238 */     out.set(this.interpolationLinearVelocity);
/* 239 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f getInterpolationAngularVelocity(Vector3f out) {
/* 243 */     out.set(this.interpolationAngularVelocity);
/* 244 */     return out;
/*     */   }
/*     */ 
/*     */   public int getIslandTag() {
/* 248 */     return this.islandTag1;
/*     */   }
/*     */ 
/*     */   public void setIslandTag(int islandTag) {
/* 252 */     this.islandTag1 = islandTag;
/*     */   }
/*     */ 
/*     */   public int getCompanionId() {
/* 256 */     return this.companionId;
/*     */   }
/*     */ 
/*     */   public void setCompanionId(int companionId) {
/* 260 */     this.companionId = companionId;
/*     */   }
/*     */ 
/*     */   public float getHitFraction() {
/* 264 */     return this.hitFraction;
/*     */   }
/*     */ 
/*     */   public void setHitFraction(float hitFraction) {
/* 268 */     this.hitFraction = hitFraction;
/*     */   }
/*     */ 
/*     */   public int getCollisionFlags() {
/* 272 */     return this.collisionFlags;
/*     */   }
/*     */ 
/*     */   public void setCollisionFlags(int collisionFlags) {
/* 276 */     this.collisionFlags = collisionFlags;
/*     */   }
/*     */ 
/*     */   public float getCcdSweptSphereRadius()
/*     */   {
/* 281 */     return this.ccdSweptSphereRadius;
/*     */   }
/*     */ 
/*     */   public void setCcdSweptSphereRadius(float ccdSweptSphereRadius)
/*     */   {
/* 286 */     this.ccdSweptSphereRadius = ccdSweptSphereRadius;
/*     */   }
/*     */ 
/*     */   public float getCcdMotionThreshold() {
/* 290 */     return this.ccdMotionThreshold;
/*     */   }
/*     */ 
/*     */   public float getCcdSquareMotionThreshold() {
/* 294 */     return this.ccdMotionThreshold * this.ccdMotionThreshold;
/*     */   }
/*     */ 
/*     */   public void setCcdMotionThreshold(float ccdMotionThreshold)
/*     */   {
/* 300 */     this.ccdMotionThreshold = ccdMotionThreshold;
/*     */   }
/*     */ 
/*     */   public Object getUserPointer() {
/* 304 */     return this.userObjectPointer;
/*     */   }
/*     */ 
/*     */   public void setUserPointer(Object userObjectPointer) {
/* 308 */     this.userObjectPointer = userObjectPointer;
/*     */   }
/*     */ 
/*     */   public boolean checkCollideWith(CollisionObject co) {
/* 312 */     if (this.checkCollideWith) {
/* 313 */       return checkCollideWithOverride(co);
/*     */     }
/*     */ 
/* 316 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.CollisionObject
 * JD-Core Version:    0.6.2
 */