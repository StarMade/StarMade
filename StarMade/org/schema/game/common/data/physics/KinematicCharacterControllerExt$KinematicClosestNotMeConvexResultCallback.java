package org.schema.game.common.data.physics;

import class_35;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.world.Segment;

class KinematicCharacterControllerExt$KinematicClosestNotMeConvexResultCallback
  extends CollisionWorld.ClosestConvexResultCallback
{
  protected CollisionObject field_419;
  protected final Vector3f field_420;
  protected float minSlopeDot;
  Transform field_422 = new Transform();
  private Segment segment;
  private class_35 cubePos;
  
  public KinematicCharacterControllerExt$KinematicClosestNotMeConvexResultCallback(CollisionObject paramCollisionObject, Vector3f paramVector3f, float paramFloat)
  {
    super(new Vector3f(), new Vector3f());
    this.field_419 = paramCollisionObject;
    this.field_420 = paramVector3f;
    this.minSlopeDot = paramFloat;
  }
  
  public float addSingleResult(CollisionWorld.LocalConvexResult paramLocalConvexResult, boolean paramBoolean)
  {
    if (paramLocalConvexResult.hitCollisionObject == this.field_419) {
      return 1.0F;
    }
    Vector3f localVector3f;
    if (paramBoolean)
    {
      localVector3f = paramLocalConvexResult.hitNormalLocal;
    }
    else
    {
      localVector3f = new Vector3f();
      this.hitCollisionObject.getWorldTransform(this.field_422).basis.transform(paramLocalConvexResult.hitNormalLocal, localVector3f);
    }
    if (this.field_420.dot(localVector3f) < this.minSlopeDot) {
      return 1.0F;
    }
    return super.addSingleResult(paramLocalConvexResult, paramBoolean);
  }
  
  public void addSingleResult(CollisionWorld.LocalConvexResult paramLocalConvexResult, boolean paramBoolean, Segment paramSegment, class_35 paramclass_35)
  {
    addSingleResult(paramLocalConvexResult, paramBoolean);
    this.segment = paramSegment;
    this.cubePos = new class_35(paramclass_35);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */