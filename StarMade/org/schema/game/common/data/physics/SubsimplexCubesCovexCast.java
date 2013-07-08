package org.schema.game.common.data.physics;

import class_35;
import class_384;
import class_46;
import class_48;
import class_798;
import class_886;
import class_988;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
import com.bulletphysics.collision.dispatch.ManifoldResult;
import com.bulletphysics.collision.narrowphase.ConvexCast;
import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
import com.bulletphysics.collision.narrowphase.GjkConvexCast;
import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectPool;
import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import o;
import org.lwjgl.input.Keyboard;
import org.schema.common.FastMath;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ShieldContainerInterface;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
import org.schema.game.common.controller.elements.shield.ShieldUnit;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.octree.ArrayOctree;
import org.schema.game.common.data.physics.octree.IntersectionCallback;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import s;

public class SubsimplexCubesCovexCast
  extends ConvexCast
{
  private static ThreadLocal threadLocal = new SubsimplexCubesCovexCast.1();
  public static String mode;
  ObjectPool pool = ObjectPool.get(o.class);
  ObjectPool pool4 = ObjectPool.get(s.class);
  ObjectPool aabbpool = ObjectPool.get(AABBb.class);
  private CubeConvexCastVariableSet field_84 = (CubeConvexCastVariableSet)threadLocal.get();
  CubeRayCastResult rayResult;
  CollisionWorld.ConvexResultCallback resultCallback;
  private SubsimplexCubesCovexCast.OuterSegmentHandler outerSegmentHandler;
  private long time;
  
  public SubsimplexCubesCovexCast(ConvexShape paramConvexShape, CollisionShape paramCollisionShape, CollisionObject paramCollisionObject, SimplexSolverInterface paramSimplexSolverInterface, CollisionWorld.ConvexResultCallback paramConvexResultCallback, CubeRayCastResult paramCubeRayCastResult)
  {
    this.field_84.shapeA = paramConvexShape;
    this.field_84.cubesB = ((CubeShape)paramCollisionShape);
    this.field_84.cubesObject = paramCollisionObject;
    this.resultCallback = paramConvexResultCallback;
    this.field_84.simplexSolver = paramSimplexSolverInterface;
    this.rayResult = paramCubeRayCastResult;
    this.outerSegmentHandler = new SubsimplexCubesCovexCast.OuterSegmentHandler(this, null);
  }
  
  public boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult)
  {
    this.field_84.sorted.clear();
    this.time = System.currentTimeMillis();
    this.field_84.shapeA.getAabb(paramTransform1, this.field_84.convexFromAABBMin, this.field_84.convexFromAABBMax);
    this.field_84.shapeA.getAabb(paramTransform2, this.field_84.convexToAABBMin, this.field_84.convexToAABBMax);
    combineAabb(this.field_84.convexFromAABBMin, this.field_84.convexFromAABBMax, this.field_84.convexToAABBMin, this.field_84.convexToAABBMax, this.field_84.castedAABBMin, this.field_84.castedAABBMax);
    this.field_84.castedAABBMin.sub(new Vector3f(0.2F, 0.2F, 0.2F));
    this.field_84.castedAABBMax.add(new Vector3f(0.2F, 0.2F, 0.2F));
    SubsimplexCubesCovexCast.OuterSegmentHandler.access$302(this.outerSegmentHandler, paramTransform1);
    SubsimplexCubesCovexCast.OuterSegmentHandler.access$402(this.outerSegmentHandler, paramCastResult);
    SubsimplexCubesCovexCast.OuterSegmentHandler.access$502(this.outerSegmentHandler, paramTransform3);
    SubsimplexCubesCovexCast.OuterSegmentHandler.access$602(this.outerSegmentHandler, paramTransform2);
    SubsimplexCubesCovexCast.OuterSegmentHandler.access$702(this.outerSegmentHandler, false);
    this.field_84.cubesB.getAabb(paramTransform3, this.field_84.outer.field_1273, this.field_84.outer.field_1274);
    this.field_84.inner.field_1273.set(this.field_84.castedAABBMin);
    this.field_84.inner.field_1274.set(this.field_84.castedAABBMax);
    if ((this.field_84.inner.a5(this.field_84.outer, this.field_84.outBB) == null) || (!this.field_84.outBB.b1())) {
      return false;
    }
    this.field_84.inv.set(paramTransform3);
    this.field_84.inv.inverse();
    AabbUtil2.transformAabb(new Vector3f(this.field_84.outBB.field_1273), new Vector3f(this.field_84.outBB.field_1274), 0.5F, this.field_84.inv, this.field_84.outBB.field_1273, this.field_84.outBB.field_1274);
    this.field_84.minIntA.field_475 = (ByteUtil.a((int)(this.field_84.outBB.field_1273.field_615 - 8.0F)) << 4);
    this.field_84.minIntA.field_476 = (ByteUtil.a((int)(this.field_84.outBB.field_1273.field_616 - 8.0F)) << 4);
    this.field_84.minIntA.field_477 = (ByteUtil.a((int)(this.field_84.outBB.field_1273.field_617 - 8.0F)) << 4);
    this.field_84.maxIntA.field_475 = (FastMath.b4((this.field_84.outBB.field_1274.field_615 + 8.0F) / 16.0F) << 4);
    this.field_84.maxIntA.field_476 = (FastMath.b4((this.field_84.outBB.field_1274.field_616 + 8.0F) / 16.0F) << 4);
    this.field_84.maxIntA.field_477 = (FastMath.b4((this.field_84.outBB.field_1274.field_617 + 8.0F) / 16.0F) << 4);
    long l;
    if ((l = (this.field_84.maxIntA.field_475 - this.field_84.minIntA.field_475) * (this.field_84.maxIntA.field_476 - this.field_84.minIntA.field_476) * (this.field_84.maxIntA.field_477 - this.field_84.minIntA.field_477) / 16) > 10000L) {
      System.err.println("[SubSimplexConvexCubes][WARNING] more then 10000 segments to test: " + l + " -> intersection [" + this.field_84.minIntA + ", " + this.field_84.maxIntA + "]");
    }
    this.field_84.absolute1.set(paramTransform3.basis);
    MatrixUtil.absolute(this.field_84.absolute1);
    this.field_84.cubesB.getSegmentBuffer().b4(this.outerSegmentHandler, this.field_84.minIntA, this.field_84.maxIntA);
    return SubsimplexCubesCovexCast.OuterSegmentHandler.access$700(this.outerSegmentHandler);
  }
  
  public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    paramDispatcherInfo = 1.0F;
    this.field_84.tmp.sub(paramCollisionObject1.getInterpolationWorldTransform(this.field_84.tmpTrans1).origin, paramCollisionObject1.getWorldTransform(this.field_84.tmpTrans2).origin);
    paramManifoldResult = this.field_84.tmp.lengthSquared();
    this.field_84.tmp.sub(paramCollisionObject2.getInterpolationWorldTransform(this.field_84.tmpTrans1).origin, paramCollisionObject2.getWorldTransform(this.field_84.tmpTrans2).origin);
    float f = this.field_84.tmp.lengthSquared();
    if ((paramManifoldResult < paramCollisionObject1.getCcdSquareMotionThreshold()) && (f < paramCollisionObject2.getCcdSquareMotionThreshold())) {
      return 1.0F;
    }
    if (this.field_84.disableCcd) {
      return 1.0F;
    }
    paramManifoldResult = (ConvexShape)paramCollisionObject1.getCollisionShape();
    SphereShape localSphereShape = new SphereShape(paramCollisionObject2.getCcdSweptSphereRadius());
    ConvexCast.CastResult localCastResult = new ConvexCast.CastResult();
    Object localObject = new VoronoiSimplexSolverExt();
    if (new GjkConvexCast(paramManifoldResult, localSphereShape, (SimplexSolverInterface)localObject).calcTimeOfImpact(paramCollisionObject1.getWorldTransform(this.field_84.tmpTrans1), paramCollisionObject1.getInterpolationWorldTransform(this.field_84.tmpTrans2), paramCollisionObject2.getWorldTransform(this.field_84.tmpTrans3), paramCollisionObject2.getInterpolationWorldTransform(this.field_84.tmpTrans4), localCastResult))
    {
      if (paramCollisionObject1.getHitFraction() > localCastResult.fraction) {
        paramCollisionObject1.setHitFraction(localCastResult.fraction);
      }
      if (paramCollisionObject2.getHitFraction() > localCastResult.fraction) {
        paramCollisionObject2.setHitFraction(localCastResult.fraction);
      }
      if (1.0F > localCastResult.fraction) {
        paramDispatcherInfo = localCastResult.fraction;
      }
    }
    paramManifoldResult = (ConvexShape)paramCollisionObject2.getCollisionShape();
    localSphereShape = new SphereShape(paramCollisionObject1.getCcdSweptSphereRadius());
    localCastResult = new ConvexCast.CastResult();
    localObject = new VoronoiSimplexSolver();
    if (new GjkConvexCast(localSphereShape, paramManifoldResult, (SimplexSolverInterface)localObject).calcTimeOfImpact(paramCollisionObject1.getWorldTransform(this.field_84.tmpTrans1), paramCollisionObject1.getInterpolationWorldTransform(this.field_84.tmpTrans2), paramCollisionObject2.getWorldTransform(this.field_84.tmpTrans3), paramCollisionObject2.getInterpolationWorldTransform(this.field_84.tmpTrans4), localCastResult))
    {
      if (paramCollisionObject1.getHitFraction() > localCastResult.fraction) {
        paramCollisionObject1.setHitFraction(localCastResult.fraction);
      }
      if (paramCollisionObject2.getHitFraction() > localCastResult.fraction) {
        paramCollisionObject2.setHitFraction(localCastResult.fraction);
      }
      if (paramDispatcherInfo > localCastResult.fraction) {
        paramDispatcherInfo = localCastResult.fraction;
      }
    }
    return paramDispatcherInfo;
  }
  
  private boolean checkExplicitCollision(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentController paramSegmentController, ConvexCast.CastResult paramCastResult)
  {
    if (((paramSegmentController instanceof class_798)) && ((((class_798)paramSegmentController).a() instanceof ShieldContainerInterface)))
    {
      paramSegmentController = ((ShieldContainerInterface)((class_798)paramSegmentController).a()).getShieldManager().getCollection().iterator();
      while (paramSegmentController.hasNext())
      {
        ShieldUnit localShieldUnit;
        if (((ShieldUnit)(localShieldUnit = (ShieldUnit)paramSegmentController.next())).getShields() <= 0) {
          return false;
        }
        this.field_84.tmpTrans.set(paramTransform3);
        localShieldUnit.getOpenGLCenter(this.field_84.fromHelp);
        this.field_84.tmpTrans.basis.transform(this.field_84.fromHelp);
        this.field_84.tmpTrans.origin.add(this.field_84.fromHelp);
        this.field_84.simplexSolver.reset();
        this.field_84.sphereShape.setRadius(localShieldUnit.getRadius());
        SubsimplexConvexCast localSubsimplexConvexCast = new SubsimplexConvexCast(this.field_84.shapeA, this.field_84.sphereShape, this.field_84.simplexSolver);
        this.field_84.sphereShape.getAabb(this.field_84.tmpTrans, this.field_84.outMin, this.field_84.outMax);
        if (localSubsimplexConvexCast.calcTimeOfImpact(paramTransform1, paramTransform2, this.field_84.tmpTrans, this.field_84.tmpTrans, paramCastResult))
        {
          if (this.rayResult != null) {
            this.rayResult.setUserData(localShieldUnit);
          }
          return true;
        }
      }
    }
    return false;
  }
  
  private void combineAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector3f paramVector3f5, Vector3f paramVector3f6)
  {
    paramVector3f5.field_615 = Math.min(paramVector3f1.field_615, paramVector3f3.field_615);
    paramVector3f5.field_616 = Math.min(paramVector3f1.field_616, paramVector3f3.field_616);
    paramVector3f5.field_617 = Math.min(paramVector3f1.field_617, paramVector3f3.field_617);
    paramVector3f6.field_615 = Math.max(paramVector3f2.field_615, paramVector3f4.field_615);
    paramVector3f6.field_616 = Math.max(paramVector3f2.field_616, paramVector3f4.field_616);
    paramVector3f6.field_617 = Math.max(paramVector3f2.field_617, paramVector3f4.field_617);
  }
  
  private boolean doNarrorTest(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentData paramSegmentData, ConvexCast.CastResult paramCastResult, class_35 paramclass_351, class_35 paramclass_352)
  {
    this.field_84.posCachePointer = 0;
    for (paramTransform2 = paramclass_351.field_453; paramTransform2 < paramclass_352.field_453; paramTransform2 = (byte)(paramTransform2 + 1)) {
      for (paramCastResult = paramclass_351.field_454; paramCastResult < paramclass_352.field_454; paramCastResult = (byte)(paramCastResult + 1)) {
        for (int i = paramclass_351.field_455; i < paramclass_352.field_455; i = (byte)(i + 1))
        {
          this.field_84.elemA.b((byte)(paramTransform2 + 8), (byte)(paramCastResult + 8), (byte)(i + 8));
          int j = SegmentData.getInfoIndex(this.field_84.elemA.field_453, this.field_84.elemA.field_454, this.field_84.elemA.field_455);
          short s;
          ElementInformation localElementInformation;
          if (((s = paramSegmentData.getType(j)) != 0) && ((localElementInformation = ElementKeyMap.getInfo(s)).isPhysical()) && ((s != 122) || (ElementKeyMap.getInfo(s).isPhysical(paramSegmentData.isActive(j)))))
          {
            s = paramSegmentData.getOrientation(j);
            boolean bool = paramSegmentData.isActive(j);
            this.field_84.elemPosA.set(paramTransform2, paramCastResult, i);
            this.field_84.elemPosA.field_615 += paramSegmentData.getSegment().field_34.field_475;
            this.field_84.elemPosA.field_616 += paramSegmentData.getSegment().field_34.field_476;
            this.field_84.elemPosA.field_617 += paramSegmentData.getSegment().field_34.field_477;
            this.field_84.field_491.set(this.field_84.elemPosA);
            this.field_84.tmpTrans.set(paramTransform3);
            this.field_84.tmpTrans.basis.transform(this.field_84.field_491);
            this.field_84.tmpTrans.origin.add(this.field_84.field_491);
            this.field_84.box0.setMargin(0.3F);
            this.field_84.box0.getAabb(this.field_84.tmpTrans, this.field_84.outMin, this.field_84.outMax);
            this.field_84.normal.set(0.0F, 0.0F, 0.0F);
            if (AabbUtil2.testAabbAgainstAabb2(this.field_84.outMin, this.field_84.outMax, this.field_84.castedAABBMin, this.field_84.castedAABBMax))
            {
              Object localObject;
              if (((this.resultCallback instanceof ClosestConvexResultCallbackExt)) && ((localObject = (ClosestConvexResultCallbackExt)this.resultCallback).checkHasHitOnly))
              {
                ((ClosestConvexResultCallbackExt)localObject).userData = paramSegmentData.getSegmentController();
                this.resultCallback.closestHitFraction = 0.5F;
                return false;
              }
              this.field_84.distTest.set(paramTransform1.origin);
              this.field_84.distTest.sub(this.field_84.tmpTrans.origin);
              (localObject = (class_46)this.pool4.get()).a(this.field_84.elemA.field_453, this.field_84.elemA.field_454, this.field_84.elemA.field_455, s * 10 + localElementInformation.getBlockStyle() + (bool ? 1000 : 0));
              float f = this.field_84.distTest.length();
              for (s = 0; (this.field_84.sorted.containsKey(f)) && (s < 100); s++) {
                f += 0.1F;
              }
              if (s >= 100) {
                System.err.println("[SUBSIMPLEX][WARNING] more than 100 tries in sorted");
              }
              this.field_84.sorted.put(f, localObject);
              this.field_84.posCachePointer += 1;
            }
          }
        }
      }
    }
    return true;
  }
  
  public boolean drawDebug()
  {
    return (this.field_84.cubesB.getSegmentBuffer().a12().isOnServer()) && (mode.equals("UP")) && (Keyboard.isKeyDown(57)) && (!Keyboard.isKeyDown(29));
  }
  
  public boolean isOnServer()
  {
    return this.field_84.cubesB.getSegmentBuffer().a12().isOnServer();
  }
  
  private boolean performCastTest(Segment paramSegment, ConvexShape paramConvexShape, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, ConvexCast.CastResult paramCastResult)
  {
    paramSegment = paramSegment.a16();
    if (!this.field_84.intersectionCallBack.initialized) {
      this.field_84.intersectionCallBack.createHitCache(512);
    }
    this.field_84.intersectionCallBack.reset();
    this.field_84.intersectionCallBack = paramSegment.getOctree().findIntersecting(paramSegment.getOctree().getSet(), this.field_84.intersectionCallBack, paramSegment.getSegment(), paramTransform3, this.field_84.absolute1, 0.0F, this.field_84.castedAABBMin, this.field_84.castedAABBMax, 1.0F);
    this.field_84.sortedAABB.clear();
    this.field_84.sorted.clear();
    paramConvexShape = 0;
    boolean bool1 = true;
    Object localObject2;
    if (this.field_84.intersectionCallBack.hitCount > 0)
    {
      Object localObject1;
      for (int i = 0; i < this.field_84.intersectionCallBack.hitCount; i++)
      {
        this.field_84.intersectionCallBack.getHit(i, this.field_84.minOut, this.field_84.maxOut, this.field_84.startOut, this.field_84.endOut);
        this.field_84.dist.set(this.field_84.maxOut);
        this.field_84.dist.sub(this.field_84.minOut);
        float f1 = this.field_84.dist.length();
        this.field_84.dist.normalize();
        this.field_84.dist.scale(f1 / 2.0F);
        this.field_84.minOut.add(this.field_84.dist);
        this.field_84.dist.sub(this.field_84.minOut, paramTransform1.origin);
        float f2 = this.field_84.dist.lengthSquared() * 1000.0F;
        for (int k = 0; (this.field_84.sortedAABB.containsKey(f2)) && (!Float.isNaN(f2)) && (!Float.isInfinite(f2)) && (k < 1000); k++) {
          f2 += 0.1F;
        }
        if (k > 100) {
          System.err.println("[CubesConvex][WARNING] extended more then 100 AABBs length: " + this.field_84.sortedAABB.size() + ": " + paramConvexShape);
        }
        if ((!Float.isNaN(f2)) && (!Float.isInfinite(f2)))
        {
          if (paramConvexShape > 1000) {
            System.err.println("[CubesConvex][WARNING] testing more then 1000 AABBs: " + this.field_84.sortedAABB.size() + ": " + paramConvexShape);
          }
          localObject2 = (AABBb)this.aabbpool.get();
          localObject1 = (class_35)this.pool.get();
          class_35 localclass_35 = (class_35)this.pool.get();
          ((class_35)localObject1).b1(this.field_84.startOut);
          localclass_35.b1(this.field_84.endOut);
          ((AABBb)localObject2).min = ((class_35)localObject1);
          ((AABBb)localObject2).max = localclass_35;
          this.field_84.sortedAABB.put(f2, localObject2);
          paramConvexShape++;
        }
      }
      if (this.field_84.sortedAABB.size() > 1000) {
        System.err.println("[CubesConvex][WARNING] testing more then 1000 AABBs: " + this.field_84.sortedAABB.size());
      }
      Iterator localIterator1 = this.field_84.sortedAABB.entrySet().iterator();
      while (localIterator1.hasNext())
      {
        localObject1 = (Map.Entry)localIterator1.next();
        if (!bool1) {
          break;
        }
        bool1 = doNarrorTest(paramTransform1, paramTransform2, paramTransform3, paramSegment, paramCastResult, ((AABBb)((Map.Entry)localObject1).getValue()).min, ((AABBb)((Map.Entry)localObject1).getValue()).max);
      }
    }
    if (!bool1) {
      return true;
    }
    if (this.field_84.sorted.size() > 1000) {
      System.err.println("[" + (isOnServer() ? "SERVER" : "CLIENT") + "][CubeConvex] DOING " + this.field_84.sorted.size() + " box tests");
    }
    boolean bool2 = false;
    Iterator localIterator2 = this.field_84.sorted.entrySet().iterator();
    while (localIterator2.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator2.next();
      this.field_84.elemA.b((byte)((class_46)localEntry.getValue()).field_467, (byte)((class_46)localEntry.getValue()).field_468, (byte)((class_46)localEntry.getValue()).field_469);
      (localObject2 = new ArrayList()).add(this.field_84.elemA);
      for (int m = 0; m < ((ArrayList)localObject2).size(); m++)
      {
        paramConvexShape = ((class_46)localEntry.getValue()).field_470 % 10;
        bool1 = ((class_46)localEntry.getValue()).field_470 >= 1000;
        int j = (((class_46)localEntry.getValue()).field_470 - (bool1 ? 1000 : 0)) / 10;
        if (m == 0)
        {
          this.field_84.elemPosA.set(this.field_84.elemA.field_453 - 8, this.field_84.elemA.field_454 - 8, this.field_84.elemA.field_455 - 8);
        }
        else
        {
          (localObject3 = (class_35)((ArrayList)localObject2).get(m)).c(this.field_84.elemA);
          this.field_84.elemPosA.set(this.field_84.elemA.field_453 - 8 + 0.5F * ((class_35)localObject3).field_453, this.field_84.elemA.field_454 - 8 + 0.5F * ((class_35)localObject3).field_454, this.field_84.elemA.field_455 - 8 + 0.5F * ((class_35)localObject3).field_455);
        }
        this.field_84.elemPosA.field_615 += paramSegment.getSegment().field_34.field_475;
        this.field_84.elemPosA.field_616 += paramSegment.getSegment().field_34.field_476;
        this.field_84.elemPosA.field_617 += paramSegment.getSegment().field_34.field_477;
        this.field_84.field_491.set(this.field_84.elemPosA);
        this.field_84.tmpTrans.set(paramTransform3);
        this.field_84.tmpTrans.basis.transform(this.field_84.field_491);
        this.field_84.tmpTrans.origin.add(this.field_84.field_491);
        this.field_84.simplexSolver.reset();
        this.field_84.box0.setMargin(0.005F);
        Object localObject3 = this.field_84.box0;
        if ((paramConvexShape > 0) && (paramConvexShape < 3)) {
          localObject3 = class_384.a6(paramConvexShape, (byte)j, bool1);
        }
        paramConvexShape = new ContinuousConvexCollision(this.field_84.shapeA, (ConvexShape)localObject3, this.field_84.simplexSolver, this.field_84.gjkEpaPenetrationDepthSolver);
        ConvexCast.CastResult localCastResult;
        (localCastResult = new ConvexCast.CastResult()).allowedPenetration = paramCastResult.allowedPenetration;
        localCastResult.fraction = 1.0F;
        if (paramConvexShape.calcTimeOfImpact(paramTransform1, paramTransform2, this.field_84.tmpTrans, this.field_84.tmpTrans, localCastResult, this.field_84.gjkVar))
        {
          if (this.resultCallback == null)
          {
            if (this.rayResult != null)
            {
              this.rayResult.setSegment(paramSegment.getSegment());
              this.rayResult.cubePos.b1(this.field_84.elemA);
            }
            return true;
          }
          if ((localCastResult.normal.lengthSquared() > 1.0E-004F) && (localCastResult.fraction <= this.resultCallback.closestHitFraction))
          {
            paramCastResult.normal.normalize();
            paramConvexShape = new CollisionWorld.LocalConvexResult(this.field_84.cubesObject, null, localCastResult.normal, localCastResult.hitPoint, localCastResult.fraction);
            if ((this.resultCallback instanceof KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback)) {
              ((KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback)this.resultCallback).addSingleResult(paramConvexShape, true, paramSegment.getSegment(), this.field_84.elemA);
            } else {
              this.resultCallback.addSingleResult(paramConvexShape, true);
            }
            if ((this.resultCallback instanceof ClosestConvexResultCallbackExt)) {
              ((ClosestConvexResultCallbackExt)this.resultCallback).userData = paramSegment.getSegmentController();
            }
            paramSegment.getSegment();
            Segment.d();
            bool2 = true;
          }
        }
      }
    }
    return bool2;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexCubesCovexCast
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */