/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.dispatch.SimulationIslandManager.IslandCallback;
/*     */ import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*     */ import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*     */ import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*     */ import com.bulletphysics.linearmath.IDebugDraw;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import zS;
/*     */ 
/*     */ class ModifiedDynamicsWorld$InplaceSolverIslandCallbackExt extends SimulationIslandManager.IslandCallback
/*     */ {
/*     */   public ContactSolverInfo solverInfo;
/*     */   public ConstraintSolver solver;
/*     */   public ObjectArrayList sortedConstraints;
/*     */   public int numConstraints;
/*     */   public IDebugDraw debugDrawer;
/*     */   public Dispatcher dispatcher;
/*     */   private zS state;
/*     */ 
/*     */   public void init(ContactSolverInfo paramContactSolverInfo, ConstraintSolver paramConstraintSolver, ObjectArrayList paramObjectArrayList, int paramInt, IDebugDraw paramIDebugDraw, Dispatcher paramDispatcher, zS paramzS)
/*     */   {
/* 407 */     this.solverInfo = paramContactSolverInfo;
/* 408 */     this.solver = paramConstraintSolver;
/* 409 */     this.sortedConstraints = paramObjectArrayList;
/* 410 */     this.numConstraints = paramInt;
/* 411 */     this.debugDrawer = paramIDebugDraw;
/* 412 */     this.dispatcher = paramDispatcher;
/*     */ 
/* 414 */     this.state = paramzS;
/*     */   }
/*     */ 
/*     */   public void processIsland(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, int paramInt4) {
/* 418 */     if (paramInt4 < 0)
/*     */     {
/* 423 */       this.solver.solveGroup(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, this.sortedConstraints, 0, this.numConstraints, this.solverInfo, this.debugDrawer, this.dispatcher); return;
/*     */     }
/*     */ 
/* 431 */     int i = -1;
/* 432 */     int j = 0;
/*     */ 
/* 436 */     for (int k = 0; k < this.numConstraints; k++) {
/* 437 */       if (ModifiedDynamicsWorld.access$000((TypedConstraint)this.sortedConstraints.getQuick(k)) == paramInt4)
/*     */       {
/* 440 */         i = k;
/* 441 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 445 */     for (; k < this.numConstraints; k++) {
/* 446 */       if (ModifiedDynamicsWorld.access$000((TypedConstraint)this.sortedConstraints.getQuick(k)) == paramInt4) {
/* 447 */         j++;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 452 */     if (paramInt3 + j > 0)
/* 453 */       this.solver.solveGroup(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, this.sortedConstraints, i, j, this.solverInfo, this.debugDrawer, this.dispatcher);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedDynamicsWorld.InplaceSolverIslandCallbackExt
 * JD-Core Version:    0.6.2
 */