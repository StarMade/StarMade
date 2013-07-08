package com.bulletphysics.collision.dispatch;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.ConcaveShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.collision.shapes.TriangleCallback;
import com.bulletphysics.collision.shapes.TriangleShape;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ObjectArrayList;
import com.bulletphysics.util.ObjectPool;
import javax.vecmath.Vector3f;

public class ConvexConcaveCollisionAlgorithm
  extends CollisionAlgorithm
{
  private boolean isSwapped;
  private ConvexTriangleCallback btConvexTriangleCallback;
  
  public void init(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1, boolean isSwapped)
  {
    super.init(local_ci);
    this.isSwapped = isSwapped;
    this.btConvexTriangleCallback = new ConvexTriangleCallback(this.dispatcher, body0, body1, isSwapped);
  }
  
  public void destroy()
  {
    this.btConvexTriangleCallback.destroy();
  }
  
  public void processCollision(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      CollisionObject convexBody = this.isSwapped ? body1 : body0;
      CollisionObject triBody = this.isSwapped ? body0 : body1;
      if (triBody.getCollisionShape().isConcave())
      {
        CollisionObject triOb = triBody;
        ConcaveShape concaveShape = (ConcaveShape)triOb.getCollisionShape();
        if (convexBody.getCollisionShape().isConvex())
        {
          float collisionMarginTriangle = concaveShape.getMargin();
          resultOut.setPersistentManifold(this.btConvexTriangleCallback.manifoldPtr);
          this.btConvexTriangleCallback.setTimeStepAndCounters(collisionMarginTriangle, dispatchInfo, resultOut);
          this.btConvexTriangleCallback.manifoldPtr.setBodies(convexBody, triBody);
          concaveShape.processAllTriangles(this.btConvexTriangleCallback, this.btConvexTriangleCallback.getAabbMin(localStack.get$javax$vecmath$Vector3f()), this.btConvexTriangleCallback.getAabbMax(localStack.get$javax$vecmath$Vector3f()));
          resultOut.refreshContactPoints();
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
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
      CollisionObject convexbody = this.isSwapped ? body1 : body0;
      CollisionObject triBody = this.isSwapped ? body0 : body1;
      tmp.sub(convexbody.getInterpolationWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin, convexbody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
      float squareMot0 = tmp.lengthSquared();
      if (squareMot0 < convexbody.getCcdSquareMotionThreshold()) {
        return 1.0F;
      }
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform triInv = triBody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      triInv.inverse();
      Transform convexFromLocal = localStack.get$com$bulletphysics$linearmath$Transform();
      convexFromLocal.mul(triInv, convexbody.getWorldTransform(tmpTrans));
      Transform convexToLocal = localStack.get$com$bulletphysics$linearmath$Transform();
      convexToLocal.mul(triInv, convexbody.getInterpolationWorldTransform(tmpTrans));
      if (triBody.getCollisionShape().isConcave())
      {
        Vector3f rayAabbMin = localStack.get$javax$vecmath$Vector3f(convexFromLocal.origin);
        VectorUtil.setMin(rayAabbMin, convexToLocal.origin);
        Vector3f rayAabbMax = localStack.get$javax$vecmath$Vector3f(convexFromLocal.origin);
        VectorUtil.setMax(rayAabbMax, convexToLocal.origin);
        float ccdRadius0 = convexbody.getCcdSweptSphereRadius();
        tmp.set(ccdRadius0, ccdRadius0, ccdRadius0);
        rayAabbMin.sub(tmp);
        rayAabbMax.add(tmp);
        float curHitFraction = 1.0F;
        LocalTriangleSphereCastCallback raycastCallback = new LocalTriangleSphereCastCallback(convexFromLocal, convexToLocal, convexbody.getCcdSweptSphereRadius(), curHitFraction);
        raycastCallback.hitFraction = convexbody.getHitFraction();
        CollisionObject concavebody = triBody;
        ConcaveShape triangleMesh = (ConcaveShape)concavebody.getCollisionShape();
        if (triangleMesh != null) {
          triangleMesh.processAllTriangles(raycastCallback, rayAabbMin, rayAabbMax);
        }
        if (raycastCallback.hitFraction < convexbody.getHitFraction())
        {
          convexbody.setHitFraction(raycastCallback.hitFraction);
          return raycastCallback.hitFraction;
        }
      }
      return 1.0F;
    }
    finally
    {
      .Stack tmp379_377 = localStack;
      tmp379_377.pop$com$bulletphysics$linearmath$Transform();
      tmp379_377.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
  {
    if (this.btConvexTriangleCallback.manifoldPtr != null) {
      manifoldArray.add(this.btConvexTriangleCallback.manifoldPtr);
    }
  }
  
  public void clearCache()
  {
    this.btConvexTriangleCallback.clearCache();
  }
  
  public static class SwappedCreateFunc
    extends CollisionAlgorithmCreateFunc
  {
    private final ObjectPool<ConvexConcaveCollisionAlgorithm> pool = ObjectPool.get(ConvexConcaveCollisionAlgorithm.class);
    
    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1)
    {
      ConvexConcaveCollisionAlgorithm algo = (ConvexConcaveCollisionAlgorithm)this.pool.get();
      algo.init(local_ci, body0, body1, true);
      return algo;
    }
    
    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
    {
      this.pool.release((ConvexConcaveCollisionAlgorithm)algo);
    }
  }
  
  public static class CreateFunc
    extends CollisionAlgorithmCreateFunc
  {
    private final ObjectPool<ConvexConcaveCollisionAlgorithm> pool = ObjectPool.get(ConvexConcaveCollisionAlgorithm.class);
    
    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1)
    {
      ConvexConcaveCollisionAlgorithm algo = (ConvexConcaveCollisionAlgorithm)this.pool.get();
      algo.init(local_ci, body0, body1, false);
      return algo;
    }
    
    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
    {
      this.pool.release((ConvexConcaveCollisionAlgorithm)algo);
    }
  }
  
  private static class LocalTriangleSphereCastCallback
    extends TriangleCallback
  {
    public final Transform ccdSphereFromTrans = new Transform();
    public final Transform ccdSphereToTrans = new Transform();
    public final Transform meshTransform = new Transform();
    public float ccdSphereRadius;
    public float hitFraction;
    private final Transform ident = new Transform();
    
    public LocalTriangleSphereCastCallback(Transform from, Transform local_to, float ccdSphereRadius, float hitFraction)
    {
      this.ccdSphereFromTrans.set(from);
      this.ccdSphereToTrans.set(local_to);
      this.ccdSphereRadius = ccdSphereRadius;
      this.hitFraction = hitFraction;
      this.ident.setIdentity();
    }
    
    public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex)
    {
      ConvexCast.CastResult castResult = new ConvexCast.CastResult();
      castResult.fraction = this.hitFraction;
      SphereShape pointShape = new SphereShape(this.ccdSphereRadius);
      TriangleShape triShape = new TriangleShape(triangle[0], triangle[1], triangle[2]);
      VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
      SubsimplexConvexCast convexCaster = new SubsimplexConvexCast(pointShape, triShape, simplexSolver);
      if ((convexCaster.calcTimeOfImpact(this.ccdSphereFromTrans, this.ccdSphereToTrans, this.ident, this.ident, castResult)) && (this.hitFraction > castResult.fraction)) {
        this.hitFraction = castResult.fraction;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexConcaveCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */