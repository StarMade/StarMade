package org.schema.game.common.data.physics;

import com.bulletphysics.collision.dispatch.ManifoldResult;
import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import java.util.Arrays;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;

public class BoxBoxDetector
{
  private Vector3f distPoint = new Vector3f();
  private Vector3f field_2081 = new Vector3f();
  private Vector3f normalC = new Vector3f();
  private Vector3f field_2082 = new Vector3f();
  private Vector3f field_2083 = new Vector3f();
  private Vector3f field_2084 = new Vector3f();
  private Vector3f field_2085 = new Vector3f();
  private Vector2f alphaBeta = new Vector2f();
  private Vector3f negNormal = new Vector3f();
  private Vector3f field_2086 = new Vector3f();
  private Vector3f normal2 = new Vector3f();
  private Vector3f anr = new Vector3f();
  private Vector3f ppa = new Vector3f();
  private Vector3f ppb = new Vector3f();
  private Vector3f field_2087 = new Vector3f();
  private Vector3f field_2088 = new Vector3f();
  private int[] iret = new int[8];
  private Vector3f pointInWorldFAA = new Vector3f();
  private Vector3f posInWorldFA = new Vector3f();
  private Vector3f center = new Vector3f();
  private Vector3f pointInWorldRes = new Vector3f();
  private Vector3f scaledN = new Vector3f();
  private BoxBoxDetector.CB field_2089 = new BoxBoxDetector.CB(this, null);
  private float fudge_factor = 1.05F;
  private float[] s_quadBuffer = new float[16];
  private float[] s_temp1 = new float[12];
  private float[] s_temp2 = new float[12];
  private float[] s_quad = new float[8];
  private float[] s_ret = new float[16];
  private float[] s_point = new float[24];
  private float[] s_dep = new float[8];
  private float[] s_A = new float[8];
  private float[] s_rectReferenceFace = new float[2];
  private int[] s_availablePoints = new int[8];
  private Vector3f normal = new Vector3f();
  private Vector3f translationA = new Vector3f();
  private Vector3f translationB = new Vector3f();
  private Vector3f box1Margin = new Vector3f();
  private Vector3f box2Margin = new Vector3f();
  private Vector3f box1MarginCache = new Vector3f();
  private Vector3f box2MarginCache = new Vector3f();
  private Vector3f rowA = new Vector3f();
  private Vector3f rowB = new Vector3f();
  private Transform transformA = new Transform();
  private Transform transformB = new Transform();
  private Vector3f pTmp = new Vector3f();
  public static final boolean USE_CENTER_POINT = false;
  private Float depth;
  private boolean cachedBoxSize;
  public int contacts;
  public float maxDepth;
  
  private static float DDOTSpec(float[] paramArrayOfFloat, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return paramArrayOfFloat[paramInt] * paramFloat1 + paramArrayOfFloat[(paramInt + 1)] * paramFloat2 + paramArrayOfFloat[(paramInt + 2)] * paramFloat3;
  }
  
  private static float DDOT(Vector3f paramVector3f, int paramInt1, float[] paramArrayOfFloat, int paramInt2)
  {
    return VectorUtil.getCoord(paramVector3f, paramInt1) * paramArrayOfFloat[paramInt2] + VectorUtil.getCoord(paramVector3f, paramInt1 + 1) * paramArrayOfFloat[(paramInt2 + 1)] + VectorUtil.getCoord(paramVector3f, paramInt1 + 2) * paramArrayOfFloat[(paramInt2 + 2)];
  }
  
  private static float DDOT14(Vector3f paramVector3f, int paramInt1, float[] paramArrayOfFloat, int paramInt2)
  {
    return VectorUtil.getCoord(paramVector3f, paramInt1) * paramArrayOfFloat[paramInt2] + VectorUtil.getCoord(paramVector3f, paramInt1 + 1) * paramArrayOfFloat[(paramInt2 + 4)] + VectorUtil.getCoord(paramVector3f, paramInt1 + 2) * paramArrayOfFloat[(paramInt2 + 8)];
  }
  
  private static float DDOT41(float[] paramArrayOfFloat, int paramInt1, Vector3f paramVector3f, int paramInt2)
  {
    return paramArrayOfFloat[paramInt1] * VectorUtil.getCoord(paramVector3f, paramInt2) + paramArrayOfFloat[(paramInt1 + 4)] * VectorUtil.getCoord(paramVector3f, paramInt2 + 1) + paramArrayOfFloat[(paramInt1 + 8)] * VectorUtil.getCoord(paramVector3f, paramInt2 + 2);
  }
  
  private static float DDOT41Spec(float[] paramArrayOfFloat, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return paramArrayOfFloat[paramInt] * paramFloat1 + paramArrayOfFloat[(paramInt + 4)] * paramFloat2 + paramArrayOfFloat[(paramInt + 8)] * paramFloat3;
  }
  
  private static float DDOT44(float[] paramArrayOfFloat1, int paramInt1, float[] paramArrayOfFloat2, int paramInt2)
  {
    return paramArrayOfFloat1[paramInt1] * paramArrayOfFloat2[paramInt2] + paramArrayOfFloat1[(paramInt1 + 4)] * paramArrayOfFloat2[(paramInt2 + 4)] + paramArrayOfFloat1[(paramInt1 + 8)] * paramArrayOfFloat2[(paramInt2 + 8)];
  }
  
  private static void DLineClosestApproach(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector2f paramVector2f, Vector3f paramVector3f5)
  {
    paramVector3f5.sub(paramVector3f3, paramVector3f1);
    paramVector3f1 = paramVector3f2.dot(paramVector3f4);
    paramVector3f2 = paramVector3f2.dot(paramVector3f5);
    paramVector3f3 = -paramVector3f4.dot(paramVector3f5);
    if ((paramVector3f4 = 1.0F - paramVector3f1 * paramVector3f1) <= 1.0E-004F)
    {
      paramVector2f.field_577 = 0.0F;
      paramVector2f.field_578 = 0.0F;
      return;
    }
    paramVector3f4 = 1.0F / paramVector3f4;
    paramVector2f.field_577 = ((paramVector3f2 + paramVector3f1 * paramVector3f3) * paramVector3f4);
    paramVector2f.field_578 = ((paramVector3f1 * paramVector3f2 + paramVector3f3) * paramVector3f4);
  }
  
  public static void DMULTIPLY0_331(Vector3f paramVector3f1, float[] paramArrayOfFloat, Vector3f paramVector3f2)
  {
    paramVector3f1.field_615 = DDOTSpec(paramArrayOfFloat, 0, paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    paramVector3f1.field_616 = DDOTSpec(paramArrayOfFloat, 4, paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    paramVector3f1.field_617 = DDOTSpec(paramArrayOfFloat, 8, paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
  }
  
  public static void DMULTIPLY1_331(Vector3f paramVector3f1, float[] paramArrayOfFloat, Vector3f paramVector3f2)
  {
    paramVector3f1.field_615 = DDOT41Spec(paramArrayOfFloat, 0, paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    paramVector3f1.field_616 = DDOT41Spec(paramArrayOfFloat, 1, paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    paramVector3f1.field_617 = DDOT41Spec(paramArrayOfFloat, 2, paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
  }
  
  private void CullPoints2(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3, int[] paramArrayOfInt)
  {
    int i = 0;
    float f2;
    float f3;
    float f1;
    if (paramInt1 == 1)
    {
      f2 = paramArrayOfFloat[0];
      f3 = paramArrayOfFloat[1];
    }
    else if (paramInt1 == 2)
    {
      f2 = 0.5F * (paramArrayOfFloat[0] + paramArrayOfFloat[2]);
      f3 = 0.5F * (paramArrayOfFloat[1] + paramArrayOfFloat[3]);
    }
    else
    {
      f1 = 0.0F;
      f2 = 0.0F;
      f3 = 0.0F;
      for (j = 0; j < paramInt1 - 1; j++)
      {
        f4 = paramArrayOfFloat[(j << 1)] * paramArrayOfFloat[((j << 1) + 3)] - paramArrayOfFloat[((j << 1) + 2)] * paramArrayOfFloat[((j << 1) + 1)];
        f1 += f4;
        f2 += f4 * (paramArrayOfFloat[(j << 1)] + paramArrayOfFloat[((j << 1) + 2)]);
        f3 += f4 * (paramArrayOfFloat[((j << 1) + 1)] + paramArrayOfFloat[((j << 1) + 3)]);
      }
      float f4 = paramArrayOfFloat[((paramInt1 << 1) - 2)] * paramArrayOfFloat[1] - paramArrayOfFloat[0] * paramArrayOfFloat[((paramInt1 << 1) - 1)];
      if (Math.abs(f1 + f4) > 1.192093E-007F) {
        f1 = 1.0F / (3.0F * (f1 + f4));
      } else {
        f1 = 1.0E+030F;
      }
      f2 = f1 * (f2 + f4 * (paramArrayOfFloat[((paramInt1 << 1) - 2)] + paramArrayOfFloat[0]));
      f3 = f1 * (f3 + f4 * (paramArrayOfFloat[((paramInt1 << 1) - 1)] + paramArrayOfFloat[1]));
    }
    float[] arrayOfFloat = this.s_A;
    for (int j = 0; j < paramInt1; j++) {
      arrayOfFloat[j] = FastMath.a3(paramArrayOfFloat[((j << 1) + 1)] - f3, paramArrayOfFloat[(j << 1)] - f2);
    }
    for (j = 0; j < paramInt1; j++) {
      this.s_availablePoints[j] = 1;
    }
    this.s_availablePoints[paramInt3] = 0;
    paramArrayOfInt[0] = paramInt3;
    i++;
    for (paramArrayOfFloat = 1; paramArrayOfFloat < paramInt2; paramArrayOfFloat++)
    {
      if ((f1 = paramArrayOfFloat * (6.283186F / paramInt2) + arrayOfFloat[paramInt3]) > 3.141593F) {
        f1 -= 6.283186F;
      }
      int k = 1;
      f3 = 3.4028235E+38F;
      paramArrayOfInt[i] = paramInt3;
      for (j = 0; j < paramInt1; j++) {
        if (this.s_availablePoints[j] != 0)
        {
          float f5;
          if ((f5 = Math.abs(arrayOfFloat[j] - f1)) > 3.141593F) {
            f5 = 6.283186F - f5;
          }
          if ((k != 0) || (f5 < f3))
          {
            k = 0;
            f3 = f5;
            paramArrayOfInt[i] = j;
          }
        }
      }
      this.s_availablePoints[paramArrayOfInt[i]] = 0;
      i++;
    }
  }
  
  public void GetClosestPoints(BoxShape paramBoxShape1, BoxShape paramBoxShape2, DiscreteCollisionDetectorInterface.ClosestPointInput paramClosestPointInput, ManifoldResult paramManifoldResult, IDebugDraw paramIDebugDraw, boolean paramBoolean)
  {
    this.contacts = 0;
    this.maxDepth = 0.0F;
    this.transformA.set(paramClosestPointInput.transformA);
    this.transformB.set(paramClosestPointInput.transformB);
    this.normal.set(0.0F, 0.0F, 0.0F);
    this.depth = Float.valueOf(0.0F);
    this.translationA.set(this.transformA.origin);
    this.translationB.set(this.transformB.origin);
    if (!this.cachedBoxSize)
    {
      paramBoxShape1.getHalfExtentsWithMargin(this.box1MarginCache);
      paramBoxShape2.getHalfExtentsWithMargin(this.box2MarginCache);
      this.cachedBoxSize = true;
    }
    this.box1Margin.set(this.box1MarginCache);
    this.box2Margin.set(this.box2MarginCache);
    for (paramBoxShape1 = 0; paramBoxShape1 < 3; paramBoxShape1++)
    {
      this.transformA.basis.getRow(paramBoxShape1, this.rowA);
      this.transformB.basis.getRow(paramBoxShape1, this.rowB);
      this.s_temp1[(0 + 4 * paramBoxShape1)] = this.rowA.field_615;
      this.s_temp2[(0 + 4 * paramBoxShape1)] = this.rowB.field_615;
      this.s_temp1[(1 + 4 * paramBoxShape1)] = this.rowA.field_616;
      this.s_temp2[(1 + 4 * paramBoxShape1)] = this.rowB.field_616;
      this.s_temp1[(2 + 4 * paramBoxShape1)] = this.rowA.field_617;
      this.s_temp2[(2 + 4 * paramBoxShape1)] = this.rowB.field_617;
    }
    this.field_2089.reset();
    DBoxBox2(this.translationA, this.s_temp1, this.box1Margin, this.translationB, this.s_temp2, this.box2Margin, this.normal, this.depth, -1, 4, 0, paramManifoldResult);
  }
  
  private int DBoxBox2(Vector3f paramVector3f1, float[] paramArrayOfFloat1, Vector3f paramVector3f2, Vector3f paramVector3f3, float[] paramArrayOfFloat2, Vector3f paramVector3f4, Vector3f paramVector3f5, Float paramFloat, int paramInt1, int paramInt2, int paramInt3, ManifoldResult paramManifoldResult)
  {
    this.field_2089.normalR = null;
    this.distPoint.sub(paramVector3f3, paramVector3f1);
    this.field_2081.set(0.0F, 0.0F, 0.0F);
    DMULTIPLY1_331(this.field_2081, paramArrayOfFloat1, this.distPoint);
    paramFloat = DDOT44(paramArrayOfFloat1, 0, paramArrayOfFloat2, 0);
    paramInt1 = DDOT44(paramArrayOfFloat1, 0, paramArrayOfFloat2, 1);
    paramInt3 = DDOT44(paramArrayOfFloat1, 0, paramArrayOfFloat2, 2);
    float f1 = DDOT44(paramArrayOfFloat1, 1, paramArrayOfFloat2, 0);
    float f2 = DDOT44(paramArrayOfFloat1, 1, paramArrayOfFloat2, 1);
    float f3 = DDOT44(paramArrayOfFloat1, 1, paramArrayOfFloat2, 2);
    float f4 = DDOT44(paramArrayOfFloat1, 2, paramArrayOfFloat2, 0);
    float f5 = DDOT44(paramArrayOfFloat1, 2, paramArrayOfFloat2, 1);
    float f6 = DDOT44(paramArrayOfFloat1, 2, paramArrayOfFloat2, 2);
    float f7 = Math.abs(paramFloat);
    float f8 = Math.abs(paramInt1);
    float f9 = Math.abs(paramInt3);
    float f10 = Math.abs(f1);
    float f11 = Math.abs(f2);
    float f12 = Math.abs(f3);
    float f13 = Math.abs(f4);
    float f14 = Math.abs(f5);
    float f15 = Math.abs(f6);
    this.field_2089.invert_normal = false;
    this.field_2089.code = 0;
    if (TST(this.field_2081.field_615, paramVector3f2.field_615 + paramVector3f4.field_615 * f7 + paramVector3f4.field_616 * f8 + paramVector3f4.field_617 * f9, paramArrayOfFloat1, 0, 1, this.field_2089)) {
      return 0;
    }
    if (TST(this.field_2081.field_616, paramVector3f2.field_616 + paramVector3f4.field_615 * f10 + paramVector3f4.field_616 * f11 + paramVector3f4.field_617 * f12, paramArrayOfFloat1, 1, 2, this.field_2089)) {
      return 0;
    }
    if (TST(this.field_2081.field_617, paramVector3f2.field_617 + paramVector3f4.field_615 * f13 + paramVector3f4.field_616 * f14 + paramVector3f4.field_617 * f15, paramArrayOfFloat1, 2, 3, this.field_2089)) {
      return 0;
    }
    if (TST(DDOT41(paramArrayOfFloat2, 0, this.distPoint, 0), paramVector3f2.field_615 * f7 + paramVector3f2.field_616 * f10 + paramVector3f2.field_617 * f13 + paramVector3f4.field_615, paramArrayOfFloat2, 0, 4, this.field_2089)) {
      return 0;
    }
    if (TST(DDOT41(paramArrayOfFloat2, 1, this.distPoint, 0), paramVector3f2.field_615 * f8 + paramVector3f2.field_616 * f11 + paramVector3f2.field_617 * f14 + paramVector3f4.field_616, paramArrayOfFloat2, 1, 5, this.field_2089)) {
      return 0;
    }
    if (TST(DDOT41(paramArrayOfFloat2, 2, this.distPoint, 0), paramVector3f2.field_615 * f9 + paramVector3f2.field_616 * f12 + paramVector3f2.field_617 * f15 + paramVector3f4.field_617, paramArrayOfFloat2, 2, 6, this.field_2089)) {
      return 0;
    }
    this.normalC.set(0.0F, 0.0F, 0.0F);
    f7 += 1.0E-005F;
    f8 += 1.0E-005F;
    f9 += 1.0E-005F;
    f10 += 1.0E-005F;
    f11 += 1.0E-005F;
    f12 += 1.0E-005F;
    f13 += 1.0E-005F;
    f14 += 1.0E-005F;
    f15 += 1.0E-005F;
    if (TST2(this.field_2081.field_617 * f1 - this.field_2081.field_616 * f4, paramVector3f2.field_616 * f13 + paramVector3f2.field_617 * f10 + paramVector3f4.field_616 * f9 + paramVector3f4.field_617 * f8, 0.0F, -f4, f1, this.normalC, 7, this.field_2089)) {
      return 0;
    }
    if (TST2(this.field_2081.field_617 * f2 - this.field_2081.field_616 * f5, paramVector3f2.field_616 * f14 + paramVector3f2.field_617 * f11 + paramVector3f4.field_615 * f9 + paramVector3f4.field_617 * f7, 0.0F, -f5, f2, this.normalC, 8, this.field_2089)) {
      return 0;
    }
    if (TST2(this.field_2081.field_617 * f3 - this.field_2081.field_616 * f6, paramVector3f2.field_616 * f15 + paramVector3f2.field_617 * f12 + paramVector3f4.field_615 * f8 + paramVector3f4.field_616 * f7, 0.0F, -f6, f3, this.normalC, 9, this.field_2089)) {
      return 0;
    }
    if (TST2(this.field_2081.field_615 * f4 - this.field_2081.field_617 * paramFloat, paramVector3f2.field_615 * f13 + paramVector3f2.field_617 * f7 + paramVector3f4.field_616 * f12 + paramVector3f4.field_617 * f11, f4, 0.0F, -paramFloat, this.normalC, 10, this.field_2089)) {
      return 0;
    }
    if (TST2(this.field_2081.field_615 * f5 - this.field_2081.field_617 * paramInt1, paramVector3f2.field_615 * f14 + paramVector3f2.field_617 * f8 + paramVector3f4.field_615 * f12 + paramVector3f4.field_617 * f10, f5, 0.0F, -paramInt1, this.normalC, 11, this.field_2089)) {
      return 0;
    }
    if (TST2(this.field_2081.field_615 * f6 - this.field_2081.field_617 * paramInt3, paramVector3f2.field_615 * f15 + paramVector3f2.field_617 * f9 + paramVector3f4.field_615 * f11 + paramVector3f4.field_616 * f10, f6, 0.0F, -paramInt3, this.normalC, 12, this.field_2089)) {
      return 0;
    }
    if (TST2(this.field_2081.field_616 * paramFloat - this.field_2081.field_615 * f1, paramVector3f2.field_615 * f10 + paramVector3f2.field_616 * f7 + paramVector3f4.field_616 * f15 + paramVector3f4.field_617 * f14, -f1, paramFloat, 0.0F, this.normalC, 13, this.field_2089)) {
      return 0;
    }
    if (TST2(this.field_2081.field_616 * paramInt1 - this.field_2081.field_615 * f2, paramVector3f2.field_615 * f11 + paramVector3f2.field_616 * f8 + paramVector3f4.field_615 * f15 + paramVector3f4.field_617 * f13, -f2, paramInt1, 0.0F, this.normalC, 14, this.field_2089)) {
      return 0;
    }
    if (TST2(this.field_2081.field_616 * paramInt3 - this.field_2081.field_615 * f3, paramVector3f2.field_615 * f12 + paramVector3f2.field_616 * f9 + paramVector3f4.field_615 * f14 + paramVector3f4.field_616 * f13, -f3, paramInt3, 0.0F, this.normalC, 15, this.field_2089)) {
      return 0;
    }
    if (this.field_2089.code == 0) {
      return 0;
    }
    if (this.field_2089.normalR != null)
    {
      paramVector3f5.field_615 = this.field_2089.normalR[(0 + this.field_2089.normalROffset)];
      paramVector3f5.field_616 = this.field_2089.normalR[(4 + this.field_2089.normalROffset)];
      paramVector3f5.field_617 = this.field_2089.normalR[(8 + this.field_2089.normalROffset)];
      paramVector3f5.normalize();
    }
    else
    {
      DMULTIPLY0_331(paramVector3f5, paramArrayOfFloat1, this.normalC);
    }
    if (this.field_2089.invert_normal) {
      paramVector3f5.negate();
    }
    paramFloat = Float.valueOf(-this.field_2089.field_2075);
    if (this.field_2089.code > 6)
    {
      this.field_2082.set(paramVector3f1);
      for (paramInt1 = 0; paramInt1 < 3; paramInt1++)
      {
        paramInt3 = DDOT14(paramVector3f5, 0, paramArrayOfFloat1, paramInt1) > 0.0F ? 1.0F : -1.0F;
        for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++) {
          VectorUtil.setCoord(this.field_2082, paramVector3f1, VectorUtil.getCoord(this.field_2082, paramVector3f1) + paramInt3 * VectorUtil.getCoord(paramVector3f2, paramInt1) * paramArrayOfFloat1[((paramVector3f1 << 2) + paramInt1)]);
        }
      }
      this.field_2083.set(paramVector3f3);
      for (paramInt1 = 0; paramInt1 < 3; paramInt1++)
      {
        paramInt3 = DDOT14(paramVector3f5, 0, paramArrayOfFloat2, paramInt1) > 0.0F ? -1.0F : 1.0F;
        for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++) {
          VectorUtil.setCoord(this.field_2083, paramVector3f1, VectorUtil.getCoord(this.field_2083, paramVector3f1) + paramInt3 * VectorUtil.getCoord(paramVector3f4, paramInt1) * paramArrayOfFloat2[((paramVector3f1 << 2) + paramInt1)]);
        }
      }
      for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++) {
        VectorUtil.setCoord(this.field_2084, paramVector3f1, paramArrayOfFloat1[((this.field_2089.code - 7) / 3 + (paramVector3f1 << 2))]);
      }
      for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++) {
        VectorUtil.setCoord(this.field_2085, paramVector3f1, paramArrayOfFloat2[((this.field_2089.code - 7) % 3 + (paramVector3f1 << 2))]);
      }
      DLineClosestApproach(this.field_2082, this.field_2084, this.field_2083, this.field_2085, this.alphaBeta, this.pTmp);
      paramInt1 = this.alphaBeta.field_577;
      paramInt3 = this.alphaBeta.field_578;
      for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
      {
        paramArrayOfFloat1 = VectorUtil.getCoord(this.field_2082, paramVector3f1) + VectorUtil.getCoord(this.field_2084, paramVector3f1) * paramInt1;
        VectorUtil.setCoord(this.field_2082, paramVector3f1, paramArrayOfFloat1);
      }
      for (paramVector3f1 = 0; paramVector3f1 < 3; paramVector3f1++)
      {
        paramArrayOfFloat1 = VectorUtil.getCoord(this.field_2083, paramVector3f1) + VectorUtil.getCoord(this.field_2085, paramVector3f1) * paramInt3;
        VectorUtil.setCoord(this.field_2083, paramVector3f1, paramArrayOfFloat1);
      }
      this.negNormal.set(paramVector3f5);
      this.negNormal.negate();
      paramManifoldResult.addContactPoint(this.negNormal, this.field_2083, -paramFloat.floatValue());
      this.maxDepth = Math.max(paramFloat.floatValue(), this.maxDepth);
      this.contacts += 1;
      return 1;
    }
    if (this.field_2089.code <= 3)
    {
      paramInt1 = paramArrayOfFloat1;
      paramInt3 = paramArrayOfFloat2;
      this.ppa.set(paramVector3f1);
      this.ppb.set(paramVector3f3);
      this.field_2087.set(paramVector3f2);
      this.field_2088.set(paramVector3f4);
    }
    else
    {
      paramInt1 = paramArrayOfFloat2;
      paramInt3 = paramArrayOfFloat1;
      this.ppa.set(paramVector3f3);
      this.ppb.set(paramVector3f1);
      this.field_2087.set(paramVector3f4);
      this.field_2088.set(paramVector3f2);
    }
    this.field_2086.set(0.0F, 0.0F, 0.0F);
    if (this.field_2089.code <= 3)
    {
      this.normal2.set(paramVector3f5);
    }
    else
    {
      this.normal2.set(paramVector3f5);
      this.normal2.negate();
    }
    DMULTIPLY1_331(this.field_2086, paramInt3, this.normal2);
    this.anr.absolute(this.field_2086);
    if (this.anr.field_616 > this.anr.field_615)
    {
      if (this.anr.field_616 > this.anr.field_617)
      {
        paramArrayOfFloat1 = 0;
        paramVector3f1 = 1;
        paramVector3f2 = 2;
      }
      else
      {
        paramArrayOfFloat1 = 0;
        paramVector3f2 = 1;
        paramVector3f1 = 2;
      }
    }
    else if (this.anr.field_615 > this.anr.field_617)
    {
      paramVector3f1 = 0;
      paramArrayOfFloat1 = 1;
      paramVector3f2 = 2;
    }
    else
    {
      paramArrayOfFloat1 = 0;
      paramVector3f2 = 1;
      paramVector3f1 = 2;
    }
    if (VectorUtil.getCoord(this.field_2086, paramVector3f1) < 0.0F) {
      for (paramVector3f3 = 0; paramVector3f3 < 3; paramVector3f3++) {
        VectorUtil.setCoord(this.center, paramVector3f3, VectorUtil.getCoord(this.ppb, paramVector3f3) - VectorUtil.getCoord(this.ppa, paramVector3f3) + VectorUtil.getCoord(this.field_2088, paramVector3f1) * paramInt3[((paramVector3f3 << 2) + paramVector3f1)]);
      }
    } else {
      for (paramVector3f3 = 0; paramVector3f3 < 3; paramVector3f3++) {
        VectorUtil.setCoord(this.center, paramVector3f3, VectorUtil.getCoord(this.ppb, paramVector3f3) - VectorUtil.getCoord(this.ppa, paramVector3f3) - VectorUtil.getCoord(this.field_2088, paramVector3f1) * paramInt3[((paramVector3f3 << 2) + paramVector3f1)]);
      }
    }
    if (this.field_2089.code <= 3) {
      paramVector3f3 = this.field_2089.code - 1;
    } else {
      paramVector3f3 = this.field_2089.code - 4;
    }
    if (paramVector3f3 == 0)
    {
      paramVector3f1 = 1;
      paramArrayOfFloat2 = 2;
    }
    else if (paramVector3f3 == 1)
    {
      paramVector3f1 = 0;
      paramArrayOfFloat2 = 2;
    }
    else
    {
      paramVector3f1 = 0;
      paramArrayOfFloat2 = 1;
    }
    paramVector3f4 = this.s_quad;
    paramFloat = DDOT14(this.center, 0, paramInt1, paramVector3f1);
    f1 = DDOT14(this.center, 0, paramInt1, paramArrayOfFloat2);
    f2 = DDOT44(paramInt1, paramVector3f1, paramInt3, paramArrayOfFloat1);
    f3 = DDOT44(paramInt1, paramVector3f1, paramInt3, paramVector3f2);
    f4 = DDOT44(paramInt1, paramArrayOfFloat2, paramInt3, paramArrayOfFloat1);
    paramInt1 = DDOT44(paramInt1, paramArrayOfFloat2, paramInt3, paramVector3f2);
    f5 = f2 * VectorUtil.getCoord(this.field_2088, paramArrayOfFloat1);
    f6 = f4 * VectorUtil.getCoord(this.field_2088, paramArrayOfFloat1);
    f7 = f3 * VectorUtil.getCoord(this.field_2088, paramVector3f2);
    f8 = paramInt1 * VectorUtil.getCoord(this.field_2088, paramVector3f2);
    paramVector3f4[0] = (paramFloat - f5 - f7);
    paramVector3f4[1] = (f1 - f6 - f8);
    paramVector3f4[2] = (paramFloat - f5 + f7);
    paramVector3f4[3] = (f1 - f6 + f8);
    paramVector3f4[4] = (paramFloat + f5 + f7);
    paramVector3f4[5] = (f1 + f6 + f8);
    paramVector3f4[6] = (paramFloat + f5 - f7);
    paramVector3f4[7] = (f1 + f6 - f8);
    this.s_rectReferenceFace[0] = VectorUtil.getCoord(this.field_2087, paramVector3f1);
    this.s_rectReferenceFace[1] = VectorUtil.getCoord(this.field_2087, paramArrayOfFloat2);
    float[] arrayOfFloat1 = this.s_ret;
    float[] arrayOfFloat2;
    if ((arrayOfFloat2 = IntersectRectQuad2(this.s_rectReferenceFace, paramVector3f4, arrayOfFloat1)) <= 0) {
      return 0;
    }
    float[] arrayOfFloat3 = this.s_point;
    float[] arrayOfFloat4 = this.s_dep;
    paramVector3f1 = 1.0F / (f2 * paramInt1 - f3 * f4);
    f2 *= paramVector3f1;
    f3 *= paramVector3f1;
    f4 *= paramVector3f1;
    paramInt1 *= paramVector3f1;
    paramVector3f1 = 0;
    int j;
    for (paramArrayOfFloat2 = 0; paramArrayOfFloat2 < arrayOfFloat2; paramArrayOfFloat2++)
    {
      paramVector3f4 = paramInt1 * (arrayOfFloat1[(paramArrayOfFloat2 << 1)] - paramFloat) - f3 * (arrayOfFloat1[((paramArrayOfFloat2 << 1) + 1)] - f1);
      f9 = -f4 * (arrayOfFloat1[(paramArrayOfFloat2 << 1)] - paramFloat) + f2 * (arrayOfFloat1[((paramArrayOfFloat2 << 1) + 1)] - f1);
      for (j = 0; j < 3; j++) {
        arrayOfFloat3[(paramVector3f1 * 3 + j)] = (VectorUtil.getCoord(this.center, j) + paramVector3f4 * paramInt3[((j << 2) + paramArrayOfFloat1)] + f9 * paramInt3[((j << 2) + paramVector3f2)]);
      }
      arrayOfFloat4[paramVector3f1] = (VectorUtil.getCoord(this.field_2087, paramVector3f3) - DDOT(this.normal2, 0, arrayOfFloat3, paramVector3f1 * 3));
      if (arrayOfFloat4[paramVector3f1] >= 0.0F)
      {
        arrayOfFloat1[(paramVector3f1 << 1)] = arrayOfFloat1[(paramArrayOfFloat2 << 1)];
        arrayOfFloat1[((paramVector3f1 << 1) + 1)] = arrayOfFloat1[((paramArrayOfFloat2 << 1) + 1)];
        paramVector3f1++;
      }
    }
    if (paramVector3f1 <= 0) {
      return 0;
    }
    if (paramInt2 > paramVector3f1) {
      paramInt2 = paramVector3f1;
    }
    if (paramInt2 <= 0) {
      paramInt2 = 1;
    }
    if (paramVector3f1 <= paramInt2)
    {
      if (this.field_2089.code < 4) {
        for (paramArrayOfFloat2 = 0; paramArrayOfFloat2 < paramVector3f1; paramArrayOfFloat2++)
        {
          for (paramVector3f4 = 0; paramVector3f4 < 3; paramVector3f4++) {
            VectorUtil.setCoord(this.pointInWorldFAA, paramVector3f4, arrayOfFloat3[(paramArrayOfFloat2 * 3 + paramVector3f4)] + VectorUtil.getCoord(this.ppa, paramVector3f4));
          }
          this.negNormal.set(paramVector3f5);
          this.negNormal.negate();
          paramManifoldResult.addContactPoint(this.negNormal, this.pointInWorldFAA, -arrayOfFloat4[paramArrayOfFloat2]);
          this.maxDepth = Math.max(arrayOfFloat4[paramArrayOfFloat2], this.maxDepth);
          this.contacts += 1;
        }
      } else {
        for (paramArrayOfFloat2 = 0; paramArrayOfFloat2 < paramVector3f1; paramArrayOfFloat2++)
        {
          for (paramVector3f4 = 0; paramVector3f4 < 3; paramVector3f4++) {
            VectorUtil.setCoord(this.pointInWorldRes, paramVector3f4, arrayOfFloat3[(paramArrayOfFloat2 * 3 + paramVector3f4)] + VectorUtil.getCoord(this.ppa, paramVector3f4));
          }
          this.negNormal.set(paramVector3f5);
          this.negNormal.negate();
          paramManifoldResult.addContactPoint(this.negNormal, this.pointInWorldRes, -arrayOfFloat4[paramArrayOfFloat2]);
          this.contacts += 1;
          this.maxDepth = Math.max(arrayOfFloat4[paramArrayOfFloat2], this.maxDepth);
        }
      }
    }
    else
    {
      paramArrayOfFloat2 = 0;
      paramVector3f4 = arrayOfFloat4[0];
      for (Vector3f localVector3f = 1; localVector3f < paramVector3f1; localVector3f++) {
        if (arrayOfFloat4[localVector3f] > paramVector3f4)
        {
          paramVector3f4 = arrayOfFloat4[localVector3f];
          paramArrayOfFloat2 = localVector3f;
        }
      }
      Arrays.fill(this.iret, 0);
      CullPoints2(paramVector3f1, arrayOfFloat1, paramInt2, paramArrayOfFloat2, this.iret);
      for (int i = 0; i < paramInt2; i++)
      {
        for (j = 0; j < 3; j++) {
          VectorUtil.setCoord(this.posInWorldFA, j, arrayOfFloat3[(this.iret[i] * 3 + j)] + VectorUtil.getCoord(this.ppa, j));
        }
        this.negNormal.set(paramVector3f5);
        this.negNormal.negate();
        if (this.field_2089.code < 4)
        {
          paramManifoldResult.addContactPoint(this.negNormal, this.posInWorldFA, -arrayOfFloat4[this.iret[i]]);
          this.contacts += 1;
        }
        else
        {
          this.scaledN.set(paramVector3f5);
          this.scaledN.scale(arrayOfFloat4[this.iret[i]]);
          this.posInWorldFA.sub(this.scaledN);
          paramManifoldResult.addContactPoint(this.negNormal, this.posInWorldFA, -arrayOfFloat4[this.iret[i]]);
          this.contacts += 1;
        }
      }
      this.maxDepth = Math.max(paramVector3f4, this.maxDepth);
      paramVector3f1 = paramInt2;
    }
    return paramVector3f1;
  }
  
  int IntersectRectQuad2(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3)
  {
    int i = 4;
    int j = 0;
    float[] arrayOfFloat1 = this.s_quadBuffer;
    float[] arrayOfFloat2 = paramArrayOfFloat3;
    for (int k = 0; k <= 1; k++) {
      for (int m = -1; m <= 1; m += 2)
      {
        float[] arrayOfFloat3 = paramArrayOfFloat2;
        float[] arrayOfFloat4 = arrayOfFloat2;
        int n = 0;
        int i1 = 0;
        j = 0;
        while (i > 0)
        {
          if (m * arrayOfFloat3[(n + k)] < paramArrayOfFloat1[k])
          {
            arrayOfFloat4[i1] = arrayOfFloat3[n];
            arrayOfFloat4[(i1 + 1)] = arrayOfFloat3[(n + 1)];
            i1 += 2;
            j++;
            if ((j & 0x8) != 0)
            {
              paramArrayOfFloat2 = arrayOfFloat2;
              break label356;
            }
          }
          float f1;
          float f2;
          if (i > 1)
          {
            f1 = arrayOfFloat3[(n + 2 + k)];
            f2 = arrayOfFloat3[(n + 2 + (1 - k))];
          }
          else
          {
            f1 = paramArrayOfFloat2[k];
            f2 = paramArrayOfFloat2[(1 - k)];
          }
          if (((m * arrayOfFloat3[(n + k)] < paramArrayOfFloat1[k] ? 1 : 0) ^ (m * f1 < paramArrayOfFloat1[k] ? 1 : 0)) != 0)
          {
            arrayOfFloat3[(n + (1 - k))] += (f2 - arrayOfFloat3[(n + (1 - k))]) / (f1 - arrayOfFloat3[(n + k)]) * (m * paramArrayOfFloat1[k] - arrayOfFloat3[(n + k)]);
            arrayOfFloat4[(i1 + k)] = (m * paramArrayOfFloat1[k]);
            i1 += 2;
            j++;
            if ((j & 0x8) != 0)
            {
              paramArrayOfFloat2 = arrayOfFloat2;
              break label356;
            }
          }
          n += 2;
          i--;
        }
        arrayOfFloat2 = (paramArrayOfFloat2 = arrayOfFloat2) == paramArrayOfFloat3 ? arrayOfFloat1 : paramArrayOfFloat3;
        i = j;
      }
    }
    label356:
    if (paramArrayOfFloat2 != paramArrayOfFloat3) {
      for (k = 0; k < j << 1; k++) {
        paramArrayOfFloat3[k] = paramArrayOfFloat2[k];
      }
    }
    return j;
  }
  
  private boolean TST(float paramFloat1, float paramFloat2, float[] paramArrayOfFloat, int paramInt1, int paramInt2, BoxBoxDetector.CB paramCB)
  {
    if ((paramFloat2 = Math.abs(paramFloat1) - paramFloat2) > 0.0F) {
      return true;
    }
    if (paramFloat2 > paramCB.field_2075)
    {
      paramCB.field_2075 = paramFloat2;
      paramCB.normalR = paramArrayOfFloat;
      paramCB.normalROffset = paramInt1;
      paramCB.invert_normal = (paramFloat1 < 0.0F);
      paramCB.code = paramInt2;
    }
    return false;
  }
  
  private boolean TST2(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, Vector3f paramVector3f, int paramInt, BoxBoxDetector.CB paramCB)
  {
    if ((paramFloat2 = Math.abs(paramFloat1) - paramFloat2) > 1.192093E-007F) {
      return true;
    }
    float f;
    if (((f = (float)Math.sqrt(paramFloat3 * paramFloat3 + paramFloat4 * paramFloat4 + paramFloat5 * paramFloat5)) > 1.192093E-007F) && (paramFloat2 /= f * this.fudge_factor > paramCB.field_2075))
    {
      paramCB.field_2075 = paramFloat2;
      paramCB.normalR = null;
      paramVector3f.field_615 = (paramFloat3 / f);
      paramVector3f.field_616 = (paramFloat4 / f);
      paramVector3f.field_617 = (paramFloat5 / f);
      paramCB.invert_normal = (paramFloat1 < 0.0F);
      paramCB.code = paramInt;
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.BoxBoxDetector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */