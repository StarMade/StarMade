package com.bulletphysics.collision.narrowphase;

import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class GjkEpaPenetrationDepthSolver
  extends ConvexPenetrationDepthSolver
{
  private GjkEpaSolver gjkEpaSolver = new GjkEpaSolver();
  
  public boolean calcPenDepth(SimplexSolverInterface simplexSolver, ConvexShape pConvexA, ConvexShape pConvexB, Transform transformA, Transform transformB, Vector3f local_v, Vector3f wWitnessOnA, Vector3f wWitnessOnB, IDebugDraw debugDraw)
  {
    float radialmargin = 0.0F;
    GjkEpaSolver.Results results = new GjkEpaSolver.Results();
    if (this.gjkEpaSolver.collide(pConvexA, transformA, pConvexB, transformB, radialmargin, results))
    {
      wWitnessOnA.set(results.witnesses[0]);
      wWitnessOnB.set(results.witnesses[1]);
      return true;
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */