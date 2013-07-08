package com.bulletphysics.collision.shapes;

import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public abstract class ConvexShape
  extends CollisionShape
{
  public static final int MAX_PREFERRED_PENETRATION_DIRECTIONS = 10;
  
  public abstract Vector3f localGetSupportingVertex(Vector3f paramVector3f1, Vector3f paramVector3f2);
  
  public abstract Vector3f localGetSupportingVertexWithoutMargin(Vector3f paramVector3f1, Vector3f paramVector3f2);
  
  public abstract void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] paramArrayOfVector3f1, Vector3f[] paramArrayOfVector3f2, int paramInt);
  
  public abstract void getAabbSlow(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2);
  
  public abstract void setLocalScaling(Vector3f paramVector3f);
  
  public abstract Vector3f getLocalScaling(Vector3f paramVector3f);
  
  public abstract void setMargin(float paramFloat);
  
  public abstract float getMargin();
  
  public abstract int getNumPreferredPenetrationDirections();
  
  public abstract void getPreferredPenetrationDirection(int paramInt, Vector3f paramVector3f);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.ConvexShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */