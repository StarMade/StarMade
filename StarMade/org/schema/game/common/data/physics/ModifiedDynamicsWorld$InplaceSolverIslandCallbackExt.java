/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   4:    */import com.bulletphysics.collision.dispatch.SimulationIslandManager.IslandCallback;
/*   5:    */import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*   6:    */import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*   7:    */import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*   8:    */import com.bulletphysics.linearmath.IDebugDraw;
/*   9:    */import com.bulletphysics.util.ObjectArrayList;
/*  10:    */import zW;
/*  11:    */
/* 394:    */class ModifiedDynamicsWorld$InplaceSolverIslandCallbackExt
/* 395:    */  extends SimulationIslandManager.IslandCallback
/* 396:    */{
/* 397:    */  public ContactSolverInfo solverInfo;
/* 398:    */  public ConstraintSolver solver;
/* 399:    */  public ObjectArrayList sortedConstraints;
/* 400:    */  public int numConstraints;
/* 401:    */  public IDebugDraw debugDrawer;
/* 402:    */  public Dispatcher dispatcher;
/* 403:    */  private zW state;
/* 404:    */  
/* 405:    */  public void init(ContactSolverInfo paramContactSolverInfo, ConstraintSolver paramConstraintSolver, ObjectArrayList paramObjectArrayList, int paramInt, IDebugDraw paramIDebugDraw, Dispatcher paramDispatcher, zW paramzW)
/* 406:    */  {
/* 407:407 */    this.solverInfo = paramContactSolverInfo;
/* 408:408 */    this.solver = paramConstraintSolver;
/* 409:409 */    this.sortedConstraints = paramObjectArrayList;
/* 410:410 */    this.numConstraints = paramInt;
/* 411:411 */    this.debugDrawer = paramIDebugDraw;
/* 412:412 */    this.dispatcher = paramDispatcher;
/* 413:    */    
/* 414:414 */    this.state = paramzW;
/* 415:    */  }
/* 416:    */  
/* 417:    */  public void processIsland(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, int paramInt4) {
/* 418:418 */    if (paramInt4 < 0)
/* 419:    */    {
/* 423:423 */      this.solver.solveGroup(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, this.sortedConstraints, 0, this.numConstraints, this.solverInfo, this.debugDrawer, this.dispatcher);return;
/* 424:    */    }
/* 425:    */    
/* 431:431 */    int i = -1;
/* 432:432 */    int j = 0;
/* 433:    */    
/* 436:436 */    for (int k = 0; k < this.numConstraints; k++) {
/* 437:437 */      if (ModifiedDynamicsWorld.access$000((TypedConstraint)this.sortedConstraints.getQuick(k)) == paramInt4)
/* 438:    */      {
/* 440:440 */        i = k;
/* 441:441 */        break;
/* 442:    */      }
/* 443:    */    }
/* 444:445 */    for (; 
/* 445:445 */        k < this.numConstraints; k++) {
/* 446:446 */      if (ModifiedDynamicsWorld.access$000((TypedConstraint)this.sortedConstraints.getQuick(k)) == paramInt4) {
/* 447:447 */        j++;
/* 448:    */      }
/* 449:    */    }
/* 450:    */    
/* 452:452 */    if (paramInt3 + j > 0) {
/* 453:453 */      this.solver.solveGroup(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, this.sortedConstraints, i, j, this.solverInfo, this.debugDrawer, this.dispatcher);
/* 454:    */    }
/* 455:    */  }
/* 456:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedDynamicsWorld.InplaceSolverIslandCallbackExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */