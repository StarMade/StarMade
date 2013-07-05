/*     */ package org.schema.game.common.controller.elements.thrust;
/*     */ 
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import ct;
/*     */ import cv;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ import jv;
/*     */ import kX;
/*     */ import kd;
/*     */ import lA;
/*     */ import lE;
/*     */ import le;
/*     */ import lg;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.PowerAddOn;
/*     */ import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
/*     */ import org.schema.game.common.data.physics.PhysicsExt;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*     */ import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import q;
/*     */ import vg;
/*     */ import x;
/*     */ 
/*     */ public class ThrusterElementManager extends UsableControllableSingleElementManager
/*     */ {
/*     */   public static final float MAX_VELOCITY_UNIT = 2.0F;
/*  25 */   private Vector3f velocity = new Vector3f();
/*     */   private long lastMoveUpdate;
/*  29 */   private Vector3f linearVelocityTmp = new Vector3f();
/*     */   private ThrusterCollectionManager thrustManager;
/*  85 */   private final Vector3f up = new Vector3f();
/*  86 */   private final Vector3f down = new Vector3f();
/*  87 */   private final Vector3f left = new Vector3f();
/*  88 */   private final Vector3f right = new Vector3f();
/*  89 */   private final Vector3f forward = new Vector3f();
/*  90 */   private final Vector3f backward = new Vector3f();
/*  91 */   private final Vector3f dir = new Vector3f();
/*     */ 
/*     */   public ThrusterElementManager(SegmentController paramSegmentController)
/*     */   {
/*  35 */     super(new ThrusterCollectionManager(paramSegmentController), paramSegmentController);
/*  36 */     this.thrustManager = ((ThrusterCollectionManager)getCollection());
/*     */ 
/*  38 */     if (!paramSegmentController.isOnServer())
/*  39 */       addObserver((kX)paramSegmentController.getState());
/*     */   }
/*     */ 
/*     */   public boolean canHandle(lA paramlA)
/*     */   {
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */   public float getActualThrust()
/*     */   {
/*     */     float f;
/*  50 */     if ((
/*  50 */       f = this.thrustManager.getTotalThrust()) == 
/*  50 */       0.0F) {
/*  51 */       return 0.0F;
/*     */     }
/*  53 */     return Math.max(0.5F, f);
/*     */   }
/*     */ 
/*     */   public float getMaxVelocity()
/*     */   {
/*  60 */     if (getSegmentController().isOnServer()) {
/*  61 */       return ((vg)getSegmentController().getState()).a().a();
/*     */     }
/*  63 */     return ((ct)getSegmentController().getState()).a().a();
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  75 */     return new ThrusterCollectionManager(getSegmentController());
/*     */   }
/*     */ 
/*     */   public Vector3f getVelocity()
/*     */   {
/*  82 */     return this.velocity;
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/*  96 */     if (getSegmentController().getMass() <= 0.0F) {
/*  97 */       return;
/*     */     }
/*  99 */     if (!((Boolean)paramlA.jdField_a_of_type_LE.a().activeControllerMask.get(1).get()).booleanValue()) {
/* 100 */       return;
/*     */     }
/*     */ 
/* 103 */     if (!kd.a.equals(paramlA.jdField_a_of_type_JavaLangObject))
/*     */     {
/* 105 */       return;
/*     */     }
/* 107 */     float f1 = 3.0F / Math.max(1.0F, getSegmentController().getMass() / 8.7F);
/* 108 */     f1 = Math.max(0.1F, f1);
/*     */ 
/* 111 */     float f2 = getActualThrust();
/*     */ 
/* 113 */     if (getSegmentController().getDockingController().a() != null) {
/* 114 */       paramlA.jdField_a_of_type_LE.c(this.up);
/* 115 */       this.down.set(this.up);
/* 116 */       this.down.negate();
/* 117 */       paramlA.jdField_a_of_type_LE.b(this.left);
/* 118 */       this.right.set(this.left);
/* 119 */       this.right.negate();
/* 120 */       paramlA.jdField_a_of_type_LE.a(this.forward);
/* 121 */       this.backward.set(this.forward);
/* 122 */       this.backward.negate();
/*     */ 
/* 125 */       getSegmentController().getPhysics().orientate(getSegmentController(), this.forward, this.up, this.right, f1);
/*     */ 
/* 130 */       return;
/*     */     }
/*     */ 
/* 133 */     if (f2 == 0.0F) {
/* 134 */       if (clientIsOwnShip()) {
/* 135 */         ((ct)getState()).a().d("WARNING!\n \nNo thrusters connected to core");
/*     */       }
/* 137 */       f2 = 0.1F;
/* 138 */     } else if ((f2 <= 0.5F) && 
/* 139 */       (clientIsOwnShip())) {
/* 140 */       ((ct)getState()).a().d("WARNING!\n \nNot enough Thrusters for\n the mass of your ship\n-> Ship can only move slow");
/*     */     }
/*     */ 
/* 144 */     paramlA.jdField_a_of_type_LE.c(this.up);
/* 145 */     this.down.set(this.up);
/* 146 */     this.down.negate();
/* 147 */     paramlA.jdField_a_of_type_LE.b(this.left);
/* 148 */     this.right.set(this.left);
/* 149 */     this.right.negate();
/* 150 */     paramlA.jdField_a_of_type_LE.a(this.forward);
/* 151 */     this.backward.set(this.forward);
/* 152 */     this.backward.negate();
/*     */ 
/* 154 */     this.dir.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 156 */     if (paramlA.jdField_a_of_type_LE.a(cv.i)) {
/* 157 */       this.dir.add(this.forward);
/*     */     }
/* 159 */     if (paramlA.jdField_a_of_type_LE.a(cv.j)) {
/* 160 */       this.dir.add(this.backward);
/*     */     }
/* 162 */     if (paramlA.jdField_a_of_type_LE.a(cv.g)) {
/* 163 */       this.dir.add(this.right);
/*     */     }
/* 165 */     if (paramlA.jdField_a_of_type_LE.a(cv.h)) {
/* 166 */       this.dir.add(this.left);
/*     */     }
/* 168 */     if (paramlA.jdField_a_of_type_LE.a(cv.k)) {
/* 169 */       this.dir.add(this.up);
/*     */     }
/* 171 */     if (paramlA.jdField_a_of_type_LE.a(cv.l)) {
/* 172 */       this.dir.add(this.down);
/*     */     }
/* 174 */     RigidBody localRigidBody = (RigidBody)getPhysicsDataContainer().getObject();
/*     */ 
/* 176 */     if (this.lastMoveUpdate + 30L < System.currentTimeMillis()) {
/* 177 */       if (this.dir.length() > 0.0F) {
/* 178 */         paramlA = this.thrustManager.getTotalThrust();
/* 179 */         if (getPowerManager().getPower() < this.thrustManager.getTotalThrust()) {
/* 180 */           if (getPowerManager().getPower() <= 0.0D) {
/* 181 */             return;
/*     */           }
/* 183 */           double d1 = getPowerManager().getPower();
/* 184 */           getPowerManager().consumePowerInstantly(d1);
/* 185 */           f2 = (float)d1;
/*     */         }
/* 188 */         else if (!getPowerManager().consumePowerInstantly(paramlA)) {
/* 189 */           if (clientIsOwnShip()) {
/* 190 */             ((ct)getState()).a().d("WARNING!\n \nThrusters have no power");
/*     */           }
/* 192 */           return;
/*     */         }
/*     */ 
/* 196 */         localRigidBody.activate();
/*     */ 
/* 198 */         this.dir.scale(f2 * 0.5F);
/* 199 */         localRigidBody.applyCentralImpulse(this.dir);
/* 200 */         localRigidBody.getLinearVelocity(this.linearVelocityTmp);
/* 201 */         if (this.linearVelocityTmp.length() > getMaxVelocity()) {
/* 202 */           this.linearVelocityTmp.normalize();
/* 203 */           this.linearVelocityTmp.scale(getMaxVelocity());
/* 204 */           localRigidBody.setLinearVelocity(this.linearVelocityTmp);
/*     */         }
/*     */ 
/*     */       }
/* 209 */       else if (paramlA.jdField_a_of_type_LE.a(cv.p))
/*     */       {
/* 211 */         if ((
/* 211 */           paramlA = localRigidBody.getLinearVelocity(new Vector3f()))
/* 211 */           .length() > 1.0F) {
/* 212 */           float f3 = this.thrustManager.getTotalThrust();
/* 213 */           if (getPowerManager().getPower() < this.thrustManager.getTotalThrust()) {
/* 214 */             if (getPowerManager().getPower() > 0.0D) {
/* 215 */               double d2 = getPowerManager().getPower();
/*     */ 
/* 218 */               getPowerManager().consumePowerInstantly(d2);
/* 219 */               f2 = (float)d2;
/* 220 */               break label885;
/*     */             }
/*     */           } else { if (getPowerManager().consumePowerInstantly(f3)) break label885;
/* 223 */             if (clientIsOwnShip())
/* 224 */               ((ct)getState()).a().d("WARNING!\n \nThrusters have no power");
/*     */           }
/* 226 */           f2 = 0.1F;
/*     */           label885: Vector3f localVector3f1;
/* 232 */           (
/* 233 */             localVector3f1 = new Vector3f(paramlA))
/* 233 */             .normalize();
/* 234 */           localVector3f1.negate();
/* 235 */           localVector3f1.scale(f2 * 0.5F);
/*     */           Vector3f localVector3f2;
/* 236 */           (
/* 237 */             localVector3f2 = new Vector3f(localVector3f1))
/* 237 */             .scale(localRigidBody.getInvMass());
/*     */ 
/* 239 */           if (paramlA.length() < localVector3f2.length()) {
/* 240 */             System.err.println("INSTA ZERO: " + paramlA.length() + " < " + localVector3f1.length());
/*     */           }
/*     */           else
/*     */           {
/* 244 */             localRigidBody.applyCentralImpulse(localVector3f1);
/*     */ 
/* 247 */             break label1010; } 
/* 248 */         }paramlA.set(0.0F, 0.0F, 0.0F);
/* 249 */         localRigidBody.setLinearVelocity(paramlA);
/*     */       }
/*     */ 
/* 256 */       label1010: if (!getAttachedPlayers().isEmpty()) {
/* 257 */         getSegmentController().getPhysics().orientate(getSegmentController(), this.forward, this.up, this.right, f1);
/*     */       }
/*     */ 
/* 260 */       localRigidBody.getLinearVelocity(getVelocity());
/* 261 */       this.lastMoveUpdate = System.currentTimeMillis();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onControllerChange()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setVelocity(Vector3f paramVector3f)
/*     */   {
/* 276 */     this.velocity = paramVector3f;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.thrust.ThrusterElementManager
 * JD-Core Version:    0.6.2
 */