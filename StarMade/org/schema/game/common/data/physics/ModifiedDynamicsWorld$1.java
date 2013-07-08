package org.schema.game.common.data.physics;

import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import java.util.Comparator;

final class ModifiedDynamicsWorld$1
  implements Comparator
{
  public final int compare(TypedConstraint paramTypedConstraint1, TypedConstraint paramTypedConstraint2)
  {
    paramTypedConstraint2 = ModifiedDynamicsWorld.access$000(paramTypedConstraint2);
    if (ModifiedDynamicsWorld.access$000(paramTypedConstraint1) < paramTypedConstraint2) {
      return -1;
    }
    return 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedDynamicsWorld.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */