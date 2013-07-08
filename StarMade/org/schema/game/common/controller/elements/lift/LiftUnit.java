package org.schema.game.common.controller.elements.lift;

import class_1419;
import class_227;
import class_247;
import class_371;
import class_48;
import class_941;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import java.util.ArrayList;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.common.data.physics.PhysicsExt;

public class LiftUnit
  extends ElementCollection
{
  private class_48 significator = new class_48();
  private float height = -1.0F;
  private RigidBody body;
  private Transform field_306;
  private float maxHeight;
  private float timeSpendUp = 0.0F;
  
  public void activate()
  {
    PhysicsExt localPhysicsExt = getController().getPhysics();
    Object localObject2 = new BoxShape(new Vector3f(Math.max(2, getMax().field_475 - getMin().field_475), 0.2F, Math.max(2, getMax().field_477 - getMin().field_477)));
    Vector3f localVector3f = new Vector3f(getSignificator().field_475 - 8, getMin().field_476 - 8 - 1.0F, getSignificator().field_477 - 8);
    getController().getWorldTransform().transform(localVector3f);
    this.field_306 = new Transform();
    this.field_306.setIdentity();
    this.field_306.origin.set(localVector3f);
    this.timeSpendUp = 0.0F;
    if (getBody() != null) {
      localPhysicsExt.removeObject(getBody());
    }
    setBody(localPhysicsExt.getBodyFromShape((CollisionShape)localObject2, 0.0F, this.field_306));
    this.height = 0.0F;
    this.maxHeight = (getMax().field_476 - getMin().field_476 + 1.5F);
    localPhysicsExt.addObject(getBody());
    if (!getController().isOnServer())
    {
      int i = 0;
      localObject2 = this;
      Object localObject1 = null;
      ((class_371)getController().getState()).a27().a93().field_98.add(localObject2);
    }
  }
  
  public void cleanUp()
  {
    deactivate();
    super.cleanUp();
  }
  
  public void deactivate()
  {
    if (isActive())
    {
      PhysicsExt localPhysicsExt = getController().getPhysics();
      if (getBody() != null) {
        localPhysicsExt.removeObject(getBody());
      }
    }
    this.height = -1.0F;
    this.timeSpendUp = 0.0F;
    setChanged();
    notifyObservers(Boolean.valueOf(false));
  }
  
  public RigidBody getBody()
  {
    return this.body;
  }
  
  public class_48 getSignificator()
  {
    return this.significator;
  }
  
  public boolean isActive()
  {
    return this.height >= 0.0F;
  }
  
  public void refreshLiftCapabilities() {}
  
  public void setBody(RigidBody paramRigidBody)
  {
    this.body = paramRigidBody;
  }
  
  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
  {
    this.significator.field_475 = (getMax().field_475 - (getMax().field_475 - getMin().field_475) / 2);
    this.significator.field_476 = (getMax().field_476 - (getMax().field_476 - getMin().field_476) / 2);
    this.significator.field_477 = (getMax().field_477 - (getMax().field_477 - getMin().field_477) / 2);
  }
  
  public void update(class_941 paramclass_941)
  {
    if (isActive())
    {
      paramclass_941 = paramclass_941.a() * Math.max(1.0F, (getMax().field_476 - getMin().field_476) / 16.0F);
      if (this.height < this.maxHeight)
      {
        this.height += paramclass_941;
        this.field_306.origin.field_616 += paramclass_941;
        getBody().setActivationState(1);
        getBody().getMotionState().setWorldTransform(this.field_306);
        getBody().setWorldTransform(this.field_306);
        return;
      }
      this.timeSpendUp += paramclass_941;
      if (this.timeSpendUp > 5.0F) {
        deactivate();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.lift.LiftUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */