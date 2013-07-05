/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import jL;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import o;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import q;
/*     */ import xO;
/*     */ 
/*     */ public class CubeShape extends CollisionShape
/*     */ {
/*  19 */   private float margin = 0.15F;
/*     */   private jL segmentBuffer;
/*  23 */   private Vector3f min = new Vector3f();
/*     */ 
/*  26 */   private Vector3f max = new Vector3f();
/*     */ 
/*  28 */   private Vector3f minCached = new Vector3f();
/*  29 */   private Vector3f maxCached = new Vector3f();
/*     */ 
/*  32 */   private Transform cachedTransform = new Transform();
/*  33 */   private short cacheDate = -1;
/*     */   private float cachedMargin;
/*  35 */   private static Vector3f localScaling = new Vector3f(1.0F, 1.0F, 1.0F);
/*     */ 
/*  37 */   public CubeShape(jL paramjL) { this.segmentBuffer = paramjL;
/*  38 */     this.cachedTransform.setIdentity();
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float paramFloat, Vector3f paramVector3f)
/*     */   {
/*  46 */     float f1 = this.segmentBuffer.a().b.x - this.segmentBuffer.a().a.x + this.margin;
/*  47 */     float f2 = this.segmentBuffer.a().b.y - this.segmentBuffer.a().a.y + this.margin;
/*  48 */     float f3 = this.segmentBuffer.a().b.z - this.segmentBuffer.a().a.z + this.margin;
/*     */ 
/*  53 */     paramVector3f.set(paramFloat / 3.0F * (f2 * f2 + f3 * f3), paramFloat / 3.0F * (f1 * f1 + f3 * f3), paramFloat / 3.0F * (f1 * f1 + f2 * f2));
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform paramTransform, float paramFloat, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*     */   {
/*  59 */     if ((paramFloat == this.cachedMargin) && (this.segmentBuffer.a().getState().getUpdateNumber() == this.cacheDate) && (paramTransform.equals(this.cachedTransform)))
/*     */     {
/*  62 */       paramVector3f1.set(this.minCached);
/*  63 */       paramVector3f2.set(this.maxCached); return;
/*     */     }
/*  65 */     getAabbUncached(paramTransform, paramFloat, paramVector3f1, paramVector3f2);
/*     */   }
/*     */ 
/*     */   public void getAabbUncached(Transform paramTransform, float paramFloat, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*     */   {
/*  70 */     if (!this.segmentBuffer.a().a())
/*     */     {
/*  72 */       this.min.set(0.0F, 0.0F, 0.0F);
/*  73 */       this.max.set(0.0F, 0.0F, 0.0F);
/*     */     } else {
/*  75 */       this.min.set(this.segmentBuffer.a().a);
/*  76 */       this.max.set(this.segmentBuffer.a().b);
/*     */     }
/*  78 */     if ((this.min.x > this.max.x) || (this.min.y > this.max.y) || (this.min.z > this.max.z)) {
/*  79 */       System.err.println("[EXCEPTION] WARNING. physics cube AABB is corrupt: " + this.segmentBuffer.a());
/*     */ 
/*  81 */       this.min.set(0.0F, 0.0F, 0.0F);
/*  82 */       this.max.set(0.0F, 0.0F, 0.0F);
/*     */     }
/*     */ 
/*  88 */     AabbUtil2.transformAabb(this.min, this.max, paramFloat, paramTransform, paramVector3f1, paramVector3f2);
/*     */ 
/*  90 */     this.minCached.set(paramVector3f1);
/*  91 */     this.maxCached.set(paramVector3f2);
/*  92 */     this.cachedTransform.set(paramTransform);
/*  93 */     this.cachedMargin = paramFloat;
/*  94 */     this.cacheDate = this.segmentBuffer.a().getState().getUpdateNumber();
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*     */   {
/* 101 */     getAabb(paramTransform, this.margin, paramVector3f1, paramVector3f2);
/*     */   }
/*     */ 
/*     */   public Vector3f getLocalScaling(Vector3f paramVector3f)
/*     */   {
/* 108 */     return localScaling;
/*     */   }
/*     */ 
/*     */   public float getMargin()
/*     */   {
/* 113 */     return this.margin;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 118 */     return "CUBES_MESH";
/*     */   }
/*     */ 
/*     */   public static void transformAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Transform paramTransform, Vector3f paramVector3f3, Vector3f paramVector3f4, AABBVarSet paramAABBVarSet, Matrix3f paramMatrix3f)
/*     */   {
/* 123 */     assert (paramVector3f1.x <= paramVector3f2.x);
/* 124 */     assert (paramVector3f1.y <= paramVector3f2.y);
/* 125 */     assert (paramVector3f1.z <= paramVector3f2.z);
/*     */     Vector3f localVector3f;
/* 127 */     (
/* 128 */       localVector3f = paramAABBVarSet.localHalfExtents)
/* 128 */       .sub(paramVector3f2, paramVector3f1);
/* 129 */     localVector3f.scale(0.5F);
/*     */ 
/* 131 */     localVector3f.x += paramFloat;
/* 132 */     localVector3f.y += paramFloat;
/* 133 */     localVector3f.z += paramFloat;
/*     */ 
/* 135 */     (
/* 136 */       paramFloat = paramAABBVarSet.localCenter)
/* 136 */       .add(paramVector3f2, paramVector3f1);
/* 137 */     paramFloat.scale(0.5F);
/*     */ 
/* 139 */     (
/* 140 */       paramVector3f1 = paramAABBVarSet.abs_b)
/* 140 */       .set(paramMatrix3f);
/*     */ 
/* 144 */     (
/* 145 */       paramVector3f2 = paramAABBVarSet.center)
/* 145 */       .set(paramFloat);
/* 146 */     paramTransform.transform(paramVector3f2);
/*     */ 
/* 148 */     (
/* 159 */       paramFloat = paramAABBVarSet.extent).x = 
/* 159 */       (paramVector3f1.m00 * localVector3f.x + paramVector3f1.m01 * localVector3f.y + paramVector3f1.m02 * localVector3f.z);
/*     */ 
/* 163 */     paramFloat.y = (paramVector3f1.m10 * localVector3f.x + paramVector3f1.m11 * localVector3f.y + paramVector3f1.m12 * localVector3f.z);
/*     */ 
/* 168 */     paramFloat.z = (paramVector3f1.m20 * localVector3f.x + paramVector3f1.m21 * localVector3f.y + paramVector3f1.m22 * localVector3f.z);
/*     */ 
/* 171 */     paramVector3f3.sub(paramVector3f2, paramFloat);
/* 172 */     paramVector3f4.add(paramVector3f2, paramFloat);
/*     */   }
/*     */ 
/*     */   public static void transformAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Transform paramTransform, Vector3f paramVector3f3, Vector3f paramVector3f4, AABBVarSet paramAABBVarSet) {
/* 176 */     assert (paramVector3f1.x <= paramVector3f2.x);
/* 177 */     assert (paramVector3f1.y <= paramVector3f2.y);
/* 178 */     assert (paramVector3f1.z <= paramVector3f2.z);
/*     */     Vector3f localVector3f;
/* 180 */     (
/* 181 */       localVector3f = paramAABBVarSet.localHalfExtents)
/* 181 */       .sub(paramVector3f2, paramVector3f1);
/* 182 */     localVector3f.scale(0.5F);
/*     */ 
/* 184 */     localVector3f.x += paramFloat;
/* 185 */     localVector3f.y += paramFloat;
/* 186 */     localVector3f.z += paramFloat;
/*     */ 
/* 188 */     (
/* 189 */       paramFloat = paramAABBVarSet.localCenter)
/* 189 */       .add(paramVector3f2, paramVector3f1);
/* 190 */     paramFloat.scale(0.5F);
/*     */ 
/* 192 */     (
/* 193 */       paramVector3f1 = paramAABBVarSet.abs_b)
/* 193 */       .set(paramTransform.basis);
/* 194 */     MatrixUtil.absolute(paramVector3f1);
/*     */ 
/* 196 */     (
/* 197 */       paramVector3f2 = paramAABBVarSet.center)
/* 197 */       .set(paramFloat);
/* 198 */     paramTransform.transform(paramVector3f2);
/*     */ 
/* 200 */     (
/* 211 */       paramFloat = paramAABBVarSet.extent).x = 
/* 211 */       (paramVector3f1.m00 * localVector3f.x + paramVector3f1.m01 * localVector3f.y + paramVector3f1.m02 * localVector3f.z);
/*     */ 
/* 215 */     paramFloat.y = (paramVector3f1.m10 * localVector3f.x + paramVector3f1.m11 * localVector3f.y + paramVector3f1.m12 * localVector3f.z);
/*     */ 
/* 220 */     paramFloat.z = (paramVector3f1.m20 * localVector3f.x + paramVector3f1.m21 * localVector3f.y + paramVector3f1.m22 * localVector3f.z);
/*     */ 
/* 222 */     paramVector3f3.sub(paramVector3f2, paramFloat);
/* 223 */     paramVector3f4.add(paramVector3f2, paramFloat);
/*     */   }
/*     */ 
/*     */   public void getSegmentAabb(Segment paramSegment, Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, AABBVarSet paramAABBVarSet)
/*     */   {
/* 228 */     Object localObject;
/* 228 */     if (paramSegment.jdField_a_of_type_Short == this.segmentBuffer.a().getState().getUpdateNumber()) { float[] arrayOfFloat = paramSegment.jdField_a_of_type_ArrayOfFloat; localObject = paramTransform; if (((arrayOfFloat[0] == ((Transform)localObject).basis.m00) && (arrayOfFloat[1] == ((Transform)localObject).basis.m10) && (arrayOfFloat[2] == ((Transform)localObject).basis.m20) && (arrayOfFloat[3] == 0.0F) && (arrayOfFloat[4] == ((Transform)localObject).basis.m01) && (arrayOfFloat[5] == ((Transform)localObject).basis.m11) && (arrayOfFloat[6] == ((Transform)localObject).basis.m21) && (arrayOfFloat[7] == 0.0F) && (arrayOfFloat[8] == ((Transform)localObject).basis.m02) && (arrayOfFloat[9] == ((Transform)localObject).basis.m12) && (arrayOfFloat[10] == ((Transform)localObject).basis.m22) && (arrayOfFloat[11] == 0.0F) && (arrayOfFloat[12] == ((Transform)localObject).origin.x) && (arrayOfFloat[13] == ((Transform)localObject).origin.y) && (arrayOfFloat[14] == ((Transform)localObject).origin.z) && (arrayOfFloat[15] == 1.0F) ? 1 : 0) != 0)
/*     */       {
/* 231 */         paramVector3f1.set(paramSegment.b);
/* 232 */         paramVector3f2.set(paramSegment.c); return;
/*     */       } }
/* 234 */     if (!paramSegment.g())
/*     */     {
/* 237 */       if ((
/* 237 */         localObject = paramSegment.a())
/* 237 */         .getMin().a <= ((SegmentData)localObject).getMax().a) {
/* 238 */         paramVector3f3.set(paramSegment.jdField_a_of_type_Q.a + ((SegmentData)localObject).getMin().a - 8 - 0.5F, paramSegment.jdField_a_of_type_Q.b + ((SegmentData)localObject).getMin().b - 8 - 0.5F, paramSegment.jdField_a_of_type_Q.c + ((SegmentData)localObject).getMin().c - 8 - 0.5F);
/*     */ 
/* 244 */         paramVector3f4.set(paramSegment.jdField_a_of_type_Q.a + ((SegmentData)localObject).getMax().a - 8 + 0.5F, paramSegment.jdField_a_of_type_Q.b + ((SegmentData)localObject).getMax().b - 8 + 0.5F, paramSegment.jdField_a_of_type_Q.c + ((SegmentData)localObject).getMax().c - 8 + 0.5F);
/*     */ 
/* 250 */         transformAabb(paramVector3f3, paramVector3f4, this.margin, paramTransform, paramVector3f1, paramVector3f2, paramAABBVarSet);
/*     */       }
/*     */       else
/*     */       {
/* 255 */         System.err.println("[CUBESHAPE] " + ((SegmentData)localObject).getSegmentController().getState() + " WARNING: NON INIT SEGMENT DATA AABB REQUEST " + ((SegmentData)localObject).getMin() + "; " + ((SegmentData)localObject).getMax() + ": " + ((SegmentData)localObject).getSegmentController() + ": RESTRUCTING AABB");
/* 256 */         ((SegmentData)localObject).restructBB(true);
/* 257 */         paramSegment.a().getSegmentBuffer().a();
/* 258 */         paramVector3f2.set(0.0F, 0.0F, 0.0F);
/* 259 */         paramVector3f1.set(0.0F, 0.0F, 0.0F);
/*     */       }
/*     */     } else {
/* 262 */       System.err.println("[CUBESHAPE] EMPTY SEGMENT DATA AABB REQUEST");
/* 263 */       paramVector3f2.set(0.0F, 0.0F, 0.0F);
/* 264 */       paramVector3f1.set(0.0F, 0.0F, 0.0F);
/*     */     }
/* 266 */     paramSegment.jdField_a_of_type_Short = this.segmentBuffer.a().getState().getUpdateNumber();
/* 267 */     paramTransform.getOpenGLMatrix(paramSegment.jdField_a_of_type_ArrayOfFloat);
/* 268 */     paramSegment.b[0] = paramVector3f1.x;
/* 269 */     paramSegment.b[1] = paramVector3f1.y;
/* 270 */     paramSegment.b[2] = paramVector3f1.z;
/*     */ 
/* 272 */     paramSegment.c[0] = paramVector3f2.x;
/* 273 */     paramSegment.c[1] = paramVector3f2.y;
/* 274 */     paramSegment.c[2] = paramVector3f2.z;
/*     */   }
/*     */ 
/*     */   public jL getSegmentBuffer() {
/* 278 */     return this.segmentBuffer;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType()
/*     */   {
/* 283 */     return BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f paramVector3f)
/*     */   {
/* 288 */     localScaling.absolute(paramVector3f);
/*     */   }
/*     */ 
/*     */   public void setMargin(float paramFloat)
/*     */   {
/* 297 */     this.margin = paramFloat;
/*     */   }
/*     */ 
/*     */   public void setSegmentBuffer(jL paramjL)
/*     */   {
/* 302 */     this.segmentBuffer = paramjL;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 307 */     return "[CubesShape" + (this.segmentBuffer.a().isOnServer() ? "|SER " : "|CLI ") + this.segmentBuffer.a() + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeShape
 * JD-Core Version:    0.6.2
 */