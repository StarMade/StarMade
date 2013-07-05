/*      */ package org.schema.game.common.data.physics;
/*      */ 
/*      */ import com.bulletphysics.collision.dispatch.ManifoldResult;
/*      */ import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*      */ import com.bulletphysics.collision.shapes.BoxShape;
/*      */ import com.bulletphysics.linearmath.IDebugDraw;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import com.bulletphysics.linearmath.VectorUtil;
/*      */ import java.util.Arrays;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Vector2f;
/*      */ import javax.vecmath.Vector3f;
/*      */ import org.schema.common.FastMath;
/*      */ 
/*      */ public class BoxBoxDetector
/*      */ {
/*   54 */   private Vector3f distPoint = new Vector3f();
/*   55 */   private Vector3f pp = new Vector3f();
/*   56 */   private Vector3f normalC = new Vector3f();
/*   57 */   private Vector3f pa = new Vector3f();
/*   58 */   private Vector3f pb = new Vector3f();
/*   59 */   private Vector3f ua = new Vector3f();
/*   60 */   private Vector3f ub = new Vector3f();
/*   61 */   private Vector2f alphaBeta = new Vector2f();
/*   62 */   private Vector3f negNormal = new Vector3f();
/*   63 */   private Vector3f nr = new Vector3f();
/*   64 */   private Vector3f normal2 = new Vector3f();
/*   65 */   private Vector3f anr = new Vector3f();
/*   66 */   private Vector3f ppa = new Vector3f();
/*   67 */   private Vector3f ppb = new Vector3f();
/*   68 */   private Vector3f Sa = new Vector3f();
/*   69 */   private Vector3f Sb = new Vector3f();
/*   70 */   private int[] iret = new int[8];
/*   71 */   private Vector3f pointInWorldFAA = new Vector3f();
/*   72 */   private Vector3f posInWorldFA = new Vector3f();
/*   73 */   private Vector3f center = new Vector3f();
/*   74 */   private Vector3f pointInWorldRes = new Vector3f();
/*   75 */   private Vector3f scaledN = new Vector3f();
/*   76 */   private BoxBoxDetector.CB cb = new BoxBoxDetector.CB(this, null);
/*      */ 
/*   80 */   private float fudge_factor = 1.05F;
/*      */ 
/*   84 */   private float[] s_quadBuffer = new float[16];
/*      */ 
/*   86 */   private float[] s_temp1 = new float[12];
/*      */ 
/*   89 */   private float[] s_temp2 = new float[12];
/*      */ 
/*   92 */   private float[] s_quad = new float[8];
/*      */ 
/*  101 */   private float[] s_ret = new float[16];
/*      */ 
/*  103 */   private float[] s_point = new float[24];
/*  104 */   private float[] s_dep = new float[8];
/*      */ 
/*  106 */   private float[] s_A = new float[8];
/*  107 */   private float[] s_rectReferenceFace = new float[2];
/*  108 */   private int[] s_availablePoints = new int[8];
/*  109 */   private Vector3f normal = new Vector3f();
/*  110 */   private Vector3f translationA = new Vector3f();
/*  111 */   private Vector3f translationB = new Vector3f();
/*  112 */   private Vector3f box1Margin = new Vector3f();
/*  113 */   private Vector3f box2Margin = new Vector3f();
/*  114 */   private Vector3f rowA = new Vector3f();
/*  115 */   private Vector3f rowB = new Vector3f();
/*  116 */   private Transform transformA = new Transform();
/*  117 */   private Transform transformB = new Transform();
/*  118 */   private Vector3f pTmp = new Vector3f();
/*      */   public static final boolean USE_CENTER_POINT = false;
/*      */   private Float depth;
/*      */ 
/*      */   private static float DDOTSpec(float[] paramArrayOfFloat, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
/*      */   {
/*  131 */     return paramArrayOfFloat[paramInt] * paramFloat1 + paramArrayOfFloat[(paramInt + 1)] * paramFloat2 + paramArrayOfFloat[(paramInt + 2)] * paramFloat3;
/*      */   }
/*      */ 
/*      */   private static float DDOT(Vector3f paramVector3f, int paramInt1, float[] paramArrayOfFloat, int paramInt2)
/*      */   {
/*  145 */     return VectorUtil.getCoord(paramVector3f, paramInt1) * paramArrayOfFloat[paramInt2] + VectorUtil.getCoord(paramVector3f, paramInt1 + 1) * paramArrayOfFloat[(paramInt2 + 1)] + VectorUtil.getCoord(paramVector3f, paramInt1 + 2) * paramArrayOfFloat[(paramInt2 + 2)];
/*      */   }
/*      */ 
/*      */   private static float DDOT14(Vector3f paramVector3f, int paramInt1, float[] paramArrayOfFloat, int paramInt2)
/*      */   {
/*  176 */     return VectorUtil.getCoord(paramVector3f, paramInt1) * paramArrayOfFloat[paramInt2] + VectorUtil.getCoord(paramVector3f, paramInt1 + 1) * paramArrayOfFloat[(paramInt2 + 4)] + VectorUtil.getCoord(paramVector3f, paramInt1 + 2) * paramArrayOfFloat[(paramInt2 + 8)];
/*      */   }
/*      */ 
/*      */   private static float DDOT41(float[] paramArrayOfFloat, int paramInt1, Vector3f paramVector3f, int paramInt2)
/*      */   {
/*  198 */     return paramArrayOfFloat[paramInt1] * VectorUtil.getCoord(paramVector3f, paramInt2) + paramArrayOfFloat[(paramInt1 + 4)] * VectorUtil.getCoord(paramVector3f, paramInt2 + 1) + paramArrayOfFloat[(paramInt1 + 8)] * VectorUtil.getCoord(paramVector3f, paramInt2 + 2);
/*      */   }
/*      */ 
/*      */   private static float DDOT41Spec(float[] paramArrayOfFloat, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
/*      */   {
/*  204 */     return paramArrayOfFloat[paramInt] * paramFloat1 + paramArrayOfFloat[(paramInt + 4)] * paramFloat2 + paramArrayOfFloat[(paramInt + 8)] * paramFloat3;
/*      */   }
/*      */ 
/*      */   private static float DDOT44(float[] paramArrayOfFloat1, int paramInt1, float[] paramArrayOfFloat2, int paramInt2)
/*      */   {
/*  210 */     return paramArrayOfFloat1[paramInt1] * paramArrayOfFloat2[paramInt2] + paramArrayOfFloat1[(paramInt1 + 4)] * paramArrayOfFloat2[(paramInt2 + 4)] + paramArrayOfFloat1[(paramInt1 + 8)] * paramArrayOfFloat2[(paramInt2 + 8)];
/*      */   }
/*      */ 
/*      */   private static void DLineClosestApproach(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector2f paramVector2f, Vector3f paramVector3f5)
/*      */   {
/*  220 */     paramVector3f5.sub(paramVector3f3, paramVector3f1);
/*      */ 
/*  222 */     paramVector3f1 = paramVector3f2.dot(paramVector3f4);
/*      */ 
/*  224 */     paramVector3f2 = paramVector3f2.dot(paramVector3f5);
/*      */ 
/*  226 */     paramVector3f3 = -paramVector3f4.dot(paramVector3f5);
/*      */ 
/*  229 */     if ((
/*  229 */       paramVector3f4 = 1.0F - paramVector3f1 * paramVector3f1) <= 
/*  229 */       1.0E-004F)
/*      */     {
/*  232 */       paramVector2f.x = 0.0F;
/*  233 */       paramVector2f.y = 0.0F; return;
/*      */     }
/*      */ 
/*  237 */     paramVector3f4 = 1.0F / paramVector3f4;
/*  238 */     paramVector2f.x = ((paramVector3f2 + paramVector3f1 * paramVector3f3) * paramVector3f4);
/*  239 */     paramVector2f.y = ((paramVector3f1 * paramVector3f2 + paramVector3f3) * paramVector3f4);
/*      */   }
/*      */ 
/*      */   public static void DMULTIPLY0_331(Vector3f paramVector3f1, float[] paramArrayOfFloat, Vector3f paramVector3f2)
/*      */   {
/*  248 */     paramVector3f1.x = DDOTSpec(paramArrayOfFloat, 0, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*  249 */     paramVector3f1.y = DDOTSpec(paramArrayOfFloat, 4, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*  250 */     paramVector3f1.z = DDOTSpec(paramArrayOfFloat, 8, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*      */   }
/*      */ 
/*      */   public static void DMULTIPLY1_331(Vector3f paramVector3f1, float[] paramArrayOfFloat, Vector3f paramVector3f2)
/*      */   {
/*  259 */     paramVector3f1.x = DDOT41Spec(paramArrayOfFloat, 0, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*  260 */     paramVector3f1.y = DDOT41Spec(paramArrayOfFloat, 1, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*  261 */     paramVector3f1.z = DDOT41Spec(paramArrayOfFloat, 2, paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/*      */   }
/*      */ 
/*      */   private void CullPoints2(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3, int[] paramArrayOfInt)
/*      */   {
/*  269 */     int i = 0;
/*      */     float f2;
/*      */     float f3;
/*      */     float f1;
/*  272 */     if (paramInt1 == 1)
/*      */     {
/*  274 */       f2 = paramArrayOfFloat[0];
/*  275 */       f3 = paramArrayOfFloat[1];
/*      */     }
/*  277 */     else if (paramInt1 == 2)
/*      */     {
/*  279 */       f2 = 0.5F * (paramArrayOfFloat[0] + paramArrayOfFloat[2]);
/*  280 */       f3 = 0.5F * (paramArrayOfFloat[1] + paramArrayOfFloat[3]);
/*      */     }
/*      */     else
/*      */     {
/*  284 */       f1 = 0.0F;
/*  285 */       f2 = 0.0F;
/*  286 */       f3 = 0.0F;
/*  287 */       for (j = 0; j < paramInt1 - 1; j++)
/*      */       {
/*  289 */         f4 = paramArrayOfFloat[(j << 1)] * paramArrayOfFloat[((j << 1) + 3)] - paramArrayOfFloat[((j << 1) + 2)] * paramArrayOfFloat[((j << 1) + 1)];
/*  290 */         f1 += f4;
/*  291 */         f2 += f4 * (paramArrayOfFloat[(j << 1)] + paramArrayOfFloat[((j << 1) + 2)]);
/*  292 */         f3 += f4 * (paramArrayOfFloat[((j << 1) + 1)] + paramArrayOfFloat[((j << 1) + 3)]);
/*      */       }
/*  294 */       float f4 = paramArrayOfFloat[((paramInt1 << 1) - 2)] * paramArrayOfFloat[1] - paramArrayOfFloat[0] * paramArrayOfFloat[((paramInt1 << 1) - 1)];
/*  295 */       if (Math.abs(f1 + f4) > 1.192093E-007F)
/*      */       {
/*  297 */         f1 = 1.0F / (3.0F * (f1 + f4));
/*      */       }
/*      */       else
/*      */       {
/*  301 */         f1 = 1.0E+030F;
/*      */       }
/*  303 */       f2 = f1 * (f2 + f4 * (paramArrayOfFloat[((paramInt1 << 1) - 2)] + paramArrayOfFloat[0]));
/*  304 */       f3 = f1 * (f3 + f4 * (paramArrayOfFloat[((paramInt1 << 1) - 1)] + paramArrayOfFloat[1]));
/*      */     }
/*      */ 
/*  308 */     float[] arrayOfFloat = this.s_A;
/*  309 */     for (int j = 0; j < paramInt1; j++)
/*      */     {
/*  311 */       arrayOfFloat[j] = FastMath.a(paramArrayOfFloat[((j << 1) + 1)] - f3, paramArrayOfFloat[(j << 1)] - f2);
/*      */     }
/*      */ 
/*  316 */     for (j = 0; j < paramInt1; j++)
/*      */     {
/*  318 */       this.s_availablePoints[j] = 1;
/*      */     }
/*  320 */     this.s_availablePoints[paramInt3] = 0;
/*  321 */     paramArrayOfInt[0] = paramInt3;
/*  322 */     i++;
/*  323 */     for (paramArrayOfFloat = 1; paramArrayOfFloat < paramInt2; paramArrayOfFloat++)
/*      */     {
/*  326 */       if ((
/*  326 */         f1 = paramArrayOfFloat * (6.283186F / paramInt2) + arrayOfFloat[paramInt3]) > 
/*  326 */         3.141593F)
/*      */       {
/*  328 */         f1 -= 6.283186F;
/*      */       }
/*  330 */       int k = 1;
/*  331 */       f3 = 3.4028235E+38F;
/*      */ 
/*  333 */       paramArrayOfInt[i] = paramInt3;
/*      */ 
/*  335 */       for (j = 0; j < paramInt1; j++)
/*      */       {
/*  337 */         if (this.s_availablePoints[j] != 0)
/*      */         {
/*      */           float f5;
/*  340 */           if ((
/*  340 */             f5 = Math.abs(arrayOfFloat[j] - f1)) > 
/*  340 */             3.141593F)
/*      */           {
/*  342 */             f5 = 6.283186F - f5;
/*      */           }
/*  344 */           if ((k != 0) || (f5 < f3))
/*      */           {
/*  346 */             k = 0;
/*  347 */             f3 = f5;
/*  348 */             paramArrayOfInt[i] = j;
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  355 */       this.s_availablePoints[paramArrayOfInt[i]] = 0;
/*  356 */       i++;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void GetClosestPoints(BoxShape paramBoxShape1, BoxShape paramBoxShape2, DiscreteCollisionDetectorInterface.ClosestPointInput paramClosestPointInput, ManifoldResult paramManifoldResult, IDebugDraw paramIDebugDraw, boolean paramBoolean)
/*      */   {
/*  364 */     this.transformA.set(paramClosestPointInput.transformA);
/*  365 */     this.transformB.set(paramClosestPointInput.transformB);
/*      */ 
/*  369 */     this.normal.set(0.0F, 0.0F, 0.0F);
/*      */ 
/*  372 */     this.depth = Float.valueOf(0.0F);
/*  373 */     this.translationA.set(this.transformA.origin);
/*      */ 
/*  377 */     this.translationB.set(this.transformB.origin);
/*      */ 
/*  380 */     paramBoxShape1.getHalfExtentsWithMargin(this.box1Margin);
/*      */ 
/*  383 */     paramBoxShape2.getHalfExtentsWithMargin(this.box2Margin);
/*      */ 
/*  390 */     for (paramBoxShape1 = 0; paramBoxShape1 < 3; paramBoxShape1++)
/*      */     {
/*  401 */       this.transformA.basis.getRow(paramBoxShape1, this.rowA);
/*  402 */       this.transformB.basis.getRow(paramBoxShape1, this.rowB);
/*      */ 
/*  404 */       this.s_temp1[(0 + 4 * paramBoxShape1)] = this.rowA.x;
/*  405 */       this.s_temp2[(0 + 4 * paramBoxShape1)] = this.rowB.x;
/*      */ 
/*  407 */       this.s_temp1[(1 + 4 * paramBoxShape1)] = this.rowA.y;
/*  408 */       this.s_temp2[(1 + 4 * paramBoxShape1)] = this.rowB.y;
/*      */ 
/*  410 */       this.s_temp1[(2 + 4 * paramBoxShape1)] = this.rowA.z;
/*  411 */       this.s_temp2[(2 + 4 * paramBoxShape1)] = this.rowB.z;
/*      */     }
/*      */ 
/*  440 */     this.cb.reset();
/*  441 */     DBoxBox2(this.translationA, this.s_temp1, this.box1Margin, this.translationB, this.s_temp2, this.box2Margin, this.normal, this.depth, -1, 4, 0, paramManifoldResult);
/*      */   }
/*      */ 
/*      */   private int DBoxBox2(Vector3f paramVector3f1, float[] paramArrayOfFloat1, Vector3f paramVector3f2, Vector3f paramVector3f3, float[] paramArrayOfFloat2, Vector3f paramVector3f4, Vector3f paramVector3f5, Float paramFloat, int paramInt1, int paramInt2, int paramInt3, ManifoldResult paramManifoldResult)
/*      */   {
/*  460 */     this.cb.normalR = null;
/*      */ 
/*  470 */     this.distPoint.sub(paramVector3f3, paramVector3f1);
/*      */ 
/*  472 */     this.pp.set(0.0F, 0.0F, 0.0F);
/*      */ 
/*  474 */     DMULTIPLY1_331(this.pp, paramArrayOfFloat1, this.distPoint);
/*      */ 
/*  486 */     paramFloat = DDOT44(paramArrayOfFloat1, 0, paramArrayOfFloat2, 0);
/*  487 */     paramInt1 = DDOT44(paramArrayOfFloat1, 0, paramArrayOfFloat2, 1);
/*  488 */     paramInt3 = DDOT44(paramArrayOfFloat1, 0, paramArrayOfFloat2, 2);
/*      */ 
/*  490 */     float f1 = DDOT44(paramArrayOfFloat1, 1, paramArrayOfFloat2, 0);
/*  491 */     float f2 = DDOT44(paramArrayOfFloat1, 1, paramArrayOfFloat2, 1);
/*  492 */     float f3 = DDOT44(paramArrayOfFloat1, 1, paramArrayOfFloat2, 2);
/*      */ 
/*  494 */     float f4 = DDOT44(paramArrayOfFloat1, 2, paramArrayOfFloat2, 0);
/*  495 */     float f5 = DDOT44(paramArrayOfFloat1, 2, paramArrayOfFloat2, 1);
/*  496 */     float f6 = DDOT44(paramArrayOfFloat1, 2, paramArrayOfFloat2, 2);
/*      */ 
/*  499 */     float f7 = Math.abs(paramFloat);
/*  500 */     float f8 = Math.abs(paramInt1);
/*  501 */     float f9 = Math.abs(paramInt3);
/*      */ 
/*  503 */     float f10 = Math.abs(f1);
/*  504 */     float f11 = Math.abs(f2);
/*  505 */     float f12 = Math.abs(f3);
/*      */ 
/*  507 */     float f13 = Math.abs(f4);
/*  508 */     float f14 = Math.abs(f5);
/*  509 */     float f15 = Math.abs(f6);
/*      */ 
/*  520 */     this.cb.invert_normal = false;
/*  521 */     this.cb.code = 0;
/*      */ 
/*  528 */     if (TST(this.pp.x, paramVector3f2.x + paramVector3f4.x * f7 + paramVector3f4.y * f8 + paramVector3f4.z * f9, paramArrayOfFloat1, 0, 1, this.cb)) return 0;
/*  529 */     if (TST(this.pp.y, paramVector3f2.y + paramVector3f4.x * f10 + paramVector3f4.y * f11 + paramVector3f4.z * f12, paramArrayOfFloat1, 1, 2, this.cb)) return 0;
/*  530 */     if (TST(this.pp.z, paramVector3f2.z + paramVector3f4.x * f13 + paramVector3f4.y * f14 + paramVector3f4.z * f15, paramArrayOfFloat1, 2, 3, this.cb)) return 0;
/*      */ 
/*  536 */     if (TST(DDOT41(paramArrayOfFloat2, 0, this.distPoint, 0), paramVector3f2.x * f7 + paramVector3f2.y * f10 + paramVector3f2.z * f13 + paramVector3f4.x, paramArrayOfFloat2, 0, 4, this.cb)) return 0;
/*  537 */     if (TST(DDOT41(paramArrayOfFloat2, 1, this.distPoint, 0), paramVector3f2.x * f8 + paramVector3f2.y * f11 + paramVector3f2.z * f14 + paramVector3f4.y, paramArrayOfFloat2, 1, 5, this.cb)) return 0;
/*  538 */     if (TST(DDOT41(paramArrayOfFloat2, 2, this.distPoint, 0), paramVector3f2.x * f9 + paramVector3f2.y * f12 + paramVector3f2.z * f15 + paramVector3f4.z, paramArrayOfFloat2, 2, 6, this.cb)) return 0;
/*      */ 
/*  545 */     this.normalC.set(0.0F, 0.0F, 0.0F);
/*      */ 
/*  547 */     f7 += 1.0E-005F;
/*      */ 
/*  550 */     f8 += 1.0E-005F;
/*  551 */     f9 += 1.0E-005F;
/*      */ 
/*  553 */     f10 += 1.0E-005F;
/*  554 */     f11 += 1.0E-005F;
/*  555 */     f12 += 1.0E-005F;
/*      */ 
/*  557 */     f13 += 1.0E-005F;
/*  558 */     f14 += 1.0E-005F;
/*  559 */     f15 += 1.0E-005F;
/*      */ 
/*  562 */     if (TST2(this.pp.z * f1 - this.pp.y * f4, paramVector3f2.y * f13 + paramVector3f2.z * f10 + paramVector3f4.y * f9 + paramVector3f4.z * f8, 0.0F, -f4, f1, this.normalC, 7, this.cb)) return 0;
/*  563 */     if (TST2(this.pp.z * f2 - this.pp.y * f5, paramVector3f2.y * f14 + paramVector3f2.z * f11 + paramVector3f4.x * f9 + paramVector3f4.z * f7, 0.0F, -f5, f2, this.normalC, 8, this.cb)) return 0;
/*  564 */     if (TST2(this.pp.z * f3 - this.pp.y * f6, paramVector3f2.y * f15 + paramVector3f2.z * f12 + paramVector3f4.x * f8 + paramVector3f4.y * f7, 0.0F, -f6, f3, this.normalC, 9, this.cb)) return 0;
/*      */ 
/*  567 */     if (TST2(this.pp.x * f4 - this.pp.z * paramFloat, paramVector3f2.x * f13 + paramVector3f2.z * f7 + paramVector3f4.y * f12 + paramVector3f4.z * f11, f4, 0.0F, -paramFloat, this.normalC, 10, this.cb)) return 0;
/*  568 */     if (TST2(this.pp.x * f5 - this.pp.z * paramInt1, paramVector3f2.x * f14 + paramVector3f2.z * f8 + paramVector3f4.x * f12 + paramVector3f4.z * f10, f5, 0.0F, -paramInt1, this.normalC, 11, this.cb)) return 0;
/*  569 */     if (TST2(this.pp.x * f6 - this.pp.z * paramInt3, paramVector3f2.x * f15 + paramVector3f2.z * f9 + paramVector3f4.x * f11 + paramVector3f4.y * f10, f6, 0.0F, -paramInt3, this.normalC, 12, this.cb)) return 0;
/*      */ 
/*  572 */     if (TST2(this.pp.y * paramFloat - this.pp.x * f1, paramVector3f2.x * f10 + paramVector3f2.y * f7 + paramVector3f4.y * f15 + paramVector3f4.z * f14, -f1, paramFloat, 0.0F, this.normalC, 13, this.cb)) return 0;
/*  573 */     if (TST2(this.pp.y * paramInt1 - this.pp.x * f2, paramVector3f2.x * f11 + paramVector3f2.y * f8 + paramVector3f4.x * f15 + paramVector3f4.z * f13, -f2, paramInt1, 0.0F, this.normalC, 14, this.cb)) return 0;
/*  574 */     if (TST2(this.pp.y * paramInt3 - this.pp.x * f3, paramVector3f2.x * f12 + paramVector3f2.y * f9 + paramVector3f4.x * f14 + paramVector3f4.y * f13, -f3, paramInt3, 0.0F, this.normalC, 15, this.cb)) return 0;
/*      */ 
/*  591 */     if (this.cb.code == 0)
/*      */     {
/*  593 */       return 0;
/*      */     }
/*      */ 
/*  597 */     if (this.cb.normalR != null)
/*      */     {
/*  600 */       paramVector3f5.x = this.cb.normalR[(0 + this.cb.normalROffset)];
/*  601 */       paramVector3f5.y = this.cb.normalR[(4 + this.cb.normalROffset)];
/*  602 */       paramVector3f5.z = this.cb.normalR[(8 + this.cb.normalROffset)];
/*  603 */       paramVector3f5.normalize();
/*      */     }
/*      */     else
/*      */     {
/*  607 */       DMULTIPLY0_331(paramVector3f5, paramArrayOfFloat1, this.normalC);
/*      */     }
/*  609 */     if (this.cb.invert_normal)
/*      */     {
/*  611 */       paramVector3f5.negate();
/*      */     }
/*  613 */     paramFloat = Float.valueOf(-this.cb.s);
/*      */ 
/*  619 */     if (this.cb.code > 6)
/*      */     {
/*  625 */       this.pa.set(paramVector3f1);
/*  626 */       for (paramInt1 = 0; paramInt1 < 3; paramInt1++)
/*      */       {
/*  628 */         paramInt3 = DDOT14(paramVector3f5, 0, paramArrayOfFloat1, paramInt1) > 0.0F ? 1.0F : -1.0F;
/*      */ 
/*  630 */         for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*      */         {
/*  632 */           VectorUtil.setCoord(this.pa, paramVector3f1, VectorUtil.getCoord(this.pa, paramVector3f1) + paramInt3 * VectorUtil.getCoord(paramVector3f2, paramInt1) * paramArrayOfFloat1[((paramVector3f1 << 2) + paramInt1)]);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  637 */       this.pb.set(paramVector3f3);
/*      */ 
/*  639 */       for (paramInt1 = 0; paramInt1 < 3; paramInt1++)
/*      */       {
/*  641 */         paramInt3 = DDOT14(paramVector3f5, 0, paramArrayOfFloat2, paramInt1) > 0.0F ? -1.0F : 1.0F;
/*  642 */         for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*      */         {
/*  644 */           VectorUtil.setCoord(this.pb, paramVector3f1, VectorUtil.getCoord(this.pb, paramVector3f1) + paramInt3 * VectorUtil.getCoord(paramVector3f4, paramInt1) * paramArrayOfFloat2[((paramVector3f1 << 2) + paramInt1)]);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  652 */       for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*      */       {
/*  654 */         VectorUtil.setCoord(this.ua, paramVector3f1, paramArrayOfFloat1[((this.cb.code - 7) / 3 + (paramVector3f1 << 2))]);
/*      */       }
/*  656 */       for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*      */       {
/*  658 */         VectorUtil.setCoord(this.ub, paramVector3f1, paramArrayOfFloat2[((this.cb.code - 7) % 3 + (paramVector3f1 << 2))]);
/*      */       }
/*      */ 
/*  661 */       DLineClosestApproach(this.pa, this.ua, this.pb, this.ub, this.alphaBeta, this.pTmp);
/*      */ 
/*  663 */       paramInt1 = this.alphaBeta.x;
/*  664 */       paramInt3 = this.alphaBeta.y;
/*      */ 
/*  666 */       for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*      */       {
/*  668 */         paramArrayOfFloat1 = VectorUtil.getCoord(this.pa, paramVector3f1) + VectorUtil.getCoord(this.ua, paramVector3f1) * paramInt1;
/*  669 */         VectorUtil.setCoord(this.pa, paramVector3f1, paramArrayOfFloat1);
/*      */       }
/*  671 */       for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
/*      */       {
/*  673 */         paramArrayOfFloat1 = VectorUtil.getCoord(this.pb, paramVector3f1) + VectorUtil.getCoord(this.ub, paramVector3f1) * paramInt3;
/*  674 */         VectorUtil.setCoord(this.pb, paramVector3f1, paramArrayOfFloat1);
/*      */       }
/*      */ 
/*  687 */       this.negNormal.set(paramVector3f5);
/*  688 */       this.negNormal.negate();
/*  689 */       paramManifoldResult.addContactPoint(this.negNormal, this.pb, -paramFloat.floatValue());
/*      */ 
/*  693 */       return 1;
/*      */     }
/*      */ 
/*  706 */     if (this.cb.code <= 3)
/*      */     {
/*  708 */       paramInt1 = paramArrayOfFloat1;
/*  709 */       paramInt3 = paramArrayOfFloat2;
/*  710 */       this.ppa.set(paramVector3f1);
/*  711 */       this.ppb.set(paramVector3f3);
/*  712 */       this.Sa.set(paramVector3f2);
/*  713 */       this.Sb.set(paramVector3f4);
/*      */     }
/*      */     else
/*      */     {
/*  717 */       paramInt1 = paramArrayOfFloat2;
/*  718 */       paramInt3 = paramArrayOfFloat1;
/*  719 */       this.ppa.set(paramVector3f3);
/*  720 */       this.ppb.set(paramVector3f1);
/*  721 */       this.Sa.set(paramVector3f4);
/*  722 */       this.Sb.set(paramVector3f2);
/*      */     }
/*      */ 
/*  727 */     this.nr.set(0.0F, 0.0F, 0.0F);
/*      */ 
/*  731 */     if (this.cb.code <= 3)
/*      */     {
/*  733 */       this.normal2.set(paramVector3f5);
/*      */     }
/*      */     else
/*      */     {
/*  737 */       this.normal2.set(paramVector3f5);
/*  738 */       this.normal2.negate();
/*      */     }
/*  740 */     DMULTIPLY1_331(this.nr, paramInt3, this.normal2);
/*      */ 
/*  743 */     this.anr.absolute(this.nr);
/*      */ 
/*  749 */     if (this.anr.y > this.anr.x)
/*      */     {
/*  751 */       if (this.anr.y > this.anr.z)
/*      */       {
/*  753 */         paramArrayOfFloat1 = 0;
/*  754 */         paramVector3f1 = 1;
/*  755 */         paramVector3f2 = 2;
/*      */       }
/*      */       else
/*      */       {
/*  759 */         paramArrayOfFloat1 = 0;
/*  760 */         paramVector3f2 = 1;
/*  761 */         paramVector3f1 = 2;
/*      */       }
/*      */ 
/*      */     }
/*  766 */     else if (this.anr.x > this.anr.z)
/*      */     {
/*  768 */       paramVector3f1 = 0;
/*  769 */       paramArrayOfFloat1 = 1;
/*  770 */       paramVector3f2 = 2;
/*      */     }
/*      */     else
/*      */     {
/*  774 */       paramArrayOfFloat1 = 0;
/*  775 */       paramVector3f2 = 1;
/*  776 */       paramVector3f1 = 2;
/*      */     }
/*      */ 
/*  782 */     if (VectorUtil.getCoord(this.nr, paramVector3f1) < 0.0F)
/*      */     {
/*  784 */       for (paramVector3f3 = 0; paramVector3f3 < 3; paramVector3f3++)
/*      */       {
/*  786 */         VectorUtil.setCoord(this.center, paramVector3f3, VectorUtil.getCoord(this.ppb, paramVector3f3) - VectorUtil.getCoord(this.ppa, paramVector3f3) + VectorUtil.getCoord(this.Sb, paramVector3f1) * paramInt3[((paramVector3f3 << 2) + paramVector3f1)]);
/*      */       }
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  793 */       for (paramVector3f3 = 0; paramVector3f3 < 3; paramVector3f3++)
/*      */       {
/*  795 */         VectorUtil.setCoord(this.center, paramVector3f3, VectorUtil.getCoord(this.ppb, paramVector3f3) - VectorUtil.getCoord(this.ppa, paramVector3f3) - VectorUtil.getCoord(this.Sb, paramVector3f1) * paramInt3[((paramVector3f3 << 2) + paramVector3f1)]);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  804 */     if (this.cb.code <= 3)
/*      */     {
/*  806 */       paramVector3f3 = this.cb.code - 1;
/*      */     }
/*      */     else
/*      */     {
/*  810 */       paramVector3f3 = this.cb.code - 4;
/*      */     }
/*  812 */     if (paramVector3f3 == 0)
/*      */     {
/*  814 */       paramVector3f1 = 1;
/*  815 */       paramArrayOfFloat2 = 2;
/*      */     }
/*  817 */     else if (paramVector3f3 == 1)
/*      */     {
/*  819 */       paramVector3f1 = 0;
/*  820 */       paramArrayOfFloat2 = 2;
/*      */     }
/*      */     else
/*      */     {
/*  824 */       paramVector3f1 = 0;
/*  825 */       paramArrayOfFloat2 = 1;
/*      */     }
/*      */ 
/*  829 */     paramVector3f4 = this.s_quad;
/*      */ 
/*  832 */     paramFloat = DDOT14(this.center, 0, paramInt1, paramVector3f1);
/*  833 */     f1 = DDOT14(this.center, 0, paramInt1, paramArrayOfFloat2);
/*      */ 
/*  838 */     f2 = DDOT44(paramInt1, paramVector3f1, paramInt3, paramArrayOfFloat1);
/*  839 */     f3 = DDOT44(paramInt1, paramVector3f1, paramInt3, paramVector3f2);
/*  840 */     f4 = DDOT44(paramInt1, paramArrayOfFloat2, paramInt3, paramArrayOfFloat1);
/*  841 */     paramInt1 = DDOT44(paramInt1, paramArrayOfFloat2, paramInt3, paramVector3f2);
/*      */ 
/*  843 */     f5 = f2 * VectorUtil.getCoord(this.Sb, paramArrayOfFloat1);
/*  844 */     f6 = f4 * VectorUtil.getCoord(this.Sb, paramArrayOfFloat1);
/*  845 */     f7 = f3 * VectorUtil.getCoord(this.Sb, paramVector3f2);
/*  846 */     f8 = paramInt1 * VectorUtil.getCoord(this.Sb, paramVector3f2);
/*  847 */     paramVector3f4[0] = (paramFloat - f5 - f7);
/*  848 */     paramVector3f4[1] = (f1 - f6 - f8);
/*  849 */     paramVector3f4[2] = (paramFloat - f5 + f7);
/*  850 */     paramVector3f4[3] = (f1 - f6 + f8);
/*  851 */     paramVector3f4[4] = (paramFloat + f5 + f7);
/*  852 */     paramVector3f4[5] = (f1 + f6 + f8);
/*  853 */     paramVector3f4[6] = (paramFloat + f5 - f7);
/*  854 */     paramVector3f4[7] = (f1 + f6 - f8);
/*      */ 
/*  859 */     this.s_rectReferenceFace[0] = VectorUtil.getCoord(this.Sa, paramVector3f1);
/*  860 */     this.s_rectReferenceFace[1] = VectorUtil.getCoord(this.Sa, paramArrayOfFloat2);
/*      */ 
/*  863 */     float[] arrayOfFloat1 = this.s_ret;
/*      */     float[] arrayOfFloat2;
/*  865 */     if ((
/*  865 */       arrayOfFloat2 = IntersectRectQuad2(this.s_rectReferenceFace, paramVector3f4, arrayOfFloat1)) <= 0)
/*      */     {
/*  867 */       return 0;
/*      */     }
/*      */ 
/*  874 */     float[] arrayOfFloat3 = this.s_point;
/*  875 */     float[] arrayOfFloat4 = this.s_dep;
/*  876 */     paramVector3f1 = 1.0F / (f2 * paramInt1 - f3 * f4);
/*  877 */     f2 *= paramVector3f1;
/*  878 */     f3 *= paramVector3f1;
/*  879 */     f4 *= paramVector3f1;
/*  880 */     paramInt1 *= paramVector3f1;
/*  881 */     paramVector3f1 = 0;
/*      */     int j;
/*  882 */     for (paramArrayOfFloat2 = 0; paramArrayOfFloat2 < arrayOfFloat2; paramArrayOfFloat2++)
/*      */     {
/*  884 */       paramVector3f4 = paramInt1 * (arrayOfFloat1[(paramArrayOfFloat2 << 1)] - paramFloat) - f3 * (arrayOfFloat1[((paramArrayOfFloat2 << 1) + 1)] - f1);
/*  885 */       f9 = -f4 * (arrayOfFloat1[(paramArrayOfFloat2 << 1)] - paramFloat) + f2 * (arrayOfFloat1[((paramArrayOfFloat2 << 1) + 1)] - f1);
/*  886 */       for (j = 0; j < 3; j++)
/*      */       {
/*  888 */         arrayOfFloat3[(paramVector3f1 * 3 + j)] = (VectorUtil.getCoord(this.center, j) + paramVector3f4 * paramInt3[((j << 2) + paramArrayOfFloat1)] + f9 * paramInt3[((j << 2) + paramVector3f2)]);
/*      */       }
/*  890 */       arrayOfFloat4[paramVector3f1] = (VectorUtil.getCoord(this.Sa, paramVector3f3) - DDOT(this.normal2, 0, arrayOfFloat3, paramVector3f1 * 3));
/*  891 */       if (arrayOfFloat4[paramVector3f1] >= 0.0F)
/*      */       {
/*  893 */         arrayOfFloat1[(paramVector3f1 << 1)] = arrayOfFloat1[(paramArrayOfFloat2 << 1)];
/*  894 */         arrayOfFloat1[((paramVector3f1 << 1) + 1)] = arrayOfFloat1[((paramArrayOfFloat2 << 1) + 1)];
/*  895 */         paramVector3f1++;
/*      */       }
/*      */     }
/*  898 */     if (paramVector3f1 <= 0)
/*      */     {
/*  900 */       return 0;
/*      */     }
/*      */ 
/*  904 */     if (paramInt2 > paramVector3f1)
/*      */     {
/*  906 */       paramInt2 = paramVector3f1;
/*      */     }
/*  908 */     if (paramInt2 <= 0)
/*      */     {
/*  910 */       paramInt2 = 1;
/*      */     }
/*      */ 
/*  913 */     if (paramVector3f1 <= paramInt2)
/*      */     {
/*  915 */       if (this.cb.code < 4)
/*      */       {
/*  918 */         for (paramArrayOfFloat2 = 0; paramArrayOfFloat2 < paramVector3f1; paramArrayOfFloat2++)
/*      */         {
/*  921 */           for (paramVector3f4 = 0; paramVector3f4 < 3; paramVector3f4++)
/*      */           {
/*  923 */             VectorUtil.setCoord(this.pointInWorldFAA, paramVector3f4, arrayOfFloat3[(paramArrayOfFloat2 * 3 + paramVector3f4)] + VectorUtil.getCoord(this.ppa, paramVector3f4));
/*      */           }
/*      */ 
/*  929 */           this.negNormal.set(paramVector3f5);
/*  930 */           this.negNormal.negate();
/*  931 */           paramManifoldResult.addContactPoint(this.negNormal, this.pointInWorldFAA, -arrayOfFloat4[paramArrayOfFloat2]);
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  937 */         for (paramArrayOfFloat2 = 0; paramArrayOfFloat2 < paramVector3f1; paramArrayOfFloat2++)
/*      */         {
/*  940 */           for (paramVector3f4 = 0; paramVector3f4 < 3; paramVector3f4++)
/*      */           {
/*  942 */             VectorUtil.setCoord(this.pointInWorldRes, paramVector3f4, arrayOfFloat3[(paramArrayOfFloat2 * 3 + paramVector3f4)] + VectorUtil.getCoord(this.ppa, paramVector3f4));
/*      */           }
/*      */ 
/*  950 */           this.negNormal.set(paramVector3f5);
/*  951 */           this.negNormal.negate();
/*  952 */           paramManifoldResult.addContactPoint(this.negNormal, this.pointInWorldRes, -arrayOfFloat4[paramArrayOfFloat2]);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  960 */       paramArrayOfFloat2 = 0;
/*  961 */       paramVector3f4 = arrayOfFloat4[0];
/*  962 */       for (Vector3f localVector3f = 1; localVector3f < paramVector3f1; localVector3f++)
/*      */       {
/*  964 */         if (arrayOfFloat4[localVector3f] > paramVector3f4)
/*      */         {
/*  966 */           paramVector3f4 = arrayOfFloat4[localVector3f];
/*  967 */           paramArrayOfFloat2 = localVector3f;
/*      */         }
/*      */       }
/*  970 */       Arrays.fill(this.iret, 0);
/*      */ 
/*  972 */       CullPoints2(paramVector3f1, arrayOfFloat1, paramInt2, paramArrayOfFloat2, this.iret);
/*      */ 
/*  974 */       for (int i = 0; i < paramInt2; i++)
/*      */       {
/*  980 */         for (j = 0; j < 3; j++)
/*      */         {
/*  982 */           VectorUtil.setCoord(this.posInWorldFA, j, arrayOfFloat3[(this.iret[i] * 3 + j)] + VectorUtil.getCoord(this.ppa, j));
/*      */         }
/*      */ 
/*  990 */         this.negNormal.set(paramVector3f5);
/*  991 */         this.negNormal.negate();
/*      */ 
/*  993 */         if (this.cb.code < 4) {
/*  994 */           paramManifoldResult.addContactPoint(this.negNormal, this.posInWorldFA, -arrayOfFloat4[this.iret[i]]);
/*      */         }
/*      */         else {
/*  997 */           this.scaledN.set(paramVector3f5);
/*  998 */           this.scaledN.scale(arrayOfFloat4[this.iret[i]]);
/*  999 */           this.posInWorldFA.sub(this.scaledN);
/* 1000 */           paramManifoldResult.addContactPoint(this.negNormal, this.posInWorldFA, -arrayOfFloat4[this.iret[i]]);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1005 */       paramVector3f1 = paramInt2;
/*      */     }
/*      */ 
/* 1008 */     return paramVector3f1;
/*      */   }
/*      */ 
/*      */   int IntersectRectQuad2(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3)
/*      */   {
/* 1016 */     int i = 4; int j = 0;
/*      */ 
/* 1018 */     float[] arrayOfFloat1 = this.s_quadBuffer;
/* 1019 */     float[] arrayOfFloat2 = paramArrayOfFloat3;
/*      */ 
/* 1023 */     for (int k = 0; k <= 1; k++)
/*      */     {
/* 1026 */       for (int m = -1; m <= 1; m += 2)
/*      */       {
/* 1029 */         float[] arrayOfFloat3 = paramArrayOfFloat2;
/* 1030 */         float[] arrayOfFloat4 = arrayOfFloat2;
/* 1031 */         int n = 0;
/* 1032 */         int i1 = 0;
/* 1033 */         j = 0;
/* 1034 */         for (; i > 0; i--)
/*      */         {
/* 1037 */           if (m * arrayOfFloat3[(n + k)] < paramArrayOfFloat1[k])
/*      */           {
/* 1040 */             arrayOfFloat4[i1] = arrayOfFloat3[n];
/* 1041 */             arrayOfFloat4[(i1 + 1)] = arrayOfFloat3[(n + 1)];
/* 1042 */             i1 += 2;
/* 1043 */             j++;
/* 1044 */             if ((j & 0x8) != 0)
/*      */             {
/* 1046 */               paramArrayOfFloat2 = arrayOfFloat2;
/* 1047 */               break label356;
/*      */             }
/*      */           }
/*      */           float f1;
/*      */           float f2;
/* 1050 */           if (i > 1)
/*      */           {
/* 1054 */             f1 = arrayOfFloat3[(n + 2 + k)];
/* 1055 */             f2 = arrayOfFloat3[(n + 2 + (1 - k))];
/*      */           }
/*      */           else
/*      */           {
/* 1059 */             f1 = paramArrayOfFloat2[k];
/* 1060 */             f2 = paramArrayOfFloat2[(1 - k)];
/*      */           }
/*      */ 
/* 1063 */           if (((m * arrayOfFloat3[(n + k)] < paramArrayOfFloat1[k] ? 1 : 0) ^ (m * f1 < paramArrayOfFloat1[k] ? 1 : 0)) != 0)
/*      */           {
/* 1066 */             arrayOfFloat3[(n + (1 - k))] += (f2 - arrayOfFloat3[(n + (1 - k))]) / (f1 - arrayOfFloat3[(n + k)]) * (m * paramArrayOfFloat1[k] - arrayOfFloat3[(n + k)]);
/*      */ 
/* 1068 */             arrayOfFloat4[(i1 + k)] = (m * paramArrayOfFloat1[k]);
/* 1069 */             i1 += 2;
/* 1070 */             j++;
/* 1071 */             if ((j & 0x8) != 0)
/*      */             {
/* 1073 */               paramArrayOfFloat2 = arrayOfFloat2;
/* 1074 */               break label356;
/*      */             }
/*      */           }
/* 1077 */           n += 2;
/*      */         }
/*      */ 
/* 1080 */         arrayOfFloat2 = (
/* 1080 */           paramArrayOfFloat2 = arrayOfFloat2) == 
/* 1080 */           paramArrayOfFloat3 ? arrayOfFloat1 : paramArrayOfFloat3;
/* 1081 */         i = j;
/*      */       }
/*      */     }
/*      */ 
/* 1085 */     label356: if (paramArrayOfFloat2 != paramArrayOfFloat3)
/*      */     {
/* 1087 */       for (k = 0; k < j << 1; k++)
/*      */       {
/* 1089 */         paramArrayOfFloat3[k] = paramArrayOfFloat2[k];
/*      */       }
/*      */     }
/* 1092 */     return j;
/*      */   }
/*      */ 
/*      */   private boolean TST(float paramFloat1, float paramFloat2, float[] paramArrayOfFloat, int paramInt1, int paramInt2, BoxBoxDetector.CB paramCB)
/*      */   {
/* 1097 */     if ((
/* 1097 */       paramFloat2 = Math.abs(paramFloat1) - paramFloat2) > 
/* 1097 */       0.0F)
/*      */     {
/* 1099 */       return true;
/*      */     }
/* 1101 */     if (paramFloat2 > paramCB.s)
/*      */     {
/* 1103 */       paramCB.s = paramFloat2;
/* 1104 */       paramCB.normalR = paramArrayOfFloat;
/*      */ 
/* 1109 */       paramCB.normalROffset = paramInt1;
/* 1110 */       paramCB.invert_normal = (paramFloat1 < 0.0F);
/* 1111 */       paramCB.code = paramInt2;
/*      */     }
/* 1113 */     return false;
/*      */   }
/*      */ 
/*      */   private boolean TST2(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, Vector3f paramVector3f, int paramInt, BoxBoxDetector.CB paramCB)
/*      */   {
/* 1120 */     if ((
/* 1120 */       paramFloat2 = Math.abs(paramFloat1) - paramFloat2) > 
/* 1120 */       1.192093E-007F)
/*      */     {
/* 1122 */       return true;
/*      */     }
/*      */     float f;
/* 1125 */     if ((
/* 1125 */       f = (float)Math.sqrt(paramFloat3 * paramFloat3 + paramFloat4 * paramFloat4 + paramFloat5 * paramFloat5)) > 
/* 1125 */       1.192093E-007F)
/*      */     {
/* 1128 */       if ((
/* 1128 */         paramFloat2 = paramFloat2 / f) * 
/* 1128 */         this.fudge_factor > paramCB.s)
/*      */       {
/* 1130 */         paramCB.s = paramFloat2;
/* 1131 */         paramCB.normalR = null;
/* 1132 */         paramVector3f.x = (paramFloat3 / f); paramVector3f.y = (paramFloat4 / f); paramVector3f.z = (paramFloat5 / f);
/* 1133 */         paramCB.invert_normal = (paramFloat1 < 0.0F);
/* 1134 */         paramCB.code = paramInt;
/*      */       }
/*      */     }
/* 1137 */     return false;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.BoxBoxDetector
 * JD-Core Version:    0.6.2
 */