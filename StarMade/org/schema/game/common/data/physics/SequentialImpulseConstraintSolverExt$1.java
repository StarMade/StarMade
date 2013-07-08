package org.schema.game.common.data.physics;

import com.bulletphysics.ContactDestroyedCallback;

class SequentialImpulseConstraintSolverExt$1
  extends ContactDestroyedCallback
{
  SequentialImpulseConstraintSolverExt$1(SequentialImpulseConstraintSolverExt paramSequentialImpulseConstraintSolverExt) {}
  
  public boolean contactDestroyed(Object paramObject)
  {
    assert (paramObject != null);
    SequentialImpulseConstraintSolverExt.access$010(this.this$0);
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.SequentialImpulseConstraintSolverExt.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */