package com.bulletphysics.collision.narrowphase;

import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public abstract class ConvexPenetrationDepthSolver
{
  public abstract boolean calcPenDepth(SimplexSolverInterface paramSimplexSolverInterface, ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, Transform paramTransform1, Transform paramTransform2, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, IDebugDraw paramIDebugDraw);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */