package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import javax.vecmath.Vector3f;

public class CylinderShapeZ
  extends CylinderShape
{
  public CylinderShapeZ(Vector3f halfExtents)
  {
    super(halfExtents, false);
    this.upAxis = 2;
    recalcLocalAabb();
  }
  
  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      return cylinderLocalSupportZ(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vec, out);
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < numVectors; local_i++) {
        cylinderLocalSupportZ(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vectors[local_i], supportVerticesOut[local_i]);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public float getRadius()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      return getHalfExtentsWithMargin(localStack.get$javax$vecmath$Vector3f()).field_615;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public String getName()
  {
    return "CylinderZ";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.CylinderShapeZ
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */