package com.bulletphysics.collision.shapes;

import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.Transform;

public class CompoundShapeChild
{
  public final Transform transform = new Transform();
  public CollisionShape childShape;
  public BroadphaseNativeType childShapeType;
  public float childMargin;
  
  public boolean equals(Object obj)
  {
    if ((obj == null) || (!(obj instanceof CompoundShapeChild))) {
      return false;
    }
    CompoundShapeChild child = (CompoundShapeChild)obj;
    return (this.transform.equals(child.transform)) && (this.childShape == child.childShape) && (this.childShapeType == child.childShapeType) && (this.childMargin == child.childMargin);
  }
  
  public int hashCode()
  {
    int hash = 7;
    hash = 19 * hash + this.transform.hashCode();
    hash = 19 * hash + this.childShape.hashCode();
    hash = 19 * hash + this.childShapeType.hashCode();
    hash = 19 * hash + Float.floatToIntBits(this.childMargin);
    return hash;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.CompoundShapeChild
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */