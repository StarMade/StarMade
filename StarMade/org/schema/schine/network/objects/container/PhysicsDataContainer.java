/*     */ package org.schema.schine.network.objects.container;
/*     */ 
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.MotionState;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import d;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class PhysicsDataContainer
/*     */ {
/*     */   private static final Transform ident;
/*     */   private CollisionShape shape;
/*     */   private CompoundShapeChild shapeChield;
/*     */   private CollisionObject object;
/*  34 */   private Vector3f inertia = new Vector3f();
/*     */   public Transform initialTransform;
/*     */   private boolean initialized;
/*  40 */   private final Transform cacheTransform = new Transform();
/*     */ 
/*  50 */   private float lastUpdatedMass = -1.0F;
/*     */ 
/*     */   public void onPhysicsAdd() {
/*  53 */     this.lastUpdatedMass = -1.0F;
/*     */   }
/*     */   public void onPhysicsRemove() {
/*  56 */     this.lastUpdatedMass = -1.0F;
/*     */   }
/*     */ 
/*     */   public void clearPhysicsInfo()
/*     */   {
/*  62 */     setObject(null);
/*  63 */     this.initialTransform = null;
/*  64 */     setShape(null);
/*  65 */     setShapeChield(null);
/*     */ 
/*  67 */     this.initialized = false;
/*     */   }
/*     */ 
/*     */   public Transform getCurrentPhysicsTransform()
/*     */   {
/*  82 */     return this.cacheTransform;
/*     */   }
/*     */   public CollisionObject getObject() {
/*  85 */     return this.object;
/*     */   }
/*     */   public CollisionShape getShape() {
/*  88 */     return this.shape;
/*     */   }
/*     */ 
/*     */   public CompoundShapeChild getShapeChild()
/*     */   {
/*  95 */     return this.shapeChield;
/*     */   }
/*     */ 
/*     */   public boolean isInitialized()
/*     */   {
/* 102 */     return this.initialized;
/*     */   }
/*     */ 
/*     */   public void setObject(CollisionObject paramCollisionObject) {
/* 106 */     this.object = paramCollisionObject;
/*     */   }
/*     */ 
/*     */   public void setShape(CollisionShape paramCollisionShape) {
/* 110 */     this.shape = paramCollisionShape;
/*     */   }
/*     */ 
/*     */   public void setShapeChield(CompoundShapeChild paramCompoundShapeChild)
/*     */   {
/* 117 */     this.shapeChield = paramCompoundShapeChild;
/*     */   }
/*     */ 
/*     */   public boolean updateMass(float paramFloat, boolean paramBoolean) {
/* 121 */     if ((this.initialized) && (getShape() != null) && (getObject() != null))
/*     */     {
/* 123 */       if ((paramFloat != this.lastUpdatedMass) || (paramBoolean)) {
/* 124 */         getShape().calculateLocalInertia(Math.max(paramFloat, 2.5F), this.inertia);
/* 125 */         ((RigidBody)getObject()).setMassProps(paramFloat, this.inertia);
/* 126 */         this.lastUpdatedMass = paramFloat;
/*     */ 
/* 129 */         return true;
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 135 */       System.err.println("[PHYSICSCONTAINER][WARNING] Could not set mass!");
/*     */     }
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */   public void updatePhysical() {
/* 141 */     updatePhysical(getObject());
/*     */   }
/*     */   public void updatePhysical(CollisionObject paramCollisionObject) {
/* 144 */     if (paramCollisionObject != null) {
/* 145 */       if ((paramCollisionObject instanceof RigidBody))
/*     */       {
/* 148 */         ((RigidBody)paramCollisionObject).getMotionState()
/* 150 */           .getWorldTransform(this.cacheTransform);
/*     */ 
/* 152 */         if ((getShapeChild() != null) && 
/* 153 */           (!getShapeChild().transform.equals(ident))) {
/* 154 */           d.a(this.cacheTransform, getShapeChild().transform);
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 160 */         paramCollisionObject.getWorldTransform(this.cacheTransform);
/*     */       }
/* 162 */       this.initialized = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateManually(Transform paramTransform) {
/* 167 */     this.cacheTransform.set(paramTransform);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  23 */     (
/*  25 */       PhysicsDataContainer.ident = new Transform())
/*  25 */       .setIdentity();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.container.PhysicsDataContainer
 * JD-Core Version:    0.6.2
 */