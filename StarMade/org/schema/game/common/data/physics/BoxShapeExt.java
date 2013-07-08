package org.schema.game.common.data.physics;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

public class BoxShapeExt
  extends BoxShape
{
  private Vector3f marginV = new Vector3f();
  private Vector3f field_349 = new Vector3f();
  
  public BoxShapeExt(Vector3f paramVector3f)
  {
    super(paramVector3f);
  }
  
  public void setDimFromBB(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    this.marginV.set(getMargin(), getMargin(), getMargin());
    this.field_349.sub(paramVector3f2, paramVector3f1);
    VectorUtil.mul(this.implicitShapeDimensions, this.field_349, this.localScaling);
    this.implicitShapeDimensions.sub(this.marginV);
  }
  
  public void setHalfSize(Vector3f paramVector3f)
  {
    this.implicitShapeDimensions.set(paramVector3f);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.BoxShapeExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */