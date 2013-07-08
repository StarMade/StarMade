package org.schema.game.common.data.physics;

import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class AABBVarSet
{
  public final Vector3f localHalfExtents = new Vector3f();
  public final Vector3f localCenter = new Vector3f();
  public final Matrix3f abs_b = new Matrix3f();
  public final Vector3f center = new Vector3f();
  public final Vector3f extent = new Vector3f();
  public final Vector3f tmp = new Vector3f();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.AABBVarSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */