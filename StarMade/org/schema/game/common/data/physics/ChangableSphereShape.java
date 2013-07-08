package org.schema.game.common.data.physics;

import com.bulletphysics.collision.shapes.SphereShape;

public class ChangableSphereShape
  extends SphereShape
{
  public ChangableSphereShape(float paramFloat)
  {
    super(paramFloat);
  }
  
  public void setRadius(float paramFloat)
  {
    this.implicitShapeDimensions.field_615 = paramFloat;
    this.collisionMargin = paramFloat;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.ChangableSphereShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */