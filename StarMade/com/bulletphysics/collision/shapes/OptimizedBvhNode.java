package com.bulletphysics.collision.shapes;

import java.io.Serializable;
import javax.vecmath.Vector3f;

public class OptimizedBvhNode
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public final Vector3f aabbMinOrg = new Vector3f();
  public final Vector3f aabbMaxOrg = new Vector3f();
  public int escapeIndex;
  public int subPart;
  public int triangleIndex;
  
  public void set(OptimizedBvhNode local_n)
  {
    this.aabbMinOrg.set(local_n.aabbMinOrg);
    this.aabbMaxOrg.set(local_n.aabbMaxOrg);
    this.escapeIndex = local_n.escapeIndex;
    this.subPart = local_n.subPart;
    this.triangleIndex = local_n.triangleIndex;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.OptimizedBvhNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */