/*     */ package org.schema.game.common.data.physics.octree;
/*     */ 
/*     */ import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*     */ import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
/*     */ import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import o;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.schema.game.common.data.physics.BoxShapeExt;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import q;
/*     */ import xO;
/*     */ import zg;
/*     */ 
/*     */ public class OctreeLeaf
/*     */ {
/*     */   private final short id;
/*     */   private short cnt;
/*     */   private boolean hasHit;
/*     */   private boolean onServer;
/*     */   private final byte lvl;
/*     */   public int index;
/*     */   public int localIndex;
/*     */   public int nodeIndex;
/*     */   public static final int BLOCK_SIZE = 6;
/*     */ 
/*     */   public OctreeLeaf(int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
/*     */   {
/*  47 */     assert (paramInt2 >= 0);
/*  48 */     assert (paramByte >= 0);
/*  49 */     this.onServer = paramBoolean;
/*  50 */     this.id = getSet().getId(paramByte, paramInt1, 0);
/*  51 */     this.lvl = paramByte;
/*     */   }
/*     */ 
/*     */   public OctreeLeaf(o paramo1, o paramo2, int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
/*     */   {
/*  59 */     assert (paramInt2 >= 0);
/*  60 */     assert (paramByte >= 0);
/*     */ 
/*  62 */     assert (paramInt1 < 32767);
/*     */ 
/*  65 */     this.onServer = paramBoolean;
/*  66 */     this.id = getSet().put(paramByte, paramInt1, 0, paramo1);
/*  67 */     this.lvl = paramByte;
/*     */ 
/*  69 */     getSet().put(paramByte, paramInt1, 1, paramo2);
/*     */ 
/*  71 */     (
/*  72 */       paramo2 = new o(paramo2))
/*  72 */       .c(paramo1);
/*     */ 
/*  74 */     getSet().put(paramByte, paramInt1, 2, paramo2);
/*     */ 
/*  76 */     (
/*  77 */       paramo1 = getDim(new o()))
/*  77 */       .a();
/*     */ 
/*  79 */     getSet().put(paramByte, paramInt1, 3, paramo1);
/*     */ 
/*  81 */     this.index = paramInt1;
/*  82 */     this.localIndex = (paramInt1 % 8);
/*     */ 
/*  84 */     if (paramBoolean) {
/*  85 */       this.nodeIndex = ArrayOctree.getIndex(paramInt1, paramByte - 1);
/*  86 */       (
/*  87 */         paramo1 = new StringBuilder())
/*  87 */         .append(paramByte);
/*  88 */       for (paramo2 = 0; paramo2 < paramByte; paramo2++) {
/*  89 */         paramo1.append("    ");
/*     */       }
/*  91 */       paramo1.append("#### I " + paramInt1 + " tot " + OctreeVariableSet.nodes + " -> " + ArrayOctree.getIndex(paramInt1, paramByte));
/*  92 */       System.err.println(paramo1);
/*     */ 
/*  94 */       OctreeVariableSet.nodes += 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean between(byte paramByte1, byte paramByte2, byte paramByte3) {
/*  99 */     return (paramByte1 >= getStartX()) && (paramByte2 >= getStartY()) && (paramByte3 >= getStartZ()) && (paramByte1 < getEndX()) && (paramByte2 < getEndY()) && (paramByte3 < getEndZ());
/*     */   }
/*     */ 
/*     */   public void delete(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt)
/*     */   {
/* 104 */     if (this.cnt <= 0) {
/* 105 */       System.err.println("Exception: WARNING Octree Size < 0");
/*     */     }
/* 107 */     this.cnt = ((short)Math.max(0, this.cnt - 1));
/*     */   }
/*     */ 
/*     */   public void deleteCached(TreeCache paramTreeCache, int paramInt)
/*     */   {
/* 112 */     assert (this.cnt > 0);
/* 113 */     this.cnt = ((short)(this.cnt - 1));
/*     */   }
/*     */ 
/*     */   public void drawOctree(Vector3f paramVector3f, boolean paramBoolean) {
/* 117 */     if ((!paramBoolean) && (
/* 118 */       (!isHasHit()) || (isEmpty()))) {
/* 119 */       return;
/*     */     }
/*     */ 
/* 123 */     getSet().tmpMin.set(getStartX(), getStartY(), getStartZ());
/* 124 */     getSet().tmpMin.scale(1.0F);
/* 125 */     getSet().tmpMin.x += -0.5F;
/* 126 */     getSet().tmpMin.y += -0.5F;
/* 127 */     getSet().tmpMin.z += -0.5F;
/*     */ 
/* 130 */     getSet().tmpMax.set(getEndX(), getEndY(), getEndZ());
/* 131 */     getSet().tmpMax.scale(1.0F);
/* 132 */     getSet().tmpMax.x += -0.5F;
/* 133 */     getSet().tmpMax.y += -0.5F;
/* 134 */     getSet().tmpMax.z += -0.5F;
/*     */ 
/* 136 */     getSet().tmpMax.sub(paramVector3f);
/* 137 */     getSet().tmpMin.sub(paramVector3f);
/* 138 */     paramVector3f = zg.a(getSet().tmpMin, getSet().tmpMax);
/*     */ 
/* 140 */     GL11.glPolygonMode(1032, 6913);
/* 141 */     GL11.glDisable(2896);
/* 142 */     GL11.glDisable(2884);
/* 143 */     GL11.glEnable(2903);
/* 144 */     GL11.glDisable(32879);
/* 145 */     GL11.glDisable(3553);
/* 146 */     GL11.glDisable(3552);
/* 147 */     GL11.glEnable(3042);
/* 148 */     GlUtil.a(1.0F, 1.0F, 1.0F, 0.2F);
/* 149 */     float f = 0.0F;
/* 150 */     if (!isEmpty()) {
/* 151 */       f = 1.0F;
/*     */     }
/* 153 */     if (!paramBoolean)
/*     */     {
/* 155 */       GlUtil.a(1.0F, 0.0F, f, 0.9F);
/*     */     }
/*     */     else
/*     */     {
/* 162 */       GlUtil.a(0.0F, 1.0F, 0.0F, 1.0F);
/*     */     }
/*     */ 
/* 166 */     GL11.glBegin(7);
/* 167 */     for (paramBoolean = false; paramBoolean < paramVector3f.length; paramBoolean++) {
/* 168 */       for (int i = 0; i < paramVector3f[paramBoolean].length; i++) {
/* 169 */         GL11.glVertex3f(paramVector3f[paramBoolean][i].x, paramVector3f[paramBoolean][i].y, paramVector3f[paramBoolean][i].z);
/*     */       }
/*     */     }
/* 172 */     GL11.glEnd();
/*     */ 
/* 174 */     GL11.glEnable(2896);
/* 175 */     GL11.glDisable(2903);
/* 176 */     GL11.glEnable(2884);
/* 177 */     GL11.glDisable(3042);
/* 178 */     GL11.glPolygonMode(1032, 6914);
/*     */   }
/*     */ 
/*     */   protected IntersectionCallback doIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2, boolean paramBoolean)
/*     */   {
/* 184 */     paramIntersectionCallback.leafCalcs += 1;
/*     */ 
/* 186 */     getStart(paramOctreeVariableSet, paramOctreeVariableSet.min);
/* 187 */     getEnd(paramOctreeVariableSet, paramOctreeVariableSet.max);
/*     */ 
/* 189 */     paramFloat2 = paramOctreeVariableSet.tmpMin;
/* 190 */     paramBoolean = paramOctreeVariableSet.tmpMax;
/* 191 */     Vector3f localVector3f1 = paramOctreeVariableSet.tmpMinOut;
/* 192 */     Vector3f localVector3f2 = paramOctreeVariableSet.tmpMaxOut;
/*     */ 
/* 195 */     float f1 = paramSegment.a.a - 0.5F;
/* 196 */     float f2 = paramSegment.a.b - 0.5F;
/* 197 */     paramSegment = paramSegment.a.c - 0.5F;
/*     */ 
/* 199 */     paramFloat2.x = (paramOctreeVariableSet.min.a + f1);
/* 200 */     paramFloat2.y = (paramOctreeVariableSet.min.b + f2);
/* 201 */     paramFloat2.z = (paramOctreeVariableSet.min.c + paramSegment);
/*     */ 
/* 203 */     paramBoolean.x = (paramOctreeVariableSet.max.a + f1);
/* 204 */     paramBoolean.y = (paramOctreeVariableSet.max.b + f2);
/* 205 */     paramBoolean.z = (paramOctreeVariableSet.max.c + paramSegment);
/*     */ 
/* 208 */     transformAabb(paramOctreeVariableSet, paramFloat2, paramBoolean, paramMatrix3f, paramFloat1, paramTransform, localVector3f1, localVector3f2);
/*     */ 
/* 210 */     paramOctreeVariableSet = AabbUtil2.testAabbAgainstAabb2(localVector3f1, localVector3f2, paramVector3f1, paramVector3f2);
/* 211 */     setHasHit(paramOctreeVariableSet);
/* 212 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   public IntersectionCallback findIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2, boolean paramBoolean)
/*     */   {
/* 220 */     paramIntersectionCallback = doIntersecting(paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2, paramBoolean);
/*     */ 
/* 224 */     if (this.hasHit)
/*     */     {
/* 226 */       paramIntersectionCallback.addHit(paramOctreeVariableSet.tmpMinOut, paramOctreeVariableSet.tmpMaxOut, paramOctreeVariableSet.min.a, paramOctreeVariableSet.min.b, paramOctreeVariableSet.min.c, paramOctreeVariableSet.max.a, paramOctreeVariableSet.max.b, paramOctreeVariableSet.max.c, 255);
/*     */     }
/*     */ 
/* 229 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   private void transformAabb(OctreeVariableSet paramOctreeVariableSet, Vector3f paramVector3f1, Vector3f paramVector3f2, Matrix3f paramMatrix3f, float paramFloat, Transform paramTransform, Vector3f paramVector3f3, Vector3f paramVector3f4)
/*     */   {
/* 234 */     (
/* 235 */       paramVector3f2 = paramOctreeVariableSet.localCenter)
/* 235 */       .add(paramVector3f1, OctreeVariableSet.localCentersAdd[this.lvl]);
/*     */ 
/* 238 */     (
/* 239 */       paramVector3f1 = paramOctreeVariableSet.center)
/* 239 */       .set(paramVector3f2);
/* 240 */     paramTransform.transform(paramVector3f1);
/*     */ 
/* 242 */     paramOctreeVariableSet = paramOctreeVariableSet.extend;
/*     */ 
/* 246 */     paramVector3f2 = OctreeVariableSet.localHalfExtends[this.lvl];
/*     */ 
/* 252 */     paramOctreeVariableSet.x = (paramMatrix3f.m00 * paramVector3f2.x + paramMatrix3f.m01 * paramVector3f2.y + paramMatrix3f.m02 * paramVector3f2.z);
/*     */ 
/* 257 */     paramOctreeVariableSet.y = (paramMatrix3f.m10 * paramVector3f2.x + paramMatrix3f.m11 * paramVector3f2.y + paramMatrix3f.m12 * paramVector3f2.z);
/*     */ 
/* 262 */     paramOctreeVariableSet.z = (paramMatrix3f.m20 * paramVector3f2.x + paramMatrix3f.m21 * paramVector3f2.y + paramMatrix3f.m22 * paramVector3f2.z);
/*     */ 
/* 264 */     paramVector3f3.sub(paramVector3f1, paramOctreeVariableSet);
/* 265 */     paramVector3f4.add(paramVector3f1, paramOctreeVariableSet);
/*     */   }
/*     */ 
/*     */   public IntersectionCallback findIntersectingCast(IntersectionCallback paramIntersectionCallback, Transform paramTransform1, BoxShapeExt paramBoxShapeExt, ConvexShape paramConvexShape, float paramFloat1, Segment paramSegment, Transform paramTransform2, Transform paramTransform3, float paramFloat2)
/*     */   {
/* 272 */     getSet().tmpMin.set(getStartX(), getStartY(), getStartZ());
/* 273 */     getSet().tmpMin.x += paramSegment.a.a - 0.5F;
/* 274 */     getSet().tmpMin.y += paramSegment.a.b - 0.5F;
/* 275 */     getSet().tmpMin.z += paramSegment.a.c - 0.5F;
/*     */ 
/* 277 */     getSet().tmpMax.set(getEndX(), getEndY(), getEndZ());
/* 278 */     getSet().tmpMax.x += paramSegment.a.a - 0.5F;
/* 279 */     getSet().tmpMax.y += paramSegment.a.b - 0.5F;
/* 280 */     getSet().tmpMax.z += paramSegment.a.c - 0.5F;
/*     */ 
/* 283 */     paramBoxShapeExt.setDimFromBB(getSet().tmpMin, getSet().tmpMax);
/*     */ 
/* 285 */     paramBoxShapeExt = new SubsimplexConvexCast(paramConvexShape, paramBoxShapeExt, new VoronoiSimplexSolver());
/* 286 */     (
/* 287 */       paramConvexShape = new ConvexCast.CastResult()).allowedPenetration = 
/* 287 */       0.03F;
/* 288 */     paramConvexShape.fraction = 1.0F;
/*     */ 
/* 290 */     paramTransform1 = paramBoxShapeExt.calcTimeOfImpact(paramTransform2, paramTransform3, paramTransform1, paramTransform1, paramConvexShape);
/* 291 */     setHasHit(paramTransform1);
/*     */ 
/* 293 */     if (isHasHit()) {
/* 294 */       System.err.println("NODE hit registered (" + paramConvexShape.hitPoint + " in: " + getSet().tmpMin + " - " + getSet().tmpMax + ", dim: " + getDim(new o()) + ": " + getClass());
/* 295 */       if (isLeaf())
/*     */       {
/* 297 */         if (!isEmpty()) {
/* 298 */           paramIntersectionCallback.addHit(getSet().tmpMinOut, getSet().tmpMaxOut, getStartX(), getStartY(), getStartZ(), getEndX(), getEndY(), getEndZ(), 255);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 303 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   public IntersectionCallback findIntersectingRay(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/*     */   {
/* 310 */     paramIntersectionCallback.leafCalcs += 1;
/*     */ 
/* 316 */     paramFloat2 = paramOctreeVariableSet.tmpMin;
/* 317 */     Vector3f localVector3f1 = paramOctreeVariableSet.tmpMax;
/* 318 */     Vector3f localVector3f2 = paramOctreeVariableSet.tmpMinOut;
/* 319 */     Vector3f localVector3f3 = paramOctreeVariableSet.tmpMaxOut;
/*     */ 
/* 321 */     getStart(paramOctreeVariableSet, paramOctreeVariableSet.min);
/* 322 */     getEnd(paramOctreeVariableSet, paramOctreeVariableSet.max);
/*     */ 
/* 324 */     float f1 = paramSegment.a.a - 0.5F;
/* 325 */     float f2 = paramSegment.a.b - 0.5F;
/* 326 */     float f3 = paramSegment.a.c - 0.5F;
/*     */ 
/* 328 */     paramFloat2.x = (paramOctreeVariableSet.min.a + f1);
/* 329 */     paramFloat2.y = (paramOctreeVariableSet.min.b + f2);
/* 330 */     paramFloat2.z = (paramOctreeVariableSet.min.c + f3);
/*     */ 
/* 332 */     localVector3f1.x = (paramOctreeVariableSet.max.a + f1);
/* 333 */     localVector3f1.y = (paramOctreeVariableSet.max.b + f2);
/* 334 */     localVector3f1.z = (paramOctreeVariableSet.max.c + f3);
/*     */ 
/* 358 */     if ((!$assertionsDisabled) && ((paramFloat2.x > localVector3f1.x) || (paramFloat2.y > localVector3f1.y) || (paramFloat2.z > localVector3f1.z))) throw new AssertionError("[WARNING] BOUNDING BOX IS FAULTY: " + paramSegment.a + " in " + paramSegment.a().getSegmentController() + ": " + paramFloat2 + " - " + localVector3f1 + "; star/end " + getStart(new o()) + " - " + getEnd(new o()) + "------ " + (paramOctreeVariableSet.tmpMin.x > paramOctreeVariableSet.tmpMax.x) + "," + (paramOctreeVariableSet.tmpMin.y > paramOctreeVariableSet.tmpMax.y) + "," + (paramOctreeVariableSet.tmpMin.z > paramOctreeVariableSet.tmpMax.z));
/*     */ 
/* 366 */     transformAabb(paramOctreeVariableSet, paramFloat2, localVector3f1, paramMatrix3f, paramFloat1, paramTransform, localVector3f2, localVector3f3);
/*     */ 
/* 389 */     paramOctreeVariableSet.param[0] = 1.0F;
/* 390 */     paramOctreeVariableSet.normal.x = 0.0F;
/* 391 */     paramOctreeVariableSet.normal.y = 0.0F;
/* 392 */     paramOctreeVariableSet.normal.z = 0.0F;
/*     */ 
/* 394 */     paramTransform = AabbUtil2.rayAabb(paramVector3f1, paramVector3f2, localVector3f2, localVector3f3, paramOctreeVariableSet.param, paramOctreeVariableSet.normal);
/*     */ 
/* 397 */     paramMatrix3f = 0;
/* 398 */     if (paramTransform == 0) {
/* 399 */       paramMatrix3f = (xO.a(paramVector3f2, localVector3f2, localVector3f3)) || (xO.a(paramVector3f1, localVector3f2, localVector3f3)) ? 1 : 0;
/*     */     }
/*     */ 
/* 403 */     setHasHit((paramTransform != 0) || (paramMatrix3f != 0));
/*     */ 
/* 406 */     if ((isLeaf()) && (isHasHit())) {
/* 407 */       paramIntersectionCallback.addHit(localVector3f2, localVector3f3, paramOctreeVariableSet.min.a, paramOctreeVariableSet.min.b, paramOctreeVariableSet.min.c, paramOctreeVariableSet.max.a, paramOctreeVariableSet.max.b, paramOctreeVariableSet.max.c, 255);
/*     */     }
/*     */ 
/* 411 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   public o getDim(o paramo) {
/* 415 */     getSet().get((short)(getId() + 2), paramo);
/* 416 */     return paramo;
/*     */   }
/*     */ 
/*     */   public o getEnd(o paramo) {
/* 420 */     getSet().get((short)(getId() + 1), paramo);
/* 421 */     return paramo;
/*     */   }
/*     */   public Vector3f getEnd(Vector3f paramVector3f) {
/* 424 */     getSet().get((short)(getId() + 1), paramVector3f);
/* 425 */     return paramVector3f;
/*     */   }
/*     */   public Vector3f getEnd(OctreeVariableSet paramOctreeVariableSet, Vector3f paramVector3f) {
/* 428 */     paramOctreeVariableSet.get((short)(getId() + 1), paramVector3f);
/* 429 */     return paramVector3f;
/*     */   }
/*     */   public o getEnd(OctreeVariableSet paramOctreeVariableSet, o paramo) {
/* 432 */     paramOctreeVariableSet.get((short)(getId() + 1), paramo);
/* 433 */     return paramo;
/*     */   }
/*     */   public o getHalfDim(o paramo) {
/* 436 */     getSet().get((short)(getId() + 3), paramo);
/* 437 */     return paramo;
/*     */   }
/*     */ 
/*     */   public byte getDimX() {
/* 441 */     return getSet().getX((short)(getId() + 2));
/*     */   }
/*     */ 
/*     */   public byte getEndX() {
/* 445 */     return getSet().getX((short)(getId() + 1));
/*     */   }
/*     */   public byte getHalfDimX() {
/* 448 */     return getSet().getX((short)(getId() + 3));
/*     */   }
/*     */ 
/*     */   public byte getDimY()
/*     */   {
/* 455 */     return getSet().getY((short)(getId() + 2));
/*     */   }
/*     */ 
/*     */   public byte getEndY() {
/* 459 */     return getSet().getY((short)(getId() + 1));
/*     */   }
/*     */   public byte getHalfDimY() {
/* 462 */     return getSet().getY((short)(getId() + 3));
/*     */   }
/*     */ 
/*     */   public byte getDimZ()
/*     */   {
/* 468 */     return getSet().getZ((short)(getId() + 2));
/*     */   }
/*     */ 
/*     */   public byte getEndZ() {
/* 472 */     return getSet().getZ((short)(getId() + 1));
/*     */   }
/*     */   public byte getHalfDimZ() {
/* 475 */     return getSet().getZ((short)(getId() + 3));
/*     */   }
/*     */   public short getId() {
/* 478 */     return this.id;
/*     */   }
/*     */ 
/*     */   public int getMaxLevel()
/*     */   {
/* 483 */     return getSet().maxLevel;
/*     */   }
/*     */ 
/*     */   public OctreeVariableSet getSet() {
/* 487 */     return Octree.get(onServer());
/*     */   }
/*     */   public byte getStartX() {
/* 490 */     return getSet().getX((short)getId());
/*     */   }
/*     */   public byte getStartY() {
/* 493 */     return getSet().getY((short)getId());
/*     */   }
/*     */   public byte getStartZ() {
/* 496 */     return getSet().getZ((short)getId());
/*     */   }
/*     */   public o getStart(o paramo) {
/* 499 */     getSet().get((short)getId(), paramo);
/* 500 */     return paramo;
/*     */   }
/*     */   public Vector3f getStart(Vector3f paramVector3f) {
/* 503 */     getSet().get((short)getId(), paramVector3f);
/* 504 */     return paramVector3f;
/*     */   }
/*     */   public o getStart(OctreeVariableSet paramOctreeVariableSet, o paramo) {
/* 507 */     paramOctreeVariableSet.get((short)getId(), paramo);
/* 508 */     return paramo;
/*     */   }
/*     */   public Vector3f getStart(OctreeVariableSet paramOctreeVariableSet, Vector3f paramVector3f) {
/* 511 */     paramOctreeVariableSet.get((short)getId(), paramVector3f);
/* 512 */     return paramVector3f;
/*     */   }
/*     */   public void insert(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt) {
/* 515 */     assert (between(paramByte1, paramByte2, paramByte3)) : ("not in range: " + paramByte1 + ", " + paramByte2 + ", " + paramByte3 + ": [" + getStartX() + " - " + getEndX() + "], half: " + getHalfDimX());
/*     */ 
/* 517 */     this.cnt = ((short)(this.cnt + 1));
/*     */   }
/*     */ 
/*     */   public void insertCached(TreeCache paramTreeCache, int paramInt)
/*     */   {
/* 522 */     this.cnt = ((short)(this.cnt + 1));
/*     */   }
/*     */   public boolean isEmpty() {
/* 525 */     return this.cnt == 0;
/*     */   }
/*     */ 
/*     */   public boolean isHasHit()
/*     */   {
/* 532 */     return this.hasHit;
/*     */   }
/*     */   protected boolean isLeaf() {
/* 535 */     return true;
/*     */   }
/*     */ 
/*     */   protected boolean onServer() {
/* 539 */     return this.onServer;
/*     */   }
/*     */ 
/*     */   public void reset() {
/* 543 */     this.cnt = 0;
/*     */   }
/*     */ 
/*     */   public void setHasHit(boolean paramBoolean)
/*     */   {
/* 555 */     this.hasHit = paramBoolean;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeLeaf
 * JD-Core Version:    0.6.2
 */