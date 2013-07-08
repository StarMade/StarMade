package org.schema.schine.network.objects.container;

import class_29;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShapeChild;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import javax.vecmath.Vector3f;

public class PhysicsDataContainer
{
  private static final Transform ident;
  private CollisionShape shape;
  private CompoundShapeChild shapeChield;
  private CollisionObject object;
  private Vector3f inertia = new Vector3f();
  public Transform initialTransform;
  private boolean initialized;
  private final Transform cacheTransform = new Transform();
  private float lastUpdatedMass = -1.0F;
  
  public void onPhysicsAdd()
  {
    this.lastUpdatedMass = -1.0F;
  }
  
  public void onPhysicsRemove()
  {
    this.lastUpdatedMass = -1.0F;
  }
  
  public void clearPhysicsInfo()
  {
    setObject(null);
    this.initialTransform = null;
    setShape(null);
    setShapeChield(null);
    this.initialized = false;
  }
  
  public Transform getCurrentPhysicsTransform()
  {
    return this.cacheTransform;
  }
  
  public CollisionObject getObject()
  {
    return this.object;
  }
  
  public CollisionShape getShape()
  {
    return this.shape;
  }
  
  public CompoundShapeChild getShapeChild()
  {
    return this.shapeChield;
  }
  
  public boolean isInitialized()
  {
    return this.initialized;
  }
  
  public void setObject(CollisionObject paramCollisionObject)
  {
    if (paramCollisionObject != null)
    {
      if ((paramCollisionObject instanceof RigidBody)) {
        ((RigidBody)paramCollisionObject).getMotionState().setWorldTransform(paramCollisionObject.getWorldTransform(new Transform()));
      }
      paramCollisionObject.setInterpolationWorldTransform(paramCollisionObject.getWorldTransform(new Transform()));
    }
    this.object = paramCollisionObject;
  }
  
  public void setShape(CollisionShape paramCollisionShape)
  {
    this.shape = paramCollisionShape;
  }
  
  public void setShapeChield(CompoundShapeChild paramCompoundShapeChild)
  {
    this.shapeChield = paramCompoundShapeChild;
  }
  
  public boolean updateMass(float paramFloat, boolean paramBoolean)
  {
    if ((this.initialized) && (getShape() != null) && (getObject() != null))
    {
      if ((paramFloat != this.lastUpdatedMass) || (paramBoolean))
      {
        getShape().calculateLocalInertia(Math.max(paramFloat, 2.5F), this.inertia);
        ((RigidBody)getObject()).setMassProps(paramFloat, this.inertia);
        this.lastUpdatedMass = paramFloat;
        return true;
      }
    }
    else {
      System.err.println("[PHYSICSCONTAINER][WARNING] Could not set mass!");
    }
    return false;
  }
  
  public void updatePhysical()
  {
    updatePhysical(getObject());
  }
  
  public void updatePhysical(CollisionObject paramCollisionObject)
  {
    if (paramCollisionObject != null)
    {
      if ((paramCollisionObject instanceof RigidBody))
      {
        ((RigidBody)paramCollisionObject).getMotionState().getWorldTransform(this.cacheTransform);
        if ((getShapeChild() != null) && (!getShapeChild().transform.equals(ident))) {
          class_29.a1(this.cacheTransform, getShapeChild().transform);
        }
      }
      else
      {
        paramCollisionObject.getWorldTransform(this.cacheTransform);
      }
      this.initialized = true;
    }
  }
  
  public void updateManually(Transform paramTransform)
  {
    this.cacheTransform.set(paramTransform);
  }
  
  static
  {
    (PhysicsDataContainer.ident = new Transform()).setIdentity();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.container.PhysicsDataContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */