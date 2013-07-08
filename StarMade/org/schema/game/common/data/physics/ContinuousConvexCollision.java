package org.schema.game.common.data.physics;

import class_49;
import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
import com.bulletphysics.collision.narrowphase.PointCollector;
import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectPool;
import java.io.PrintStream;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.common.util.linAlg.TransformTools;

public class ContinuousConvexCollision
{
  private SimplexSolverInterface simplexSolver;
  private ConvexPenetrationDepthSolver penetrationDepthSolver;
  private ConvexShape convexA;
  private ConvexShape convexB1;
  private StaticPlaneShape planeShape;
  static final int MAX_ITERATIONS = 64;
  protected final ObjectPool pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
  
  public ContinuousConvexCollision(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver)
  {
    this.simplexSolver = paramSimplexSolverInterface;
    this.penetrationDepthSolver = paramConvexPenetrationDepthSolver;
    this.convexA = paramConvexShape1;
    this.convexB1 = paramConvexShape2;
    this.planeShape = null;
  }
  
  public ContinuousConvexCollision(ConvexShape paramConvexShape, StaticPlaneShape paramStaticPlaneShape)
  {
    this.convexA = paramConvexShape;
    this.planeShape = paramStaticPlaneShape;
  }
  
  boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult, GjkPairDetectorVariables paramGjkPairDetectorVariables)
  {
    Vector3f localVector3f1 = new Vector3f();
    Vector3f localVector3f2 = new Vector3f();
    Vector3f localVector3f3 = new Vector3f();
    Vector3f localVector3f4 = new Vector3f();
    TransformTools.a1(paramTransform1, paramTransform2, 1.0F, localVector3f1, localVector3f2, paramGjkPairDetectorVariables.axis, paramGjkPairDetectorVariables.tmp2, paramGjkPairDetectorVariables.dmat, paramGjkPairDetectorVariables.dorn);
    TransformTools.a1(paramTransform3, paramTransform4, 1.0F, localVector3f3, localVector3f4, paramGjkPairDetectorVariables.axis, paramGjkPairDetectorVariables.tmp2, paramGjkPairDetectorVariables.dmat, paramGjkPairDetectorVariables.dorn);
    if ((class_49.a3(localVector3f1)) || (class_49.a3(localVector3f3)))
    {
      System.err.println("WARNING: LINEAR VELOCITY WAS NAN: " + localVector3f1 + "; " + localVector3f3);
      return false;
    }
    paramTransform2 = this.convexA.getAngularMotionDisc();
    paramTransform4 = this.convexB1 != null ? this.convexB1.getAngularMotionDisc() : 0.0F;
    paramTransform2 = localVector3f2.length() * paramTransform2 + localVector3f4.length() * paramTransform4;
    (paramTransform4 = new Vector3f()).sub(localVector3f3, localVector3f1);
    if (paramTransform4.length() + paramTransform2 == 0.0F) {
      return false;
    }
    float f1 = 0.0F;
    new Vector3f(1.0F, 0.0F, 0.0F);
    Vector3f localVector3f5 = new Vector3f(0.0F, 0.0F, 0.0F);
    float f4 = 0.0F;
    int i = 0;
    PointCollector localPointCollector = new PointCollector();
    computeClosestPoints(paramTransform1, paramTransform3, localPointCollector, paramGjkPairDetectorVariables);
    boolean bool = localPointCollector.hasResult;
    Object localObject1 = localPointCollector.pointInWorld;
    if (bool)
    {
      float f2 = localPointCollector.distance + paramCastResult.allowedPenetration;
      localVector3f5.set(localPointCollector.normalOnBInWorld);
      if (paramTransform4.dot(localVector3f5) + paramTransform2 <= 1.192093E-007F) {
        return false;
      }
      while (f2 > 0.001F)
      {
        float f5;
        if ((f5 = paramTransform4.dot(localVector3f5)) + paramTransform2 <= 1.192093E-007F) {
          return false;
        }
        f2 /= (f5 + paramTransform2);
        if (f1 += f2 > 1.0F) {
          return false;
        }
        if (f1 < 0.0F)
        {
          System.err.println("HAS RESULT: BUT LAMDA IS TO SMALL " + f1);
          return false;
        }
        if (f1 <= f4)
        {
          System.err.println("HAS RESULT: BUT LAST LAMDA IS <= LAST LAMBDA");
          return false;
        }
        f4 = f1;
        Transform localTransform;
        (localTransform = new Transform()).setIdentity();
        (localObject1 = new Transform()).setIdentity();
        TransformTools.a(paramTransform1, localVector3f1, localVector3f2, f1, localTransform, paramGjkPairDetectorVariables.iAxis, paramGjkPairDetectorVariables.iDorn, paramGjkPairDetectorVariables.iorn0, paramGjkPairDetectorVariables.iPredictOrn, paramGjkPairDetectorVariables.float4Temp);
        TransformTools.a(paramTransform3, localVector3f3, localVector3f4, f1, (Transform)localObject1, paramGjkPairDetectorVariables.iAxis, paramGjkPairDetectorVariables.iDorn, paramGjkPairDetectorVariables.iorn0, paramGjkPairDetectorVariables.iPredictOrn, paramGjkPairDetectorVariables.float4Temp);
        (localObject2 = new Transform()).set((Transform)localObject1);
        ((Transform)localObject2).inverse();
        ((Transform)localObject2).mul(localTransform);
        Object localObject2 = new PointCollector();
        computeClosestPoints(localTransform, (Transform)localObject1, (PointCollector)localObject2, paramGjkPairDetectorVariables);
        if (((PointCollector)localObject2).hasResult)
        {
          float f3 = ((PointCollector)localObject2).distance + paramCastResult.allowedPenetration;
          localObject1 = ((PointCollector)localObject2).pointInWorld;
          localVector3f5.set(((PointCollector)localObject2).normalOnBInWorld);
        }
        else
        {
          System.err.println("POINT HAS NO RESULT: -1 " + i);
          return false;
        }
        i++;
        if (i > 64) {
          return false;
        }
      }
      paramCastResult.fraction = f1;
      paramCastResult.normal.set(localVector3f5);
      paramCastResult.hitPoint.set((Tuple3f)localObject1);
      return true;
    }
    return false;
  }
  
  void computeClosestPoints(Transform paramTransform1, Transform paramTransform2, PointCollector paramPointCollector, GjkPairDetectorVariables paramGjkPairDetectorVariables)
  {
    Object localObject1;
    if (this.convexB1 != null)
    {
      this.simplexSolver.reset();
      (paramGjkPairDetectorVariables = new GjkPairDetectorExt(paramGjkPairDetectorVariables)).init(this.convexA, this.convexB1, this.simplexSolver, this.penetrationDepthSolver);
      (localObject1 = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get()).init();
      ((DiscreteCollisionDetectorInterface.ClosestPointInput)localObject1).transformA.set(paramTransform1);
      ((DiscreteCollisionDetectorInterface.ClosestPointInput)localObject1).transformB.set(paramTransform2);
      paramGjkPairDetectorVariables.getClosestPoints((DiscreteCollisionDetectorInterface.ClosestPointInput)localObject1, paramPointCollector, null);
      this.pointInputsPool.release(localObject1);
      return;
    }
    paramGjkPairDetectorVariables = this.convexA;
    Vector3f localVector3f2 = (localObject1 = this.planeShape).getPlaneNormal(new Vector3f());
    float f = ((StaticPlaneShape)localObject1).getPlaneConstant();
    Object localObject2;
    (localObject2 = new Transform()).set(paramTransform2);
    ((Transform)localObject2).inverse();
    ((Transform)localObject2).mul(paramTransform1);
    Object localObject3;
    (localObject3 = new Transform()).set(paramTransform1);
    ((Transform)localObject3).inverse();
    ((Transform)localObject3).mul(paramTransform2);
    (paramTransform1 = new Matrix3f()).set(((Transform)localObject3).basis);
    (localObject3 = new Vector3f(localVector3f2)).scale(-1.0F);
    paramTransform1.transform((Tuple3f)localObject3);
    paramTransform1 = paramGjkPairDetectorVariables.localGetSupportingVertex((Vector3f)localObject3, new Vector3f());
    (paramGjkPairDetectorVariables = new Vector3f()).set(paramTransform1);
    ((Transform)localObject2).transform(paramTransform1);
    paramTransform1 = localVector3f2.dot(paramGjkPairDetectorVariables) - f;
    Vector3f localVector3f1 = new Vector3f();
    (localObject2 = new Vector3f(localVector3f2)).scale(paramTransform1);
    localVector3f1.sub(paramGjkPairDetectorVariables, (Tuple3f)localObject2);
    paramGjkPairDetectorVariables = new Vector3f(localVector3f1);
    paramTransform2.transform(paramGjkPairDetectorVariables);
    localVector3f1 = new Vector3f(localVector3f2);
    paramTransform2.basis.transform(localVector3f1);
    paramPointCollector.addContactPoint(localVector3f1, paramGjkPairDetectorVariables, paramTransform1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.ContinuousConvexCollision
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */