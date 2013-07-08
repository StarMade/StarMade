package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.Dbvt;
import com.bulletphysics.collision.broadphase.Dbvt.Node;
import com.bulletphysics.collision.broadphase.DbvtAabbMm;
import javax.vecmath.Vector3f;

public class DbvtExt
  extends Dbvt
{
  Vector3f tmp = new Vector3f();
  
  public boolean update(Dbvt.Node paramNode, DbvtAabbMm paramDbvtAabbMm, Vector3f paramVector3f, float paramFloat)
  {
    if (paramNode.volume.Contain(paramDbvtAabbMm)) {
      return false;
    }
    this.tmp.set(paramFloat, paramFloat, paramFloat);
    paramDbvtAabbMm.Expand(this.tmp);
    paramDbvtAabbMm.SignedExpand(paramVector3f);
    update(paramNode, paramDbvtAabbMm);
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.DbvtExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */