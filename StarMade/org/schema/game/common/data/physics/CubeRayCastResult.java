package org.schema.game.common.data.physics;

import class_35;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public class CubeRayCastResult
  extends CollisionWorld.ClosestRayResultCallback
{
  private Object owner;
  private Object userData;
  private boolean respectShields = false;
  private boolean ignoereNotPhysical = false;
  public SegmentController filter;
  public boolean onlyCubeMeshes;
  public class_35 cubePos = new class_35();
  private Segment segment;
  private Segment lastHitSegment;
  
  public CubeRayCastResult(Vector3f paramVector3f1, Vector3f paramVector3f2, Object paramObject, SegmentController paramSegmentController)
  {
    super(paramVector3f1, paramVector3f2);
    this.owner = paramObject;
    this.filter = paramSegmentController;
  }
  
  public Segment getLastHitSegment()
  {
    return this.lastHitSegment;
  }
  
  public Object getOwner()
  {
    return this.owner;
  }
  
  public Object getUserData()
  {
    return this.userData;
  }
  
  public boolean isIgnoereNotPhysical()
  {
    return this.ignoereNotPhysical;
  }
  
  public boolean isRespectShields()
  {
    return this.respectShields;
  }
  
  public void setIgnoereNotPhysical(boolean paramBoolean)
  {
    this.ignoereNotPhysical = paramBoolean;
  }
  
  public void setLastHitSegment(Segment paramSegment)
  {
    this.lastHitSegment = paramSegment;
  }
  
  public void setRespectShields(boolean paramBoolean)
  {
    this.respectShields = paramBoolean;
  }
  
  public void setUserData(Object paramObject)
  {
    this.userData = paramObject;
  }
  
  public Segment getSegment()
  {
    return this.segment;
  }
  
  public void setSegment(Segment paramSegment)
  {
    this.segment = paramSegment;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeRayCastResult
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */