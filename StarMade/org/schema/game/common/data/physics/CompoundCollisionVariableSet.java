package org.schema.game.common.data.physics;

import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectPool;

public class CompoundCollisionVariableSet
{
  public Transform tmpTrans = new Transform();
  public Transform orgTrans = new Transform();
  public Transform chieldTrans = new Transform();
  public Transform interpolationTrans = new Transform();
  public Transform newChildWorldTrans = new Transform();
  public Transform tmpTransO = new Transform();
  public Transform orgTransO = new Transform();
  public Transform chieldTransO = new Transform();
  public Transform interpolationTransO = new Transform();
  public Transform newChildWorldTransO = new Transform();
  public int instances;
  public final ObjectPool pool = new ObjectPool(CompoundCollisionAlgorithmExt.class);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CompoundCollisionVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */