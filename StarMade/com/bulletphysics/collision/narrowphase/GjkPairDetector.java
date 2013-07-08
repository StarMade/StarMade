package com.bulletphysics.collision.narrowphase;

import com.bulletphysics..Stack;
import com.bulletphysics.BulletStats;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class GjkPairDetector
  extends DiscreteCollisionDetectorInterface
{
  private static final float REL_ERROR2 = 1.0E-006F;
  private final Vector3f cachedSeparatingAxis = new Vector3f();
  private ConvexPenetrationDepthSolver penetrationDepthSolver;
  private SimplexSolverInterface simplexSolver;
  private ConvexShape minkowskiA;
  private ConvexShape minkowskiB;
  private boolean ignoreMargin;
  public int lastUsedMethod;
  public int curIter;
  public int degenerateSimplex;
  public int catchDegeneracies;
  
  public void init(ConvexShape objectA, ConvexShape objectB, SimplexSolverInterface simplexSolver, ConvexPenetrationDepthSolver penetrationDepthSolver)
  {
    this.cachedSeparatingAxis.set(0.0F, 0.0F, 1.0F);
    this.ignoreMargin = false;
    this.lastUsedMethod = -1;
    this.catchDegeneracies = 1;
    this.penetrationDepthSolver = penetrationDepthSolver;
    this.simplexSolver = simplexSolver;
    this.minkowskiA = objectA;
    this.minkowskiB = objectB;
  }
  
  public void getClosestPoints(DiscreteCollisionDetectorInterface.ClosestPointInput arg1, DiscreteCollisionDetectorInterface.Result arg2, IDebugDraw arg3, boolean arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      float distance = 0.0F;
      Vector3f normalInB = localStack.get$javax$vecmath$Vector3f();
      normalInB.set(0.0F, 0.0F, 0.0F);
      Vector3f pointOnA = localStack.get$javax$vecmath$Vector3f();
      Vector3f pointOnB = localStack.get$javax$vecmath$Vector3f();
      Transform localTransA = localStack.get$com$bulletphysics$linearmath$Transform(input.transformA);
      Transform localTransB = localStack.get$com$bulletphysics$linearmath$Transform(input.transformB);
      Vector3f positionOffset = localStack.get$javax$vecmath$Vector3f();
      positionOffset.add(localTransA.origin, localTransB.origin);
      positionOffset.scale(0.5F);
      localTransA.origin.sub(positionOffset);
      localTransB.origin.sub(positionOffset);
      float marginA = this.minkowskiA.getMargin();
      float marginB = this.minkowskiB.getMargin();
      BulletStats.gNumGjkChecks += 1;
      if (this.ignoreMargin)
      {
        marginA = 0.0F;
        marginB = 0.0F;
      }
      this.curIter = 0;
      int gGjkMaxIter = 1000;
      this.cachedSeparatingAxis.set(0.0F, 1.0F, 0.0F);
      boolean isValid = false;
      boolean checkSimplex = false;
      boolean checkPenetration = true;
      this.degenerateSimplex = 0;
      this.lastUsedMethod = -1;
      float squaredDistance = 3.4028235E+38F;
      float delta = 0.0F;
      float margin = marginA + marginB;
      this.simplexSolver.reset();
      Vector3f seperatingAxisInA = localStack.get$javax$vecmath$Vector3f();
      Vector3f seperatingAxisInB = localStack.get$javax$vecmath$Vector3f();
      Vector3f pInA = localStack.get$javax$vecmath$Vector3f();
      Vector3f qInB = localStack.get$javax$vecmath$Vector3f();
      Vector3f pWorld = localStack.get$javax$vecmath$Vector3f();
      Vector3f qWorld = localStack.get$javax$vecmath$Vector3f();
      Vector3f local_w = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmpPointOnA = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmpPointOnB = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmpNormalInB = localStack.get$javax$vecmath$Vector3f();
      for (;;)
      {
        seperatingAxisInA.negate(this.cachedSeparatingAxis);
        MatrixUtil.transposeTransform(seperatingAxisInA, seperatingAxisInA, input.transformA.basis);
        seperatingAxisInB.set(this.cachedSeparatingAxis);
        MatrixUtil.transposeTransform(seperatingAxisInB, seperatingAxisInB, input.transformB.basis);
        this.minkowskiA.localGetSupportingVertexWithoutMargin(seperatingAxisInA, pInA);
        this.minkowskiB.localGetSupportingVertexWithoutMargin(seperatingAxisInB, qInB);
        pWorld.set(pInA);
        localTransA.transform(pWorld);
        qWorld.set(qInB);
        localTransB.transform(qWorld);
        local_w.sub(pWorld, qWorld);
        delta = this.cachedSeparatingAxis.dot(local_w);
        if ((delta > 0.0F) && (delta * delta > squaredDistance * input.maximumDistanceSquared))
        {
          checkPenetration = false;
          break;
        }
        if (this.simplexSolver.inSimplex(local_w))
        {
          this.degenerateSimplex = 1;
          checkSimplex = true;
          break;
        }
        float local_f0 = squaredDistance - delta;
        float local_f1 = squaredDistance * 1.0E-006F;
        if (local_f0 <= local_f1)
        {
          if (local_f0 <= 0.0F) {
            this.degenerateSimplex = 2;
          }
          checkSimplex = true;
          break;
        }
        this.simplexSolver.addVertex(local_w, pWorld, qWorld);
        if (!this.simplexSolver.closest(this.cachedSeparatingAxis))
        {
          this.degenerateSimplex = 3;
          checkSimplex = true;
          break;
        }
        if (this.cachedSeparatingAxis.lengthSquared() < 1.0E-006F)
        {
          this.degenerateSimplex = 6;
          checkSimplex = true;
          break;
        }
        float previousSquaredDistance = squaredDistance;
        squaredDistance = this.cachedSeparatingAxis.lengthSquared();
        if (previousSquaredDistance - squaredDistance <= 1.192093E-007F * previousSquaredDistance)
        {
          this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
          checkSimplex = true;
          break;
        }
        if (this.curIter++ > gGjkMaxIter) {
          break;
        }
        boolean check = !this.simplexSolver.fullSimplex();
        if (!check)
        {
          this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
          break;
        }
      }
      if (checkSimplex)
      {
        this.simplexSolver.compute_points(pointOnA, pointOnB);
        normalInB.sub(pointOnA, pointOnB);
        float local_f0 = this.cachedSeparatingAxis.lengthSquared();
        if (local_f0 < 1.0E-004F) {
          this.degenerateSimplex = 5;
        }
        if (local_f0 > 1.421086E-014F)
        {
          float local_f1 = 1.0F / (float)Math.sqrt(local_f0);
          normalInB.scale(local_f1);
          float previousSquaredDistance = (float)Math.sqrt(squaredDistance);
          assert (previousSquaredDistance > 0.0F);
          tmp.scale(marginA / previousSquaredDistance, this.cachedSeparatingAxis);
          pointOnA.sub(tmp);
          tmp.scale(marginB / previousSquaredDistance, this.cachedSeparatingAxis);
          pointOnB.add(tmp);
          distance = 1.0F / local_f1 - margin;
          isValid = true;
          this.lastUsedMethod = 1;
        }
        else
        {
          this.lastUsedMethod = 2;
        }
      }
      boolean local_f0 = (this.catchDegeneracies != 0) && (this.penetrationDepthSolver != null) && (this.degenerateSimplex != 0) && (distance + margin < 0.01F);
      if ((checkPenetration) && ((!isValid) || (local_f0)) && (this.penetrationDepthSolver != null))
      {
        BulletStats.gNumDeepPenetrationChecks += 1;
        boolean local_f1 = this.penetrationDepthSolver.calcPenDepth(this.simplexSolver, this.minkowskiA, this.minkowskiB, localTransA, localTransB, this.cachedSeparatingAxis, tmpPointOnA, tmpPointOnB, debugDraw);
        if (local_f1)
        {
          tmpNormalInB.sub(tmpPointOnB, tmpPointOnA);
          float previousSquaredDistance = tmpNormalInB.lengthSquared();
          if (previousSquaredDistance > 1.421086E-014F)
          {
            tmpNormalInB.scale(1.0F / (float)Math.sqrt(previousSquaredDistance));
            tmp.sub(tmpPointOnA, tmpPointOnB);
            float check = -tmp.length();
            if ((!isValid) || (check < distance))
            {
              distance = check;
              pointOnA.set(tmpPointOnA);
              pointOnB.set(tmpPointOnB);
              normalInB.set(tmpNormalInB);
              isValid = true;
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
      if (isValid)
      {
        tmp.add(pointOnB, positionOffset);
        output.addContactPoint(normalInB, tmp, distance);
      }
      return;
    }
    finally
    {
      .Stack tmp1101_1099 = localStack;
      tmp1101_1099.pop$com$bulletphysics$linearmath$Transform();
      tmp1101_1099.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setMinkowskiA(ConvexShape minkA)
  {
    this.minkowskiA = minkA;
  }
  
  public void setMinkowskiB(ConvexShape minkB)
  {
    this.minkowskiB = minkB;
  }
  
  public void setCachedSeperatingAxis(Vector3f seperatingAxis)
  {
    this.cachedSeparatingAxis.set(seperatingAxis);
  }
  
  public void setPenetrationDepthSolver(ConvexPenetrationDepthSolver penetrationDepthSolver)
  {
    this.penetrationDepthSolver = penetrationDepthSolver;
  }
  
  public void setIgnoreMargin(boolean ignoreMargin)
  {
    this.ignoreMargin = ignoreMargin;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.GjkPairDetector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */