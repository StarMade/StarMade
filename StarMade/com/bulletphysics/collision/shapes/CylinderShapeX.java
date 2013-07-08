package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import javax.vecmath.Vector3f;

public class CylinderShapeX
  extends CylinderShape
{
  public CylinderShapeX(Vector3f halfExtents)
  {
    super(halfExtents, false);
    this.upAxis = 0;
    recalcLocalAabb();
  }
  
  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      return cylinderLocalSupportX(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vec, out);
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
        cylinderLocalSupportX(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vectors[local_i], supportVerticesOut[local_i]);
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
      return getHalfExtentsWithMargin(localStack.get$javax$vecmath$Vector3f()).field_616;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public String getName()
  {
    return "CylinderX";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.CylinderShapeX
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */