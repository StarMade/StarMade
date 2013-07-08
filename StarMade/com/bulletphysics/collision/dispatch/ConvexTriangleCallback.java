package com.bulletphysics.collision.dispatch;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.TriangleCallback;
import com.bulletphysics.collision.shapes.TriangleShape;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

class ConvexTriangleCallback
  extends TriangleCallback
{
  private CollisionObject convexBody;
  private CollisionObject triBody;
  private final Vector3f aabbMin = new Vector3f();
  private final Vector3f aabbMax = new Vector3f();
  private ManifoldResult resultOut;
  private Dispatcher dispatcher;
  private DispatcherInfo dispatchInfoPtr;
  private float collisionMarginTriangle;
  public int triangleCount;
  public PersistentManifold manifoldPtr;
  private CollisionAlgorithmConstructionInfo field_290 = new CollisionAlgorithmConstructionInfo();
  private TriangleShape field_291 = new TriangleShape();
  
  public ConvexTriangleCallback(Dispatcher dispatcher, CollisionObject body0, CollisionObject body1, boolean isSwapped)
  {
    this.dispatcher = dispatcher;
    this.dispatchInfoPtr = null;
    this.convexBody = (isSwapped ? body1 : body0);
    this.triBody = (isSwapped ? body0 : body1);
    this.manifoldPtr = dispatcher.getNewManifold(this.convexBody, this.triBody);
    clearCache();
  }
  
  public void destroy()
  {
    clearCache();
    this.dispatcher.releaseManifold(this.manifoldPtr);
  }
  
  public void setTimeStepAndCounters(float arg1, DispatcherInfo arg2, ManifoldResult arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      this.dispatchInfoPtr = dispatchInfo;
      this.collisionMarginTriangle = collisionMarginTriangle;
      this.resultOut = resultOut;
      Transform convexInTriangleSpace = localStack.get$com$bulletphysics$linearmath$Transform();
      this.triBody.getWorldTransform(convexInTriangleSpace);
      convexInTriangleSpace.inverse();
      convexInTriangleSpace.mul(this.convexBody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()));
      CollisionShape convexShape = this.convexBody.getCollisionShape();
      convexShape.getAabb(convexInTriangleSpace, this.aabbMin, this.aabbMax);
      float extraMargin = collisionMarginTriangle;
      Vector3f extra = localStack.get$javax$vecmath$Vector3f();
      extra.set(extraMargin, extraMargin, extraMargin);
      this.aabbMax.add(extra);
      this.aabbMin.sub(extra);
      return;
    }
    finally
    {
      .Stack tmp143_141 = localStack;
      tmp143_141.pop$com$bulletphysics$linearmath$Transform();
      tmp143_141.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void processTriangle(Vector3f[] arg1, int arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      this.field_290.dispatcher1 = this.dispatcher;
      CollisionObject local_ob = this.triBody;
      if ((this.dispatchInfoPtr != null) && (this.dispatchInfoPtr.debugDraw != null) && (this.dispatchInfoPtr.debugDraw.getDebugMode() > 0))
      {
        Vector3f color = localStack.get$javax$vecmath$Vector3f();
        color.set(255.0F, 255.0F, 0.0F);
        Transform local_tr = local_ob.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
        Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
        tmp1.set(triangle[0]);
        local_tr.transform(tmp1);
        tmp2.set(triangle[1]);
        local_tr.transform(tmp2);
        this.dispatchInfoPtr.debugDraw.drawLine(tmp1, tmp2, color);
        tmp1.set(triangle[1]);
        local_tr.transform(tmp1);
        tmp2.set(triangle[2]);
        local_tr.transform(tmp2);
        this.dispatchInfoPtr.debugDraw.drawLine(tmp1, tmp2, color);
        tmp1.set(triangle[2]);
        local_tr.transform(tmp1);
        tmp2.set(triangle[0]);
        local_tr.transform(tmp2);
        this.dispatchInfoPtr.debugDraw.drawLine(tmp1, tmp2, color);
      }
      if (this.convexBody.getCollisionShape().isConvex())
      {
        this.field_291.init(triangle[0], triangle[1], triangle[2]);
        this.field_291.setMargin(this.collisionMarginTriangle);
        CollisionShape color = local_ob.getCollisionShape();
        local_ob.internalSetTemporaryCollisionShape(this.field_291);
        CollisionAlgorithm local_tr = this.field_290.dispatcher1.findAlgorithm(this.convexBody, this.triBody, this.manifoldPtr);
        this.resultOut.setShapeIdentifiers(-1, -1, partId, triangleIndex);
        local_tr.processCollision(this.convexBody, this.triBody, this.dispatchInfoPtr, this.resultOut);
        this.field_290.dispatcher1.freeCollisionAlgorithm(local_tr);
        local_ob.internalSetTemporaryCollisionShape(color);
      }
      return;
    }
    finally
    {
      .Stack tmp385_383 = localStack;
      tmp385_383.pop$com$bulletphysics$linearmath$Transform();
      tmp385_383.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void clearCache()
  {
    this.dispatcher.clearManifold(this.manifoldPtr);
  }
  
  public Vector3f getAabbMin(Vector3f out)
  {
    out.set(this.aabbMin);
    return out;
  }
  
  public Vector3f getAabbMax(Vector3f out)
  {
    out.set(this.aabbMax);
    return out;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexTriangleCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */