/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*   4:    */import java.util.Comparator;
/*   5:    */
/* 484:    */final class ModifiedDynamicsWorld$1
/* 485:    */  implements Comparator
/* 486:    */{
/* 487:    */  public final int compare(TypedConstraint paramTypedConstraint1, TypedConstraint paramTypedConstraint2)
/* 488:    */  {
/* 489:489 */    paramTypedConstraint2 = ModifiedDynamicsWorld.access$000(paramTypedConstraint2);
/* 490:    */    
/* 491:491 */    if (ModifiedDynamicsWorld.access$000(paramTypedConstraint1) < paramTypedConstraint2) return -1; return 1;
/* 492:    */  }
/* 493:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedDynamicsWorld.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */