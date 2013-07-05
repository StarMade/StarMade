package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.util.ObjectArrayList;

public abstract class ConstraintSolver
{
  public void prepareSolve(int numBodies, int numManifolds)
  {
  }

  public abstract float solveGroup(ObjectArrayList<CollisionObject> paramObjectArrayList, int paramInt1, ObjectArrayList<PersistentManifold> paramObjectArrayList1, int paramInt2, int paramInt3, ObjectArrayList<TypedConstraint> paramObjectArrayList2, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw, Dispatcher paramDispatcher);

  public void allSolved(ContactSolverInfo info, IDebugDraw debugDrawer)
  {
  }

  public abstract void reset();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ConstraintSolver
 * JD-Core Version:    0.6.2
 */