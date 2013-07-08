package org.schema.game.common.data.physics;

import class_1407;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.dispatch.SimulationIslandManager.IslandCallback;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.util.ObjectArrayList;

class ModifiedDynamicsWorld$InplaceSolverIslandCallbackExt
  extends SimulationIslandManager.IslandCallback
{
  public ContactSolverInfo solverInfo;
  public ConstraintSolver solver;
  public ObjectArrayList sortedConstraints;
  public int numConstraints;
  public IDebugDraw debugDrawer;
  public Dispatcher dispatcher;
  private class_1407 state;
  
  public void init(ContactSolverInfo paramContactSolverInfo, ConstraintSolver paramConstraintSolver, ObjectArrayList paramObjectArrayList, int paramInt, IDebugDraw paramIDebugDraw, Dispatcher paramDispatcher, class_1407 paramclass_1407)
  {
    this.solverInfo = paramContactSolverInfo;
    this.solver = paramConstraintSolver;
    this.sortedConstraints = paramObjectArrayList;
    this.numConstraints = paramInt;
    this.debugDrawer = paramIDebugDraw;
    this.dispatcher = paramDispatcher;
    this.state = paramclass_1407;
  }
  
  public void processIsland(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt4 < 0)
    {
      this.solver.solveGroup(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, this.sortedConstraints, 0, this.numConstraints, this.solverInfo, this.debugDrawer, this.dispatcher);
      return;
    }
    int i = -1;
    int j = 0;
    for (int k = 0; k < this.numConstraints; k++) {
      if (ModifiedDynamicsWorld.access$000((TypedConstraint)this.sortedConstraints.getQuick(k)) == paramInt4)
      {
        i = k;
        break;
      }
    }
    while (k < this.numConstraints)
    {
      if (ModifiedDynamicsWorld.access$000((TypedConstraint)this.sortedConstraints.getQuick(k)) == paramInt4) {
        j++;
      }
      k++;
    }
    if (paramInt3 + j > 0) {
      this.solver.solveGroup(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, this.sortedConstraints, i, j, this.solverInfo, this.debugDrawer, this.dispatcher);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedDynamicsWorld.InplaceSolverIslandCallbackExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */