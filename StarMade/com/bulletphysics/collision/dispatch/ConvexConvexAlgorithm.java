package com.bulletphysics.collision.dispatch;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
import com.bulletphysics.collision.narrowphase.GjkConvexCast;
import com.bulletphysics.collision.narrowphase.GjkPairDetector;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import com.bulletphysics.util.ObjectPool;
import javax.vecmath.Vector3f;

public class ConvexConvexAlgorithm
  extends CollisionAlgorithm
{
  protected final ObjectPool<DiscreteCollisionDetectorInterface.ClosestPointInput> pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
  private GjkPairDetector gjkPairDetector = new GjkPairDetector();
  public boolean ownManifold;
  public PersistentManifold manifoldPtr;
  public boolean lowLevelOfDetail;
  private static boolean disableCcd = false;
  
  public void init(PersistentManifold local_mf, CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1, SimplexSolverInterface simplexSolver, ConvexPenetrationDepthSolver pdSolver)
  {
    super.init(local_ci);
    this.gjkPairDetector.init(null, null, simplexSolver, pdSolver);
    this.manifoldPtr = local_mf;
    this.ownManifold = false;
    this.lowLevelOfDetail = false;
  }
  
  public void destroy()
  {
    if (this.ownManifold)
    {
      if (this.manifoldPtr != null) {
        this.dispatcher.releaseManifold(this.manifoldPtr);
      }
      this.manifoldPtr = null;
    }
  }
  
  public void setLowLevelOfDetail(boolean useLowLevel)
  {
    this.lowLevelOfDetail = useLowLevel;
  }
  
  public void processCollision(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
  {
    if (this.manifoldPtr == null)
    {
      this.manifoldPtr = this.dispatcher.getNewManifold(body0, body1);
      this.ownManifold = true;
    }
    resultOut.setPersistentManifold(this.manifoldPtr);
    ConvexShape min0 = (ConvexShape)body0.getCollisionShape();
    ConvexShape min1 = (ConvexShape)body1.getCollisionShape();
    DiscreteCollisionDetectorInterface.ClosestPointInput input = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get();
    input.init();
    this.gjkPairDetector.setMinkowskiA(min0);
    this.gjkPairDetector.setMinkowskiB(min1);
    input.maximumDistanceSquared = (min0.getMargin() + min1.getMargin() + this.manifoldPtr.getContactBreakingThreshold());
    input.maximumDistanceSquared *= input.maximumDistanceSquared;
    body0.getWorldTransform(input.transformA);
    body1.getWorldTransform(input.transformB);
    this.gjkPairDetector.getClosestPoints(input, resultOut, dispatchInfo.debugDraw);
    this.pointInputsPool.release(input);
    if (this.ownManifold) {
      resultOut.refreshContactPoints();
    }
  }
  
  public float calculateTimeOfImpact(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Transform tmpTrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform tmpTrans2 = localStack.get$com$bulletphysics$linearmath$Transform();
      float resultFraction = 1.0F;
      tmp.sub(col0.getInterpolationWorldTransform(tmpTrans1).origin, col0.getWorldTransform(tmpTrans2).origin);
      float squareMot0 = tmp.lengthSquared();
      tmp.sub(col1.getInterpolationWorldTransform(tmpTrans1).origin, col1.getWorldTransform(tmpTrans2).origin);
      float squareMot1 = tmp.lengthSquared();
      if ((squareMot0 < col0.getCcdSquareMotionThreshold()) && (squareMot1 < col1.getCcdSquareMotionThreshold())) {
        return resultFraction;
      }
      if (disableCcd) {
        return 1.0F;
      }
      Transform tmpTrans3 = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform tmpTrans4 = localStack.get$com$bulletphysics$linearmath$Transform();
      ConvexShape convex0 = (ConvexShape)col0.getCollisionShape();
      SphereShape sphere1 = new SphereShape(col1.getCcdSweptSphereRadius());
      ConvexCast.CastResult result = new ConvexCast.CastResult();
      VoronoiSimplexSolver voronoiSimplex = new VoronoiSimplexSolver();
      GjkConvexCast ccd1 = new GjkConvexCast(convex0, sphere1, voronoiSimplex);
      if (ccd1.calcTimeOfImpact(col0.getWorldTransform(tmpTrans1), col0.getInterpolationWorldTransform(tmpTrans2), col1.getWorldTransform(tmpTrans3), col1.getInterpolationWorldTransform(tmpTrans4), result))
      {
        if (col0.getHitFraction() > result.fraction) {
          col0.setHitFraction(result.fraction);
        }
        if (col1.getHitFraction() > result.fraction) {
          col1.setHitFraction(result.fraction);
        }
        if (resultFraction > result.fraction) {
          resultFraction = result.fraction;
        }
      }
      ConvexShape convex0 = (ConvexShape)col1.getCollisionShape();
      SphereShape sphere1 = new SphereShape(col0.getCcdSweptSphereRadius());
      ConvexCast.CastResult result = new ConvexCast.CastResult();
      VoronoiSimplexSolver voronoiSimplex = new VoronoiSimplexSolver();
      GjkConvexCast ccd1 = new GjkConvexCast(sphere1, convex0, voronoiSimplex);
      if (ccd1.calcTimeOfImpact(col0.getWorldTransform(tmpTrans1), col0.getInterpolationWorldTransform(tmpTrans2), col1.getWorldTransform(tmpTrans3), col1.getInterpolationWorldTransform(tmpTrans4), result))
      {
        if (col0.getHitFraction() > result.fraction) {
          col0.setHitFraction(result.fraction);
        }
        if (col1.getHitFraction() > result.fraction) {
          col1.setHitFraction(result.fraction);
        }
        if (resultFraction > result.fraction) {
          resultFraction = result.fraction;
        }
      }
      return resultFraction;
    }
    finally
    {
      .Stack tmp477_475 = localStack;
      tmp477_475.pop$com$bulletphysics$linearmath$Transform();
      tmp477_475.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
  {
    if ((this.manifoldPtr != null) && (this.ownManifold)) {
      manifoldArray.add(this.manifoldPtr);
    }
  }
  
  public PersistentManifold getManifold()
  {
    return this.manifoldPtr;
  }
  
  public static class CreateFunc
    extends CollisionAlgorithmCreateFunc
  {
    private final ObjectPool<ConvexConvexAlgorithm> pool = ObjectPool.get(ConvexConvexAlgorithm.class);
    public ConvexPenetrationDepthSolver pdSolver;
    public SimplexSolverInterface simplexSolver;
    
    public CreateFunc(SimplexSolverInterface simplexSolver, ConvexPenetrationDepthSolver pdSolver)
    {
      this.simplexSolver = simplexSolver;
      this.pdSolver = pdSolver;
    }
    
    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1)
    {
      ConvexConvexAlgorithm algo = (ConvexConvexAlgorithm)this.pool.get();
      algo.init(local_ci.manifold, local_ci, body0, body1, this.simplexSolver, this.pdSolver);
      return algo;
    }
    
    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
    {
      this.pool.release((ConvexConvexAlgorithm)algo);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexConvexAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */