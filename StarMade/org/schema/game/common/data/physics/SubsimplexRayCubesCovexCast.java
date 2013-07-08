package org.schema.game.common.data.physics;

import class_35;
import class_384;
import class_46;
import class_48;
import class_798;
import class_886;
import class_988;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.LocalRayResult;
import com.bulletphysics.collision.narrowphase.ConvexCast;
import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectPool;
import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import o;
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
import org.schema.schine.network.server.ServerStateInterface;
import s;

public class SubsimplexRayCubesCovexCast
  extends ConvexCast
{
  private static ThreadLocal threadLocal = new SubsimplexRayCubesCovexCast.1();
  public static boolean debug;
  private float extraMargin = 0.0F;
  private CubeRayCastResult rayResult;
  private CubeRayVariableSet field_84 = (CubeRayVariableSet)threadLocal.get();
  ObjectPool pool = ObjectPool.get(o.class);
  ObjectPool pool4 = ObjectPool.get(s.class);
  ObjectPool aabbpool = ObjectPool.get(AABBb.class);
  SubsimplexRayCubesCovexCast.OuterSegmentIterator outerSegmentIterator = new SubsimplexRayCubesCovexCast.OuterSegmentIterator(this, null);
  boolean hit = false;
  int hitboxes = 0;
  int casts = 0;
  private long currentTime;
  
  public SubsimplexRayCubesCovexCast(ConvexShape paramConvexShape, CollisionObject paramCollisionObject, SimplexSolverInterface paramSimplexSolverInterface, CubeRayCastResult paramCubeRayCastResult)
  {
    this.field_84.shapeA = paramConvexShape;
    this.field_84.cubesB = ((CubeShape)paramCollisionObject.getCollisionShape());
    this.field_84.cubesCollisionObject = paramCollisionObject;
    this.field_84.simplexSolver = paramSimplexSolverInterface;
    this.rayResult = paramCubeRayCastResult;
    this.field_84.box0.setMargin(0.15F);
    this.field_84.lastMinDist = -1.0F;
  }
  
  public boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult)
  {
    if (debug) {
      System.err.println("TESTING RAY COLLISION:  " + paramTransform1.origin + " -> " + paramTransform2.origin + " on " + this.field_84.cubesB.getSegmentBuffer().a12());
    }
    this.currentTime = System.currentTimeMillis();
    this.field_84.lastHitpointWorld.set((0.0F / 0.0F), 0.0F, 0.0F);
    if ((this.rayResult.filter != null) && (!this.rayResult.filter.equals(this.field_84.cubesB.getSegmentBuffer().a12()))) {
      return false;
    }
    this.field_84.lastMinDist = -1.0F;
    this.hit = false;
    if (this.field_84.oSet == null) {
      this.field_84.oSet = ArrayOctree.getSet(this.field_84.cubesB.getSegmentBuffer().a12().getState() instanceof ServerStateInterface);
    } else {
      assert (this.field_84.oSet == ArrayOctree.getSet(this.field_84.cubesB.getSegmentBuffer().a12().isOnServer()));
    }
    this.field_84.absolute.set(paramTransform3.basis);
    MatrixUtil.absolute(this.field_84.absolute);
    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$302(this.outerSegmentIterator, paramTransform1);
    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$402(this.outerSegmentIterator, false);
    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$502(this.outerSegmentIterator, paramCastResult);
    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$602(this.outerSegmentIterator, paramTransform3);
    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$702(this.outerSegmentIterator, paramTransform2);
    SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$802(this.outerSegmentIterator, paramTransform4);
    this.field_84.solve.initialize(paramTransform1.origin, paramTransform2.origin, this.field_84.cubesB.getSegmentBuffer().a12(), paramTransform3);
    if (debug) {
      System.err.println("TRAVERSING ON: " + this.outerSegmentIterator);
    }
    this.field_84.solve.traverseSegmentsOnRay(this.outerSegmentIterator);
    if (SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$400(this.outerSegmentIterator)) {
      return true;
    }
    return this.hit;
  }
  
  private boolean checkExplicitCollision(CollisionObject paramCollisionObject, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentController paramSegmentController, ConvexCast.CastResult paramCastResult)
  {
    if (((paramSegmentController instanceof class_798)) && ((((class_798)paramSegmentController).a() instanceof ShieldContainerInterface)))
    {
      paramSegmentController = ((ShieldContainerInterface)((class_798)paramSegmentController).a()).getShieldManager().getCollection().iterator();
      while (paramSegmentController.hasNext())
      {
        ShieldUnit localShieldUnit;
        if (((ShieldUnit)(localShieldUnit = (ShieldUnit)paramSegmentController.next())).getShields() <= 0)
        {
          System.err.println("Shields decativated");
          return false;
        }
        this.field_84.tmpTrans3.set(paramTransform3);
        localShieldUnit.getOpenGLCenter(this.field_84.fromHelp);
        this.field_84.tmpTrans3.basis.transform(this.field_84.fromHelp);
        this.field_84.tmpTrans3.origin.add(this.field_84.fromHelp);
        this.field_84.simplexSolver.reset();
        this.field_84.sphereShape.setRadius(localShieldUnit.getRadius());
        ContinuousConvexCollision localContinuousConvexCollision = new ContinuousConvexCollision(this.field_84.shapeA, this.field_84.sphereShape, this.field_84.simplexSolver, this.field_84.gjkEpaPenetrationDepthSolver);
        this.field_84.sphereShape.getAabb(this.field_84.tmpTrans3, this.field_84.outMin, this.field_84.outMax);
        if ((localContinuousConvexCollision.calcTimeOfImpact(paramTransform1, paramTransform2, this.field_84.tmpTrans3, this.field_84.tmpTrans3, paramCastResult, this.field_84.gjkVar)) && (paramCastResult.fraction < this.rayResult.closestHitFraction))
        {
          paramTransform1.basis.transform(paramCastResult.normal);
          paramCastResult.normal.normalize();
          paramCollisionObject = new CollisionWorld.LocalRayResult(paramCollisionObject, null, paramCastResult.normal, paramCastResult.fraction);
          this.rayResult.setUserData(localShieldUnit);
          this.rayResult.addSingleResult(paramCollisionObject, true);
          System.err.println("[RAY] Explicid COLLISION");
          return true;
        }
      }
    }
    return false;
  }
  
  private void checkSegment(Segment paramSegment, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult)
  {
    if ((paramSegment.a16() == null) || (paramSegment.g())) {
      return;
    }
    paramSegment.a17(this.field_84.distToHit);
    this.field_84.distToHit.sub(paramTransform1.origin);
    this.field_84.cubesB.setMargin(this.field_84.cubesB.getMargin() + this.extraMargin);
    this.field_84.cubesB.getSegmentAabb(paramSegment, paramTransform3, this.field_84.outMin, this.field_84.outMax, this.field_84.localMinOut, this.field_84.localMaxOut, this.field_84.aabbVarSet);
    this.field_84.hitLambda[0] = 1.0F;
    this.field_84.normal.set(0.0F, 0.0F, 0.0F);
    this.field_84.cubesB.setMargin(this.field_84.cubesB.getMargin() - this.extraMargin);
    paramTransform4 = (AabbUtil2.rayAabb(paramTransform1.origin, paramTransform2.origin, this.field_84.outMin, this.field_84.outMax, this.field_84.hitLambda, this.field_84.normal)) || (class_988.a(paramTransform1.origin, this.field_84.outMin, this.field_84.outMax)) || (class_988.a(paramTransform2.origin, this.field_84.outMin, this.field_84.outMax)) ? 1 : 0;
    if (debug) {
      System.err.println("HIT?: " + paramTransform4);
    }
    if (paramTransform4 != 0)
    {
      new Transform().setIdentity();
      this.hitboxes += 1;
      paramSegment = performCastTest(this.field_84.cubesCollisionObject, paramSegment, paramTransform1, paramTransform2, paramTransform3, paramCastResult);
      paramTransform1 = this.field_84.sorted.values().iterator();
      while (paramTransform1.hasNext())
      {
        paramTransform2 = (class_46)paramTransform1.next();
        this.pool4.release(paramTransform2);
      }
      this.field_84.sorted.clear();
      if (paramSegment != 0) {
        this.casts += 1;
      }
      this.hit |= paramSegment;
      if (paramSegment != 0)
      {
        if (this.rayResult.hasHit()) {
          return;
        }
        this.field_84.lastHitpointWorld.set(this.rayResult.hitPointWorld);
        if ((this.field_84.lastMinDist < 0.0F) || (this.field_84.distToHit.length() < this.field_84.lastMinDist)) {
          this.field_84.lastMinDist = this.field_84.distToHit.length();
        }
      }
    }
  }
  
  private void doNarrorTest(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentData paramSegmentData, ConvexCast.CastResult paramCastResult, class_35 paramclass_351, class_35 paramclass_352)
  {
    this.field_84.posCachePointer = 0;
    for (paramTransform2 = paramclass_351.field_453; paramTransform2 < paramclass_352.field_453; paramTransform2 = (byte)(paramTransform2 + 1)) {
      for (paramCastResult = paramclass_351.field_454; paramCastResult < paramclass_352.field_454; paramCastResult = (byte)(paramCastResult + 1)) {
        for (int i = paramclass_351.field_455; i < paramclass_352.field_455; i = (byte)(i + 1))
        {
          this.field_84.elemA.b((byte)(paramTransform2 + 8), (byte)(paramCastResult + 8), (byte)(i + 8));
          int j = SegmentData.getInfoIndex(this.field_84.elemA.field_453, this.field_84.elemA.field_454, this.field_84.elemA.field_455);
          if ((paramSegmentData.containsFast(j)) && ((this.rayResult == null) || (this.rayResult.isIgnoereNotPhysical()) || (ElementKeyMap.getInfo(paramSegmentData.getType(j)).isPhysical(paramSegmentData.isActive(j)))))
          {
            ElementInformation localElementInformation = ElementKeyMap.getInfo(paramSegmentData.getType(j));
            int k = paramSegmentData.getOrientation(j);
            boolean bool = paramSegmentData.isActive(j);
            this.field_84.elemPosA.set(paramTransform2, paramCastResult, i);
            this.field_84.elemPosA.field_615 += paramSegmentData.getSegment().field_34.field_475;
            this.field_84.elemPosA.field_616 += paramSegmentData.getSegment().field_34.field_476;
            this.field_84.elemPosA.field_617 += paramSegmentData.getSegment().field_34.field_477;
            this.field_84.field_1803.set(this.field_84.elemPosA);
            this.field_84.tmpTrans3.set(paramTransform3);
            this.field_84.tmpTrans3.basis.transform(this.field_84.field_1803);
            this.field_84.tmpTrans3.origin.add(this.field_84.field_1803);
            this.field_84.box0.setMargin(0.2F + this.extraMargin);
            this.field_84.normal.set(0.0F, 0.0F, 0.0F);
            this.field_84.distTest.set(paramTransform1.origin);
            this.field_84.distTest.sub(this.field_84.tmpTrans3.origin);
            class_46 localclass_46;
            (localclass_46 = (class_46)this.pool4.get()).a(this.field_84.elemA.field_453, this.field_84.elemA.field_454, this.field_84.elemA.field_455, k * 10 + localElementInformation.getBlockStyle() + (bool ? 1000 : 0));
            for (float f = this.field_84.distTest.length(); this.field_84.sorted.containsKey(f); f += 0.1F) {}
            this.field_84.sorted.put(f, localclass_46);
            this.field_84.posCachePointer += 1;
          }
        }
      }
    }
  }
  
  private boolean performCastTest(CollisionObject paramCollisionObject, Segment paramSegment, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, ConvexCast.CastResult paramCastResult)
  {
    paramSegment = paramSegment.a16();
    if (!this.field_84.intersectionCallBack.initialized) {
      this.field_84.intersectionCallBack.createHitCache(512);
    }
    this.field_84.intersectionCallBack.reset();
    this.field_84.intersectionCallBack = paramSegment.getOctree().findIntersectingRay(this.field_84.oSet, this.field_84.intersectionCallBack, paramTransform3, this.field_84.absolute, 0.15F + this.extraMargin, paramSegment.getSegment(), paramTransform1.origin, paramTransform2.origin, 1.0F);
    this.field_84.sortedAABB.clear();
    this.field_84.sorted.clear();
    if (debug) {
      System.err.println("CAST TEST: (INSIDE)" + this.field_84.intersectionCallBack.hitCount);
    }
    Object localObject2;
    Object localObject3;
    if (this.field_84.intersectionCallBack.hitCount > 0)
    {
      for (int i = 0; i < this.field_84.intersectionCallBack.hitCount; i++)
      {
        this.field_84.intersectionCallBack.getHit(i, this.field_84.minOut, this.field_84.maxOut, this.field_84.startOut, this.field_84.endOut);
        (localObject1 = new Vector3f(this.field_84.maxOut)).sub(this.field_84.minOut);
        ((Vector3f)localObject1).scale(0.5F + this.extraMargin);
        (localObject2 = new Vector3f(this.field_84.minOut)).add((Tuple3f)localObject1);
        ((Vector3f)localObject2).sub(paramTransform1.origin);
        for (float f = ((Vector3f)localObject2).length(); this.field_84.sortedAABB.containsKey(f); f += 0.1F) {}
        AABBb localAABBb = (AABBb)this.aabbpool.get();
        localObject2 = (class_35)this.pool.get();
        localObject3 = (class_35)this.pool.get();
        ((class_35)localObject2).b1(this.field_84.startOut);
        ((class_35)localObject3).b1(this.field_84.endOut);
        localAABBb.min = ((class_35)localObject2);
        localAABBb.max = ((class_35)localObject3);
        this.field_84.sortedAABB.put(f, localAABBb);
      }
      Iterator localIterator = this.field_84.sortedAABB.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localObject1 = (Map.Entry)localIterator.next();
        doNarrorTest(paramTransform1, paramTransform2, paramTransform3, paramSegment, paramCastResult, ((AABBb)((Map.Entry)localObject1).getValue()).min, ((AABBb)((Map.Entry)localObject1).getValue()).max);
        this.pool.release(((AABBb)((Map.Entry)localObject1).getValue()).min);
        this.pool.release(((AABBb)((Map.Entry)localObject1).getValue()).max);
        this.aabbpool.release(((Map.Entry)localObject1).getValue());
      }
      this.field_84.sortedAABB.clear();
    }
    Object localObject1 = this.field_84.sorted.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)localObject1).next();
      this.field_84.elemA.b((byte)((class_46)((Map.Entry)localObject2).getValue()).field_467, (byte)((class_46)((Map.Entry)localObject2).getValue()).field_468, (byte)((class_46)((Map.Entry)localObject2).getValue()).field_469);
      this.field_84.elemPosA.set(this.field_84.elemA.field_453 - 8, this.field_84.elemA.field_454 - 8, this.field_84.elemA.field_455 - 8);
      int k = ((class_46)((Map.Entry)localObject2).getValue()).field_470 % 10;
      boolean bool = ((class_46)((Map.Entry)localObject2).getValue()).field_470 >= 1000;
      int j = (((class_46)((Map.Entry)localObject2).getValue()).field_470 - (bool ? 1000 : 0)) / 10;
      this.field_84.elemPosA.field_615 += paramSegment.getSegment().field_34.field_475;
      this.field_84.elemPosA.field_616 += paramSegment.getSegment().field_34.field_476;
      this.field_84.elemPosA.field_617 += paramSegment.getSegment().field_34.field_477;
      this.field_84.field_1803.set(this.field_84.elemPosA);
      this.field_84.tmpTrans3.set(paramTransform3);
      this.field_84.tmpTrans3.basis.transform(this.field_84.field_1803);
      this.field_84.tmpTrans3.origin.add(this.field_84.field_1803);
      this.field_84.simplexSolver.reset();
      this.field_84.box0.setMargin(0.01F + this.extraMargin);
      localObject3 = this.field_84.box0;
      if ((k > 0) && (k < 3)) {
        localObject3 = class_384.a6(k, (byte)j, bool);
      }
      if ((new ContinuousConvexCollision(this.field_84.shapeA, (ConvexShape)localObject3, this.field_84.simplexSolver, this.field_84.gjkEpaPenetrationDepthSolver).calcTimeOfImpact(paramTransform1, paramTransform2, this.field_84.tmpTrans3, this.field_84.tmpTrans3, paramCastResult, this.field_84.gjkVar)) && (paramCastResult.normal.lengthSquared() > 1.0E-006F) && (paramCastResult.fraction < this.rayResult.closestHitFraction))
      {
        assert (paramSegment.getSegment() != null) : ("SEGMENT NULL OF DATA: " + paramSegment + " ");
        this.rayResult.setSegment(paramSegment.getSegment());
        this.rayResult.cubePos.b1(this.field_84.elemA);
        paramTransform1.basis.transform(paramCastResult.normal);
        paramCastResult.normal.normalize();
        paramCollisionObject = new CollisionWorld.LocalRayResult(paramCollisionObject, null, paramCastResult.normal, paramCastResult.fraction);
        this.rayResult.addSingleResult(paramCollisionObject, true);
        paramSegment.getSegment();
        Segment.d();
        assert ((!this.rayResult.hasHit()) || (this.rayResult.getSegment() != null));
        return true;
      }
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexRayCubesCovexCast
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */