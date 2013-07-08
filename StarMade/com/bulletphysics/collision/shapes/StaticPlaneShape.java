package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.TransformUtil;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

public class StaticPlaneShape
  extends ConcaveShape
{
  protected final Vector3f localAabbMin = new Vector3f();
  protected final Vector3f localAabbMax = new Vector3f();
  protected final Vector3f planeNormal = new Vector3f();
  protected float planeConstant;
  protected final Vector3f localScaling = new Vector3f(0.0F, 0.0F, 0.0F);
  
  public StaticPlaneShape(Vector3f planeNormal, float planeConstant)
  {
    this.planeNormal.normalize(planeNormal);
    this.planeConstant = planeConstant;
  }
  
  public Vector3f getPlaneNormal(Vector3f out)
  {
    out.set(this.planeNormal);
    return out;
  }
  
  public float getPlaneConstant()
  {
    return this.planeConstant;
  }
  
  public void processAllTriangles(TriangleCallback arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
      halfExtents.sub(aabbMax, aabbMin);
      halfExtents.scale(0.5F);
      float radius = halfExtents.length();
      Vector3f center = localStack.get$javax$vecmath$Vector3f();
      center.add(aabbMax, aabbMin);
      center.scale(0.5F);
      Vector3f tangentDir0 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tangentDir1 = localStack.get$javax$vecmath$Vector3f();
      TransformUtil.planeSpace1(this.planeNormal, tangentDir0, tangentDir1);
      Vector3f supVertex0 = localStack.get$javax$vecmath$Vector3f();
      Vector3f supVertex1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f projectedCenter = localStack.get$javax$vecmath$Vector3f();
      tmp.scale(this.planeNormal.dot(center) - this.planeConstant, this.planeNormal);
      projectedCenter.sub(center, tmp);
      Vector3f[] triangle = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
      tmp1.scale(radius, tangentDir0);
      tmp2.scale(radius, tangentDir1);
      VectorUtil.add(triangle[0], projectedCenter, tmp1, tmp2);
      tmp1.scale(radius, tangentDir0);
      tmp2.scale(radius, tangentDir1);
      tmp.sub(tmp1, tmp2);
      VectorUtil.add(triangle[1], projectedCenter, tmp);
      tmp1.scale(radius, tangentDir0);
      tmp2.scale(radius, tangentDir1);
      tmp.sub(tmp1, tmp2);
      triangle[2].sub(projectedCenter, tmp);
      callback.processTriangle(triangle, 0, 0);
      tmp1.scale(radius, tangentDir0);
      tmp2.scale(radius, tangentDir1);
      tmp.sub(tmp1, tmp2);
      triangle[0].sub(projectedCenter, tmp);
      tmp1.scale(radius, tangentDir0);
      tmp2.scale(radius, tangentDir1);
      tmp.add(tmp1, tmp2);
      triangle[1].sub(projectedCenter, tmp);
      tmp1.scale(radius, tangentDir0);
      tmp2.scale(radius, tangentDir1);
      VectorUtil.add(triangle[2], projectedCenter, tmp1, tmp2);
      callback.processTriangle(triangle, 0, 1);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void getAabb(Transform local_t, Vector3f aabbMin, Vector3f aabbMax)
  {
    aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
    aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.STATIC_PLANE_PROXYTYPE;
  }
  
  public void setLocalScaling(Vector3f scaling)
  {
    this.localScaling.set(scaling);
  }
  
  public Vector3f getLocalScaling(Vector3f out)
  {
    out.set(this.localScaling);
    return out;
  }
  
  public void calculateLocalInertia(float mass, Vector3f inertia)
  {
    inertia.set(0.0F, 0.0F, 0.0F);
  }
  
  public String getName()
  {
    return "STATICPLANE";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.StaticPlaneShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */