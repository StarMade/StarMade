package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.ManifoldResult;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.collision.shapes.ConcaveShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.IntArrayList;
import com.bulletphysics.util.ObjectArrayList;
import com.bulletphysics.util.ObjectPool;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class GImpactCollisionAlgorithm
  extends CollisionAlgorithm
{
  protected CollisionAlgorithm convex_algorithm;
  protected PersistentManifold manifoldPtr;
  protected ManifoldResult resultOut;
  protected DispatcherInfo dispatchInfo;
  protected int triface0;
  protected int part0;
  protected int triface1;
  protected int part1;
  private PairSet tmpPairset = new PairSet();
  
  public void init(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1)
  {
    super.init(local_ci);
    this.manifoldPtr = null;
    this.convex_algorithm = null;
  }
  
  public void destroy()
  {
    clearCache();
  }
  
  public void processCollision(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
  {
    clearCache();
    this.resultOut = resultOut;
    this.dispatchInfo = dispatchInfo;
    if (body0.getCollisionShape().getShapeType() == BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE)
    {
      GImpactShapeInterface gimpactshape0 = (GImpactShapeInterface)body0.getCollisionShape();
      if (body1.getCollisionShape().getShapeType() == BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE)
      {
        GImpactShapeInterface gimpactshape1 = (GImpactShapeInterface)body1.getCollisionShape();
        gimpact_vs_gimpact(body0, body1, gimpactshape0, gimpactshape1);
      }
      else
      {
        gimpact_vs_shape(body0, body1, gimpactshape0, body1.getCollisionShape(), false);
      }
    }
    else if (body1.getCollisionShape().getShapeType() == BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE)
    {
      GImpactShapeInterface gimpactshape1 = (GImpactShapeInterface)body1.getCollisionShape();
      gimpact_vs_shape(body1, body0, gimpactshape1, body0.getCollisionShape(), true);
    }
  }
  
  public void gimpact_vs_gimpact(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, GImpactShapeInterface arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      if (shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE)
      {
        GImpactMeshShape meshshape0 = (GImpactMeshShape)shape0;
        this.part0 = meshshape0.getMeshPartCount();
        while (this.part0-- != 0) {
          gimpact_vs_gimpact(body0, body1, meshshape0.getMeshPart(this.part0), shape1);
        }
        return;
      }
      if (shape1.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE)
      {
        GImpactMeshShape meshshape0 = (GImpactMeshShape)shape1;
        this.part1 = meshshape0.getMeshPartCount();
        while (this.part1-- != 0) {
          gimpact_vs_gimpact(body0, body1, shape0, meshshape0.getMeshPart(this.part1));
        }
        return;
      }
      Transform meshshape0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      PairSet pairset = this.tmpPairset;
      pairset.clear();
      gimpact_vs_gimpact_find_pairs(meshshape0, orgtrans1, shape0, shape1, pairset);
      if (pairset.size() == 0) {
        return;
      }
      if ((shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE_PART) && (shape1.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE_PART))
      {
        GImpactMeshShapePart shapepart0 = (GImpactMeshShapePart)shape0;
        GImpactMeshShapePart shapepart1 = (GImpactMeshShapePart)shape1;
        collide_sat_triangles(body0, body1, shapepart0, shapepart1, pairset, pairset.size());
        return;
      }
      shape0.lockChildShapes();
      shape1.lockChildShapes();
      GIM_ShapeRetriever shapepart0 = new GIM_ShapeRetriever(shape0);
      GIM_ShapeRetriever shapepart1 = new GIM_ShapeRetriever(shape1);
      boolean child_has_transform0 = shape0.childrenHasTransform();
      boolean child_has_transform1 = shape1.childrenHasTransform();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      int local_i = pairset.size();
      while (local_i-- != 0)
      {
        Pair pair = pairset.get(local_i);
        this.triface0 = pair.index1;
        this.triface1 = pair.index2;
        CollisionShape colshape0 = shapepart0.getChildShape(this.triface0);
        CollisionShape colshape1 = shapepart1.getChildShape(this.triface1);
        if (child_has_transform0)
        {
          tmpTrans.mul(meshshape0, shape0.getChildTransform(this.triface0));
          body0.setWorldTransform(tmpTrans);
        }
        if (child_has_transform1)
        {
          tmpTrans.mul(orgtrans1, shape1.getChildTransform(this.triface1));
          body1.setWorldTransform(tmpTrans);
        }
        convex_vs_convex_collision(body0, body1, colshape0, colshape1);
        if (child_has_transform0) {
          body0.setWorldTransform(meshshape0);
        }
        if (child_has_transform1) {
          body1.setWorldTransform(orgtrans1);
        }
      }
      shape0.unlockChildShapes();
      shape1.unlockChildShapes();
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public void gimpact_vs_shape(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, CollisionShape arg4, boolean arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      if (shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE)
      {
        GImpactMeshShape meshshape0 = (GImpactMeshShape)shape0;
        this.part0 = meshshape0.getMeshPartCount();
        while (this.part0-- != 0) {
          gimpact_vs_shape(body0, body1, meshshape0.getMeshPart(this.part0), shape1, swapped);
        }
        return;
      }
      if ((shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE_PART) && (shape1.getShapeType() == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE))
      {
        GImpactMeshShapePart meshshape0 = (GImpactMeshShapePart)shape0;
        StaticPlaneShape planeshape = (StaticPlaneShape)shape1;
        gimpacttrimeshpart_vs_plane_collision(body0, body1, meshshape0, planeshape, swapped);
        return;
      }
      if (shape1.isCompound())
      {
        CompoundShape meshshape0 = (CompoundShape)shape1;
        gimpact_vs_compoundshape(body0, body1, shape0, meshshape0, swapped);
        return;
      }
      if (shape1.isConcave())
      {
        ConcaveShape meshshape0 = (ConcaveShape)shape1;
        gimpact_vs_concave(body0, body1, shape0, meshshape0, swapped);
        return;
      }
      Transform meshshape0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Transform planeshape = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      IntArrayList collided_results = new IntArrayList();
      gimpact_vs_shape_find_pairs(meshshape0, planeshape, shape0, shape1, collided_results);
      if (collided_results.size() == 0) {
        return;
      }
      shape0.lockChildShapes();
      GIM_ShapeRetriever retriever0 = new GIM_ShapeRetriever(shape0);
      boolean child_has_transform0 = shape0.childrenHasTransform();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      int local_i = collided_results.size();
      while (local_i-- != 0)
      {
        int child_index = collided_results.get(local_i);
        if (swapped) {
          this.triface1 = child_index;
        } else {
          this.triface0 = child_index;
        }
        CollisionShape colshape0 = retriever0.getChildShape(child_index);
        if (child_has_transform0)
        {
          tmpTrans.mul(meshshape0, shape0.getChildTransform(child_index));
          body0.setWorldTransform(tmpTrans);
        }
        if (swapped) {
          shape_vs_shape_collision(body1, body0, shape1, colshape0);
        } else {
          shape_vs_shape_collision(body0, body1, colshape0, shape1);
        }
        if (child_has_transform0) {
          body0.setWorldTransform(meshshape0);
        }
      }
      shape0.unlockChildShapes();
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public void gimpact_vs_compoundshape(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, CompoundShape arg4, boolean arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Transform childtrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      int local_i = shape1.getNumChildShapes();
      while (local_i-- != 0)
      {
        CollisionShape colshape1 = shape1.getChildShape(local_i);
        childtrans1.mul(orgtrans1, shape1.getChildTransform(local_i, tmpTrans));
        body1.setWorldTransform(childtrans1);
        gimpact_vs_shape(body0, body1, shape0, colshape1, swapped);
        body1.setWorldTransform(orgtrans1);
      }
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public void gimpact_vs_concave(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, ConcaveShape arg4, boolean arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      GImpactTriangleCallback tricallback = new GImpactTriangleCallback();
      tricallback.algorithm = this;
      tricallback.body0 = body0;
      tricallback.body1 = body1;
      tricallback.gimpactshape0 = shape0;
      tricallback.swapped = swapped;
      tricallback.margin = shape1.getMargin();
      Transform gimpactInConcaveSpace = localStack.get$com$bulletphysics$linearmath$Transform();
      body1.getWorldTransform(gimpactInConcaveSpace);
      gimpactInConcaveSpace.inverse();
      gimpactInConcaveSpace.mul(body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()));
      Vector3f minAABB = localStack.get$javax$vecmath$Vector3f();
      Vector3f maxAABB = localStack.get$javax$vecmath$Vector3f();
      shape0.getAabb(gimpactInConcaveSpace, minAABB, maxAABB);
      shape1.processAllTriangles(tricallback, minAABB, maxAABB);
      return;
    }
    finally
    {
      .Stack tmp144_142 = localStack;
      tmp144_142.pop$com$bulletphysics$linearmath$Transform();
      tmp144_142.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected PersistentManifold newContactManifold(CollisionObject body0, CollisionObject body1)
  {
    this.manifoldPtr = this.dispatcher.getNewManifold(body0, body1);
    return this.manifoldPtr;
  }
  
  protected void destroyConvexAlgorithm()
  {
    if (this.convex_algorithm != null)
    {
      this.dispatcher.freeCollisionAlgorithm(this.convex_algorithm);
      this.convex_algorithm = null;
    }
  }
  
  protected void destroyContactManifolds()
  {
    if (this.manifoldPtr == null) {
      return;
    }
    this.dispatcher.releaseManifold(this.manifoldPtr);
    this.manifoldPtr = null;
  }
  
  protected void clearCache()
  {
    destroyContactManifolds();
    destroyConvexAlgorithm();
    this.triface0 = -1;
    this.part0 = -1;
    this.triface1 = -1;
    this.part1 = -1;
  }
  
  protected PersistentManifold getLastManifold()
  {
    return this.manifoldPtr;
  }
  
  protected void checkManifold(CollisionObject body0, CollisionObject body1)
  {
    if (getLastManifold() == null) {
      newContactManifold(body0, body1);
    }
    this.resultOut.setPersistentManifold(getLastManifold());
  }
  
  protected CollisionAlgorithm newAlgorithm(CollisionObject body0, CollisionObject body1)
  {
    checkManifold(body0, body1);
    CollisionAlgorithm convex_algorithm = this.dispatcher.findAlgorithm(body0, body1, getLastManifold());
    return convex_algorithm;
  }
  
  protected void checkConvexAlgorithm(CollisionObject body0, CollisionObject body1)
  {
    if (this.convex_algorithm != null) {
      return;
    }
    this.convex_algorithm = newAlgorithm(body0, body1);
  }
  
  protected void addContactPoint(CollisionObject body0, CollisionObject body1, Vector3f point, Vector3f normal, float distance)
  {
    this.resultOut.setShapeIdentifiers(this.part0, this.triface0, this.part1, this.triface1);
    checkManifold(body0, body1);
    this.resultOut.addContactPoint(normal, point, distance);
  }
  
  void collide_sat_triangles(CollisionObject arg1, CollisionObject arg2, GImpactMeshShapePart arg3, GImpactMeshShapePart arg4, PairSet arg5, int arg6)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      .Stack tmp11_7 = tmp7_5;
      tmp11_7.push$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
      .Stack tmp15_11 = tmp11_7;
      tmp15_11.push$javax$vecmath$Vector3f();
      tmp15_11.push$com$bulletphysics$extras$gimpact$TriangleContact();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Transform orgtrans0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      PrimitiveTriangle ptri0 = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
      PrimitiveTriangle ptri1 = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
      TriangleContact contact_data = localStack.get$com$bulletphysics$extras$gimpact$TriangleContact();
      shape0.lockChildShapes();
      shape1.lockChildShapes();
      int pair_pointer = 0;
      while (pair_count-- != 0)
      {
        Pair pair = pairs.get(pair_pointer++);
        this.triface0 = pair.index1;
        this.triface1 = pair.index2;
        shape0.getPrimitiveTriangle(this.triface0, ptri0);
        shape1.getPrimitiveTriangle(this.triface1, ptri1);
        ptri0.applyTransform(orgtrans0);
        ptri1.applyTransform(orgtrans1);
        ptri0.buildTriPlane();
        ptri1.buildTriPlane();
        if ((ptri0.overlap_test_conservative(ptri1)) && (ptri0.find_triangle_collision_clip_method(ptri1, contact_data)))
        {
          int local_j = contact_data.point_count;
          while (local_j-- != 0)
          {
            tmp.field_615 = contact_data.separating_normal.field_596;
            tmp.field_616 = contact_data.separating_normal.field_597;
            tmp.field_617 = contact_data.separating_normal.field_598;
            addContactPoint(body0, body1, contact_data.points[local_j], tmp, -contact_data.penetration_depth);
          }
        }
      }
      shape0.unlockChildShapes();
      shape1.unlockChildShapes();
      return;
    }
    finally
    {
      .Stack tmp300_298 = localStack;
      tmp300_298.pop$com$bulletphysics$linearmath$Transform();
      .Stack tmp304_300 = tmp300_298;
      tmp304_300.pop$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
      .Stack tmp308_304 = tmp304_300;
      tmp308_304.pop$javax$vecmath$Vector3f();
      tmp308_304.pop$com$bulletphysics$extras$gimpact$TriangleContact();
    }
  }
  
  protected void shape_vs_shape_collision(CollisionObject body0, CollisionObject body1, CollisionShape shape0, CollisionShape shape1)
  {
    CollisionShape tmpShape0 = body0.getCollisionShape();
    CollisionShape tmpShape1 = body1.getCollisionShape();
    body0.internalSetTemporaryCollisionShape(shape0);
    body1.internalSetTemporaryCollisionShape(shape1);
    CollisionAlgorithm algor = newAlgorithm(body0, body1);
    this.resultOut.setShapeIdentifiers(this.part0, this.triface0, this.part1, this.triface1);
    algor.processCollision(body0, body1, this.dispatchInfo, this.resultOut);
    this.dispatcher.freeCollisionAlgorithm(algor);
    body0.internalSetTemporaryCollisionShape(tmpShape0);
    body1.internalSetTemporaryCollisionShape(tmpShape1);
  }
  
  protected void convex_vs_convex_collision(CollisionObject body0, CollisionObject body1, CollisionShape shape0, CollisionShape shape1)
  {
    CollisionShape tmpShape0 = body0.getCollisionShape();
    CollisionShape tmpShape1 = body1.getCollisionShape();
    body0.internalSetTemporaryCollisionShape(shape0);
    body1.internalSetTemporaryCollisionShape(shape1);
    this.resultOut.setShapeIdentifiers(this.part0, this.triface0, this.part1, this.triface1);
    checkConvexAlgorithm(body0, body1);
    this.convex_algorithm.processCollision(body0, body1, this.dispatchInfo, this.resultOut);
    body0.internalSetTemporaryCollisionShape(tmpShape0);
    body1.internalSetTemporaryCollisionShape(tmpShape1);
  }
  
  void gimpact_vs_gimpact_find_pairs(Transform arg1, Transform arg2, GImpactShapeInterface arg3, GImpactShapeInterface arg4, PairSet arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      if ((shape0.hasBoxSet()) && (shape1.hasBoxSet()))
      {
        GImpactBvh.find_collision(shape0.getBoxSet(), trans0, shape1.getBoxSet(), trans1, pairset);
      }
      else
      {
        BoxCollision.AABB boxshape0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
        BoxCollision.AABB boxshape1 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
        int local_i = shape0.getNumChildShapes();
        while (local_i-- != 0)
        {
          shape0.getChildAabb(local_i, trans0, boxshape0.min, boxshape0.max);
          int local_j = shape1.getNumChildShapes();
          while (local_j-- != 0)
          {
            shape1.getChildAabb(local_i, trans1, boxshape1.min, boxshape1.max);
            if (boxshape1.has_collision(boxshape0)) {
              pairset.push_pair(local_i, local_j);
            }
          }
        }
      }
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
  
  protected void gimpact_vs_shape_find_pairs(Transform arg1, Transform arg2, GImpactShapeInterface arg3, CollisionShape arg4, IntArrayList arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      BoxCollision.AABB boxshape = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      if (shape0.hasBoxSet())
      {
        Transform trans1to0 = localStack.get$com$bulletphysics$linearmath$Transform();
        trans1to0.inverse(trans0);
        trans1to0.mul(trans1);
        shape1.getAabb(trans1to0, boxshape.min, boxshape.max);
        shape0.getBoxSet().boxQuery(boxshape, collided_primitives);
      }
      else
      {
        shape1.getAabb(trans1, boxshape.min, boxshape.max);
        BoxCollision.AABB trans1to0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
        int local_i = shape0.getNumChildShapes();
        while (local_i-- != 0)
        {
          shape0.getChildAabb(local_i, trans0, trans1to0.min, trans1to0.max);
          if (boxshape.has_collision(trans1to0)) {
            collided_primitives.add(local_i);
          }
        }
      }
      return;
    }
    finally
    {
      .Stack tmp165_163 = localStack;
      tmp165_163.pop$com$bulletphysics$linearmath$Transform();
      tmp165_163.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
  
  protected void gimpacttrimeshpart_vs_plane_collision(CollisionObject arg1, CollisionObject arg2, GImpactMeshShapePart arg3, StaticPlaneShape arg4, boolean arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      .Stack tmp11_7 = tmp7_5;
      tmp11_7.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      .Stack tmp15_11 = tmp11_7;
      tmp15_11.push$javax$vecmath$Vector3f();
      tmp15_11.push$javax$vecmath$Vector4f();
      Transform orgtrans0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      StaticPlaneShape planeshape = shape1;
      Vector4f plane = localStack.get$javax$vecmath$Vector4f();
      PlaneShape.get_plane_equation_transformed(planeshape, orgtrans1, plane);
      BoxCollision.AABB tribox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      shape0.getAabb(orgtrans0, tribox.min, tribox.max);
      tribox.increment_margin(planeshape.getMargin());
      if (tribox.plane_classify(plane) != PlaneIntersectionType.COLLIDE_PLANE) {
        return;
      }
      shape0.lockChildShapes();
      float margin = shape0.getMargin() + planeshape.getMargin();
      Vector3f vertex = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      int local_vi = shape0.getVertexCount();
      while (local_vi-- != 0)
      {
        shape0.getVertex(local_vi, vertex);
        orgtrans0.transform(vertex);
        float distance = VectorUtil.dot3(vertex, plane) - plane.field_599 - margin;
        if (distance < 0.0F) {
          if (swapped)
          {
            tmp.set(-plane.field_596, -plane.field_597, -plane.field_598);
            addContactPoint(body1, body0, vertex, tmp, distance);
          }
          else
          {
            tmp.set(plane.field_596, plane.field_597, plane.field_598);
            addContactPoint(body0, body1, vertex, tmp, distance);
          }
        }
      }
      shape0.unlockChildShapes();
      return;
    }
    finally
    {
      .Stack tmp314_312 = localStack;
      tmp314_312.pop$com$bulletphysics$linearmath$Transform();
      .Stack tmp318_314 = tmp314_312;
      tmp318_314.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      .Stack tmp322_318 = tmp318_314;
      tmp322_318.pop$javax$vecmath$Vector3f();
      tmp322_318.pop$javax$vecmath$Vector4f();
    }
  }
  
  public void setFace0(int value)
  {
    this.triface0 = value;
  }
  
  public int getFace0()
  {
    return this.triface0;
  }
  
  public void setFace1(int value)
  {
    this.triface1 = value;
  }
  
  public int getFace1()
  {
    return this.triface1;
  }
  
  public void setPart0(int value)
  {
    this.part0 = value;
  }
  
  public int getPart0()
  {
    return this.part0;
  }
  
  public void setPart1(int value)
  {
    this.part1 = value;
  }
  
  public int getPart1()
  {
    return this.part1;
  }
  
  public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
  {
    return 1.0F;
  }
  
  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
  {
    if (this.manifoldPtr != null) {
      manifoldArray.add(this.manifoldPtr);
    }
  }
  
  public static void registerAlgorithm(CollisionDispatcher dispatcher)
  {
    CreateFunc createFunc = new CreateFunc();
    for (int local_i = 0; local_i < BroadphaseNativeType.MAX_BROADPHASE_COLLISION_TYPES.ordinal(); local_i++) {
      dispatcher.registerCollisionCreateFunc(BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE.ordinal(), local_i, createFunc);
    }
    for (int local_i = 0; local_i < BroadphaseNativeType.MAX_BROADPHASE_COLLISION_TYPES.ordinal(); local_i++) {
      dispatcher.registerCollisionCreateFunc(local_i, BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE.ordinal(), createFunc);
    }
  }
  
  public static class CreateFunc
    extends CollisionAlgorithmCreateFunc
  {
    private final ObjectPool<GImpactCollisionAlgorithm> pool = ObjectPool.get(GImpactCollisionAlgorithm.class);
    
    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1)
    {
      GImpactCollisionAlgorithm algo = (GImpactCollisionAlgorithm)this.pool.get();
      algo.init(local_ci, body0, body1);
      return algo;
    }
    
    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
    {
      this.pool.release((GImpactCollisionAlgorithm)algo);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */