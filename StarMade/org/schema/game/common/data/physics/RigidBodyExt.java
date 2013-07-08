package org.schema.game.common.data.physics;

import class_48;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.schema.common.util.linAlg.TransformTools;
import org.schema.game.common.controller.SegmentController;

public class RigidBodyExt
  extends RigidBody
  implements RelativeBody
{
  private final SegmentController segmentController;
  public String virtualString;
  public class_48 virtualSec;
  private final Vector3f angularVeloTmp = new Vector3f();
  private final Vector3f linearVeloTmp = new Vector3f();
  private final Vector3f axis = new Vector3f();
  private final Matrix3f tmp = new Matrix3f();
  private final Matrix3f dmat = new Matrix3f();
  private final Quat4f dorn = new Quat4f();
  
  public RigidBodyExt(SegmentController paramSegmentController, float paramFloat, MotionState paramMotionState, CollisionShape paramCollisionShape)
  {
    super(paramFloat, paramMotionState, paramCollisionShape);
    this.segmentController = paramSegmentController;
    this.interpolationWorldTransform.setIdentity();
  }
  
  public RigidBodyExt(SegmentController paramSegmentController, float paramFloat, MotionState paramMotionState, CollisionShape paramCollisionShape, Vector3f paramVector3f)
  {
    super(paramFloat, paramMotionState, paramCollisionShape, paramVector3f);
    this.segmentController = paramSegmentController;
    this.interpolationWorldTransform.setIdentity();
  }
  
  public RigidBodyExt(SegmentController paramSegmentController, RigidBodyConstructionInfo paramRigidBodyConstructionInfo)
  {
    super(paramRigidBodyConstructionInfo);
    this.segmentController = paramSegmentController;
    this.interpolationWorldTransform.setIdentity();
  }
  
  public void saveKinematicState(float paramFloat)
  {
    if (paramFloat != 0.0F)
    {
      if (getMotionState() != null) {
        getMotionState().getWorldTransform(this.worldTransform);
      }
      getLinearVelocity(this.linearVeloTmp);
      getAngularVelocity(this.angularVeloTmp);
      if ((this.linearVeloTmp.lengthSquared() > 0.0F) || (this.angularVeloTmp.lengthSquared() > 0.0F))
      {
        TransformTools.a1(this.interpolationWorldTransform, this.worldTransform, paramFloat, this.linearVeloTmp, this.angularVeloTmp, this.axis, this.tmp, this.dmat, this.dorn);
        setLinearVelocity(this.linearVeloTmp);
        setAngularVelocity(this.angularVeloTmp);
      }
      this.interpolationLinearVelocity.set(this.linearVeloTmp);
      this.interpolationAngularVelocity.set(this.angularVeloTmp);
      this.interpolationWorldTransform.set(this.worldTransform);
    }
  }
  
  public Vector3f getInvInertiaDiagLocal(Vector3f paramVector3f)
  {
    return super.getInvInertiaDiagLocal(paramVector3f);
  }
  
  public String toString()
  {
    return "RigBEx" + (this.virtualString != null ? this.virtualString : "Orig") + "@" + hashCode() + "(" + getCollisionShape() + ")";
  }
  
  public void setLinearVelocity(Vector3f paramVector3f)
  {
    if (this.virtualSec == null) {
      super.setLinearVelocity(paramVector3f);
    }
  }
  
  public void setAngularVelocity(Vector3f paramVector3f)
  {
    if (this.virtualSec == null) {
      super.setAngularVelocity(paramVector3f);
    }
  }
  
  public SegmentController getSegmentController()
  {
    return this.segmentController;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.RigidBodyExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */