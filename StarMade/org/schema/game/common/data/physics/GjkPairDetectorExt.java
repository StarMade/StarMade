package org.schema.game.common.data.physics;

import com.bulletphysics.BulletStats;
import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface;
import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.Result;
import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class GjkPairDetectorExt
  extends DiscreteCollisionDetectorInterface
{
  private static final float REL_ERROR2 = 1.0E-006F;
  private final Vector3f cachedSeparatingAxis = new Vector3f();
  private ConvexPenetrationDepthSolver penetrationDepthSolver;
  private SimplexSolverInterface simplexSolver;
  private ConvexShape minkowskiA;
  private ConvexShape minkowskiB;
  private boolean ignoreMargin;
  public int contacts;
  public int lastUsedMethod;
  public int curIter;
  public int degenerateSimplex;
  public int catchDegeneracies;
  private GjkPairDetectorVariables field_88;
  public float maxDepth;
  
  public GjkPairDetectorExt(GjkPairDetectorVariables paramGjkPairDetectorVariables)
  {
    this.field_88 = paramGjkPairDetectorVariables;
  }
  
  public void init(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver)
  {
    this.cachedSeparatingAxis.set(0.0F, 0.0F, 1.0F);
    this.ignoreMargin = false;
    this.lastUsedMethod = -1;
    this.catchDegeneracies = 1;
    this.penetrationDepthSolver = paramConvexPenetrationDepthSolver;
    this.simplexSolver = paramSimplexSolverInterface;
    this.minkowskiA = paramConvexShape1;
    this.minkowskiB = paramConvexShape2;
  }
  
  public void getClosestPoints(DiscreteCollisionDetectorInterface.ClosestPointInput paramClosestPointInput, DiscreteCollisionDetectorInterface.Result paramResult, IDebugDraw paramIDebugDraw, boolean paramBoolean)
  {
    this.contacts = 0;
    this.maxDepth = 0.0F;
    paramBoolean = this.field_88.tmp;
    float f1 = 0.0F;
    Vector3f localVector3f1;
    (localVector3f1 = this.field_88.normalInB).set(0.0F, 0.0F, 0.0F);
    Vector3f localVector3f2 = this.field_88.pointOnA;
    Vector3f localVector3f3 = this.field_88.pointOnB;
    Transform localTransform1 = this.field_88.localTransA;
    Transform localTransform2 = this.field_88.localTransB;
    localTransform1.set(paramClosestPointInput.transformA);
    localTransform2.set(paramClosestPointInput.transformB);
    Vector3f localVector3f4;
    (localVector3f4 = this.field_88.positionOffset).add(localTransform1.origin, localTransform2.origin);
    localVector3f4.scale(0.5F);
    localTransform1.origin.sub(localVector3f4);
    localTransform2.origin.sub(localVector3f4);
    float f2 = this.minkowskiA.getMargin();
    float f3 = this.minkowskiB.getMargin();
    BulletStats.gNumGjkChecks += 1;
    if (this.ignoreMargin)
    {
      f2 = 0.0F;
      f3 = 0.0F;
    }
    this.curIter = 0;
    this.cachedSeparatingAxis.set(0.0F, 1.0F, 0.0F);
    int i = 0;
    int j = 0;
    int k = 1;
    this.degenerateSimplex = 0;
    this.lastUsedMethod = -1;
    float f4 = 3.4028235E+38F;
    float f7 = f2 + f3;
    this.simplexSolver.reset();
    Vector3f localVector3f5 = this.field_88.seperatingAxisInA;
    Vector3f localVector3f6 = this.field_88.seperatingAxisInB;
    Vector3f localVector3f7 = this.field_88.pInA;
    Vector3f localVector3f8 = this.field_88.qInB;
    Vector3f localVector3f9 = this.field_88.pWorld;
    Vector3f localVector3f10 = this.field_88.qWorld;
    Vector3f localVector3f11 = this.field_88.field_483;
    Vector3f localVector3f12 = this.field_88.tmpPointOnA;
    Vector3f localVector3f13 = this.field_88.tmpPointOnB;
    Vector3f localVector3f14 = this.field_88.tmpNormalInB;
    float f5;
    float f8;
    for (;;)
    {
      localVector3f5.negate(this.cachedSeparatingAxis);
      MatrixUtil.transposeTransform(localVector3f5, localVector3f5, paramClosestPointInput.transformA.basis);
      localVector3f6.set(this.cachedSeparatingAxis);
      MatrixUtil.transposeTransform(localVector3f6, localVector3f6, paramClosestPointInput.transformB.basis);
      this.minkowskiA.localGetSupportingVertexWithoutMargin(localVector3f5, localVector3f7);
      this.minkowskiB.localGetSupportingVertexWithoutMargin(localVector3f6, localVector3f8);
      localVector3f9.set(localVector3f7);
      localTransform1.transform(localVector3f9);
      localVector3f10.set(localVector3f8);
      localTransform2.transform(localVector3f10);
      localVector3f11.sub(localVector3f9, localVector3f10);
      if (((f5 = this.cachedSeparatingAxis.dot(localVector3f11)) > 0.0F) && (f5 * f5 > f4 * paramClosestPointInput.maximumDistanceSquared))
      {
        k = 0;
        break;
      }
      if (this.simplexSolver.inSimplex(localVector3f11))
      {
        this.degenerateSimplex = 1;
        j = 1;
        break;
      }
      f5 = f4 - f5;
      f8 = f4 * 1.0E-006F;
      if (f5 <= f8)
      {
        if (f5 <= 0.0F) {
          this.degenerateSimplex = 2;
        }
        j = 1;
        break;
      }
      this.simplexSolver.addVertex(localVector3f11, localVector3f9, localVector3f10);
      if (!this.simplexSolver.closest(this.cachedSeparatingAxis))
      {
        this.degenerateSimplex = 3;
        j = 1;
        break;
      }
      if (this.cachedSeparatingAxis.lengthSquared() < 1.0E-006F)
      {
        this.degenerateSimplex = 6;
        j = 1;
        break;
      }
      f5 = f4;
      f4 = this.cachedSeparatingAxis.lengthSquared();
      if (f5 - f4 <= 1.192093E-007F * f5)
      {
        this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
        j = 1;
        break;
      }
      if (this.curIter++ > 1000) {
        break;
      }
      if ((!this.simplexSolver.fullSimplex() ? 1 : 0) == 0)
      {
        this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
        break;
      }
    }
    if (j != 0)
    {
      this.simplexSolver.compute_points(localVector3f2, localVector3f3);
      localVector3f1.sub(localVector3f2, localVector3f3);
      if ((f5 = this.cachedSeparatingAxis.lengthSquared()) < 1.0E-004F) {
        this.degenerateSimplex = 5;
      }
      if (f5 > 1.421086E-014F)
      {
        f8 = 1.0F / (float)Math.sqrt(f5);
        localVector3f1.scale(f8);
        f5 = (float)Math.sqrt(f4);
        assert (f5 > 0.0F);
        paramBoolean.scale(f2 / f5, this.cachedSeparatingAxis);
        localVector3f2.sub(paramBoolean);
        paramBoolean.scale(f3 / f5, this.cachedSeparatingAxis);
        localVector3f3.add(paramBoolean);
        f1 = 1.0F / f8 - f7;
        i = 1;
        this.lastUsedMethod = 1;
      }
      else
      {
        this.lastUsedMethod = 2;
      }
    }
    int m = (this.catchDegeneracies != 0) && (this.penetrationDepthSolver != null) && (this.degenerateSimplex != 0) && (f1 + f7 < 0.01F) ? 1 : 0;
    if ((k != 0) && ((i == 0) || (m != 0)) && (this.penetrationDepthSolver != null))
    {
      BulletStats.gNumDeepPenetrationChecks += 1;
      if (this.penetrationDepthSolver.calcPenDepth(this.simplexSolver, this.minkowskiA, this.minkowskiB, localTransform1, localTransform2, this.cachedSeparatingAxis, localVector3f12, localVector3f13, paramIDebugDraw))
      {
        localVector3f14.sub(localVector3f13, localVector3f12);
        float f6;
        if ((f6 = localVector3f14.lengthSquared()) > 1.421086E-014F)
        {
          localVector3f14.scale(1.0F / (float)Math.sqrt(f6));
          paramBoolean.sub(localVector3f12, localVector3f13);
          f6 = -paramBoolean.length();
          if ((i == 0) || (f6 < f1))
          {
            f1 = f6;
            localVector3f2.set(localVector3f12);
            localVector3f3.set(localVector3f13);
            localVector3f1.set(localVector3f14);
            i = 1;
            this.lastUsedMethod = 3;
          }
        }
        else
        {
          this.lastUsedMethod = 4;
        }
      }
      else
      {
        this.lastUsedMethod = 5;
      }
    }
    if (i != 0)
    {
      paramBoolean.add(localVector3f3, localVector3f4);
      paramResult.addContactPoint(localVector3f1, paramBoolean, f1);
      this.maxDepth = Math.max(f1, this.maxDepth);
      this.contacts += 1;
    }
  }
  
  public void setMinkowskiA(ConvexShape paramConvexShape)
  {
    this.minkowskiA = paramConvexShape;
  }
  
  public void setMinkowskiB(ConvexShape paramConvexShape)
  {
    this.minkowskiB = paramConvexShape;
  }
  
  public void setCachedSeperatingAxis(Vector3f paramVector3f)
  {
    this.cachedSeparatingAxis.set(paramVector3f);
  }
  
  public void setPenetrationDepthSolver(ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver)
  {
    this.penetrationDepthSolver = paramConvexPenetrationDepthSolver;
  }
  
  public void setIgnoreMargin(boolean paramBoolean)
  {
    this.ignoreMargin = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.GjkPairDetectorExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */