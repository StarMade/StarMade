/*    */ package com.bulletphysics.collision.narrowphase;
/*    */ 
/*    */ import com.bulletphysics.collision.shapes.ConvexShape;
/*    */ import com.bulletphysics.linearmath.IDebugDraw;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class GjkEpaPenetrationDepthSolver extends ConvexPenetrationDepthSolver
/*    */ {
/* 39 */   private GjkEpaSolver gjkEpaSolver = new GjkEpaSolver();
/*    */ 
/*    */   public boolean calcPenDepth(SimplexSolverInterface simplexSolver, ConvexShape pConvexA, ConvexShape pConvexB, Transform transformA, Transform transformB, Vector3f v, Vector3f wWitnessOnA, Vector3f wWitnessOnB, IDebugDraw debugDraw)
/*    */   {
/* 47 */     float radialmargin = 0.0F;
/*    */ 
/* 51 */     GjkEpaSolver.Results results = new GjkEpaSolver.Results();
/* 52 */     if (this.gjkEpaSolver.collide(pConvexA, transformA, pConvexB, transformB, radialmargin, results))
/*    */     {
/* 57 */       wWitnessOnA.set(results.witnesses[0]);
/* 58 */       wWitnessOnB.set(results.witnesses[1]);
/* 59 */       return true;
/*    */     }
/*    */ 
/* 62 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver
 * JD-Core Version:    0.6.2
 */