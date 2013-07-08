/*  1:   */package com.bulletphysics.collision.narrowphase;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.shapes.ConvexShape;
/*  4:   */import com.bulletphysics.linearmath.IDebugDraw;
/*  5:   */import com.bulletphysics.linearmath.Transform;
/*  6:   */import javax.vecmath.Vector3f;
/*  7:   */
/* 36:   */public class GjkEpaPenetrationDepthSolver
/* 37:   */  extends ConvexPenetrationDepthSolver
/* 38:   */{
/* 39:39 */  private GjkEpaSolver gjkEpaSolver = new GjkEpaSolver();
/* 40:   */  
/* 45:   */  public boolean calcPenDepth(SimplexSolverInterface simplexSolver, ConvexShape pConvexA, ConvexShape pConvexB, Transform transformA, Transform transformB, Vector3f v, Vector3f wWitnessOnA, Vector3f wWitnessOnB, IDebugDraw debugDraw)
/* 46:   */  {
/* 47:47 */    float radialmargin = 0.0F;
/* 48:   */    
/* 51:51 */    GjkEpaSolver.Results results = new GjkEpaSolver.Results();
/* 52:52 */    if (this.gjkEpaSolver.collide(pConvexA, transformA, pConvexB, transformB, radialmargin, results))
/* 53:   */    {
/* 57:57 */      wWitnessOnA.set(results.witnesses[0]);
/* 58:58 */      wWitnessOnB.set(results.witnesses[1]);
/* 59:59 */      return true;
/* 60:   */    }
/* 61:   */    
/* 62:62 */    return false;
/* 63:   */  }
/* 64:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */