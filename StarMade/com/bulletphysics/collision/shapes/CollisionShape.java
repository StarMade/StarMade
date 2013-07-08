package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public abstract class CollisionShape
{
  protected Object userPointer;
  
  public abstract void getAabb(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2);
  
  public void getBoundingSphere(Vector3f arg1, float[] arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Transform local_tr = localStack.get$com$bulletphysics$linearmath$Transform();
      local_tr.setIdentity();
      Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
      getAabb(local_tr, aabbMin, aabbMax);
      tmp.sub(aabbMax, aabbMin);
      radius[0] = (tmp.length() * 0.5F);
      tmp.add(aabbMin, aabbMax);
      center.scale(0.5F, tmp);
      return;
    }
    finally
    {
      .Stack tmp101_99 = localStack;
      tmp101_99.pop$com$bulletphysics$linearmath$Transform();
      tmp101_99.pop$javax$vecmath$Vector3f();
    }
  }
  
  public float getAngularMotionDisc()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f center = localStack.get$javax$vecmath$Vector3f();
      float[] disc = new float[1];
      getBoundingSphere(center, disc);
      disc[0] += center.length();
      return disc[0];
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void calculateTemporalAabb(Transform arg1, Vector3f arg2, Vector3f arg3, float arg4, Vector3f arg5, Vector3f arg6)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      getAabb(curTrans, temporalAabbMin, temporalAabbMax);
      float temporalAabbMaxx = temporalAabbMax.field_615;
      float temporalAabbMaxy = temporalAabbMax.field_616;
      float temporalAabbMaxz = temporalAabbMax.field_617;
      float temporalAabbMinx = temporalAabbMin.field_615;
      float temporalAabbMiny = temporalAabbMin.field_616;
      float temporalAabbMinz = temporalAabbMin.field_617;
      Vector3f linMotion = localStack.get$javax$vecmath$Vector3f(linvel);
      linMotion.scale(timeStep);
      if (linMotion.field_615 > 0.0F) {
        temporalAabbMaxx += linMotion.field_615;
      } else {
        temporalAabbMinx += linMotion.field_615;
      }
      if (linMotion.field_616 > 0.0F) {
        temporalAabbMaxy += linMotion.field_616;
      } else {
        temporalAabbMiny += linMotion.field_616;
      }
      if (linMotion.field_617 > 0.0F) {
        temporalAabbMaxz += linMotion.field_617;
      } else {
        temporalAabbMinz += linMotion.field_617;
      }
      float angularMotion = angvel.length() * getAngularMotionDisc() * timeStep;
      Vector3f angularMotion3d = localStack.get$javax$vecmath$Vector3f();
      angularMotion3d.set(angularMotion, angularMotion, angularMotion);
      temporalAabbMin.set(temporalAabbMinx, temporalAabbMiny, temporalAabbMinz);
      temporalAabbMax.set(temporalAabbMaxx, temporalAabbMaxy, temporalAabbMaxz);
      temporalAabbMin.sub(angularMotion3d);
      temporalAabbMax.add(angularMotion3d);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public boolean isPolyhedral()
  {
    return getShapeType().isPolyhedral();
  }
  
  public boolean isConvex()
  {
    return getShapeType().isConvex();
  }
  
  public boolean isConcave()
  {
    return getShapeType().isConcave();
  }
  
  public boolean isCompound()
  {
    return getShapeType().isCompound();
  }
  
  public boolean isInfinite()
  {
    return getShapeType().isInfinite();
  }
  
  public abstract BroadphaseNativeType getShapeType();
  
  public abstract void setLocalScaling(Vector3f paramVector3f);
  
  public abstract Vector3f getLocalScaling(Vector3f paramVector3f);
  
  public abstract void calculateLocalInertia(float paramFloat, Vector3f paramVector3f);
  
  public abstract String getName();
  
  public abstract void setMargin(float paramFloat);
  
  public abstract float getMargin();
  
  public void setUserPointer(Object userPtr)
  {
    this.userPointer = userPtr;
  }
  
  public Object getUserPointer()
  {
    return this.userPointer;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.CollisionShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */