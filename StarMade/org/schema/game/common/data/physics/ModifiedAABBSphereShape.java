package org.schema.game.common.data.physics;

import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class ModifiedAABBSphereShape
  extends SphereShape
{
  private float ext;
  Vector3f extent = new Vector3f();
  
  public ModifiedAABBSphereShape(float paramFloat1, float paramFloat2)
  {
    super(paramFloat1);
    this.ext = paramFloat2;
  }
  
  public void getAabb(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    super.getAabb(paramTransform, paramVector3f1, paramVector3f2);
    paramTransform = paramTransform.origin;
    this.extent.set(getMargin() + this.ext, getMargin() + this.ext, getMargin() + this.ext);
    paramVector3f1.sub(paramTransform, this.extent);
    paramVector3f2.add(paramTransform, this.extent);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedAABBSphereShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */