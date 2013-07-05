/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.CompoundShape;
/*     */ import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ 
/*     */ public class CubesCompoundShape extends CompoundShape
/*     */ {
/*     */   private SegmentController segmentController;
/*  25 */   private final ArrayList attached = new ArrayList();
/*     */   private static Transform ident;
/*  62 */   private Vector3f aabbMin = new Vector3f();
/*  63 */   private Vector3f aabbMax = new Vector3f();
/*  64 */   private Vector3f halfExtents = new Vector3f();
/*     */ 
/*  67 */   private Vector3f localAabbMin = new Vector3f();
/*  68 */   private Vector3f localAabbMax = new Vector3f();
/*     */ 
/*  90 */   private final Vector3f tmpLocalAabbMin = new Vector3f();
/*  91 */   private final Vector3f tmpLocalAabbMax = new Vector3f();
/*     */ 
/* 117 */   Vector3f _localAabbMin = new Vector3f();
/* 118 */   Vector3f _localAabbMax = new Vector3f();
/*     */ 
/* 150 */   Vector3f localCenter = new Vector3f();
/* 151 */   Vector3f localHalfExtents = new Vector3f();
/* 152 */   Matrix3f abs_b = new Matrix3f();
/* 153 */   Vector3f center = new Vector3f();
/* 154 */   Vector3f tmp = new Vector3f();
/* 155 */   Vector3f extent = new Vector3f();
/*     */ 
/*     */   public CubesCompoundShape(SegmentController paramSegmentController)
/*     */   {
/*  29 */     this.segmentController = paramSegmentController;
/*     */   }
/*     */ 
/*     */   public ArrayList getAttached()
/*     */   {
/*  39 */     return this.attached;
/*     */   }
/*     */ 
/*     */   public SegmentController getSegmentController() {
/*  43 */     return this.segmentController;
/*     */   }
/*     */ 
/*     */   public boolean isCompound()
/*     */   {
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float paramFloat, Vector3f paramVector3f)
/*     */   {
/*  76 */     getAabb(ident, this.aabbMin, this.aabbMax);
/*     */ 
/*  78 */     this.halfExtents.sub(this.aabbMax, this.aabbMin);
/*  79 */     this.halfExtents.scale(0.5F);
/*     */ 
/*  81 */     float f1 = 2.0F * this.halfExtents.x;
/*  82 */     float f2 = 2.0F * this.halfExtents.y;
/*  83 */     float f3 = 2.0F * this.halfExtents.z;
/*     */ 
/*  85 */     paramVector3f.x = (paramFloat / 12.0F * (f2 * f2 + f3 * f3));
/*  86 */     paramVector3f.y = (paramFloat / 12.0F * (f1 * f1 + f3 * f3));
/*  87 */     paramVector3f.z = (paramFloat / 12.0F * (f1 * f1 + f2 * f2));
/*     */   }
/*     */ 
/*     */   public void recalculateLocalAabb()
/*     */   {
/*  95 */     this.localAabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  96 */     this.localAabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*     */ 
/* 101 */     for (int i = 0; i < getChildList().size(); i++) {
/* 102 */       ((CompoundShapeChild)getChildList().getQuick(i)).childShape.getAabb(((CompoundShapeChild)getChildList().getQuick(i)).transform, this.tmpLocalAabbMin, this.tmpLocalAabbMax);
/*     */ 
/* 104 */       for (int j = 0; j < 3; j++) {
/* 105 */         if (VectorUtil.getCoord(this.localAabbMin, j) > VectorUtil.getCoord(this.tmpLocalAabbMin, j)) {
/* 106 */           VectorUtil.setCoord(this.localAabbMin, j, VectorUtil.getCoord(this.tmpLocalAabbMin, j));
/*     */         }
/* 108 */         if (VectorUtil.getCoord(this.localAabbMax, j) < VectorUtil.getCoord(this.tmpLocalAabbMax, j))
/* 109 */           VectorUtil.setCoord(this.localAabbMax, j, VectorUtil.getCoord(this.tmpLocalAabbMax, j));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addChildShape(Transform paramTransform, CollisionShape paramCollisionShape)
/*     */   {
/*     */     CompoundShapeChild localCompoundShapeChild;
/* 122 */     (
/* 123 */       localCompoundShapeChild = new CompoundShapeChild()).transform
/* 123 */       .set(paramTransform);
/* 124 */     localCompoundShapeChild.childShape = paramCollisionShape;
/* 125 */     localCompoundShapeChild.childShapeType = paramCollisionShape.getShapeType();
/* 126 */     localCompoundShapeChild.childMargin = paramCollisionShape.getMargin();
/*     */ 
/* 128 */     getChildList().add(localCompoundShapeChild);
/*     */ 
/* 132 */     paramCollisionShape.getAabb(paramTransform, this._localAabbMin, this._localAabbMax);
/*     */ 
/* 146 */     VectorUtil.setMin(this.localAabbMin, this._localAabbMin);
/* 147 */     VectorUtil.setMax(this.localAabbMax, this._localAabbMax);
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*     */   {
/* 162 */     this.localHalfExtents.sub(this.localAabbMax, this.localAabbMin);
/* 163 */     this.localHalfExtents.scale(0.5F);
/* 164 */     this.localHalfExtents.x += getMargin();
/* 165 */     this.localHalfExtents.y += getMargin();
/* 166 */     this.localHalfExtents.z += getMargin();
/*     */ 
/* 170 */     this.localCenter.add(this.localAabbMax, this.localAabbMin);
/* 171 */     this.localCenter.scale(0.5F);
/*     */ 
/* 175 */     this.abs_b.set(paramTransform.basis);
/* 176 */     MatrixUtil.absolute(this.abs_b);
/*     */ 
/* 178 */     this.center.set(this.localCenter);
/* 179 */     paramTransform.transform(this.center);
/*     */ 
/* 184 */     this.abs_b.getRow(0, this.tmp);
/* 185 */     this.extent.x = this.tmp.dot(this.localHalfExtents);
/* 186 */     this.abs_b.getRow(1, this.tmp);
/* 187 */     this.extent.y = this.tmp.dot(this.localHalfExtents);
/* 188 */     this.abs_b.getRow(2, this.tmp);
/* 189 */     this.extent.z = this.tmp.dot(this.localHalfExtents);
/*     */ 
/* 191 */     paramVector3f1.sub(this.center, this.extent);
/* 192 */     paramVector3f2.add(this.center, this.extent);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 232 */     return "[CCS" + (this.segmentController.isOnServer() ? "|SER " : "|CLI ") + this.segmentController + "]";
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  58 */     (
/*  60 */       CubesCompoundShape.ident = new Transform())
/*  60 */       .setIdentity();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubesCompoundShape
 * JD-Core Version:    0.6.2
 */