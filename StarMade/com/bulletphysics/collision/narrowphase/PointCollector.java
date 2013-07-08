package com.bulletphysics.collision.narrowphase;

import javax.vecmath.Vector3f;

public class PointCollector
  extends DiscreteCollisionDetectorInterface.Result
{
  public final Vector3f normalOnBInWorld = new Vector3f();
  public final Vector3f pointInWorld = new Vector3f();
  public float distance = 1.0E+030F;
  public boolean hasResult = false;
  
  public void setShapeIdentifiers(int partId0, int index0, int partId1, int index1) {}
  
  public void addContactPoint(Vector3f normalOnBInWorld, Vector3f pointInWorld, float depth)
  {
    if (depth < this.distance)
    {
      this.hasResult = true;
      this.normalOnBInWorld.set(normalOnBInWorld);
      this.pointInWorld.set(pointInWorld);
      this.distance = depth;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.PointCollector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */