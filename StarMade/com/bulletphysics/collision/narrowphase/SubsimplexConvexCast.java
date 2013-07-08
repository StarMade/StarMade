package com.bulletphysics.collision.narrowphase;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

public class SubsimplexConvexCast
  extends ConvexCast
{
  private static final int MAX_ITERATIONS = 32;
  private SimplexSolverInterface simplexSolver;
  private ConvexShape convexA;
  private ConvexShape convexB;
  
  public SubsimplexConvexCast(ConvexShape shapeA, ConvexShape shapeB, SimplexSolverInterface simplexSolver)
  {
    this.convexA = shapeA;
    this.convexB = shapeB;
    this.simplexSolver = simplexSolver;
  }
  
  public boolean calcTimeOfImpact(Transform arg1, Transform arg2, Transform arg3, Transform arg4, ConvexCast.CastResult arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      this.simplexSolver.reset();
      Vector3f linVelA = localStack.get$javax$vecmath$Vector3f();
      Vector3f linVelB = localStack.get$javax$vecmath$Vector3f();
      linVelA.sub(toA.origin, fromA.origin);
      linVelB.sub(toB.origin, fromB.origin);
      float lambda = 0.0F;
      Transform interpolatedTransA = localStack.get$com$bulletphysics$linearmath$Transform(fromA);
      Transform interpolatedTransB = localStack.get$com$bulletphysics$linearmath$Transform(fromB);
      Vector3f local_r = localStack.get$javax$vecmath$Vector3f();
      local_r.sub(linVelA, linVelB);
      Vector3f local_v = localStack.get$javax$vecmath$Vector3f();
      tmp.negate(local_r);
      MatrixUtil.transposeTransform(tmp, tmp, fromA.basis);
      Vector3f supVertexA = this.convexA.localGetSupportingVertex(tmp, localStack.get$javax$vecmath$Vector3f());
      fromA.transform(supVertexA);
      MatrixUtil.transposeTransform(tmp, local_r, fromB.basis);
      Vector3f supVertexB = this.convexB.localGetSupportingVertex(tmp, localStack.get$javax$vecmath$Vector3f());
      fromB.transform(supVertexB);
      local_v.sub(supVertexA, supVertexB);
      int maxIter = 32;
      Vector3f local_n = localStack.get$javax$vecmath$Vector3f();
      local_n.set(0.0F, 0.0F, 0.0F);
      boolean hasResult = false;
      Vector3f local_c = localStack.get$javax$vecmath$Vector3f();
      float lastLambda = lambda;
      float dist2 = local_v.lengthSquared();
      float epsilon = 1.0E-004F;
      Vector3f local_w = localStack.get$javax$vecmath$Vector3f();
      Vector3f local_p = localStack.get$javax$vecmath$Vector3f();
      while ((dist2 > epsilon) && (maxIter-- != 0))
      {
        tmp.negate(local_v);
        MatrixUtil.transposeTransform(tmp, tmp, interpolatedTransA.basis);
        this.convexA.localGetSupportingVertex(tmp, supVertexA);
        interpolatedTransA.transform(supVertexA);
        MatrixUtil.transposeTransform(tmp, local_v, interpolatedTransB.basis);
        this.convexB.localGetSupportingVertex(tmp, supVertexB);
        interpolatedTransB.transform(supVertexB);
        local_w.sub(supVertexA, supVertexB);
        float VdotW = local_v.dot(local_w);
        if (lambda > 1.0F) {
          return false;
        }
        if (VdotW > 0.0F)
        {
          float VdotR = local_v.dot(local_r);
          if (VdotR >= -1.421086E-014F) {
            return false;
          }
          lambda -= VdotW / VdotR;
          VectorUtil.setInterpolate3(interpolatedTransA.origin, fromA.origin, toA.origin, lambda);
          VectorUtil.setInterpolate3(interpolatedTransB.origin, fromB.origin, toB.origin, lambda);
          local_w.sub(supVertexA, supVertexB);
          lastLambda = lambda;
          local_n.set(local_v);
          hasResult = true;
        }
        this.simplexSolver.addVertex(local_w, supVertexA, supVertexB);
        if (this.simplexSolver.closest(local_v))
        {
          dist2 = local_v.lengthSquared();
          hasResult = true;
        }
        else
        {
          dist2 = 0.0F;
        }
      }
      result.fraction = lambda;
      if (local_n.lengthSquared() >= 1.421086E-014F) {
        result.normal.normalize(local_n);
      } else {
        result.normal.set(0.0F, 0.0F, 0.0F);
      }
      if (result.normal.dot(local_r) >= -result.allowedPenetration) {
        return false;
      }
      Vector3f VdotW = localStack.get$javax$vecmath$Vector3f();
      Vector3f hitB = localStack.get$javax$vecmath$Vector3f();
      this.simplexSolver.compute_points(VdotW, hitB);
      result.hitPoint.set(hitB);
      return true;
    }
    finally
    {
      .Stack tmp644_642 = localStack;
      tmp644_642.pop$com$bulletphysics$linearmath$Transform();
      tmp644_642.pop$javax$vecmath$Vector3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.SubsimplexConvexCast
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */