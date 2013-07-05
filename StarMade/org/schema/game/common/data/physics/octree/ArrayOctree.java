/*     */ package org.schema.game.common.data.physics.octree;
/*     */ 
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.Arrays;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import o;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import q;
/*     */ import xO;
/*     */ 
/*     */ public class ArrayOctree
/*     */ {
/*     */   private final short[] data;
/*     */   private final boolean[] hits;
/*     */   private final OctreeVariableSet set;
/*     */   static final int MAX_DEEPNESS = 2;
/*     */   static final int COUNT_LVLS = 3;
/*     */   static final int NODES;
/*     */   public static final short HIT_BIT = 16384;
/*     */   public static final int POSSIBLE_LEAF_HITS = 512;
/*     */   static final int BUFFER_SIZE;
/*     */   static ByteBuffer dimBuffer;
/*     */   static IntBuffer indexBuffer;
/*  63 */   private static OctreeVariableSet serverSet = new OctreeVariableSet();
/*  64 */   private static OctreeVariableSet clientSet = new OctreeVariableSet();
/*     */ 
/*     */   public ArrayOctree(boolean paramBoolean) {
/*  67 */     this.data = new short[NODES];
/*  68 */     this.hits = new boolean[NODES];
/*  69 */     if (paramBoolean) {
/*  70 */       this.set = serverSet; return;
/*     */     }
/*  72 */     this.set = clientSet;
/*     */   }
/*     */ 
/*     */   public static byte getStart(int paramInt1, int paramInt2)
/*     */   {
/* 135 */     paramInt2 *= 6;
/* 136 */     return dimBuffer.get(paramInt2 + paramInt1);
/*     */   }
/*     */ 
/*     */   public static byte getEnd(int paramInt1, int paramInt2) {
/* 140 */     paramInt2 *= 6;
/* 141 */     return dimBuffer.get(paramInt2 + 3 + paramInt1);
/*     */   }
/*     */ 
/*     */   public static void getBox(int paramInt, o paramo1, o paramo2) {
/* 145 */     paramInt *= 6;
/* 146 */     paramo1.b(dimBuffer.get(paramInt), dimBuffer.get(paramInt + 1), dimBuffer.get(paramInt + 2));
/* 147 */     paramo2.b(dimBuffer.get(paramInt + 3), dimBuffer.get(paramInt + 4), dimBuffer.get(paramInt + 5));
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 152 */     System.err.println(128);
/*     */   }
/*     */ 
/*     */   public static int getIndex(int paramInt1, int paramInt2)
/*     */   {
/* 175 */     if (paramInt2 == 0) {
/* 176 */       return paramInt1 + 1;
/*     */     }
/*     */ 
/* 179 */     int i = 1;
/* 180 */     int j = 8;
/* 181 */     for (int k = 0; k < paramInt2; k++) {
/* 182 */       i += j;
/* 183 */       j <<= 3;
/*     */     }
/*     */ 
/* 188 */     return i + paramInt1;
/*     */   }
/*     */ 
/*     */   private static int getSuperIndex(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 193 */     switch (paramInt4)
/*     */     {
/*     */     case 0:
/* 196 */       return paramInt1;
/*     */     case 1:
/* 197 */       return (paramInt1 << 3) + paramInt2;
/*     */     case 2:
/* 198 */       return (paramInt1 << 3 << 3) + (paramInt2 << 3) + paramInt3;
/*     */     }
/* 200 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */   public void insert(byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/* 206 */     for (int i = 0; i < 3; i++) {
/* 207 */       int j = ArrayOctreeGenerator.getNodeIndex(paramByte1, paramByte2, paramByte3, i);
/* 208 */       if (i == 2) {
/* 209 */         int k = (paramByte3 % 2 << 2) + (paramByte2 % 2 << 1) + paramByte1 % 2;
/*     */ 
/* 211 */         k = (short)(1 << k);
/*     */         int tmp55_53 = j;
/*     */         short[] tmp55_50 = this.data; tmp55_50[tmp55_53] = ((short)(tmp55_50[tmp55_53] | k));
/*     */ 
/* 214 */         assert (this.data[j] > 0);
/*     */       }
/*     */       else
/*     */       {
/*     */         int tmp95_93 = j;
/*     */         short[] tmp95_90 = this.data; tmp95_90[tmp95_93] = ((short)(tmp95_90[tmp95_93] + 1));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 221 */   public void delete(byte paramByte1, byte paramByte2, byte paramByte3) { for (int i = 0; i < 3; i++) {
/* 222 */       int j = ArrayOctreeGenerator.getNodeIndex(paramByte1, paramByte2, paramByte3, i);
/* 223 */       if (i == 2)
/*     */       {
/* 225 */         int k = (paramByte3 % 2 << 2) + (paramByte2 % 2 << 1) + paramByte1 % 2;
/*     */ 
/* 227 */         k = (short)(1 << k);
/*     */         int tmp55_53 = j;
/*     */         short[] tmp55_50 = this.data; tmp55_50[tmp55_53] = ((short)(tmp55_50[tmp55_53] & (k ^ 0xFFFFFFFF)));
/*     */       }
/*     */       else
/*     */       {
/*     */         int tmp73_71 = j;
/*     */         short[] tmp73_68 = this.data; tmp73_68[tmp73_71] = ((short)(tmp73_68[tmp73_71] - 1));
/*     */       }
/*     */ 
/* 233 */       if (this.data[j] < 0) {
/* 234 */         System.err.println("[OCTREE] EXCEPTION: NODE < 0");
/* 235 */         this.data[j] = 0;
/*     */       }
/*     */     } }
/*     */ 
/*     */   public void reset() {
/* 240 */     Arrays.fill(this.data, (short)0);
/*     */   }
/*     */   public void setHasHit(int paramInt, boolean paramBoolean) {
/* 243 */     this.hits[paramInt] = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean isHasHit(int paramInt)
/*     */   {
/* 252 */     return this.hits[paramInt];
/*     */   }
/*     */ 
/*     */   public IntersectionCallback findIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/*     */   {
/* 259 */     for (int i = 0; i < 8; i++) {
/* 260 */       paramIntersectionCallback = findIntersecting(i, 0, paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2);
/*     */     }
/* 262 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   private IntersectionCallback findIntersecting(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/*     */   {
/* 269 */     int i = getIndex(paramInt1, paramInt2);
/* 270 */     if (this.data[i] > 0)
/*     */     {
/* 272 */       paramIntersectionCallback = doIntersecting(paramInt1, paramInt2, paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2);
/*     */ 
/* 274 */       if ((paramInt2 < 2) && 
/* 275 */         (isHasHit(i))) {
/* 276 */         paramInt1 <<= 3;
/* 277 */         for (i = 0; i < 8; i++) {
/* 278 */           paramIntersectionCallback = findIntersecting(paramInt1 + i, paramInt2 + 1, paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2);
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 284 */       setHasHit(i, false);
/*     */     }
/* 286 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   private IntersectionCallback doIntersecting(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/*     */   {
/* 293 */     paramIntersectionCallback.leafCalcs += 1;
/*     */ 
/* 296 */     getBox(paramInt1 = getIndex(paramInt1, paramInt2), 
/* 296 */       paramOctreeVariableSet.min, paramOctreeVariableSet.max);
/*     */ 
/* 298 */     paramFloat2 = paramOctreeVariableSet.tmpMin;
/* 299 */     Vector3f localVector3f1 = paramOctreeVariableSet.tmpMax;
/* 300 */     Vector3f localVector3f2 = paramOctreeVariableSet.tmpMinOut;
/* 301 */     Vector3f localVector3f3 = paramOctreeVariableSet.tmpMaxOut;
/*     */ 
/* 304 */     float f1 = paramSegment.a.a - 0.5F;
/* 305 */     float f2 = paramSegment.a.b - 0.5F;
/* 306 */     paramSegment = paramSegment.a.c - 0.5F;
/*     */ 
/* 308 */     paramFloat2.x = (paramOctreeVariableSet.min.a + f1);
/* 309 */     paramFloat2.y = (paramOctreeVariableSet.min.b + f2);
/* 310 */     paramFloat2.z = (paramOctreeVariableSet.min.c + paramSegment);
/*     */ 
/* 312 */     localVector3f1.x = (paramOctreeVariableSet.max.a + f1);
/* 313 */     localVector3f1.y = (paramOctreeVariableSet.max.b + f2);
/* 314 */     localVector3f1.z = (paramOctreeVariableSet.max.c + paramSegment);
/*     */ 
/* 316 */     transformAabb(paramOctreeVariableSet, paramInt2, paramFloat2, localVector3f1, paramMatrix3f, paramFloat1, paramTransform, localVector3f2, localVector3f3);
/*     */ 
/* 322 */     paramSegment = AabbUtil2.testAabbAgainstAabb2(localVector3f2, localVector3f3, paramVector3f1, paramVector3f2);
/*     */ 
/* 324 */     setHasHit(paramInt1, paramSegment);
/*     */ 
/* 327 */     if ((paramSegment != 0) && (paramInt2 == 2))
/*     */     {
/* 331 */       assert (this.data[paramInt1] > 0);
/* 332 */       paramIntersectionCallback.addHit(localVector3f2, localVector3f3, paramOctreeVariableSet.min.a, paramOctreeVariableSet.min.b, paramOctreeVariableSet.min.c, paramOctreeVariableSet.max.a, paramOctreeVariableSet.max.b, paramOctreeVariableSet.max.c, this.data[paramInt1]);
/*     */     }
/*     */ 
/* 347 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   public IntersectionCallback findIntersectingRay(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/*     */   {
/* 354 */     for (int i = 0; i < 8; i++) {
/* 355 */       paramIntersectionCallback = findIntersectingRay(i, 0, paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
/*     */     }
/* 357 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   private IntersectionCallback findIntersectingRay(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/*     */   {
/* 364 */     int i = getIndex(paramInt1, paramInt2);
/*     */ 
/* 366 */     if ((this.data[i] > 0) || (paramInt2 == 0))
/*     */     {
/* 368 */       paramIntersectionCallback = doIntersectingRay(paramInt1, paramInt2, paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
/*     */ 
/* 370 */       if ((paramInt2 < 2) && 
/* 371 */         (isHasHit(i))) {
/* 372 */         paramInt1 <<= 3;
/* 373 */         for (i = 0; i < 8; i++)
/* 374 */           paramIntersectionCallback = findIntersectingRay(paramInt1 + i, paramInt2 + 1, paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 379 */       setHasHit(i, false);
/*     */     }
/* 381 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   private IntersectionCallback doIntersectingRay(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/*     */   {
/* 388 */     paramInt1 = getIndex(paramInt1, paramInt2);
/*     */ 
/* 390 */     paramIntersectionCallback.leafCalcs += 1;
/*     */ 
/* 392 */     paramFloat2 = paramOctreeVariableSet.tmpMin;
/* 393 */     Vector3f localVector3f1 = paramOctreeVariableSet.tmpMax;
/* 394 */     Vector3f localVector3f2 = paramOctreeVariableSet.tmpMinOut;
/* 395 */     Vector3f localVector3f3 = paramOctreeVariableSet.tmpMaxOut;
/*     */ 
/* 397 */     getBox(paramInt1, paramOctreeVariableSet.min, paramOctreeVariableSet.max);
/*     */ 
/* 399 */     float f1 = paramSegment.a.a - 0.5F;
/* 400 */     float f2 = paramSegment.a.b - 0.5F;
/* 401 */     paramSegment = paramSegment.a.c - 0.5F;
/*     */ 
/* 403 */     paramFloat2.x = (paramOctreeVariableSet.min.a + f1);
/* 404 */     paramFloat2.y = (paramOctreeVariableSet.min.b + f2);
/* 405 */     paramFloat2.z = (paramOctreeVariableSet.min.c + paramSegment);
/*     */ 
/* 407 */     localVector3f1.x = (paramOctreeVariableSet.max.a + f1);
/* 408 */     localVector3f1.y = (paramOctreeVariableSet.max.b + f2);
/* 409 */     localVector3f1.z = (paramOctreeVariableSet.max.c + paramSegment);
/*     */ 
/* 412 */     transformAabb(paramOctreeVariableSet, paramInt2, paramFloat2, localVector3f1, paramMatrix3f, paramFloat1, paramTransform, localVector3f2, localVector3f3);
/*     */ 
/* 415 */     paramOctreeVariableSet.param[0] = 1.0F;
/* 416 */     paramOctreeVariableSet.normal.x = 0.0F;
/* 417 */     paramOctreeVariableSet.normal.y = 0.0F;
/* 418 */     paramOctreeVariableSet.normal.z = 0.0F;
/*     */ 
/* 420 */     paramTransform = rayAabb(paramVector3f1, paramVector3f2, localVector3f2, localVector3f3, paramOctreeVariableSet.param, paramOctreeVariableSet.normal);
/*     */ 
/* 423 */     paramMatrix3f = 0;
/* 424 */     if (paramTransform == 0) {
/* 425 */       paramMatrix3f = (xO.a(paramVector3f2, localVector3f2, localVector3f3)) || (xO.a(paramVector3f1, localVector3f2, localVector3f3)) ? 1 : 0;
/*     */     }
/*     */ 
/* 428 */     paramTransform = (paramTransform != 0) || (paramMatrix3f != 0) ? 1 : 0;
/* 429 */     setHasHit(paramInt1, paramTransform);
/*     */ 
/* 432 */     if ((paramTransform != 0) && (paramInt2 == 2)) {
/* 433 */       assert (this.data[paramInt1] > 0);
/* 434 */       paramIntersectionCallback.addHit(localVector3f2, localVector3f3, paramOctreeVariableSet.min.a, paramOctreeVariableSet.min.b, paramOctreeVariableSet.min.c, paramOctreeVariableSet.max.a, paramOctreeVariableSet.max.b, paramOctreeVariableSet.max.c, this.data[paramInt1]);
/*     */     }
/*     */ 
/* 438 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   private void transformAabb(OctreeVariableSet paramOctreeVariableSet, int paramInt, Vector3f paramVector3f1, Vector3f paramVector3f2, Matrix3f paramMatrix3f, float paramFloat, Transform paramTransform, Vector3f paramVector3f3, Vector3f paramVector3f4)
/*     */   {
/* 444 */     (
/* 446 */       paramInt = paramOctreeVariableSet.localCenter)
/* 446 */       .add(paramVector3f2, paramVector3f1);
/* 447 */     paramInt.scale(0.5F);
/*     */     Vector3f localVector3f;
/* 452 */     (
/* 453 */       localVector3f = paramOctreeVariableSet.center)
/* 453 */       .set(paramInt);
/* 454 */     paramTransform.transform(localVector3f);
/*     */ 
/* 456 */     paramInt = paramOctreeVariableSet.extend;
/* 457 */     (
/* 458 */       paramOctreeVariableSet = paramOctreeVariableSet.localHalfExtents)
/* 458 */       .sub(paramVector3f2, paramVector3f1);
/* 459 */     paramOctreeVariableSet.scale(0.5F);
/*     */ 
/* 461 */     paramOctreeVariableSet.x += paramFloat;
/* 462 */     paramOctreeVariableSet.y += paramFloat;
/* 463 */     paramOctreeVariableSet.z += paramFloat;
/*     */ 
/* 465 */     paramInt.x = (paramMatrix3f.m00 * paramOctreeVariableSet.x + paramMatrix3f.m01 * paramOctreeVariableSet.y + paramMatrix3f.m02 * paramOctreeVariableSet.z);
/* 466 */     paramInt.y = (paramMatrix3f.m10 * paramOctreeVariableSet.x + paramMatrix3f.m11 * paramOctreeVariableSet.y + paramMatrix3f.m12 * paramOctreeVariableSet.z);
/* 467 */     paramInt.z = (paramMatrix3f.m20 * paramOctreeVariableSet.x + paramMatrix3f.m21 * paramOctreeVariableSet.y + paramMatrix3f.m22 * paramOctreeVariableSet.z);
/*     */ 
/* 469 */     paramVector3f3.sub(localVector3f, paramInt);
/* 470 */     paramVector3f4.add(localVector3f, paramInt);
/*     */   }
/*     */   public boolean rayAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, float[] paramArrayOfFloat, Vector3f paramVector3f5) {
/* 473 */     Vector3f localVector3f1 = this.set.aabbHalfExtent;
/* 474 */     Vector3f localVector3f2 = this.set.aabbCenter;
/* 475 */     Vector3f localVector3f3 = this.set.source;
/* 476 */     Vector3f localVector3f4 = this.set.target;
/* 477 */     Vector3f localVector3f6 = this.set.r;
/* 478 */     Vector3f localVector3f7 = this.set.hitNormal;
/*     */ 
/* 480 */     localVector3f1.sub(paramVector3f4, paramVector3f3);
/* 481 */     localVector3f1.scale(0.5F);
/*     */ 
/* 483 */     localVector3f2.add(paramVector3f4, paramVector3f3);
/* 484 */     localVector3f2.scale(0.5F);
/*     */ 
/* 486 */     localVector3f3.sub(paramVector3f1, localVector3f2);
/* 487 */     localVector3f4.sub(paramVector3f2, localVector3f2);
/*     */ 
/* 489 */     paramVector3f1 = AabbUtil2.outcode(localVector3f3, localVector3f1);
/* 490 */     paramVector3f2 = AabbUtil2.outcode(localVector3f4, localVector3f1);
/* 491 */     if ((paramVector3f1 & paramVector3f2) == 0) {
/* 492 */       paramVector3f3 = 0.0F;
/* 493 */       paramVector3f4 = paramArrayOfFloat[0];
/* 494 */       localVector3f6.sub(localVector3f4, localVector3f3);
/*     */ 
/* 496 */       float f1 = 1.0F;
/* 497 */       localVector3f7.set(0.0F, 0.0F, 0.0F);
/* 498 */       Vector3f localVector3f5 = 1;
/*     */ 
/* 500 */       for (int j = 0; j < 2; j++) {
/* 501 */         for (int k = 0; k != 3; k++)
/*     */         {
/*     */           float f2;
/* 502 */           if ((paramVector3f1 & localVector3f5) != 0) {
/* 503 */             f2 = (-VectorUtil.getCoord(localVector3f3, k) - VectorUtil.getCoord(localVector3f1, k) * f1) / VectorUtil.getCoord(localVector3f6, k);
/* 504 */             if (paramVector3f3 <= f2) {
/* 505 */               paramVector3f3 = f2;
/* 506 */               localVector3f7.set(0.0F, 0.0F, 0.0F);
/* 507 */               VectorUtil.setCoord(localVector3f7, k, f1);
/*     */             }
/*     */           }
/* 510 */           else if ((paramVector3f2 & localVector3f5) != 0) {
/* 511 */             f2 = (-VectorUtil.getCoord(localVector3f3, k) - VectorUtil.getCoord(localVector3f1, k) * f1) / VectorUtil.getCoord(localVector3f6, k);
/*     */ 
/* 513 */             paramVector3f4 = Math.min(paramVector3f4, f2);
/*     */           }
/*     */           int i;
/* 515 */           localVector3f5 <<= 1;
/*     */         }
/* 517 */         f1 = -1.0F;
/*     */       }
/* 519 */       if (paramVector3f3 <= paramVector3f4) {
/* 520 */         paramArrayOfFloat[0] = paramVector3f3;
/* 521 */         paramVector3f5.set(localVector3f7);
/* 522 */         return true;
/*     */       }
/*     */     }
/* 525 */     return false;
/*     */   }
/*     */ 
/*     */   public OctreeVariableSet getSet()
/*     */   {
/* 531 */     return this.set;
/*     */   }
/*     */   public static OctreeVariableSet getSet(boolean paramBoolean) {
/* 534 */     if (paramBoolean) return serverSet; return clientSet;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  44 */     NODES = 585;
/*     */ 
/*  54 */     dimBuffer = BufferUtils.createByteBuffer(ArrayOctree.BUFFER_SIZE = 3510);
/*     */ 
/*  56 */     indexBuffer = BufferUtils.createIntBuffer(12288);
/*     */ 
/*  60 */     ArrayOctreeGenerator.splitStart(new o(-8.0F, -8.0F, -8.0F), new o(8.0F, 8.0F, 8.0F), (byte)8);
/*  61 */     ArrayOctreeTraverse.create();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.ArrayOctree
 * JD-Core Version:    0.6.2
 */