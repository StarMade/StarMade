package org.schema.game.common.data.physics;

import class_35;
import class_384;
import class_48;
import class_886;
import class_988;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.ManifoldResult;
import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import com.bulletphysics.util.ObjectPool;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.octree.ArrayOctree;
import org.schema.game.common.data.physics.octree.IntersectionCallback;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

public class CubeConvexCollisionAlgorithm
  extends CollisionAlgorithm
{
  protected final ObjectPool pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
  GjkPairDetectorExt gjkPairDetector;
  private static ThreadLocal threadLocal = new CubeConvexCollisionAlgorithm.1();
  public boolean lowLevelOfDetail;
  private boolean ownManifold;
  private static final float margin = 0.05F;
  private boolean onServer;
  private PersistentManifold manifoldPtr;
  private CubeConvexVariableSet field_264;
  private CubeConvexCollisionAlgorithm.OuterSegmentHandler outerSegmentHandler;
  private long currentTime;
  
  public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    System.err.println("CALCULATING CONVEX CUBE TOI");
    return 1.0F;
  }
  
  public void destroy() {}
  
  private void doNarrowTest(SegmentData paramSegmentData, CollisionObject paramCollisionObject, class_35 paramclass_351, class_35 paramclass_352, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    paramCollisionObject.getCollisionShape().getAabb(this.field_264.convexShapeTransform, this.field_264.otherminOut, this.field_264.othermaxOut);
    for (paramCollisionObject = paramclass_351.field_453; paramCollisionObject < paramclass_352.field_453; paramCollisionObject = (byte)(paramCollisionObject + 1)) {
      for (paramDispatcherInfo = paramclass_351.field_454; paramDispatcherInfo < paramclass_352.field_454; paramDispatcherInfo = (byte)(paramDispatcherInfo + 1)) {
        for (paramManifoldResult = paramclass_351.field_455; paramManifoldResult < paramclass_352.field_455; paramManifoldResult = (byte)(paramManifoldResult + 1))
        {
          int i = SegmentData.getInfoIndex((byte)(paramCollisionObject + 8), (byte)(paramDispatcherInfo + 8), (byte)(paramManifoldResult + 8));
          ElementInformation localElementInformation;
          if ((paramSegmentData.contains(i)) && ((localElementInformation = ElementKeyMap.getInfo(paramSegmentData.getType(i))).isPhysical(paramSegmentData.isActive(i))))
          {
            int j = paramSegmentData.getOrientation(i);
            this.field_264.elemPosA.set(paramCollisionObject, paramDispatcherInfo, paramManifoldResult);
            this.field_264.elemPosA.field_615 += paramSegmentData.getSegment().field_34.field_475;
            this.field_264.elemPosA.field_616 += paramSegmentData.getSegment().field_34.field_476;
            this.field_264.elemPosA.field_617 += paramSegmentData.getSegment().field_34.field_477;
            this.field_264.min.set(this.field_264.elemPosA);
            this.field_264.max.set(this.field_264.elemPosA);
            this.field_264.min.field_615 -= 0.5F;
            this.field_264.min.field_616 -= 0.5F;
            this.field_264.min.field_617 -= 0.5F;
            this.field_264.max.field_615 += 0.5F;
            this.field_264.max.field_616 += 0.5F;
            this.field_264.max.field_617 += 0.5F;
            AabbUtil2.transformAabb(this.field_264.min, this.field_264.max, 0.25F, this.field_264.cubeMeshTransform, this.field_264.minOut, this.field_264.maxOut);
            this.field_264.box0.setMargin(0.0F);
            if (AabbUtil2.testAabbAgainstAabb2(this.field_264.minOut, this.field_264.maxOut, this.field_264.otherminOut, this.field_264.othermaxOut))
            {
              paramSegmentData.getSegment();
              Segment.d();
              this.field_264.boxTransformation.set(this.field_264.cubeMeshTransform);
              this.field_264.field_1924.set(this.field_264.elemPosA);
              this.field_264.boxTransformation.basis.transform(this.field_264.field_1924);
              this.field_264.boxTransformation.origin.add(this.field_264.field_1924);
              this.field_264.positionCache.add(new Transform(this.field_264.boxTransformation));
              this.field_264.blockInfoCache.add(new class_48(localElementInformation.getBlockStyle(), j, paramSegmentData.isActive(i) ? 1 : 0));
            }
          }
        }
      }
    }
  }
  
  private void doRegularCollision(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, Transform paramTransform1, Transform paramTransform2, ManifoldResult paramManifoldResult, PersistentManifold paramPersistentManifold, DispatcherInfo paramDispatcherInfo)
  {
    DiscreteCollisionDetectorInterface.ClosestPointInput localClosestPointInput;
    (localClosestPointInput = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get()).init();
    this.gjkPairDetector.setMinkowskiA(paramConvexShape1);
    this.gjkPairDetector.setMinkowskiB(paramConvexShape2);
    localClosestPointInput.maximumDistanceSquared = (paramConvexShape1.getMargin() + paramPersistentManifold.getContactBreakingThreshold());
    localClosestPointInput.maximumDistanceSquared *= localClosestPointInput.maximumDistanceSquared;
    localClosestPointInput.transformA.set(paramTransform1);
    localClosestPointInput.transformB.set(paramTransform2);
    this.gjkPairDetector.getClosestPoints(localClosestPointInput, paramManifoldResult, paramDispatcherInfo.debugDraw);
    this.pointInputsPool.release(localClosestPointInput);
    paramManifoldResult.refreshContactPoints();
  }
  
  public void getAllContactManifolds(ObjectArrayList paramObjectArrayList)
  {
    if ((this.manifoldPtr != null) && (this.ownManifold)) {
      paramObjectArrayList.add(this.manifoldPtr);
    }
  }
  
  public void init(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo)
  {
    super.init(paramCollisionAlgorithmConstructionInfo);
  }
  
  public void init(PersistentManifold paramPersistentManifold, CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver, boolean paramBoolean)
  {
    super.init(paramCollisionAlgorithmConstructionInfo);
    this.manifoldPtr = paramPersistentManifold;
    this.field_264 = ((CubeConvexVariableSet)threadLocal.get());
    this.gjkPairDetector = new GjkPairDetectorExt(this.field_264.gjkVars);
    this.gjkPairDetector.init(null, null, paramSimplexSolverInterface, paramConvexPenetrationDepthSolver);
    if (this.manifoldPtr == null)
    {
      if (!paramBoolean) {
        this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
      } else {
        this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject2, paramCollisionObject1);
      }
      this.ownManifold = true;
    }
    this.outerSegmentHandler = new CubeConvexCollisionAlgorithm.OuterSegmentHandler(this, null);
  }
  
  public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    if (this.manifoldPtr == null) {
      this.ownManifold = true;
    }
    this.manifoldPtr = paramManifoldResult.getPersistentManifold();
    this.ownManifold = true;
    long l = System.currentTimeMillis();
    this.currentTime = l;
    if (((paramCollisionObject2 = paramCollisionObject2) instanceof PairCachingGhostObjectUncollidable)) {
      return;
    }
    this.field_264.positionCache.clear();
    this.field_264.blockInfoCache.clear();
    paramManifoldResult.setPersistentManifold(this.manifoldPtr);
    Object localObject1 = (CubeShape)paramCollisionObject1.getCollisionShape();
    ConvexShape localConvexShape = (ConvexShape)paramCollisionObject2.getCollisionShape();
    this.onServer = ((CubeShape)localObject1).getSegmentBuffer().a12().isOnServer();
    this.field_264.oSet = ArrayOctree.getSet(((CubeShape)localObject1).getSegmentBuffer().a12().isOnServer());
    this.field_264.cubeMeshTransform = paramCollisionObject1.getWorldTransform(this.field_264.cubeMeshTransform);
    this.field_264.convexShapeTransform = paramCollisionObject2.getWorldTransform(this.field_264.convexShapeTransform);
    this.field_264.absolute.set(this.field_264.cubeMeshTransform.basis);
    MatrixUtil.absolute(this.field_264.absolute);
    ((CubeShape)localObject1).setMargin(0.05F);
    ((CubeShape)localObject1).getAabb(this.field_264.cubeMeshTransform, this.field_264.outMin, this.field_264.outMax);
    localConvexShape.getAabb(this.field_264.convexShapeTransform, this.field_264.shapeMin, this.field_264.shapeMax);
    if (!AabbUtil2.testAabbAgainstAabb2(this.field_264.outMin, this.field_264.outMax, this.field_264.shapeMin, this.field_264.shapeMax)) {
      return;
    }
    ((CubeShape)localObject1).setMargin(0.05F);
    this.outerSegmentHandler.col1 = paramCollisionObject2;
    this.outerSegmentHandler.cubeShape0 = ((CubeShape)localObject1);
    this.outerSegmentHandler.dispatchInfo = paramDispatcherInfo;
    this.outerSegmentHandler.resultOut = paramManifoldResult;
    this.field_264.outer.field_1273.set(this.field_264.outMin);
    this.field_264.outer.field_1274.set(this.field_264.outMax);
    this.field_264.inner.field_1273.set(this.field_264.shapeMin);
    this.field_264.inner.field_1274.set(this.field_264.shapeMax);
    if ((this.field_264.inner.a5(this.field_264.outer, this.field_264.outBB) == null) || (!this.field_264.outBB.b1())) {
      return;
    }
    paramCollisionObject1.getWorldTransform(this.field_264.inv);
    this.field_264.inv.inverse();
    AabbUtil2.transformAabb(new Vector3f(this.field_264.outBB.field_1273), new Vector3f(this.field_264.outBB.field_1274), 0.5F, this.field_264.inv, this.field_264.outBB.field_1273, this.field_264.outBB.field_1274);
    this.field_264.minIntA.field_475 = (ByteUtil.a((int)(this.field_264.outBB.field_1273.field_615 - 8.0F)) << 4);
    this.field_264.minIntA.field_476 = (ByteUtil.a((int)(this.field_264.outBB.field_1273.field_616 - 8.0F)) << 4);
    this.field_264.minIntA.field_477 = (ByteUtil.a((int)(this.field_264.outBB.field_1273.field_617 - 8.0F)) << 4);
    this.field_264.maxIntA.field_475 = (FastMath.b4((this.field_264.outBB.field_1274.field_615 + 8.0F) / 16.0F) << 4);
    this.field_264.maxIntA.field_476 = (FastMath.b4((this.field_264.outBB.field_1274.field_616 + 8.0F) / 16.0F) << 4);
    this.field_264.maxIntA.field_477 = (FastMath.b4((this.field_264.outBB.field_1274.field_617 + 8.0F) / 16.0F) << 4);
    ((CubeShape)localObject1).getSegmentBuffer().b4(this.outerSegmentHandler, this.field_264.minIntA, this.field_264.maxIntA);
    paramCollisionObject1 = this.field_264.positionCache.size();
    for (paramCollisionObject2 = 0; paramCollisionObject2 < paramCollisionObject1; paramCollisionObject2++)
    {
      localObject1 = (Transform)this.field_264.positionCache.get(paramCollisionObject2);
      class_48 localclass_48;
      int i = (localclass_48 = (class_48)this.field_264.blockInfoCache.get(paramCollisionObject2)).field_475;
      int j = localclass_48.field_476;
      boolean bool = localclass_48.field_477 == 1;
      Object localObject2 = this.field_264.box0;
      if ((i > 0) && (i < 3)) {
        localObject2 = class_384.a6(i, (byte)j, bool);
      }
      try
      {
        doRegularCollision((ConvexShape)localObject2, localConvexShape, (Transform)localObject1, this.field_264.convexShapeTransform, paramManifoldResult, this.manifoldPtr, paramDispatcherInfo);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        System.err.println(this.field_264.box0 + ", " + localConvexShape + ", " + localObject1 + ", " + this.field_264.convexShapeTransform + ", " + paramManifoldResult + ", " + paramDispatcherInfo);
      }
    }
    if (this.ownManifold)
    {
      paramManifoldResult.refreshContactPoints();
      return;
    }
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
  }
  
  public void processDistinctCollision(CubeShape paramCubeShape, CollisionObject paramCollisionObject, SegmentData paramSegmentData, Transform paramTransform1, Transform paramTransform2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    if (!this.field_264.intersectionCallBackAwithB.initialized) {
      this.field_264.intersectionCallBackAwithB.createHitCache(512);
    }
    paramCollisionObject.getCollisionShape().getAabb(paramTransform2, this.field_264.othermin, this.field_264.othermax);
    this.field_264.intersectionCallBackAwithB.reset();
    this.field_264.intersectionCallBackAwithB = paramSegmentData.getOctree().findIntersecting(this.field_264.oSet, this.field_264.intersectionCallBackAwithB, paramSegmentData.getSegment(), paramTransform1, this.field_264.absolute, 0.05F, this.field_264.othermin, this.field_264.othermax, 1.0F);
    if (this.field_264.intersectionCallBackAwithB.hitCount == 0)
    {
      paramManifoldResult.refreshContactPoints();
      return;
    }
    for (paramCubeShape = 0; paramCubeShape < this.field_264.intersectionCallBackAwithB.hitCount; paramCubeShape++)
    {
      this.field_264.intersectionCallBackAwithB.getHit(paramCubeShape, this.field_264.hitMin, this.field_264.hitMax, this.field_264.startA, this.field_264.endA);
      assert ((this.field_264.startA.field_453 < this.field_264.endA.field_453) && (this.field_264.startA.field_454 < this.field_264.endA.field_454) && (this.field_264.startA.field_455 < this.field_264.endA.field_455));
      doNarrowTest(paramSegmentData, paramCollisionObject, this.field_264.startA, this.field_264.endA, paramDispatcherInfo, paramManifoldResult);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */