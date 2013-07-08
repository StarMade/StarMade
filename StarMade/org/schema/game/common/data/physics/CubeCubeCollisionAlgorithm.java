package org.schema.game.common.data.physics;

import class_1057;
import class_1403;
import class_1419;
import class_35;
import class_384;
import class_48;
import class_796;
import class_886;
import class_988;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.ManifoldResult;
import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CompoundShapeChild;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import com.bulletphysics.util.ObjectPool;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
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
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public class CubeCubeCollisionAlgorithm
  extends CollisionAlgorithm
{
  private int handles = 0;
  protected final ObjectPool pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
  private GjkPairDetectorExt gjkPairDetector;
  private BoxBoxDetector boxboxPairDetector = new BoxBoxDetector();
  private int slow_threashold = ((Integer)class_1057.field_1335.a3()).intValue();
  public boolean lowLevelOfDetail;
  private boolean ownManifold;
  private PersistentManifold manifoldPtr;
  private CubeCubeCollisionAlgorithm.OuterSegmentHandler outerSegmentHandler;
  private CubeCubeCollisionAlgorithm.InnerSegmentHandler innerSegmentHandler;
  static float margin = 0.05F;
  private static ThreadLocal threadLocal = new CubeCubeCollisionAlgorithm.1();
  public CubeCubeCollisionVariableSet field_264;
  private int aabbBlockOverlapping;
  private int narrowTime;
  private long octATreeTime;
  private int leaftCallsA;
  private long leafRetrieve;
  private long leafAABBTest;
  private long currentTime;
  public boolean localSwap;
  private boolean stuck;
  private float currentMaxDepth;
  private long stuckStarted;
  
  public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    this.handles = 0;
    this.stuck = false;
    if ((!(paramCollisionObject1.getCollisionShape() instanceof CubeShape)) || (!(paramCollisionObject2.getCollisionShape() instanceof CubeShape))) {
      return;
    }
    if ((paramCollisionObject1.isStaticObject()) && (paramCollisionObject2.isStaticObject())) {
      return;
    }
    if (this.localSwap)
    {
      System.err.println("LOCAL SWAP IN CUBE CUBE");
      localObject = paramCollisionObject1;
      paramCollisionObject1 = paramCollisionObject2;
      paramCollisionObject2 = (CollisionObject)localObject;
    }
    this.currentTime = System.currentTimeMillis();
    this.narrowTime = 0;
    this.octATreeTime = 0L;
    this.aabbBlockOverlapping = 0;
    this.leaftCallsA = 0;
    this.leafRetrieve = 0L;
    this.leafAABBTest = 0L;
    Object localObject = (CubeShape)paramCollisionObject1.getCollisionShape();
    CubeShape localCubeShape = (CubeShape)paramCollisionObject2.getCollisionShape();
    if (localObject == localCubeShape)
    {
      System.err.println("EUALCOL " + ((CubeShape)localObject).getSegmentBuffer().a12());
      return;
    }
    assert (((CubeShape)localObject).getSegmentBuffer().a12().getPhysicsDataContainer().getShapeChild().childShape == localObject) : (((CubeShape)localObject).getSegmentBuffer().a12().getPhysicsDataContainer().getShapeChild().childShape + " -- " + localObject);
    assert (localCubeShape.getSegmentBuffer().a12().getPhysicsDataContainer().getShapeChild().childShape == localCubeShape) : (localCubeShape.getSegmentBuffer().a12().getPhysicsDataContainer().getShapeChild().childShape + " -- " + localCubeShape);
    PhysicsExt localPhysicsExt;
    if (!(localPhysicsExt = ((CubeShape)localObject).getSegmentBuffer().a12().getPhysics()).getPhysicsExceptions().isEmpty()) {
      for (i = 0; i < localPhysicsExt.getPhysicsExceptions().size(); i++)
      {
        class_1403 localclass_1403;
        if ((((localclass_1403 = (class_1403)localPhysicsExt.getPhysicsExceptions().get(i)).field_1620 == paramCollisionObject1) && (localclass_1403.field_1621 == paramCollisionObject2)) || ((localclass_1403.field_1620 == paramCollisionObject2) && (localclass_1403.field_1621 == paramCollisionObject1))) {
          return;
        }
      }
    }
    this.field_264.oSet = ArrayOctree.getSet(((CubeShape)localObject).getSegmentBuffer().a12().isOnServer());
    this.field_264.tmpTrans1 = paramCollisionObject1.getWorldTransform(this.field_264.tmpTrans1);
    this.field_264.tmpTrans2 = paramCollisionObject2.getWorldTransform(this.field_264.tmpTrans2);
    this.field_264.absolute1.set(this.field_264.tmpTrans1.basis);
    MatrixUtil.absolute(this.field_264.absolute1);
    this.field_264.absolute2.set(this.field_264.tmpTrans2.basis);
    MatrixUtil.absolute(this.field_264.absolute2);
    ((CubeShape)localObject).getSegmentBuffer().a12().onProximity(localCubeShape.getSegmentBuffer().a12());
    localCubeShape.getSegmentBuffer().a12().onProximity(((CubeShape)localObject).getSegmentBuffer().a12());
    CubeCubeCollisionAlgorithm.OuterSegmentHandler.access$402(this.outerSegmentHandler, (CubeShape)localObject);
    CubeCubeCollisionAlgorithm.OuterSegmentHandler.access$502(this.outerSegmentHandler, localCubeShape);
    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$602(this.innerSegmentHandler, (CubeShape)localObject);
    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$702(this.innerSegmentHandler, localCubeShape);
    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$802(this.innerSegmentHandler, paramDispatcherInfo);
    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$902(this.innerSegmentHandler, paramManifoldResult);
    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1002(this.innerSegmentHandler, 0);
    CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1102(this.innerSegmentHandler, 0L);
    ((CubeShape)localObject).getAabb(this.field_264.tmpTrans1, this.field_264.outer.field_1273, this.field_264.outer.field_1274);
    localCubeShape.getAabb(this.field_264.tmpTrans2, this.field_264.inner.field_1273, this.field_264.inner.field_1274);
    if (this.field_264.inner.a5(this.field_264.outer, this.field_264.outBB) == null) {
      return;
    }
    this.field_264.outBBCopy.a9(this.field_264.outBB);
    paramCollisionObject1.getWorldTransform(this.field_264.wtInv0);
    this.field_264.wtInv0.inverse();
    AabbUtil2.transformAabb(this.field_264.outBB.field_1273, this.field_264.outBB.field_1274, 0.5F, this.field_264.wtInv0, this.field_264.outBB.field_1273, this.field_264.outBB.field_1274);
    this.field_264.minIntA.field_475 = (ByteUtil.a((int)(this.field_264.outBB.field_1273.field_615 - 8.0F)) << 4);
    this.field_264.minIntA.field_476 = (ByteUtil.a((int)(this.field_264.outBB.field_1273.field_616 - 8.0F)) << 4);
    this.field_264.minIntA.field_477 = (ByteUtil.a((int)(this.field_264.outBB.field_1273.field_617 - 8.0F)) << 4);
    this.field_264.maxIntA.field_475 = (FastMath.b4((this.field_264.outBB.field_1274.field_615 + 8.0F) / 16.0F) << 4);
    this.field_264.maxIntA.field_476 = (FastMath.b4((this.field_264.outBB.field_1274.field_616 + 8.0F) / 16.0F) << 4);
    this.field_264.maxIntA.field_477 = (FastMath.b4((this.field_264.outBB.field_1274.field_617 + 8.0F) / 16.0F) << 4);
    this.field_264.outBB.a9(this.field_264.outBBCopy);
    paramCollisionObject2.getWorldTransform(this.field_264.wtInv1);
    this.field_264.wtInv1.inverse();
    AabbUtil2.transformAabb(this.field_264.outBB.field_1273, this.field_264.outBB.field_1274, 0.5F, this.field_264.wtInv1, this.field_264.outBB.field_1273, this.field_264.outBB.field_1274);
    this.field_264.minIntB.field_475 = (ByteUtil.a((int)(this.field_264.outBB.field_1273.field_615 - 8.0F)) << 4);
    this.field_264.minIntB.field_476 = (ByteUtil.a((int)(this.field_264.outBB.field_1273.field_616 - 8.0F)) << 4);
    this.field_264.minIntB.field_477 = (ByteUtil.a((int)(this.field_264.outBB.field_1273.field_617 - 8.0F)) << 4);
    this.field_264.maxIntB.field_475 = (FastMath.b4((this.field_264.outBB.field_1274.field_615 + 8.0F) / 16.0F) << 4);
    this.field_264.maxIntB.field_476 = (FastMath.b4((this.field_264.outBB.field_1274.field_616 + 8.0F) / 16.0F) << 4);
    this.field_264.maxIntB.field_477 = (FastMath.b4((this.field_264.outBB.field_1274.field_617 + 8.0F) / 16.0F) << 4);
    paramDispatcherInfo = Math.abs(ByteUtil.a(this.field_264.maxIntA.field_475) - ByteUtil.a(this.field_264.minIntA.field_475)) * Math.abs(ByteUtil.a(this.field_264.maxIntA.field_476) - ByteUtil.a(this.field_264.minIntA.field_476)) * Math.abs(ByteUtil.a(this.field_264.maxIntA.field_477) - ByteUtil.a(this.field_264.minIntA.field_477));
    DispatcherInfo localDispatcherInfo = Math.abs(ByteUtil.a(this.field_264.maxIntB.field_475) - ByteUtil.a(this.field_264.minIntB.field_475)) * Math.abs(ByteUtil.a(this.field_264.maxIntB.field_476) - ByteUtil.a(this.field_264.minIntB.field_476)) * Math.abs(ByteUtil.a(this.field_264.maxIntB.field_477) - ByteUtil.a(this.field_264.minIntB.field_477));
    long l2 = System.currentTimeMillis();
    try
    {
      ((CubeShape)localObject).getSegmentBuffer().b4(this.outerSegmentHandler, this.field_264.minIntA, this.field_264.maxIntA);
    }
    catch (Exception localException)
    {
      localException;
    }
    int i = CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1000(this.innerSegmentHandler);
    long l1 = CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1100(this.innerSegmentHandler);
    int j = (int)(System.currentTimeMillis() - l2);
    if (this.stuck)
    {
      StateInterface localStateInterface = ((CubeShape)localObject).getSegmentBuffer().a12().getState();
      System.err.println("[CC] " + localStateInterface + " getSObjects detected stuck: " + ((CubeShape)localObject).getSegmentBuffer().a12() + " <-> " + localCubeShape.getSegmentBuffer().a12());
      if (paramCollisionObject1.isStaticOrKinematicObject())
      {
        System.err.println("[CC] " + localStateInterface + " Objects detected stuck --- warping out: " + localCubeShape.getSegmentBuffer().a12());
        localCubeShape.getSegmentBuffer().a12().setImmediateStuck(true);
      }
      else
      {
        System.err.println("[CC] " + localStateInterface + " Objects detected stuck --- warping out: " + ((CubeShape)localObject).getSegmentBuffer().a12());
        ((CubeShape)localObject).getSegmentBuffer().a12().setImmediateStuck(true);
      }
    }
    if (j > 15)
    {
      if (this.aabbBlockOverlapping > ((CubeShape)localObject).getSegmentBuffer().a12().getTotalElements() << 1)
      {
        if (!paramCollisionObject1.isStaticObject())
        {
          ((CubeShape)localObject).getSegmentBuffer().a12().flagPhysicsSlowdown();
          break label1931;
        }
      }
      else
      {
        if (this.aabbBlockOverlapping <= localCubeShape.getSegmentBuffer().a12().getTotalElements() << 1) {
          break label1931;
        }
        if (paramCollisionObject2.isStaticObject())
        {
          ((CubeShape)localObject).getSegmentBuffer().a12().flagPhysicsSlowdown();
          break label1931;
        }
      }
      localCubeShape.getSegmentBuffer().a12().flagPhysicsSlowdown();
      label1931:
      System.err.println("[CUBECUBE] CUBECUBE COL: " + j + " ms; NarTests: " + i + "; overlappingSingleBlockAABBs: " + this.aabbBlockOverlapping + "; handle: " + this.handles + " of MAX " + paramDispatcherInfo * localDispatcherInfo + "; DistiTime: " + l1 / 1000000L + ";NarrTime: " + this.narrowTime / 1000000 + "; Oct: " + this.octATreeTime / 1000000L + " call(" + this.leaftCallsA + ") retr(" + this.leafRetrieve / 1000000L + ") aabb(" + this.leafAABBTest / 1000000L + "); on " + ((CubeShape)localObject).getSegmentBuffer().a12() + ": COUNT " + ((CubeShape)localObject).getSegmentBuffer().c() + "; TOTEl " + ((CubeShape)localObject).getSegmentBuffer().a12().getTotalElements() + "; " + localCubeShape.getSegmentBuffer().a12() + ": COUNT " + localCubeShape.getSegmentBuffer().c() + "; TOTEl " + localCubeShape.getSegmentBuffer().a12().getTotalElements() + " on " + localCubeShape.getSegmentBuffer().a12().getState() + ";OverlappingBOX A " + this.field_264.minIntA + "-" + this.field_264.maxIntA + "; B " + this.field_264.minIntB + "-" + this.field_264.maxIntB);
      System.err.println("[CC]INFO: " + paramCollisionObject1 + ": " + paramCollisionObject1.getWorldTransform(new Transform()).origin + "; " + paramCollisionObject2 + ": " + paramCollisionObject2.getWorldTransform(new Transform()).origin + " UPDATE#: " + ((CubeShape)localObject).getSegmentBuffer().a12().getState().getUpdateNumber());
    }
    if (this.ownManifold) {
      paramManifoldResult.refreshContactPoints();
    }
  }
  
  public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    return 1.0F;
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
  
  private int doExtCubeCubeCollision(BoxShape paramBoxShape1, BoxShape paramBoxShape2, Transform paramTransform1, Transform paramTransform2, ManifoldResult paramManifoldResult, DispatcherInfo paramDispatcherInfo)
  {
    DiscreteCollisionDetectorInterface.ClosestPointInput localClosestPointInput;
    (localClosestPointInput = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get()).init();
    localClosestPointInput.maximumDistanceSquared = (paramBoxShape1.getMargin() + paramBoxShape2.getMargin() + this.manifoldPtr.getContactBreakingThreshold());
    localClosestPointInput.maximumDistanceSquared *= localClosestPointInput.maximumDistanceSquared;
    localClosestPointInput.transformA.set(paramTransform1);
    localClosestPointInput.transformB.set(paramTransform2);
    this.boxboxPairDetector.GetClosestPoints(paramBoxShape1, paramBoxShape2, localClosestPointInput, paramManifoldResult, paramDispatcherInfo.debugDraw, false);
    this.pointInputsPool.release(localClosestPointInput);
    this.currentMaxDepth = this.boxboxPairDetector.maxDepth;
    return this.boxboxPairDetector.contacts;
  }
  
  private int doNonBlockCollision(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, Transform paramTransform1, Transform paramTransform2, ManifoldResult paramManifoldResult, DispatcherInfo paramDispatcherInfo)
  {
    DiscreteCollisionDetectorInterface.ClosestPointInput localClosestPointInput;
    (localClosestPointInput = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get()).init();
    paramManifoldResult.setPersistentManifold(this.manifoldPtr);
    this.gjkPairDetector.setMinkowskiA(paramConvexShape1);
    this.gjkPairDetector.setMinkowskiB(paramConvexShape2);
    localClosestPointInput.maximumDistanceSquared = (paramConvexShape1.getMargin() + paramConvexShape2.getMargin() + this.manifoldPtr.getContactBreakingThreshold());
    localClosestPointInput.maximumDistanceSquared *= localClosestPointInput.maximumDistanceSquared;
    localClosestPointInput.transformA.set(paramTransform1);
    localClosestPointInput.transformB.set(paramTransform2);
    this.gjkPairDetector.getClosestPoints(localClosestPointInput, paramManifoldResult, paramDispatcherInfo.debugDraw);
    this.pointInputsPool.release(localClosestPointInput);
    this.currentMaxDepth = this.gjkPairDetector.maxDepth;
    return this.gjkPairDetector.contacts;
  }
  
  private void doNarrowTest(SegmentData paramSegmentData1, SegmentData paramSegmentData2, class_35 paramclass_351, class_35 paramclass_352, class_35 paramclass_353, class_35 paramclass_354, int paramInt1, int paramInt2, class_988 paramclass_988, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    paramclass_352 = org.schema.game.common.data.physics.octree.ArrayOctreeTraverse.tMap[paramInt1];
    paramclass_354 = org.schema.game.common.data.physics.octree.ArrayOctreeTraverse.tMap[paramInt2];
    for (paramInt1 = 0; paramInt1 < paramclass_352.length; paramInt1++)
    {
      paramInt2 = (byte)(paramclass_351.field_453 + paramclass_352[paramInt1][0]);
      int i = (byte)(paramclass_351.field_454 + paramclass_352[paramInt1][1]);
      int k = (byte)(paramclass_351.field_455 + paramclass_352[paramInt1][2]);
      int m = SegmentData.getInfoIndex((byte)(paramInt2 + 8), (byte)(i + 8), (byte)(k + 8));
      int n = 0;
      paramManifoldResult.getPersistentManifold().getNumContacts();
      ElementInformation localElementInformation1;
      if ((paramSegmentData1.containsFast(m)) && ((localElementInformation1 = ElementKeyMap.getInfo(paramSegmentData1.getType(m))).isPhysical(paramSegmentData1.isActive(m))))
      {
        byte b1 = paramSegmentData1.getOrientation(m);
        boolean bool1 = paramSegmentData1.isActive(m);
        this.field_264.elemPosA.set(paramInt2 + paramSegmentData1.getSegment().field_34.field_475, i + paramSegmentData1.getSegment().field_34.field_476, k + paramSegmentData1.getSegment().field_34.field_477);
        this.field_264.elemPosAAbs.set(this.field_264.elemPosA);
        this.field_264.tmpTrans1.transform(this.field_264.elemPosAAbs);
        this.field_264.min.set(this.field_264.elemPosA.field_615 - 0.5F, this.field_264.elemPosA.field_616 - 0.5F, this.field_264.elemPosA.field_617 - 0.5F);
        this.field_264.max.set(this.field_264.elemPosA.field_615 + 0.5F, this.field_264.elemPosA.field_616 + 0.5F, this.field_264.elemPosA.field_617 + 0.5F);
        CubeShape.transformAabb(this.field_264.min, this.field_264.max, margin, this.field_264.tmpTrans1, this.field_264.minOut, this.field_264.maxOut, this.field_264.aabbVarSet, this.field_264.absolute1);
        if (AabbUtil2.testAabbAgainstAabb2(paramclass_988.field_1273, paramclass_988.field_1274, this.field_264.minOut, this.field_264.maxOut)) {
          for (paramInt2 = 0; paramInt2 < paramclass_354.length; paramInt2++)
          {
            i = (byte)(paramclass_353.field_453 + paramclass_354[paramInt2][0]);
            k = (byte)(paramclass_353.field_454 + paramclass_354[paramInt2][1]);
            int i1 = (byte)(paramclass_353.field_455 + paramclass_354[paramInt2][2]);
            int i2 = SegmentData.getInfoIndex((byte)(i + 8), (byte)(k + 8), (byte)(i1 + 8));
            ElementInformation localElementInformation2;
            if ((paramSegmentData2.containsFast(i2)) && ((localElementInformation2 = ElementKeyMap.getInfo(paramSegmentData2.getType(i2))).isPhysical(paramSegmentData2.isActive(i2))))
            {
              byte b2 = paramSegmentData2.getOrientation(i2);
              boolean bool2 = paramSegmentData2.isActive(i2);
              this.field_264.elemPosB.set(i + paramSegmentData2.getSegment().field_34.field_475, k + paramSegmentData2.getSegment().field_34.field_476, i1 + paramSegmentData2.getSegment().field_34.field_477);
              this.field_264.elemPosBAbs.set(this.field_264.elemPosB);
              this.field_264.tmpTrans2.transform(this.field_264.elemPosBAbs);
              this.field_264.elemPosBAbs.sub(this.field_264.elemPosAAbs);
              if ((this.field_264.elemPosBAbs.length() < 1.5F ? 1 : 0) != 0)
              {
                this.aabbBlockOverlapping += 1;
                this.field_264.tmpTrans3.set(this.field_264.tmpTrans1);
                this.field_264.tmpTrans4.set(this.field_264.tmpTrans2);
                this.field_264.field_1867.set(this.field_264.elemPosA);
                this.field_264.field_1868.set(this.field_264.elemPosB);
                this.field_264.tmpTrans3.basis.transform(this.field_264.field_1867);
                this.field_264.tmpTrans4.basis.transform(this.field_264.field_1868);
                this.field_264.tmpTrans3.origin.add(this.field_264.field_1867);
                this.field_264.tmpTrans4.origin.add(this.field_264.field_1868);
                paramSegmentData1.getSegment();
                Segment.d();
                paramSegmentData2.getSegment();
                Segment.d();
                this.field_264.box0.setMargin(margin);
                this.field_264.box1.setMargin(margin);
                Object localObject1 = this.field_264.box0;
                Object localObject2 = this.field_264.box1;
                i1 = 0;
                if ((localElementInformation1.getBlockStyle() > 0) && (localElementInformation1.getBlockStyle() < 3))
                {
                  localObject1 = class_384.a6(localElementInformation1.getBlockStyle(), b1, bool1);
                  i1 = 1;
                }
                if ((localElementInformation2.getBlockStyle() > 0) && (localElementInformation2.getBlockStyle() < 3))
                {
                  localObject2 = class_384.a6(localElementInformation2.getBlockStyle(), b2, bool2);
                  i1 = 1;
                }
                this.currentMaxDepth = 0.0F;
                int j;
                if (i1 != 0) {
                  j = doNonBlockCollision((ConvexShape)localObject1, (ConvexShape)localObject2, this.field_264.tmpTrans3, this.field_264.tmpTrans4, paramManifoldResult, paramDispatcherInfo);
                } else {
                  j = doExtCubeCubeCollision(this.field_264.box0, this.field_264.box1, this.field_264.tmpTrans3, this.field_264.tmpTrans4, paramManifoldResult, paramDispatcherInfo);
                }
                if (j > 0)
                {
                  paramManifoldResult.getPersistentManifold().getNumContacts();
                  n++;
                }
                if ((!this.stuck) && (this.currentMaxDepth > 0.8F))
                {
                  System.err.println("CHECKING STUCK " + paramSegmentData1.getSegment().a15() + " -> " + paramSegmentData2.getSegment().a15() + ": " + n + "; CC " + paramManifoldResult.getPersistentManifold().getNumContacts() + "; " + this.currentMaxDepth);
                  checkStuck(paramSegmentData1, paramSegmentData2, this.field_264.tmpTrans1, this.field_264.wtInv1, this.field_264.elemPosA);
                  System.err.println("CHECKING STUCK " + paramSegmentData2.getSegment().a15() + " -> " + paramSegmentData1.getSegment().a15() + ": " + n + "; CC " + paramManifoldResult.getPersistentManifold().getNumContacts() + "; " + this.currentMaxDepth);
                  checkStuck(paramSegmentData2, paramSegmentData1, this.field_264.tmpTrans2, this.field_264.wtInv0, this.field_264.elemPosB);
                }
              }
            }
          }
        }
      }
    }
  }
  
  private void checkStuck(SegmentData paramSegmentData1, SegmentData paramSegmentData2, Transform paramTransform1, Transform paramTransform2, Vector3f paramVector3f)
  {
    this.field_264.elemPosTest.set(paramVector3f.field_615, paramVector3f.field_616, paramVector3f.field_617);
    paramTransform1.transform(this.field_264.elemPosTest);
    paramTransform2.transform(this.field_264.elemPosTest);
    this.field_264.elemPosCheck.b((int)this.field_264.elemPosTest.field_615 + 8, (int)this.field_264.elemPosTest.field_616 + 8, (int)this.field_264.elemPosTest.field_617 + 8);
    paramTransform1 = 0;
    try
    {
      for (paramTransform2 = 0; paramTransform2 < 6; paramTransform2++)
      {
        this.field_264.elemPosCheckD.b2(this.field_264.elemPosCheck, org.schema.game.common.data.element.Element.DIRECTIONSi[paramTransform2]);
        if (((paramVector3f = paramSegmentData2.getSegmentController().getSegmentBuffer().a10(this.field_264.elemPosCheckD, false, this.field_264.pieceTmp)) != null) && (paramVector3f.a9() != 0)) {
          paramTransform1++;
        }
      }
      if (((paramTransform2 = paramSegmentData2.getSegmentController().getSegmentBuffer().a10(this.field_264.elemPosCheck, false, this.field_264.pieceTmp)) != null) && (paramTransform2.a9() != 0)) {
        paramTransform1++;
      }
      if (paramTransform1 >= 7)
      {
        System.err.println("[CC]POSSIBLE STUCK: " + paramSegmentData1.getSegmentController() + " <-> " + paramSegmentData2.getSegmentController() + ": from " + this.field_264.elemPosTest + " to " + this.field_264.elemPosCheck + "; existing surround: " + paramTransform1);
        if ((this.stuckStarted == 0L) || (System.currentTimeMillis() - this.stuckStarted > 10000L))
        {
          this.stuckStarted = System.currentTimeMillis();
          return;
        }
        if (System.currentTimeMillis() - this.stuckStarted > 5000L) {
          this.stuck = true;
        }
      }
      else
      {
        System.err.println("[CC]POSSIBLE NOT STUCK: " + paramSegmentData1.getSegmentController() + " <-> " + paramSegmentData2.getSegmentController() + ": from " + this.field_264.elemPosTest + " to " + this.field_264.elemPosCheck + "; existing surround: " + paramTransform1);
      }
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
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
  
  public void init(PersistentManifold paramPersistentManifold, CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, SimplexSolverInterface paramSimplexSolverInterface, GjkEpaPenetrationDepthSolver paramGjkEpaPenetrationDepthSolver)
  {
    super.init(paramCollisionAlgorithmConstructionInfo);
    this.field_264 = ((CubeCubeCollisionVariableSet)threadLocal.get());
    this.gjkPairDetector = new GjkPairDetectorExt(this.field_264.gjkVar);
    this.outerSegmentHandler = new CubeCubeCollisionAlgorithm.OuterSegmentHandler(this, null);
    this.innerSegmentHandler = new CubeCubeCollisionAlgorithm.InnerSegmentHandler(this, null);
    this.manifoldPtr = paramPersistentManifold;
    this.gjkPairDetector.init(null, null, paramSimplexSolverInterface, paramGjkEpaPenetrationDepthSolver);
    if (this.manifoldPtr == null)
    {
      this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
      this.ownManifold = true;
    }
  }
  
  private void processDistinctCollision(CubeShape paramCubeShape1, CubeShape paramCubeShape2, SegmentData paramSegmentData1, SegmentData paramSegmentData2, Transform paramTransform1, Transform paramTransform2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    if (!this.field_264.intersectionCallBackAwithB.initialized)
    {
      this.field_264.intersectionCallBackAwithB.createHitCache(512);
      this.field_264.intersectionCallBackBwithA.createHitCache(512);
    }
    this.field_264.intersectionCallBackAwithB.reset();
    this.field_264.intersectionCallBackBwithA.reset();
    long l1 = System.nanoTime();
    this.field_264.intersectionCallBackAwithB = paramSegmentData1.getOctree().findIntersecting(this.field_264.oSet, this.field_264.intersectionCallBackAwithB, paramSegmentData1.getSegment(), paramTransform1, this.field_264.absolute1, margin, this.field_264.bbSectorIntersection.field_1273, this.field_264.bbSectorIntersection.field_1274, 1.0F);
    this.leafAABBTest += this.field_264.intersectionCallBackAwithB.aabbTest;
    this.leafRetrieve += this.field_264.intersectionCallBackAwithB.aabbRetrieve;
    this.leaftCallsA += this.field_264.intersectionCallBackAwithB.leafCalcs;
    if (this.field_264.intersectionCallBackAwithB.hitCount == 0) {
      return;
    }
    this.field_264.intersectionCallBackBwithA.reset();
    this.field_264.oSet.debug = paramSegmentData2.getSegmentController().isOnServer();
    this.field_264.intersectionCallBackBwithA = paramSegmentData2.getOctree().findIntersecting(this.field_264.oSet, this.field_264.intersectionCallBackBwithA, paramSegmentData2.getSegment(), paramTransform2, this.field_264.absolute2, margin, this.field_264.bbSectorIntersection.field_1273, this.field_264.bbSectorIntersection.field_1274, 1.0F);
    this.field_264.oSet.debug = false;
    if (this.field_264.intersectionCallBackBwithA.hitCount == 0) {
      return;
    }
    for (paramCubeShape1 = 0; paramCubeShape1 < this.field_264.intersectionCallBackAwithB.hitCount; paramCubeShape1++)
    {
      paramCubeShape2 = this.field_264.intersectionCallBackAwithB.getHit(paramCubeShape1, this.field_264.bbOuterOct.field_1273, this.field_264.bbOuterOct.field_1274, this.field_264.startA, this.field_264.endA);
      for (paramTransform1 = 0; paramTransform1 < this.field_264.intersectionCallBackBwithA.hitCount; paramTransform1++)
      {
        paramTransform2 = this.field_264.intersectionCallBackBwithA.getHit(paramTransform1, this.field_264.bbInnerOct.field_1273, this.field_264.bbInnerOct.field_1274, this.field_264.startB, this.field_264.endB);
        if (AabbUtil2.testAabbAgainstAabb2(this.field_264.bbInnerOct.field_1273, this.field_264.bbInnerOct.field_1274, this.field_264.bbOuterOct.field_1273, this.field_264.bbOuterOct.field_1274))
        {
          this.field_264.bbInnerOct.a5(this.field_264.bbOuterOct, this.field_264.bbOctIntersection);
          long l2 = System.nanoTime();
          doNarrowTest(paramSegmentData1, paramSegmentData2, this.field_264.startA, this.field_264.endA, this.field_264.startB, this.field_264.endB, paramCubeShape2, paramTransform2, this.field_264.bbOctIntersection, paramDispatcherInfo, paramManifoldResult);
          this.narrowTime = ((int)(this.narrowTime + (System.nanoTime() - l2)));
        }
      }
    }
    this.octATreeTime += System.nanoTime() - l1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */